package org.computate.frFR.java;

import java.io.File;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**   
 * NomCanonique.enUS: org.computate.enUS.java.WritePageClass
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */  
public class EcrirePageClasse extends EcrireApiClasse {

	/**
	 * Var.enUS: classPathGenPage
	 */
	protected String classeCheminGenPage;

	/**
	 * Var.enUS: classPathPage
	 */
	protected String classeCheminPage;

	/**
	 * Var.enUS: classPathPageCss
	 */
	protected String classeCheminPageCss;

	/**
	 * Var.enUS: classPathPageJs
	 */
	protected String classeCheminPageJs;

	/**
	 * Var.enUS: classFilePageGen
	 */
	protected File classeFichierPageGen;

	/**
	 * Var.enUS: classFilePage
	 */
	protected File classeFichierPage;

	/**
	 * Var.enUS: classFilePageCss
	 */
	protected File classeFichierPageCss;

	/**
	 * Var.enUS: classFilePageJs
	 */
	protected File classeFichierPageJs;

	/**
	 * Var.enUS: writerGenPageClass
	 */
	protected ToutEcrivain auteurGenPageClasse;

	/**
	 * Var.enUS: writerPageClass
	 */
	protected ToutEcrivain auteurPageClasse;

	/**
	 * Var.enUS: writerPageCss
	 */
	protected ToutEcrivain auteurPageCss;

	/**
	 * Var.enUS: writerPageJs
	 */
	protected ToutEcrivain auteurPageJs;

	/**
	 * Var.enUS: classSimpleNamePage
	 */
	protected String classeNomSimplePage;

	/**
	 * Var.enUS: classSimpleNameGenPage
	 */
	protected String classeNomSimpleGenPage;

	/**
	 * Var.enUS: contextAName
	 */
	protected String contexteUnNom;

	/**
	 * Var.enUS: contextThis
	 */
	protected String contexteCe;

	/**
	 * Var.enUS: contextThisName
	 */
	protected String contexteCeNom;

	/**
	 * Var.enUS: contextA
	 */
	protected String contexteUn;

	/**
	 * Var.enUS: contextTheName
	 */
	protected String contexteLeNom;

	/**
	 * Var.enUS: contextNameSingular
	 */
	protected String contexteNomSingulier;

	/**
	 * Var.enUS: contextNamePlural
	 */
	protected String contexteNomPluriel;

	/**
	 * Var.enUS: contextActualName
	 */
	protected String contexteNomActuel;

	/**
	 * Var.enUS: contextAll
	 */
	protected String contexteTous;

	/**
	 * Var.enUS: contextAllName
	 */
	protected String contexteTousNom;

	/**
	 * Var.enUS: contextNoneNameFound
	 */
	protected String contexteAucunNomTrouve;

	/**
	 * Var.enUS: contextNameVar
	 */
	protected String contexteNomVar;

	/**
	 * Var.enUS: contextOfName
	 */
	protected String contexteDeNom;

	/**
	 * Var.enUS: contextAdjective
	 */
	protected String contexteAdjectif;

	/**
	 * Var.enUS: contextAdjectivePlural
	 */
	protected String contexteAdjectifPluriel;

	/**
	 * Var.enUS: contextAdjectiveVar
	 */
	protected String contexteAdjectifVar;

	/**
	 * Var.enUS: contextANameAdjective
	 */
	protected String contexteUnNomAdjectif;

	/**
	 * Var.enUS: contextNameAdjectiveSingular
	 */
	protected String contexteNomAdjectifSingulier;

	/**
	 * Var.enUS: contextNameAdjectivePlural
	 */
	protected String contexteNomAdjectifPluriel;

	/**
	 * Var.enUS: contextColor
	 */
	protected String contexteCouleur;

	/**
	 * Var.enUS: contextIconGroup
	 */
	protected String contexteIconeGroupe;

	/**
	 * Var.enUS: contextIconName
	 */
	protected String contexteIconeNom;

	/**
	 * Var.enUS: classContext
	 */
	protected Boolean classeContexte;

	public void pageCodeClasseDebut(String langueNom) throws Exception {
//		o = auteurGenPageClasse;
//		l("package ", classeNomEnsemble, ";");
//		l();
//		if(classeImportationsGenPage.size() > 0) { 
//			for(String classeImportation : classeImportationsGenPage) {
//				l("import ", classeImportation, ";");
//			}
//			l();
//		}
//
//		tl(0, "");
//		ecrireCommentaire(classeCommentaire, 0); 
//		s("public class ", classeNomSimpleGenPage);
//		l(" {");
//		l();
//		tl(1, "private static final Logger LOGGER = LoggerFactory.getLogger(", classeNomSimpleGenPage, ".class);");
	}

	/**
	 * r: Ecrire: 
	 * r.enUS: Write: 
	 * r: auteurGenPageClasse
	 * r.enUS: writerGenPageClass
	 * r: auteurPageClasse
	 * r.enUS: writerPageClass
	 * r: auteurPageCss
	 * r.enUS: writerPageCss
	 * r: auteurPageJs
	 * r.enUS: writerPageJs
	 */
	public void pageCodeClasseFin(String langueNom) throws Exception {
//		o = auteurGenPageClasse;
//
//		s(wPageEntites.toString());
//		l();
//		tl(1, "public void pageGet", classeNomSimple, "(SiteContexte siteContexte) {");
//		tl(2, "OpenAPI3RouterFactory usineRouteur = siteContexte.getUsineRouteur_();");
//		l();
//		tl(2, "usineRouteur.addHandlerByOperationId(\"getPage", classeNomSimple, "\", contexteRoutage -> {");
//		Integer tBase = 0;
//		if(classeRolesTrouves) {
//			String requeteRole = classeRoles.get(0);
//			tBase = 6;
//			tl(3, "contexteRoutage.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
//			tl(4, "try {");
//			tl(5, "if (authRes.result() == Boolean.TRUE) {");
//		}
//		else {
//			tBase = 4;
//			tl(3, "try {");
//		}
//		l();
//		tl(tBase, "contexteRoutage.response().putHeader(\"content-type\", \"application/json\").setChunked(true);");
//		tl(tBase, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, contexteRoutage);");
//		tl(tBase, "SolrQuery rechercheSolr = requeteSite.getRechercheSolr_();");
//		tl(tBase, "SolrDocumentList resultatsRecherche = requeteSite.getReponseRecherche().getResults();");
//		tl(tBase, "Integer rechercheLignes = rechercheSolr.getRows();");
//		l();
//		tl(tBase, "genererGetDebut", classeNomSimple, "(requeteSite);");
//		tl(tBase, "for(long i = resultatsRecherche.getStart(); i < resultatsRecherche.getNumFound(); i+=rechercheLignes) {");
//		tl(tBase + 1, "for(int j = 0; j < resultatsRecherche.size(); j++) {");
//		tl(tBase + 2, "long resultatIndice = i + j;");
//		tl(tBase + 2, "SolrDocument documentSolr = resultatsRecherche.get(j);");
//		tl(tBase + 2, "ResultatRecherche resultatRecherche = new ResultatRecherche();");
//		tl(tBase + 2, "resultatRecherche.setRequeteSite_(requeteSite);");
//		tl(tBase + 2, "resultatRecherche.setDocumentSolr(documentSolr);");
//		tl(tBase + 2, "resultatRecherche.setResultatIndice(resultatIndice);");
//		tl(tBase + 2, "genererGetIndividuel", classeNomSimple, "(resultatRecherche);");
//		tl(tBase + 1, "}");
//		tl(tBase, "}");
//		tl(tBase, "genererGetFin", classeNomSimple, "(requeteSite);");
//		tl(tBase, "requeteSite.getReponseServeur().end();");
//		if(classeRolesTrouves) {
//			tl(5, "}");
//			tl(5, "else {");
//			tl(6, "contexteRoutage.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();");
//			tl(5, "}");
//			tl(4, "} catch(Exception e) {");
//			tl(5, "LOGGER.error(\"Error: \", e.getMessage());");
//			tl(5, "contexteRoutage.fail(e);");
//			tl(4, "}");
//			tl(3, "});");
//		}
//		else {
//			tl(3, "} catch(Exception e) {");
//			tl(4, "LOGGER.error(\"Error: \", e.getMessage());");
//			tl(4, "contexteRoutage.fail(e);");
//			tl(3, "}");
//		}
//		tl(2, "});");
//		tl(2, "usineRouteur.addFailureHandlerByOperationId(\"get", classeNomSimple, "\", contexteRoutage -> {");
//		tl(3, "Throwable failure = contexteRoutage.failure();");
//		tl(3, "if (failure instanceof ValidationException) {");
//		tl(4, "String validationErrorMessage = failure.getMessage();");
//		tl(4, "LOGGER.error(\"Error: \", validationErrorMessage);");
//		tl(4, "contexteRoutage.fail(failure);");
//		tl(3, "}");
//		tl(2, "});");
//		tl(1, "}");
//		l();
//		tl(1, "public void genererGetDebut", classeNomSimple, "(RequeteSite requeteSite) {");
//		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//		tl(2, "QueryResponse reponseRecherche = requeteSite.getReponseRecherche();");
//		tl(2, "reponseServeur.write(\"{\\n\");");
//		tl(2, "Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());");
//		tl(2, "Long millisTransmission = reponseRecherche.getElapsedTime();");
//		tl(2, "Long numCommence = reponseRecherche.getResults().getStart();");
//		tl(2, "Long numTrouve = reponseRecherche.getResults().getNumFound();");
//		tl(2, "Integer numRetourne = reponseRecherche.getResults().size();");
//		tl(2, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
//		tl(2, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
//		tl(2, "Exception exceptionRecherche = reponseRecherche.getException();");
//		l();
//		tl(2, "reponseServeur.write(\"\\t\\\"numCommence\\\": \");");
//		tl(2, "reponseServeur.write(numCommence.toString());");
//		l();
//		tl(2, "reponseServeur.write(\",\\n\\t\\\"numTrouve\\\": \");");
//		tl(2, "reponseServeur.write(numTrouve.toString());");
//		l();
//		tl(2, "reponseServeur.write(\",\\n\\t\\\"numRetourne\\\": \");");
//		tl(2, "reponseServeur.write(numRetourne.toString());");
//		l();
//		tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsRecherche\\\": \\\"\");");
//		tl(2, "reponseServeur.write(tempsRecherche);");
//		tl(2, "reponseServeur.write(\"\\\"\");");
//		l();
//		tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsTransmission\\\": \\\"\");");
//		tl(2, "reponseServeur.write(tempsTransmission);");
//		tl(2, "reponseServeur.write(\"\\\"\");");
//		l();
//		tl(2, "if(exceptionRecherche != null) {");
//		tl(3, "reponseServeur.write(\",\\n\\t\\\"exceptionRecherche\\\": \\\"\");");
//		tl(3, "reponseServeur.write(exceptionRecherche.getMessage());");
//		tl(3, "reponseServeur.write(\"\\\"\");");
//		tl(2, "}");
//		l();
//		tl(2, "reponseServeur.write(\",\\n\\t\\\"resultats\\\": [\\n\");");
//		tl(1, "}");
//		l();
//		tl(1, "public void genererGetIndividuel", classeNomSimple, "(ResultatRecherche resultatRecherche) throws Exception {");
//		tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
//		tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();");
//		tl(2, "Long resultatIndice = resultatRecherche.getResultatIndice();");
//		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//		tl(2, "reponseServeur.write(\"\\t\\t\");");
//		tl(2, "if(resultatIndice > 0)");
//		tl(3, "reponseServeur.write(\", \");");
//		tl(2, "reponseServeur.write(\"{\\n\");");
//		tl(2, "Collection<String> champNoms = documentSolr.getFieldNames();");
//		tl(2, "Integer j = 0;");
//		tl(2, "for(String champNomStocke : champNoms) {");
//		tl(3, "Collection<Object> champValeurs = documentSolr.getFieldValues(champNomStocke);");
//		tl(3, "j = genererGet", classeNomSimple, "(j, resultatRecherche, champNomStocke, champValeurs);");
//		tl(2, "}");
//		tl(2, "reponseServeur.write(\"\\t\\t}\\n\");");
//		tl(1, "}");
//		l();
//		tl(1, "public void genererGetFin", classeNomSimple, "(RequeteSite requeteSite) {");
//		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
////		tl(2, "if(exceptionRecherche != null) {");
////		l();
////		tl(4, "reponseServeur.write(\"\\t\\t}\\n\");");
////		tl(3, "}");
////		tl(2, "}");
//		tl(2, "reponseServeur.write(\"\\t]\\n\");");
//		tl(2, "reponseServeur.write(\"}\\n\");");
//		tl(1, "}");
////		tl(1, "@Override protected void doGet(HttpServerRequest requeteServeur, HttpServerResponse reponseServeur) throws ServletException, IOException {");
//		l();
//		tl(1, "public String varIndexe", classeNomSimple, "(String entiteVar) throws Exception {");
//		tl(2, "switch(entiteVar) {");
//		s(wPageGet.toString());
//		tl(3, "default:");
//		tl(4, "throw new Exception(String.format(\"\\\"%s\\\" n'est pas une entité indexé. \", entiteVar));");
//		tl(2, "}");
//		tl(1, "}");
//		l();
//		tl(1, "public SolrQuery genererRecherche", classeNomSimple, "(HttpServerRequest requeteServeur) throws Exception {");
//		tl(2, "String entiteVar = null;");
//		tl(2, "String valeurIndexe = null;");
//		tl(2, "String varIndexe = null;");
//		tl(2, "String valeurTri = null;");
//		tl(2, "Integer rechercheDebut = null;");
//		tl(2, "Integer rechercheNum = null;");
//		tl(2, "SolrQuery rechercheSolr = new SolrQuery();");
//		tl(2, "rechercheSolr.setQuery(\"*:*\");");
//		tl(2, "rechercheSolr.setRows(1000000);");
//		tl(2, "rechercheSolr.addSort(\"partNumero_indexed_int\", ORDER.asc);");
//		tl(2, "MultiMap paramMap = requeteServeur.params();");
//		tl(2, "for(String paramCle : paramMap.names()) {");
//		tl(3, "List<String> paramValeurs = paramMap.getAll(paramCle);");
//		tl(3, "for(String paramValeur : paramValeurs) {");
//		tl(4, "switch(paramCle) {");
//
//		tl(5, "case \"q\":");
//		tl(6, "entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, \":\"));");
//		tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, \":\"));");
//		tl(6, "varIndexe = varIndexe", classeNomSimple, "(paramCle);");
//		tl(6, "rechercheSolr.setQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
//		tl(6, "break;");
//
//		tl(5, "case \"fq\":");
//		tl(6, "entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, \":\"));");
//		tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, \":\"));");
//		tl(6, "varIndexe = varIndexe", classeNomSimple, "(paramCle);");
//		tl(6, "rechercheSolr.addFilterQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
//		tl(6, "break;");
//
//		tl(5, "case \"sort\":");
//		tl(6, "entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, \" \"));");
//		tl(6, "valeurTri = StringUtils.trim(StringUtils.substringAfter(paramValeur, \" \"));");
//		tl(6, "varIndexe = varIndexe", classeNomSimple, "(paramCle);");
//		tl(6, "rechercheSolr.addSort(varIndexe, ORDER.valueOf(valeurTri));");
//		tl(6, "break;");
//
//		tl(5, "case \"fl\":");
//		tl(6, "entiteVar = StringUtils.trim(paramValeur);");
//		tl(6, "varIndexe = varIndexe", classeNomSimple, "(paramCle);");
//		tl(6, "rechercheSolr.addField(varIndexe);");
//		tl(6, "break;");
//
//		tl(5, "case \"start\":");
//		tl(6, "rechercheDebut = Integer.parseInt(paramValeur);");
//		tl(6, "rechercheSolr.setStart(rechercheDebut);");
//		tl(6, "break;");
//
//		tl(5, "case \"rows\":");
//		tl(6, "rechercheNum = Integer.parseInt(paramValeur);");
//		tl(6, "rechercheSolr.setRows(rechercheNum);");
//		tl(6, "break;");
//
//		tl(4, "}");
//		tl(3, "}");
//		tl(2, "}");
//		tl(2, "return rechercheSolr;");
//		tl(1, "}");
//		l();
//		tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, RoutingContext contexteItineraire) throws Exception {");
//		tl(2, "Vertx vertx = siteContexte.getVertx_();");
//		tl(2, "SolrQuery rechercheSolr = genererRecherche", classeNomSimple, "(contexteItineraire.request());");
//		l();
//		tl(2, "RequeteSite requeteSite = new RequeteSite();");
//		tl(2, "requeteSite.setVertx_(vertx);");
//		tl(2, "requeteSite.setContexteItineraire_(contexteItineraire);");
//		tl(2, "requeteSite.setSiteContexte_(siteContexte);");
//		tl(2, "requeteSite.setRechercheSolr_(rechercheSolr);");
//		tl(2, "requeteSite.initLoinRequeteSite(requeteSite);");
//		l();
//
//		tl(2, "UtilisateurSite utilisateurSite = new UtilisateurSite();");
//		tl(2, "utilisateurSite.initLoinUtilisateurSite(requeteSite);");
//		tl(2, "requeteSite.setUtilisateurSite(utilisateurSite);");
//		tl(2, "utilisateurSite.setRequeteSite_(requeteSite);");
//
//		tl(2, "return requeteSite;");
//		tl(1, "}");
//		l();
////		tl(1, "public Integer genererGet", classeNomSimple, "(Integer j, PrintWriter ecrivain, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {");
//		tl(1, "public Integer genererGet", classeNomSimple, "(Integer j, ResultatRecherche resultatRecherche, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {");
//		tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
//		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//		tl(2, "if(!champValeurs.isEmpty()) {");
//		tl(3, "Object champValeur = champValeurs.iterator().next();");
//		tl(3, "if(champValeur != null) {");
////		s(wPageGenererGet.toString());
//		tl(3, "}");
//		tl(2, "}");
//		tl(2, "return j;");
//		tl(1, "}");
//
//		////////////
//		// Erreur //
//		////////////
////		l();
////		tl(1, "public void genererErreur(RequeteSite requeteSite, Exception e) {");
////		tl(2, "e.printStackTrace();");
////		tl(2, "try {");
////		tl(3, "MimeMessage message = new MimeMessage(requeteSite.SiteContexte_.sessionCourriel);");
////		tl(3, "message.setFrom(new InternetAddress(requeteSite.configSite_.mailAdmin));");
////		tl(3, "InternetAddress destinaires[] = new InternetAddress[1];");
////		tl(3, "destinaires[0] = new InternetAddress(requeteSite.configSite_.mailAdmin);");
////		tl(3, "message.setRecipients(Message.RecipientType.TO, destinaires);");
////		tl(3, "String nomDomaine = requeteSite.configSite_.nomDomaine;");
////		tl(3, "String sujet = nomDomaine + \" erreur \" + \" \" + requeteSite.utilisateurNom + \" \" + requeteSite.requeteServeur.getRequestURI();");
////		tl(3, "String corps = ExceptionUtils.getStackTrace(e);");
////		tl(3, "message.setSubject(sujet);");
////		tl(3, "message.setContent(corps, \"text/plain\");");
////		tl(3, "Transport.send(message);");
////		tl(3, "String s = e.getMessage();");
////		tl(3, "requeteSite.getReponseServeur().sendError(500, s);");
////		tl(2, "} catch(Exception e2) {");
////		tl(3, "e.printStackTrace();");
////		tl(2, "}");
////		tl(1, "}");
//		tl(0, "}");
//
//		System.out.println("Ecrire: " + classeCheminGenPage); 
	}

	/**
	 * Var.enUS: pageCodeClass
	 * Param1.var.enUS: languageName
	 * r: Ecrire: 
	 * r.enUS: Write: 
	 * r: auteurGenPageClasse
	 * r.enUS: writerGenPageClass
	 * r: auteurPageClasse
	 * r.enUS: writerPageClass
	 * r: auteurPageCss
	 * r.enUS: writerPageCss
	 * r: auteurPageJs
	 * r.enUS: writerPageJs
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 * r: Couverture
	 * r.enUS: Wrap
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeImportationsGenPage
	 * r.enUS: classImportsGenPage
	 * r: classeImportation
	 * r.enUS: classImport
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: langueNom
	 * r.enUS: languageName
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: entiteHtmlLigne
	 * r.enUS: entityHtmlLine
	 * r: entiteHtml
	 * r.enUS: entityHtml
	 * 
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: classeEntiteVars
	 * r.enUS: classEntityVars
	 * r: contexteIconeGroupe
	 * r.enUS: contextIconGroup
	 * r: contexteIconeNom
	 * r.enUS: contextIconName
	 * r: contexteCe
	 * r.enUS: contextThis
	 * r: contexteCeNom
	 * r.enUS: contextThisName
	 * r: contexteUn
	 * r.enUS: contextA
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: contexteLeNom
	 * r.enUS: contextTheName
	 * r: contexteNomSingulier
	 * r.enUS: contextNameSingular
	 * r: contexteNomPluriel
	 * r.enUS: contextNamePlural
	 * r: contexteNomActuel
	 * r.enUS: contextActualName
	 * r: contexteTous
	 * r.enUS: contextAll
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: contexteAucunNomTrouve
	 * r.enUS: contextNoneNameFound
	 * r: contexteNomVar
	 * r.enUS: contextNameVar
	 * r: contexteDeNom
	 * r.enUS: contextOfName
	 * r: contexteNom
	 * r.enUS: contextName
	 * r: UnNomAdjectif
	 * r.enUS: ANameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheNameAdjective
	 * r: AdjectifAvant
	 * r.enUS: AdjectiveBefore
	 * r: NomAdjectifSingulier
	 * r.enUS: NameAdjectiveSingular
	 * r: NomAdjectifPluriel
	 * r.enUS: NameAdjectivePlural
	 * r: PlurielNomAdjectif
	 * r.enUS: PluralNameAdjective
	 * r: SingulierNomAdjectif
	 * r.enUS: SingularNameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheAdjectiveName
	 * r: UnNomAdjectif
	 * r.enUS: AnAdjectiveName
	 * r: AdjectifVar
	 * r.enUS: AdjectiveVar
	 * r: Adjectif
	 * r.enUS: Adjective
	 * r: CONTEXTE_UnMasculinVoyelle
	 * r.enUS: CONTEXT_AMaleVowel
	 * r: CONTEXTE_UnFemininVoyelle
	 * r.enUS: CONTEXT_AFemaleVowel
	 * r: CONTEXTE_UnMasculinConsonne
	 * r.enUS: CONTEXT_AMaleConsonant
	 * r: CONTEXTE_UnFemininConsonne
	 * r.enUS: CONTEXT_AFemaleConsonant
	 * r: CONTEXTE_CetMasculinVoyelle
	 * r.enUS: CONTEXT_ThisMaleVowel
	 * r: CONTEXTE_CetteFemininVoyelle
	 * r.enUS: CONTEXT_ThisFemaleVowel
	 * r: CONTEXTE_CeMasculinConsonne
	 * r.enUS: CONTEXT_ThisMaleConsonant
	 * r: CONTEXTE_CetteFemininConsonne
	 * r.enUS: CONTEXT_ThisFemaleConsonant
	 * r: CONTEXTE_CesPluriel
	 * r.enUS: CONTEXT_ThesePlural
	 * r: CONTEXTE_LMasculinVoyelle
	 * r.enUS: CONTEXT_TheMaleVowel
	 * r: CONTEXTE_LFemininVoyelle
	 * r.enUS: CONTEXT_TheFemaleVowel
	 * r: CONTEXTE_LeMasculinConsonne
	 * r.enUS: CONTEXT_TheMaleConsonant
	 * r: CONTEXTE_LaFemininConsonne
	 * r.enUS: CONTEXT_TheFemaleConsonant
	 * r: CONTEXTE_LesPluriel
	 * r.enUS: CONTEXT_ThePlural
	 * r: CONTEXTE_ActuelMasculinAvant
	 * r.enUS: CONTEXT_CurrentMaleBefore
	 * r: CONTEXTE_ActuelleFemininAvant
	 * r.enUS: CONTEXT_CurrentFemaleBefore
	 * r: CONTEXTE_ActuelMasculinApres
	 * r.enUS: CONTEXT_CurrentMaleAfter
	 * r: CONTEXTE_ActuelleFemininApres
	 * r.enUS: CONTEXT_CurrentFemaleAfter
	 * r: CONTEXTE_TousMasculinPluriel
	 * r.enUS: CONTEXT_AllMalePlural
	 * r: CONTEXTE_ToutesFemininPluriel
	 * r.enUS: CONTEXT_AllFemalePlural
	 * r: CONTEXTE_AucunTrouveMasculinAvant
	 * r.enUS: CONTEXT_NoneFoundMaleBefore
	 * r: CONTEXTE_AucuneTrouveFemininAvant
	 * r.enUS: CONTEXT_NoneFemaleBefore
	 * r: CONTEXTE_AucunTrouveMasculinApres
	 * r.enUS: CONTEXT_NoneFoundMaleAfter
	 * r: CONTEXTE_AucuneTrouveFemininApres
	 * r.enUS: CONTEXT_NoneFemaleAfter
	 * r: CONTEXTE_DVoyelle
	 * r.enUS: CONTEXT_OfVowel
	 * r: CONTEXTE_DeConsonne
	 * r.enUS: CONTEXT_OfConsonant
	 * r: CONTEXTE_AdjectifPluriel
	 * r.enUS: CONTEXT_AdjectivePlural
	 * r: rechercheLigneActuel
	 * r.enUS: searchLineActual
	 * r: rechercheLigne
	 * r.enUS: searchLine
	 * 
	 * r: Milieu
	 * r.enUS: Middle
	 * r: liste
	 * r.enUS: list
	 * r: plusiers
	 * r.enUS: multiple
	 */ 
	public void pageCodeClasse(String langueNom) throws Exception {

		if(auteurGenPageClasse != null) {
			o = auteurGenPageClasse;
	
			l("package ", classeNomEnsemble, ";");
			l();
			if(classeImportationsGenPage.size() > 0) { 
				for(String classeImportation : classeImportationsGenPage) {
					l("import ", classeImportation, ";");
				}
				l();
			}
	
			tl(0, "");
			ecrireCommentaire(classeCommentaire, 0); 
			s("public class ", classeNomSimple, "GenPage");
			s(" extends ", classeNomSimple, "GenPageGen<MiseEnPage>");
			l(" {");
			l();
			tl(1, "/**");
			tl(1, " * {@inheritDoc}");
			tl(1, " * ");
			tl(1, " **/");
			tl(1, "protected void _liste", classeNomSimple, "(Couverture<ListeRecherche<", classeNomSimple, ">> c) {");
			tl(1, "}");
			l();
			tl(1, "@Override public void initLoin", classeNomSimple, "GenPage() {");
			tl(2, "init", classeNomSimple, "GenPage();");
			tl(2, "super.initLoinMiseEnPage();");
			tl(1, "}");
			l();
			tl(1, "public void html", classeNomSimple, "GenPageMilieu() {");
			tl(2, "if(liste", classeNomSimple, ".size() == 1) {");
			t(3).l("// ", contexteUnNom);
			t(3).l(classeNomSimple, " o = liste", classeNomSimple, ".first();");
			l();
			t(3).be("h1").dfl();
			if(StringUtils.isNotBlank(contexteIconeGroupe) && StringUtils.isNotBlank(contexteIconeNom)) {
				t(4).e("i").da("class", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, " w3-margin-right-4 ").df().dgl("i");
				t(4).e("span").da("class", " ").df().dsx(contexteNomPluriel).dgl("i");
			}
			t(3).bgl("h1");
			t(3).be("h2").dfl();
			if(classeEntiteVars != null && classeEntiteVars.contains("pageH2"))
				t(4).e("span").da("class", " ").df().s(".sx(o.getPageH2())").dgl("i");
			t(3).bgl("h2");
			t(3).be("div").da("class", "w3-card w3-margin w3-padding w3-margin-top w3-show ").dfl();

			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1000000);
			rechercheSolr.addFilterQuery("classeCheminAbsolu_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
			rechercheSolr.addFilterQuery("classeEtendGen_indexed_boolean:true");
			rechercheSolr.addSort("entiteHtmlLigne_indexed_int", ORDER.asc);
			rechercheSolr.addSort("entiteHtmlCellule_indexed_int", ORDER.asc);
			QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
			SolrDocumentList rechercheListe = rechercheReponse.getResults();
			Integer rechercheLignes = rechercheSolr.getRows();
			Integer rechercheLigne = -1;
			Integer rechercheLigneActuel;

			if(rechercheListe.size() > 0) {
				for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
					for(Integer j = 0; j < rechercheListe.size(); j++) {
						SolrDocument entiteDocumentSolr = rechercheListe.get(j);
						String entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
						Boolean entiteHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean"));
						if(entiteHtml) {
							rechercheLigneActuel = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
							if(rechercheLigne != rechercheLigneActuel) {
								if(rechercheLigne != -1)
									t(4).bgl("div");
								t(4).be("div").da("class", "w3-cell-row ").dfl();
								rechercheLigne = rechercheLigneActuel;
							}
							t(5).be("div").da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
							t(6).e("input").da("name", entiteVar).dfgl();
				//			if().da("class", objets).da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
							t(5).bgl("div");
						}
					}
					rechercheSolr.setStart(i.intValue() + rechercheLignes);
					rechercheReponse = clientSolrComputate.query(rechercheSolr);
					rechercheListe = rechercheReponse.getResults();
				}
				t(4).bgl("div");
			}

			t(3).bgl("div");
			tl(2, "} else if(liste", classeNomSimple, ".size() == 1) {");
			t(3).l("// plusiers ", contexteNomPluriel);
			l();
			tl(2, "} else {");
			tl(2, "}");
			tl(1, "}");
			tl(0, "}");
		}

		if(auteurPageClasse != null)
			auteurPageClasse.flushClose();
		auteurGenPageClasse.flushClose();
		auteurPageCss.flushClose();
		auteurPageJs.flushClose();
//		auteurGenPageClasse.close();
	}
}
