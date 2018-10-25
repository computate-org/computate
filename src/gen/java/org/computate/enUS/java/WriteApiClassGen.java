package org.computate.enUS.java;

import org.computate.enUS.java.WriteGenClass;
import org.computate.enUS.java.WriteGenClass;

/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public abstract class WriteApiClassGen<DEV> extends WriteGenClass {

	/////////////////////
	// initialiserLoin //
	/////////////////////

	protected boolean dejaInitialiseWriteApiClass = false;

	public void initLoinWriteApiClass(RequeteSite requeteSite) throws Exception {
		setRequeteSite(requeteSite);
		initLoinWriteApiClass();
	}

	public void initLoinWriteApiClass() throws Exception {
		if(!dejaInitialiseWriteApiClass) {
			super.initLoinWriteGenClass(requeteSite);
			dejaInitialiseWriteApiClass = true;
		}
	}

	public void initLoinPourClasse(RequeteSite requeteSite) throws Exception {
		initLoinWriteApiClass(requeteSite);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteWriteApiClass(RequeteSite requeteSite) throws Exception {
			super.requeteSiteWriteGenClass(requeteSite);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite) throws Exception {
		requeteSiteWriteApiClass(requeteSite);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = org.apache.commons.lang3.StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirWriteApiClass(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirWriteApiClass(String var) throws Exception {
		WriteApiClass oWriteApiClass = (WriteApiClass)this;
		switch(var) {
			default:
				return super.obtenirWriteGenClass(var);
		}
	}

	///////////////
	// attribuer //
	///////////////

	@Override public boolean attribuerPourClasse(String var, Object val) throws Exception {
		String[] vars = org.apache.commons.lang3.StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerWriteApiClass(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerWriteApiClass(String var, Object val) throws Exception {
		WriteApiClass oWriteApiClass = (WriteApiClass)this;
		switch(var) {
			default:
				return super.attribuerWriteGenClass(var, val);
		}
	}

	/////////////
	// definir //
	/////////////

	@Override public boolean definirPourClasse(String var, String...vals) throws Exception {
		String[] vars = org.apache.commons.lang3.StringUtils.split(var, ".");
		Object o = null;
		String val = vals == null ? null : vals[vals.length - 1];
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirWriteApiClass(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirWriteApiClass(String var, String val) throws Exception {
		WriteApiClass oWriteApiClass = (WriteApiClass)this;
		switch(var) {
			default:
				return super.definirWriteGenClass(var, val);
		}
	}
}
