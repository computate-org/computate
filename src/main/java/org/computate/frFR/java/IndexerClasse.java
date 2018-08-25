package org.computate.frFR.java;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaConstructor;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMember;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;

/** 
 * classeNomCanonique_enUS: org.computate.enUS.java.IndexClass
 */  
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
	
	protected Boolean stocker(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_string"), valeurChamp);
		return valeurChamp;
	}
	
	protected Boolean stocker(SolrInputDocument doc, String nomChamp, String langueNom, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stocke_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	protected String stocker(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_string"), valeurChamp);
		return valeurChamp;
	}
	
	protected String stockerListe(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_strings"), valeurChamp);
		return valeurChamp;
	}
	
	protected String stocker(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stocke_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	protected String stockerListe(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stocke_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	protected List<String> stocker(SolrInputDocument doc, String nomChamp, String langueNom, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_stocke_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	}
	
	protected Boolean indexer(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexe_string"), valeurChamp);
		return valeurChamp;
	} 
	
	protected Boolean indexerListe(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexe_strings"), valeurChamp);
		return valeurChamp;
	} 
	
	protected Boolean indexer(SolrInputDocument doc, String nomChamp, String langueNom, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexe_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	protected String indexer(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexe_string"), valeurChamp);
		return valeurChamp;
	}
	
	protected String indexer(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexe_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	protected String indexerListe(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexe_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	protected List<String> indexer(SolrInputDocument doc, String nomChamp, String langueNom, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_indexe_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	} 
	
	protected Long indexerStocker(SolrInputDocument doc, String nomChamp, Long valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_long"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexe_long"), valeurChamp);
		return valeurChamp;
	}
	
	protected Integer indexerStocker(SolrInputDocument doc, String nomChamp, Integer valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_int"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexe_int"), valeurChamp);
		return valeurChamp;
	}
	
	protected Boolean indexerStocker(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_boolean"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexe_boolean"), valeurChamp);
		return valeurChamp;
	}
	
	protected Date indexerStocker(SolrInputDocument doc, String nomChamp, Date valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_date"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexe_date"), valeurChamp);
		return valeurChamp;
	}
	
	protected Boolean indexerStocker(SolrInputDocument doc, String nomChamp, String langueNom, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stocke_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexe_string"), valeurChamp);
		}
		return valeurChamp;
	}   
	
	protected String indexerStocker(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_string"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexe_string"), valeurChamp);
		return valeurChamp;
	}
	
	protected String indexerStockerListe(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stocke_strings"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexe_strings"), valeurChamp);
		return valeurChamp;
	}
	
	protected String indexerStocker(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stocke_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexe_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	protected String indexerStockerListe(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stocke_strings"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexe_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	protected List<String> indexerStocker(SolrInputDocument doc, String nomChamp, String langueNom, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_stocke_strings"), valeurChamp);
				doc.addField(concat(nomChamp, "_", langueNom, "_indexe_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	}

	protected SolrDocument classeDocsAjouter(String nomCanonique) throws Exception {
		SolrDocument doc = null;
		if(StringUtils.startsWith(nomEnsembleDomaine, nomCanonique)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNom + "_indexe_string:" + ClientUtils.escapeQueryChars(nomCanonique));
			rechercheSolr.addFilterQuery("partEstClasse_indexe_boolean:true");
			QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) {
				doc = listeRecherche.get(0);
				classeDocs.put(nomCanonique, doc);
			}
		}
		return doc;
	}
	
	protected String rechercherNomCanonique(String langueNom, String nomCanonique) throws Exception {
		SolrDocument doc = classeDocsAjouter(nomCanonique);
		String val = null;
		if(doc != null) {
			val = (String)doc.get("classeNomCanonique_" + langueNom + "_stocke_string");
		}
		if(StringUtils.isEmpty(val)) {
			val = nomCanonique;  
		}
		return val;
	}  

	////////////
	// autres //
	////////////

	public String stockerRegexCommentaires(String commentaire, String langueNom, SolrInputDocument doc, String varEntite) throws Exception {
		if(!StringUtils.isEmpty(commentaire)) {
			Matcher m = Pattern.compile("^(enUS|frFR): (.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			StringBuilder b = new StringBuilder();
			
			while(trouve) {
				String langue = m.group(1);
				String texte = m.group(2);
				if(langueNom.equals(langue))
					b.append(texte).append("\n");

				trouve = m.find();
			}
			if(StringUtils.isNotEmpty(b))
				stocker(doc, varEntite, langueNom, b.toString());
		}
		return commentaire;
	}
//
//	public SolrDocument documentSolr(ClassePartis classePartis) throws Exception {
//		SolrDocument documentSolr = classePartis.documentSolr(this);
//		return documentSolr;
//	}

	public ClassePartis classePartis(JavaClass classeQdox) throws Exception {
		String nomCanonique = classeQdox.getCanonicalName();
		ClassePartis resultat = classePartis.get(nomCanonique);
		if(resultat == null) {
			resultat = new ClassePartis().initClassePartis(classeQdox);
			SolrDocument documentSolr = resultat.documentSolr(this);
			classePartis.put(nomCanonique, resultat);
		}
		return resultat;
	}

	public ClassePartis classePartis(ClassePartis classePartis, String langueNom) throws Exception {
		ClassePartis resultat = ClassePartis.initClassePartis(classePartis, langueNom);
		return resultat;
	} 

	protected void indexerClasse(String classeCheminAbsolu) throws Exception { 
		SolrInputDocument classeDoc = new SolrInputDocument();
		String classeNomCanonique = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classeCheminAbsolu, "."), cheminSrcMainJava + "/"), "/", ".");
		String classeNomSimple = StringUtils.substringAfterLast(classeNomCanonique, ".");
		String classeNomCanoniqueGen = classeNomCanonique + "Gen";
		String classeNomSimpleGen = classeNomSimple + "Gen";
		JavaClass classeQdoxClasse = bricoleur.getClassByName(classeNomCanonique.toString());
		JavaClass classeQdoxSuper = classeQdoxClasse.getSuperJavaClass();
		String classeNomCanoniqueSuper = classeQdoxSuper.getCanonicalName();
		String classeNomSimpleSuper = StringUtils.substringAfterLast(classeNomCanoniqueSuper, ".");
		Boolean classeEtendGen = indexerStocker(classeDoc, "classeEtendGen", StringUtils.endsWith(classeNomSimpleSuper, "Gen"));
		
		String classeNomCompletSuper = indexerStocker(classeDoc, "classeNomCompletSuper", langueNom, classeQdoxSuper.getGenericCanonicalName());
		String classeNomCompletSuperGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(classeNomCompletSuper, "<"), ">");
		String classeNomCanoniqueSuperGenerique = null;
		String classeNomSimpleSuperGenerique = null;
		JavaClass classeSuperGeneriqueQdox = null;
		if(StringUtils.isNotEmpty(classeNomCompletSuper)) {
			indexerStocker(classeDoc, "classeNomCompletSuperGenerique", langueNom, classeNomCompletSuperGenerique);
			classeNomCanoniqueSuperGenerique = classeNomCompletSuper.contains("<") ? StringUtils.substringBefore(classeNomCompletSuper, "<") : classeNomCompletSuper;
			classeNomCanoniqueSuperGenerique = classeNomCompletSuper.contains(",") ? StringUtils.substringBefore(classeNomCompletSuper, ",") : classeNomCompletSuper;
			if(StringUtils.isNotEmpty(classeNomCanoniqueSuperGenerique)) {
				indexerStocker(classeDoc, "classeNomCanoniqueSuperGenerique", langueNom, classeNomCanoniqueSuperGenerique);
				classeSuperGeneriqueQdox = bricoleur.getClassByName(classeNomCanoniqueSuperGenerique);

				if(classeNomCanoniqueSuperGenerique.contains("."))
					classeNomSimpleSuperGenerique = StringUtils.substringAfterLast(classeNomCanoniqueSuperGenerique, ".");
				else
					classeNomSimpleSuperGenerique = classeNomCanoniqueSuperGenerique;
				indexerStocker(classeDoc, "classeNomSimpleSuperGenerique", langueNom, classeNomSimpleSuperGenerique);
			}
		}
		
		
		
		
		
		String commentaire = stockerRegexCommentaires(classeQdoxClasse.getComment(), langueNom, classeDoc, "classeCommentaire");
		String classeNomEnsemble = StringUtils.substringBeforeLast(classeNomCanonique, ".");
		String classeChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), ".java");
		String classeCheminRepertoire = StringUtils.substringBeforeLast(classeChemin, "/");
		String classeCheminGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Gen.java");
		String classeCheminRepertoireGen = StringUtils.substringBeforeLast(classeCheminGen, "/");
		String cleClasse = classeCheminAbsolu;
		Instant modifiee = Instant.now();
		Date modifieeDate = Date.from(modifiee);
		
		ArrayList<JavaClass> classesSuperQdox = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtMoi = new ArrayList<JavaClass>();
		classesSuperQdoxEtMoi.add(classeQdoxClasse);
		ArrayList<JavaClass> classesSuperQdoxEtInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi = new ArrayList<JavaClass>();
		classesSuperQdoxInterfacesEtMoi.add(classeQdoxClasse);
		peuplerClassesSuperQdoxInterfacesEtMoi(classeQdoxClasse, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi);

		indexerStocker(classeDoc, "langueNom", langueNom); 
		indexerStocker(classeDoc, "modifiee", modifieeDate); 
		indexerStocker(classeDoc, "classeNomCanonique", langueNom, classeNomCanonique); 
		indexerStocker(classeDoc, "classeNomSimple", langueNom, classeNomSimple); 
		indexerStocker(classeDoc, "classeNomEnsemble", langueNom, classeNomEnsemble); 
		indexerStocker(classeDoc, "classeNomCanoniqueGen", langueNom, classeNomCanoniqueGen); 
		indexerStocker(classeDoc, "classeNomSimpleGen", langueNom, classeNomSimpleGen); 
		indexerStocker(classeDoc, "classeNomCanoniqueSuper", langueNom, classeNomCanoniqueSuper); 
		indexerStocker(classeDoc, "classeNomSimpleSuper", langueNom, classeNomSimpleSuper); 
		indexerStocker(classeDoc, "classeCheminAbsolu", classeCheminAbsolu);
		indexerStocker(classeDoc, "classeChemin", langueNom, classeChemin); 
		indexerStocker(classeDoc, "classeCheminRepertoire", langueNom, classeCheminRepertoire);  
		indexerStocker(classeDoc, "classeCheminGen", langueNom, classeCheminGen); 
		indexerStocker(classeDoc, "classeCheminRepertoireGen", langueNom, classeCheminRepertoireGen); 

		SolrDocument classeNomCanoniqueSuperDoc = null;   
		if(StringUtils.startsWith(classeNomCanoniqueSuper, nomEnsembleDomaine)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexe_string:" + ClientUtils.escapeQueryChars(classeNomCanoniqueSuper));
			rechercheSolr.addFilterQuery("partEstClasse_indexe_boolean:true");
			QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) { 
				classeNomCanoniqueSuperDoc = listeRecherche.get(0);
			}
		}  
		for(String langueNom : autresLangues) { 
			String appliCheminLangue = appliChemins.get(langueNom);
			stockerRegexCommentaires(commentaire, langueNom, classeDoc, "classeCommentaire");
			String cheminSrcMainJavaLangue = appliCheminLangue + "/src/main/java";
			String classeNomCanoniqueLangue = regex("^classeNomCanonique\\_" + langueNom + ":\\s*(.*)", commentaire, classeNomCanonique);
			String classeNomSimpleLangue = StringUtils.substringAfterLast(classeNomCanoniqueLangue, ".");
			String classeNomEnsembleLangue = StringUtils.substringBeforeLast(classeNomCanoniqueLangue, ".");
			String classeNomCanoniqueGenLangue = classeNomCanoniqueLangue + "Gen";
			String classeNomSimpleGenLangue = classeNomSimpleLangue + "Gen";
			String classeCheminLangue = indexerStocker(classeDoc, "classeChemin", langueNom, concat(cheminSrcMainJavaLangue, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java"));
			String classeCheminRepertoireLangue = stocker(classeDoc, "classeCheminRepertoire", langueNom, StringUtils.substringBeforeLast(classeCheminLangue, "/"));
			String classeCheminGenLangue = indexerStocker(classeDoc, "classeCheminGen", langueNom, concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java"));
			String classeCheminRepertoireGenLangue = stocker(classeDoc, "classeCheminRepertoireGen", langueNom, StringUtils.substringBeforeLast(classeCheminGenLangue, "/"));

			indexerStocker(classeDoc, "classeNomCanonique", langueNom, classeNomCanoniqueLangue); 
			indexerStocker(classeDoc, "classeNomSimple", langueNom, classeNomSimpleLangue); 
			indexerStocker(classeDoc, "classeNomCanoniqueGen", langueNom, classeNomCanoniqueGenLangue); 
			indexerStocker(classeDoc, "classeNomSimpleGen", langueNom, classeNomSimpleGenLangue); 
			indexerStocker(classeDoc, "classeNomEnsemble", langueNom, classeNomEnsembleLangue); 

			if(classeNomCanoniqueSuperDoc == null) {
				indexerStocker(classeDoc, "classeNomCanoniqueSuper", langueNom, classeNomCanoniqueSuper); 
				indexerStocker(classeDoc, "classeNomSimpleSuper", langueNom, classeNomSimpleSuper); 
			}
			else {
				indexerStocker(classeDoc, "classeNomCanoniqueSuper", langueNom, (String)classeNomCanoniqueSuperDoc.get("classeNomCanonique_" + langueNom + "_stocke_string"));
				indexerStocker(classeDoc, "classeNomSimpleSuper", langueNom, (String)classeNomCanoniqueSuperDoc.get("classeNomSimple_" + langueNom + "_stocke_string"));
			}
			String classeNomCompletSuperLangue = indexerStocker(classeDoc, "classeNomCompletSuper", langueNom, rechercherNomCanonique(langueNom, classeNomCompletSuper));
			if(StringUtils.isNotEmpty(classeNomCompletSuperGenerique)) {
				String classeNomCompletSuperGeneriqueLangue = indexerStocker(classeDoc, "classeNomCompletSuperGenerique", langueNom, rechercherNomCanonique(langueNom, classeNomCompletSuperGenerique));
				String classeNomCanoniqueSuperGeneriqueLangue = indexerStocker(classeDoc, "classeNomCanoniqueSuperGenerique", langueNom, rechercherNomCanonique(langueNom, classeNomCanoniqueSuperGenerique));
				String classeNomSimpleSuperGeneriqueLangue = indexerStocker(classeDoc, "classeNomSimpleSuperGenerique", langueNom, rechercherNomCanonique(langueNom, classeNomSimpleSuperGenerique));
			}
		} 

		SolrInputDocument docClasseClone = classeDoc.deepCopy();
		Integer partNumero = 1;

		classeDoc.addField("cle", cleClasse);  

		indexerStocker(classeDoc, "partEstClasse", true);
		indexerStocker(classeDoc, "partNumero", partNumero);
		
		List<String> classeImportations = indexerStocker(classeDoc, "classeImportations", langueNom, classeQdoxClasse.getSource().getImports());
		for(String classeImportation : classeImportations) {
			SolrDocument classeImportationDoc = classeDocsAjouter(classeNomSimpleSuperGenerique);
			indexerStockerListe(classeDoc, "classeImportations", langueNom, classeImportation);
			for(String langueNom : autresLangues) {  if(classeImportationDoc == null) {
					indexerStockerListe(classeDoc, "classeImportations", langueNom, classeImportation);
				} else {
					String classeImportationLangue = (String)classeImportationDoc.get("classeNomCanonique_" + langueNom + "_stocke_string");
					indexerStockerListe(classeDoc, "classeImportations", langueNom, classeImportationLangue);
				}
			}
		}

		List<JavaMember> membresQdox = new ArrayList<JavaMember>();
		membresQdox.addAll(classeQdoxClasse.getFields());
		membresQdox.addAll(classeQdoxClasse.getConstructors());
		membresQdox.addAll(classeQdoxClasse.getMethods());
		for(JavaMember membreQdox : membresQdox) {  
			partNumero++;

			if(membreQdox instanceof JavaField) {    
				SolrInputDocument champDoc = docClasseClone.deepCopy();
				JavaField champQdox = (JavaField)membreQdox;
				String champCommentaire = champQdox.getComment();
				String champVar = champQdox.getName();
				String champCle = classeCheminAbsolu + "." + champVar;
				String champCodeSource = champQdox.getCodeBlock();

				// Champs Solr du champ. 

				champDoc.addField("cle", champCle);
				indexerStocker(champDoc, "champVar", langueNom, champVar); 
				indexerStocker(champDoc, "partEstChamp", true);
				indexerStocker(champDoc, "partNumero", partNumero);
				indexerStocker(champDoc, "champEstPublic", champQdox.isPublic()); 
				indexerStocker(champDoc, "champEstProtege", champQdox.isProtected()); 
				indexerStocker(champDoc, "champEstPrive", champQdox.isPrivate()); 
				indexerStocker(champDoc, "champEstStatique", champQdox.isStatic()); 
				indexerStocker(champDoc, "champEstFinale", champQdox.isFinal()); 
				indexerStocker(champDoc, "champEstAbstrait", champQdox.isAbstract()); 
				indexerStocker(champDoc, "champEstNatif", champQdox.isNative()); 
	
				///////////////////////
				// Champ Annotations //
				///////////////////////
				List<JavaAnnotation> annotations = champQdox.getAnnotations(); 
				ArrayList<String> annotationsLangue = new ArrayList<String>();
				Boolean champEstTest = false;
				Boolean champEstSubstitue = false;
				for(JavaAnnotation annotation : annotations) {
					String champAnnotationLangue = annotation.getType().getCanonicalName();
					indexerStocker(champDoc, "champAnnotation", langueNom, champAnnotationLangue); 

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						champEstTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						champEstSubstitue = true;
					}
				}
				indexerStocker(champDoc, "champEstTest", langueNom, champEstTest); 
				indexerStocker(champDoc, "champEstSubstitue", langueNom, champEstSubstitue); 

				ClassePartis champClassePartis = classePartis(champQdox.getType());
	
				stockerRegexCommentaires(champCommentaire, langueNom, champDoc, "champCommentaire");
				stocker(champDoc, "champNomSimpleComplet", langueNom, champClassePartis.nomSimpleComplet);
				stocker(champDoc, "champCodeSource", langueNom, champCodeSource);
				//////////////////
				// Champ Langue //
				//////////////////
				for(String langueNom : autresLangues) { 
					ClassePartis champClassePartisLangue = classePartis(champClassePartis, langueNom);
					String champVarLangue = regex("champVar_" + langueNom + ": (.*)", champCommentaire);
					champVarLangue = champVarLangue == null ? champVar : champVarLangue;
					String champCodeSourceLangue = regexRemplacerTout(champCommentaire, champCodeSource, langueNom);

					indexerStocker(champDoc, "champVar", langueNom, champVarLangue); 
					stocker(champDoc, "champNomSimpleComplet", langueNom, champClassePartisLangue.nomSimpleComplet);
					stockerRegexCommentaires(champCommentaire, langueNom, champDoc, "champCommentaire");
					stocker(champDoc, "champCodeSource", langueNom, champCodeSourceLangue);
				}  

				clientSolrComputate.add(champDoc); 
			}
			else if(membreQdox instanceof JavaConstructor) { 
				SolrInputDocument constructeurDoc = docClasseClone.deepCopy();
				JavaConstructor constructeurQdox = (JavaConstructor)membreQdox;
//				JavaClass champClasseQdox = constructeurQdox.getType();
//				String constructeurCle = classeCheminAbsolu + "." + constructeurVar;
//
//				constructeurDoc.addField("cle", constructeurCle);

				indexerStocker(classeDoc, "constructeurEstConstructeur", langueNom, true); 
				indexerStocker(classeDoc, "constructeurEstPublic", langueNom, constructeurQdox.isPublic()); 
				indexerStocker(classeDoc, "constructeurEstProtege", langueNom, constructeurQdox.isProtected()); 
				indexerStocker(classeDoc, "constructeurEstPrive", langueNom, constructeurQdox.isPrivate()); 
				indexerStocker(classeDoc, "constructeurEstStatique", langueNom, constructeurQdox.isStatic()); 
				indexerStocker(classeDoc, "constructeurEstFinale", langueNom, constructeurQdox.isFinal()); 
				indexerStocker(classeDoc, "constructeurEstAbstrait", langueNom, constructeurQdox.isAbstract()); 
				indexerStocker(classeDoc, "constructeurEstNatif", langueNom, constructeurQdox.isNative()); 

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
				JavaMethod methodeQdox = (JavaMethod)membreQdox;
				String methodeCommentaire = methodeQdox.getComment();
				Boolean ignorer = "true".equals(regex("ignorer: (.*)", methodeCommentaire));
				if(!ignorer) {
					JavaClass methodeClasseQdoxRetour = methodeQdox.getReturns();
					String methodeNomCanoniqueRetourComplet = null;
					String methodeNomCanoniqueRetour = null;
					JavaClass classeQdoxRetour = methodeQdox.getReturns();
					List<JavaParameter> methodeParamsQdox = methodeQdox.getParameters();
		
					///////////////////////
					// Methode Annotations //
					///////////////////////
					List<JavaAnnotation> annotations = methodeQdox.getAnnotations(); 
					ArrayList<String> annotationsLangue = new ArrayList<String>();
					Boolean methodeEstTest = false;
					Boolean methodeEstSubstitue = false;
					String methodeVar = methodeQdox.getName();
					for(JavaAnnotation annotation : annotations) {
						String methodeAnnotationLangue = annotation.getType().getCanonicalName();
	
						if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
							methodeEstTest = true;
						}
						if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
							methodeEstSubstitue = true;
						}
					}

					List<JavaClass> methodeExceptionsQdox = methodeQdox.getExceptions();
	
					if(!methodeEstSubstitue && !methodeQdox.isStatic() && !methodeQdox.isFinal() && methodeQdox.getDeclaringClass().equals(classeQdoxClasse) 
							&& methodeQdox.isProtected() && methodeParamsQdox.size() == 1 && classeQdoxRetour.isVoid()
							&& StringUtils.startsWith(methodeQdox.getName(), "_")) {
						// est Entite. 
						SolrInputDocument entiteDoc = docClasseClone.deepCopy();
						String entiteVar = indexerStocker(entiteDoc, "entiteVar", langueNom, StringUtils.substringAfter(methodeQdox.getName(), "_"));
						JavaClass entiteClasseQdox = methodeParamsQdox.get(0).getJavaClass();
						boolean entiteCouverture = false;
						String entiteNomCanonique = indexerStocker(entiteDoc, "entiteNomCanonique", langueNom, entiteClasseQdox.getCanonicalName());

						String entiteNomSimple;
						if(entiteNomCanonique.contains("."))
							entiteNomSimple = StringUtils.substringBefore(StringUtils.substringAfterLast(entiteNomCanonique, "."), ">");
						else
							entiteNomSimple = StringUtils.substringBefore(entiteNomCanonique.toString(), ">");
						indexerStocker(entiteDoc, "entiteNomSimple", langueNom, entiteNomSimple);

						String entiteTypeOrigine = indexerStocker(entiteDoc, "entiteTypeOrigine", langueNom, entiteClasseQdox.getGenericCanonicalName());

						String entiteNomCompletGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteTypeOrigine, "<"), ">");
						String entiteNomCanoniqueGenerique = null;
						JavaClass entiteClasseGeneriqueQdox = null;
						String entiteNomSimpleGenerique = null;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique)) {
							indexerStocker(entiteDoc, "entiteNomCompletGenerique", langueNom, entiteNomCompletGenerique);
							entiteNomCanoniqueGenerique = entiteNomCompletGenerique.contains("<") ? StringUtils.substringBefore(entiteNomCompletGenerique, "<") : entiteNomCompletGenerique;
							entiteNomCanoniqueGenerique = entiteNomCompletGenerique.contains(",") ? StringUtils.substringBefore(entiteNomCompletGenerique, ",") : entiteNomCompletGenerique;
							if(StringUtils.isNotEmpty(entiteNomCanoniqueGenerique)) {
								indexerStocker(entiteDoc, "entiteNomCanoniqueGenerique", langueNom, entiteNomCanoniqueGenerique);
								entiteClasseGeneriqueQdox = bricoleur.getClassByName(entiteNomCanoniqueGenerique);
	//							String nomCanoniqueGeneriqueEnUS = classe_.regex("nomCanonique.enUS:\\s*(.*)", commentaire, 1);
	//							o.enUS(StringUtils.isEmpty(nomCanoniqueGeneriqueEnUS) ? o.frFR() : nomCanoniqueGeneriqueEnUS);
	//							if(nomCanoniqueGenerique.frFR().contains(".")) {
	//								classe_.importationsAjouter(nomCanoniqueGenerique);
	//								classe_.importationsGenAjouter(nomCanoniqueGenerique);
	//							}
	
								if(entiteNomCanoniqueGenerique.contains("."))
									entiteNomSimpleGenerique = StringUtils.substringAfterLast(entiteNomCanoniqueGenerique, ".");
								else
									entiteNomSimpleGenerique = entiteNomCanoniqueGenerique;
								indexerStocker(entiteDoc, "entiteNomSimpleGenerique", langueNom, entiteNomSimpleGenerique);
							}
						}
						
						String entiteNomCanoniqueComplet;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique))
							entiteNomCanoniqueComplet = entiteNomCanonique + "<" + entiteNomCompletGenerique + ">";
						else
							entiteNomCanoniqueComplet = entiteNomCanonique;
						indexerStocker(entiteDoc, "entiteNomCanoniqueComplet", langueNom, entiteNomCanoniqueComplet);
						
						String entiteNomSimpleComplet = entiteNomSimple;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique)) {
							entiteNomSimpleComplet += "<";
							if(entiteNomCompletGenerique.contains(".")) {
								entiteNomSimpleComplet += StringUtils.substringAfterLast(entiteNomCompletGenerique, ".");
							}
							else {
								entiteNomSimpleComplet += entiteNomCompletGenerique;
							}
							entiteNomSimpleComplet += ">";
						}
						indexerStocker(entiteDoc, "entiteNomSimpleComplet", langueNom, entiteNomSimpleComplet);
						
						String entiteNomSimpleCompletGenerique = null;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique)) {
							if(entiteNomCompletGenerique.contains(".")) {
								entiteNomSimpleCompletGenerique = StringUtils.substringAfterLast(entiteNomCompletGenerique, ".");
							}
							else {
								entiteNomSimpleCompletGenerique = entiteNomCompletGenerique;
							}
						}
						indexerStocker(entiteDoc, "entiteNomSimpleCompletGenerique", langueNom, entiteNomSimpleCompletGenerique);
						
						JavaClass entiteClasseQdoxBase = null;
						JavaClass entiteClasseSuperQdox = entiteClasseQdox.getSuperJavaClass();
						if(entiteClasseSuperQdox != null) {
							String entiteNomCanoniqueSuperQdox = entiteClasseSuperQdox.getCanonicalName();
							if(entiteNomCanoniqueSuperQdox.endsWith("Gen")) {
								entiteClasseQdoxBase = entiteClasseSuperQdox.getSuperJavaClass();
							}
						}
						
						String entiteNomCanoniqueBase = null;
						if(entiteClasseQdoxBase != null) {
							String s = entiteClasseQdoxBase.getCanonicalName();
							if(s.contains("."))
								entiteNomCanoniqueBase = s;
						}
						indexerStocker(entiteDoc, "entiteNomCanoniqueBase", langueNom, entiteNomCanoniqueBase);
						
						String entiteNomSimpleBase = null;
						if(StringUtils.isNotEmpty(entiteNomCanoniqueBase)) {
							entiteNomSimpleBase = StringUtils.substringAfterLast(entiteNomCanoniqueBase, ".");
						}
						indexerStocker(entiteDoc, "entiteNomSimpleBase", langueNom, entiteNomSimpleBase);
						
						String entiteVarParam;
						if(entiteNomCanonique.equals(ArrayList.class.getCanonicalName()) || entiteNomCanonique.equals(List.class.getCanonicalName()))
							entiteVarParam = "l";
						else
							entiteVarParam = "o";
						indexerStocker(entiteDoc, "entiteVarParam", langueNom, entiteVarParam);
						
						String entiteVarCouverture = indexerStocker(entiteDoc, "entiteVarCouverture", langueNom, entiteVar + "Couverture");
//						boolean entiteCouverture = false;
//	
//						String varEntiteEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
//						entite.var.frFR(entiteVar);
//						entite.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? entiteVar : StringUtils.substringAfter(varEntiteEnUS, "_"));
//	
//						regexCommentaires(methodeQdox.getComment(), entite.commentaire);
//						regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), entite.codeSource);
//			
//						if(entiteClasseQdox.getFullyQualifiedName().equals(nomCanoniqueCouvertureActuel)) {
//							entiteType = StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteType, "<"), ">");
//							if(StringUtils.contains(entiteType, "<"))
//								entiteClasseQdox = typeBricoleur.getClassByName(StringUtils.substringBefore(entiteType, "<"));
//							else
//								entiteClasseQdox = typeBricoleur.getClassByName(entiteType);
//							entiteNomCanonique = entiteClasseQdox.getCanonicalName();
//							entiteCouverture = true;
//							entite.couverture(true);
//						} 
//						if(entite.cleUnique)
//							varCleUniqueActuel.tout(entite.var);
//						if(entite.suggere)
//							varSuggereActuel.tout(entite.var);
//	
//						if(!entitesTout.contains(entite))
//							entitesTout.add(entite);
//	
//						tout.add(entite);
//						importationsAjouter(new Chaine().tout(entiteNomCanonique));
//						importationsGenAjouter(new Chaine().tout(entiteNomCanonique));
//						if(entiteListeTypeGenerique != null) {
//							Chaine importation = new Chaine().tout(entiteListeTypeGenerique);
//							importationsAjouter(importation);
//							importationsGenAjouter(importation);
//						}
//	
//						boolean etendCluster = etendClasse(entiteNomCanoniqueClusterActuel);
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
						
						
						
						
						
						
						
						
						
						
						
						
						
						indexerStocker(entiteDoc, "entiteVar", langueNom, entiteVar);
						for(JavaAnnotation annotation : annotations) {
							String entiteAnnotationLangue = indexerStocker(entiteDoc, "entiteAnnotations", langueNom, annotation.getType().getCanonicalName());
						}
//						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void")) {
//							entiteNomCanoniqueRetourComplet = indexerStocker(entiteDoc, "entiteNomCanoniqueRetourComplet", langueNom, classeQdoxRetour.getGenericCanonicalName());
//							entiteNomCanoniqueRetour = indexerStocker(entiteDoc, "entiteNomCanoniqueRetour", langueNom, classeQdoxRetour.getCanonicalName());
//							String entiteNomSimpleRetour = indexerStocker(entiteDoc, "entiteNomSimpleRetour", langueNom, StringUtils.substringAfterLast(entiteNomCanoniqueRetour, "."));
//							String listeNomTypeOrigineRetourGenerique = entiteNomCanoniqueRetourComplet;
//							String entiteNomCanoniqueRetourGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineRetourGenerique, "<"), ">");
//							String entiteNomSimpleRetourComplet;
//							String entiteNomSimpleRetourGenerique;
//							entiteNomCanoniqueRetourGenerique = entiteNomCanoniqueRetourGenerique.contains("<") ? StringUtils.substringBefore(entiteNomCanoniqueRetourGenerique, "<") : entiteNomCanoniqueRetourGenerique;
//							entiteNomCanoniqueRetourGenerique = entiteNomCanoniqueRetourGenerique.contains(",") ? StringUtils.substringBefore(entiteNomCanoniqueRetourGenerique, ",") : entiteNomCanoniqueRetourGenerique;
//							if(StringUtils.isNotEmpty(entiteNomCanoniqueRetourGenerique)) {
//								indexerStocker(entiteDoc, "entiteNomCanoniqueRetourGenerique", langueNom, entiteNomCanoniqueRetourGenerique);
//	
//								if(StringUtils.contains(entiteNomCanoniqueRetourGenerique, "."))
//									entiteNomSimpleRetourGenerique = indexerStocker(entiteDoc, "entiteNomSimpleRetourGenerique", langueNom, StringUtils.substringAfterLast(entiteNomCanoniqueRetourGenerique, "."));
//								else
//									entiteNomSimpleRetourGenerique = indexerStocker(entiteDoc, "entiteNomSimpleRetourGenerique", langueNom, entiteNomCanoniqueRetourGenerique);
//	
//								if(StringUtils.contains(entiteNomSimpleRetourGenerique, ".")) {
//									entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomSimpleRetourComplet", langueNom, concat(StringUtils.substringAfterLast(entiteNomSimpleRetour, "."), "<", entiteNomSimpleRetourGenerique, ">"));
//								}
//								else {
//									entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomSimpleRetourComplet", langueNom, concat(entiteNomSimpleRetour, "<", entiteNomSimpleRetourGenerique, ">"));
//								}
//							}
//							else {
//								entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomCanoniqueRetourComplet", langueNom, entiteNomSimpleRetour);
//							}
//						}
	
						String entiteCle = classeCheminAbsolu + "." + entiteVar;
		
						// Entites Solr du entite. 
		
						entiteDoc.addField("cle", entiteCle);
						indexerStocker(entiteDoc, "partEstEntite", true);
						indexerStocker(entiteDoc, "partNumero", partNumero);
	
						String entiteVarLangue = regex("var\\." + langueNom + ": (.*)", methodeCommentaire);
						entiteVarLangue = indexerStocker(entiteDoc, "entiteVar", langueNom, entiteVarLangue == null ? entiteVar : entiteVarLangue);
	
						List<String> entiteCommentairesLangue = regexListe("(.*)", methodeCommentaire);
						String entiteCommentaireLangue = indexerStocker(entiteDoc, "entiteCommentaire", langueNom, StringUtils.join(entiteCommentairesLangue, "\n"));
	
						String entiteBlocCode = methodeQdox.getCodeBlock();
						String entiteBlocCodeLangue = entiteBlocCode;
						ArrayList<String> remplacerClesLangue = regexListe("^r." + langueNom + "\\s*=\\s*(.*)\\n.*", methodeCommentaire);
						ArrayList<String> remplacerValeursLangue = regexListe("^r." + langueNom + "\\s*=\\s*.*\\n(.*)", methodeCommentaire);
						for(int i = 0; i < remplacerClesLangue.size(); i++) {
							String cle = remplacerClesLangue.get(i);
							String valeur = remplacerValeursLangue.get(i);
							StringUtils.replace(entiteBlocCodeLangue, cle, valeur);
						}
						stocker(entiteDoc, "entiteBlocCode", langueNom, entiteBlocCodeLangue); 

						stockerRegexCommentaires(methodeCommentaire, langueNom, entiteDoc, "entiteCommentaire");

						clientSolrComputate.add(entiteDoc); 
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
					else {
						// est Méthode.  
						
						SolrInputDocument methodeDoc = docClasseClone.deepCopy();
						indexerStocker(methodeDoc, "methodeVar", langueNom, methodeVar);
						for(Integer methodeParamNum = 1; methodeParamNum <= methodeParamsQdox.size(); methodeParamNum++) {
							JavaParameter methodeParamQdox = methodeParamsQdox.get(methodeParamNum - 1);
							String methodeParamVar = methodeParamQdox.getName();
							stockerListe(methodeDoc, "methodeParamVar", langueNom, methodeParamVar);
							ClassePartis methodeParamClassePartis = classePartis(methodeParamQdox.getJavaClass());
							stockerListe(methodeDoc, "methodeParamNomSimpleComplet", langueNom, methodeParamClassePartis.nomSimpleComplet);
							for(String langueNom : autresLangues) { 
								String methodeParamVarLangue = regex("methodeParamVar_" + langueNom + "_" + methodeParamNum + ": (.*)", methodeCommentaire);
								if(methodeParamVarLangue == null)
									methodeParamVarLangue = methodeParamVar;
								ClassePartis methodeParamClassePartisLangue = classePartis(methodeParamClassePartis, langueNom);

								stockerListe(methodeDoc, "methodeParamNomSimpleComplet", langueNom, methodeParamClassePartisLangue.nomSimpleComplet);
								stockerListe(methodeDoc, "methodeParamVar", langueNom, methodeParamVarLangue);
							}  
						}
						for(JavaAnnotation annotation : annotations) {
							String methodeAnnotationBlocCode = stockerListe(methodeDoc, "methodeAnnotationBlocCode", langueNom, annotation.getCodeBlock());
						}
						for(JavaClass methodeExceptionQdox : methodeExceptionsQdox) {
							String methodeExceptionNomSimpleComplet = methodeExceptionQdox.getSimpleName();
							stockerListe(methodeDoc, "methodeExceptionNomSimpleComplet", methodeExceptionNomSimpleComplet);
						}
						Boolean methodeEstVide = false;
						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void")) {
	
							ClassePartis methodeRetourClassePartis = classePartis(methodeQdox.getReturns());
				
							stocker(methodeDoc, "methodeRetourNomSimpleComplet", langueNom, methodeRetourClassePartis.nomSimpleComplet);
							//////////////////
							// Champ Langue //
							//////////////////
							for(String langueNom : autresLangues) { 
								ClassePartis methodeRetourClassePartisLangue = classePartis(methodeRetourClassePartis, langueNom);
								stocker(methodeDoc, "methodeNomSimpleComplet", langueNom, methodeRetourClassePartisLangue.nomSimpleComplet);
							}  
							
							
							
							
							
							
							
							
							
							
//							ClassePartis methodeRetourClassePartis = classePartis(methodeQdox.getReturns());
//				
//							stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
//							stocker(methodeDoc, "methodeRetourNomSimpleComplet", langueNom, methodeRetourClassePartis.nomSimpleComplet);
//							stocker(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSource);
//							//////////////////
//							// Champ Langue //
//							//////////////////
//							for(String langueNom : autresLangues) { 
//								ClassePartis methodeRetourClassePartisLangue = classePartis(methodeRetourClassePartis, langueNom);
//								String methodeVarLangue = regex("methodeVar_" + langueNom + ": (.*)", methodeCommentaire);
//								methodeVarLangue = methodeVarLangue == null ? methodeVar : methodeVarLangue;
//								String methodeCodeSourceLangue = regexRemplacerTout(methodeCommentaire, methodeCodeSource, langueNom);
//			
//								indexerStocker(methodeDoc, "methodeVar", langueNom, methodeVarLangue); 
//								stocker(methodeDoc, "methodeNomSimpleComplet", langueNom, methodeClassePartisLangue.nomSimpleComplet);
//								stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
//								stocker(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSourceLangue);
//							}  
							
							
							
							
							
							
							
							
							
							
					
					
					
							methodeNomCanoniqueRetourComplet = indexerStocker(methodeDoc, "methodeNomCanoniqueRetourComplet", langueNom, classeQdoxRetour.getGenericCanonicalName());
							methodeNomCanoniqueRetour = indexerStocker(methodeDoc, "methodeNomCanoniqueRetour", langueNom, classeQdoxRetour.getCanonicalName());
							String methodeNomSimpleRetour = indexerStocker(methodeDoc, "methodeNomSimpleRetour", langueNom, StringUtils.substringAfterLast(methodeNomCanoniqueRetour, "."));
							String listeNomTypeOrigineRetourGenerique = methodeNomCanoniqueRetourComplet;
							String methodeNomCanoniqueRetourGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineRetourGenerique, "<"), ">");
							String methodeNomSimpleRetourComplet;
							String methodeNomSimpleRetourGenerique;
							methodeNomCanoniqueRetourGenerique = methodeNomCanoniqueRetourGenerique.contains("<") ? StringUtils.substringBefore(methodeNomCanoniqueRetourGenerique, "<") : methodeNomCanoniqueRetourGenerique;
							methodeNomCanoniqueRetourGenerique = methodeNomCanoniqueRetourGenerique.contains(",") ? StringUtils.substringBefore(methodeNomCanoniqueRetourGenerique, ",") : methodeNomCanoniqueRetourGenerique;
							if(StringUtils.isNotEmpty(methodeNomCanoniqueRetourGenerique)) {
								indexerStocker(methodeDoc, "methodeNomCanoniqueRetourGenerique", langueNom, methodeNomCanoniqueRetourGenerique);
	
								if(StringUtils.contains(methodeNomCanoniqueRetourGenerique, "."))
									methodeNomSimpleRetourGenerique = indexerStocker(methodeDoc, "methodeNomSimpleRetourGenerique", langueNom, StringUtils.substringAfterLast(methodeNomCanoniqueRetourGenerique, "."));
								else
									methodeNomSimpleRetourGenerique = indexerStocker(methodeDoc, "methodeNomSimpleRetourGenerique", langueNom, methodeNomCanoniqueRetourGenerique);
	
								if(StringUtils.contains(methodeNomSimpleRetourGenerique, ".")) {
									methodeNomSimpleRetourComplet = indexerStocker(methodeDoc, "methodeNomSimpleRetourComplet", langueNom, concat(StringUtils.substringAfterLast(methodeNomSimpleRetour, "."), "<", methodeNomSimpleRetourGenerique, ">"));
								}
								else {
									methodeNomSimpleRetourComplet = indexerStocker(methodeDoc, "methodeNomSimpleRetourComplet", langueNom, concat(methodeNomSimpleRetour, "<", methodeNomSimpleRetourGenerique, ">"));
								}
							}
							else {
								methodeNomSimpleRetourComplet = indexerStocker(methodeDoc, "methodeNomSimpleRetourComplet", langueNom, methodeNomSimpleRetour);
							}
						}
						else {
							methodeEstVide = true;
						}
						methodeEstVide = indexerStocker(methodeDoc, "methodeEstVide", methodeEstVide);
	
						String methodeCle = classeCheminAbsolu + "." + methodeVar + "(";

						for(int i = 0; i < methodeParamsQdox.size(); i++) {
							JavaParameter paramQdox = methodeParamsQdox.get(i);
							if(i > 0)
								methodeCle += ", ";
							methodeCle += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
						}
						methodeCle += ")"; 
		
						// Methodes Solr du methode. 
		
						methodeDoc.addField("cle", methodeCle);
						indexerStocker(methodeDoc, "partEstMethode", true);
						indexerStocker(methodeDoc, "partNumero", partNumero);

						indexerStocker(methodeDoc, "methodeEstPublic", methodeQdox.isPublic());
						indexerStocker(methodeDoc, "methodeEstProtege", methodeQdox.isProtected());
						indexerStocker(methodeDoc, "methodeEstPrive", methodeQdox.isPrivate());
						indexerStocker(methodeDoc, "methodeEstStatique", methodeQdox.isStatic());
						indexerStocker(methodeDoc, "methodeEstFinale", methodeQdox.isFinal());
						indexerStocker(methodeDoc, "methodeEstAbstrait", methodeQdox.isAbstract());
						indexerStocker(methodeDoc, "methodeEstNatif", methodeQdox.isNative());
						indexerStocker(methodeDoc, "methodeEstTest", methodeEstTest);
						indexerStocker(methodeDoc, "methodeEstSubstitue", methodeEstSubstitue);
						stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
	
						String methodeVarLangue = regex("var\\." + langueNom + ": (.*)", methodeCommentaire);
						methodeVarLangue =  methodeVarLangue == null ? methodeVar : methodeVarLangue;
	
						List<String> methodeCommentairesLangue = regexListe("^" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);

						String methodeCodeSource = methodeQdox.getSourceCode();
						String methodeCodeSourceLangue = methodeCodeSource;
						ArrayList<String> remplacerClesLangue = regexListe("^r." + langueNom + "\\s*=\\s*(.*)\\n.*", methodeCommentaire);
						ArrayList<String> remplacerValeursLangue = regexListe("^r." + langueNom + "\\s*=\\s*.*\\n(.*)", methodeCommentaire);
						for(int i = 0; i < remplacerClesLangue.size(); i++) {
							String cle = remplacerClesLangue.get(i);
							String valeur = remplacerValeursLangue.get(i);
							StringUtils.replace(methodeCodeSourceLangue, cle, valeur);
						}
						stocker(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSourceLangue);
		
						String varEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
//						methode.nomMethode.frFR(methodeQdox.getName());
//						methode.nomMethode.enUS(StringUtils.isEmpty(varEnUS) ? methodeQdox.getName() : varEnUS);
//		
//						List<JavaParameter> parametresQdox = methodeParamsQdox;
//
//						regexCommentaires(methodeQdox.getComment(), methode.commentaire);
//						regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), methode.codeSource);
//						methode.classe_(this);
//						methode.initialiserLoinUneMethode(requeteSite);
//						methodes.add(methode);
//						tout.add(methode);

						for(String langueNom : autresLangues) {  

							methodeVarLangue = regex("methodeVar\\_" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
							methodeVarLangue = indexerStocker(methodeDoc, "methodeVar", langueNom, methodeVarLangue == null ? methodeVar : methodeVarLangue);
		
							methodeCommentairesLangue = regexListe("^" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
		
							methodeCodeSourceLangue = regexRemplacerTout(methodeCommentaire, methodeCodeSource, langueNom);
							stocker(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSourceLangue);
							stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
						} 
	
						clientSolrComputate.add(methodeDoc); 
					}
			
						//////////////////
						// Methode Langue //
						//////////////////
		
	//						String methodeVarLangue = regex("var\\." + langueNom + ": (.*)", methodeCommentaire);
	//						methodeVarLangue = methodeVarLangue == null ? methodeVar : methodeVarLangue;
	//						methodeDoc.addField(concat("methodeVar_", langueNom, "_indexe_string"), methodeVarLangue);
	//						methodeDoc.addField(concat("methodeVar_", langueNom, "_stocke_string"), methodeVarLangue);
	//	
	//						List<String> methodeCommentairesLangue = regexListe("(.*)", methodeCommentaire);
	//						String methodeCommentaireLangue = StringUtils.join(methodeCommentairesLangue, "\n");
	//						methodeDoc.addField(concat("methodeCommentaire_", langueNom, "_indexe_string"), methodeCommentaireLangue);
	//						methodeDoc.addField(concat("methodeCommentaire_", langueNom, "_stocke_string"), methodeCommentaireLangue);
	//	
	//						String methodeBlocCode = methodeQdox.getCodeBlock();
	//						String methodeBlocCodeLangue = methodeBlocCode;
	//						ArrayList<String> remplacerClesLangue = regexListe("^r." + langueNom + "\\s*=\\s*(.*)\\n.*", methodeCommentaire);
	//						ArrayList<String> remplacerValeursLangue = regexListe("^r." + langueNom + "\\s*=\\s*.*\\n(.*)", methodeCommentaire);
	//						for(int i = 0; i < remplacerClesLangue.size(); i++) {
	//							String cle = remplacerClesLangue.get(i);
	//							String valeur = remplacerValeursLangue.get(i);
	//							StringUtils.replace(methodeBlocCodeLangue, cle, valeur);
	//						}
	//						methodeDoc.addField(concat("methodeBlocCode_", langueNom, "_indexe_string"), methodeBlocCodeLangue);
	//						methodeDoc.addField(concat("methodeBlocCode_", langueNom, "_stocke_string"), methodeBlocCodeLangue);
	//					
	//						String nomCanoniqueRetourComplet = null;
	//						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void"))
	//							nomCanoniqueRetourComplet = classeQdoxRetour.getGenericCanonicalName();
	//		//						methode.nomCanoniqueRetourComplet(classeQdoxRetour.getGenericCanonicalName());
	//		
	//						String nomCanoniqueRetourEnUS = regex("nomCanonique.enUS: (.*)", classeQdoxRetour.getComment());
	//						methode.nomCanoniqueRetour.frFR(StringUtils.substringBefore(nomCanoniqueRetourComplet, "<"));    
	//						methode.nomCanoniqueRetour.enUS(StringUtils.isEmpty(nomCanoniqueRetourEnUS) ? methode.nomCanoniqueRetour.frFR() : nomCanoniqueRetourEnUS);
	//		
	//						if(StringUtils.contains(nomCanoniqueRetourComplet, "<")) {
	//							methode.nomCanoniqueRetourGenerique.frFR(StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueRetourComplet, ">"), "<"));  
	//							methode.nomCanoniqueRetourGenerique.enUS(StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueRetourComplet, ">"), "<"));  
	//						}
	//						if(StringUtils.contains(methode.nomCanoniqueRetour.toString(), ".")) {
	//							methode.nomSimpleRetour.frFR(StringUtils.substringAfterLast(methode.nomCanoniqueRetour.frFR(), "."));    
	//							methode.nomSimpleRetour.enUS(StringUtils.substringAfterLast(methode.nomCanoniqueRetour.enUS(), "."));    
	//							if(methode.nomCanoniqueRetourGenerique.estVide()) {
	//								methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR());
	//								methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS());
	//							}
	//							else {
	//								if(methode.nomCanoniqueRetourGenerique.contient(".")) {
	//									methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR(), "<", StringUtils.substringAfterLast(methode.nomCanoniqueRetourGenerique.frFR(), "."), ">");    
	//									methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS(), "<", StringUtils.substringAfterLast(methode.nomCanoniqueRetourGenerique.enUS(), "."), ">");    
	//								}
	//								else {
	//									methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR(), "<", methode.nomCanoniqueRetourGenerique.frFR(), ">");    
	//									methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS(), "<", methode.nomCanoniqueRetourGenerique.enUS(), ">");    
	//								}
	//							}
	//						}
	//						else {
	//							methode.nomSimpleRetour.frFR(methode.nomCanoniqueRetour.frFR());
	//							methode.nomSimpleRetour.enUS(methode.nomCanoniqueRetour.enUS());
	//							if(methode.nomCanoniqueRetourGenerique.estVide()) {
	//								methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR());
	//								methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS());
	//							}
	//							else {
	//								methode.nomSimpleRetourComplet.frFR(methode.nomSimpleRetour.frFR(), "<", methode.nomCanoniqueRetourGenerique.frFR(), ">");    
	//								methode.nomSimpleRetourComplet.enUS(methode.nomSimpleRetour.enUS(), "<", methode.nomCanoniqueRetourGenerique.enUS(), ">");    
	//							}
	//						}
	//		
	//						if(methode.nomCanoniqueRetourGenerique.estVide()) {
	//							methode.nomCanoniqueRetourComplet.enUS(methode.nomCanoniqueRetour.enUS());
	//							methode.nomCanoniqueRetourComplet.frFR(methode.nomCanoniqueRetour.frFR());
	//						}
	//						else {
	//							methode.nomCanoniqueRetourComplet.enUS(methode.nomCanoniqueRetour.enUS(), "<", methode.nomCanoniqueRetourGenerique.enUS(), ">");
	//							methode.nomCanoniqueRetourComplet.frFR(methode.nomCanoniqueRetour.frFR(), "<", methode.nomCanoniqueRetourGenerique.frFR(), ">");
	//						}
	//		
	//						String varEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
	//						methode.nomMethode.frFR(methodeQdox.getName());
	//						methode.nomMethode.enUS(StringUtils.isEmpty(varEnUS) ? methodeQdox.getName() : varEnUS);
	//		
	//						List<JavaAnnotation> annotations = methodeQdox.getAnnotations();
	//						methode.estSubstitue(false);
	//						for(JavaAnnotation annotation : annotations) {
	//							if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
	//								methode.estTest(true);
	//							}
	//							if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
	//								methode.estSubstitue(true);
	//							}
	//						}
	//						List<JavaParameter> parametresQdox = methodeParamsQdox;
	//		
	//						if(!methode.estSubstitue && !methode.champEstStatique && !methode.champEstFinale && methodeQdox.getDeclaringClass().equals(classeQdox) 
	//								&& methode.champEstProtege && parametresQdox.size() == 1 && classeQdoxRetour.isVoid()
	//								&& StringUtils.startsWith(methodeQdox.getName(), "_")) {
	//				
	//							String nomEntite = StringUtils.substringAfter(methodeQdox.getName(), "_");
	//							UnEntite entite = new UnEntite();
	//							JavaClass classeEntite = parametresQdox.get(0).getJavaClass();
	//							boolean typeCouverture = false;
	//							String nomTypeOrigine = classeEntite.getGenericCanonicalName();
	//							String nomType = nomTypeOrigine;
	//							String typeNomCanonique = classeEntite.getCanonicalName();
	//		//					JavaClass classeEntite = typeBricoleur.getClassByName(typeNomCanonique);
	//							String listeNomTypeOrigineGenerique = null;
	//							String listeNomTypeGenerique = null;
	//							String listeNomTypeGeneriqueComplet = null;
	//							String varCouverture = nomEntite + varCouvertureCapitalise.toString();
	//		
	//							entite.classe_(this);
	//							entite.requeteSite(requeteSite);
	//		
	//							String varEntiteEnUS = regex("^var.enUS: (.*)", methodeQdox.getComment());
	//							entite.var.frFR(nomEntite);
	//							entite.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? nomEntite : StringUtils.substringAfter(varEntiteEnUS, "_"));
	//		
	//							regexCommentaires(methodeQdox.getComment(), entite.commentaire);
	//							regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), entite.codeSource);
	//				
	//							if(classeEntite.getFullyQualifiedName().equals(nomCanoniqueCouvertureActuel)) {
	//								nomType = StringUtils.substringBeforeLast(StringUtils.substringAfter(nomType, "<"), ">");
	//								if(StringUtils.contains(nomType, "<"))
	//									classeEntite = typeBricoleur.getClassByName(StringUtils.substringBefore(nomType, "<"));
	//								else
	//									classeEntite = typeBricoleur.getClassByName(nomType);
	//								typeNomCanonique = classeEntite.getCanonicalName();
	//								typeCouverture = true;
	//								entite.couverture(true);
	//							} 
	//				
	//							entite.classeQdox(classeEntite);
	//							entite.methodeQdox(methodeQdox);
	//							entite.initialiserLoinUnEntite(requeteSite);
	//							entites.add(entite);
	//							if(entite.cleUnique)
	//								varCleUniqueActuel.tout(entite.var);
	//							if(entite.suggere)
	//								varSuggereActuel.tout(entite.var);
	//		
	//							if(!entitesTout.contains(entite))
	//								entitesTout.add(entite);
	//		
	//							tout.add(entite);
	//							importationsAjouter(new Chaine().tout(typeNomCanonique));
	//							importationsGenAjouter(new Chaine().tout(typeNomCanonique));
	//							if(listeNomTypeGenerique != null) {
	//								Chaine importation = new Chaine().tout(listeNomTypeGenerique);
	//								importationsAjouter(importation);
	//								importationsGenAjouter(importation);
	//							}
	//		
	//							boolean etendCluster = entite.etendClasse(nomCanoniqueClusterActuel);
	//							entite.etendCluster(etendCluster);
	//							if(!etendCluster && entite.nomCanoniqueGenerique.pasVide()) {
	//								boolean listeCluster = etendClasse(nomCanoniqueClusterActuel, entite.nomCanoniqueGenerique.toString());
	//								entite.listeCluster(listeCluster);
	//							}
	//		
	//							boolean etendPageXml = entite.etendClasse(nomCanoniquePageXmlActuel);
	//							entite.etendPageXml(etendPageXml);
	//							if(!etendPageXml && entite.nomCanoniqueGenerique.pasVide()) {
	//								boolean listePageXml = etendClasse(nomCanoniquePageXmlActuel, entite.nomCanoniqueGenerique.toString());
	//								entite.listePageXml(listePageXml);
	//							}
	//		
	//							boolean etendPageParti = entite.etendClasse(nomCanoniquePagePartiActuel);
	//							entite.etendPageParti(etendPageParti);
	//							if(!etendPageParti && entite.nomCanoniqueGenerique.pasVide()) {
	//								boolean listePageParti = etendClasse(nomCanoniquePagePartiActuel, entite.nomCanoniqueGenerique.toString());
	//								entite.listePageParti(listePageParti);
	//							}
	//		
	//		
	//							boolean contientRequeteSite = contientChamp(varRequeteSite.toString(), entite.classeQdox);
	//							entite.contientRequeteSite(contientRequeteSite);
	//		
	//							boolean contientSetterString = contientMethode(entite.var.toString(), classeQdoxString);
	//							entite.contientSetterString(contientSetterString);
	//		
	//							entiteEstCmd(entite);
	//						}
	//						else {
	//							regexCommentaires(methodeQdox.getComment(), methode.commentaire);
	//							regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), methode.codeSource);
	//							methode.classe_(this);
	//							methode.initialiserLoinUneMethode(requeteSite);
	//							methodes.add(methode);
	//							tout.add(methode);
	//						}
				}
			}
		}
		clientSolrComputate.add(classeDoc);
		clientSolrComputate.commit();
		String qSupprimer = concat("classeCheminAbsolu_indexe_string", ":\"", classeChemin, "\" AND (modifiee_indexe_date:[* TO ", modifiee.toString(), "-1MILLI] OR (*:* NOT modifiee_indexe_date:*))");
		clientSolrComputate.deleteByQuery(qSupprimer);
		clientSolrComputate.commit(); 
	}
}
