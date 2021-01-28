package org.computate.frFR.java;

import java.io.File;
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
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLigneRecherche = rechercheLigneActuelRecherche;
					resultat = true;
				}
			}
			else if("POST".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPOST = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePOST != rechercheLigneActuelPOST) {
					if(rechercheLignePOST != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePOST = rechercheLigneActuelPOST;
					resultat = true;
				}
			}
			else if("PUTImport".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPUTImport = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePUTImport != rechercheLigneActuelPUTImport) {
					if(rechercheLignePUTImport != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePUTImport = rechercheLigneActuelPUTImport;
					resultat = true;
				}
			}
			else if(str_PUTFusion(langueNom).equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPUTFusion = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePUTFusion != rechercheLigneActuelPUTFusion) {
					if(rechercheLignePUTFusion != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePUTFusion = rechercheLigneActuelPUTFusion;
					resultat = true;
				}
			}
			else if(str_PUTCopie(langueNom).equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPUTCopie = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePUTCopie != rechercheLigneActuelPUTCopie) {
					if(rechercheLignePUTCopie != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePUTCopie = rechercheLigneActuelPUTCopie;
					resultat = true;
				}
			}
			else if("PATCH".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPATCH = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePATCH != rechercheLigneActuelPATCH) {
					if(rechercheLignePATCH != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePATCH = rechercheLigneActuelPATCH;
					resultat = true;
				}
			}
			else if("Page".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPage = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePage != rechercheLigneActuelPage) {
					if(rechercheLignePage != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePage = rechercheLigneActuelPage;
					resultat = true;
				}
			}
			
			wForm.t(3).l("o.htm", entiteVarCapitalise, "(\"", classeApiMethodeMethode, "\");");
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
	 * r: classePageCheminGen
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
	 * r.enUS: operationRequest
	 * r: OperationRequete
	 * r.enUS: OperationRequest
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
		if(classePartsMiseEnPage != null) {
			for(String classePageMethode : classeApiMethodes) {
	
				String classePageCheminGen = (String)classeDoc.get("classePageCheminGen" + classePageMethode  + "_" + langueNom + "_stored_string");
				String classePageChemin = (String)classeDoc.get("classePageChemin" + classePageMethode  + "_" + langueNom + "_stored_string");
				String classePageCheminCss = (String)classeDoc.get("classePageCheminCss" + classePageMethode  + "_" + langueNom + "_stored_string");
				String classePageCheminJs = (String)classeDoc.get("classePageCheminJs" + classePageMethode  + "_" + langueNom + "_stored_string");
				String classePageUriMethode = (String)classeDoc.get("classeApiUri" + classePageMethode + "_" + langueNom + "_stored_string");
				String classePageLangueNom = (String)classeDoc.get("classePageLangueNom" + classePageMethode + "_" + langueNom + "_stored_string");
	
				classePageNomSimple = (String)classeDoc.get("classePageNomSimple" + classePageMethode  + "_" + langueNom + "_stored_string");
				classePageSuperNomSimple = (String)classeDoc.get("classePageSuperNomSimple" + classePageMethode  + "_" + langueNom + "_stored_string");
				classeGenPageNomSimple = (String)classeDoc.get("classeGenPageNomSimple" + classePageMethode  + "_" + langueNom + "_stored_string");
				String classePageNomCanonique = (String)classeDoc.get("classePageNomCanonique" + classePageMethode  + "_" + langueNom + "_stored_string");
				classeAttribuerNomSimplePages = (List<String>)classeDoc.get("classeAttribuerNomSimplePages_" + langueNom + "_stored_strings");
		
				if(!classePageCheminsGen.contains(classePageCheminGen) && classePageCheminGen != null && StringUtils.equals(classePageLangueNom, langueNom)) {
					classePageCheminsGen.add(classePageCheminGen);
	
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
				
					File classePageFichierGen = null;
					File classePageFichier = null;
					File classePageFichierCss = null;
					File classePageFichierJs = null;
	
					if(classePageCheminGen != null)
						classePageFichierGen = new File(classePageCheminGen);
					if(classePageChemin != null)
						classePageFichier = new File(classePageChemin);
					if(classePageCheminCss != null)
						classePageFichierCss = new File(classePageCheminCss);
					if(classePageCheminJs != null)
						classePageFichierJs = new File(classePageCheminJs);
				
					ToutEcrivain auteurPageGenClasse = null;
					ToutEcrivain auteurPageClasse = null;
					ToutEcrivain auteurPageCss = null;
					ToutEcrivain auteurPageJs = null;
					ToutEcrivain auteurWebsocket = null;
	
					if(classePageFichierGen != null)
						auteurPageGenClasse = ToutEcrivain.create(classePageFichierGen);
					if(classePageFichier != null && !classePageFichier.exists())
						auteurPageClasse = ToutEcrivain.create(classePageFichier);
					if(classePageFichierCss != null) {
						classePageFichierCss.getParentFile().mkdirs();
						auteurPageCss = ToutEcrivain.create(classePageFichierCss);
					}
					if(classePageFichierJs != null) {
						classePageFichierJs.getParentFile().mkdirs();
						auteurPageJs = ToutEcrivain.create(classePageFichierJs);
					}
	
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
					ToutEcrivain wEntites = ToutEcrivain.create();
					ToutEcrivain wJsInit = ToutEcrivain.create();
					ToutEcrivain wWebsocket = ToutEcrivain.create();
					ToutEcrivain wWebsocketInput = ToutEcrivain.create();
					ToutEcrivain wPks = ToutEcrivain.create();
		
					o = auteurPageGenClasse;
					{
						SolrQuery rechercheSolr = new SolrQuery();   
						rechercheSolr.setQuery("*:*");
						rechercheSolr.setRows(1000000);
						String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
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
										wJsInit.tl(2, "if(");
										wJsInit.tl(4, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)");
										wJsInit.tl(4, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)");
										wJsInit.tl(4, ") {");
										wJsInit.tl(3, "tl(2, ", "\"", str_suggere(langueNom), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], $('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, true); \"", ");");
										wJsInit.tl(2, "} else {");
										wJsInit.tl(3, "tl(2, ", "\"", str_suggere(langueNom), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], $('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, false); \"", ");");
										wJsInit.tl(2, "}");
	//									wWebsocket.tl(2, "tl(2, \"", "await patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + \" + ", str_requeteSite(langueNom), "_.get", str_Requete(langueNom), StringUtils.capitalize(classeVarClePrimaire), "() + \" } ], {});\");");
										wPks.tl(2, "tl(4, \"if(c == '", entiteAttribuerNomSimple, "')\");");
										wPks.tl(2, "tl(5, \"", "await patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + pk2 } ], {});\");");
									}
									if(entiteSignature) {
										wJsInit.tl(2, "tl(2, ", "\"$('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature({'height':200}).bind('change', function(e){ patch", classeNomSimple, "Val([{ name: 'fq', value: 'pk:' + pk }], 'set", entiteVarCapitalise, "', $('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature('getData', 'default')); }); \"", ");");
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
	//						wWebsocket.tl(3, "await patch", classeNomSimple, "Vals( [ {name: 'fq', value: '", classeVarClePrimaire, ":' + pk} ], {});");
	//						wWebsocket.tl(1, "}");
	
							if(resultatFormPOST)
								wFormPOST.t(2).bgl("div");
							if(resultatFormPUTImport)
								wFormPUTImport.t(2).bgl("div");
							if(resultatFormPUTFusion)
								wFormPUTFusion.t(2).bgl("div");
							if(resultatFormPUTCopie)
								wFormPUTCopie.t(2).bgl("div");
							if(resultatFormPage)
								wFormPage.t(2).bgl("div");
							if(resultatFormPATCH)
								wFormPATCH.t(2).bgl("div");
							if(resultatFormRecherche)
								wFormRecherche.t(2).bgl("div");
						}
					}
	//	
	//				wEntites.l();
	//				wEntites.tl(1, "@Override protected void _pageH1(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
	//				if(classeVarTitre != null) {
	//					wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), "_ != null && ", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "() != null)");
	//					wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "()", ");");
	//					wEntites.tl(2, "else if(", StringUtils.uncapitalize(classeNomSimple), "_ != null)");
	//				} else {
	//					wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), "_ != null)");
	//				}
	//				if(contexteH1 != null)
	//					wEntites.tl(3, "c.o(", q(contexteH1), ");");
	//				else
	//					wEntites.tl(3, "c.o(", q(contexteUnNom), ");");
	//				if(!classePageSimple) {
	//					wEntites.tl(2, "else if(", str_liste(langueNom), classeNomSimple, " == null || ", str_liste(langueNom), classeNomSimple, ".size() == 0)");
	//					wEntites.tl(3, "c.o(", q(contexteAucunNomTrouve), ");");
	//				}
	//				if(contexteH1 != null) {
	//					wEntites.tl(2, "else");
	//					wEntites.tl(3, "c.o(", q(contexteH1), ");");
	//				}
	//				wEntites.tl(1, "}");
		
					wEntites.l();
					wEntites.tl(1, "@Override protected void _pageH1(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					if(classeVarH1 != null) {
						wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), "_ != null && ", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "() != null)");
						wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "()", ");");
						if(contexteH1 != null) {
							wEntites.tl(2, "else");
							wEntites.tl(3, "c.o(", q(contexteH1), ");");
						}
					} else if(contexteH1 != null)
						wEntites.tl(3, "c.o(", q(contexteH1), ");");
					else {
						wEntites.tl(3, "c.o(", q(contexteNomAdjectifPluriel), ");");
					}
					wEntites.tl(1, "}");
		
					wEntites.l();
					wEntites.tl(1, "@Override protected void _pageH2(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					if(classeVarH2 != null) {
						wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), "_ != null && ", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "() != null)");
						wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "()", ");");
						if(contexteH2 != null) {
							wEntites.tl(2, "else");
							wEntites.tl(3, "c.o(", q(contexteH2), ");");
						}
					} else {
						wEntites.tl(2, "c.o(", q(contexteH2), ");");
					}
					wEntites.tl(1, "}");
		
					wEntites.l();
					wEntites.tl(1, "@Override protected void _pageH3(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					if(classeVarH3 != null) {
						wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), "_ != null && ", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "() != null)");
						wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "()", ");");
						if(contexteH3 != null) {
							wEntites.tl(2, "else");
							wEntites.tl(3, "c.o(", q(contexteH3), ");");
						}
					} else {
						wEntites.tl(2, "c.o(", q(contexteH3), ");");
					}
					wEntites.tl(1, "}");
		
					wEntites.l();
					wEntites.tl(1, "@Override protected void _page", str_Titre(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					if(classeVarTitre != null) {
						wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), "_ != null && ", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "() != null)");
						wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarTitre), "()", ");");
						wEntites.tl(2, "else if(", StringUtils.uncapitalize(classeNomSimple), "_ != null)");
					} else {
						wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), "_ != null)");
					}
					wEntites.tl(3, "c.o(", q(contexteTitre), ");");
					if(!classePageSimple) {
						wEntites.tl(2, "else if(", str_liste(langueNom), classeNomSimple, " == null || ", str_liste(langueNom), classeNomSimple, ".size() == 0)");
						wEntites.tl(3, "c.o(", q(contexteAucunNomTrouve), ");");
					}
					if(contexteTitre != null) {
						wEntites.tl(2, "else");
						wEntites.tl(3, "c.o(", q(contexteTitre), ");");
					}
					wEntites.tl(1, "}");
		
					wEntites.l();
					wEntites.tl(1, "@Override protected void _pageUri(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					wEntites.tl(2, "c.o(", q(classePageUriMethode), ");");
					wEntites.tl(1, "}");
					for(String pageLangueNom : toutesLangues) {
						if(!StringUtils.equals(classePageLangueNom, pageLangueNom)) {
							String classePageUriMethodeLangue = (String)classeDoc.get(StringUtils.replace("classeApiUri" + classePageMethode + "_stored_string", StringUtils.capitalize(classePageLangueNom), StringUtils.capitalize(pageLangueNom)));
							
							if(classePageUriMethodeLangue != null) {
								wEntites.l();
								wEntites.tl(1, "@Override protected void _pageUri", StringUtils.capitalize(pageLangueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
								wEntites.tl(2, "c.o(", q(classePageUriMethodeLangue), ");");
								wEntites.tl(1, "}");
							}
						}
					}
			
					if(auteurPageClasse != null) {
						auteurPageClasse.l("package ", classeNomEnsemble, ";");
						auteurPageClasse.l();
						auteurPageClasse.l("/**");
						auteurPageClasse.l(" * ", str_Traduire(langueNom), ": false");
						if(StringUtils.equals(classePageMethode, "PageRecherche")) {
							for(String langueNom2 : autresLangues) {
								String classePageNomSimple2 = (String)classeDoc.get("classePageNomCanonique" + str_PageRecherche(langueNom2)  + "_" + langueNom2 + "_stored_string");
								if(classePageNomSimple2 != null)
									l(" * ", str_NomCanonique(langueNom), ".", langueNom2, ": ", classePageNomSimple2);
							}
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
					l(" * ", str_Traduire(langueNom), ": false");
					if(StringUtils.equals(classePageMethode, "PageRecherche")) {
						for(String langueNom2 : autresLangues) {
							String classeGenPageNomSimple2 = (String)classeDoc.get("classeGenPageNomCanonique" + str_PageRecherche(langueNom2)  + "_" + langueNom2 + "_stored_string");
							if(classeGenPageNomSimple2 != null)
								l(" * ", str_NomCanonique(langueNom), ".", langueNom2, ": ", classeGenPageNomSimple2);
						}
					}
					l(" **/");
					s("public class ", classeGenPageNomSimple);
					s(" extends ", classeGenPageNomSimple, "Gen");
					s("<", classePageSuperNomSimple, ">");
					l(" {");
	
					if(classeRoleLiresTrouves || classeRolesTrouves) {
						l();
						tl(1, "public static final List<String> ROLES = Arrays.asList(\"", StringUtils.join(classeRoles, "\", \""), "\");");
						tl(1, "public static final List<String> ROLE_READS = Arrays.asList(\"", StringUtils.join(classeRoleLires, "\", \""), "\");");
					}
					else {
						l();
						tl(1, "public static final List<String> ROLES = Arrays.asList();");
						tl(1, "public static final List<String> ROLE_READS = Arrays.asList();");
					}
	
					if(!classePageSimple) {
						l();
						tl(1, "/**");
						tl(1, " * {@inheritDoc}");
						tl(1, " * ");
						tl(1, " **/");
						tl(1, "protected void _", str_liste(langueNom), classeNomSimple, "(", classePartsCouverture.nomSimple(langueNom), "<", str_ListeRecherche(langueNom), "<", classeNomSimple, ">> c) {");
						tl(1, "}");
					}
					l();
					tl(1, "protected void _", StringUtils.uncapitalize(classeNomSimple), "_(", "", classePartsCouverture.nomSimple(langueNom), "<", classeNomSimple, "> c", ") {");
					if(classePageSimple) {
						tl(2, "c.o(new ", classeNomSimple, "());");
					} else {
						tl(2, "if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1)");
						tl(3, "c.o(", str_liste(langueNom), classeNomSimple, ".get(0)", ");");
					}
					tl(1, "}");
					s(wEntites);
		
					if(contexteDescription != null) {
						l();
						tl(1, "@Override protected void _pageDescription(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
						tl(3, "c.o(", q(contexteDescription), ");");
						tl(1, "}");
					}
		
					if(classeImage != null) {
						l();
						tl(1, "@Override protected void _pageImageUri(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
						tl(3, "c.o(", q("/png", classePageUriMethode, "-999.png"), ");");
						tl(1, "}");
					}
		
					if(contexteImageLargeur != null) {
						l();
						tl(1, "@Override protected void _pageImage", str_Largeur(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
						tl(3, "c.o(", contexteImageLargeur, ");");
						tl(1, "}");
					}
		
					if(contexteImageHauteur != null) {
						l();
						tl(1, "@Override protected void _pageImage", str_Hauteur(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
						tl(3, "c.o(", contexteImageHauteur, ");");
						tl(1, "}");
					}
		
					if(StringUtils.isNotBlank(contexteVideoId)) {
						l();
						tl(1, "@Override protected void _pageVideoId(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
						tl(3, "c.o(", q(contexteVideoId), ");");
						tl(1, "}");
					}
		
					if(StringUtils.isNotBlank(contexteIconeGroupe)) {
						l();
						tl(1, "@Override protected void _", str_contexte(langueNom), str_Icone(langueNom), str_Groupe(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
						tl(3, "c.o(", q(contexteIconeGroupe), ");");
						tl(1, "}");
					}
		
					if(StringUtils.isNotBlank(contexteIconeNom)) {
						l();
						tl(1, "@Override protected void _", str_contexte(langueNom), str_Icone(langueNom), str_Nom(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
						tl(3, "c.o(", q(contexteIconeNom), ");");
						tl(1, "}");
					}
		
					l();
					tl(1, "@Override public void ", str_initLoin(langueNom), classeGenPageNomSimple, "() {");
					tl(2, "init", classeGenPageNomSimple, "();");
					tl(2, "super.", str_initLoin(langueNom), classePartsMiseEnPage.nomSimple(langueNom), "();");
					tl(1, "}");
					l();
					tl(1, "@Override public void htmlScripts", classeGenPageNomSimple, "() {");
					t(2).l("e(\"script\").a(\"src\", ", str_statiqueUrlBase(langueNom), ", \"/js/", langueNom, "/", classePageNomSimple, ".js\").f().g(\"script\");");
					if(classeAttribuerNomSimplePages != null) {
						for(String classeAttribuerNomSimplePage : classeAttribuerNomSimplePages) {
							t(2).l("e(\"script\").a(\"src\", ", str_statiqueUrlBase(langueNom), ", \"/js/", langueNom, "/", classeAttribuerNomSimplePage, ".js\").f().g(\"script\");");
						}
					}
					tl(1, "}");
		
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
						String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
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
									String entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
									String entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
									String entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
									String entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
									Boolean entiteHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean"));
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
			
										wGetters.l();
										wGetters.tl(1, "public Boolean get", str_Colonne(langueNom), entiteVarCapitalise, "() {");
										wGetters.tl(2, "return true;");
										wGetters.tl(1, "}");
	
										wTh.tl(3, "if(get", str_Colonne(langueNom), entiteVarCapitalise, "()) {");
										wTh.tl(4, "e(\"th\").f().sx(", q(entiteNomAffichage), ").g(\"th\");");
										wTh.tl(3, "}");
			
										wTd.tl(4, "if(get", str_Colonne(langueNom), entiteVarCapitalise, "()) {");
										wTd.tl(5, "{ e(\"td\").f();");
										wTd.tl(6, "{ e(\"a\").a(\"href\", uri).f();");
										if(contexteIconeGroupe != null && contexteIconeNom != null && BooleanUtils.isTrue(entiteVarTitre))
											wTd.t(7).e("i").da("class", "fa", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, " ").df().dgl("i");
										wTd.tl(7, "{ e(\"span\").f();");
										wTd.tl(8, "sx(o.str", entiteVarCapitalise, "());");
										wTd.tl(7, "} g(\"span\");");
										wTd.tl(6, "} g(\"a\");");
										if(entiteHighlighting) {
											wTd.tl(6, "if(highlightList != null) {");
											wTd.t(7).be("div").da("class", "site-highlight ").dfl();
												wTd.t(8).sscl("StringUtils.join(highlightList, \" ... \")");
											wTd.t(7).bgl("div");
											wTd.tl(6, "}");
										}
										wTd.tl(5, "} g(\"td\");");
										wTd.tl(4, "}");
									}
	
									wFoot.tl(3, "if(get", str_Colonne(langueNom), entiteVarCapitalise, "()) {");
									wFoot.tl(4, "e(\"td\").f();");
									if(entiteFacetsTrouves) {
										for(String entiteFacet : entiteFacets) {
											if("sum".equals(entiteFacet)) {
				
												if("Double".equals(entiteSolrNomSimple))
													wFoot.tl(4, "BigDecimal ", entiteFacet, "_", entiteVar, " = Optional.ofNullable((", entiteSolrNomSimple, ")facets.get(\"", entiteFacet, "_", entiteVar, "\")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));");
												else
													wFoot.tl(4, entiteSolrNomSimple, " ", entiteFacet, "_", entiteVar, " = Optional.ofNullable((", entiteSolrNomSimple, ")facets.get(\"", entiteFacet, "_", entiteVar, "\")).orElse(new ", entiteSolrNomSimple, "(0));");
	
												wFoot.tl(4, "e(\"span\").a(\"class\", \"font-weight-bold \").f().sx(", entiteFacet, "_", entiteVar, ").g(\"span\");");
											}
										}
									}
									wFoot.tl(4, "g(\"td\");");
									wFoot.tl(3, "}");
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
						String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
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
//													t(tIndex + 3).s(classePrefixe, "e(\"input\")").l();
//													t(tIndex + 5).dal("type", "text");
//													t(tIndex + 5).s(classePrefixe).s(".a(\"class\", \"w3-input w3-border datepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\")");
//													t(tIndex + 5).dal("placeholder", str_DDDashMMDashYYYY_HHColonMM(langueNom));
//													t(tIndex + 5).dal("data-timeformat", str_ddDashMMDashyyyy(langueNom));
//													t(tIndex + 5).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")");
//													if(entiteDescription != null)
//														t(tIndex + 5).dal("title", entiteDescription + " (", str_DDDashMMDashYYYY(langueNom), ")");
//													tl(tIndex + 4, ".a(\"value\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", str_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV(langueNom), "\").format(", entiteVar, "));");
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
//													tl(tIndex + 3, "fg();");
//												}
//												else if("LocalTime".equals(entiteNomSimple)) {
//													t(tIndex + 3).s(classePrefixe, "e(\"input\")").l();
//													t(tIndex + 5).dal("type", "text");
//													t(tIndex + 5).s(classePrefixe).s(".a(\"class\", \"w3-input w3-border timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\")");
//													t(tIndex + 5).dal("placeholder", str_HHColonMM(langueNom));
//													t(tIndex + 5).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")");
//													if(entiteDescription != null)
//														t(tIndex + 5).da("title", entiteDescription + " (", str_HAposhAposmm(langueNom), ")");
//													tl(tIndex + 5, ".a(\"value\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", str_HAposhAposmm(langueNom), "\").format(", entiteVar, "));");
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
//													tl(tIndex + 3, "fg();");
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
		
					l();
					if(!classePageSimple) {
						tl(1, "@Override public void htmlScript", classeGenPageNomSimple, "() {");
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
									auteurPageJs.tl(1, "var json = $", str_formulaireValeurs(langueNom), ".find('.", "PUTImport", "_", str_liste(langueNom), "').val();");
									auteurPageJs.tl(1, "if(json != null && json !== '')");
									auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), success, error);");
								}
								else if(methodePUTFusion) {
									auteurPageJs.tl(1, "var json = $", str_formulaireValeurs(langueNom), ".find('.", str_PUTFusion(langueNom), "_", str_liste(langueNom), "').val();");
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
									String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
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
						tl(2, "l(\"$(document).ready(function() {\");");
						tl(2, "tl(1, \"document.onkeydown = function(evt) {\");");
						tl(2, "tl(2, \"evt = evt || window.event;\");");
						tl(2, "tl(2, \"var isEscape = false;\");");
						tl(2, "tl(2, \"if ('key' in evt) {\");");
						tl(2, "tl(3, \"isEscape = (evt.key === 'Escape' || evt.key === 'Esc');\");");
						tl(2, "tl(2, \"} else {\");");
						tl(2, "tl(3, \"isEscape = (evt.keyCode === 27);\");");
						tl(2, "tl(2, \"}\");");
						tl(2, "tl(2, \"if (isEscape) {\");");
						tl(2, "tl(3, \"$('.w3-modal:visible').hide();\");");
						tl(2, "tl(2, \"}\");");
						tl(2, "tl(1, \"};\");");
						tl(2, "tl(1, \"window.eventBus = new EventBus('/eventbus');\");");
						tl(2, "tl(1, \"var pk = \", Optional.ofNullable(", str_requeteSite(langueNom), "_.get", str_Requete(langueNom), StringUtils.capitalize(classeVarClePrimaire), "()).map(l -> l.toString()).orElse(\"null\"), \";\");");
						tl(2, "tl(1, \"if(pk != null) {\");");
						s(wJsInit);
						tl(2, "tl(1, \"}\");");
						tl(2, "tl(1, ", q("websocket", classeNomSimple, "(websocket", classeNomSimple, "Inner);"), ");");
	//					s(wWebsocket);
	//					tl(2, "tl(1, ", q("});"), ");");
						tl(2, "l(\"});\");");
						tl(1, "}");
						l();
						tl(1, "public void htmlFormPage", classeNomSimple, "(", classeNomSimple, " o) {");
						s(wFormPage);
						tl(1, "}");
						l();
						tl(1, "public void htmlFormPOST", classeNomSimple, "(", classeNomSimple, " o) {");
						s(wFormPOST);
						tl(1, "}");
						l();
						tl(1, "public void htmlFormPUTImport", classeNomSimple, "(", classeNomSimple, " o) {");
						t(2).be("div").da("class", "w3-cell-row ").dfl();
						t(3).s("e(\"textarea\")").l();
						t(4).s(".a(\"class\", \"", "PUTImport", "_", str_liste(langueNom), " w3-input w3-border \")").l("");
						t(4).s(".a(\"style\", \"height: 400px; \")").l();
						t(4).s(".a(\"placeholder\", \"{ \\\"", str_liste(langueNom), "\\\": [ { \\\"pk\\\": ... , \\\"", str_sauvegardes(langueNom), "\\\": [ ... ] }, ... ] }\")").l();
						t(4).s(";").l();
						t(4).s("f();").l();
						t(3).s("g(\"textarea\");").l();
						t(2).bgl("div");
						tl(1, "}");
						l();
						tl(1, "public void htmlForm", str_PUTFusion(langueNom), classeNomSimple, "(", classeNomSimple, " o) {");
						t(2).be("div").da("class", "w3-cell-row ").dfl();
						t(3).s("e(\"textarea\")").l();
						t(4).s(".a(\"class\", \"", str_PUTFusion(langueNom), "_", str_liste(langueNom), " w3-input w3-border \")").l();
						t(4).s(".a(\"style\", \"height: 400px; \")").l();
						t(4).s(".a(\"placeholder\", \"{ \\\"", str_liste(langueNom), "\\\": [ { \\\"pk\\\": ... , \\\"", str_sauvegardes(langueNom), "\\\": [ ... ] }, ... ] }\")").l();
						t(4).s(";").l();
						t(4).s("f();").l();
						t(3).s("g(\"textarea\");").l();
						t(2).bgl("div");
						tl(1, "}");
						l();
						tl(1, "public void htmlForm", str_PUTCopie(langueNom), classeNomSimple, "(", classeNomSimple, " o) {");
						s(wFormPUTCopie);
						tl(1, "}");
						l();
						tl(1, "public void htmlFormPATCH", classeNomSimple, "(", classeNomSimple, " o) {");
						s(wFormPATCH);
						tl(1, "}");
						l();
						tl(1, "public void htmlForm", str_Recherche(langueNom), classeNomSimple, "(", classeNomSimple, " o) {");
						s(wFormRecherche);
						tl(1, "}");
					}
					l();
					tl(1, "@Override public void htmlBody", classeGenPageNomSimple, "() {");
					if(classePageSimple) {
						l();
						tl(2, "if(StringUtils.isNotBlank(pageH1)) {");
	
						t(3).be("h1").dfl();
						t(5).be("span").da("class", "w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, " ").dfl();
						tl(4, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
						tl(5, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
						if(classeEntiteVars != null && classeEntiteVars.contains("pageH1"))
							t(4).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
						else
							t(4).e("span").da("class", " ").df().dsxq(contexteUnNom).dgl("span");
						t(4).bgl("span");
						t(3).bgl("h1");
	
						tl(2, "}");
	
						t(2).be("div").da("class", "w3-padding-16 w3-card-4 w3-light-grey ").dfl();
	
						if(classeEntiteVars != null && classeEntiteVars.contains("pageH2")) {
							tl(2, "if(StringUtils.isNotBlank(pageH2)) {");
							t(3).be("h2").dfl();
							t(4).be("span").da("class", "w3-bar-item w3-padding w3-center w3-block w3-", contexteCouleur, " ").dfl();
							t(5).e("span").da("class", " ").df().s(".sx(pageH2)").dgl("span");
							t(4).bgl("span");
							t(3).bgl("h2");
							tl(2, "}");
						}
			
						if(classeEntiteVars != null && classeEntiteVars.contains("pageH3")) {
							tl(2, "if(StringUtils.isNotBlank(pageH3)) {");
							t(3).be("h3").dfl();
							t(4).be("span").da("class", "w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " ").dfl();
							t(5).e("span").da("class", " ").df().s(".sx(pageH3)").dgl("span");
							t(4).bgl("span");
							t(3).bgl("h3");
							tl(2, "}");
						}
			
						if(contexteVideoId != null) {
							t(2).be("div").da("class", "site-video-box ").dfl();
								t(3).e("iframe").da("class", "site-video-embed ").da("width", "560").da("height", "315").s(".a(\"src\", pageVideoUrlEmbed)").da("frameborder", "0").da("allow", "autoplay; encrypted-media").da("allowfullscreen", "").df().dgl("iframe");
							t(2).bgl("div"); 
						}
						if(classeMethodeVars.contains("htmlBody")) {
							l();
							tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), "_ != null)");
							tl(3, StringUtils.uncapitalize(classeNomSimple), ".htmlBody();");
						}
						l();
					} else {
						l();
						tl(2, "OperationRequest ", str_operationRequete(langueNom), " = ", str_requeteSite(langueNom), "_.get", str_OperationRequete(langueNom), "();");
						tl(2, "JsonObject params = ", str_operationRequete(langueNom), ".getParams();");
						tl(2, "if(", str_liste(langueNom), classeNomSimple, " == null || ", str_liste(langueNom), classeNomSimple, ".size() == 0) {");
	//					t(3).l("// contexteAucunNomTrouve : ", contexteAucunNomTrouve);
						l();
	
						t(3).be("h1").dfl();
						t(4).be("a").da("href", classePageUriMethode).da("class", "w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, " ").dfl();
						tl(5, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
						tl(6, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
						t(5).e("span").da("class", " ").df().dsxq(contexteNomAdjectifPluriel).dgl("span");
						t(4).bgl("a");
						t(3).bgl("h1");
	
						t(3).e("div").da("class", "w3-padding-16 w3-card-4 w3-light-grey ").dfl();
	
						t(3).be("h2").dfl();
						t(4).be("span").da("class", "w3-bar-item w3-padding w3-center w3-block w3-", contexteCouleur, " ").dfl();
						tl(5, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
						tl(6, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
						t(5).e("span").da("class", " ").df().dsxq(contexteAucunNomTrouve).dgl("span");
						t(4).bgl("span");
						t(3).bgl("h2");
	
						tl(2, "} else if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\")) {");
	
						tl(3, classeNomSimple, " o = ", str_liste(langueNom), classeNomSimple, ".get(0);");
						tl(3, str_requeteSite(langueNom), "_.set", str_Requete(langueNom), "", StringUtils.capitalize(classeVarClePrimaire), "(o.get", StringUtils.capitalize(classeVarClePrimaire), "());");
						tl(3, "if(StringUtils.isNotEmpty(pageH1)) {");
						t(4).be("h1").dfl();
						t(5).be("a").da("href", classePageUriMethode).da("class", "w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, " ").dfl();
						tl(6, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
						tl(7, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
						t(6).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
						t(5).bgl("a");
						t(4).bgl("h1");
						tl(3, "}");
			
						t(3).e("div").da("class", "w3-padding-16 w3-card-4 w3-light-grey ").dfl();
	
						tl(3, "if(StringUtils.isNotEmpty(pageH2)) {");
						t(4).be("h2").dfl();
						t(5).be("span").da("class", "w3-bar-item w3-padding w3-center w3-block w3-", contexteCouleur, " ").dfl();
						t(6).e("span").da("class", " ").df().s(".sx(pageH2)").dgl("span");
						t(5).bgl("span");
						t(4).bgl("h2");
						tl(3, "}");
			
						tl(3, "if(StringUtils.isNotEmpty(pageH3)) {");
						t(4).be("h3").dfl();
						t(5).be("span").da("class", "w3-bar-item w3-padding w3-center w3-block w3-", contexteCouleur, " ").dfl();
						t(6).e("span").da("class", " ").df().s(".sx(pageH3)").dgl("span");
						t(5).bgl("span");
						t(4).bgl("h3");
						tl(3, "}");
			
						tl(2, "} else {");
	//					t(3).l("// contexteNomPluriel : plusiers ", contexteNomPluriel);
						l();
	//					t(3).be("h1").dfl();
	//					tl(4, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
	//					tl(5, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
	//					t(4).e("span").da("class", " ").df().dsxq(contexteNomPluriel).dgl("span");
	//					t(3).bgl("h1");
						t(3).be("h1").dfl();
						t(4).be("a").da("href", classePageUriMethode).da("class", "w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, " ").dfl();
						tl(5, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
						tl(6, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
						t(5).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
						t(4).bgl("a");
						t(3).bgl("h1");
	
	//					t(3).e("div").da("class", "w3-padding-16 w3-card-4 w3-light-grey ").dfl();
						t(3).e("div").da("class", "").dfl();
	
						t(4).be("div").dfl();
						tl(5, "JsonObject queryParams = Optional.ofNullable(", str_operationRequete(langueNom), ").map(OperationRequest::getParams).map(or -> or.getJsonObject(\"query\")).orElse(new JsonObject());");
						tl(5, "Long num = ", str_liste(langueNom), classeNomSimple, ".getQueryResponse().getResults().getNumFound();");
						tl(5, "String q = \"*:*\";");
						tl(5, "String query1 = \"", classeVarTexte, "\";");
						tl(5, "String query2 = \"\";");
						tl(5, "String query = \"*:*\";");
						tl(5, "for(String param", str_Nom(langueNom), " : queryParams.fieldNames()) {");
						tl(6, "String ", str_entite(langueNom), "Var = null;");
						tl(6, "String ", str_valeur(langueNom), str_Indexe(langueNom), " = null;");
						tl(6, "Object param", str_ValeursObjet(langueNom), " = queryParams.getValue(param", str_Nom(langueNom), ");");
						tl(6, "JsonArray param", str_Objets(langueNom), " = param", str_ValeursObjet(langueNom), " instanceof JsonArray ? (JsonArray)param", str_ValeursObjet(langueNom), " : new JsonArray().add(param", str_ValeursObjet(langueNom), ");");
						l();
						tl(6, "try {");
						tl(7, "for(Object param", str_Objet(langueNom), " : param", str_Objets(langueNom), ") {");
						tl(8, "switch(param", str_Nom(langueNom), ") {");
				
						tl(9, "case \"q\":");
						tl(10, "q = (String)param", str_Objet(langueNom), ";");
						tl(10, str_entite(langueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(langueNom), ", \":\"));");
						tl(10, str_valeur(langueNom), str_Indexe(langueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(langueNom), ", \":\")), \"UTF-8\");");
						tl(10, "query1 = ", str_entite(langueNom), "Var.equals(\"*\") ? query1 : ", str_entite(langueNom), "Var;");
						tl(10, "query2 = ", str_valeur(langueNom), str_Indexe(langueNom), ";");
						tl(10, "query = query1 + \":\" + query2;");
						tl(8, "}");
						tl(7, "}");
						tl(6, "} catch(Exception e) {");
						tl(7, "ExceptionUtils.rethrow(e);");
						tl(6, "}");
						tl(5, "}");
						l();
						tl(5, "Integer rows1 = Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getRows()).orElse(10);");
						tl(5, "Integer start1 = Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getStart()).orElse(1);");
						tl(5, "Integer start2 = start1 - rows1;");
						tl(5, "Integer start3 = start1 + rows1;");
						tl(5, "Integer rows2 = rows1 / 2;");
						tl(5, "Integer rows3 = rows1 * 2;");
						tl(5, "start2 = start2 < 0 ? 0 : start2;");
						tl(5, "StringBuilder fqs = new StringBuilder();");
						tl(5, "for(String fq : Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getFilterQueries()).orElse(new String[0])) {");
						tl(6, "if(!StringUtils.contains(fq, \"(\")) {");
						tl(7, "String fq1 = StringUtils.substringBefore(fq, \"_\");");
						tl(7, "String fq2 = StringUtils.substringAfter(fq, \":\");");
						tl(7, "if(!StringUtils.startsWithAny(fq, \"", str_classeNomsCanoniques(langueNom), "_\", \"", str_archive(langueNom), "_\", \"", str_supprime(langueNom), "_\", \"sessionId\", \"", str_utilisateur(langueNom), str_Cle(langueNom), "s\"))");
						tl(8, "fqs.append(\"&fq=\").append(fq1).append(\":\").append(fq2);");
						tl(6, "}");
						tl(5, "}");
						tl(5, "StringBuilder sorts = new StringBuilder();");
						tl(5, "for(SortClause sort : Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getSorts()).orElse(Arrays.asList())) {");
						tl(6, "sorts.append(\"&sort=\").append(StringUtils.substringBefore(sort.getItem(), \"_\")).append(\" \").append(sort.getOrder().name());");
						tl(5, "}");
						l();
						tl(5, "if(start1 == 0) {");
						t(6).e("i").da("class", "fas fa-arrow-square-left w3-opacity ").df().dgl("i");
						tl(5, "} else {");
						tl(6, "{ e(\"a\").a(\"href\", \"", classePageUriMethode + "?q=\", query, fqs, sorts, \"&start=\", start2, \"&rows=\", rows1).f();");
						t(7).e("i").da("class", "fas fa-arrow-square-left ").df().dgl("i");
						t(6).bgl("a");
						tl(5, "}");
						l();
						tl(5, "if(rows1 <= 1) {");
						t(6).e("i").da("class", "fas fa-minus-square w3-opacity ").df().dgl("i");
						tl(5, "} else {");
						tl(6, "{ e(\"a\").a(\"href\", \"", classePageUriMethode + "?q=\", query, fqs, sorts, \"&start=\", start1, \"&rows=\", rows2).f();");
						t(7).e("i").da("class", "fas fa-minus-square ").df().dgl("i");
						t(6).bgl("a");
						tl(5, "}");
						l();
						tl(5, "{ e(\"a\").a(\"href\", \"", classePageUriMethode + "?q=\", query, fqs, sorts, \"&start=\", start1, \"&rows=\", rows3).f();");
						t(6).e("i").da("class", "fas fa-plus-square ").df().dgl("i");
						t(5).bgl("a");
						l();
						tl(5, "if(start3 >= num) {");
						t(6).e("i").da("class", "fas fa-arrow-square-right w3-opacity ").df().dgl("i");
						tl(5, "} else {");
						tl(6, "{ e(\"a\").a(\"href\", \"", classePageUriMethode + "?q=\", query, fqs, sorts, \"&start=\", start3, \"&rows=\", rows1).f();");
						t(7).e("i").da("class", "fas fa-arrow-square-right ").df().dgl("i");
						t(6).bgl("a");
						tl(5, "}");
						tl(6, "e(\"span\").f().sx((start1 + 1), \" - \", (start1 + rows1), \" ", str_de(langueNom), " \", num).g(\"span\");");
	
						t(4).bgl("div");
	
						tl(4, "table1", classeGenPageNomSimple, "();");
						tl(2, "}");
	
						// singulier part 2
						l();
	//					tl(2, "if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\") && params.getJsonObject(\"query\").getJsonArray(\"fq\") == null) {");
						tl(2, "if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\")) {");
						t(3).l(classeNomSimple, " o = ", str_liste(langueNom), classeNomSimple, ".first();");
						l();
						t(3).be("div").da("class", "").dfl();
						if(classeVarClePrimaire != null) {
							l();
							tl(4, "if(o.get", StringUtils.capitalize(classeVarClePrimaire), "() != null) {");
							t(5).be("form").da("action", classeApiUri).da("id", classeNomSimple, "Form").da("style", "display: inline-block; width: 100%; ").da("onsubmit", "event.preventDefault(); return false; ").dfl();
							t(6).e("input").l();
							t(6).dal("name", classeVarClePrimaire);
							t(6).dal("class", str_valeur(langueNom), StringUtils.capitalize(classeVarClePrimaire));
							t(6).dal("type", "hidden");
							tl(6, ".a(\"value\", o.get", StringUtils.capitalize(classeVarClePrimaire), "())");
							t(6).dfgl();
							t(6).e("input").l();
							t(6).dal("name", "focusId");
							t(6).dal("type", "hidden");
							t(6).dfgl();
							t(5).bgl("form");
							tl(5, "htmlFormPage", classeNomSimple, "(o);");
							tl(4, "}");
						}
						if(classeMethodeVars.contains("htmlBody")) {
							l();
							tl(4, "if(o != null)");
							tl(5, "o.htmlBody();");
						}
						l();
						t(3).bgl("div").l();
						tl(2, "}");
	
						// formulaires
						if(!classePageSimple) {
							tl(2, "htmlBodyForms", classeGenPageNomSimple, "();");
						}
			
						t(2).gl("div");
						tl(1, "}");
						l();
						tl(1, "public void table1", classeGenPageNomSimple, "() {");
						t(2).be("table").da("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").dfl();
						tl(3, "table2", classeGenPageNomSimple, "();");
						t(2).bgl("table");
						tl(1, "}");
						l();
						tl(1, "public void table2", classeGenPageNomSimple, "() {");
						tl(2, "thead1", classeGenPageNomSimple, "();");
						tl(2, "tbody1", classeGenPageNomSimple, "();");
						tl(2, "tfoot1", classeGenPageNomSimple, "();");
						tl(1, "}");
						l();
						tl(1, "public void thead1", classeGenPageNomSimple, "() {");
						t(2).be("thead").da("class", "w3-", contexteCouleur, " w3-hover-", contexteCouleur, " ").dfl();
						tl(3, "thead2", classeGenPageNomSimple, "();");
						t(2).bgl("thead");
						tl(1, "}");
						l();
						tl(1, "public void thead2", classeGenPageNomSimple, "() {");
						t(3).be("tr").dfl();
						s(wTh);
						t(3).bgl("tr");
						tl(1, "}");
						l();
						tl(1, "public void tbody1", classeGenPageNomSimple, "() {");
						t(2).be("tbody").dfl();
						tl(3, "tbody2", classeGenPageNomSimple, "();");
						t(2).bgl("tbody");
						tl(1, "}");
						l();
						tl(1, "public void tbody2", classeGenPageNomSimple, "() {");
						tl(2, "Map<String, Map<String, List<String>>> highlighting = ", str_liste(langueNom), classeNomSimple, ".getQueryResponse().getHighlighting();");
						tl(2, "for(int i = 0; i < ", str_liste(langueNom), classeNomSimple, ".size(); i++) {");
						tl(3, classeNomSimple, " o = ", str_liste(langueNom), classeNomSimple, ".getList().get(i);");
						tl(3, "Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());");
						tl(3, "List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));");
						tl(3, "String uri = ", classeEntiteVars.contains("pageUri") ? "o.getPageUri()" : (q(classePageUriMethode, "/") + " + o.get" + StringUtils.capitalize(classeVarClePrimaire) + "()"), ";");
						tl(3, "{ e(\"tr\").f();");
						s(wTd);
						tl(3, "} g(\"tr\");");
						tl(2, "}");
						tl(1, "}");
						l();
						tl(1, "public void tfoot1", classeGenPageNomSimple, "() {");
						t(2).be("tfoot").da("class", "w3-", contexteCouleur, " w3-hover-", contexteCouleur, " ").dfl();
						tl(3, "tfoot2", classeGenPageNomSimple, "();");
						t(2).bgl("tfoot");
						tl(1, "}");
						l();
						tl(1, "public void tfoot2", classeGenPageNomSimple, "() {");
						t(2).be("tr").dfl();
						tl(3, "SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ".getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get(\"facets\")).orElse(new SimpleOrderedMap());");
						s(wFoot);
						t(2).bgl("tr");
						tl(1, "}");
						s(wGetters);
						l();
						tl(1, "public void htmlBodyForms", classeGenPageNomSimple, "() {");
						if(!classePageSimple) {
	
							tl(2, "if(");
							tl(4, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)");
							tl(4, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)");
							tl(4, ") {");
	
							t(3).e("div").da("class", "w3-margin-top ").dfl();
							l();
	
							// refraîchir 1 //
							tl(3, "if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1) {");
							t(4).be("button").l();
							t(5).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " ");
							t(6).da("id", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteCe)), classeGenPageNomSimple).l();
							t(6).s(".a(\"onclick\", \"patch", classeNomSimple, "Vals( [ {name: 'fq', value: '", classeVarClePrimaire, ":' + \" + ", str_requeteSite(langueNom), "_.get", str_Requete(langueNom), "", StringUtils.capitalize(classeVarClePrimaire), "() + \" } ], {}, function() { ", str_ajouterLueur(langueNom), "($('#", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteCe)), classeGenPageNomSimple, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteCe)), classeGenPageNomSimple, "')); }); return false; \")").dfl();
							t(6).e("i").da("class", "fas fa-sync-alt ").df().dgl("i");
							t(5).sxqscl(str_recharger(langueNom), " ", contexteCeNom);
							t(4).bgl("button");
							tl(3, "}");
	
							tl(2, "}");
							tl(2, "if(");
							tl(4, "", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "().contains(\"SiteAdmin\")");
							tl(4, "|| ", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "().contains(\"SiteAdmin\")");
							tl(4, ") {");
	
	
							for(String classeApiMethode : classeApiMethodes) {
								String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
								String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
								String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
								String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");
				
								if("application/json".equals(classeApiTypeMediaMethode) && (classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals(str_PUTCopie(langueNom)) || classeApiMethode.equals(str_PUTFusion(langueNom)) || classeApiMethode.equals("PUTImport"))) {
									Integer tab = classeApiMethodeMethode.contains("PATCH") || classeApiMethodeMethode.contains("POST") || classeApiMethodeMethode.contains("PUT") ? 0 : 1;
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
				
				
									l();
									if(tab > 0)
										tl(3, "if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1) {");
									t(3 + tab).be("button").l();
									t(4 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " ");
									t(4 + tab).dal("onclick", "$('#", classeApiOperationIdMethode, str_Modale(langueNom), "').show(); ");
									t(4 + tab).dfl();
	
										if(classeApiMethodeMethode.contains("POST"))
											t(4 + tab).e("i").da("class", "fas fa-file-plus ").df().dgl("i");
										else if(classeApiMethodeMethode.contains("PATCH"))
											t(4 + tab).e("i").da("class", "fas fa-edit ").df().dgl("i");
										else if(classeApiMethode.contains("PUTImport"))
											t(4 + tab).e("i").da("class", "fas fa-file-import ").df().dgl("i");
										else if(classeApiMethode.contains(str_PUTFusion(langueNom)))
											t(4 + tab).e("i").da("class", "fas fa-code-merge ").df().dgl("i");
										else if(classeApiMethode.contains(str_PUTCopie(langueNom)))
											t(4 + tab).e("i").da("class", "fas fa-copy ").df().dgl("i");
	
										t(4 + tab).sxqscl(methodeTitreValeurs);
									t(3 + tab).bgl("button");
									{ t(3 + tab).be("div").da("id", classeApiOperationIdMethode, str_Modale(langueNom)).da("class", "w3-modal w3-padding-32 ").dfl();
										{ t(4 + tab).be("div").da("class", "w3-modal-content ").dfl();
											{ t(5 + tab).be("div").da("class", "w3-card-4 ").dfl();
												{ t(6 + tab).be("header").da("class", "w3-container w3-", contexteCouleur, " ").dfl();
													t(7 + tab).e("span").da("class", "w3-button w3-display-topright ").da("onclick", "$('#", classeApiOperationIdMethode, str_Modale(langueNom), "').hide(); ").df().dsxq("×").dgl("span");
													t(7 + tab).e("h2").da("class", "w3-padding ").df().dsxq(methodeTitreValeurs).dgl("h2");
												} t(6 + tab).bgl("header");
					
												{ t(6 + tab).be("div").da("class", "w3-container ").da("id", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom)).dfl();
													tl(7+ tab, classeNomSimple, " o = new ", classeNomSimple, "();");
		//											tl(7+ tab, "o.", str_initLoin(langueNom), str_PourClasse(langueNom), "(", str_requeteSite(langueNom), "_);");
													tl(7+ tab, "o.set", str_RequeteSite(langueNom), "_(", str_requeteSite(langueNom), "_);");
													if("PATCH".equals(classeApiMethodeMethode) || str_PUTFusion(langueNom).equals(classeApiMethodeMethode) || str_PUTCopie(langueNom).equals(classeApiMethodeMethode) || "PUTImport".equals(classeApiMethodeMethode)) {
														l();
					
														if("DELETE".equals(classeApiMethodeMethode))
															tl(7 + tab, "htmlFormPATCH", classeNomSimple, "(o);");
														else
															tl(7 + tab, "htmlForm", classeApiMethodeMethode, classeNomSimple, "(o);");
					
														t(7 + tab).e("button").l();
														t(8 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-", contexteCouleur, " ");
						
														if("POST".equals(classeApiMethodeMethode))
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \")");
														else if("PATCH".equals(classeApiMethodeMethode))
//															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireFiltres(langueNom), "'), $('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "'), \", Optional.ofNullable(", StringUtils.uncapitalize(classeNomSimple), "_).map(", classeNomSimple, "::get", StringUtils.capitalize(classeVarClePrimaire), ").map(a -> a.toString()).orElse(\"null\"), \", function() {}, function() {}); \")");
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(null, $('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "'), \", Optional.ofNullable(", StringUtils.uncapitalize(classeNomSimple), "_).map(", classeNomSimple, "::get", StringUtils.capitalize(classeVarClePrimaire), ").map(a -> a.toString()).orElse(\"null\"), \", function() {}, function() {}); \")");
														else if("PUTImport".equals(classeApiMethode))
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "'), \", Optional.ofNullable(", StringUtils.uncapitalize(classeNomSimple), "_).map(", classeNomSimple, "::get", StringUtils.capitalize(classeVarClePrimaire), ").map(a -> a.toString()).orElse(\"null\"), \"); \")");
														else if(str_PUTFusion(langueNom).equals(classeApiMethode))
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "'), \", Optional.ofNullable(", StringUtils.uncapitalize(classeNomSimple), "_).map(", classeNomSimple, "::get", StringUtils.capitalize(classeVarClePrimaire), ").map(a -> a.toString()).orElse(\"null\"), \"); \")");
														else if(str_PUTCopie(langueNom).equals(classeApiMethode))
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "'), \", Optional.ofNullable(", StringUtils.uncapitalize(classeNomSimple), "_).map(", classeNomSimple, "::get", StringUtils.capitalize(classeVarClePrimaire), ").map(a -> a.toString()).orElse(\"null\"), \"); \")");
														else if(tab > 0)
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.get", StringUtils.capitalize(classeVarClePrimaire), "(), \"); \")");
														else
															t(8 + tab).dal("onclick", classeApiOperationIdMethode, "(); ");
						
														t(8 + tab).df().dsxq(methodeTitreValeurs).l();
														t(7 + tab).dgl("button");
														l();
													}
													else {
														l();
														t(7 + tab).l("// Form ", classeApiMethodeMethode);
														{ t(7 + tab).be("div").da("id", classeApiOperationIdMethode, "Form").dfl();
					
														if("DELETE".equals(classeApiMethodeMethode))
															tl(8 + tab, "htmlFormPATCH", classeNomSimple, "(o);");
														else if("PUTImport".equals(classeApiMethode))
															tl(8 + tab, "htmlFormPUTImport", classeNomSimple, "(o);");
														else if(str_PUTFusion(langueNom).equals(classeApiMethode))
															tl(8 + tab, "htmlForm", str_PUTFusion(langueNom), classeNomSimple, "(o);");
														else if(str_PUTCopie(langueNom).equals(classeApiMethode))
															tl(8 + tab, "htmlForm", str_PUTCopie(langueNom), classeNomSimple, "(o);");
														else
															tl(8 + tab, "htmlForm", classeApiMethodeMethode, classeNomSimple, "(o);");
					
														} t(7 + tab).bgl("div");
														t(7 + tab).e("button").l();
														t(8 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-", contexteCouleur, " ");
						
						//								tl(8 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classeApiOperationIdMethode, "Form').serializeArray().reduce(function(a, x) { a[x.name] = x.value; return a; }, {}))); \")");
						//								tl(8 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classeApiOperationIdMethode, "Form').serializeObject())); \")");
						
														if("POST".equals(classeApiMethodeMethode))
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \")");
														else if("PATCH".equals(classeApiMethodeMethode))
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireFiltres(langueNom), "'), $('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "'), function() {}, function() {}); \")");
														else if("PUTImport".equals(classeApiMethode))
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \")");
														else if(str_PUTFusion(langueNom).equals(classeApiMethode))
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \")");
														else if(str_PUTCopie(langueNom).equals(classeApiMethode))
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form'), \", ", StringUtils.uncapitalize(classeNomSimple), "_ == null ? \"null\" : ", StringUtils.uncapitalize(classeNomSimple), "_.get", StringUtils.capitalize(classeVarClePrimaire), "(), \"); \")");
														else if(tab > 0)
															tl(8 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.get", StringUtils.capitalize(classeVarClePrimaire), "(), \"); \")");
														else
															t(8 + tab).dal("onclick", classeApiOperationIdMethode, "(); ");
						
														t(8 + tab).df().dsxq(methodeTitreValeurs).l();
														t(7 + tab).dgl("button");
														l();
													}
												} t(6 + tab).bgl("div");
											} t(5 + tab).bgl("div");
										} t(4 + tab).bgl("div");
									} t(3 + tab).bgl("div");
				
									l();
									if(tab > 0)
										tl(3, "}");
								}
							}
							t(3).gl("div");
	
							t(2).s("}").l();
							tl(2, "html", str_Suggere(langueNom), classeGenPageNomSimple, "(this, null, ", str_liste(langueNom), classeNomSimple, ");");
						}
					}
					tl(1, "}");
	
					if(!classePageSimple) {
						l();
						tl(1, "/**");
						if(StringUtils.equals(langueNomActuel, langueNom) && str_PageRecherche(langueNomActuel).equals(classePageMethode))
						for(String langueNom2 : autresLangues) {
							String classeGenPageNomSimple2 = (String)classeDoc.get("classeGenPageNomSimple" + str_PageRecherche(langueNom2)  + "_" + langueNom2 + "_stored_string");
							String classeNomSimple2 = (String)classeDoc.get("classeNomSimple" + "_" + langueNom2 + "_stored_string");
							String contexteTousNom2 = (String)classeDoc.get("contexteTousNom" + "_" + langueNom2 + "_stored_string");
							String contexteNomAdjectifPluriel2 = (String)classeDoc.get("contexteNomAdjectifPluriel" + "_" + langueNom2 + "_stored_string");
							String classePageUriMethode2 = (String)classeDoc.get("classeApiUri" + str_PageRecherche(langueNom2) + "_" + langueNom2 + "_stored_string");
							String classeVarSuggere2 = (String)classeDoc.get("classeVarSuggere" + "_" + langueNom2 + "_stored_string");
							String classeVarTexte2 = (String)classeDoc.get("classeVarTexte" + "_" + langueNom2 + "_stored_string");
	
							tl(1, " * Var.", langueNom2, ": html", str_Suggere(langueNom2), classeGenPageNomSimple2);
							tl(1, " * r: \"", classePageUriMethode, "\"");
							tl(1, " * r.", langueNom2, ": \"", classePageUriMethode2, "\"");
							tl(1, " * r: \"", str_voir(langueNom), " ", contexteTousNom, "\"");
							tl(1, " * r.", langueNom2, ": \"", str_voir(langueNom2), " ", contexteTousNom2, "\"");
							tl(1, " * r: \"", str_recharger(langueNom), classeGenPageNomSimple, "\"");
							tl(1, " * r.", langueNom2, ": \"", str_recharger(langueNom2), classeGenPageNomSimple2, "\"");
							tl(1, " * r: \"", str_recharger(langueNom), " ", contexteTousNom, "\"");
							tl(1, " * r.", langueNom2, ": \"", str_recharger(langueNom2), " ", contexteTousNom2, "\"");
							tl(1, " * r: \"", str_rechercher(langueNom), " ", contexteNomAdjectifPluriel, str_deuxPoints(langueNom), "\"");
							tl(1, " * r.", langueNom2, ": \"", str_rechercher(langueNom2), " ", contexteNomAdjectifPluriel2, str_deuxPoints(langueNom2), "\"");
							tl(1, " * r: \"", str_suggere(langueNom), "Form", classeNomSimple, "\"");
							tl(1, " * r.", langueNom2, ": \"", str_suggere(langueNom2), "Form", classeNomSimple2, "\"");
							tl(1, " * r: \"", str_rechercher(langueNom), " ", contexteNomAdjectifPluriel, "\"");
							tl(1, " * r.", langueNom2, ": \"", str_rechercher(langueNom2), " ", contexteNomAdjectifPluriel2, "\"");
							tl(1, " * r: \"", str_suggere(langueNom), classeNomSimple, " w3-input w3-border w3-cell w3-cell-middle ", "\"");
							tl(1, " * r.", langueNom2, ": \"", str_suggere(langueNom2), classeNomSimple2, " w3-input w3-border w3-cell w3-cell-middle ", "\"");
							tl(1, " * r: \"", str_suggere(langueNom), classeNomSimple, "\"");
							tl(1, " * r.", langueNom2, ": \"", str_suggere(langueNom2), classeNomSimple2, "\"");
	
							tl(1, " * r: ", "patch", classeNomSimple, "Vals", "");
							tl(1, " * r.", langueNom2, ": ", "patch", classeNomSimple2, "Vals", "");
							tl(1, " * r: ", str_ajouterLueur(langueNom), "");
							tl(1, " * r.", langueNom2, ": ", str_ajouterLueur(langueNom2), "");
							tl(1, " * r: ", str_recharger(langueNom), classeGenPageNomSimple, "");
							tl(1, " * r.", langueNom2, ": ", str_recharger(langueNom2), classeGenPageNomSimple2, "");
							tl(1, " * r: ", str_ajouterErreur(langueNom), "");
							tl(1, " * r.", langueNom2, ": ", str_ajouterErreur(langueNom2), "");
							tl(1, " * r: ", str_suggere(langueNom), classeNomSimple, StringUtils.capitalize(classeVarSuggere));
							tl(1, " * r.", langueNom2, ": ", str_suggere(langueNom2), classeNomSimple2, StringUtils.capitalize(classeVarSuggere2));
							tl(1, " * r: ", str_texte(langueNom), classeNomSimple, StringUtils.capitalize(classeVarTexte));
							tl(1, " * r.", langueNom2, ": ", str_texte(langueNom2), classeNomSimple2, StringUtils.capitalize(classeVarTexte2));
							tl(1, " * r: ", "'", classeVarSuggere, ":'", "");
							tl(1, " * r.", langueNom2, ": ", "'", classeVarSuggere2, ":'", "");
							tl(1, " * r: ", "'", classeVarTexte, ":'", "");
							tl(1, " * r.", langueNom2, ": ", "'", classeVarTexte2, ":'", "");
							tl(1, " * r: ", "'#", str_suggere(langueNom), "List", classeNomSimple, "'", "");
							tl(1, " * r.", langueNom2, ": ", "'#", str_suggere(langueNom2), "List", classeNomSimple2, "'", "");
							tl(1, " * r: \"", str_suggere(langueNom), "List", classeNomSimple, "\"");
							tl(1, " * r.", langueNom2, ": \"", str_suggere(langueNom2), "List", classeNomSimple2, "\"");
						}
						tl(1, "**/");
						tl(1, "public static void html", str_Suggere(langueNom), classeGenPageNomSimple, "(", classePartsMiseEnPage.nomSimple(langueNom), " p, String id, ", str_ListeRecherche(langueNom), "<", classeNomSimple, "> ", str_liste(langueNom), classeNomSimple, ") {");
						tl(2, classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_ = p.get", str_RequeteSite(langueNom), "_();");
						tl(2, "try {");
						tl(3, "OperationRequest ", str_operationRequete(langueNom), " = ", str_requeteSite(langueNom), "_.get", str_OperationRequete(langueNom), "();");
						tl(3, "JsonObject queryParams = Optional.ofNullable(", str_operationRequete(langueNom), ").map(OperationRequest::getParams).map(or -> or.getJsonObject(\"query\")).orElse(new JsonObject());");
						tl(3, "String q = \"*:*\";");
						tl(3, "String query1 = \"", classeVarTexte, "\";");
						tl(3, "String query2 = \"\";");
						tl(3, "for(String param", str_Nom(langueNom), " : queryParams.fieldNames()) {");
						tl(4, "String ", str_entite(langueNom), "Var = null;");
						tl(4, "String ", str_valeur(langueNom), str_Indexe(langueNom), " = null;");
						tl(4, "Object param", str_ValeursObjet(langueNom), " = queryParams.getValue(param", str_Nom(langueNom), ");");
						tl(4, "JsonArray param", str_Objets(langueNom), " = param", str_ValeursObjet(langueNom), " instanceof JsonArray ? (JsonArray)param", str_ValeursObjet(langueNom), " : new JsonArray().add(param", str_ValeursObjet(langueNom), ");");
						l();
						tl(4, "try {");
						tl(5, "for(Object param", str_Objet(langueNom), " : param", str_Objets(langueNom), ") {");
						tl(6, "switch(param", str_Nom(langueNom), ") {");
				
						tl(7, "case \"q\":");
						tl(8, "q = (String)param", str_Objet(langueNom), ";");
						tl(8, str_entite(langueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(langueNom), ", \":\"));");
						tl(8, str_valeur(langueNom), str_Indexe(langueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(langueNom), ", \":\")), \"UTF-8\");");
						tl(8, "query1 = ", str_entite(langueNom), "Var.equals(\"*\") ? query1 : ", str_entite(langueNom), "Var;");
						tl(8, "query2 = ", str_valeur(langueNom), str_Indexe(langueNom), ".equals(\"*\") ? \"\" : ", str_valeur(langueNom), str_Indexe(langueNom), ";");
						tl(6, "}");
						tl(5, "}");
						tl(4, "} catch(Exception e) {");
						tl(5, "ExceptionUtils.rethrow(e);");
						tl(4, "}");
						tl(3, "}");
						l();
						tl(3, "Integer rows1 = Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getRows()).orElse(10);");
						tl(3, "Integer start1 = Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getStart()).orElse(1);");
						tl(3, "Integer start2 = start1 - rows1;");
						tl(3, "Integer start3 = start1 + rows1;");
						tl(3, "Integer rows2 = rows1 / 2;");
						tl(3, "Integer rows3 = rows1 * 2;");
						tl(3, "start2 = start2 < 0 ? 0 : start2;");
						tl(3, "StringBuilder fqs = new StringBuilder();");
						tl(3, "for(String fq : Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getFilterQueries()).orElse(new String[0])) {");
						tl(4, "if(!StringUtils.contains(fq, \"(\")) {");
						tl(5, "String fq1 = StringUtils.substringBefore(fq, \"_\");");
						tl(5, "String fq2 = StringUtils.substringAfter(fq, \":\");");
						tl(5, "if(!StringUtils.startsWithAny(fq, \"", str_classeNomsCanoniques(langueNom), "_\", \"", str_archive(langueNom), "_\", \"", str_supprime(langueNom), "_\", \"sessionId\", \"", str_utilisateur(langueNom), str_Cle(langueNom), "s\"))");
						tl(6, "fqs.append(\"&fq=\").append(fq1).append(\":\").append(fq2);");
						tl(4, "}");
						tl(3, "}");
						tl(3, "StringBuilder sorts = new StringBuilder();");
						tl(3, "for(SortClause sort : Optional.ofNullable(", str_liste(langueNom), classeNomSimple, ").map(l -> l.getSorts()).orElse(Arrays.asList())) {");
						tl(4, "sorts.append(\"&sort=\").append(StringUtils.substringBefore(sort.getItem(), \"_\")).append(\" \").append(sort.getOrder().name());");
						tl(3, "}");
						l();
	
						tl(3, "if(");
						tl(5, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ", classeGenPageNomSimple, ".ROLES)");
						tl(5, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ", classeGenPageNomSimple, ".ROLES)");
						tl(5, ") {");
	
						// recharger tous //
//						t(4).s("if(", str_liste(langueNom), classeNomSimple, " == null) {").l();
						t(5).s("{ p.").e("div").da("class", "").dfl();
						t(6).s("{ p.").e("button").s(".a(\"id\", \"", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteTous)), classeGenPageNomSimple, "\", id)").da("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " ");
						s(".a(\"onclick\", \"patch", classeNomSimple, "Vals([], {}, function() { ", str_ajouterLueur(langueNom), "($('#", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteTous)), classeGenPageNomSimple, "\", id, \"')); }, function() { ", str_ajouterErreur(langueNom), "($('#", str_recharger(langueNom), StringUtils.trim(StringUtils.capitalize(contexteTous)), classeGenPageNomSimple, "\", id, \"')); }); \")");
						dfl();
						t(7).s("p.").e("i").da("class", "fas fa-sync-alt ").df().dgl("i");
						t(7).s("p.").sxqscl(str_recharger(langueNom), " ", contexteTousNom);
						t(6).s("} p.").gl("button");
						t(5).s("} p.").gl("div");
//						t(4).s("}").l();
	
						t(3).s("}").l();
	
						t(3).s("{ p.").e("div").da("class", "w3-cell-row ").dfl();
						t(4).s("{ p.").e("div").da("class", "w3-cell ").dfl();
						t(5).s("{ p.").e("span").dfl();
						t(6).s("p.").sxqscl(str_rechercher(langueNom), " ", contexteNomAdjectifPluriel, str_deuxPoints(langueNom));
						t(5).s("} p.").gl("span");
						t(4).s("} p.").gl("div");
						t(3).s("} p.").gl("div");
						t(3).s("{ p.").e("div").da("class", "w3-bar ").dfl();
						l();
		
						t(4).s("p.").e("input").l();
						t(5).dal("type", "text");
		
						if(contexteRechercherTousNom != null) {
							t(5).dal("placeholder", contexteRechercherTousNom);
						}
		
						t(5).dal("class", str_suggere(langueNom), classeNomSimple, " w3-input w3-border w3-bar-item ");
						t(5).dal("name", str_suggere(langueNom), classeNomSimple);
						t(5).s(".a(\"id\", \"", str_suggere(langueNom), classeNomSimple, "\", id)").l();
						t(5).dal("autocomplete", "off");
						t(5).s(".a(\"oninput\", \"", str_suggere(langueNom), classeNomSimple, StringUtils.capitalize(classeVarSuggere), "( [ { 'name': 'q', 'value': '", classeVarSuggere, ":' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", classeVarClePrimaire, classeVarUrlPk == null ? "" : "," + classeVarUrlPk, classeVarTitre == null ? "" : "," + classeVarTitre, "' } ], $('#", str_suggere(langueNom), "List", classeNomSimple, "\", id, \"'), \", p.get", str_RequeteSite(langueNom), "_().get", str_Requete(langueNom), "", StringUtils.capitalize(classeVarClePrimaire), "(), \"); \")").l();
						tl(5, ".a(\"onkeyup\", \"if (event.keyCode === 13) { event.preventDefault(); window.location.href = '", classePageUriMethode + "?q=\", query1, \":' + encodeURIComponent(this.value) + '\", fqs, sorts, \"&start=\", start2, \"&rows=\", rows1, \"'; }\"); ");
						t(4).s("if(", str_liste(langueNom), classeNomSimple, " != null)").l();
						t(5).l("p.a(\"value\", query2);");
						t(4).s("p.").fgl();
						t(4).s("{ p.").e("button").l();
						t(5).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", contexteCouleur, " ");
						tl(5, ".a(\"onclick\", \"window.location.href = '", classePageUriMethode + "?q=\", query1, \":' + encodeURIComponent(this.previousElementSibling.value) + '\", fqs, sorts, \"&start=\", start2, \"&rows=\", rows1, \"'; \") ");
						t(5).dfl();
						t(5).s("p.").e("i").da("class", "fas fa-search ").df().dgl("i");
						t(4).s("} p.").gl("button");
		
						l();
						t(3).s("} p.").gl("div");
						t(3).s("{ p.").e("div").da("class", "w3-cell-row ").dfl();
						t(4).s("{ p.").e("div").da("class", "w3-cell w3-left-align w3-cell-top ").dfl();
						t(5).s("{ p.").e("ul").da("class", "w3-ul w3-hoverable ").s(".a(\"id\", \"", str_suggere(langueNom), "List", classeNomSimple, "\", id)").dfl();
						t(5).s("} p.").gl("ul");
						t(4).s("} p.").gl("div");
						t(3).s("} p.").gl("div");
	
						// voir tous //
						t(3).s("{ p.").e("div").da("class", "").dfl();
						t(4).s("{ p.").e("a").da("href", classePageUriMethode).da("class", "").dfl();
						if(contexteIconeGroupe != null && contexteIconeNom != null)
							t(5).s("p.").e("i").da("class", "fa", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, " ").df().dgl("i");
						t(5).s("p.").sxqscl(str_voir(langueNom), " ", contexteTousNom);
						t(4).s("} p.").gl("a");
						t(3).s("} p.").gl("div");
	
						tl(2, "} catch(Exception e) {");
						tl(3, "ExceptionUtils.rethrow(e);");
						tl(2, "}");
						tl(1, "}");
	
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
						auteurPageJs.tl(3, "var $box = $('<div>').attr('class', 'w3-display-topright w3-quarter box-' + id + ' ').attr('id', 'box-' + id).attr('data-numPATCH', numPATCH);");
						auteurPageJs.tl(3, "var $margin = $('<div>').attr('class', 'w3-margin ').attr('id', 'margin-' + id);");
						auteurPageJs.tl(3, "var $card = $('<div>').attr('class', 'w3-card w3-white ').attr('id', 'card-' + id);");
						auteurPageJs.tl(3, "var $header = $('<div>').attr('class', 'w3-container fa-", contexteCouleur, " ').attr('id', 'header-' + id);");
						auteurPageJs.tl(3, "var $i = $('<i>').attr('class', 'fa", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, " w3-margin-right ').attr('id', 'icon-' + id);");
						auteurPageJs.tl(3, "var $headerSpan = $('<span>').attr('class', '').text('", str_modifier(langueNom), " ", contexteNomAdjectifPluriel, "');");
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
						auteurPageJs.tl(5, "$('.box-' + id).remove();");
						auteurPageJs.tl(5, "$('.top-box').append($box);");
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
	
					l();
					if(classeMethodeVars.contains("htmlBody" + str_Court(langueNom))) {
						tl(1, "@Override public void htmlBodyCourt", classeGenPageNomSimple, "() {");
						l();
						tl(2, StringUtils.uncapitalize(classeNomSimple), ".htmlBody" + str_Court(langueNom) + "();");
						tl(1, "}");
					}
		
					tl(0, "}");
		
					wTh.flushClose();
	
					auteurPageGenClasse.flushClose();
					System.out.println(str_Ecrire(langueNom) + ": " + classePageCheminGen); 
					if(auteurPageClasse != null) {
						auteurPageClasse.flushClose();
						System.out.println(str_Ecrire(langueNom) + ": " + classePageChemin); 
					}
					auteurPageCss.flushClose();
					System.out.println(str_Ecrire(langueNom) + ": " + classePageCheminCss); 
					auteurPageJs.flushClose();
					System.out.println(str_Ecrire(langueNom) + ": " + classePageCheminJs); 
	
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
						regarderClasse.classeCheminAbsolu = classePageCheminGen;
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
		
		//		auteurGenPageClasse.close();
				}
			}
		}
	}
}
