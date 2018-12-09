package org.computate.enUS.java;

import io.vertx.core.http.HttpServerResponse;
import org.apache.commons.text.StringEscapeUtils;

public abstract class IndexClassGen<DEV> {

	/////////////////////
	// initialiserLoin //
	/////////////////////

	protected boolean dejaInitialiseIndexClass = false;

	public IndexClass initLoinIndexClass(RequeteSite requeteSite) throws Exception {
		setRequeteSite_(requeteSite);
		initLoinIndexClass();
		return (IndexClass)this;
	}

	public IndexClass initLoinIndexClass() throws Exception {
		if(!dejaInitialiseIndexClass) {
			dejaInitialiseIndexClass = true;
		}
		return (IndexClass)this;
	}

	public void initLoinPourClasse(RequeteSite requeteSite) throws Exception {
		initLoinIndexClass(requeteSite);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteIndexClass(RequeteSite requeteSite) throws Exception {
	}

	public void requeteSitePourClasse(RequeteSite requeteSite) throws Exception {
		requeteSiteIndexClass(requeteSite);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = org.apache.commons.lang3.StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirIndexClass(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirIndexClass(String var) throws Exception {
		IndexClass oIndexClass = (IndexClass)this;
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
				o = attribuerIndexClass(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerIndexClass(String var, Object val) throws Exception {
		IndexClass oIndexClass = (IndexClass)this;
		switch(var) {
			default:
				return null;
		}
	}
}
