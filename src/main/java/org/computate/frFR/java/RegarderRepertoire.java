package org.computate.frFR.java;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;

/**
 * nomCanonique.enUS: org.computate.enUS.java.WatchDirectory
 */    
public class RegarderRepertoire {  
	/** 
	 * var.enUS: log
	 */
	protected final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
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
//	protected Class<?> coureurTest = SeulCoureurTestJUnit.class;
	/**
	 * var.enUS: outputStream
	 */
	protected ByteArrayOutputStream fluxSortie = new ByteArrayOutputStream();
//	protected PumpStreamHandler gestionnaireFluxPompe = new PumpStreamHandler(new ExecLogHandler(log, Level.INFO), new ExecLogHandler(log, Level.ERROR));
	/**
	 * var.enUS: executor
	 */
	protected DefaultExecutor executeur = new DefaultExecutor();
	/**
	 * var.enUS: pathsBin
	 */
	protected ArrayList<String> cheminsBin = new ArrayList<String>();
	/**
	 * var.enUS: classPathPaths
	 */
	protected ArrayList<String> cheminsCheminClasse = new ArrayList<String>();
	/**
	 * var.enUS: pathsToWatch
	 */
	protected ArrayList<String> cheminsARegarder = new ArrayList<String>();
	/**
	 * var.enUS: testMethodNames
	 */
	protected ArrayList<String> nomsMethodeTest = new ArrayList<String>();
	/**
	 * var.enUS: sourcePaths
	 */
	protected ArrayList<String> cheminsSource = new ArrayList<String>();
	/**
	 * var.enUS: allSourcePaths
	 */
	protected ArrayList<String> toutCheminsSource = new ArrayList<String>();
	/**
	 * var.enUS: libraryPaths
	 */
	protected ArrayList<String> cheminsBibliotheque = new ArrayList<String>();
	/**
	 * var.enUS: classAppDirPath
	 */
	protected String classeCheminRepertoireAppli;
	/**
	 * var.enUS: classPath
	 */
	protected String classeChemin;
	/**
	 * var.enUS: srcMainJavaPath
	 */
	protected String cheminSrcMainJava;
	/**
	 * var.enUS: srcGenJavaPath
	 */
	protected String cheminSrcGenJava;
	/**
	 * var.enUS: configPath
	 */
	protected String cheminConfig;
	/**
	 * var.enUS: configFile
	 */
	protected File fichierConfig;
	/**
	 * var.enUS: configuration
	 */
	protected INIConfiguration configuration;
	/**
	 * var.enUS: appName
	 */
	protected String appliNom;
	/**
	 * var.enUS: appPath
	 */
	protected String appliChemin;
	/**
	 * var.enUS: appComputatePath
	 */
	protected String appliComputateChemin;

	 /**
	 * r: appliNom
	 * r.enUS: appName
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: cheminsBin
	 * r.enUS: pathsBin
	 * r: appliComputateChemin
	 * r.enUS: appComputatePath
	 * r: initialiserRegarderRepertoire
	 * r.enUS: initializeWatchDirectory
	 * r: ajouterCheminsARegarder
	 * r.enUS: addPathsToWatch
	 * r: Erreur pendant traiterEvenements. 
	 * r.enUS: Error during handleEvents. 
	 * r: traiterEvenements
	 * r.enUS: handleEvents
	 * r: RegarderRepertoire
	 * r.enUS: WatchDirectory
	 * r: regarderRepertoire
	 * r.enUS: watchDirectory
	 * r: classeCheminRepertoireAppli
	 * r.enUS: classAppDirPath
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: cheminConfig
	 * r.enUS: configPath
	 * r: fichierConfig
	 * r.enUS: configFile
	 */
	public static void main(String[] args) throws Exception { 
		String appliNom = System.getenv("appliNom");
		String appliChemin = System.getenv("appliChemin");
		String appliComputateChemin = System.getenv("appliComputateChemin");
		RegarderRepertoire regarderRepertoire = new RegarderRepertoire();
		regarderRepertoire.appliNom = appliNom;
		regarderRepertoire.appliChemin = appliChemin;
		regarderRepertoire.appliComputateChemin = appliComputateChemin;
		regarderRepertoire.classeCheminRepertoireAppli = appliChemin;

		regarderRepertoire.cheminSrcMainJava = appliChemin + "/src/main/java";
		regarderRepertoire.cheminSrcGenJava = appliChemin + "/src/gen/java";
		regarderRepertoire.cheminsBin.add(appliChemin + "/src/main/resources");

		regarderRepertoire.cheminConfig = appliChemin + "/config/" + appliNom + ".config";
		regarderRepertoire.fichierConfig = new File(regarderRepertoire.cheminConfig);
		Configurations configurations = new Configurations();
		regarderRepertoire.configuration = configurations.ini(regarderRepertoire.fichierConfig);

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
	/*
	 * r: cles
	 * r: observateur
	 * r.enUS: observer
	 * r.enUS: keys
	 * r: fluxSortie
	 * r.enUS: outputStream
	 * r: executeur
	 * r.enUS: executor
	 * r: cheminsCheminClasse
	 * r.enUS: classPathPaths
	 * r: nomsMethodeTest
	 * r.enUS: testMethodNames
	 * r: cheminsBibliotheque
	 * r.enUS: libraryPaths
	 * r: classeChemin
	 * r.enUS: classPath
	 */

	/**
	 * var.enUS: initializeWatchDirectory
	 * r: observateur
	 * r.enUS: observer
	 * r: cheminsARegarder
	 * r.enUS: pathsToWatch
	 * r: cheminsSource
	 * r.enUS: sourcePaths
	 * r: toutCheminsSource
	 * r.enUS: allSourcePaths
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: cheminsBin
	 * r.enUS: pathsBin
	 * r: cheminsCheminClasse
	 * r.enUS: classPathPaths
	 * r: cheminsBibliotheque
	 * r.enUS: libraryPaths
	 * r: classeChemin
	 * r.enUS: classPath
	 */
	public void initialiserRegarderRepertoire() throws Exception {
		observateur = FileSystems.getDefault().newWatchService();
//		executeur.setStreamHandler(gestionnaireFluxPompe);

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

		classeChemin = StringUtils.join(cheminsCheminClasse, File.pathSeparator);
	}

	/** 
	 * var.enUS: addPathsToWatch
	 * r: cheminsARegarder
	 * r.enUS: pathsToWatch
	 * r: cheminARegarder
	 * r.enUS: pathToWatch
	 * r: chemins
	 * r.enUS: paths
	 * r: enregistrerTout
	 * r.enUS: saveAll
	 * r: Regarder: 
	 * r.enUS: Watch: 
	 * r: Erreur à ajouter chemin pour regarder.
	 * r.enUS: Error adding path to watch. 
	 */  
	public void ajouterCheminsARegarder() {
		for(String cheminARegarder : cheminsARegarder) {
			try {
				chemins.add(enregistrerTout(Paths.get(cheminARegarder)));
				log.info("Regarder: {}", cheminARegarder);
			} catch (IOException e) { 
				log.error("Erreur à ajouter chemin pour regarder.", e);
			}
		}
	}      

	/**
	 * param1.var.enUS: event
	 * r: evenement
	 * r.enUS: event
	 */
	@SuppressWarnings("unchecked") static <T> WatchEvent<T> cast(WatchEvent<?> evenement) {
		return (WatchEvent<T>) evenement;
	}

	/**
	 * var.enUS: save
	 * param1.var.enUS: dirPath
	 * r: regarderCle
	 * r.enUS: watchKey
	 * r: cheminRepertoire
	 * r.enUS: dirPath
	 * r: observateur
	 * r.enUS: observer
	 * r: cles
	 * r.enUS: keys
	 */
	private void enregistrer(Path cheminRepertoire) throws IOException { 
		WatchKey regarderCle = cheminRepertoire.register(observateur, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		if (trace) {
			cles.get(regarderCle);
		}
		cles.put(regarderCle, cheminRepertoire);
	}

	/**
	 * var.enUS: saveAll
	 * param1.var.enUS: start
	 * r: repertoire
	 * r.enUS: dir
	 * r: champs
	 * r.enUS: fields
	 * r: enregistrer
	 * r.enUS: save
	 * r: demarrer
	 * r.enUS: start
	 */
	protected Path enregistrerTout(final Path demarrer) throws IOException { 
		Files.walkFileTree(demarrer, new SimpleFileVisitor<Path>() {
			@Override public FileVisitResult preVisitDirectory(Path repertoire, BasicFileAttributes champs) throws IOException {
				enregistrer(repertoire);
				return FileVisitResult.CONTINUE;
			}
		});
		return demarrer;
	} 

	/*
	 * var.enUS: handleEvents
	 * r: fluxSortie
	 * r.enUS: outputStream
	 * r: cheminsBin
	 * r.enUS: pathsBin
	 * r: cheminsCheminClasse
	 * r.enUS: classPathPaths
	 * r: cheminsARegarder
	 * r.enUS: pathsToWatch
	 * r: nomsMethodeTest
	 * r.enUS: testMethodNames
	 * r: cheminsSource
	 * r.enUS: sourcePaths
	 * r: toutCheminsSource
	 * r.enUS: allSourcePaths
	 * r: cheminsBibliotheque
	 * r.enUS: libraryPaths
	 * r: classeCheminRepertoireAppli
	 * r.enUS: classAppDirPath
	 * r: classeChemin
	 * r.enUS: classPath
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: cheminConfig
	 * r.enUS: configPath
	 * r: fichierConfig
	 * r.enUS: configFile
	 * r: appliNom
	 * r.enUS: appName
	 * r: chemins
	 * r.enUS: paths
	 */

	/** 
	 * var.enUS: handleEvents
	 * r: classeCheminAbsolue
	 * r.enUS: classAbsolutePath
	 * r: executeur
	 * r.enUS: executor
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: appliComputateChemin
	 * r.enUS: appComputatePath
	 * r: observateur
	 * r.enUS: observer
	 * r: cles
	 * r.enUS: keys
	 * r: enregistrerTout
	 * r.enUS: saveAll
	 * r: RegarderClasse
	 * r.enUS: WatchClass
	 * r: regarderCle
	 * r.enUS: watchKey
	 * r: cheminRepertoire
	 * r.enUS: dirPath
	 * r: Cle de surveillance n'est pas reconnue !
	 * r.enUS: Watch key is not recognized!
	 * r: evenementSurveillance
	 * r.enUS: watchEvent
	 * r: cheminRelatif
	 * r.enUS: relativePath
	 * r: cheminComplet
	 * r.enUS: completePath
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: ligneCommande
	 * r.enUS: commandLine
	 * r: repertoireTravail
	 * r.enUS: workDir
	 * r: Ignorer pour simplifier le log.
	 * r.enUS: Ignore to simplify the log.
	 * r: valide
	 * r.enUS: valid
	 * r: classeCheminRepertoireAppli
	 * r.enUS: classAppDirPath
	 */
	protected void traiterEvenements() {
		for (;;) {

			WatchKey regarderCle;
			try {
				regarderCle = observateur.take();
			} catch (InterruptedException x) {  
				return;
			}  

			Path cheminRepertoire = cles.get(regarderCle);
			if (cheminRepertoire == null) {
				System.err.println("Cle de surveillance n'est pas reconnue !");
				continue;
			}

			for (WatchEvent<?> event : regarderCle.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (kind == OVERFLOW) {
					continue;
				}

				WatchEvent<Path> evenementSurveillance = cast(event);
				Path cheminRelatif = evenementSurveillance.context();
				Path cheminComplet = cheminRepertoire.resolve(cheminRelatif);

				try { 
					String classeCheminAbsolu = cheminComplet.toAbsolutePath().toString();   
					String cp = FileUtils.readFileToString(new File(appliChemin + "/config/cp.txt"), "UTF-8");
					CommandLine ligneCommande = CommandLine.parse("java -cp \"" + cp + ":" + appliChemin + "/target/classes\" " + RegarderClasse.class.getCanonicalName() + " \"" + classeCheminRepertoireAppli + "\" \"" + classeCheminAbsolu + "\"");
					File repertoireTravail = new File(appliComputateChemin);

					executeur.setWorkingDirectory(repertoireTravail);
					executeur.execute(ligneCommande); 
				} catch (Exception e) {  
					log.error("Une Problème d'exécution de RegarderRepertoire: " + cheminComplet.toAbsolutePath().toString(), e);
				} 

				if (kind == ENTRY_CREATE) {
					try {
						if (Files.isDirectory(cheminComplet, NOFOLLOW_LINKS)) {
							enregistrerTout(cheminComplet);
						}
					} catch (IOException x) {
						// Ignorer pour simplifier le log.
					}
				}
			}

			boolean valide = regarderCle.reset();
			if (!valide) {
				cles.remove(regarderCle);

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
