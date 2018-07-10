package org.computate.tout.java;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaConstructor;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMember;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;

public class IndexerClasse extends RegarderClasseBase {

	public void peuplerClassesSuperQdoxInterfacesEtMoi (
			JavaClass c
			, ArrayList<JavaClass> classesSuperQdox
			, ArrayList<JavaClass> classesSuperQdoxEtMoi
			, ArrayList<JavaClass> classesSuperQdoxEtInterfaces
			, ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi
			) throws Exception { 
		if(c != null) {
			JavaClass classeSuper = c.getSuperJavaClass();
			List<JavaClass> interfacesImplémentées = c.getInterfaces();
//			JavaConstructor constructeur = c.getConstructor(new ArrayList<JavaType>());
//			if(constructeur != null)
//				constructeursAucunParametresAjouter(constructeur);

			for(JavaClass iface : interfacesImplémentées) {
				if(iface != null && !iface.getCanonicalName().equals("java.lang.Object") && !c.equals(iface)) {
					classesSuperQdoxInterfacesEtMoi.add(iface);
					classesSuperQdoxEtInterfaces.add(classeSuper);
					peuplerClassesSuperQdoxInterfacesEtMoi(iface, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi); // Doesn't seem to work for interfaces that extend other interfaces.
				}
			}
			classesSuperQdoxEtMoi.add(c);
			if(classeSuper != null && !classeSuper.getCanonicalName().equals("java.lang.Object") && !c.equals(classeSuper)) {
				classesSuperQdoxInterfacesEtMoi.add(classeSuper);
				classesSuperQdoxEtMoi.add(classeSuper);
				classesSuperQdoxEtInterfaces.add(classeSuper);
				classesSuperQdox.add(classeSuper);
				peuplerClassesSuperQdoxInterfacesEtMoi(classeSuper, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi);
			}
		}
	}
	
	protected Boolean indexerStocker(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_string"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexe_string"), valeurChamp);
		return valeurChamp;
	}
	
	protected Boolean indexerStocker(SolrInputDocument doc, String nomChamp, String nomLangue, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_", nomLangue, "_stocke_string"), valeurChamp);
		doc.addField(concat(nomChamp, "_", nomLangue, "_indexe_string"), valeurChamp);
		return valeurChamp;
	}
	
	protected String indexerStocker(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_string"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexe_string"), valeurChamp);
		return valeurChamp;
	}
	
	protected String indexerStocker(SolrInputDocument doc, String nomChamp, String nomLangue, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_", nomLangue, "_stocke_string"), valeurChamp);
		doc.addField(concat(nomChamp, "_", nomLangue, "_indexe_string"), valeurChamp);
		return valeurChamp;
	}

	protected void indexerClasse(String classeCheminAbsolu) throws Exception { 
		SolrInputDocument docClasse = new SolrInputDocument();
		String classeNomCanonique = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classeCheminAbsolu, "."), cheminSrcMainJava + "/"), "/", ".");
		String classeNomSimple = StringUtils.substringAfterLast(classeNomCanonique, ".");
		String classeNomCanoniqueGen = classeNomCanonique + "Gen";
		String classeNomSimpleGen = classeNomSimple + "Gen";
		JavaClass classeQdoxClasse = bricoleur.getClassByName(classeNomCanonique.toString());
		JavaClass classeQdoxSuper = classeQdoxClasse.getSuperJavaClass();
		String classeNomCanoniqueSuper = classeQdoxSuper.getCanonicalName();
		String classeNomSimpleSuper = StringUtils.substringAfterLast(classeNomCanoniqueSuper, ".");
		String commentaire = classeQdoxClasse.getComment();
		String classeNomEnsemble = StringUtils.substringBeforeLast(classeNomCanonique, ".");
		String classeChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), ".java");
		String classeCheminRepertoire = StringUtils.substringBeforeLast(classeChemin, "/");
		String classeCheminGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Gen.java");
		String classeCheminRepertoireGen = StringUtils.substringBeforeLast(classeCheminGen, "/");
		String cleClasse = classeChemin;
		Instant modifiee = Instant.now();
		Date modifieeDate = Date.from(modifiee);
		
		ArrayList<JavaClass> classesSuperQdox = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtMoi = new ArrayList<JavaClass>();
		classesSuperQdoxEtMoi.add(classeQdoxClasse);
		ArrayList<JavaClass> classesSuperQdoxEtInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi = new ArrayList<JavaClass>();
		classesSuperQdoxInterfacesEtMoi.add(classeQdoxClasse);
		peuplerClassesSuperQdoxInterfacesEtMoi(classeQdoxClasse, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi);

		docClasse.addField("modifiee_indexe_date", modifieeDate);
		docClasse.addField("modifiee_stocke_date", modifieeDate);

		docClasse.addField(concat("classeNomCanonique_", nomLangue, "_indexe_string"), classeNomCanonique);
		docClasse.addField(concat("classeNomCanonique_", nomLangue, "_stocke_string"), classeNomCanonique);

		docClasse.addField(concat("classeNomSimple_", nomLangue, "_indexe_string"), classeNomSimple);
		docClasse.addField(concat("classeNomSimple_", nomLangue, "_stocke_string"), classeNomSimple);

		docClasse.addField(concat("classeNomEnsemble_", nomLangue, "_indexe_string"), classeNomEnsemble);
		docClasse.addField(concat("classeNomEnsemble_", nomLangue, "_stocke_string"), classeNomEnsemble);

		docClasse.addField(concat("classeNomCanoniqueGen_", nomLangue, "_indexe_string"), classeNomCanoniqueGen);
		docClasse.addField(concat("classeNomCanoniqueGen_", nomLangue, "_stocke_string"), classeNomCanoniqueGen);

		docClasse.addField(concat("classeNomSimpleGen_", nomLangue, "_indexe_string"), classeNomSimpleGen);
		docClasse.addField(concat("classeNomSimpleGen_", nomLangue, "_stocke_string"), classeNomSimpleGen);

		docClasse.addField(concat("classeNomCanoniqueSuper_", nomLangue, "_indexe_string"), classeNomCanoniqueSuper);
		docClasse.addField(concat("classeNomCanoniqueSuper_", nomLangue, "_stocke_string"), classeNomCanoniqueSuper);

		docClasse.addField(concat("classeNomSimpleSuper_", nomLangue, "_indexe_string"), classeNomSimpleSuper);
		docClasse.addField(concat("classeNomSimpleSuper_", nomLangue, "_stocke_string"), classeNomSimpleSuper);

		docClasse.addField(concat("classeCheminAbsolu_indexe_string"), classeCheminAbsolu);
		docClasse.addField(concat("classeCheminAbsolu_stocke_string"), classeCheminAbsolu);

		docClasse.addField(concat("classeChemin_", nomLangue, "_indexe_string"), classeChemin);
		docClasse.addField(concat("classeChemin_", nomLangue, "_stocke_string"), classeChemin);

		docClasse.addField(concat("classeCheminRepertoire_", nomLangue, "_indexe_string"), classeCheminRepertoire);
		docClasse.addField(concat("classeCheminRepertoire_", nomLangue, "_stocke_string"), classeCheminRepertoire);

		docClasse.addField(concat("classeCheminGen_", nomLangue, "_indexe_string"), classeCheminGen);
		docClasse.addField(concat("classeCheminGen_", nomLangue, "_stocke_string"), classeCheminGen);

		docClasse.addField(concat("classeCheminRepertoireGen_", nomLangue, "_indexe_string"), classeCheminRepertoireGen);
		docClasse.addField(concat("classeCheminRepertoireGen_", nomLangue, "_stocke_string"), classeCheminRepertoireGen);

		for(String nomLangue : autresLangues) {  
			String classeNomCanoniqueLangue = regex("classeNomCanonique\\." + nomLangue + ": (.*)", commentaire);
			String classeNomSimpleLangue = StringUtils.substringAfterLast(classeNomCanoniqueLangue, ".") + "Gen";
			String classeNomEnsembleLangue = StringUtils.substringBeforeLast(classeNomCanoniqueLangue, ".");
			String classeNomCanoniqueGenLangue = classeNomCanoniqueLangue + "Gen";
			String classeNomSimpleGenLangue = classeNomSimpleLangue + "Gen";
			String classeCheminLangue = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java");
			String classeCheminRepertoireLangue = StringUtils.substringBeforeLast(classeCheminLangue, "/");
			String classeCheminGenLangue = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java");
			String classeCheminRepertoireGenLangue = StringUtils.substringBeforeLast(classeCheminGenLangue, "/");

			docClasse.addField(concat("classeNomCanonique_", nomLangue, "_indexe_string"), classeNomCanoniqueLangue);
			docClasse.addField(concat("classeNomCanonique_", nomLangue, "_stocke_string"), classeNomCanoniqueLangue);

			docClasse.addField(concat("classeNomSimple_", nomLangue, "_indexe_string"), classeNomSimpleLangue);
			docClasse.addField(concat("classeNomSimple_", nomLangue, "_stocke_string"), classeNomSimpleLangue);

			docClasse.addField(concat("classeNomCanoniqueGen_", nomLangue, "_indexe_string"), classeNomCanoniqueGenLangue);
			docClasse.addField(concat("classeNomCanoniqueGen_", nomLangue, "_stocke_string"), classeNomCanoniqueGenLangue);

			docClasse.addField(concat("classeNomSimpleGen_", nomLangue, "_indexe_string"), classeNomSimpleGenLangue);
			docClasse.addField(concat("classeNomSimpleGen_", nomLangue, "_stocke_string"), classeNomSimpleGenLangue);

			docClasse.addField(concat("classeNomEnsemble_", nomLangue, "_indexe_string"), classeNomEnsembleLangue);
			docClasse.addField(concat("classeNomEnsemble_", nomLangue, "_stocke_string"), classeNomEnsembleLangue);

			docClasse.addField(concat("classeChemin_", nomLangue, "_indexe_string"), classeCheminLangue);
			docClasse.addField(concat("classeChemin_", nomLangue, "_stocke_string"), classeCheminLangue);

			docClasse.addField(concat("classeCheminRepertoire_", nomLangue, "_indexe_string"), classeCheminRepertoireLangue);
			docClasse.addField(concat("classeCheminRepertoire_", nomLangue, "_stocke_string"), classeCheminRepertoireLangue);

			docClasse.addField(concat("classeCheminGen_", nomLangue, "_indexe_string"), classeCheminGenLangue);
			docClasse.addField(concat("classeCheminGen_", nomLangue, "_stocke_string"), classeCheminGenLangue);

			docClasse.addField(concat("classeCheminRepertoireGen_", nomLangue, "_indexe_string"), classeCheminRepertoireGenLangue);
			docClasse.addField(concat("classeCheminRepertoireGen_", nomLangue, "_stocke_string"), classeCheminRepertoireGenLangue);
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
				SolrInputDocument champDoc = docClasseClone.deepCopy();
				JavaField champQdox = (JavaField)membreQdox;
				JavaClass champClasseQdox = champQdox.getType();
				String champCommentaire = champQdox.getComment();
				String champVar = champQdox.getName();
				String champNomCanonique = champQdox.getType().getCanonicalName();
				String nomTypeOrigine = champClasseQdox.getGenericCanonicalName();
				String listeNomTypeOrigineGenerique = nomTypeOrigine;
				String champNomCanoniqueGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineGenerique, "<"), ">");
				champNomCanoniqueGenerique = champNomCanoniqueGenerique.contains("<") ? StringUtils.substringBefore(champNomCanoniqueGenerique, "<") : champNomCanoniqueGenerique;
				champNomCanoniqueGenerique = champNomCanoniqueGenerique.contains(",") ? StringUtils.substringBefore(champNomCanoniqueGenerique, ",") : champNomCanoniqueGenerique;
				String champCle = classeChemin + "." + champVar;

				// Champs Solr du champ. 

				champDoc.addField("cle", champCle);

				champDoc.addField(concat("champVar_", nomLangue, "_indexe_string"), champVar);
				champDoc.addField(concat("champVar_", nomLangue, "_stocke_string"), champVar);

				champDoc.addField(concat("champEstChamp_indexe_boolean"), true);
				champDoc.addField(concat("champEstChamp_stocke_boolean"), true);

				champDoc.addField(concat("champEstPublic_indexe_boolean"), champQdox.isPublic());
				champDoc.addField(concat("champEstPublic_stocke_boolean"), champQdox.isPublic());

				champDoc.addField(concat("champEstProtege_indexe_boolean"), champQdox.isProtected());
				champDoc.addField(concat("champEstProtege_stocke_boolean"), champQdox.isProtected());

				champDoc.addField(concat("champEstPrive_indexe_boolean"), champQdox.isPrivate());
				champDoc.addField(concat("champEstPrive_stocke_boolean"), champQdox.isPrivate());

				champDoc.addField(concat("champEstStatique_indexe_boolean"), champQdox.isStatic());
				champDoc.addField(concat("champEstStatique_stocke_boolean"), champQdox.isStatic());

				champDoc.addField(concat("champEstFinale_indexe_boolean"), champQdox.isFinal());
				champDoc.addField(concat("champEstFinale_stocke_boolean"), champQdox.isFinal());

				champDoc.addField(concat("champEstAbstrait_indexe_boolean"), champQdox.isAbstract());
				champDoc.addField(concat("champEstAbstrait_stocke_boolean"), champQdox.isAbstract());

				champDoc.addField(concat("champEstNatif_indexe_boolean"), champQdox.isNative());
				champDoc.addField(concat("champEstNatif_stocke_boolean"), champQdox.isNative());
	
				///////////////////////
				// Champ Annotations //
				///////////////////////
				List<JavaAnnotation> annotations = champQdox.getAnnotations(); 
				ArrayList<String> annotationsLangue = new ArrayList<String>();
				Boolean champEstTest = false;
				Boolean champEstSubstitue = false;
				for(JavaAnnotation annotation : annotations) {
					String champAnnotationLangue = annotation.getType().getCanonicalName();

					champDoc.addField(concat("champAnnotations_", nomLangue, "_indexe_string"), champAnnotationLangue);
					champDoc.addField(concat("champAnnotations_", nomLangue, "_stocke_string"), champAnnotationLangue);

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						champEstTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						champEstSubstitue = true;
					}
				}

				champDoc.addField(concat("champEstTest_indexe_boolean"), champEstTest);
				champDoc.addField(concat("champEstTest_stocke_boolean"), champEstTest);

				champDoc.addField(concat("champEstSubstitue_indexe_boolean"), champEstSubstitue);
				champDoc.addField(concat("champEstSubstitue_stocke_boolean"), champEstSubstitue);
	
				//////////////////
				// Champ Langue //
				//////////////////
				for(String nomLangue : autresLangues) {  

					String champVarLangue = regex("var\\." + nomLangue + ": (.*)", champCommentaire);
					champVarLangue = champVarLangue == null ? champVar : champVarLangue;
					champDoc.addField(concat("champVar_", nomLangue, "_indexe_string"), champVarLangue);
					champDoc.addField(concat("champVar_", nomLangue, "_stocke_string"), champVarLangue);

					List<String> champCommentairesLangue = regexListe("(.*)", champCommentaire);
					String champCommentaireLangue = StringUtils.join(champCommentairesLangue, "\n");
					champDoc.addField(concat("champCommentaire_", nomLangue, "_indexe_string"), champCommentaireLangue);
					champDoc.addField(concat("champCommentaire_", nomLangue, "_stocke_string"), champCommentaireLangue);

					String champBlocCode = champQdox.getCodeBlock();
					String champBlocCodeLangue = champBlocCode;
					ArrayList<String> remplacerClesLangue = regexListe("remplacer." + nomLangue + "\\s*=\\s*(.*)\\n.*", champCommentaire);
					ArrayList<String> remplacerValeursLangue = regexListe("remplacer." + nomLangue + "\\s*=\\s*.*\\n(.*)", champCommentaire);
					for(int i = 0; i < remplacerClesLangue.size(); i++) {
						String cle = remplacerClesLangue.get(i);
						String valeur = remplacerValeursLangue.get(i);
						StringUtils.replace(champBlocCodeLangue, cle, valeur);
					}
					champDoc.addField(concat("champBlocCode_", nomLangue, "_indexe_string"), champBlocCodeLangue);
					champDoc.addField(concat("champBlocCode_", nomLangue, "_stocke_string"), champBlocCodeLangue);
				}  

				clientSolr.add(champDoc); 
			}
			else if(membreQdox instanceof JavaConstructor) { 
				SolrInputDocument constructeurDoc = docClasseClone.deepCopy();
				JavaConstructor constructeurQdox = (JavaConstructor)membreQdox;
//				JavaClass champClasseQdox = constructeurQdox.getType();
//				String constructeurCle = classeChemin + "." + constructeurVar;
//
//				constructeurDoc.addField("cle", constructeurCle);

				docClasse.addField(concat("constructeurEstConstructeur_indexe_boolean"), true);
				docClasse.addField(concat("constructeurEstConstructeur_stocke_boolean"), true);

				constructeurDoc.addField(concat("constructeurEstPublic_indexe_boolean"), constructeurQdox.isPublic());
				constructeurDoc.addField(concat("constructeurEstPublic_stocke_boolean"), constructeurQdox.isPublic());

				constructeurDoc.addField(concat("constructeurEstProtege_indexe_boolean"), constructeurQdox.isProtected());
				constructeurDoc.addField(concat("constructeurEstProtege_stocke_boolean"), constructeurQdox.isProtected());

				constructeurDoc.addField(concat("constructeurEstPrive_indexe_boolean"), constructeurQdox.isPrivate());
				constructeurDoc.addField(concat("constructeurEstPrive_stocke_boolean"), constructeurQdox.isPrivate());

				constructeurDoc.addField(concat("constructeurEstStatique_indexe_boolean"), constructeurQdox.isStatic());
				constructeurDoc.addField(concat("constructeurEstStatique_stocke_boolean"), constructeurQdox.isStatic());

				constructeurDoc.addField(concat("constructeurEstFinale_indexe_boolean"), constructeurQdox.isFinal());
				constructeurDoc.addField(concat("constructeurEstFinale_stocke_boolean"), constructeurQdox.isFinal());

				constructeurDoc.addField(concat("constructeurEstAbstrait_indexe_boolean"), constructeurQdox.isAbstract());
				constructeurDoc.addField(concat("constructeurEstAbstrait_stocke_boolean"), constructeurQdox.isAbstract());

				constructeurDoc.addField(concat("constructeurEstNatif_indexe_boolean"), constructeurQdox.isNative());
				constructeurDoc.addField(concat("constructeurEstNatif_stocke_boolean"), constructeurQdox.isNative());



//				UnConstructeur constructeur = new UnConstructeur();
//				constructeur.requeteSite(requeteSite);
//				constructeur.constructeurQdox(constructeurQdox);
//				constructeur.constructeurEstPublic(constructeurQdox.isPublic());
//				constructeur.constructeurEstProtege(constructeurQdox.isProtected());
//				constructeur.constructeurEstPrive(constructeurQdox.isPrivate());
//				constructeur.constructeurEstStatique(constructeurQdox.isStatic());
//				constructeur.constructeurEstFinale(constructeurQdox.isFinal());
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
			}
			else if(membreQdox instanceof JavaMethod) {
				SolrInputDocument methodeDoc = docClasseClone.deepCopy();
				JavaMethod methodeQdox = (JavaMethod)membreQdox;
				String methodeCommentaire = methodeQdox.getComment();
				Boolean ignorer = "true".equals(regex("ignorer: (.*)", methodeCommentaire));
				if(!ignorer) {
					JavaClass methodeClasseQdoxRetour = methodeQdox.getReturns();
					String methodeVar = indexerStocker(methodeDoc, "methodeVar", nomLangue, methodeQdox.getName());
					String methodeNomCanoniqueRetourComplet = null;
					String methodeNomCanoniqueRetour = null;
					JavaClass classeQdoxRetour = methodeQdox.getReturns();
					if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void")) {
						methodeNomCanoniqueRetourComplet = indexerStocker(methodeDoc, "methodeNomCanoniqueRetourComplet", nomLangue, classeQdoxRetour.getGenericCanonicalName());
						methodeNomCanoniqueRetour = indexerStocker(methodeDoc, "methodeNomCanoniqueRetour", nomLangue, classeQdoxRetour.getCanonicalName());
						String methodeNomSimpleRetour = indexerStocker(methodeDoc, "methodeNomSimpleRetour", nomLangue, StringUtils.substringAfterLast(methodeNomCanoniqueRetour, "."));
						String listeNomTypeOrigineRetourGenerique = methodeNomCanoniqueRetourComplet;
						String methodeNomCanoniqueRetourGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineRetourGenerique, "<"), ">");
						String methodeNomSimpleRetourComplet;
						String methodeNomSimpleRetourGenerique;
						methodeNomCanoniqueRetourGenerique = methodeNomCanoniqueRetourGenerique.contains("<") ? StringUtils.substringBefore(methodeNomCanoniqueRetourGenerique, "<") : methodeNomCanoniqueRetourGenerique;
						methodeNomCanoniqueRetourGenerique = methodeNomCanoniqueRetourGenerique.contains(",") ? StringUtils.substringBefore(methodeNomCanoniqueRetourGenerique, ",") : methodeNomCanoniqueRetourGenerique;
						if(StringUtils.isNotEmpty(methodeNomCanoniqueRetourGenerique)) {
							indexerStocker(methodeDoc, "methodeNomCanoniqueRetourGenerique", nomLangue, methodeNomCanoniqueRetourGenerique);

							if(StringUtils.contains(methodeNomCanoniqueRetourGenerique, "."))
								methodeNomSimpleRetourGenerique = indexerStocker(methodeDoc, "methodeNomSimpleRetourGenerique", nomLangue, StringUtils.substringAfterLast(methodeNomCanoniqueRetourGenerique, "."));
							else
								methodeNomSimpleRetourGenerique = indexerStocker(methodeDoc, "methodeNomSimpleRetourGenerique", nomLangue, methodeNomCanoniqueRetourGenerique);

							if(StringUtils.contains(methodeNomSimpleRetourGenerique, ".")) {
								methodeNomSimpleRetourComplet = indexerStocker(methodeDoc, "methodeNomSimpleRetourComplet", nomLangue, concat(StringUtils.substringAfterLast(methodeNomSimpleRetour, "."), "<", methodeNomSimpleRetourGenerique, ">"));
							}
							else {
								methodeNomSimpleRetourComplet = indexerStocker(methodeDoc, "methodeNomSimpleRetourComplet", nomLangue, concat(methodeNomSimpleRetour, "<", methodeNomSimpleRetourGenerique, ">"));
							}
						}
						else {
							methodeNomSimpleRetourComplet = indexerStocker(methodeDoc, "methodeNomCanoniqueRetourComplet", nomLangue, methodeNomSimpleRetour);
						}
					}

					String methodeCle = classeChemin + "." + methodeVar;
	
					// Methodes Solr du methode. 
	
					methodeDoc.addField("cle", methodeCle);
					indexerStocker(methodeDoc, "methodeEstMethode", true);
					indexerStocker(methodeDoc, "methodeEstPublic", methodeQdox.isPublic());
					indexerStocker(methodeDoc, "methodeEstProtege", methodeQdox.isProtected());
					indexerStocker(methodeDoc, "methodeEstPrive", methodeQdox.isPrivate());
					indexerStocker(methodeDoc, "methodeEstStatique", methodeQdox.isStatic());
					indexerStocker(methodeDoc, "methodeEstFinale", methodeQdox.isFinal());
					indexerStocker(methodeDoc, "methodeEstAbstrait", methodeQdox.isAbstract());
					indexerStocker(methodeDoc, "methodeEstNatif", methodeQdox.isNative());
		
					///////////////////////
					// Methode Annotations //
					///////////////////////
					List<JavaAnnotation> annotations = methodeQdox.getAnnotations(); 
					ArrayList<String> annotationsLangue = new ArrayList<String>();
					Boolean methodeEstTest = false;
					Boolean methodeEstSubstitue = false;
					for(JavaAnnotation annotation : annotations) {
						String methodeAnnotationLangue = indexerStocker(methodeDoc, "methodeAnnotations", nomLangue, annotation.getType().getCanonicalName());
	
						if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
							methodeEstTest = true;
						}
						if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
							methodeEstSubstitue = true;
						}
					}
					indexerStocker(methodeDoc, "methodeEstTest", methodeEstTest);
					indexerStocker(methodeDoc, "methodeEstSubstitue", methodeEstSubstitue);

					String methodeVarLangue = regex("var\\." + nomLangue + ": (.*)", methodeCommentaire);
					methodeVarLangue = indexerStocker(methodeDoc, "methodeVar", nomLangue, methodeVarLangue == null ? methodeVar : methodeVarLangue);

					List<String> methodeCommentairesLangue = regexListe("(.*)", methodeCommentaire);
					String methodeCommentaireLangue = indexerStocker(methodeDoc, "methodeCommentaire", nomLangue, StringUtils.join(methodeCommentairesLangue, "\n"));

					String methodeBlocCode = methodeQdox.getCodeBlock();
					String methodeBlocCodeLangue = methodeBlocCode;
					ArrayList<String> remplacerClesLangue = regexListe("remplacer." + nomLangue + "\\s*=\\s*(.*)\\n.*", methodeCommentaire);
					ArrayList<String> remplacerValeursLangue = regexListe("remplacer." + nomLangue + "\\s*=\\s*.*\\n(.*)", methodeCommentaire);
					for(int i = 0; i < remplacerClesLangue.size(); i++) {
						String cle = remplacerClesLangue.get(i);
						String valeur = remplacerValeursLangue.get(i);
						StringUtils.replace(methodeBlocCodeLangue, cle, valeur);
					}
					indexerStocker(methodeDoc, "methodeBlocCode", nomLangue, methodeBlocCodeLangue);
	
					String varEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
					methode.nomMethode.frFR(methodeQdox.getName());
					methode.nomMethode.enUS(StringUtils.isEmpty(varEnUS) ? methodeQdox.getName() : varEnUS);
	
					List<JavaAnnotation> annotations = methodeQdox.getAnnotations();
					methode.estSubstitue(false);
					for(JavaAnnotation annotation : annotations) {
						if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
							methode.estTest(true);
						}
						if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
							methode.estSubstitue(true);
						}
					}
					List<JavaParameter> parametresQdox = methodeQdox.getParameters();
	
					if(!methode.estSubstitue && !methode.champEstStatique && !methode.champEstFinale && methodeQdox.getDeclaringClass().equals(classeQdox) 
							&& methode.champEstProtege && parametresQdox.size() == 1 && classeQdoxRetour.isVoid()
							&& StringUtils.startsWith(methodeQdox.getName(), "_")) {
			
						String nomEntite = StringUtils.substringAfter(methodeQdox.getName(), "_");
						UnEntite entite = new UnEntite();
						JavaClass classeEntite = parametresQdox.get(0).getJavaClass();
						boolean typeCouverture = false;
						String nomTypeOrigine = classeEntite.getGenericCanonicalName();
						String nomType = nomTypeOrigine;
						String typeNomCanonique = classeEntite.getCanonicalName();
	//					JavaClass classeEntite = typeBricoleur.getClassByName(typeNomCanonique);
						String listeNomTypeOrigineGenerique = null;
						String listeNomTypeGenerique = null;
						String listeNomTypeGeneriqueComplet = null;
						String varCouverture = nomEntite + varCouvertureCapitalise.toString();
	
						entite.classe_(this);
						entite.requeteSite(requeteSite);
	
						String varEntiteEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
						entite.var.frFR(nomEntite);
						entite.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? nomEntite : StringUtils.substringAfter(varEntiteEnUS, "_"));
	
						regexCommentaires(methodeQdox.getComment(), entite.commentaire);
						regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), entite.codeSource);
			
						if(classeEntite.getFullyQualifiedName().equals(nomCanoniqueCouvertureActuel)) {
							nomType = StringUtils.substringBeforeLast(StringUtils.substringAfter(nomType, "<"), ">");
							if(StringUtils.contains(nomType, "<"))
								classeEntite = typeBricoleur.getClassByName(StringUtils.substringBefore(nomType, "<"));
							else
								classeEntite = typeBricoleur.getClassByName(nomType);
							typeNomCanonique = classeEntite.getCanonicalName();
							typeCouverture = true;
							entite.couverture(true);
						} 
			
						entite.classeQdox(classeEntite);
						entite.methodeQdox(methodeQdox);
						entite.initialiserLoinUnEntite(requeteSite);
						entites.add(entite);
						if(entite.cleUnique)
							varCleUniqueActuel.tout(entite.var);
						if(entite.suggere)
							varSuggereActuel.tout(entite.var);
	
						if(!entitesTout.contains(entite))
							entitesTout.add(entite);
	
						tout.add(entite);
						importationsAjouter(new Chaine().tout(typeNomCanonique));
						importationsGenAjouter(new Chaine().tout(typeNomCanonique));
						if(listeNomTypeGenerique != null) {
							Chaine importation = new Chaine().tout(listeNomTypeGenerique);
							importationsAjouter(importation);
							importationsGenAjouter(importation);
						}
	
						boolean etendCluster = entite.etendClasse(nomCanoniqueClusterActuel);
						entite.etendCluster(etendCluster);
						if(!etendCluster && entite.nomCanoniqueGenerique.pasVide()) {
							boolean listeCluster = etendClasse(nomCanoniqueClusterActuel, entite.nomCanoniqueGenerique.toString());
							entite.listeCluster(listeCluster);
						}
	
						boolean etendPageXml = entite.etendClasse(nomCanoniquePageXmlActuel);
						entite.etendPageXml(etendPageXml);
						if(!etendPageXml && entite.nomCanoniqueGenerique.pasVide()) {
							boolean listePageXml = etendClasse(nomCanoniquePageXmlActuel, entite.nomCanoniqueGenerique.toString());
							entite.listePageXml(listePageXml);
						}
	
						boolean etendPageParti = entite.etendClasse(nomCanoniquePagePartiActuel);
						entite.etendPageParti(etendPageParti);
						if(!etendPageParti && entite.nomCanoniqueGenerique.pasVide()) {
							boolean listePageParti = etendClasse(nomCanoniquePagePartiActuel, entite.nomCanoniqueGenerique.toString());
							entite.listePageParti(listePageParti);
						}
	
	
						boolean contientRequeteSite = contientChamp(varRequeteSite.toString(), entite.classeQdox);
						entite.contientRequeteSite(contientRequeteSite);
	
						boolean contientSetterString = contientMethode(entite.var.toString(), classeQdoxString);
						entite.contientSetterString(contientSetterString);
	
						entiteEstCmd(entite);
					}
					else {
						regexCommentaires(methodeQdox.getComment(), methode.commentaire);
						regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), methode.codeSource);
						methode.classe_(this);
						methode.initialiserLoinUneMethode(requeteSite);
						methodes.add(methode);
						tout.add(methode);
					}
		
					//////////////////
					// Methode Langue //
					//////////////////
					for(String nomLangue : autresLangues) {  
	
						String methodeVarLangue = regex("var\\." + nomLangue + ": (.*)", methodeCommentaire);
						methodeVarLangue = methodeVarLangue == null ? methodeVar : methodeVarLangue;
						methodeDoc.addField(concat("methodeVar_", nomLangue, "_indexe_string"), methodeVarLangue);
						methodeDoc.addField(concat("methodeVar_", nomLangue, "_stocke_string"), methodeVarLangue);
	
						List<String> methodeCommentairesLangue = regexListe("(.*)", methodeCommentaire);
						String methodeCommentaireLangue = StringUtils.join(methodeCommentairesLangue, "\n");
						methodeDoc.addField(concat("methodeCommentaire_", nomLangue, "_indexe_string"), methodeCommentaireLangue);
						methodeDoc.addField(concat("methodeCommentaire_", nomLangue, "_stocke_string"), methodeCommentaireLangue);
	
						String methodeBlocCode = methodeQdox.getCodeBlock();
						String methodeBlocCodeLangue = methodeBlocCode;
						ArrayList<String> remplacerClesLangue = regexListe("remplacer." + nomLangue + "\\s*=\\s*(.*)\\n.*", methodeCommentaire);
						ArrayList<String> remplacerValeursLangue = regexListe("remplacer." + nomLangue + "\\s*=\\s*.*\\n(.*)", methodeCommentaire);
						for(int i = 0; i < remplacerClesLangue.size(); i++) {
							String cle = remplacerClesLangue.get(i);
							String valeur = remplacerValeursLangue.get(i);
							StringUtils.replace(methodeBlocCodeLangue, cle, valeur);
						}
						methodeDoc.addField(concat("methodeBlocCode_", nomLangue, "_indexe_string"), methodeBlocCodeLangue);
						methodeDoc.addField(concat("methodeBlocCode_", nomLangue, "_stocke_string"), methodeBlocCodeLangue);
					
						String nomCanoniqueRetourComplet = null;
						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void"))
							nomCanoniqueRetourComplet = classeQdoxRetour.getGenericCanonicalName();
		//						methode.nomCanoniqueRetourComplet(classeQdoxRetour.getGenericCanonicalName());
		
						String nomCanoniqueRetourEnUS = regex("nomCanonique.enUS: (.*)", classeQdoxRetour.getComment());
						methode.nomCanoniqueRetour.frFR(StringUtils.substringBefore(nomCanoniqueRetourComplet, "<"));    
						methode.nomCanoniqueRetour.enUS(StringUtils.isEmpty(nomCanoniqueRetourEnUS) ? methode.nomCanoniqueRetour.frFR() : nomCanoniqueRetourEnUS);
		
						if(StringUtils.contains(nomCanoniqueRetourComplet, "<")) {
							methode.nomCanoniqueRetourGenerique.frFR(StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueRetourComplet, ">"), "<"));  
							methode.nomCanoniqueRetourGenerique.enUS(StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueRetourComplet, ">"), "<"));  
						}
						if(StringUtils.contains(methode.nomCanoniqueRetour.toString(), ".")) {
							methode.nomSimpleRetour.frFR(StringUtils.substringAfterLast(methode.nomCanoniqueRetour.frFR(), "."));    
							methode.nomSimpleRetour.enUS(StringUtils.substringAfterLast(methode.nomCanoniqueRetour.enUS(), "."));    
							if(methode.nomCanoniqueRetourGenerique.estVide()) {
								methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR());
								methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS());
							}
							else {
								if(methode.nomCanoniqueRetourGenerique.contient(".")) {
									methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR(), "<", StringUtils.substringAfterLast(methode.nomCanoniqueRetourGenerique.frFR(), "."), ">");    
									methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS(), "<", StringUtils.substringAfterLast(methode.nomCanoniqueRetourGenerique.enUS(), "."), ">");    
								}
								else {
									methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR(), "<", methode.nomCanoniqueRetourGenerique.frFR(), ">");    
									methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS(), "<", methode.nomCanoniqueRetourGenerique.enUS(), ">");    
								}
							}
						}
						else {
							methode.nomSimpleRetour.frFR(methode.nomCanoniqueRetour.frFR());
							methode.nomSimpleRetour.enUS(methode.nomCanoniqueRetour.enUS());
							if(methode.nomCanoniqueRetourGenerique.estVide()) {
								methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR());
								methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS());
							}
							else {
								methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR(), "<", methode.nomCanoniqueRetourGenerique.frFR(), ">");    
								methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS(), "<", methode.nomCanoniqueRetourGenerique.enUS(), ">");    
							}
						}
		
						if(methode.nomCanoniqueRetourGenerique.estVide()) {
							methode.nomCanoniqueRetourComplet.enUS(methode.nomCanoniqueRetour.enUS());
							methode.nomCanoniqueRetourComplet.frFR(methode.nomCanoniqueRetour.frFR());
						}
						else {
							methode.nomCanoniqueRetourComplet.enUS(methode.nomCanoniqueRetour.enUS(), "<", methode.nomCanoniqueRetourGenerique.enUS(), ">");
							methode.nomCanoniqueRetourComplet.frFR(methode.nomCanoniqueRetour.frFR(), "<", methode.nomCanoniqueRetourGenerique.frFR(), ">");
						}
		
						String varEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
						methode.nomMethode.frFR(methodeQdox.getName());
						methode.nomMethode.enUS(StringUtils.isEmpty(varEnUS) ? methodeQdox.getName() : varEnUS);
		
						List<JavaAnnotation> annotations = methodeQdox.getAnnotations();
						methode.estSubstitue(false);
						for(JavaAnnotation annotation : annotations) {
							if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
								methode.estTest(true);
							}
							if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
								methode.estSubstitue(true);
							}
						}
						List<JavaParameter> parametresQdox = methodeQdox.getParameters();
		
						if(!methode.estSubstitue && !methode.champEstStatique && !methode.champEstFinale && methodeQdox.getDeclaringClass().equals(classeQdox) 
								&& methode.champEstProtege && parametresQdox.size() == 1 && classeQdoxRetour.isVoid()
								&& StringUtils.startsWith(methodeQdox.getName(), "_")) {
				
							String nomEntite = StringUtils.substringAfter(methodeQdox.getName(), "_");
							UnEntite entite = new UnEntite();
							JavaClass classeEntite = parametresQdox.get(0).getJavaClass();
							boolean typeCouverture = false;
							String nomTypeOrigine = classeEntite.getGenericCanonicalName();
							String nomType = nomTypeOrigine;
							String typeNomCanonique = classeEntite.getCanonicalName();
		//					JavaClass classeEntite = typeBricoleur.getClassByName(typeNomCanonique);
							String listeNomTypeOrigineGenerique = null;
							String listeNomTypeGenerique = null;
							String listeNomTypeGeneriqueComplet = null;
							String varCouverture = nomEntite + varCouvertureCapitalise.toString();
		
							entite.classe_(this);
							entite.requeteSite(requeteSite);
		
							String varEntiteEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
							entite.var.frFR(nomEntite);
							entite.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? nomEntite : StringUtils.substringAfter(varEntiteEnUS, "_"));
		
							regexCommentaires(methodeQdox.getComment(), entite.commentaire);
							regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), entite.codeSource);
				
							if(classeEntite.getFullyQualifiedName().equals(nomCanoniqueCouvertureActuel)) {
								nomType = StringUtils.substringBeforeLast(StringUtils.substringAfter(nomType, "<"), ">");
								if(StringUtils.contains(nomType, "<"))
									classeEntite = typeBricoleur.getClassByName(StringUtils.substringBefore(nomType, "<"));
								else
									classeEntite = typeBricoleur.getClassByName(nomType);
								typeNomCanonique = classeEntite.getCanonicalName();
								typeCouverture = true;
								entite.couverture(true);
							} 
				
							entite.classeQdox(classeEntite);
							entite.methodeQdox(methodeQdox);
							entite.initialiserLoinUnEntite(requeteSite);
							entites.add(entite);
							if(entite.cleUnique)
								varCleUniqueActuel.tout(entite.var);
							if(entite.suggere)
								varSuggereActuel.tout(entite.var);
		
							if(!entitesTout.contains(entite))
								entitesTout.add(entite);
		
							tout.add(entite);
							importationsAjouter(new Chaine().tout(typeNomCanonique));
							importationsGenAjouter(new Chaine().tout(typeNomCanonique));
							if(listeNomTypeGenerique != null) {
								Chaine importation = new Chaine().tout(listeNomTypeGenerique);
								importationsAjouter(importation);
								importationsGenAjouter(importation);
							}
		
							boolean etendCluster = entite.etendClasse(nomCanoniqueClusterActuel);
							entite.etendCluster(etendCluster);
							if(!etendCluster && entite.nomCanoniqueGenerique.pasVide()) {
								boolean listeCluster = etendClasse(nomCanoniqueClusterActuel, entite.nomCanoniqueGenerique.toString());
								entite.listeCluster(listeCluster);
							}
		
							boolean etendPageXml = entite.etendClasse(nomCanoniquePageXmlActuel);
							entite.etendPageXml(etendPageXml);
							if(!etendPageXml && entite.nomCanoniqueGenerique.pasVide()) {
								boolean listePageXml = etendClasse(nomCanoniquePageXmlActuel, entite.nomCanoniqueGenerique.toString());
								entite.listePageXml(listePageXml);
							}
		
							boolean etendPageParti = entite.etendClasse(nomCanoniquePagePartiActuel);
							entite.etendPageParti(etendPageParti);
							if(!etendPageParti && entite.nomCanoniqueGenerique.pasVide()) {
								boolean listePageParti = etendClasse(nomCanoniquePagePartiActuel, entite.nomCanoniqueGenerique.toString());
								entite.listePageParti(listePageParti);
							}
		
		
							boolean contientRequeteSite = contientChamp(varRequeteSite.toString(), entite.classeQdox);
							entite.contientRequeteSite(contientRequeteSite);
		
							boolean contientSetterString = contientMethode(entite.var.toString(), classeQdoxString);
							entite.contientSetterString(contientSetterString);
		
							entiteEstCmd(entite);
						}
						else {
							regexCommentaires(methodeQdox.getComment(), methode.commentaire);
							regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), methode.codeSource);
							methode.classe_(this);
							methode.initialiserLoinUneMethode(requeteSite);
							methodes.add(methode);
							tout.add(methode);
						}
					}  
	
					clientSolr.add(methodeDoc); 
				}
			}
		}
		clientSolr.add(docClasse);
		clientSolr.commit();
		clientSolr.deleteByQuery(concat("classeChemin", "_", nomLangue, "_indexe_string") + ":\"" + classeChemin + "\" AND modifiee_indexe_date:[* TO " + modifiee + "-1MILLI]");
		clientSolr.commit(); 
	}
}
