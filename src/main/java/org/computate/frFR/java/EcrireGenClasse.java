package org.computate.frFR.java;      

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 * nomCanonique.enUS: org.computate.enUS.java.WriteGenClass
 * gen: true
 * 
 * val.VAL_entityCommentLine1Part1.enUS:The "
 * val.VAL_entiteCommentaireLigne1Part1.frFR:L'entité « 
 * val.VAL_entityCommentLine1Part2.enUS:" entity
 * val.VAL_entiteCommentaireLigne1Part2.frFR: »
 * val.VAL_entityWrapLine1Part1.enUS: is set to null before it is initialized. 
 * val.VAL_entiteCouvertureLigne1Part1.frFR: est défini comme null avant d'être initialisé. 
 * val.VAL_entityWrapLine2Part1.enUS: is for wrapping a value to be assigned to this field during initialization. 
 * val.VAL_entiteCouvertureLigne2Part1.frFR: est pour envelopper une valeur à assigner à ce champ lors de l'initialisation. 
 * val.VAL_entityConstructedLine1Part1.enUS:It is constructed before it is initialized with the default constructor 
 * val.VAL_entiteConstruitLigne1Part1.frFR:Il est construit avant d'être initialisé avec le constructeur par défaut 
 * val.VAL_entityConstructedLine1Part2.enUS:(). 
 * val.VAL_entiteConstruitLigne1Part2.frFR:(). 
 * val.VAL_entityConstructedLine2Part1.enUS: is the field already constructed. 
 * val.VAL_entiteConstruitLigne2Part1.frFR: est le champ déjà construit. 
 * val.VAL_entityThrowsLine2Part1.enUS: so that any exception during initialization is handled by the servlet. 
 * val.VAL_entiteThrowsLigne2Part1.frFR: afin que toute exception lors de l'initialisation est gérée par le servlet. 
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */  
public class EcrireGenClasse extends EcrireGenClasseGen<EcrireClasse> {

	/**
	 * var.enUS: writeGenClass
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
	 * r: ecrireClasseGen
	 * r.enUS: writeGenClass
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */    
	protected void ecrireClasseGen(String classeCheminAbsolu, String langueNom) throws Exception { 

		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addFilterQuery("classeEtendGen_indexed_boolean:true");
		rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);

		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		ecrireClasseGen(reponseRecherche, langueNom);
	}

	/** 
	 * var.enUS: writeGenClass
	 * param1.var.enUS: searchResponse
	 * param2.var.enUS: languageName
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
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: Ecrire:
	 * r.enUS: Write:
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */ 
	protected void ecrireClasseGen(QueryResponse reponseRecherche, String langueNom) throws Exception { 
		SolrDocumentList listeRecherche = reponseRecherche.getResults();

		if(listeRecherche.size() > 0 && (langueIndexe || !StringUtils.equals(langueNom, this.langueNom))) {    
			String classeCheminRepertoireGen = null;
			String classeCheminGen = null; 
			File classeRepertoireGen = null;
			File classeFichierGen = null;
			StringBuilder s = new StringBuilder();
			
			String classeNomSimpleGen = null;
			String classeNomSimpleSuper = null;    
			String classeNomSimpleSuperGenerique = null;    
			String classeNomEnsemble = null;      
			String classeNomSimple = null;
			String classeNomCanoniqueSuper = null;    
			String classeCommentaire = null;      
			List<String> classeImportations = null;  
			List<String> classeParametreTypeNoms = null;  
			List<String> classeSuperParametreTypeNoms = null;  
			Boolean classeEtendGen = null;
			Boolean classeBaseEtendGen = null;
			Boolean classeContientRequeteSite = null;

			StringWriter wInitialiserLoin = null;
			PrintWriter codeInitialiserLoin = null;
			PrintWriter oAvant = null;
	
			for(int i = 0; i < listeRecherche.size(); i++) {
				SolrDocument doc = listeRecherche.get(i); 
				Integer partNumero = (Integer)doc.get("partNumero_stored_int");
				if(partNumero.equals(1)) {
					classeCheminRepertoireGen = (String)doc.get("classeCheminRepertoireGen_" + langueNom + "_stored_string");
					classeCheminGen = (String)doc.get("classeCheminGen_" + langueNom + "_stored_string"); 
					classeRepertoireGen = new File(classeCheminRepertoireGen);
					classeRepertoireGen.mkdirs();
					classeFichierGen = new File(classeCheminGen);
					o = new PrintWriter(classeFichierGen);
					classeNomSimple = (String)doc.get("classeNomSimple_" + langueNom + "_stored_string");
					classeNomSimpleGen = (String)doc.get("classeNomSimpleGen_" + langueNom + "_stored_string");
					classeNomCanoniqueSuper = (String)doc.get("classeNomCanoniqueSuper_" + langueNom + "_stored_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + langueNom + "_stored_string");
					classeNomCanoniqueSuper = (String)doc.get("classeNomCanoniqueSuper_" + langueNom + "_stored_string");
					classeNomSimpleSuperGenerique = (String)doc.get("classeNomSimpleSuperGenerique_" + langueNom + "_stored_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + langueNom + "_stored_string");
					classeCommentaire = (String)doc.get("classeCommentaire_" + langueNom + "_stored_string");
					classeImportations = (List<String>)doc.get("classeImportations_" + langueNom + "_stored_strings");
					classeParametreTypeNoms = (List<String>)doc.get("classeParametreTypeNoms_stored_strings");
					classeSuperParametreTypeNoms = (List<String>)doc.get("classeSuperParametreTypeNoms_stored_strings");
					classeEtendGen = (Boolean)doc.get("classeEtendGen_stored_boolean");
					classeBaseEtendGen = (Boolean)doc.get("classeBaseEtendGen_stored_boolean");
					classeContientRequeteSite = (Boolean)doc.get("classeContientRequeteSite_stored_boolean");

					oAvant = o;
					wInitialiserLoin = new StringWriter();
					codeInitialiserLoin = new PrintWriter(wInitialiserLoin);
					o = codeInitialiserLoin;
					l(); 
					tl(1, "protected boolean dejaInitialise", classeNomSimple, " = false;");
					if(BooleanUtils.isTrue(classeContientRequeteSite)) {
						l();
						tl(1, "public void initLoin", classeNomSimple, "(RequeteSite requeteSite) throws Exception {");
//						if(contientRequeteSite && !StringUtils.equals(classeNomSimple, "RequeteSite"))
							tl(2, "((", classeNomSimple, ")this).requeteSite(requeteSite);");
						tl(2, "requeteSite(requeteSite);");
						tl(2, "initLoin", classeNomSimple, "();");
						tl(1, "}");
					}
					l();
					tl(1, "public void initLoin", classeNomSimple, "() throws Exception {");
					tl(2, "if(!dejaInitialise", classeNomSimple, ") {");
					if(BooleanUtils.isTrue(classeBaseEtendGen)) 
						tl(3, "super.initLoin", classeNomSimpleSuperGenerique, "(requeteSite);");
					o = oAvant;
		
					l("package ", classeNomEnsemble, ";");
					l();
					if(classeImportations.size() > 0) { 
						for(String classeImportation : classeImportations) {
							l("import ", classeImportation, ";");
						} 
						l();  
					}
					ecrireCommentaire(classeCommentaire, 0); 
					s("public abstract class ", classeNomSimpleGen);
					if(classeParametreTypeNoms != null && classeParametreTypeNoms.size() > 0) {
						s("<");
						for(int j = 0; j < classeParametreTypeNoms.size(); j++) {
							String classeParametreTypeNom = classeParametreTypeNoms.get(j);
							if(i > 0)
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
//									s(", ");
//								s(classeSuperParametreTypeNom);
//							}
////							s(">");
//						}	
					}
					s(" {\n");
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
				else {
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stored_boolean");
	
					if(BooleanUtils.isTrue(partEstEntite)) {
						String entiteVar = (String)doc.get("entiteVar_" + langueNom + "_stored_string");
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

						List<String> entiteMethodesAvantVisibilite = (List<String>)doc.get("entiteMethodesAvantVisibilite_stored_strings");
						List<String> entiteMethodesAvantVar = (List<String>)doc.get("entiteMethodesAvantVar_stored_strings");
						List<String> entiteMethodesAvantParamVar = (List<String>)doc.get("entiteMethodesAvantParamVar_stored_strings");
						List<String> entiteMethodesAvantParamNomSimple = (List<String>)doc.get("entiteMethodesAvantParamNomSimple_stored_strings");
						List<Boolean> entiteMethodesAvantNomParam = (List<Boolean>)doc.get("entiteMethodesAvantNomParam_stored_booleans");

						List<String> entiteMethodesApresVisibilite = (List<String>)doc.get("entiteMethodesApresVisibilite_stored_strings");
						List<String> entiteMethodesApresVar = (List<String>)doc.get("entiteMethodesApresVar_stored_strings");
						List<String> entiteMethodesApresParamVar = (List<String>)doc.get("entiteMethodesApresParamVar_stored_strings");
						List<String> entiteMethodesApresParamNomSimple = (List<String>)doc.get("entiteMethodesApresParamNomSimple_stored_strings");
						List<Boolean> entiteMethodesApresNomParam = (List<Boolean>)doc.get("entiteMethodesApresNomParam_stored_booleans");

						l();
						String ligneCommentaire = "\t///" + String.join("", Collections.nCopies(entiteVar.length(), "/")) + "///";
						l(ligneCommentaire);
						tl(1, "// ", entiteVar, " //");
						l(ligneCommentaire);
						l();
						t(1, "/**");
						t(1);
							s(VAL_entiteCommentaireLigne1Part1, entiteVar, VAL_entiteCommentaireLigne1Part2);
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
							tl(1, " *\t", VAL_entiteCouvertureLigne1Part1);
						}
						else {
							tl(1, " *\t", VAL_entiteConstruitLigne1Part1, entiteNomSimpleComplet, VAL_entiteConstruitLigne1Part2);
						}
						tl(1, " */");

						t(1, "public ", entiteNomSimpleComplet, " ", entiteVar);
						if(!entiteCouverture)
							s(" = new ", entiteNomSimpleComplet, "()");
						l(";");

						t(1, "public Couverture<", entiteNomSimpleComplet, "> ", entiteVar, "Couverture");
						l(" = new Couverture<", entiteNomSimpleComplet, ">().p(this).c(", entiteNomSimple, ".class).var(\"", entiteVar, "\").o(", entiteVar, ");");
			
						// Methode underscore //
						l();
						t(1, "/**");
						t(1);
							s(VAL_entiteCommentaireLigne1Part1, entiteVar, VAL_entiteCommentaireLigne1Part2);
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
							tl(1, " *\t", VAL_entiteCouvertureLigne1Part1);
							tl(1, " *\t@param ", entiteVarParam, VAL_entiteCouvertureLigne2Part1);
						}
						else {
							tl(1, " *\t", VAL_entiteConstruitLigne1Part1, entiteNomSimpleComplet, VAL_entiteConstruitLigne1Part2);
							tl(1, " *\t@param ", entiteVar, VAL_entiteConstruitLigne2Part1);
						}
						tl(1, " *\t@throws java.lang.Exception", VAL_entiteThrowsLigne2Part1);
						tl(1, " */");
						t(1, "protected abstract void");
						s(" _", entiteVar);
						s("(");
						if(entiteCouverture) {
							s("Couverture<", entiteNomSimpleComplet, "> ", entiteVarParam);
						}
						else {
							s(entiteNomSimpleComplet, " ", entiteVarParam);
						}
						l(") throws Exception;");

						l();
						tl(1, "public ", classeNomSimple, " ", entiteVar, "(", entiteNomSimpleComplet, " ", entiteVarParam, ") throws Exception {");
						tl(2, "set", entiteVarCapitalise, "(", entiteVarParam, ");");
						tl(2, "return (", classeNomSimple, ")this;");
						tl(1, "}");

						l();
						tl(1, "public void set", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVarParam, ") throws Exception {");
						tl(2, "this.", entiteVar, " = ", entiteVarParam, ";");
						tl(1, "}");

						l();
						tl(1, "public ", entiteNomSimpleComplet, " ", entiteVar, "() throws Exception {");
						tl(2, "return get", entiteVarCapitalise, "();");
						tl(1, "}");

						l();
						tl(1, "public ", entiteNomSimpleComplet, " get", entiteVarCapitalise, "() throws Exception {");
						tl(2, "return ", entiteVar, ";");
						tl(1, "}");
		
						// Setter List //
						if(StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName()) && StringUtils.equals(entiteNomCanoniqueGenerique, Long.class.getCanonicalName())) {
							tl(1, "public ", classeNomSimple, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o)) {");
							tl(3, "Long l = Long.parseLong(o);");
							tl(3, entiteVar, "Ajouter(l);");
							tl(2, "}");
							tl(2, "return (", classeNomSimple, ")this;");
							tl(1, "}");
						}
		
						// Setter Boolean //
						if(StringUtils.equals(entiteNomCanonique, Boolean.class.getCanonicalName())) {
							tl(1, "public ", classeNomSimple, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))");
							tl(3, "this.", entiteVar, " = Boolean.parseBoolean(o);");
							tl(2, "return (", classeNomSimple, ")this;");
							tl(1, "}");
						}
		
						// Setter Integer //
						if(StringUtils.equals(entiteNomCanonique, Integer.class.getCanonicalName())) {
							tl(1, "public ", classeNomSimple, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o))");
							tl(3, "this.", entiteVar, " = Integer.parseInt(o);");
							tl(2, "return (", classeNomSimple, ")this;");
							tl(1, "}");
						}
		
						// Setter Double //
						if(StringUtils.equals(entiteNomCanonique, Double.class.getCanonicalName())) {
							tl(1, "public ", classeNomSimple, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o))");
							tl(3, "this.", entiteVar, " = Double.parseDouble(o);");
							tl(2, "return (", classeNomSimple, ")this;");
							tl(1, "}");
						}
		
						// Setter Long //
						if(StringUtils.equals(entiteNomCanonique, Long.class.getCanonicalName())) {
							tl(1, "public ", classeNomSimple, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o))");
							tl(3, "this.", entiteVar, " = Long.parseLong(o);");
							tl(2, "return (", classeNomSimple, ")this;");
							tl(1, "}");
						}
		
						// Setter LocalDateTime //
						if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
							tl(1, "public ", classeNomSimple, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "this.", entiteVar, " = java.time.LocalDateTime.parse(o, java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
							tl(2, "return (", classeNomSimple, ")this;");
							tl(1, "}");
							tl(1, "public ", classeNomSimple, " ", entiteVar, "(java.util.Date o) throws Exception {");
							tl(2, "this.", entiteVar, " = java.time.LocalDateTime.ofInstant(o.toInstant(), java.time.ZoneId.systemDefault());");
							tl(2, "return (", classeNomSimple, ")this;");
							tl(1, "}");
						}
		
						// Ajouter //
						if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
							tl(1, "public ", classeNomSimple, " ", entiteVar, "Ajouter(", entiteNomSimpleCompletGenerique, "...objets) throws Exception {");
							tl(2, "for(", entiteNomSimpleCompletGenerique, " o : objets) {");
							tl(3, "", entiteVar, "Ajouter(o);");
							tl(2, "}");
							tl(2, "return (", classeNomSimple, ")this;");
							tl(1, "}");
							tl(1, "public ", classeNomSimple, " ", entiteVar, "Ajouter(", entiteNomSimpleCompletGenerique, " o) throws Exception {");
							tl(2, "if(o != null && !", entiteVar, ".contains(o))");
							tl(3, "this.", entiteVar, ".add(o);");
							tl(2, "return (", classeNomSimple, ")this;");
							tl(1, "}");
						}
				
						// Initialise //
						if(entiteInitialise) {
			
							if(entiteMethodesAvantVar != null) {
								for(int j = 0; j < entiteMethodesAvantVar.size(); j++) {
									String entiteMethodeAvantVisibilite = entiteMethodesAvantVisibilite.get(j);
									String entiteMethodeAvantVar = entiteMethodesAvantVar.get(j);
									String entiteMethodeAvantParamVar = entiteMethodesAvantParamVar.get(j);
									String entiteMethodeAvantParamNomSimple = entiteMethodesAvantParamNomSimple.get(j);
									Boolean entiteMethodeAvantNomParam = entiteMethodesAvantNomParam.get(j);
	
									t(1, entiteMethodeAvantVisibilite, " abstract void ", entiteMethodeAvantVar, "Avant(", entiteMethodeAvantParamNomSimple, " ", entiteMethodeAvantParamVar);
									if(entiteMethodeAvantNomParam)
										s(", String entiteVar");
									l(") throws Exception;");
								}
							}
					
							// Initialiser //
							tl(1, "protected void ", entiteVar, "Init() throws Exception {");
			
							if(!entiteCouverture && entiteMethodesAvantVar != null) {
								for(int j = 0; j < entiteMethodesAvantVar.size(); j++) {
									String entiteMethodeAvantVar = entiteMethodesAvantVar.get(j);
									Boolean entiteMethodeAvantNomParam = entiteMethodesAvantNomParam.get(j);
	
									t(2, entiteMethodeAvantVar, "(", entiteVar);
									if(entiteMethodeAvantNomParam)
										s(", \"", entiteVar, "\"");
									l(");");
								}
							}
			
							tl(2, "if(!", entiteVar, "Couverture.dejaInitialise) {");
							if(entiteCouverture) {
								tl(3, "_", entiteVar, "(", entiteVar, "Couverture);");
								tl(3, "if(", entiteVar, " == null)");
								tl(4, entiteVar, "(", entiteVar, "Couverture.o);");
							}
							else {
								tl(3, "_", entiteVar, "(", entiteVar, ");");
							}
							tl(2, "}");
			
			
							if(entiteCouverture && entiteMethodesAvantVar != null) {
								for(int j = 0; j < entiteMethodesAvantVar.size(); j++) {
									String entiteMethodeAvantVar = entiteMethodesAvantVar.get(j);
									Boolean entiteMethodeAvantNomParam = entiteMethodesAvantNomParam.get(j);
	
									t(2, entiteMethodeAvantVar, "(", entiteVar);
									if(entiteMethodeAvantNomParam)
										s(", \"", entiteVar, "\"");
									l(");");
								}
							}
			
							// initLoin
			
		//						if(initLoin && nomCanonique.enUS().startsWith(classe.nomEnsembleDomaine.enUS())) {
							if(entiteInitLoin) {
								if(entiteCouverture) {
									tl(2, "if(", entiteVar, " != null)");
									tl(3, entiteVar, ".initLoinPourClasse(requeteSite);");
								}
								else {
									tl(2, entiteVar, ".initLoinPourClasse(requeteSite);");
								}
							}
			
							if(entiteMethodesApresVar != null) {
								for(int j = 0; j < entiteMethodesApresVar.size(); j++) {
									String entiteMethodeApresVar = entiteMethodesApresVar.get(j);
									Boolean entiteMethodeApresNomParam = entiteMethodesApresNomParam.get(j);
	
									t(2, entiteMethodeApresVar, "(", entiteVar);
									if(entiteMethodeApresNomParam)
										s(", \"", entiteVar, "\"");
									l(");");
								}
							}
			
							tl(2, entiteVar, "Couverture.dejaInitialise(true);");
							tl(1, "}");
			
							if(entiteMethodesApresVar != null) {
								for(int j = 0; j < entiteMethodesApresVar.size(); j++) {
									String entiteMethodeApresVisibilite = entiteMethodesApresVisibilite.get(j);
									String entiteMethodeApresVar = entiteMethodesApresVar.get(j);
									String entiteMethodeApresParamVar = entiteMethodesApresParamVar.get(j);
									String entiteMethodeApresParamNomSimple = entiteMethodesApresParamNomSimple.get(j);
									Boolean entiteMethodeApresNomParam = entiteMethodesApresNomParam.get(j);
	
									t(1, entiteMethodeApresVisibilite, " abstract void ", entiteMethodeApresVar, "Apres(", entiteMethodeApresParamNomSimple, " ", entiteMethodeApresParamVar);
									if(entiteMethodeApresNomParam)
										s(", String entiteVar");
									l(") throws Exception;");
								}
							}
						}

						oAvant = o;
						o = codeInitialiserLoin;
							if(entiteInitialise) {
								tl(3, entiteVar, "Init();");
							}
						o = oAvant;
					}
				}
			}
			if(o != null) {
				if(listeRecherche.size() > 0 && !StringUtils.equals(classeCheminAbsolu, classeCheminGen)) {

					oAvant = o;
					o = codeInitialiserLoin;
					tl(3, "dejaInitialise", classeNomSimple, " = true;");
					tl(2, "}");
					tl(1, "}");
					if(classeContientRequeteSite) {
						l();
						tl(1, "public void initLoinPourClasse(RequeteSite requeteSite) throws Exception {");
						tl(2, "initLoin", classeNomSimple, "(requeteSite);");
						tl(1, "}");  
					}
					o = oAvant; 

					codeInitialiserLoin.flush();
					codeInitialiserLoin.flush();
					s(wInitialiserLoin.toString());

					l("}"); 

					System.out.println("Ecrire: " + classeCheminGen); 
					o.flush();
					o.close();
				}
			}
		} 
	}  
// 
//	/**	Récupérer les enregistrements de la classe à partir du moteur de recherche, 
//	 *	traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
//	 */
//	protected void  ecrireClasseGen(QueryResponse reponseRecherche, String langueNom) throws Exception { 
//		SolrDocumentList listeRecherche = reponseRecherche.getResults();
//
//		if(listeRecherche.size() > 0 && (langueIndexe || !StringUtils.equals(langueNom, this.langueNom))) {    
//			String classeCheminRepertoireGen = null;
//			String classeCheminGen = null; 
//			File classeRepertoire = null;
//			File classeFichier = null;
//			StringBuilder s = new StringBuilder();
//			
//			String classeNomSimpleGen = null;
//			String classeNomSimpleSuper = null;    
//			String classeNomEnsemble = null;      
//	
//			for(int i = 0; i < listeRecherche.size(); i++) {
//				SolrDocument doc = listeRecherche.get(i); 
//				Integer partNumero = (Integer)doc.get("partNumero_stored_int");
//				if(partNumero.equals(1)) {
//					classeCheminRepertoireGen = (String)doc.get("classeCheminRepertoireGen_" + langueNom + "_stored_string");
//					classeCheminGen = (String)doc.get("classeCheminGen_" + langueNom + "_stored_string"); 
//					classeRepertoire = new File(classeCheminRepertoireGen);
//					classeRepertoire.mkdirs();
//					classeFichier = new File(classeCheminGen);
//					classeNomSimpleGen = (String)doc.get("classeNomSimpleGen_" + langueNom + "_stored_string");
//					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + langueNom + "_stored_string");
//					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + langueNom + "_stored_string");
//		
//					s("package ", classeNomEnsemble, ";\n\n");
//					s("public class ", classeNomSimpleGen, " extends ", classeNomSimpleSuper);
//					s(" {\n");
//					s("\n"); 
//				} 
//				else {
//					Boolean partEstChamp = (Boolean)doc.get("partEstChamp_stored_boolean");
//					Boolean partEstMethode = (Boolean)doc.get("partEstMethode_stored_boolean");
//					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
//					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stored_boolean");
//					String champVar = (String)doc.get("champVar_" + langueNom + "_stored_string");
//	
//					if(BooleanUtils.isTrue(partEstChamp)) {
//						s("\t");
//						if(BooleanUtils.isTrue((Boolean)doc.get("champEstPublic_stored_boolean")))
//							s("public ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("champEstProtege_stored_boolean")))
//							s("protege ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("champEstPrive_stored_boolean")))
//							s("prive ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("champEstStatique_stored_boolean")))
//							s("static ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("champEstFinale_stored_boolean")))
//							s("final ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("champEstAbstrait_stored_boolean")))
//							s("abstract ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("champEstNatif_stored_boolean")))
//							s("native ");
//						s(";\n");
//					}     
//	
//					if(BooleanUtils.isTrue(partEstMethode)) {
//						String methodeVar = (String)doc.get("methodeVar_" + langueNom + "_stored_string");
//						String methodeCodeSource = (String)doc.get("methodeCodeSource_" + langueNom + "_stored_string");
//						s("\t");
//						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPublic_stored_boolean")))
//							s("public ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstProtege_stored_boolean")))
//							s("protected ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPrive_stored_boolean")))
//							s("private ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstStatique_stored_boolean")))
//							s("static ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstFinale_stored_boolean")))
//							s("final ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstAbstrait_stored_boolean")))
//							s("abstract ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstNatif_stored_boolean")))
//							s("native ");
//						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstVide_stored_boolean")))
//							s("void ");
//						else
//							s((String)doc.get("methodeNomSimpleComplet_stored_string"), " ");
//						s(methodeVar);
//						s("(");
//						s(")");
//						s(" {");
//						s(methodeCodeSource);
//						s("}\n\n");
//					} 
//				}
//			}
//			s("}\n");
//			FileUtils.write(classeFichier, s, Charset.forName("UTF-8"));  
//		} 
//	}
//
//	public void ecrireChampJava(SolrDocument doc) throws Exception {
//		boolean exact = BooleanUtils.isTrue((Boolean)doc.get("entiteExact_stored_boolean"));
//		boolean cleUnique = BooleanUtils.isTrue((Boolean)doc.get("entiteCleUnique_stored_boolean"));
//		boolean indexe = BooleanUtils.isTrue((Boolean)doc.get("entiteIndexe_stored_boolean"));
//		boolean stocke = BooleanUtils.isTrue((Boolean)doc.get("entiteStocke_stored_boolean"));
//		boolean crypte = BooleanUtils.isTrue((Boolean)doc.get("entiteCrypte_stored_boolean"));
//		boolean suggere = BooleanUtils.isTrue((Boolean)doc.get("entiteSuggere_stored_boolean"));
//		boolean sauvegarde = BooleanUtils.isTrue((Boolean)doc.get("entiteSauvegarde_stored_boolean"));
//		boolean texte = BooleanUtils.isTrue((Boolean)doc.get("entiteTexte_stored_boolean"));
//		boolean incremente = BooleanUtils.isTrue((Boolean)doc.get("entiteNomAffichage_stored_boolean"));
//		boolean nomAffichage = BooleanUtils.isTrue((Boolean)doc.get("entiteNomAffichage_stored_boolean"));
//		boolean ignorer = BooleanUtils.isTrue((Boolean)doc.get("entiteIgnorer_stored_boolean"));
//		boolean declarer = BooleanUtils.isTrue((Boolean)doc.get("entiteDeclarer_stored_boolean"));
//		boolean rechercher = BooleanUtils.isTrue((Boolean)doc.get("entiteRechercher_stored_boolean"));
//		boolean attribuer = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuer_stored_boolean"));
//		boolean ajouter = BooleanUtils.isTrue((Boolean)doc.get("entiteAjouter_stored_boolean"));
//		boolean supprimer = BooleanUtils.isTrue((Boolean)doc.get("entiteSupprimer_stored_boolean"));
//		boolean modifier = BooleanUtils.isTrue((Boolean)doc.get("entiteModifier_stored_boolean"));
//		boolean recharger = BooleanUtils.isTrue((Boolean)doc.get("entiteRecharger_stored_boolean"));
//		boolean multiligne = BooleanUtils.isTrue((Boolean)doc.get("entiteMultiligne_stored_boolean"));
//		boolean cles = BooleanUtils.isTrue((Boolean)doc.get("entiteCles_stored_boolean"));
//
//		String var;
//		String nomCanoniqueContexteEnfant;
//		String nomCanonique;
//		String nomCanoniqueGenerique;
//		String nomCompletGenerique;
//		String nomSimpleGenerique;
//		String nomCanoniqueComplet;
//
//		if(!ignorer) {
//			if(attribuer || ajouter || supprimer || recharger || cles || rechercher) {
//				o.tabLigne(0);
//				o.tabLigne(1, "protected void _", var, "Contexte(", nomCanoniqueContexteEnfant, " o) throws Exception {}");
//			}
//			o.tabLigne(0);
//			o.tabLigne(1, "protected void _", var, "Champ(ChampJava o) throws Exception {");
//			o.tabLigne(2, "o.var.enUS(\"", var.enUS(), "\");");
//			o.tabLigne(2, "o.var.frFR(\"", var.frFR(), "\");");
//	
//			if(nomCanonique.commencePar("org.computate")) {
//				o.tabLigne(2, "o.nomCanonique.enUS(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanonique.enUS(), "org.computate"), "\");");
//				o.tabLigne(2, "o.nomCanonique.frFR(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanonique.frFR(), "org.computate"), "\");");
//			}
//			else if(nomCanonique.pasVide()) {
//				o.tabLigne(2, "o.nomCanonique.tout(\"", nomCanonique, "\");");
//			}
//	
//			if(nomCanoniqueGenerique.pasVide()) {
//				if(nomCanoniqueGenerique.commencePar("org.computate")) {
//					o.tabLigne(2, "o.nomCanoniqueGenerique.enUS(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanoniqueGenerique.enUS(), "org.computate"), "\");");
//					o.tabLigne(2, "o.nomCanoniqueGenerique.frFR(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanoniqueGenerique.frFR(), "org.computate"), "\");");
//				}
//				else if(nomCanoniqueGenerique.pasVide()) {
//					o.tabLigne(2, "o.nomCanoniqueGenerique.enUS(\"", nomCanoniqueGenerique.enUS(), "\");");
//					o.tabLigne(2, "o.nomCanoniqueGenerique.frFR(\"", nomCanoniqueGenerique.frFR(), "\");");
//				}
//			}
//	
//			if(nomCompletGenerique.pasVide()) {
//				if(nomCompletGenerique.commencePar("org.computate")) {
//					o.tabLigne(2, "o.nomCompletGenerique.enUS(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCompletGenerique.enUS(), "org.computate"), "\");");
//					o.tabLigne(2, "o.nomCompletGenerique.frFR(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCompletGenerique.frFR(), "org.computate"), "\");");
//				}
//				else if(nomCompletGenerique.pasVide()) {
//					o.tabLigne(2, "o.nomCompletGenerique.enUS(\"", nomCompletGenerique.enUS(), "\");");
//					o.tabLigne(2, "o.nomCompletGenerique.frFR(\"", nomCompletGenerique.frFR(), "\");");
//				}
//			}
//	
//			if(nomSimpleGenerique.pasVide()) {
//				if(nomSimpleGenerique.commencePar("org.computate")) {
//					o.tabLigne(2, "o.nomSimpleGenerique.enUS(\"", nomSimpleGenerique.enUS(), "\");");
//					o.tabLigne(2, "o.nomSimpleGenerique.frFR(\"", nomSimpleGenerique.frFR(), "\");");
//				}
//				else if(nomSimpleGenerique.pasVide()) {
//					o.tabLigne(2, "o.nomSimpleGenerique.enUS(\"", nomSimpleGenerique.enUS(), "\");");
//					o.tabLigne(2, "o.nomSimpleGenerique.frFR(\"", nomSimpleGenerique.frFR(), "\");");
//				}
//			} 
//	
//			if(nomCanoniqueComplet.pasVide()) {
//				if(nomCanonique.commencePar("org.computate")) {
//					o.tab(2, "o.nomCanoniqueComplet.enUS(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanonique.enUS(), "org.computate"));
//					if(nomCanoniqueGenerique.pasVide()) {
//						o.tout("<");
//						if(nomCanoniqueGenerique.commencePar("org.computate")) {
//							o.tout("\", cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanoniqueGenerique.enUS(), "org.computate"));
//						}
//						else if(nomCanoniqueGenerique.pasVide()) {
//							o.tout(nomCanoniqueGenerique.enUS());
//						}
//						o.tout(">");
//					}
//					o.toutLigne("\");");
//					o.tab(2, "o.nomCanoniqueComplet.frFR(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanonique.frFR(), "org.computate"));
//					if(nomCanoniqueGenerique.pasVide()) {
//						o.tout("<");
//						if(nomCanoniqueGenerique.commencePar("org.computate")) {
//							o.tout("\", cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanoniqueGenerique.frFR(), "org.computate"));
//						}
//						else if(nomCanoniqueGenerique.pasVide()) {
//							o.tout(nomCanoniqueGenerique.frFR());
//						}
//						o.tout(">");
//					}
//					o.toutLigne("\");");
//				}
//				else if(nomCanonique.pasVide()) {
//					o.tab(2, "o.nomCanoniqueComplet.enUS(\"", nomCanonique.enUS());
//					if(nomCanoniqueGenerique.pasVide()) {
//						o.tout("<");
//						if(nomCanoniqueGenerique.commencePar("org.computate")) {
//							o.tout("\", cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanoniqueGenerique.enUS(), "org.computate"));
//						}
//						else if(nomCompletGenerique.pasVide()) {
//							o.tout(nomCompletGenerique.enUS());
//						}
//						o.tout(">");
//					}
//					o.toutLigne("\");");
//					o.tab(2, "o.nomCanoniqueComplet.frFR(\"", nomCanonique.frFR());
//					if(nomCanoniqueGenerique.pasVide()) {
//						o.tout("<");
//						if(nomCanoniqueGenerique.commencePar("org.computate")) {
//							o.tout("\", cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanoniqueGenerique.frFR(), "org.computate"));
//						}
//						else if(nomCompletGenerique.pasVide()) {
//							o.tout(nomCompletGenerique.frFR());
//						}
//						o.tout(">");
//					}
//					o.toutLigne("\");");
//				}
//			}
//	
//			if(nomSimpleComplet.pasVide()) {
//				o.tab(2, "o.nomSimpleComplet.enUS(\"", nomSimple.enUS());
//				if(nomCanoniqueGenerique.pasVide()) {
//					o.tout("<");
//					o.tout(nomSimpleCompletGenerique.enUS());
//					o.tout(">");
//				}
//				o.toutLigne("\");");
//				o.tab(2, "o.nomSimpleComplet.frFR(\"", nomSimple.frFR());
//				if(nomCanoniqueGenerique.pasVide()) {
//					o.tout("<");
//					o.tout(nomSimpleCompletGenerique.frFR());
//					o.tout(">");
//				}
//				o.toutLigne("\");");
//			}
//	
//			if(nomSimpleCompletGenerique.pasVide()) {
//				o.tab(2, "o.nomSimpleCompletGenerique.enUS(\"", nomSimpleCompletGenerique.enUS());
//				o.toutLigne("\");");
//				o.tab(2, "o.nomSimpleCompletGenerique.frFR(\"", nomSimpleCompletGenerique.frFR());
//				o.toutLigne("\");");
//			}
//	
//			if(nomCanoniqueBase.pasVide() && !nomCanoniqueBase.toString().equals("java.lang.Object")) {
//				if(nomCanoniqueBase.commencePar("org.computate")) {
//					o.tabLigne(2, "o.nomCanoniqueBase.enUS(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanoniqueBase.enUS(), "org.computate"), "\");");
//					o.tabLigne(2, "o.nomCanoniqueBase.frFR(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomCanoniqueBase.frFR(), "org.computate"), "\");");
//				}
//				else if(nomCanoniqueBase.pasVide()) {
//					o.tabLigne(2, "o.nomCanoniqueBase.enUS(\"", nomCanoniqueBase.enUS(), "\");");
//					o.tabLigne(2, "o.nomCanoniqueBase.frFR(\"", nomCanoniqueBase.frFR(), "\");");
//				}
//			}
//	
//			if(nomSimpleBase.pasVide() && !nomCanoniqueBase.toString().equals("java.lang.Object")) {
//				if(nomCanoniqueBase.commencePar("org.computate")) {
//					o.tabLigne(2, "o.nomSimpleBase.enUS(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomSimpleBase.enUS(), "org.computate"), "\");");
//					o.tabLigne(2, "o.nomSimpleBase.frFR(cours.nomEnsembleSite, \"", StringUtils.substringAfter(nomSimpleBase.frFR(), "org.computate"), "\");");
//				}
//				else if(nomCanoniqueBase.pasVide()) {
//					o.tabLigne(2, "o.nomSimpleBase.enUS(\"", nomSimpleBase.enUS(), "\");");
//					o.tabLigne(2, "o.nomSimpleBase.frFR(\"", nomSimpleBase.frFR(), "\");");
//				}
//			}
//	
//			if(nomCanonique.commencePar("org.computate")) {
//				o.tabLigne(2, "o.nomSimple.enUS(\"", nomSimple.enUS(), "\");");
//				o.tabLigne(2, "o.nomSimple.frFR(\"", nomSimple.frFR(), "\");");
//			}
//			else if(nomCanonique.pasVide()) {
//				o.tabLigne(2, "o.nomSimple.enUS(\"", nomSimple.enUS(), "\");");
//				o.tabLigne(2, "o.nomSimple.frFR(\"", nomSimple.frFR(), "\");");
//			}
//	
//			if(!StringUtils.equals(paramVar.toString(), "o")) {
//				o.tabLigne(2, "o.paramVar.tout(\"", paramVar, "\");");
//			}
//	
//			if(typeSolr.pasVide()) {
//				o.tabLigne(2, "o.typeSolr.tout(\"", typeSolr, "\");");
//			}
//
//			if(etendPageXml)
//				o.tabLigne(2, "o.etendPageXml(", etendPageXml, ");");
//			if(etendPageParti)
//				o.tabLigne(2, "o.etendPageParti(", etendPageParti, ");");
//			if(etendCluster)
//				o.tabLigne(2, "o.etendCluster(", etendCluster, ");");
//			if(listePageXml)
//				o.tabLigne(2, "o.listePageXml(", listePageXml, ");");
//			if(listePageParti)
//				o.tabLigne(2, "o.listePageParti(", listePageParti, ");");
//			if(listeCluster)
//				o.tabLigne(2, "o.listeCluster(", listeCluster, ");");
//
//			if(cleUnique)
//				o.tabLigne(2, "o.cleUnique(", cleUnique, ");");
//			if(indexe)
//				o.tabLigne(2, "o.indexe(", indexe, ");");
//			if(stocke)
//				o.tabLigne(2, "o.stocke(", stocke, ");");
//			if(crypte)
//				o.tabLigne(2, "o.crypte(", crypte, ");");
//			if(suggere)
//				o.tabLigne(2, "o.suggere(", suggere, ");");
//			if(sauvegarde)
//				o.tabLigne(2, "o.sauvegarde(", sauvegarde, ");");
//			if(texte)
//				o.tabLigne(2, "o.texte(", texte, ");");
//			if(enUS)
//				o.tabLigne(2, "o.enUS(", enUS, ");");
//			if(frFR)
//				o.tabLigne(2, "o.frFR(", frFR, ");");
//			if(incremente)
//				o.tabLigne(2, "o.incremente(", incremente, ");");
//			if(nomAffichage != null) {
//				o.tabLigne(2, "o.nomAffichage.enUS(\"", nomAffichage.enUS().replace("\"", "\\\""), "\");");
//				o.tabLigne(2, "o.nomAffichage.frFR(\"", nomAffichage.frFR().replace("\"", "\\\""), "\");");
//			}
//			if(declarer) {
//				o.tabLigne(2, "o.declarer(", declarer, ");");
//			}
//			if(rechercher) {
//				o.tabLigne(2, "o.rechercher(", rechercher, ");");
//			}
//			if(attribuer) {
//				o.tabLigne(2, "o.attribuer(", attribuer, ");");
//			}
//			if(ajouter) {
//				o.tabLigne(2, "o.ajouter(", ajouter, ");");
//			}
//			if(supprimer) {
//				o.tabLigne(2, "o.supprimer(", supprimer, ");");
//			}
//			if(modifier) {
//				o.tabLigne(2, "o.modifier(", modifier, ");");
//			}
//			if(recharger) {
//				o.tabLigne(2, "o.recharger(", recharger, ");");
//			}
//			if(multiligne) {
//				o.tabLigne(2, "o.multiligne(", multiligne, ");");
//			}
//			if(cles) {
//				o.tabLigne(2, "o.cles(", cles, ");");
//			}
//			if(attribuer || ajouter || supprimer || recharger || cles || rechercher) {
//				o.tabLigne(2, "o.contexteEnfant(", var, "Contexte);");
//				o.tabLigne(2, "o.contexteParent(classeContexte);");
//			}
//			if(couverture)
//				o.tabLigne(2, "o.couverture(", couverture, ");");
//			if(!triAscendant)
//				o.tabLigne(2, "o.triAscendant(", triAscendant, ");");
//			if(initialise)
//				o.tabLigne(2, "o.initialise(", initialise, ");");
//			if(initLoin)
//				o.tabLigne(2, "o.initLoin(", initLoin, ");");
//			if(contientRequeteSite)
//				o.tabLigne(2, "o.contientRequeteSite(", contientRequeteSite, ");");
//			if(contientSetterString)
//				o.tabLigne(2, "o.contientSetterString(", contientSetterString, ");");
//			if(methodeInitialiserRemplace)
//				o.tabLigne(2, "o.methodeInitialiserRemplace(", methodeInitialiserRemplace, ");");
//			if(methodeRechercheRemplace)
//				o.tabLigne(2, "o.methodeRechercheRemplace(", methodeRechercheRemplace, ");");
//	
//			if(varCleUnique.pasVide()) {
//				o.tabLigne(2, "o.varCleUnique.enUS(\"", varCleUnique.enUS(), "\");");
//				o.tabLigne(2, "o.varCleUnique.frFR(\"", varCleUnique.frFR(), "\");");
//			}
//			if(varSuggere.pasVide()) {
//				o.tabLigne(2, "o.varSuggere.enUS(\"", varSuggere.enUS(), "\");");
//				o.tabLigne(2, "o.varSuggere.frFR(\"", varSuggere.frFR(), "\");");
//			}
//			if(varSuggereEnUS.pasVide()) {
//				o.tabLigne(2, "o.varSuggereEnUS.enUS(\"", varSuggereEnUS.enUS(), "\");");
//				o.tabLigne(2, "o.varSuggereEnUS.frFR(\"", varSuggereEnUS.frFR(), "\");");
//			}
//			if(varSuggereFrFR.pasVide()) {
//				o.tabLigne(2, "o.varSuggereFrFR.enUS(\"", varSuggereFrFR.enUS(), "\");");
//				o.tabLigne(2, "o.varSuggereFrFR.frFR(\"", varSuggereFrFR.frFR(), "\");");
//			}
//			if(varIncremente.pasVide()) {
//				o.tabLigne(2, "o.varIncremente.enUS(\"", varIncremente.enUS(), "\");");
//				o.tabLigne(2, "o.varIncremente.frFR(\"", varIncremente.frFR(), "\");");
//			}
//			if(varCrypte.pasVide()) {
//				o.tabLigne(2, "o.varCrypte.enUS(\"", varCrypte.enUS(), "\");");
//				o.tabLigne(2, "o.varCrypte.frFR(\"", varCrypte.frFR(), "\");");
//			}
//			if(varIndexe.pasVide()) {
//				o.tabLigne(2, "o.varIndexe.enUS(\"", varIndexe.enUS(), "\");");
//				o.tabLigne(2, "o.varIndexe.frFR(\"", varIndexe.frFR(), "\");");
//			}
//			if(varIndexeEnUS.pasVide()) {
//				o.tabLigne(2, "o.varIndexeEnUS.enUS(\"", varIndexeEnUS.enUS(), "\");");
//				o.tabLigne(2, "o.varIndexeEnUS.frFR(\"", varIndexeEnUS.frFR(), "\");");
//			}
//			if(varIndexeFrFR.pasVide()) {
//				o.tabLigne(2, "o.varIndexeFrFR.enUS(\"", varIndexeFrFR.enUS(), "\");");
//				o.tabLigne(2, "o.varIndexeFrFR.frFR(\"", varIndexeFrFR.frFR(), "\");");
//			}
//			if(varStocke.pasVide()) {
//				o.tabLigne(2, "o.varStocke.enUS(\"", varStocke.enUS(), "\");");
//				o.tabLigne(2, "o.varStocke.frFR(\"", varStocke.frFR(), "\");");
//			}
//			if(varStockeAvantLangue.pasVide()) {
//				o.tabLigne(2, "o.varStockeAvantLangue.enUS(\"", varStockeAvantLangue.enUS(), "\");");
//				o.tabLigne(2, "o.varStockeAvantLangue.frFR(\"", varStockeAvantLangue.frFR(), "\");");
//			}
//			if(varStockeApresLangue.pasVide()) {
//				o.tabLigne(2, "o.varStockeApresLangue.enUS(\"", varStockeApresLangue.enUS(), "\");");
//				o.tabLigne(2, "o.varStockeApresLangue.frFR(\"", varStockeApresLangue.frFR(), "\");");
//			}
//			if(varStockeEnUS.pasVide()) {
//				o.tabLigne(2, "o.varStockeEnUS.enUS(\"", varStockeEnUS.enUS(), "\");");
//				o.tabLigne(2, "o.varStockeEnUS.frFR(\"", varStockeEnUS.frFR(), "\");");
//			}
//			if(varStockeFrFR.pasVide()) {
//				o.tabLigne(2, "o.varStockeFrFR.enUS(\"", varStockeFrFR.enUS(), "\");");
//				o.tabLigne(2, "o.varStockeFrFR.frFR(\"", varStockeFrFR.frFR(), "\");");
//			}
//	
//			Boolean remplacerComputate = StringUtils.equals("true", classe_.regex("remplacerComputate:\\s*(.*)", commentaireQdox, 1));
//			if(codeSource.pasVide()) {
//				o.tabLigne(0);
//				{
//					o.tabLigne(2, "o.codeSource");
//					String[] lignes = codeSource.frFR().split("\n");
//					for(int i = 0; i < lignes.length; i++) {
//						String ligne = lignes[i];
//						Boolean dernier = i + 1 == lignes.length;
//						Integer tabulations = StringUtils.countMatches(ligne, "\t");
//						if(remplacerComputate) {
//							if(dernier)
//								o.tabLigne(3, ".frFRTab(", tabulations, ").frFR(\"", StringUtils.replace(StringUtils.replace(StringUtils.replace(ligne.substring(tabulations), "\\", "\\\\"), "\"", "\\\""), "computate.org", "\", cours.nomDomaine, \""), "\");");
//							else
//								o.tabLigne(3, ".frFRTab(", tabulations, ").frFRLigne(\"", StringUtils.replace(StringUtils.replace(StringUtils.replace(ligne.substring(tabulations), "\\", "\\\\"), "\"", "\\\""), "computate.org", "\", cours.nomDomaine, \""), "\")");
//						}
//						else {
//							if(dernier)
//								o.tabLigne(3, ".frFRTab(", tabulations, ").frFR(\"", StringUtils.replace(StringUtils.replace(ligne.substring(tabulations), "\\", "\\\\"), "\"", "\\\""), "\");");
//							else
//								o.tabLigne(3, ".frFRTab(", tabulations, ").frFRLigne(\"", StringUtils.replace(StringUtils.replace(ligne.substring(tabulations), "\\", "\\\\"), "\"", "\\\""), "\")");
//						}
//					}
//				}
//				{
//					o.tabLigne(2, "o.codeSource");
//					String[] lignes = codeSource.enUS().split("\n");
//					for(int i = 0; i < lignes.length; i++) {
//						String ligne = lignes[i];
//						Boolean dernier = i + 1 == lignes.length;
//						Integer tabulations = StringUtils.countMatches(ligne, "\t");
//						if(remplacerComputate) {
//							if(dernier)
//								o.tabLigne(3, ".enUSTab(", tabulations, ").enUS(\"", StringUtils.replace(StringUtils.replace(StringUtils.replace(ligne.substring(tabulations), "\\", "\\\\"), "\"", "\\\""), "computate.org", "\", cours.nomDomaine, \""), "\");");
//							else
//								o.tabLigne(3, ".enUSTab(", tabulations, ").enUSLigne(\"", StringUtils.replace(StringUtils.replace(StringUtils.replace(ligne.substring(tabulations), "\\", "\\\\"), "\"", "\\\""), "computate.org", "\", cours.nomDomaine, \""), "\")");
//						}
//						else {
//							if(dernier)
//								o.tabLigne(3, ".enUSTab(", tabulations, ").enUS(\"", StringUtils.replace(StringUtils.replace(ligne.substring(tabulations), "\\", "\\\\"), "\"", "\\\""), "\");");
//							else
//								o.tabLigne(3, ".enUSTab(", tabulations, ").enUSLigne(\"", StringUtils.replace(StringUtils.replace(ligne.substring(tabulations), "\\", "\\\\"), "\"", "\\\""), "\")");
//						}
//					}
//				}
//			}
//	
//			if(commentaire.pasVide()) {
//				o.tabLigne(0);
//				{
//					o.tabLigne(2, "if(requeteSite.frFR()) {");
//					o.tabLigne(3, "o.commentaire");
//					String[] lignes = commentaire.frFR().split("\n");
//					for(int i = 0; i < lignes.length; i++) {
//						String ligne = lignes[i];
//						Boolean dernier = i + 1 == lignes.length;
//						Integer tabulations = StringUtils.countMatches(ligne, "\t");
//						if(dernier)
//							o.tabLigne(4, ".tab(", tabulations, ", \"", StringUtils.replaceEach(ligne.substring(tabulations), new String[] {"\\", "\""}, new String[] {"\\\\", "\\\""}), "\");");
//						else
//							o.tabLigne(4, ".tabLigne(", tabulations, ", \"", StringUtils.replaceEach(ligne.substring(tabulations), new String[] {"\\", "\""}, new String[] {"\\\\", "\\\""}), "\")");
//					}
//					o.tabLigne(2, "}");
//				}
//				{
//					o.tabLigne(2, "else {");
//					o.tabLigne(3, "o.commentaire");
//					String[] lignes = commentaire.enUS().split("\n");
//					for(int i = 0; i < lignes.length; i++) {
//						String ligne = lignes[i];
//						Boolean dernier = i + 1 == lignes.length;
//						Integer tabulations = StringUtils.countMatches(ligne, "\t");
//						if(dernier)
//							o.tabLigne(4, ".tab(", tabulations, ", \"", StringUtils.replaceEach(ligne.substring(tabulations), new String[] {"\\", "\""}, new String[] {"\\\\", "\\\""}), "\");");
//						else
//							o.tabLigne(4, ".tabLigne(", tabulations, ", \"", StringUtils.replaceEach(ligne.substring(tabulations), new String[] {"\\", "\""}, new String[] {"\\\\", "\\\""}), "\")");
//					}
//					o.tabLigne(2, "}");
//				}
//			}
//	
//			o.tabLigne(1, "}");
//		}
//	}
//
//	public void ecrireUnderscoreMethode(Chaine o, UneClasse classe) throws Exception {
//		if(!nul && initialise) {
//			// Préparer commentaire //
//			Chaine comment = new Chaine();
//			comment.requeteSite(requeteSite);
//			comment.tout("\t/**\t");
//			comment.enUS(StringUtils.trim(commentaire.enUS()).replaceAll("\n(\\s*\\S.*)", "\n\t *\t<br/>$1"));
//			comment.frFR(StringUtils.trim(commentaire.frFR()).replaceAll("\n(\\s*\\S.*)", "\n\t *\t<br/>$1"));
//			comment.tout(" **/\n");
//	
//			// Underscore méthode //
//			o.toutLigne();
//			o.tout(comment);
//			if(couverture)
//				o.toutLigne("\tprotected void _", varComplet, "(", classe.nomSimpleUneCouverture, "<", nomSimpleComplet, "> c) throws Exception {");
//			else
//				o.toutLigne("\tprotected void _", varComplet, "(", nomSimpleComplet + " " + paramVar, ") throws Exception {");
////			o.tout(methodeJava.frFR().replaceAll("^(\\s*\\S.*)", "\t\t$1").replaceAll("\n(\\s*\\S.*)", "\n\t\t$1"));
//			o.tout(codeSource);
//			o.toutLigne("\t}");
//		}
//		o.tout(methodeJavaApres);
//	}
//
//	public void ecrireUnderscoreMethodeAbstrait(Chaine o, UneClasse classe) throws Exception {
//		if(!nul && initialise) {
//			// Préparer commentaire //
//			Chaine comment = new Chaine();
//			comment.requeteSite(requeteSite);
//			comment.tout("\t/**\t");
//			comment.enUS(StringUtils.trim(commentaire.enUS()).replaceAll("\n(\\s*\\S.*)", "\n\t *\t<br/>$1"));
//			comment.frFR(StringUtils.trim(commentaire.frFR()).replaceAll("\n(\\s*\\S.*)", "\n\t *\t<br/>$1"));
//			comment.tout(" **/\n");
//	
//			// Underscore méthode //
//			if(couverture)
//				o.toutLigne("\tprotected abstract void _", varComplet, "(", classe.nomSimpleUneCouverture, "<", nomSimpleComplet, "> c) throws Exception;");
//			else
//				o.toutLigne("\tprotected abstract void _", varComplet, "(", nomSimpleComplet + " " + paramVar, ") throws Exception;");
//		}
//		o.tout(methodeJavaApres);
//	}
//
//	public void ecrireChamp(Chaine o, UneClasse classe) throws Exception {
//		// Préparer commentaire //
//		Chaine comment = new Chaine();
//		comment.requeteSite(requeteSite);
//		comment.tout("\t/**\t");
//		comment.enUS(StringUtils.trim(commentaire.enUS()).replaceAll("\n(\\s*\\S.*)", "\n\t *\t<br/>$1"));
//		comment.frFR(StringUtils.trim(commentaire.frFR()).replaceAll("\n(\\s*\\S.*)", "\n\t *\t<br/>$1"));
//		comment.tout(" **/\n");
//
//		// Préparer ligne //
//		Chaine ligne = new Chaine();
//		ligne.enUS("\t///", String.join("", Collections.nCopies(varComplet.enUS().length(), "/")), "///");
//		ligne.frFR("\t///", String.join("", Collections.nCopies(varComplet.frFR().length(), "/")), "///");
//
//		// Ligne //
//		o.toutLigne("");
//		o.toutLigne(ligne);
//		o.toutLigne("\t// ", varComplet, " //");
//		o.toutLigne(ligne);
//		o.toutLigne("");
//
//		// Champ //
//		o.tout(comment);
//		o.tout("\tpublic ", nomSimpleComplet, " ", var);
//		if(couverture) 
//			o.toutLigne(" = null;"); 
//		else if(nul) {
//			o.tout(" = ");
//
//			if(val.enUS.isEmpty())
//				o.tout("null");
//			else
//				o.tout(val);
//
//			o.toutLigne(";"); 
//		}
//		else o.toutLigne(" = new ", nomSimpleComplet, "();");
//
//		// Couverture //
//		if(!nul) {
//			o.tout(comment);
//			o.tout("\tpublic ", classe.nomSimpleUneCouverture, "<", nomSimpleComplet, "> ", varComplet, classe.varCouvertureCapitalise);
////			o.toutLigne(" = new ", classe.nomSimpleUneCouverture, "<", nomSimpleComplet, ">(this, ", nomSimple, ".class, \"", varComplet, "\", ", varComplet, ");");
//			o.tout(" = new ").enUS("Wrap").frFR("Couverture").tout("<", nomSimpleComplet, ">().p(this).c(", nomSimple, ".class).var(\"", var, "\").o(", var, ")");
//			o.toutLigne(";");
//		}
//
//		// Setter //
//		if(setter) {
//			o.toutLigne("\tpublic ", classe.nomSimpleComplet, " ", varComplet, "(", nomSimpleComplet, " o) throws Exception {");
//			o.toutLigne("\t\tthis.", varComplet, " = o;");
//			o.tabLigne(2, "if(o != null)");
//			o.tabLigne(3, varComplet, classe.varCouvertureCapitalise, ".", classe.varDejaInitialise, "(true);");
//			o.toutLigne("\t\treturn (", classe.nomSimple, ")this;");
//			o.toutLigne("\t}");
//
//			if(nomCanonique.toString().equals(Boolean.class.getCanonicalName())) {
//				o.tabLigne(1, "public ", classe.nomSimpleComplet, " ", varComplet, "(String o) throws Exception {");
//				o.tabLigne(2, "if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))");
//				o.tabLigne(3, "this.", varComplet, " = Boolean.parseBoolean(o);");
//				o.tabLigne(2, varComplet, classe.varCouvertureCapitalise, ".", classe.varDejaInitialise, "(true);");
//				o.tabLigne(2, "return (", classe.nomSimple, ")this;");
//				o.tabLigne(1, "}");
//			}
//			else if(nomCanonique.toString().equals(Long.class.getCanonicalName())) {
//				o.tabLigne(1, "public ", classe.nomSimpleComplet, " ", varComplet, "(String o) throws Exception {");
//				o.tabLigne(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o))");
//				o.tabLigne(3, "this.", varComplet, " = Long.parseLong(o);");
//				o.tabLigne(3, varComplet, classe.varCouvertureCapitalise, ".", classe.varDejaInitialise, "(true);");
//				o.tabLigne(2, "return (", classe.nomSimple, ")this;");
//				o.tabLigne(1, "}");
//			}
//			else if(nomCanonique.equals(ArrayList.class.getCanonicalName()) && nomCanoniqueGenerique.equals(Long.class.getCanonicalName())) {
//				o.tabLigne(1, "public ", classe.nomSimpleComplet, " ", varComplet, "(String o) throws Exception {");
//				o.tabLigne(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o)) {");
//				o.tabLigne(3, "Long l = Long.parseLong(o);");
//				o.tabLigne(3, var, "Ajouter(l);");
//				o.tabLigne(3, varComplet, classe.varCouvertureCapitalise, ".", classe.varDejaInitialise, "(true);");
//				o.tabLigne(2, "}");
//				o.tabLigne(2, "return (", classe.nomSimple, ")this;");
//				o.tabLigne(1, "}");
//			}
//			else if(nomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
//				o.tabLigne(1, "public ", classe.nomSimpleComplet, " ", varComplet, "(String o) throws Exception {");
//				o.tabLigne(2, "this.", varComplet, " = java.time.LocalDateTime.parse(o, java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
//				o.tabLigne(2, varComplet, classe.varCouvertureCapitalise, ".", classe.varDejaInitialise, "(true);");
//				o.tabLigne(2, "return (", classe.nomSimple, ")this;");
//				o.tabLigne(1, "}");
//				o.tabLigne(1, "public ", classe.nomSimpleComplet, " ", varComplet, "(java.util.Date o) throws Exception {");
//				o.tabLigne(2, "this.", varComplet, " = java.time.LocalDateTime.ofInstant(o.toInstant(), java.time.ZoneId.systemDefault());");
//				o.tabLigne(2, varComplet, classe.varCouvertureCapitalise, ".", classe.varDejaInitialise, "(true);");
//				o.tabLigne(2, "return (", classe.nomSimple, ")this;");
//				o.tabLigne(1, "}");
//			}
//
//			if(setterStringJava.enUS().length() > 0) {
//				o.toutLigne("\tpublic ", classe.nomSimpleComplet, " ", varComplet, "(String s) throws Exception {");
//				o.tout(setterStringJava.frFR().replaceAll("^(\\s*\\S.*)", "\t\t$1").replaceAll("\n(\\s*\\S.*)", "\n\t\t$1"));
//				o.toutLigne("\t\treturn (", classe.nomSimple, ")this;");
//				o.toutLigne("\t}");
//			}
//		}
//
//		// Ajouter //
//		if(nomCanonique.equals(classe_.nomCanoniqueArrayList) || nomCanonique.equals(classe_.nomCanoniqueList)) {
//			o.tabLigne(1, "public ", classe.nomSimpleComplet, " ", varComplet, classe.varAjouterCapitalise, "(", nomCompletGenerique, "...", classe_.varObjets, ") throws Exception {");
//			o.tabLigne(2, "for(", nomCompletGenerique, " o : ", classe_.varObjets, ") {");
//			o.tabLigne(3, "", varComplet, classe_.varAjouterCapitalise, "(o);");
//			o.tabLigne(2, "}");
//			o.tabLigne(2, "return (", classe.nomSimple, ")this;");
//			o.tabLigne(1, "}");
//			o.tabLigne(1, "public ", classe.nomSimpleComplet, " ", varComplet, classe.varAjouterCapitalise, "(", nomCompletGenerique, " o) throws Exception {");
//			o.tabLigne(2, "if(o != null && !", varComplet, ".contains(o))");
//			o.tabLigne(3, "this.", varComplet, ".add(o);");
//			o.tabLigne(2, "return (", classe.nomSimple, ")this;");
//			o.tabLigne(1, "}");
//		}
//		if(classeQdox != null && classeQdox.getCanonicalName().equals(classe_.nomCanoniqueChaineActuel.toString())) {
//			o.tabLigne(1, "public ", classe.nomSimpleComplet, " ", varComplet, "(Object...", classe_.varObjets, ") {");
//			o.tabLigne(2, varComplet, ".", classe_.varTout, "(", classe_.varObjets, ");");
//			o.tabLigne(2, "return (", classe.nomSimple, ")this;");
//			o.tabLigne(1, "}");
//		}
//
//		{ // sh //
//			String nomMethodeAvant = classe_.varSh.toString() + classe_.varAvantCapitalise.toString();
//			String nomMethodeApres = classe_.varSh.toString() + classe_.varApresCapitalise.toString();
//			JavaMethod methodeAvant = classe_.obtenirMethode(classeQdox, nomMethodeAvant);
//			JavaMethod methodeApres = classe_.obtenirMethode(classeQdox, nomMethodeApres);
////			if(methodeAvant != null && methodeApres != null) {
//			if(etendPageParti) {
//				o.tabLigne(1, "protected void _", varComplet, classe_.varShCapitalise, "() throws Exception {}");
//				o.tabLigne(1, "public void ", varComplet, classe_.varShCapitalise, "() throws Exception {");
//				o.tabLigne(2, varComplet, ".", classe_.varSh, classe_.varAvantCapitalise, "();");
//				o.tabLigne(2, "_", varComplet, classe_.varShCapitalise, "();");
//				o.tabLigne(2, varComplet, ".", classe_.varSh, classe_.varApresCapitalise, "();");
//				o.tabLigne(1, "}");
//			}
//		}
//		o.toutLigne();
//
//		{ // code //
//			String nomMethodeAvant = "code" + classe_.varAvantCapitalise.toString();
//			String nomMethodeApres = "code" + classe_.varApresCapitalise.toString();
//			JavaMethod methodeAvant = classe_.obtenirMethode(classeQdox, nomMethodeAvant); 
//			JavaMethod methodeApres = classe_.obtenirMethode(classeQdox, nomMethodeApres);
////			if(methodeAvant != null && methodeApres != null) {
//			if(etendPageParti) {
//				o.tabLigne(1, "protected void _", varComplet, "Code", "() throws Exception {}");
//				o.tabLigne(1, "public void ", varComplet, "Code", "() throws Exception {");
//				o.tabLigne(2, varComplet, ".", "code", classe_.varAvantCapitalise, "();");
//				o.tabLigne(2, "_", varComplet, "Code", "();");
//				o.tabLigne(2, varComplet, ".", "code", classe_.varApresCapitalise, "();");
//				o.tabLigne(1, "}");
//			}
//		}
//		o.toutLigne();
//
//		{ // shShHtml //
//			String nomMethodeAvant = classe_.varShHtml.toString() + classe_.varAvantCapitalise.toString();
//			String nomMethodeApres = classe_.varShHtml.toString() + classe_.varApresCapitalise.toString();
//			JavaMethod methodeAvant = classe_.obtenirMethode(classeQdox, nomMethodeAvant); 
//			JavaMethod methodeApres = classe_.obtenirMethode(classeQdox, nomMethodeApres);
////			if(methodeAvant != null && methodeApres != null) {
//			if(etendPageParti) {
//				o.tabLigne(1, "protected void _", varComplet, classe_.varShHtmlCapitalise, "() throws Exception {}");
//				o.tabLigne(1, "public void ", varComplet, classe_.varShHtmlCapitalise, "() throws Exception {");
//				o.tabLigne(2, varComplet, ".", classe_.varShHtml, classe_.varAvantCapitalise, "();");
//				o.tabLigne(2, "_", varComplet, classe_.varShHtmlCapitalise, "();");
//				o.tabLigne(2, varComplet, ".", classe_.varShHtml, classe_.varApresCapitalise, "();");
//				o.tabLigne(1, "}");
//			}
//		}
//		o.toutLigne();
//
//		{ // html //
//			String nomMethodeAvant = classe_.varHtml.toString() + classe_.varAvantCapitalise.toString();
//			String nomMethodeApres = classe_.varHtml.toString() + classe_.varApresCapitalise.toString();
//			JavaMethod methodeAvant = classe_.obtenirMethode(classeQdox, nomMethodeAvant);
//			JavaMethod methodeApres = classe_.obtenirMethode(classeQdox, nomMethodeApres);
////			if(methodeAvant != null && methodeApres != null) {
//			if(etendPageParti) {
//				o.tabLigne(1, "protected void _", varComplet, classe_.varHtmlCapitalise, "() throws Exception {}");
//				o.tabLigne(1, "public void ", varComplet, classe_.varHtmlCapitalise, "() throws Exception {");
//				o.tabLigne(2, varComplet, ".", classe_.varHtml, classe_.varAvantCapitalise, "();");
//				o.tabLigne(2, "_", varComplet, classe_.varHtmlCapitalise, "();");
//				o.tabLigne(2, varComplet, ".", classe_.varHtml, classe_.varApresCapitalise, "();");
//				o.tabLigne(1, "}");
//			}
//			else if(classe_.etendCluster && !classe_.nomCanonique.toString().equals(classe_.nomCanoniqueClusterActuel.toString())) {
//				o.tabLigne(1, "public void ", varComplet, classe.varHtmlCapitalise, "() throws Exception {");
//				o.tabLigne(2, "e(\"div\").a(\"class\", \"w3-cell w3-cell-middle w3-center w3-mobile\").f();");
//					o.tabLigne(3, "e(\"label\").a(\"class\", \"\").f();");
//						o.tabLigne(4, "toutXml(", var, classe_.nomSimpleUneCouverture, ".", classe_.varNomAffichage, "());");
//						o.tabLigne(4, "page_.enUSXml(\": \");");
//						o.tabLigne(4, "page_.frFRXml(\" : \");");
//					o.tabLigne(3, "g(\"label\");");
//					o.tabLigne(3, "e(\"form\").a(\"method\", \"POST\").a(\"action\", ", classe_.varPage, "_.", classe_.varPageUri, ").a(\"class\", \"champForm \").f();");
//						o.tabLigne(4, "e(\"input\").a(\"type\", \"hidden\").a(\"name\", \"", classe_.varCle, "\").a(\"value\", ", classe_.varCle, ").fg();");
//						o.tabLigne(4, "e(\"input\").a(\"class\", \"w3-input w3-border champInput \").a(\"name\", \"", var, "\").a(\"type\", \"text\").a(\"value\", ", var, ").a(\"title\", ", var, ");");
//							// if lueur
//							//o.tabLigne(5, "a(\"onchange\", \"envoyerFormulaire($(this), $('#\" + id + \"')); \");");
//							o.tabLigne(5, "a(\"onchange\", \"envoyerFormulaire($(this), $(this)); \").a(\"onclick\", \"enleverLueur($(this)); \");");
//							o.tabLigne(5, "fg();");
//					o.tabLigne(3, "g(\"form\");");
//				o.tabLigne(2, "g(\"div\");");
//				o.tabLigne(1, "}");
//			}
//		}
//		o.toutLigne();
//
//		// Html //
//	}
//
//	public void ecrireInitialiser(Chaine o, UneClasse classe) throws Exception {
//		if(initialise) {
//			Chaine comment = new Chaine();
//			comment.requeteSite(requeteSite);
//			comment.tout("\t/**\t");
//			comment.enUS(StringUtils.trim(commentaire.enUS()).replaceAll("\n(\\s*\\S.*)", "\n\t *\t<br/>$1"));
//			comment.frFR(StringUtils.trim(commentaire.frFR()).replaceAll("\n(\\s*\\S.*)", "\n\t *\t<br/>$1"));
//			comment.tout(" **/\n");
//	
//			// Initialiser //
//			o.toutLigne("\tprotected void ", varComplet, classe.varInitialiserCapitalise, "() throws Exception {");
//			if(nul) {
//				o.toutLigne("\t\t_", varComplet, "(", varComplet, ");");
//			}
//			else {
//
//				// init
//
//				o.tout("\t\tif(!", varComplet, classe.varCouvertureCapitalise, ".", classe.varDejaInitialise);
//				if(nomCanonique.equals(classe_.nomCanoniqueChaineActuel)) {
//					o.tout(" && ", varComplet, ".", classe_.varEstVide, "()");
//				}
//				o.toutLigne(") {");
//				if(couverture) {
//					o.toutLigne("\t\t\t_", varComplet, "(", varComplet, classe.varCouvertureCapitalise, ");");
//					o.toutLigne("\t\t\tif(", varComplet, " == null && ", varComplet, classe.varCouvertureCapitalise, " != null)");
//					o.toutLigne("\t\t\t\t", varComplet, "(", varComplet, classe.varCouvertureCapitalise, ".o);");
//				}
//				else {
//					o.toutLigne("\t\t\t_", varComplet, "(", varComplet, ");");
//				}
//				o.toutLigne("\t\t}");
//
//				// Avant champ //
//				if(classeQdox != null) {
//					String nomMethodeAvant = var.toString() + classe_.varAvantCapitalise;
//					ArrayList<JavaType> params = new ArrayList<JavaType>();
//					params.add(classeQdox);
//					methodeAvantChamp = classe_.classeQdox.getMethod(nomMethodeAvant, params, false);
//					if(methodeAvantChamp != null) {
//						o.tab(2);
//						if(!classe_.classeCopier && !classe_.classeCompleter)
//							o.tout("((", classe_.nomSimple, ")this).");
//						o.toutLigne(nomMethodeAvant, "(", var, ");");
//					}
//				}
//
//				// Avant classe //
//				if(classeQdox != null) {
//					String nomMethodeAvant = classe_.varAvant.toString() + nomSimple.toString();
//					methodeAvantClasse = classe_.obtenirMethode(nomMethodeAvant, classeQdox, new DefaultJavaType(String.class.getCanonicalName()));
//					if(methodeAvantClasse != null) {
//						o.tab(2);
//						if(!classe_.classeCopier && !classe_.classeCompleter)
//							o.tout("((", classe_.nomSimple, ")this).");
//						o.toutLigne(nomMethodeAvant, "(", var, ", \"", var, "\");");
//					}
//					else {
//						methodeAvantClasse = classe_.obtenirMethode(nomMethodeAvant, classeQdox);
//						if(methodeAvantClasse != null) {
//							o.tab(2);
//							if(!classe_.classeCopier && !classe_.classeCompleter)
//								o.tout("((", classe_.nomSimple, ")this).");
//							o.toutLigne(nomMethodeAvant, "(", var, ");");
//						}
//						else if(nomSimpleBase.pasVide()){
//								
//							nomMethodeAvant = classe_.varAvant.toString() + nomSimpleBase.toString();
//							methodeAvantClasse = classe_.obtenirMethode(nomMethodeAvant, classeQdoxBase, new DefaultJavaType(String.class.getCanonicalName()));
//							if(methodeAvantClasse != null) {
//								o.tab(2);
//								if(!classe_.classeCopier && !classe_.classeCompleter)
//									o.tout("((", classe_.nomSimple, ")this).");
//								o.toutLigne(nomMethodeAvant, "(", var, ", \"", var, "\");");
//							}
//							else {
//								methodeAvantClasse = classe_.obtenirMethode(nomMethodeAvant, classeQdoxBase);
//								if(methodeAvantClasse != null) {
//									o.tab(2);
//									if(!classe_.classeCopier && !classe_.classeCompleter)
//										o.tout("((", classe_.nomSimple, ")this).");
//									o.toutLigne(nomMethodeAvant, "(", var, ");");
//								}
//							}
//						}
//					}
//				}
//
//				// initLoin
//
////				if(!couverture && nomCanonique.enUS().startsWith(classe.nomEnsembleDomaine.enUS()))
//				if(initLoin && nomCanonique.enUS().startsWith(classe.nomEnsembleDomaine.enUS())) {
//					if(couverture) {
//						o.tabLigne(2, "if(", varComplet, " != null)");
//						o.tabLigne(3, varComplet, ".", classe.varInitialiserLoin, nomSimple, "(", classe.varRequeteSite, ");");
//					}
//					else {
//						o.tabLigne(2, varComplet, ".", classe.varInitialiserLoin, nomSimple, "(", classe.varRequeteSite, ");");
//					}
//				}
//
//				// Apres champ //
//				if(classeQdox != null) {
//					String nomMethodeApres = var.toString() + classe_.varApresCapitalise;
//					ArrayList<JavaType> params = new ArrayList<JavaType>();
//					params.add(classeQdox);
//					methodeApresChamp = classe_.classeQdox.getMethod(nomMethodeApres, params, false);
//					if(methodeApresChamp != null) {
//						o.tab(2);
//						if(!classe_.classeCopier && !classe_.classeCompleter)
//							o.tout("((", classe_.nomSimple, ")this).");
//						o.toutLigne(nomMethodeApres, "(", var, ");");
//					}
//				}
//
//				// Apres classe //
//				if(classeQdox != null) {
//					String nomMethodeApres = classe_.varApres.toString() + nomSimple.toString();
//					methodeApresClasse = classe_.obtenirMethode(nomMethodeApres, classeQdox, new DefaultJavaType(String.class.getCanonicalName()));
//					if(methodeApresClasse != null) {
//						o.tab(2);
//						if(!classe_.classeCopier && !classe_.classeCompleter)
//							o.tout("((", classe_.nomSimple, ")this).");
//						o.toutLigne(nomMethodeApres, "(", var, ", \"", var, "\");");
//					}
//					else {
//						methodeApresClasse = classe_.obtenirMethode(nomMethodeApres, classeQdox);
//						if(methodeApresClasse != null) {
//							o.tab(2);
//							if(!classe_.classeCopier && !classe_.classeCompleter)
//								o.tout("((", classe_.nomSimple, ")this).");
//							o.toutLigne(nomMethodeApres, "(", var, ");");
//						}
//						else if(nomSimpleBase.pasVide()){
//								
//							nomMethodeApres = classe_.varApres.toString() + nomSimpleBase.toString();
//							methodeApresClasse = classe_.obtenirMethode(nomMethodeApres, classeQdoxBase, new DefaultJavaType(String.class.getCanonicalName()));
//							if(methodeApresClasse != null) {
//								o.tab(2);
//								if(!classe_.classeCopier && !classe_.classeCompleter)
//									o.tout("((", classe_.nomSimpleBase, ")this).");
//								o.toutLigne(nomMethodeApres, "(", var, ", \"", var, "\");");
//							}
//							else {
//								methodeApresClasse = classe_.obtenirMethode(nomMethodeApres, classeQdoxBase);
//								if(methodeApresClasse != null) {
//									o.tab(2);
//									if(!classe_.classeCopier && !classe_.classeCompleter)
//										o.tout("((", classe_.nomSimpleBase, ")this).");
//									o.toutLigne(nomMethodeApres, "(", var, ");");
//								}
//							}
//						}
//					}
//				}
//
//				o.toutLigne("\t\t", varComplet, classe.varCouvertureCapitalise, ".", classe.varDejaInitialise, "(true);");
//			}
//			o.toutLigne("\t}");
//		}
//	}
//
//	public void ecrirePageRequete(Chaine o, UneClasse classe) throws Exception {
//		if(initialise) {
//			o.tout("\t\t", varComplet, classe.varCouvertureCapitalise, ".", classe.varRequeteSite, "(", classe.varRequeteSite, ");");
//			if(!couverture && nomCanonique.enUS().startsWith(classe.nomEnsembleProjet.enUS()))
//				o.tout(" ", varComplet, ".", classe.varRequeteSite, nomSimple, "();");
//			o.toutLigne();
//		}
//	}
//
//	@Override public String toString() {
//		StringBuilder s = new StringBuilder();
//		for(Object o : nomCanonique.frFR) {
//			s(o);
//		}
//		s(" ");
//		for(Object o : var.frFR) {
//			s(o);
//		}
//		return s.toString();
//	}
//
//	@Override public boolean equals(Object obj) {
//		Boolean o = toString().equals(obj.toString());
//		return o;
//	}
//
//	@Override public int hashCode() {
//		int o = toString().hashCode();
//		return super.hashCode();
//	}
}
