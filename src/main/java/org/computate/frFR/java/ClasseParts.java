package org.computate.frFR.java; 

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.thoughtworks.qdox.model.JavaClass;

/**
 * NomCanonique.enUS: org.computate.enUS.java.ClassParts
 */  
public class ClasseParts {
//
//	private ConfigSite configSite;

	public static final String NOM_ENSEMBLE_DOMAINE_COMPUTATE = "org.computate.";

	private String valeurGenerique;

	/**
	 */
	private String nomCanonique;
	/**
	 */
	public String nomCanonique(String langueNom) {
		if(langueNom == null || documentSolr == null)
			return nomCanonique;
		else
			return (String)documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string");
	}

	/**
	 */  
	private String nomCanoniqueComplet;
	/**
	 */
	public String nomCanoniqueComplet(String langueNom) {
		StringBuilder b = new StringBuilder();

		if(langueNom == null || documentSolr == null) {
			b.append(nomCanonique);
		}
		else {
			b.append((String)documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string"));
		}
		if(valeurGenerique != null) {
			b.append("<");
			for(int i = 0; i < classeParts.size(); i++) {
				ClasseParts classePart = classeParts.get(i);
				if(i > 0)
					b.append(", ");
				b.append(classePart.nomCanoniqueComplet(langueNom));
			}
			b.append(">");
		}

		return b.toString();
	}

	/**
	 */
	private String nomSimple;
	/**
	 */
	public String nomSimple(String langueNom) {
		if(langueNom == null || documentSolr == null)
			return nomSimple;
		else
			return (String)documentSolr.get("classeNomSimple_" + langueNom + "_stored_string");
	}
	/**
	 */
	public String nomSimpleComplet(String langueNom) {
		StringBuilder b = new StringBuilder();

		if(langueNom == null || documentSolr == null) {
			b.append(nomSimple);
		}
		else {
			b.append((String)documentSolr.get("classeNomSimple_" + langueNom + "_stored_string"));
		}
		if(valeurGenerique != null) {
			b.append("<");
			for(int i = 0; i < classeParts.size(); i++) {
				ClasseParts classePart = classeParts.get(i);
				if(i > 0)
					b.append(", ");
				b.append(classePart.nomSimpleComplet(langueNom));
			}
			b.append(">");
		}

		return b.toString();
	}

	/**
	 */
	public String nomCanoniqueGenerique(String langueNom) {
		StringBuilder b = new StringBuilder();

		if(valeurGenerique != null) {
			for(int i = 0; i < classeParts.size(); i++) {
				ClasseParts classePart = classeParts.get(i);
				if(i > 0)
					b.append(", ");
				b.append(classePart.nomCanoniqueComplet(langueNom));
			}
		}

		return b.toString();
	}

	/**
	 */
	public String nomSimpleGenerique(String langueNom) {
		StringBuilder b = new StringBuilder();

		if(valeurGenerique != null) {
			for(int i = 0; i < classeParts.size(); i++) {
				ClasseParts classePart = classeParts.get(i);
				if(i > 0)
					b.append(", ");
				b.append(classePart.nomSimpleComplet(langueNom));
			}
		}

		return b.toString();
	}

	/**
	 */
	private String nomCanoniqueGenerique;

	/**
	 */
	private String nomSimpleComplet;

	/**
	 */
	private String nomSimpleGenerique;

	/**
	 */
	private String nomSimpleSuperGenerique;

	/**
	 */
	private String nomCanoniqueSuperGenerique;

	/**
	 */
	private Boolean etendGen = false;

	/**
	 */
	private Boolean etendBase = false;

	/**
	 */
	private List<ClasseParts> classeParts = new ArrayList<>();
	/**
	 */
	private SolrDocument documentSolr;

	/**
	 */
	private String langueNom;

	/**
	 */
	public static SolrDocument documentSolr(ConfigSite configSite, String nomSimpleOuCanonique, String langueNom) throws Exception {
		SolrDocument doc = null;   
		Boolean contientPoint = nomSimpleOuCanonique.contains(".");
		if(!contientPoint || StringUtils.startsWith(nomSimpleOuCanonique, configSite.nomEnsembleDomaine) || StringUtils.startsWith(nomSimpleOuCanonique, NOM_ENSEMBLE_DOMAINE_COMPUTATE)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);

			if(contientPoint)
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimpleOuCanonique));
			else
				rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimpleOuCanonique));

			rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
			if(!contientPoint)
				rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(configSite.nomEnsembleDomaine));
//			rechercheSolr.addFilterQuery("langueNom_indexed_string:" + ClientUtils.escapeQueryChars(langueNom));
			QueryResponse reponseRecherche = configSite.clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) { 
				doc = listeRecherche.get(0);
			}
		}
		return doc;
	}

	/**
	 */
	public static ClasseParts initClasseParts(ConfigSite configSite, ClasseParts classeParts, String langueNom) throws Exception {
		ClasseParts o = initClasseParts(configSite, classeParts.nomCanoniqueComplet, langueNom);
		return o;
	} 
//
//	/**
//	 */
//	public static ClasseParts initClasseParts(ConfigSite configSite, JavaClass classeQdox, String langueNom) throws Exception {
//		return initClasseParts(configSite, classeQdox, langueNom, null);
//	}

	/**
	 */ 
//	public static ClasseParts initClasseParts(ConfigSite configSite, JavaClass classeQdox, String langueNom, String classeLangueNom) throws Exception {
	public static ClasseParts initClasseParts(ConfigSite configSite, JavaClass classeQdox, String langueNom) throws Exception {
		ClasseParts classeParts = new ClasseParts();
		String nomCanonique = StringUtils.replace(classeQdox.getCanonicalName(), "$", ".");
//		String nomCanoniqueComplet = StringUtils.replace(classeQdox.getGenericFullyQualifiedName(), "$", ".");
//		String valeurGeneriqueSimpleAvant = StringUtils.replace(classeQdox.getGenericValue(), "$", ".");
		String valeurGeneriqueCanoniqueAvant = StringUtils.replace(classeQdox.getGenericCanonicalName(), "$", ".");
		classeParts.initClasseParts2(configSite, valeurGeneriqueCanoniqueAvant, langueNom);
//		if(StringUtils.isBlank(valeurGeneriqueCanoniqueAvant)) {
//			classeParts.initClasseParts2(configSite, nomCanonique, langueNom);
//		}
//		else {
//			classeParts.initClasseParts2(configSite, nomCanonique + "<" + valeurGeneriqueCanoniqueAvant + ">", langueNom);
//		}
//		if(StringUtils.contains(valeurGeneriqueCanoniqueAvant, "<")) {
//			String valeurGeneriqueSimple = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueSimpleAvant, ">"), "<");
//			String valeurGeneriqueCanonique = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueCanoniqueAvant, ">"), "<");
//			String[] partsSimple = StringUtils.split(valeurGeneriqueSimple, ",");
//			String[] partsCanonique = StringUtils.split(valeurGeneriqueCanonique, ",");
//			String nomCanoniqueGenerique = "";
//			for(int i = 0; i < partsSimple.length; i++) {
//				String nomSimpleCompletPart = StringUtils.trim(partsSimple[i]);
//				String nomSimplePart = StringUtils.trim(partsSimple[i]);
//				if(StringUtils.contains(nomSimplePart, "<"))
//					nomSimplePart = StringUtils.substringBefore(nomSimplePart, "<");
//				String nomCanoniqueCompletPart = StringUtils.trim(partsCanonique[i]);
//				String nomCanoniquePart = StringUtils.trim(partsCanonique[i]);
//				if(StringUtils.contains(nomCanoniquePart, "<"))
//					nomCanoniquePart = StringUtils.substringBefore(nomCanoniquePart, "<");
//
//				if(i > 0) {
//					nomCanoniqueGenerique += ", ";
//				}
//				SolrQuery rechercheSolr = new SolrQuery();   
//				rechercheSolr.setQuery("*:*");
//				rechercheSolr.setRows(1);
////				rechercheSolr.addFilterQuery("classeNomSimple_" + (classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom) + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimplePart));
//				rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimplePart));
//				rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
//				rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(configSite.nomEnsembleDomaine));
//				QueryResponse reponseRecherche = configSite.clientSolrComputate.query(rechercheSolr);
//				SolrDocumentList listeRecherche = reponseRecherche.getResults();
//				if(listeRecherche.size() > 0) { 
//					SolrDocument doc = listeRecherche.get(0);
//					String nomSimpleGeneriquePart = (String)doc.get("classeNomSimple_" + langueNom + "_stored_string");
//					String nomCanoniqueGeneriquePart = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
//					if(nomSimpleGeneriquePart != null && nomCanoniqueGeneriquePart != null) {
//						nomCanoniqueGenerique += nomCanoniqueGeneriquePart;
//					}
//					else {
//						nomCanoniqueGenerique += nomCanoniqueCompletPart;
//					}
//					if(nomSimpleCompletPart.contains("<")) {
//						String nomSimplePart2 = StringUtils.substringAfter(StringUtils.substringBeforeLast(nomSimpleCompletPart, ">"), "<");
//						SolrQuery rechercheSolr2 = new SolrQuery();   
//						rechercheSolr2.setQuery("*:*");
//						rechercheSolr2.setRows(1);
////						rechercheSolr2.addFilterQuery("classeNomSimple_" + (classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom) + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimplePart2));
//						rechercheSolr2.addFilterQuery("classeNomSimple_" + langueNom);
//						rechercheSolr2.addFilterQuery("partEstClasse_indexed_boolean:true");
//						rechercheSolr2.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(configSite.nomEnsembleDomaine));
//						QueryResponse reponseRecherche2 = configSite.clientSolrComputate.query(rechercheSolr2);
//						SolrDocumentList listeRecherche2 = reponseRecherche2.getResults();
//						if(listeRecherche2.size() > 0) { 
//							SolrDocument doc2 = listeRecherche2.get(0);
//							String nomSimpleGeneriquePart2 = (String)doc2.get("classeNomSimple_" + langueNom + "_stored_string");
//							String nomCanoniqueGeneriquePart2 = (String)doc2.get("classeNomCanonique_" + langueNom + "_stored_string");
//							if(nomSimpleGeneriquePart2 != null && nomCanoniqueGeneriquePart2 != null) {
//								nomCanoniqueGenerique += "<" + nomCanoniqueGeneriquePart2 + ">";
//							}
//						}
//					}
//				}
//				else if(StringUtils.equals(nomSimplePart, "List")) {
//					nomCanoniqueGenerique = "java.util.List<" + StringUtils.substringAfter(nomCanoniqueCompletPart, "<");
//				}
//				else if(StringUtils.equals(nomSimplePart, "ArrayList")) {
//					nomCanoniqueGenerique = "java.util.ArrayList<" + StringUtils.substringAfter(nomCanoniqueCompletPart, "<");
//				}
//				else {
//					nomCanoniqueGenerique += nomCanoniqueCompletPart;
//				}
//			}
//			nomCanoniqueComplet = nomCanonique + "<" + nomCanoniqueGenerique + ">";
//		}
////		ClasseParts classeParts = initClasseParts(configSite, nomCanoniqueComplet, langueNom, classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom);
//		ClasseParts classeParts = initClasseParts(configSite, nomCanoniqueComplet, langueNom);
		return classeParts;
	} 

	public void initClasseParts2(ConfigSite configSite, String nomSimpleOuCanoniqueComplet, String langueNom) throws Exception {
		nomCanonique = nomSimpleOuCanoniqueComplet;
		nomCanoniqueGenerique = null;
		valeurGenerique = null;
		etendGen = false;

		if(StringUtils.contains(nomSimpleOuCanoniqueComplet, "<")) {
			nomCanonique = StringUtils.substringBefore(nomSimpleOuCanoniqueComplet, "<");
			valeurGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(nomSimpleOuCanoniqueComplet, ">"), "<");
		}

		documentSolr = documentSolr(configSite, nomCanonique, langueNom);

		if(nomSimpleOuCanoniqueComplet != null && langueNom != null && (nomSimpleOuCanoniqueComplet.contains(langueNom) || documentSolr != null))
			this.langueNom = langueNom;

		if(documentSolr != null) {
//			nomCanonique = (String)documentSolr1.get("classeNomCanonique_" + langueNom + "_stored_string");
			nomSimple = (String)documentSolr.get("classeNomSimple_" + langueNom + "_stored_string");
			etendGen = (Boolean)documentSolr.get("classeEtendGen_stored_boolean");
			etendBase = (Boolean)documentSolr.get("classeEtendBase_stored_boolean");
			setNomSimpleSuperGenerique((String)documentSolr.get("classeNomSimpleSuperGenerique_" + langueNom + "_stored_string"));
			setNomCanoniqueSuperGenerique((String)documentSolr.get("classeNomCanoniqueSuperGenerique_" + langueNom + "_stored_string"));
		}
		if(nomSimple == null) {
			nomSimple = StringUtils.substringAfterLast(nomCanonique, ".");
			if(StringUtils.isEmpty(nomSimple))
				nomSimple = nomCanonique;
		}
		nomCanoniqueComplet = nomCanonique;
		nomSimpleComplet = nomSimple;

		if(valeurGenerique != null) {
			Integer level = 0;
			StringBuilder b = new StringBuilder();
			String part;
			List<String> partsGenerique = new ArrayList<>();
			for(char c : valeurGenerique.toCharArray()) {
				if(c == ' ')
					continue;
				if(c == '<')
					level++;
				if(level == 0 && c == ',') {
					part = b.toString();
					if(StringUtils.isNotBlank(part))
						partsGenerique.add(part);
					b = new StringBuilder();
				}
				else {
					b.append(c);
				}
				if(c == '>')
					level--;
			}
			part = b.toString();
			if(StringUtils.isNotBlank(part))
				partsGenerique.add(part);

			nomCanoniqueGenerique = "";
			nomSimpleGenerique = "";
			for(int i = 0; i < partsGenerique.size(); i++) {
				String nomCanoniqueGeneriquePart = StringUtils.trim(partsGenerique.get(i));

				ClasseParts partClasseParts = ClasseParts.initClasseParts(configSite, nomCanoniqueGeneriquePart, langueNom);
				if(i > 0) {
					nomSimpleGenerique += ", ";
					nomCanoniqueGenerique += ", ";
				}
				classeParts.add(partClasseParts);
				nomSimpleGenerique += partClasseParts.nomSimpleComplet(langueNom);
				nomCanoniqueGenerique += partClasseParts.nomCanoniqueComplet(langueNom);
			}
			nomSimpleComplet = nomSimple + "<" + nomSimpleGenerique + ">";
			nomCanoniqueComplet = nomCanonique + "<" + nomCanoniqueGenerique + ">";
		}
//
//		if(StringUtils.contains(valeurGeneriqueCanoniqueAvant, "<")) {
//			String valeurGeneriqueSimple = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueSimpleAvant, ">"), "<");
//			String valeurGeneriqueCanonique = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueCanoniqueAvant, ">"), "<");
//			String[] partsSimple = StringUtils.split(valeurGeneriqueSimple, ",");
//			String[] partsCanonique = StringUtils.split(valeurGeneriqueCanonique, ",");
//			String nomCanoniqueGenerique = "";
//			for(int i = 0; i < partsSimple.length; i++) {
//				String nomSimpleCompletPart = StringUtils.trim(partsSimple[i]);
//				String nomSimplePart = StringUtils.trim(partsSimple[i]);
//				if(StringUtils.contains(nomSimplePart, "<"))
//					nomSimplePart = StringUtils.substringBefore(nomSimplePart, "<");
//				String nomCanoniqueCompletPart = StringUtils.trim(partsCanonique[i]);
//				String nomCanoniquePart = StringUtils.trim(partsCanonique[i]);
//				if(StringUtils.contains(nomCanoniquePart, "<"))
//					nomCanoniquePart = StringUtils.substringBefore(nomCanoniquePart, "<");
//
//				if(i > 0) {
//					nomCanoniqueGenerique += ", ";
//				}
//				SolrQuery rechercheSolr = new SolrQuery();   
//				rechercheSolr.setQuery("*:*");
//				rechercheSolr.setRows(1);
////				rechercheSolr.addFilterQuery("classeNomSimple_" + (classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom) + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimplePart));
//				rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimplePart));
//				rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
//				rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(configSite.nomEnsembleDomaine));
//				QueryResponse reponseRecherche = configSite.clientSolrComputate.query(rechercheSolr);
//				SolrDocumentList listeRecherche = reponseRecherche.getResults();
//				if(listeRecherche.size() > 0) { 
//					SolrDocument doc = listeRecherche.get(0);
//					String nomSimpleGeneriquePart = (String)doc.get("classeNomSimple_" + langueNom + "_stored_string");
//					String nomCanoniqueGeneriquePart = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
//					if(nomSimpleGeneriquePart != null && nomCanoniqueGeneriquePart != null) {
//						nomCanoniqueGenerique += nomCanoniqueGeneriquePart;
//					}
//					else {
//						nomCanoniqueGenerique += nomCanoniqueCompletPart;
//					}
//					if(nomSimpleCompletPart.contains("<")) {
//						String nomSimplePart2 = StringUtils.substringAfter(StringUtils.substringBeforeLast(nomSimpleCompletPart, ">"), "<");
//						SolrQuery rechercheSolr2 = new SolrQuery();   
//						rechercheSolr2.setQuery("*:*");
//						rechercheSolr2.setRows(1);
////						rechercheSolr2.addFilterQuery("classeNomSimple_" + (classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom) + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimplePart2));
//						rechercheSolr2.addFilterQuery("classeNomSimple_" + langueNom);
//						rechercheSolr2.addFilterQuery("partEstClasse_indexed_boolean:true");
//						rechercheSolr2.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(configSite.nomEnsembleDomaine));
//						QueryResponse reponseRecherche2 = configSite.clientSolrComputate.query(rechercheSolr2);
//						SolrDocumentList listeRecherche2 = reponseRecherche2.getResults();
//						if(listeRecherche2.size() > 0) { 
//							SolrDocument doc2 = listeRecherche2.get(0);
//							String nomSimpleGeneriquePart2 = (String)doc2.get("classeNomSimple_" + langueNom + "_stored_string");
//							String nomCanoniqueGeneriquePart2 = (String)doc2.get("classeNomCanonique_" + langueNom + "_stored_string");
//							if(nomSimpleGeneriquePart2 != null && nomCanoniqueGeneriquePart2 != null) {
//								nomCanoniqueGenerique += "<" + nomCanoniqueGeneriquePart2 + ">";
//							}
//						}
//					}
//				}
//				else if(StringUtils.equals(nomSimplePart, "List")) {
//					nomCanoniqueGenerique = "java.util.List<" + StringUtils.substringAfter(nomCanoniqueCompletPart, "<");
//				}
//				else if(StringUtils.equals(nomSimplePart, "ArrayList")) {
//					nomCanoniqueGenerique = "java.util.ArrayList<" + StringUtils.substringAfter(nomCanoniqueCompletPart, "<");
//				}
//				else {
//					nomCanoniqueGenerique += nomCanoniqueCompletPart;
//				}
//			}
//			nomCanoniqueComplet = nomCanonique + "<" + nomCanoniqueGenerique + ">";
//		}
////		ClasseParts classeParts = initClasseParts(configSite, nomCanoniqueComplet, langueNom, classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom);
//		ClasseParts classeParts = initClasseParts(configSite, nomCanoniqueComplet, langueNom);
//		return classeParts;
	} 
//
//	/**
//	 **/
//	public static ClasseParts initClasseParts(ConfigSite configSite, String nomCanoniqueComplet, String langueNom) throws Exception {
//		return initClasseParts(configSite, nomCanoniqueComplet, langueNom, null);
//	}

	/**
	 */
	public static ClasseParts initClasseParts(ConfigSite configSite, String nomCanoniqueComplet, String langueNom) throws Exception {
		ClasseParts classeParts = new ClasseParts();
		classeParts.initClasseParts2(configSite, nomCanoniqueComplet, langueNom);
//		classeParts.nomCanonique = nomCanoniqueComplet;
//		classeParts.nomCanoniqueGenerique = null;
//		String valeurGenerique = null;
//
//		if(StringUtils.contains(nomCanoniqueComplet, "<")) {
//			classeParts.nomCanonique = StringUtils.substringBefore(nomCanoniqueComplet, "<");
//			valeurGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueComplet, ">"), "<");
//		}
////		classeParts.documentSolr = documentSolr(configSite, classeParts.nomCanonique, classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom);
//		if(classeParts.getDocumentSolr() != null)
//			classeParts.documentSolr = documentSolr(configSite, classeParts.nomCanonique, langueNom);
//
//		if(nomCanoniqueComplet != null && langueNom != null && (nomCanoniqueComplet.contains(langueNom) || classeParts.documentSolr != null))
//			classeParts.langueNom = langueNom;
//
//		String nomCanonique = null;
//		String nomSimple = null;
//		if(classeParts.documentSolr != null) {
//			nomCanonique = (String)classeParts.documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string");
//			nomSimple = (String)classeParts.documentSolr.get("classeNomSimple_" + langueNom + "_stored_string");
//			classeParts.etendGen = (Boolean)classeParts.documentSolr.get("classeEtendGen_stored_boolean");
//		}
//		if(nomCanonique != null && nomSimple != null) {
//			classeParts.nomCanonique = nomCanonique;
//			classeParts.nomSimple = nomSimple;
//		}
//		else {
//			classeParts.nomSimple = StringUtils.substringAfterLast(classeParts.nomCanonique, ".");
//			if(StringUtils.isEmpty(classeParts.nomSimple))
//			classeParts.nomSimple = classeParts.nomCanonique;
//		}
//		classeParts.nomCanoniqueComplet = classeParts.nomCanonique;
//		classeParts.nomSimpleComplet = classeParts.nomSimple;
//
//		if(valeurGenerique != null) {
//			String[] partsGenerique = StringUtils.split(valeurGenerique, ",");
//			classeParts.nomCanoniqueGenerique = "";
//			classeParts.nomSimpleGenerique = "";
//			for(int i = 0; i < partsGenerique.length; i++) {
//				String nomCanoniqueGeneriquePart = StringUtils.trim(partsGenerique[i]);
//
//				ClasseParts partClasseParts = ClasseParts.initClasseParts(configSite, nomCanoniqueGeneriquePart, langueNom);
//				if(i > 0) {
//					classeParts.nomSimpleGenerique += ", ";
//					classeParts.nomCanoniqueGenerique += ", ";
//				}
//
//				classeParts.nomSimpleGenerique += partClasseParts.nomSimpleComplet;
//				classeParts.nomCanoniqueGenerique += partClasseParts.nomCanoniqueComplet;
//			}
//			classeParts.nomSimpleComplet = classeParts.nomSimple + "<" + classeParts.nomSimpleGenerique + ">";
//			classeParts.nomCanoniqueComplet = classeParts.nomCanonique + "<" + classeParts.nomCanoniqueGenerique + ">";
//		}
		return classeParts;
	}

	/**
	 */
	public static String concat(String...valeurs) throws Exception { 
		String o = Stream.of(valeurs).collect(Collectors.joining());
		return o;
	}  

	public String getValeurGenerique() {
		return valeurGenerique;
	}
	public void setValeurGenerique(String valeurGenerique) {
		this.valeurGenerique = valeurGenerique;
	}

	public Boolean getEtendBase() {
		return etendBase;
	}

	public void setEtendBase(Boolean etendBase) {
		this.etendBase = etendBase;
	}

	public Boolean getEtendGen() {
		return etendGen;
	}

	public void setEtendGen(Boolean etendGen) {
		this.etendGen = etendGen;
	}

	public SolrDocument getDocumentSolr() {
		return documentSolr;
	}

	public void setDocumentSolr(SolrDocument documentSolr) {
		this.documentSolr = documentSolr;
	}

	public String getLangueNom() {
		return langueNom;
	}

	public void setLangueNom(String langueNom) {
		this.langueNom = langueNom;
	}

	public String getNomCanoniqueSuperGenerique() {
		return nomCanoniqueSuperGenerique;
	}
	public void setNomCanoniqueSuperGenerique(String nomCanoniqueSuperGenerique) {
		this.nomCanoniqueSuperGenerique = nomCanoniqueSuperGenerique;
	}

	public String getNomSimpleSuperGenerique() {
		return nomSimpleSuperGenerique;
	}
	public void setNomSimpleSuperGenerique(String nomSimpleSuperGenerique) {
		this.nomSimpleSuperGenerique = nomSimpleSuperGenerique;
	}
	/**
	 */
	@Override public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("nomCanoniqueComplet: ").append(nomCanoniqueComplet).append("\n");
		b.append("nomCanonique: ").append(nomCanonique).append("\n");
		b.append("nomSimple: ").append(nomSimple).append("\n");
		b.append("nomCanoniqueGenerique: ").append(nomCanoniqueGenerique).append("\n");
		b.append("nomSimpleComplet: ").append(nomSimpleComplet).append("\n");
		b.append("nomSimpleGenerique: ").append(nomSimpleGenerique).append("\n");
		b.append("etendGen: ").append(etendGen).append("\n");
		b.append("langueNom: ").append(langueNom).append("\n");
		return b.toString();
	}
}
