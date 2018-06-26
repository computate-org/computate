package org.computate.tout.java;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Level;
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

/**
 * classeNomCanonique.enUS: org.computate.enUS.java.WatchDirectory
 */     
public class RegarderRepertoire {   
	/** 
	 * var.enUS: log
	 */
	protected final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	/** 
	 * var.enUS: observer
	 */
	protected WatchService observateur;
	/**
	 * var.enUS: keys
	 */
	protected Map<WatchKey, Path> cles = new LinkedHashMap<WatchKey, Path>();
	/**
	 * var.enUS: trace
	 */
	protected Boolean trace = false;
	/**
	 * var.enUS: paths
	 */
	protected ArrayList<Path> chemins = new ArrayList<Path>();
	/**
	 * var.enUS: testRunner
	 */
//	protected Class<?> coureurTest = SeulCoureurTestJUnit.class;
	protected ByteArrayOutputStream fluxSortie = new ByteArrayOutputStream();
//	protected PumpStreamHandler gestionnaireFluxPompe = new PumpStreamHandler(new ExecLogHandler(log, Level.INFO), new ExecLogHandler(log, Level.ERROR));
	protected DefaultExecutor executeur = new DefaultExecutor();
	protected ArrayList<String> cheminsBin = new ArrayList<String>();
	protected ArrayList<String> cheminsCheminClasse = new ArrayList<String>();
	protected ArrayList<String> cheminsÀRegarder = new ArrayList<String>();
	protected ArrayList<String> nomsMethodeTest = new ArrayList<String>();
	protected ArrayList<String> cheminsSource = new ArrayList<String>();
	protected ArrayList<String> toutCheminsSource = new ArrayList<String>();
	protected ArrayList<String> cheminsBibliotheque = new ArrayList<String>();
	protected String classeCheminRepertoireAppli;
	protected String cheminClasse;
	protected String cheminSrcMainJava;
	protected String cheminSrcGenJava;
	protected String cheminConfiguration;
	protected File fichierConfiguration;
	protected INIConfiguration configuration;

	public static void main(String[] args) throws Exception { 
		String classeCheminRepertoireAppli = args[0];
		RegarderRepertoire regarderRepertoire = new RegarderRepertoire();
		regarderRepertoire.classeCheminRepertoireAppli = classeCheminRepertoireAppli;

		regarderRepertoire.cheminSrcMainJava = classeCheminRepertoireAppli + "/src/main/java";
		regarderRepertoire.cheminSrcGenJava = classeCheminRepertoireAppli + "/src/gen/java";

		regarderRepertoire.cheminsBibliotheque.add(classeCheminRepertoireAppli + "/lib");
		regarderRepertoire.cheminsBibliotheque.add(classeCheminRepertoireAppli + "/lib-tomcat");
		regarderRepertoire.cheminsBibliotheque.add(classeCheminRepertoireAppli + "/lib-keycloak");

		regarderRepertoire.cheminsBin.add(classeCheminRepertoireAppli + "/bin");
		regarderRepertoire.cheminsBin.add(classeCheminRepertoireAppli + "/src/main/resources");

		regarderRepertoire.cheminConfiguration = classeCheminRepertoireAppli + "/config/computate.config";
		regarderRepertoire.fichierConfiguration = new File(regarderRepertoire.cheminConfiguration);
		Configurations configurations = new Configurations();
		regarderRepertoire.configuration = configurations.ini(regarderRepertoire.fichierConfiguration);

		regarderRepertoire.trace = true;
		try {
			regarderRepertoire.initialiserRegarderRepertoire();
			regarderRepertoire.ajouterCheminsARegarder();
			regarderRepertoire.traiterEvenements();
		}
		catch(Exception e) {
			System.err.println("Erreur pendant traiterEvenements. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
	} 
	
	public void initialiserRegarderRepertoire() throws Exception {
		observateur = FileSystems.getDefault().newWatchService();
//		executeur.setStreamHandler(gestionnaireFluxPompe);

		cheminsÀRegarder.add(cheminSrcMainJava);
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

		cheminClasse = StringUtils.join(cheminsCheminClasse, File.pathSeparator);
	}

	public void ajouterCheminsARegarder() {
		for(String cheminÀRegarder : cheminsÀRegarder) {
			try {
				chemins.add(enregistrerTout(Paths.get(cheminÀRegarder)));
				log.info("Regarder: {}", cheminÀRegarder);
			} catch (IOException e) { 
				log.error("Erreur à ajouter chemin pour regarder.", e);
			}
		}
	}   

	@SuppressWarnings("unchecked") static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}

	private void enregistrer(Path dir) throws IOException { 
		WatchKey key = dir.register(observateur, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		if (trace) {
			cles.get(key);
		}
		cles.put(key, dir);
	}

	protected Path enregistrerTout(final Path démarrer) throws IOException { 
		Files.walkFileTree(démarrer, new SimpleFileVisitor<Path>() {
			@Override public FileVisitResult preVisitDirectory(Path répertoire, BasicFileAttributes champs) throws IOException {
				enregistrer(répertoire);
				return FileVisitResult.CONTINUE;
			}
		});
		return démarrer;
	} 

	protected void traiterEvenements() {
		for (;;) {

			WatchKey clé;
			try {
				clé = observateur.take();
			} catch (InterruptedException x) {  
				return;
			}  

			Path répertoire = cles.get(clé);
			if (répertoire == null) {
				System.err.println("Cle de surveillance n'est pas reconnue !");
				continue;
			}

			for (WatchEvent<?> event : clé.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (kind == OVERFLOW) {
					continue;
				}

				WatchEvent<Path> événementSurveillance = cast(event);
				Path nom = événementSurveillance.context();
				Path enfant = répertoire.resolve(nom);

				try { 
					String classeCheminAbsolu = enfant.toAbsolutePath().toString();  
					CommandLine ligneCommande = CommandLine.parse("mvn -q exec:java -Dexec.mainClass=" + RegarderClasse.class.getCanonicalName() + " -Dexec.args=\"" + classeCheminRepertoireAppli + " " + classeCheminAbsolu + "\"");
					executeur.execute(ligneCommande); 

//					bricoleur = null;
//					bricoleur = new JavaProjectBuilder();
//					for(String cheminSource : toutCheminsSource) {
//						File répertoireSource = new File(cheminSource);
//						bricoleur.addSourceFolder(répertoireSource);
//					}
//					System.out.println("cheminAbsolu : " + classeCheminAbsolu);
//					if (classeCheminAbsolu.endsWith(".java")) { 
//						indexerClasse(classeCheminAbsolu);
//						ecrireClasseGen(classeCheminAbsolu);
//					}
				} catch (Exception e) {  
					String cheminAbsolu = enfant.toAbsolutePath().toString();  
					log.error("Une Problème d'exécution de RegarderRepertoire: " + cheminAbsolu, e);
				} 

				if (kind == ENTRY_CREATE) {
					try {
						if (Files.isDirectory(enfant, NOFOLLOW_LINKS)) {
							enregistrerTout(enfant);
						}
					} catch (IOException x) {
						// ignorer pour simplifier le log.
					}
				}
			}

			boolean valide = clé.reset();
			if (!valide) {
				cles.remove(clé);

				if (cles.isEmpty()) {
					break;
				} 
			}
		}
	}  
//
//		public void entiteEstCmd(UnEntite entite) throws Exception {
//			if(entite.nomSimple.equals("Cmd")) { 
//				String commentaire = entite.commentaireQdox;
//				Matcher m = Pattern.compile("^(cmd\\.param(\\d+)).val: (.*)", Pattern.MULTILINE).matcher(commentaire);
//				boolean trouve = m.find();
//				
//				while(trouve) {
//					String prefixe = m.group(1);
//					Integer paramNumero = Integer.parseInt(m.group(2));
//					String paramVal = m.group(3);
//					UnEntite paramEntite = new UnEntite();
//
//					if(StringUtils.isEmpty(paramVal))
//						paramVal = regex("^cmd\\.param" + paramNumero + ".val.var: (.*)", commentaire, 1);
//					else
//						paramVal = "\"" + paramVal + "\"";
//		
//					paramEntite.declarer(true);
//					paramEntite.classe_(this);
//					paramEntite.requeteSite(requeteSite);
//					paramEntite.couverture(true);
//		
//					paramEntite.var.frFR(entite.var.frFR() + "Param" + paramNumero);
//					paramEntite.var.enUS(entite.var.enUS() + "Param" + paramNumero);
//
//					paramEntite.codeSource.tabLigne(0);
//					paramEntite.codeSource.tabLigne(2, "if(", entite.var, " != null) {");
//					paramEntite.codeSource.tabLigne(3, "CmdParam o = new CmdParam();");
//					paramEntite.codeSource.tabLigne(3, "o.valeur(", paramVal, ");");
//					{
//						Matcher m2 = Pattern.compile("^" + prefixe + ".description.enUS: (.*)", Pattern.MULTILINE).matcher(commentaire);
//						boolean trouve2 = m2.find();
//						while(trouve2) {
//							String ligne = m2.group(1);
//							paramEntite.commentaire.enUSLigne(ligne);
//							paramEntite.codeSource.tabLigne(3, "o.description.enUS(\"", ligne.replace("\"", "\\\""), "\");");
//							trouve2 = m2.find();
//						}
//					}
//					{
//						Matcher m2 = Pattern.compile("^" + prefixe + ".description.frFR: (.*)", Pattern.MULTILINE).matcher(commentaire);
//						boolean trouve2 = m2.find();
//						while(trouve2) {
//							String ligne = m2.group(1);
//							paramEntite.commentaire.frFRLigne(ligne);
//							paramEntite.codeSource.tabLigne(3, "o.description.frFR(\"", ligne.replace("\"", "\\\""), "\");");
//							trouve2 = m2.find();
//						}
//					}
//					paramEntite.codeSource.tabLigne(3, entite.var, ".paramsAjouter(o);");
//					paramEntite.codeSource.tabLigne(3, "c.o(o);");
//					paramEntite.codeSource.tabLigne(2, "}");
//					paramEntite.codeSource.tab(1);
//		
//					paramEntite.classeQdox(classeQdoxCmdParamActuel);
////					paramEntite.methodeQdox(methodeQdox);
//					paramEntite.initialiserLoinUnEntite(requeteSite);
//					entites.add(paramEntite);
//					if(!entitesTout.contains(paramEntite))
//						entitesTout.add(paramEntite);
//					tout.add(paramEntite);
//					importationsAjouter(new Chaine().tout(classeQdoxCmdParamActuel.getCanonicalName()));
//					importationsGenAjouter(new Chaine().tout(classeQdoxCmdParamActuel.getCanonicalName()));
//
//					trouve = m.find();
//				}
//			}
//		}
//		clientSolr.add(doc);
//		clientSolr.commit();
//	}
//
//	public Chaine texteGen(Chaine o) throws Exception {     
//		o.requeteSite(requeteSite);
//
//		o.tout(texteClasseAvant);
//
//		o.tout("package ", nomEnsemble.frFR, ";\n");
//		o.tout("\n");
////		o.tout("import org.computate.dév.java.*;\n");
////		o.tout("import org.computate.dév.temporal.*;\n");
//		for(Chaine c : importationsGen) {
//			o.tout("import ", c, ";\n");
//		}
//		o.tout("\n");
////		o.tout("public class ", nomSimple.frFR, " extends ", nomSimple.frFR, "Généré<", nomSimpleSuper.frFR, "> {\n");
//		o.tout("public abstract class ", nomSimpleGen.frFR, classeGenSuffixeGenerique);
//		if(nomSimpleBase.pasVide()) {
//			if(!nomSimpleBase.toString().equals("DEV"))
//				o.tout(" extends ", nomSimpleBase);
//		}
//		else if(nomCanoniqueSuper.pasVide()) {
//			o.tout(" extends ", nomSimpleSuper);
//		}
//
////		if(nomCanoniqueSuper.pasVide() && nomCanoniqueSuper.terminePasPar(SuffixeGen)) {
////			o.tout(" extends ", nomSimpleSuper);
////		}
////		else if(nomCanoniqueBase.pasVide() && nomCanoniqueBase.contient(".")) {
////			o.tout(" extends ", nomSimpleBase);
////		}
//
//		if(implementations.size() > 0) {
//			o.tout(" implements");
//			for(Chaine c : implementations) {
//				o.enUS(" ", StringUtils.substringAfterLast(c.enUS(), "."));
//				o.frFR(" ", StringUtils.substringAfterLast(c.frFR(), "."));
//			}
//		}
//		o.toutLigne(" {\n");
////		o.toutLigne("\tprotected Logger log = LoggerFactory.getLogger(getClass());");
////		o.tout("\n");
//		o.tout("\tpublic ", nomSimpleGen, "() {\n");
//		o.tout("\t\tsuper();\n");
//		o.tout(texteClasseConstructeur);
//		o.tout("\t}\n");
//		o.tout("\n");
////		o.tout(texteClasseConstructeurApres);
//
//		ecrireEntitesGen(o);
//		if(ecrireIndexer)
//			ecrireIndexer(o);
//		ecrireEntitesInitialiser(o);
//		ecrireClasseGenere(o);
//		if(ecrireSh)
//			ecrireSh(o);
//		if(ecrireCode)
//			ecrireCode(o);
//		if(ecrireShHtml)
//			ecrireShHtml(o);
//		if(ecrireHtml)
//			ecrireHtml(o);
//
//		o.tout(texteClasseApres);
//
//		ecrireClasseFin(o);
//
//		o.tout("}\n");
//		return o;
//	}
}
