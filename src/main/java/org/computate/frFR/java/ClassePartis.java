package org.computate.frFR.java;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.computate.frFR.config.ConfigSite;

import com.thoughtworks.qdox.model.JavaClass;

/** 
 * classeNomCanonique_enUS: org.computate.enUS.java.ClassParts
 */  
public class ClassePartis {

	public String nomCanoniqueComplet;
	public String nomCanonique;
	public String nomSimple;
	public String nomCanoniqueGenerique;
	public String nomSimpleComplet;
	public String nomSimpleGenerique;
	public SolrDocument documentSolr;

	public static SolrDocument documentSolr(ConfigSite configSite, String nomCanonique) throws Exception {
		SolrDocument doc = null;   
		if(StringUtils.startsWith(nomCanonique, configSite.nomEnsembleDomaine)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + configSite.langueNomActuel + "_indexe_string:" + ClientUtils.escapeQueryChars(nomCanonique));
			rechercheSolr.addFilterQuery("partEstClasse_indexe_boolean:true");
			QueryResponse reponseRecherche = configSite.clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) { 
				doc = listeRecherche.get(0);
			}
		}
		return doc;
	}

	public static ClassePartis initClassePartis(ConfigSite configSite, ClassePartis classePartis, String langueNom) throws Exception {
		ClassePartis resultat = initClassePartis(configSite, classePartis.nomCanoniqueComplet, langueNom);
		return resultat;
	} 

	public static ClassePartis initClassePartis(ConfigSite configSite, JavaClass classeQdox, String langueNom) throws Exception {
		String nomCanonique = classeQdox.getCanonicalName();
		String nomCanoniqueComplet = classeQdox.getGenericFullyQualifiedName();
		String valeurGeneriqueSimpleAvant = classeQdox.getGenericValue();
		String valeurGeneriqueCanoniqueAvant = classeQdox.getGenericCanonicalName();
		if(StringUtils.contains(valeurGeneriqueCanoniqueAvant, "<")) {
			String valeurGeneriqueSimple = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueSimpleAvant, ">"), "<");
			String valeurGeneriqueCanonique = StringUtils.substringAfter(StringUtils.substringBeforeLast(valeurGeneriqueCanoniqueAvant, ">"), "<");
			String[] partisSimple = StringUtils.split(valeurGeneriqueSimple, ",");
			String[] partisCanonique = StringUtils.split(valeurGeneriqueCanonique, ",");
			String nomCanoniqueGenerique = "";
			for(int i = 0; i < partisSimple.length; i++) {
				String nomSimpleParti = StringUtils.trim(partisSimple[i]);
				String nomCanoniqueParti = StringUtils.trim(partisCanonique[i]);

				if(i > 0) {
					nomCanoniqueGenerique += ", ";
				}
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1);
				rechercheSolr.addFilterQuery("classeNomSimple_" + configSite.langueNomActuel + "_indexe_string:" + ClientUtils.escapeQueryChars(nomSimpleParti));
				rechercheSolr.addFilterQuery("partEstClasse_indexe_boolean:true");
				QueryResponse reponseRecherche = configSite.clientSolrComputate.query(rechercheSolr);
				SolrDocumentList listeRecherche = reponseRecherche.getResults();
				if(listeRecherche.size() > 0) { 
					SolrDocument doc = listeRecherche.get(0);
					String nomSimpleGeneriqueParti = (String)doc.get("classeNomSimple_" + langueNom + "_stocke_string");
					String nomCanoniqueGeneriqueParti = (String)doc.get("classeNomCanonique_" + langueNom + "_stocke_string");
					if(nomSimpleGeneriqueParti != null && nomCanoniqueGeneriqueParti != null) {
						nomCanoniqueGenerique += nomCanoniqueGeneriqueParti;
					}
					else {
						nomCanoniqueGenerique += nomCanoniqueParti;
					}
				}
				else {
					nomCanoniqueGenerique += nomCanoniqueParti;
				}
			}
			nomCanoniqueComplet = nomCanonique + "<" + nomCanoniqueGenerique + ">";
		}
		ClassePartis classePartis = initClassePartis(configSite, nomCanoniqueComplet, langueNom);
		return classePartis;
	} 

	public static ClassePartis initClassePartis(ConfigSite configSite, String nomCanoniqueComplet, String langueNom) throws Exception {
		ClassePartis classePartis = new ClassePartis();
		classePartis.nomCanoniqueComplet = nomCanoniqueComplet;
		classePartis.nomCanonique = nomCanoniqueComplet;
		classePartis.nomCanoniqueGenerique = null;
		String valeurGenerique = null;

		if(StringUtils.contains(nomCanoniqueComplet, "<")) {
			classePartis.nomCanonique = StringUtils.substringBefore(nomCanoniqueComplet, "<");
			valeurGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(nomCanoniqueComplet, ">"), "<");
		}
		classePartis.documentSolr = documentSolr(configSite, classePartis.nomCanonique);

		if(classePartis.documentSolr != null) {
			classePartis.nomCanonique = (String)classePartis.documentSolr.get("classeNomCanonique_" + langueNom + "_stocke_string");
			classePartis.nomSimple = (String)classePartis.documentSolr.get("classeNomSimple_" + langueNom + "_stocke_string");
		}
		else {
			classePartis.nomSimple = StringUtils.substringAfterLast(classePartis.nomCanonique, ".");
		}
		classePartis.nomSimpleComplet = classePartis.nomSimple;

		if(valeurGenerique != null) {
			String[] partis = StringUtils.split(valeurGenerique, ",");
			classePartis.nomCanoniqueGenerique = "";
			classePartis.nomSimpleGenerique = "";
			for(int i = 0; i < partis.length; i++) {
				String nomCanoniqueGeneriqueParti = StringUtils.trim(partis[i]);

				ClassePartis partiClassePartis = ClassePartis.initClassePartis(configSite, nomCanoniqueGeneriqueParti, langueNom);
				if(i > 0) {
					classePartis.nomSimpleGenerique += ", ";
					classePartis.nomCanoniqueGenerique += ", ";
				}

				classePartis.nomSimpleGenerique += partiClassePartis.nomSimpleComplet;
				classePartis.nomCanoniqueGenerique += partiClassePartis.nomCanoniqueComplet;
			}
			classePartis.nomSimpleComplet = classePartis.nomSimple + "<" + classePartis.nomSimpleGenerique + ">";
			classePartis.nomCanoniqueComplet = classePartis.nomCanonique + "<" + classePartis.nomCanoniqueGenerique + ">";
		}
		return classePartis;
	}

	public static String concat(String...valeurs) throws Exception { 
		String resultat = Stream.of(valeurs).collect(Collectors.joining());
		return resultat;
	}  
}
