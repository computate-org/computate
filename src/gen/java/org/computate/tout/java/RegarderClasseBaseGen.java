package org.computate.tout.java;

public class RegarderClasseBaseGen extends Object {

	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protege ;
	protected void _cheminAppli() {
		cheminAppli = args[0];
	}

	protected void _classeCheminAbsolu() {
		classeCheminAbsolu = args[1];
	}

	protected void _cheminSrcMainJava() {
		cheminSrcMainJava = cheminAppli + "/src/main/java";
	}

	protected void _cheminSrcGenJava() {
		cheminSrcGenJava = cheminAppli + "/src/gen/java";
	}

	protected void _cheminsBibliotheque() {
		cheminsBibliotheque.add(cheminAppli + "/lib");
		cheminsBibliotheque.add(cheminAppli + "/lib-tomcat");
		cheminsBibliotheque.add(cheminAppli + "/lib-keycloak");
	}

	protected void _cheminsBin() {
		cheminsBin.add(cheminAppli + "/bin");
		cheminsBin.add(cheminAppli + "/src/main/resources");
	}

	protected void _cheminConfiguration() {
		cheminConfiguration = cheminAppli + "/config/computate.config";
		System.out.println("cheminConfiguration: " + cheminConfiguration);  
	}

	protected void _fichierConfiguration() {
		fichierConfiguration = new File(cheminConfiguration);
	}

	protected void _configurations() {
		configurations = new Configurations();
	}

	protected void _config() {
		config = configurations.ini(fichierConfiguration);
	}

	protected void _nomAppli() {
		nomAppli = config.getString("nomAppli"); 
	}

	protected void _nomLangue() {
		nomLangue = config.getString(nomAppli + ".nomLangue");
	}

	protected void _toutesLangues() {
		toutesLangues = config.getStringArray(nomAppli + ".nomLangue");
	}

	protected void _autresLangues() {
		autresLangues = ArrayUtils.removeElement(toutesLangues, nomLangue);
	}

	protected void _langueIndexe() {
		langueIndexe = ArrayUtils.contains(toutesLangues, nomLangue);
	}

	protected void _nomFicherConfig() {
		nomFichierConfig = config.getString(nomAppli + ".nomFichierConfig", nomAppli + ".config");
	}

	protected void _cheminConfig() {
		cheminConfig = config.getString(nomAppli + ".cheminConfig", cheminAppli + "/config/" + nomFichierConfig);
	}

	protected void _versionMaven() {
		versionMaven = config.getString("maven.versionMaven", "3.5.3");
	}

	protected void _versionZookeeper() {
		versionZookeeper = config.getString("maven.versionZookeeper", "3.5.4");
	}

	protected void _prefixePortZookeeper() {
		prefixePortZookeeper = config.getString("zookeeper.prefixePortZookeeper", "102");
	}

	protected void _portClientZookeeper() {
		portClientZookeeper = config.getString("zookeeper.portClientZookeeper", prefixePortZookeeper + "81");
	}

	protected void _portAdminZookeeper() {
		portAdminZookeeper = config.getString("zookeeper.portAdminZookeeper", prefixePortZookeeper + "80");
	}

	protected void _versionSolr() {
		versionSolr = config.getString("zookeeper.versionSolr", "7.3.1");
	}

	protected void _prefixePortSolr() {
		prefixePortSolr = config.getString("zookeeper.prefixePortSolr", "103");
	}

	protected void _portSolr() {
		portSolr = config.getString("zookeeper.portSolr", prefixePortSolr + "83");
	}

	protected void _urlSolr() {
		urlSolr = config.getString("zookeeper.urlSolr", "http://localhost:" + portSolr + "/solr/" + nomAppli);
	}

	protected void _clientSolr() {
		clientSolr = new HttpSolrClient.Builder(urlSolr).build();
	}

	protected void _cheminsARegarder() {
		cheminsARegarder.add(cheminSrcMainJava);
	}

	protected void _cheminsSource() {
		cheminsSource.add(cheminSrcMainJava);
		cheminsSource.add(cheminSrcGenJava);
	}

	protected void _toutCheminsSource() {
		toutCheminsSource.add(cheminSrcMainJava);
		toutCheminsSource.add(cheminSrcGenJava);
	}

	protected void _cheminsCheminClasse() {
		for(String chemin : cheminsBin) {
			cheminsCheminClasse.add(chemin);
		}
		for(String chemin : cheminsBibliotheque) {
			cheminsCheminClasse.add(chemin + "/*");
		}
	}

	protected void _nomsMethodeTest() {
	}

	protected void _bricoleur() {
		bricoleur = new JavaProjectBuilder();
		for(String cheminSource : toutCheminsSource) {
			File répertoireSource = new File(cheminSource);
			bricoleur.addSourceFolder(répertoireSource);
		}
	}

	public void initRegarderClasseBase() {
		_cheminAppli();
		_classeCheminAbsolu();
		_cheminSrcMainJava();
		_cheminSrcGenJava();
		_cheminsBibliotheque();
		_cheminsBin();
		_cheminConfiguration();
		_fichierConfiguration();
		_configurations();
		_config();
		_nomAppli();
		_nomLangue();
		_toutesLangues();
		_autresLangues();
		_nomFicherConfig();
		_cheminConfig();
		_versionMaven();
		_versionZookeeper();
		_prefixePortZookeeper();
		_portClientZookeeper();
		_portAdminZookeeper();
		_versionSolr();
		_prefixePortSolr();
		_portSolr();
		_urlSolr();
		_clientSolr();
		_cheminsARegarder();
		_cheminsSource();
		_toutCheminsSource();
		_cheminsCheminClasse();
		_nomsMethodeTest();
		_bricoleur();
	}

	public null regex() {
		String o = null;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if(trouve)
				o = m.group(groupe);
		}
		return o;
	}

	public null regexListe() {
		ArrayList<String> resultats = new ArrayList<String>();
		String o = null;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			while(trouve) {
				o = m.group(1);
				resultats.add(o);
				trouve = m.find();
			}
		}
		return resultats;
	}

	public null regexRemplacerTout() {
		String codeSourceLangue = codeSource;
		if(!StringUtils.isEmpty(commentaire)) {
			Matcher m = Pattern.compile("^r." + nomLangue + ": (.*\\n.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			
			while(trouve) {
				String texteRechercheRemplacement = m.group(1);
				String[] partisRechercheRemplacement = StringUtils.split(texteRechercheRemplacement, "\n");
				if(partisRechercheRemplacement.length == 2) {
					String texteRecherche = partisRechercheRemplacement[0];
					String texteRemplacement = partisRechercheRemplacement[1];

					Matcher m2 = Pattern.compile(Pattern.quote(texteRecherche), Pattern.MULTILINE).matcher(codeSourceLangue);
					boolean trouve2 = m2.find();
					StringBuffer sortie2 = new StringBuffer();
					
					while(trouve2) {
						m2.appendReplacement(sortie2, texteRemplacement);
						trouve2 = m2.find();
					}
					m2.appendTail(sortie2);
					codeSourceLangue = sortie2.toString();
				}

				trouve = m.find();
			}
		}
		return codeSourceLangue;
	}

	public null concat() { 
		String resultat = Stream.of(valeurs).collect(Collectors.joining());
		return resultat;
	}

	protected null etendClasse() {
		if(nomCanonique != null && classeQdox != null) {
			if(classeQdox.getCanonicalName().equals(Object.class.getCanonicalName()))
				return false;
			if(classeQdox.getCanonicalName().equals(nomCanonique))
				return true;
			else if(!classeQdox.equals(classeQdox.getSuperClass()))
				return etendClasse(nomCanonique, classeQdox.getSuperJavaClass());
		}
		return false;
	}

	public null contientChamp() {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuper : classesSuperQdoxEtMoi) {
			JavaField champRequeteSite = classeSuper.getFieldByName(nomChamp);
			JavaMethod methodeRequeteSite = classeSuper.getMethod("_" + nomChamp, listeParams, false);
			Boolean o = champRequeteSite != null || methodeRequeteSite != null;
			if(o)
				return true;
		}
		return false;
	}

	public null contientMethodeSeul() {
		JavaMethod o = obtenirMethodeSeul(classeQdox, nomMethode, tableauParams);
		return o != null;
	}

	public null contientMethode() {
		JavaMethod o = obtenirMethode(classeQdox, nomMethode, tableauParams);
		return o != null;
	}

	public null contientAttribut() {
		Boolean resultat = false;
		if(classeAttribut.getCanonicalName().startsWith(nomEnsembleDomaine.toString())) {
			JavaField attribut = classeAttribut.getFieldByName(nomAttribut);
			if(attribut == null) {
				resultat = contientAttribut(nomEnsembleDomaine, nomAttribut, classeAttribut.getSuperJavaClass());
			}
			else {
				resultat = true;
			}
		}
		else {
			resultat = false;
		}
		return resultat;
	}

	public null obtenirMethode() {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuper : classesSuperQdoxEtMoi) {
			JavaMethod methode = classeSuper.getMethodBySignature(nomMethode, listeParams);
			if(methode != null)
				return methode;
		}
		return null;
	}

	public null obtenirMethodeSeul() {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, false);
		return methode;
	}

}
