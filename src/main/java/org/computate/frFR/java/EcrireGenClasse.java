package org.computate.frFR.java;      

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
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
public class EcrireGenClasse extends EcrireClasse {

	public static final String VAL_entiteCommentaireLigne1Part1 = "L'entit\u00E9 \u00AB ";
	public static final String VAL_entiteCommentaireLigne1Part2 = " \u00BB";
	public static final String VAL_entiteCouvertureLigne1Part1 = " est d\u00E9fini comme null avant d'\u00EAtre initialis\u00E9. ";
	public static final String VAL_entiteCouvertureLigne2Part1 = " est pour envelopper une valeur \u00E0 assigner \u00E0 ce champ lors de l'initialisation. ";
	public static final String VAL_entiteConstruitLigne1Part1 = "Il est construit avant d'\u00EAtre initialis\u00E9 avec le constructeur par d\u00E9faut ";
	public static final String VAL_entiteConstruitLigne1Part2 = "(). ";
	public static final String VAL_entiteConstruitLigne2Part1 = " est le champ d\u00E9j\u00E0 construit. ";
	public static final String VAL_entiteThrowsLigne2Part1 = " afin que toute exception lors de l'initialisation est g\u00E9r\u00E9e par le servlet. ";

	protected String classeCheminRepertoireGen;
	protected String classeCheminGen;
	protected String classeCheminApiGen;
	protected String classeCheminPageGen;
	protected File classeRepertoireGen;
	protected File classeFichierGen;
	protected File classeFichierApi;
	protected File classeFichierPage;
	protected StringBuilder s = new StringBuilder();
			
	protected SolrDocument doc;
	protected String classeNomSimpleGen;
	protected String classeNomSimpleSuper;
	protected String classeNomSimpleSuperGenerique;
	protected String classeNomCanoniqueSuperGenerique;
	protected String classeNomEnsemble;
	protected String classeNomSimple;
	protected String classeNomCanoniqueSuper;
	protected String classeCommentaire;
	protected List<String> classeImportationsGen;
	protected List<String> classeImportationsGenApi;
	protected List<String> classeImportationsGenPage;
	protected List<String> classeParametreTypeNoms;
	protected List<String> classeSuperParametreTypeNoms;
	protected Boolean classeEtendGen;
	protected Boolean classeBaseEtendGen;
	protected Boolean classeInitLoin;
	protected Boolean classeIndexe;
	protected Boolean classeEtendBase;
	protected Boolean classeEstBase;
	protected Boolean classeSauvegarde;
	protected Boolean classeModele;
	protected Boolean classeApi;
	protected Boolean classePage;

	protected StringWriter wInitialiserLoin;
	protected PrintWriter codeInitialiserLoin;

	protected StringWriter wRequeteSite;
	protected PrintWriter codeRequeteSite;

	protected StringWriter wIndexer;
	protected PrintWriter codeIndexer;

	protected StringWriter wObtenir;
	protected PrintWriter codeObtenir;

	protected StringWriter wAttribuer;
	protected PrintWriter codeAttribuer;

	protected StringWriter wDefinir;
	protected PrintWriter codeDefinir;

	protected StringWriter wPeupler;
	protected PrintWriter codePeupler;

	protected StringWriter wExiste;
	protected PrintWriter codeExiste;

	protected StringWriter wSauvegardes;
	protected PrintWriter codeSauvegardes;

	protected StringWriter wSauvegarder;
	protected PrintWriter codeSauvegarder;

	protected StringWriter wApiGet;
	protected PrintWriter codeApiGet;

	protected StringWriter wApiEcrireJson;
	protected PrintWriter codeApiEcrireJson;

	protected StringWriter wApiChamps;
	protected PrintWriter codeApiChamps;
//
//	protected PrintWriter oAvant;

	protected String entiteVarCleUnique;

	protected String entiteVar;
	protected String entiteVarCapitalise;
	protected String entiteNomCanonique;
	protected String entiteNomCanoniqueGenerique;
	protected String entiteNomSimpleComplet;
	protected String entiteNomSimpleCompletGenerique;
	protected String entiteNomSimple;
	protected String entiteCommentaire;
	protected String entiteVarParam;
	protected Boolean entiteCouverture;
	protected Boolean entiteInitialise;
	protected Boolean entiteInitLoin;

	protected String entiteVarCleUniqueActuel;
	protected String entiteVarSuggere;
	protected String entiteVarIncremente;
	protected String entiteVarCrypte;
	protected String entiteVarIndexe;
	protected String entiteVarStocke;
	protected String entiteTypeSolr;

	protected Boolean entiteExact;
	protected Boolean entiteCleUnique;
	protected Boolean entiteCrypte;
	protected Boolean entiteSuggere;
	protected Boolean entiteSauvegarde;
	protected Boolean entiteIndexe;
	protected Boolean entiteStocke;
	protected Boolean entitetexte;
	protected Boolean entiteIncremente;
	protected Boolean entiteNomAffichage;
	protected Boolean entiteIgnorer;
	protected Boolean entiteDeclarer;
	protected Boolean entiteRechercher;
	protected Boolean entiteAttribuer;
	protected Boolean entiteAjouter;
	protected Boolean entiteSupprimer;
	protected Boolean entiteModifier;
	protected Boolean entiteRecharger;
	protected Boolean entiteMultiligne;
	protected Boolean entiteCles;
	protected Boolean entiteIndexeOuStocke;

	protected List<String> entiteMethodesAvantVisibilite;
	protected List<String> entiteMethodesAvantVar;
	protected List<String> entiteMethodesAvantParamVar;
	protected List<String> entiteMethodesAvantParamNomSimple;
	protected List<Boolean> entiteMethodesAvantNomParam;

	protected List<String> entiteMethodesApresVisibilite;
	protected List<String> entiteMethodesApresVar;
	protected List<String> entiteMethodesApresParamVar;
	protected List<String> entiteMethodesApresParamNomSimple;
	protected List<Boolean> entiteMethodesApresNomParam;
	
	protected PrintWriter auteurGenClasse;

	public void genCodeInit() throws Exception {

		wInitialiserLoin = new StringWriter();
		codeInitialiserLoin = new PrintWriter(wInitialiserLoin);

		wRequeteSite = new StringWriter();
		codeRequeteSite = new PrintWriter(wRequeteSite);

		wIndexer = new StringWriter();
		codeIndexer = new PrintWriter(wIndexer);

		wObtenir = new StringWriter();
		codeObtenir = new PrintWriter(wObtenir);

		wAttribuer = new StringWriter();
		codeAttribuer = new PrintWriter(wAttribuer);

		wDefinir = new StringWriter();
		codeDefinir = new PrintWriter(wDefinir);

		wPeupler = new StringWriter();
		codePeupler = new PrintWriter(wPeupler);

		wSauvegardes = new StringWriter();
		codeSauvegardes = new PrintWriter(wSauvegardes);

		wExiste = new StringWriter();
		codeExiste = new PrintWriter(wExiste);

		wSauvegarder = new StringWriter();
		codeSauvegarder = new PrintWriter(wSauvegarder);

		wApiChamps = new StringWriter();
		codeApiChamps = new PrintWriter(wApiChamps);

		wApiGet = new StringWriter();
		codeApiGet = new PrintWriter(wApiGet);

		wApiEcrireJson = new StringWriter();
		codeApiEcrireJson = new PrintWriter(wApiEcrireJson);
	}

	public void genCodeInitialiserLoin(String langueNom) throws Exception {
		o = codeInitialiserLoin;
		l(); 
		tl(1, "/////////////////////");
		tl(1, "// initialiserLoin //");
		tl(1, "/////////////////////");
		l(); 
		tl(1, "protected boolean dejaInitialise", classeNomSimple, " = false;");
		if(BooleanUtils.isTrue(classeInitLoin)) {
			l();
			tl(1, "public void initLoin", classeNomSimple, "(RequeteSite requeteSite) throws Exception {");
//						if(contientRequeteSite && !StringUtils.equals(classeNomSimple, "RequeteSite"))
//							tl(2, "((", classeNomSimple, ")this).setRequeteSite(requeteSite);");
			tl(2, "setRequeteSite(requeteSite);");
			tl(2, "initLoin", classeNomSimple, "();");
			tl(1, "}");
		}
		l();
		tl(1, "public void initLoin", classeNomSimple, "() throws Exception {");
		tl(2, "if(!dejaInitialise", classeNomSimple, ") {");
		if(BooleanUtils.isTrue(classeEtendBase)) 
			tl(3, "super.initLoin", classeNomSimpleSuperGenerique, "(requeteSite);");
	}

	public void genCodeRequeteSite(String langueNom) throws Exception {
		o = codeRequeteSite;
		if(BooleanUtils.isTrue(classeInitLoin)) {
			l(); 
			tl(1, "/////////////////");
			tl(1, "// requeteSite //");
			tl(1, "/////////////////");
			l(); 
			tl(1, "public void requeteSite", classeNomSimple, "(RequeteSite requeteSite) throws Exception {");
			if(BooleanUtils.isTrue(classeEtendBase)) 
				tl(3, "super.requeteSite", classeNomSimpleSuperGenerique, "(requeteSite);");
		}
	}

	public void genCodeIndexer(String langueNom) throws Exception {
		o = codeIndexer;
		if(classeIndexe) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// indexer //");
			tl(1, "/////////////");
			tl(0);
			tl(1, "public void indexer", classeNomSimple, "() throws Exception {");
			tl(2, "RequeteSite requeteSite = new RequeteSite();");
			tl(2, "requeteSite.initLoinRequeteSite();");
			tl(2, "EcouteurContexte ecouteurContexte = new EcouteurContexte();");
			tl(2, "ecouteurContexte.initLoinEcouteurContexte();");
			tl(2, "ecouteurContexte.setRequeteSite(requeteSite);");
			tl(2, "requeteSite.setEcouteurContexte(ecouteurContexte);");
			tl(2, "requeteSite.setConfigSite(ecouteurContexte.configSite);");
			tl(2, "requeteSite", classeNomSimple, "(requeteSite);");
			tl(2, "initLoin", classeNomSimple, "(requeteSite);");
			tl(2, "indexer", classeNomSimple, "(requeteSite);");
			tl(1, "}");
			tl(0);
			if(classeEtendBase || classeEstBase) {
				tl(0);
				t(1);
				if(!classeEstBase)
					s("@Override ");
				l("public void indexerPourClasse(RequeteSite requeteSite) throws Exception {");
				tl(2, "indexer", classeNomSimple, "(requeteSite);");
				tl(1, "}");
				tl(0);
				t(1);
				if(!classeEstBase)
					s("@Override ");
				l("public void indexerPourClasse(SolrInputDocument document) throws Exception {");
				tl(2, "indexer", classeNomSimple, "(document);");
				tl(1, "}");
			}
			tl(1, "public void indexer", classeNomSimple, "(RequeteSite requeteSite) throws Exception {");
			tl(2, "SolrInputDocument document = new SolrInputDocument();");
			tl(2, "indexer", classeNomSimple, "(document);");
			if(classeSauvegarde)
				tl(2, "document.addField(\"sauvegardes", classeNomSimple, "_stored_strings\", sauvegardes);");
			tl(2, "SolrClient clientSolr = requeteSite.ecouteurContexte.clientSolr;");
			tl(2, "clientSolr.add(document);");
			tl(2, "clientSolr.commit();");
			l("\t}");

			tl(0);
			tl(1, "public void indexer", classeNomSimple, "(SolrInputDocument document) throws Exception {");
		}
	}

	public void genCodeObtenir(String langueNom) throws Exception {
		o = codeObtenir;
		if(classeEtendBase || classeEstBase) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// obtenir //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			l("public Object obtenirPourClasse(String var) throws Exception {");
			tl(2, "String[] vars = org.apache.commons.lang3.StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "for(String v : vars) {");
			tl(3, "if(o == null)");
			tl(4, "o = obtenir", classeNomSimple, "(v);");
			tl(3, "else if(o instanceof Cluster) {");
			tl(4, "Cluster cluster = (Cluster)o;");
			tl(4, "o = cluster.obtenirPourClasse(v);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o;");
			tl(1, "}");
			tl(1, "public Object obtenir", classeNomSimple, "(String var) throws Exception {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
			tl(2, "switch(var) {");
		}
	}

	public void genCodeAttribuer(String langueNom) throws Exception {
		o = codeAttribuer;
		if(classeEtendBase || classeEstBase) {
			l(); 
			tl(1, "///////////////");
			tl(1, "// attribuer //");
			tl(1, "///////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			l("public boolean attribuerPourClasse(String var, Object val) throws Exception {");
			tl(2, "String[] vars = org.apache.commons.lang3.StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "for(String v : vars) {");
			tl(3, "if(o == null)");
			tl(4, "o = attribuer", classeNomSimple + "(v, val);");
			tl(3, "else if(o instanceof Cluster) {");
			tl(4, "Cluster cluster = (Cluster)o;");
			tl(4, "o = cluster.attribuerPourClasse(v, val);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object attribuer", classeNomSimple, "(String var, Object val) throws Exception {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
			tl(2, "switch(var) {");

		}
	}

	public void genCodeDefinir(String langueNom) throws Exception {
		o = codeDefinir;
		if(classeEtendBase || classeEstBase) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// definir //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			l("public boolean definirPourClasse(String var, String...vals) throws Exception {");
			tl(2, "String[] vars = org.apache.commons.lang3.StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "String val = vals == null ? null : vals[vals.length - 1];");
			tl(2, "if(val != null) {");
			tl(3, "for(String v : vars) {");
			tl(4, "if(o == null)");
			tl(5, "o = definir", classeNomSimple + "(v, val);");
			tl(4, "else if(o instanceof Cluster) {");
			tl(5, "Cluster cluster = (Cluster)o;");
			tl(5, "o = cluster.definirPourClasse(v, val);");
			tl(4, "}");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object definir", classeNomSimple, "(String var, String val) throws Exception {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
			tl(2, "switch(var) {");
		}
	}

	public void genCodePeupler(String langueNom) throws Exception {
		o = codePeupler;
		if(classeSauvegarde) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// peupler //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classeNomSimple.equals("Cluster"))
				s("@Override ");
			l("public void peuplerPourClasse(", SolrDocument.class.getCanonicalName(), " documentSolr) throws Exception {");
			if(classeSauvegarde) {
				tl(2, "sauvegardes", classeNomSimple, " = (java.util.ArrayList<String>)documentSolr.get(\"sauvegardes", classeNomSimple, "_stored_strings\");");
			tl(2, "peupler", classeNomSimple, "(documentSolr);");
			}
			tl(1, "}");
			tl(1, "public void peupler", classeNomSimple, "(", SolrDocument.class.getCanonicalName(), " documentSolr) throws Exception {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
		}
	}

	public void genCodeExiste(String langueNom) throws Exception {
		o = codeExiste;
		if(classeSauvegarde) {
			l(); 
			tl(1, "////////////");
			tl(1, "// existe //");
			tl(1, "////////////");
			tl(0);
			t(1);
			if(!classeNomSimple.equals("Cluster"))
				s("@Override ");
			l("public Boolean existePourClasse() throws Exception {");
			tl(2, "String cleStr = requeteSite.requete.getParameter(\"cle\");");
			tl(2, "Long cle = ", StringUtils.class.getCanonicalName(), ".isNumeric(cleStr) ? Long.parseLong(cleStr) : null;");
			tl(2, "Boolean existe = existePourClasse(cle);");
			tl(2, "return existe;");
			tl(1, "}");
			t(1);
			if(!classeNomSimple.equals("Cluster"))
				s("@Override ");
			l("public Boolean existePourClasse(Long cle) throws Exception {");
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
	}

	public void genCodeSauvegardes(String langueNom) throws Exception {
		o = codeSauvegardes;
		if(classeSauvegarde) {
			l(); 
			tl(1, "/////////////////");
			tl(1, "// sauvegardes //");
			tl(1, "/////////////////");
			tl(0);
			tl(1, "protected java.util.ArrayList<String> sauvegardes", classeNomSimple, " = new java.util.ArrayList<String>();");
			t(1);
			if(!classeNomSimple.equals("Cluster"))
				s("@Override ");
			l("public void sauvegardesPourClasse(RequeteSite requeteSite) throws Exception {");
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
			tl(4, "sauvegardes", classeNomSimple, ".add(chemin);");
			tl(3, "}");
			tl(2, "}");
			tl(1, "}");
		}
	}

	public void genCodeSauvegarder(String langueNom) throws Exception {
		o = codeSauvegarder;
		if(classeSauvegarde) {
			l(); 
			tl(1, "/////////////////");
			tl(1, "// sauvegarder //");
			tl(1, "/////////////////");
			tl(0);
			t(1);
			if(!classeNomSimple.equals("Cluster"))
				s("@Override ");
			l("public void sauvegarderPourClasse(RequeteSite requeteSite) throws Exception {");
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
//						tl(4, "sauvegardes", classeNomSimple, ".add(chemin);");
//						tl(3, "}");
//						tl(2, "}");
			tl(0);
//						tl(2, "{");
			tl(2, "String sqlInsertP = \"insert into p(chemin, valeur, cle_objet) values(?, ?, ?) on conflict(chemin, cle_objet) do update set valeur=? where p.chemin=? and p.cle_objet=?\";");
			tl(2, "String sqlInsertA = \"insert into a(champ1, cle1, champ2, cle2) values(?, ?, ?, ?) on conflict  do nothing\";");
			tl(2, "String sqlDeleteP = \"delete from p where chemin=? and cle_objet=?\";");
			tl(2, "String sqlDeleteA = \"delete from a where champ1=? and cle1=? and champ2=? and cle2=?\";");
			tl(2, "sauvegarder", classeNomSimple, "(requeteSite, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
//						tl(2, "}");
			tl(1, "}");
			tl(1, "public void sauvegarder", classeNomSimple, "(RequeteSite requeteSite, String sqlInsertP, String sqlInsertA, String sqlDeleteP, String sqlDeleteA, ", ArrayListHandler.class.getCanonicalName(), " gestionnaireListe, ", QueryRunner.class.getCanonicalName(), " coureur) throws Exception {");
		}
	}

	public void genCodeClasseDebut(String langueNom) throws Exception {
		o = auteurGenClasse;

		l("package ", classeNomEnsemble, ";");
		l();
		if(classeImportationsGen.size() > 0) { 
			for(String classeImportation : classeImportationsGen) {
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
				if(j > 0)
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
//									s();
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

	public void genCodeEntite(String langueNom) throws Exception {
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

		String entiteVarCleUniqueActuel = (String)doc.get("entiteVarCleUnique_stored_boolean");
		if(StringUtils.isNotEmpty(entiteVarCleUniqueActuel))
			entiteVarCleUnique = entiteVarCleUniqueActuel;
		String entiteVarSuggere = (String)doc.get("entiteVarSuggere_stored_string");
		String entiteVarIncremente = (String)doc.get("entiteVarIncremente_stored_string");
		String entiteVarCrypte = (String)doc.get("entiteVarCrypte_stored_string");
		String entiteVarIndexe = (String)doc.get("entiteVarIndexe_stored_string");
		String entiteVarStocke = (String)doc.get("entiteVarStocke_stored_string");
		String entiteTypeSolr = (String)doc.get("entiteTypeSolr_stored_string");

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
		Boolean entiteDefinir = (Boolean)doc.get("entiteDefinir_stored_boolean");

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

		o = auteurGenClasse;

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

//						l();
//						tl(1, "public ", classeNomSimple, " ", entiteVar, "(", entiteNomSimpleComplet, " ", entiteVarParam, ") throws Exception {");
//						tl(2, "set", entiteVarCapitalise, "(", entiteVarParam, ");");
//						tl(2, "return (", classeNomSimple, ")this;");
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
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
			tl(3, "Long l = Long.parseLong(o);");
			tl(3, entiteVar, "Ajouter(l);");
			tl(2, "}");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
		}

		// Setter Boolean //
		if(StringUtils.equals(entiteNomCanonique, Boolean.class.getCanonicalName())) {
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))");
			tl(3, "this.", entiteVar, " = Boolean.parseBoolean(o);");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
		}

		// Setter Integer //
		if(StringUtils.equals(entiteNomCanonique, Integer.class.getCanonicalName())) {
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entiteVar, " = Integer.parseInt(o);");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
		}

		// Setter BigDecimal //
		if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entiteVar, " = new BigDecimal(o);");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
		}

		// Setter Float //
		if(StringUtils.equals(entiteNomCanonique, Float.class.getCanonicalName())) {
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entiteVar, " = Float.parseFloat(o);");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
		}

		// Setter Double //
		if(StringUtils.equals(entiteNomCanonique, Double.class.getCanonicalName())) {
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entiteVar, " = Double.parseDouble(o);");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
		}

		// Setter Long //
		if(StringUtils.equals(entiteNomCanonique, Long.class.getCanonicalName())) {
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
			tl(3, "this.", entiteVar, " = Long.parseLong(o);");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
		}

		// Setter Long //
		if(StringUtils.equals(entiteNomSimple, "Chaine")) {
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, entiteVar, ".tout(o);");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
		}

		// Setter LocalDate //
		if(StringUtils.equals(entiteNomCanonique, LocalDate.class.getCanonicalName())) {
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, "this.", entiteVar, " = java.time.LocalDate.parse(o, java.time.format.DateTimeFormatter.ISO_OFFSET_DATE);");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(java.util.Date o) throws Exception {");
			tl(2, "this.", entiteVar, " = o.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
		}

		// Setter LocalDateTime //
		if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) throws Exception {");
			tl(2, "this.", entiteVar, " = java.time.LocalDateTime.parse(o, java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
			tl(2, "return (", classeNomSimple, ")this;");
			tl(1, "}");
			tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(java.util.Date o) throws Exception {");
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

		////////////////////
		// codeIninitLoin //
		////////////////////
		o = codeInitialiserLoin;
		if(entiteInitialise) {
			tl(3, entiteVar, "Init();");
		}


		/////////////////////
		// codeRequeteSite //
		/////////////////////
		if(classeInitLoin && entiteInitLoin) {
			o = codeRequeteSite;
			tl(2, entiteVar, ".setRequeteSite(requeteSite);");
		}

		/////////////////
		// codeIndexer //
		/////////////////
		o = codeIndexer;
		if(classeIndexe && entiteIndexeOuStocke) {
			tl(2, "if(", entiteVar, " != null) {");
			if(StringUtils.isNotEmpty(entiteVarCleUniqueActuel) && entiteCleUnique) {
				// cleUnique
				tl(3, "document.addField(\"", entiteVarCleUniqueActuel, "\", ", entiteVar, ");");
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
					tl(3, "document.addField(\"", entiteVarSuggere, "\", ", entiteVar, ");");
				}
				else if(entiteNomSimple.equals("Timestamp") || entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
					tl(3, "document.addField(\"", entiteVarSuggere, "\", java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ", java.time.OffsetDateTime.now().getOffset(), java.time.ZoneId.of(\"UTC\"))));");
				}
				else if(entiteNomSimple.toString().equals("LocalDate")) {
					tl(3, "document.addField(\"", entiteVarSuggere, "\", java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(inrDate.atStartOfDay(java.time.ZoneId.of(\"UTC\"))));");
				}
				else {
					tl(3, "document.addField(\"", entiteVarSuggere, "\", ", entiteVar, ");");
				}
			}

			if(StringUtils.isNotEmpty(entiteVarIndexe) && entiteIndexe) {
				// indexe
				if(entiteNomSimple.equals("Chaine")) {
					tl(3, "document.addField(\"", entiteVarIndexe, "\", ", entiteVar, ");");
				}
				else if(entiteNomSimple.equals("Timestamp") || entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
					tl(3, "document.addField(\"", entiteVarIndexe, "\", java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ", java.time.OffsetDateTime.now().getOffset(), java.time.ZoneId.of(\"UTC\"))));");
				}
				else if(entiteNomSimple.toString().equals("LocalDate")) {
					tl(3, "document.addField(\"", entiteVarIndexe, "\", java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(inrDate.atStartOfDay(java.time.ZoneId.of(\"UTC\"))));");
				}
				else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList")) {
					tl(3, "for(", entiteNomCanoniqueGenerique, " o : ", entiteVar, ") {");
					tl(4, "document.addField(\"", entiteVarIndexe, "\", o);");
					tl(3, "}");
				}
				else {
					tl(3, "document.addField(\"", entiteVarIndexe, "\", ", entiteVar, ");");
				}
			}
			else {
				if(StringUtils.isNotEmpty(entiteVarIndexe)) {
					tl(3, "document.addField(\"", entiteVarIndexe, "\", ", entiteVar, ");");
				}
			}

			if(StringUtils.isNotEmpty(entiteVarStocke) && entiteStocke) {
				// stocke
				if(entiteNomSimple.equals("Chaine")) {
					tl(3, "document.addField(\"", entiteVarStocke, "\", ", entiteVar, ");");
				}
				else if(entiteNomSimple.equals("Timestamp") || entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
					tl(3, "document.addField(\"", entiteVarStocke, "\", java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ", java.time.OffsetDateTime.now().getOffset(), java.time.ZoneId.of(\"UTC\"))));");
				}
				else if(entiteNomSimple.toString().equals("LocalDate")) {
					tl(3, "document.addField(\"", entiteVarStocke, "\", java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(inrDate.atStartOfDay(java.time.ZoneId.of(\"UTC\"))));");
				}
				else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList")) {
					tl(3, "for(", entiteNomCanoniqueGenerique, " o : ", entiteVar, ") {");
					tl(4, "document.addField(\"", entiteVarStocke, "\", o);");
					tl(3, "}");
				}
				else {
					tl(3, "document.addField(\"", entiteVarStocke, "\", ", entiteVar, ");");
				}
			}
			tl(2, "}");
		}

		/////////////////
		// codeObtenir //
		/////////////////
		o = codeObtenir;
		if(classeEtendBase || classeEstBase) {
			tl(3, "case \"", entiteVar, "\":");
			tl(4, "return o", classeNomSimple, ".", entiteVar, ";");
		}	

		///////////////////
		// codeAttribuer //
		///////////////////
		o = codeAttribuer;
		if(classeEtendBase || classeEstBase) {
			tl(3, "case \"", entiteVar, "\":");
			tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "((", entiteNomSimpleComplet, ")val);");
			tl(4, "return val;");
		}	

		/////////////////
		// codeDefinir //
		/////////////////
		o = codeDefinir;
		if(classeEtendBase || classeEstBase && BooleanUtils.isTrue(entiteDefinir)) {
//							if(champ.contientSetterString) {
//							if(entiteContientSetterString) {
				tl(3, "case \"", entiteVar, "\":");
				tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(val);");
				tl(4, "return val;");
//							}
		}	

		/////////////////
		// codePeupler //
		/////////////////
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
//									tl(3, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
					tl(4, entiteTypeSolr, " ", entiteVar, " = (", entiteTypeSolr, ")documentSolr.get(\"", entiteVarSuggere, "\");");
					tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
				}
				else if(!StringUtils.isEmpty(entiteVarIncremente)) {
//									tl(3, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
					tl(4, entiteTypeSolr, " ", entiteVar, " = (", entiteTypeSolr, ")documentSolr.get(\"", entiteVarIncremente, "\");");
					tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
				}
				else if(!StringUtils.isEmpty(entiteVarCleUniqueActuel)) {
//									tl(3, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
					tl(4, entiteTypeSolr, " ", entiteVar, " = org.apache.commons.lang3.math.NumberUtils.toLong((String)documentSolr.get(\"", entiteVarCleUniqueActuel, "\"));");
					tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
				}
				else if(!StringUtils.isEmpty(entiteVarCrypte)) {
//									tl(3, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
					if(siteCrypte)
						tl(4, entiteTypeSolr, " ", entiteVar, " = requeteSite.decrypterStr((", entiteTypeSolr, ")documentSolr.get(\"", entiteVarCrypte, "\"));");
					else
						tl(4, entiteTypeSolr, " ", entiteVar, " = (", entiteTypeSolr, ")documentSolr.get(\"", entiteVarCrypte, "\");");
					tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
				}
				else {
//									tl(3, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
					tl(4, entiteTypeSolr, " ", entiteVar, " = (", entiteTypeSolr, ")documentSolr.get(\"", entiteVarStocke, "\");");
					tl(4, "if(", entiteVar, " != null)");
					if(StringUtils.contains(entiteTypeSolr, "<"))
						tl(5, "o", classeNomSimple, ".", entiteVar, ".addAll(", entiteVar, ");");
					else
						tl(5, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//									tl(3, "}");
				}

			}
		}	

		/////////////////////
		// codeSauvegarder //
		/////////////////////
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
					tl(3, "sauvegardes", classeNomSimple, ".add(\"", nomChamp, "\");");
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
//									tl(5, "sauvegardes", classeNomSimple, ".add(\"", nomChamp, "\");");
//									tl(4, "}");
//									tl(4, "if(", varSupprimer, " != null) {");
//									tl(5, champ.contexteEnfant.classeNomSimple, " ", champ.contexteEnfant.nomVarMinuscule, " = new ", champ.contexteEnfant.classeNomSimple, "();");
//									tl(5, champ.contexteEnfant.nomVarMinuscule, ".cle(", varSupprimer, ");");
//									tl(5, champ.contexteEnfant.nomVarMinuscule, ".sauvegardesPourClasse(requeteSite);");
//									tl(5, champ.contexteEnfant.nomVarMinuscule, ".initLoinPourClasse(requeteSite);");
//									tl(5, champ.contexteEnfant.nomVarMinuscule, ".indexerPourClasse(requeteSite);");
//									tl(4, "}");
//									tl(3, "}");
//									tl(2, "}");
//								}
		}	

		/////////////////
		// codeApiChamps //
		/////////////////
		o = codeApiChamps;
		l();
		tl(1, "public static final String ENTITE_VAR_", entiteVar, " = \"", entiteVar, "\";");
		if(classeIndexe) {
			if(entiteIndexe)
				tl(1, "public static final String ENTITE_VAR_INDEXE_", entiteVar, " = \"", entiteVarIndexe, "\";");
			if(entiteStocke)
				tl(1, "public static final String ENTITE_VAR_STOCKE_", entiteVar, " = \"", entiteVarStocke, "\";");
			if(entiteCrypte)
				tl(1, "public static final String ENTITE_VAR_CRYPTE_", entiteVar, " = \"", entiteVarCrypte, "\";");
		}

		/////////////////
		// codeApiGet //
		/////////////////
		o = codeApiGet;
		if(classeIndexe && entiteIndexe) {
			tl(3, "case ENTITE_VAR_", entiteVar, ":");
			tl(4, "return ENTITE_VAR_INDEXE_", entiteVar, ";");
		}

		///////////////////////
		// codeApiEcrireJson //
		///////////////////////
		o = codeApiEcrireJson;
		if(classeIndexe && entiteStocke) {
			tl(3, "case ENTITE_VAR_STOCKE_", entiteVar, ":");
			tl(4, "return ENTITE_VAR_", entiteVar, ";");
		}
	}

	public void genCodeClasseFin(String langueNom) throws Exception {
		//////////////////
		// codeInitLoin //
		//////////////////
		o = codeInitialiserLoin;
		tl(3, "dejaInitialise", classeNomSimple, " = true;");
		tl(2, "}");
		tl(1, "}");
		if(classeInitLoin) {
			l();
			tl(1, "public void initLoinPourClasse(RequeteSite requeteSite) throws Exception {");
			tl(2, "initLoin", classeNomSimple, "(requeteSite);");
			tl(1, "}");  
		}

		/////////////////////
		// codeRequeteSite //
		/////////////////////
		if(classeInitLoin) {
			o = codeRequeteSite;
			tl(1, "}");
			l();
			tl(1, "public void requeteSitePourClasse(RequeteSite requeteSite) throws Exception {");
			tl(2, "requeteSite", classeNomSimple, "(requeteSite);");
			tl(1, "}");  
		}

		/////////////////
		// codeIndexer //
		/////////////////
		o = codeIndexer;
		if(classeIndexe) {
			if(classeEtendBase && !classeEstBase) {
				tl(2, "super.indexer", classeNomSimpleSuperGenerique, "(document);");
				tl(0);
			}
			l("\t}");

			if(StringUtils.isNotEmpty(entiteVarCleUnique)) {
				tl(0);
				tl(1, "public void desindexer", classeNomSimple, "() throws Exception {");
				tl(2, "RequeteSite requeteSite = new RequeteSite();");
				tl(2, "requeteSite.initLoinRequeteSite();");
				tl(2, "EcouteurContexte ecouteurContexte = new EcouteurContexte();");
				tl(2, "ecouteurContexte.initLoinEcouteurContexte();");
				tl(2, "ecouteurContexte.setRequeteSite(requeteSite);");
				tl(2, "requeteSite.setEcouteurContexte(ecouteurContexte);");
				tl(2, "requeteSite.setConfigSite(ecouteurContexte.configSite);");
				tl(2, "initLoin", classeNomSimple, "(ecouteurContexte.requeteSite);");
				tl(2, "SolrClient clientSolr = ecouteurContexte.clientSolr;");
				tl(2, "clientSolr.deleteById(", entiteVarCleUnique, ".toString());");
				tl(2, "clientSolr.commit();");
				tl(1, "}");
			}
		}

		/////////////////
		// codeObtenir //
		/////////////////
		o = codeObtenir;
		if(classeEtendBase || classeEstBase) {
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return super.obtenir", classeNomSimpleSuperGenerique, "(var);");

			tl(2, "}");
			tl(1, "}");
		}	

		///////////////////
		// codeAttribuer //
		///////////////////
		o = codeAttribuer;
		if(classeEtendBase || classeEstBase) {
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return super.attribuer", classeNomSimpleSuperGenerique, "(var, val);");

			tl(2, "}");
			tl(1, "}");

		}	

		/////////////////
		// codeDefinir //
		/////////////////
		o = codeDefinir;
		if(classeEtendBase || classeEstBase) {
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return super.definir", classeNomSimpleSuperGenerique, "(var, val);");

			tl(2, "}");
			tl(1, "}");
		}	

		/////////////////
		// codePeupler //
		/////////////////
		o = codePeupler;
		if(classeSauvegarde) {
//						t(2, "}");

			if(!classeNomSimple.equals("Cluster")) {
				tl(0);
				tl(2, "super.peupler", classeNomSimpleSuperGenerique, "(documentSolr);");
			}

			tl(1, "}");
		}	

		/////////////////////
		// codeSauvegarder //
		/////////////////////
		o = codeSauvegarder;
		if(classeSauvegarde) {
			if(!classeNomSimple.equals("Cluster")) {
				tl(0);
				tl(2, "super.sauvegarder", classeNomSimpleSuperGenerique + "(requeteSite, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
			}
			tl(1, "}");
		}	

		codeInitialiserLoin.flush();
		codeRequeteSite.flush();
		codeIndexer.flush();
		codeObtenir.flush();
		codeAttribuer.flush();
		codeDefinir.flush();
		codePeupler.flush();
		codeExiste.flush();
		codeSauvegardes.flush();
		codeSauvegarder.flush();
		codeApiChamps.flush();
		codeApiGet.flush();
		codeApiEcrireJson.flush();

		o = auteurGenClasse;

		s(wInitialiserLoin.toString());
		s(wRequeteSite.toString());
		s(wIndexer.toString());
		s(wObtenir.toString());
		s(wAttribuer.toString());
		s(wDefinir.toString());
		s(wPeupler.toString());
		s(wExiste.toString());
		s(wSauvegardes.toString());
		s(wSauvegarder.toString());

		l("}"); 

		System.out.println("Ecrire: " + classeCheminGen); 
		auteurGenClasse.flush();
		auteurGenClasse.close();
	}  
}
