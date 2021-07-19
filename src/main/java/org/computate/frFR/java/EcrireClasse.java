package org.computate.frFR.java;     

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 * NomCanonique.enUS: org.computate.enUS.java.WriteClass
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 * Val.VAL_entiteCommentaireLigne1Part1.enUS:The "
 * Val.VAL_entiteCommentaireLigne1Part1.frFR:Le champ « 
 */  
public class EcrireClasse extends IndexerClasse { 

	protected ToutEcrivain auteurClasse;

	/**
	 * Var.enUS: writeClass
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
	 * r: ecrireClasse
	 * r.enUS: writeClass
	 */   
	public void ecrireClasse(String classeCheminAbsolu, String langueNom) throws Exception { 
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);

		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		ecrireClasse(classeCheminAbsolu, langueNom, reponseRecherche);
	}  

	/**
	 * Var.enUS: writeComment
	 * Param1.var.enUS: comment
	 * Param2.var.enUS: tabs
	 * r: commentaire
	 * r.enUS: comment
	 * r: tabulations
	 * r.enUS: tabs
	 * r: ecrireCommentairePart
	 * r.enUS: writeCommentPart
	 */
	public void ecrireCommentaire(String commentaire, Integer tabulations) {
		String tabulationsStr = StringUtils.repeat("\t", tabulations);
		if(StringUtils.isNotEmpty(commentaire)) {
			String[] parts = StringUtils.split(commentaire, "\n");
			for(int j = 0; j < parts.length; j++) { 
				String ligne = parts[j];
				if(j == 0) {
					l(tabulationsStr, "/**");
					break;
				}
			}
			ecrireCommentairePart(commentaire, tabulations);
			l(tabulationsStr, " **/");  
		} 
	}

	/**
	 * Var.enUS: writeCommentPart
	 * Param1.var.enUS: comment
	 * Param2.var.enUS: tabs
	 * r: commentaire
	 * r.enUS: comment
	 * r: tabulations
	 * r.enUS: tabs
	 * 
	 */
	public void ecrireCommentairePart(String commentaire, Integer tabulations) {
		String tabulationsStr = StringUtils.repeat("\t", tabulations);
		if(StringUtils.isNotEmpty(commentaire)) {
			String[] parts = StringUtils.split(commentaire, "\n");
			for(int j = 0; j < parts.length; j++) { 
				String ligne = parts[j];
				l(tabulationsStr, " * ", ligne);
			}
		} 
	}

	/**
	 * Var.enUS: writeClass
	 * Param1.var.enUS: classAbsolutePath
	 * Param2.var.enUS: languageName
	 * Param3.var.enUS: searchResponse
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
	 * r: classeParamsTypeNom
	 * r.enUS: classTypeParameterNames
	 * r: classeParamTypeNom
	 * r.enUS: classTypeParameterName
	 * r: methodeParamsTypeNom
	 * r.enUS: methodTypeParameterNames
	 * r: methodeParamTypeNom
	 * r.enUS: methodTypeParameterName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeSuperParamsTypeNom
	 * r.enUS: classSuperTypeParameterNames
	 * r: classeParamTypesNom
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
	 * r.enUS: classGenDirPathLanguage
	 * r: classeCheminRepertoireGen
	 * r.enUS: classGenDirPath
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
	 * r: classeEstAbstrait
	 * r.enUS: classIsAbstract
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: classeSuperParamTypeNom
	 * r.enUS: classSuperTypeParameterName
	 * r: classeImplementsNomSimpleCompletListe
	 * r.enUS: classImplementsSimpleNameCompleteList
	 * r: classeImplementsNomSimpleComplet
	 * r.enUS: classImplementsSimpleNameComplete
	 * r: partEstConstructeur
	 * r.enUS: partIsConstructor
	 * r: partEstChamp
	 * r.enUS: partIsField
	 * r: champEstPublic
	 * r.enUS: fieldIsPublic
	 * r: champEstProtege
	 * r.enUS: fieldIsProtected
	 * r: champEstPrive
	 * r.enUS: fieldIsPrivate
	 * r: champEstStatique
	 * r.enUS: fieldIsStatic
	 * r: champEstFinale
	 * r.enUS: fieldIsFinal
	 * r: champEstAbstrait
	 * r.enUS: fieldIsAbstract
	 * r: champEstNatif
	 * r.enUS: fieldIsNative
	 * r: champEstTest
	 * r.enUS: fieldIsTest
	 * r: champEstSubstitue
	 * r.enUS: fieldIsOverride
	 * r: champAnnotationLangue
	 * r.enUS: fieldAnnotationLanguage
	 * r: champClassePartsLangue
	 * r.enUS: fieldClassPartsLanguage
	 * r: champClasseParts
	 * r.enUS: fieldClassParts
	 * r: champVarLangue
	 * r.enUS: fieldVarLanguage
	 * r: champVar
	 * r.enUS: fieldVar
	 * r: champNomCanoniqueComplet
	 * r.enUS: fieldCanonicalNameComplete
	 * r: champNomSimpleComplet
	 * r.enUS: fieldSimpleNameComplete
	 * r: champCommentaire
	 * r.enUS: fieldComment
	 * r: champCle
	 * r.enUS: fieldKey
	 * r: champCodeSource
	 * r.enUS: fieldSourceCode
	 * r: methodeVarLangue
	 * r.enUS: methodVarLanguage
	 * r: methodeVar
	 * r.enUS: methodVar
	 * r: methodeDoc
	 * r.enUS: methodDoc
	 * r: methodeQdox
	 * r.enUS: methodQdox
	 * r: methodeCommentaire
	 * r.enUS: methodComment
	 * r: methodeCle
	 * r.enUS: methodKey
	 * r: methodeCodeSourceLangue
	 * r.enUS: methodSourceCodeLanguage
	 * r: methodeCodeSource
	 * r.enUS: methodSourceCode
	 * r: methodeEstPublic
	 * r.enUS: methodIsPublic
	 * r: methodeEstProtege
	 * r.enUS: methodIsProtected
	 * r: methodeEstPrive
	 * r.enUS: methodIsPrivate
	 * r: methodeEstStatique
	 * r.enUS: methodIsStatic
	 * r: methodeEstFinale
	 * r.enUS: methodIsFinal
	 * r: methodeEstAbstrait
	 * r.enUS: methodIsAbstract
	 * r: methodeEstNatif
	 * r.enUS: methodIsNative
	 * r: methodeParamsNomSimpleComplet
	 * r.enUS: methodParamsSimpleNameComplete
	 * r: methodeParamArgsVariables
	 * r.enUS: methodParamVariableArgs
	 * r: methodeParamsVar
	 * r.enUS: methodParamsVar
	 * r: methodeExceptionsNomSimpleComplet
	 * r.enUS: methodExceptionsSimpleNameComplete
	 * r: methodeAnnotationsNomSimpleCompletListe
	 * r.enUS: methodAnnotationsSimpleNameCompleteList
	 * r: methodeAnnotationsNomSimpleComplet
	 * r.enUS: methodAnnotationsSimpleNameComplete
	 * r: methodeAnnotationNomSimpleComplet
	 * r.enUS: methodAnnotationSimpleNameComplete
	 * r: methodeAnnotationsBlocCodeListe
	 * r.enUS: methodAnnotationsCodeBlockList
	 * r: methodeAnnotationsBlocCode
	 * r.enUS: methodAnnotationsCodeBlock
	 * r: methodeAnnotationBlocCode
	 * r.enUS: methodAnnotationCodeBlock
	 * r: methodeAnnotations
	 * r.enUS: methodAnnotations
	 * r: methodeAnnotationLangue
	 * r.enUS: methodAnnotationLanguage
	 * r: methodeParamsQdox
	 * r.enUS: methodParamsQdox
	 * r: methodeExceptionsQdox
	 * r.enUS: methodExceptionsQdox
	 * r: methodeParamNum
	 * r.enUS: methodParamNum
	 * r: methodeParamQdox
	 * r.enUS: methodParamQdox
	 * r: methodeParamVarLangue
	 * r.enUS: methodParamVarLanguage
	 * r: methodeParamVarLangue
	 * r.enUS: methodParamVar
	 * r: methodeParamClassePartsLangue
	 * r.enUS: methodParamClassPartsLanguage
	 * r: methodeParamNomSimpleCompletListe
	 * r.enUS: methodParamSimpleNameCompleteList
	 * r: methodeParamNomSimpleComplet
	 * r.enUS: methodParamSimpleNameComplete
	 * r: methodeParamVar
	 * r.enUS: methodeParamVar
	 * r: methodeParamsArgsVariablesListe
	 * r.enUS: methodParamVariableArgsList
	 * r: methodeParamsArgsVariables
	 * r.enUS: methodParamsVariableArgs
	 * r: methodeAnnotationBlocCode
	 * r.enUS: methodAnnotationCodeBlock
	 * r: methodeExceptionQdox
	 * r.enUS: methodExceptionQdox
	 * r: methodeExceptionNomSimpleCompletListe
	 * r.enUS: methodExceptionSimpleNameCompleteList
	 * r: methodeExceptionNomSimpleComplet
	 * r.enUS: methodExceptionSimpleNameComplete
	 * r: methodeAnnotation
	 * r.enUS: methodAnnotation
	 * r: partEstMethode
	 * r.enUS: partIsMethod
	 * r: methodeRetourNomSimpleComplet
	 * r.enUS: methodReturnSimpleNameComplete
	 * r: methodeRetourClassePartsLangue
	 * r.enUS: methodReturnClassPartsLanguage
	 * r: methodeRetourClasseParts
	 * r.enUS: methodReturnClassParts
	 * r: methodeEstVide
	 * r.enUS: methodIsVoid
	 * r: methodeNomSimpleRetourComplet
	 * r.enUS: methodReturnSimpleNameComplete
	 * r: methodeEstTest
	 * r.enUS: methodIsTest
	 * r: methodeEstSubstitue
	 * r.enUS: methodIsOverride
	 * r: methodeParamVarListe
	 * r.enUS: methodParamVarList
	 * r: methodeParamVar
	 * r.enUS: methodParamVar
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: entiteCommentaire
	 * r.enUS entityComment
	 * r: entiteBlocCode
	 * r.enUS: entityCodeBlock
	 * r: entiteCodeSource
	 * r.enUS: entitySourceCode
	 * r: entiteCouverture
	 * r.enUS: entityWrap
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: entiteExceptionsNomSimpleComplet
	 * r.enUS: entityExceptionsSimpleNameComplete
	 * r: entiteParamsTypeNom
	 * r.enUS: entityParamsTypeName
	 * r: entiteAnnotationsNomSimpleCompletListe
	 * r.enUS: entityAnnotationsSimpleNameCompleteList
	 * r: entiteAnnotationsBlocCodeListe
	 * r.enUS: entityAnnotationsCodeBlockList
	 * r: entiteExceptionNomSimpleComplet
	 * r.enUS: entityExceptionSimpleNameComplete
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: Couverture
	 * r.enUS: Wrap
	 * r: Ecrire:
	 * r.enUS: Write:
	 * r: Traduire
	 * r.enUS: Translate
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */   
	public void ecrireClasse(String classeCheminAbsolu, String classeLangueNom, QueryResponse reponseRecherche) throws Exception { 
		SolrDocumentList listeRecherche = reponseRecherche.getResults(); 

		if(listeRecherche.size() > 0) {
			String classeCheminRepertoire = null;
			String classeChemin = null; 
			File classeRepertoire = null;
			File classeFichier = null;
			
			String classeNomSimple = null;
			String classeNomSimpleSuper = null;    
			String classeNomSimpleSuperGenerique = null;    
			String classeNomCanoniqueSuper = null;    
			String classeNomEnsemble = null;
			String classeCommentaire = null;      
			List<String> classeImportations = null;  
			List<String> classeParamsTypeNom = null;  
			List<String> classeSuperParamsTypeNom = null;  
			List<String> classeImplementsNomSimpleCompletListe = null;  
	
			for(int i = 0; i < listeRecherche.size(); i++) { 
				SolrDocument doc = listeRecherche.get(i); 
				Integer partNumero = (Integer)doc.get("partNumero_stored_int");
				if(partNumero == null)
					partNumero = 2;
				if(partNumero.equals(1)) {
					classeCheminRepertoire = (String)doc.get("classeCheminRepertoire_" + classeLangueNom + "_stored_string");
					classeChemin = (String)doc.get("classeChemin_" + classeLangueNom + "_stored_string"); 
					classeCheminAbsolu = (String)doc.get("classeCheminAbsolu_stored_string"); 
					if(StringUtils.equals(classeChemin, classeCheminAbsolu))
						break;
					classeRepertoire = new File(classeCheminRepertoire);
					classeRepertoire.mkdirs();
					classeFichier = new File(classeChemin);
					classeNomSimple = (String)doc.get("classeNomSimple_" + classeLangueNom + "_stored_string");
					classeNomCanoniqueSuper = (String)doc.get("classeNomCanoniqueSuper_" + classeLangueNom + "_stored_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + classeLangueNom + "_stored_string");
					classeNomSimpleSuperGenerique = (String)doc.get("classeNomSimpleSuperGenerique_" + classeLangueNom + "_stored_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + classeLangueNom + "_stored_string");
					classeCommentaire = (String)doc.get("classeCommentaire_" + classeLangueNom + "_stored_string");
					classeImportations = (List<String>)doc.get("classeImportations_" + classeLangueNom + "_stored_strings");
					classeParamsTypeNom = (List<String>)doc.get("classeParamsTypeNom_stored_strings");
					classeSuperParamsTypeNom = (List<String>)doc.get("classeSuperParamsTypeNom_stored_strings");
					classeImplementsNomSimpleCompletListe = (List<String>)doc.get("classeImplementsNomSimpleComplet_" + classeLangueNom + "_stored_strings");
					Boolean classeEtendGen = (Boolean)doc.get("classeEtendGen_stored_boolean");
					Boolean classeModele = (Boolean)doc.get("classeModele_stored_boolean");
					Boolean classeTraduire = (Boolean)doc.get("classeTraduire_stored_boolean");
					Boolean classeInitLoin = (Boolean)doc.get("classeInitLoin_stored_boolean");
					Boolean classeApi = (Boolean)doc.get("classeApi_stored_boolean");
					Boolean classePage = (Boolean)doc.get("classePage_stored_boolean");
					Boolean classePageSimple = (Boolean)doc.get("classePageSimple_stored_boolean");
					Boolean classeSauvegarde = (Boolean)doc.get("classeSauvegarde_stored_boolean");
					Boolean classePublicLire = Optional.ofNullable((Boolean)doc.get("classePublicLire_stored_boolean")).orElse(false);
					Boolean classeRoleSession = (Boolean)doc.get("classeRoleSession_stored_boolean");
					Boolean classeRoleUtilisateur = (Boolean)doc.get("classeRoleUtilisateur_stored_boolean");
					String contexteCouleur = (String)doc.get("contexteCouleur_stored_string");
					String contexteIconeGroupe = (String)doc.get("contexteIconeGroupe_stored_string");
					String contexteIconeNom = (String)doc.get("contexteIconeNom_stored_string");

					auteurClasse = ToutEcrivain.create(classeFichier);
					o = auteurClasse;
		
					l("package ", classeNomEnsemble, ";"); 
					l();
					if(classeImportations != null && classeImportations.size() > 0) { 
						for(String classeImportation : classeImportations) {
							l("import ", classeImportation, ";");
						} 
						l();  
					}

					StringBuilder c = new StringBuilder();
					if(!classeTraduire)
						c.append(str_Traduire(classeLangueNom)).append(": ").append(classeTraduire).append("\n");
					if(!classeInitLoin)
						c.append(str_InitLoin(classeLangueNom)).append(": ").append(classeInitLoin).append("\n");
					if(classeModele)
						c.append(str_Modele(classeLangueNom)).append(": ").append(classeModele).append("\n");
					if(classeApi)
						c.append(str_Api(classeLangueNom)).append(": ").append(classeApi).append("\n");
					if(classePage)
						c.append(str_Page(classeLangueNom)).append(": ").append(classePage).append("\n");
					if(classePageSimple)
						c.append(str_PageSimple(classeLangueNom)).append(": ").append(classePageSimple).append("\n");
					if(classeSauvegarde)
						c.append(str_Sauvegarde(classeLangueNom)).append(": ").append(classeSauvegarde).append("\n");
					if(classePublicLire)
						c.append(str_PublicLire(classeLangueNom)).append(": ").append(classePublicLire).append("\n");
					if(classeRoleSession)
						c.append(str_RoleSession(classeLangueNom)).append(": ").append(classeRoleSession).append("\n");
					if(classeRoleUtilisateur)
						c.append(str_RoleUtilisateur(classeLangueNom)).append(": ").append(classeRoleUtilisateur).append("\n");
					if(contexteCouleur != null)
						c.append(str_Couleur(classeLangueNom)).append(": ").append(contexteCouleur).append("\n");
					if(contexteIconeGroupe != null)
						c.append(str_IconeGroupe(classeLangueNom)).append(": ").append(contexteIconeGroupe).append("\n");
					if(contexteIconeNom != null)
						c.append(str_IconeNom(classeLangueNom)).append(": ").append(contexteIconeNom).append("\n");
					if(StringUtils.isNotBlank(classeCommentaire))
						c.append("\n").append(classeCommentaire);

					for(String langueNom : toutesLangues) {
						String classeApiUri = (String)doc.get("classeApiUri_" + langueNom + "_stored_string");
						String classeApiTag = (String)doc.get("classeApiTag_" + langueNom + "_stored_string");
						String contexteUnNom = (String)doc.get("contexteUnNom_" + langueNom + "_stored_string");
						List<String> classeRoles = (List<String>)doc.get("classeRoles_stored_strings");
						List<String> classeRolesLangue = (List<String>)doc.get("classeRolesLangue_stored_strings");

						c.append("\n");
						if(classeRoles != null) {
							for(Integer j=0; j < classeRoles.size(); j++) {
								String classeRole = classeRoles.get(j);
								String classeRoleLangue = classeRolesLangue.get(j);
								if(classeRoleLangue.equals(langueNom))
									c.append(str_Role(classeLangueNom)).append(".").append(langueNom).append(": ").append(classeRole).append("\n");
							}
						}
						if(StringUtils.isNotBlank(classeApiUri))
							c.append(str_ApiUri(classeLangueNom)).append(".").append(langueNom).append(": ").append(classeApiUri).append("\n");
						if(StringUtils.isNotBlank(classeApiTag))
							c.append(str_ApiTag(classeLangueNom)).append(".").append(langueNom).append(": ").append(classeApiTag).append("\n");
						if(StringUtils.isNotBlank(contexteUnNom))
							c.append(str_UnNom(classeLangueNom)).append(".").append(langueNom).append(": ").append(contexteUnNom).append("\n");
						if(!langueNom.equals(classeLangueNom)) {
							String classeNomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
							c.append(str_NomCanonique(classeLangueNom)).append(": ").append(classeNomCanonique).append("\n");
						}
					}

					ecrireCommentaire(c.toString(), 0); 
					s("public ");
					if(BooleanUtils.isTrue((Boolean)doc.get("classeEstAbstrait_stored_boolean")))
						s("abstract ");
					s("class ", classeNomSimple);

					if(classeParamsTypeNom != null && classeParamsTypeNom.size() > 0) {
						s("<");
						for(int j = 0; j < classeParamsTypeNom.size(); j++) {
							String classeParamTypeNom = classeParamsTypeNom.get(j);
							if(j > 0)
								s(", ");
							s(classeParamTypeNom);
						}
						s(">");
					}

					if(!"java.lang.Object".equals(classeNomCanoniqueSuper)) {
						s(" extends ");
						if(classeEtendGen) {
							s(classeNomSimple, "Gen");
						} 
						else {
							s(classeNomSimpleSuper);
						}

						if(StringUtils.isNotEmpty(classeNomSimpleSuperGenerique)) {
							s("<", classeNomSimpleSuperGenerique, ">");
						}
						else if(classeSuperParamsTypeNom != null && classeSuperParamsTypeNom.size() > 0) {
							s("<");
							for(int j = 0; j < classeSuperParamsTypeNom.size(); j++) {
								String classeSuperParamTypeNom = classeSuperParamsTypeNom.get(j);
								if(i > 0)
									s(", ");
								s(classeSuperParamTypeNom);
							}
							s(">");
						}
					}
					if(classeImplementsNomSimpleCompletListe != null && classeImplementsNomSimpleCompletListe.size() > 0) {
						s(" implements");
						for(Integer j = 0; j < classeImplementsNomSimpleCompletListe.size(); j++) {
							String classeImplementsNomSimpleComplet = classeImplementsNomSimpleCompletListe.get(i);

							if(j > 0)
								s(",");
							s(" ", classeImplementsNomSimpleComplet);
						}
					}
					l(" {");
				} 
				else {     
					Boolean partEstChamp = (Boolean)doc.get("partEstChamp_stored_boolean");
					Boolean partEstMethode = (Boolean)doc.get("partEstMethode_stored_boolean");
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stored_boolean");
	
					if(BooleanUtils.isTrue(partEstChamp)) {
						String champCommentaire = (String)doc.get("champCommentaire_" + classeLangueNom + "_stored_string");
						String champVar = (String)doc.get("champVar_" + classeLangueNom + "_stored_string");
						String champNomSimpleComplet = (String)doc.get("champNomSimpleComplet_" + classeLangueNom + "_stored_string");
						String champCodeSource = (String)doc.get("champCodeSource_" + classeLangueNom + "_stored_string");
						Boolean champTraduire = (Boolean)doc.get("champTraduire_stored_boolean");

						if(champTraduire) {
							l(); 
							ecrireCommentaire(champCommentaire, 1);
							s("\t");
							if(BooleanUtils.isTrue((Boolean)doc.get("champEstPublic_stored_boolean")))
								s("public ");
							if(BooleanUtils.isTrue((Boolean)doc.get("champEstProtege_stored_boolean")))
								s("protected ");
							if(BooleanUtils.isTrue((Boolean)doc.get("champEstPrive_stored_boolean")))
								s("private ");
							if(BooleanUtils.isTrue((Boolean)doc.get("champEstStatique_stored_boolean")))
								s("static ");
							if(BooleanUtils.isTrue((Boolean)doc.get("champEstFinale_stored_boolean")))
								s("final ");
							if(BooleanUtils.isTrue((Boolean)doc.get("champEstAbstrait_stored_boolean")))
								s("abstract ");
							if(BooleanUtils.isTrue((Boolean)doc.get("champEstNatif_stored_boolean")))
								s("native ");
							
							s(champNomSimpleComplet, " ", champVar);
							if(StringUtils.isNotEmpty(champCodeSource))
								s(" = ", champCodeSource);
							l(";");
						}
					}     
					else if(BooleanUtils.isTrue(partEstConstructeur)) {
						String constructeurCodeSource = (String)doc.get("constructeurCodeSource_" + classeLangueNom + "_stored_string");
						String constructeurCommentaire = (String)doc.get("constructeurCommentaire_" + classeLangueNom + "_stored_string");
						List<String> constructeurExceptionsNomSimpleComplet = (List<String>)doc.get("constructeurExceptionsNomSimpleComplet_stored_strings");
						List<String> constructeurAnnotationsNomSimpleCompletListe = (List<String>)doc.get("constructeurAnnotationsNomSimpleComplet_" + classeLangueNom + "_stored_strings");
						List<String> constructeurAnnotationsBlocCodeListe = (List<String>)doc.get("constructeurAnnotationsBlocCode_" + classeLangueNom + "_stored_strings");

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

						s(classeNomSimple);
						s("(");
						List<String> constructeurParamsNomSimpleComplet = (List<String>)doc.get("constructeurParamsNomSimpleComplet_" + classeLangueNom + "_stored_strings"); 
						List<String> constructeurParamsVar = (List<String>)doc.get("constructeurParamsVar_" + classeLangueNom + "_stored_strings");
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
					else if(BooleanUtils.isTrue(partEstMethode)) {
						String methodeVar = (String)doc.get("methodeVar_" + classeLangueNom + "_stored_string");
						String methodeCodeSource = (String)doc.get("methodeCodeSource_" + classeLangueNom + "_stored_string");
						String methodeCommentaire = (String)doc.get("methodeCommentaire_" + classeLangueNom + "_stored_string");
						List<String> methodeExceptionsNomSimpleComplet = (List<String>)doc.get("methodeExceptionsNomSimpleComplet_stored_strings");
						List<String> methodeParamsTypeNom = (List<String>)doc.get("methodeParamsTypeNom_stored_strings");
						List<String> methodeAnnotationsNomSimpleCompletListe = (List<String>)doc.get("methodeAnnotationsNomSimpleComplet_" + classeLangueNom + "_stored_strings");
						List<String> methodeAnnotationsBlocCodeListe = (List<String>)doc.get("methodeAnnotationsBlocCode_" + classeLangueNom + "_stored_strings");

						l(""); 
						ecrireCommentaire(methodeCommentaire, 1);
						if(methodeAnnotationsNomSimpleCompletListe != null && methodeAnnotationsBlocCodeListe != null) {
							for(int j = 0; j < methodeAnnotationsNomSimpleCompletListe.size(); j++) {
								String methodeAnnotationNomSimpleComplet = methodeAnnotationsNomSimpleCompletListe.get(j);
								String methodeAnnotationBlocCode = methodeAnnotationsBlocCodeListe.get(j);
								l("\t@", methodeAnnotationNomSimpleComplet, methodeAnnotationBlocCode, "");
							}
						}
						s("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPublic_stored_boolean")))
							s("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstProtege_stored_boolean")))
							s("protected ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPrive_stored_boolean")))
							s("private ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstStatique_stored_boolean")))
							s("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstFinale_stored_boolean")))
							s("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstAbstrait_stored_boolean")))
							s("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstNatif_stored_boolean")))
							s("native ");


						if(methodeParamsTypeNom != null && methodeParamsTypeNom.size() > 0) {
							s("<");
							for(int j = 0; j < methodeParamsTypeNom.size(); j++) {
								String methodeParamTypeNom = methodeParamsTypeNom.get(j);
								if(j > 0)
									s(", ");
								s(methodeParamTypeNom);
							}
							s("> ");
						}

						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstVide_stored_boolean")))
							s("void ");
						else
							s((String)doc.get("methodeRetourNomSimpleComplet_" + classeLangueNom + "_stored_string"));
						s(" ");
						s(methodeVar);
						s("(");
						List<String> methodeParamsNomSimpleComplet = (List<String>)doc.get("methodeParamsNomSimpleComplet_" + classeLangueNom + "_stored_strings"); 
						List<String> methodeParamsVar = (List<String>)doc.get("methodeParamsVar_" + classeLangueNom + "_stored_strings");
						List<Boolean> methodeParamsArgsVariables = (List<Boolean>)doc.get("methodeParamsArgsVariables_stored_booleans");
						if(methodeParamsNomSimpleComplet != null && methodeParamsVar != null && methodeParamsNomSimpleComplet.size() == methodeParamsVar.size()) {
							for(int j = 0; j < methodeParamsVar.size(); j++) {
								String methodeParamNomSimpleComplet = methodeParamsNomSimpleComplet.get(j);
								String methodeParamVar = methodeParamsVar.get(j);
								Boolean methodeParamArgsVariables = methodeParamsArgsVariables.get(j);
								if(j > 0)
									s(", ");
								s(methodeParamNomSimpleComplet);

								if(methodeParamArgsVariables)
									s("...");
								else
									s(" ");

								s(methodeParamVar);
							}
						}    
						s(")");
						if(methodeExceptionsNomSimpleComplet != null && methodeExceptionsNomSimpleComplet.size() > 0) {
							s(" throws ");
							for(int j = 0; j < methodeExceptionsNomSimpleComplet.size(); j++) {
								String methodeExceptionNomSimpleComplet = methodeExceptionsNomSimpleComplet.get(j);
								if(j > 0)
									s(", ");
								s(methodeExceptionNomSimpleComplet);
							}
						}
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstAbstrait_stored_boolean"))) {
							s(";");
						}
						else {
							s(" {");
							s(methodeCodeSource);
							l("}");
						}
					} 
					else if(BooleanUtils.isTrue(partEstEntite)) {
						String entiteVar = (String)doc.get("entiteVar_" + classeLangueNom + "_stored_string");
						String entiteVarParam = (String)doc.get("entiteVarParam_" + classeLangueNom + "_stored_string");
						String entiteCodeSource = (String)doc.get("entiteCodeSource_" + classeLangueNom + "_stored_string");
						String entiteCommentaire = (String)doc.get("entiteCommentaire_" + classeLangueNom + "_stored_string");
						String entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + classeLangueNom + "_stored_string");
						Boolean entiteCouverture = (Boolean)doc.get("entiteCouverture_stored_boolean");
						List<String> entiteExceptionsNomSimpleComplet = (List<String>)doc.get("entiteExceptionsNomSimpleComplet_stored_strings");
						List<String> entiteParamsTypeNom = (List<String>)doc.get("entiteParamsTypeNom_stored_strings");
						List<String> entiteAnnotationsNomSimpleCompletListe = (List<String>)doc.get("entiteAnnotationsNomSimpleComplet_" + classeLangueNom + "_stored_strings");
						List<String> entiteAnnotationsBlocCodeListe = (List<String>)doc.get("entiteAnnotationsBlocCode_" + classeLangueNom + "_stored_strings");

						l(""); 
						ecrireCommentaire(entiteCommentaire, 1);
						if(entiteAnnotationsNomSimpleCompletListe != null && entiteAnnotationsBlocCodeListe != null) {
							for(int j = 0; j < entiteAnnotationsNomSimpleCompletListe.size(); j++) {
								String entiteAnnotationNomSimpleComplet = entiteAnnotationsNomSimpleCompletListe.get(j);
								String entiteAnnotationBlocCode = entiteAnnotationsBlocCodeListe.get(j);
								l("\t@", entiteAnnotationNomSimpleComplet, entiteAnnotationBlocCode, "");
							}
						}
						s("\t");
						s("protected void _");
						s(entiteVar);

						if(BooleanUtils.isTrue(entiteCouverture))
							s("(", classePartsCouverture.nomSimple(classeLangueNom), "<", entiteNomSimpleComplet, "> ", entiteVarParam);
						else
							s("(", entiteNomSimpleComplet, " ", entiteVarParam);
						s(")");
						if(entiteExceptionsNomSimpleComplet != null && entiteExceptionsNomSimpleComplet.size() > 0) {
							s(" throws ");
							for(int j = 0; j < entiteExceptionsNomSimpleComplet.size(); j++) {
								String entiteExceptionNomSimpleComplet = entiteExceptionsNomSimpleComplet.get(j);
								if(j > 0)
									s(", ");
								s(entiteExceptionNomSimpleComplet);
							}
						}
						s(" {");
						s(entiteCodeSource);
						l("}");
					}
				}
			}
			if(o != null) {
				if(listeRecherche.size() > 0 && !StringUtils.equals(classeCheminAbsolu, classeChemin)) {
					l("}"); 

					System.out.println("Ecrire: " + classeChemin); 
					o.flushClose();
				}
			}
		}
		else {
			System.err.println("No file was found in the search engine. ");
		}
	}  

	ToutEcrivain o;
	public EcrireClasse o(ToutEcrivain o) {
		this.o = o;
		return this;
	}

	/**
	 * Var.enUS: languageName
	 */
	String langueNom;
	/**
	 * Var.enUS: languageName
	 * Param1.var.enUS: languageName
	 * r: langueNom
	 * r.enUS: languageName
	 */
	public EcrireClasse langueNom(String langueNom) {
		this.langueNom = langueNom;
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objets
	 * r.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse s(Object...objets) {
		for(Object objet : objets)
			if(objet != null)
				o.s(objet.toString());
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsxq(Object...objets) {
		s(".sx(", q(objets), ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsq(Object...objets) {
		s(".s(", q(objets), ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsx(Object...objets) {
		s(".sx(", objets, ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse ds(Object...objets) {
		s(".s(", objets, ")");
		return this;
	}



	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsxqscl(Object...objets) {
		l(".sx(", q(objets), ");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsqscl(Object...objets) {
		l(".s(", q(objets), ");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsxscl(Object...objets) {
		l(".sx(", objets, ");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsscl(Object...objets) {
		l(".s(", objets, ");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsxql(Object...objets) {
		l(".sx(", q(objets), ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsql(Object...objets) {
		l(".s(", q(objets), ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsxl(Object...objets) {
		l(".sx(", objets, ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse sxqscl(Object...objets) {
		l("sx(", q(objets), ");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse sqscl(Object...objets) {
		l("s(", q(objets), ");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse sxscl(Object...objets) {
		s("sx(");
		s(objets);
		l(");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse sscl(Object...objets) {
		s("s(");
		s(objets);
		l(");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse sxql(Object...objets) {
		l("sx(", q(objets), ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse sql(Object...objets) {
		l("s(", q(objets), ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse sxl(Object...objets) {
		s("sx(");
		s(objets);
		l(");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dsl(Object...objets) {
		l(".s(", objets, ")");
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objets
	 * r.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse t(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			o.s("\t");
		for(Object objet : objets)
			if(objet != null)
				o.s(objet.toString());
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objets
	 * r.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse l(Object...objets) {
		for(Object objet : objets)
			if(objet != null)
				o.s(objet.toString());
		o.s("\n");
		return this;
	}

	/**
	 * Param1.var.enUS: numberTabs
	 * Param2.var.enUS: objects
	 * r: nombreTabulations
	 * r.enUS: numberTabs
	 * r: objets
	 * r.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse tl(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			o.s("\t");
		for(Object objet : objets)
			if(objet != null)
				o.s(objet.toString());
		o.s("\n");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public String q(Object...objets) {
		StringBuilder o = new StringBuilder();
		o.append("\"");
		for(Object objet : objets)
			if(objet != null)
				o.append(StringUtils.replace(StringUtils.replace(objet.toString(), "\\", "\\\\"), "\"", "\\\""));
		o.append("\"");
		return o.toString();
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse be(Object...objets) {
		s("{ e(", q(objets), ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse e(Object...objets) {
		s("e(", q(objets), ")");
		return this;
	}

	/**
	 * Param2.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse da(String var, Object...objets) {
		s(".a(", q(var), ", ", q(objets), ")");
		return this;
	}

	/**
	 * Param2.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse a(String var, Object...objets) {
		s("a(", q(var), ", ", q(objets), ")");
		return this;
	}

	/**
	 * Param2.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dal(String var, Object...objets) {
		l(".a(", q(var), ", ", q(objets), ")");
		return this;
	}

	/**
	 * Param2.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse al(String var, Object...objets) {
		l("a(", q(var), ", ", q(objets), ")");
		return this;
	}

	/**
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse df() {
		s(".f()");
		return this;
	}

	/**
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dfl() {
		l(".f();");
		return this;
	}

	/**
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dfg() {
		s(".fg()");
		return this;
	}

	/**
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dfgl() {
		l(".fg();");
		return this;
	}

	/**
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse fgl() {
		l("fg();");
		return this;
	}

	/**
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dfgbl() {
		l(".fg(); }");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dg(Object...objets) {
		s(".g(", q(objets), ")");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse gl(Object...objets) {
		l("g(", q(objets), ");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse bgl(Object...objets) {
		l("} g(", q(objets), ");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dgl(Object...objets) {
		l(".g(", q(objets), ");");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse dgbl(Object...objets) {
		l(".g(", q(objets), "); }");
		return this;
	}

	/**
	 * Param1.var.enUS: objects
	 * r: objet
	 * r.enUS: object
	 */
	public EcrireClasse gbl(Object...objets) {
		l("g(", q(objets), "); }");
		return this;
	}
}
