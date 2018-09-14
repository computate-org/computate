package org.computate.frFR.java;     

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 * nomCanonique.enUS: org.computate.enUS.java.WriteClass
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 * val.VAL_entiteCommentaireLigne1Part1.enUS:The "
 * val.VAL_entiteCommentaireLigne1Part1.frFR:Le champ « 
 * gen: true
 */  
public class EcrireClasse extends IndexerClasse {

	/**
	 * var.enUS: writeClass
	 * param1.var.enUS: classAbsolutePath
	 * param2.var.enUS: languageName
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
	protected void ecrireClasse(String classeCheminAbsolu, String langueNom) throws Exception { 
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);

		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		ecrireClasse(classeCheminAbsolu, langueNom, reponseRecherche);
	}  

	/**
	 * var.enUS: writeComment
	 * param2.var.enUS: comment
	 * param3.var.enUS: tabs
	 * r: commentaire
	 * r.enUS: comment
	 * r: tabulations
	 * r.enUS: tabs
	 * 
	 */
	public void ecrireCommentaire(String commentaire, Integer tabulations) {
		String tabulationsStr = StringUtils.repeat("\t", tabulations);
		if(StringUtils.isNotEmpty(commentaire)) {
			String[] parts = StringUtils.split(commentaire, "\n");
			for(int j = 0; j < parts.length; j++) { 
				String ligne = parts[j];
				if(j == 0)
					l(tabulationsStr, "/**\t", ligne);
				else
					l(tabulationsStr, " *\t", ligne);
			}
			l(tabulationsStr, " */");  
		} 
	}

	/**
	 * var.enUS: writeClass
	 * param1.var.enUS: classAbsolutePath
	 * param2.var.enUS: languageName
	 * param3.var.enUS: searchResponse
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
	 * r.enUS: classTypeParameterNames
	 * r: classeParametreTypeNom
	 * r.enUS: classTypeParameterName
	 * r: methodeParametreTypeNoms
	 * r.enUS: methodTypeParameterNames
	 * r: methodeParametreTypeNom
	 * r.enUS: methodTypeParameterName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeSuperParametreTypeNoms
	 * r.enUS: classSuperTypeParameterNames
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
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: classeSuperParametreTypeNom
	 * r.enUS: classSuperTypeParameterName
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
	 * r: methodeParamArgsVariableListe
	 * r.enUS: methodParamVariableArgsList
	 * r: methodeParamArgsVariable
	 * r.enUS: methodParamVariableArgs
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
	 * r: Ecrire:
	 * r.enUS: Write:
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */   
	protected void ecrireClasse(String classeCheminAbsolu, String langueNom, QueryResponse reponseRecherche) throws Exception { 
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
			List<String> classeParametreTypeNoms = null;  
			List<String> classeSuperParametreTypeNoms = null;  
			Boolean classeEtendGen = null;
	
			for(int i = 0; i < listeRecherche.size(); i++) { 
				SolrDocument doc = listeRecherche.get(i); 
				Integer partNumero = (Integer)doc.get("partNumero_stored_int");
				if(partNumero == null)
					partNumero = 2;
				if(partNumero.equals(1)) {
					classeCheminRepertoire = (String)doc.get("classeCheminRepertoire_" + langueNom + "_stored_string");
					classeChemin = (String)doc.get("classeChemin_" + langueNom + "_stored_string"); 
					classeRepertoire = new File(classeCheminRepertoire);
					classeRepertoire.mkdirs();
					classeFichier = new File(classeChemin);
					o = new PrintWriter(classeFichier);
					classeNomSimple = (String)doc.get("classeNomSimple_" + langueNom + "_stored_string");
					classeNomCanoniqueSuper = (String)doc.get("classeNomCanoniqueSuper_" + langueNom + "_stored_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + langueNom + "_stored_string");
					classeNomSimpleSuperGenerique = (String)doc.get("classeNomSimpleSuperGenerique_" + langueNom + "_stored_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + langueNom + "_stored_string");
					classeCommentaire = (String)doc.get("classeCommentaire_" + langueNom + "_stored_string");
					classeImportations = (List<String>)doc.get("classeImportations_" + langueNom + "_stored_strings");
					classeParametreTypeNoms = (List<String>)doc.get("classeParametreTypeNoms_stored_strings");
					classeSuperParametreTypeNoms = (List<String>)doc.get("classeSuperParametreTypeNoms_stored_strings");
					classeEtendGen = (Boolean)doc.get("classeEtendGen_stored_boolean");
		
					l("package ", classeNomEnsemble, ";"); 
					l();
					if(classeImportations != null && classeImportations.size() > 0) { 
						for(String classeImportation : classeImportations) {
							l("import ", classeImportation, ";");
						} 
						l();  
					}
					ecrireCommentaire(classeCommentaire, 0); 
					s("public class ", classeNomSimple);

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
						else if(classeSuperParametreTypeNoms != null && classeSuperParametreTypeNoms.size() > 0) {
							s("<");
							for(int j = 0; j < classeSuperParametreTypeNoms.size(); j++) {
								String classeSuperParametreTypeNom = classeSuperParametreTypeNoms.get(j);
								if(i > 0)
									s(", ");
								s(classeSuperParametreTypeNom);
							}
							s(">");
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
						String champCommentaire = (String)doc.get("champCommentaire_" + langueNom + "_stored_string");
						String champVar = (String)doc.get("champVar_" + langueNom + "_stored_string");
						String champNomSimpleComplet = (String)doc.get("champNomSimpleComplet_" + langueNom + "_stored_string");
						String champCodeSource = (String)doc.get("champCodeSource_" + langueNom + "_stored_string");

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
	
					if(BooleanUtils.isTrue(partEstMethode)) {
						String methodeVar = (String)doc.get("methodeVar_" + langueNom + "_stored_string");
						String methodeCodeSource = (String)doc.get("methodeCodeSource_" + langueNom + "_stored_string");
						String methodeCommentaire = (String)doc.get("methodeCommentaire_" + langueNom + "_stored_string");
						List<String> methodeExceptionNomSimpleCompletListe = (List<String>)doc.get("methodeExceptionNomSimpleComplet_stored_strings");
						List<String> methodeParametreTypeNoms = (List<String>)doc.get("methodeParametreTypeNoms_stored_strings");
						List<String> methodeAnnotationsNomSimpleCompletListe = (List<String>)doc.get("methodeAnnotationsNomSimpleComplet_" + langueNom + "_stored_strings");
						List<String> methodeAnnotationsBlocCodeListe = (List<String>)doc.get("methodeAnnotationsBlocCode_" + langueNom + "_stored_strings");

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


						if(methodeParametreTypeNoms != null && methodeParametreTypeNoms.size() > 0) {
							s("<");
							for(int j = 0; j < methodeParametreTypeNoms.size(); j++) {
								String methodeParametreTypeNom = methodeParametreTypeNoms.get(j);
								if(j > 0)
									s(", ");
								s(methodeParametreTypeNom);
							}
							s("> ");
						}

						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstVide_stored_boolean")))
							s("void ");
						else
							s((String)doc.get("methodeRetourNomSimpleComplet_" + langueNom + "_stored_string"));
						s(" ");
						s(methodeVar);
						s("(");
						List<String> methodeParamNomSimpleCompletListe = (List<String>)doc.get("methodeParamNomSimpleComplet_" + langueNom + "_stored_strings"); 
						List<String> methodeParamVarListe = (List<String>)doc.get("methodeParamVar_" + langueNom + "_stored_strings");
						List<Boolean> methodeParamArgsVariableListe = (List<Boolean>)doc.get("methodeParamArgsVariable_stored_booleans");
						if(methodeParamNomSimpleCompletListe != null && methodeParamVarListe != null && methodeParamNomSimpleCompletListe.size() == methodeParamVarListe.size()) {
							for(int j = 0; j < methodeParamVarListe.size(); j++) {
								String methodeParamNomSimpleComplet = methodeParamNomSimpleCompletListe.get(j);
								String methodeParamVar = methodeParamVarListe.get(j);
								Boolean methodeParamArgsVariable = methodeParamArgsVariableListe.get(j);
								if(j > 0)
									s(", ");
								s(methodeParamNomSimpleComplet);

								if(methodeParamArgsVariable)
									s("...");
								else
									s(" ");

								s(methodeParamVar);
							}
						}    
						s(")");
						if(methodeExceptionNomSimpleCompletListe != null && methodeExceptionNomSimpleCompletListe.size() > 0) {
							s(" throws ");
							for(int j = 0; j < methodeExceptionNomSimpleCompletListe.size(); j++) {
								String methodeExceptionNomSimpleComplet = methodeExceptionNomSimpleCompletListe.get(j);
								if(j > 0)
									s(", ");
								s(methodeExceptionNomSimpleComplet);
							}
						}
						s(" {");
						s(methodeCodeSource);
						l("}");
					} 
					else if(BooleanUtils.isTrue(partEstEntite)) {
						
					}
				}
			}
			l("}"); 
			if(listeRecherche.size() > 0 && !StringUtils.equals(classeCheminAbsolu, classeChemin)) {
				System.out.println("Ecrire: " + classeChemin); 
				o.flush();
				o.close();
			}
		}
		else {
			System.err.println("No file was found in the search engine. ");
		}
	}  

	PrintWriter o;
	public EcrireClasse o(PrintWriter o) {
		this.o = o;
		return this;
	}

	String langueNom;
	public EcrireClasse langueNom(String langueNom) {
		this.langueNom = langueNom;
		return this;
	}

	public void s(Object...objets) {
		for(Object objet : objets)
			if(objet != null)
				o.append(objet.toString());
	}

	public void t(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			o.append("\t");
		for(Object objet : objets)
			if(objet != null)
				o.append(objet.toString());
	}

	public void l(Object...objets) {
		for(Object objet : objets)
			if(objet != null)
				o.append(objet.toString());
		o.append("\n");
	}

	public void tl(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			o.append("\t");
		for(Object objet : objets)
			if(objet != null)
				o.append(objet.toString());
		o.append("\n");
	}
}
