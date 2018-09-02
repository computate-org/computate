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

	/**
	 * var.enUS: populateQdoxSuperClassesInterfacesAndMe
	 * param2.var.enUS: qdoxSuperClasses
	 * param3.var.enUS: qdoxSuperClassesAndMe
	 * param4.var.enUS: qdoxSuperClassesAndInterfaces
	 * param5.var.enUS: qdoxSuperClassesInterfacesAndMe
	 * r: classeSuper
	 * r.enUS: superClass
	 * r: interfacesImplementees
	 * r.enUS: interfacesImplemented
	 * r: classesSuperQdoxInterfacesEtMoi
	 * r.enUS: qdoxSuperClassesInterfacesAndMe
	 * r: classesSuperQdoxEtInterfaces
	 * r.enUS: qdoxSuperClassesAndInterfaces
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: classesSuperQdox
	 * r.enUS: qdoxSuperClasses
	 * r: peuplerClassesSuperQdoxInterfacesEtMoi
	 * r.enUS: populateQdoxSuperClassesInterfacesAndMe
	 */ 
	public void peuplerClassesSuperQdoxInterfacesEtMoi (
			JavaClass c
			, ArrayList<JavaClass> classesSuperQdox
			, ArrayList<JavaClass> classesSuperQdoxEtMoi
			, ArrayList<JavaClass> classesSuperQdoxEtInterfaces
			, ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi
			) throws Exception { 
		if(c != null) {
			JavaClass classeSuper = c.getSuperJavaClass();
			List<JavaClass> interfacesImplementees = c.getInterfaces();

			for(JavaClass interfaceQdox : interfacesImplementees) {
				if(interfaceQdox != null && !interfaceQdox.getCanonicalName().equals("java.lang.Object") && !c.equals(interfaceQdox)) {
					classesSuperQdoxInterfacesEtMoi.add(interfaceQdox);
					classesSuperQdoxEtInterfaces.add(classeSuper);
					peuplerClassesSuperQdoxInterfacesEtMoi(interfaceQdox, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi); // Doesn't seem to work for interfaces that extend other interfaces.
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
	
	/**
	 * var.enUS: storeSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean stockerSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_string"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: storeSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean stockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * var.enUS: storeSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_string"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: storeListSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerListeSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_strings"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: storeListSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean stockerListeSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_booleans"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: storeSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * var.enUS: storeListSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerListeSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * var.enUS: storeSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValues
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeursChamp
	 * r.enUS: fieldValues
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected List<String> stockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	}
	
	/**
	 * var.enUS: indexSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * var.enUS: indexListSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerListeSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_strings"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * var.enUS: indexSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerSolr(SolrInputDocument doc, String nomChamp, String langueNom, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexListSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerListeSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValues
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeursChamp
	 * r.enUS: fieldValues
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected List<String> indexerSolr(SolrInputDocument doc, String nomChamp, String langueNom, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	} 
	
	/**
	 * var.enUS: indexStoreSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Long indexerStockerSolr(SolrInputDocument doc, String nomChamp, Long valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_long"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_long"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexStoreSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Integer indexerStockerSolr(SolrInputDocument doc, String nomChamp, Integer valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_int"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_int"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexStoreSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerStockerSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_boolean"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_boolean"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexStoreSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Date indexerStockerSolr(SolrInputDocument doc, String nomChamp, Date valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_date"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_date"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexStoreSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerStockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}   
	
	/**
	 * var.enUS: indexStoreSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_string"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexStoreListSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerListeSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_strings"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_strings"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexStoreSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexStoreListSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerListeSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * var.enUS: indexStoreSolr
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: languageName
	 * param4.var.enUS: fieldValues
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeursChamp
	 * r.enUS: fieldValues
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected List<String> indexerStockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
				doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	}

	/**
	 * var.enUS: classDocsAdd
	 * param1.var.enUS: canonicalName
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeDocs
	 * r.enUS: classDocs
	 */
	protected SolrDocument classeDocsAjouter(String nomCanonique) throws Exception {
		SolrDocument doc = null;
		if(StringUtils.startsWith(nomEnsembleDomaine, nomCanonique)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(nomCanonique));
			rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
			QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) {
				doc = listeRecherche.get(0);
				classeDocs.put(nomCanonique, doc);
			}
		}
		return doc;
	}
	
	/**
	 * var.enUS: searchCanonicalName
	 * param1.var.enUS: languageName
	 * param2.var.enUS: canonicalName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeDocsAjouter
	 * r.enUS: classDocsAdd
	 */
	protected String rechercherNomCanonique(String langueNom, String nomCanonique) throws Exception {
		SolrDocument doc = classeDocsAjouter(nomCanonique);
		String val = null;
		if(doc != null) {
			val = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
		}
		if(StringUtils.isEmpty(val)) {
			val = nomCanonique;  
		}
		return val;
	}  

	////////////
	// autres //
	////////////

	/**
	 * var.enUS: storeRegexComments
	 * param1.var.enUS: comment
	 * param2.var.enUS: languageName
	 * param4.var.enUS: entityVar
	 * r: langueNom
	 * r.enUS: languageName
	 * r: varEntite
	 * r.enUS: entityVar
	 * r: commentaire
	 * r.enUS: comment
	 * r: trouve
	 * r.enUS: found
	 * r: langue
	 * r.enUS: language
	 * r: texte
	 * r.enUS: text
	 * r: stockerSolr
	 * r.enUS: storeSolr
	 */
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
				stockerSolr(doc, varEntite, langueNom, b.toString());
		}
		return commentaire;
	}
//
//	public SolrDocument documentSolr(ClasseParts classeParts) throws Exception {
//		SolrDocument documentSolr = classeParts.documentSolr(this);
//		return documentSolr;
//	}
//
//	public ClasseParts classeParts(JavaClass classeQdox, String langueNom) throws Exception {
//		String nomCanoniqueGenerique = classeQdox.getGenericCanonicalName();
//		ClasseParts resultat = classeParts.get(nomCanoniqueGenerique + "." + langueNom);
//		if(resultat == null) {
//			resultat = ClasseParts.initClasseParts(this, classeQdox, langueNom);
//			SolrDocument documentSolr = resultat.documentSolr(this);
//			resultat.documentSolr = documentSolr;
//			classeParts.put(nomCanoniqueGenerique + "." + langueNom, resultat);
//		}
//		return resultat;
//	}
//
//	public ClasseParts classeParts(ClasseParts classeParts, String langueNom) throws Exception {
//		ClasseParts resultat = ClasseParts.initClasseParts(this, classeParts, langueNom);
//		return resultat;
//	} 

	/**
	 * var.enUS: indexClass
	 * param1.var.enUS: classAbsolutePath
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: classeNomCanoniqueSuperGeneriqueLangue
	 * r.enUS: classSuperCanonicalNameGenericLanguage
	 * r: classeNomCanoniqueSuperGenerique
	 * r.enUS: classSuperCanonicalNameGeneric
	 * r: classeNomCanoniqueSuper
	 * r.enUS: classSuperCanonicalName
	 * r: classeNomCanoniqueGen
	 * r.enUS: classCanonicalNameGen
	 * r: classeNomCanoniqueSuperDoc
	 * r.enUS: classSuperCanonicalNameDoc
	 * r: classeNomCanoniqueGenLangue
	 * r.enUS: classCanonicalNameGenLanguage
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeNomSimpleSuperGeneriqueLangue
	 * r.enUS: classSuperSimpleNameGenericLanguage
	 * r: classeNomSimpleSuperGenerique
	 * r.enUS: classSuperSimpleNameGeneric
	 * r: classeNomSimpleSuper
	 * r.enUS: classSuperSimpleName
	 * r: classeNomSimpleGen
	 * r.enUS: classSimpleNameGen
	 * r: classeNomSimpleSuperDoc
	 * r.enUS: classSuperSimpleNameDoc
	 * r: classeNomSimpleGenLangue
	 * r.enUS: classSimpleNameGenLanguage
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: bricoleur
	 * r.enUS: builder
	 * r: classeQdoxSuper
	 * r.enUS: classSuperQdox
	 * r: classeQdoxRetour
	 * r.enUS: classReturnQdox
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * r:  indexerStockerSolr
	 * r.enUS: indexStoreSolr
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeNomCompletSuperGenerique
	 * r.enUS: classSuperCompleteNameGeneric
	 * r: classeNomCompletSuper
	 * r.enUS: classSuperCompleteName
	 * r: stockerRegexCommentaires
	 * r.enUS: storeRegexComments
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: commentaire
	 * r.enUS: comment
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeCheminRepertoireGenLangue
	 * r.enUS: classGenDirPathLanguage
	 * r: classeCheminRepertoireGen
	 * r.enUS: classGenDirPath
	 * r: classeCheminRepertoireLangue
	 * r.enUS: classDirPathLanguage
	 * r: classeCheminRepertoire
	 * r.enUS: classDirPath
	 * r: classeCheminGen
	 * r.enUS: classGenPath
	 * r: classeCheminAbsolu
	 * r.enUS: classPathAbsolute
	 * r: classeChemin
	 * r.enUS: classPath
	 * r: peuplerClassesSuperQdoxInterfacesEtMoi
	 * r.enUS: populateQdoxSuperClassesInterfacesAndMe
	 * r: classeCle
	 * r.enUS: classKey
	 * r: modifieeDate
	 * r.enUS: modifiedDate
	 * r: classesSuperQdoxInterfacesEtMoi
	 * r.enUS: qdoxSuperClassesInterfacesAndMe
	 * r: classesSuperQdoxEtInterfaces
	 * r.enUS: qdoxSuperClassesAndInterfaces
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: classesSuperQdox
	 * r.enUS: qdoxSuperClasses
	 * r: modifiee
	 * r.enUS: modified
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: appliCheminLangue
	 * r.enUS: appPathLanguage
	 * r: appliChemins
	 * r.enUS: appPaths
	 * r: stockerSolr
	 * r.enUS: storeSolr
	 * r: rechercherNomCanonique
	 * r.enUS: searchCanonicalName
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: regexRemplacerTout
	 * r.enUS: regexReplaceAll
	 * r: BBBBBBBBBBB
	 * r.enUS: BBBBBBBBBBB
	 * r: BBBBBBBBBBB
	 * r.enUS: BBBBBBBBBBB
	 * r: BBBBBBBBBBB
	 * r.enUS: BBBBBBBBBBB
	 * r: BBBBBBBBBBB
	 * r.enUS: BBBBBBBBBBB
	 */
	protected void indexerClasse(String classeCheminAbsolu) throws Exception { 
		SolrInputDocument classeDoc = new SolrInputDocument();
		String classeNomCanonique = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classeCheminAbsolu, "."), cheminSrcMainJava + "/"), "/", ".");
		String classeNomSimple = StringUtils.substringAfterLast(classeNomCanonique, ".");
		String classeNomCanoniqueGen = classeNomCanonique + "Gen";
		String classeNomSimpleGen = classeNomSimple + "Gen";
		JavaClass classeQdox = bricoleur.getClassByName(classeNomCanonique.toString());
		JavaClass classeQdoxSuper = classeQdox.getSuperJavaClass();
		String classeNomCanoniqueSuper = classeQdoxSuper.getCanonicalName();
		String classeNomSimpleSuper = StringUtils.substringAfterLast(classeNomCanoniqueSuper, ".");
		Boolean classeEtendGen = indexerStockerSolr(classeDoc, "classeEtendGen", StringUtils.endsWith(classeNomSimpleSuper, "Gen"));
		
		String classeNomCompletSuper = indexerStockerSolr(classeDoc, "classeNomCompletSuper", langueNom, classeQdoxSuper.getGenericCanonicalName());
		String classeNomCompletSuperGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(classeNomCompletSuper, "<"), ">");
		String classeNomCanoniqueSuperGenerique = null;
		String classeNomSimpleSuperGenerique = null;
		JavaClass classeSuperGeneriqueQdox = null;
		if(StringUtils.isNotEmpty(classeNomCompletSuper)) {
			indexerStockerSolr(classeDoc, "classeNomCompletSuperGenerique", langueNom, classeNomCompletSuperGenerique);
			classeNomCanoniqueSuperGenerique = classeNomCompletSuper.contains("<") ? StringUtils.substringBefore(classeNomCompletSuper, "<") : classeNomCompletSuper;
			classeNomCanoniqueSuperGenerique = classeNomCompletSuper.contains(",") ? StringUtils.substringBefore(classeNomCompletSuper, ",") : classeNomCompletSuper;
			if(StringUtils.isNotEmpty(classeNomCanoniqueSuperGenerique)) {
				indexerStockerSolr(classeDoc, "classeNomCanoniqueSuperGenerique", langueNom, classeNomCanoniqueSuperGenerique);
				classeSuperGeneriqueQdox = bricoleur.getClassByName(classeNomCanoniqueSuperGenerique);

				if(classeNomCanoniqueSuperGenerique.contains("."))
					classeNomSimpleSuperGenerique = StringUtils.substringAfterLast(classeNomCanoniqueSuperGenerique, ".");
				else
					classeNomSimpleSuperGenerique = classeNomCanoniqueSuperGenerique;
				indexerStockerSolr(classeDoc, "classeNomSimpleSuperGenerique", langueNom, classeNomSimpleSuperGenerique);
			}
		}
		
		
		
		
		
		String commentaire = stockerRegexCommentaires(classeQdox.getComment(), langueNom, classeDoc, "classeCommentaire");
		String classeNomEnsemble = StringUtils.substringBeforeLast(classeNomCanonique, ".");
		String classeChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), ".java");
		String classeCheminRepertoire = StringUtils.substringBeforeLast(classeChemin, "/");
		String classeCheminGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Gen.java");
		String classeCheminRepertoireGen = StringUtils.substringBeforeLast(classeCheminGen, "/");
		String classeCle = classeCheminAbsolu;
		Instant modifiee = Instant.now();
		Date modifieeDate = Date.from(modifiee);
		
		ArrayList<JavaClass> classesSuperQdox = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtMoi = new ArrayList<JavaClass>();
		classesSuperQdoxEtMoi.add(classeQdox);
		ArrayList<JavaClass> classesSuperQdoxEtInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi = new ArrayList<JavaClass>();
		classesSuperQdoxInterfacesEtMoi.add(classeQdox);
		peuplerClassesSuperQdoxInterfacesEtMoi(classeQdox, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi);

		indexerStockerSolr(classeDoc, "langueNom", langueNom); 
		indexerStockerSolr(classeDoc, "modifiee", modifieeDate); 
		indexerStockerSolr(classeDoc, "classeNomCanonique", langueNom, classeNomCanonique); 
		indexerStockerSolr(classeDoc, "classeNomSimple", langueNom, classeNomSimple); 
		indexerStockerSolr(classeDoc, "classeNomEnsemble", langueNom, classeNomEnsemble); 
		indexerStockerSolr(classeDoc, "classeNomCanoniqueGen", langueNom, classeNomCanoniqueGen); 
		indexerStockerSolr(classeDoc, "classeNomSimpleGen", langueNom, classeNomSimpleGen); 
		indexerStockerSolr(classeDoc, "classeNomCanoniqueSuper", langueNom, classeNomCanoniqueSuper); 
		indexerStockerSolr(classeDoc, "classeNomSimpleSuper", langueNom, classeNomSimpleSuper); 
		indexerStockerSolr(classeDoc, "classeCheminAbsolu", classeCheminAbsolu);
		indexerStockerSolr(classeDoc, "classeChemin", langueNom, classeChemin); 
		indexerStockerSolr(classeDoc, "classeCheminRepertoire", langueNom, classeCheminRepertoire);  
		indexerStockerSolr(classeDoc, "classeCheminGen", langueNom, classeCheminGen); 
		indexerStockerSolr(classeDoc, "classeCheminRepertoireGen", langueNom, classeCheminRepertoireGen); 

		SolrDocument classeNomCanoniqueSuperDoc = null;   
		if(StringUtils.startsWith(classeNomCanoniqueSuper, nomEnsembleDomaine)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + ClientUtils.escapeQueryChars(classeNomCanoniqueSuper));
			rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
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
			String classeCheminLangue = indexerStockerSolr(classeDoc, "classeChemin", langueNom, concat(cheminSrcMainJavaLangue, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java"));
			String classeCheminRepertoireLangue = stockerSolr(classeDoc, "classeCheminRepertoire", langueNom, StringUtils.substringBeforeLast(classeCheminLangue, "/"));
			String classeCheminGenLangue = indexerStockerSolr(classeDoc, "classeCheminGen", langueNom, concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java"));
			String classeCheminRepertoireGenLangue = stockerSolr(classeDoc, "classeCheminRepertoireGen", langueNom, StringUtils.substringBeforeLast(classeCheminGenLangue, "/"));

			indexerStockerSolr(classeDoc, "classeNomCanonique", langueNom, classeNomCanoniqueLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimple", langueNom, classeNomSimpleLangue); 
			indexerStockerSolr(classeDoc, "classeNomCanoniqueGen", langueNom, classeNomCanoniqueGenLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimpleGen", langueNom, classeNomSimpleGenLangue); 
			indexerStockerSolr(classeDoc, "classeNomEnsemble", langueNom, classeNomEnsembleLangue); 

			if(classeNomCanoniqueSuperDoc == null) {
				indexerStockerSolr(classeDoc, "classeNomCanoniqueSuper", langueNom, classeNomCanoniqueSuper); 
				indexerStockerSolr(classeDoc, "classeNomSimpleSuper", langueNom, classeNomSimpleSuper); 
			}
			else {
				indexerStockerSolr(classeDoc, "classeNomCanoniqueSuper", langueNom, (String)classeNomCanoniqueSuperDoc.get("classeNomCanonique_" + langueNom + "_stored_string"));
				indexerStockerSolr(classeDoc, "classeNomSimpleSuper", langueNom, (String)classeNomCanoniqueSuperDoc.get("classeNomSimple_" + langueNom + "_stored_string"));
			}
			String classeNomCompletSuperLangue = indexerStockerSolr(classeDoc, "classeNomCompletSuper", langueNom, rechercherNomCanonique(langueNom, classeNomCompletSuper));
			if(StringUtils.isNotEmpty(classeNomCompletSuperGenerique)) {
				String classeNomCompletSuperGeneriqueLangue = indexerStockerSolr(classeDoc, "classeNomCompletSuperGenerique", langueNom, rechercherNomCanonique(langueNom, classeNomCompletSuperGenerique));
				String classeNomCanoniqueSuperGeneriqueLangue = indexerStockerSolr(classeDoc, "classeNomCanoniqueSuperGenerique", langueNom, rechercherNomCanonique(langueNom, classeNomCanoniqueSuperGenerique));
				String classeNomSimpleSuperGeneriqueLangue = indexerStockerSolr(classeDoc, "classeNomSimpleSuperGenerique", langueNom, rechercherNomCanonique(langueNom, classeNomSimpleSuperGenerique));
			}
		} 

		SolrInputDocument docClasseClone = classeDoc.deepCopy();
		Integer partNumero = 1;

		classeDoc.addField("cle", classeCle);  

		indexerStockerSolr(classeDoc, "partEstClasse", true);
		indexerStockerSolr(classeDoc, "partNumero", partNumero);
		
//		List<String> classeImportations = indexerStocker(classeDoc, "classeImportations", langueNom, classeQdoxClasse.getSource().getImports());
		for(String classeImportation : classeQdox.getSource().getImports()) {
			ClasseParts classeImportationClasseParts = ClasseParts.initClasseParts(this, classeImportation, langueNom);
			indexerStockerListeSolr(classeDoc, "classeImportations", langueNom, classeImportationClasseParts.nomCanonique);

//			SolrDocument classeImportationDoc = classeDocsAjouter(classeNomSimpleSuperGenerique);
//			indexerStockerListe(classeDoc, "classeImportations", langueNom, classeImportation);
			for(String langueNom : autresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classeImportationClasseParts, langueNom);
				indexerStockerListeSolr(classeDoc, "classeImportations", langueNom, classeImportationClassePartsLangue.nomCanonique);
//				if(classeImportationDoc == null) {
//					indexerStockerListe(classeDoc, "classeImportations", langueNom, classeImportation);
//				} else {
//					String classeImportationLangue = (String)classeImportationDoc.get("classeNomCanonique_" + langueNom + "_stored_string");
//					indexerStockerListe(classeDoc, "classeImportations", langueNom, classeImportationLangue);
//				}
			}
		}

		List<JavaMember> membresQdox = new ArrayList<JavaMember>();
		membresQdox.addAll(classeQdox.getFields());
		membresQdox.addAll(classeQdox.getConstructors());
		membresQdox.addAll(classeQdox.getMethods());
		for(JavaMember membreQdox : membresQdox) {  
			partNumero++;

			if(membreQdox instanceof JavaField) {    
				SolrInputDocument champDoc = docClasseClone.deepCopy();
				JavaField champQdox = (JavaField)membreQdox;
				String champCommentaire = champQdox.getComment();
				String champVar = champQdox.getName();
				String champCle = classeCheminAbsolu + "." + champVar;
				String champCodeSource = StringUtils.substringBeforeLast(StringUtils.trim(regex("\\s+" + champVar + "\\s*=([\\s\\S]*)", champQdox.getCodeBlock(), 1)), ";");

				// Champs Solr du champ. 

				champDoc.addField("cle", champCle);
				indexerStockerSolr(champDoc, "champVar", langueNom, champVar); 
				indexerStockerSolr(champDoc, "partEstChamp", true);
				indexerStockerSolr(champDoc, "partNumero", partNumero);
				indexerStockerSolr(champDoc, "champEstPublic", champQdox.isPublic()); 
				indexerStockerSolr(champDoc, "champEstProtege", champQdox.isProtected()); 
				indexerStockerSolr(champDoc, "champEstPrive", champQdox.isPrivate()); 
				indexerStockerSolr(champDoc, "champEstStatique", champQdox.isStatic()); 
				indexerStockerSolr(champDoc, "champEstFinale", champQdox.isFinal()); 
				indexerStockerSolr(champDoc, "champEstAbstrait", champQdox.isAbstract()); 
				indexerStockerSolr(champDoc, "champEstNatif", champQdox.isNative()); 
	
				///////////////////////
				// Champ Annotations //
				///////////////////////
				List<JavaAnnotation> annotations = champQdox.getAnnotations(); 
				ArrayList<String> annotationsLangue = new ArrayList<String>();
				Boolean champEstTest = false;
				Boolean champEstSubstitue = false;
				for(JavaAnnotation annotation : annotations) {
					String champAnnotationLangue = annotation.getType().getCanonicalName();
					indexerStockerSolr(champDoc, "champAnnotation", langueNom, champAnnotationLangue); 

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						champEstTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						champEstSubstitue = true;
					}
				}
				indexerStockerSolr(champDoc, "champEstTest", langueNom, champEstTest); 
				indexerStockerSolr(champDoc, "champEstSubstitue", langueNom, champEstSubstitue); 

				ClasseParts champClasseParts = ClasseParts.initClasseParts(this, champQdox.getType(), langueNom);
	
				stockerRegexCommentaires(champCommentaire, langueNom, champDoc, "champCommentaire");
				stockerSolr(champDoc, "champNomSimpleComplet", langueNom, champClasseParts.nomSimpleComplet);
				stockerSolr(champDoc, "champNomCanoniqueComplet", langueNom, champClasseParts.nomCanoniqueComplet);
				stockerSolr(champDoc, "champCodeSource", langueNom, champCodeSource);
				//////////////////
				// Champ Langue //
				//////////////////
				for(String langueNom : autresLangues) { 
					ClasseParts champClassePartsLangue = ClasseParts.initClasseParts(this, champClasseParts, langueNom);
					String champVarLangue = regex("^var\\." + langueNom + ": (.*)", champCommentaire);
					champVarLangue = champVarLangue == null ? champVar : champVarLangue;
					String champCodeSourceLangue = regexRemplacerTout(champCommentaire, champCodeSource, langueNom);

					indexerStockerSolr(champDoc, "champVar", langueNom, champVarLangue); 
					stockerSolr(champDoc, "champNomSimpleComplet", langueNom, champClassePartsLangue.nomSimpleComplet);
					stockerSolr(champDoc, "nomCanoniqueComplet", langueNom, champClassePartsLangue.nomCanoniqueComplet);
					stockerRegexCommentaires(champCommentaire, langueNom, champDoc, "champCommentaire");
					stockerSolr(champDoc, "champCodeSource", langueNom, champCodeSourceLangue);
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

				indexerStockerSolr(classeDoc, "constructeurEstConstructeur", langueNom, true); 
				indexerStockerSolr(classeDoc, "constructeurEstPublic", langueNom, constructeurQdox.isPublic()); 
				indexerStockerSolr(classeDoc, "constructeurEstProtege", langueNom, constructeurQdox.isProtected()); 
				indexerStockerSolr(classeDoc, "constructeurEstPrive", langueNom, constructeurQdox.isPrivate()); 
				indexerStockerSolr(classeDoc, "constructeurEstStatique", langueNom, constructeurQdox.isStatic()); 
				indexerStockerSolr(classeDoc, "constructeurEstFinale", langueNom, constructeurQdox.isFinal()); 
				indexerStockerSolr(classeDoc, "constructeurEstAbstrait", langueNom, constructeurQdox.isAbstract()); 
				indexerStockerSolr(classeDoc, "constructeurEstNatif", langueNom, constructeurQdox.isNative()); 

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
	
					if(!methodeEstSubstitue && !methodeQdox.isStatic() && !methodeQdox.isFinal() && methodeQdox.getDeclaringClass().equals(classeQdox) 
							&& methodeQdox.isProtected() && methodeParamsQdox.size() == 1 && classeQdoxRetour.isVoid()
							&& StringUtils.startsWith(methodeQdox.getName(), "_")) {
						// est Entite. 
						SolrInputDocument entiteDoc = docClasseClone.deepCopy();
						String entiteVar = indexerStockerSolr(entiteDoc, "entiteVar", langueNom, StringUtils.substringAfter(methodeQdox.getName(), "_"));
						JavaClass entiteClasseQdox = methodeParamsQdox.get(0).getJavaClass();
						boolean entiteCouverture = false;
						String entiteNomCanonique = indexerStockerSolr(entiteDoc, "entiteNomCanonique", langueNom, entiteClasseQdox.getCanonicalName());

						String entiteNomSimple;
						if(entiteNomCanonique.contains("."))
							entiteNomSimple = StringUtils.substringBefore(StringUtils.substringAfterLast(entiteNomCanonique, "."), ">");
						else
							entiteNomSimple = StringUtils.substringBefore(entiteNomCanonique.toString(), ">");
						indexerStockerSolr(entiteDoc, "entiteNomSimple", langueNom, entiteNomSimple);

						String entiteTypeOrigine = indexerStockerSolr(entiteDoc, "entiteTypeOrigine", langueNom, entiteClasseQdox.getGenericCanonicalName());

						String entiteNomCompletGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteTypeOrigine, "<"), ">");
						String entiteNomCanoniqueGenerique = null;
						JavaClass entiteClasseGeneriqueQdox = null;
						String entiteNomSimpleGenerique = null;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique)) {
							indexerStockerSolr(entiteDoc, "entiteNomCompletGenerique", langueNom, entiteNomCompletGenerique);
							entiteNomCanoniqueGenerique = entiteNomCompletGenerique.contains("<") ? StringUtils.substringBefore(entiteNomCompletGenerique, "<") : entiteNomCompletGenerique;
							entiteNomCanoniqueGenerique = entiteNomCompletGenerique.contains(",") ? StringUtils.substringBefore(entiteNomCompletGenerique, ",") : entiteNomCompletGenerique;
							if(StringUtils.isNotEmpty(entiteNomCanoniqueGenerique)) {
								indexerStockerSolr(entiteDoc, "entiteNomCanoniqueGenerique", langueNom, entiteNomCanoniqueGenerique);
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
								indexerStockerSolr(entiteDoc, "entiteNomSimpleGenerique", langueNom, entiteNomSimpleGenerique);
							}
						}
						
						String entiteNomCanoniqueComplet;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique))
							entiteNomCanoniqueComplet = entiteNomCanonique + "<" + entiteNomCompletGenerique + ">";
						else
							entiteNomCanoniqueComplet = entiteNomCanonique;
						indexerStockerSolr(entiteDoc, "entiteNomCanoniqueComplet", langueNom, entiteNomCanoniqueComplet);
						
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
						indexerStockerSolr(entiteDoc, "entiteNomSimpleComplet", langueNom, entiteNomSimpleComplet);
						
						String entiteNomSimpleCompletGenerique = null;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique)) {
							if(entiteNomCompletGenerique.contains(".")) {
								entiteNomSimpleCompletGenerique = StringUtils.substringAfterLast(entiteNomCompletGenerique, ".");
							}
							else {
								entiteNomSimpleCompletGenerique = entiteNomCompletGenerique;
							}
						}
						indexerStockerSolr(entiteDoc, "entiteNomSimpleCompletGenerique", langueNom, entiteNomSimpleCompletGenerique);
						
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
						indexerStockerSolr(entiteDoc, "entiteNomCanoniqueBase", langueNom, entiteNomCanoniqueBase);
						
						String entiteNomSimpleBase = null;
						if(StringUtils.isNotEmpty(entiteNomCanoniqueBase)) {
							entiteNomSimpleBase = StringUtils.substringAfterLast(entiteNomCanoniqueBase, ".");
						}
						indexerStockerSolr(entiteDoc, "entiteNomSimpleBase", langueNom, entiteNomSimpleBase);
						
						String entiteVarParam;
						if(entiteNomCanonique.equals(ArrayList.class.getCanonicalName()) || entiteNomCanonique.equals(List.class.getCanonicalName()))
							entiteVarParam = "l";
						else
							entiteVarParam = "o";
						indexerStockerSolr(entiteDoc, "entiteVarParam", langueNom, entiteVarParam);
						
						String entiteVarCouverture = indexerStockerSolr(entiteDoc, "entiteVarCouverture", langueNom, entiteVar + "Couverture");
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
//						boolean etendPagePart = entite.etendClasse(nomCanoniquePagePartActuel);
//						entite.etendPagePart(etendPagePart);
//						if(!etendPagePart && entite.nomCanoniqueGenerique.pasVide()) {
//							boolean listePagePart = etendClasse(nomCanoniquePagePartActuel, entite.nomCanoniqueGenerique.toString());
//							entite.listePagePart(listePagePart);
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
						
						
						
						
						
						
						
						
						
						
						
						
						
						indexerStockerSolr(entiteDoc, "entiteVar", langueNom, entiteVar);
						for(JavaAnnotation annotation : annotations) {
							String entiteAnnotationLangue = indexerStockerSolr(entiteDoc, "entiteAnnotations", langueNom, annotation.getType().getCanonicalName());
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
						indexerStockerSolr(entiteDoc, "partEstEntite", true);
						indexerStockerSolr(entiteDoc, "partNumero", partNumero);
	
						String entiteVarLangue = regex("^var\\." + langueNom + ": (.*)", methodeCommentaire);
						entiteVarLangue = indexerStockerSolr(entiteDoc, "entiteVar", langueNom, entiteVarLangue == null ? entiteVar : entiteVarLangue);
	
						List<String> entiteCommentairesLangue = regexListe("(.*)", methodeCommentaire);
						String entiteCommentaireLangue = indexerStockerSolr(entiteDoc, "entiteCommentaire", langueNom, StringUtils.join(entiteCommentairesLangue, "\n"));
	
						String entiteBlocCode = methodeQdox.getCodeBlock();
						String entiteBlocCodeLangue = entiteBlocCode;
						ArrayList<String> remplacerClesLangue = regexListe("^r." + langueNom + "\\s*=\\s*(.*)\\n.*", methodeCommentaire);
						ArrayList<String> remplacerValeursLangue = regexListe("^r." + langueNom + "\\s*=\\s*.*\\n(.*)", methodeCommentaire);
						for(int i = 0; i < remplacerClesLangue.size(); i++) {
							String cle = remplacerClesLangue.get(i);
							String valeur = remplacerValeursLangue.get(i);
							StringUtils.replace(entiteBlocCodeLangue, cle, valeur);
						}
						stockerSolr(entiteDoc, "entiteBlocCode", langueNom, entiteBlocCodeLangue); 

						stockerRegexCommentaires(methodeCommentaire, langueNom, entiteDoc, "entiteCommentaire");

						clientSolrComputate.add(entiteDoc); 
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
					else {
						// est Methode. 
						
						SolrInputDocument methodeDoc = docClasseClone.deepCopy();
						indexerStockerSolr(methodeDoc, "methodeVar", langueNom, methodeVar);
						for(Integer methodeParamNum = 1; methodeParamNum <= methodeParamsQdox.size(); methodeParamNum++) {
							JavaParameter methodeParamQdox = methodeParamsQdox.get(methodeParamNum - 1);
							String methodeParamVar = methodeParamQdox.getName();
							stockerListeSolr(methodeDoc, "methodeParamVar", langueNom, methodeParamVar);
							ClasseParts methodeParamClasseParts = ClasseParts.initClasseParts(this, methodeParamQdox.getJavaClass(), langueNom);
							stockerListeSolr(methodeDoc, "methodeParamNomSimpleComplet", langueNom, methodeParamClasseParts.nomSimpleComplet);
							stockerListeSolr(methodeDoc, "methodeParamArgsVariable", methodeParamQdox.isVarArgs());
							for(String langueNom : autresLangues) { 
								String methodeParamVarLangue = regex("param" + methodeParamNum + "\\.var\\." + langueNom + ": (.*)", methodeCommentaire);
								if(methodeParamVarLangue == null)
									methodeParamVarLangue = methodeParamVar;
								ClasseParts methodeParamClassePartsLangue = ClasseParts.initClasseParts(this, methodeParamClasseParts, langueNom);

								stockerListeSolr(methodeDoc, "methodeParamNomSimpleComplet", langueNom, methodeParamClassePartsLangue.nomSimpleComplet);
								stockerListeSolr(methodeDoc, "methodeParamVar", langueNom, methodeParamVarLangue);
							}  
						}
						for(JavaAnnotation annotation : annotations) {
							String methodeAnnotationBlocCode = stockerListeSolr(methodeDoc, "methodeAnnotationBlocCode", langueNom, annotation.toString());
						}
						for(JavaClass methodeExceptionQdox : methodeExceptionsQdox) {
							String methodeExceptionNomSimpleComplet = StringUtils.substringAfterLast(methodeExceptionQdox.getCanonicalName(), ".");
							stockerListeSolr(methodeDoc, "methodeExceptionNomSimpleComplet", methodeExceptionNomSimpleComplet);
						}
						Boolean methodeEstVide = false;
						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void")) {
	
							ClasseParts methodeRetourClasseParts = ClasseParts.initClasseParts(this, methodeQdox.getReturns(), langueNom);
				
							stockerSolr(methodeDoc, "methodeRetourNomSimpleComplet", langueNom, methodeRetourClasseParts.nomSimpleComplet);
							//////////////////
							// Champ Langue //
							//////////////////
							for(String langueNom : autresLangues) { 
								ClasseParts methodeRetourClassePartsLangue = ClasseParts.initClasseParts(this, methodeRetourClasseParts, langueNom);
								stockerSolr(methodeDoc, "methodeRetourNomSimpleComplet", langueNom, methodeRetourClassePartsLangue.nomSimpleComplet);
							}  
							
							
							
							
							
							
							
							
							
							
//							ClasseParts methodeRetourClasseParts = classeParts(methodeQdox.getReturns());
//				
//							stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
//							stocker(methodeDoc, "methodeRetourNomSimpleComplet", langueNom, methodeRetourClasseParts.nomSimpleComplet);
//							stocker(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSource);
//							//////////////////
//							// Champ Langue //
//							//////////////////
//							for(String langueNom : autresLangues) { 
//								ClasseParts methodeRetourClassePartsLangue = classeParts(methodeRetourClasseParts, langueNom);
//								String methodeVarLangue = regex("methodeVar_" + langueNom + ": (.*)", methodeCommentaire);
//								methodeVarLangue = methodeVarLangue == null ? methodeVar : methodeVarLangue;
//								String methodeCodeSourceLangue = regexRemplacerTout(methodeCommentaire, methodeCodeSource, langueNom);
//			
//								indexerStocker(methodeDoc, "methodeVar", langueNom, methodeVarLangue); 
//								stocker(methodeDoc, "methodeNomSimpleComplet", langueNom, methodeClassePartsLangue.nomSimpleComplet);
//								stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
//								stocker(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSourceLangue);
//							}  
							
							
							
							
							
							
							
							
							
							
					
					
					
							methodeNomCanoniqueRetourComplet = indexerStockerSolr(methodeDoc, "methodeNomCanoniqueRetourComplet", langueNom, classeQdoxRetour.getGenericCanonicalName());
							methodeNomCanoniqueRetour = indexerStockerSolr(methodeDoc, "methodeNomCanoniqueRetour", langueNom, classeQdoxRetour.getCanonicalName());
							String methodeNomSimpleRetour = indexerStockerSolr(methodeDoc, "methodeNomSimpleRetour", langueNom, StringUtils.substringAfterLast(methodeNomCanoniqueRetour, "."));
							String listeNomTypeOrigineRetourGenerique = methodeNomCanoniqueRetourComplet;
							String methodeNomCanoniqueRetourGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineRetourGenerique, "<"), ">");
							String methodeNomSimpleRetourComplet;
							String methodeNomSimpleRetourGenerique;
							methodeNomCanoniqueRetourGenerique = methodeNomCanoniqueRetourGenerique.contains("<") ? StringUtils.substringBefore(methodeNomCanoniqueRetourGenerique, "<") : methodeNomCanoniqueRetourGenerique;
							methodeNomCanoniqueRetourGenerique = methodeNomCanoniqueRetourGenerique.contains(",") ? StringUtils.substringBefore(methodeNomCanoniqueRetourGenerique, ",") : methodeNomCanoniqueRetourGenerique;
							if(StringUtils.isNotEmpty(methodeNomCanoniqueRetourGenerique)) {
								indexerStockerSolr(methodeDoc, "methodeNomCanoniqueRetourGenerique", langueNom, methodeNomCanoniqueRetourGenerique);
	
								if(StringUtils.contains(methodeNomCanoniqueRetourGenerique, "."))
									methodeNomSimpleRetourGenerique = indexerStockerSolr(methodeDoc, "methodeNomSimpleRetourGenerique", langueNom, StringUtils.substringAfterLast(methodeNomCanoniqueRetourGenerique, "."));
								else
									methodeNomSimpleRetourGenerique = indexerStockerSolr(methodeDoc, "methodeNomSimpleRetourGenerique", langueNom, methodeNomCanoniqueRetourGenerique);
	
								if(StringUtils.contains(methodeNomSimpleRetourGenerique, ".")) {
									methodeNomSimpleRetourComplet = indexerStockerSolr(methodeDoc, "methodeNomSimpleRetourComplet", langueNom, concat(StringUtils.substringAfterLast(methodeNomSimpleRetour, "."), "<", methodeNomSimpleRetourGenerique, ">"));
								}
								else {
									methodeNomSimpleRetourComplet = indexerStockerSolr(methodeDoc, "methodeNomSimpleRetourComplet", langueNom, concat(methodeNomSimpleRetour, "<", methodeNomSimpleRetourGenerique, ">"));
								}
							}
							else {
								methodeNomSimpleRetourComplet = indexerStockerSolr(methodeDoc, "methodeNomSimpleRetourComplet", langueNom, methodeNomSimpleRetour);
							}
						}
						else {
							methodeEstVide = true;
						}
						methodeEstVide = indexerStockerSolr(methodeDoc, "methodeEstVide", methodeEstVide);
	
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
						indexerStockerSolr(methodeDoc, "partEstMethode", true);
						indexerStockerSolr(methodeDoc, "partNumero", partNumero);

						indexerStockerSolr(methodeDoc, "methodeEstPublic", methodeQdox.isPublic());
						indexerStockerSolr(methodeDoc, "methodeEstProtege", methodeQdox.isProtected());
						indexerStockerSolr(methodeDoc, "methodeEstPrive", methodeQdox.isPrivate());
						indexerStockerSolr(methodeDoc, "methodeEstStatique", methodeQdox.isStatic());
						indexerStockerSolr(methodeDoc, "methodeEstFinale", methodeQdox.isFinal());
						indexerStockerSolr(methodeDoc, "methodeEstAbstrait", methodeQdox.isAbstract());
						indexerStockerSolr(methodeDoc, "methodeEstNatif", methodeQdox.isNative());
						indexerStockerSolr(methodeDoc, "methodeEstTest", methodeEstTest);
						indexerStockerSolr(methodeDoc, "methodeEstSubstitue", methodeEstSubstitue);
						stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
	
						String methodeVarLangue = regex("^var\\." + langueNom + ": (.*)", methodeCommentaire);
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
						stockerSolr(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSourceLangue);
		
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

							methodeVarLangue = regex("^var\\." + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
							methodeVarLangue = indexerStockerSolr(methodeDoc, "methodeVar", langueNom, methodeVarLangue == null ? methodeVar : methodeVarLangue);
		
							methodeCommentairesLangue = regexListe("^" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
		
							methodeCodeSourceLangue = regexRemplacerTout(methodeCommentaire, methodeCodeSource, langueNom);
							stockerSolr(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSourceLangue);
							stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
						} 
	
						clientSolrComputate.add(methodeDoc); 
					}
			
						//////////////////
						// Methode Langue //
						//////////////////
		
	//						String methodeVarLangue = regex("^var\\." + langueNom + ": (.*)", methodeCommentaire);
	//						methodeVarLangue = methodeVarLangue == null ? methodeVar : methodeVarLangue;
	//						methodeDoc.addField(concat("methodeVar_", langueNom, "_indexed_string"), methodeVarLangue);
	//						methodeDoc.addField(concat("methodeVar_", langueNom, "_stored_string"), methodeVarLangue);
	//	
	//						List<String> methodeCommentairesLangue = regexListe("(.*)", methodeCommentaire);
	//						String methodeCommentaireLangue = StringUtils.join(methodeCommentairesLangue, "\n");
	//						methodeDoc.addField(concat("methodeCommentaire_", langueNom, "_indexed_string"), methodeCommentaireLangue);
	//						methodeDoc.addField(concat("methodeCommentaire_", langueNom, "_stored_string"), methodeCommentaireLangue);
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
	//						methodeDoc.addField(concat("methodeBlocCode_", langueNom, "_indexed_string"), methodeBlocCodeLangue);
	//						methodeDoc.addField(concat("methodeBlocCode_", langueNom, "_stored_string"), methodeBlocCodeLangue);
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
	//							boolean etendPagePart = entite.etendClasse(nomCanoniquePagePartActuel);
	//							entite.etendPagePart(etendPagePart);
	//							if(!etendPagePart && entite.nomCanoniqueGenerique.pasVide()) {
	//								boolean listePagePart = etendClasse(nomCanoniquePagePartActuel, entite.nomCanoniqueGenerique.toString());
	//								entite.listePagePart(listePagePart);
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
		String qSupprimer = concat("classeCheminAbsolu_indexed_string", ":\"", classeChemin, "\" AND (modifiee_indexed_date:[* TO ", modifiee.toString(), "-1MILLI] OR (*:* NOT modifiee_indexed_date:*))");
		clientSolrComputate.deleteByQuery(qSupprimer);
		clientSolrComputate.commit(); 
	}
}
