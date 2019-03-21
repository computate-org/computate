package org.computate.enUS.java;

import java.io.File;
import java.util.Arrays;
import java.util.List;
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

	protected String classPageSimpleName;

	protected String classPageSuperSimpleName;

	protected String classGenPageSimpleName;

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
//		tl(1, "public void pageGet", classeNomSimple, "(", classePartsSiteContexte.nomSimple(langueNom), " siteContexte) {");
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
//		tl(tBase, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(siteContexte, contexteRoutage);");
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
//		tl(1, "public void genererGetDebut", classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite) {");
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
//		tl(2, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = resultatRecherche.getRequeteSite_();");
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
//		tl(1, "public void genererGetFin", classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite) {");
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
//		tl(1, "public ", classePartsRequeteSite.nomSimple(langueNom), " generer", classePartsRequeteSite.nomSimple(langueNom), "Pour", classeNomSimple, "(", classePartsSiteContexte.nomSimple(langueNom), " siteContexte, RoutingContext contexteItineraire) throws Exception {");
//		tl(2, "Vertx vertx = siteContexte.getVertx_();");
//		tl(2, "SolrQuery rechercheSolr = genererRecherche", classeNomSimple, "(contexteItineraire.request());");
//		l();
//		tl(2, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = new ", classePartsRequeteSite.nomSimple(langueNom), "();");
//		tl(2, "requeteSite.setVertx_(vertx);");
//		tl(2, "requeteSite.setContexteItineraire_(contexteItineraire);");
//		tl(2, "requeteSite.setSiteContexte_(siteContexte);");
//		tl(2, "requeteSite.setRechercheSolr_(rechercheSolr);");
//		tl(2, "requeteSite.initLoin", classePartsRequeteSite.nomSimple(langueNom), "(requeteSite);");
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
//		tl(2, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = resultatRecherche.getRequeteSite_();");
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
////		tl(1, "public void genererErreur(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite, Exception e) {");
////		tl(2, "e.printStackTrace();");
////		tl(2, "try {");
////		tl(3, "MimeMessage message = new MimeMessage(requeteSite.", classePartsSiteContexte.nomSimple(langueNom), "_.sessionCourriel);");
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

	public Boolean ecrireFormEntite(AllWriter wForm, String classeApiMethodeMethode) {
		int tIndex = 0;
		Boolean result = false;

		if(entiteHtml) {
			if("Recherche".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelRecherche = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLigneRecherche != rechercheLigneActuelRecherche) {
					if(rechercheLigneRecherche != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLigneRecherche = rechercheLigneActuelRecherche;
					result = true;
				}
			}
			else if("POST".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPOST = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePOST != rechercheLigneActuelPOST) {
					if(rechercheLignePOST != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePOST = rechercheLigneActuelPOST;
					result = true;
				}
			}
			else if("PATCH".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPATCH = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePATCH != rechercheLigneActuelPATCH) {
					if(rechercheLignePATCH != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePATCH = rechercheLigneActuelPATCH;
					result = true;
				}
			}
			else if("Page".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPage = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePage != rechercheLigneActuelPage) {
					if(rechercheLignePage != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePage = rechercheLigneActuelPage;
					result = true;
				}
			}

			wForm.t(3).be("div").da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
			if(entiteModifier) {

				// entiteModifier: true

				if("Page".equals(classeApiMethodeMethode)) {
					tIndex = 1;
					wForm.t(tIndex + 3).be("form").da("id", entiteVar, "Form").da("style", "display: inline-block; ").dfl();
				}
				if("LocalDate".equals(entiteNomSimple)) {
					wForm.tl(tIndex + 4, entiteNomSimpleComplet, " val = o.get", entiteVarCapitalise, "();");
					l();
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 4).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsx(entiteNomAffichage).dgl("label");
					}
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "text");
					wForm.t(tIndex + 5).dal("class", "w3-input w3-border datepicker ");
					wForm.t(tIndex + 5).dal("placeholder", "DD-MM-YYYY");
					wForm.t(tIndex + 5).dal("data-timeformat", "DD-MM-YYYY");
					wForm.t(tIndex + 5).dal("onclick", "enleverLueur($(this)); ");
					if(entiteDescription != null)
						wForm.t(tIndex + 5).dal("title", entiteDescription + " (DD-MM-YYYY)");
					wForm.tl(tIndex + 5, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"dd/MM/yyyy\", Locale.forLanguageTag(\"fr-FR\")).format(val))");
					wForm.t(tIndex + 5).s(".a(\"onchange\", \"");
						wForm.s("var t = moment(this.value, 'DD-MM-YYYY'); ");
						wForm.s("if(t) { ");
							wForm.s("var s = t.format('YYYY-MM-DD'); ");
							wForm.s("$(this).next().val(s); ");
							wForm.s("$(this).next().trigger('change'); ");
						wForm.s("} ");
					wForm.l("\")");
					wForm.tl(tIndex + 5, ".fg();");
	
					wForm.t(tIndex + 4).e("input").l();
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("class", "set", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("name", "set", entiteVarCapitalise);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "valeur", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("name", entiteVar);
						wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classeNomSimple, "($('#", classeNomSimple, "Form'), $('#", entiteVar, "Form')); ");
					}
	
					wForm.t(tIndex + 5).dal("type", "hidden");
					wForm.tl(tIndex + 5, ".a(\"value\", o.str", entiteVarCapitalise, "())");
					wForm.t(tIndex + 4).dfgl();
				}
				else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
					wForm.tl(tIndex + 4, entiteNomSimpleComplet, " val = o.get", entiteVarCapitalise, "();");
					wForm.l();
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 4).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsx(entiteNomAffichage).dgl("label");
					}
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "text");
					wForm.t(tIndex + 5).dal("class", "w3-input w3-border datepicker ");
					wForm.t(tIndex + 5).dal("placeholder", "DD-MM-YYYY");
					wForm.t(tIndex + 5).dal("data-timeformat", "DD-MM-YYYY");
					wForm.t(tIndex + 5).dal("onclick", "enleverLueur($(this)); ");
					if(entiteDescription != null)
						wForm.t(tIndex + 5).dal("title", entiteDescription + " (DD-MM-YYYY)");
					wForm.tl(tIndex + 5, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"dd/MM/yyyy\", Locale.forLanguageTag(\"fr-FR\")).format(val))");
					wForm.t(tIndex + 5).s(".a(\"onchange\", \"");
						wForm.s("var t = moment(this.value, 'DD-MM-YYYY'); ");
						wForm.s("if(t) { ");
							wForm.s("var s = t.format('YYYY-MM-DD'); ");
							wForm.s("$(this).next().val(s); ");
							wForm.s("$(this).next().trigger('change'); ");
						wForm.s("} ");
					wForm.l("\")");
					wForm.tl(tIndex + 5, ".fg();");
	
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "hidden");
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("class", "set", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("name", "set", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "valeur", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("name", entiteVar);
						wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classeNomSimple, "($('#", classeNomSimple, "Form'), $('#", entiteVar, "Form')); ");
					}
	
					wForm.tl(tIndex + 5, ".a(\"value\", o.str", entiteVarCapitalise, "())");
					wForm.t(tIndex + 4).dfgl();
				}
				else if("LocalTime".equals(entiteNomSimple)) {
					wForm.tl(tIndex + 4, entiteNomSimpleComplet, " val = o.get", entiteVarCapitalise, "();");
					wForm.l();
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 4).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsx(entiteNomAffichage).dgl("label");
					}
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "text");
					wForm.t(tIndex + 5).dal("class", "w3-input w3-border timepicker ");
					wForm.t(tIndex + 5).dal("placeholder", "HH:MM AM");
					wForm.t(tIndex + 5).dal("onclick", "enleverLueur($(this)); ");
					if(entiteDescription != null)
						wForm.t(tIndex + 5).da("title", entiteDescription + " (h'h'mm)");
					wForm.tl(tIndex + 5, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"dd/MM/yyyy\", Locale.forLanguageTag(\"fr-FR\")).format(val))");
					wForm.t(tIndex + 5).s(".a(\"onchange\", \"");
						wForm.s("var t = parseTime(this.value); ");
						wForm.s("if(t) { ");
							wForm.s("var s = dateFormat(t, \"'h'MM\"); ");
							wForm.s("$(this).next().val(s); ");
							wForm.s("$(this).next().trigger('change'); ");
						wForm.s("} ");
					wForm.l("\")");
					wForm.tl(tIndex + 5, ".fg();");
	
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "hidden");
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("class", "set", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("name", "set", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "valeur", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("name", entiteVar);
						wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classeNomSimple, "($('#", classeNomSimple, "Form'), $('#", entiteVar, "Form')); ");
					}
	
					wForm.tl(tIndex + 5, ".a(\"value\", val == null ? \"\" : o.str", entiteVarCapitalise, "())");
					wForm.t(tIndex + 4).dfgl();
				}
				else if("Boolean".equals(entiteNomSimple)) {
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "hidden");
					wForm.t(tIndex + 5).dal("name", entiteVar);
					wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					wForm.t(tIndex + 5).dal("value", "false");
					wForm.t(tIndex + 4).dfgl();
					wForm.l();
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "checkbox");
					wForm.t(tIndex + 5).dal("value", "true");
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("class", "set", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("name", "set", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "valeur", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("name", entiteVar);
						wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classeNomSimple, "($('#", classeNomSimple, "Form'), $('#", entiteVar, "Form')); ");
					}
					wForm.tl(tIndex + 5, ";");
	
					wForm.tl(tIndex + 5, "if(o.get", entiteVarCapitalise, "() != null && o.get", entiteVarCapitalise, "())");
					wForm.t(tIndex + 6).a("checked", "checked").l(";");
					wForm.t(tIndex + 4).fgl();
					wForm.l();
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 4).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsx(entiteNomAffichage).dgl("label");
					}
				}
				else {
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 4).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsx(entiteNomAffichage).dgl("label");
					}
					wForm.l();
	
					if(entiteMultiligne)
						wForm.t(tIndex + 4).e("textarea").l();
					else
						wForm.t(tIndex + 4).e("input").l().t(tIndex + 5).dal("type", "text");
	
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 5).dal("placeholder", entiteNomAffichage);
					}
					if(entiteDescription != null) {
						wForm.t(tIndex + 5).dal("title", entiteDescription);
					}
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("class", "set", entiteVarCapitalise, " w3-input w3-border ");
						wForm.t(tIndex + 5).dal("name", "set", entiteVarCapitalise);
						wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "valeur", entiteVarCapitalise, " w3-input w3-border ");
						wForm.t(tIndex + 5).dal("name", entiteVar);
						wForm.t(tIndex + 5).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classeNomSimple, "($('#", classeNomSimple, "Form'), $('#", entiteVar, "Form')); ");
					}
	
					if(entiteMultiligne) {
						wForm.t(tIndex + 4).df();
						wForm.s(".sx(o.str", entiteVarCapitalise, "())");
					}
					else {
						wForm.tl(tIndex + 5, ".a(\"value\", o.str", entiteVarCapitalise, "())");
					}
	
					if(entiteMultiligne)
						wForm.dgl("textarea");
					else
						wForm.t(tIndex + 4).dfgl();
	
					wForm.l();
				}
				if("Page".equals(classeApiMethodeMethode)) {
					wForm.t(tIndex + 3).bgl("form");
				}
			}
			else {

				// entiteModifier: false

				wForm.t(tIndex + 4).be("div").da("class", "").dfl();
				wForm.t(tIndex + 5).e("label").da("class", "").df().dsx(entiteNomAffichage).dgl("label");
				wForm.t(tIndex + 4).bgl("div");
				wForm.t(tIndex + 4).be("div").da("class", "").dfl();
				wForm.t(tIndex + 5).e("span").df().s(".sx(o.str", entiteVarCapitalise, "())").dgl("span");
				wForm.t(tIndex + 4).bgl("div");
			}

//			if().da("class", objets).da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
			wForm.t(3).bgl("div");
		}
		return result;
	}

	public void  pageCodeClass(String languageName) throws Exception, Exception {
		for(String classePageMethode : classApiMethods) {

			String classePageCheminGen = (String)doc.get("classePageCheminGen" + classePageMethode  + "_stored_string");
			String classePageChemin = (String)doc.get("classePageChemin" + classePageMethode  + "_stored_string");
			String classePageCheminCss = (String)doc.get("classePageCheminCss" + classePageMethode  + "_stored_string");
			String classePageCheminJs = (String)doc.get("classePageCheminJs" + classePageMethode  + "_stored_string");
			String classePageUriMethode = (String)classDoc.get("classApiUri" + classePageMethode + "_stored_string");
			String classePageLangueNom = (String)classDoc.get("classePageLangueNom" + classePageMethode + "_stored_string");

			classePageNomSimple = (String)doc.get("classePageNomSimple" + classePageMethode  + "_stored_string");
			classePageSuperNomSimple = (String)doc.get("classePageSuperNomSimple" + classePageMethode  + "_stored_string");
			classeGenPageNomSimple = (String)doc.get("classeGenPageNomSimple" + classePageMethode  + "_stored_string");
			String classePageNomCanonique = (String)doc.get("classePageNomCanonique" + classePageMethode  + "_stored_string");
	
			if(classePageCheminGen != null && StringUtils.equals(classePageLangueNom, languageName)) {
			
				File classePageFichierGen = null;
				File classePageFichier = null;
				File classePageFichierCss = null;
				File classePageFichierJs = null;

				if(classePageCheminGen != null)
					classePageFichierGen = new File(classePageCheminGen);
				if(classePageChemin != null)
					classePageFichier = new File(classePageChemin);
				if(classePageCheminCss != null)
					classePageFichierCss = new File(classePageCheminCss);
				if(classePageCheminJs != null)
					classePageFichierJs = new File(classePageCheminJs);
			
				AllWriter auteurPageGenClasse = null;
				AllWriter writerPageClass = null;
				AllWriter writerPageCss = null;
				AllWriter writerPageJs = null;

				if(classePageFichierGen != null)
					auteurPageGenClasse = AllWriter.create(classePageFichierGen);
				if(classePageFichier != null && !classePageFichier.exists())
					writerPageClass = AllWriter.create(classePageFichier);
				if(classePageFichierCss != null)
					writerPageCss = AllWriter.create(classePageFichierCss);
				if(classePageFichierJs != null)
					writerPageJs = AllWriter.create(classePageFichierJs);

				Boolean pageH1 = false;
				Boolean pageH2 = false;
				Boolean pageH3 = false;
				Boolean pageTitle = false;
	
				AllWriter wSearch = AllWriter.create();
				AllWriter wPOST = AllWriter.create();
				AllWriter wPATCH = AllWriter.create();
				AllWriter wTh = AllWriter.create();
				AllWriter wTd = AllWriter.create();
				AllWriter wFormSearch = AllWriter.create();
				AllWriter wFormPOST = AllWriter.create();
				AllWriter wFormPage = AllWriter.create();
				AllWriter wFormPATCH = AllWriter.create();
				AllWriter wEntitys = AllWriter.create();
	
				o = auteurPageGenClasse;
				{
					SolrQuery rechercheSolr = new SolrQuery();   
					rechercheSolr.setQuery("*:*");
					rechercheSolr.setRows(1000000);
					String fqClassesSuperEtMoi = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					rechercheSolr.addFilterQuery("partEstEntity_indexed_boolean:true");
					rechercheSolr.addFilterQuery("classeNomCanonique_" + languageName + "_indexed_string:" + fqClassesSuperEtMoi);
//					rechercheSolr.addFilterQuery("entityHtmlRow_indexed_int:[* TO *]");
					rechercheSolr.addSort("entityHtmlRow_indexed_int", ORDER.asc);
					rechercheSolr.addSort("entityHtmlCellule_indexed_int", ORDER.asc);
					QueryResponse rechercheReponse = solrClientComputate.query(rechercheSolr);
					SolrDocumentList rechercheListe = rechercheReponse.getResults();

					searchLines = rechercheSolr.getRows();

					searchLineSearch = -1;
					searchLinePOST = -1;
					searchLinePATCH = -1;
					searchLinePage = -1;

					List<String> pageVars = Arrays.asList("pageH1", "pageH2", "pageH3", "pageTitle");
	
					if(rechercheListe.size() > 0) {
						Boolean resultFormPOST = false; 
						Boolean resultFormPage = false; 
						Boolean resultFormPATCH = false; 
						Boolean resultFormSearch = false; 

						for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=searchLines) {
							for(Integer j = 0; j < rechercheListe.size(); j++) {
								entiteDocumentSolr = rechercheListe.get(j);
								entityVar = (String)entiteDocumentSolr.get("entityVar_" + languageName + "_stored_string");
								entityVarCapitalized = (String)entiteDocumentSolr.get("entityVarCapitalized_" + languageName + "_stored_string");
								entitySimpleName = (String)entiteDocumentSolr.get("entitySimpleName_" + languageName + "_stored_string");
								entitySimpleNameGenerique = (String)entiteDocumentSolr.get("entitySimpleNameGenerique_" + languageName + "_stored_string");
								entitySimpleNameComplet = (String)entiteDocumentSolr.get("entitySimpleNameComplet_" + languageName + "_stored_string");
								entityDescription = (String)entiteDocumentSolr.get("entityDescription_" + languageName + "_stored_string");
								entityDisplayName = (String)entiteDocumentSolr.get("entityDisplayName_" + languageName + "_stored_string");
								entityHtmlRow = (Integer)entiteDocumentSolr.get("entityHtmlRow_stored_int");
								entityHtml = (Boolean)entiteDocumentSolr.get("entityHtml_stored_boolean");
								entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
								entiteStocke = (Boolean)entiteDocumentSolr.get("entiteStocke_stored_boolean");
								entityMultiline = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityMultiline_stored_boolean"));
								entiteModify = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
	
								if(entityHtmlRow != null && pageVars.contains(entityVar)) {
									if(entiteWrap) {
										if("pageH1".equals(entityVar)) {
											pageH1 = true;
										}
										else if("pageH2".equals(entityVar)) {
											pageH2 = true;
										}
										else if("pageH3".equals(entityVar)) {
											pageH3 = true;
										}
										else if("pageTitle".equals(entityVar)) {
											pageTitle = true;
										}
										else {
											wEntitys.l();
											wEntitys.t(1, "@Override protected void _", entityVar, "(");
											if(entiteWrap)
												wEntitys.s("Wrap<", entitySimpleNameComplet, "> c");
											else
												wEntitys.s(entitySimpleNameComplet, " o");
											wEntitys.l(") {");
											wEntitys.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null)");
											wEntitys.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".get", entityVarCapitalized, "()", ");");
											wEntitys.tl(1, "}");
										}
									}
								}
	
								if(entityHtml) {
									if(entityHtmlRow != null) {
										if(ecrireFormEntity(wFormPOST, "POST"))
											resultFormPOST = true;
										if(ecrireFormEntity(wFormPage, "Page"))
											resultFormPage = true;
									}
									if(entiteStocke) {
										if(ecrireFormEntity(wFormPATCH, "PATCH"))
											resultFormPATCH = true;
									}
									if(entiteIndexe) {
										if(ecrireFormEntity(wFormSearch, "Search"))
											resultFormSearch = true;
									}
								}
							}
							rechercheSolr.setStart(i.intValue() + searchLines);
							rechercheReponse = solrClientComputate.query(rechercheSolr);
							rechercheListe = rechercheReponse.getResults();
						}
						if(resultFormPOST)
							wFormPOST.t(2).bgl("div");
						if(resultFormPage)
							wFormPage.t(2).bgl("div");
						if(resultFormPATCH)
							wFormPATCH.t(2).bgl("div");
						if(resultFormSearch)
							wFormSearch.t(2).bgl("div");
					}
				}
	
				wEntitys.l();
				wEntitys.tl(1, "@Override protected void _pageH1(Wrap<String> c) {");
				if(pageH1) {
					wEntitys.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null && ", StringUtils.uncapitalize(classSimpleName), ".getPageH1() != null)");
					wEntitys.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".getPageH1()", ");");
					wEntitys.tl(2, "else if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				} else {
					wEntitys.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				}
				if(contextH1 != null)
					wEntitys.tl(3, "c.o(", q(contextH1), ");");
				else
					wEntitys.tl(3, "c.o(", q(contextAName), ");");
				wEntitys.tl(2, "else if(list", classSimpleName, ".size() == 0)");
				wEntitys.tl(3, "c.o(", q(contextNoneNameFound), ");");
				if(contextH1 != null) {
					wEntitys.tl(2, "else");
					wEntitys.tl(3, "c.o(", q(contextH1), ");");
				}
				wEntitys.tl(1, "}");
	
				wEntitys.l();
				wEntitys.tl(1, "@Override protected void _pageH2(Wrap<String> c) {");
				if(pageH2) {
					wEntitys.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null && ", StringUtils.uncapitalize(classSimpleName), ".getPageH2() != null)");
					wEntitys.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".getPageH2()", ");");
					if(contextH2 != null) {
						wEntitys.tl(2, "else");
						wEntitys.tl(3, "c.o(", q(contextH2), ");");
					}
				} else {
					wEntitys.tl(2, "c.o(", q(contextH2), ");");
				}
				wEntitys.tl(1, "}");
	
				wEntitys.l();
				wEntitys.tl(1, "@Override protected void _pageH3(Wrap<String> c) {");
				if(pageH3) {
					wEntitys.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null && ", StringUtils.uncapitalize(classSimpleName), ".getPageH3() != null)");
					wEntitys.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".getPageH3()", ");");
					if(contextH3 != null) {
						wEntitys.tl(2, "else");
						wEntitys.tl(3, "c.o(", q(contextH3), ");");
					}
				} else {
					wEntitys.tl(2, "c.o(", q(contextH3), ");");
				}
				wEntitys.tl(1, "}");
	
				wEntitys.l();
				wEntitys.tl(1, "@Override protected void _pageTitle(Wrap<String> c) {");
				if(pageTitle) {
					wEntitys.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null && ", StringUtils.uncapitalize(classSimpleName), ".getPageTitle() != null)");
					wEntitys.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".getPageTitle()", ");");
					wEntitys.tl(2, "else if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				} else {
					wEntitys.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				}
				wEntitys.tl(3, "c.o(", q(contextAName), ");");
				wEntitys.tl(2, "else if(list", classSimpleName, ".size() == 0)");
				wEntitys.tl(3, "c.o(", q(contextNoneNameFound), ");");
				if(contextTitle != null) {
					wEntitys.tl(2, "else");
					wEntitys.tl(3, "c.o(", q(contextTitle), ");");
				}
				wEntitys.tl(1, "}");
		
				if(writerPageClass != null) {
					writerPageClass.l("package ", classPackageName, ";");
					writerPageClass.l();
					writerPageClass.l("/**");
					writerPageClass.l(" * Traduire: false");
					writerPageClass.l(" **/");
					writerPageClass.s("public class ", classePageNomSimple);
					writerPageClass.s(" extends ", classePageNomSimple, "Gen<", classeGenPageNomSimple, ">");
					writerPageClass.l(" {");
					writerPageClass.tl(0, "}");
				}

				l("package ", classPackageName, ";");
				l();
				if(classImportsGenPage.size() > 0) { 
					for(String classImport : classImportsGenPage) {
						l("import ", classImport, ";");
					}
					l();
				}
		
				tl(0, "");
//				writeComment(classComment, 0); 
				l("/**");
				l(" * Traduire: false");
				l(" **/");
				s("public class ", classeGenPageNomSimple);
				s(" extends ", classeGenPageNomSimple, "Gen");
				s("<", classePageSuperNomSimple, ">");
				l(" {");
				l();
				tl(1, "/**");
				tl(1, " * {@inheritDoc}");
				tl(1, " * ");
				tl(1, " **/");
				tl(1, "protected void _list", classSimpleName, "(Wrap<SearchList<", classSimpleName, ">> c) {");
				tl(1, "}");
				l();
				tl(1, "protected void _", StringUtils.uncapitalize(classSimpleName), "(", "Wrap<", classSimpleName, "> c", ") {");
				tl(2, "if(list", classSimpleName, ".size() == 1)");
				tl(3, "c.o(list", classSimpleName, ".get(0)", ");");
				tl(1, "}");
				s(wEntitys);
	
				if(StringUtils.isNotBlank(contextIconGroup)) {
					l();
					tl(1, "@Override protected void _contextIconGroup(Wrap<String> c) {");
					tl(3, "c.o(", q(contextIconGroup), ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contextIconName)) {
					l();
					tl(1, "@Override protected void _contextIconName(Wrap<String> c) {");
					tl(3, "c.o(", q(contextIconName), ");");
					tl(1, "}");
				}
	
				l();
				tl(1, "@Override public void initDeep", classeGenPageNomSimple, "() {");
				tl(2, "init", classeGenPageNomSimple, "();");
				tl(2, "super.initDeepPageLayout();");
				tl(1, "}");
				l();
				tl(1, "@Override public void htmlScripts", classeGenPageNomSimple, "() {");
				t(2).e("script").da("src", "/static/js/", classeGenPageNomSimple, ".js").df().dgl("script");
				tl(1, "}");
	
				if(StringUtils.isNotBlank(classApiUri)) {
					l();
					tl(1, "protected void _pageUri", classSimpleName, "(Wrap<String> c) {");
					tl(3, "c.o(", q(classePageUriMethode), ");");
					tl(1, "}");
				}
	
				{
					SolrQuery rechercheSolr = new SolrQuery();   
					rechercheSolr.setQuery("*:*");
					rechercheSolr.setRows(1000000);
					String fqClassesSuperEtMoi = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					rechercheSolr.addFilterQuery("partEstEntity_indexed_boolean:true");
					rechercheSolr.addFilterQuery("classeNomCanonique_" + languageName + "_indexed_string:" + fqClassesSuperEtMoi);
					rechercheSolr.addFilterQuery("entityHtmlColumn_indexed_double:[* TO *]");
					rechercheSolr.addSort("entityHtmlColumn_indexed_double", ORDER.asc);
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
								String entitySimpleNameGenerique = (String)entiteDocumentSolr.get("entitySimpleNameGenerique_" + languageName + "_stored_string");
								String entitySimpleNameComplet = (String)entiteDocumentSolr.get("entitySimpleNameComplet_" + languageName + "_stored_string");
								String entityDescription = (String)entiteDocumentSolr.get("entityDescription_" + languageName + "_stored_string");
								String entityDisplayName = (String)entiteDocumentSolr.get("entityDisplayName_" + languageName + "_stored_string");
								Boolean entityHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityHtml_stored_boolean"));
								Boolean entityMultiline = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityMultiline_stored_boolean"));
								if(entityHtml) {
									String jsVal = ".val()";
									if("Boolean".equals(entitySimpleName)) {
										jsVal = ".prop('checked')";
									}
		
									wTh.tl(6, "e(\"th\").f().sx(", q(entityDisplayName), ").g(\"th\");");
		
									wTd.tl(7, "{ e(\"td\").f();");
									wTd.tl(8, "{ e(\"a\").a(\"href\", uri).f();");
									wTd.tl(9, "sx(o.get", entityVarCapitalized, "());");
									wTd.tl(8, "} g(\"a\");");
									wTd.tl(7, "} g(\"td\");");
								}
							}
							rechercheSolr.setStart(i.intValue() + searchLines);
							rechercheReponse = solrClientComputate.query(rechercheSolr);
							rechercheListe = rechercheReponse.getResults();
						}
					}
				}
	
				{
					SolrQuery rechercheSolr = new SolrQuery();   
					rechercheSolr.setQuery("*:*");
					rechercheSolr.setRows(1000000);
					String fqClassesSuperEtMoi = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					rechercheSolr.addFilterQuery("partEstEntity_indexed_boolean:true");
					rechercheSolr.addFilterQuery("classeNomCanonique_" + languageName + "_indexed_string:" + fqClassesSuperEtMoi);
					rechercheSolr.addSort("entityHtmlRow_indexed_int", ORDER.asc);
					rechercheSolr.addSort("entityHtmlCellule_indexed_int", ORDER.asc);
					QueryResponse rechercheReponse = solrClientComputate.query(rechercheSolr);
					SolrDocumentList rechercheListe = rechercheReponse.getResults();
					Integer searchLines = rechercheSolr.getRows();
					Integer searchLine = -1;
					Integer searchLineActual;
		
					if(rechercheListe.size() > 0) {
						for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=searchLines) {
							for(Integer j = 0; j < rechercheListe.size(); j++) {
								entiteDocumentSolr = rechercheListe.get(j);
								entityVar = (String)entiteDocumentSolr.get("entityVar_" + languageName + "_stored_string");
								entityVarCapitalized = (String)entiteDocumentSolr.get("entityVarCapitalized_" + languageName + "_stored_string");
								entitySimpleName = (String)entiteDocumentSolr.get("entitySimpleName_" + languageName + "_stored_string");
								entitySimpleNameGenerique = (String)entiteDocumentSolr.get("entitySimpleNameGenerique_" + languageName + "_stored_string");
								entitySimpleNameComplet = (String)entiteDocumentSolr.get("entitySimpleNameComplet_" + languageName + "_stored_string");
								entityDescription = (String)entiteDocumentSolr.get("entityDescription_" + languageName + "_stored_string");
								entityDisplayName = (String)entiteDocumentSolr.get("entityDisplayName_" + languageName + "_stored_string");
								entityHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityHtml_stored_boolean"));
								entityMultiline = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entityMultiline_stored_boolean"));
								entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
								String jsVal = ".val()";
								if("Boolean".equals(entitySimpleName)) {
									jsVal = ".prop('checked')";
								}

								if(entiteIndexe) {
		
									wSearch.l();
									wSearch.tl(1, "var filter", entityVarCapitalized, " = $formFilters.find('.value", entityVarCapitalized, "')", jsVal, ";");

									if("Boolean".equals(entitySimpleName))
										wSearch.tl(1, "if(filter", entityVarCapitalized, " != null && filter", entityVarCapitalized, " === true)");
									else
										wSearch.tl(1, "if(filter", entityVarCapitalized, " != null && filter", entityVarCapitalized, " !== '')");

									wSearch.tl(2, "filters.push({ name: 'fq', value: '", entityVar, ":' + filter", entityVarCapitalized, " });");
								}

								if(entityHtml) {
		
									wPOST.l();
									wPOST.tl(1, "var value", entityVarCapitalized, " = $formValues.find('.value", entityVarCapitalized, "')", jsVal, ";");
									wPOST.tl(1, "if(value", entityVarCapitalized, " != null && value", entityVarCapitalized, " !== '')");
									wPOST.tl(2, "values['", entityVar, "'] = value", entityVarCapitalized, ";");
		
									wPATCH.l();
									wPATCH.tl(1, "var set", entityVarCapitalized, " = $formValues.find('.set", entityVarCapitalized, "')", jsVal, ";");
									wPATCH.tl(1, "if(set", entityVarCapitalized, " != null && set", entityVarCapitalized, " !== '')");
									wPATCH.tl(2, "values['set", entityVarCapitalized, "'] = set", entityVarCapitalized, ";");
									wPATCH.tl(1, "var add", entityVarCapitalized, " = $formValues.find('.add", entityVarCapitalized, "')", jsVal, ";");
									wPATCH.tl(1, "if(add", entityVarCapitalized, " != null && add", entityVarCapitalized, " !== '')");
									wPATCH.tl(2, "values['add", entityVarCapitalized, "'] = add", entityVarCapitalized, ";");
									wPATCH.tl(1, "var remove", entityVarCapitalized, " = $formValues.find('.remove", entityVarCapitalized, "')", jsVal, ";");
									wPATCH.tl(1, "if(remove", entityVarCapitalized, " != null && remove", entityVarCapitalized, " !== '')");
									wPATCH.tl(2, "values['remove", entityVarCapitalized, "'] = remove", entityVarCapitalized, ";");
								}
							}
							rechercheSolr.setStart(i.intValue() + searchLines);
							rechercheReponse = solrClientComputate.query(rechercheSolr);
							rechercheListe = rechercheReponse.getResults();
						}
					}
				}
	
				l();
				tl(1, "@Override public void htmlScript", classeGenPageNomSimple, "() {");
				for(String classeApiMethode : classApiMethods) {
					String classeApiOperationIdMethode = (String)classDoc.get("classeApiOperationId" + classeApiMethode + "_frFR_stored_string");
					String classApiUriMethode = (String)classDoc.get("classApiUri" + classeApiMethode + "_frFR_stored_string");
					String classeApiTypeMediaMethode = (String)classDoc.get("classeApiTypeMedia200" + classeApiMethode + "_stored_string");
					String classeApiMethodeMethode = (String)classDoc.get("classeApiMethode" + classeApiMethode + "_stored_string");
	
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
							writerPageJs.s("$formValues");
						else if(methodePUT)
							writerPageJs.s("pk, $formValues");
						else if(methodePATCH)
							writerPageJs.s("$formFilters, $formValues");
						else if(methodeSearch)
							writerPageJs.s("$formFilters");
						else if(methodeGET || methodeDELETE)
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
						else if(methodePATCH) {
							writerPageJs.tl(1, "var filters = [];");
							writerPageJs.s(wSearch);
							writerPageJs.l();
							writerPageJs.tl(1, "var values = {};");
							writerPageJs.s(wPATCH);
							writerPageJs.l();
						}
						else if(methodeSearch) {
							writerPageJs.tl(1, "var filters = [];");
							writerPageJs.s(wSearch);
						}
	
						writerPageJs.tl(1, "$.ajax({");
	
						if(methodeGET || methodeDELETE || methodePUT)
							writerPageJs.tl(2, "url: '", StringUtils.replace(classApiUriMethode, "{id}", "' + id"));
						else if(methodePATCH || methodeSearch)
							writerPageJs.tl(2, "url: '", classApiUriMethode, "?' + $.param(filters)");
						else
							writerPageJs.tl(2, "url: '", classApiUriMethode, "'");
	
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
				tl(1, "public void htmlFormPage", classSimpleName, "(", classSimpleName, " o) {");
				s(wFormPage);
				tl(1, "}");
				l();
				tl(1, "public void htmlFormPOST", classSimpleName, "(", classSimpleName, " o) {");
				s(wFormPOST);
				tl(1, "}");
				l();
				tl(1, "public void htmlFormPATCH", classSimpleName, "(", classSimpleName, " o) {");
				s(wFormPATCH);
				tl(1, "}");
				l();
				tl(1, "public void htmlFormSearch", classSimpleName, "(", classSimpleName, " o) {");
				s(wFormSearch);
				tl(1, "}");
				l();
				tl(1, "@Override public void htmlBody", classeGenPageNomSimple, "() {");
				l();
				tl(2, "if(list", classSimpleName, ".size() == 0) {");
				t(3).l("//", contextNoneNameFound);
				l();
				t(3).be("h1").dfl();
				tl(4, "if(contextIconCssClasses != null)");
				tl(5, "e(\"i\").a(\"class\", contextIconCssClasses + \" w3-margin-right-4 \").f().g(\"i\");");
				t(4).e("span").da("class", " ").df().dsx(contextNoneNameFound).dgl("span");
				t(3).bgl("h1");
				tl(2, "} else if(list", classSimpleName, ".size() == 1) {");
				t(3).l("// ", contextAName);
				t(3).l(classSimpleName, " o = list", classSimpleName, ".first();");
				l();
				tl(3, "if(pageH1 != null) {");
				t(4).be("h1").dfl();
				tl(5, "if(contextIconCssClasses != null)");
				tl(6, "e(\"i\").a(\"class\", contextIconCssClasses + \" w3-margin-right-4 \").f().g(\"i\");");
	
				if(classEntityVars != null && classEntityVars.contains("pageH1"))
					t(5).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
				else
					t(5).e("span").da("class", " ").df().dsx(contextAName).dgl("span");
	
				t(4).bgl("h1");
				tl(3, "}");
	
				if(classEntityVars != null && classEntityVars.contains("pageH2")) {
					tl(3, "if(pageH2 != null) {");
					t(4).be("h2").dfl();
					t(5).e("span").da("class", " ").df().s(".sx(pageH2)").dgl("span");
					t(4).bgl("h2");
					tl(3, "}");
				}
	
				if(classEntityVars != null && classEntityVars.contains("pageH3")) {
					tl(3, "if(pageH3 != null) {");
					t(4).be("h3").dfl();
					t(5).e("span").da("class", " ").df().s(".sx(pageH3)").dgl("span");
					t(4).bgl("h3");
					tl(3, "}");
				}
	
				t(3).be("div").da("class", "w3-card w3-margin w3-padding w3-margin-top w3-show ").dfl();
				if(classVarPrimaryKey != null) {
					l();
					tl(4, "if(o.get", StringUtils.capitalize(classVarPrimaryKey), "() != null) {");
					t(5).be("form").da("id", classSimpleName, "Form").da("style", "display: inline-block; ").dfl();
					t(6).e("input").l();
					t(6).dal("name", classVarPrimaryKey);
					t(6).dal("class", "value", StringUtils.capitalize(classVarPrimaryKey));
					t(6).dal("type", "hidden");
					tl(6, ".a(\"value\", o.get", StringUtils.capitalize(classVarPrimaryKey), "())");
					t(6).dfgl();
					t(5).bgl("form");
					tl(5, "htmlFormPage", classSimpleName, "(o);");
					tl(4, "}");
				}
				if(classeMethodeVars.contains("htmlBody")) {
					l();
					tl(4, "o.htmlBody();");
				}
				l();
				t(3).bgl("div");
				tl(2, "} else {");
				t(3).l("// multiple ", contextNamePlural);
				l();
				t(3).be("h1").dfl();
				tl(4, "if(contextIconCssClasses != null)");
				tl(5, "e(\"i\").a(\"class\", contextIconCssClasses + \" w3-margin-right-4 \").f().g(\"i\");");
				t(4).e("span").da("class", " ").df().dsx(contextNamePlural).dgl("i");
				t(3).bgl("h1");
				t(3).be("table").da("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").dfl();
				t(4).be("thead").dfl();
				t(5).be("tr").dfl();
				s(wTh);
				t(5).bgl("tr");
				t(4).bgl("thead");
				t(4).be("tbody").dfl();
				tl(5, "for(int i = 0; i < list", classSimpleName, ".size(); i++) {");
				tl(6, classSimpleName, " o = list", classSimpleName, ".getList().get(i);");
				tl(6, "String uri = ", classEntityVars.contains("pageUri") ? "o.getPageUri()" : q(classePageUriMethode, "/") + " + o.getPk()", ";");
				tl(6, "{ e(\"tr\").f();");
				s(wTd);
				tl(6, "} g(\"tr\");");
				tl(5, "}");
				t(4).bgl("tbody");
				t(3).bgl("table");
				tl(2, "}");
	
				t(2).e("div").dfl();
				l();
				for(String classeApiMethode : classApiMethods) {
					String classeApiOperationIdMethode = (String)classDoc.get("classeApiOperationId" + classeApiMethode + "_frFR_stored_string");
					String classApiUriMethode = (String)classDoc.get("classApiUri" + classeApiMethode + "_frFR_stored_string");
					String classeApiTypeMediaMethode = (String)classDoc.get("classeApiTypeMedia200" + classeApiMethode + "_stored_string");
					String classeApiMethodeMethode = (String)classDoc.get("classeApiMethode" + classeApiMethode + "_stored_string");
	
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
						t(3 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-", contextColor, " ");
						t(3 + tab).dal("onclick", "$('#", classeApiOperationIdMethode, "Modale').show(); ");
						t(3 + tab).df().dsx(methodTitle).l();
						t(2 + tab).dgl("button");
						{ t(2 + tab).be("div").da("id", classeApiOperationIdMethode, "Modale").da("class", "w3-modal ").dfl();
							{ t(3 + tab).be("div").da("class", "w3-modal-content w3-card-4 ").dfl();
								{ t(4 + tab).be("header").da("class", "w3-container w3-", contextColor, " ").dfl();
									t(5 + tab).e("span").da("class", "w3-button w3-display-topright ").da("onclick", "$('#", classeApiOperationIdMethode, "Modale').hide(); ").df().dsx("×").dgl("span");
									t(5 + tab).e("h2").da("class", "").df().dsx(methodTitle).dgl("h2");
								} t(4 + tab).bgl("header");
	
								{ t(4 + tab).be("div").da("class", "w3-container ").dfl();
									tl(5+ tab, classSimpleName, " o = new ", classSimpleName, "();");
									if("PATCH".equals(classeApiMethodeMethode)) {

										l();
										{ t(5 + tab).be("form").da("id", classeApiOperationIdMethode, "FormFilters").dfl();
										tl(6 + tab, "htmlFormSearch", classSimpleName, "(o);");
										} t(5 + tab).bgl("form");
										t(5 + tab).e("button").l();
										t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-", contextColor, " ");
		
										tl(6 + tab, ".a(\"onclick\", \"recherche", classSimpleName, "($('#", classeApiOperationIdMethode, "FormFilters')); \")");
		
										t(6 + tab).df().dsx(methodTitle).l();
										t(5 + tab).dgl("button");
										l();
										
										l();
										{ t(5 + tab).be("form").da("id", classeApiOperationIdMethode, "FormValues").dfl();
	
										if("DELETE".equals(classeApiMethodeMethode))
											tl(6 + tab, "htmlFormPATCH", classSimpleName, "(o);");
										else if("PUT".equals(classeApiMethodeMethode))
											tl(6 + tab, "htmlFormPOST", classSimpleName, "(o);");
										else
											tl(6 + tab, "htmlForm", classeApiMethodeMethode, classSimpleName, "(o);");
	
										} t(5 + tab).bgl("form");
										t(5 + tab).e("button").l();
										t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-", contextColor, " ");
		
										if("POST".equals(classeApiMethodeMethode))
											tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \")");
										else if("PATCH".equals(classeApiMethodeMethode))
											tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "FormFilters'), $('#", classeApiOperationIdMethode, "FormValues')); \")");
										else if("PUT".equals(classeApiMethodeMethode))
											tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \", $('#", classeApiOperationIdMethode, "Form')); \")");
										else if(tab > 0)
											tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \"); \")");
										else
											t(6 + tab).dal("onclick", classeApiOperationIdMethode, "(); ");
		
										t(6 + tab).df().dsx(methodTitle).l();
										t(5 + tab).dgl("button");
										l();
									}
									else {
										l();
										{ t(5 + tab).be("form").da("id", classeApiOperationIdMethode, "Form").dfl();
	
										if("DELETE".equals(classeApiMethodeMethode))
											tl(6 + tab, "htmlFormPATCH", classSimpleName, "(o);");
										else if("PUT".equals(classeApiMethodeMethode))
											tl(6 + tab, "htmlFormPOST", classSimpleName, "(o);");
										else
											tl(6 + tab, "htmlForm", classeApiMethodeMethode, classSimpleName, "(o);");
	
										} t(5 + tab).bgl("form");
										t(5 + tab).e("button").l();
										t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-", contextColor, " ");
		
		//								tl(6 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classeApiOperationIdMethode, "Form').serializeArray().reduce(function(a, x) { a[x.name] = x.value; return a; }, {}))); \")");
		//								tl(6 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classeApiOperationIdMethode, "Form').serializeObject())); \")");
		
										if("POST".equals(classeApiMethodeMethode))
											tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \")");
										else if("PATCH".equals(classeApiMethodeMethode))
											tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "FormFilters'), $('#", classeApiOperationIdMethode, "FormValues')); \")");
										else if("PUT".equals(classeApiMethodeMethode))
											tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \", $('#", classeApiOperationIdMethode, "Form')); \")");
										else if(tab > 0)
											tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \"); \")");
										else
											t(6 + tab).dal("onclick", classeApiOperationIdMethode, "(); ");
		
										t(6 + tab).df().dsx(methodTitle).l();
										t(5 + tab).dgl("button");
										l();
									}
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
	
				wTh.flushClose();

				auteurPageGenClasse.flushClose();
				System.out.println("Write: " + classePageCheminGen); 
				if(writerPageClass != null) {
					writerPageClass.flushClose();
					System.out.println("Write: " + classePageChemin); 
				}
				writerPageCss.flushClose();
				System.out.println("Write: " + classePageCheminCss); 
				writerPageJs.flushClose();
				System.out.println("Write: " + classePageCheminJs); 

				{
					RegarderClasse regarderClasse = new RegarderClasse();
					regarderClasse.appliChemin = appliChemin;
					regarderClasse.classAbsolutePath = classePageChemin;
					regarderClasse.appliNom = appliNom;
					regarderClasse.initRegarderClasseBase(); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classAbsolutePath, languageName, languageName);
					RegarderClasse.regarderClasse(regarderClasse, languageName);
				}

				{
					RegarderClasse regarderClasse = new RegarderClasse();
					regarderClasse.appliChemin = appliChemin;
					regarderClasse.classAbsolutePath = classePageCheminGen;
					regarderClasse.appliNom = appliNom;
					regarderClasse.initRegarderClasseBase(); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classAbsolutePath, languageName, languageName);
					RegarderClasse.regarderClasse(regarderClasse, languageName);
				}
	
	//		writerGenPageClass.close();
			}
		}
	}
}
