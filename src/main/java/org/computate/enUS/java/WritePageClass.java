package org.computate.enUS.java;

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
			tl(1, "@Override public void htmlBody", classSimpleName, "GenPage() {");
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

			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1000000);
			rechercheSolr.addFilterQuery("classAbsolutePath_indexed_string:" + ClientUtils.escapeQueryChars(classAbsolutePath));
			rechercheSolr.addFilterQuery("classeEtendGen_indexed_boolean:true");
			rechercheSolr.addSort("entityHtmlLine_indexed_int", ORDER.asc);
			rechercheSolr.addSort("entityHtmlCellule_indexed_int", ORDER.asc);
			QueryResponse rechercheReponse = solrClientComputate.query(rechercheSolr);
			SolrDocumentList rechercheListe = rechercheReponse.getResults();
			Integer searchLines = rechercheSolr.getRows();
			Integer searchLine = -1;
			Integer searchLineActual;

			if(rechercheListe.size() > 0) {
				for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=searchLines) {
					for(Integer j = 0; j < rechercheListe.size(); j++) {
						SolrDocument entiteDocumentSolr = rechercheListe.get(j);
						String entityVar = (String)entiteDocumentSolr.get("entityVar_" + languageName + "_stored_string");
						String entityVarCapitalized = (String)entiteDocumentSolr.get("entityVarCapitalized_" + languageName + "_stored_string");
						String entitySimpleName = (String)entiteDocumentSolr.get("entitySimpleName_" + languageName + "_stored_string");
						String entitySimpleNameGenerique = (String)entiteDocumentSolr.get("entitySimpleNameNomSimple_" + languageName + "_stored_string");
						String entityDescription = (String)entiteDocumentSolr.get("entityDescription_" + languageName + "_stored_string");
						String entityDisplayName = (String)entiteDocumentSolr.get("entityDisplayName_" + languageName + "_stored_string");
						Boolean entityHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityHtml_stored_boolean"));
						Boolean entityMultiline = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityMultiline_stored_boolean"));
						if(entityHtml) {
							searchLineActual = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entityHtmlLine_stored_int"), 0);
							if(searchLine != searchLineActual) {
								if(searchLine != -1)
									t(4).bgl("div");
								t(4).be("div").da("class", "w3-cell-row ").dfl();
								searchLine = searchLineActual;
							}
							t(5).be("div").da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
							if("LocalDate".equals(entitySimpleName)) {
								if(entityDisplayName != null) {
									t(6).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
								l();
								t(6).e("input").l();
								t(7).dal("type", "text");
								t(7).dal("class", "w3-input w3-border datepicker ");
								t(7).dal("placeholder", "MM/DD/YYYY");
								t(7).dal("data-timeformat", "MM/DD/YYYY");
								t(7).dal("onclick", "enleverLueur($(this)); ");
								if(entityDescription != null)
									t(7).da("title", entityDescription + " (MM/DD/YYYY)");
								tl(7, ".a(\"value\", DateTimeFormatter.ofPattern(\"MM/dd/yyyy\", Locale.forLanguageTag(\"en-US\")).format(o.get", entityVarCapitalized, "()))");
								t(7).s(".a(\"onchange\", \"");
									s("var t = moment(this.value, 'MM/DD/YYYY'); ");
									s("if(t) { ");
										s("var s = t.format('YYYY-MM-DD'); ");
										s("$(this).next().val(s); ");
										s("$(this).next().trigger('change'); ");
									s("} ");
								l("\")");
								tl(7, ".fg();");

								t(6).e("input").l();
								t(7).dal("name", entityVar);
								t(7).dal("type", "hidden");
								t(7).dal("onchange", "envoyer(); ");
								tl(7, ".a(\"value\", o.str", entityVarCapitalized, "())");
								t(6).dfgl();
							}
							else if("LocalDateTime".equals(entitySimpleName)) {
								if(entityDisplayName != null) {
									t(6).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
								l();
								t(6).e("input").l();
								t(7).dal("type", "text");
								t(7).dal("class", "w3-input w3-border datepicker ");
								t(7).dal("placeholder", "MM/DD/YYYY");
								t(7).dal("data-timeformat", "MM/DD/YYYY");
								t(7).dal("onclick", "enleverLueur($(this)); ");
								if(entityDescription != null)
									t(7).da("title", entityDescription + " (MM/DD/YYYY)");
								tl(7, ".a(\"value\", DateTimeFormatter.ofPattern(\"MM/dd/yyyy\", Locale.forLanguageTag(\"en-US\")).format(o.get", entityVarCapitalized, "()))");
								t(7).s(".a(\"onchange\", \"");
									s("var t = moment(this.value, 'MM/DD/YYYY'); ");
									s("if(t) { ");
										s("var s = t.format('YYYY-MM-DD'); ");
										s("$(this).next().val(s); ");
										s("$(this).next().trigger('change'); ");
									s("} ");
								l("\")");
								tl(7, ".fg();");

								t(6).e("input").l();
								t(7).dal("type", "hidden");
								t(7).dal("name", entityVar);
								t(7).dal("onchange", "envoyer(); ");
								tl(7, ".a(\"value\", o.str", entityVarCapitalized, "())");
								t(6).dfgl();
							}
							else if("LocalTime".equals(entitySimpleName)) {
								if(entityDisplayName != null) {
									t(6).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
								l();
								t(6).e("input").l();
								t(7).dal("type", "text");
								t(7).dal("class", "w3-input w3-border timepicker ");
								t(7).dal("placeholder", "HH:MM AM");
								t(7).dal("onclick", "enleverLueur($(this)); ");
								if(entityDescription != null)
									t(7).da("title", entityDescription + " (h:mm a)");
								tl(7, ".a(\"value\", DateTimeFormatter.ofPattern(\"MM/dd/yyyy\", Locale.forLanguageTag(\"en-US\")).format(o.get", entityVarCapitalized, "()))");
								t(7).s(".a(\"onchange\", \"");
									s("var t = parseTime(this.value); ");
									s("if(t) { ");
										s("var s = dateFormat(t, \"'h'MM\"); ");
										s("$(this).next().val(s); ");
										s("$(this).next().trigger('change'); ");
									s("} ");
								l("\")");
								tl(7, ".fg();");

								t(6).e("input").l();
								t(7).dal("type", "hidden");
								t(7).dal("name", entityVar);
								t(7).dal("onchange", "envoyer(); ");
								tl(7, ".a(\"value\", o.str", entityVarCapitalized, "())");
								t(6).dfgl();
							}
							else if("Boolean".equals(entitySimpleName)) {
								t(6).e("input").l();
								t(7).dal("type", "hidden");
								t(7).dal("name", entityVar);
								t(7).dal("value", "false");
								t(6).dfgl();
								l();
								t(6).e("input").l();
								t(7).dal("type", "checkbox");
								t(7).dal("name", entityVar);
								t(7).dal("value", "true");
								t(7).da("onchange", "envoyer(); ").l(";");
								tl(7, "if(o.get", entityVarCapitalized, "() != null && o.get", entityVarCapitalized, "())");
								t(8).a("checked", "checked").l(";");
								t(6).fgl();
								l();
								if(entityDisplayName != null) {
									t(6).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
							}
							else {
								if(entityDisplayName != null) {
									t(6).e("label").da("class", "").df().dsx(entityDisplayName).dgl("label");
								}
								l();
								if(entityMultiline)
									t(6).e("textarea").l();
								else
									t(6).e("input").l().t(7).dal("type", "text");

								t(7).dal("name", entityVar);
								t(7).dal("class", "w3-input w3-border ");
								if(entityDisplayName != null) {
									t(7).dal("placeholder", entityDisplayName);
								}
								if(entityDescription != null) {
									t(7).dal("title", entityDescription);
								}
								t(7).dal("onchange", "envoyer(); ");
								t(6).dfgl();
								l();
							}

				//			if().da("class", objets).da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
							t(5).bgl("div");
						}
					}
					rechercheSolr.setStart(i.intValue() + searchLines);
					rechercheReponse = solrClientComputate.query(rechercheSolr);
					rechercheListe = rechercheReponse.getResults();
				}
				t(4).bgl("div");
			}

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
