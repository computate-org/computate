package org.computate.enUS.java;


/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public abstract class WriteAllClassesApiGen implements Handler<RoutingContext> {

	public static final String VAL_virguleEspace = ", ";
	public static final String VAL_citation = "\"";
	public static final String VAL_citationDeuxPointsEspaceCitation = "\": \"";
	public static final String VAL_citationDeuxPointsEspace = "\": ";
	public static final String VAL_citationLigne = "\"\n";
	public static final String VAL_ligne = "\n";
	public static final String VAL_citationVirguleEspaceCitation = "\", \"";
	public static final String VAL_citationDeuxPointsEspaceGuillmets = "\": [";
	public static final String VAL_guillmetsFin = "]";

	public void handleGetWriteAllClassesApi(Vertx vertx, Router router, OAuth2Auth oauth2) {
		router.get("").handler(rc -> {
			rc.user().isAuthorized("realm:view-account", authRes -> {
				try {
					if (authRes.result() == Boolean.TRUE) {
						ReactiveWriteStream<ResultatRecherche> rws = ReactiveWriteStream.writeStream(vertx);
						ReactiveReadStream<ResultatRecherche> rrs = ReactiveReadStream.readStream();
						HttpServerResponse outStream = rc.response();
						outStream.putHeader("content-type", "application/json");
						rws.subscribe(rrs);
						SolrQuery rechercheSolr = genererRecherche(rc.request());
						RequeteSite requeteSite = genererRequeteSite(rc.request(), rc.response());
						requeteSite.setPrincipalUtilisateur(rc.user().principal());
						QueryResponse reponseRecherche = requeteSite.SiteContexte_.clientSolr.query(rechercheSolr);
						requeteSite.setReponseRecherche(reponseRecherche);
						SolrDocumentList resultatsRecherche = reponseRecherche.getResults();
						genererGetDebut(requeteSite);
						for(int i = 0; i < resultatsRecherche.size(); i++) {
							SolrDocument documentSolr = resultatsRecherche.get(i);
							ResultatRecherche resultatRecherche = new ResultatRecherche();
							resultatRecherche.setRequeteSite_(requeteSite);
							resultatRecherche.setDocumentSolr(documentSolr);
							resultatRecherche.setResultatIndice(i);
							if(i == 0)
								genererGetIndividuel(resultatRecherche);
							else
								rws.write(resultatRecherche);
						}
						rrs.handler(resultatRecherche -> {
							if (outStream.writeQueueFull()) {
								outStream.drainHandler((s) -> {
									rrs.resume();
								});
								rrs.pause();
							} else {
								try {
									genererGetIndividuel(resultatRecherche);
								} catch(Exception e) {
									// TODO
								}
							}
						}).endHandler(h -> {
							genererGetFin(requeteSite);
							outStream.end();
						});
					}
					else {
						rc.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();
					}
				} catch(Exception e) {
					// TODO
				}
				});
		});
	}

	public void genererGetDebut(RequeteSite requeteSite) {
		HttpServerResponse reponseServeur = requeteSite.reponseServeur;
		QueryResponse reponseRecherche = requeteSite.reponseRecherche;
		reponseServeur.write("{\n");
		Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());
		Long millisTransmission = reponseRecherche.getElapsedTime();
		Long numCommence = reponseRecherche.getResults().getStart();
		Long numTrouve = reponseRecherche.getResults().getNumFound();
		Integer numRetourne = reponseRecherche.getResponse().size();
		String tempsRecherche = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));
		String tempsTransmission = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));
		Exception exceptionRecherche = reponseRecherche.getException();

		reponseServeur.write("\t\"numCommence\": ");
		reponseServeur.write(numCommence.toString());

		reponseServeur.write("\t, \"numTrouve\": ");
		reponseServeur.write(numTrouve.toString());

		reponseServeur.write("\t, \"numRetourne\": ");
		reponseServeur.write(numRetourne.toString());

		reponseServeur.write("\t, \"tempsRecherche\": \"");
		reponseServeur.write(tempsRecherche);
		reponseServeur.write("\"");

		reponseServeur.write("\t, \"tempsTransmission\": \"");
		reponseServeur.write(tempsTransmission);
		reponseServeur.write("\"");

		if(exceptionRecherche != null) {
			reponseServeur.write("\t, \"exceptionRecherche\": \"");
			reponseServeur.write(exceptionRecherche.getMessage());
			reponseServeur.write("\"");
		}

		reponseServeur.write("\t, \"resultats\": [\n");
	}

	public void genererGetIndividuel(ResultatRecherche resultatRecherche) throws Exception {
		RequeteSite requeteSite = resultatRecherche.requeteSite_;
		SolrDocument documentSolr = resultatRecherche.documentSolr;
		Integer resultatIndice = resultatRecherche.resultatIndice;
		HttpServerResponse reponseServeur = requeteSite.reponseServeur;
		reponseServeur.write("\t\t");
		if(resultatIndice > 0)
			reponseServeur.write(", ");
		reponseServeur.write("{\n");
		Collection<String> champNoms = documentSolr.getFieldNames();
		Integer j = 0;
		for(String champNomStocke : champNoms) {
			Collection<Object> champValeurs = documentSolr.getFieldValues(champNomStocke);
			j = genererGetWriteAllClasses(j, reponseServeur, champNomStocke, champValeurs);
		}
		reponseServeur.write("\t\t}\n");
	}

	public void genererGetFin(RequeteSite requeteSite) {
		HttpServerResponse reponseServeur = requeteSite.reponseServeur;
		QueryResponse reponseRecherche = requeteSite.reponseRecherche;
		reponseServeur.write("\t]\n");
		reponseServeur.write("}\n");
	}

	@Override protected void doPost(HttpServerRequest requeteServeur, HttpServerResponse reponseServeur) throws ServletException, IOException {
		RequeteSite requeteSite = null;
		try {
			SolrQuery rechercheSolr = genererRecherche(requeteServeur);
			requeteSite = genererRequeteSite(requeteServeur, reponseServeur);
			QueryResponse reponseRecherche = requeteSite.SiteContexte_.clientSolr.query(rechercheSolr);
			genererPost(requeteSite, reponseRecherche);
		} catch(Exception e) {
			genererErreur(requeteSite, e);
		}
	}

	public String varIndexeWriteAllClasses(String entiteVar) throws Exception {
		switch(entiteVar) {
			default:
				throw new Exception(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public SolrQuery genererRecherche(HttpServerRequest requeteServeur) throws Exception {
		String entiteVar = null;
		String valeurIndexe = null;
		String varIndexe = null;
		String valeurTri = null;
		Integer rechercheDebut = null;
		Integer rechercheNum = null;
		SolrQuery rechercheSolr = new SolrQuery();
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);
		MultiMap paramMap = requeteServeur.params();
		for(String paramCle : paramMap.names()) {
			List<String> paramValeurs = paramMap.getAll(paramCle);
			for(String paramValeur : paramValeurs) {
				switch(paramCle) {
					case "q":
						entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, ":"));
						valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, ":"));
						varIndexe = varIndexeWriteAllClasses(paramCle);
						rechercheSolr.setQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
						break;
					case "fq":
						entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, ":"));
						valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, ":"));
						varIndexe = varIndexeWriteAllClasses(paramCle);
						rechercheSolr.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
						break;
					case "sort":
						entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, " "));
						valeurTri = StringUtils.trim(StringUtils.substringAfter(paramValeur, " "));
						varIndexe = varIndexeWriteAllClasses(paramCle);
						rechercheSolr.addSort(varIndexe, ORDER.valueOf(valeurTri));
						break;
					case "fl":
						entiteVar = StringUtils.trim(paramValeur);
						varIndexe = varIndexeWriteAllClasses(paramCle);
						rechercheSolr.addField(varIndexe);
						break;
					case "start":
						rechercheDebut = Integer.parseInt(paramValeur);
						rechercheSolr.setStart(rechercheDebut);
						break;
					case "rows":
						rechercheNum = Integer.parseInt(paramValeur);
						rechercheSolr.setRows(rechercheNum);
						break;
				}
			}
		}
		return rechercheSolr;
	}

	public RequeteSite genererRequeteSite(HttpServerRequest requeteServeur, HttpServerResponse reponseServeur) throws Exception {
		SiteContexte SiteContexte = (SiteContexte)requeteServeur.getServletContext().getAttribute("SiteContexte");

		RequeteSite requeteSite = new RequeteSite();
		requeteSite.setSiteContexte_(SiteContexte);
		requeteSite.setRequeteServeur(requeteServeur);
		requeteSite.setReponseServeur(reponseServeur);
		requeteSite.initLoinRequeteSite(requeteSite);

		UtilisateurSite utilisateurSite = new UtilisateurSite();
		utilisateurSite.initLoinUtilisateurSite(requeteSite);
		requeteSite.setUtilisateurSite(utilisateurSite);
		utilisateurSite.setRequeteSite_(requeteSite);
		return requeteSite;
	}

	public Integer genererGetWriteAllClasses(Integer j, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {
		if(!champValeurs.isEmpty()) {
			Object champValeur = champValeurs.iterator().next();
			if(champValeur != null) {
			}
		}
		return j;
	}

	public Integer genererPostWriteAllClasses(Integer j, String entiteVarStocke, Collection<Object> champValeurs) throws Exception {
		if(!champValeurs.isEmpty()) {
			Object champValeur = champValeurs.iterator().next();
			if(champValeur != null) {
			}
		}
		return j;
	}
}
