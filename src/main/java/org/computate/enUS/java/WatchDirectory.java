package org.computate.enUS.java;

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

public class WatchDirectory {

	protected final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

	protected WatchService observer;

	protected Map<WatchKey, Path> keys = new LinkedHashMap<WatchKey, Path>();

	protected Boolean trace = false;

	protected ArrayList<Path> paths = new ArrayList<Path>();

	protected ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	protected DefaultExecutor executor = new DefaultExecutor();

	protected ArrayList<String> pathsBin = new ArrayList<String>();

	protected ArrayList<String> classPathPaths = new ArrayList<String>();

	protected ArrayList<String> pathsToWatch = new ArrayList<String>();

	protected ArrayList<String> testMethodNames = new ArrayList<String>();

	protected ArrayList<String> sourcePaths = new ArrayList<String>();

	protected ArrayList<String> allSourcePaths = new ArrayList<String>();

	protected ArrayList<String> libraryPaths = new ArrayList<String>();

	protected String classAppDirPath;

	protected String classPath;

	protected String srcMainJavaPath;

	protected String srcGenJavaPath;

	protected String configPath;

	protected File configFile;

	protected INIConfiguration configuration;

	protected String appName;

	protected String appPath;

	protected String appComputatePath;

	public static void  main(String[] args) throws Exception { 
		String appName = System.getenv("appName");
		String appPath = System.getenv("appPath");
		String appComputatePath = System.getenv("appComputatePath");
		WatchDirectory watchDirectory = new WatchDirectory();
		watchDirectory.appName = appName;
		watchDirectory.appPath = appPath;
		watchDirectory.appComputatePath = appComputatePath;
		watchDirectory.classAppDirPath = appPath;

		watchDirectory.srcMainJavaPath = appPath + "/src/main/java";
		watchDirectory.srcGenJavaPath = appPath + "/src/gen/java";
		watchDirectory.pathsBin.add(appPath + "/src/main/resources");

		watchDirectory.configPath = appPath + "/config/" + appName + ".config";
		watchDirectory.configFile = new File(watchDirectory.configPath);
		Configurations configurations = new Configurations();
		watchDirectory.configuration = configurations.ini(watchDirectory.configFile);

		watchDirectory.trace = true;
		try {
			watchDirectory.initializeWatchDirectory();
			watchDirectory.addPathsToWatch();
			watchDirectory.handleEvents();
		}
		catch(Exception e) {
			System.err.println("Error during handleEvents. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
	}

	public void  initializeWatchDirectory() throws Exception {
		observer = FileSystems.getDefault().newWatchService();
//		executeur.setStreamHandler(gestionnaireFluxPompe);

		pathsToWatch.add(srcMainJavaPath);
		sourcePaths.add(srcMainJavaPath);
		allSourcePaths.add(srcMainJavaPath);

		sourcePaths.add(srcGenJavaPath);
		allSourcePaths.add(srcGenJavaPath);
		
		for(String chemin : pathsBin) {
			classPathPaths.add(chemin);
		}
		
		for(String chemin : libraryPaths) {
			classPathPaths.add(chemin + "/*");
		} 

		classPath = StringUtils.join(classPathPaths, File.pathSeparator);
	}

	public void  addPathsToWatch() {
		for(String pathToWatch : pathsToWatch) {
			try {
				paths.add(saveAll(Paths.get(pathToWatch)));
				log.info("Watch: {}", pathToWatch);
			} catch (IOException e) { 
				log.error("Error adding path to watch.", e);
			}
		}
	}

	@SuppressWarnings(value="unchecked")
	static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}

	private void  save(Path dirPath) throws IOException { 
		WatchKey watchKey = dirPath.register(observer, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		if (trace) {
			keys.get(watchKey);
		}
		keys.put(watchKey, dirPath);
	}

	protected Path saveAll(Path start) throws IOException { 
		Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
			@Override public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes fields) throws IOException {
				save(dir);
				return FileVisitResult.CONTINUE;
			}
		});
		return start;
	}

	protected void  traiterEvenements() {
		for (;;) {

			WatchKey watchKey;
			try {
				watchKey = observer.take();
			} catch (InterruptedException x) {  
				return;
			}  

			Path directory = keys.get(watchKey);
			if (directory == null) {
				System.err.println("Cle de surveillance n'est pas reconnue !");
				continue;
			}

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (kind == OVERFLOW) {
					continue;
				}

				WatchEvent<Path> evenementSurveillance = cast(event);
				Path nom = evenementSurveillance.context();
				Path enfant = directory.resolve(nom);

				try { 
					String classeCheminAbsolu = enfant.toAbsolutePath().toString();   
					String cp = FileUtils.readFileToString(new File(appPath + "/config/cp.txt"), "UTF-8");
					CommandLine ligneCommande = CommandLine.parse("java -cp \"" + cp + ":" + appPath + "/target/classes\" " + WatchClass.class.getCanonicalName() + " \"" + classeCheminRepertoireAppli + "\" \"" + classeCheminAbsolu + "\"");
					File directoryTravail = new File(appComputatePath);

					executor.setWorkingDirectory(directoryTravail);
					executor.execute(ligneCommande); 
				} catch (Exception e) {  
					String cheminAbsolu = enfant.toAbsolutePath().toString();  
					log.error("Une Problème d'exécution de RegarderRepertoire: " + cheminAbsolu, e);
				} 

				if (kind == ENTRY_CREATE) {
					try {
						if (Files.isDirectory(enfant, NOFOLLOW_LINKS)) {
							saveAll(enfant);
						}
					} catch (IOException x) {
						// ignorer pour simplifier le log.
					}
				}
			}

			boolean valide = watchKey.reset();
			if (!valide) {
				keys.remove(watchKey);

				if (keys.isEmpty()) {
					break;
				} 
			}
		}
	}
}
