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
 * nomCanonique.enUS: org.computate.enUS.java.ClassParts
 */ 
public class ClasseParts {

	/**
	 * var.enUS: canonicalNameComplete
	 */  
	public String nomCanoniqueComplet;

	/**
	 * var.enUS: canonicalName
	 */
	public String nomCanonique;

	/**
	 * var.enUS: simpleName
	 */
	public String nomSimple;

	/**
	 * var.enUS: canonicalNameGeneric
	 */
	public String nomCanoniqueGenerique;

	/**
	 * var.enUS: simpleNameComplete
	 */
	public String nomSimpleComplet;

	/**
	 * var.enUS: simpleNameGeneric
	 */
	public String nomSimpleGenerique;

	/**
	 * var.enUS: extendsGen
	 */
	public Boolean etendGen;

	/**
	 * var.enUS: solrDocument
	 */
	public SolrDocument documentSolr;

	/**
	 * var.enUS: solrDocument
	 * param1.var.enUS: siteConfig
	 * param2.var.enUS: canonicalName
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
	public static SolrDocument documentSolr(ConfigSite configSite, String nomCanonique) throws Exception {
		SolrDocument doc = null;   
		if(StringUtils.startsWith(nomCanonique, configSite.nomEnsembleDomaine)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + configSite.langueNomActuel + "_indexed_string:" + ClientUtils.escapeQueryChars(nomCanonique));
			rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
			QueryResponse reponseRecherche = configSite.clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) { 
				doc = listeRecherche.get(0);
			}
		}
		return doc;
	}

	/**
	 * var.enUS: initClassParts
	 * param1.var.enUS: siteConfig
	 * param2.var.enUS: classParts
	 * param3.var.enUS: languageName
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
	 * var.enUS: initClassParts
	 * param1.var.enUS: siteConfig
	 * param2.var.enUS: classQdox
	 * param3.var.enUS: languageName
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
	 */
	public static ClasseParts initClasseParts(ConfigSite configSite, JavaClass classeQdox, String langueNom) throws Exception {
		String nomCanonique = classeQdox.getCanonicalName();
		String nomCanoniqueComplet = classeQdox.getGenericFullyQualifiedName();
		String valeurGeneriqueSimpleAvant = classeQdox.getGenericValue();
		String valeurGeneriqueCanoniqueAvant = classeQdox.getGenericCanonicalName();
		if(StringUtils.contains(valeurGeneriqueCanoniqueAvant, "<")) {
			String valeurGeneriqueSimple = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueSimpleAvant, ">"), "<");
			String valeurGeneriqueCanonique = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueCanoniqueAvant, ">"), "<");
			String[] partsSimple = StringUtils.split(valeurGeneriqueSimple, ",");
			String[] partsCanonique = StringUtils.split(valeurGeneriqueCanonique, ",");
			String nomCanoniqueGenerique = "";
			for(int i = 0; i < partsSimple.length; i++) {
				String nomSimplePart = StringUtils.trim(partsSimple[i]);
				String nomCanoniquePart = StringUtils.trim(partsCanonique[i]);

				if(i > 0) {
					nomCanoniqueGenerique += ", ";
				}
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1);
				rechercheSolr.addFilterQuery("classeNomSimple_" + configSite.langueNomActuel + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimplePart));
				rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
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
						nomCanoniqueGenerique += nomCanoniquePart;
					}
				}
				else {
					nomCanoniqueGenerique += nomCanoniquePart;
				}
			}
			nomCanoniqueComplet = nomCanonique + "<" + nomCanoniqueGenerique + ">";
		}
		ClasseParts classeParts = initClasseParts(configSite, nomCanoniqueComplet, langueNom);
		return classeParts;
	} 

	/**
	 * var.enUS: initClassParts
	 * param1.var.enUS: siteConfig
	 * param2.var.enUS: canonicalNameComplete
	 * param3.var.enUS: languageName
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
	 */
	public static ClasseParts initClasseParts(ConfigSite configSite, String nomCanoniqueComplet, String langueNom) throws Exception {
		ClasseParts classeParts = new ClasseParts();
		classeParts.nomCanonique = nomCanoniqueComplet;
		classeParts.nomCanoniqueGenerique = null;
		String valeurGenerique = null;

		if(StringUtils.contains(nomCanoniqueComplet, "<")) {
			classeParts.nomCanonique = StringUtils.substringBefore(nomCanoniqueComplet, "<");
			valeurGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueComplet, ">"), "<");
		}
		classeParts.documentSolr = documentSolr(configSite, classeParts.nomCanonique);

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
	 * param1.var.enUS: values
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
