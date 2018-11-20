package org.computate.frFR.java;

import java.io.PrintWriter;

import org.apache.solr.client.solrj.SolrQuery;

/**   
 * nomCanonique.enUS: org.computate.enUS.java.WriteApiClass
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 * apiUri.enUS: /api/v1/oai/moissoneur
 */  
public class EcrireApiClasse extends EcrireGenClasse {  

	protected PrintWriter auteurApiGenClasse;
	protected String classeNomSimpleApi;
	protected String classeNomSimpleApiGen;

	public void apiCodeClasseDebut(String langueNom) throws Exception {
		o = auteurApiGenClasse;
		l("package ", classeNomEnsemble, ";");
		l();
		if(classeImportationsGenApi.size() > 0) { 
			for(String classeImportation : classeImportationsGenApi) {
				l("import ", classeImportation, ";");
			}
			l();
		}

		tl(0, "");
		ecrireCommentaire(classeCommentaire, 0); 
		s("public class ", classeNomSimpleApiGen);
//		l(" extends HttpServlet {");
//		l(" implements Handler<RoutingContext> {");
		l(" {");
		l();
		tl(1, "private static final Logger LOGGER = LoggerFactory.getLogger(", classeNomSimpleApiGen, ".class);");
		l();
		tl(1, "public static final String VAL_virguleEspace = \", \";");
		tl(1, "public static final String VAL_citation = \"\\\"\";");
		tl(1, "public static final String VAL_citationDeuxPointsEspaceCitation = \"\\\": \\\"\";");
		tl(1, "public static final String VAL_citationDeuxPointsEspace = \"\\\": \";");
		tl(1, "public static final String VAL_citationLigne = \"\\\"\\n\";");
		tl(1, "public static final String VAL_ligne = \"\\n\";");
		tl(1, "public static final String VAL_citationVirguleEspaceCitation = \"\\\", \\\"\";");
		tl(1, "public static final String VAL_citationDeuxPointsEspaceGuillmets = \"\\\": [\";");
		tl(1, "public static final String VAL_guillmetsFin = \"]\";");
	}

	public void apiCodeClasseFin(String langueNom) throws Exception {
		o = auteurApiGenClasse;

		s(wApiChamps.toString());
		l();
		tl(1, "public void handleGet", classeNomSimple, "(SiteContexte siteContexte) {");
		tl(2, "Router siteRouteur = siteContexte.getSiteRouteur_();");

		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
		Integer tBase = 0;
		if(classeRolesTrouve) {
			tBase = 6;
			tl(3, "rc.user().isAuthorized(\"realm:view-account\", authRes -> {");
			tl(4, "try {");
			tl(5, "if (authRes.result() == Boolean.TRUE) {");
		}
		else {
			tBase = 4;
			tl(3, "try {");
		}
		tl(tBase, "rc.response().putHeader(\"content-type\", \"application/json\").setChunked(true);");
		tl(tBase, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, rc);");
		tl(tBase, "SolrQuery rechercheSolr = requeteSite.getRechercheSolr_();");
		tl(tBase, "SolrDocumentList resultatsRecherche = requeteSite.getReponseRecherche().getResults();");
		tl(tBase, "Integer rechercheLignes = rechercheSolr.getRows();");
		l();
		tl(tBase, "genererGetDebut", classeNomSimple, "(requeteSite);");
		tl(tBase, "for(long i = resultatsRecherche.getStart(); i < resultatsRecherche.getNumFound(); i+=rechercheLignes) {");
		tl(tBase + 1, "for(int j = 0; j < resultatsRecherche.size(); j++) {");
		tl(tBase + 2, "long resultatIndice = i + j;");
		tl(tBase + 2, "SolrDocument documentSolr = resultatsRecherche.get(j);");
		tl(tBase + 2, "ResultatRecherche resultatRecherche = new ResultatRecherche();");
		tl(tBase + 2, "resultatRecherche.setRequeteSite_(requeteSite);");
		tl(tBase + 2, "resultatRecherche.setDocumentSolr(documentSolr);");
		tl(tBase + 2, "resultatRecherche.setResultatIndice(resultatIndice);");
		tl(tBase + 2, "genererGetIndividuel", classeNomSimple, "(resultatRecherche);");
		tl(tBase + 1, "}");
		tl(tBase, "}");
		tl(tBase, "genererGetFin", classeNomSimple, "(requeteSite);");
		tl(tBase, "requeteSite.getReponseServeur().end();");
		if(classeRolesTrouve) {
			tl(5, "}");
			tl(5, "else {");
			tl(6, "rc.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "LOGGER.error(\"Error: \", e.getMessage());");
			tl(5, "rc.fail(e);");
			tl(4, "}");
			tl(3, "});");
		}
		else {
			tl(3, "} catch(Exception e) {");
			tl(4, "LOGGER.error(\"Error: \", e.getMessage());");
			tl(4, "rc.fail(e);");
			tl(3, "}");
		}
		tl(2, "});");
		tl(1, "}");
		l();
		tl(1, "public void genererGetDebut", classeNomSimple, "(RequeteSite requeteSite) {");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
		tl(2, "QueryResponse reponseRecherche = requeteSite.getReponseRecherche();");
		tl(2, "reponseServeur.write(\"{\\n\");");
		tl(2, "Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());");
		tl(2, "Long millisTransmission = reponseRecherche.getElapsedTime();");
		tl(2, "Long numCommence = reponseRecherche.getResults().getStart();");
		tl(2, "Long numTrouve = reponseRecherche.getResults().getNumFound();");
		tl(2, "Integer numRetourne = reponseRecherche.getResults().size();");
		tl(2, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
		tl(2, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
		tl(2, "Exception exceptionRecherche = reponseRecherche.getException();");
		l();
		tl(2, "reponseServeur.write(\"\\t\\\"numCommence\\\": \");");
		tl(2, "reponseServeur.write(numCommence.toString());");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"numTrouve\\\": \");");
		tl(2, "reponseServeur.write(numTrouve.toString());");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"numRetourne\\\": \");");
		tl(2, "reponseServeur.write(numRetourne.toString());");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsRecherche\\\": \\\"\");");
		tl(2, "reponseServeur.write(tempsRecherche);");
		tl(2, "reponseServeur.write(\"\\\"\");");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsTransmission\\\": \\\"\");");
		tl(2, "reponseServeur.write(tempsTransmission);");
		tl(2, "reponseServeur.write(\"\\\"\");");
		l();
		tl(2, "if(exceptionRecherche != null) {");
		tl(3, "reponseServeur.write(\",\\n\\t\\\"exceptionRecherche\\\": \\\"\");");
		tl(3, "reponseServeur.write(exceptionRecherche.getMessage());");
		tl(3, "reponseServeur.write(\"\\\"\");");
		tl(2, "}");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"resultats\\\": [\\n\");");
		tl(1, "}");
		l();
		tl(1, "public void genererGetIndividuel", classeNomSimple, "(ResultatRecherche resultatRecherche) throws Exception {");
		tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
		tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();");
		tl(2, "Long resultatIndice = resultatRecherche.getResultatIndice();");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
		tl(2, "reponseServeur.write(\"\\t\\t\");");
		tl(2, "if(resultatIndice > 0)");
		tl(3, "reponseServeur.write(\", \");");
		tl(2, "reponseServeur.write(\"{\\n\");");
		tl(2, "Collection<String> champNoms = documentSolr.getFieldNames();");
		tl(2, "Integer j = 0;");
		tl(2, "for(String champNomStocke : champNoms) {");
		tl(3, "Collection<Object> champValeurs = documentSolr.getFieldValues(champNomStocke);");
		tl(3, "j = genererGet", classeNomSimple, "(j, resultatRecherche, champNomStocke, champValeurs);");
		tl(2, "}");
		tl(2, "reponseServeur.write(\"\\t\\t}\\n\");");
		tl(1, "}");
		l();
		tl(1, "public void genererGetFin", classeNomSimple, "(RequeteSite requeteSite) {");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//		tl(2, "if(exceptionRecherche != null) {");
//		l();
//		tl(4, "reponseServeur.write(\"\\t\\t}\\n\");");
//		tl(3, "}");
//		tl(2, "}");
		tl(2, "reponseServeur.write(\"\\t]\\n\");");
		tl(2, "reponseServeur.write(\"}\\n\");");
		tl(1, "}");
//		tl(1, "@Override protected void doGet(HttpServerRequest requeteServeur, HttpServerResponse reponseServeur) throws ServletException, IOException {");
		l();
		tl(1, "public String varIndexe", classeNomSimple, "(String entiteVar) throws Exception {");
		tl(2, "switch(entiteVar) {");
		s(wApiGet.toString());
		tl(3, "default:");
		tl(4, "throw new Exception(String.format(\"\\\"%s\\\" n'est pas une entité indexé. \", entiteVar));");
		tl(2, "}");
		tl(1, "}");
		l();
		tl(1, "public SolrQuery genererRecherche", classeNomSimple, "(HttpServerRequest requeteServeur) throws Exception {");
		tl(2, "String entiteVar = null;");
		tl(2, "String valeurIndexe = null;");
		tl(2, "String varIndexe = null;");
		tl(2, "String valeurTri = null;");
		tl(2, "Integer rechercheDebut = null;");
		tl(2, "Integer rechercheNum = null;");
		tl(2, "SolrQuery rechercheSolr = new SolrQuery();");
		tl(2, "rechercheSolr.setQuery(\"*:*\");");
		tl(2, "rechercheSolr.setRows(1000000);");
		tl(2, "rechercheSolr.addSort(\"partNumero_indexed_int\", ORDER.asc);");
		tl(2, "MultiMap paramMap = requeteServeur.params();");
		tl(2, "for(String paramCle : paramMap.names()) {");
		tl(3, "List<String> paramValeurs = paramMap.getAll(paramCle);");
		tl(3, "for(String paramValeur : paramValeurs) {");
		tl(4, "switch(paramCle) {");

		tl(5, "case \"q\":");
		tl(6, "entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, \":\"));");
		tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, \":\"));");
		tl(6, "varIndexe = varIndexe", classeNomSimple, "(paramCle);");
		tl(6, "rechercheSolr.setQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
		tl(6, "break;");

		tl(5, "case \"fq\":");
		tl(6, "entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, \":\"));");
		tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, \":\"));");
		tl(6, "varIndexe = varIndexe", classeNomSimple, "(paramCle);");
		tl(6, "rechercheSolr.addFilterQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
		tl(6, "break;");

		tl(5, "case \"sort\":");
		tl(6, "entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, \" \"));");
		tl(6, "valeurTri = StringUtils.trim(StringUtils.substringAfter(paramValeur, \" \"));");
		tl(6, "varIndexe = varIndexe", classeNomSimple, "(paramCle);");
		tl(6, "rechercheSolr.addSort(varIndexe, ORDER.valueOf(valeurTri));");
		tl(6, "break;");

		tl(5, "case \"fl\":");
		tl(6, "entiteVar = StringUtils.trim(paramValeur);");
		tl(6, "varIndexe = varIndexe", classeNomSimple, "(paramCle);");
		tl(6, "rechercheSolr.addField(varIndexe);");
		tl(6, "break;");

		tl(5, "case \"start\":");
		tl(6, "rechercheDebut = Integer.parseInt(paramValeur);");
		tl(6, "rechercheSolr.setStart(rechercheDebut);");
		tl(6, "break;");

		tl(5, "case \"rows\":");
		tl(6, "rechercheNum = Integer.parseInt(paramValeur);");
		tl(6, "rechercheSolr.setRows(rechercheNum);");
		tl(6, "break;");

		tl(4, "}");
		tl(3, "}");
		tl(2, "}");
		tl(2, "return rechercheSolr;");
		tl(1, "}");
		l();
		tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, RoutingContext contexteItineraire) throws Exception {");
		tl(2, "Vertx vertx = siteContexte.getVertx_();");
		tl(2, "SolrQuery rechercheSolr = genererRecherche", classeNomSimple, "(contexteItineraire.request());");
		l();
		tl(2, "RequeteSite requeteSite = new RequeteSite();");
		tl(2, "requeteSite.setVertx_(vertx);");
		tl(2, "requeteSite.setContexteItineraire_(contexteItineraire);");
		tl(2, "requeteSite.setSiteContexte_(siteContexte);");
		tl(2, "requeteSite.setRechercheSolr_(rechercheSolr);");
		tl(2, "requeteSite.initLoinRequeteSite(requeteSite);");
		l();

		tl(2, "UtilisateurSite utilisateurSite = new UtilisateurSite();");
		tl(2, "utilisateurSite.initLoinUtilisateurSite(requeteSite);");
		tl(2, "requeteSite.setUtilisateurSite(utilisateurSite);");
		tl(2, "utilisateurSite.setRequeteSite_(requeteSite);");

		tl(2, "return requeteSite;");
		tl(1, "}");
		l();
//		tl(1, "public Integer genererGet", classeNomSimple, "(Integer j, PrintWriter ecrivain, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {");
		tl(1, "public Integer genererGet", classeNomSimple, "(Integer j, ResultatRecherche resultatRecherche, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {");
		tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
		tl(2, "if(!champValeurs.isEmpty()) {");
		tl(3, "Object champValeur = champValeurs.iterator().next();");
		tl(3, "if(champValeur != null) {");
		s(wApiGenererGet.toString());
		tl(3, "}");
		tl(2, "}");
		tl(2, "return j;");
		tl(1, "}");
		l();
		tl(1, "protected void handlePost", classeNomSimple, "(SiteContexte siteContexte) {");
		tl(2, "Router siteRouteur = siteContexte.getSiteRouteur_();");

		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
		tBase = 0;
		if(classeRolesTrouve) {
			tBase = 6;
			tl(3, "rc.user().isAuthorized(\"realm:view-account\", authRes -> {");
			tl(4, "try {");
			tl(5, "if (authRes.result() == Boolean.TRUE) {");
		}
		else {
			tBase = 4;
			tl(3, "try {");
		}
		tl(tBase, "rc.response().putHeader(\"content-type\", \"application/json\").setChunked(true);");
		tl(tBase, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, rc);");
		tl(tBase, "SolrQuery rechercheSolr = requeteSite.getRechercheSolr_();");
		tl(tBase, "SolrDocumentList resultatsRecherche = requeteSite.getReponseRecherche().getResults();");
		tl(tBase, "Integer rechercheLignes = rechercheSolr.getRows();");
		l();
		tl(tBase, "genererPostDebut", classeNomSimple, "(requeteSite);");
		tl(tBase, "for(long i = resultatsRecherche.getStart(); i < resultatsRecherche.getNumFound(); i+=rechercheLignes) {");
		tl(tBase + 1, "for(int j = 0; j < resultatsRecherche.size(); j++) {");
		tl(tBase + 2, "long resultatIndice = i + j;");
		tl(tBase + 2, "SolrDocument documentSolr = resultatsRecherche.get(j);");
		tl(tBase + 2, "ResultatRecherche resultatRecherche = new ResultatRecherche();");
		tl(tBase + 2, "resultatRecherche.setRequeteSite_(requeteSite);");
		tl(tBase + 2, "resultatRecherche.setDocumentSolr(documentSolr);");
		tl(tBase + 2, "resultatRecherche.setResultatIndice(resultatIndice);");
		tl(tBase + 2, "genererPostIndividuel", classeNomSimple, "(resultatRecherche);");
		tl(tBase + 1, "}");
		tl(tBase, "}");
		tl(tBase, "genererPostFin", classeNomSimple, "(requeteSite);");
		tl(tBase, "requeteSite.getReponseServeur().end();");
		if(classeRolesTrouve) {
			tl(5, "}");
			tl(5, "else {");
			tl(6, "rc.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "LOGGER.error(\"Error: \", e.getMessage());");
			tl(5, "rc.fail(e);");
			tl(4, "}");
			tl(3, "});");
		}
		else {
			tl(3, "} catch(Exception e) {");
			tl(4, "LOGGER.error(\"Error: \", e.getMessage());");
			tl(4, "rc.fail(e);");
			tl(3, "}");
		}
		tl(2, "});");
		tl(1, "}");
		l();
		tl(1, "public void genererPostDebut", classeNomSimple, "(RequeteSite requeteSite) {");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
		tl(2, "QueryResponse reponseRecherche = requeteSite.getReponseRecherche();");
		tl(2, "reponseServeur.write(\"{\\n\");");
		tl(2, "Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());");
		tl(2, "Long millisTransmission = reponseRecherche.getElapsedTime();");
		tl(2, "Long numCommence = reponseRecherche.getResults().getStart();");
		tl(2, "Long numTrouve = reponseRecherche.getResults().getNumFound();");
		tl(2, "Integer numRetourne = reponseRecherche.getResponse().size();");
		tl(2, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
		tl(2, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
		tl(2, "Exception exceptionRecherche = reponseRecherche.getException();");
		l();
		tl(2, "reponseServeur.write(\"\\t\\\"numCommence\\\": \");");
		tl(2, "reponseServeur.write(numCommence.toString());");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"numTrouve\\\": \");");
		tl(2, "reponseServeur.write(numTrouve.toString());");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"numRetourne\\\": \");");
		tl(2, "reponseServeur.write(numRetourne.toString());");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsRecherche\\\": \\\"\");");
		tl(2, "reponseServeur.write(tempsRecherche);");
		tl(2, "reponseServeur.write(\"\\\"\");");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsTransmission\\\": \\\"\");");
		tl(2, "reponseServeur.write(tempsTransmission);");
		tl(2, "reponseServeur.write(\"\\\"\");");
		l();
		tl(2, "if(exceptionRecherche != null) {");
		tl(3, "reponseServeur.write(\",\\n\\t\\\"exceptionRecherche\\\": \\\"\");");
		tl(3, "reponseServeur.write(exceptionRecherche.getMessage());");
		tl(3, "reponseServeur.write(\"\\\"\");");
		tl(2, "}");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"resultats\\\": [\\n\");");
		tl(1, "}");
		l();
		tl(1, "public void genererPostIndividuel", classeNomSimple, "(ResultatRecherche resultatRecherche) throws Exception {");
		tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
		tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();");
		tl(2, "Long resultatIndice = resultatRecherche.getResultatIndice();");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
		tl(2, "reponseServeur.write(\"\\t\\t\");");
		tl(2, "if(resultatIndice > 0)");
		tl(3, "reponseServeur.write(\", \");");
		tl(2, "reponseServeur.write(\"{\\n\");");
		tl(2, "Collection<String> champNoms = documentSolr.getFieldNames();");
		tl(2, "Integer j = 0;");
		tl(2, "for(String champNomStocke : champNoms) {");
		tl(3, "Collection<Object> champValeurs = documentSolr.getFieldValues(champNomStocke);");
		tl(3, "j = genererPost", classeNomSimple, "(j, resultatRecherche, champNomStocke, champValeurs);");
		tl(2, "}");
		tl(2, "reponseServeur.write(\"\\t\\t}\\n\");");
		tl(1, "}");
		l();
//		tl(1, "public Integer genererPost", classeNomSimple, "(Integer j, PrintWriter ecrivain, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {");
		tl(1, "public Integer genererPost", classeNomSimple, "(Integer j, ResultatRecherche resultatRecherche, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {");
		tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
		tl(2, "if(!champValeurs.isEmpty()) {");
		tl(3, "Object champValeur = champValeurs.iterator().next();");
		tl(3, "if(champValeur != null) {");
		s(wApiGenererGet.toString());
		tl(3, "}");
		tl(2, "}");
		tl(2, "return j;");
		tl(1, "}");
		l();
		tl(1, "public void genererPostFin", classeNomSimple, "(RequeteSite requeteSite) {");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//		tl(2, "if(exceptionRecherche != null) {");
//		l();
//		tl(4, "reponseServeur.write(\"\\t\\t}\\n\");");
//		tl(3, "}");
//		tl(2, "}");
		tl(2, "reponseServeur.write(\"\\t]\\n\");");
		tl(2, "reponseServeur.write(\"}\\n\");");
		tl(1, "}");
//		l();
//		tl(1, "public void genererErreur(RequeteSite requeteSite, Exception e) {");
//		tl(2, "e.printStackTrace();");
//		tl(2, "try {");
//		tl(3, "MimeMessage message = new MimeMessage(requeteSite.SiteContexte_.sessionCourriel);");
//		tl(3, "message.setFrom(new InternetAddress(requeteSite.configSite_.mailAdmin));");
//		tl(3, "InternetAddress destinaires[] = new InternetAddress[1];");
//		tl(3, "destinaires[0] = new InternetAddress(requeteSite.configSite_.mailAdmin);");
//		tl(3, "message.setRecipients(Message.RecipientType.TO, destinaires);");
//		tl(3, "String nomDomaine = requeteSite.configSite_.nomDomaine;");
//		tl(3, "String sujet = nomDomaine + \" erreur \" + \" \" + requeteSite.utilisateurNom + \" \" + requeteSite.requeteServeur.getRequestURI();");
//		tl(3, "String corps = ExceptionUtils.getStackTrace(e);");
//		tl(3, "message.setSubject(sujet);");
//		tl(3, "message.setContent(corps, \"text/plain\");");
//		tl(3, "Transport.send(message);");
//		tl(3, "String s = e.getMessage();");
//		tl(3, "requeteSite.getReponseServeur().sendError(500, s);");
//		tl(2, "} catch(Exception e2) {");
//		tl(3, "e.printStackTrace();");
//		tl(2, "}");
//		tl(1, "}");
		tl(0, "}");

		System.out.println("Ecrire: " + classeCheminApiGen); 
		auteurApiGenClasse.flush();
		auteurApiGenClasse.close();
	}
}
