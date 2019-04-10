package org.computate.frFR.java; 

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**   
 * NomCanonique.enUS: org.computate.enUS.java.WriteApiClass
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */     
public class EcrireApiClasse extends EcrireGenClasse {   
//
//	/**
//	 * Var.enUS: writerApiPackageInfo
//	 */
//	protected ToutEcrivain auteurApiEnsembleInfo;

	/**
	 * Var.enUS: writerApiServiceImpl
	 */
	protected ToutEcrivain auteurApiServiceImpl;

	/**
	 * Var.enUS: writerGenApiServiceImpl
	 */
	protected ToutEcrivain auteurGenApiServiceImpl;

	/**
	 * Var.enUS: writerGenApiService
	 */  
	protected ToutEcrivain auteurGenApiService;

	/////////////////////
	// classeNomSimple //
	/////////////////////

	/**
	 * Var.enUS: classSimpleNameApiPackageInfo
	 */
	protected String classeNomSimpleApiEnsembleInfo;

	/**
	 * Var.enUS: classSimpleNameGenApiServiceImpl
	 */
	protected String classeNomSimpleGenApiServiceImpl;

	/**
	 * Var.enUS: classSimpleNameApiServiceImpl
	 */
	protected String classeNomSimpleApiServiceImpl;

	/**
	 * Var.enUS: classSimpleNameGenApiService
	 */
	protected String classeNomSimpleGenApiService;

	////////////////////////
	// classeNomCanonique //
	////////////////////////

	/**
	 * Var.enUS: classCanonicalNameApiPackageInfo
	 */
	protected String classeNomCanoniqueApiEnsembleInfo;

	/**
	 * Var.enUS: classCanonicalNameGenApiServiceImpl
	 */
	protected String classeNomCanoniqueGenApiServiceImpl;

	/**
	 * Var.enUS: classCanonicalNameApiServiceImpl
	 */
	protected String classeNomCanoniqueApiServiceImpl;

	/**
	 * Var.enUS: classCanonicalNameGenApiService
	 */
	protected String classeNomCanoniqueGenApiService;

	/**
	 * Var.enUS: classPageSimple
	 */
	protected Boolean classePageSimple;

	/**
	 * Var.enUS: apiCodeClassBegin
	 * Param1.var.enUS: languageName
	 * r: auteurGenApiServiceImpl
	 * r.enUS: writerGenApiServiceImpl
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * r: auteurApiServiceGenClasse
	 * r.enUS: writerApiServiceGenClass
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeImporationsGenApi
	 * r.enUS: classImportsGenApi
	 * r: classeImportation
	 * r.enUS: classImport
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 */
	public void apiCodeClasseDebut(String langueNom) throws Exception {
//		l();
//		tl(1, "public static final String VAL_nomCanonique", classeNomSimple, " = \"", classeNomCanonique, "\";");
//		tl(1, "public static final String VAL_virguleEspace = \", \";");
//		tl(1, "public static final String VAL_citation = \"\\\"\";");
//		tl(1, "public static final String VAL_citationDeuxPointsEspaceCitation = \"\\\": \\\"\";");
//		tl(1, "public static final String VAL_citationDeuxPointsEspace = \"\\\": \";");
//		tl(1, "public static final String VAL_citationLigne = \"\\\"\\n\";");
//		tl(1, "public static final String VAL_ligne = \"\\n\";");
//		tl(1, "public static final String VAL_citationVirguleEspaceCitation = \"\\\", \\\"\";");
//		tl(1, "public static final String VAL_citationDeuxPointsEspaceGuillmets = \"\\\": [\";");
//		tl(1, "public static final String VAL_guillmetsFin = \"]\";");
	}
//
//	/**
//	 * Var.enUS: writeApiPackageInfo
//	 * Param1.var.enUS: languageName
//	 * 
//	 * r: auteurApiEnsembleInfo
//	 * r.enUS: writerApiPackageInfo
//	 * r: classeNomSimple
//	 * r.enUS: classSimpleName
//	 * r: classeNomEnsemble
//	 * r.enUS: classPackageName
//	 **/
//	public void ecrireApiEnsembleInfo(String langueNom) throws Exception {
//		if(auteurApiEnsembleInfo != null) {
//			auteurApiEnsembleInfo.l("@ModuleGen(name=\"", classeNomSimple, "Api", "\", groupPackage=\"", classeNomEnsemble, "\")");
//			auteurApiEnsembleInfo.l("package ", classeNomEnsemble, ";");
//			auteurApiEnsembleInfo.l();
//			auteurApiEnsembleInfo.l("import io.vertx.codegen.annotations.ModuleGen;");
//
//			auteurApiEnsembleInfo.flushClose();
//		}
//	}

	/**
	 * Var.enUS: writeGenApiService
	 * Param1.var.enUS: languageName
	 * 
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: operationRequete
	 * r.enUS: operationRequest
	 * r: gestionnaireResultat
	 * r.enUS: resultHandler
	 * r: // Une méthode d'usine pour créer une instance et un proxy. 
	 * r.enUS: // A factory method to create an instance and a proxy. 
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: classeApiOperationIdMethode
	 * r.enUS: classApiOperationIdMethod
	 * r: classeApiOperationId
	 * r.enUS: classApiOperationId
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * 
	 * r: recherche
	 * r.enUS: search
	 **/ 
	public void ecrireGenApiService(String langueNom) throws Exception {
		if(auteurGenApiService != null) {
			auteurGenApiService.l("package ", classeNomEnsemble, ";");
			auteurGenApiService.l();
			auteurGenApiService.l("import ", classePartsSiteContexte.documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string"), ";");
//			auteurGenApiService.l("import ", classeNomEnsemble, ".", classeNomSimple, "ApiServiceVertxEBProxy;");
			auteurGenApiService.l("import io.vertx.codegen.annotations.ProxyGen;");
			auteurGenApiService.l("import io.vertx.ext.web.api.generator.WebApiServiceGen;");
			auteurGenApiService.l("import io.vertx.serviceproxy.ServiceBinder;");
			auteurGenApiService.l("import io.vertx.core.AsyncResult;");
			auteurGenApiService.l("import io.vertx.core.Handler;");
			auteurGenApiService.l("import io.vertx.core.Vertx;");
			auteurGenApiService.l("import io.vertx.core.json.JsonObject;");
			auteurGenApiService.l("import io.vertx.core.json.JsonArray;");
			auteurGenApiService.l("import io.vertx.ext.web.api.OperationRequest;");
			auteurGenApiService.l("import io.vertx.ext.web.api.OperationResponse;");
			auteurGenApiService.l();
			auteurGenApiService.l("/**");
			auteurGenApiService.l(" * Traduire: false");
			auteurGenApiService.l(" * Gen: false");
			auteurGenApiService.l(" **/");
			auteurGenApiService.l("@WebApiServiceGen");
			auteurGenApiService.l("@ProxyGen");
			auteurGenApiService.s("public interface ", classeNomSimpleGenApiService, " {");
			auteurGenApiService.l();
			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
			auteurGenApiService.tl(1, "static void enregistrerService(", classePartsSiteContexte.nomSimple(langueNom), " siteContexte, Vertx vertx) {");
			auteurGenApiService.tl(2, "new ServiceBinder(vertx).setAddress(", q(langueNom, classeNomSimple), ").register(", classeNomSimpleGenApiService, ".class, new ", classeNomSimpleApiServiceImpl, "(siteContexte));");
			auteurGenApiService.tl(1, "}");
			auteurGenApiService.l();
			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
			auteurGenApiService.tl(1, "static ", classeNomSimpleGenApiService, " creer(", classePartsSiteContexte.nomSimple(langueNom), " siteContexte, Vertx vertx) {");
			auteurGenApiService.tl(2, "return new ", classeNomSimpleApiServiceImpl, "(siteContexte);");
			auteurGenApiService.tl(1, "}");
			auteurGenApiService.l();
			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
			auteurGenApiService.tl(1, "static ", classeNomSimpleGenApiService, " creerProxy(Vertx vertx, String addresse) {");
			auteurGenApiService.tl(2, "return new ", classeNomSimpleGenApiService, "VertxEBProxy(vertx, addresse);");
			auteurGenApiService.tl(1, "}");
			auteurGenApiService.l();
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_stored_string");
				String classePageNomCanoniqueMethode = (String)classeDoc.get("classePageNomCanonique" + classeApiMethode + "_stored_string");
				String classePageLangueNom = (String)classeDoc.get("classePageLangueNom" + classeApiMethode + "_stored_string");

				if(classePageLangueNom == null || classePageLangueNom.equals(langueNom)) {
					if(classePageNomCanoniqueMethode != null) {
						auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "Id(");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
							auteurGenApiService.s("JsonObject body, ");
						auteurGenApiService.l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);");
					}
	
					auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						auteurGenApiService.s("JsonObject body, ");
					auteurGenApiService.l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);");
				}
			}
			auteurGenApiService.tl(0, "}");

			auteurGenApiService.flushClose();
		}
	}

	/**
	 * Var.enUS: writeApiServiceImpl
	 * Param1.var.enUS: languageName
	 * 
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * 
	 **/
	public void ecrireApiServiceImpl(String langueNom) throws Exception {
		if(auteurApiServiceImpl != null) {
			auteurApiServiceImpl.l("package ", classeNomEnsemble, ";");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.l("import ", classePartsSiteContexte.documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string"), ";");
//			auteurGenApiService.l("import ", classeNomEnsemble, ".", classeNomSimple, "ApiServiceVertxEBProxy;");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.l("/**");
			auteurApiServiceImpl.l(" * Traduire: false");
			auteurApiServiceImpl.l(" **/");
			auteurApiServiceImpl.l("public class ", classeNomSimpleApiServiceImpl, " extends ", classeNomSimpleGenApiServiceImpl, " {");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.tl(1, "public ", classeNomSimpleApiServiceImpl, "(", classePartsSiteContexte.nomSimple(langueNom), " siteContexte) {");
			auteurApiServiceImpl.tl(2, "super(siteContexte);");
			auteurApiServiceImpl.tl(1, "}");
			auteurApiServiceImpl.l("}");

			auteurApiServiceImpl.flushClose();
		}
	}

	/** 
	 * Var.enUS: writeGenApiServiceImpl
	 * Param1.var.enUS: languageName
	 * 
	 * r: classeCheminGenApiServiceImpl
	 * r.enUS: classPathGenApiServiceImpl
	 * r: classeCheminApiServiceImpl
	 * r.enUS: classPathApiServiceImpl
	 * r: classeCheminGenApiService
	 * r.enUS: classPathGenApiService
	 * 
	 * r: auteurGenApiServiceImpl
	 * r.enUS: writerGenApiServiceImpl
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * 
	 * r: wApiEntites
	 * r.enUS: wApiEntities
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * 
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRoles
	 * r.enUS: classRoles
	 * 
	 * r: classeFiltresTrouves
	 * r.enUS: classFiltersFound
	 * r: classeFiltre
	 * r.enUS: classFilter
	 * 
	 * r: wApiGenererGet
	 * r.enUS: wApiGenerateGet
	 * r: wApiGenererPatch
	 * r.enUS: wApiGeneratePatch
	 * r: classeCheminApiGen
	 * r.enUS: classPathApiGen
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: gestionnaireEvenements
	 * r.enUS: eventHandler
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: wApiGenererPost
	 * r.enUS: wApiGeneratePost
	 * r: // Une méthode d'usine pour créer une instance et un proxy. 
	 * r.enUS: // A factory method to create an instance and a proxy. 
	 * r: creer
	 * r.enUS: create
	 * r: addresse
	 * r.enUS: address
	 * r: operationRequete
	 * r.enUS: operationRequest
	 * r: gestionnaireResultat
	 * r.enUS: resultHandler
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: classeImportationsGenApi
	 * r.enUS: classImportsGenApi
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeApiOperationIdMethode
	 * r.enUS: classApiOperationIdMethod
	 * r: classeApiOperationId
	 * r.enUS: classApiOperationId
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: paramRequete
	 * r.enUS: queryParam
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: paramNom
	 * r.enUS paramName
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: paramValeursObjet
	 * r.enUS: paramValuesObject
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: classePageNomSimpleMethode
	 * r.enUS: classPageSimpleNameMethod
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: objetJson
	 * r.enUS: jsonObject
	 * r: ObjetJson
	 * r.enUS: JsonObject
	 * r: siteUrlBase
	 * r.enUS: siteBaseUrl
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: connexionSql
	 * r.enUS: sqlConnection
	 * r: reponseOperation
	 * r.enUS: operationResponse
	 * r: utilisateurValeur
	 * r.enUS: userValue
	 * r: utilisateurPk
	 * r.enUS: userPk
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: utilisateurId
	 * r.enUS: userId
	 * 
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: Ecrire: 
	 * r.enUS: Write: 
	 * r: recherche
	 * r.enUS: search
	 * r: auteurApi
	 * r.enUS: writerApi
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 * r: creer
	 * r.enUS: create
	 * r: reponse
	 * r.enUS: response
	 * 
	 */ 
	public void ecrireGenApiServiceImpl(String langueNom) throws Exception {

		if(auteurGenApiServiceImpl != null) {
			o = auteurGenApiServiceImpl;
	
			l("package ", classeNomEnsemble, ";");
			l();
			if(classeImportationsGenApi.size() > 0) { 
				for(String classeImportation : classeImportationsGenApi) {
					l("import ", classeImportation, ";");
				}
				l();
			}

			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNom + "_indexed_string:" + fqClassesSuperEtMoi);
				QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList rechercheListe = rechercheReponse.getResults();
				Integer rechercheLignes = rechercheSolr.getRows();
	
				if(rechercheListe.size() > 0) {
					for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
						for(Integer j = 0; j < rechercheListe.size(); j++) {
							SolrDocument doc = rechercheListe.get(j);
							entiteVar = (String)doc.get("entiteVar_" + langueNom + "_stored_string");
							entiteVarCapitalise = (String)doc.get("entiteVarCapitalise_" + langueNom + "_stored_string");
							entiteAttribuer = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuer_stored_boolean"));
							entiteAttribuerVar = (String)doc.get("entiteAttribuerVar_" + langueNom + "_stored_string");
							entiteDefinir = (Boolean)doc.get("entiteDefinir_stored_boolean");
							entiteSuffixeType = (String)doc.get("entiteSuffixeType_stored_string");
							entiteIndexe = (Boolean)doc.get("entiteIndexe_stored_boolean");
							entiteStocke = (Boolean)doc.get("entiteStocke_stored_boolean");
							entiteSolrNomCanonique = (String)doc.get("entiteSolrNomCanonique_stored_string");
							entiteSolrNomSimple = (String)doc.get("entiteSolrNomSimple_stored_string");
							entiteNomSimpleVertxJson = (String)doc.get("entiteNomSimpleVertxJson_stored_string");
							entiteNomCanoniqueVertxJson = (String)doc.get("entiteNomCanoniqueVertxJson_stored_string");
							entiteListeNomSimpleVertxJson = (String)doc.get("entiteListeNomSimpleVertxJson_stored_string");
							entiteListeNomCanoniqueVertxJson = (String)doc.get("entiteListeNomCanoniqueVertxJson_stored_string");
							entiteNomCanonique = (String)doc.get("entiteNomCanonique_" + langueNom + "_stored_string");
							entiteNomCanoniqueGenerique = (String)doc.get("entiteNomCanoniqueGenerique_" + langueNom + "_stored_string");
							entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
							entiteNomSimpleCompletGenerique = (String)doc.get("entiteNomSimpleCompletGenerique_" + langueNom + "_stored_string");
							entiteNomSimple = (String)doc.get("entiteNomSimple_" + langueNom + "_stored_string");
	
							/////////////////
							// codeApiGet //
							/////////////////

							if(classeIndexe && entiteIndexe) {
								wApiGet.tl(3, "case \"", entiteVar, "\":");
								wApiGet.tl(4, "return \"", entiteVar, "_indexed", entiteSuffixeType, "\";");
							}
							
							///////////////////////
							// codeApiGenererGet //
							///////////////////////
							o = wApiGenererGet;
							if(classeIndexe && entiteStocke) {
				//				tl(4, "if(", q(entiteVar, "_stored", entiteSuffixeType), ".equals(entiteVarStocke)) {");
								if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
									if(VAL_nomCanoniqueBoolean.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Boolean)entiteValeur).toString());");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Long)entiteValeur).toString());");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(BigDecimal.valueOf((Double)entiteValeur).toString());");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Double)entiteValeur).toString());");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Float)entiteValeur).toString());");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Integer)entiteValeur).toString());");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " entiteValeurs = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, entiteNumero++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "int k = 0;");
										tl(5, "while(entiteValeur != null) {");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(((String)entiteValeur));");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "entiteValeur = entiteValeurs.iterator().hasNext() ? entiteValeurs.iterator().next() : null;");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
								}
								else {
									l();
									tl(4, "entiteValeur = o.get", entiteVarCapitalise, "();");
				//					tl(4, "entiteValeur = Optional.ofNullable(documentSolr.getFieldValues(", q(entiteVar, "_stored", entiteSuffixeType), ")).map(Collection<Object>::stream).orElseGet(Stream::empty).findFirst().orElse(null);");
				//					tl(4, "entiteValeur = documentSolr.getFieldValues(", q(entiteVar, "_stored", entiteSuffixeType), ").stream().findFirst().orElse(null);");
				//					tl(5, "entiteValeur = documentSolr.getFieldValues(", q(entiteVar, "_stored", entiteSuffixeType), ").stream().findFirst().orElse(null);");
									tl(4, "if(entiteValeur != null)");
									if (VAL_nomCanoniqueBoolean.equals(entiteSolrNomCanonique)) {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
				
										// tomorrow put this line everywhere. 
										tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", entiteValeur);");
									} else if (VAL_nomCanoniqueDate.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueTimestamp.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(entiteValeur));");
										} else if (VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime());");
											tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(entiteValeur));");
										} else if (VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(entiteValeur));");
										} else if (VAL_nomCanoniqueLocalDate.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());");
											tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(entiteValeur));");
										} else {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(entiteValeur));");
										}
									} else if (VAL_nomCanoniqueLong.equals(entiteSolrNomCanonique)) {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
										tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", entiteValeur);");
									} else if (VAL_nomCanoniqueDouble.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueBigDecimal.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
											tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", entiteValeur);");
										}
										else {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
											tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", entiteValeur);");
										}
									} else if (VAL_nomCanoniqueFloat.equals(entiteSolrNomCanonique)) {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
										tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", entiteValeur);");
									} else if (VAL_nomCanoniqueInteger.equals(entiteSolrNomCanonique)) {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
										tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", entiteValeur);");
									}
									else {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
										tl(5, "w.tl(3, entiteNumero++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(entiteValeur));");
									}
								}
				//				tl(3, ");");
				//				tl(3, "}");
							}
					
							////////////////////////
							// codeApiGenererPatch //
							////////////////////////
							o = wApiGenererPatch;

							if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
								Integer tBase = 3;
								if(BooleanUtils.isTrue(entiteAttribuer)) {
									if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
						
										if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
											tl(tBase + 2, "case \"add", entiteVarCapitalise, "\":");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_addA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", requeteJson.get", entiteListeNomSimpleVertxJson, "(methodeNom)", "));");
					
											tl(tBase + 2, "case \"addAll", entiteVarCapitalise, "\":");
											tl(tBase + 3, entiteNomSimpleVertxJson, " addAll", entiteVarCapitalise, "Valeurs = requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom);");
											tl(tBase + 3, "for(Integer i = 0; i <  addAll", entiteVarCapitalise, "Valeurs.size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_addA);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", addAll", entiteVarCapitalise, "Valeurs.get", entiteListeNomSimpleVertxJson, "(i)", "));");
											tl(tBase + 3, "}");
						
											tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
											tl(tBase + 3, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, "Valeurs = requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom);");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_clearA1);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom)", "));");
					
											tl(tBase + 3, "for(Integer i = 0; i <  set", entiteVarCapitalise, "Valeurs.size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_addA);");
											tl(tBase + 4, "patchSqlParams.set(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", addAll", entiteVarCapitalise, "Valeurs.get", entiteListeNomSimpleVertxJson, "(i)", "));");
											tl(tBase + 3, "}");
										}
										else {
											tl(tBase + 2, "case \"add", entiteVarCapitalise, "\":");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_addA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", requeteJson.get", entiteListeNomSimpleVertxJson, "(methodeNom)", "));");
					
											tl(tBase + 2, "case \"addAll", entiteVarCapitalise, "\":");
											tl(tBase + 3, entiteNomSimpleVertxJson, " addAll", entiteVarCapitalise, "Valeurs = requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom);");
											tl(tBase + 3, "for(Integer i = 0; i <  addAll", entiteVarCapitalise, "Valeurs.size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_setA2);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", ", "addAll", entiteVarCapitalise, "Valeurs.get", entiteListeNomSimpleVertxJson, "(i)", q(entiteVar), ", ", classeVarClePrimaire, "));");
											tl(tBase + 3, "}");
						
											tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
											tl(tBase + 3, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, "Valeurs = requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom);");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_clearA2);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", requeteJson.get", entiteListeNomSimpleVertxJson, "(methodeNom)", ", ", q(entiteVar), ", ", classeVarClePrimaire, "));");
					
											tl(tBase + 3, "for(Integer i = 0; i <  set", entiteVarCapitalise, "Valeurs.size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_setA2);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", set", entiteVarCapitalise, "Valeurs.get", entiteListeNomSimpleVertxJson, "(i)", q(entiteVar), ", ", classeVarClePrimaire, "));");
											tl(tBase + 3, "}");
										}
									}
									else {
						
										tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
										if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
											tl(tBase + 3, "o2.set", entiteVarCapitalise, "(requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom));");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_setA1);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", o2.get", entiteVarCapitalise, "()));");
										}
										else {
											tl(tBase + 3, "o2.set", entiteVarCapitalise, "(requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom));");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_setA2);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", o2.get", entiteVarCapitalise, "()", ", ", q(entiteVar), ", ", classeVarClePrimaire, "));");
										}
									}
						
									tl(tBase + 7, "break;");
								}
								else if(BooleanUtils.isTrue(entiteDefinir)) {
									if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
						
										tl(tBase + 2, "case \"add", entiteVarCapitalise, "\":");
										tl(tBase + 3, "o2.set", entiteVarCapitalise, "(requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom));");
										tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_addA);");
										tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", o2.get", entiteVarCapitalise, "()", ", ", classeVarClePrimaire, "));");
						
										tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
										tl(tBase + 3, "o2.set", entiteVarCapitalise, "(requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom));");
										tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_setD);");
										tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", o2.get", entiteVarCapitalise, "(), ", classeVarClePrimaire, "));");
									}
									else {
						
										tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
										tl(tBase + 3, "o2.set", entiteVarCapitalise, "(requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom));");
										tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_setD);");
										tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", o2.get", entiteVarCapitalise, "(), ", classeVarClePrimaire, "));");
									}
						
									tl(tBase + 3, "break;");
								}
							}	
						}
						rechercheSolr.setStart(i.intValue() + rechercheLignes);
						rechercheReponse = clientSolrComputate.query(rechercheSolr);
						rechercheListe = rechercheReponse.getResults();
					}
				}
			}
			wApiGet.flushClose();
			wApiGenererGet.flushClose();
	
			o = auteurGenApiServiceImpl;
			tl(0, "");
			l("/**");
			l(" * Traduire: false");
			l(" **/");
			s("public class ", classeNomSimpleGenApiServiceImpl);
			s(" implements ", classeNomSimpleGenApiService);
			l(" {");
			l();
			tl(1, "private static final Logger LOGGER = LoggerFactory.getLogger(", classeNomSimpleGenApiServiceImpl, ".class);");
			l();
			tl(1, "private static final String SERVICE_ADDRESS = \"", classeNomSimpleApiServiceImpl, "\";");
			l();
			tl(1, "protected ", classePartsSiteContexte.nomSimple(langueNom), " siteContexte;");
			l();
			tl(1, "public ", classeNomSimpleGenApiServiceImpl, "(", classePartsSiteContexte.nomSimple(langueNom), " siteContexte) {");
			tl(2, "this.siteContexte = siteContexte;");
			tl(2, classeNomSimpleGenApiService, " service = ", classeNomSimpleGenApiService, ".creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);");
			tl(1, "}");

			for(String classeApiMethode : classeApiMethodes) {
				String classePageNomCanoniqueMethode = (String)classeDoc.get("classePageNomCanonique" + classeApiMethode + "_stored_string");
				String classePageNomSimpleMethode = (String)classeDoc.get("classePageNomSimple" + classeApiMethode + "_stored_string");
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_stored_string");
				String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia" + classeApiMethode + "_stored_string");
				String classePageLangueNom = (String)classeDoc.get("classePageLangueNom" + classeApiMethode + "_stored_string");
				if(classePageLangueNom == null || classePageLangueNom.equals(langueNom)) {
					l();
					tl(1, "// ", classeApiMethode, " //");
					if(classePageNomCanoniqueMethode != null) {
						l();
						tl(1, "@Override");
						t(1, "public void ", classeApiOperationIdMethode, "Id(");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
							s("JsonObject body, ");
						l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
						tl(2, classeApiOperationIdMethode, "(operationRequete, gestionnaireEvenements);");
						tl(1, "}");
					}
					l();
					tl(1, "@Override");
					t(1, "public void ", classeApiOperationIdMethode, "(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						s("JsonObject body, ");
					l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
	
					if(classeApiMethode.contains("POST")) {
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(siteContexte, operationRequete, body);");
						tl(3, "sql", classeNomSimple, "(requeteSite, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "creer", classeApiMethode, classeNomSimple, "(requeteSite, b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, classeNomSimple, " ", StringUtils.uncapitalize(classeNomSimple), " = b.result();");
						tl(7, "sql", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "definir", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "attribuer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, "indexer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", f -> {");
						tl(14, "if(f.succeeded()) {");
						tl(15, "reponse200", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", g -> {");
						tl(16, "if(f.succeeded()) {");
						tl(17, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(17, "connexionSql.commit(h -> {");
						tl(18, "if(a.succeeded()) {");
						tl(19, "connexionSql.close(i -> {");
						tl(20, "if(a.succeeded()) {");
						tl(21, "gestionnaireEvenements.handle(Future.succeededFuture(g.result()));");
						tl(20, "} else {");
						tl(21, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, i);");
						tl(20, "}");
						tl(19, "});");
						tl(18, "} else {");
						tl(19, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, h);");
						tl(18, "}");
						tl(17, "});");
						tl(16, "} else {");
						tl(17, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, g);");
						tl(16, "}");
						tl(15, "});");
						tl(14, "} else {");
						tl(15, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, f);");
						tl(14, "}");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, e);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "erreur", classeNomSimple, "(null, gestionnaireEvenements, Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classeApiMethode.contains("PATCH")) {
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(siteContexte, operationRequete, body);");
						tl(3, "sql", classeNomSimple, "(requeteSite, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "utilisateur", classeNomSimple, "(requeteSite, b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "recherche", classeNomSimple, "(requeteSite, false, true, ", "null", ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, " = c.result();");
						tl(9, "liste", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(11, "connexionSql.commit(e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, "connexionSql.close(f -> {");
						tl(14, "if(f.succeeded()) {");
						tl(15, "gestionnaireEvenements.handle(Future.succeededFuture(d.result()));");
						tl(14, "} else {");
						tl(15, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, f);");
						tl(14, "}");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, e);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "erreur", classeNomSimple, "(null, gestionnaireEvenements, Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classeApiMethode.contains("Recherche")) {
						if(classePageNomSimpleMethode == null) {
							tl(2, "try {");
							tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(siteContexte, operationRequete);");
							tl(3, "recherche", classeNomSimple, "(requeteSite, false, true, ", "null", ", a -> {");
							tl(4, "if(a.succeeded()) {");
							tl(5, "ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, " = a.result();");
							tl(5, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ", b -> {");
							tl(6, "if(b.succeeded()) {");
							tl(7, "gestionnaireEvenements.handle(Future.succeededFuture(b.result()));");
							tl(6, "} else {");
							tl(7, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
							tl(6, "}");
							tl(5, "});");
							tl(4, "} else {");
							tl(5, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
							tl(4, "}");
							tl(3, "});");
							tl(2, "} catch(Exception e) {");
							tl(3, "erreur", classeNomSimple, "(null, gestionnaireEvenements, Future.failedFuture(e));");
							tl(2, "}");
						}
						else {
							tl(2, "try {");
							tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(siteContexte, operationRequete);");
							tl(3, "sql", classeNomSimple, "(requeteSite, a -> {");
							tl(4, "if(a.succeeded()) {");
							tl(5, "utilisateur", classeNomSimple, "(requeteSite, b -> {");
							tl(6, "if(b.succeeded()) {");
							tl(7, "recherche", classeNomSimple, "(requeteSite, false, true, ", q(classeApiUriMethode), ", c -> {");
							tl(8, "if(c.succeeded()) {");
							tl(9, "ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, " = c.result();");
							tl(9, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ", d -> {");
							tl(10, "if(d.succeeded()) {");
							tl(11, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
							tl(11, "connexionSql.commit(e -> {");
							tl(12, "if(e.succeeded()) {");
							tl(13, "connexionSql.close(f -> {");
							tl(14, "if(f.succeeded()) {");
							tl(15, "gestionnaireEvenements.handle(Future.succeededFuture(d.result()));");
							tl(14, "} else {");
							tl(15, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, f);");
							tl(14, "}");
							tl(13, "});");
							tl(12, "} else {");
							tl(13, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, e);");
							tl(12, "}");
							tl(11, "});");
							tl(10, "} else {");
							tl(11, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, d);");
							tl(10, "}");
							tl(9, "});");
							tl(8, "} else {");
							tl(9, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, c);");
							tl(8, "}");
							tl(7, "});");
							tl(6, "} else {");
							tl(7, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
							tl(6, "}");
							tl(5, "});");
							tl(4, "} else {");
							tl(5, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
							tl(4, "}");
							tl(3, "});");
							tl(2, "} catch(Exception e) {");
							tl(3, "erreur", classeNomSimple, "(null, gestionnaireEvenements, Future.failedFuture(e));");
							tl(2, "}");
						}
					}
					else if(classeApiMethode.contains("GET")) {
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(siteContexte, operationRequete);");
						tl(3, "recherche", classeNomSimple, "(requeteSite, false, true, ", "null", ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, " = a.result();");
						tl(5, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "gestionnaireEvenements.handle(Future.succeededFuture(b.result()));");
						tl(6, "} else {");
						tl(7, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "erreur", classeNomSimple, "(null, gestionnaireEvenements, Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classeApiMethode.contains("PUT")) {
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(siteContexte, operationRequete, body);");
						tl(3, "sql", classeNomSimple, "(requeteSite, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "remplacer", classeApiMethode, classeNomSimple, "(requeteSite, b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, classeNomSimple, " ", StringUtils.uncapitalize(classeNomSimple), " = b.result();");
						tl(7, "sql", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "definir", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "attribuer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, "indexer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", f -> {");
						tl(14, "if(f.succeeded()) {");
						tl(15, "reponse200", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", g -> {");
						tl(16, "if(g.succeeded()) {");
						tl(17, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(17, "connexionSql.commit(h -> {");
						tl(18, "if(a.succeeded()) {");
						tl(19, "connexionSql.close(i -> {");
						tl(20, "if(a.succeeded()) {");
						tl(21, "gestionnaireEvenements.handle(Future.succeededFuture(g.result()));");
						tl(20, "} else {");
						tl(21, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, i);");
						tl(20, "}");
						tl(19, "});");
						tl(18, "} else {");
						tl(19, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, h);");
						tl(18, "}");
						tl(17, "});");
						tl(16, "} else {");
						tl(17, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, g);");
						tl(16, "}");
						tl(15, "});");
						tl(14, "} else {");
						tl(15, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, f);");
						tl(14, "}");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, e);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "erreur", classeNomSimple, "(null, gestionnaireEvenements, Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classeApiMethode.contains("DELETE")) {
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(siteContexte, operationRequete);");
						tl(3, "sql", classeNomSimple, "(requeteSite, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "recherche", classeNomSimple, "(requeteSite, false, true, ", "null", ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, " = b.result();");
						tl(7, "supprimer", classeApiMethode, classeNomSimple, "(requeteSite, c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "reponse200", classeApiMethode, classeNomSimple, "(requeteSite, d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(11, "connexionSql.commit(e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, "connexionSql.close(f -> {");
						tl(14, "if(f.succeeded()) {");
						tl(15, "gestionnaireEvenements.handle(Future.succeededFuture(d.result()));");
						tl(14, "} else {");
						tl(15, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, f);");
						tl(14, "}");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, e);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "erreur", classeNomSimple, "(null, gestionnaireEvenements, Future.failedFuture(e));");
						tl(2, "}");
					}
					tl(1, "}");
	
					if(classeApiMethode.contains("Recherche")) {
	//					l();
	//					tl(1, "public Future<OperationResponse> listeRecherche", classeNomSimple, "(ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, ") {");
	//					tl(2, "List<Future> futures = new ArrayList<>();");
	//					tl(2, "liste", classeNomSimple, ".getList().forEach(o -> {");
	//					tl(3, "futures.add(");
	//					tl(4, "sqlPATCH", classeNomSimple, "(o).compose(");
	//					tl(5, "b -> indexer", classeNomSimple, "(o)");
	//					tl(4, ")");
	//					tl(3, ");");
	//					tl(2, "});");
	//					tl(2, "Future<OperationResponse> future = CompositeFuture.all(futures).compose( a -> ");
	//					tl(3, "reponse200Recherche", classeNomSimple, "(liste", classeNomSimple, ")");
	//					tl(2, ");");
	//					tl(2, "return future;");
	//					tl(1, "}");
					}
					if(classeApiMethode.contains("POST")) {
						l();
						tl(1, "public void creer", classeApiMethode, classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite, Handler<AsyncResult<", classeNomSimple, ">> gestionnaireEvenements) {");
						tl(2, "try {");
						tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(3, "String utilisateurId = requeteSite.getUtilisateurId();");
						l();
						tl(3, "connexionSql.queryWithParams(");
						tl(5, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_creer");
						tl(5, ", new JsonArray(Arrays.asList(", classeNomSimple, ".class.getCanonicalName(), utilisateurId))");
						tl(5, ", creerAsync");
						tl(3, "-> {");
						tl(4, "JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);");
						tl(4, "Long ", classeVarClePrimaire, " = creerLigne.getLong(0);");
						tl(4, classeNomSimple, " o = new ", classeNomSimple, "();");
						tl(4, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
						tl(4, "o.initLoin", classeNomSimple, "(requeteSite);");
						tl(4, "gestionnaireEvenements.handle(Future.succeededFuture(o));");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("PATCH")) {
						l();
						tl(1, "public void liste", classeApiMethode, classeNomSimple, "(ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, ", Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, "liste", classeNomSimple, ".getList().forEach(o -> {");
						tl(3, "futures.add(");
						tl(4, "sql", classeApiMethode, classeNomSimple, "(o).compose(");
						tl(5, "a -> definir", classeApiMethode, classeNomSimple, "(a).compose(");
						tl(6, "b -> indexer", classeApiMethode, classeNomSimple, "(b)");
						tl(5, ")");
						tl(4, ")");
						tl(3, ");");
						tl(2, "});");
						tl(2, "CompositeFuture.all(futures).setHandler( a -> {");
						tl(3, "if(a.succeeded()) {");
						tl(4, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ", gestionnaireEvenements);");
						tl(3, "} else {");
						tl(4, "erreur", classeNomSimple, "(liste", classeNomSimple, ".getRequeteSite_(), gestionnaireEvenements, a);");
						tl(3, "}");
						tl(2, "});");
						tl(1, "}");
						l();
						tl(1, "public Future<", classeNomSimple, "> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
						tl(2, "Future<", classeNomSimple, "> future = Future.future();");
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = o.getRequeteSite_();");
						tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
						tl(3, "JsonObject requeteJson = requeteSite.getObjetJson();");
						tl(3, "StringBuilder patchSql = new StringBuilder();");
						tl(3, "List<Object> patchSqlParams = new ArrayList<Object>();");
						tl(3, "Set<String> methodeNoms = requeteJson.fieldNames();");
						tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
						l();
						tl(3, "patchSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_modifier);");
						tl(3, "patchSqlParams.addAll(Arrays.asList(pk, ", q(classeNomCanonique), "));");
						tl(3, "for(String methodeNom : methodeNoms) {");
						tl(4, "switch(methodeNom) {");
						s(wApiGenererPatch.toString());
						tl(4, "}");
						tl(3, "}");
						tl(3, "connexionSql.queryWithParams(");
						tl(5, "patchSql.toString()");
						tl(5, ", new JsonArray(patchSqlParams)");
						tl(5, ", patchAsync");
						tl(3, "-> {");
						tl(4, "o2.setRequeteSite_(o.getRequeteSite_());");
						tl(4, "o2.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
						tl(4, "future.complete(o2);");
						tl(3, "});");
						tl(3, "return future;");
						tl(2, "} catch(Exception e) {");
						tl(3, "return Future.failedFuture(e);");
						tl(2, "}");
						tl(1, "}");
						l();
						tl(1, "public Future<", classeNomSimple, "> definir", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
						tl(2, "Future<", classeNomSimple, "> future = Future.future();");
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = o.getRequeteSite_();");
						tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
						tl(3, "connexionSql.queryWithParams(");
						tl(5, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_definir");
						tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, ", ", classeVarClePrimaire, ", ", classeVarClePrimaire, "))");
						tl(5, ", definirAsync");
						tl(3, "-> {");
						tl(4, "if(definirAsync.succeeded()) {");
						tl(5, "for(JsonArray definition : definirAsync.result().getResults()) {");
						tl(6, "o.definirPourClasse(definition.getString(0), definition.getString(1));");
						tl(5, "}");
						tl(5, "future.complete(o);");
						tl(4, "} else {");
						tl(3, "future.fail(definirAsync.cause());");
						tl(4, "}");
						tl(3, "});");
						tl(3, "return future;");
						tl(2, "} catch(Exception e) {");
						tl(3, "return Future.failedFuture(e);");
						tl(2, "}");
						tl(1, "}");
						l();
						tl(1, "public Future<Void> indexer", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
						tl(2, "Future<Void> future = Future.future();");
						tl(2, "try {");
						tl(3, "o.initLoinPourClasse(o.getRequeteSite_());");
						tl(3, "o.indexerPourClasse();");
						tl(4, "future.complete();");
						tl(3, "return future;");
						tl(2, "} catch(Exception e) {");
						tl(3, "return Future.failedFuture(e);");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("PUT")) {
						l();
						tl(1, "public void remplacer", classeApiMethode, classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite, Handler<AsyncResult<", classeNomSimple, ">> gestionnaireEvenements) {");
						tl(2, "try {");
						tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(3, "String utilisateurId = requeteSite.getUtilisateurId();");
						tl(3, "Long pk = requeteSite.getRequetePk();");
						l();
						tl(3, "connexionSql.queryWithParams(");
						tl(5, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_vider");
						tl(5, ", new JsonArray(Arrays.asList(pk, ", classeNomSimple, ".class.getCanonicalName(), pk, pk, pk))");
						tl(5, ", remplacerAsync");
						tl(3, "-> {");
						tl(4, classeNomSimple, " o = new ", classeNomSimple, "();");
						tl(4, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
						tl(4, "gestionnaireEvenements.handle(Future.succeededFuture(o));");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT")) {
						l();
						tl(1, "public void sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = o.getRequeteSite_();");
						tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
						tl(3, "JsonObject jsonObject = requeteSite.getObjetJson();");
						tl(3, "StringBuilder postSql = new StringBuilder();");
						tl(3, "List<Object> postSqlParams = new ArrayList<Object>();");
						l();
						tl(3, "if(jsonObject != null) {");
						tl(4, "Set<String> entiteVars = jsonObject.fieldNames();");
						tl(4, "for(String entiteVar : entiteVars) {");
						tl(5, "switch(entiteVar) {");
						s(wApiGenererPost.toString());
						tl(5, "}");
						tl(4, "}");
						tl(3, "}");
						tl(3, "connexionSql.queryWithParams(");
						tl(5, "postSql.toString()");
						tl(5, ", new JsonArray(postSqlParams)");
						tl(5, ", postAsync");
						tl(3, "-> {");
						tl(4, "gestionnaireEvenements.handle(Future.succeededFuture());");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("GET")) {
					}
					if(classeApiMethode.contains("DELETE")) {
						l();
						tl(1, "public void supprimer", classeApiMethode, classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
						tl(2, "try {");
						tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
						tl(3, "String utilisateurId = requeteSite.getUtilisateurId();");
						tl(3, "Long pk = requeteSite.getRequetePk();");
						l();
						tl(3, "connexionSql.queryWithParams(");
						tl(5, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_supprimer");
						tl(5, ", new JsonArray(Arrays.asList(pk, ", classeNomSimple, ".class.getCanonicalName(), pk, pk, pk, pk))");
						tl(5, ", supprimerAsync");
						tl(3, "-> {");
						tl(4, "gestionnaireEvenements.handle(Future.succeededFuture());");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					l();
					t(1, "public void reponse200", classeApiMethode, classeNomSimple, "(");
	
					if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT"))
						s(classeNomSimple, " o");
					else if(classeApiMethode.contains("DELETE"))
						s("", classePartsRequeteSite.nomSimple(langueNom), " requeteSite");
					else
						s("ListeRecherche<", classeNomSimple, "> liste", classeNomSimple);
	
					l(", Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
	
					tl(2, "try {");
					tl(3, "Buffer buffer = Buffer.buffer();");
	
					if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT")) {
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = o.getRequeteSite_();");
					}
					else if(classeApiMethode.contains("Recherche") || classeApiMethode.contains("PATCH") || classeApiMethode.contains("GET")) {
						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = liste", classeNomSimple, ".getRequeteSite_();");
					}
					else {
					}
	
					t(3, "ToutEcrivain w = ToutEcrivain.creer(");
					if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT"))
						s("o.getRequeteSite_()");
					else if(classeApiMethode.contains("DELETE"))
						s("requeteSite");
					else
						s("liste", classeNomSimple, ".getRequeteSite_()");
					l(", buffer);");
					tl(3, "requeteSite.setW(w);");
	
	
					if(classeApiMethode.contains("GET")) {
						tl(3, "SolrDocumentList documentsSolr = liste", classeNomSimple, ".getSolrDocumentList();");
						l();
					}
					if(classeApiMethode.contains("Recherche")) {
					}
	
					if(classeApiMethode.contains("Recherche") || classeApiMethode.contains("GET")) {
					}
					else if(classeApiMethode.contains("DELETE")) {
					}
	
					if(classeApiMethode.contains("Recherche")) {
						if(classePageNomCanoniqueMethode != null) {
							tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
//							tl(3, "page.setPageUrl(\"", siteUrlBase, classeApiUri, "\");");
							tl(3, "SolrDocument pageDocumentSolr = new SolrDocument();");
							l();
							tl(3, "pageDocumentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
							tl(3, "page.setPageDocumentSolr(pageDocumentSolr);");
							tl(3, "page.setW(w);");
							if(!classePageSimple)
								tl(3, "page.setListe", classeNomSimple, "(liste", classeNomSimple, ");");
							tl(3, "page.initLoin", classePageNomSimpleMethode, "(requeteSite);");
							tl(3, "page.html();");
						}
						else {
							tl(3, "QueryResponse reponseRecherche = liste", classeNomSimple, ".getQueryResponse();");
							tl(3, "SolrDocumentList documentsSolr = liste", classeNomSimple, ".getSolrDocumentList();");
							tl(3, "Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());");
							tl(3, "Long millisTransmission = reponseRecherche.getElapsedTime();");
							tl(3, "Long numCommence = reponseRecherche.getResults().getStart();");
							tl(3, "Long numTrouve = reponseRecherche.getResults().getNumFound();");
							tl(3, "Integer numRetourne = reponseRecherche.getResults().size();");
							tl(3, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
							tl(3, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
							tl(3, "Exception exceptionRecherche = reponseRecherche.getException();");
							l();
							tl(3, "w.l(\"{\");");
							tl(3, "w.tl(1, ", q(q("numCommence"), ": "), ", numCommence);");
							tl(3, "w.tl(1, ", q(", ", q("numTrouve"), ": "), ", numTrouve);");
							tl(3, "w.tl(1, ", q(", ", q("numRetourne"), ": "), ", numRetourne);");
							tl(3, "w.tl(1, ", q(", ", q("tempsRecherche"), ": "), ", w.q(tempsRecherche));");
							tl(3, "w.tl(1, ", q(", ", q("tempsTransmission"), ": "), ", w.q(tempsTransmission));");
							tl(3, "w.tl(1, ", q(", ", q("liste"), ": ["), ");");
							tl(3, "for(int i = 0; i < liste", classeNomSimple, ".size(); i++) {");
							tl(4, classeNomSimple, " o = liste", classeNomSimple, ".getList().get(i);");
							tl(4, "Object entiteValeur;");
							tl(4, "Integer entiteNumero = 0;");
		//					tl(4, "List<String> champNoms = new ArrayList<>(documentSolr.getFieldNames());");
							l();
							tl(4, "w.t(2);");
							tl(4, "if(i > 0)");
							tl(5, "w.s(", q(", "), ");");
							tl(4, "w.l(", q("{"), ");");
		//					tl(4, "for(int j = 0; j < champNoms.size(); j++) {");
		//					tl(5, "String entiteVarStocke = champNoms.get(j);");
		//					tl(5, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entiteVarStocke));");
							s(wApiGenererGet.toString());
		//					tl(4, "}");
							l();
							tl(4, "w.tl(2, ", q("}"), ");");
							tl(3, "}");
							tl(3, "w.tl(1, ", q("]"), ");");
							tl(3, "if(exceptionRecherche != null) {");
							tl(4, "w.tl(1, ", q(", ", q("exceptionRecherche"), ": "), ", w.q(exceptionRecherche.getMessage()));");
							tl(3, "}");
							tl(3, "w.l(\"}\");");
						}
					}
					if(classeApiMethode.contains("GET")) {
						if(classePageNomCanoniqueMethode != null) {
							tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
//							tl(3, "page.setPageUrl(\"", siteUrlBase, classeApiUri, "\");");
							tl(3, "SolrDocument pageDocumentSolr = new SolrDocument();");
							tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = liste", classeNomSimple, ".getRequeteSite_();");
							l();
							tl(3, "pageDocumentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
							tl(3, "page.setPageDocumentSolr(pageDocumentSolr);");
							tl(3, "page.setW(w);");
							tl(3, "page.initLoin", classePageNomSimpleMethode, "(requeteSite);");
							tl(3, "page.html();");
						}
						else {
							tl(3, "if(liste", classeNomSimple, ".size() > 0) {");
							tl(4, "SolrDocument documentSolr = documentsSolr.get(0);");
							tl(4, classeNomSimple, " o = liste", classeNomSimple, ".get(0);");
							tl(4, "Object entiteValeur;");
							tl(4, "Integer entiteNumero = 0;");
							l();
							tl(4, "w.l(", q("{"), ");");
		//					tl(4, "for(int j = 0; j < champNoms.size(); j++) {");
		//					tl(5, "String entiteVarStocke = champNoms.get(j);");
		//					tl(5, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entiteVarStocke));");
							s(wApiGenererGet.toString());
		//					tl(4, "}");
							l();
							tl(4, "w.l(", q("}"), ");");
							tl(3, "}");
						}
					}
	
					if((classeApiMethode.contains("GET") || classeApiMethode.contains("Recherche")) && classePageNomCanoniqueMethode != null) {
						tl(3, "gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, \"OK\", buffer, new CaseInsensitiveHeaders())));");
					}
					else {
						tl(3, "gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));");
					}
	
					tl(2, "} catch(Exception e) {");
					tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
			}
	
			s(wApiEntites.toString());

			ToutEcrivain wVarIndexe = ToutEcrivain.create();
			ToutEcrivain wVarRecherche = ToutEcrivain.create();
			ToutEcrivain wVarSuggere = ToutEcrivain.create();
			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
				QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList rechercheListe = rechercheReponse.getResults();
				Integer rechercheLignes = rechercheSolr.getRows();
	
				if(rechercheListe.size() > 0) {
					for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
						for(Integer j = 0; j < rechercheListe.size(); j++) {
							SolrDocument entiteDocumentSolr = rechercheListe.get(j);
							entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNomActuel + "_stored_string");
							entiteSuffixeType = (String)entiteDocumentSolr.get("entiteSuffixeType_stored_string");
							entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
							entiteTexte = (Boolean)entiteDocumentSolr.get("entiteTexte_stored_boolean");
							entiteLangue = (String)entiteDocumentSolr.get("entiteLangue_stored_string");
							entiteSuggere = (Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean");

							if(classeIndexe) {
								if(entiteIndexe) {
									wVarIndexe.tl(3, "case \"", entiteVar, "\":");
									wVarIndexe.tl(4, "return \"", entiteVar, "_indexed", entiteSuffixeType, "\";");
								}
								if(entiteTexte && entiteLangue != null) {
									wVarRecherche.tl(3, "case \"", entiteVar, "\":");
									wVarRecherche.tl(4, "return \"", entiteVar, "_text_" + entiteLangue, "\";");
								}
								if(entiteSuggere) {
									wVarSuggere.tl(3, "case \"", entiteVar, "\":");
									wVarSuggere.tl(4, "return \"", entiteVar, "_suggested", "\";");
								}
							}
						}
						rechercheSolr.setStart(i.intValue() + rechercheLignes);
						rechercheReponse = clientSolrComputate.query(rechercheSolr);
						rechercheListe = rechercheReponse.getResults();
					}
				}
			}

			l();
			tl(1, "public String varIndexe", classeNomSimple, "(String entiteVar) {");
			tl(2, "switch(entiteVar) {");
			s(wVarIndexe);
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" n'est pas une entité indexé. \", entiteVar));");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public String varRecherche", classeNomSimple, "(String entiteVar) {");
			tl(2, "switch(entiteVar) {");
			s(wVarRecherche);
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" n'est pas une entité indexé. \", entiteVar));");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public String varSuggere", classeNomSimple, "(String entiteVar) {");
			tl(2, "switch(entiteVar) {");
			s(wVarSuggere);
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" n'est pas une entité indexé. \", entiteVar));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "// Partagé //");
			l();
			tl(1, "public void erreur", classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {");
			tl(2, "Throwable e = resultatAsync.cause();");
			tl(2, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(2, "OperationResponse reponseOperation = new OperationResponse(400, \"BAD REQUEST\", ");
			tl(3, "Buffer.buffer().appendString(");
			tl(4, "new JsonObject() {{");
			tl(5, "put(\"erreur\", new JsonObject() {{");
			tl(5, "put(\"message\", e.getMessage());");
			tl(5, "}});");
			tl(4, "}}.encodePrettily()");
			tl(3, ")");
			tl(3, ", new CaseInsensitiveHeaders()");
			tl(2, ");");
			tl(2, "if(requeteSite != null) {");
			tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(3, "if(connexionSql != null) {");
			tl(4, "connexionSql.rollback(a -> {");
			tl(5, "if(a.succeeded()) {");
			tl(6, "connexionSql.close(b -> {");
			tl(7, "if(a.succeeded()) {");
			tl(8, "gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));");
			tl(7, "} else {");
			tl(8, "gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));");
			tl(7, "}");
			tl(6, "});");
			tl(5, "} else {");
			tl(6, "gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "} else {");
			tl(4, "gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));");
			tl(3, "}");
			tl(2, "} else {");
			tl(3, "gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void sql", classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
			tl(2, "try {");
			tl(3, "SQLClient clientSql = requeteSite.getSiteContexte_().getClientSql();");
			l();
			tl(3, "clientSql.getConnection(sqlAsync -> {");
			tl(4, "if(sqlAsync.succeeded()) {");
			tl(5, "SQLConnection connexionSql = sqlAsync.result();");
			tl(5, "connexionSql.setAutoCommit(false, a -> {");
			tl(6, "if(a.succeeded()) {");
			tl(7, "requeteSite.setConnexionSql(connexionSql);");
			tl(7, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(6, "} else {");
			tl(7, "gestionnaireEvenements.handle(Future.failedFuture(a.cause()));");
			tl(6, "}");
			tl(5, "});");
			tl(4, "} else {");
			tl(5, "gestionnaireEvenements.handle(Future.failedFuture(sqlAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
	//		tl(1, "public ", classePartsRequeteSite.nomSimple(langueNom), " generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(", classePartsSiteContexte.nomSimple(langueNom), " siteContexte, RoutingContext contexteItineraire) {");
			tl(1, "public ", classePartsRequeteSite.nomSimple(langueNom), " generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(", classePartsSiteContexte.nomSimple(langueNom), " siteContexte, OperationRequest operationRequete) {");
			tl(2, "return generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(siteContexte, operationRequete, null);");
			tl(1, "}");
			l();
			tl(1, "public ", classePartsRequeteSite.nomSimple(langueNom), " generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(", classePartsSiteContexte.nomSimple(langueNom), " siteContexte, OperationRequest operationRequete, JsonObject body) {");
			tl(2, "Vertx vertx = siteContexte.getVertx();");
			tl(2, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = new ", classePartsRequeteSite.nomSimple(langueNom), "();");
			tl(2, "requeteSite.setObjetJson(body);");
			tl(2, "requeteSite.setVertx(vertx);");
	//		tl(2, "requeteSite.setContexteItineraire(contexteItineraire);");
			tl(2, "requeteSite.setSiteContexte_(siteContexte);");
			tl(2, "requeteSite.setConfigSite_(siteContexte.getConfigSite());");
			tl(2, "requeteSite.setOperationRequete(operationRequete);");
			tl(2, "requeteSite.initLoin", classePartsRequeteSite.nomSimple(langueNom), "(requeteSite);");
			l();
			tl(2, "return requeteSite;");
			tl(1, "}");
			l();
			tl(1, "public void utilisateur", classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
			tl(2, "try {");
			tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(3, "String utilisateurId = requeteSite.getUtilisateurId();");
			tl(3, "if(utilisateurId == null) {");
			tl(4, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(3, "} else {");
			tl(4, "connexionSql.queryWithParams(");
			tl(6, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_selectC");
			tl(6, ", new JsonArray(Arrays.asList(", q(classePartsUtilisateurSite.nomCanonique), ", utilisateurId))");
			tl(6, ", selectCAsync");
			tl(4, "-> {");
			tl(5, "if(selectCAsync.succeeded()) {");
//					tl(4, "entiteValeur = Optional.ofNullable(documentSolr.getFieldValues(", q(entiteVar, "_stored", entiteSuffixeType), ")).map(Collection<Object>::stream).orElseGet(Stream::empty).findFirst().orElse(null);");
			tl(6, "JsonArray utilisateurValeurs = selectCAsync.result().getResults().stream().findFirst().orElse(null);");
			tl(6, "if(utilisateurValeurs == null) {");
			tl(7, "connexionSql.queryWithParams(");
			tl(9, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_creer");
			tl(9, ", new JsonArray(Arrays.asList(UtilisateurSite.class.getCanonicalName(), utilisateurId))");
			tl(9, ", creerAsync");
			tl(7, "-> {");
			tl(8, "JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);");
			tl(8, "Long ", classeVarClePrimaire, "Utilisateur = creerLigne.getLong(0);");
			tl(8, "UtilisateurSite utilisateurSite = new UtilisateurSite();");
			tl(8, "utilisateurSite.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, "Utilisateur);");
			l();
			tl(8, "connexionSql.queryWithParams(");
			tl(10, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_definir");
			tl(10, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, "Utilisateur, ", classeVarClePrimaire, "Utilisateur, ", classeVarClePrimaire, "Utilisateur))");
			tl(10, ", definirAsync");
			tl(8, "-> {");
			tl(9, "if(definirAsync.succeeded()) {");
			tl(10, "try {");
			tl(11, "for(JsonArray definition : definirAsync.result().getResults()) {");
			tl(12, "utilisateurSite.definirPourClasse(definition.getString(0), definition.getString(1));");
			tl(11, "}");
			tl(11, "JsonObject utilisateurVertx = requeteSite.getOperationRequete().getUser();");
			tl(11, "JsonObject principalJson = KeycloakHelper.parseToken(utilisateurVertx.getString(\"access_token\"));");
			tl(11, "utilisateurSite.setUtilisateurNom(principalJson.getString(\"preferred_username\"));");
			tl(11, "utilisateurSite.setUtilisateurPrenom(principalJson.getString(\"given_name\"));");
			tl(11, "utilisateurSite.setUtilisateurNomFamille(principalJson.getString(\"family_name\"));");
			tl(11, "utilisateurSite.setUtilisateurId(principalJson.getString(\"sub\"));");
			tl(11, "utilisateurSite.initLoinPourClasse(requeteSite);");
			tl(11, "utilisateurSite.indexerPourClasse();");
			tl(11, "requeteSite.setUtilisateurSite(utilisateurSite);");
			tl(11, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(10, "} catch(Exception e) {");
			tl(11, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(10, "}");
			tl(9, "} else {");
			tl(10, "gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));");
			tl(9, "}");
			tl(8, "});");

			tl(7, "});");
			tl(6, "} else {");
			tl(7, "Long ", classeVarClePrimaire, "Utilisateur = utilisateurValeurs.getLong(0);");
			tl(7, "UtilisateurSite utilisateurSite = new UtilisateurSite();");
			tl(7, "utilisateurSite.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, "Utilisateur);");
			l();
			tl(7, "connexionSql.queryWithParams(");
			tl(9, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_definir");
			tl(9, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, "Utilisateur, ", classeVarClePrimaire, "Utilisateur, ", classeVarClePrimaire, "Utilisateur))");
			tl(9, ", definirAsync");
			tl(7, "-> {");
			tl(8, "if(definirAsync.succeeded()) {");
			tl(9, "for(JsonArray definition : definirAsync.result().getResults()) {");
			tl(10, "utilisateurSite.definirPourClasse(definition.getString(0), definition.getString(1));");
			tl(9, "}");
			tl(9, "JsonObject utilisateurVertx = requeteSite.getOperationRequete().getUser();");
			tl(9, "JsonObject principalJson = KeycloakHelper.parseToken(utilisateurVertx.getString(\"access_token\"));");
			tl(9, "utilisateurSite.setUtilisateurNom(principalJson.getString(\"preferred_username\"));");
			tl(9, "utilisateurSite.setUtilisateurPrenom(principalJson.getString(\"given_name\"));");
			tl(9, "utilisateurSite.setUtilisateurNomFamille(principalJson.getString(\"family_name\"));");
			tl(9, "utilisateurSite.setUtilisateurId(principalJson.getString(\"sub\"));");
			tl(9, "utilisateurSite.initLoinPourClasse(requeteSite);");
			tl(9, "requeteSite.setUtilisateurSite(utilisateurSite);");
			tl(9, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(8, "} else {");
			tl(9, "gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));");
			tl(8, "}");
			tl(7, "});");

			tl(6, "}");
			tl(5, "} else {");
			tl(6, "gestionnaireEvenements.handle(Future.failedFuture(selectCAsync.cause()));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "}");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void recherche", classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<", classeNomSimple, ">>> gestionnaireEvenements) {");
			tl(2, "try {");
			tl(3, "OperationRequest operationRequete = requeteSite.getOperationRequete();");
			tl(3, "String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
			tl(3, "String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(", q(",\\s*"), ");");
			tl(3, "ListeRecherche<", classeNomSimple, "> listeRecherche = new ListeRecherche<", classeNomSimple, ">();");
			tl(3, "listeRecherche.setPeupler(peupler);");
			tl(3, "listeRecherche.setStocker(stocker);");
			tl(3, "listeRecherche.setQuery(\"*:*\");");
			tl(3, "listeRecherche.setC(", classeNomSimple, ".class);");
			tl(3, "if(entiteListe != null)");
			tl(3, "listeRecherche.setFields(entiteListe);");
			tl(3, "listeRecherche.addSort(\"archive_indexed_boolean\", ORDER.asc);");
			tl(3, "listeRecherche.addSort(\"supprime_indexed_boolean\", ORDER.asc);");
			tl(3, "listeRecherche.addFilterQuery(\"classeNomsCanoniques_indexed_strings:\" + ClientUtils.escapeQueryChars(", q(classeNomCanonique), "));");
			if(classeFiltresTrouves && classeFiltres.contains("utilisateurId"))
				tl(3, "listeRecherche.addFilterQuery(\"utilisateurId_indexed_string:\" + ClientUtils.escapeQueryChars(requeteSite.getUtilisateurId()));");
			tl(3, "UtilisateurSite utilisateurSite = requeteSite.getUtilisateurSite();");
			tl(3, "if(utilisateurSite != null && !utilisateurSite.getVoirSupprime())");
			tl(4, "listeRecherche.addFilterQuery(\"supprime_indexed_boolean:false\");");
			tl(3, "if(utilisateurSite != null && !utilisateurSite.getVoirArchive())");
			tl(4, "listeRecherche.addFilterQuery(\"archive_indexed_boolean:false\");");
			l();
			tl(3, "String pageUri = null;");
			tl(3, "String id = operationRequete.getParams().getJsonObject(\"path\").getString(\"id\");");
			tl(3, "if(", classeVarCleUnique, " != null) {");
			tl(4, "pageUri = classeApiUriMethode + ", q("/"), " + id;");
			tl(4, "listeRecherche.addFilterQuery(\"pageUri_indexed_string:\" + ClientUtils.escapeQueryChars(pageUri));");
			tl(3, "}");
			l();
			tl(3, "operationRequete.getParams().getJsonObject(\"query\").forEach(paramRequete -> {");
			tl(4, "String entiteVar = null;");
			tl(4, "String valeurIndexe = null;");
			tl(4, "String varIndexe = null;");
			tl(4, "String valeurTri = null;");
			tl(4, "Integer rechercheDebut = null;");
			tl(4, "Integer rechercheNum = null;");
			tl(4, "String paramNom = paramRequete.getKey();");
			tl(4, "Object paramValeursObjet = paramRequete.getValue();");
			tl(4, "JsonArray paramObjets = paramValeursObjet instanceof JsonArray ? (JsonArray)paramValeursObjet : new JsonArray().add(paramValeursObjet);");
			l();
			tl(4, "try {");
			tl(5, "for(Object paramObjet : paramObjets) {");
			tl(6, "switch(paramNom) {");
	
			tl(7, "case \"q\":");
			tl(8, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, \":\"));");
			tl(8, "varIndexe = \"*\".equals(entiteVar) ? entiteVar : varRecherche", classeNomSimple, "(entiteVar);");
			tl(8, "valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, \":\")), \"UTF-8\");");
			tl(8, "valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? \"*\" : valeurIndexe;");
//			tl(8, "if(StringUtils.isEmpty(valeurIndexe)) {");
//			tl(9, "valeurIndexe = entiteVar;");
//			tl(9, "entiteVar = \"*\";");
//			tl(8, "}");
			tl(8, "listeRecherche.setQuery(varIndexe + \":\" + (\"*\".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));");
			tl(8, "if(!\"*\".equals(entiteVar)) {");
			tl(9, "listeRecherche.setHighlight(true);");
			tl(9, "listeRecherche.setHighlightSnippets(3);");
			tl(9, "listeRecherche.addHighlightField(varIndexe);");
			tl(9, "listeRecherche.setParam(\"hl.encoder\", \"html\");");
			tl(8, "}");
			tl(8, "break;");
	
			tl(7, "case \"fq\":");
			tl(8, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, \":\"));");
			tl(8, "valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, \":\")), \"UTF-8\");");
			tl(8, "varIndexe = varIndexe", classeNomSimple, "(entiteVar);");
			tl(8, "listeRecherche.addFilterQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
			tl(8, "break;");
	
			tl(7, "case \"sort\":");
			tl(8, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, \" \"));");
			tl(8, "valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, \" \"));");
			tl(8, "varIndexe = varIndexe", classeNomSimple, "(entiteVar);");
			tl(8, "listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));");
			tl(8, "break;");
	
			tl(7, "case \"fl\":");
			tl(8, "entiteVar = StringUtils.trim((String)paramObjet);");
			tl(8, "varIndexe = varIndexe", classeNomSimple, "(entiteVar);");
			tl(8, "listeRecherche.addField(varIndexe);");
			tl(8, "break;");
	
			tl(7, "case \"start\":");
			tl(8, "rechercheDebut = (Integer)paramObjet;");
			tl(8, "listeRecherche.setStart(rechercheDebut);");
			tl(8, "break;");
	
			tl(7, "case \"rows\":");
			tl(8, "rechercheNum = (Integer)paramObjet;");
			tl(8, "listeRecherche.setRows(rechercheNum);");
			tl(8, "break;");
	
			tl(6, "}");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(4, "}");

			tl(3, "});");
			tl(3, "listeRecherche.initLoinPourClasse(requeteSite);");
			tl(3, "gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void definir", classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
			tl(2, "try {");
			tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = o.getRequeteSite_();");
			tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(3, "connexionSql.queryWithParams(");
			tl(5, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_definir");
			tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, ", ", classeVarClePrimaire, ", ", classeVarClePrimaire, "))");
			tl(5, ", definirAsync");
			tl(3, "-> {");
			tl(4, "if(definirAsync.succeeded()) {");
			tl(5, "for(JsonArray definition : definirAsync.result().getResults()) {");
			tl(6, "o.definirPourClasse(definition.getString(0), definition.getString(1));");
			tl(5, "}");
			tl(5, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(4, "} else {");
			tl(5, "gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void attribuer", classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
			tl(2, "try {");
			tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = o.getRequeteSite_();");
			tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(3, "connexionSql.queryWithParams(");
			tl(5, "", classePartsSiteContexte.nomSimple(langueNom), ".SQL_attribuer");
			tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, ", ", classeVarClePrimaire, "))");
			tl(5, ", attribuerAsync");
			tl(3, "-> {");
			tl(4, "if(attribuerAsync.succeeded()) {");
			tl(5, "if(attribuerAsync.result() != null) {");
			tl(6, "for(JsonArray definition : attribuerAsync.result().getResults()) {");
			tl(7, "o.attribuerPourClasse(definition.getString(0), definition.getString(1));");
			tl(6, "}");
			tl(5, "}");
			tl(5, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(4, "} else {");
			tl(5, "gestionnaireEvenements.handle(Future.failedFuture(attribuerAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void indexer", classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
			tl(2, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = o.getRequeteSite_();");
			tl(2, "try {");
			tl(3, "o.initLoinPourClasse(requeteSite);");
			tl(3, "o.indexerPourClasse();");
			tl(3, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
	
			tl(0, "}");

			auteurGenApiServiceImpl.flushClose();
			System.out.println("Ecrire: " + classeCheminGenApiServiceImpl); 
		}
	}
}
