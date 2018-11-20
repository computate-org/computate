package org.computate.enUS.java;

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

public class IndexClass extends RegarderClasseBase {

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

	protected HashMap<String, ClassParts> classePartsGenApi = new HashMap<String, ClasseParts>();

	protected HashMap<String, ClassParts> classePartsGenPage = new HashMap<String, ClasseParts>();

	public void  populateQdoxSuperClassesInterfacesAndMe(JavaClass c, ArrayList<JavaClass> qdoxSuperClasses, ArrayList<JavaClass> qdoxSuperClassesAndMe, ArrayList<JavaClass> qdoxSuperClassesAndInterfaces, ArrayList<JavaClass> qdoxSuperClassesInterfacesAndMe) throws Exception { 
		if(c != null) {
			JavaClass superClass = c.getSuperJavaClass();
			List<JavaClass> interfacesImplemented = c.getInterfaces();

			for(JavaClass interfaceQdox : interfacesImplemented) {
				if(interfaceQdox != null && !interfaceQdox.getCanonicalName().equals("java.lang.Object") && !c.equals(interfaceQdox)) {
					qdoxSuperClassesInterfacesAndMe.add(interfaceQdox);
					qdoxSuperClassesAndInterfaces.add(superClass);
					populateQdoxSuperClassesInterfacesAndMe(interfaceQdox, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe); // Doesn't seem to work for interfaces that extend other interfaces.
				}
			}
			qdoxSuperClassesAndMe.add(c);
			if(superClass != null && !superClass.getCanonicalName().equals("java.lang.Object") && !c.equals(superClass)) {
				qdoxSuperClassesInterfacesAndMe.add(superClass);
				qdoxSuperClassesAndMe.add(superClass);
				qdoxSuperClassesAndInterfaces.add(superClass);
				qdoxSuperClasses.add(superClass);
				populateQdoxSuperClassesInterfacesAndMe(superClass, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe);
			}
		}
	}

	protected Boolean storeSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_boolean"), fieldValue);
		return fieldValue;
	}

	protected Boolean storeSolr(SolrInputDocument doc, String fieldName, String languageName, Boolean fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_boolean"), fieldValue);
		}
		return fieldValue;
	}

	protected String storeSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_string"), fieldValue);
		return fieldValue;
	}

	protected String storeListSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_strings"), fieldValue);
		return fieldValue;
	}

	protected Boolean storeListSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_booleans"), fieldValue);
		return fieldValue;
	}

	protected String storeSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String storeListSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> storeSolr(SolrInputDocument doc, String fieldName, String languageName, List<String> fieldValues) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			for(String fieldValue : fieldValues) {
				doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
			}
		}
		return fieldValues;
	}

	protected Boolean indexSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexListSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_indexed_strings"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexSolr(SolrInputDocument doc, String fieldName, String languageName, Boolean fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected String indexSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexListSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexSolr(SolrInputDocument doc, String fieldName, String languageName, List<String> fieldValues) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			for(String fieldValue : fieldValues) {
				doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
			}
		}
		return fieldValues;
	}

	protected Long indexStoreSolr(SolrInputDocument doc, String fieldName, Long fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_long"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_long"), fieldValue);
		return fieldValue;
	}

	protected Integer indexStoreSolr(SolrInputDocument doc, String fieldName, Integer fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_int"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_int"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexStoreSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_boolean"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_boolean"), fieldValue);
		return fieldValue;
	}

	protected Date indexStoreSolr(SolrInputDocument doc, String fieldName, Date fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_date"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_date"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexStoreSolr(SolrInputDocument doc, String fieldName, String languageName, Boolean fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexStoreSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_string"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected String indexStoreListSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_strings"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_strings"), fieldValue);
		return fieldValue;
	}

	protected String indexStoreSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexStoreListSolr(SolrInputDocument doc, String fieldName, String fieldValue, String valeurChamp) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexStoreSolr(SolrInputDocument doc, String fieldName, String languageName, List<String> fieldValues) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			for(String fieldValue : fieldValues) {
				doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
				doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
			}
		}
		return fieldValues;
	}

	protected SolrDocument classDocsAdd(String canonicalName) throws Exception {
		SolrDocument doc = null;
		if(StringUtils.startsWith(domainPackageName, canonicalName)) {
			SolrQuery solrSearch = new SolrQuery();   
			solrSearch.setQuery("*:*");
			solrSearch.setRows(1);
			solrSearch.addFilterQuery("classCanonicalName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(canonicalName));
			solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
			solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
			QueryResponse searchResponse = solrClientComputate.query(solrSearch);
			SolrDocumentList searchList = searchResponse.getResults();
			if(searchList.size() > 0) {
				doc = searchList.get(0);
				classDocs.put(canonicalName, doc);
			}
		}
		return doc;
	}

	protected String searchCanonicalName(String languageName, String canonicalName) throws Exception {
		SolrDocument doc = classDocsAdd(canonicalName);
		String val = null;
		if(doc != null) {
			val = (String)doc.get("classCanonicalName_" + languageName + "_stored_string");
		}
		if(StringUtils.isEmpty(val)) {
			val = canonicalName;  
		}
		return val;
	}

	protected ClassParts classePartsCouverture(String nomEnsembleDomaine) throws Exception {
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

	protected ClassParts classePartsRequeteSite(String nomEnsembleDomaine) throws Exception {
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

	protected ClassParts classePartsSiteContexte(String nomEnsembleDomaine) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:SiteContexte");
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

	protected ClassParts classePartsConfigSite(String nomEnsembleDomaine) throws Exception {
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

	protected ClassParts classePartsUtilisateurSite(String nomEnsembleDomaine) throws Exception {
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

	protected ClassParts classePartsCluster(String nomEnsembleDomaine) throws Exception {
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

	protected ClassParts classePartsResultatRecherche(String nomEnsembleDomaine) throws Exception {
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

	public String storeRegexComments(String comment, String languageName, SolrInputDocument doc, String entityVar) throws Exception {
		if(!StringUtils.isEmpty(comment)) {
			Matcher m = Pattern.compile("^(enUS|frFR): (.*)", Pattern.MULTILINE).matcher(comment);
			boolean found = m.find();
			StringBuilder b = new StringBuilder();
			
			while(found) {
				String language = m.group(1);
				String text = m.group(2);
				if(languageName.equals(language))
					b.append(text).append("\n");

				found = m.find();
			}
			if(StringUtils.isNotEmpty(b))
				storeSolr(doc, entityVar, languageName, b.toString());
		}
		return comment;
	}

	public void  classePartsGenAjouter(ClassParts classeParts) {
		if(classePartsGen != null && classeParts != null && !classePartsGen.containsKey(classeParts.nomCanonique) && StringUtils.contains(classeParts.nomCanonique, "."))
			classePartsGen.put(classeParts.nomCanonique, classeParts);
	}

	public void  classePartsGenApiAjouter(ClassParts classeParts) {
		if(classePartsGenApi != null && classeParts != null && !classePartsGenApi.containsKey(classeParts.nomCanonique) && StringUtils.contains(classeParts.nomCanonique, "."))
			classePartsGenApi.put(classeParts.nomCanonique, classeParts);
	}

	public void  classePartsGenPageAjouter(ClassParts classeParts) {
		if(classePartsGenPage != null && classeParts != null && !classePartsGenPage.containsKey(classeParts.nomCanonique) && StringUtils.contains(classeParts.nomCanonique, "."))
			classePartsGenPage.put(classeParts.nomCanonique, classeParts);
	}

	protected SolrInputDocument indexClass(String classAbsolutePath) throws Exception { 

		SolrInputDocument classDoc = new SolrInputDocument();
		String classCanonicalName = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classAbsolutePath, "."), srcMainJavaPath + "/"), "/", ".");
		String classSimpleName = StringUtils.substringAfterLast(classCanonicalName, ".");
		String classCanonicalNameGen = classCanonicalName + "Gen";
		String classSimpleNameGen = classSimpleName + "Gen";
		String classCanonicalNameApiGen = classCanonicalName + "ApiGen";
		String classSimpleNameApi = indexStoreSolr(classDoc, "classSimpleNameApi", languageName, classSimpleName + "Api");
		String classSimpleNameApiGen = indexStoreSolr(classDoc, "classSimpleNameApiGen", languageName, classSimpleName + "ApiGen");
		String classCanonicalNamePageGen = classCanonicalName + "PageGen";
		String classSimpleNamePage = indexStoreSolr(classDoc, "classSimpleNamePage", languageName, classSimpleName + "Page");
		String classSimpleNamePageGen = indexStoreSolr(classDoc, "classSimpleNamePageGen", languageName, classSimpleName + "PageGen");
		JavaClass classQdox = builder.getClassByName(classCanonicalName.toString());
		JavaClass classSuperQdox = classQdox.getSuperJavaClass();
		JavaClass classQdoxString = builder.getClassByName(String.class.getCanonicalName());
		String classSuperCanonicalName = classSuperQdox.getCanonicalName();
		String classSuperSimpleName = StringUtils.substringAfterLast(classSuperCanonicalName, ".");
		if(StringUtils.isEmpty(classSuperSimpleName))
			classSuperSimpleName = classSuperCanonicalName;

		List<JavaTypeVariable<JavaGenericDeclaration>> classTypeParameters = classQdox.getTypeParameters();
		for(JavaTypeVariable<JavaGenericDeclaration> classTypeParameter : classTypeParameters) {
			String classTypeParameterNom = classTypeParameter.getName();
			storeListSolr(classDoc, "classTypeParameterNames", classTypeParameterNom);
		}

		if(classSuperQdox instanceof DefaultJavaParameterizedType) {
			DefaultJavaParameterizedType typeSuper = (DefaultJavaParameterizedType)classSuperQdox;
			List<JavaType> classeSuperParametresType = typeSuper.getActualTypeArguments();
			for(JavaType classeSuperParametreType : classeSuperParametresType) {
				String classeSuperParametreTypeNom = classeSuperParametreType.getValue();
				storeListSolr(classDoc, "classSuperTypeParameterNames", classeSuperParametreTypeNom);
			}
		}
//		classTypeParameters.get(0).getGenericFullyQualifiedName(); // returns <DEV>
//		classQdox.getSuperClass().getGenericCanonicalName(); // returns CouvertureGen<DEV>
		
		String classSuperCompleteName = indexStoreSolr(classDoc, "classSuperCompleteName", languageName, classSuperQdox.getGenericCanonicalName());
		String classSuperCompleteNameGeneric = StringUtils.substringBeforeLast(StringUtils.substringAfter(classSuperCompleteName, "<"), ">");
		String classSuperCanonicalNameGeneric = null;
		String classSuperSimpleNameGeneric = null;
		JavaClass classeSuperGeneriqueQdox = null;
		Boolean classeBaseEtendGen = false;
		if(StringUtils.isNotEmpty(classSuperCompleteName)) {
			indexStoreSolr(classDoc, "classSuperCompleteNameGeneric", languageName, classSuperCompleteNameGeneric);
			if(classSuperCompleteName.contains("<")) {
				classSuperCanonicalNameGeneric = StringUtils.substringAfter(StringUtils.substringBeforeLast(classSuperCompleteName, ">"), "<");
				classSuperCanonicalNameGeneric = classSuperCanonicalNameGeneric.contains(",") ? StringUtils.substringBefore(classSuperCanonicalNameGeneric, ",") : classSuperCanonicalNameGeneric;
				indexStoreSolr(classDoc, "classSuperCanonicalNameGeneric", languageName, classSuperCanonicalNameGeneric);
				classeSuperGeneriqueQdox = builder.getClassByName(classSuperCanonicalNameGeneric);
				classSuperCompleteNameGeneric = classSuperCanonicalNameGeneric;

				if(classSuperCanonicalNameGeneric.contains("."))
					classSuperSimpleNameGeneric = StringUtils.substringAfterLast(classSuperCanonicalNameGeneric, ".");
				else
					classSuperSimpleNameGeneric = classSuperCanonicalNameGeneric;
				indexStoreSolr(classDoc, "classSuperSimpleNameGeneric", languageName, classSuperSimpleNameGeneric);

				ClassParts classePartsBase = ClassParts.initClassParts(this, classSuperCanonicalNameGeneric, languageName);
				classeBaseEtendGen = classePartsBase.etendGen;
				if(classeBaseEtendGen == null)
					classeBaseEtendGen = false;
			}
		}
		Boolean classeEstBase = storeSolr(classDoc, "classeEstBase", !classeBaseEtendGen || StringUtils.isEmpty(classSuperCompleteNameGeneric) || StringUtils.equals(classSuperCompleteNameGeneric, "java.lang.Object"));
		Boolean classeEtendBase = storeSolr(classDoc, "classeEtendBase", !classeEstBase && classeBaseEtendGen && !StringUtils.equals(classSuperCompleteNameGeneric, "java.lang.Object"));
		indexStoreSolr(classDoc, "classeBaseEtendGen", classeBaseEtendGen);
		Boolean classeContientRequeteSite = indexStoreSolr(classDoc, "classeContientRequeteSite", classQdox.getMethodBySignature("getRequeteSite", new ArrayList<JavaType>(), true) != null);
		
		String classComment = storeRegexComments(classQdox.getComment(), languageName, classDoc, "classComment");
		String classPackageName = StringUtils.substringBeforeLast(classCanonicalName, ".");
		String classPath = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), ".java");
		String classDirPath = StringUtils.substringBeforeLast(classPath, "/");
		String classGenPath = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "Gen.java");
		String classPathApiGen = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "ApiGen.java");
		String classPathPageGen = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "PageGen.java");
		String classGenDirPath = StringUtils.substringBeforeLast(classGenPath, "/");
		String classKey = classAbsolutePath;
		Instant modified = Instant.now();
		Date modifiedDate = Date.from(modified);
		Boolean classeContientCouverture = false;

		Boolean classExtendsGen = StringUtils.endsWith(classSuperSimpleName, "Gen");
		ClassParts classePartsRequeteSite = classePartsRequeteSite(domainPackageName);
		if(!classExtendsGen && regexFound("^gen:\\s*(true)$", classComment)) {
			classExtendsGen = true;
		}
		Boolean classeModele = storeSolr(classDoc, "classeModele", regexFound("^modele: \\s*(true)$", classComment));
		Boolean classeApi = storeSolr(classDoc, "classeApi", regexFound("^api: \\s*(true)$", classComment) || classeModele);
		Boolean classePage = storeSolr(classDoc, "classePage", regexFound("^page: \\s*(true)$", classComment) || classeModele);
		Boolean classeInitLoin = !regexFound("^initLoin:\\s*(false)$", classComment);
		if(classeInitLoin)
			classeInitLoin = classeEtendBase || classeEstBase;
		classeInitLoin = storeSolr(classDoc, "classeInitLoin", classeInitLoin);
		if(classeInitLoin)
			classePartsGenAjouter(classePartsRequeteSite);
		indexStoreSolr(classDoc, "classExtendsGen", classExtendsGen);
		Boolean classeSauvegarde = indexStoreSolr(classDoc, "classeSauvegarde", regexFound("^sauvegarde:\\s*(true)$", classComment));
		Boolean classeIndexe = indexStoreSolr(classDoc, "classeIndexe", regexFound("^indexe:\\s*(true)$", classComment) || classeSauvegarde || classeModele);

		ClassParts classePartsSolrInputDocument = ClassParts.initClassParts(this, "org.apache.solr.common.SolrInputDocument", languageName);
		ClassParts classePartsSolrClient = ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrClient", languageName);
		ClassParts classePartsTest = ClassParts.initClassParts(this, "org.junit.Test", languageName);
		ClassParts classePartsSiteContexte = classePartsSiteContexte(domainPackageName);
		ClassParts classePartsConfigSite = classePartsConfigSite(domainPackageName);
		ClassParts classePartsUtilisateurSite = classePartsUtilisateurSite(domainPackageName);
		ClassParts classePartsCluster = classePartsCluster(domainPackageName);
		ClassParts classePartsResultatRecherche = classePartsResultatRecherche(domainPackageName);

		if(classePage) {
			classePartsGenPageAjouter(classePartsConfigSite);
			classePartsGenPageAjouter(classePartsRequeteSite);
			classePartsGenPageAjouter(classePartsSiteContexte);
			classePartsGenPageAjouter(classePartsUtilisateurSite);
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.io.IOException", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.servlet.http.HttpServlet", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerRequest", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerResponse", languageName));
		}

		if(classeApi) {
			classePartsGenApiAjouter(classePartsConfigSite);
			classePartsGenApiAjouter(classePartsRequeteSite);
			classePartsGenApiAjouter(classePartsSiteContexte);
			classePartsGenApiAjouter(classePartsUtilisateurSite);
			classePartsGenApiAjouter(classePartsResultatRecherche);
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.io.IOException", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.servlet.http.HttpServlet", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.servlet.http.HttpServerRequest", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.servlet.http.HttpServerResponse", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.util.Collections", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.util.Map", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.servlet.ServletException", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.util.concurrent.TimeUnit", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.util.stream.Collectors", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.core.json.Json", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.json.JsonArray", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.json.JsonObject", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.json.JsonReader", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery.ORDER", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.response.QueryResponse", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.util.ClientUtils", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.apache.commons.lang3.StringUtils", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.keycloak.KeycloakPrincipal", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.keycloak.KeycloakSecurityContext", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.keycloak.representations.AccessToken", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.keycloak.representations.AccessToken.Access", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.security.Principal", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.mail.Message", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.mail.Session", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.mail.Transport", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.mail.internet.InternetAddress", languageName));
//			classePartsGenApiAjouter(ClassParts.initClassParts(this, "javax.mail.internet.MimeMessage", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.io.PrintWriter", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocumentList", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocument", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.util.Collection", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.math.BigDecimal", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.util.Date", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.time.ZoneId", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "java.util.List", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.core.Handler", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.ext.web.RoutingContext", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.ext.web.Router", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.core.Vertx", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.ext.reactivestreams.ReactiveReadStream", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.ext.reactivestreams.ReactiveWriteStream", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.core.MultiMap", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.ext.auth.oauth2.OAuth2Auth", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.netty.handler.codec.http.HttpResponseStatus", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.core.logging.Logger", languageName));
			classePartsGenApiAjouter(ClassParts.initClassParts(this, "io.vertx.core.logging.LoggerFactory", languageName));
		}
		if(classeIndexe) {
			classePartsGenAjouter(classePartsSolrInputDocument);
			classePartsGenAjouter(classePartsSolrClient);
//			classePartsGenAjouter(classePartsTest);
			classePartsGenAjouter(classePartsSiteContexte);
		}
		if(classeEtendBase || classeEstBase) {
			classePartsGenAjouter(classePartsCluster);
		}

		
		ArrayList<JavaClass> qdoxSuperClasses = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesAndMe = new ArrayList<JavaClass>();
		qdoxSuperClassesAndMe.add(classQdox);
		ArrayList<JavaClass> qdoxSuperClassesAndInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesInterfacesAndMe = new ArrayList<JavaClass>();
		qdoxSuperClassesInterfacesAndMe.add(classQdox);
		populateQdoxSuperClassesInterfacesAndMe(classQdox, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe);

		indexStoreSolr(classDoc, "languageName", languageName); 
		indexStoreSolr(classDoc, "modified", modifiedDate); 
		indexStoreSolr(classDoc, "classCanonicalName", languageName, classCanonicalName); 
		indexStoreSolr(classDoc, "classePageUri", languageName, regex("^pageUri\\." + languageName + ":\\s*(.*)", classComment));
		indexStoreSolr(classDoc, "classeApiUri", languageName, regex("^apiUri\\." + languageName + ":\\s*(.*)", classComment));
		indexStoreSolr(classDoc, "classSimpleName", languageName, classSimpleName); 
		indexStoreSolr(classDoc, "classPackageName", languageName, classPackageName); 
		indexStoreSolr(classDoc, "classCanonicalNameGen", languageName, classCanonicalNameGen); 
		indexStoreSolr(classDoc, "classSimpleNameGen", languageName, classSimpleNameGen); 
		indexStoreSolr(classDoc, "classSuperCanonicalName", languageName, classSuperCanonicalName); 
		indexStoreSolr(classDoc, "classSuperSimpleName", languageName, classSuperSimpleName); 
		indexStoreSolr(classDoc, "classAbsolutePath", classAbsolutePath);
		indexStoreSolr(classDoc, "classPath", languageName, classPath); 
		indexStoreSolr(classDoc, "classDirPath", languageName, classDirPath);  
		indexStoreSolr(classDoc, "classGenPath", languageName, classGenPath); 
		indexStoreSolr(classDoc, "classPathApiGen", languageName, classPathApiGen); 
		indexStoreSolr(classDoc, "classPathPageGen", languageName, classPathPageGen); 
		indexStoreSolr(classDoc, "classGenDirPath", languageName, classGenDirPath); 
		indexStoreSolr(classDoc, "domainPackageName", domainPackageName); 

		if(classComment != null) {
			Matcher classeValsRecherche = Pattern.compile("^val\\.(\\w+)\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(classComment);
			boolean classeValsTrouve = classeValsRecherche.find();
			while(classeValsTrouve) {
				String classeValVar = classeValsRecherche.group(1);
				String classeValLangue = classeValsRecherche.group(2);
				String classeValValeur = classeValsRecherche.group(3);
				storeListSolr(classDoc, "classeValsVar", classeValVar);
				storeListSolr(classDoc, "classeValsLangue", classeValLangue);
				storeListSolr(classDoc, "classeValsValeur", classeValValeur);
				classeValsTrouve = classeValsRecherche.find();
			}

			Matcher classeRolesRecherche = Pattern.compile("^role:\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classComment);
			boolean classeRolesTrouve = classeRolesRecherche.find();
			while(classeRolesTrouve) {
				String classeRole = classeRolesRecherche.group(1);
				storeListSolr(classDoc, "classeRoles", classeRole);
				classeRolesTrouve = classeRolesRecherche.find();
			}
			indexStoreSolr(classDoc, "classeRolesTrouve", classeRolesTrouve); 
		}

		SolrDocument classSuperCanonicalNameDoc = null;   
		if(StringUtils.startsWith(classSuperCanonicalName, domainPackageName)) {
			SolrQuery solrSearch = new SolrQuery();   
			solrSearch.setQuery("*:*");
			solrSearch.setRows(1);
			solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + ClientUtils.escapeQueryChars(classSuperCanonicalName));
			solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
			solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
			QueryResponse searchResponse = solrClientComputate.query(solrSearch);
			SolrDocumentList searchList = searchResponse.getResults();
			if(searchList.size() > 0) { 
				classSuperCanonicalNameDoc = searchList.get(0);
			}
		}  

		if(classExtendsGen) {
			ClassParts classePartsSuperGenerique = ClassParts.initClassParts(this, classSuperCompleteNameGeneric, languageName);
			classePartsGenAjouter(classePartsSuperGenerique);
		}

		for(String languageName : otherLanguages) { 
			String appPathLanguage = appPaths.get(languageName);
			storeRegexComments(classComment, languageName, classDoc, "classComment");
			String srcMainJavaPathLanguage = appPathLanguage + "/src/main/java";
			String srcGenJavaPathLanguage = appPathLanguage + "/src/gen/java";
			String classCanonicalNameLanguage = regex("^canonicalName\\." + languageName + ":\\s*(.*)", classComment, classCanonicalName);
			String classePageUriLangue = indexStoreSolr(classDoc, "classePageUri", languageName, regex("^pageUri\\." + languageName + ":\\s*(.*)", classComment));
			String classeApiUriLangue = indexStoreSolr(classDoc, "classeApiUri", languageName, regex("^apiUri\\." + languageName + ":\\s*(.*)", classComment));
			String classSimpleNameLanguage = StringUtils.substringAfterLast(classCanonicalNameLanguage, ".");
			String classPackageNameLanguage = StringUtils.substringBeforeLast(classCanonicalNameLanguage, ".");
			String classCanonicalNameGenLanguage = classCanonicalNameLanguage + "Gen";
			String classCanonicalNameApiLangue = classCanonicalNameLanguage + "Api";
			String classCanonicalNameApiGenLangue = classCanonicalNameLanguage + "ApiGen";
			String classCanonicalNamePageLangue = classCanonicalNameLanguage + "Page";
			String classCanonicalNamePageGenLangue = classCanonicalNameLanguage + "PageGen";
			String classSimpleNameGenLanguage = classSimpleNameLanguage + "Gen";
			String classSimpleNameApiLangue = classSimpleNameLanguage + "Api";
			String classSimpleNamePageLangue = classSimpleNameLanguage + "Page";
			String classSimpleNameApiGenLangue = classSimpleNameLanguage + "ApiGen";
			String classSimpleNamePageGenLangue = classSimpleNameLanguage + "PageGen";
			String classPathLanguage = indexStoreSolr(classDoc, "classPath", languageName, concat(srcMainJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameLanguage, ".", "/"), ".java"));
			String classDirPathLanguage = storeSolr(classDoc, "classDirPath", languageName, StringUtils.substringBeforeLast(classPathLanguage, "/"));
			String classGenPathLanguage = indexStoreSolr(classDoc, "classGenPath", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameGenLanguage, ".", "/"), ".java"));
			String classPathApiGenLangue = indexStoreSolr(classDoc, "classPathApiGen", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameApiGenLangue, ".", "/"), ".java"));
			String classPathPageGenLangue = indexStoreSolr(classDoc, "classPathPageGen", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNamePageGenLangue, ".", "/"), ".java"));
			String classGenDirPathLanguage = storeSolr(classDoc, "classGenDirPath", languageName, StringUtils.substringBeforeLast(classGenPathLanguage, "/"));

			indexStoreSolr(classDoc, "classCanonicalName", languageName, classCanonicalNameLanguage); 
			indexStoreSolr(classDoc, "classSimpleName", languageName, classSimpleNameLanguage); 
			indexStoreSolr(classDoc, "classCanonicalNameGen", languageName, classCanonicalNameGenLanguage); 
			indexStoreSolr(classDoc, "classSimpleNameGen", languageName, classSimpleNameGenLanguage); 
			indexStoreSolr(classDoc, "classSimpleNameApi", languageName, classSimpleNameApiLangue); 
			indexStoreSolr(classDoc, "classSimpleNamePage", languageName, classSimpleNamePageLangue); 
			indexStoreSolr(classDoc, "classSimpleNameApiGen", languageName, classSimpleNameApiGenLangue); 
			indexStoreSolr(classDoc, "classSimpleNamePageGen", languageName, classSimpleNamePageGenLangue); 
			indexStoreSolr(classDoc, "classPackageName", languageName, classPackageNameLanguage); 

			String classSuperCompleteNameLanguage;
			ClassParts classSuperPartsLanguage;

			if(classExtendsGen) {
				classSuperPartsLanguage = ClassParts.initClassParts(this, classCanonicalNameLanguage + "Gen", languageName);
			}
			else {
				classSuperPartsLanguage = ClassParts.initClassParts(this, classSuperQdox, languageName);
			}

			indexStoreSolr(classDoc, "classSuperCanonicalName", languageName, classSuperPartsLanguage.canonicalName); 
			indexStoreSolr(classDoc, "classSuperSimpleName", languageName, classSuperPartsLanguage.simpleName); 
			indexStoreSolr(classDoc, "classCanonicalNameCompletSuper", languageName, classSuperPartsLanguage.canonicalNameComplete);
			indexStoreSolr(classDoc, "classSimpleNameCompletSuper", languageName, classSuperPartsLanguage.simpleNameComplete);
			if(StringUtils.isNotEmpty(classSuperCompleteNameGeneric)) {
				ClassParts classePartsSuperGeneriqueLangue = ClassParts.initClassParts(this, classSuperCompleteNameGeneric, languageName);
				indexStoreSolr(classDoc, "classSuperCanonicalNameGeneric", languageName, classePartsSuperGeneriqueLangue.canonicalNameComplete);
				indexStoreSolr(classDoc, "classSuperSimpleNameGeneric", languageName, classePartsSuperGeneriqueLangue.simpleNameComplete);
				if(classExtendsGen) {
					classePartsGenAjouter(classePartsSuperGeneriqueLangue);
				}
			}





//			if(classSuperCanonicalNameDoc == null) {  
//				indexStoreSolr(classDoc, "classSuperCanonicalName", languageName, classSuperCanonicalName); 
//				indexStoreSolr(classDoc, "classSuperSimpleName", languageName, classSuperSimpleName); 
//			}
//			else {
//				indexStoreSolr(classDoc, "classSuperCanonicalName", languageName, (String)classSuperCanonicalNameDoc.get("classCanonicalName_" + languageName + "_stored_string"));
//				indexStoreSolr(classDoc, "classSuperSimpleName", languageName, (String)classSuperCanonicalNameDoc.get("classSimpleName_" + languageName + "_stored_string"));
//			}
//			String classSuperCompleteNameLanguage = searchCanonicalName(languageName, classSuperCompleteName);
//			indexStoreSolr(classDoc, "classSuperCompleteName", languageName, classSuperCompleteNameLanguage);
//			if(StringUtils.isNotEmpty(classSuperCompleteNameGeneric)) {
//				String classSuperCompleteNameGenericLanguage = indexStoreSolr(classDoc, "classSuperCompleteNameGeneric", languageName, searchCanonicalName(languageName, classSuperCompleteNameGeneric));
//				String classSuperCanonicalNameGenericLanguage = indexStoreSolr(classDoc, "classSuperCanonicalNameGeneric", languageName, searchCanonicalName(languageName, classSuperCanonicalNameGeneric));
//				String classSuperSimpleNameGenericLanguage = indexStoreSolr(classDoc, "classSuperSimpleNameGeneric", languageName, searchCanonicalName(languageName, classSuperSimpleNameGeneric));
//			}
		} 

		SolrInputDocument classDocClone = classDoc.deepCopy();
		Integer partNumber = 1;

		classDoc.addField("key", classKey);  

		indexStoreSolr(classDoc, "partIsClass", true);
		indexStoreSolr(classDoc, "partNumber", partNumber);
		
		for(String classeImportation : classQdox.getSource().getImports()) {
			ClassParts classeImportClassParts = ClassParts.initClassParts(this, classeImportation, languageName);
			indexStoreListSolr(classDoc, "classImports", languageName, classeImportClassParts.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classeImportClassParts, languageName);
				indexStoreListSolr(classDoc, "classImports", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		List<JavaMember> membersQdox = new ArrayList<JavaMember>();
		membersQdox.addAll(classQdox.getFields());
		membersQdox.addAll(classQdox.getConstructors());
		membersQdox.addAll(classQdox.getMethods());
		for(JavaMember memberQdox : membersQdox) {  
			partNumber++;

			if(memberQdox instanceof JavaField) {    
				SolrInputDocument fieldDoc = classDocClone.deepCopy();
				JavaField fieldQdox = (JavaField)memberQdox;
				String fieldComment = fieldQdox.getComment();
				String fieldVar = fieldQdox.getName();
				String fieldKey = classAbsolutePath + "." + fieldVar;
				String fieldSourceCode = StringUtils.substringBeforeLast(StringUtils.trim(regex("\\s+" + fieldVar + "\\s*=([\\s\\S]*)", fieldQdox.getCodeBlock(), 1)), ";");

				// Champs Solr du champ. 

				fieldDoc.addField("key", fieldKey);
				indexStoreSolr(fieldDoc, "fieldVar", languageName, fieldVar); 
				indexStoreSolr(fieldDoc, "partIsField", true);
				indexStoreSolr(fieldDoc, "partNumber", partNumber);
				indexStoreSolr(fieldDoc, "fieldIsPublic", fieldQdox.isPublic()); 
				indexStoreSolr(fieldDoc, "fieldIsProtected", fieldQdox.isProtected()); 
				indexStoreSolr(fieldDoc, "fieldIsPrivate", fieldQdox.isPrivate()); 
				indexStoreSolr(fieldDoc, "fieldIsStatic", fieldQdox.isStatic()); 
				indexStoreSolr(fieldDoc, "fieldIsFinal", fieldQdox.isFinal()); 
				indexStoreSolr(fieldDoc, "fieldIsAbstract", fieldQdox.isAbstract()); 
				indexStoreSolr(fieldDoc, "fieldIsNative", fieldQdox.isNative()); 
	
				/////////////////
				// Annotations //
				/////////////////
				List<JavaAnnotation> annotations = fieldQdox.getAnnotations(); 
				ArrayList<String> annotationsLanguage = new ArrayList<String>();
				Boolean fieldIsTest = false;
				Boolean fieldIsOverride = false;
				for(JavaAnnotation annotation : annotations) {
					String fieldAnnotationLanguage = annotation.getType().getCanonicalName();
					indexStoreSolr(fieldDoc, "champAnnotation", languageName, fieldAnnotationLanguage); 

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						fieldIsTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						fieldIsOverride = true;
					}
				}
				indexStoreSolr(fieldDoc, "fieldIsTest", languageName, fieldIsTest); 
				indexStoreSolr(fieldDoc, "fieldIsOverride", languageName, fieldIsOverride); 

				ClassParts fieldClassParts = ClassParts.initClassParts(this, fieldQdox.getType(), languageName);
	
				storeRegexComments(fieldComment, languageName, fieldDoc, "fieldComment");
				storeSolr(fieldDoc, "fieldSimpleNameComplete", languageName, fieldClassParts.simpleNameComplete);
				storeSolr(fieldDoc, "fieldCanonicalNameComplete", languageName, fieldClassParts.canonicalNameComplete);
				storeSolr(fieldDoc, "fieldSourceCode", languageName, fieldSourceCode);

				for(String languageName : otherLanguages) { 
					ClassParts fieldClassPartsLanguage = ClassParts.initClassParts(this, fieldClassParts, languageName);
					String fieldVarLanguage = regex("^var\\." + languageName + ": (.*)", fieldComment);
					fieldVarLanguage = fieldVarLanguage == null ? fieldVar : fieldVarLanguage;
					String fieldSourceCodeLanguage = regexReplaceAll(fieldComment, fieldSourceCode, languageName);

					indexStoreSolr(fieldDoc, "fieldVar", languageName, fieldVarLanguage); 
					storeSolr(fieldDoc, "fieldSimpleNameComplete", languageName, fieldClassPartsLanguage.simpleNameComplete);
					storeSolr(fieldDoc, "fieldCanonicalNameComplete", languageName, fieldClassPartsLanguage.canonicalNameComplete);
					storeRegexComments(fieldComment, languageName, fieldDoc, "fieldComment");
					storeSolr(fieldDoc, "fieldSourceCode", languageName, fieldSourceCodeLanguage);
				}  

				solrClientComputate.add(fieldDoc); 
			}
			else if(memberQdox instanceof JavaConstructor) { 
				SolrInputDocument constructorDoc = classDocClone.deepCopy();
				JavaConstructor constructorQdox = (JavaConstructor)memberQdox;
				String constructorComment = constructorQdox.getComment();
				List<JavaParameter> constructorParamsQdox = constructorQdox.getParameters();
				List<JavaAnnotation> constructorAnnotations = constructorQdox.getAnnotations(); 
				List<JavaClass> constructorExceptionsQdox = constructorQdox.getExceptions();
				for(Integer constructorParamNum = 1; constructorParamNum <= constructorParamsQdox.size(); constructorParamNum++) {
					JavaParameter constructorParamQdox = constructorParamsQdox.get(constructorParamNum - 1);
					String constructeurParamVar = constructorParamQdox.getName();
					storeListSolr(constructorDoc, "constructeurParamVar", languageName, constructeurParamVar);
					ClassParts constructeurParamClassParts = ClassParts.initClassParts(this, constructorParamQdox.getJavaClass(), languageName);
					storeListSolr(constructorDoc, "constructorParamSimpleNameComplete", languageName, constructeurParamClassParts.simpleNameComplete);
					storeListSolr(constructorDoc, "constructorParamVariableArgs", constructorParamQdox.isVarArgs());
					for(String languageName : otherLanguages) { 
						String constructorParamVarLanguage = regex("param" + constructorParamNum + "\\.var\\." + languageName + ": (.*)", constructorComment);
						if(constructorParamVarLanguage == null)
							constructorParamVarLanguage = constructeurParamVar;
						ClassParts constructorParamClassPartsLanguage = ClassParts.initClassParts(this, constructeurParamClassParts, languageName);

						storeListSolr(constructorDoc, "constructorParamSimpleNameComplete", languageName, constructorParamClassPartsLanguage.simpleNameComplete);
						storeListSolr(constructorDoc, "constructeurParamVar", languageName, constructorParamVarLanguage);
					}  
				}
				for(JavaAnnotation constructorAnnotation : constructorAnnotations) {
//					String constructorAnnotationCodeBlock = storeListSolr(constructorDoc, "constructorAnnotationCodeBlock", languageName, annotation.toString());
				}
				for(JavaClass constructorExceptionQdox : constructorExceptionsQdox) {
					String constructorExceptionSimpleNameComplete = StringUtils.substringAfterLast(constructorExceptionQdox.getCanonicalName(), ".");
					storeListSolr(constructorDoc, "constructorExceptionSimpleNameComplete", constructorExceptionSimpleNameComplete);
				}
				String constructorKey = classAbsolutePath + "." + classSimpleName + "(";

				for(int i = 0; i < constructorParamsQdox.size(); i++) {
					JavaParameter paramQdox = constructorParamsQdox.get(i);
					if(i > 0)
						constructorKey += ", ";
					constructorKey += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
				}
				constructorKey += ")"; 

				constructorDoc.addField("key", constructorKey);
				indexStoreSolr(constructorDoc, "partIsConstructor", true);
				indexStoreSolr(constructorDoc, "partNumber", partNumber);

				indexStoreSolr(constructorDoc, "constructorIsPublic", constructorQdox.isPublic());
				indexStoreSolr(constructorDoc, "constructorIsProtected", constructorQdox.isProtected());
				indexStoreSolr(constructorDoc, "constructorIsPrivate", constructorQdox.isPrivate());
				indexStoreSolr(constructorDoc, "constructorIsStatic", constructorQdox.isStatic());
				indexStoreSolr(constructorDoc, "constructorIsFinal", constructorQdox.isFinal());
				indexStoreSolr(constructorDoc, "constructorIsAbstract", constructorQdox.isAbstract());
				indexStoreSolr(constructorDoc, "constructorIsNative", constructorQdox.isNative());
				storeRegexComments(constructorComment, languageName, constructorDoc, "constructorComment");

				String constructorSourceCode = constructorQdox.getSourceCode();
				String constructorSourceCodeLanguage = constructorSourceCode;
				ArrayList<String> replaceKeysLanguage = regexList("^r." + languageName + "\\s*=\\s*(.*)\\n.*", constructorComment);
				ArrayList<String> replaceValuesLanguage = regexList("^r." + languageName + "\\s*=\\s*.*\\n(.*)", constructorComment);
				for(int i = 0; i < replaceKeysLanguage.size(); i++) {
					String cle = replaceKeysLanguage.get(i);
					String valeur = replaceValuesLanguage.get(i);
					StringUtils.replace(constructorSourceCodeLanguage, cle, valeur);
				}
				storeSolr(constructorDoc, "constructorSourceCode", languageName, constructorSourceCodeLanguage);

				for(String languageName : otherLanguages) {  
					constructorSourceCodeLanguage = regexReplaceAll(constructorComment, constructorSourceCode, languageName);
					storeSolr(constructorDoc, "constructorSourceCode", languageName, constructorSourceCodeLanguage);
					storeRegexComments(constructorComment, languageName, constructorDoc, "constructorComment");
				} 

				solrClientComputate.add(constructorDoc); 
			}
			else if(memberQdox instanceof JavaMethod) {
				JavaMethod methodQdox = (JavaMethod)memberQdox;
				String methodComment = methodQdox.getComment();
				Boolean ignorer = "true".equals(regex("ignorer: (.*)", methodComment));
				if(!ignorer) {
					JavaClass methodeClasseQdoxRetour = methodQdox.getReturns();
					String methodeNomCanoniqueRetourComplet = null;
					String methodeNomCanoniqueRetour = null;
					JavaClass classReturnQdox = methodQdox.getReturns();
					List<JavaParameter> methodParamsQdox = methodQdox.getParameters();
		
					List<JavaAnnotation> annotations = methodQdox.getAnnotations(); 
					ArrayList<String> annotationsLanguage = new ArrayList<String>();
					Boolean methodIsTest = false;
					Boolean methodIsOverride = false;
					String methodVar = methodQdox.getName();
					for(JavaAnnotation annotation : annotations) {
						String methodAnnotationLanguage = annotation.getType().getCanonicalName();
	
						if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
							methodIsTest = true;
						}
						if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
							methodIsOverride = true;
						}
					}

					List<JavaClass> methodExceptionsQdox = methodQdox.getExceptions();
	
					if(!methodIsOverride && !methodQdox.isStatic() && !methodQdox.isFinal() && methodQdox.getDeclaringClass().equals(classQdox) 
							&& methodQdox.isProtected() && methodParamsQdox.size() == 1 && classReturnQdox.isVoid()
							&& StringUtils.startsWith(methodQdox.getName(), "_")) {

						// est Entite. 
						SolrInputDocument entiteDoc = classDocClone.deepCopy();
						String entiteVar = indexStoreSolr(entiteDoc, "entiteVar", languageName, StringUtils.substringAfter(methodQdox.getName(), "_"));
						String entiteVarCapitalise = indexStoreSolr(entiteDoc, "entiteVarCapitalise", languageName, StringUtils.capitalize(entiteVar));
						JavaClass entiteClasseQdox = methodParamsQdox.get(0).getJavaClass();
						ClassParts entiteClassParts = ClassParts.initClassParts(this, entiteClasseQdox, languageName);
						Boolean entiteCouverture = false;

						if(entiteClassParts.simpleName.equals("Couverture")) {
							entiteClassParts = ClassParts.initClassParts(this, entiteClassParts.canonicalNameGeneric, entiteVar);
							entiteCouverture = true;
							classeContientCouverture = true;
						}

						classePartsGenAjouter(entiteClassParts);
						if(StringUtils.isNotEmpty(entiteClassParts.canonicalNameGeneric)) {
							ClassParts classePartsGenerique = ClassParts.initClassParts(this, entiteClassParts.canonicalNameGeneric, languageName);
							classePartsGenAjouter(classePartsGenerique);
						}

						indexStoreSolr(entiteDoc, "entiteCouverture", entiteCouverture);
						indexStoreSolr(entiteDoc, "entiteInitialise", true);

						String entiteNomCanonique = indexStoreSolr(entiteDoc, "entiteNomCanonique", languageName, entiteClassParts.canonicalName);
						String entiteNomSimple = indexStoreSolr(entiteDoc, "entiteNomSimple", languageName, entiteClassParts.simpleName);
						String entiteNomCompletGenerique = indexStoreSolr(entiteDoc, "entiteNomCompletGenerique", languageName, entiteClassParts.canonicalNameGeneric);
						String entiteNomCanoniqueGenerique = indexStoreSolr(entiteDoc, "entiteNomCanoniqueGenerique", languageName, entiteClassParts.canonicalNameGeneric);
						String entiteNomSimpleGenerique = indexStoreSolr(entiteDoc, "entiteNomSimpleGenerique", languageName, entiteClassParts.simpleNameGeneric);
						indexStoreSolr(entiteDoc, "entiteNomCanoniqueComplet", languageName, entiteClassParts.canonicalNameComplete);
						indexStoreSolr(entiteDoc, "entiteNomSimpleComplet", languageName, entiteClassParts.simpleNameComplete);
						indexStoreSolr(entiteDoc, "entiteNomSimpleCompletGenerique", languageName, entiteClassParts.simpleNameGeneric);

						JavaMethod entiteSetter = classQdox.getMethodBySignature("set" + entiteVarCapitalise, new ArrayList<JavaType>() {{ add(classQdoxString); }}, true);
						Boolean entiteDefinir = storeSolr(entiteDoc, "entiteDefinir", 
								entiteNomCanonique.equals(VAL_canonicalNameString)
								|| entiteNomCanonique.equals(VAL_canonicalNameBoolean)
								|| entiteNomCanonique.equals(VAL_canonicalNameInteger)
								|| entiteNomCanonique.equals(VAL_canonicalNameBigDecimal)
								|| entiteNomCanonique.equals(VAL_canonicalNameDouble)
								|| entiteNomCanonique.equals(VAL_canonicalNameFloat)
								|| entiteNomCanonique.equals(VAL_canonicalNameLong)
								|| entiteNomCanonique.equals(VAL_canonicalNameLocalDateTime)
								|| entiteNomCanonique.equals(VAL_canonicalNameLocalDate)
								|| entiteNomCanonique.equals(VAL_canonicalNameTimestamp)
								|| entiteNomCanonique.equals(VAL_canonicalNameDate)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameString.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameBoolean.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameInteger.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameBigDecimal.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameDouble.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameFloat.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameLong.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameLocalDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameLocalDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameTimestamp.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameList) && VAL_canonicalNameDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameString.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameBoolean.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameInteger.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameBigDecimal.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameDouble.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameFloat.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLong.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLocalDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLocalDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameTimestamp.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLong.equals(entiteNomCanoniqueGenerique)
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
						indexStoreSolr(entiteDoc, "entiteNomCanoniqueBase", languageName, entiteNomCanoniqueBase);
						
						String entiteNomSimpleBase = null;
						if(StringUtils.isNotEmpty(entiteNomCanoniqueBase)) {
							entiteNomSimpleBase = StringUtils.substringAfterLast(entiteNomCanoniqueBase, ".");
						}
						indexStoreSolr(entiteDoc, "entiteNomSimpleBase", languageName, entiteNomSimpleBase);
						
						String entiteVarParam;
						if(entiteClassParts.canonicalName.equals(ArrayList.class.getCanonicalName()) || entiteClassParts.canonicalName.equals(List.class.getCanonicalName()))
							entiteVarParam = "l";
						else
							entiteVarParam = "o";
						indexStoreSolr(entiteDoc, "entiteVarParam", languageName, entiteVarParam);
						
						String entiteVarCouverture = indexStoreSolr(entiteDoc, "entiteVarCouverture", languageName, entiteVar + "Couverture");

						Boolean entiteInitLoin = indexStoreSolr(entiteDoc, "entiteInitLoin", !entiteVar.endsWith("_") && BooleanUtils.isTrue(entiteClassParts.etendGen));
						
//						String entiteParamVar = StringUtils.equalsAny(entiteClasseQdox, "");
//						indexStoreSolr(entiteDoc, "entiteParamVar", regexFound("^exact:\\s*(true)$", methodComment));
//							if(canonicalName.equals(classe_.canonicalNameArrayList) || canonicalName.equals(classe_.canonicalNameList))
//								o.tout("l");
//							else if(o.estVide())
//								o.tout("o");

						List<JavaMethod> entiteMethodesAvant = new ArrayList<JavaMethod>();
						entiteMethodesAvant.add(classQdox.getMethodBySignature(entiteVar + "Avant", new ArrayList<JavaType>() {{ add(entiteClasseQdox); }}, true));
						for(JavaClass c : qdoxSuperClassesAndMe) {
							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
							entiteMethodesAvant.add(classQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
							entiteMethodesAvant.add(classQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classQdoxString); }}, true));
						}
						for(JavaMethod methode : entiteMethodesAvant) {
							if(methode != null) {
								JavaParameter param = methode.getParameters().get(0);
								storeListSolr(entiteDoc, "entiteMethodesAvantVisibilite", methode.isPublic() ? "public" : "protected");
								storeListSolr(entiteDoc, "entiteMethodesAvantVar", methode.getName());
								storeListSolr(entiteDoc, "entiteMethodesAvantParamVar", param.getName());
								storeListSolr(entiteDoc, "entiteMethodesAvantParamNomSimple", StringUtils.substringAfterLast(param.getCanonicalName(), "."));
								storeListSolr(entiteDoc, "entiteMethodesAvantNomParam", methode.getParameters().size() > 1);
							}
						}

						List<JavaMethod> entiteMethodesApres = new ArrayList<JavaMethod>();
						entiteMethodesApres.add(classQdox.getMethodBySignature(entiteVar + "Apres", new ArrayList<JavaType>() {{ add(entiteClasseQdox); }}, true));
						for(JavaClass c : qdoxSuperClassesAndMe) {
							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
							entiteMethodesApres.add(classQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
							entiteMethodesApres.add(classQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classQdoxString); }}, true));
						}
						for(JavaMethod methode : entiteMethodesApres) {
							if(methode != null) {
								JavaParameter param = methode.getParameters().get(0);
								storeListSolr(entiteDoc, "entiteMethodesApresVar", methode.getName());
								storeListSolr(entiteDoc, "entiteMethodesApresParamVar", param.getName());
								storeListSolr(entiteDoc, "entiteMethodesApresParamNomSimple", StringUtils.substringAfterLast(param.getCanonicalName(), "."));
								storeListSolr(entiteDoc, "entiteMethodesApresNomParam", methode.getParameters().size() > 1);
							}
						}

						indexStoreSolr(entiteDoc, "entiteExact", regexFound("^exact:\\s*(true)$", methodComment));
						Boolean entiteCleUnique = indexStoreSolr(entiteDoc, "entiteCleUnique", regexFound("^cleUnique:\\s*(true)$", methodComment));
						Boolean entiteCrypte = indexStoreSolr(entiteDoc, "entiteCrypte", regexFound("^crypte:\\s*(true)$", methodComment));
						Boolean entiteSuggere = indexStoreSolr(entiteDoc, "entiteSuggere", regexFound("^suggere:\\s*(true)$", methodComment));
						Boolean entiteSauvegarde = indexStoreSolr(entiteDoc, "entiteSauvegarde", regexFound("^sauvegarde:\\s*(true)$", methodComment));
						Boolean entiteIndexe = indexStoreSolr(entiteDoc, "entiteIndexe", regexFound("^indexe:\\s*(true)$", methodComment));
						Boolean entiteIncremente = indexStoreSolr(entiteDoc, "entiteIncremente", regexFound("^incremente:\\s*(true)$", methodComment));
						Boolean entiteStocke = indexStoreSolr(entiteDoc, "entiteStocke", regexFound("^stocke:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteIndexeOuStocke", entiteCleUnique || entiteCrypte || entiteSuggere || entiteIndexe || entiteStocke || entiteIncremente);
						indexStoreSolr(entiteDoc, "entitetexte", regexFound("^texte:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteNomAffichage", regexFound("^nomAffichage:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteIgnorer", regexFound("^ignorer:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteDeclarer", regexFound("^declarer:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteRechercher", regexFound("^rechercher:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteAttribuer", regexFound("^attribuer:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteAjouter", regexFound("^ajouter:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteSupprimer", regexFound("^supprimer:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteModifier", regexFound("^modifier:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteRecharger", regexFound("^recharger:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteMultiligne", regexFound("^multiligne:\\s*(true)$", methodComment));
						indexStoreSolr(entiteDoc, "entiteCles", regexFound("^cles:\\s*(true)$", methodComment));

//						boolean entiteCouverture = false;
//	
//						String varEntiteEnUS = regex("^var.enUS: (.*)", methodQdox.getComment());
//						entite.var.frFR(entiteVar);
//						entite.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? entiteVar : StringUtils.substringAfter(varEntiteEnUS, "_"));
//	
//						regexCommentaires(methodQdox.getComment(), entite.comment);
//						regexReplaceAll(methodQdox.getComment(), methodQdox.getSourceCode(), entite.codeSource);
//			
//						if(entiteClasseQdox.getFullyQualifiedName().equals(canonicalNameCouvertureActuel)) {
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
//						if(!etendCluster && entite.canonicalNameGeneric.pasVide()) {
//							boolean listeCluster = etendClasse(canonicalNameClusterActuel, entite.canonicalNameGeneric.toString());
//							entite.listeCluster(listeCluster);
//						}
//	
//						boolean etendPageXml = entite.etendClasse(canonicalNamePageXmlActuel);
//						entite.etendPageXml(etendPageXml);
//						if(!etendPageXml && entite.canonicalNameGeneric.pasVide()) {
//							boolean listePageXml = etendClasse(canonicalNamePageXmlActuel, entite.canonicalNameGeneric.toString());
//							entite.listePageXml(listePageXml);
//						}
//	
//						boolean etendPagePart = entite.etendClasse(canonicalNamePagePartActuel);
//						entite.etendPagePart(etendPagePart);
//						if(!etendPagePart && entite.canonicalNameGeneric.pasVide()) {
//							boolean listePagePart = etendClasse(canonicalNamePagePartActuel, entite.canonicalNameGeneric.toString());
//							entite.listePagePart(listePagePart);
//						}
//	
//	
//						boolean contientRequeteSite = contientChamp(varRequeteSite.toString(), entite.classQdox);
//						entite.contientRequeteSite(contientRequeteSite);
//	
//						boolean contientSetterString = contientMethode(entite.var.toString(), classQdoxString);
//						entite.contientSetterString(contientSetterString);
//	
//						entiteEstCmd(entite);
						
						
						
						
						
						
						
						
						
						
						
						
						
						for(JavaAnnotation annotation : annotations) {
							String entiteAnnotationLangue = indexStoreSolr(entiteDoc, "entiteAnnotations", languageName, annotation.getType().getCanonicalName());
						}
//						if(classReturnQdox != null && !classReturnQdox.getCanonicalName().equals("void")) {
//							entiteNomCanoniqueRetourComplet = indexerStocker(entiteDoc, "entiteNomCanoniqueRetourComplet", languageName, classReturnQdox.getGenericCanonicalName());
//							entiteNomCanoniqueRetour = indexerStocker(entiteDoc, "entiteNomCanoniqueRetour", languageName, classReturnQdox.getCanonicalName());
//							String entiteNomSimpleRetour = indexerStocker(entiteDoc, "entiteNomSimpleRetour", languageName, StringUtils.substringAfterLast(entiteNomCanoniqueRetour, "."));
//							String listeNomTypeOrigineRetourGenerique = entiteNomCanoniqueRetourComplet;
//							String entiteNomCanoniqueRetourGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineRetourGenerique, "<"), ">");
//							String entiteNomSimpleRetourComplet;
//							String entiteNomSimpleRetourGenerique;
//							entiteNomCanoniqueRetourGenerique = entiteNomCanoniqueRetourGenerique.contains("<") ? StringUtils.substringBefore(entiteNomCanoniqueRetourGenerique, "<") : entiteNomCanoniqueRetourGenerique;
//							entiteNomCanoniqueRetourGenerique = entiteNomCanoniqueRetourGenerique.contains(",") ? StringUtils.substringBefore(entiteNomCanoniqueRetourGenerique, ",") : entiteNomCanoniqueRetourGenerique;
//							if(StringUtils.isNotEmpty(entiteNomCanoniqueRetourGenerique)) {
//								indexerStocker(entiteDoc, "entiteNomCanoniqueRetourGenerique", languageName, entiteNomCanoniqueRetourGenerique);
//	
//								if(StringUtils.contains(entiteNomCanoniqueRetourGenerique, "."))
//									entiteNomSimpleRetourGenerique = indexerStocker(entiteDoc, "entiteNomSimpleRetourGenerique", languageName, StringUtils.substringAfterLast(entiteNomCanoniqueRetourGenerique, "."));
//								else
//									entiteNomSimpleRetourGenerique = indexerStocker(entiteDoc, "entiteNomSimpleRetourGenerique", languageName, entiteNomCanoniqueRetourGenerique);
//	
//								if(StringUtils.contains(entiteNomSimpleRetourGenerique, ".")) {
//									entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomSimpleRetourComplet", languageName, concat(StringUtils.substringAfterLast(entiteNomSimpleRetour, "."), "<", entiteNomSimpleRetourGenerique, ">"));
//								}
//								else {
//									entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomSimpleRetourComplet", languageName, concat(entiteNomSimpleRetour, "<", entiteNomSimpleRetourGenerique, ">"));
//								}
//							}
//							else {
//								entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomCanoniqueRetourComplet", languageName, entiteNomSimpleRetour);
//							}
//						}
	
						String entiteCle = classAbsolutePath + "." + entiteVar;
		
						// Entites Solr du entite. 
		
						entiteDoc.addField("key", entiteCle);
						indexStoreSolr(entiteDoc, "partEstEntite", true);
						indexStoreSolr(entiteDoc, "partNumber", partNumber);

						String entiteBlocCode = methodQdox.getCodeBlock();





						String entiteTypeSolr = null;
						String entiteSuffixeType = null;
						if(StringUtils.equalsAny(entiteNomCanonique, VAL_canonicalNameBoolean)) {
							entiteTypeSolr = VAL_canonicalNameBoolean;
							entiteSuffixeType = "_boolean";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameLocalDate, VAL_canonicalNameDate)) {
							entiteTypeSolr = VAL_canonicalNameDate;
							entiteSuffixeType = "_date";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_canonicalNameLong)) {
							entiteTypeSolr = VAL_canonicalNameLong;
							entiteSuffixeType = "_long";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_canonicalNameBigDecimal)) {
							entiteTypeSolr = VAL_canonicalNameDouble;
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_canonicalNameDouble)) {
							entiteTypeSolr = VAL_canonicalNameDouble;
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_canonicalNameFloat)) {
							entiteTypeSolr = VAL_canonicalNameFloat;
							entiteSuffixeType = "_float";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_canonicalNameInteger)) {
							entiteTypeSolr = VAL_canonicalNameInteger;
							entiteSuffixeType = "_int";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_canonicalNameList, VAL_canonicalNameArrayList)) {
							if(entiteNomCanoniqueGenerique.equals(VAL_canonicalNameBoolean)) {
								entiteTypeSolr = VAL_canonicalNameList + "<" + VAL_canonicalNameBoolean + ">";
								entiteSuffixeType = "_booleans";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameLocalDate)) {
								entiteTypeSolr = VAL_canonicalNameList + "<" + VAL_canonicalNameDate + ">";
								entiteSuffixeType = "_dates";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_canonicalNameLong)) {
								entiteTypeSolr = VAL_canonicalNameList + "<" + VAL_canonicalNameLong + ">";
								entiteSuffixeType = "_longs";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_canonicalNameBigDecimal)) {
								entiteTypeSolr = VAL_canonicalNameList + "<" + VAL_canonicalNameBigDecimal + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_canonicalNameDouble)) {
								entiteTypeSolr = VAL_canonicalNameList + "<" + VAL_canonicalNameDouble + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_canonicalNameFloat)) {
								entiteTypeSolr = VAL_canonicalNameList + "<" + VAL_canonicalNameFloat + ">";
								entiteSuffixeType = "_floats";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_canonicalNameInteger)) {
								entiteTypeSolr = VAL_canonicalNameList + "<" + VAL_canonicalNameInteger + ">";
								entiteSuffixeType = "_ints";
							}
							else {
								entiteSuffixeType = "_strings";
							}
						}
						else {
							entiteTypeSolr = VAL_canonicalNameList + "<" + VAL_canonicalNameString + ">";
							entiteSuffixeType = "_string";
////								if(videDernier)
////									suffixeType += "_videDernier";
						}
						storeSolr(entiteDoc, "entiteTypeSolr", entiteTypeSolr);
						
						if(entiteCleUnique)
							storeSolr(entiteDoc, "entiteVarCleUnique", entiteVar);
						if(entiteSuggere)
							storeSolr(entiteDoc, "entiteVarSuggere", entiteVar + "_suggere");
						if(entiteIncremente)
							storeSolr(entiteDoc, "entiteVarIncremente", entiteVar + "_incremente");
						if(entiteCrypte)
							storeSolr(entiteDoc, "entiteVarCrypte", entiteVar + "_crypte");
						if(entiteIndexe)
							storeSolr(entiteDoc, "entiteVarIndexe", entiteVar + "_indexe" + entiteSuffixeType);
						if(entiteStocke)
							storeSolr(entiteDoc, "entiteVarStocke", entiteVar + "_stocke" + entiteSuffixeType);

						for(String languageName : otherLanguages) {  
							String entiteVarLangue = regex("^var\\." + languageName + ": (.*)", methodComment);
							entiteVarLangue = indexStoreSolr(entiteDoc, "entiteVar", languageName, entiteVarLangue == null ? entiteVar : entiteVarLangue);
//		
//							List<String> entiteCommentairesLangue = regexList("(.*)", methodComment);
//							String entiteCommentaireLangue = indexStoreSolr(entiteDoc, "entiteCommentaire", languageName, StringUtils.join(entiteCommentairesLangue, "\n"));
	
							String entiteBlocCodeLangue = entiteBlocCode;
							ArrayList<String> replaceKeysLanguage = regexList("^r." + languageName + "\\s*=\\s*(.*)\\n.*", methodComment);
							ArrayList<String> replaceValuesLanguage = regexList("^r." + languageName + "\\s*=\\s*.*\\n(.*)", methodComment);
							for(int i = 0; i < replaceKeysLanguage.size(); i++) {
								String cle = replaceKeysLanguage.get(i);
								String valeur = replaceValuesLanguage.get(i);
								StringUtils.replace(entiteBlocCodeLangue, cle, valeur);
							}
							storeSolr(entiteDoc, "entiteBlocCode", languageName, entiteBlocCodeLangue); 
	
							storeRegexComments(methodComment, languageName, entiteDoc, "entiteCommentaire");
						}

						solrClientComputate.add(entiteDoc); 
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
					else {
						// est Methode. 
						
						SolrInputDocument methodDoc = classDocClone.deepCopy();
						indexStoreSolr(methodDoc, "methodVar", languageName, methodVar);
						for(Integer methodParamNum = 1; methodParamNum <= methodParamsQdox.size(); methodParamNum++) {
							JavaParameter methodParamQdox = methodParamsQdox.get(methodParamNum - 1);
							String methodParamVar = methodParamQdox.getName();
							storeListSolr(methodDoc, "methodParamVar", languageName, methodParamVar);
							ClassParts methodeParamClassParts = ClassParts.initClassParts(this, methodParamQdox.getJavaClass(), languageName);
							storeListSolr(methodDoc, "methodParamSimpleNameComplete", languageName, methodeParamClassParts.simpleNameComplete);
							storeListSolr(methodDoc, "methodParamVariableArgs", methodParamQdox.isVarArgs());
							for(String languageName : otherLanguages) { 
								String methodParamVarLanguage = regex("param" + methodParamNum + "\\.var\\." + languageName + ": (.*)", methodComment);
								if(methodParamVarLanguage == null)
									methodParamVarLanguage = methodParamVar;
								ClassParts methodParamClassPartsLanguage = ClassParts.initClassParts(this, methodeParamClassParts, languageName);

								storeListSolr(methodDoc, "methodParamSimpleNameComplete", languageName, methodParamClassPartsLanguage.simpleNameComplete);
								storeListSolr(methodDoc, "methodParamVar", languageName, methodParamVarLanguage);
							}  
						}

						List<JavaTypeVariable<JavaGenericDeclaration>> methodTypeParameters = methodQdox.getTypeParameters();
						for(JavaTypeVariable<JavaGenericDeclaration> methodTypeParameter : methodTypeParameters) {
							String methodTypeParameterNom = methodTypeParameter.getName();
							storeListSolr(methodDoc, "methodTypeParameterNames", methodTypeParameterNom);
						}

						for(JavaAnnotation annotation : annotations) {
							ClassParts methodAnnotationClassParts = ClassParts.initClassParts(this, annotation.getType(), languageName);
							storeListSolr(methodDoc, "methodAnnotationsNomSimpleComplet", languageName, methodAnnotationClassParts.simpleNameComplete);
							storeListSolr(methodDoc, "methodAnnotationsBlocCode", languageName, StringUtils.substringAfter(annotation.toString(), methodAnnotationClassParts.simpleName));
							for(String languageName : otherLanguages) {  
								ClassParts methodAnnotationClassPartsLangue = ClassParts.initClassParts(this, methodAnnotationClassParts, languageName);
								storeListSolr(methodDoc, "methodAnnotationsNomSimpleComplet", languageName, methodAnnotationClassPartsLangue.simpleNameComplete);
								storeListSolr(methodDoc, "methodAnnotationsBlocCode", languageName, StringUtils.substringAfter(annotation.toString(), methodAnnotationClassParts.simpleName));
							}
						}

						for(JavaClass methodExceptionQdox : methodExceptionsQdox) {
							String methodExceptionSimpleNameComplete = StringUtils.substringAfterLast(methodExceptionQdox.getCanonicalName(), ".");
							storeListSolr(methodDoc, "methodExceptionSimpleNameComplete", methodExceptionSimpleNameComplete);
						}
						Boolean methodIsVoid = false;
						if(classReturnQdox != null && !classReturnQdox.getCanonicalName().equals("void")) {
							ClassParts methodReturnClassParts = ClassParts.initClassParts(this, methodQdox.getReturns(), languageName);
							storeSolr(methodDoc, "methodReturnSimpleNameComplete", languageName, methodReturnClassParts.simpleNameComplete);
							for(String languageName : otherLanguages) { 
								ClassParts methodReturnClassPartsLanguage = ClassParts.initClassParts(this, methodReturnClassParts, languageName);
								storeSolr(methodDoc, "methodReturnSimpleNameComplete", languageName, methodReturnClassPartsLanguage.simpleNameComplete);
							}
						}
						else {
							methodIsVoid = true;
						}
						methodIsVoid = indexStoreSolr(methodDoc, "methodIsVoid", methodIsVoid);
	
						String methodKey = classAbsolutePath + "." + methodVar + "(";
						for(int i = 0; i < methodParamsQdox.size(); i++) {
							JavaParameter paramQdox = methodParamsQdox.get(i);
							if(i > 0)
								methodKey += ", ";
							methodKey += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
						}
						methodKey += ")"; 
		
						// Methodes Solr du methode. 
		
						methodDoc.addField("key", methodKey);
						indexStoreSolr(methodDoc, "partIsMethod", true);
						indexStoreSolr(methodDoc, "partNumber", partNumber);

						indexStoreSolr(methodDoc, "methodIsPublic", methodQdox.isPublic());
						indexStoreSolr(methodDoc, "methodIsProtected", methodQdox.isProtected());
						indexStoreSolr(methodDoc, "methodIsPrivate", methodQdox.isPrivate());
						indexStoreSolr(methodDoc, "methodIsStatic", methodQdox.isStatic());
						indexStoreSolr(methodDoc, "methodIsFinal", methodQdox.isFinal());
						indexStoreSolr(methodDoc, "methodIsAbstract", methodQdox.isAbstract());
						indexStoreSolr(methodDoc, "methodIsNative", methodQdox.isNative());
						indexStoreSolr(methodDoc, "methodIsTest", methodIsTest);
						indexStoreSolr(methodDoc, "methodIsOverride", methodIsOverride);
						storeRegexComments(methodComment, languageName, methodDoc, "methodComment");
	
						String methodVarLanguage = regex("^var\\." + languageName + ": (.*)", methodComment);
						methodVarLanguage =  methodVarLanguage == null ? methodVar : methodVarLanguage;
	
						regexList("^" + languageName + ":\\s*([^\n]+)", methodComment);

						String methodSourceCode = methodQdox.getSourceCode();
						String methodSourceCodeLanguage = methodSourceCode;
						ArrayList<String> replaceKeysLanguage = regexList("^r." + languageName + "\\s*=\\s*(.*)\\n.*", methodComment);
						ArrayList<String> replaceValuesLanguage = regexList("^r." + languageName + "\\s*=\\s*.*\\n(.*)", methodComment);
						for(int i = 0; i < replaceKeysLanguage.size(); i++) {
							String regexKey = replaceKeysLanguage.get(i);
							String regexValue = replaceValuesLanguage.get(i);
							StringUtils.replace(methodSourceCodeLanguage, regexKey, regexValue);
						}
						storeSolr(methodDoc, "methodSourceCode", languageName, methodSourceCodeLanguage);

						for(String languageName : otherLanguages) {  
							methodVarLanguage = regex("^var\\." + languageName + ":\\s*([^\n]+)", methodComment);
							methodVarLanguage = indexStoreSolr(methodDoc, "methodVar", languageName, methodVarLanguage == null ? methodVar : methodVarLanguage);
							regexList("^" + languageName + ":\\s*([^\n]+)", methodComment);
							methodSourceCodeLanguage = regexReplaceAll(methodComment, methodSourceCode, languageName);
							storeSolr(methodDoc, "methodSourceCode", languageName, methodSourceCodeLanguage);
							storeRegexComments(methodComment, languageName, methodDoc, "methodComment");
						} 
	
						solrClientComputate.add(methodDoc); 
					}
				}
			}
		}
		
		ClassParts classePartsCouverture = classePartsCouverture(domainPackageName);
		classePartsGenAjouter(classePartsCouverture);

		for(ClassParts classePartGen : classePartsGen.values()) {
			indexStoreListSolr(classDoc, "classImportsGen", languageName, classePartGen.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classePartGen, languageName);
				indexStoreListSolr(classDoc, "classImportsGen", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		for(ClassParts classePartGenApi : classePartsGenApi.values()) {
			indexStoreListSolr(classDoc, "classImportsGenApi", languageName, classePartGenApi.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classePartGenApi, languageName);
				indexStoreListSolr(classDoc, "classImportsGenApi", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		for(ClassParts classePartGenPage : classePartsGenPage.values()) {
			indexStoreListSolr(classDoc, "classImportsGenPage", languageName, classePartGenPage.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classePartGenPage, languageName);
				indexStoreListSolr(classDoc, "classImportsGenPage", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		solrClientComputate.add(classDoc);
		solrClientComputate.commit();
		String qDelete = concat("classAbsolutePath_indexed_string", ":\"", classPath, "\" AND (modified_indexed_date:[* TO ", modified.toString(), "-1MILLI] OR (*:* NOT modified_indexed_date:*))");
		solrClientComputate.deleteByQuery(qDelete);
		solrClientComputate.commit(); 
		return classDoc;
	}
}
