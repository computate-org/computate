package org.computate.frFR.java; 

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

	/**
	 * Var.enUS: canonicalNameComplete
	 */  
	public String nomCanoniqueComplet;

	/**
	 * Var.enUS: canonicalName
	 */
	public String nomCanonique;
	/**
	 * Var.enUS: canonicalName
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 */
	public String nomCanonique(String langueNom) {
		if(langueNom == null || documentSolr == null)
			return nomCanonique;
		else
			return (String)documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string");
	}

	/**
	 * Var.enUS: simpleName
	 */
	public String nomSimple;
	/**
	 * Var.enUS: simpleName
	 * r: nomSimple
	 * r.enUS: simpleName
	 */
	public String nomSimple(String langueNom) {
		if(langueNom == null || documentSolr == null)
			return nomSimple;
		else
			return (String)documentSolr.get("classeNomSimple_" + langueNom + "_stored_string");
	}

	/**
	 * Var.enUS: canonicalNameGeneric
	 */
	public String nomCanoniqueGenerique;

	/**
	 * Var.enUS: simpleNameComplete
	 */
	public String nomSimpleComplet;

	/**
	 * Var.enUS: simpleNameGeneric
	 */
	public String nomSimpleGenerique;

	/**
	 * Var.enUS: extendsGen
	 */
	public Boolean etendGen;

	/**
	 * Var.enUS: solrDocument
	 */
	public SolrDocument documentSolr;

	/**
	 * Var.enUS: languageName
	 */
	public String langueNom;

	/**
	 * Var.enUS: solrDocument
	 * Param1.var.enUS: siteConfig
	 * Param2.var.enUS: canonicalName
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: listeRecherche
	 * r.enUS: searchList
	 */
	public static SolrDocument documentSolr(ConfigSite configSite, String nomCanonique, String langueNom) throws Exception {
		SolrDocument doc = null;   
		if(StringUtils.startsWith(nomCanonique, configSite.nomEnsembleDomaine)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(nomCanonique));
			rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
			rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(configSite.nomEnsembleDomaine));
			QueryResponse reponseRecherche = configSite.clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) { 
				doc = listeRecherche.get(0);
			}
		}
		return doc;
	}

	/**
	 * Var.enUS: initClassParts
	 * Param1.var.enUS: siteConfig
	 * Param2.var.enUS: classParts
	 * Param3.var.enUS: languageName
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: initClasseParts
	 * r.enUS: initClassParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: nomCanoniqueComplet
	 * r.enUS: canonicalNameComplete
	 * r: langueNom
	 * r.enUS: languageName
	 */
	public static ClasseParts initClasseParts(ConfigSite configSite, ClasseParts classeParts, String langueNom) throws Exception {
		ClasseParts o = initClasseParts(configSite, classeParts.nomCanoniqueComplet, langueNom);
		return o;
	} 

	/**
	 * Var.enUS: initClassParts
	 * Param1.var.enUS: siteConfig
	 * Param2.var.enUS: classQdox
	 * Param3.var.enUS: languageName
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: initClasseParts
	 * r.enUS: initClassParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 */
	public static ClasseParts initClasseParts(ConfigSite configSite, JavaClass classeQdox, String langueNom) throws Exception {
		return initClasseParts(configSite, classeQdox, langueNom, null);
	}

	/**
	 * Var.enUS: initClassParts
	 * Param1.var.enUS: siteConfig
	 * Param2.var.enUS: classQdox
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: classeLanguageName
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: initClasseParts
	 * r.enUS: initClassParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: nomCanoniqueGeneriquePart
	 * r.enUS: canonicalNameGenericPart
	 * r: nomCanoniqueGenerique
	 * r.enUS: canonicalNameGeneric
	 * r: nomCanoniqueComplet
	 * r.enUS: canonicalNameComplete
	 * r: nomCanoniquePart
	 * r.enUS: canonicalNamePart
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: valeurGeneriqueSimpleAvant
	 * r.enUS: genericSimpleValueBefore
	 * r: valeurGeneriqueCanoniqueAvant
	 * r.enUS: genericCanonicalValueBefore
	 * r: valeurGeneriqueSimple
	 * r.enUS: genericSimpleValue
	 * r: valeurGeneriqueCanonique
	 * r.enUS: genericCanonicalValue
	 * r: partsSimple
	 * r.enUS: simpleParts
	 * r: partsCanonique
	 * r.enUS: canonicalParts
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: nomSimplePart
	 * r.enUS: simpleNamePart
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: nomSimpleGeneriquePart
	 * r.enUS: simpleNameGenericPart
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: nomSimpleCompletPart
	 * r.enUS: simpleNameCompletePart
	 * r: etendGen
	 * r.enUS: extendsGen
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 */ 
	public static ClasseParts initClasseParts(ConfigSite configSite, JavaClass classeQdox, String langueNom, String classeLangueNom) throws Exception {
		String nomCanonique = StringUtils.replace(classeQdox.getCanonicalName(), "$", ".");
		String nomCanoniqueComplet = StringUtils.replace(classeQdox.getGenericFullyQualifiedName(), "$", ".");
		String valeurGeneriqueSimpleAvant = StringUtils.replace(classeQdox.getGenericValue(), "$", ".");
		String valeurGeneriqueCanoniqueAvant = StringUtils.replace(classeQdox.getGenericCanonicalName(), "$", ".");
		if(StringUtils.contains(valeurGeneriqueCanoniqueAvant, "<")) {
			String valeurGeneriqueSimple = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueSimpleAvant, ">"), "<");
			String valeurGeneriqueCanonique = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueCanoniqueAvant, ">"), "<");
			String[] partsSimple = StringUtils.split(valeurGeneriqueSimple, ",");
			String[] partsCanonique = StringUtils.split(valeurGeneriqueCanonique, ",");
			String nomCanoniqueGenerique = "";
			for(int i = 0; i < partsSimple.length; i++) {
				String nomSimpleCompletPart = StringUtils.trim(partsSimple[i]);
				String nomSimplePart = StringUtils.trim(partsSimple[i]);
				if(StringUtils.contains(nomSimplePart, "<"))
					nomSimplePart = StringUtils.substringBefore(nomSimplePart, "<");
				String nomCanoniqueCompletPart = StringUtils.trim(partsCanonique[i]);
				String nomCanoniquePart = StringUtils.trim(partsCanonique[i]);
				if(StringUtils.contains(nomCanoniquePart, "<"))
					nomCanoniquePart = StringUtils.substringBefore(nomCanoniquePart, "<");

				if(i > 0) {
					nomCanoniqueGenerique += ", ";
				}
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1);
				rechercheSolr.addFilterQuery("classeNomSimple_" + (classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom) + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimplePart));
				rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
				rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(configSite.nomEnsembleDomaine));
				QueryResponse reponseRecherche = configSite.clientSolrComputate.query(rechercheSolr);
				SolrDocumentList listeRecherche = reponseRecherche.getResults();
				if(listeRecherche.size() > 0) { 
					SolrDocument doc = listeRecherche.get(0);
					String nomSimpleGeneriquePart = (String)doc.get("classeNomSimple_" + langueNom + "_stored_string");
					String nomCanoniqueGeneriquePart = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
					if(nomSimpleGeneriquePart != null && nomCanoniqueGeneriquePart != null) {
						nomCanoniqueGenerique += nomCanoniqueGeneriquePart;
					}
					else {
						nomCanoniqueGenerique += nomCanoniqueCompletPart;
					}
					if(nomSimpleCompletPart.contains("<")) {
						String nomSimplePart2 = StringUtils.substringAfter(StringUtils.substringBeforeLast(nomSimpleCompletPart, ">"), "<");
						SolrQuery rechercheSolr2 = new SolrQuery();   
						rechercheSolr2.setQuery("*:*");
						rechercheSolr2.setRows(1);
						rechercheSolr2.addFilterQuery("classeNomSimple_" + (classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom) + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimplePart2));
						rechercheSolr2.addFilterQuery("partEstClasse_indexed_boolean:true");
						rechercheSolr2.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(configSite.nomEnsembleDomaine));
						QueryResponse reponseRecherche2 = configSite.clientSolrComputate.query(rechercheSolr2);
						SolrDocumentList listeRecherche2 = reponseRecherche2.getResults();
						if(listeRecherche2.size() > 0) { 
							SolrDocument doc2 = listeRecherche2.get(0);
							String nomSimpleGeneriquePart2 = (String)doc2.get("classeNomSimple_" + langueNom + "_stored_string");
							String nomCanoniqueGeneriquePart2 = (String)doc2.get("classeNomCanonique_" + langueNom + "_stored_string");
							if(nomSimpleGeneriquePart2 != null && nomCanoniqueGeneriquePart2 != null) {
								nomCanoniqueGenerique += "<" + nomCanoniqueGeneriquePart2 + ">";
							}
						}
					}
				}
				else if(StringUtils.equals(nomSimplePart, "List")) {
					nomCanoniqueGenerique = "java.util.List<" + StringUtils.substringAfter(nomCanoniqueCompletPart, "<");
				}
				else if(StringUtils.equals(nomSimplePart, "ArrayList")) {
					nomCanoniqueGenerique = "java.util.ArrayList<" + StringUtils.substringAfter(nomCanoniqueCompletPart, "<");
				}
				else {
					nomCanoniqueGenerique += nomCanoniqueCompletPart;
				}
			}
			nomCanoniqueComplet = nomCanonique + "<" + nomCanoniqueGenerique + ">";
		}
		ClasseParts classeParts = initClasseParts(configSite, nomCanoniqueComplet, langueNom, classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom);
		return classeParts;
	} 

	/**
	 * Var.enUS: initClassParts
	 * Param1.var.enUS: siteConfig
	 * Param2.var.enUS: canonicalNameComplete
	 * Param3.var.enUS: languageName
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: initClasseParts
	 * r.enUS: initClassParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 **/
	public static ClasseParts initClasseParts(ConfigSite configSite, String nomCanoniqueComplet, String langueNom) throws Exception {
		return initClasseParts(configSite, nomCanoniqueComplet, langueNom, null);
	}

	/**
	 * Var.enUS: initClassParts
	 * Param1.var.enUS: siteConfig
	 * Param2.var.enUS: canonicalNameComplete
	 * Param3.var.enUS: languageName
	 * Param3.var.enUS: classeLanguageName
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: initClasseParts
	 * r.enUS: initClassParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: nomCanoniqueGeneriquePart
	 * r.enUS: canonicalNameGenericPart
	 * r: nomCanoniqueGenerique
	 * r.enUS: canonicalNameGeneric
	 * r: nomCanoniqueComplet
	 * r.enUS: canonicalNameComplete
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: documentSolr
	 * r.enUS: solrDocument
	 * r: valeurGenerique
	 * r.enUS: genericValue
	 * r: nomSimpleGenerique
	 * r.enUS: simpleNameGeneric
	 * r: nomSimpleComplet
	 * r.enUS: simpleNameComplete
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: partsGenerique
	 * r.enUS: genericParts
	 * r: etendGen
	 * r.enUS: extendsGen
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 */
	public static ClasseParts initClasseParts(ConfigSite configSite, String nomCanoniqueComplet, String langueNom, String classeLangueNom) throws Exception {
		ClasseParts classeParts = new ClasseParts();
		classeParts.nomCanonique = nomCanoniqueComplet;
		classeParts.nomCanoniqueGenerique = null;
		String valeurGenerique = null;

		if(StringUtils.contains(nomCanoniqueComplet, "<")) {
			classeParts.nomCanonique = StringUtils.substringBefore(nomCanoniqueComplet, "<");
			valeurGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueComplet, ">"), "<");
		}
		classeParts.documentSolr = documentSolr(configSite, classeParts.nomCanonique, classeLangueNom == null ? configSite.langueNomActuel : classeLangueNom);

		if(nomCanoniqueComplet != null && langueNom != null && (nomCanoniqueComplet.contains(langueNom) || classeParts.documentSolr != null))
			classeParts.langueNom = langueNom;

		String nomCanonique = null;
		String nomSimple = null;
		if(classeParts.documentSolr != null) {
			nomCanonique = (String)classeParts.documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string");
			nomSimple = (String)classeParts.documentSolr.get("classeNomSimple_" + langueNom + "_stored_string");
			classeParts.etendGen = (Boolean)classeParts.documentSolr.get("classeEtendGen_stored_boolean");
		}
		if(nomCanonique != null && nomSimple != null) {
			classeParts.nomCanonique = nomCanonique;
			classeParts.nomSimple = nomSimple;
		}
		else {
			classeParts.nomSimple = StringUtils.substringAfterLast(classeParts.nomCanonique, ".");
			if(StringUtils.isEmpty(classeParts.nomSimple))
			classeParts.nomSimple = classeParts.nomCanonique;
		}
		classeParts.nomCanoniqueComplet = classeParts.nomCanonique;
		classeParts.nomSimpleComplet = classeParts.nomSimple;

		if(valeurGenerique != null) {
			String[] partsGenerique = StringUtils.split(valeurGenerique, ",");
			classeParts.nomCanoniqueGenerique = "";
			classeParts.nomSimpleGenerique = "";
			for(int i = 0; i < partsGenerique.length; i++) {
				String nomCanoniqueGeneriquePart = StringUtils.trim(partsGenerique[i]);

				ClasseParts partClasseParts = ClasseParts.initClasseParts(configSite, nomCanoniqueGeneriquePart, langueNom);
				if(i > 0) {
					classeParts.nomSimpleGenerique += ", ";
					classeParts.nomCanoniqueGenerique += ", ";
				}

				classeParts.nomSimpleGenerique += partClasseParts.nomSimpleComplet;
				classeParts.nomCanoniqueGenerique += partClasseParts.nomCanoniqueComplet;
			}
			classeParts.nomSimpleComplet = classeParts.nomSimple + "<" + classeParts.nomSimpleGenerique + ">";
			classeParts.nomCanoniqueComplet = classeParts.nomCanonique + "<" + classeParts.nomCanoniqueGenerique + ">";
		}
		return classeParts;
	}

	/**
	 * Param1.var.enUS: values
	 * r: valeurs
	 * r.enUS: values
	 */
	public static String concat(String...valeurs) throws Exception { 
		String o = Stream.of(valeurs).collect(Collectors.joining());
		return o;
	}  

	/**
	 * r: nomCanoniqueComplet
	 * r.enUS: canonicalNameComplete
	 * r: nomCanoniqueGenerique
	 * r.enUS: canonicalNameGeneric
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: nomSimpleComplet
	 * r.enUS: simpleNameComplete
	 * r: nomSimpleGenerique
	 * r.enUS: simpleNameGeneric
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: etendGen
	 * r.enUS: extendsGen
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
		return b.toString();
	}
}
