package org.computate.frFR.java; 

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.BooleanUtils;
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
import com.thoughtworks.qdox.model.JavaGenericDeclaration;
import com.thoughtworks.qdox.model.JavaMember;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.JavaType;
import com.thoughtworks.qdox.model.JavaTypeVariable;
import com.thoughtworks.qdox.model.impl.DefaultJavaParameterizedType;

/**
 * nomCanonique.enUS: org.computate.enUS.java.IndexClass
 */
public class IndexerClasse extends RegarderClasseBase { 
	public static final String VAL_nomCanoniqueString = String.class.getCanonicalName();
	public static final String VAL_nomCanoniqueBoolean = Boolean.class.getCanonicalName();
	public static final String VAL_nomCanoniqueDate = Date.class.getCanonicalName();
	public static final String VAL_nomCanoniqueLong = Long.class.getCanonicalName();
	public static final String VAL_nomCanoniqueDouble = Double.class.getCanonicalName();
	public static final String VAL_nomCanoniqueFloat = Float.class.getCanonicalName();
	public static final String VAL_nomCanoniqueBigDecimal = BigDecimal.class.getCanonicalName();
	public static final String VAL_nomCanoniqueInteger = Integer.class.getCanonicalName();
	public static final String VAL_nomCanoniqueTimestamp = Timestamp.class.getCanonicalName();
	public static final String VAL_nomCanoniqueLocalDateTime = LocalDateTime.class.getCanonicalName();
	public static final String VAL_nomCanoniqueLocalDate = LocalDate.class.getCanonicalName();
	public static final String VAL_nomCanoniqueList = List.class.getCanonicalName();
	public static final String VAL_nomCanoniqueArrayList = ArrayList.class.getCanonicalName();

	protected HashMap<String, ClasseParts> classePartsGenApi = new HashMap<String, ClasseParts>();
	protected HashMap<String, ClasseParts> classePartsGenPage = new HashMap<String, ClasseParts>();

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
		doc.addField(concat(nomChamp, "_stored_boolean"), valeurChamp);
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
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_boolean"), valeurChamp);
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
			rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
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

	protected ClasseParts classePartsCouverture(String nomEnsembleDomaine) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:Couverture");
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, langueNom);
		}
		return classeParts;
	}

	protected ClasseParts classePartsRequeteSite(String nomEnsembleDomaine) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:RequeteSite");
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, langueNom);
		}
		return classeParts;
	}

	protected ClasseParts classePartsEcouteurContexte(String nomEnsembleDomaine) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:EcouteurContexte");
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, langueNom);
		}
		return classeParts;
	}

	protected ClasseParts classePartsConfigSite(String nomEnsembleDomaine) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:ConfigSite");
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, langueNom);
		}
		return classeParts;
	}

	protected ClasseParts classePartsUtilisateurSite(String nomEnsembleDomaine) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:UtilisateurSite");
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, langueNom);
		}
		return classeParts;
	}

	protected ClasseParts classePartsCluster(String nomEnsembleDomaine) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:Cluster");
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, langueNom);
		}
		return classeParts;
	}

	protected ClasseParts classePartsResultatRecherche(String nomEnsembleDomaine) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:ResultatRecherche");
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, langueNom);
		}
		return classeParts;
	}

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

	public void classePartsGenAjouter(ClasseParts classeParts) {
		if(classePartsGen != null && classeParts != null && !classePartsGen.containsKey(classeParts.nomCanonique) && StringUtils.contains(classeParts.nomCanonique, "."))
			classePartsGen.put(classeParts.nomCanonique, classeParts);
	}

	public void classePartsGenApiAjouter(ClasseParts classeParts) {
		if(classePartsGenApi != null && classeParts != null && !classePartsGenApi.containsKey(classeParts.nomCanonique) && StringUtils.contains(classeParts.nomCanonique, "."))
			classePartsGenApi.put(classeParts.nomCanonique, classeParts);
	}

	public void classePartsGenPageAjouter(ClasseParts classeParts) {
		if(classePartsGenPage != null && classeParts != null && !classePartsGenPage.containsKey(classeParts.nomCanonique) && StringUtils.contains(classeParts.nomCanonique, "."))
			classePartsGenPage.put(classeParts.nomCanonique, classeParts);
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
	 * r: classeNomCanoniqueGenLangue
	 * r.enUS: classCanonicalNameGenLanguage
	 * r: classeNomCanoniqueGen
	 * r.enUS: classCanonicalNameGen
	 * r: classeNomCanoniqueSuperDoc
	 * r.enUS: classSuperCanonicalNameDoc
	 * r: classeNomCanoniqueLangue
	 * r.enUS: classCanonicalNameLanguage
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeNomSimpleSuperGeneriqueLangue
	 * r.enUS: classSuperSimpleNameGenericLanguage
	 * r: classeNomSimpleSuperGenerique
	 * r.enUS: classSuperSimpleNameGeneric
	 * r: classeNomSimpleSuper
	 * r.enUS: classSuperSimpleName
	 * r: classeNomSimpleGenLangue
	 * r.enUS: classSimpleNameGenLanguage
	 * r: classeNomSimpleGen
	 * r.enUS: classSimpleNameGen
	 * r: classeNomSimpleSuperDoc
	 * r.enUS: classSuperSimpleNameDoc
	 * r: classeNomSimpleGenLangue
	 * r.enUS: classSimpleNameGenLanguage
	 * r: classeNomSimpleLangue
	 * r.enUS: classSimpleNameLanguage
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
	 * r: classeNomCompletSuperGeneriqueLangue
	 * r.enUS: classSuperCompleteNameGenericLanguage
	 * r: classeNomCompletSuperGenerique
	 * r.enUS: classSuperCompleteNameGeneric
	 * r: classeNomCompletSuperLangue
	 * r.enUS: classSuperCompleteNameLanguage
	 * r: classeNomCompletSuper
	 * r.enUS: classSuperCompleteName
	 * r: stockerRegexCommentaires
	 * r.enUS: storeRegexComments
	 * r: cheminSrcMainJavaLangue
	 * r.enUS: srcMainJavaPathLanguage
	 * r: cheminSrcGenJavaLangue
	 * r.enUS: srcGenJavaPathLanguage
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: commentaire
	 * r.enUS: comment
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: classeNomEnsembleLangue
	 * r.enUS: classPackageNameLanguage
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
	 * r: classeCheminGenLangue
	 * r.enUS: classGenPathLanguage
	 * r: classeCheminGen
	 * r.enUS: classGenPath
	 * r: classeCheminAbsolu
	 * r.enUS: classPathAbsolute
	 * r: classeCheminLangue
	 * r.enUS: classPathLanguage
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
	 * r: regexRemplacerTout
	 * r.enUS: regexReplaceAll
	 * r: classeDocClone
	 * r.enUS: classDocClone
	 * r: "cle"
	 * r.enUS: "key"
	 * r: partNumero
	 * r.enUS: partNumber
	 * r: classeImportationClassePartsLangue
	 * r.enUS: classImportClassPartsLanguage
	 * r: classeImportationClasseParts
	 * r.enUS: classeImportClassParts
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * r: classeImportations
	 * r.enUS: classImports
	 * r: indexerStockerListeSolr
	 * r.enUS: indexStoreListSolr
	 * r: membresQdox
	 * r.enUS: membersQdox
	 * r: membreQdox
	 * r.enUS: memberQdox
	 * r: champDoc
	 * r.enUS: fieldDoc
	 * r: champQdox
	 * r.enUS: fieldQdox
	 * r: champCommentaire
	 * r.enUS: fieldComment
	 * r: champCle
	 * r.enUS: fieldKey
	 * r: champCodeSourceLangue
	 * r.enUS: fieldSourceCodeLanguage
	 * r: champCodeSource
	 * r.enUS: fieldSourceCode
	 * r: partEstChamp
	 * r.enUS: partIsField
	 * r: champEstPublic
	 * r.enUS: fieldIsPublic
	 * r: champEstProtege
	 * r.enUS: fieldIsProtected
	 * r: champEstPrive
	 * r.enUS: fieldIsPrivate
	 * r: champEstStatique
	 * r.enUS: fieldIsStatic
	 * r: champEstFinale
	 * r.enUS: fieldIsFinal
	 * r: champEstAbstrait
	 * r.enUS: fieldIsAbstract
	 * r: champEstNatif
	 * r.enUS: fieldIsNative
	 * r: annotationsLangue
	 * r.enUS: annotationsLanguage
	 * r: annotations
	 * r.enUS: annotations
	 * r: champEstTest
	 * r.enUS: fieldIsTest
	 * r: champEstSubstitue
	 * r.enUS: fieldIsOverride
	 * r: champAnnotationLangue
	 * r.enUS: fieldAnnotationLanguage
	 * r: champClassePartsLangue
	 * r.enUS: fieldClassPartsLanguage
	 * r: champClasseParts
	 * r.enUS: fieldClassParts
	 * r: champVarLangue
	 * r.enUS: fieldVarLanguage
	 * r: champVar
	 * r.enUS: fieldVar
	 * r: champNomCanoniqueComplet
	 * r.enUS: fieldCanonicalNameComplete
	 * r: champNomSimpleComplet
	 * r.enUS: fieldSimpleNameComplete
	 * r: stockerListeSolr
	 * r.enUS: storeListSolr
	 * r: constructeurDoc
	 * r.enUS: constructorDoc
	 * r: constructeurQdox
	 * r.enUS: constructorQdox
	 * r: constructeurCommentaire
	 * r.enUS: constructorComment
	 * r: constructeurCle
	 * r.enUS: constructorKey
	 * r: constructeurCodeSourceLangue
	 * r.enUS: constructorSourceCodeLanguage
	 * r: constructeurCodeSource
	 * r.enUS: constructorSourceCode
	 * r: constructeurEstPublic
	 * r.enUS: constructorIsPublic
	 * r: constructeurEstProtege
	 * r.enUS: constructorIsProtected
	 * r: constructeurEstPrive
	 * r.enUS: constructorIsPrivate
	 * r: constructeurEstStatique
	 * r.enUS: constructorIsStatic
	 * r: constructeurEstFinale
	 * r.enUS: constructorIsFinal
	 * r: constructeurEstAbstrait
	 * r.enUS: constructorIsAbstract
	 * r: constructeurEstNatif
	 * r.enUS: constructorIsNative
	 * r: constructeurAnnotations
	 * r.enUS: constructorAnnotations
	 * r: constructeurAnnotationLangue
	 * r.enUS: constructorAnnotationLanguage
	 * r: constructeurParamsQdox
	 * r.enUS: constructorParamsQdox
	 * r: constructeurExceptionsQdox
	 * r.enUS: constructorExceptionsQdox
	 * r: constructeurParamNum
	 * r.enUS: constructorParamNum
	 * r: constructeurParamQdox
	 * r.enUS: constructorParamQdox
	 * r: constructeurParamVarLangue
	 * r.enUS: constructorParamVarLanguage
	 * r: constructeurParamVarLangue
	 * r.enUS: constructorParamVar
	 * r: constructeurParamClassePartsLangue
	 * r.enUS: constructorParamClassPartsLanguage
	 * r: constructeurParamNomSimpleComplet
	 * r.enUS: constructorParamSimpleNameComplete
	 * r: constructeurParamVar
	 * r.enUS: constructeurParamVar
	 * r: constructeurParamArgsVariable
	 * r.enUS: constructorParamVariableArgs
	 * r: constructeurAnnotationBlocCode
	 * r.enUS: constructorAnnotationCodeBlock
	 * r: constructeurExceptionQdox
	 * r.enUS: constructorExceptionQdox
	 * r: constructeurExceptionNomSimpleComplet
	 * r.enUS: constructorExceptionSimpleNameComplete
	 * r: constructeurAnnotation
	 * r.enUS: constructorAnnotation
	 * r: partEstConstructeur
	 * r.enUS: partIsConstructor
	 * r: methodeVarLangue
	 * r.enUS: methodVarLanguage
	 * r: methodeVar
	 * r.enUS: methodVar
	 * r: methodeDoc
	 * r.enUS: methodDoc
	 * r: methodeQdox
	 * r.enUS: methodQdox
	 * r: methodeCommentaire
	 * r.enUS: methodComment
	 * r: methodeCle
	 * r.enUS: methodKey
	 * r: methodeCodeSourceLangue
	 * r.enUS: methodSourceCodeLanguage
	 * r: methodeCodeSource
	 * r.enUS: methodSourceCode
	 * r: methodeEstPublic
	 * r.enUS: methodIsPublic
	 * r: methodeEstProtege
	 * r.enUS: methodIsProtected
	 * r: methodeEstPrive
	 * r.enUS: methodIsPrivate
	 * r: methodeEstStatique
	 * r.enUS: methodIsStatic
	 * r: methodeEstFinale
	 * r.enUS: methodIsFinal
	 * r: methodeEstAbstrait
	 * r.enUS: methodIsAbstract
	 * r: methodeEstNatif
	 * r.enUS: methodIsNative
	 * r: methodeAnnotations
	 * r.enUS: methodAnnotations
	 * r: methodeAnnotationLangue
	 * r.enUS: methodAnnotationLanguage
	 * r: methodeParamsQdox
	 * r.enUS: methodParamsQdox
	 * r: methodeExceptionsQdox
	 * r.enUS: methodExceptionsQdox
	 * r: methodeParamNum
	 * r.enUS: methodParamNum
	 * r: methodeParamQdox
	 * r.enUS: methodParamQdox
	 * r: methodeParamVarLangue
	 * r.enUS: methodParamVarLanguage
	 * r: methodeParamVar
	 * r.enUS: methodParamVar
	 * r: methodeParamClassePartsLangue
	 * r.enUS: methodParamClassPartsLanguage
	 * r: methodeParamNomSimpleComplet
	 * r.enUS: methodParamSimpleNameComplete
	 * r: methodeParamVar
	 * r.enUS: methodeParamVar
	 * r: methodeParamArgsVariable
	 * r.enUS: methodParamVariableArgs
	 * r: methodeAnnotationBlocCode
	 * r.enUS: methodAnnotationCodeBlock
	 * r: methodeExceptionQdox
	 * r.enUS: methodExceptionQdox
	 * r: methodeExceptionNomSimpleComplet
	 * r.enUS: methodExceptionSimpleNameComplete
	 * r: methodeAnnotation
	 * r.enUS: methodAnnotation
	 * r: partEstMethode
	 * r.enUS: partIsMethod
	 * r: methodeRetourNomSimpleComplet
	 * r.enUS: methodReturnSimpleNameComplete
	 * r: methodeRetourClassePartsLangue
	 * r.enUS: methodReturnClassPartsLanguage
	 * r: methodeRetourClasseParts
	 * r.enUS: methodReturnClassParts
	 * r: methodeEstVide
	 * r.enUS: methodIsVoid
	 * r: methodeNomSimpleRetourComplet
	 * r.enUS: methodReturnSimpleNameComplete
	 * r: methodeEstTest
	 * r.enUS: methodIsTest
	 * r: methodeEstSubstitue
	 * r.enUS: methodIsOverride
	 * r: regexCle
	 * r.enUS: regexKey
	 * r: regexValeur
	 * r.enUS: regexValue
	 * r: qSupprimer
	 * r.enUS: qDelete
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: nomCanoniqueGenerique
	 * r.enUS: canonicalNameGeneric
	 * r: nomCanoniqueComplet
	 * r.enUS: canonicalNameComplete
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: nomSimpleGenerique
	 * r.enUS: simpleNameGeneric
	 * r: nomSimpleComplet
	 * r.enUS: simpleNameComplete
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: regexListe
	 * r.enUS: regexList
	 * r: remplacerClesLangue
	 * r.enUS: replaceKeysLanguage
	 * r: remplacerValeursLangue
	 * r.enUS: replaceValuesLanguage
	 * r: annotationsLangue
	 * r.enUS: annotationsLanguage
	 * r: methodeParametreTypeNoms
	 * r.enUS: methodTypeParameterNames
	 * r: classeSuperParametreTypeNoms
	 * r.enUS: classSuperTypeParameterNames
	 * r: classeParametreTypeNoms
	 * r.enUS: classTypeParameterNames
	 * r: classeParametresType
	 * r.enUS: classTypeParameters
	 * r: classeParametreType
	 * r.enUS: classTypeParameter
	 * r: methodeParametreTypeNoms
	 * r.enUS: methodTypeParameterNames
	 * r: methodeParametresType
	 * r.enUS: methodTypeParameters
	 * r: methodeParametreType
	 * r.enUS: methodTypeParameter
	 * r: classePartsSuperLangue
	 * r.enUS: classSuperPartsLanguage
	 * r: regexTrouve
	 * r.enUS: regexFound
	 */
	protected SolrInputDocument indexerClasse(String classeCheminAbsolu) throws Exception { 

		SolrInputDocument classeDoc = new SolrInputDocument();
		String classeNomCanonique = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classeCheminAbsolu, "."), cheminSrcMainJava + "/"), "/", ".");
		String classeNomSimple = StringUtils.substringAfterLast(classeNomCanonique, ".");
		String classeNomCanoniqueGen = classeNomCanonique + "Gen";
		String classeNomSimpleGen = classeNomSimple + "Gen";
		String classeNomCanoniqueApiGen = classeNomCanonique + "ApiGen";
		String classeNomSimpleApi = indexerStockerSolr(classeDoc, "classeNomSimpleApi", langueNom, classeNomSimple + "Api");
		String classeNomSimpleApiGen = indexerStockerSolr(classeDoc, "classeNomSimpleApiGen", langueNom, classeNomSimple + "ApiGen");
		String classeNomCanoniquePageGen = classeNomCanonique + "PageGen";
		String classeNomSimplePage = indexerStockerSolr(classeDoc, "classeNomSimplePage", langueNom, classeNomSimple + "Page");
		String classeNomSimplePageGen = indexerStockerSolr(classeDoc, "classeNomSimplePageGen", langueNom, classeNomSimple + "PageGen");
		JavaClass classeQdox = bricoleur.getClassByName(classeNomCanonique.toString());
		JavaClass classeQdoxSuper = classeQdox.getSuperJavaClass();
		JavaClass classeQdoxString = bricoleur.getClassByName(String.class.getCanonicalName());
		String classeNomCanoniqueSuper = classeQdoxSuper.getCanonicalName();
		String classeNomSimpleSuper = StringUtils.substringAfterLast(classeNomCanoniqueSuper, ".");
		if(StringUtils.isEmpty(classeNomSimpleSuper))
			classeNomSimpleSuper = classeNomCanoniqueSuper;

		List<JavaTypeVariable<JavaGenericDeclaration>> classeParametresType = classeQdox.getTypeParameters();
		for(JavaTypeVariable<JavaGenericDeclaration> classeParametreType : classeParametresType) {
			String classeParametreTypeNom = classeParametreType.getName();
			stockerListeSolr(classeDoc, "classeParametreTypeNoms", classeParametreTypeNom);
		}

		if(classeQdoxSuper instanceof DefaultJavaParameterizedType) {
			DefaultJavaParameterizedType typeSuper = (DefaultJavaParameterizedType)classeQdoxSuper;
			List<JavaType> classeSuperParametresType = typeSuper.getActualTypeArguments();
			for(JavaType classeSuperParametreType : classeSuperParametresType) {
				String classeSuperParametreTypeNom = classeSuperParametreType.getValue();
				stockerListeSolr(classeDoc, "classeSuperParametreTypeNoms", classeSuperParametreTypeNom);
			}
		}
//		classeParametresType.get(0).getGenericFullyQualifiedName(); // returns <DEV>
//		classeQdox.getSuperClass().getGenericCanonicalName(); // returns CouvertureGen<DEV>
		
		String classeNomCompletSuper = indexerStockerSolr(classeDoc, "classeNomCompletSuper", langueNom, classeQdoxSuper.getGenericCanonicalName());
		String classeNomCompletSuperGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(classeNomCompletSuper, "<"), ">");
		String classeNomCanoniqueSuperGenerique = null;
		String classeNomSimpleSuperGenerique = null;
		JavaClass classeSuperGeneriqueQdox = null;
		Boolean classeBaseEtendGen = false;
		if(StringUtils.isNotEmpty(classeNomCompletSuper)) {
			indexerStockerSolr(classeDoc, "classeNomCompletSuperGenerique", langueNom, classeNomCompletSuperGenerique);
			if(classeNomCompletSuper.contains("<")) {
				classeNomCanoniqueSuperGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(classeNomCompletSuper, ">"), "<");
				classeNomCanoniqueSuperGenerique = classeNomCanoniqueSuperGenerique.contains(",") ? StringUtils.substringBefore(classeNomCanoniqueSuperGenerique, ",") : classeNomCanoniqueSuperGenerique;
				indexerStockerSolr(classeDoc, "classeNomCanoniqueSuperGenerique", langueNom, classeNomCanoniqueSuperGenerique);
				classeSuperGeneriqueQdox = bricoleur.getClassByName(classeNomCanoniqueSuperGenerique);
				classeNomCompletSuperGenerique = classeNomCanoniqueSuperGenerique;

				if(classeNomCanoniqueSuperGenerique.contains("."))
					classeNomSimpleSuperGenerique = StringUtils.substringAfterLast(classeNomCanoniqueSuperGenerique, ".");
				else
					classeNomSimpleSuperGenerique = classeNomCanoniqueSuperGenerique;
				indexerStockerSolr(classeDoc, "classeNomSimpleSuperGenerique", langueNom, classeNomSimpleSuperGenerique);

				ClasseParts classePartsBase = ClasseParts.initClasseParts(this, classeNomCanoniqueSuperGenerique, langueNom);
				classeBaseEtendGen = classePartsBase.etendGen;
				if(classeBaseEtendGen == null)
					classeBaseEtendGen = false;
			}
		}
		Boolean classeEstBase = stockerSolr(classeDoc, "classeEstBase", !classeBaseEtendGen || StringUtils.isEmpty(classeNomCompletSuperGenerique) || StringUtils.equals(classeNomCompletSuperGenerique, "java.lang.Object"));
		Boolean classeEtendBase = stockerSolr(classeDoc, "classeEtendBase", !classeEstBase && classeBaseEtendGen && !StringUtils.equals(classeNomCompletSuperGenerique, "java.lang.Object"));
		indexerStockerSolr(classeDoc, "classeBaseEtendGen", classeBaseEtendGen);
		Boolean classeContientRequeteSite = indexerStockerSolr(classeDoc, "classeContientRequeteSite", classeQdox.getMethodBySignature("getRequeteSite", new ArrayList<JavaType>(), true) != null);
		
		String classeCommentaire = stockerRegexCommentaires(classeQdox.getComment(), langueNom, classeDoc, "classeCommentaire");
		String classeNomEnsemble = StringUtils.substringBeforeLast(classeNomCanonique, ".");
		String classeChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), ".java");
		String classeCheminRepertoire = StringUtils.substringBeforeLast(classeChemin, "/");
		String classeCheminGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Gen.java");
		String classeCheminApiGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "ApiGen.java");
		String classeCheminPageGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "PageGen.java");
		String classeCheminRepertoireGen = StringUtils.substringBeforeLast(classeCheminGen, "/");
		String classeCle = classeCheminAbsolu;
		Instant modifiee = Instant.now();
		Date modifieeDate = Date.from(modifiee);
		Boolean classeContientCouverture = false;

		Boolean classeEtendGen = StringUtils.endsWith(classeNomSimpleSuper, "Gen");
		ClasseParts classePartsRequeteSite = classePartsRequeteSite(nomEnsembleDomaine);
		if(!classeEtendGen && regexTrouve("^gen:\\s*(true)$", classeCommentaire)) {
			classeEtendGen = true;
		}
		Boolean classeModele = stockerSolr(classeDoc, "classeModele", regexTrouve("^modele: \\s*(true)$", classeCommentaire));
		Boolean classeApi = stockerSolr(classeDoc, "classeApi", regexTrouve("^api: \\s*(true)$", classeCommentaire) || classeModele);
		Boolean classePage = stockerSolr(classeDoc, "classePage", regexTrouve("^page: \\s*(true)$", classeCommentaire) || classeModele);
		Boolean classeInitLoin = !regexTrouve("^initLoin:\\s*(false)$", classeCommentaire);
		if(classeInitLoin)
			classeInitLoin = classeEtendBase || classeEstBase;
		classeInitLoin = stockerSolr(classeDoc, "classeInitLoin", classeInitLoin);
		if(classeInitLoin)
			classePartsGenAjouter(classePartsRequeteSite);
		indexerStockerSolr(classeDoc, "classeEtendGen", classeEtendGen);
		Boolean classeSauvegarde = indexerStockerSolr(classeDoc, "classeSauvegarde", regexTrouve("^sauvegarde:\\s*(true)$", classeCommentaire));
		Boolean classeIndexe = indexerStockerSolr(classeDoc, "classeIndexe", regexTrouve("^indexe:\\s*(true)$", classeCommentaire) || classeSauvegarde || classeModele);

		ClasseParts classePartsSolrInputDocument = ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrInputDocument", langueNom);
		ClasseParts classePartsSolrClient = ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrClient", langueNom);
		ClasseParts classePartsTest = ClasseParts.initClasseParts(this, "org.junit.Test", langueNom);
		ClasseParts classePartsEcouteurContexte = classePartsEcouteurContexte(nomEnsembleDomaine);
		ClasseParts classePartsConfigSite = classePartsConfigSite(nomEnsembleDomaine);
		ClasseParts classePartsUtilisateurSite = classePartsUtilisateurSite(nomEnsembleDomaine);
		ClasseParts classePartsCluster = classePartsCluster(nomEnsembleDomaine);
		ClasseParts classePartsResultatRecherche = classePartsResultatRecherche(nomEnsembleDomaine);

		if(classePage) {
			classePartsGenPageAjouter(classePartsConfigSite);
			classePartsGenPageAjouter(classePartsRequeteSite);
			classePartsGenPageAjouter(classePartsEcouteurContexte);
			classePartsGenPageAjouter(classePartsUtilisateurSite);
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.IOException", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.servlet.http.HttpServlet", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpServerRequest", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpServerResponse", langueNom));
		}

		if(classeApi) {
			classePartsGenApiAjouter(classePartsConfigSite);
			classePartsGenApiAjouter(classePartsRequeteSite);
			classePartsGenApiAjouter(classePartsEcouteurContexte);
			classePartsGenApiAjouter(classePartsUtilisateurSite);
			classePartsGenApiAjouter(classePartsResultatRecherche);
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.IOException", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.servlet.http.HttpServlet", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.servlet.http.HttpServerRequest", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.servlet.http.HttpServerResponse", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Collections", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Map", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.servlet.ServletException", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.concurrent.TimeUnit", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.stream.Collectors", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.Json", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.json.JsonArray", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.json.JsonObject", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.json.JsonReader", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrQuery", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrQuery.ORDER", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.response.QueryResponse", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.util.ClientUtils", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.StringUtils", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.keycloak.KeycloakPrincipal", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.keycloak.KeycloakSecurityContext", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.keycloak.representations.AccessToken", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.keycloak.representations.AccessToken.Access", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.security.Principal", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.Message", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.Session", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.Transport", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.internet.InternetAddress", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.internet.MimeMessage", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.PrintWriter", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrDocumentList", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrDocument", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Collection", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.math.BigDecimal", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Date", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.List", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Handler", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.RoutingContext", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.Router", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Vertx", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.reactivestreams.ReactiveReadStream", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.reactivestreams.ReactiveWriteStream", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.MultiMap", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.oauth2.OAuth2Auth", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.netty.handler.codec.http.HttpResponseStatus", langueNom));
		}
		if(classeIndexe) {
			classePartsGenAjouter(classePartsSolrInputDocument);
			classePartsGenAjouter(classePartsSolrClient);
//			classePartsGenAjouter(classePartsTest);
			classePartsGenAjouter(classePartsEcouteurContexte);
		}
		if(classeEtendBase || classeEstBase) {
			classePartsGenAjouter(classePartsCluster);
		}

		
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
		indexerStockerSolr(classeDoc, "classePageUri", langueNom, regex("^pageUri\\." + langueNom + ":\\s*(.*)", classeCommentaire));
		indexerStockerSolr(classeDoc, "classeApiUri", langueNom, regex("^apiUri\\." + langueNom + ":\\s*(.*)", classeCommentaire));
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
		indexerStockerSolr(classeDoc, "classeCheminApiGen", langueNom, classeCheminApiGen); 
		indexerStockerSolr(classeDoc, "classeCheminPageGen", langueNom, classeCheminPageGen); 
		indexerStockerSolr(classeDoc, "classeCheminRepertoireGen", langueNom, classeCheminRepertoireGen); 
		indexerStockerSolr(classeDoc, "nomEnsembleDomaine", nomEnsembleDomaine); 

		if(classeCommentaire != null) {
			Matcher classeValsRecherche = Pattern.compile("^val\\.(\\w+)\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeValsTrouve = classeValsRecherche.find();
			while(classeValsTrouve) {
				String classeValVar = classeValsRecherche.group(1);
				String classeValLangue = classeValsRecherche.group(2);
				String classeValValeur = classeValsRecherche.group(3);
				stockerListeSolr(classeDoc, "classeValsVar", classeValVar);
				stockerListeSolr(classeDoc, "classeValsLangue", classeValLangue);
				stockerListeSolr(classeDoc, "classeValsValeur", classeValValeur);
				classeValsTrouve = classeValsRecherche.find();
			}
		}

		SolrDocument classeNomCanoniqueSuperDoc = null;   
		if(StringUtils.startsWith(classeNomCanoniqueSuper, nomEnsembleDomaine)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + ClientUtils.escapeQueryChars(classeNomCanoniqueSuper));
			rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
			rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
			QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) { 
				classeNomCanoniqueSuperDoc = listeRecherche.get(0);
			}
		}  

		if(classeEtendGen) {
			ClasseParts classePartsSuperGenerique = ClasseParts.initClasseParts(this, classeNomCompletSuperGenerique, langueNom);
			classePartsGenAjouter(classePartsSuperGenerique);
		}

		for(String langueNom : autresLangues) { 
			String appliCheminLangue = appliChemins.get(langueNom);
			stockerRegexCommentaires(classeCommentaire, langueNom, classeDoc, "classeCommentaire");
			String cheminSrcMainJavaLangue = appliCheminLangue + "/src/main/java";
			String cheminSrcGenJavaLangue = appliCheminLangue + "/src/gen/java";
			String classeNomCanoniqueLangue = regex("^nomCanonique\\." + langueNom + ":\\s*(.*)", classeCommentaire, classeNomCanonique);
			String classePageUriLangue = indexerStockerSolr(classeDoc, "classePageUri", langueNom, regex("^pageUri\\." + langueNom + ":\\s*(.*)", classeCommentaire));
			String classeApiUriLangue = indexerStockerSolr(classeDoc, "classeApiUri", langueNom, regex("^apiUri\\." + langueNom + ":\\s*(.*)", classeCommentaire));
			String classeNomSimpleLangue = StringUtils.substringAfterLast(classeNomCanoniqueLangue, ".");
			String classeNomEnsembleLangue = StringUtils.substringBeforeLast(classeNomCanoniqueLangue, ".");
			String classeNomCanoniqueGenLangue = classeNomCanoniqueLangue + "Gen";
			String classeNomCanoniqueApiLangue = classeNomCanoniqueLangue + "Api";
			String classeNomCanoniqueApiGenLangue = classeNomCanoniqueLangue + "ApiGen";
			String classeNomCanoniquePageLangue = classeNomCanoniqueLangue + "Page";
			String classeNomCanoniquePageGenLangue = classeNomCanoniqueLangue + "PageGen";
			String classeNomSimpleGenLangue = classeNomSimpleLangue + "Gen";
			String classeNomSimpleApiLangue = classeNomSimpleLangue + "Api";
			String classeNomSimplePageLangue = classeNomSimpleLangue + "Page";
			String classeNomSimpleApiGenLangue = classeNomSimpleLangue + "ApiGen";
			String classeNomSimplePageGenLangue = classeNomSimpleLangue + "PageGen";
			String classeCheminLangue = indexerStockerSolr(classeDoc, "classeChemin", langueNom, concat(cheminSrcMainJavaLangue, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java"));
			String classeCheminRepertoireLangue = stockerSolr(classeDoc, "classeCheminRepertoire", langueNom, StringUtils.substringBeforeLast(classeCheminLangue, "/"));
			String classeCheminGenLangue = indexerStockerSolr(classeDoc, "classeCheminGen", langueNom, concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniqueGenLangue, ".", "/"), ".java"));
			String classeCheminApiGenLangue = indexerStockerSolr(classeDoc, "classeCheminApiGen", langueNom, concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniqueApiGenLangue, ".", "/"), ".java"));
			String classeCheminPageGenLangue = indexerStockerSolr(classeDoc, "classeCheminPageGen", langueNom, concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniquePageGenLangue, ".", "/"), ".java"));
			String classeCheminRepertoireGenLangue = stockerSolr(classeDoc, "classeCheminRepertoireGen", langueNom, StringUtils.substringBeforeLast(classeCheminGenLangue, "/"));

			indexerStockerSolr(classeDoc, "classeNomCanonique", langueNom, classeNomCanoniqueLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimple", langueNom, classeNomSimpleLangue); 
			indexerStockerSolr(classeDoc, "classeNomCanoniqueGen", langueNom, classeNomCanoniqueGenLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimpleGen", langueNom, classeNomSimpleGenLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimpleApi", langueNom, classeNomSimpleApiLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimplePage", langueNom, classeNomSimplePageLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimpleApiGen", langueNom, classeNomSimpleApiGenLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimplePageGen", langueNom, classeNomSimplePageGenLangue); 
			indexerStockerSolr(classeDoc, "classeNomEnsemble", langueNom, classeNomEnsembleLangue); 

			String classeNomCompletSuperLangue;
			ClasseParts classePartsSuperLangue;

			if(classeEtendGen) {
				classePartsSuperLangue = ClasseParts.initClasseParts(this, classeNomCanoniqueLangue + "Gen", langueNom);
			}
			else {
				classePartsSuperLangue = ClasseParts.initClasseParts(this, classeQdoxSuper, langueNom);
			}

			indexerStockerSolr(classeDoc, "classeNomCanoniqueSuper", langueNom, classePartsSuperLangue.nomCanonique); 
			indexerStockerSolr(classeDoc, "classeNomSimpleSuper", langueNom, classePartsSuperLangue.nomSimple); 
			indexerStockerSolr(classeDoc, "classeNomCanoniqueCompletSuper", langueNom, classePartsSuperLangue.nomCanoniqueComplet);
			indexerStockerSolr(classeDoc, "classeNomSimpleCompletSuper", langueNom, classePartsSuperLangue.nomSimpleComplet);
			if(StringUtils.isNotEmpty(classeNomCompletSuperGenerique)) {
				ClasseParts classePartsSuperGeneriqueLangue = ClasseParts.initClasseParts(this, classeNomCompletSuperGenerique, langueNom);
				indexerStockerSolr(classeDoc, "classeNomCanoniqueSuperGenerique", langueNom, classePartsSuperGeneriqueLangue.nomCanoniqueComplet);
				indexerStockerSolr(classeDoc, "classeNomSimpleSuperGenerique", langueNom, classePartsSuperGeneriqueLangue.nomSimpleComplet);
				if(classeEtendGen) {
					classePartsGenAjouter(classePartsSuperGeneriqueLangue);
				}
			}





//			if(classeNomCanoniqueSuperDoc == null) {  
//				indexerStockerSolr(classeDoc, "classeNomCanoniqueSuper", langueNom, classeNomCanoniqueSuper); 
//				indexerStockerSolr(classeDoc, "classeNomSimpleSuper", langueNom, classeNomSimpleSuper); 
//			}
//			else {
//				indexerStockerSolr(classeDoc, "classeNomCanoniqueSuper", langueNom, (String)classeNomCanoniqueSuperDoc.get("classeNomCanonique_" + langueNom + "_stored_string"));
//				indexerStockerSolr(classeDoc, "classeNomSimpleSuper", langueNom, (String)classeNomCanoniqueSuperDoc.get("classeNomSimple_" + langueNom + "_stored_string"));
//			}
//			String classeNomCompletSuperLangue = rechercherNomCanonique(langueNom, classeNomCompletSuper);
//			indexerStockerSolr(classeDoc, "classeNomCompletSuper", langueNom, classeNomCompletSuperLangue);
//			if(StringUtils.isNotEmpty(classeNomCompletSuperGenerique)) {
//				String classeNomCompletSuperGeneriqueLangue = indexerStockerSolr(classeDoc, "classeNomCompletSuperGenerique", langueNom, rechercherNomCanonique(langueNom, classeNomCompletSuperGenerique));
//				String classeNomCanoniqueSuperGeneriqueLangue = indexerStockerSolr(classeDoc, "classeNomCanoniqueSuperGenerique", langueNom, rechercherNomCanonique(langueNom, classeNomCanoniqueSuperGenerique));
//				String classeNomSimpleSuperGeneriqueLangue = indexerStockerSolr(classeDoc, "classeNomSimpleSuperGenerique", langueNom, rechercherNomCanonique(langueNom, classeNomSimpleSuperGenerique));
//			}
		} 

		SolrInputDocument classeDocClone = classeDoc.deepCopy();
		Integer partNumero = 1;

		classeDoc.addField("cle", classeCle);  

		indexerStockerSolr(classeDoc, "partEstClasse", true);
		indexerStockerSolr(classeDoc, "partNumero", partNumero);
		
		for(String classeImportation : classeQdox.getSource().getImports()) {
			ClasseParts classeImportationClasseParts = ClasseParts.initClasseParts(this, classeImportation, langueNom);
			indexerStockerListeSolr(classeDoc, "classeImportations", langueNom, classeImportationClasseParts.nomCanonique);
			for(String langueNom : autresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classeImportationClasseParts, langueNom);
				indexerStockerListeSolr(classeDoc, "classeImportations", langueNom, classeImportationClassePartsLangue.nomCanonique);
			}
		}

		List<JavaMember> membresQdox = new ArrayList<JavaMember>();
		membresQdox.addAll(classeQdox.getFields());
		membresQdox.addAll(classeQdox.getConstructors());
		membresQdox.addAll(classeQdox.getMethods());
		for(JavaMember membreQdox : membresQdox) {  
			partNumero++;

			if(membreQdox instanceof JavaField) {    
				SolrInputDocument champDoc = classeDocClone.deepCopy();
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
	
				/////////////////
				// Annotations //
				/////////////////
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

				for(String langueNom : autresLangues) { 
					ClasseParts champClassePartsLangue = ClasseParts.initClasseParts(this, champClasseParts, langueNom);
					String champVarLangue = regex("^var\\." + langueNom + ": (.*)", champCommentaire);
					champVarLangue = champVarLangue == null ? champVar : champVarLangue;
					String champCodeSourceLangue = regexRemplacerTout(champCommentaire, champCodeSource, langueNom);

					indexerStockerSolr(champDoc, "champVar", langueNom, champVarLangue); 
					stockerSolr(champDoc, "champNomSimpleComplet", langueNom, champClassePartsLangue.nomSimpleComplet);
					stockerSolr(champDoc, "champNomCanoniqueComplet", langueNom, champClassePartsLangue.nomCanoniqueComplet);
					stockerRegexCommentaires(champCommentaire, langueNom, champDoc, "champCommentaire");
					stockerSolr(champDoc, "champCodeSource", langueNom, champCodeSourceLangue);
				}  

				clientSolrComputate.add(champDoc); 
			}
			else if(membreQdox instanceof JavaConstructor) { 
				SolrInputDocument constructeurDoc = classeDocClone.deepCopy();
				JavaConstructor constructeurQdox = (JavaConstructor)membreQdox;
				String constructeurCommentaire = constructeurQdox.getComment();
				List<JavaParameter> constructeurParamsQdox = constructeurQdox.getParameters();
				List<JavaAnnotation> constructeurAnnotations = constructeurQdox.getAnnotations(); 
				List<JavaClass> constructeurExceptionsQdox = constructeurQdox.getExceptions();
				for(Integer constructeurParamNum = 1; constructeurParamNum <= constructeurParamsQdox.size(); constructeurParamNum++) {
					JavaParameter constructeurParamQdox = constructeurParamsQdox.get(constructeurParamNum - 1);
					String constructeurParamVar = constructeurParamQdox.getName();
					stockerListeSolr(constructeurDoc, "constructeurParamVar", langueNom, constructeurParamVar);
					ClasseParts constructeurParamClasseParts = ClasseParts.initClasseParts(this, constructeurParamQdox.getJavaClass(), langueNom);
					stockerListeSolr(constructeurDoc, "constructeurParamNomSimpleComplet", langueNom, constructeurParamClasseParts.nomSimpleComplet);
					stockerListeSolr(constructeurDoc, "constructeurParamArgsVariable", constructeurParamQdox.isVarArgs());
					for(String langueNom : autresLangues) { 
						String constructeurParamVarLangue = regex("param" + constructeurParamNum + "\\.var\\." + langueNom + ": (.*)", constructeurCommentaire);
						if(constructeurParamVarLangue == null)
							constructeurParamVarLangue = constructeurParamVar;
						ClasseParts constructeurParamClassePartsLangue = ClasseParts.initClasseParts(this, constructeurParamClasseParts, langueNom);

						stockerListeSolr(constructeurDoc, "constructeurParamNomSimpleComplet", langueNom, constructeurParamClassePartsLangue.nomSimpleComplet);
						stockerListeSolr(constructeurDoc, "constructeurParamVar", langueNom, constructeurParamVarLangue);
					}  
				}
				for(JavaAnnotation constructeurAnnotation : constructeurAnnotations) {
//					String constructeurAnnotationBlocCode = stockerListeSolr(constructeurDoc, "constructeurAnnotationBlocCode", langueNom, annotation.toString());
				}
				for(JavaClass constructeurExceptionQdox : constructeurExceptionsQdox) {
					String constructeurExceptionNomSimpleComplet = StringUtils.substringAfterLast(constructeurExceptionQdox.getCanonicalName(), ".");
					stockerListeSolr(constructeurDoc, "constructeurExceptionNomSimpleComplet", constructeurExceptionNomSimpleComplet);
				}
				String constructeurCle = classeCheminAbsolu + "." + classeNomSimple + "(";

				for(int i = 0; i < constructeurParamsQdox.size(); i++) {
					JavaParameter paramQdox = constructeurParamsQdox.get(i);
					if(i > 0)
						constructeurCle += ", ";
					constructeurCle += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
				}
				constructeurCle += ")"; 

				constructeurDoc.addField("cle", constructeurCle);
				indexerStockerSolr(constructeurDoc, "partEstConstructeur", true);
				indexerStockerSolr(constructeurDoc, "partNumero", partNumero);

				indexerStockerSolr(constructeurDoc, "constructeurEstPublic", constructeurQdox.isPublic());
				indexerStockerSolr(constructeurDoc, "constructeurEstProtege", constructeurQdox.isProtected());
				indexerStockerSolr(constructeurDoc, "constructeurEstPrive", constructeurQdox.isPrivate());
				indexerStockerSolr(constructeurDoc, "constructeurEstStatique", constructeurQdox.isStatic());
				indexerStockerSolr(constructeurDoc, "constructeurEstFinale", constructeurQdox.isFinal());
				indexerStockerSolr(constructeurDoc, "constructeurEstAbstrait", constructeurQdox.isAbstract());
				indexerStockerSolr(constructeurDoc, "constructeurEstNatif", constructeurQdox.isNative());
				stockerRegexCommentaires(constructeurCommentaire, langueNom, constructeurDoc, "constructeurCommentaire");

				String constructeurCodeSource = constructeurQdox.getSourceCode();
				String constructeurCodeSourceLangue = constructeurCodeSource;
				ArrayList<String> remplacerClesLangue = regexListe("^r." + langueNom + "\\s*=\\s*(.*)\\n.*", constructeurCommentaire);
				ArrayList<String> remplacerValeursLangue = regexListe("^r." + langueNom + "\\s*=\\s*.*\\n(.*)", constructeurCommentaire);
				for(int i = 0; i < remplacerClesLangue.size(); i++) {
					String cle = remplacerClesLangue.get(i);
					String valeur = remplacerValeursLangue.get(i);
					StringUtils.replace(constructeurCodeSourceLangue, cle, valeur);
				}
				stockerSolr(constructeurDoc, "constructeurCodeSource", langueNom, constructeurCodeSourceLangue);

				for(String langueNom : autresLangues) {  
					constructeurCodeSourceLangue = regexRemplacerTout(constructeurCommentaire, constructeurCodeSource, langueNom);
					stockerSolr(constructeurDoc, "constructeurCodeSource", langueNom, constructeurCodeSourceLangue);
					stockerRegexCommentaires(constructeurCommentaire, langueNom, constructeurDoc, "constructeurCommentaire");
				} 

				clientSolrComputate.add(constructeurDoc); 
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
						SolrInputDocument entiteDoc = classeDocClone.deepCopy();
						String entiteVar = indexerStockerSolr(entiteDoc, "entiteVar", langueNom, StringUtils.substringAfter(methodeQdox.getName(), "_"));
						String entiteVarCapitalise = indexerStockerSolr(entiteDoc, "entiteVarCapitalise", langueNom, StringUtils.capitalize(entiteVar));
						JavaClass entiteClasseQdox = methodeParamsQdox.get(0).getJavaClass();
						ClasseParts entiteClasseParts = ClasseParts.initClasseParts(this, entiteClasseQdox, langueNom);
						Boolean entiteCouverture = false;

						if(entiteClasseParts.nomSimple.equals("Couverture")) {
							entiteClasseParts = ClasseParts.initClasseParts(this, entiteClasseParts.nomCanoniqueGenerique, entiteVar);
							entiteCouverture = true;
							classeContientCouverture = true;
						}

						classePartsGenAjouter(entiteClasseParts);
						if(StringUtils.isNotEmpty(entiteClasseParts.nomCanoniqueGenerique)) {
							ClasseParts classePartsGenerique = ClasseParts.initClasseParts(this, entiteClasseParts.nomCanoniqueGenerique, langueNom);
							classePartsGenAjouter(classePartsGenerique);
						}

						indexerStockerSolr(entiteDoc, "entiteCouverture", entiteCouverture);
						indexerStockerSolr(entiteDoc, "entiteInitialise", true);

						String entiteNomCanonique = indexerStockerSolr(entiteDoc, "entiteNomCanonique", langueNom, entiteClasseParts.nomCanonique);
						String entiteNomSimple = indexerStockerSolr(entiteDoc, "entiteNomSimple", langueNom, entiteClasseParts.nomSimple);
						String entiteNomCompletGenerique = indexerStockerSolr(entiteDoc, "entiteNomCompletGenerique", langueNom, entiteClasseParts.nomCanoniqueGenerique);
						String entiteNomCanoniqueGenerique = indexerStockerSolr(entiteDoc, "entiteNomCanoniqueGenerique", langueNom, entiteClasseParts.nomCanoniqueGenerique);
						String entiteNomSimpleGenerique = indexerStockerSolr(entiteDoc, "entiteNomSimpleGenerique", langueNom, entiteClasseParts.nomSimpleGenerique);
						indexerStockerSolr(entiteDoc, "entiteNomCanoniqueComplet", langueNom, entiteClasseParts.nomCanoniqueComplet);
						indexerStockerSolr(entiteDoc, "entiteNomSimpleComplet", langueNom, entiteClasseParts.nomSimpleComplet);
						indexerStockerSolr(entiteDoc, "entiteNomSimpleCompletGenerique", langueNom, entiteClasseParts.nomSimpleGenerique);

						JavaMethod entiteSetter = classeQdox.getMethodBySignature("set" + entiteVarCapitalise, new ArrayList<JavaType>() {{ add(classeQdoxString); }}, true);
						Boolean entiteDefinir = stockerSolr(entiteDoc, "entiteDefinir", 
								entiteNomCanonique.equals(VAL_nomCanoniqueString)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueBoolean)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueInteger)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueBigDecimal)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueDouble)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueFloat)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueLong)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueLocalDateTime)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueLocalDate)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueTimestamp)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueDate)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueString.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueBoolean.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueString.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueBoolean.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
								|| entiteSetter != null
								);
						
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
						if(entiteClasseParts.nomCanonique.equals(ArrayList.class.getCanonicalName()) || entiteClasseParts.nomCanonique.equals(List.class.getCanonicalName()))
							entiteVarParam = "l";
						else
							entiteVarParam = "o";
						indexerStockerSolr(entiteDoc, "entiteVarParam", langueNom, entiteVarParam);
						
						String entiteVarCouverture = indexerStockerSolr(entiteDoc, "entiteVarCouverture", langueNom, entiteVar + "Couverture");

						Boolean entiteInitLoin = indexerStockerSolr(entiteDoc, "entiteInitLoin", !entiteVar.endsWith("_") && BooleanUtils.isTrue(entiteClasseParts.etendGen));
						
//						String entiteParamVar = StringUtils.equalsAny(entiteClasseQdox, "");
//						indexerStockerSolr(entiteDoc, "entiteParamVar", regexTrouve("^exact:\\s*(true)$", methodeCommentaire));
//							if(nomCanonique.equals(classe_.nomCanoniqueArrayList) || nomCanonique.equals(classe_.nomCanoniqueList))
//								o.tout("l");
//							else if(o.estVide())
//								o.tout("o");

						List<JavaMethod> entiteMethodesAvant = new ArrayList<JavaMethod>();
						entiteMethodesAvant.add(classeQdox.getMethodBySignature(entiteVar + "Avant", new ArrayList<JavaType>() {{ add(entiteClasseQdox); }}, true));
						for(JavaClass c : classesSuperQdoxEtMoi) {
							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
							entiteMethodesAvant.add(classeQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
							entiteMethodesAvant.add(classeQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classeQdoxString); }}, true));
						}
						for(JavaMethod methode : entiteMethodesAvant) {
							if(methode != null) {
								JavaParameter param = methode.getParameters().get(0);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantVisibilite", methode.isPublic() ? "public" : "protected");
								stockerListeSolr(entiteDoc, "entiteMethodesAvantVar", methode.getName());
								stockerListeSolr(entiteDoc, "entiteMethodesAvantParamVar", param.getName());
								stockerListeSolr(entiteDoc, "entiteMethodesAvantParamNomSimple", StringUtils.substringAfterLast(param.getCanonicalName(), "."));
								stockerListeSolr(entiteDoc, "entiteMethodesAvantNomParam", methode.getParameters().size() > 1);
							}
						}

						List<JavaMethod> entiteMethodesApres = new ArrayList<JavaMethod>();
						entiteMethodesApres.add(classeQdox.getMethodBySignature(entiteVar + "Apres", new ArrayList<JavaType>() {{ add(entiteClasseQdox); }}, true));
						for(JavaClass c : classesSuperQdoxEtMoi) {
							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
							entiteMethodesApres.add(classeQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
							entiteMethodesApres.add(classeQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classeQdoxString); }}, true));
						}
						for(JavaMethod methode : entiteMethodesApres) {
							if(methode != null) {
								JavaParameter param = methode.getParameters().get(0);
								stockerListeSolr(entiteDoc, "entiteMethodesApresVar", methode.getName());
								stockerListeSolr(entiteDoc, "entiteMethodesApresParamVar", param.getName());
								stockerListeSolr(entiteDoc, "entiteMethodesApresParamNomSimple", StringUtils.substringAfterLast(param.getCanonicalName(), "."));
								stockerListeSolr(entiteDoc, "entiteMethodesApresNomParam", methode.getParameters().size() > 1);
							}
						}

						indexerStockerSolr(entiteDoc, "entiteExact", regexTrouve("^exact:\\s*(true)$", methodeCommentaire));
						Boolean entiteCleUnique = indexerStockerSolr(entiteDoc, "entiteCleUnique", regexTrouve("^cleUnique:\\s*(true)$", methodeCommentaire));
						Boolean entiteCrypte = indexerStockerSolr(entiteDoc, "entiteCrypte", regexTrouve("^crypte:\\s*(true)$", methodeCommentaire));
						Boolean entiteSuggere = indexerStockerSolr(entiteDoc, "entiteSuggere", regexTrouve("^suggere:\\s*(true)$", methodeCommentaire));
						Boolean entiteSauvegarde = indexerStockerSolr(entiteDoc, "entiteSauvegarde", regexTrouve("^sauvegarde:\\s*(true)$", methodeCommentaire));
						Boolean entiteIndexe = indexerStockerSolr(entiteDoc, "entiteIndexe", regexTrouve("^indexe:\\s*(true)$", methodeCommentaire));
						Boolean entiteIncremente = indexerStockerSolr(entiteDoc, "entiteIncremente", regexTrouve("^incremente:\\s*(true)$", methodeCommentaire));
						Boolean entiteStocke = indexerStockerSolr(entiteDoc, "entiteStocke", regexTrouve("^stocke:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteIndexeOuStocke", entiteCleUnique || entiteCrypte || entiteSuggere || entiteIndexe || entiteStocke || entiteIncremente);
						indexerStockerSolr(entiteDoc, "entitetexte", regexTrouve("^texte:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteNomAffichage", regexTrouve("^nomAffichage:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteIgnorer", regexTrouve("^ignorer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteDeclarer", regexTrouve("^declarer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRechercher", regexTrouve("^rechercher:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteAttribuer", regexTrouve("^attribuer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteAjouter", regexTrouve("^ajouter:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSupprimer", regexTrouve("^supprimer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteModifier", regexTrouve("^modifier:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRecharger", regexTrouve("^recharger:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteMultiligne", regexTrouve("^multiligne:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteCles", regexTrouve("^cles:\\s*(true)$", methodeCommentaire));

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

						String entiteBlocCode = methodeQdox.getCodeBlock();





						String entiteTypeSolr = null;
						String entiteSuffixeType = null;
						if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBoolean)) {
							entiteTypeSolr = VAL_nomCanoniqueBoolean;
							entiteSuffixeType = "_boolean";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueDate)) {
							entiteTypeSolr = VAL_nomCanoniqueDate;
							entiteSuffixeType = "_date";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
							entiteTypeSolr = VAL_nomCanoniqueLong;
							entiteSuffixeType = "_long";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
							entiteTypeSolr = VAL_nomCanoniqueDouble;
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueDouble)) {
							entiteTypeSolr = VAL_nomCanoniqueDouble;
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueFloat)) {
							entiteTypeSolr = VAL_nomCanoniqueFloat;
							entiteSuffixeType = "_float";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueInteger)) {
							entiteTypeSolr = VAL_nomCanoniqueInteger;
							entiteSuffixeType = "_int";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList)) {
							if(entiteNomCanoniqueGenerique.equals(VAL_nomCanoniqueBoolean)) {
								entiteTypeSolr = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueBoolean + ">";
								entiteSuffixeType = "_booleans";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate)) {
								entiteTypeSolr = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueDate + ">";
								entiteSuffixeType = "_dates";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteTypeSolr = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueLong + ">";
								entiteSuffixeType = "_longs";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
								entiteTypeSolr = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueBigDecimal + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
								entiteTypeSolr = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueDouble + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
								entiteTypeSolr = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueFloat + ">";
								entiteSuffixeType = "_floats";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
								entiteTypeSolr = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueInteger + ">";
								entiteSuffixeType = "_ints";
							}
							else {
								entiteSuffixeType = "_strings";
							}
						}
						else {
							entiteTypeSolr = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueString + ">";
							entiteSuffixeType = "_string";
////								if(videDernier)
////									suffixeType += "_videDernier";
						}
						stockerSolr(entiteDoc, "entiteTypeSolr", entiteTypeSolr);
						
						if(entiteCleUnique)
							stockerSolr(entiteDoc, "entiteVarCleUnique", entiteVar);
						if(entiteSuggere)
							stockerSolr(entiteDoc, "entiteVarSuggere", entiteVar + "_suggere");
						if(entiteIncremente)
							stockerSolr(entiteDoc, "entiteVarIncremente", entiteVar + "_incremente");
						if(entiteCrypte)
							stockerSolr(entiteDoc, "entiteVarCrypte", entiteVar + "_crypte");
						if(entiteIndexe)
							stockerSolr(entiteDoc, "entiteVarIndexe", entiteVar + "_indexe" + entiteSuffixeType);
						if(entiteStocke)
							stockerSolr(entiteDoc, "entiteVarStocke", entiteVar + "_stocke" + entiteSuffixeType);

						for(String langueNom : autresLangues) {  
							String entiteVarLangue = regex("^var\\." + langueNom + ": (.*)", methodeCommentaire);
							entiteVarLangue = indexerStockerSolr(entiteDoc, "entiteVar", langueNom, entiteVarLangue == null ? entiteVar : entiteVarLangue);
//		
//							List<String> entiteCommentairesLangue = regexListe("(.*)", methodeCommentaire);
//							String entiteCommentaireLangue = indexerStockerSolr(entiteDoc, "entiteCommentaire", langueNom, StringUtils.join(entiteCommentairesLangue, "\n"));
	
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
						}

						clientSolrComputate.add(entiteDoc); 
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
					else {
						// est Methode. 
						
						SolrInputDocument methodeDoc = classeDocClone.deepCopy();
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

						List<JavaTypeVariable<JavaGenericDeclaration>> methodeParametresType = methodeQdox.getTypeParameters();
						for(JavaTypeVariable<JavaGenericDeclaration> methodeParametreType : methodeParametresType) {
							String methodeParametreTypeNom = methodeParametreType.getName();
							stockerListeSolr(methodeDoc, "methodeParametreTypeNoms", methodeParametreTypeNom);
						}

						for(JavaAnnotation annotation : annotations) {
							ClasseParts methodeAnnotationClasseParts = ClasseParts.initClasseParts(this, annotation.getType(), langueNom);
							stockerListeSolr(methodeDoc, "methodeAnnotationsNomSimpleComplet", langueNom, methodeAnnotationClasseParts.nomSimpleComplet);
							stockerListeSolr(methodeDoc, "methodeAnnotationsBlocCode", langueNom, StringUtils.substringAfter(annotation.toString(), methodeAnnotationClasseParts.nomSimple));
							for(String langueNom : autresLangues) {  
								ClasseParts methodeAnnotationClassePartsLangue = ClasseParts.initClasseParts(this, methodeAnnotationClasseParts, langueNom);
								stockerListeSolr(methodeDoc, "methodeAnnotationsNomSimpleComplet", langueNom, methodeAnnotationClassePartsLangue.nomSimpleComplet);
								stockerListeSolr(methodeDoc, "methodeAnnotationsBlocCode", langueNom, StringUtils.substringAfter(annotation.toString(), methodeAnnotationClasseParts.nomSimple));
							}
						}

						for(JavaClass methodeExceptionQdox : methodeExceptionsQdox) {
							String methodeExceptionNomSimpleComplet = StringUtils.substringAfterLast(methodeExceptionQdox.getCanonicalName(), ".");
							stockerListeSolr(methodeDoc, "methodeExceptionNomSimpleComplet", methodeExceptionNomSimpleComplet);
						}
						Boolean methodeEstVide = false;
						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void")) {
							ClasseParts methodeRetourClasseParts = ClasseParts.initClasseParts(this, methodeQdox.getReturns(), langueNom);
							stockerSolr(methodeDoc, "methodeRetourNomSimpleComplet", langueNom, methodeRetourClasseParts.nomSimpleComplet);
							for(String langueNom : autresLangues) { 
								ClasseParts methodeRetourClassePartsLangue = ClasseParts.initClasseParts(this, methodeRetourClasseParts, langueNom);
								stockerSolr(methodeDoc, "methodeRetourNomSimpleComplet", langueNom, methodeRetourClassePartsLangue.nomSimpleComplet);
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
	
						regexListe("^" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);

						String methodeCodeSource = methodeQdox.getSourceCode();
						String methodeCodeSourceLangue = methodeCodeSource;
						ArrayList<String> remplacerClesLangue = regexListe("^r." + langueNom + "\\s*=\\s*(.*)\\n.*", methodeCommentaire);
						ArrayList<String> remplacerValeursLangue = regexListe("^r." + langueNom + "\\s*=\\s*.*\\n(.*)", methodeCommentaire);
						for(int i = 0; i < remplacerClesLangue.size(); i++) {
							String regexCle = remplacerClesLangue.get(i);
							String regexValeur = remplacerValeursLangue.get(i);
							StringUtils.replace(methodeCodeSourceLangue, regexCle, regexValeur);
						}
						stockerSolr(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSourceLangue);

						for(String langueNom : autresLangues) {  
							methodeVarLangue = regex("^var\\." + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
							methodeVarLangue = indexerStockerSolr(methodeDoc, "methodeVar", langueNom, methodeVarLangue == null ? methodeVar : methodeVarLangue);
							regexListe("^" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
							methodeCodeSourceLangue = regexRemplacerTout(methodeCommentaire, methodeCodeSource, langueNom);
							stockerSolr(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSourceLangue);
							stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
						} 
	
						clientSolrComputate.add(methodeDoc); 
					}
				}
			}
		}
		
		ClasseParts classePartsCouverture = classePartsCouverture(nomEnsembleDomaine);
		classePartsGenAjouter(classePartsCouverture);

		for(ClasseParts classePartGen : classePartsGen.values()) {
			indexerStockerListeSolr(classeDoc, "classeImportationsGen", langueNom, classePartGen.nomCanonique);
			for(String langueNom : autresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGen, langueNom);
				indexerStockerListeSolr(classeDoc, "classeImportationsGen", langueNom, classeImportationClassePartsLangue.nomCanonique);
			}
		}

		for(ClasseParts classePartGenApi : classePartsGenApi.values()) {
			indexerStockerListeSolr(classeDoc, "classeImportationsGenApi", langueNom, classePartGenApi.nomCanonique);
			for(String langueNom : autresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGenApi, langueNom);
				indexerStockerListeSolr(classeDoc, "classeImportationsGenApi", langueNom, classeImportationClassePartsLangue.nomCanonique);
			}
		}

		for(ClasseParts classePartGenPage : classePartsGenPage.values()) {
			indexerStockerListeSolr(classeDoc, "classeImportationsGenPage", langueNom, classePartGenPage.nomCanonique);
			for(String langueNom : autresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGenPage, langueNom);
				indexerStockerListeSolr(classeDoc, "classeImportationsGenPage", langueNom, classeImportationClassePartsLangue.nomCanonique);
			}
		}

		clientSolrComputate.add(classeDoc);
		clientSolrComputate.commit();
		String qSupprimer = concat("classeCheminAbsolu_indexed_string", ":\"", classeChemin, "\" AND (modifiee_indexed_date:[* TO ", modifiee.toString(), "-1MILLI] OR (*:* NOT modifiee_indexed_date:*))");
		clientSolrComputate.deleteByQuery(qSupprimer);
		clientSolrComputate.commit(); 
		return classeDoc;
	}
}
