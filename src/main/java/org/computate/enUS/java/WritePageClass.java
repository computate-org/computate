package org.computate.enUS.java;

import java.io.File;
import java.util.stream.Collectors;
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
 *	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 **/
public class WritePageClass extends WriteApiClass {

	protected String classPathGenPage;

	protected String classPathPage;

	protected String classPathPageCss;

	protected String classPathPageJs;

	protected File classFilePageGen;

	protected File classFilePage;

	protected File classFilePageCss;

	protected File classFilePageJs;

	protected AllWriter writerGenPageClass;

	protected AllWriter writerPageClass;

	protected AllWriter writerPageCss;

	protected AllWriter writerPageJs;

	protected String classSimpleNamePage;

	protected String classSimpleNameGenPage;

	protected String contextAName;

	protected String contextThis;

	protected String contextThisName;

	protected String contextA;

	protected String contextTheName;

	protected String contextNameSingular;

	protected String contextNamePlural;

	protected String contextActualName;

	protected String contextAll;

	protected String contextAllName;

	protected String contextNoneNameFound;

	protected String contextNameVar;

	protected String contextOfName;

	protected String contextAdjective;

	protected String contextAdjectivePlural;

	protected String contextAdjectiveVar;

	protected String contextANameAdjective;

	protected String contextNameAdjectiveSingular;

	protected String contextNameAdjectivePlural;

	protected String contextColor;

	protected String contextIconGroup;

	protected String contextIconName;

	protected Boolean classContext;

	public void  pageCodeClasseDebut(String langueNom) throws Exception, Exception {
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

	public void  pageCodeClasseFin(String langueNom) throws Exception, Exception {
//		o = writerGenPageClass;
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
//		System.out.println("Write: " + classeCheminGenPage); 
	}

	public void  pageCodeClass(String languageName) throws Exception, Exception {

		if(writerGenPageClass != null) {
			o = writerGenPageClass;

			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1000000);
			String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
			rechercheSolr.addFilterQuery("entiteClassesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
			rechercheSolr.addSort("entityHtmlLine_indexed_int", ORDER.asc);
			rechercheSolr.addSort("entityHtmlCellule_indexed_int", ORDER.asc);
			QueryResponse rechercheReponse = solrClientComputate.query(rechercheSolr);
			SolrDocumentList rechercheListe = rechercheReponse.getResults();
			Integer searchLines = rechercheSolr.getRows();
			Integer searchLine = -1;
			Integer searchLineActual;
	
			l("package ", classPackageName, ";");
			l();
			if(classImportsGenPage.size() > 0) { 
				for(String classImport : classImportsGenPage) {
					l("import ", classImport, ";");
				}
				l();
			}
	
			tl(0, "");
			writeComment(classComment, 0); 
			s("public class ", classSimpleName, "GenPage");
			s(" extends ", classSimpleName, "GenPageGen<PageLayout>");
			l(" {");
			l();
			tl(1, "/**");
			tl(1, " * {@inheritDoc}");
			tl(1, " * ");
			tl(1, " **/");
			tl(1, "protected void _list", classSimpleName, "(Wrap<SearchList<", classSimpleName, ">> c) {");
			tl(1, "}");
			l();
			tl(1, "@Override public void initDeep", classSimpleName, "GenPage() {");
			tl(2, "init", classSimpleName, "GenPage();");
			tl(2, "super.initDeepPageLayout();");
			tl(1, "}");
			l();
			tl(1, "@Override public void htmlScripts", classSimpleName, "GenPage() {");
			t(2).e("script").da("src", "/static/js/", classSimpleName, "Page.js").df().dgl("script");
			tl(1, "}");
			ToutEcrivain wSearch = ToutEcrivain.create();
			ToutEcrivain wPOST = ToutEcrivain.create();
			ToutEcrivain wPATCH = ToutEcrivain.create();

			if(rechercheListe.size() > 0) {
				for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=searchLines) {
					for(Integer j = 0; j < rechercheListe.size(); j++) {
						SolrDocument entiteDocumentSolr = rechercheListe.get(j);
						String entityVar = (String)entiteDocumentSolr.get("entityVar_" + languageName + "_stored_string");
						String entityVarCapitalized = (String)entiteDocumentSolr.get("entityVarCapitalized_" + languageName + "_stored_string");
						String entitySimpleName = (String)entiteDocumentSolr.get("entitySimpleName_" + languageName + "_stored_string");
						String entitySimpleNameGenerique = (String)entiteDocumentSolr.get("entitySimpleNameGenerique_" + languageName + "_stored_string");
						String entitySimpleNameComplet = (String)entiteDocumentSolr.get("entitySimpleNameComplet_" + languageName + "_stored_string");
						String entityDescription = (String)entiteDocumentSolr.get("entityDescription_" + languageName + "_stored_string");
						String entityDisplayName = (String)entiteDocumentSolr.get("entityDisplayName_" + languageName + "_stored_string");
						Boolean entityHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityHtml_stored_boolean"));
						Boolean entityMultiline = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityMultiline_stored_boolean"));
						if(entityHtml) {

							wSearch.l();
							wSearch.tl(1, "var filter", entityVarCapitalized, " = $formulaireFiltres.find('.value", entityVarCapitalized, "').val();");
							wSearch.tl(1, "if(filter", entityVarCapitalized, ")");
							wSearch.tl(2, "filters['", entityVar, "'] = value", entityVarCapitalized, ";");

							wPOST.l();
							wPOST.tl(1, "var value", entityVarCapitalized, " = $formulaireValeurs.find('.value", entityVarCapitalized, "').val();");
							wPOST.tl(1, "if(value", entityVarCapitalized, ")");
							wPOST.tl(2, "values['", entityVar, "'] = value", entityVarCapitalized, ";");

							wPATCH.l();
							wPATCH.tl(1, "var set", entityVarCapitalized, " = $formulaireValeurs.find('.set", entityVarCapitalized, "').val();");
							wPATCH.tl(1, "if(set", entityVarCapitalized, ")");
							wPATCH.tl(2, "patchs['set", entityVarCapitalized, "'] = set", entityVarCapitalized, ";");
							wPATCH.tl(1, "var add", entityVarCapitalized, " = $formulaireValeurs.find('.add", entityVarCapitalized, "').val();");
							wPATCH.tl(1, "if(add", entityVarCapitalized, ")");
							wPATCH.tl(2, "patchs['add", entityVarCapitalized, "'] = add", entityVarCapitalized, ";");
							wPATCH.tl(1, "var remove", entityVarCapitalized, " = $formulaireValeurs.find('.remove", entityVarCapitalized, "').val();");
							wPATCH.tl(1, "if(remove", entityVarCapitalized, ")");
							wPATCH.tl(2, "patchs['remove", entityVarCapitalized, "'] = remove", entityVarCapitalized, ";");
						}
					}
				}
			}

			l();
			tl(1, "@Override public void htmlScript", classSimpleName, "GenPage() {");
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_frFR_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_frFR_stored_string");
				String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_stored_string");
				String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_stored_string");

				if("application/json".equals(classeApiTypeMediaMethode)) {
					Boolean methodePOST = classeApiMethodeMethode.equals("POST");
					Boolean methodeGET = classeApiMethode.contains("GET");
					Boolean methodePUT = classeApiMethodeMethode.equals("PUT");
					Boolean methodePATCH = classeApiMethodeMethode.equals("PATCH");
					Boolean methodeDELETE = classeApiMethodeMethode.equals("DELETE");
					Boolean methodeSearch = classeApiMethode.contains("Search");

					writerPageJs.l();
					writerPageJs.tl(0, "// ", classeApiMethode, " //");
					writerPageJs.l();
					writerPageJs.l("/**");
					if(methodePATCH) {
					writerPageJs.l(" * Modify un ou multiple ", contextNamePlural, " sans valuers qui change, ");
					writerPageJs.l(" * ou changer des values pour un ou multiple ", contextTheName, ". ");
					writerPageJs.l(" * @param params: [ \"q=*:*\", \"fq=pk:1\", \"sort=pk asc\", \"rows=1\", \"fl=pk\" ]");
					writerPageJs.l(" *        Une list des opérations de recherche sur des ", contextNamePlural, " ");
					writerPageJs.l(" *        pour rechercher \"q=*:*\", filterr \"fq=pk:1\", trier \"sort=pk desc\", ");
					writerPageJs.l(" *        limiter les résultats \"rows=1\", ou limiter les values \"fl=pk\". ");
					writerPageJs.l(" * @param values Noms des champs et values à changer selon les filters fq. ");
					writerPageJs.l(" *           Example: { pk: 1 }");
					}
					writerPageJs.l(" */");
					writerPageJs.t(0, "function ", classeApiOperationIdMethode, "(");
					if(methodePOST)
						writerPageJs.s("$formulaireValeurs");
					else if(methodePUT)
						writerPageJs.s("pk, $formulaireValeurs");
					if(methodePATCH)
						writerPageJs.s("$formulaireFiltres, $formulaireValeurs");
					if(methodeGET || methodeDELETE)
						writerPageJs.s("pk");

					writerPageJs.l(") {");
					if(methodePOST) {
						writerPageJs.tl(1, "var values = {};");
						writerPageJs.s(wPOST);
						writerPageJs.l();
					}
					else if(methodePUT) {
						writerPageJs.tl(1, "var values = {};");
						writerPageJs.s(wPOST);
						writerPageJs.l();
					}
					if(methodePATCH) {
						writerPageJs.tl(1, "var filters = {};");
						writerPageJs.s(wSearch);
						writerPageJs.l();
						writerPageJs.tl(1, "var patchs = {};");
						writerPageJs.s(wPATCH);
						writerPageJs.l();
					}

					writerPageJs.tl(1, "$.ajax({");

					if(methodeGET || methodeDELETE || methodePUT)
						writerPageJs.tl(2, "url: '", StringUtils.replace(classeApiUriMethode, "{pk}", "' + pk"));
					else if(methodePATCH || methodeSearch)
						writerPageJs.tl(2, "url: '", classeApiUriMethode, "' + (!params || params.length == 0 ? '' : '?' + params.join('&'))");
					else
						writerPageJs.tl(2, "url: '", classeApiUriMethode, "'");

					writerPageJs.tl(2, ", dataType: 'json'");
					writerPageJs.tl(2, ", type: '", classeApiMethodeMethode, "'");
					writerPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
					if(!"GET".equals(classeApiMethodeMethode) || "DELETE".equals(classeApiMethodeMethode))
						writerPageJs.tl(2, ", data: JSON.stringify(values)");
					writerPageJs.tl(2, ", success: function( data, textStatus, jQxhr ) {");
					writerPageJs.tl(2, "}");
					writerPageJs.tl(2, ", error: function( jqXhr, textStatus, errorThrown ) {");
					writerPageJs.tl(2, "}");
					writerPageJs.tl(1, "});");
					writerPageJs.l("}");
				}
				
			}
			tl(1, "}");
			l();
			tl(1, "public void htmlForm", classSimpleName, "(", classSimpleName, " o) {");
			if(rechercheListe.size() > 0) {
				for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=searchLines) {
					for(Integer j = 0; j < rechercheListe.size(); j++) {
						SolrDocument entiteDocumentSolr = rechercheListe.get(j);
						String entityVar = (String)entiteDocumentSolr.get("entityVar_" + languageName + "_stored_string");
						String entityVarCapitalized = (String)entiteDocumentSolr.get("entityVarCapitalized_" + languageName + "_stored_string");
						String entitySimpleName = (String)entiteDocumentSolr.get("entitySimpleName_" + languageName + "_stored_string");
						String entitySimpleNameGenerique = (String)entiteDocumentSolr.get("entitySimpleNameGenerique_" + languageName + "_stored_string");
						String entitySimpleNameComplet = (String)entiteDocumentSolr.get("entitySimpleNameComplet_" + languageName + "_stored_string");
						String entityDescription = (String)entiteDocumentSolr.get("entityDescription_" + languageName + "_stored_string");
						String entityDisplayName = (String)entiteDocumentSolr.get("entityDisplayName_" + languageName + "_stored_string");
						Boolean entityHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityHtml_stored_boolean"));
						Boolean entityMultiline = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityMultiline_stored_boolean"));
						if(entityHtml) {
							searchLineActual = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entityHtmlLine_stored_int"), 0);
							if(searchLine != searchLineActual) {
								if(searchLine != -1)
									t(2).bgl("div");
								t(2).be("div").da("class", "w3-cell-row ").dfl();
								searchLine = searchLineActual;
							}
							t(3).be("div").da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
							if("LocalDate".equals(entitySimpleName)) {
								tl(4, entitySimpleNameComplet, " val = o.get", entityVarCapitalized, "();");
								l();
								if(entityDisplayName != null) {
									t(4).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
								t(4).e("input").l();
								t(5).dal("type", "text");
								t(5).dal("class", "w3-input w3-border datepicker ");
								t(5).dal("placeholder", "MM/DD/YYYY");
								t(5).dal("data-timeformat", "MM/DD/YYYY");
								t(5).dal("onclick", "enleverLueur($(this)); ");
								if(entityDescription != null)
									t(5).dal("title", entityDescription + " (MM/DD/YYYY)");
								tl(5, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"MM/dd/yyyy\", Locale.forLanguageTag(\"en-US\")).format(val))");
								t(5).s(".a(\"onchange\", \"");
									s("var t = moment(this.value, 'MM/DD/YYYY'); ");
									s("if(t) { ");
										s("var s = t.format('YYYY-MM-DD'); ");
										s("$(this).next().val(s); ");
										s("$(this).next().trigger('change'); ");
									s("} ");
								l("\")");
								tl(5, ".fg();");

								t(4).e("input").l();
								t(5).dal("class", "value", entityVarCapitalized);
								t(5).dal("name", entityVar);
								t(5).dal("type", "hidden");
								t(5).dal("onchange", "envoyer(); ");
								tl(5, ".a(\"value\", o.str", entityVarCapitalized, "())");
								t(4).dfgl();
							}
							else if("LocalDateTime".equals(entitySimpleName)) {
								tl(4, entitySimpleNameComplet, " val = o.get", entityVarCapitalized, "();");
								l();
								if(entityDisplayName != null) {
									t(4).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
								t(4).e("input").l();
								t(5).dal("type", "text");
								t(5).dal("class", "w3-input w3-border datepicker ");
								t(5).dal("placeholder", "MM/DD/YYYY");
								t(5).dal("data-timeformat", "MM/DD/YYYY");
								t(5).dal("onclick", "enleverLueur($(this)); ");
								if(entityDescription != null)
									t(5).dal("title", entityDescription + " (MM/DD/YYYY)");
								tl(5, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"MM/dd/yyyy\", Locale.forLanguageTag(\"en-US\")).format(val))");
								t(5).s(".a(\"onchange\", \"");
									s("var t = moment(this.value, 'MM/DD/YYYY'); ");
									s("if(t) { ");
										s("var s = t.format('YYYY-MM-DD'); ");
										s("$(this).next().val(s); ");
										s("$(this).next().trigger('change'); ");
									s("} ");
								l("\")");
								tl(5, ".fg();");

								t(4).e("input").l();
								t(5).dal("type", "hidden");
								t(5).dal("class", "value", entityVarCapitalized);
								t(5).dal("name", entityVar);
								t(5).dal("onchange", "envoyer(); ");
								tl(5, ".a(\"value\", o.str", entityVarCapitalized, "())");
								t(4).dfgl();
							}
							else if("LocalTime".equals(entitySimpleName)) {
								tl(4, entitySimpleNameComplet, " val = o.get", entityVarCapitalized, "();");
								l();
								if(entityDisplayName != null) {
									t(4).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
								t(4).e("input").l();
								t(5).dal("type", "text");
								t(5).dal("class", "w3-input w3-border timepicker ");
								t(5).dal("placeholder", "HH:MM AM");
								t(5).dal("onclick", "enleverLueur($(this)); ");
								if(entityDescription != null)
									t(5).da("title", entityDescription + " (h:mm a)");
								tl(5, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"MM/dd/yyyy\", Locale.forLanguageTag(\"en-US\")).format(val))");
								t(5).s(".a(\"onchange\", \"");
									s("var t = parseTime(this.value); ");
									s("if(t) { ");
										s("var s = dateFormat(t, \"'h'MM\"); ");
										s("$(this).next().val(s); ");
										s("$(this).next().trigger('change'); ");
									s("} ");
								l("\")");
								tl(5, ".fg();");

								t(4).e("input").l();
								t(5).dal("type", "hidden");
								t(5).dal("class", "value", entityVarCapitalized);
								t(5).dal("name", entityVar);
								t(5).dal("onchange", "envoyer(); ");
								tl(5, ".a(\"value\", val == null ? \"\" : o.str", entityVarCapitalized, "())");
								t(4).dfgl();
							}
							else if("Boolean".equals(entitySimpleName)) {
								t(4).e("input").l();
								t(5).dal("type", "hidden");
								t(5).dal("name", entityVar);
								t(5).dal("value", "false");
								t(4).dfgl();
								l();
								t(4).e("input").l();
								t(5).dal("type", "checkbox");
								t(5).dal("class", "value", entityVarCapitalized);
								t(5).dal("name", entityVar);
								t(5).dal("value", "true");
								t(5).da("onchange", "envoyer(); ").l(";");
								tl(5, "if(o.get", entityVarCapitalized, "() != null && o.get", entityVarCapitalized, "())");
								t(6).a("checked", "checked").l(";");
								t(4).fgl();
								l();
								if(entityDisplayName != null) {
									t(4).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
							}
							else {
								if(entityDisplayName != null) {
									t(4).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
								l();
								if(entityMultiline)
									t(4).e("textarea").l();
								else
									t(4).e("input").l().t(7).dal("type", "text");

								t(5).dal("class", "value", entityVarCapitalized);
								t(5).dal("name", entityVar);
								t(5).dal("class", "w3-input w3-border ");
								if(entityDisplayName != null) {
									t(5).dal("placeholder", entityDisplayName);
								}
								if(entityDescription != null) {
									t(5).dal("title", entityDescription);
								}
								t(5).dal("onchange", "envoyer(); ");
								t(4).dfgl();
								l();
							}

				//			if().da("class", objets).da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
							t(3).bgl("div");
						}
					}
					rechercheSolr.setStart(i.intValue() + searchLines);
					rechercheReponse = solrClientComputate.query(rechercheSolr);
					rechercheListe = rechercheReponse.getResults();
				}
				t(2).bgl("div");
			}
			tl(1, "}");
			l();
			tl(1, "@Override public void htmlBody", classSimpleName, "GenPage() {");
			l();
			tl(2, "if(list", classSimpleName, ".size() == 0) {");
			t(3).l("//", contextNoneNameFound);
			l();
			t(3).be("h1").dfl();
			if(StringUtils.isNotBlank(contextIconGroup) && StringUtils.isNotBlank(contextIconName)) {
				t(4).e("i").da("class", "fa", StringUtils.substring(contextIconGroup, 0, 1), " fa-", contextIconName, " w3-margin-right-4 ").df().dgl("i");
				t(4).e("span").da("class", " ").df().dsx(contextNoneNameFound).dgl("i");
			}
			t(3).bgl("h1");
			tl(2, "} else if(list", classSimpleName, ".size() == 1) {");
			t(3).l("// ", contextAName);
			t(3).l(classSimpleName, " o = list", classSimpleName, ".first();");
			l();
			t(3).be("h1").dfl();
			if(StringUtils.isNotBlank(contextIconGroup) && StringUtils.isNotBlank(contextIconName)) {
				t(4).e("i").da("class", "fa", StringUtils.substring(contextIconGroup, 0, 1), " fa-", contextIconName, " w3-margin-right-4 ").df().dgl("i");
				t(4).e("span").da("class", " ").df().dsx(contextAName).dgl("i");
			}
			t(3).bgl("h1");
			t(3).be("h2").dfl();
			if(classEntityVars != null && classEntityVars.contains("pageH2"))
				t(4).e("span").da("class", " ").df().s(".sx(o.getPageH2())").dgl("i");
			t(3).bgl("h2");
			t(3).be("div").da("class", "w3-card w3-margin w3-padding w3-margin-top w3-show ").dfl();
			l();
			tl(4, "htmlForm", classSimpleName, "(o);");
			l();
			t(3).bgl("div");
			tl(2, "} else {");
			t(3).l("// ", contextNamePlural);
			l();
			t(3).be("h1").dfl();
			if(StringUtils.isNotBlank(contextIconGroup) && StringUtils.isNotBlank(contextIconName)) {
				t(4).e("i").da("class", "fa", StringUtils.substring(contextIconGroup, 0, 1), " fa-", contextIconName, " w3-margin-right-4 ").df().dgl("i");
				t(4).e("span").da("class", " ").df().dsx(contextNamePlural).dgl("i");
			}
			t(3).bgl("h1");
			tl(2, "}");

			t(2).e("div").dfl();
			l();
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_frFR_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_frFR_stored_string");
				String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_stored_string");
				String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_stored_string");

				if("application/json".equals(classeApiTypeMediaMethode) && !"GET".equals(classeApiMethodeMethode)) {
					Integer tab = classeApiMethodeMethode.contains("PATCH") || classeApiMethodeMethode.contains("DELETE") || classeApiMethodeMethode.contains("POST") ? 0 : 1;
					String methodTitle = null;

					if("POST".equals(classeApiMethodeMethode))
						methodTitle = "Create " + contextAName;
					else if("PUT".equals(classeApiMethodeMethode))
						methodTitle = "Replace " + contextTheName;
					else if("PATCH".equals(classeApiMethodeMethode))
						methodTitle = "Modify des " + contextNamePlural;
					else if("DELETE".equals(classeApiMethodeMethode))
						methodTitle = "Delete des " + contextNamePlural;


					l();
					if(tab > 0)
						tl(2, "if(list", classSimpleName, ".size() == 1) {");
					t(2 + tab).e("button").l();
					t(3 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-", contexteCouleur, " ");
					t(3 + tab).dal("onclick", "$('#", classeApiOperationIdMethode, "Modale').show(); ");
					t(3 + tab).df().dsx(methodTitle).l();
					t(2 + tab).dgl("button");
					{ t(2 + tab).be("div").da("id", classeApiOperationIdMethode, "Modale").da("class", "w3-modal ").dfl();
						{ t(3 + tab).be("div").da("class", "w3-modal-content w3-card-4 ").dfl();
							{ t(4 + tab).be("header").da("class", "w3-container w3-", contexteCouleur, " ").dfl();
								t(5 + tab).e("span").da("class", "w3-button w3-display-topright ").da("onclick", "$('#", classeApiOperationIdMethode, "Modale').hide(); ").df().dsx("×").dgl("span");
								t(5 + tab).e("h2").da("class", "").df().dsx(methodTitle).dgl("h2");
							} t(4 + tab).bgl("header");

							{ t(4 + tab).be("div").da("class", "w3-container ").dfl();
								tl(5+ tab, classSimpleName, " o = new ", classSimpleName, "();");
								l();
								{ t(5 + tab).be("form").da("id", classeApiOperationIdMethode, "Formulaire").dfl();
									tl(6 + tab, "htmlForm", classSimpleName, "(o);");
								} t(5 + tab).bgl("form");
								t(5 + tab).e("button").l();
								t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-", contexteCouleur, " ");

//								tl(6 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classeApiOperationIdMethode, "Formulaire').serializeArray().reduce(function(a, x) { a[x.name] = x.value; return a; }, {}))); \")");
//								tl(6 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classeApiOperationIdMethode, "Formulaire').serializeObject())); \")");

								if("POST".equals(classeApiMethodeMethode))
									tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Formulaire')); \")");
								else if("PUT".equals(classeApiMethodeMethode))
									tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \", $('#", classeApiOperationIdMethode, "Formulaire')); \")");
								else if(tab > 0)
									tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \"); \")");
								else
									t(6 + tab).dal("onclick", classeApiOperationIdMethode, "(); ");

								t(6 + tab).df().dsx(methodTitle).l();
								t(5 + tab).dgl("button");
								l();
							} t(4 + tab).bgl("div");
						} t(3 + tab).bgl("div");
					} t(2 + tab).bgl("div");

					l();
					if(tab > 0)
						tl(2, "}");
				}
			}
			t(2).gl("div");

			tl(1, "}");
			tl(0, "}");
		}

		if(writerPageClass != null)
			writerPageClass.flushClose();
		writerGenPageClass.flushClose();
		writerPageCss.flushClose();
		writerPageJs.flushClose();
//		writerGenPageClass.close();
	}
}
