package org.computate.enUS.java;

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
import org.computate.frFR.site.cours.c000.java.ChampJava;

/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public class WriteGenClass extends WriteGenClassGen<WriteClass> {

	/**	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 */
	protected void  writeGenClass(String classAbsolutePath, String languageName) throws Exception { 

		SolrQuery solrSearch = new SolrQuery();   
		solrSearch.setQuery("*:*");
		solrSearch.setRows(1000000);
		solrSearch.addFilterQuery("classAbsolutePath_indexed_string:" + ClientUtils.escapeQueryChars(classAbsolutePath));
		solrSearch.addFilterQuery("classExtendsGen_indexed_boolean:true");
		solrSearch.addSort("partNumber_indexed_int", ORDER.asc);

		QueryResponse searchResponse = solrClientComputate.query(solrSearch);
		writeGenClass(searchResponse, languageName);
	}

	/**	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 */
	protected void  writeGenClass(QueryResponse searchResponse, String languageName) throws Exception { 
		SolrDocumentList searchList = searchResponse.getResults();

		if(searchList.size() > 0 && (languageIndexed || !StringUtils.equals(languageName, this.languageName))) {    
			String classGenDirPath = null;
			String classPathGen = null; 
			File classDirGen = null;
			File classFileGen = null;
			StringBuilder s = new StringBuilder();
			
			String classSimpleNameGen = null;
			String classSuperSimpleName = null;    
			String classSuperSimpleNameGeneric = null;    
			String classPackageName = null;      
			String classSimpleName = null;
			String classSuperCanonicalName = null;    
			String classComment = null;      
			List<String> classImports = null;  
			List<String> classTypeParameterNames = null;  
			List<String> classSuperTypeParameterNames = null;  
			Boolean classExtendsGen = null;

			StringWriter wInitialiserLoin = null;
			PrintWriter codeInitialiserLoin = null;
			PrintWriter oAvant = null;
	
			for(int i = 0; i < searchList.size(); i++) {
				SolrDocument doc = searchList.get(i); 
				Integer partNumber = (Integer)doc.get("partNumber_stored_int");
				if(partNumber.equals(1)) {
					wInitialiserLoin = new StringWriter();
					codeInitialiserLoin = new PrintWriter(wInitialiserLoin);

					classGenDirPath = (String)doc.get("classGenDirPath_" + languageName + "_stored_string");
					classPathGen = (String)doc.get("classPathGen_" + languageName + "_stored_string"); 
					classDirGen = new File(classGenDirPath);
					classDirGen.mkdirs();
					classFileGen = new File(classPathGen);
					o = new PrintWriter(classFileGen);
					classSimpleName = (String)doc.get("classSimpleName_" + languageName + "_stored_string");
					classSimpleNameGen = (String)doc.get("classSimpleNameGen_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleName = (String)doc.get("classSuperSimpleName_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleNameGeneric = (String)doc.get("classSuperSimpleNameGeneric_" + languageName + "_stored_string");
					classPackageName = (String)doc.get("classPackageName_" + languageName + "_stored_string");
					classComment = (String)doc.get("classComment_" + languageName + "_stored_string");
					classImports = (List<String>)doc.get("classImports_" + languageName + "_stored_strings");
					classTypeParameterNames = (List<String>)doc.get("classTypeParameterNames_stored_strings");
					classSuperTypeParameterNames = (List<String>)doc.get("classSuperTypeParameterNames_stored_strings");
					classExtendsGen = (Boolean)doc.get("classExtendsGen_stored_boolean");
		
					l("package ", classPackageName, ";");
					l();
					if(classImports.size() > 0) { 
						for(String classImport : classImports) {
							l("import ", classImport, ";");
						} 
						l();  
					}
					writeComment(classComment, 0); 
					s("public abstract class ", classSimpleNameGen);
					if(classTypeParameterNames != null && classTypeParameterNames.size() > 0) {
						s("<");
						for(int j = 0; j < classTypeParameterNames.size(); j++) {
							String classTypeParameterName = classTypeParameterNames.get(j);
							if(i > 0)
								s(", ");
							s(classTypeParameterName);
						}
						s(">");
					}
					else {
						s("<DEV>");
					}
					if(classSuperSimpleNameGeneric != null && !"java.lang.Object".equals(classSuperSimpleNameGeneric) && !"DEV".equals(classSuperSimpleNameGeneric)) {
						s(" extends ");
//						s(classSuperSimpleName);
						
						if(classSuperSimpleNameGeneric != null) {
							s(classSuperSimpleNameGeneric);
						}
//						else if(classSuperTypeParameterNames != null && classSuperTypeParameterNames.size() > 0) {
////							s("<");
//							for(int j = 0; j < classSuperTypeParameterNames.size(); j++) {
//								String classSuperTypeParameterName = classSuperTypeParameterNames.get(j);
//								if(i > 0)
//									s(", ");
//								s(classSuperTypeParameterName);
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

							if(StringUtils.equals(languageName, classeValLangue)) {
								tl(1, "public static final String ", classeValVar, " = \"", StringEscapeUtils.escapeJava(classeValValeur), "\";");
							}
						}
					}
				} 
				else {
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partIsEntity = (Boolean)doc.get("partIsEntity_stored_boolean");
	
					if(BooleanUtils.isTrue(partIsEntity)) {
						String entiteVar = (String)doc.get("entiteVar_" + languageName + "_stored_string");
						String entiteVarCapitalise = (String)doc.get("entiteVarCapitalise_" + languageName + "_stored_string");
						String entiteNomCanonique = (String)doc.get("entiteNomCanonique_" + languageName + "_stored_string");
						String entiteNomCanoniqueGenerique = (String)doc.get("entiteNomCanoniqueGenerique_" + languageName + "_stored_string");
						String entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + languageName + "_stored_string");
						String entiteNomSimpleCompletGenerique = (String)doc.get("entiteNomSimpleCompletGenerique_" + languageName + "_stored_string");
						String entiteNomSimple = (String)doc.get("entiteNomSimple_" + languageName + "_stored_string");
						String entiteCommentaire = (String)doc.get("entiteCommentaire_" + languageName + "_stored_string");
						String entiteVarParam = (String)doc.get("entiteVarParam_" + languageName + "_stored_string");
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
							s(VAL_entityCommentLine1Part1, entiteVar, VAL_entityCommentLine1Part2);
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
							s(VAL_entityCommentLine1Part1, entiteVar, VAL_entityCommentLine1Part2);
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
						tl(1, "public ", classSimpleName, " ", entiteVar, "(", entiteNomSimpleComplet, " ", entiteVarParam, ") throws Exception {");
						tl(2, "set", entiteVarCapitalise, "(", entiteVarParam, ");");
						tl(2, "return (", classSimpleName, ")this;");
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
							tl(1, "public ", classSimpleName, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o)) {");
							tl(3, "Long l = Long.parseLong(o);");
							tl(3, entiteVar, "Ajouter(l);");
							tl(2, "}");
							tl(2, "return (", classSimpleName, ")this;");
							tl(1, "}");
						}
		
						// Setter Boolean //
						if(StringUtils.equals(entiteNomCanonique, Boolean.class.getCanonicalName())) {
							tl(1, "public ", classSimpleName, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))");
							tl(3, "this.", entiteVar, " = Boolean.parseBoolean(o);");
							tl(2, "return (", classSimpleName, ")this;");
							tl(1, "}");
						}
		
						// Setter Integer //
						if(StringUtils.equals(entiteNomCanonique, Integer.class.getCanonicalName())) {
							tl(1, "public ", classSimpleName, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o))");
							tl(3, "this.", entiteVar, " = Integer.parseInt(o);");
							tl(2, "return (", classSimpleName, ")this;");
							tl(1, "}");
						}
		
						// Setter Double //
						if(StringUtils.equals(entiteNomCanonique, Double.class.getCanonicalName())) {
							tl(1, "public ", classSimpleName, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o))");
							tl(3, "this.", entiteVar, " = Double.parseDouble(o);");
							tl(2, "return (", classSimpleName, ")this;");
							tl(1, "}");
						}
		
						// Setter Long //
						if(StringUtils.equals(entiteNomCanonique, Long.class.getCanonicalName())) {
							tl(1, "public ", classSimpleName, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o))");
							tl(3, "this.", entiteVar, " = Long.parseLong(o);");
							tl(2, "return (", classSimpleName, ")this;");
							tl(1, "}");
						}
		
						// Setter LocalDateTime //
						if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
							tl(1, "public ", classSimpleName, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "this.", entiteVar, " = java.time.LocalDateTime.parse(o, java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
							tl(2, "return (", classSimpleName, ")this;");
							tl(1, "}");
							tl(1, "public ", classSimpleName, " ", entiteVar, "(java.util.Date o) throws Exception {");
							tl(2, "this.", entiteVar, " = java.time.LocalDateTime.ofInstant(o.toInstant(), java.time.ZoneId.systemDefault());");
							tl(2, "return (", classSimpleName, ")this;");
							tl(1, "}");
						}
		
						// Ajouter //
						if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
							tl(1, "public ", classSimpleName, " ", entiteVar, "Ajouter(", entiteNomSimpleCompletGenerique, "...objets) throws Exception {");
							tl(2, "for(", entiteNomSimpleCompletGenerique, " o : objets) {");
							tl(3, "", entiteVar, "Ajouter(o);");
							tl(2, "}");
							tl(2, "return (", classSimpleName, ")this;");
							tl(1, "}");
							tl(1, "public ", classSimpleName, " ", entiteVar, "Ajouter(", entiteNomSimpleCompletGenerique, " o) throws Exception {");
							tl(2, "if(o != null && !", entiteVar, ".contains(o))");
							tl(3, "this.", entiteVar, ".add(o);");
							tl(2, "return (", classSimpleName, ")this;");
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
						l();
						tl(1, "protected boolean dejaInitialise = false;");
						tl(1, "public void initLoin(RequeteSite requeteSite) throws Exception {");
						if(contientRequeteSite && !StringUtils.equals(classSimpleName, "RequeteSite"))
							tl(2, "((", classSimpleName, ")this).requeteSite(requeteSite);");
						tl(2, "requeteSite", classSimpleName, "(requeteSite);");
						tl(2, "initLoin", classSimpleName, "();");
						tl(1, "}");
						tl(1, "public void initLoin", classSimpleName, "() throws Exception {");
						tl("\t\tif(!dejaInitialise", classSimpleName, ") {");
						if(StringUtils.startsWith(nomCanoniqueBase, nomEnsembleSite))
							tl(3, "super.initLoin", nomSimpleBase, "(requeteSite);");
						for(ChampJava champ : champs) {
							if(champ.initialise) {
								page_.tout("\t\t\t", champ.var).enUS("Initialize").frFR("Initialiser").toutLigne("();");
							}
						}
						page_.tout("\t\t\t").enUS("alreadyInitialized").frFR("dejaInitialise").toutLigne(nomSimple, " = true;");
						page_.toutLigne("\t\t}");
						page_.toutLigne("\t}");
					
						page_.toutLigne();
						page_.tab(1, "public void ").enUS("initializeDeepForClass").frFR("initialiserLoinPourClasse").tout("(").enUS("SiteRequest").frFR("RequeteSite").tout(" ").enUS("siteRequest").frFR("requeteSite").toutLigne(") throws Exception {");
						page_.tab(2).enUS("initializeDeep").frFR("initialiserLoin").tout(classSimpleName, "(").enUS("siteRequest").frFR("requeteSite").toutLigne(");");
						page_.tabLigne(1, "}");
						o = oAvant;
					}
				}
			}
			if(o != null) {
				if(searchList.size() > 0 && !StringUtils.equals(classAbsolutePath, classPathGen)) {

					codeInitialiserLoin.flush();
					codeInitialiserLoin.flush();
					s(wInitialiserLoin.toString());

					l("}"); 

					System.out.println("Write: " + classPathGen); 
					o.flush();
					o.close();
				}
			}
		} 
	}
}
