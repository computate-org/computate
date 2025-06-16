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
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrInputDocument;
import org.computate.i18n.I18n;
import org.computate.vertx.config.ComputateConfigKeys;
import org.slf4j.Logger;

import com.hubspot.jinjava.Jinjava;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxBuilder;
import io.vertx.core.VertxOptions;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.SharedData;

/**
 * NomCanonique.enUS: org.computate.enUS.java.WatchDirectory
 */    
public class RegarderRepertoire extends AbstractVerticle {  
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
	protected JsonObject classeLangueConfig;
	protected String langueNom;
	/**
	 * Var.enUS: SITE_NAME
	 */
	protected String SITE_NOM;
	protected String ZOOKEEPER_ROOT_PATH;
	/**
	 * Var.enUS: SITE_PATH
	 */
	protected String SITE_SRC;
	protected String COMPUTATE_SRC;
	protected String COMPUTATE_VERTX_SRC;

	protected WorkerExecutor workerExecutor;

	 /**
	 * r: SITE_NOM
	 * r.enUS: SITE_NAME
	 * r: SITE_SRC
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
		Logger LOG = org.slf4j.LoggerFactory.getLogger(RegarderRepertoire.class);
		try {
			String lang = Optional.ofNullable(System.getenv("SITE_LANG")).orElse("frFR");
			String appComputate = System.getenv("COMPUTATE_SRC");
			String appComputateVertx = System.getenv("COMPUTATE_VERTX_SRC");
			Jinjava jinjava = ConfigSite.getJinjava();
			JsonObject classeLangueConfig = ConfigSite.getLangueConfigGlobale(jinjava, appComputateVertx, lang);
			RegarderRepertoire regarderRepertoire = new RegarderRepertoire();
			regarderRepertoire.configuration = ConfigSite.getConfiguration(jinjava, classeLangueConfig);

			String SITE_NOM = regarderRepertoire.configuration.getString(classeLangueConfig.getString("var_SITE_NOM"));
			String SITE_SRC = regarderRepertoire.configuration.getString(classeLangueConfig.getString("var_SITE_SRC"));
			String ZOOKEEPER_ROOT_PATH = regarderRepertoire.configuration.getString("ZOOKEEPER_ROOT_PATH");
			Boolean REGARDER = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_REGARDER"))).orElse("true"));
			Boolean REGARDER_MAINTENANT = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_REGARDER_MAINTENANT"))).orElse("false"));
			Boolean GENERER = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_GENERER"))).orElse("true"));
			Boolean GENERER_MAINTENANT = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_GENERER_MAINTENANT"))).orElse("false"));

			regarderRepertoire.langueNom = lang;
			regarderRepertoire.SITE_NOM = SITE_NOM;
			regarderRepertoire.SITE_SRC = SITE_SRC;
			regarderRepertoire.ZOOKEEPER_ROOT_PATH = ZOOKEEPER_ROOT_PATH;
			regarderRepertoire.COMPUTATE_SRC = appComputate;
			regarderRepertoire.COMPUTATE_VERTX_SRC = appComputateVertx;
			regarderRepertoire.classeCheminRepertoireAppli = SITE_SRC;

			regarderRepertoire.cheminSrcMainJava = SITE_SRC + "/src/main/java";
			regarderRepertoire.cheminSrcGenJava = SITE_SRC + "/src/gen/java";
			regarderRepertoire.cheminsBin.add(SITE_SRC + "/src/main/resources");


			regarderRepertoire.trace = true;
			regarderRepertoire.initialiserRegarderRepertoire(classeLangueConfig);
			regarderRepertoire.ajouterCheminsARegarder(classeLangueConfig, REGARDER);

			if(REGARDER || REGARDER_MAINTENANT) {
				if(!REGARDER_MAINTENANT) {
					indexerClasses(SITE_SRC, classeLangueConfig);
					indexerPageClasses(SITE_SRC, classeLangueConfig);
					indexerPageClasses(SITE_SRC, classeLangueConfig);
					indexerPageClasses(SITE_SRC, classeLangueConfig);
				}
				LOG.info(classeLangueConfig.getString(I18n.str_Pret));
				regarderRepertoire.traiterEvenements(classeLangueConfig);
			} else {
				if(!GENERER_MAINTENANT) {
					indexerClasses(SITE_SRC, classeLangueConfig);
					indexerPageClasses(SITE_SRC, classeLangueConfig);
					indexerPageClasses(SITE_SRC, classeLangueConfig);
				}
				if(GENERER) {
					indexerPageClasses(SITE_SRC, classeLangueConfig);
					indexerEcrireClasses(SITE_SRC, classeLangueConfig);
				} else {
					indexerPageClasses(SITE_SRC, classeLangueConfig);
				}
			}
		}
		catch(Exception ex) {
			LOG.error("Erreur pendant traiterEvenements. ", ex);
		}
	} 

	public static void indexerClasses(String SITE_SRC, JsonObject classeLangueConfig) throws Exception {
		Logger LOG = org.slf4j.LoggerFactory.getLogger(RegarderRepertoire.class);
		String classeLangueNom = StringUtils.defaultString(System.getenv("SITE_LANG"), "frFR");
		File dir = new File(String.format("%s/src/main/java", SITE_SRC));

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
					regarderClasse.setArgs(new String[] {SITE_SRC, cheminStr});
					regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig);
					SolrInputDocument classeDoc = new SolrInputDocument();
					regarderClasse.indexerClasse(cheminStr, classeDoc, classeLangueNom);
					LOG.info(String.format("%s %s", classeLangueConfig.getString(I18n.var_Indexe), cheminStr));
				} catch(Exception ex) {
					LOG.error(String.format("An exception occured while indexing files"), ex);
				}
			});
		}
	}

	public static void indexerPageClasses(String SITE_SRC, JsonObject classeLangueConfig) throws Exception {
		Logger LOG = org.slf4j.LoggerFactory.getLogger(RegarderRepertoire.class);
		String classeLangueNom = StringUtils.defaultString(System.getenv("SITE_LANG"), "frFR");
		File dir = new File(String.format("%s/src/main/java", SITE_SRC));

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
					regarderClasse.setArgs(new String[] {SITE_SRC, cheminStr});
					regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig);
					SolrInputDocument classeDoc = new SolrInputDocument();
					regarderClasse.indexerClasse(cheminStr, classeDoc, classeLangueNom);
					LOG.info(String.format("%s %s", classeLangueConfig.getString(I18n.var_Indexe), cheminStr));

					Boolean classeEtendGen = (Boolean)classeDoc.get("classeEtendGen_stored_boolean").getValue();
					String classeCheminGen = Optional.ofNullable(classeDoc.get("classeCheminGen_enUS_stored_string")).map(o -> (String)o.getValue()).orElse(null);
					String classePageChemin = Optional.ofNullable(classeDoc.get("classePageChemin_enUS_stored_string")).map(o -> (String)o.getValue()).orElse(null);
					String classeGenPageChemin = Optional.ofNullable(classeDoc.get("classeGenPageChemin_enUS_stored_string")).map(o -> (String)o.getValue()).orElse(null);

					if(classePageChemin != null) {
						RegarderClasse regarderClasse2 = new RegarderClasse();
						try {
							regarderClasse2.args = regarderClasse.args;
							regarderClasse2.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
							SolrInputDocument classeDoc2 = new SolrInputDocument();
							regarderClasse2.indexerClasse(classePageChemin, classeDoc2, classeLangueNom);
							LOG.info(String.format("%s %s", classeLangueConfig.getString(I18n.var_Indexe), classePageChemin));
						}
						catch(Exception ex) {
							LOG.error(String.format("An exception occured while indexing files"), ex);
						}
					}
					if(classePageChemin != null) {
						String classePageGenChemin = classePageChemin.replace("/src/main/java", "/src/gen/java").replace(".java", "Gen.java");
				
						RegarderClasse regarderClasse2 = new RegarderClasse();
						try {
							regarderClasse2.args = regarderClasse.args;
							regarderClasse2.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
							SolrInputDocument classeDoc2 = new SolrInputDocument();
							regarderClasse2.indexerClasse(classePageGenChemin, classeDoc2, classeLangueNom);
							LOG.info(String.format("%s %s", classeLangueConfig.getString(I18n.var_Indexe), classePageGenChemin));
						}
						catch(Exception ex) {
							LOG.error(String.format("An exception occured while indexing files"), ex);
						}
					}
					if(classeGenPageChemin != null) {
						RegarderClasse regarderClasse2 = new RegarderClasse();
						try {
							regarderClasse2.args = regarderClasse.args;
							regarderClasse2.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
							SolrInputDocument classeDoc2 = new SolrInputDocument();
							regarderClasse2.indexerClasse(classeGenPageChemin, classeDoc2, classeLangueNom);
							LOG.info(String.format("%s %s", classeLangueConfig.getString(I18n.var_Indexe), classeGenPageChemin));
						}
						catch(Exception ex) {
							LOG.error(String.format("An exception occured while indexing files"), ex);
						}
					}
					if(classeGenPageChemin != null) {
						String classeGenPageGenChemin = classeGenPageChemin.replace("/src/main/java", "/src/gen/java").replace(".java", "Gen.java");
				
						RegarderClasse regarderClasse2 = new RegarderClasse();
						try {
							regarderClasse2.args = regarderClasse.args;
							regarderClasse2.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
							SolrInputDocument classeDoc2 = new SolrInputDocument();
							regarderClasse2.indexerClasse(classeGenPageGenChemin, classeDoc2, classeLangueNom);
							LOG.info(String.format("%s %s", classeLangueConfig.getString(I18n.var_Indexe), classeGenPageGenChemin));
						}
						catch(Exception ex) {
							LOG.error(String.format("An exception occured while indexing files"), ex);
						}
					}
				} catch(Exception ex) {
					LOG.error(String.format("An exception occured while indexing files"), ex);
				}
			});
		}
	}

	public static void indexerEcrireClasses(String SITE_SRC, JsonObject classeLangueConfig) throws Exception {
		Logger LOG = org.slf4j.LoggerFactory.getLogger(RegarderRepertoire.class);
		String appComputate = System.getenv("COMPUTATE_SRC");
		String appComputateVertx = System.getenv("COMPUTATE_VERTX_SRC");
		String classeLangueNom = StringUtils.defaultString(System.getenv("SITE_LANG"), "frFR");
		File dir = new File(String.format("%s/src/main/java", SITE_SRC));

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
					regarderClasse.setArgs(new String[] {SITE_SRC, cheminStr});
					regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig);
					SolrInputDocument classeDoc = new SolrInputDocument();
					regarderClasse.indexerClasse(cheminStr, classeDoc, classeLangueNom);
					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, classeLangueNom, classeLangueNom, classeLangueConfig);
					LOG.info(String.format("%s %s", classeLangueConfig.getString(I18n.var_Indexe), cheminStr));
				} catch(Exception ex) {
					LOG.error(String.format("An exception occured while indexing files: %s"), ex);
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
	 * r: SITE_SRC
	 * r.enUS: SITE_PATH
	 * r: SITE_NOM
	 * r.enUS: SITE_NAME
	 */
	public void initialiserRegarderRepertoire(JsonObject classeLangueConfig) throws Exception {
		observateur = FileSystems.getDefault().newWatchService();
		String cheminRelatifARegarder = configuration.getString(classeLangueConfig.getString(I18n.var_CHEMINS_RELATIFS_A_REGARDER));
		String cheminARegarder = SITE_SRC + "/" + cheminRelatifARegarder;
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
				// if(REGARDER)
				// 	LOG.info("{}: {}", classeLangueConfig.getString(I18n.var_Regarder), cheminARegarder);
				// else
				// 	LOG.info("{}: {}", classeLangueConfig.getString(I18n.var_Generer), cheminARegarder);
			} catch (IOException ex) { 
				LOG.error("Erreur à ajouter chemin pour regarder.", ex);
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

	String REGARDER_CLASSE_ADDRESSE = "RegarderClasse";

	private void regarderClasseEvenement(Message<Object> message) {
		workerExecutor.executeBlocking(() -> {
			Promise<Void> promise = Promise.promise();
			String orderLock = null;
			try {
				JsonObject body = ((JsonObject)message.body()).getJsonObject("context").getJsonObject("params").getJsonObject("body");
				String cheminCompletStr = body.getString("cheminComplet");
				LOG.debug(String.format("Received request on the event bus: %s", cheminCompletStr));
				Path cheminComplet = Path.of(cheminCompletStr);
				orderLock = String.format("/%s/%s", ZOOKEEPER_ROOT_PATH, cheminCompletStr.replace("/", "-"));
				SharedData sharedData = vertx.sharedData();
				sharedData.getLocalLockWithTimeout(orderLock, config().getLong(ComputateConfigKeys.ZOOKEEPER_CONNECTION_TIMEOUT_MILLIS, 3000L)).onSuccess(lock -> {
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
						String classeNomSimple = StringUtils.substringBeforeLast(cheminComplet.getFileName().toString(), ".");
						String log = String.format(classeLangueConfig.getString(I18n.str_chemin_absolu), classeNomSimple);
						LOG.info(log);
						promise.complete();
						lock.release();
					} catch(Exception ex) {
						LOG.error(String.format(classeLangueConfig.getString(I18n.str_UneProblemeExecutionRegarderRepertoire), cheminCompletStr), ex);
						promise.fail(ex);
						lock.release();
					}
				}).onFailure(ex -> {
					promise.complete();
				});
			} catch(Exception ex) {
					LOG.error(String.format(classeLangueConfig.getString(I18n.str_UneProblemeExecutionRegarderRepertoire), ((JsonObject)message.body()).getJsonObject("context").getJsonObject("params").getJsonObject("body").getString("cheminComplet")), ex);
				promise.fail(ex);
			}
			return promise.future();
		});
	}

	/** 
	 * Var.enUS: handleEvents
	 * r: classeCheminAbsolue
	 * r.enUS: classAbsolutePath
	 * r: executeur
	 * r.enUS: executor
	 * r: SITE_SRC
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
		VertxBuilder vertxBuilder = Vertx.builder();
		VertxOptions vertxOptions = new VertxOptions();
		Long vertxWarningExceptionSeconds = configuration.getLong(ComputateConfigKeys.VERTX_WARNING_EXCEPTION_SECONDS);
		Long vertxMaxEventLoopExecuteTime = configuration.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME);
		Long vertxMaxWorkerExecuteTime = Long.MAX_VALUE;
		vertxOptions.setWarningExceptionTime(vertxWarningExceptionSeconds);
		vertxOptions.setWarningExceptionTimeUnit(TimeUnit.SECONDS);
		vertxOptions.setMaxEventLoopExecuteTime(vertxMaxEventLoopExecuteTime);
		vertxOptions.setMaxEventLoopExecuteTimeUnit(TimeUnit.SECONDS);
		vertxOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
		vertxOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);
		Integer workerPoolSize = 1;
		vertxOptions.setWorkerPoolSize(workerPoolSize);
		Integer siteInstances = Integer.parseInt(configuration.getString(ComputateConfigKeys.COMPUTATE_INSTANCES, "4"));
		vertxBuilder.with(vertxOptions);
		Vertx vertx = vertxBuilder.build();


		DeploymentOptions deploymentOptions = new DeploymentOptions();
		LOG.info(String.format("instances: %s", siteInstances));
		deploymentOptions.setInstances(siteInstances);
		deploymentOptions.setConfig(configuration);
		deploymentOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
		deploymentOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);
		vertx.deployVerticle(new Supplier<Verticle>() {
					@Override
					public Verticle get() {
						RegarderRepertoire verticle = new RegarderRepertoire();
						return verticle;
					}
				}, deploymentOptions).onSuccess(a -> {

			workerExecutor = vertx.createSharedWorkerExecutor(String.format("%s-worker", Thread.currentThread().getName()), workerPoolSize, vertxMaxWorkerExecuteTime, TimeUnit.SECONDS);
			workerExecutor.executeBlocking(() -> {
				Promise<Void> promise = Promise.promise();
				for(String cheminARegarder : cheminsARegarder)
					LOG.info(classeLangueConfig.getString(I18n.var_Regarder) + " " + cheminARegarder);
				for (;;) {

					WatchKey regarderCle;
					try {
						regarderCle = observateur.take();
					} catch (InterruptedException x) {  
						break;
					}  

					Path cheminRepertoire = cles.get(regarderCle);
					if (cheminRepertoire == null) {
						LOG.error("Cle de surveillance n'est pas reconnue !");
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
						String cheminCompletStr = cheminComplet.toString();

						try { 
							if(!StringUtils.endsWithAny(cheminCompletStr, "GenApiServiceImpl.java", "ApiServiceImpl.java", "GenApiService.java")) {
								JsonObject body = new JsonObject();
								body.put("cheminComplet", cheminCompletStr);
								JsonObject params = new JsonObject();
								params.put("body", body);
								params.put("path", new JsonObject());
								params.put("cookie", new JsonObject());
								params.put("header", new JsonObject());
								params.put("form", new JsonObject());
								JsonObject query = new JsonObject();
								params.put("query", query);
								JsonObject context = new JsonObject().put("params", params);
								JsonObject json = new JsonObject().put("context", context);
								LOG.debug(String.format("Sent request on the event bus: %s", cheminCompletStr));
								vertx.eventBus().send(REGARDER_CLASSE_ADDRESSE, json, new DeliveryOptions());
							}
						} catch (Exception ex) {  
							LOG.error("Une Problème d'exécution de RegarderRepertoire: " + cheminComplet.toAbsolutePath().toString(), ex);
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
				promise.complete();
				return promise.future();
			});
		}).onFailure(ex -> {
			LOG.error("Une Problème d'exécution du Verticle de RegarderRepertoire: ", ex);
			LOG.error("Failed to start main verticle. ", ex);
			vertx.close();
		});
	}  

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		try {
			String lang = Optional.ofNullable(System.getenv("SITE_LANG")).orElse("frFR");
			String appComputate = System.getenv("COMPUTATE_SRC");
			String appComputateVertx = System.getenv("COMPUTATE_VERTX_SRC");
			Jinjava jinjava = ConfigSite.getJinjava();
			classeLangueConfig = ConfigSite.getLangueConfigGlobale(jinjava, appComputateVertx, lang);
			configuration = ConfigSite.getConfiguration(jinjava, classeLangueConfig);

			SITE_NOM = configuration.getString(classeLangueConfig.getString("var_SITE_NOM"));
			SITE_SRC = configuration.getString(classeLangueConfig.getString("var_SITE_SRC"));
			ZOOKEEPER_ROOT_PATH = configuration.getString("ZOOKEEPER_ROOT_PATH");
			Boolean REGARDER = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_REGARDER"))).orElse("true"));
			Boolean REGARDER_MAINTENANT = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_REGARDER_MAINTENANT"))).orElse("false"));
			Boolean GENERER = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_GENERER"))).orElse("true"));
			Boolean GENERER_MAINTENANT = Boolean.parseBoolean(Optional.ofNullable(System.getenv(classeLangueConfig.getString("var_GENERER_MAINTENANT"))).orElse("false"));

			langueNom = lang;
			COMPUTATE_SRC = appComputate;
			COMPUTATE_VERTX_SRC = appComputateVertx;
			classeCheminRepertoireAppli = SITE_SRC;

			cheminSrcMainJava = SITE_SRC + "/src/main/java";
			cheminSrcGenJava = SITE_SRC + "/src/gen/java";
			cheminsBin.add(SITE_SRC + "/src/main/resources");

			trace = true;
			initialiserRegarderRepertoire(classeLangueConfig);
			ajouterCheminsARegarder(classeLangueConfig, REGARDER);

			vertx.eventBus().consumer(REGARDER_CLASSE_ADDRESSE, message -> {
				regarderClasseEvenement(message);
			});
			Long vertxMaxWorkerExecuteTime = configuration.getLong(ComputateConfigKeys.VERTX_MAX_WORKER_EXECUTE_TIME);
			Integer workerPoolSize = 1;
			workerExecutor = vertx.createSharedWorkerExecutor(String.format("%s-worker", Thread.currentThread().getName()), workerPoolSize, vertxMaxWorkerExecuteTime, TimeUnit.SECONDS);
			startPromise.complete();
		} catch (Exception ex) {
			LOG.error("Couldn't start verticle. ", ex);
			startPromise.fail(ex);
		}
	}
}
