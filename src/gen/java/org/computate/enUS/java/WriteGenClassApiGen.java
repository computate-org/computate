package org.computate.enUS.java;


/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public abstract class WriteGenClassApiGen extends HttpServlet {

	public String varIndexeWriteGenClass(String entiteVar) throws Exception {
		switch(entiteVar) {
			default:
				throw new Exception(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	@Override protected void doGet(HttpServletRequest requeteServlet, HttpServletResponse reponseServlet) throws ServletException, IOException {
		RequeteSite requeteSite = null;
		try {
			SolrQuery rechercheSolr = genererRecherche(requeteServlet);
			requeteSite = genererRequeteSite(requeteServlet, reponseServlet);
			QueryResponse reponseRecherche = requeteSite.ecouteurContexte_.clientSolr.query(rechercheSolr);
			genererJson(requeteSite, reponseRecherche);
		} catch(Exception e) {
			genererErreur(requeteSite, e);
		}
	}

	public SolrQuery genererRecherche(HttpServletRequest requeteServlet) throws Exception {
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
		Map<String, String[]> paramMap = (Map<String, String[]>)Collections.list(requeteServlet.getParameterNames()).stream()
				.collect(Collectors.toMap(parameterName -> parameterName, requeteServlet::getParameterValues));
		for(String paramCle : paramMap.keySet()) {
			String[] paramValeurs = paramMap.get(paramCle);
			for(String paramValeur : paramValeurs) {
				switch(paramCle) {
					case "q":
						entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, ":"));
						valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, ":"));
						varIndexe = varIndexeWriteGenClass(paramCle);
						rechercheSolr.setQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
						break;
					case "fq":
						entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, ":"));
						valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, ":"));
						varIndexe = varIndexeWriteGenClass(paramCle);
						rechercheSolr.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
						break;
					case "sort":
						entiteVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, " "));
						valeurTri = StringUtils.trim(StringUtils.substringAfter(paramValeur, " "));
						varIndexe = varIndexeWriteGenClass(paramCle);
						rechercheSolr.addSort(varIndexe, ORDER.valueOf(valeurTri));
						break;
					case "fl":
						entiteVar = StringUtils.trim(paramValeur);
						varIndexe = varIndexeWriteGenClass(paramCle);
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

	public RequeteSite genererRequeteSite(HttpServletRequest requeteServlet, HttpServletResponse reponseServlet) throws Exception {
		EcouteurContexte ecouteurContexte = (EcouteurContexte)requeteServlet.getServletContext().getAttribute("ecouteurContexte");

		RequeteSite requeteSite = new RequeteSite();
		requeteSite.setEcouteurContexte_(ecouteurContexte);
		requeteSite.setRequeteServlet(requeteServlet);
		requeteSite.setReponseServlet(reponseServlet);
		requeteSite.initLoinRequeteSite(requeteSite);

		UtilisateurSite utilisateurSite = new UtilisateurSite();
		utilisateurSite.initLoinUtilisateurSite(requeteSite);
		requeteSite.setUtilisateurSite(utilisateurSite);
		utilisateurSite.setRequeteSite_(requeteSite);
		return requeteSite;
	}

	public void ecrireJsonWriteGenClass(String entiteVarStocke, Collection<Object> champValeurs) throws Exception {
		switch(entiteVarStocke) {
		}
	}

	public void genererJson(RequeteSite requeteSite, QueryResponse reponseRecherche) throws Exception {
		PrintWriter ecrivain = requeteSite.ecrivain;
		ecrivain.write("{\n");
		Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());
		Long millisTransmission = reponseRecherche.getElapsedTime();
		Long numCommence = reponseRecherche.getResults().getStart();
		Long numTrouve = reponseRecherche.getResults().getNumFound();
		Integer numRetourne = reponseRecherche.getResponse().size();
		String tempsRecherche = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));
		String tempsTransmission = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));
		Exception exceptionRecherche = reponseRecherche.getException();
		SolrDocumentList resultatsRecherche = reponseRecherche.getResults();

		ecrivain.write("\t\"numCommence\": ");
		ecrivain.write(numCommence.toString());

		ecrivain.write("\t, \"numTrouve\": ");
		ecrivain.write(numTrouve.toString());

		ecrivain.write("\t, \"numRetourne\": ");
		ecrivain.write(numRetourne);

		ecrivain.write("\t, \"tempsRecherche\": \"");
		ecrivain.write(tempsRecherche);
		ecrivain.write("\"");

		ecrivain.write("\t, \"tempsTransmission\": \"");
		ecrivain.write(tempsTransmission);
		ecrivain.write("\"");

		if(exceptionRecherche != null) {
			ecrivain.write("\t, \"exceptionRecherche\": \"");
			ecrivain.write(exceptionRecherche.getMessage());
			ecrivain.write("\"");
		}

		ecrivain.write("\t, \"resultats\": {\n");
		if(resultatsRecherche != null) {
			for(Integer i = 0; i < resultatsRecherche.size(); i++) {
				ecrivain.write("\t\t");
				if(resultatsRecherche != null && resultatsRecherche.size() > 0)
					ecrivain.write(", ");
				ecrivain.write("{\n");
				SolrDocument resultatRecherche = resultatsRecherche.get(i);
				Collection<String> champNoms = resultatRecherche.getFieldNames();
				Integer j = 0;
				for(String champNomStocke : champNoms) {
					Collection<Object> champValeurs = resultatRecherche.getFieldValues(champNomStocke);
					ecrireJsonWriteGenClass(champNomStocke, champValeurs);
					j++;
				}
				ecrivain.write("\t\t}\n");
			}
		}
		ecrivain.write("\t}\n");

		ecrivain.write("}\n");
	}

	public void genererErreur(RequeteSite requeteSite, Exception e) {
		e.printStackTrace();
		try {
			MimeMessage message = new MimeMessage(requeteSite.ecouteurContexte_.sessionCourriel);
			message.setFrom(new InternetAddress(requeteSite.configSite_.mailAdmin));
			InternetAddress destinaires[] = new InternetAddress[1];
			destinaires[0] = new InternetAddress(requeteSite.configSite_.mailAdmin);
			message.setRecipients(Message.RecipientType.TO, destinaires);
			String nomDomaine = requeteSite.configSite_.nomDomaine;
			String sujet = nomDomaine + " erreur " + " " + requeteSite.utilisateurNom + " " + requeteSite.requeteServlet.getRequestURI();
			String corps = ExceptionUtils.getStackTrace(e);
			message.setSubject(sujet);
			message.setContent(corps, "text/plain");
			Transport.send(message);
			String s = e.getMessage();
			requeteSite.reponseServlet.sendError(500, s);
		} catch(Exception e2) {
			e.printStackTrace();
		}
	}
}
