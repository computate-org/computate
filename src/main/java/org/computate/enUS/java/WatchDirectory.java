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

	protected ArrayList<String> relativePathsToWatch = new ArrayList<String>();

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
		Logger log = org.slf4j.LoggerFactory.getLogger(WatchDirectory.class);
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
		String[] relativePathsToWatch = configuration.getStringArray(StringUtils.replace(appName, ".", "..") + ".relativePathsToWatch");
		for(String cheminRelatifARegarder : relativePathsToWatch) {
			String cheminARegarder = appPath + "/" + cheminRelatifARegarder;
			pathsToWatch.add(cheminARegarder);
		}

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

	protected void  handleEvents() {
		for (;;) {

			WatchKey watchKey;
			try {
				watchKey = observer.take();
			} catch (InterruptedException x) {  
				return;
			}  

			Path dirPath = keys.get(watchKey);
			if (dirPath == null) {
				System.err.println("Watch key is not recognized!");
				continue;
			}

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (kind == OVERFLOW) {
					continue;
				}

				WatchEvent<Path> watchEvent = cast(event);
				Path relativePath = watchEvent.context();
				Path completePath = dirPath.resolve(relativePath);

				try { 
					String classAbsolutePath = completePath.toAbsolutePath().toString();   
					String cp = FileUtils.readFileToString(new File(appComputatePath + "/config/cp.txt"), "UTF-8");
					CommandLine commandLine = CommandLine.parse("java -cp \"" + cp + ":" + appComputatePath + "/target/classes\" " + WatchClass.class.getCanonicalName() + " \"" + classAppDirPath + "\" \"" + classAbsolutePath + "\"");
					File workDir = new File(appComputatePath);

					executor.setWorkingDirectory(workDir);
					executor.execute(commandLine); 
				} catch (Exception e) {  
					log.error("Une Problème d'exécution de RegarderRepertoire: " + completePath.toAbsolutePath().toString(), e);
				} 

				if (kind == ENTRY_CREATE) {
					try {
						if (Files.isDirectory(completePath, NOFOLLOW_LINKS)) {
							saveAll(completePath);
						}
					} catch (IOException x) {
						// Ignore to simplify the log.
					}
				}
			}

			boolean valid = watchKey.reset();
			if (!valid) {
				keys.remove(watchKey);

				if (keys.isEmpty()) {
					break;
				} 
			}
		}
	}
}
