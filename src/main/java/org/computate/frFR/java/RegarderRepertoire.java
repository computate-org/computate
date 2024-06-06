/*
 * Copyright (c) 2018-2022 Computate Limited Liability Company in Utah, USA. 
 *
 * This program and the accompanying materials are made available under the
 * terms of the GNU GENERAL PUBLIC LICENSE Version 3 which is available at
 * 
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 * 
 * You may not propagate or modify a covered work except as expressly provided 
 * under this License. Any attempt otherwise to propagate or modify it is void, 
 * and will automatically terminate your rights under this License (including 
 * any patent licenses granted under the third paragraph of section 11).
 */
package org.computate.frFR.java;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.JinjavaConfig;
import com.hubspot.jinjava.loader.FileLocator;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * NomCanonique.enUS: org.computate.enUS.java.WatchDirectory
 */    
public class RegarderRepertoire {  
	/** 
	 * Var.enUS: log
	 */
	protected final Logger LOG = org.slf4j.LoggerFactory.getLogger(getClass());
	
	/** 
	 * Var.enUS: observer
	 */
	protected WatchService observateur;
	/**
	 * Var.enUS: keys
	 */
	protected Map<WatchKey, Path> cles = new LinkedHashMap<WatchKey, Path>();
	/**
	 * Var.enUS: trace
	 */
	protected Boolean trace = false;
	/**
	 * Var.enUS: paths
	 */
	protected ArrayList<Path> chemins = new ArrayList<Path>();
//	protected Class<?> coureurTest = SeulCoureurTestJUnit.class;
	/**
	 * Var.enUS: outputStream
	 */
	protected ByteArrayOutputStream fluxSortie = new ByteArrayOutputStream();
//	protected PumpStreamHandler gestionnaireFluxPompe = new PumpStreamHandler(new ExecLogHandler(log, Level.INFO), new ExecLogHandler(log, Level.ERROR));
	/**
	 * Var.enUS: executor
	 */
	protected DefaultExecutor executeur = new DefaultExecutor();
	/**
	 * Var.enUS: pathsBin
	 */
	protected ArrayList<String> cheminsBin = new ArrayList<String>();
	/**
	 * Var.enUS: classPathPaths
	 */
	protected ArrayList<String> cheminsCheminClasse = new ArrayList<String>();
	/**
	 * Var.enUS: pathsToWatch
	 */
	protected ArrayList<String> cheminsARegarder = new ArrayList<String>();
	/**
	 * Var.enUS: testMethodNames
	 */
	protected ArrayList<String> nomsMethodeTest = new ArrayList<String>();
	/**
	 * Var.enUS: sourcePaths
	 */
	protected ArrayList<String> cheminsSource = new ArrayList<String>();
	/**
	 * Var.enUS: RELATIVE_PATHS_TO_WATCH
	 */
	protected ArrayList<String> CHEMINS_RELATIFS_A_REGARDER = new ArrayList<String>();
	/**
	 * Var.enUS: allSourcePaths
	 */
	protected ArrayList<String> toutCheminsSource = new ArrayList<String>();
	/**
	 * Var.enUS: libraryPaths
	 */
	protected ArrayList<String> cheminsBibliotheque = new ArrayList<String>();
	/**
	 * Var.enUS: classAppDirPath
	 */
	protected String classeCheminRepertoireAppli;
	/**
	 * Var.enUS: classPath
	 */
	protected String classeChemin;
	/**
	 * Var.enUS: srcMainJavaPath
	 */
	protected String cheminSrcMainJava;
	/**
	 * Var.enUS: srcGenJavaPath
	 */
	protected String cheminSrcGenJava;
	/**
	 * Var.enUS: configPath
	 */
	protected String configChemin;
	/**
	 * Var.enUS: configFile
	 */
	protected File fichierConfig;
	/**
	 * Var.enUS: configuration
	 */
	protected JsonObject configuration;
	protected String langueNom;
	/**
	 * Var.enUS: SITE_NAME
	 */
	protected String SITE_NOM;
	/**
	 * Var.enUS: SITE_PATH
	 */
	protected String SITE_CHEMIN;
	protected String SITE_PREFIXE;
	protected String COMPUTATE_SRC;

	 /**
	 * r: SITE_NOM
	 * r.enUS: SITE_NAME
	 * r: SITE_CHEMIN
	 * r.enUS: SITE_PATH
	 * r: cheminsBin
	 * r.enUS: pathsBin
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
	 * r: configChemin
	 * r.enUS: configPath
	 * r: fichierConfig
	 * r.enUS: configFile
	 */
	public static void main(String[] args) throws Exception { 
		try {
			String lang = Optional.ofNullable(System.getenv("SITE_LANG")).orElse("frFR");
			String appComputate = System.getenv("COMPUTATE_SRC");
			Jinjava jinjava = ConfigSite.getJinjava();
			JsonObject classeLangueConfig = ConfigSite.getLangueConfigGlobale(jinjava, appComputate, lang);
			String SITE_NOM = System.getenv(classeLangueConfig.getString("var_SITE_NOM"));
			String SITE_CHEMIN = System.getenv(classeLangueConfig.getString("var_SITE_CHEMIN"));
			String SITE_PREFIXE = System.getenv(classeLangueConfig.getString("var_SITE_PREFIXE"));
			Boolean REGARDER = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_REGARDER"))).orElse("true"));
			Boolean GENERER = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_GENERER"))).orElse("true"));

			RegarderRepertoire regarderRepertoire = new RegarderRepertoire();
			regarderRepertoire.langueNom = lang;
			regarderRepertoire.SITE_NOM = SITE_NOM;
			regarderRepertoire.SITE_CHEMIN = SITE_CHEMIN;
			regarderRepertoire.SITE_PREFIXE = SITE_PREFIXE;
			regarderRepertoire.COMPUTATE_SRC = appComputate;
			regarderRepertoire.classeCheminRepertoireAppli = SITE_CHEMIN;

			regarderRepertoire.cheminSrcMainJava = SITE_CHEMIN + "/src/main/java";
			regarderRepertoire.cheminSrcGenJava = SITE_CHEMIN + "/src/gen/java";
			regarderRepertoire.cheminsBin.add(SITE_CHEMIN + "/src/main/resources");

			regarderRepertoire.configuration = ConfigSite.getConfiguration(jinjava, classeLangueConfig);

			regarderRepertoire.trace = true;
			regarderRepertoire.initialiserRegarderRepertoire(classeLangueConfig);
			regarderRepertoire.ajouterCheminsARegarder(classeLangueConfig, REGARDER);

			if(REGARDER) {
				indexerClasses(SITE_CHEMIN, classeLangueConfig);
				indexerClasses(SITE_CHEMIN, classeLangueConfig);
				indexerClasses(SITE_CHEMIN, classeLangueConfig);
				System.out.println(classeLangueConfig.getString(ConfigCles.str_Pret));
				regarderRepertoire.traiterEvenements(classeLangueConfig);
			} else {
				indexerClasses(SITE_CHEMIN, classeLangueConfig);
				indexerClasses(SITE_CHEMIN, classeLangueConfig);
				if(GENERER) {
					indexerEcrireClasses(SITE_CHEMIN, classeLangueConfig);
				} else {
					indexerClasses(SITE_CHEMIN, classeLangueConfig);
				}
			}
		}
		catch(Exception e) {
			System.err.println("Erreur pendant traiterEvenements. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
	} 

	public static void indexerClasses(String SITE_CHEMIN, JsonObject classeLangueConfig) throws Exception {
		String classeLangueNom = StringUtils.defaultString(System.getenv("SITE_LANG"), "frFR");
		File dir = new File(String.format("%s/src/main/java", SITE_CHEMIN));

		try (Stream<Path> stream = Files.walk(Paths.get(dir.getAbsolutePath()))) {
			stream.filter(Files::isRegularFile)
					.filter(chemin -> chemin.toString().endsWith(".java"))
					.filter(chemin -> {
						try {
							return !FileUtils.readFileToString(chemin.toFile(), "UTF-8").contains("* Translate: false");
						} catch(Exception ex) {
							return false;
						}
					})
			.forEach(chemin -> {
				String cheminStr = chemin.toString();
				RegarderClasse regarderClasse = new RegarderClasse();
				try {
					regarderClasse.setArgs(new String[] {SITE_CHEMIN, cheminStr});
					regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig);
					SolrInputDocument classeDoc = new SolrInputDocument();
					regarderClasse.indexerClasse(cheminStr, classeDoc, classeLangueNom);
					System.out.println(String.format("%s %s", classeLangueConfig.getString(ConfigCles.var_Indexe), cheminStr));
				} catch(Exception ex) {
					System.err.println(String.format("An exception occured while indexing files: %s", ExceptionUtils.getStackTrace(ex)));
				}
			});
		}
	}


	public static void indexerEcrireClasses(String SITE_CHEMIN, JsonObject classeLangueConfig) throws Exception {
		String appComputate = System.getenv("COMPUTATE_SRC");
		String classeLangueNom = StringUtils.defaultString(System.getenv("SITE_LANG"), "frFR");
		File dir = new File(String.format("%s/src/main/java", SITE_CHEMIN));

		try (Stream<Path> stream = Files.walk(Paths.get(dir.getAbsolutePath()))) {
			stream.filter(Files::isRegularFile)
					.filter(chemin -> chemin.toString().endsWith(".java"))
					.filter(chemin -> {
						try {
							return !FileUtils.readFileToString(chemin.toFile(), "UTF-8").contains("* Translate: false");
						} catch(Exception ex) {
							return false;
						}
					})
			.forEach(chemin -> {
				String cheminStr = chemin.toString();
				RegarderClasse regarderClasse = new RegarderClasse();
				try {
					regarderClasse.setArgs(new String[] {SITE_CHEMIN, cheminStr});
					regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig);
					SolrInputDocument classeDoc = new SolrInputDocument();
					regarderClasse.indexerClasse(cheminStr, classeDoc, classeLangueNom);
					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, classeLangueNom, classeLangueNom, classeLangueConfig);
					System.out.println(String.format("%s %s", classeLangueConfig.getString(ConfigCles.var_Indexe), cheminStr));
				} catch(Exception ex) {
					System.err.println(String.format("An exception occured while indexing files: %s", ExceptionUtils.getStackTrace(ex)));
				}
			});
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
	 * Var.enUS: initializeWatchDirectory
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
	 * r: CHEMINS_RELATIFS_A_REGARDER
	 * r.enUS: RELATIVE_PATHS_TO_WATCH
	 * r: SITE_CHEMIN
	 * r.enUS: SITE_PATH
	 * r: SITE_NOM
	 * r.enUS: SITE_NAME
	 */
	public void initialiserRegarderRepertoire(JsonObject classeLangueConfig) throws Exception {
		observateur = FileSystems.getDefault().newWatchService();
		String cheminRelatifARegarder = configuration.getString(classeLangueConfig.getString(ConfigCles.var_CHEMINS_RELATIFS_A_REGARDER));
		String cheminARegarder = SITE_CHEMIN + "/" + cheminRelatifARegarder;
		cheminsARegarder.add(cheminARegarder);

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
	 * Var.enUS: addPathsToWatch
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
	public void ajouterCheminsARegarder(JsonObject classeLangueConfig, Boolean REGARDER) {
		for(String cheminARegarder : cheminsARegarder) {
			try {
				chemins.add(enregistrerTout(Paths.get(cheminARegarder)));
				if(REGARDER)
					LOG.info("{}: {}", classeLangueConfig.getString(ConfigCles.var_Regarder), cheminARegarder);
				else
					LOG.info("{}: {}", classeLangueConfig.getString(ConfigCles.var_Generer), cheminARegarder);
			} catch (IOException e) { 
				LOG.error("Erreur à ajouter chemin pour regarder.", e);
			}
		}
	}      

	/**
	 * Param1.var.enUS: event
	 * r: evenement
	 * r.enUS: event
	 */
	@SuppressWarnings("unchecked") static <T> WatchEvent<T> cast(WatchEvent<?> evenement) {
		return (WatchEvent<T>) evenement;
	}

	/**
	 * Var.enUS: save
	 * Param1.var.enUS: dirPath
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
	 * Var.enUS: saveAll
	 * Param1.var.enUS: start
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
	 * Var.enUS: handleEvents
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
	 * r: configChemin
	 * r.enUS: configPath
	 * r: fichierConfig
	 * r.enUS: configFile
	 * r: SITE_NOM
	 * r.enUS: SITE_NAME
	 * r: chemins
	 * r.enUS: paths
	 */

	/** 
	 * Var.enUS: handleEvents
	 * r: classeCheminAbsolue
	 * r.enUS: classAbsolutePath
	 * r: executeur
	 * r.enUS: executor
	 * r: SITE_CHEMIN
	 * r.enUS: SITE_PATH
	 * r: SITE_COMPUTATE_CHEMIN
	 * r.enUS: COMPUTATE_SRC_PATH
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
	protected void traiterEvenements(JsonObject classeLangueConfig) {
		for(String cheminARegarder : cheminsARegarder)
			System.out.println(classeLangueConfig.getString(ConfigCles.var_Regarder) + " " + cheminARegarder);
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
					String cp = FileUtils.readFileToString(new File(COMPUTATE_SRC + "/config/cp.txt"), "UTF-8");
					String classpath = String.format("%s:%s/target/classes", cp, COMPUTATE_SRC);
					CommandLine ligneCommande = new CommandLine("java");
					ligneCommande.addArgument("-cp");
					ligneCommande.addArgument(classpath);
					ligneCommande.addArgument(RegarderClasse.class.getCanonicalName());
					ligneCommande.addArgument(classeCheminRepertoireAppli);
					ligneCommande.addArgument(classeCheminAbsolu);
					File repertoireTravail = new File(COMPUTATE_SRC);

					executeur.setWorkingDirectory(repertoireTravail);
					executeur.execute(ligneCommande); 
				} catch (Exception e) {  
					LOG.error("Une Problème d'exécution de RegarderRepertoire: " + cheminComplet.toAbsolutePath().toString(), e);
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
}
