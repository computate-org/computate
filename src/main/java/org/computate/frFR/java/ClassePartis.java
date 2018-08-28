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

public class ClassePartis {

	public String nomCanoniqueComplet;
	public String nomCanonique;
	public String nomSimple;
	private String listeNomTypeOrigineGenerique;
	public String nomCanoniqueGenerique;
	public String nomSimpleComplet;
	public String nomSimpleGenerique;
	public SolrDocument documentSolr;

	public SolrDocument documentSolr(ConfigSite configSite) throws Exception {
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

	public static ClassePartis initClassePartis(ClassePartis classePartis, String langueNom) throws Exception {
		SolrDocument documentSolr = classePartis.documentSolr;
		if(documentSolr != null) {
			ClassePartis resultat = new ClassePartis();
			resultat.nomCanonique = (String)documentSolr.get("classeNomCanonique_" + langueNom + "_stocke_string");
			resultat.nomCanoniqueComplet = resultat.nomCanonique + StringUtils.substringAfter(classePartis.nomCanoniqueComplet, classePartis.nomCanonique);
			resultat.nomCanoniqueGenerique = classePartis.nomCanoniqueGenerique;
			resultat.nomSimple = (String)documentSolr.get("classeNomCanonique_" + langueNom + "_stocke_string");
			resultat.nomSimpleComplet = resultat.nomSimple + StringUtils.substringAfter(classePartis.nomSimpleComplet, classePartis.nomSimple);
			resultat.nomSimpleGenerique = classePartis.nomSimpleGenerique;
			return resultat;
		}
		else {
			return classePartis;
		}
	}

	public ClassePartis initClassePartis(JavaClass classeQdox) throws Exception {
		nomCanoniqueComplet = classeQdox.getGenericCanonicalName();
		nomCanonique = classeQdox.getCanonicalName();
		nomSimple = StringUtils.substringAfterLast(nomCanonique, ".");
		listeNomTypeOrigineGenerique = nomCanoniqueComplet;
		nomCanoniqueGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineGenerique, "<"), ">");
//		nomCanoniqueGenerique = nomCanoniqueGenerique.contains("<") ? StringUtils.substringBefore(nomCanoniqueGenerique, "<") : nomCanoniqueGenerique;
//		nomCanoniqueGenerique = nomCanoniqueGenerique.contains(",") ? StringUtils.substringBefore(nomCanoniqueGenerique, ",") : nomCanoniqueGenerique;
		nomSimpleGenerique = "";
		nomSimpleComplet = nomSimple;
		if(StringUtils.isNotEmpty(nomCanoniqueGenerique)) {
			String[] partis = StringUtils.split(nomCanoniqueGenerique, ",");
			for(int i = 0; i < partis.length; i++) {
				String nomCanoniqueGeneriqueParti = partis[i];
				String nomSimpleGeneriqueParti;
				if(i > 0) {
					nomSimpleGenerique += ", ";
					nomSimpleComplet += ", ";
				}
	
				if(StringUtils.contains(nomCanoniqueGeneriqueParti, "."))
					nomSimpleGeneriqueParti = StringUtils.substringAfterLast(nomCanoniqueGeneriqueParti, ".");
				else
					nomSimpleGeneriqueParti = nomCanoniqueGeneriqueParti;

				nomSimpleGenerique += nomSimpleGeneriqueParti;
			}
		}
		if(StringUtils.isNotEmpty(nomSimpleGenerique)) {
			nomSimpleComplet = concat(nomSimple, "<", nomSimpleGenerique, ">");
		}
		return this;
	}

	public String concat(String...valeurs) throws Exception { 
		String resultat = Stream.of(valeurs).collect(Collectors.joining());
		return resultat;
	}  
}
