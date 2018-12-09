package org.computate.frFR.java;

import io.vertx.core.http.HttpServerResponse;
import org.apache.commons.text.StringEscapeUtils;

public abstract class IndexerClasseGen<DEV> {

	/////////////////////
	// initialiserLoin //
	/////////////////////

	protected boolean dejaInitialiseIndexerClasse = false;

	public IndexerClasse initLoinIndexerClasse(RequeteSite requeteSite) throws Exception {
		setRequeteSite_(requeteSite);
		initLoinIndexerClasse();
		return (IndexerClasse)this;
	}

	public IndexerClasse initLoinIndexerClasse() throws Exception {
		if(!dejaInitialiseIndexerClasse) {
			dejaInitialiseIndexerClasse = true;
		}
		return (IndexerClasse)this;
	}

	public void initLoinPourClasse(RequeteSite requeteSite) throws Exception {
		initLoinIndexerClasse(requeteSite);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteIndexerClasse(RequeteSite requeteSite) throws Exception {
	}

	public void requeteSitePourClasse(RequeteSite requeteSite) throws Exception {
		requeteSiteIndexerClasse(requeteSite);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = org.apache.commons.lang3.StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirIndexerClasse(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirIndexerClasse(String var) throws Exception {
		IndexerClasse oIndexerClasse = (IndexerClasse)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// attribuer //
	///////////////

	public boolean attribuerPourClasse(String var, Object val) throws Exception {
		String[] vars = org.apache.commons.lang3.StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerIndexerClasse(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerIndexerClasse(String var, Object val) throws Exception {
		IndexerClasse oIndexerClasse = (IndexerClasse)this;
		switch(var) {
			default:
				return null;
		}
	}
}
