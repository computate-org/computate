package org.computate.enUS.java;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.computate.enUS.java.SiteConfig;
import com.thoughtworks.qdox.model.JavaClass;

public class ClassParts {

	public String canonicalNameComplete;

	public String canonicalName;

	public String simpleName;

	public String canonicalNameGeneric;

	public String simpleNameComplete;

	public String simpleNameGeneric;

	public Boolean extendsGen;

	public SolrDocument solrDocument;

	public static SolrDocument solrDocument(SiteConfig siteConfig, String canonicalName) throws Exception {
		SolrDocument doc = null;   
		if(StringUtils.startsWith(canonicalName, siteConfig.domainPackageName)) {
			SolrQuery solrSearch = new SolrQuery();   
			solrSearch.setQuery("*:*");
			solrSearch.setRows(1);
			solrSearch.addFilterQuery("classCanonicalName_" + siteConfig.languageActualName + "_indexed_string:" + ClientUtils.escapeQueryChars(canonicalName));
			solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
			QueryResponse searchResponse = siteConfig.solrClientComputate.query(solrSearch);
			SolrDocumentList searchList = searchResponse.getResults();
			if(searchList.size() > 0) { 
				doc = searchList.get(0);
			}
		}
		return doc;
	}

	public static ClassParts initClassParts(SiteConfig siteConfig, ClassParts classParts, String languageName) throws Exception {
		ClassParts o = initClassParts(siteConfig, classParts.canonicalNameComplete, languageName);
		return o;
	}

	public static ClassParts initClassParts(SiteConfig siteConfig, JavaClass classQdox, String languageName) throws Exception {
		String canonicalName = classQdox.getCanonicalName();
		String canonicalNameComplete = classQdox.getGenericFullyQualifiedName();
		String genericSimpleValueBefore = classQdox.getGenericValue();
		String genericCanonicalValueBefore = classQdox.getGenericCanonicalName();
		if(StringUtils.contains(genericCanonicalValueBefore, "<")) {
			String genericSimpleValue = StringUtils.substringAfter(StringUtils.substringBeforeLast(genericSimpleValueBefore, ">"), "<");
			String genericCanonicalValue = StringUtils.substringAfter(StringUtils.substringBeforeLast(genericCanonicalValueBefore, ">"), "<");
			String[] simpleParts = StringUtils.split(genericSimpleValue, ",");
			String[] canonicalParts = StringUtils.split(genericCanonicalValue, ",");
			String canonicalNameGeneric = "";
			for(int i = 0; i < simpleParts.length; i++) {
				String simpleNamePart = StringUtils.trim(simpleParts[i]);
				String canonicalNamePart = StringUtils.trim(canonicalParts[i]);

				if(i > 0) {
					canonicalNameGeneric += ", ";
				}
				SolrQuery solrSearch = new SolrQuery();   
				solrSearch.setQuery("*:*");
				solrSearch.setRows(1);
				solrSearch.addFilterQuery("classSimpleName_" + siteConfig.languageActualName + "_indexed_string:" + ClientUtils.escapeQueryChars(simpleNamePart));
				solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
				QueryResponse searchResponse = siteConfig.solrClientComputate.query(solrSearch);
				SolrDocumentList searchList = searchResponse.getResults();
				if(searchList.size() > 0) { 
					SolrDocument doc = searchList.get(0);
					String simpleNameGenericPart = (String)doc.get("classSimpleName_" + languageName + "_stored_string");
					String canonicalNameGenericPart = (String)doc.get("classCanonicalName_" + languageName + "_stored_string");
					if(simpleNameGenericPart != null && canonicalNameGenericPart != null) {
						canonicalNameGeneric += canonicalNameGenericPart;
					}
					else {
						canonicalNameGeneric += canonicalNamePart;
					}
				}
				else {
					canonicalNameGeneric += canonicalNamePart;
				}
			}
			canonicalNameComplete = canonicalName + "<" + canonicalNameGeneric + ">";
		}
		ClassParts classParts = initClassParts(siteConfig, canonicalNameComplete, languageName);
		return classParts;
	}

	public static ClassParts initClassParts(SiteConfig siteConfig, String canonicalNameComplete, String languageName) throws Exception {
		ClassParts classParts = new ClassParts();
		classParts.canonicalName = canonicalNameComplete;
		classParts.canonicalNameGeneric = null;
		String genericValue = null;

		if(StringUtils.contains(canonicalNameComplete, "<")) {
			classParts.canonicalName = StringUtils.substringBefore(canonicalNameComplete, "<");
			genericValue = StringUtils.substringAfter(StringUtils.substringBeforeLast(canonicalNameComplete, ">"), "<");
		}
		classParts.solrDocument = solrDocument(siteConfig, classParts.canonicalName);

		String canonicalName = null;
		String simpleName = null;
		if(classParts.solrDocument != null) {
			canonicalName = (String)classParts.solrDocument.get("classCanonicalName_" + languageName + "_stored_string");
			simpleName = (String)classParts.solrDocument.get("classSimpleName_" + languageName + "_stored_string");
			classParts.etendGen = (Boolean)classParts.solrDocument.get("classeEtendGen_stored_boolean");

		}
		if(canonicalName != null && simpleName != null) {
			classParts.canonicalName = canonicalName;
			classParts.simpleName = simpleName;
		}
		else {
			classParts.simpleName = StringUtils.substringAfterLast(classParts.canonicalName, ".");
			if(StringUtils.isEmpty(classParts.simpleName))
			classParts.simpleName = classParts.canonicalName;
		}
		classParts.canonicalNameComplete = classParts.canonicalName;
		classParts.simpleNameComplete = classParts.simpleName;

		if(genericValue != null) {
			String[] genericParts = StringUtils.split(genericValue, ",");
			classParts.canonicalNameGeneric = "";
			classParts.simpleNameGeneric = "";
			for(int i = 0; i < genericParts.length; i++) {
				String canonicalNameGenericPart = StringUtils.trim(genericParts[i]);

				ClassParts partClassParts = ClassParts.initClassParts(siteConfig, canonicalNameGenericPart, languageName);
				if(i > 0) {
					classParts.simpleNameGeneric += ", ";
					classParts.canonicalNameGeneric += ", ";
				}

				classParts.simpleNameGeneric += partClassParts.simpleNameComplete;
				classParts.canonicalNameGeneric += partClassParts.canonicalNameComplete;
			}
			classParts.simpleNameComplete = classParts.simpleName + "<" + classParts.simpleNameGeneric + ">";
			classParts.canonicalNameComplete = classParts.canonicalName + "<" + classParts.canonicalNameGeneric + ">";
		}
		return classParts;
	}

	public static String concat(String...values) throws Exception { 
		String o = Stream.of(values).collect(Collectors.joining());
		return o;
	}

	@Override()
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("canonicalNameComplete: ").append(canonicalNameComplete).append("\n");
		b.append("canonicalName: ").append(canonicalName).append("\n");
		b.append("simpleName: ").append(simpleName).append("\n");
		b.append("canonicalNameGeneric: ").append(canonicalNameGeneric).append("\n");
		b.append("simpleNameComplete: ").append(simpleNameComplete).append("\n");
		b.append("simpleNameGeneric: ").append(simpleNameGeneric).append("\n");
		b.append("etendGen: ").append(etendGen).append("\n");
		return b.toString();
	}
}
