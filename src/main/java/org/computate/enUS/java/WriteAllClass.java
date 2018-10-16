package org.computate.enUS.java;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public class WriteAllClass extends WriteAllClassGen<WriteGenClass> {

	/**	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 */
	protected void  writeApiClass(String classAbsolutePath, String languageName) throws Exception { 

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
	protected void  writeApiClass(QueryResponse searchResponse, String languageName) throws Exception { 
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
			String classSuperCanonicalNameGeneric = null;    
			String classPackageName = null;      
			String classSimpleName = null;
			String classSuperCanonicalName = null;    
			String classComment = null;      
			List<String> classImportsGen = null;  
			List<String> classTypeParameterNames = null;  
			List<String> classSuperTypeParameterNames = null;  
			Boolean classExtendsGen = null;
			Boolean classeBaseEtendGen = null;
			Boolean classeContientRequeteSite = null;
			Boolean classeIndexe = null;
			Boolean classeEtendBase = null;
			Boolean classeEstBase = null;
			Boolean classeSauvegarde = null;

			StringWriter wInitialiserLoin = null;
			PrintWriter codeInitialiserLoin = null;

			StringWriter wIndexer = null;
			PrintWriter codeIndexer = null;

			StringWriter wObtenir = null;
			PrintWriter codeObtenir = null;

			StringWriter wAttribuer = null;
			PrintWriter codeAttribuer = null;

			StringWriter wDefinir = null;
			PrintWriter codeDefinir = null;

			StringWriter wPeupler = null;
			PrintWriter codePeupler = null;

			StringWriter wExiste = null;
			PrintWriter codeExiste = null;

			StringWriter wSauvegardes = null;
			PrintWriter codeSauvegardes = null;

			StringWriter wSauvegarder = null;
			PrintWriter codeSauvegarder = null;

			PrintWriter oAvant = null;

			String entiteVarCleUnique = null;
	
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
					classSimpleName = (String)doc.get("classSimpleName_" + languageName + "_stored_string");
					classSimpleNameGen = (String)doc.get("classSimpleNameGen_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleName = (String)doc.get("classSuperSimpleName_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleNameGeneric = (String)doc.get("classSuperSimpleNameGeneric_" + languageName + "_stored_string");
					classSuperCanonicalNameGeneric = (String)doc.get("classSuperCanonicalNameGeneric_" + languageName + "_stored_string");
					classPackageName = (String)doc.get("classPackageName_" + languageName + "_stored_string");
					classComment = (String)doc.get("classComment_" + languageName + "_stored_string");
					classImportsGen = (List<String>)doc.get("classImportsGen_" + languageName + "_stored_strings");
					if(classImportsGen == null)
						classImportsGen = new ArrayList<String>();
					classTypeParameterNames = (List<String>)doc.get("classTypeParameterNames_stored_strings");
					classSuperTypeParameterNames = (List<String>)doc.get("classSuperTypeParameterNames_stored_strings");
					classExtendsGen = (Boolean)doc.get("classExtendsGen_stored_boolean");
					classeEtendBase = classExtendsGen;
					classeBaseEtendGen = (Boolean)doc.get("classeBaseEtendGen_stored_boolean");
					classeEstBase = !BooleanUtils.isNotTrue(classeBaseEtendGen);
					classeContientRequeteSite = (Boolean)doc.get("classeContientRequeteSite_stored_boolean");
					classeSauvegarde = BooleanUtils.isTrue((Boolean)doc.get("classeSauvegarde_stored_boolean"));
					classeIndexe = BooleanUtils.isTrue((Boolean)doc.get("classeIndexe_stored_boolean"));

					/////////////////////////
					// codeInitialiserLoin //
					/////////////////////////
					oAvant = o;
					wInitialiserLoin = new StringWriter();
					codeInitialiserLoin = new PrintWriter(wInitialiserLoin);
					o = codeInitialiserLoin;
					l(); 
					tl(1, "protected boolean dejaInitialise", classSimpleName, " = false;");
					if(BooleanUtils.isTrue(classeContientRequeteSite)) {
						l();
						tl(1, "public void initLoin", classSimpleName, "(RequeteSite requeteSite) throws Exception {");
//						if(contientRequeteSite && !StringUtils.equals(classSimpleName, "RequeteSite"))
//							tl(2, "((", classSimpleName, ")this).setRequeteSite(requeteSite);");
						tl(2, "setRequeteSite(requeteSite);");
						tl(2, "initLoin", classSimpleName, "();");
						tl(1, "}");
					}
					l();
					tl(1, "public void initLoin", classSimpleName, "() throws Exception {");
					tl(2, "if(!dejaInitialise", classSimpleName, ") {");
					if(BooleanUtils.isTrue(classeBaseEtendGen)) 
						tl(3, "super.initLoin", classSuperSimpleNameGeneric, "(requeteSite);");
					o = oAvant;

					/////////////////
					// codeIndexer //
					/////////////////
					oAvant = o;
					wIndexer = new StringWriter();
					codeIndexer = new PrintWriter(wIndexer);
					o = codeIndexer;
					if(classeIndexe) {
						tl(0);
						tl(1, "@Test public void indexer", classSimpleName, "Test() throws Exception {");
						tl(2, "RequeteSite requeteSite = new RequeteSite();");
						tl(2, "requeteSite.initLoinRequeteSite();");
						tl(2, "EcouteurContexte ecouteurContexte = new EcouteurContexte();");
						tl(2, "ecouteurContexte.initLoinEcouteurContexte();");
						tl(2, "ecouteurContexte.requeteSite(requeteSite);");
						tl(2, "requeteSite.ecouteurContexte(ecouteurContexte);");
						tl(2, "requeteSite.configSite(ecouteurContexte.configSite);");
						tl(2, "requeteSite", classSimpleName, "(requeteSite);");
						tl(2, "initLoin", classSimpleName, "(requeteSite);");
						tl(2, "indexer", classSimpleName, "(requeteSite);");
						tl(1, "}");
						tl(0);
						if(classeEtendBase) {
							tl(0);
							t(1);
							if(!classeEstBase)
								s("@Override ");
							s("public void indexerPourClasse(RequeteSite requeteSite) throws Exception {");
							tl(2, "indexer", classSimpleName, "(requeteSite);");
							tl(1, "}");
							tl(0);
							t(1);
							if(!classeEstBase)
								s("@Override ");
							s("public void indexerPourClasse(SolrInputDocument document) throws Exception {");
							tl(2, "indexer", classSimpleName, "(document);");
							tl(1, "}");
						}
						tl(1, "public void indexer", classSimpleName, "(RequeteSite requeteSite) throws Exception {");
						tl(2, "SolrInputDocument document = new SolrInputDocument();");
						tl(2, "indexer", classSimpleName, "(document);");
						if(classeSauvegarde)
							tl(2, "document.addField(\"sauvegardes", classSimpleName, "_stored_strings\", sauvegardes);");
						tl(2, "SolrClient clientSolr = requeteSite.ecouteurContexte.clientSolr;");
						tl(2, "clientSolr.add(document);");
						tl(2, "clientSolr.commit();");
						l("\t}");

						tl(0);
						tl(1, "public void indexer", classSimpleName, "(SolrInputDocument document) throws Exception {");
					}
					o = oAvant;

					/////////////////
					// codeObtenir //
					/////////////////
					oAvant = o;
					wObtenir = new StringWriter();
					codeObtenir = new PrintWriter(wObtenir);
					o = codeObtenir;
					if(classeEtendBase) {
						tl(0);
						t(1);
						if(!classeEstBase)
							s("@Override ");
						s("public Object obtenirPourClasse(String var) throws Exception {");
						tl(2, "String[] vars = org.apache.commons.lang3.StringUtils.split(var, \".\");");
						tl(2, "Object o = null;");
						tl(2, "for(String v : vars) {");
						tl(3, "if(o == null)");
						tl(4, "o = obtenir", classSimpleName, "(v);");
						tl(3, "else if(o instanceof Cluster) {");
						tl(4, "Cluster cluster = (Cluster)o;");
						tl(4, "o = cluster.obtenirPourClasse(v);");
						tl(3, "}");
						tl(2, "}");
						tl(2, "return o;");
						tl(1, "}");
						tl(1, "public Object obtenir", classSimpleName, "(String var) throws Exception {");
						tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
						tl(2, "switch(var) {");
					}
					o = oAvant;

					///////////////////
					// codeAttribuer //
					///////////////////
					oAvant = o;
					wAttribuer = new StringWriter();
					codeAttribuer = new PrintWriter(wAttribuer);
					o = codeAttribuer;
					if(classeEtendBase) {
						tl(0);
						t(1);
						if(!classeEstBase)
							s("@Override ");
						s("public boolean attribuerPourClasse(String var, Object val) throws Exception {");
						tl(2, "String[] vars = org.apache.commons.lang3.StringUtils.split(var, \".\");");
						tl(2, "Object o = null;");
						tl(2, "for(String v : vars) {");
						tl(3, "if(o == null)");
						tl(4, "o = attribuer", classSimpleName + "(v, val);");
						tl(3, "else if(o instanceof Cluster) {");
						tl(4, "Cluster cluster = (Cluster)o;");
						tl(4, "o = cluster.attribuerPourClasse(v, val);");
						tl(3, "}");
						tl(2, "}");
						tl(2, "return o != null;");
						tl(1, "}");
						tl(1, "public Object attribuer", classSimpleName, "(String var, Object val) throws Exception {");
						t(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
						tl(2, "switch(var) {");

					}
					o = oAvant;

					/////////////////
					// codeDefinir //
					/////////////////
					oAvant = o;
					wDefinir = new StringWriter();
					codeDefinir = new PrintWriter(wDefinir);
					o = codeDefinir;
					if(classeEtendBase) {
						tl(0);
						t(1);
						if(!classeEstBase)
							s("@Override ");
						s("public boolean definirPourClasse(String var, String...vals) throws Exception {");
						tl(2, "String[] vars = org.apache.commons.lang3.StringUtils.split(var, \".\");");
						tl(2, "Object o = null;");
						tl(2, "String val = vals == null ? null : vals[vals.length - 1];");
						tl(2, "if(val != null) {");
						tl(3, "for(String v : vars) {");
						tl(4, "if(o == null)");
						tl(5, "o = definir", classSimpleName + "(v, val);");
						tl(4, "else if(o instanceof Cluster) {");
						tl(5, "Cluster cluster = (Cluster)o;");
						tl(5, "o = cluster.definirPourClasse(v, val);");
						tl(4, "}");
						tl(3, "}");
						tl(2, "}");
						tl(2, "return o != null;");
						tl(1, "}");
						tl(1, "public Object definir", classSimpleName, "(String var, String val) throws Exception {");
						tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
						tl(2, "switch(var) {");
					}
					o = oAvant;

					/////////////////
					// codePeupler //
					/////////////////
					oAvant = o;
					wPeupler = new StringWriter();
					codePeupler = new PrintWriter(wPeupler);
					o = codePeupler;
					if(classeSauvegarde) {
						tl(0);
						t(1);
						if(!classSimpleName.equals("Cluster"))
							s("@Override ");
						s("public void peuplerPourClasse(", SolrDocument.class.getCanonicalName(), " documentSolr) throws Exception {");
						if(classeSauvegarde) {
							tl(2, "sauvegardes", classSimpleName, " = (java.util.ArrayList<String>)documentSolr.get(\"sauvegardes", classSimpleName, "_stored_strings\");");
						tl(2, "peupler", classSimpleName, "(documentSolr);");
						}
						tl(1, "}");
						tl(1, "public void peupler", classSimpleName, "(", SolrDocument.class.getCanonicalName(), " documentSolr) throws Exception {");
						tl(2, classSimpleName, " o", classSimpleName, " = (", classSimpleName, ")this;");
					}
					o = oAvant;

					////////////////
					// codeExiste //
					////////////////
					oAvant = o;
					wExiste = new StringWriter();
					codeExiste = new PrintWriter(wExiste);
					o = codeExiste;
					if(classeSauvegarde) {
						tl(0);
						t(1);
						if(!classSimpleName.equals("Cluster"))
							s("@Override ");
						s("public Boolean existePourClasse() throws Exception {");
						tl(2, "String cleStr = requeteSite.requete.getParameter(\"cle\");");
						tl(2, "Long cle = ", StringUtils.class.getCanonicalName(), ".isNumeric(cleStr) ? Long.parseLong(cleStr) : null;");
						tl(2, "Boolean existe = existePourClasse(cle);");
						tl(2, "return existe;");
						tl(1, "}");
						t(1);
						if(!classSimpleName.equals("Cluster"))
							s("@Override ");
						s("public Boolean existePourClasse(Long cle) throws Exception {");
						tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.ecouteurContexte.sourceDonnees);");
						tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");
						tl(2, "utilisateurId = requeteSite.utilisateurId;");
						tl(2, "this.cle = cle;");
						tl(2, "String nomCanonique = getClass().getCanonicalName();");
						tl(2, "Boolean existe = false;");
						tl(2);
						tl(2, "if(cle == null) {");
						tl(3, "String sql = \"select clep from objet where objet.id_utilisateur=? and objet.nom_canonique=?\";");
						tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*select count(*) from objet where objet.id_utilisateur=*/, requeteSite.utilisateurId /* and objet.nom_canonique=*/, nomCanonique);");
						tl(3, "existe = resultats.size() > 0;");
						tl(3, "if(existe) {");
						tl(4, "cle = (Long)resultats.get(0)[0];");
						tl(4, "cle(cle);");
						tl(3, "}");
						tl(2, "}");
						tl(2, "else {");
						tl(3, "String sql = \"select count(*) from objet where objet.clep=? and objet.id_utilisateur=? and objet.nom_canonique=?\";");
						tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*select count(*) from objet where objet.clep=*/, cle /* and objet.id_utilisateur=*/, requeteSite.utilisateurId /* and objet.nom_canonique=*/, nomCanonique);");
						tl(3, "existe = ((Long)resultats.get(0)[0]) > 0L;");

						tl(2, "}");
						tl(2, "return existe;");
						tl(1, "}");
					}
					o = oAvant;

					/////////////////////
					// codeSauvegardes //
					/////////////////////
					oAvant = o;
					wSauvegardes = new StringWriter();
					codeSauvegardes = new PrintWriter(wSauvegardes);
					o = codeSauvegardes;
					if(classeSauvegarde) {
						tl(0);
						tl(1, "protected java.util.ArrayList<String> sauvegardes", classSimpleName, " = new java.util.ArrayList<String>();");
						t(1);
						if(!classSimpleName.equals("Cluster"))
							s("@Override ");
						s("public void sauvegardesPourClasse(RequeteSite requeteSite) throws Exception {");
						tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.ecouteurContexte.sourceDonnees);");
						tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");

						tl(2);
						tl(2, "if(cle != null) {");
						tl(3, "String sql = \"select cree, modifie from objet where objet.clep=?\";");
						tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*select cree, modifie from objet where objet.clep=*/, cle);");
						tl(3, "if(resultats.size() > 0) {");
						tl(4, "cree((java.util.Date)resultats.get(0)[0]);");
						tl(4, "modifie((java.util.Date)resultats.get(0)[1]);");
						tl(3, "}");

						t(3, "sql = \"select chemin, valeur from p where p.cle_objet=? ");
						s("union select champ2, cle2::text from a where a.cle1=? ");
						s("union select champ1, cle1::text from a where a.cle2=? ");
						l("\";");
						tl(3, "resultats = coureur.query(sql, gestionnaireListe /*select chemin, valeur from p where p.cle_objet=*/, cle, cle, cle);");
						tl(3, "for(Object[] objets : resultats) {");
						tl(4, "String chemin = (String)objets[0];");
						tl(4, "String valeur = requeteSite.decrypterStr((String)objets[1]);");
						tl(4, "definirPourClasse(chemin, valeur);");
						tl(4, "sauvegardes", classSimpleName, ".add(chemin);");
						tl(3, "}");
						tl(2, "}");
						tl(1, "}");
					}
					o = oAvant;

					/////////////////////
					// codeSauvegarder //
					/////////////////////
					oAvant = o;
					wSauvegarder = new StringWriter();
					codeSauvegarder = new PrintWriter(wSauvegarder);
					o = codeSauvegarder;
					if(classeSauvegarde) {
						tl(0);
						t(1);
						if(!classSimpleName.equals("Cluster"))
							s("@Override ");
						s("public void sauvegarderPourClasse(RequeteSite requeteSite) throws Exception {");
						tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.ecouteurContexte.sourceDonnees);");
						tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");
						tl(2, "String cleStr = requeteSite.requete.getParameter(\"cle\");");
						tl(2, "cle = ", StringUtils.class.getCanonicalName(), ".isNumeric(cleStr) ? Long.parseLong(cleStr) : null;");
						tl(2, "utilisateurId = requeteSite.utilisateurId;");
						tl(2, "String nomCanonique = getClass().getCanonicalName();");
						tl(2, "modifie = ", LocalDateTime.class.getCanonicalName(), ".now();");
						tl(2, Timestamp.class.getCanonicalName(), " horodatage = java.sql.Timestamp.valueOf(modifie);");
			
						tl(2);
						tl(2, "if(cle == null) {");
						tl(3, "String sql = \"insert into objet(nom_canonique, id_utilisateur, cree, modifie) values(?, ?, ?, ?) returning clep\";");
						tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.insert(sql, gestionnaireListe /*insert into objet(nom_canonique, id_utilisateur, cree, modifie) values(*/, nomCanonique, requeteSite.utilisateurId, horodatage, horodatage /*) returning clep, cree*/);");
						tl(3, "cle = (Long)resultats.get(0)[0];");
						tl(3, "cree = modifie;");
						tl(2, "}");
						tl(2, "else {");
						tl(3, "String sql = \"update objet set modifie=? where objet.clep=? and objet.id_utilisateur=? and objet.nom_canonique=? returning cree\";");
						tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*update objet set modifie=*/, horodatage /* where objet.clep=*/, cle /* and objet.id_utilisateur=*/, requeteSite.utilisateurId /* and objet.nom_canonique=*/, nomCanonique /* returning cree*/);");
						tl(3, "if(resultats.size() == 0)");
						t(4, "throw new Exception(\"");
						s("L'objet avec le cle \" + cle + \" et nom canonique \" + cle + \" pour utilisateur \" + requeteSite.utilisateurId + \" \" + requeteSite.utilisateurNom + \" n'existe pas dejà. ");
						l("\");");
						tl(3, "horodatage = (java.sql.Timestamp)resultats.get(0)[0];");
						tl(3, "cree = ", LocalDateTime.class.getCanonicalName(), ".from(horodatage.toLocalDateTime());");
						tl(2, "}");
//						tl(0);
//						tl(2, "{");
//						tl(3, "String sqlSelectP = \"select chemin, valeur from p where p.cle_objet=?\";");
//						tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sqlSelectP, gestionnaireListe /*select chemin, valeur from p where p.cle_objet=*/, cle);");
//						tl(3, "for(Object[] objets : resultats) {");
//						tl(4, "String chemin = (String)objets[0];");
//						if(coursCrypte)
//							tl(4, "String valeur = requeteSite.decrypterStr((String)objets[1]);");
//						else
//							tl(4, "String valeur = (String)objets[1];");
//						tl(4, "definir(chemin, valeur);");
//						tl(4, "sauvegardes", classSimpleName, ".add(chemin);");
//						tl(3, "}");
//						tl(2, "}");
						tl(0);
//						tl(2, "{");
						tl(2, "String sqlInsertP = \"insert into p(chemin, valeur, cle_objet) values(?, ?, ?) on conflict(chemin, cle_objet) do update set valeur=? where p.chemin=? and p.cle_objet=?\";");
						tl(2, "String sqlInsertA = \"insert into a(champ1, cle1, champ2, cle2) values(?, ?, ?, ?) on conflict  do nothing\";");
						tl(2, "String sqlDeleteP = \"delete from p where chemin=? and cle_objet=?\";");
						tl(2, "String sqlDeleteA = \"delete from a where champ1=? and cle1=? and champ2=? and cle2=?\";");
						tl(2, "sauvegarder", classSimpleName, "(requeteSite, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
//						tl(2, "}");
						tl(1, "}");
						tl(1, "public void sauvegarder", classSimpleName, "(RequeteSite requeteSite, String sqlInsertP, String sqlInsertA, String sqlDeleteP, String sqlDeleteA, ", ArrayListHandler.class.getCanonicalName(), " gestionnaireListe, ", QueryRunner.class.getCanonicalName(), " coureur) throws Exception {");
					}
					o = oAvant;

					////////////
					// classe //
					////////////
		
					l("package ", classPackageName, ";");
					l();
					if(classImportsGen.size() > 0) { 
						for(String classImport : classImportsGen) {
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
								s();
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
//									s();
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

						String entiteVarCleUniqueActuel = (String)doc.get("entiteVarCleUnique_stored_boolean");
						if(StringUtils.isNotEmpty(entiteVarCleUniqueActuel))
							entiteVarCleUnique = entiteVarCleUniqueActuel;
						String entiteVarSuggere = (String)doc.get("entiteVarSuggere_stored_boolean");
						String entiteVarIncremente = (String)doc.get("entiteVarIncremente_stored_boolean");
						String entiteVarCrypte = (String)doc.get("entiteVarCrypte_stored_boolean");
						String entiteVarIndexe = (String)doc.get("entiteVarIndexe_stored_boolean");
						String entiteVarStocke = (String)doc.get("entiteVarStocke_stored_boolean");
						String entiteTypeSolr = (String)doc.get("entiteTypeSolr_stored_boolean");

						Boolean entiteExact = (Boolean)doc.get("entiteExact_stored_boolean");
						Boolean entiteCleUnique = (Boolean)doc.get("entiteCleUnique_stored_boolean");
						Boolean entiteCrypte = (Boolean)doc.get("entiteCrypte_stored_boolean");
						Boolean entiteSuggere = (Boolean)doc.get("entiteSuggere_stored_boolean");
						Boolean entiteSauvegarde = (Boolean)doc.get("entiteSauvegarde_stored_boolean");
						Boolean entiteIndexe = (Boolean)doc.get("entiteIndexe_stored_boolean");
						Boolean entiteStocke = (Boolean)doc.get("entiteStocke_stored_boolean");
						Boolean entitetexte = (Boolean)doc.get("entitetexte_stored_boolean");
						Boolean entiteIncremente = (Boolean)doc.get("entiteIncremente_stored_boolean");
						Boolean entiteNomAffichage = (Boolean)doc.get("entiteNomAffichage_stored_boolean");
						Boolean entiteIgnorer = (Boolean)doc.get("entiteIgnorer_stored_boolean");
						Boolean entiteDeclarer = (Boolean)doc.get("entiteDeclarer_stored_boolean");
						Boolean entiteRechercher = (Boolean)doc.get("entiteRechercher_stored_boolean");
						Boolean entiteAttribuer = (Boolean)doc.get("entiteAttribuer_stored_boolean");
						Boolean entiteAjouter = (Boolean)doc.get("entiteAjouter_stored_boolean");
						Boolean entiteSupprimer = (Boolean)doc.get("entiteSupprimer_stored_boolean");
						Boolean entiteModifier = (Boolean)doc.get("entiteModifier_stored_boolean");
						Boolean entiteRecharger = (Boolean)doc.get("entiteRecharger_stored_boolean");
						Boolean entiteMultiligne = (Boolean)doc.get("entiteMultiligne_stored_boolean");
						Boolean entiteCles = (Boolean)doc.get("entiteCles_stored_boolean");
						Boolean entiteIndexeOuStocke = (Boolean)doc.get("entiteIndexeOuStocke_stored_boolean");

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

//						l();
//						tl(1, "public ", classSimpleName, " ", entiteVar, "(", entiteNomSimpleComplet, " ", entiteVarParam, ") throws Exception {");
//						tl(2, "set", entiteVarCapitalise, "(", entiteVarParam, ");");
//						tl(2, "return (", classSimpleName, ")this;");
//						tl(1, "}");

						l();
						tl(1, "public void set", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVarParam, ") throws Exception {");
						tl(2, "this.", entiteVar, " = ", entiteVarParam, ";");
						tl(1, "}");
//
//						l();
//						tl(1, "public ", entiteNomSimpleComplet, " ", entiteVar, "() throws Exception {");
//						tl(2, "return get", entiteVarCapitalise, "();");
//						tl(1, "}");

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
		
						// Setter BigDecimal //
						if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
							tl(1, "public ", classSimpleName, " ", entiteVar, "(String o) throws Exception {");
							tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isNumber(o))");
							tl(3, "this.", entiteVar, " = new BigDecimal(o);");
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
								tl(4, "set", entiteVarCapitalise, "(", entiteVar, "Couverture.o);");
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

						///////////////////////////
						// codeIninitLoin //
						///////////////////////////
						oAvant = o;
						o = codeInitialiserLoin;
						if(entiteInitialise) {
							tl(3, entiteVar, "Init();");
						}
						o = oAvant;


						/////////////////
						// codeIndexer //
						/////////////////
						oAvant = o;
						o = codeIndexer;
						if(entiteIndexeOuStocke) {
							tl(2, "if(", entiteVar, " != null) {");
							if(StringUtils.isNotEmpty(entiteVarCleUniqueActuel) && entiteCleUnique) {
								// cleUnique
								tl(3, "document.addField(\"", entiteVarCleUniqueActuel, "\"", entiteVar, ");");
							}
							if(StringUtils.isNotEmpty(entiteVarCrypte) && entiteCrypte) {
								// crypte
								tl(3, "String valCrypte = requeteSite.crypterStr(", entiteVar, ");");
								tl(3, "document.addField(\"", entiteVarCrypte, "\"", "valCrypte);");
							}
							if(StringUtils.isNotEmpty(entiteVarIncremente) && entiteIncremente) {
								// crypte
								tl(3, "document.addField(\"", entiteVarIncremente, "\", new java.util.HashMap<String, ", entiteNomSimple, ">() {{ put(\"inc\"", ("Long".equals(entiteNomSimple.toString()) ? "1L" : "1"), "); }});");
							}
							if(StringUtils.isNotEmpty(entiteVarSuggere) && entiteSuggere) {
								// suggere
								if(entiteNomSimple.equals("Chaine")) {
									tl(3, "document.addField(\"", entiteVarSuggere, "\"", entiteVar, ");");
								}
								else if(entiteNomSimple.equals("Timestamp") || entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName()) || entiteNomSimple.toString().equals("LocalDate")) {
									tl(3, "document.addField(\"", entiteVarSuggere, "\", java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ", java.time.OffsetDateTime.now().getOffset(), java.time.ZoneId.of(\"UTC\"))));");
								}
								else {
									tl(3, "document.addField(\"", entiteVarSuggere, "\"", entiteVar, ");");
								}
							}

							if(StringUtils.isNotEmpty(entiteVarIndexe) && entiteIndexe) {
								// indexe
								if(entiteNomSimple.equals("Chaine")) {
									tl(3, "document.addField(\"", entiteVarIndexe, "\"", entiteVar, ");");
								}
								else if(entiteNomSimple.equals("Timestamp") || entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName()) || entiteNomSimple.toString().equals("LocalDate")) {
									tl(3, "document.addField(\"", entiteVarIndexe, "\", java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ", java.time.OffsetDateTime.now().getOffset(), java.time.ZoneId.of(\"UTC\"))));");
								}
								else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList")) {
									tl(3, "for(", entiteNomCanoniqueGenerique, " o : ", entiteVar, ") {");
									tl(4, "document.addField(\"", entiteVarIndexe, "\", o);");
									tl(3, "}");
								}
								else {
									tl(3, "document.addField(\"", entiteVarIndexe, "\"", entiteVar, ");");
								}
							}
							else {
								if(StringUtils.isNotEmpty(entiteVarIndexe)) {
									tl(3, "document.addField(\"", entiteVarIndexe, "\"", entiteVar, ");");
								}
							}

							if(StringUtils.isNotEmpty(entiteVarStocke) && entiteStocke) {
								// stocke
								if(entiteNomSimple.equals("Chaine")) {
									tl(3, "document.addField(\"", entiteVarStocke, "\"", entiteVar, ");");
								}
								else if(entiteNomSimple.equals("Timestamp") || entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName()) || entiteNomSimple.toString().equals("LocalDate")) {
									tl(3, "document.addField(\"", entiteVarStocke, "\", java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ", java.time.OffsetDateTime.now().getOffset(), java.time.ZoneId.of(\"UTC\"))));");
								}
								else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList")) {
									tl(3, "for(", entiteNomCanoniqueGenerique, " o : ", entiteVar, ") {");
									tl(4, "document.addField(\"", entiteVarStocke, "\", o);");
									tl(3, "}");
								}
								else {
									tl(3, "document.addField(\"", entiteVarStocke, "\"", entiteVar, ");");
								}
							}
							tl(2, "}");
						}
						o = oAvant;
	
						/////////////////
						// codeObtenir //
						/////////////////
						oAvant = o;
						wObtenir = new StringWriter();
						codeObtenir = new PrintWriter(wObtenir);
						o = codeObtenir;
						if(classeEtendBase) {
							tl(2, "case \"", entiteVar, "\": return o", classSimpleName, ".", entiteVar, ";");
						}	
						o = oAvant;
	
						///////////////////
						// codeAttribuer //
						///////////////////
						oAvant = o;
						wAttribuer = new StringWriter();
						codeAttribuer = new PrintWriter(wAttribuer);
						o = codeAttribuer;
						if(classeEtendBase) {
							tl(2, "case \"", entiteVar, "\": o", classSimpleName, ".", entiteVar, "((", entiteNomSimpleComplet, ")val); return val;");
						}	
						o = oAvant;
	
						/////////////////
						// codeDefinir //
						/////////////////
						oAvant = o;
						wDefinir = new StringWriter();
						codeDefinir = new PrintWriter(wDefinir);
						o = codeDefinir;
						if(classeEtendBase) {
//							if(champ.contientSetterString) {
//							if(entiteContientSetterString) {
								tl(2, "case \"", entiteVar, "\":");
								tl(3, "o", classSimpleName, ".", entiteVarCapitalise, "(val);");
								tl(3, "return val;");
//							}
						}	
						o = oAvant;
	
						/////////////////
						// codePeupler //
						/////////////////
						oAvant = o;
						wPeupler = new StringWriter();
						codePeupler = new PrintWriter(wPeupler);
						o = codePeupler;
						if(classeSauvegarde) {
//							String nomChamp = entiteVar.toString();
//							String varCrypte = entiteVarCrypte.toString();
//							String varStocke = entiteVarStocke.toString();
//							String varSuggere = entiteVarSuggere.toString();
//							String varIncremente = entiteVarIncremente.toString();
//							String varCleUnique = entiteVarCleUniqueActuel.toString();
							if(!StringUtils.isEmpty(entiteVarCrypte) || !StringUtils.isEmpty(entiteVarStocke) || !StringUtils.isEmpty(entiteVarCleUniqueActuel) || !StringUtils.isEmpty(entiteVarSuggere) || !StringUtils.isEmpty(entiteVarIncremente)) {
								tl(0);

								if(!StringUtils.isEmpty(entiteVarSuggere)) {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entiteVar, "\")) {");
									tl(4, entiteTypeSolr, " ", entiteVar, " = (", entiteTypeSolr, ")documentSolr.get(\"", entiteVarSuggere, "\");");
									tl(4, "o", classSimpleName, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
								}
								else if(!StringUtils.isEmpty(entiteVarIncremente)) {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entiteVar, "\")) {");
									tl(4, entiteTypeSolr, " ", entiteVar, " = (", entiteTypeSolr, ")documentSolr.get(\"", entiteVarIncremente, "\");");
									tl(4, "o", classSimpleName, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
								}
								else if(!StringUtils.isEmpty(entiteVarCleUniqueActuel)) {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entiteVar, "\")) {");
									tl(4, entiteTypeSolr, " ", entiteVar, " = org.apache.commons.lang3.math.NumberUtils.toLong((String)documentSolr.get(\"", entiteVarCleUniqueActuel, "\"));");
									tl(4, "o", classSimpleName, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
								}
								else if(!StringUtils.isEmpty(entiteVarCrypte)) {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entiteVar, "\")) {");
									if(siteCrypte)
										tl(4, entiteTypeSolr, " ", entiteVar, " = requeteSite.decrypterStr((", entiteTypeSolr, ")documentSolr.get(\"", entiteVarCrypte, "\"));");
									else
										tl(4, entiteTypeSolr, " ", entiteVar, " = (", entiteTypeSolr, ")documentSolr.get(\"", entiteVarCrypte, "\");");
									tl(4, "o", classSimpleName, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
								}
								else {
//									tl(3, "if(sauvegardes", classSimpleName, ".contains(\"", entiteVar, "\")) {");
									tl(4, entiteTypeSolr, " ", entiteVar, " = (", entiteTypeSolr, ")documentSolr.get(\"", entiteVarStocke, "\");");
									tl(4, "if(", entiteVar, " != null)");
									if(StringUtils.contains(entiteTypeSolr, "<"))
										tl(5, "o", classSimpleName, ".", entiteVar, ".addAll(", entiteVar, ");");
									else
										tl(5, "o", classSimpleName, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
								}

							}
						}	
						o = oAvant;
	
						/////////////////////
						// codeSauvegarder //
						/////////////////////
						oAvant = o;
						wSauvegarder = new StringWriter();
						codeSauvegarder = new PrintWriter(wSauvegarder);
						o = codeSauvegarder;
						if(classeSauvegarde) {
								String nomChamp = entiteVar.toString();
								if(entiteSauvegarde) {
									tl(0);
	
									tl(2, "if(\"true\".equals(requeteSite.requete.getParameter(\"", nomChamp, "Supprimer\"))) {");
									tl(3, "coureur.update(sqlDeleteP /*delete from p where chemin=*/, \"", nomChamp, "\" /* and cle_objet=*/, cle);");
									tl(2, "} else if(definirPourClasse(\"", nomChamp, "\"", "requeteSite.requete.getParameterValues(\"", nomChamp, "\"))) {");
									if(siteCrypte) {
										tl(3, "String valCrypte = requeteSite.crypterStr(", nomChamp, ");");
										tl(3, "coureur.insert(sqlInsertP, gestionnaireListe /*insert into p(chemin, valeur, cle_objet) values(*/, \"", nomChamp, "\"", "valCrypte, cle /*) on conflict(chemin, cle_objet) do update set valeur=*/, valCrypte /* where p.chemin=*/, \"", nomChamp, "\" /* and p.cle_objet=*/, cle);");
									}
									else {
										tl(3, "coureur.insert(sqlInsertP, gestionnaireListe /*insert into p(chemin, valeur, cle_objet) values(*/, \"", nomChamp, "\"", nomChamp, ", ", "cle /*) on conflict(chemin, cle_objet) do update set valeur=*/, ", nomChamp, " /* where p.chemin=*/, \"", nomChamp, "\" /* and p.cle_objet=*/, cle);");
									}
									tl(3, "sauvegardes", classSimpleName, ".add(\"", nomChamp, "\");");
									tl(2, "}");
								}
	
//								if(champ.cles && champ.contexteParent != null) {
//									tl(0);
//									String parentContexteVar = entiteVar.toString() + "VarInverse";
//									String chaineVarInverse = champ.contexteParent.obtenirPourClasse(parentContexteVar).toString();
//									String var1, var2, val1, val2, val, valSupprimer, varSupprimer;
//									if(nomChamp.compareTo(chaineVarInverse) < 0) {
//										var1 = nomChamp;
//										var2 = chaineVarInverse;
//										varSupprimer = champ.contexteEnfant.nomVarMinuscule + (requeteSite ? "Cle" : "Key");
//										valSupprimer = nomChamp + (entiteNomCanonique.equals(ArrayList.class.getCanonicalName()) ? ".get(0)" : "");
//										val1 = varSupprimer;
//										val2 = requeteSite ? "cle" : "key";
//									}
//									else {
//										var1 = chaineVarInverse;
//										var2 = nomChamp;
//										varSupprimer = champ.contexteEnfant.nomVarMinuscule + (requeteSite ? "Cle" : "Key");
//										valSupprimer = nomChamp + (entiteNomCanonique.equals(ArrayList.class.getCanonicalName()) ? ".get(0)" : "");
//										val1 = requeteSite ? "cle" : "key";
//										val2 = varSupprimer;
//									}
//	
//									tl(2, "{");
//									tl(3, "String[] valeursCles = requeteSite.requete.getParameterValues(\"", nomChamp, "\");");
//									tl(3, "if(valeursCles != null) {");
//									tl(4, "String[] valeursSuppression = requeteSite.requete.getParameterValues(\"", nomChamp, "Supprimer\");");
//									tl(4, "Long ", varSupprimer, " = Long.parseLong(valeursCles[valeursCles.length - 1]);");
//									tl(4, "if(valeursSuppression != null && \"true\".equals(valeursSuppression[valeursSuppression.length - 1])) {");
//									tl(5, "coureur.update(sqlDeleteA /*delete from a where champ1=*/, \"", var1, "\" /* and cle1=*/, ", val1, " /* and champ2=*/, \"", var2, "\" /* and cle2=*/, ", val2, ");");
//									tl(4, "} else if(definirPourClasse(\"", nomChamp, "\"", "valeursCles[valeursCles.length - 1])) {");
//	//								tl(5, varSupprimer, " = ", valSupprimer, ";");
//									tl(5, "coureur.insert(sqlInsertA, gestionnaireListe /*insert into a(champ1, cle1, champ2, cle2) values(*/, \"", var1, "\"", val1, ", \"", var2, "\"", val2, " /*) on conflict do nothing */);");
//									tl(5, "sauvegardes", classSimpleName, ".add(\"", nomChamp, "\");");
//									tl(4, "}");
//									tl(4, "if(", varSupprimer, " != null) {");
//									tl(5, champ.contexteEnfant.classSimpleName, " ", champ.contexteEnfant.nomVarMinuscule, " = new ", champ.contexteEnfant.classSimpleName, "();");
//									tl(5, champ.contexteEnfant.nomVarMinuscule, ".cle(", varSupprimer, ");");
//									tl(5, champ.contexteEnfant.nomVarMinuscule, ".sauvegardesPourClasse(requeteSite);");
//									tl(5, champ.contexteEnfant.nomVarMinuscule, ".initLoinPourClasse(requeteSite);");
//									tl(5, champ.contexteEnfant.nomVarMinuscule, ".indexerPourClasse(requeteSite);");
//									tl(4, "}");
//									tl(3, "}");
//									tl(2, "}");
//								}
						}	
						o = oAvant;
					}
				}
			}
			if(o != null) {
				if(searchList.size() > 0 && !StringUtils.equals(classAbsolutePath, classPathGen)) {

					///////////////////////////
					// codeIninitLoin //
					///////////////////////////
					oAvant = o;
					o = codeInitialiserLoin;
					tl(3, "dejaInitialise", classSimpleName, " = true;");
					tl(2, "}");
					tl(1, "}");
					if(classeContientRequeteSite) {
						l();
						tl(1, "public void initLoinPourClasse(RequeteSite requeteSite) throws Exception {");
						tl(2, "initLoin", classSimpleName, "(requeteSite);");
						tl(1, "}");  
					}
					o = oAvant; 

					/////////////////
					// codeIndexer //
					/////////////////
					oAvant = o;
					wIndexer = new StringWriter();
					codeIndexer = new PrintWriter(wIndexer);
					o = codeIndexer;
					if(classeIndexe) {
						if(classeEtendBase && !classeEstBase) {
							tl(2, "super.indexer", classSuperSimpleNameGeneric, "(document);");
							tl(0);
						}
						l("\t}");

						if(StringUtils.isNotEmpty(entiteVarCleUnique)) {
							tl(0);
							tl(1, "@Test public void desindexer", classSimpleName, "Test() throws Exception {");
							tl(2, "RequeteSite requeteSite = new RequeteSite();");
							tl(2, "requeteSite.initLoinRequeteSite();");
							tl(2, "EcouteurContexte ecouteurContexte = new EcouteurContexte();");
							tl(2, "ecouteurContexte.initLoinEcouteurContexte();");
							tl(2, "ecouteurContexte.requeteSite(requeteSite);");
							tl(2, "requeteSite.ecouteurContexte(ecouteurContexte);");
							tl(2, "requeteSite.configSite(ecouteurContexte.configSite);");
							tl(2, "initLoin", classSimpleName, "(ecouteurContexte.requeteSite);");
							tl(2, "SolrClient clientSolr = ecouteurContexte.clientSolr;");
							tl(2, "clientSolr.deleteById(", entiteVarCleUnique, ".toString());");
							tl(2, "clientSolr.commit();");
							tl(1, "}");
						}
					}
					o = oAvant;

					/////////////////
					// codeObtenir //
					/////////////////
					oAvant = o;
					wObtenir = new StringWriter();
					codeObtenir = new PrintWriter(wObtenir);
					o = codeObtenir;
					if(classeEtendBase) {
						tl(2, "default:");

						if(classeEstBase)
							tl(3, "return null;");
						else
							tl(3, "return super.obtenir", classSuperSimpleNameGeneric, "(var);");

						tl(2, "}");
						tl(1, "}");
					}	
					o = oAvant;

					///////////////////
					// codeAttribuer //
					///////////////////
					oAvant = o;
					wAttribuer = new StringWriter();
					codeAttribuer = new PrintWriter(wAttribuer);
					o = codeAttribuer;
					if(classeEtendBase) {
						tl(2, "default:");

						if(classeEstBase)
							tl(3, "return null;");
						else
							tl(3, "return super.attribuer", classSuperSimpleNameGeneric, "(var, val);");

						tl(2, "}");
						tl(1, "}");

					}	
					o = oAvant;

					/////////////////
					// codeDefinir //
					/////////////////
					oAvant = o;
					wDefinir = new StringWriter();
					codeDefinir = new PrintWriter(wDefinir);
					o = codeDefinir;
					if(classeEtendBase) {
						tl(2, "default:");

						if(classeEstBase)
							tl(3, "return null;");
						else
							tl(3, "return super.definir", classSuperSimpleNameGeneric, "(var, val);");

						tl(2, "}");
						tl(1, "}");
					}	
					o = oAvant;

					/////////////////
					// codePeupler //
					/////////////////
					oAvant = o;
					wPeupler = new StringWriter();
					codePeupler = new PrintWriter(wPeupler);
					o = codePeupler;
					if(classeSauvegarde) {
//						t(2, "}");

						if(!classSimpleName.equals("Cluster")) {
							tl(0);
							tl(2, "super.peupler", classSuperSimpleNameGeneric, "(documentSolr);");
						}

						tl(1, "}");
					}	
					o = oAvant;

					/////////////////////
					// codeSauvegarder //
					/////////////////////
					oAvant = o;
					wSauvegarder = new StringWriter();
					codeSauvegarder = new PrintWriter(wSauvegarder);
					o = codeSauvegarder;
					if(classeSauvegarde) {
						if(!classSimpleName.equals("Cluster")) {
							tl(0);
							tl(2, "super.sauvegarder", classSuperSimpleNameGeneric + "(requeteSite, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
						}
						tl(1, "}");
					}	
					o = oAvant;

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
