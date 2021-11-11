package org.computate.frFR.java;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	 * Var.enUS: classPageSimpleName
	 */
	protected String classePageNomSimple;

	/**
	 * Var.enUS: classPageSuperSimpleName
	 */
	protected String classePageSuperNomSimple;

	/**
	 * Var.enUS: classGenPageSimpleName
	 */
	protected String classeGenPageNomSimple;

	/**
	 * Var.enUS: classAttributeSimpleNamePages
	 */
	protected List<String> classeAttribuerNomSimplePages;

	protected List<String> classePageCheminsGen = new ArrayList<>();

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
	public Boolean ecrireFormEntite(String langueNom, ToutEcrivain wForm, String classeApiMethodeMethode) {
		int tIndex = 0;
		Boolean resultat = false;

		if(entiteHtml) {
			if(str_Recherche(langueNom).equals(classeApiMethodeMethode)) {
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
			else if(str_PUTFusion(langueNom).equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPUTFusion = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePUTFusion != rechercheLigneActuelPUTFusion) {
					if(rechercheLignePUTFusion != -1)
						wForm.tl(7, "</div>");
					wForm.tl(7, "<div class=\"w3-cell-row \">");
					rechercheLignePUTFusion = rechercheLigneActuelPUTFusion;
					resultat = true;
				}
			}
			else if(str_PUTCopie(langueNom).equals(classeApiMethodeMethode)) {
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
			
			wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", str_classeApiMethodeMethode(langueNom), "=\"", classeApiMethodeMethode, "\"}}");
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
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: appliNom
	 * r.enUS: appName
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
	public void pageCodeClasse(String langueNom) throws Exception {

		String classeVarClePrimaire = (String)classeDoc.get("classeVarClePrimaire"   + "_" + langueNom + "_stored_string");
		String classeGenPageChemin = (String)classeDoc.get("classeGenPageChemin"   + "_" + langueNom + "_stored_string");
		String classePageChemin = (String)classeDoc.get("classePageChemin"   + "_" + langueNom + "_stored_string");
		String classePageCheminCss = (String)classeDoc.get("classePageCheminCss"   + "_" + langueNom + "_stored_string");
		String classePageCheminJs = (String)classeDoc.get("classePageCheminJs"   + "_" + langueNom + "_stored_string");
		String classePageCheminHbs = (String)classeDoc.get("classePageCheminHbs"   + "_" + langueNom + "_stored_string");
		String classeGenPageCheminHbs = (String)classeDoc.get("classeGenPageCheminHbs"   + "_" + langueNom + "_stored_string");
		String classePageUriMethode = (String)classeDoc.get("classeApiUri"  + "_" + langueNom + "_stored_string");
		String classePageLangueNom = (String)classeDoc.get("classePageLangueNom"  + "_" + langueNom + "_stored_string");

		classePageNomSimple = (String)classeDoc.get("classePageNomSimple"   + "_" + langueNom + "_stored_string");
		classePageSuperNomSimple = (String)classeDoc.get("classePageSuperNomSimple"   + "_" + langueNom + "_stored_string");
		classeGenPageNomSimple = (String)classeDoc.get("classeGenPageNomSimple"   + "_" + langueNom + "_stored_string");
		String classePageNomCanonique = (String)classeDoc.get("classePageNomCanonique"   + "_" + langueNom + "_stored_string");
		classeAttribuerNomSimplePages = (List<String>)classeDoc.get("classeAttribuerNomSimplePages_" + langueNom + "_stored_strings");

		System.out.println(classeGenPageChemin + " " + classePageLangueNom + " " + langueNom);
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
							entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + str_Recherche(langueNom) + "_" + langueNom + "_stored_string");
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
									if(ecrireFormEntite(langueNom, wFormPOST, "POST"))
										resultatFormPOST = true;
									if(ecrireFormEntite(langueNom, wFormPage, "Page"))
										resultatFormPage = true;
								}
								if(entiteHtmlLigne != null && (entiteDefinir || entiteAttribuer)) {
//											if(ecrireFormEntite(langueNom, wFormPUTImport, "PUTImport"))
//												resultatFormPUTImport = true;
//											if(ecrireFormEntite(langueNom, wFormPUTFusion, str_PUTFusion(langueNom)))
//												resultatFormPUTFusion = true;
									if(ecrireFormEntite(langueNom, wFormPUTCopie, str_PUTCopie(langueNom)))
										resultatFormPUTCopie = true;
									if(ecrireFormEntite(langueNom, wFormPATCH, "PATCH"))
										resultatFormPATCH = true;
								}
								if(entiteIndexe) {
									if(ecrireFormEntite(langueNom, wFormRecherche, str_Recherche(langueNom)))
										resultatFormRecherche = true;
								}
							}
							if(entiteAttribuer) {
								wJsInit.tl(2, "{{#ifContainsAnyRoles roles ", str_rolesRequis(langueNom), "}}");
								wJsInit.tl(5, str_suggere(langueNom), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], $('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, true);");
								wJsInit.tl(2, "{{else}}");
								wJsInit.tl(5, str_suggere(langueNom), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], $('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, false);");
								wJsInit.tl(2, "{{/ifContainsAnyRoles}}");
//									wWebsocket.tl(2, "tl(2, \"", "await patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + \" + ", str_requeteSite(langueNom), "_.get", str_Requete(langueNom), StringUtils.capitalize(classeVarClePrimaire), "() + \" } ], {});\");");
								wPks.tl(2, "if(c == '", entiteAttribuerNomSimple, "')");
								wPks.tl(2, "await patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + pk2 } ], {});");
							}
							if(entiteSignature) {
								wJsInit.tl(2, "$('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature({'height':200}).bind('change', function(e){ patch{{", str_classeNomSimple(langueNom), "}}Val([{ name: 'fq', value: 'pk:' + pk }], 'set", entiteVarCapitalise, "', $('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature('getData', 'default'));");
							}
							if(entiteDefinir || entiteAttribuer || entiteIndexe || entiteStocke) {
								if("LocalDate".equals(entiteNomSimple)) {
									wWebsocketInput.tl(3, "var val = o['", entiteVar, "'];");
									wWebsocketInput.tl(3, "if(val != null) {");
									wWebsocketInput.tl(4, "var t = moment(val, 'YYYY-MM-DD');");
									wWebsocketInput.tl(4, "if(t)");
									wWebsocketInput.tl(5, "val = t.format('", str_DDDashMMDashYYYY(classePageLangueNom), "');");
									wWebsocketInput.tl(3, "}");
								}
								else if("LocalDateTime".equals(entiteNomSimple)) {
									wWebsocketInput.tl(3, "var val = o['", entiteVar, "'];");
									wWebsocketInput.tl(3, "if(val != null) {");
									wWebsocketInput.tl(4, "var t = moment(val, 'YYYY-MM-DD');");
									wWebsocketInput.tl(4, "if(t)");
									wWebsocketInput.tl(5, "val = t.format('", str_DDDashMMDashYYYY(classePageLangueNom), "');");
									wWebsocketInput.tl(3, "}");
								}
								else if("LocalTime".equals(entiteNomSimple)) {
									wWebsocketInput.tl(3, "var val = o['", entiteVar, "'];");
									wWebsocketInput.tl(3, "if(val != null) {");
									wWebsocketInput.tl(4, "var t = moment(val, 'HH:mm');");
									wWebsocketInput.tl(4, "if(t)");
									wWebsocketInput.tl(5, "val = t.format('", str_HAposhAposmm(classePageLangueNom), "');");
									wWebsocketInput.tl(3, "}");
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
									wWebsocketInput.tl(5, str_ajouterLueur(langueNom), "($('.signatureInput", classeNomSimple, "' + pk + '", entiteVarCapitalise, "'));");
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
								wWebsocketInput.tl(4, str_ajouterLueur(langueNom), "($('.input", classeNomSimple, "' + pk + '", entiteVarCapitalise, "'));");
								wWebsocketInput.tl(3, "}");
							}
						}
						rechercheSolr.setStart(i.intValue() + rechercheLignes);
						rechercheReponse = clientSolrComputate.query(rechercheSolr);
						rechercheListe = rechercheReponse.getResults();
					}

					wWebsocket.tl(1, "var pk = ", str_requeteApi(langueNom), "['pk'];");
					wWebsocket.tl(1, "var pks = ", str_requeteApi(langueNom), "['pks'];");
					wWebsocket.tl(1, "var classes = ", str_requeteApi(langueNom), "['classes'];");
					wWebsocket.tl(1, "var vars = ", str_requeteApi(langueNom), "['vars'];");
					wWebsocket.tl(1, "var empty = ", str_requeteApi(langueNom), "['empty'];");
					wWebsocket.l();
					wWebsocket.tl(1, "if(pk != null) {");
					wWebsocket.tl(2, str_rechercher(langueNom), classeNomSimple, "Vals([ {name: 'fq', value: '", classeVarClePrimaire, ":' + pk} ], function( data, textStatus, jQxhr ) {");
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
//						wWebsocket.tl(3, "await patch{{", str_classeNomSimple(langueNom), "}}Vals( [ {name: 'fq', value: '", classeVarClePrimaire, ":' + pk} ], {});");
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

				auteurPageClasse.l(" * ", str_Traduire(langueNom), ": false");
				for(String langueNom2 : autresLangues) {
					String classePageNomSimple2 = (String)classeDoc.get("classePageNomCanonique" + str_PageRecherche(langueNom2)  + "_" + langueNom2 + "_stored_string");
					if(classePageNomSimple2 != null)
						auteurPageClasse.	l(" * ", str_NomCanonique(langueNom), ".", langueNom2, ": ", classePageNomSimple2);
				}
				auteurPageClasse.l(" **/");
				auteurPageClasse.s("public class ", classePageNomSimple);
				auteurPageClasse.s(" extends ", classePageNomSimple, "Gen<", classeGenPageNomSimple, ">");
				auteurPageClasse.l(" {");
				auteurPageClasse.tl(0, "}");
			}

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
			l(" * ", str_Traduire(langueNom), ": false");
			for(String langueNom2 : autresLangues) {
				String classeGenPageNomSimple2 = (String)classeDoc.get("classeGenPageNomCanonique" + str_PageRecherche(langueNom2)  + "_" + langueNom2 + "_stored_string");
				if(classeGenPageNomSimple2 != null)
					l(" * ", str_NomCanonique(langueNom), ".", langueNom2, ": ", classeGenPageNomSimple2);
			}
			l(" **/");
			s("public class ", classeGenPageNomSimple);
			s(" extends ", classeGenPageNomSimple, "Gen");
			s("<", (classePageSuperNomSimple == null ? "Object" : classePageSuperNomSimple), ">");
			l(" {");

			if(classePageSuperNomSimple == null) {
				l();
				tl(1, "/**");
				tl(1, " * ", str_Ignorer(langueNom), ": true");
				tl(1, "**/");
				tl(1, "protected void _", str_requeteSite(langueNom), "_(", "", classePartsCouverture.nomSimple(langueNom), "<", classePartsRequeteSite.nomSimple(langueNom), "> c", ") {");
				tl(1, "}");
			}

			l();
			tl(1, "/**");
			tl(1, " * {@inheritDoc}");
			tl(1, " * ", str_Ignorer(langueNom), ": true");
			tl(1, " **/");
			tl(1, "protected void _", str_listeRecherche(langueNom), classeNomSimple, "_(", classePartsCouverture.nomSimple(langueNom), "<", str_ListeRecherche(langueNom), "<", classeNomSimple, ">> ", str_cVar(langueNom), ") {");
			tl(1, "}");
			l();
			tl(1, "/**");
			tl(1, " * {@inheritDoc}");
			tl(1, " **/");
			tl(1, "protected void _", str_liste(langueNom), classeNomSimple, "(JsonArray l) {");
			tl(2, "Optional.ofNullable(", str_listeRecherche(langueNom), classeNomSimple, "_).map(o -> o.get", str_Liste(langueNom), "()).orElse(Arrays.asList()).stream().map(o -> JsonObject.mapFrom(o)).forEach(o -> l.add(o));");
			tl(1, "}");
			l();
			tl(1, "protected void _", uncapitalizeClasseNomSimple, "Count(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", str_cVar(langueNom), ") {");
			tl(2, str_cVar(langueNom), ".o(", str_listeRecherche(langueNom), classeNomSimple, "_ == null ? 0 : ", str_listeRecherche(langueNom), classeNomSimple, "_.size());");
			tl(1, "}");
			l();
			tl(1, "protected void _", uncapitalizeClasseNomSimple, "_(", "", classePartsCouverture.nomSimple(langueNom), "<", classeNomSimple, "> ", str_cVar(langueNom), ") {");
			if(classePageSimple) {
				tl(2, str_cVar(langueNom), ".o(new ", classeNomSimple, "());");
			} else {
				tl(2, "if(", uncapitalizeClasseNomSimple, "Count == 1)");
				tl(3, str_cVar(langueNom), ".o(", str_listeRecherche(langueNom), classeNomSimple, "_.get(0));");
			}
			tl(1, "}");
			l();
			tl(1, "protected void _", classeVarClePrimaire, "(", classePartsCouverture.nomSimple(langueNom), "<Long> ", str_cVar(langueNom), ") {");
			tl(2, "if(", uncapitalizeClasseNomSimple, "Count == 1)");
			tl(3, str_cVar(langueNom), ".o(", uncapitalizeClasseNomSimple, "_.get", StringUtils.capitalize(classeVarClePrimaire), "());");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null) {
				tl(1, "@Override");
			} else {
				tl(1, "/**");
				tl(1, " * ", str_Ignorer(langueNom), ": true");
				tl(1, "**/");
			}
			tl(1, "protected void _promise", str_Avant(langueNom), "(Promise<Void> promise) {");
			tl(2, "promise.complete();");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _", str_classeNomSimple(classePageLangueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> ", str_cVar(langueNom), ") {");
			tl(2, str_cVar(langueNom), ".o(\"", classeNomSimple, "\");");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _page", str_Titre(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
			if(classeVarTitre != null) {
				tl(2, "if(", uncapitalizeClasseNomSimple, "_ != null && ", uncapitalizeClasseNomSimple, "_.get", StringUtils.capitalize(classeVarTitre), "() != null)");
				tl(3, "c.o(", uncapitalizeClasseNomSimple, "_.get", StringUtils.capitalize(classeVarTitre), "()", ");");
				tl(2, "else if(", uncapitalizeClasseNomSimple, "_ != null)");
			} else {
				tl(2, "if(", uncapitalizeClasseNomSimple, "_ != null)");
			}
			tl(3, "c.o(", q(contexteTitre), ");");
			if(!classePageSimple) {
				tl(2, "else if(", str_listeRecherche(langueNom), classeNomSimple, "_ == null || ", uncapitalizeClasseNomSimple, "Count == 0)");
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
			tl(1, "protected void _", str_roles(langueNom), "(List<String> l) {");
			tl(2, "if(", str_requeteSite(langueNom), "_ != null) {");
			tl(3, "l.addAll(Stream.concat(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "().stream(), ", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "().stream()).distinct().collect(Collectors.toList()));");
			tl(2, "}");
			tl(1, "}");
			if(classeRolesTrouves) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", str_rolesRequis(langueNom), "(List<String> l) {");
				tl(2, "l.addAll(Optional.ofNullable(siteRequest_.getConfig().getJsonArray(", classePartsConfigCles.nomSimple(langueNom), ".", str_AUTH_ROLES_REQUIS(langueNom), " + \"_", classeNomSimple, "\")).orElse(new JsonArray()).stream().map(o -> o.toString()).collect(Collectors.toList()));");
				tl(1, "}");
			}
			if(classeRoleLiresTrouves) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", str_rolesPourLires(langueNom), "(List<String> l) {");
				tl(2, "l.addAll(Optional.ofNullable(siteRequest_.getConfig().getJsonArray(", classePartsConfigCles.nomSimple(langueNom), ".", str_AUTH_ROLES_LIRE_REQUIS(langueNom), " + \"_", classeNomSimple, "\")).orElse(new JsonArray()).stream().map(o -> o.toString()).collect(Collectors.toList()));");
				tl(1, "}");
			}

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _pagination(JsonObject pagination) {");
			tl(2, "JsonArray pages = new JsonArray();");
			tl(2, "Long ", str_debut(langueNom), " = ", str_listeRecherche(langueNom), classeNomSimple, "_.getStart().longValue();");
			tl(2, "Long ", str_lignes(langueNom), " = ", str_listeRecherche(langueNom), classeNomSimple, "_.getRows().longValue();");
			tl(2, "Long ", str_numTrouve(langueNom), " = ", str_listeRecherche(langueNom), classeNomSimple, "_.getQueryResponse().getResults().getNumFound();");
			tl(2, "Long ", str_debut(langueNom), "Num = ", str_debut(langueNom), " + 1L;");
			tl(2, "Long ", str_fin(langueNom), "Num = ", str_debut(langueNom), " + ", str_lignes(langueNom), ";");
			tl(2, "Long floorMod = Math.floorMod(", str_numTrouve(langueNom), ", ", str_lignes(langueNom), ");");
			tl(2, "Long ", str_dernier(langueNom), " = Math.floorDiv(", str_numTrouve(langueNom), ", ", str_lignes(langueNom), ") - (floorMod.equals(0L) ? 1L : 0L) * ", str_lignes(langueNom), ";");
			tl(2, str_fin(langueNom), "Num = ", str_fin(langueNom), "Num < ", str_numTrouve(langueNom), " ? ", str_fin(langueNom), "Num : ", str_numTrouve(langueNom), ";");
			tl(2, str_debut(langueNom), "Num = ", str_numTrouve(langueNom), " == 0L ? 0L : ", str_debut(langueNom), "Num;");
			tl(2, "Long pagination", str_Debut(langueNom), " = ", str_debut(langueNom), " - 10L * ", str_lignes(langueNom), ";");
			tl(2, "if(pagination", str_Debut(langueNom), " < 0L)");
			tl(3, "pagination", str_Debut(langueNom), " = 0L;");
			tl(2, "Long pagination", str_Fin(langueNom), " = ", str_debut(langueNom), " + 10L * ", str_lignes(langueNom), ";");
			tl(2, "if(pagination", str_Fin(langueNom), " > ", str_numTrouve(langueNom), ")");
			tl(3, "pagination", str_Fin(langueNom), " = ", str_numTrouve(langueNom), ";");
			l();
			tl(2, "pagination.put(\"1L\", 1L);");
			tl(2, "pagination.put(\"0L\", 0L);");
			tl(2, "pagination.put(\"", str_debut(langueNom), "\", ", str_debut(langueNom), ");");
			tl(2, "pagination.put(\"", str_lignes(langueNom), "\", ", str_lignes(langueNom), ");");
			tl(2, "pagination.put(\"", str_lignes(langueNom), str_Precedent(langueNom), "\", ", str_lignes(langueNom), " / 2);");
			tl(2, "pagination.put(\"", str_lignes(langueNom), str_Prochaine(langueNom), "\", ", str_lignes(langueNom), " * 2);");
			tl(2, "pagination.put(\"", str_debut(langueNom), "Num\", ", str_debut(langueNom), "Num);");
			tl(2, "pagination.put(\"", str_fin(langueNom), "Num\", ", str_fin(langueNom), "Num);");
			tl(2, "pagination.put(\"", str_numTrouve(langueNom), "\", ", str_numTrouve(langueNom), ");");
			tl(2, "pagination.put(\"page", str_Debut(langueNom), "\", new JsonObject().put(\"", str_debut(langueNom), "\", 0L).put(\"page", str_Numero(langueNom), "\", 1L));");
			tl(2, "if(", str_debut(langueNom), " > 0L)");
			tl(3, "pagination.put(\"page", str_Precedent(langueNom), "\", new JsonObject().put(\"", str_debut(langueNom), "\", ", str_debut(langueNom), " - ", str_lignes(langueNom), ").put(\"page", str_Numero(langueNom), "\", ", str_debut(langueNom), " - ", str_lignes(langueNom), " + 1L));");
			tl(2, "if(", str_debut(langueNom), " + ", str_lignes(langueNom), " < ", str_numTrouve(langueNom), ")");
			tl(3, "pagination.put(\"page", str_Prochaine(langueNom), "\", new JsonObject().put(\"", str_debut(langueNom), "\", ", str_debut(langueNom), " + ", str_lignes(langueNom), ").put(\"page", str_Numero(langueNom), "\", ", str_debut(langueNom), " + ", str_lignes(langueNom), " + 1L));");
			tl(2, "pagination.put(\"page", str_Fin(langueNom), "\", new JsonObject().put(\"", str_debut(langueNom), "\", ", str_dernier(langueNom), ").put(\"page", str_Numero(langueNom), "\", ", str_dernier(langueNom), " + 1L));");
			tl(2, "pagination.put(\"pages\", pages);");
			tl(2, "for(Long i = pagination", str_Debut(langueNom), "; i < pagination", str_Fin(langueNom), "; i += ", str_lignes(langueNom), ") {");
			tl(3, "Long page", str_Numero(langueNom), " = Math.floorDiv(i, ", str_lignes(langueNom), ") + 1L;");
			tl(3, "JsonObject page = new JsonObject();");
			tl(3, "page.put(\"page", str_Numero(langueNom), "\", page", str_Numero(langueNom), ");");
			tl(3, "page.put(\"", str_pageActuel(langueNom), "\", ", str_debut(langueNom), ".equals(i));");
			tl(3, "page.put(\"", str_debut(langueNom), "\", i);");
			tl(3, "pages.add(page);");
			tl(2, "}");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _query(JsonObject query) {");
			tl(2, "ServiceRequest ", str_requeteService(langueNom), " = ", str_requeteSite(langueNom), "_.getServiceRequest();");
			tl(2, "JsonObject params = ", str_requeteService(langueNom), ".getParams();");
			l();
			tl(2, "JsonObject queryParams = Optional.ofNullable(", str_requeteService(langueNom), ").map(ServiceRequest::getParams).map(or -> or.getJsonObject(\"query\")).orElse(new JsonObject());");
			tl(2, "Long num = ", str_listeRecherche(langueNom), classeNomSimple, "_.getQueryResponse().getResults().getNumFound();");
			tl(2, "String q = \"*:*\";");
			tl(2, "String q1 = \"", classeVarTexte, "\";");
			tl(2, "String q2 = \"\";");
			tl(2, "for(String param", str_Nom(langueNom), " : queryParams.fieldNames()) {");
			tl(3, "String ", str_entite(langueNom), "Var = null;");
			tl(3, "String ", str_valeur(langueNom), str_Indexe(langueNom), " = null;");
			tl(3, "Object param", str_ValeursObjet(langueNom), " = queryParams.getValue(param", str_Nom(langueNom), ");");
			tl(3, "JsonArray param", str_Objets(langueNom), " = param", str_ValeursObjet(langueNom), " instanceof JsonArray ? (JsonArray)param", str_ValeursObjet(langueNom), " : new JsonArray().add(param", str_ValeursObjet(langueNom), ");");
			l();
			tl(3, "try {");
			tl(4, "for(Object param", str_Objet(langueNom), " : param", str_Objets(langueNom), ") {");
			tl(5, "switch(param", str_Nom(langueNom), ") {");
			tl(5, "case \"q\":");
			tl(6, "q = (String)param", str_Objet(langueNom), ";");
			tl(6, str_entite(langueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(langueNom), ", \":\"));");
			tl(6, str_valeur(langueNom), str_Indexe(langueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(langueNom), ", \":\")), \"UTF-8\");");
			tl(6, "q1 = ", str_entite(langueNom), "Var.equals(\"*\") ? q1 : ", str_entite(langueNom), "Var;");
			tl(6, "q2 = ", str_valeur(langueNom), str_Indexe(langueNom), ";");
			tl(6, "q = q1 + \":\" + q2;");
			tl(5, "}");
			tl(4, "}");
			tl(3, "} catch(Exception e) {");
			tl(4, "ExceptionUtils.rethrow(e);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "query.put(\"q\", q);");
			l();
			tl(2, "Integer rows1 = Optional.ofNullable(", str_listeRecherche(langueNom), classeNomSimple, "_).map(l -> l.getRows()).orElse(10);");
			tl(2, "Integer start1 = Optional.ofNullable(", str_listeRecherche(langueNom), classeNomSimple, "_).map(l -> l.getStart()).orElse(1);");
			tl(2, "Integer start2 = start1 - rows1;");
			tl(2, "Integer start3 = start1 + rows1;");
			tl(2, "Integer rows2 = rows1 / 2;");
			tl(2, "Integer rows3 = rows1 * 2;");
			tl(2, "start2 = start2 < 0 ? 0 : start2;");
			tl(2, "JsonArray fqs = new JsonArray();");
			tl(2, "for(String fq : Optional.ofNullable(", str_listeRecherche(langueNom), classeNomSimple, "_).map(l -> l.getFilterQueries()).orElse(new String[0])) {");
			tl(3, "if(!StringUtils.contains(fq, \"(\")) {");
			tl(4, "String fq1 = StringUtils.substringBefore(fq, \"_\");");
			tl(4, "String fq2 = StringUtils.substringAfter(fq, \":\");");
			tl(4, "if(!StringUtils.startsWithAny(fq, \"", str_classeNomsCanoniques(langueNom), "_\", \"", str_archive(langueNom), "_\", \"", str_supprime(langueNom), "_\", \"sessionId\", \"", str_utilisateur(langueNom), str_Cle(langueNom), "s\"))");
			tl(5, "fqs.add(new JsonObject().put(\"var\", fq1).put(\"val\", fq2));");
			tl(4, "}");
			tl(3, "}");
			tl(2, "query.put(\"fq\", fqs);");
			l();
			tl(2, "JsonArray sorts = new JsonArray();");
			tl(2, "for(SortClause sort : Optional.ofNullable(", str_listeRecherche(langueNom), classeNomSimple, "_).map(l -> l.getSorts()).orElse(Arrays.asList())) {");
			tl(3, "sorts.add(new JsonObject().put(\"var\", StringUtils.substringBefore(sort.getItem(), \"_\")).put(\"order\", sort.getOrder().name()));");
			tl(2, "}");
			tl(2, "query.put(\"sort\", sorts);");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null) {
				tl(1, "@Override");
			} else {
				tl(1, "/**");
				tl(1, " * ", str_Ignorer(langueNom), ": true");
				tl(1, "**/");
			}
			tl(1, "protected void _promise", str_Apres(langueNom), "(Promise<Void> promise) {");
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
				tl(1, "protected void _pageImage", str_Largeur(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
				tl(3, "c.o(", contexteImageLargeur, ");");
				tl(1, "}");
			}

			if(contexteImageHauteur != null) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageImage", str_Hauteur(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
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
				tl(1, "protected void _", str_contexte(langueNom), str_Icone(langueNom), str_Groupe(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(contexteIconeGroupe), ");");
				tl(1, "}");
			}

			if(StringUtils.isNotBlank(contexteIconeNom)) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", str_contexte(langueNom), str_Icone(langueNom), str_Nom(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(contexteIconeNom), ");");
				tl(1, "}");
			}
//
//			l();
//			if(classePageSuperNomSimple != null)
//				tl(1, "@Override");
//			tl(1, "public void ", str_promesseLoin(langueNom), classeGenPageNomSimple, "() {");
//			tl(2, str_promesseLoin(langueNom), classeGenPageNomSimple, "();");
////				tl(2, "super.", str_initLoin(langueNom), classePartsMiseEnPage.nomSimple(langueNom), "();");
//			tl(1, "}");

			if(StringUtils.isNotBlank(classeApiUri)) {
				l();
				tl(1, "protected void _pageUri", classeNomSimple, "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
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
				rechercheSolr.addFilterQuery("entiteHtmlColonne_indexed_double:[* TO *]");
				rechercheSolr.addSort("entiteHtmlColonne_indexed_double", ORDER.asc);
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
//								wGetters.tl(1, "public Boolean get", str_Colonne(langueNom), entiteVarCapitalise, "() {");
//								wGetters.tl(2, "return true;");
//								wGetters.tl(1, "}");

								wTh.tl(4, "<th>", entiteNomAffichage, "</th>");
	
//								wTd.tl(4, "{{#if get", str_Colonne(langueNom), entiteVarCapitalise, "}}");
								wTd.tl(5, "<td>");
								wTd.tl(6, "<a href=\"{{", classeVarUrlPk, "}}\">");
								if(contexteIconeGroupe != null && contexteIconeNom != null && BooleanUtils.isTrue(entiteVarTitre))
									wTd.tl(7, "<i class=\"fa", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, " \"></i>");
								wTd.t(7, "<span class=\"white-space-pre-wrap \">");
								if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
									wTd.s("{{siteZonedDateTimeFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
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

							wFoot.tl(3, "{{#if get", str_Colonne(langueNom), entiteVarCapitalise, "}}");
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
									wRecherche.tl(2, "var $", str_filtre(langueNom), entiteVarCapitalise, "Checkbox = $", str_formulaireFiltres(langueNom), ".find('input.", str_valeur(langueNom), entiteVarCapitalise, "[type = \"checkbox\"]');");
									wRecherche.tl(2, "var $", str_filtre(langueNom), entiteVarCapitalise, "Select = $", str_formulaireFiltres(langueNom), ".find('select.", str_valeur(langueNom), entiteVarCapitalise, "');");
									wRecherche.tl(2, "var ", str_filtre(langueNom), entiteVarCapitalise, " = $", str_filtre(langueNom), entiteVarCapitalise, "Select.length ? $", str_filtre(langueNom), entiteVarCapitalise, "Select.val() : $", str_filtre(langueNom), entiteVarCapitalise, "Checkbox.prop('checked');");

									wRecherche.tl(2, "var ", str_filtre(langueNom), entiteVarCapitalise, "SelectVal = $", str_formulaireFiltres(langueNom), ".find('select.", str_filtre(langueNom), entiteVarCapitalise, "').val();");
									wRecherche.tl(2, "var ", str_filtre(langueNom), entiteVarCapitalise, " = null;");
									wRecherche.tl(2, "if(", str_filtre(langueNom), entiteVarCapitalise, "SelectVal !== '')");
									wRecherche.tl(3, str_filtre(langueNom), entiteVarCapitalise, " = ", str_filtre(langueNom), entiteVarCapitalise, "SelectVal == 'true';");
								}
								else {
									wRecherche.tl(2, "var ", str_filtre(langueNom), entiteVarCapitalise, " = $", str_formulaireFiltres(langueNom), ".find('.", str_valeur(langueNom), entiteVarCapitalise, "').val();");
								}

								if("Boolean".equals(entiteNomSimple))
									wRecherche.tl(2, "if(", str_filtre(langueNom), entiteVarCapitalise, " != null && ", str_filtre(langueNom), entiteVarCapitalise, " === true)");
								else
									wRecherche.tl(2, "if(", str_filtre(langueNom), entiteVarCapitalise, " != null && ", str_filtre(langueNom), entiteVarCapitalise, " !== '')");

								wRecherche.tl(3, str_filtres(langueNom), ".push({ name: '", (entiteSuggere ? "q" : "fq"), "', value: '", entiteVar, ":' + ", str_filtre(langueNom), entiteVarCapitalise, " });");
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
										wPOST.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.", str_valeur(langueNom), entiteVarCapitalise, "').val();");
										wPOST.tl(1, "if(", str_valeur(langueNom), entiteVarCapitalise, " != null && ", str_valeur(langueNom), entiteVarCapitalise, " !== '')");
										if("Boolean".equals(entiteNomSimple)) {
											wPOST.tl(2, "vals['", entiteVar, "'] = ", str_valeur(langueNom), entiteVarCapitalise, " == 'true';");
										} else {
											wPOST.tl(2, "vals['", entiteVar, "'] = ", str_valeur(langueNom), entiteVarCapitalise, ";");
										}
									}
									else {
										wPOST.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, " = [];");
										wPOST.tl(1, "$", str_formulaireValeurs(langueNom), ".find('input.", str_valeur(langueNom), entiteVarCapitalise, ":checked').each(function(index) {");
										wPOST.tl(2, str_valeur(langueNom), entiteVarCapitalise, ".push($(this).val());");
										wPOST.tl(1, "});");
										wPOST.tl(1, "if(", str_valeur(langueNom), entiteVarCapitalise, ".length > 0)");
										wPOST.tl(2, "vals['", entiteVar, "'] = ", str_valeur(langueNom), entiteVarCapitalise, ";");
									}
								}
								else {
									wPOST.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.", str_valeur(langueNom), entiteVarCapitalise, "').val();");
									wPOST.tl(1, "if(", str_valeur(langueNom), entiteVarCapitalise, " != null && ", str_valeur(langueNom), entiteVarCapitalise, " !== '')");
									if("Boolean".equals(entiteNomSimple)) {
										wPOST.tl(2, "vals['", entiteVar, "'] = ", str_valeur(langueNom), entiteVarCapitalise, " == 'true';");
									} else {
										wPOST.tl(2, "vals['", entiteVar, "'] = ", str_valeur(langueNom), entiteVarCapitalise, ";");
									}
								}
	
								wPUTCopie.l();
								if(entiteAttribuer)
									wPUTCopie.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('input.", str_valeur(langueNom), entiteVarCapitalise, ":checked').val();");
								else
									wPUTCopie.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.", str_valeur(langueNom), entiteVarCapitalise, "').val();");
								if(entiteAttribuer) {
									wPUTCopie.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, str_Vider(langueNom), " = $", str_formulaireValeurs(langueNom), ".find('input.", entiteVar, "_", str_vider(langueNom), ":checked').val();");
									wPUTCopie.tl(1, "if(", str_valeur(langueNom), entiteVarCapitalise, str_Vider(langueNom), " != null && ", str_valeur(langueNom), entiteVarCapitalise, str_Vider(langueNom), ")");
									wPUTCopie.tl(2, "vals['", entiteVar, "'] = null;");
									wPUTCopie.tl(1, "else if(", str_valeur(langueNom), entiteVarCapitalise, " != null && ", str_valeur(langueNom), entiteVarCapitalise, ")");
									if(entiteListeTypeJson == null) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, str_valeur(langueNom), entiteVarCapitalise, valSuffixe, ";");
									}
									else {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = [", valPrefixe, str_valeur(langueNom), entiteVarCapitalise, valSuffixe, "];");
									}
								} else {
									wPUTCopie.tl(1, "if(", str_valeur(langueNom), entiteVarCapitalise, " != null && ", str_valeur(langueNom), entiteVarCapitalise, " !== '')");
									if("Boolean".equals(entiteNomSimple)) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, str_valeur(langueNom), entiteVarCapitalise, valSuffixe, " == 'true';");
									} else {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, str_valeur(langueNom), entiteVarCapitalise, valSuffixe, ";");
									}
								}
	
								wPATCH.l();
								if(entiteAttribuer)
									wPATCH.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('input.", str_valeur(langueNom), entiteVarCapitalise, ":checked').val();");
								else
									wPATCH.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.", str_valeur(langueNom), entiteVarCapitalise, "').val();");
								if(entiteAttribuer) {
									wPATCH.tl(1, "if(", str_valeur(langueNom), entiteVarCapitalise, " != null && ", str_valeur(langueNom), entiteVarCapitalise, " !== '')");
									if(entiteListeTypeJson == null) {
										wPATCH.tl(2, "vals['set", entiteVarCapitalise, "'] = ", valPrefixe, str_valeur(langueNom), entiteVarCapitalise, valSuffixe, ";");
									}
									else {
										wPATCH.tl(2, "vals['add", entiteVarCapitalise, "'] = ", valPrefixe, str_valeur(langueNom), entiteVarCapitalise, valSuffixe, ";");
									}
								} else {

									wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.remove", entiteVarCapitalise, "').val() === 'true';");

									if("Boolean".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, "SelectVal = $", str_formulaireValeurs(langueNom), ".find('select.set", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, " = null;");
										wPATCH.tl(1, "if(", str_valeur(langueNom), entiteVarCapitalise, "SelectVal != null && ", str_valeur(langueNom), entiteVarCapitalise, "SelectVal !== '')");
										wPATCH.tl(2, str_valeur(langueNom), entiteVarCapitalise, " = ", str_valeur(langueNom), entiteVarCapitalise, "SelectVal == 'true';");
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : ", str_valeur(langueNom), entiteVarCapitalise, ";");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.add", entiteVarCapitalise, "').prop('checked');");
									}
									else if("LocalDate".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", str_formulaireValeurs(langueNom), ".find('.set", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.add", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var setMoment = set", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ", '", str_DDDashMMDashYYYY(langueNom), "') : null; ");
										wPATCH.tl(1, "var addMoment = add", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "add", entiteVarCapitalise, valSuffixe, ", '", str_DDDashMMDashYYYY(langueNom), "') : null; ");
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
//													t(tIndex + 5).s(classePrefixe).s(" class=\"\"w3-input w3-border datepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\"");
//													t(tIndex + 5).dal("placeholder", str_DDDashMMDashYYYY_HHColonMM(langueNom));
//													t(tIndex + 5).dal("data-timeformat", str_ddDashMMDashyyyy(langueNom));
//													t(tIndex + 5).l(" id=\"", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\"");
//													if(entiteDescription != null)
//														t(tIndex + 5).dal("title", entiteDescription + " (", str_DDDashMMDashYYYY(langueNom), ")");
//													tl(tIndex + 4, " value=\"", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", str_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV(langueNom), "\".format(", entiteVar, "));");
//													t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
//													t(tIndex + 4).l("a(\"onclick\", \"", str_enleverLueur(langueNom), "($(this)); \");");
//													t(tIndex + 4).s("a(\"onchange\", \"");
//														s("var t = moment(this.value, '", str_DDDashMMDashYYYY(langueNom), "'); ");
//														s("if(t) { ");
//															s("var s = t.format('YYYY-MM-DD'); ");
//															s("patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', s, function() { ", str_ajouterLueur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }); ");
//														s("} ");
//													l("\");");
//													t(tIndex + 3).l("}");
//													tl(tIndex + 3, "/>");
//												}
//												else if("LocalTime".equals(entiteNomSimple)) {
//													t(tIndex + 3).s(classePrefixe, "<input").l();
//													t(tIndex + 5).dal("type", "text");
//													t(tIndex + 5).s(classePrefixe).s(" class=\"\"w3-input w3-border timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\"");
//													t(tIndex + 5).dal("placeholder", str_HHColonMM(langueNom));
//													t(tIndex + 5).l(" id=\"", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\"");
//													if(entiteDescription != null)
//														t(tIndex + 5).da("title", entiteDescription + " (", str_HAposhAposmm(langueNom), ")");
//													tl(tIndex + 5, " value=\"", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", str_HAposhAposmm(langueNom), "\".format(", entiteVar, "));");
//													t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
//													t(tIndex + 4).l("a(\"onclick\", \"", str_enleverLueur(langueNom), "($(this)); \");");
//													t(tIndex + 4).s("a(\"onchange\", \"");
//														s("var t = moment(this.value, '", str_HAposhAposmm(langueNom), "'); ");
//														s("if(t) { ");
//															s("var s = t.format('HH:mm'); ");
//															s("patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', s, function() { ", str_ajouterLueur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }); ");
//														s("} ");
//													l("\");");
//													t(tIndex + 3).l("}");
//													tl(tIndex + 3, "/>");
//												}
									else {
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", str_formulaireValeurs(langueNom), ".find('.set", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.add", entiteVarCapitalise, "').val();");
									}
									wPATCH.tl(1, "if(remove", entiteVarCapitalise, " || set", entiteVarCapitalise, " != null && set", entiteVarCapitalise, " !== '')");
									wPATCH.tl(2, "vals['set", entiteVarCapitalise, "'] = ", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ";");
									wPATCH.tl(1, "if(add", entiteVarCapitalise, " != null && add", entiteVarCapitalise, " !== '')");
									wPATCH.tl(2, "vals['add", entiteVarCapitalise, "'] = ", valPrefixe, "add", entiteVarCapitalise, valSuffixe, ";");
									if("Boolean".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.remove", entiteVarCapitalise, "').prop('checked');");
									} else {
										wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.remove", entiteVarCapitalise, "').val();");
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
					l("{{#partial \"htmBody", str_Debut(langueNom), "\"}}{{> htmBody", str_Debut(langueNom), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", str_Fin(langueNom), "\"}}{{> htmBody", str_Fin(langueNom), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody\"}}{{> htmBody", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBodyCount0\"}}{{> htmBodyCount0", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBodyCount1", str_Tous(langueNom), "\"}}{{> htmBodyCount1", str_Tous(langueNom), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBodyCount1\"}}{{> htmBodyCount1", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", str_Tous(langueNom), "\"}}{{> htmBody", str_Tous(langueNom), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htm", str_Formulaire(langueNom), "\"}}{{> htm", str_Formulaire(langueNom), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htm", str_Formulaires(langueNom), "\"}}{{> htm", str_Formulaires(langueNom), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", str_Suggere(langueNom), "\"}}{{> htmBody", str_Suggere(langueNom), classePageNomSimple, "}}{{/partial}}");
					for(String classeApiMethode : classeApiMethodes) {
						String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");
		
						if(classeApiMethode.equals(str_PageRecherche(langueNom)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals(str_PUTCopie(langueNom)) || classeApiMethode.equals(str_PUTFusion(langueNom)) || classeApiMethode.equals("PUTImport")) {
							l("{{#partial \"htm", str_Formulaire(langueNom), "_", classeApiOperationIdMethode, "\"}}{{> htm", str_Formulaire(langueNom), classePageNomSimple, "_", classeApiOperationIdMethode, "}}{{/partial}}");
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
				s("{{#if ", str_listeRecherche(langueNom), classeNomSimple, "_}}");
				s("{{#eq ", uncapitalizeClasseNomSimple, "Count int1}}");
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
			tl(2, "<script src=\"{{", str_statiqueUrlBase(langueNom), "}}/js/", langueNom, "/", classePageNomSimple, ".js\"></script>");
			if(classeAttribuerNomSimplePages != null) {
				for(String classeAttribuerNomSimplePage : classeAttribuerNomSimplePages) {
					t(2).l("<script src=\"{{", str_statiqueUrlBase(langueNom), "}}/js/", langueNom, "/", classeAttribuerNomSimplePage, ".js\"></script>");
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
						Boolean methodePUTFusion = classeApiMethode.equals(str_PUTFusion(langueNom));
						Boolean methodePUTCopie = classeApiMethode.equals(str_PUTCopie(langueNom));
						Boolean methodePATCH = classeApiMethodeMethode.equals("PATCH");
						Boolean methodeDELETE = classeApiMethodeMethode.equals("DELETE");
						Boolean methodeRecherche = classeApiMethode.contains(str_Recherche(langueNom));
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
							auteurPageJs.s("$", str_formulaireValeurs(langueNom));
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						}
						else if(methodePUTImport) {
							auteurPageJs.s("$", str_formulaireValeurs(langueNom), ", ", classeVarClePrimaire, ", success, error");
						}
						else if(methodePUTFusion) {
							auteurPageJs.s("$", str_formulaireValeurs(langueNom), ", ", classeVarClePrimaire, ", success, error");
						}
						else if(methodePUTCopie) {
							auteurPageJs.s("$", str_formulaireValeurs(langueNom), ", ", classeVarClePrimaire, ", success, error");
						}
						else if(methodePATCH)
							auteurPageJs.s("$", str_formulaireFiltres(langueNom), ", $", str_formulaireValeurs(langueNom), ", ", classeVarClePrimaire, ", success, error");
						else if(methodeRecherche) {
							auteurPageJs.s("$", str_formulaireFiltres(langueNom), "");
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
							auteurPageJs.tl(3, str_ajouterLueur(langueNom), "($", str_formulaireValeurs(langueNom), ".next('button'));");
							if(classeVarUrlPk != null) {
								auteurPageJs.tl(3, "var url = data['", classeVarUrlPk, "'];");
								auteurPageJs.tl(3, "if(url)");
								auteurPageJs.tl(4, "window.location.href = url;");
							}
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "if(error == null) {");
							auteurPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {");
							auteurPageJs.tl(3, str_ajouterErreur(langueNom), "($", str_formulaireValeurs(langueNom), ".next('button'));");
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.s(wPOST);
							auteurPageJs.l();
						}
						else if(methodePUTImport) {
							auteurPageJs.tl(1, "var json = $", str_formulaireValeurs(langueNom), ".find('.", "PUTImport", "_", str_listeRecherche(langueNom), "').val();");
							auteurPageJs.tl(1, "if(json != null && json !== '')");
							auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), success, error);");
						}
						else if(methodePUTFusion) {
							auteurPageJs.tl(1, "var json = $", str_formulaireValeurs(langueNom), ".find('.", str_PUTFusion(langueNom), "_", str_listeRecherche(langueNom), "').val();");
							auteurPageJs.tl(1, "if(json != null && json !== '')");
							auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), success, error);");
						}
						else if(methodePUTCopie) {
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.s(wPUTCopie);
							auteurPageJs.l();
						}
						else if(methodePATCH) {
							auteurPageJs.tl(1, "var ", str_filtres(langueNom), " = ", classeApiOperationIdMethode,str_Filtres(langueNom), "($", str_formulaireFiltres(langueNom), ");");
							auteurPageJs.l();
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.s(wPATCH);
							auteurPageJs.l();
						}
						else if(methodeRecherche) {
							auteurPageJs.tl(1, "var ", str_filtres(langueNom), " = ", classeApiOperationIdMethode,str_Filtres(langueNom), "($", str_formulaireFiltres(langueNom), ");");
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
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", str_filtres(langueNom), ", success, error);");
						}
						else {
							auteurPageJs.tl(1, "$.ajax({");
		
							if(methodeGET || methodeDELETE || methodePUTCopie)
								auteurPageJs.tl(2, "url: '", StringUtils.replace(classeApiUriMethode, "{id}", "' + id"));
							else if(methodePATCH || methodeRecherche)
								auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", str_filtres(langueNom), ")");
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
//								auteurPageJs.tl(4, "$", str_formulaireValeurs(langueNom), ".find('.' + key).removeClass('", str_lueur(langueNom), str_Erreur(langueNom), "');");
//								auteurPageJs.tl(4, "$", str_formulaireValeurs(langueNom), ".find('.' + key).addClass('", str_lueur(langueNom), str_Succes(langueNom), "');");
//								auteurPageJs.tl(3, "});");
//								auteurPageJs.tl(2, "}");
//								auteurPageJs.tl(2, ", error: function( jqXhr, textStatus, errorThrown ) {");
//								auteurPageJs.tl(3, "$.each( vals, function( key, value ) {");
//								auteurPageJs.tl(4, "$", str_formulaireValeurs(langueNom), ".find('.' + key).removeClass('", str_lueur(langueNom), str_Succes(langueNom), "');");
//								auteurPageJs.tl(4, "$", str_formulaireValeurs(langueNom), ".find('.' + key).addClass('", str_lueur(langueNom), str_Erreur(langueNom), "');");
//								auteurPageJs.tl(3, "});");
//								auteurPageJs.tl(2, "}");
							auteurPageJs.tl(1, "});");
						}
						auteurPageJs.l("}");

						if(methodePATCH || methodeRecherche) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, str_Filtres(langueNom), "($", str_formulaireFiltres(langueNom), ") {");
							auteurPageJs.tl(1, "var ", str_filtres(langueNom), " = [];");
							auteurPageJs.tl(1, "if($", str_formulaireFiltres(langueNom), ") {");
							if(methodePATCH)
								auteurPageJs.tl(2, str_filtres(langueNom), ".push({ name: 'softCommit', value: 'true' });");
							auteurPageJs.s(wRecherche);
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "return ", str_filtres(langueNom), ";");
							auteurPageJs.tl(0, "}");
						}
						if(methodePATCH) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Val(", str_filtres(langueNom), ", v, val, success, error) {");
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.tl(1, "vals[v] = val;");
							auteurPageJs.tl(1, "", classeApiOperationIdMethode, "Vals(", str_filtres(langueNom), ", vals, success, error);");
							auteurPageJs.l("}"); 
						}
						if(methodeRecherche) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", str_filtres(langueNom), ", success, error) {");
							if(contexteRows != null) {
								auteurPageJs.l();
//										auteurPageJs.tl(1, str_filtres(langueNom), ".push({ name: 'rows', value: ", contexteRows, " });");
							}
							if(classeTrisVar != null) {
								auteurPageJs.l();
								for(Integer i = 0; i < classeTrisVar.size(); i++) {
									String classeTriVar = classeTrisVar.get(i);
									String classeTriOrdre = classeTrisOrdre.get(i);

									auteurPageJs.tl(1, str_filtres(langueNom), ".push({ name: '", "sort", "', value: '", classeTriVar, " ", classeTriOrdre, "' });");
								}
							}
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", str_filtres(langueNom), ")");
						}
						if(methodePATCH || methodePUTCopie) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", str_filtres(langueNom), ", vals, success, error) {");
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", str_filtres(langueNom), ")");
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
										entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + str_Recherche(langueNom) + "_" + langueNom + "_stored_string");
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
											auteurPageJs.tl(0, "function ", str_suggere(langueNom), classeNomSimple, entiteVarCapitalise, "($", str_formulaireFiltres(langueNom), ", $list) {");
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
											auteurPageJs.tl(1, "", str_rechercher(langueNom), classeNomSimple, "Vals($", str_formulaireFiltres(langueNom), ", success, error);");
											auteurPageJs.tl(0, "}");
										}
										else if(entiteAttribuer) {

											auteurPageJs.l();
											auteurPageJs.tl(0, "function ", str_suggere(langueNom), classeNomSimple, entiteVarCapitalise, "(", str_filtres(langueNom), ", $list, ", classeVarClePrimaire, " = null, ", str_attribuer(langueNom), "=true) {");
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
											auteurPageJs.tl(3, "$input.attr('class', '", str_valeur(langueNom), entiteVarCapitalise, " w3-check ');");

											auteurPageJs.tl(3, "if(", classeVarClePrimaire, " != null) {");
											auteurPageJs.t(4, "$input.attr('onchange', \"var $input = $('#", classeApiMethodeMethode, "_", entiteVar, "_\" + ", classeVarClePrimaire, " + \"_", entiteAttribuerVar, "_\" + o['", classeVarClePrimaire, "'] + \"'); ");
											if("array".equals(entiteTypeJson)) {
												auteurPageJs.s("", entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeVarClePrimaire, ":\" + ", classeVarClePrimaire, " + \"' }], { [($input.prop('checked') ? 'add' : 'remove') + '", entiteVarCapitalise, "']: \\\"\" + o['", classeVarClePrimaire, "'] + \"\\\" }");
											}
											else {
												auteurPageJs.s("", entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeVarClePrimaire, ":\" + ", classeVarClePrimaire, " + \"' }], { [($input.prop('checked') ? 'set' : 'remove') + '", entiteVarCapitalise, "']: \\\"\" + o['", classeVarClePrimaire, "'] + \"\\\" }");
											}
											auteurPageJs.l(" ); \");");

											auteurPageJs.tl(4, "$input.attr('onclick', '", str_enleverLueur(langueNom), "($(this)); ');");
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
													auteurPageJs.tl(k, "$sortInput.attr('id', \"", str_attribuer(langueNom), "_\" + o['", classeVarClePrimaire, "'] + \"_", str_tri(langueNom), "_", entiteAttribuerTriVar, "\");");
													auteurPageJs.tl(k, "$sortInput.attr('value', ", entiteAttribuerTriVar, ").attr('onchange', ");
													auteurPageJs.tl(k + 1, "\"$('#", classeNomSimple, "Form :input[name=\\\"focusId\\\"]').val($(this).attr('id')); \"");
													auteurPageJs.tl(k + 1, "+ \"", entiteAttribuerOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeVarClePrimaire, ":\" + o['", classeVarClePrimaire, "'] + \"' }], { ['set", StringUtils.capitalize(entiteAttribuerTriVar), "']: $(this).val() ? $(this).val() : null }\"");
													auteurPageJs.tl(k + 2, "+ \", function() { \"");
													auteurPageJs.tl(k + 2, "+ \"}\"");
													auteurPageJs.tl(k + 2, "+ \", function() { ", str_ajouterErreur(langueNom), "($('#", str_attribuer(langueNom), "_\" + o['", classeVarClePrimaire, "'] + \"_", str_tri(langueNom), "_", entiteAttribuerTriVar, "')); }\"");
													auteurPageJs.tl(k + 2, "+ \" ); \"); ");
													auteurPageJs.tl(k, "$sort.append($sortInput);");
													auteurPageJs.tl(k, "$li.append($sort);");
													if(entiteAttribuerTriVarAncien != null)
														auteurPageJs.tl(3, "}");

													entiteAttribuerTriVarAncien = entiteAttribuerTriVar;
												}
											}
											auteurPageJs.tl(3, "if(", str_attribuer(langueNom), ")");
											auteurPageJs.tl(4, "$li.append($input);");
											auteurPageJs.tl(3, "$li.append($a);");
											auteurPageJs.tl(3, "$list.append($li);");
											auteurPageJs.tl(2, "});");
											auteurPageJs.tl(2, "var focusId = $('#", classeNomSimple, "Form :input[name=\"focusId\"]').val();");
											auteurPageJs.tl(2, "if(focusId)");
											auteurPageJs.tl(3, "$('#' + focusId).parent().next().find('input').focus();");
											auteurPageJs.tl(1, "};");
											auteurPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
											auteurPageJs.tl(1, "", entiteAttribuerOperationIdRecherche, "Vals(", str_filtres(langueNom), ", success, error);");
											auteurPageJs.tl(0, "}");

											auteurWebsocket.l();
											auteurWebsocket.tl(2, "window.eventBus.registerHandler('websocket", entiteAttribuerNomSimple, "', function (error, message) {");
//												auteurWebsocket.tl(3, "var json = JSON.parse(message['body']);");
//												auteurWebsocket.tl(3, "var id = json['id'];");
//												auteurWebsocket.tl(3, str_suggere(langueNom), classeNomSimple, entiteVarCapitalise, "($('#' + ($(this).val() ? '", str_suggere(langueNom), "' : 'form') + '", classeNomSimple, entiteVarCapitalise, "'), $('#", "list", classeNomSimple, entiteVarCapitalise, "_", classeApiMethodeMethode, "'));");
											auteurWebsocket.tl(3, "$('#Page_", entiteVar, "').trigger('oninput');");
											auteurWebsocket.tl(3, "$('#Page_", entiteVar, "_", str_ajouter(langueNom), "').text('", str_ajouter(langueNom), " ", entiteAttribuerContexteUnNom, "');");
											auteurWebsocket.tl(3, "$('#Page_", entiteVar, "_", str_ajouter(langueNom), "').removeClass('w3-disabled');");
											auteurWebsocket.tl(3, "$('#Page_", entiteVar, "_", str_ajouter(langueNom), "').attr('disabled', false);");
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
				tl(4, "websocket", classeNomSimple, "(websocket", classeNomSimple, "Inner);");
//					s(wWebsocket);
//					tl(2, "tl(1, ", q("});"), ");");
				tl(3, "});");
				tl(2, "</script>");
				tl(0, "{{/inline}}");
			}
			t(0, "{{#*inline \"htmUrl", classeNomSimple, "\"}}");
			s("{{pageUri}}");
			s("?q={{query.q}}");
			s("&amp;rows={{#if rows}}{{rows}}{{else}}{{pagination.", str_lignes(langueNom), "}}{{/if}}");
			s("&amp;rows={{#if start}}{{start}}{{else}}{{pagination.", str_debut(langueNom), "}}{{/if}}");
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

			s("{{#*inline \"htmBodyCount0", classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBodyCount0", classePageNomSimple, "\" -->");
			tl(2, "<h1>");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(4, "{{#if ", str_contexteIconeClassesCss(langueNom), "}}");
			tl(5, "<i class=\"", str_contexteIconeClassesCss(langueNom), " site-menu-icon \"></i>");
			tl(4, "{{/if}}");
			tl(4, "<span class=\"\">", contexteNomAdjectifPluriel, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");
			tl(2, "<h2>");
			tl(3, "<span class=\"w3-bar-item w3-padding w3-center w3-block w3-", contexteCouleur, "\">");
			tl(4, "{{#if ", str_contexteIconeClassesCss(langueNom), "}}");
			tl(5, "<i class=\"", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \"></i>");
			tl(4, "{{/if}}");
			tl(4, "<span class=\"\">", contexteAucunNomTrouve, "</span>");
			tl(3, "</span>");
			tl(2, "</h2>");
			l("{{/inline}}");

			s("{{#*inline \"htmBodyCount1", str_Tous(langueNom), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBodyCount1", str_Tous(langueNom), classePageNomSimple, "\" -->");
			tl(2, "<h1 class=\"w3-center \">");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(3, "{{#if ", str_contexteIconeClassesCss(langueNom), "}}");
			tl(4, "<i class=\"", str_contexteIconeClassesCss(langueNom), " site-menu-icon \"></i>");
			tl(3, "{{/if}}");
			tl(4, "<span class=\"\">", contexteNomAdjectifSingulier, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");

			tl(2, "<h2 class=\"w3-center \">");
			tl(3, "<span class\"w3-bar-item w3-padding w3-center w3-block w3-", contexteCouleur, "\">");
			tl(4, "<span class=\"\">{{", uncapitalizeClasseNomSimple, "_.", str_objetTitre(langueNom), "}}</span>");
			tl(3, "</span>");
			tl(2, "</h2>");

			if(classeVarClePrimaire != null) {
				tl(0, "{{#block \"htm", str_Formulaire(langueNom), "\"}}{{/block}}");
			}
			l("{{/inline}}");
	
			s("{{#*inline \"htmBodyCount1", classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBodyCount1", classePageNomSimple, "\" -->");
			tl(2, "<h1>");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(1, "{{#if ", str_contexteIconeClassesCss(langueNom), "}}");
			tl(4, "<i class=\"", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \"></i>");
			tl(1, "{{/if}}");
			tl(4, "<span class=\"\">", contexteNomAdjectifPluriel, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");
			tl(2, "<div>");
			tl(1, "{{#if pagination.page", str_Precedent(langueNom), "}}");
			tl(3, "<a href=\"{{pageUri}}?start={{pagination.page", str_Precedent(langueNom), ".", str_debut(langueNom), "}}&amp;rows={{pagination.", str_lignes(langueNom), "}}\">");
			tl(4, "<i class=\"fas fa-arrow-square-left \"></i>");
			tl(3, "</a>");
			tl(1, "{{else}}");
			tl(3, "<i class=\"fas fa-arrow-square-left w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(1, "{{#gte pagination.", str_lignes(langueNom), str_Precedent(langueNom), " pagination.1L}}");
			tl(3, "<a href=\"{{pageUri}}?start={{pagination.", str_debut(langueNom), "}}&amp;rows={{ pagination.", str_lignes(langueNom), str_Precedent(langueNom), " }}\">");
			tl(4, "<i class=\"fas fa-minus-square \"></i>");
			tl(3, "</a>");
			tl(1, "{{else}}");
			tl(3, "<i class=\"fas fa-minus-square w3-opacity \"></i>");
			tl(1, "{{/gte}}");
			tl(3, "<a href=\"{{pageUri}}?start={{pagination.", str_debut(langueNom), "}}&amp;rows={{ pagination.", str_lignes(langueNom), str_Prochaine(langueNom), " }}\">");
			tl(4, "<i class=\"fas fa-plus-square \"></i>");
			tl(3, "</a>");
			tl(1, "{{#if pagination.page", str_Prochaine(langueNom), "}}");
			tl(3, "<a href=\"{{pageUri}}?start={{pagination.page", str_Prochaine(langueNom), ".", str_debut(langueNom), "}}&amp;rows={{pagination.", str_lignes(langueNom), "}}\">");
			tl(4, "<i class=\"fas fa-arrow-square-right \"></i>");
			tl(3, "</a>");
			tl(1, "{{else}}");
			tl(3, "<i class=\"fas fa-arrow-square-right w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(3, "<span>{{ pagination.", str_debut(langueNom), "Num }} - {{ pagination.", str_fin(langueNom), "Num }} ", str_de(langueNom), " {{ pagination.", str_numTrouve(langueNom), " }}</span>");
			tl(2, "</div>");
			tl(0, "{{> \"table1", classePageNomSimple, "\"}}");
			l("{{/inline}}");

			s("{{#*inline \"htmBody", str_Tous(langueNom), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", str_Tous(langueNom), classePageNomSimple, "\" -->");
			tl(2, "<h1>");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(1, "{{#if ", str_contexteIconeClassesCss(langueNom), "}}");
			tl(4, "<i class=\"", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \"></i>");
			tl(1, "{{/if}}");
			tl(4, "<span class=\"\">", contexteNomAdjectifPluriel, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");
			tl(2, "<div class=\"\">");
			tl(3, "<div>");
			tl(1, "{{#if pagination.page", str_Precedent(langueNom), "}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.page", str_Precedent(langueNom), ".", str_debut(langueNom), "}}&amp;rows={{pagination.", str_lignes(langueNom), "}}\">");
			tl(5, "<i class=\"fas fa-arrow-square-left \"></i>");
			tl(4, "</a>");
			tl(1, "{{else}}");
			tl(4, "<i class=\"fas fa-arrow-square-left w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(1, "{{#gte pagination.", str_lignes(langueNom), str_Precedent(langueNom), " pagination.1L}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.", str_debut(langueNom), "}}&amp;rows={{ pagination.", str_lignes(langueNom), str_Precedent(langueNom), " }}\">");
			tl(5, "<i class=\"fas fa-minus-square \"></i>");
			tl(4, "</a>");
			tl(1, "{{else}}");
			tl(4, "<i class=\"fas fa-minus-square w3-opacity \"></i>");
			tl(1, "{{/gte}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.", str_debut(langueNom), "}}&amp;rows={{ pagination.", str_lignes(langueNom), str_Prochaine(langueNom), " }}\">");
			tl(5, "<i class=\"fas fa-plus-square \"></i>");
			tl(4, "</a>");
			tl(1, "{{#if pagination.page", str_Prochaine(langueNom), "}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.page", str_Prochaine(langueNom), ".", str_debut(langueNom), "}}&amp;rows={{pagination.", str_lignes(langueNom), "}}\">");
			tl(5, "<i class=\"fas fa-arrow-square-right \"></i>");
			tl(4, "</a>");
			tl(1, "{{else}}");
			tl(4, "<i class=\"fas fa-arrow-square-right w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(4, "<span>{{ pagination.", str_debut(langueNom), "Num }} - {{ pagination.", str_fin(langueNom), "Num }} ", str_de(langueNom), " {{ pagination.", str_numTrouve(langueNom), " }}</span>");
			tl(3, "</div>");
			tl(0, "{{> \"table1", classePageNomSimple, "\"}}");
			tl(2, "</div>");
			l("{{/inline}}");

			if(classeVarClePrimaire != null) {
				s("{{#*inline \"htm", str_Formulaire(langueNom), classePageNomSimple, "\"}}");
				tl(1, "{{#if ", classeVarClePrimaire, "}}");
				tl(2, "<!-- #*inline \"htm", str_Formulaire(langueNom), "\" -->");
				tl(2, "<form action=\"", classeApiUri, "\" id=\"", classeNomSimple, "Form\" style=\"display: inline-block; width: 100%; \" onsubmit=\"event.preventDefault(); return false; \">");
				t(3, "<input");
				s(" name=\"", classeVarClePrimaire, "\"");
				s(" class=\"", str_valeur(langueNom), StringUtils.capitalize(classeVarClePrimaire), "\"");
				s(" type=\"hidden\"");
				s(" value=\"{{", classeVarClePrimaire, "}}\"");
				l("/>");
				t(3, "<input");
				s(" name=\"focusId\"");
				s(" type=\"hidden\"");
				l("/>");
				tl(2, "</form>");
				l("{{#block \"htm", str_Formulaire(langueNom), "_", StringUtils.lowerCase(str_PageRecherche(langueNom)), classeNomSimple, "\"}}{{/block}}");
				tl(1, "{{/if}}");
				l("{{/inline}}");
			}

			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if(classeApiMethode.equals(str_PageRecherche(langueNom)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals(str_PUTCopie(langueNom)) || classeApiMethode.equals(str_PUTFusion(langueNom)) || classeApiMethode.equals("PUTImport")) {
					l("{{#*inline \"htm", str_Formulaire(langueNom), classePageNomSimple, "_", classeApiOperationIdMethode, "\"}}");
					String methodeTitreFiltres = null;
					String methodeTitreValeurs = null;

					if("POST".equals(classeApiMethodeMethode)) {
						methodeTitreValeurs = str_Creer_(langueNom) + contexteUnNom;
					}
					else if("PUTImport".equals(classeApiMethode)) {
						methodeTitreFiltres = str_Rechercher_(langueNom) + contexteUnNom;
						methodeTitreValeurs = str_Importer_(langueNom) + contexteNomPluriel;
					}
					else if(str_PUTFusion(langueNom).equals(classeApiMethode)) {
						methodeTitreFiltres = str_Rechercher_(langueNom) + contexteUnNom;
						methodeTitreValeurs = str_Fusionner_(langueNom) + contexteNomPluriel;
					}
					else if(str_PUTCopie(langueNom).equals(classeApiMethode)) {
						methodeTitreFiltres = str_Rechercher_(langueNom) + contexteUnNom;
						methodeTitreValeurs = str_Dupliquer_(langueNom) + contexteNomPluriel;
					}
					else if("PATCH".equals(classeApiMethodeMethode)) {
						methodeTitreFiltres = str_Rechercher_(langueNom) + contexteUnNom;
						methodeTitreValeurs = str_Modifier_des_(langueNom) + contexteNomPluriel;
					}
					else if("DELETE".equals(classeApiMethodeMethode)) {
						methodeTitreFiltres = str_Rechercher_(langueNom) + contexteUnNom;
						methodeTitreValeurs = str_Supprimer_(langueNom) + contexteNomPluriel;
					}

					if(!classeApiMethode.equals(str_PageRecherche(langueNom))) {
						tl(2, "<button");
						tl(3, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " \"");
						tl(3, "onclick=\"$('#", classeApiOperationIdMethode, str_Modale(langueNom), "').show(); \"");
						tl(3, ">");
	
							if(classeApiMethodeMethode.contains("POST"))
								tl(3, "<i class=\"fas fa-file-plus \"></i>");
							else if(classeApiMethodeMethode.contains("PATCH"))
								tl(3, "<i class=\"fas fa-edit \"></i>");
							else if(classeApiMethode.contains("PUTImport"))
								tl(3, "<i class=\"fas fa-file-import \"></i>");
							else if(classeApiMethode.contains(str_PUTFusion(langueNom)))
								tl(3, "<i class=\"fas fa-code-merge \"></i>");
							else if(classeApiMethode.contains(str_PUTCopie(langueNom)))
								tl(3, "<i class=\"fas fa-copy \"></i>");
	
							tl(3, methodeTitreValeurs);
						tl(2, "</button>");
					}
					{ tl(2, "<div id=\"", classeApiOperationIdMethode, str_Modale(langueNom), "\" class=\"", classeApiMethode.equals(str_PageRecherche(langueNom)) ? "" : "w3-modal ", "w3-padding-32 \">");
						{ tl(3, "<div class=\"", classeApiMethode.equals(str_PageRecherche(langueNom)) ? "" : "w3-modal-content ", "\">");
							{ tl(4, "<div class=\"w3-card-4 \">");
								{ tl(5, "<header class=\"w3-container w3-", contexteCouleur, "\">");
									tl(1, "{{#eq \"Page\" ", str_classeApiMethodeMethode(langueNom), "}}");
									tl(6, "<span class=\"w3-button w3-display-topright \" onclick=\"$('#", classeApiOperationIdMethode, str_Modale(langueNom), "').hide(); \">×</span>");
									tl(1, "{{/eq}}");
									tl(6, "<h2 class=\"w3-padding \">", methodeTitreValeurs, "</h2>");
								} tl(5, "</header>");
	
								{ tl(5, "<div class=\"\" id=\"", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "\">");
//											TODO
//											tl(7+ tab, classeNomSimple, " o = new ", classeNomSimple, "();");
////											tl(7+ tab, "o.", str_initLoin(langueNom), str_PourClasse(langueNom), "(", str_requeteSite(langueNom), "_);");
//											tl(7+ tab, "o.set", str_RequeteSite(langueNom), "_(", str_requeteSite(langueNom), "_);");
									if("PATCH".equals(classeApiMethode) || str_PUTFusion(langueNom).equals(classeApiMethode) || str_PUTCopie(langueNom).equals(classeApiMethode) || "PUTImport".equals(classeApiMethode)) {
	
										if("PUTImport".equals(classeApiMethode)) {
											tl(6, "<div class=\"w3-cell-row \">");
											tl(7, "<textarea");
											tl(8, "class=\"", "PUTImport_", str_listeRecherche(langueNom), " w3-input w3-border \"");
											tl(8, "style=\"height: 400px; \"");
											tl(8, "placeholder=\"{ '", str_listeRecherche(langueNom), "': [ { 'pk': ... , '", str_sauvegardes(langueNom), "': [ ... ] }, ... ] }\"");
											tl(8, "></textarea>");
											tl(6, "</div>");
										} else if(str_PUTFusion(langueNom).equals(classeApiMethode)) {
											tl(6, "<div class=\"w3-cell-row \">");
											tl(7, "<textarea");
											tl(8, "class=\"", "PUT", str_PUTFusion(langueNom), "_", str_listeRecherche(langueNom), " w3-input w3-border \"");
											tl(8, "style=\"height: 400px; \"");
											tl(8, "placeholder=\"{ '", str_listeRecherche(langueNom), "': [ { 'pk': ... , '", str_sauvegardes(langueNom), "': [ ... ] }, ... ] }\"");
											tl(8, "></textarea>");
											tl(6, "</div>");
										} else if(str_PUTCopie(langueNom).equals(classeApiMethode)) {
											s(wFormPUTCopie);
										} else if("PATCH".equals(classeApiMethodeMethode)) {
											s(wFormPATCH);
										}
	
										tl(6, "<button");
										tl(7, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-", contexteCouleur, " \"");
		
										if("PATCH".equals(classeApiMethode))
											tl(7, "onclick=\"", classeApiOperationIdMethode, "(null, $('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "')); \"");
										else if("PUTImport".equals(classeApiMethode))
											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "')); \"");
										else if(str_PUTFusion(langueNom).equals(classeApiMethode))
											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "')); \"");
										else if(str_PUTCopie(langueNom).equals(classeApiMethode))
											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "')); \"");
										else
											tl(7, "onclick=\"", classeApiOperationIdMethode, "\"");
		
										tl(7, ">", methodeTitreValeurs, "</button>");
									} else {
										{ tl(6, "<div id=\"", classeApiOperationIdMethode, "Form\">");
	
										if("POST".equals(classeApiMethode)) {
											s(wFormPOST);
										} else if(str_Recherche(langueNom).equals(classeApiMethode)) {
											s(wFormRecherche);
										} else {
											s(wFormPage);
										}
	
										} tl(6, "</div>");
//										tl(6, "<button");
//										tl(7, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-", contexteCouleur, " \"");
//										if("POST".equals(classeApiMethodeMethode))
//											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \"");
//										else if("PATCH".equals(classeApiMethodeMethode))
//											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireFiltres(langueNom), "'), $('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "'), function() {}, function() {}); \"");
//										else if("PUTImport".equals(classeApiMethode))
//											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \"");
//										else if(str_PUTFusion(langueNom).equals(classeApiMethode))
//											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \"");
//										else if(str_PUTCopie(langueNom).equals(classeApiMethode))
//											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form'), \", ", uncapitalizeClasseNomSimple, "_ == null ? \"null\" : ", uncapitalizeClasseNomSimple, "_.get", StringUtils.capitalize(classeVarClePrimaire), "(), \"; \")");
//										else if(classeApiMethodeMethode.contains("PATCH") || classeApiMethodeMethode.contains("POST") || classeApiMethodeMethode.contains("PUT"))
//											tl(7, "onclick=\"", classeApiOperationIdMethode, "(\", o.get", StringUtils.capitalize(classeVarClePrimaire), "(), \"; \")");
//										else
//											tl(7, "onclick=\"", classeApiOperationIdMethode, "(); \"");
//		
//										tl(7, ">", methodeTitreValeurs, "</button>");
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

			tl(0, "{{#*inline \"htmBody", str_Debut(langueNom), classePageNomSimple, "\"}}");
			tl(0, "{{> \"htmBody", str_Debut(langueNom), classePageSuperNomSimple, "\"}}");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"htmBody", str_Fin(langueNom), classePageNomSimple, "\"}}");
			tl(0, "{{> \"htmBody", str_Fin(langueNom), classePageSuperNomSimple, "\"}}");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"htmBody", classePageNomSimple, "\"}}");
			tl(0, "{{#block \"htmBody", str_Debut(langueNom), "\"}}{{/block}}");
			tl(1, "{{#eq ", uncapitalizeClasseNomSimple, "Count int0}}");
			tl(0, "{{#block \"htmBodyCount0\"}}{{/block}}");
			tl(1, "{{else}}");

			tl(2, "{{#eq ", uncapitalizeClasseNomSimple, "Count int1}}");
			tl(3, "{{#eq params.query.q \"*:*\"}}");
			tl(0, "{{#block \"htmBodyCount1", str_Tous(langueNom), "\"}}{{/block}}");
			tl(3, "{{else}}");
			tl(0, "{{#block \"htmBodyCount1\"}}{{/block}}");
			tl(3, "{{/eq}}");
			tl(2, "{{else}}");
			tl(0, "{{#block \"htmBody", str_Tous(langueNom), "\"}}{{/block}}");
			tl(2, "{{/eq}}");
			tl(1, "{{/eq}}");
			if(classeMethodeVars.contains("htmBody")) {
				tl(6, "{{#if o}}");
				tl(7, "{{> \"htmBody\"}}");
				tl(6, "{{/if}}");
			}

			// formulaires
			if(!classePageSimple) {
				l("{{#block \"htm", str_Formulaires(langueNom), "\"}}{{/block}}");
			}

			tl(0, "{{#block \"htmBody", str_Fin(langueNom), "\"}}{{/block}}");
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
//				tl(2, "Map<String, Map<String, List<String>>> highlighting = ", str_liste(langueNom), classeNomSimple, ".getQueryResponse().getHighlighting();");
			tl(2, "{{#each ", str_liste(langueNom), classeNomSimple, "}}");
//				TODO
//				tl(3, classeNomSimple, " o = ", str_liste(langueNom), classeNomSimple, ".getList().get(i);");
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
//				tl(3, "SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ".getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get(\"facets\")).orElse(new SimpleOrderedMap());");
			s(wFoot);
			tl(2, "</tr>");
			tl(1, "{{/inline}}");
			s(wGetters);
			tl(0, "{{#*inline \"htm", str_Formulaires(langueNom), classePageNomSimple, "\"}}");
			tl(1, "{{#ifContainsAnyRoles roles ", str_rolesRequis(langueNom), "}}");

			// refraîchir 1 //
			tl(2, "{{#eq ", uncapitalizeClasseNomSimple, "Count int1}}");
			tl(2, "<button");
			tl(3, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " \"");
			tl(3, "id=\"", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteCe)), classeGenPageNomSimple, "\"");
			tl(3, "onclick=\"patch{{", str_classeNomSimple(langueNom), "}}Vals( [ {name: 'fq', value: '", classeVarClePrimaire, ":{{", classeVarClePrimaire, "}}' } ], {}, function() { ", str_ajouterLueur(langueNom), "($('#", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteCe)), classeGenPageNomSimple, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteCe)), classeGenPageNomSimple, "')); }); return false; \">");
			tl(3, "<i class=\"fas fa-sync-alt \"></i>");
			tl(3, str_recharger(langueNom), " ", contexteCeNom);
			tl(2, "</button>");
			tl(2, "{{/eq}}");

			tl(1, "{{/ifContainsAnyRoles}}");

			// formulaires //
			if(activerRoleAdmin) {
				tl(1, "{{#ifContainsAnyRoles roles ", str_authRolesAdmin(langueNom), "}}");
			}
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if("application/json".equals(classeApiTypeMediaMethode) && (classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals(str_PUTCopie(langueNom)) || classeApiMethode.equals(str_PUTFusion(langueNom)) || classeApiMethode.equals("PUTImport"))) {
					l("{{#block \"htm", str_Formulaire(langueNom), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
				}
			}

			if(activerRoleAdmin) {
				tl(1, "{{/ifContainsAnyRoles}}");
			}
			l("{{#block \"htm", str_Suggere(langueNom), "\"}}{{/block}}");
			tl(0, "{{/inline}}");

			if(!classePageSimple) {
//				TODO
//				tl(1, "/**");
//				if(StringUtils.equals(langueNomActuel, langueNom))
//				for(String langueNom2 : autresLangues) {
//					String classeGenPageNomSimple2 = (String)classeDoc.get("classeGenPageNomSimple" + str_PageRecherche(langueNom2)  + "_" + langueNom2 + "_stored_string");
//					String classeNomSimple2 = (String)classeDoc.get("classeNomSimple" + "_" + langueNom2 + "_stored_string");
//					String contexteTousNom2 = (String)classeDoc.get("contexteTousNom" + "_" + langueNom2 + "_stored_string");
//					String contexteNomAdjectifPluriel2 = (String)classeDoc.get("contexteNomAdjectifPluriel" + "_" + langueNom2 + "_stored_string");
//					String classePageUriMethode2 = (String)classeDoc.get("classeApiUri" + str_PageRecherche(langueNom2) + "_" + langueNom2 + "_stored_string");
//					String classeVarSuggere2 = (String)classeDoc.get("classeVarSuggere" + "_" + langueNom2 + "_stored_string");
//					String classeVarTexte2 = (String)classeDoc.get("classeVarTexte" + "_" + langueNom2 + "_stored_string");
//
//					tl(1, " * Var.", langueNom2, ": htm", str_Suggere(langueNom2), classeGenPageNomSimple2);
//					tl(1, " * r: \"", classePageUriMethode, "\"");
//					tl(1, " * r.", langueNom2, ": \"", classePageUriMethode2, "\"");
//					tl(1, " * r: \"", str_voir(langueNom), " ", contexteTousNom, "\"");
//					tl(1, " * r.", langueNom2, ": \"", str_voir(langueNom2), " ", contexteTousNom2, "\"");
//					tl(1, " * r: \"", str_recharger(langueNom), classeGenPageNomSimple, "\"");
//					tl(1, " * r.", langueNom2, ": \"", str_recharger(langueNom2), classeGenPageNomSimple2, "\"");
//					tl(1, " * r: \"", str_recharger(langueNom), " ", contexteTousNom, "\"");
//					tl(1, " * r.", langueNom2, ": \"", str_recharger(langueNom2), " ", contexteTousNom2, "\"");
//					tl(1, " * r: \"", str_rechercher(langueNom), " ", contexteNomAdjectifPluriel, str_deuxPoints(langueNom), "\"");
//					tl(1, " * r.", langueNom2, ": \"", str_rechercher(langueNom2), " ", contexteNomAdjectifPluriel2, str_deuxPoints(langueNom2), "\"");
//					tl(1, " * r: \"", str_suggere(langueNom), "Form", classeNomSimple, "\"");
//					tl(1, " * r.", langueNom2, ": \"", str_suggere(langueNom2), "Form", classeNomSimple2, "\"");
//					tl(1, " * r: \"", str_rechercher(langueNom), " ", contexteNomAdjectifPluriel, "\"");
//					tl(1, " * r.", langueNom2, ": \"", str_rechercher(langueNom2), " ", contexteNomAdjectifPluriel2, "\"");
//					tl(1, " * r: \"", str_suggere(langueNom), classeNomSimple, " w3-input w3-border w3-cell w3-cell-middle ", "\"");
//					tl(1, " * r.", langueNom2, ": \"", str_suggere(langueNom2), classeNomSimple2, " w3-input w3-border w3-cell w3-cell-middle ", "\"");
//					tl(1, " * r: \"", str_suggere(langueNom), classeNomSimple, "\"");
//					tl(1, " * r.", langueNom2, ": \"", str_suggere(langueNom2), classeNomSimple2, "\"");
//
//					tl(1, " * r: ", "patch{{", str_classeNomSimple(langueNom), "}}Vals", "");
//					tl(1, " * r.", langueNom2, ": ", "patch", classeNomSimple2, "Vals", "");
//					tl(1, " * r: ", str_ajouterLueur(langueNom), "");
//					tl(1, " * r.", langueNom2, ": ", str_ajouterLueur(langueNom2), "");
//					tl(1, " * r: ", str_recharger(langueNom), classeGenPageNomSimple, "");
//					tl(1, " * r.", langueNom2, ": ", str_recharger(langueNom2), classeGenPageNomSimple2, "");
//					tl(1, " * r: ", str_ajouterErreur(langueNom), "");
//					tl(1, " * r.", langueNom2, ": ", str_ajouterErreur(langueNom2), "");
//					tl(1, " * r: ", str_suggere(langueNom), classeNomSimple, StringUtils.capitalize(classeVarSuggere));
//					tl(1, " * r.", langueNom2, ": ", str_suggere(langueNom2), classeNomSimple2, StringUtils.capitalize(classeVarSuggere2));
//					tl(1, " * r: ", str_texte(langueNom), classeNomSimple, StringUtils.capitalize(classeVarTexte));
//					tl(1, " * r.", langueNom2, ": ", str_texte(langueNom2), classeNomSimple2, StringUtils.capitalize(classeVarTexte2));
//					tl(1, " * r: ", "'", classeVarSuggere, ":'", "");
//					tl(1, " * r.", langueNom2, ": ", "'", classeVarSuggere2, ":'", "");
//					tl(1, " * r: ", "'", classeVarTexte, ":'", "");
//					tl(1, " * r.", langueNom2, ": ", "'", classeVarTexte2, ":'", "");
//					tl(1, " * r: ", "'#", str_suggere(langueNom), "List", classeNomSimple, "'", "");
//					tl(1, " * r.", langueNom2, ": ", "'#", str_suggere(langueNom2), "List", classeNomSimple2, "'", "");
//					tl(1, " * r: \"", str_suggere(langueNom), "List", classeNomSimple, "\"");
//					tl(1, " * r.", langueNom2, ": \"", str_suggere(langueNom2), "List", classeNomSimple2, "\"");
//				}
//				tl(1, "**/");
				l("{{#*inline \"htm", str_Suggere(langueNom), classePageNomSimple, "\"}}");
//					tl(1, "public static void htm", str_Suggere(langueNom), classeGenPageNomSimple, "(", classePartsMiseEnPage.nomSimple(langueNom), " p, String id, ", str_ListeRecherche(langueNom), "<", classeNomSimple, "> ", str_liste(langueNom), classeNomSimple, ") {");
//				tl(2, classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_ = p.get", str_RequeteSite(langueNom), "_();");
//				TODO
//				tl(2, "try {");
//				tl(3, "ServiceRequest ", str_requeteService(langueNom), " = ", str_requeteSite(langueNom), "_.get", str_RequeteService(langueNom), "();");
//				tl(3, "JsonObject queryParams = Optional.ofNullable(", str_requeteService(langueNom), ").map(ServiceRequest::getParams).map(or -> or.getJsonObject(\"query\")).orElse(new JsonObject());");
//				tl(3, "String q = \"*:*\";");
//				tl(3, "String query1 = \"", classeVarTexte, "\";");
//				tl(3, "String query2 = \"\";");
//				tl(3, "for(String param", str_Nom(langueNom), " : queryParams.fieldNames()) {");
//				tl(4, "String ", str_entite(langueNom), "Var = null;");
//				tl(4, "String ", str_valeur(langueNom), str_Indexe(langueNom), " = null;");
//				tl(4, "Object param", str_ValeursObjet(langueNom), " = queryParams.getValue(param", str_Nom(langueNom), ");");
//				tl(4, "JsonArray param", str_Objets(langueNom), " = param", str_ValeursObjet(langueNom), " instanceof JsonArray ? (JsonArray)param", str_ValeursObjet(langueNom), " : new JsonArray().add(param", str_ValeursObjet(langueNom), ");");
//				l();
//				tl(4, "try {");
//				tl(5, "for(Object param", str_Objet(langueNom), " : param", str_Objets(langueNom), ") {");
//				tl(6, "switch(param", str_Nom(langueNom), ") {");
//		
//				tl(7, "case \"q\":");
//				tl(8, "q = (String)param", str_Objet(langueNom), ";");
//				tl(8, str_entite(langueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(langueNom), ", \":\"));");
//				tl(8, str_valeur(langueNom), str_Indexe(langueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(langueNom), ", \":\")), \"UTF-8\");");
//				tl(8, "query1 = ", str_entite(langueNom), "Var.equals(\"*\") ? query1 : ", str_entite(langueNom), "Var;");
//				tl(8, "query2 = ", str_valeur(langueNom), str_Indexe(langueNom), ".equals(\"*\") ? \"\" : ", str_valeur(langueNom), str_Indexe(langueNom), ";");
//				tl(6, "}");
//				tl(5, "}");
//				tl(4, "} catch(Exception e) {");
//				tl(5, "ExceptionUtils.rethrow(e);");
//				tl(4, "}");
//				tl(3, "}");
//				l();
//				tl(3, "Integer rows1 = Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getRows()).orElse(10);");
//				tl(3, "Integer start1 = Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getStart()).orElse(1);");
//				tl(3, "Integer start2 = start1 - rows1;");
//				tl(3, "Integer start3 = start1 + rows1;");
//				tl(3, "Integer rows2 = rows1 / 2;");
//				tl(3, "Integer rows3 = rows1 * 2;");
//				tl(3, "start2 = start2 < 0 ? 0 : start2;");
//				tl(3, "StringBuilder fqs = new StringBuilder();");
//				tl(3, "for(String fq : Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getFilterQueries()).orElse(new String[0])) {");
//				tl(4, "if(!StringUtils.contains(fq, \"(\")) {");
//				tl(5, "String fq1 = StringUtils.substringBefore(fq, \"_\");");
//				tl(5, "String fq2 = StringUtils.substringAfter(fq, \":\");");
//				tl(5, "if(!StringUtils.startsWithAny(fq, \"", str_classeNomsCanoniques(langueNom), "_\", \"", str_archive(langueNom), "_\", \"", str_supprime(langueNom), "_\", \"sessionId\", \"", str_utilisateur(langueNom), str_Cle(langueNom), "s\"))");
//				tl(6, "fqs.append(\"&fq=\").append(fq1).append(\":\").append(fq2);");
//				tl(4, "}");
//				tl(3, "}");
//				tl(3, "StringBuilder sorts = new StringBuilder();");
//				tl(3, "for(SortClause sort : Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getSorts()).orElse(Arrays.asList())) {");
//				tl(4, "sorts.append(\"&sort=\").append(StringUtils.substringBefore(sort.getItem(), \"_\")).append(\" \").append(sort.getOrder().name());");
//				tl(3, "}");
//				l();

				tl(3, "{{#ifContainsAnyRoles roles ", str_rolesRequis(langueNom), "}}");

				// recharger tous //
//						t(4).s("{{# if ", str_liste(langueNom), classeNomSimple, " == null) {").l();
				tl(5, "<div class=\"\">");
				tl(6, "<button id=\"", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteTous)), classeGenPageNomSimple, "{{id}}\" class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " \"");
				tl(7, "onclick=\"patch{{", str_classeNomSimple(langueNom), "}}Vals([], {}, function() { ", str_ajouterLueur(langueNom), "($('#", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteTous)), classeGenPageNomSimple, "{{id}}')); }, function() { ", str_ajouterErreur(langueNom), "($('#", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteTous)), classeGenPageNomSimple, "{{id}}')); }); \"");
				tl(7, ">");
				tl(7, "<i class=\"fas fa-sync-alt \"></i>");
				tl(7, str_recharger(langueNom), " ", contexteTousNom);
				tl(6, "</button>");
				tl(5, "</div>");
//						t(4, "}").l();

				tl(3, "{{/ifContainsAnyRoles}}");

				tl(3, "<div class=\"w3-cell-row \">");
				tl(4, "<div class=\"w3-cell \">");
				tl(5, "<span>");
				tl(6, str_rechercher(langueNom), " ", contexteNomAdjectifPluriel, str_deuxPoints(langueNom));
				tl(5, "</span>");
				tl(4, "</div>");
				tl(3, "</div>");
				tl(3, "<div class=\"w3-bar \">");

				tl(4, "<input");
				tl(5, "type=\"text\"");

				if(contexteRechercherTousNom != null) {
					tl(5, "placeholder=\"", contexteRechercherTousNom, "\"");
				}

				tl(5, "class=\"", str_suggere(langueNom), classeNomSimple, " w3-input w3-border w3-bar-item \"");
				tl(5, "name=\"", str_suggere(langueNom), classeNomSimple, "\"");
				tl(5, "id=\"", str_suggere(langueNom), classeNomSimple, "{{id}}\"");
				tl(5, "autocomplete=\"off\"");
				tl(5, "oninput=\"", str_suggere(langueNom), classeNomSimple, StringUtils.capitalize(classeVarSuggere), "( [ { 'name': 'q', 'value': '", classeVarSuggere, ":' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", classeVarClePrimaire, classeVarUrlPk == null ? "" : "," + classeVarUrlPk, classeVarTitre == null ? "" : "," + classeVarTitre, "' } ], $('#", str_suggere(langueNom), "List", classeNomSimple, "{{id}}'), {{", classeVarClePrimaire, "}}; \"");
				tl(5, "onkeyup=\"if (event.keyCode === 13) { event.preventDefault(); window.location.href = '", classePageUriMethode + "?q={{query1}}:' + encodeURIComponent(this.value) + '{{fqs}}{{sorts}}&amp;rows={{start2}}&amp;rows={{rows1}}\"");
				tl(4, "{{#if ", str_liste(langueNom), classeNomSimple, "}}");
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
				tl(5, "<ul class=\"w3-ul w3-hoverable \" id=\"", str_suggere(langueNom), "List", classeNomSimple, "{{id}}\">");
				tl(5, "</ul>");
				tl(4, "</div>");
				tl(3, "</div>");

				// voir tous //
				tl(3, "<div class=\"\">");
				tl(4, "<a href=\"", classePageUriMethode, "\" class=\"\">");
				if(contexteIconeGroupe != null && contexteIconeNom != null)
					tl(5, "<i class=\"fa", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, "\"></i>");
				tl(5, str_voir(langueNom), " ", contexteTousNom);
				tl(4, "</a>");
				tl(3, "</div>");

//				tl(2, "} catch(Exception e) {");
//				tl(3, "ExceptionUtils.rethrow(e);");
//				tl(2, "}");
				tl(0, "{{/inline}}");
				l("{{> ", classePageSuperNomSimple
						, "Object".equals(classeNomSimpleSuperGenerique) ? "" : (
								" " + StringUtils.uncapitalize(classeNomSimpleSuperGenerique) + "_=" + uncapitalizeClasseNomSimple + "_"
								), "}}");

				if(classeMethodeVars.contains("htmBody" + str_Court(langueNom))) {
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(0, "{{#*inline \"htmBodyCourt", classePageNomSimple, "\"}}");
					tl(2, uncapitalizeClasseNomSimple, ".htmBody" + str_Court(langueNom) + "();");
					tl(0, "{{/inline}}");
				}

				auteurWebsocket.flushClose();
				auteurPageJs.l();
				auteurPageJs.tl(0, "async function websocket", classeNomSimple, "(success) {");
				auteurPageJs.tl(1, "window.eventBus.onopen = function () {");
				auteurPageJs.l();
				auteurPageJs.tl(2, "window.eventBus.registerHandler('websocket", classeNomSimple, "', function (error, message) {");
				auteurPageJs.tl(3, "var json = JSON.parse(message['body']);");
				auteurPageJs.tl(3, "var id = json['id'];");
				auteurPageJs.tl(3, "var pk = json['pk'];");
				auteurPageJs.tl(3, "var pkPage = $('#", classeNomSimple, "Form :input[name=", classeVarClePrimaire, "]').val();");
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
				auteurPageJs.tl(3, "var $headerSpan = $('<span>').attr('class', '').text('", str_modifier(langueNom), " ", contexteNomAdjectifPluriel, " ", str_dans(langueNom), " ' + json.", str_tempsRestant(langueNom), ");");
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

				auteurPageJs.tl(0, "async function websocket", classeNomSimple, "Inner(", str_requeteApi(langueNom), ") {");
				auteurPageJs.s(wWebsocket);
				auteurPageJs.tl(0, "}");
			}
	
			o = auteurGenPageHbs;

			s(auteurGenPageHbsEntite);
	
			o = auteurPageGenClasse;

			tl(0, "}");

			wTh.flushClose();

			auteurPageGenClasse.flushClose();
			System.out.println(str_Ecrire(langueNom) + ": " + classeGenPageChemin); 
			if(auteurPageClasse != null) {
				auteurPageClasse.flushClose();
				System.out.println(str_Ecrire(langueNom) + ": " + classePageChemin); 
			}
			auteurPageCss.flushClose();
			System.out.println(str_Ecrire(langueNom) + ": " + classePageCheminCss); 
			auteurPageJs.flushClose();
			System.out.println(str_Ecrire(langueNom) + ": " + classePageCheminJs); 
			if(auteurPageHbs != null) {
				auteurPageHbs.flushClose();
				System.out.println(str_Ecrire(langueNom) + ": " + classePageCheminHbs); 
			}
			auteurGenPageHbs.flushClose();
			System.out.println(str_Ecrire(langueNom) + ": " + classeGenPageCheminHbs); 

			String appliCheminVertx = appliCheminsVertx.get(langueNom);
			String appliNomVertx = StringUtils.substringAfterLast(appliCheminVertx, "/");
			String cheminSrcGenJavaVertx = (appliCheminVertx == null ? appliChemin : appliCheminVertx) + "/src/gen/java";
			String cheminSrcMainJavaVertx = (appliCheminVertx == null ? appliChemin : appliCheminVertx) + "/src/main/java";
			String cheminSrcMainResourcesVertx = (appliCheminVertx == null ? appliChemin : appliCheminVertx) + "/src/main/resources";

			{
				RegarderClasse regarderClasse = new RegarderClasse();
				regarderClasse.appliChemin = appliCheminVertx;
				regarderClasse.appliNom = appliNomVertx;
				regarderClasse.classeCheminAbsolu = classePageChemin;
				regarderClasse.cheminSrcGenJava = cheminSrcGenJavaVertx;
				regarderClasse.cheminSrcMainJava = cheminSrcMainJavaVertx;
				regarderClasse.cheminSrcMainResources = cheminSrcMainResourcesVertx;
				regarderClasse.initRegarderClasseBase(); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
//					RegarderClasse.regarderClasse(regarderClasse, langueNom);
				SolrInputDocument classeDoc = new SolrInputDocument();
				regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, langueNom);
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
			}

			{
				RegarderClasse regarderClasse = new RegarderClasse();
				regarderClasse.appliChemin = appliCheminVertx;
				regarderClasse.appliNom = appliNomVertx;
				regarderClasse.classeCheminAbsolu = classeGenPageChemin;
				regarderClasse.cheminSrcGenJava = cheminSrcGenJavaVertx;
				regarderClasse.cheminSrcMainJava = cheminSrcMainJavaVertx;
				regarderClasse.cheminSrcMainResources = cheminSrcMainResourcesVertx;
				regarderClasse.initRegarderClasseBase(); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
//					RegarderClasse.regarderClasse(regarderClasse, langueNom);
				SolrInputDocument classeDoc = new SolrInputDocument();
				regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, langueNom);
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
			}
		}
	}
}
