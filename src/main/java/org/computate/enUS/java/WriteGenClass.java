package org.computate.enUS.java;

import java.io.File;
import java.io.PrintWriter;
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
	
			for(int i = 0; i < searchList.size(); i++) {
				SolrDocument doc = searchList.get(i); 
				Integer partNumber = (Integer)doc.get("partNumber_stored_int");
				if(partNumber.equals(1)) {
					classGenDirPath = (String)doc.get("classGenDirPath_" + languageName + "_stored_string");
					classPathGen = (String)doc.get("classPathGen_" + languageName + "_stored_string"); 
					classDirGen = new File(classGenDirPath);
					classDirGen.mkdirs();
					classFileGen = new File(classPathGen);
					o = new PrintWriter(classFileGen);
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
					s("public class ", classSimpleNameGen);
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
					String entiteVar = (String)doc.get("entiteVar_" + languageName + "_stored_string");
					String entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + languageName + "_stored_string");
					String entiteCommentaire = (String)doc.get("entiteCommentaire_" + languageName + "_stored_string");
	
					if(BooleanUtils.isTrue(partIsEntity)) {
						s("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstPublic_stored_boolean")))
							s("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstProtege_stored_boolean")))
							s("protege ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstPrive_stored_boolean")))
							s("prive ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstStatique_stored_boolean")))
							s("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstFinale_stored_boolean")))
							s("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstAbstrait_stored_boolean")))
							s("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstNatif_stored_boolean")))
							s("native ");
						s(entiteNomSimpleComplet, " ", entiteVar);
						s(";\n");
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
						tl(1, " */");

//							page_.enUS("The \"", var, "\" attribute ");
//							page_.frFR("Le champ « ", var, " » ");
//						String[] lignes = commentaire.toString().split("\n");
//						for(int i = 0; i < lignes.length; i++) {
//							String ligne = lignes[i];
//							if(!StringUtils.isEmpty(ligne)) {
//								Boolean premier = i == 0;
//								Integer tabulations = StringUtils.countMatches(ligne, "\t");
//									if(!premier)
//										page_.tab(1 + tabulations, " *\t");
//									page_.tout(ligne.substring(tabulations));
//								page_.toutLigne();
//							}
//						}
//
//						if(requeteSite.frFR()) {
//
//							if(couverture) {
//								page_.tabLigne(1, " *\tIl est défini comme null avant d'être initialisé. ");
//								page_.tabLigne(1, " *\t@param c est pour envelopper une valeur à assigner à ce champ lors de l'initialisation. ");
//							}
//							else {
//								page_.tabLigne(1, " *\tIl est construit avant d'être initialisé avec le constructeur par défaut ", nomSimpleComplet, "(). ");
//								page_.tabLigne(1, " *\t@param ", paramVar, " est le champ déjà construit. ");
//							}
//
//							page_.tabLigne(1, " *\t@throws java.lang.Exception afin que toute exception lors de l'initialisation est gérée par le servlet. ");
//						}
//						else {
//
//							if(couverture) {
//								page_.tabLigne(1, " *\tIt is set to null before it is initialized. ");
//								page_.tabLigne(1, " *\t@param c is for wrapping a value to be assigned to this field during initialization. ");
//							}
//							else {
//								page_.tabLigne(1, " *\tIt is constructed before it is initialized with the default constructor ", nomSimpleComplet, "(). ");
//								page_.tabLigne(1, " *\t@param ", paramVar, " is the field already constructed. ");
//							}
//
//							page_.tabLigne(1, " *\t@throws java.lang.Exception so that any exception during initialization is handled by the servlet. ");
//						}
//
//						page_.tabLigne(1, " */");
					}     
				}
			}
			l("}");
			if(searchList.size() > 0 && !StringUtils.equals(classAbsolutePath, classPathGen)) {
				System.out.println("Write: " + classPathGen); 
				o.flush();
				o.close();
			}
		} 
	}
}
