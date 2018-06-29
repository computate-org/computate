package org.computate.tout.java;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrInputDocument;

import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaConstructor;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMember;

public class IndexerClasse extends RegarderClasseBase {

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
					String champCommentaire = champQdox.getComment();

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
//			}
//			else if(membreQdox instanceof JavaMethod) {
//				JavaMethod methodeQdox = (JavaMethod)membreQdox;
//				Boolean ignorer = "true".equals(regex("ignorer: (.*)", methodeQdox.getComment()));
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
//					String nomCanoniqueRetourEnUS = regex("nomCanonique.enUS: (.*)", classeQdoxRetour.getComment());
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
//					String varEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
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
//						String varEntiteEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
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
//	//						entite.cleUnique(StringUtils.equals(classe_.regex("cleUnique: (.*)", entite.commentaire.toString()), "true"));
//	//						entite.indexe(StringUtils.equals(classe_.regex("indexe: (.*)", entite.commentaire.toString()), "true"));
//	//						entite.stocke(StringUtils.equals(classe_.regex("stocke: (.*)", entite.commentaire.toString()), "true"));
//	//						entite.crypte(StringUtils.equals(classe_.regex("crypte: (.*)", entite.commentaire.toString()), "true"));
//	//						String exactStr = classe_.regex("exact: (.*)", entite.commentaire.toString());
//	//						if(exactStr != null)
//	//							entite.exact(StringUtils.equals(exactStr, "true"));
//	//						entite.suggere(StringUtils.equals(classe_.regex("suggere: (.*)", entite.commentaire.toString()), "true"));
//	//						entite.sauvegarde(StringUtils.equals(classe_.regex("sauvegarde: (.*)", entite.commentaire.toString()), "true"));
//	//						entite.incremente(StringUtils.equals(classe_.regex("incremente: (.*)", entite.commentaire.toString()), "true"));
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
		}
		clientSolr.add(docClasse);
		clientSolr.commit();
		clientSolr.deleteByQuery(concat("classeChemin", "_", nomLangue, "_indexe_string") + ":\"" + classeChemin + "\" AND modifiee_indexe_date:[* TO " + modifiee + "-1MILLI]");
		clientSolr.commit(); 
	}
}
