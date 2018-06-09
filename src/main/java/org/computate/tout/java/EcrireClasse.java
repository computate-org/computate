package org.computate.tout.java;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMember;

public class EcrireClasse {

	protected JavaProjectBuilder bricoleur;
	protected String urlSolr;
	protected SolrClient clientSolr;
	protected String langue;
	protected String[] autresLangues;
	protected String cheminConfiguration;
	protected File fichierConfiguration;
	protected INIConfiguration configuration;
	protected String cheminSrcMainJava;
	protected String cheminSrcGenJava;
	protected ArrayList<String> cheminsBin = new ArrayList<String>();
	protected ArrayList<String> cheminsCheminClasse = new ArrayList<String>();
	protected ArrayList<String> cheminsARegarder = new ArrayList<String>();
	protected ArrayList<String> nomsMethodeTest = new ArrayList<String>();
	protected ArrayList<String> cheminsSource = new ArrayList<String>();
	protected ArrayList<String> toutCheminsSource = new ArrayList<String>();
	protected ArrayList<String> cheminsBibliotheque = new ArrayList<String>();

	public static void main(String[] args) throws Exception {  
		String classeCheminRepertoireAppli = args[0];
		String classeCheminAbsolu = args[1];
		EcrireClasse ecrireClasse = new EcrireClasse();

		ecrireClasse.cheminSrcMainJava = classeCheminRepertoireAppli + "/src/main/java";
		ecrireClasse.cheminSrcGenJava = classeCheminRepertoireAppli + "/src/gen/java";

		ecrireClasse.cheminsBibliotheque.add(classeCheminRepertoireAppli + "/lib");
		ecrireClasse.cheminsBibliotheque.add(classeCheminRepertoireAppli + "/lib-tomcat");
		ecrireClasse.cheminsBibliotheque.add(classeCheminRepertoireAppli + "/lib-keycloak");

		ecrireClasse.cheminsBin.add(classeCheminRepertoireAppli + "/bin");
		ecrireClasse.cheminsBin.add(classeCheminRepertoireAppli + "/src/main/resources");

		ecrireClasse.cheminConfiguration = classeCheminRepertoireAppli + "/config/computate.config";
		ecrireClasse.fichierConfiguration = new File(ecrireClasse.cheminConfiguration);
		Configurations configurations = new Configurations();
		ecrireClasse.configuration = configurations.ini(ecrireClasse.fichierConfiguration);

		ecrireClasse.langue = "frFR";
		ecrireClasse.autresLangues = new String[] { "enUS" };
		ecrireClasse.urlSolr = ecrireClasse.configuration.getString("urlSolr");
		ecrireClasse.clientSolr = new HttpSolrClient.Builder(ecrireClasse.urlSolr).build();
		try {
			ecrireClasse.initialiserEcrireClasse();
		}
		catch(Exception e) {
			System.err.println("Erreur pendant traiterEvenements. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
		ecrireClasse.bricoleur = null;
		ecrireClasse.bricoleur = new JavaProjectBuilder();
		for(String cheminSource : ecrireClasse.toutCheminsSource) {
			File répertoireSource = new File(cheminSource);
			ecrireClasse.bricoleur.addSourceFolder(répertoireSource);
		}
		System.out.println("cheminAbsolu : " + classeCheminAbsolu);

		ecrireClasse.indexerClasse(classeCheminAbsolu);
		ecrireClasse.ecrireClasseGen(classeCheminAbsolu);
	}
	
	public void initialiserEcrireClasse() throws Exception {
		cheminsARegarder.add(cheminSrcMainJava);
		cheminsSource.add(cheminSrcMainJava);
		toutCheminsSource.add(cheminSrcMainJava);

		cheminsSource.add(cheminSrcGenJava);
		toutCheminsSource.add(cheminSrcGenJava);
		
		for(String chemin : cheminsBin) {
			cheminsCheminClasse.add(chemin);
		}
		
		for(String chemin : cheminsBibliotheque) {
			cheminsCheminClasse.add(chemin + "/*");
		}
//
//		cheminClasse = StringUtils.join(cheminsCheminClasse, File.pathSeparator);
	}

	public String regex(String motif, String texte, Integer groupe) {
		String o = null;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if(trouve)
				o = m.group(1);
		}
		return o;
	}

	public String concat(String...valeurs) throws Exception {
		String resultat = Stream.of(valeurs).collect(Collectors.joining());
		return resultat;
	}  

	protected void ecrireClasseGen(String classeCheminAbsolu) throws Exception { 

		SolrQuery rechercheSolr = new SolrQuery();
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexe_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addFilterQuery("classeEstClasse_indexe_boolean:true");
		System.out.println(rechercheSolr);

		QueryResponse reponseRecherche = clientSolr.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		System.out.println("Going"); 
		if(listeRecherche.size() > 0) {
			System.out.println("Found");
			SolrDocument document = listeRecherche.get(0);
			String classeCheminRepertoireGen = (String)document.get("classeCheminRepertoireGen_" + langue + "_stocke_string");
			String classeCheminGen = (String)document.get("classeCheminGen_" + langue + "_stocke_string");
			File classeRepertoire = new File(classeCheminRepertoireGen);
			classeRepertoire.mkdirs();
			File classeFichier = new File(classeCheminGen);
			StringBuilder s = new StringBuilder();
			FileUtils.write(classeFichier, s, Charset.forName("UTF-8"));
		}
	}  

	protected void indexerClasse(String classeCheminAbsolu) throws Exception { 
		SolrInputDocument docClasse = new SolrInputDocument();
		String classeNomCanonique = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classeCheminAbsolu, "."), cheminSrcMainJava + "/"), "/", ".");
		JavaClass classeQdoxClasse = bricoleur.getClassByName(classeNomCanonique.toString());
		String commentaire = classeQdoxClasse.getComment();
		String classeNomEnsemble = StringUtils.substringBeforeLast(classeNomCanonique, ".");
		String classeChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), ".java");
		String classeCheminRepertoire = StringUtils.substringBeforeLast(classeChemin, "/");
		String classeCheminGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Gen.java");
		String classeCheminRepertoireGen = StringUtils.substringBeforeLast(classeCheminGen, "/");
		String cleClasse = classeChemin;
		Instant modifiee = Instant.now();
		Date modifieeDate = Date.from(modifiee);
		System.out.println(modifiee);  

		docClasse.addField("modifiee_indexe_date", modifieeDate);
		docClasse.addField("modifiee_stocke_date", modifieeDate);

		docClasse.addField(concat("classeNomCanonique_", langue, "_indexe_string"), classeNomCanonique);
		docClasse.addField(concat("classeNomCanonique_", langue, "_stocke_string"), classeNomCanonique);

		docClasse.addField(concat("classeNomEnsemble_", langue, "_indexe_string"), classeNomEnsemble);
		docClasse.addField(concat("classeNomEnsemble_", langue, "_stocke_string"), classeNomEnsemble);

		docClasse.addField(concat("classeCheminAbsolu_indexe_string"), classeCheminAbsolu);
		docClasse.addField(concat("classeCheminAbsolu_stocke_string"), classeCheminAbsolu);

		docClasse.addField(concat("classeChemin_", langue, "_indexe_string"), classeChemin);
		docClasse.addField(concat("classeChemin_", langue, "_stocke_string"), classeChemin);

		docClasse.addField(concat("classeCheminRepertoire_", langue, "_indexe_string"), classeCheminRepertoire);
		docClasse.addField(concat("classeCheminRepertoire_", langue, "_stocke_string"), classeCheminRepertoire);

		docClasse.addField(concat("classeCheminRepertoireGen_", langue, "_indexe_string"), classeCheminRepertoireGen);
		docClasse.addField(concat("classeCheminRepertoireGen_", langue, "_stocke_string"), classeCheminRepertoireGen);

		docClasse.addField(concat("classeCheminGen_", langue, "_indexe_string"), classeCheminGen);
		docClasse.addField(concat("classeCheminGen_", langue, "_stocke_string"), classeCheminGen);

		for(String langue : autresLangues) {  
			String classeNomCanoniqueLangue = regex("classeNomCanonique\\." + langue + ": (.*)", commentaire, 1);
			String classeNomEnsembleLangue = StringUtils.substringBeforeLast(classeNomCanoniqueLangue, ".");
			String classeCheminLangue = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java");
			String classeCheminRepertoireLangue = StringUtils.substringBeforeLast(classeCheminLangue, "/");

			docClasse.addField(concat("classeNomCanonique_", langue, "_indexe_string"), classeNomCanoniqueLangue);
			docClasse.addField(concat("classeNomCanonique_", langue, "_stocke_string"), classeNomCanoniqueLangue);

			docClasse.addField(concat("classeNomEnsemble_", langue, "_indexe_string"), classeNomEnsembleLangue);
			docClasse.addField(concat("classeNomEnsemble_", langue, "_stocke_string"), classeNomEnsembleLangue);

			docClasse.addField(concat("classeChemin_", langue, "_indexe_string"), classeCheminLangue);
			docClasse.addField(concat("classeChemin_", langue, "_stocke_string"), classeCheminLangue);

			docClasse.addField(concat("classeCheminRepertoire_", langue, "_indexe_string"), classeCheminRepertoireLangue);
			docClasse.addField(concat("classeCheminRepertoire_", langue, "_stocke_string"), classeCheminRepertoireLangue);
		} 

		SolrInputDocument docClasseClone = docClasse.deepCopy();

		docClasse.addField("cle", cleClasse);  

		docClasse.addField(concat("classeEstClasse_indexe_boolean"), true);
		docClasse.addField(concat("classeEstClasse_stocke_boolean"), true);

		List<JavaMember> membresQdox = new ArrayList<JavaMember>();
		membresQdox.addAll(classeQdoxClasse.getFields());
		membresQdox.addAll(classeQdoxClasse.getConstructors());
		membresQdox.addAll(classeQdoxClasse.getMethods());
		for(JavaMember membreQdox : membresQdox) {  

			if(membreQdox instanceof JavaField) {    
				SolrInputDocument docChamp = docClasseClone.deepCopy();
				JavaField champQdox = (JavaField)membreQdox;
				JavaClass classeQdoxChamp = champQdox.getType();
				String champVar = champQdox.getName();
				String nomCanoniqueChamp = champQdox.getType().getCanonicalName();
				String nomTypeOrigine = classeQdoxChamp.getGenericCanonicalName();
				String listeNomTypeOrigineGenerique = nomTypeOrigine;
				String nomCanoniqueGeneriqueChamp = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineGenerique, "<"), ">");
				nomCanoniqueGeneriqueChamp = nomCanoniqueGeneriqueChamp.contains("<") ? StringUtils.substringBefore(nomCanoniqueGeneriqueChamp, "<") : nomCanoniqueGeneriqueChamp;
				nomCanoniqueGeneriqueChamp = nomCanoniqueGeneriqueChamp.contains(",") ? StringUtils.substringBefore(nomCanoniqueGeneriqueChamp, ",") : nomCanoniqueGeneriqueChamp;
				String cleChamp = classeChemin + "." + champVar;

				// Champs Solr du champ. 

				docChamp.addField("cle", cleChamp);

				docChamp.addField(concat("champVar_", langue, "_indexe_string"), champVar);
				docChamp.addField(concat("champVar_", langue, "_stocke_string"), champVar);
		
				for(String langue : autresLangues) { 
					String commentaireChamp = champQdox.getComment();

					String champVarLangue = regex("var\\." + langue + ": (.*)", commentaireChamp, 1);
					champVarLangue = champVarLangue == null ? champVar : champVarLangue;
		
					docChamp.addField(concat("champVar_", langue, "_indexe_string"), champVarLangue);
					docChamp.addField(concat("champVar_", langue, "_stocke_string"), champVarLangue);
				}  

//				docClasse.addField(concat("champEstChamp_indexe_boolean"), true);
//				docClasse.addField(concat("champEstChamp_stocke_boolean"), true);

				docChamp.addField(concat("champEstPublic_indexe_boolean"), champQdox.isPublic());
				docChamp.addField(concat("champEstPublic_stocke_boolean"), champQdox.isPublic());

				docChamp.addField(concat("champEstProtege_indexe_boolean"), champQdox.isProtected());
				docChamp.addField(concat("champEstProtege_stocke_boolean"), champQdox.isProtected());

				docChamp.addField(concat("champEstPrive_indexe_boolean"), champQdox.isPrivate());
				docChamp.addField(concat("champEstPrive_stocke_boolean"), champQdox.isPrivate());

				docChamp.addField(concat("champEstStatique_indexe_boolean"), champQdox.isStatic());
				docChamp.addField(concat("champEstStatique_stocke_boolean"), champQdox.isStatic());

				docChamp.addField(concat("champEstFinale_indexe_boolean"), champQdox.isFinal());
				docChamp.addField(concat("champEstFinale_stocke_boolean"), champQdox.isFinal());

				docChamp.addField(concat("champEstAbstrait_indexe_boolean"), champQdox.isAbstract());
				docChamp.addField(concat("champEstAbstrait_stocke_boolean"), champQdox.isAbstract());

				docChamp.addField(concat("champEstNatif_indexe_boolean"), champQdox.isNative());
				docChamp.addField(concat("champEstNatif_stocke_boolean"), champQdox.isNative());
//	
//				String varEnUS = regex("^var.enUS: (.*)", champQdox.getComment(), 1);
//				champ.nomChamp.frFR(champQdox.getName());
//				champ.nomChamp.enUS(StringUtils.isEmpty(varEnUS) ? champQdox.getName() : varEnUS);
//	
//				regexCommentaires(champQdox.getComment(), champ.commentaire);
//				regexRemplacerTout(champQdox.getComment(), champQdox.getCodeBlock(), champ.blocCode);
//				List<JavaAnnotation> annotations = champQdox.getAnnotations();
//				for(JavaAnnotation annotation : annotations) {
//				}
//				champ.classe_(this);
//				champ.initialiserLoinUnChamp(requeteSite);
//				tout.add(champ);
				clientSolr.add(docChamp);
			}
//			else if(membreQdox instanceof JavaConstructor) { 
//				JavaConstructor constructeurQdox = (JavaConstructor)membreQdox;
//				UnConstructeur constructeur = new UnConstructeur();
//				constructeur.requeteSite(requeteSite);
//				constructeur.constructeurQdox(constructeurQdox);
//				constructeur.champEstPublic(constructeurQdox.isPublic());
//				constructeur.champEstProtege(constructeurQdox.isProtected());
//				constructeur.champEstPrive(constructeurQdox.isPrivate());
//				constructeur.champEstStatique(constructeurQdox.isStatic());
//				constructeur.champEstFinale(constructeurQdox.isFinal());
//				JavaClass classeQdoxConstructeur = constructeurQdox.getDeclaringClass();
//				constructeur.nomConstructeur(constructeurQdox.getName());
//				regexCommentaires(constructeurQdox.getComment(), constructeur.commentaire);
//				regexRemplacerTout(constructeurQdox.getComment(), constructeurQdox.getSourceCode(), constructeur.codeSource);
//				List<JavaAnnotation> annotations = constructeurQdox.getAnnotations();
//				for(JavaAnnotation annotation : annotations) {
//				}
//				constructeur.classe_(this);
//				constructeur.initialiserLoinUnConstructeur(requeteSite);
//				tout.add(constructeur);
//			}
//			else if(membreQdox instanceof JavaMethod) {
//				JavaMethod methodeQdox = (JavaMethod)membreQdox;
//				Boolean ignorer = "true".equals(regex("ignorer: (.*)", methodeQdox.getComment(), 1));
//				if(!ignorer) {
//					UneMethode methode = new UneMethode();
//					methode.methodeQdox(methodeQdox);
//					methode.requeteSite(requeteSite);
//					methode.champEstPublic(methodeQdox.isPublic());
//					methode.champEstProtege(methodeQdox.isProtected());
//					methode.champEstPrive(methodeQdox.isPrivate());
//					methode.champEstStatique(methodeQdox.isStatic());
//					methode.champEstFinale(methodeQdox.isFinal());
//					JavaClass classeQdoxRetour = methodeQdox.getReturns();
//					String nomCanoniqueRetourComplet = null;
//					if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void"))
//						nomCanoniqueRetourComplet = classeQdoxRetour.getGenericCanonicalName();
//	//						methode.nomCanoniqueRetourComplet(classeQdoxRetour.getGenericCanonicalName());
//	
//					String nomCanoniqueRetourEnUS = regex("nomCanonique.enUS: (.*)", classeQdoxRetour.getComment(), 1);
//					methode.nomCanoniqueRetour.frFR(StringUtils.substringBefore(nomCanoniqueRetourComplet, "<"));    
//					methode.nomCanoniqueRetour.enUS(StringUtils.isEmpty(nomCanoniqueRetourEnUS) ? methode.nomCanoniqueRetour.frFR() : nomCanoniqueRetourEnUS);
//	
//					if(StringUtils.contains(nomCanoniqueRetourComplet, "<")) {
//						methode.nomCanoniqueRetourGenerique.frFR(StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueRetourComplet, ">"), "<"));  
//						methode.nomCanoniqueRetourGenerique.enUS(StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueRetourComplet, ">"), "<"));  
//					}
//					if(StringUtils.contains(methode.nomCanoniqueRetour.toString(), ".")) {
//						methode.nomSimpleRetour.frFR(StringUtils.substringAfterLast(methode.nomCanoniqueRetour.frFR(), "."));    
//						methode.nomSimpleRetour.enUS(StringUtils.substringAfterLast(methode.nomCanoniqueRetour.enUS(), "."));    
//						if(methode.nomCanoniqueRetourGenerique.estVide()) {
//							methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR());
//							methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS());
//						}
//						else {
//							if(methode.nomCanoniqueRetourGenerique.contient(".")) {
//								methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR(), "<", StringUtils.substringAfterLast(methode.nomCanoniqueRetourGenerique.frFR(), "."), ">");    
//								methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS(), "<", StringUtils.substringAfterLast(methode.nomCanoniqueRetourGenerique.enUS(), "."), ">");    
//							}
//							else {
//								methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR(), "<", methode.nomCanoniqueRetourGenerique.frFR(), ">");    
//								methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS(), "<", methode.nomCanoniqueRetourGenerique.enUS(), ">");    
//							}
//						}
//					}
//					else {
//						methode.nomSimpleRetour.frFR(methode.nomCanoniqueRetour.frFR());
//						methode.nomSimpleRetour.enUS(methode.nomCanoniqueRetour.enUS());
//						if(methode.nomCanoniqueRetourGenerique.estVide()) {
//							methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR());
//							methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS());
//						}
//						else {
//							methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR(), "<", methode.nomCanoniqueRetourGenerique.frFR(), ">");    
//							methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS(), "<", methode.nomCanoniqueRetourGenerique.enUS(), ">");    
//						}
//					}
//	
//					if(methode.nomCanoniqueRetourGenerique.estVide()) {
//						methode.nomCanoniqueRetourComplet.enUS(methode.nomCanoniqueRetour.enUS());
//						methode.nomCanoniqueRetourComplet.frFR(methode.nomCanoniqueRetour.frFR());
//					}
//					else {
//						methode.nomCanoniqueRetourComplet.enUS(methode.nomCanoniqueRetour.enUS(), "<", methode.nomCanoniqueRetourGenerique.enUS(), ">");
//						methode.nomCanoniqueRetourComplet.frFR(methode.nomCanoniqueRetour.frFR(), "<", methode.nomCanoniqueRetourGenerique.frFR(), ">");
//					}
//	
//					String varEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment(), 1);
//					methode.nomMethode.frFR(methodeQdox.getName());
//					methode.nomMethode.enUS(StringUtils.isEmpty(varEnUS) ? methodeQdox.getName() : varEnUS);
//	
//					List<JavaAnnotation> annotations = methodeQdox.getAnnotations();
//					methode.estSubstitue(false);
//					for(JavaAnnotation annotation : annotations) {
//						if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
//							methode.estTest(true);
//						}
//						if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
//							methode.estSubstitue(true);
//						}
//					}
//					List<JavaParameter> parametresQdox = methodeQdox.getParameters();
//	
//					if(!methode.estSubstitue && !methode.champEstStatique && !methode.champEstFinale && methodeQdox.getDeclaringClass().equals(classeQdox) 
//							&& methode.champEstProtege && parametresQdox.size() == 1 && classeQdoxRetour.isVoid()
//							&& StringUtils.startsWith(methodeQdox.getName(), "_")) {
//			
//						String nomEntite = StringUtils.substringAfter(methodeQdox.getName(), "_");
//						UnEntite entite = new UnEntite();
//						JavaClass classeEntite = parametresQdox.get(0).getJavaClass();
//						boolean typeCouverture = false;
//						String nomTypeOrigine = classeEntite.getGenericCanonicalName();
//						String nomType = nomTypeOrigine;
//						String typeNomCanonique = classeEntite.getCanonicalName();
//	//					JavaClass classeEntite = typeBricoleur.getClassByName(typeNomCanonique);
//						String listeNomTypeOrigineGenerique = null;
//						String listeNomTypeGenerique = null;
//						String listeNomTypeGeneriqueComplet = null;
//						String varCouverture = nomEntite + varCouvertureCapitalise.toString();
//	
//						entite.classe_(this);
//						entite.requeteSite(requeteSite);
//	
//						String varEntiteEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment(), 1);
//						entite.var.frFR(nomEntite);
//						entite.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? nomEntite : StringUtils.substringAfter(varEntiteEnUS, "_"));
//	
//						regexCommentaires(methodeQdox.getComment(), entite.commentaire);
//						regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), entite.codeSource);
//	//					for(int i = constructeursAucunParametres.size() - 1; i >= 0; i--) {  
//	//						JavaConstructor constructeur = constructeursAucunParametres.get(i);
//	//						String texteConstructeur = constructeur.getSourceCode();
//	////						{
//	////							Matcher recherche = Pattern.compile("\\t*" + nomEntite.toString() + varCouvertureCapitalise.toString() + ".*\\." + varCleUnique.toString() + "\\(([a-z]+)\\)").matcher(texteConstructeur);
//	////							if(recherche.find()) {
//	////								String s = recherche.group(1);
//	////								Boolean bool = Boolean.parseBoolean(s);
//	////								entite.cleUnique(bool);
//	////							}
//	////						}
//	////						{
//	////							Matcher recherche = Pattern.compile("\\t*" + nomEntite.toString() + varCouvertureCapitalise.toString() + ".*\\." + varIndexe.toString() + "\\(([a-z]+)\\)").matcher(texteConstructeur);
//	////							if(recherche.find()) {
//	////								String s = recherche.group(1);
//	////								Boolean bool = Boolean.parseBoolean(s);
//	////								entite.indexe(bool);
//	////							}
//	////						}
//	////						{
//	////							Matcher recherche = Pattern.compile("\\t*" + nomEntite.toString() + varCouvertureCapitalise.toString() + ".*\\." + varStocke.toString() + "\\(([a-z]+)\\)").matcher(texteConstructeur);
//	////							if(recherche.find()) {
//	////								String s = recherche.group(1);
//	////								Boolean bool = Boolean.parseBoolean(s);
//	////								entite.stocke(bool);
//	////							}
//	////						}
//	////						{
//	////							Matcher recherche = Pattern.compile("\\t*" + nomEntite.toString() + varCouvertureCapitalise.toString() + ".*\\." + varCrypte.toString() + "\\(([a-z]+)\\)").matcher(texteConstructeur);
//	////							if(recherche.find()) {
//	////								String s = recherche.group(1);
//	////								Boolean bool = Boolean.parseBoolean(s);
//	////								entite.crypte(bool);
//	////							}
//	////						}
//	////						{
//	////							Matcher recherche = Pattern.compile("\\t*" + nomEntite.toString() + varCouvertureCapitalise.toString() + ".*\\." + varExact.toString() + "\\(([a-z]+)\\)").matcher(texteConstructeur);
//	////							if(recherche.find()) {
//	////								String s = recherche.group(1);
//	////								Boolean bool = Boolean.parseBoolean(s);
//	////								entite.exact(bool);
//	////							}
//	////						}
//	////						{
//	////							Matcher recherche = Pattern.compile("\\t*" + nomEntite.toString() + varCouvertureCapitalise.toString() + ".*\\." + varSuggere.toString() + "\\(([a-z]+)\\)").matcher(texteConstructeur);
//	////							if(recherche.find()) {
//	////								String s = recherche.group(1);
//	////								Boolean bool = Boolean.parseBoolean(s);
//	////								entite.suggere(bool);
//	////							}
//	////						}
//	////						{
//	////							Matcher recherche = Pattern.compile("\\t*" + nomEntite.toString() + varCouvertureCapitalise.toString() + ".*\\." + varSauvegarde.toString() + "\\(([a-z]+)\\)").matcher(texteConstructeur);
//	////							if(recherche.find()) {
//	////								String s = recherche.group(1);
//	////								Boolean bool = Boolean.parseBoolean(s);
//	////								entite.sauvegarde(bool);
//	////							}
//	////						}
//	//						entite.cleUnique(StringUtils.equals(classe_.regex("cleUnique: (.*)", entite.commentaire.toString(), 1), "true"));
//	//						entite.indexe(StringUtils.equals(classe_.regex("indexe: (.*)", entite.commentaire.toString(), 1), "true"));
//	//						entite.stocke(StringUtils.equals(classe_.regex("stocke: (.*)", entite.commentaire.toString(), 1), "true"));
//	//						entite.crypte(StringUtils.equals(classe_.regex("crypte: (.*)", entite.commentaire.toString(), 1), "true"));
//	//						String exactStr = classe_.regex("exact: (.*)", entite.commentaire.toString(), 1);
//	//						if(exactStr != null)
//	//							entite.exact(StringUtils.equals(exactStr, "true"));
//	//						entite.suggere(StringUtils.equals(classe_.regex("suggere: (.*)", entite.commentaire.toString(), 1), "true"));
//	//						entite.sauvegarde(StringUtils.equals(classe_.regex("sauvegarde: (.*)", entite.commentaire.toString(), 1), "true"));
//	//						entite.incremente(StringUtils.equals(classe_.regex("incremente: (.*)", entite.commentaire.toString(), 1), "true"));
//	//			//					entite.dejaInitialise(couverture.dejaInitialise);
//	//					}
//			
//						if(classeEntite.getFullyQualifiedName().equals(nomCanoniqueCouvertureActuel)) {
//							nomType = StringUtils.substringBeforeLast(StringUtils.substringAfter(nomType, "<"), ">");
//							if(StringUtils.contains(nomType, "<"))
//								classeEntite = typeBricoleur.getClassByName(StringUtils.substringBefore(nomType, "<"));
//							else
//								classeEntite = typeBricoleur.getClassByName(nomType);
//							typeNomCanonique = classeEntite.getCanonicalName();
//							typeCouverture = true;
//							entite.couverture(true);
//						}
//			
//	//					if(classeEntite != null && (typeNomCanonique.equals(List.class.getCanonicalName()) || typeNomCanonique.equals(ArrayList.class.getCanonicalName()) || typeNomCanonique.equals(Set.class.getCanonicalName()) || typeNomCanonique.equals(HashSet.class.getCanonicalName()) || typeNomCanonique.equals(Stack.class.getCanonicalName()))) {
//	//						listeNomTypeOrigineGenerique = nomTypeOrigine;
//	//						listeNomTypeGeneriqueComplet = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineGenerique, "<"), ">");
//	//						listeNomTypeGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineGenerique, "<"), ">");
//	//						listeNomTypeGenerique = listeNomTypeGenerique.contains("<") ? StringUtils.substringBefore(listeNomTypeGenerique, "<") : listeNomTypeGenerique;
//	////						listeNomTypeGeneriqueComplet = StringUtils.contains(listeNomTypeGenerique, "<") ? StringUtils.substringBefore(listeNomTypeGenerique, "<") : listeNomTypeGenerique;
//	//						entite.nomCanoniqueGenerique(listeNomTypeGenerique);
//	//						entite.nomCompletGenerique(listeNomTypeGeneriqueComplet);
//	//						entite.nomCanoniqueComplet(typeNomCanonique, "<", listeNomTypeGeneriqueComplet, ">");
//	//						entite.nomSimpleComplet(StringUtils.substringAfterLast(typeNomCanonique, "."), "<", listeNomTypeGeneriqueComplet, ">");
//	//					}  
//						entite.classeQdox(classeEntite);
//						entite.methodeQdox(methodeQdox);
//	//					entite.classeEntite(classeEntite);
//	//					entite.nomCanonique(classeEntite.getCanonicalName());
//						entite.initialiserLoinUnEntite(requeteSite);
//						entites.add(entite);
//						if(entite.cleUnique)
//							varCleUniqueActuel.tout(entite.var);
//						if(entite.suggere)
//							varSuggereActuel.tout(entite.var);
//	
//						if(!entitesTout.contains(entite))
//							entitesTout.add(entite);
//	
//						tout.add(entite);
//						importationsAjouter(new Chaine().tout(typeNomCanonique));
//						importationsGenAjouter(new Chaine().tout(typeNomCanonique));
//						if(listeNomTypeGenerique != null) {
//							Chaine importation = new Chaine().tout(listeNomTypeGenerique);
//							importationsAjouter(importation);
//							importationsGenAjouter(importation);
//						}
//	
//						boolean etendCluster = entite.etendClasse(nomCanoniqueClusterActuel);
//						entite.etendCluster(etendCluster);
//						if(!etendCluster && entite.nomCanoniqueGenerique.pasVide()) {
//							boolean listeCluster = etendClasse(nomCanoniqueClusterActuel, entite.nomCanoniqueGenerique.toString());
//							entite.listeCluster(listeCluster);
//						}
//	
//						boolean etendPageXml = entite.etendClasse(nomCanoniquePageXmlActuel);
//						entite.etendPageXml(etendPageXml);
//						if(!etendPageXml && entite.nomCanoniqueGenerique.pasVide()) {
//							boolean listePageXml = etendClasse(nomCanoniquePageXmlActuel, entite.nomCanoniqueGenerique.toString());
//							entite.listePageXml(listePageXml);
//						}
//	
//						boolean etendPageParti = entite.etendClasse(nomCanoniquePagePartiActuel);
//						entite.etendPageParti(etendPageParti);
//						if(!etendPageParti && entite.nomCanoniqueGenerique.pasVide()) {
//							boolean listePageParti = etendClasse(nomCanoniquePagePartiActuel, entite.nomCanoniqueGenerique.toString());
//							entite.listePageParti(listePageParti);
//						}
//	
//	
//						boolean contientRequeteSite = contientChamp(varRequeteSite.toString(), entite.classeQdox);
//						entite.contientRequeteSite(contientRequeteSite);
//	
//						boolean contientSetterString = contientMethode(entite.var.toString(), classeQdoxString);
//						entite.contientSetterString(contientSetterString);
//	
//						entiteEstCmd(entite);
//					}
//					else {
//						regexCommentaires(methodeQdox.getComment(), methode.commentaire);
//						regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), methode.codeSource);
//						methode.classe_(this);
//						methode.initialiserLoinUneMethode(requeteSite);
//						methodes.add(methode);
//						tout.add(methode);
//					}
//				}
//			} 
		}
		clientSolr.add(docClasse);
		clientSolr.commit();
		clientSolr.deleteByQuery(concat("classeChemin", "_", langue, "_indexe_string") + ":\"" + classeChemin + "\" AND modifiee_indexe_date:[* TO " + modifiee + "-1MILLI]");
		clientSolr.commit(); 
	}
}
