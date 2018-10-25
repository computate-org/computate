package org.computate.frFR.java;

import org.computate.enUS.java.WriteGenClass;
import org.computate.frFR.java.EcrireGenClasse;

/**	Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue.
 */
public abstract class EcrireApiClasseGen<DEV> extends EcrireGenClasse {

	/////////////////////
	// initialiserLoin //
	/////////////////////

	protected boolean dejaInitialiseEcrireApiClasse = false;

	public void initLoinEcrireApiClasse(RequeteSite requeteSite) throws Exception {
		setRequeteSite(requeteSite);
		initLoinEcrireApiClasse();
	}

	public void initLoinEcrireApiClasse() throws Exception {
		if(!dejaInitialiseEcrireApiClasse) {
			super.initLoinEcrireGenClasse(requeteSite);
			dejaInitialiseEcrireApiClasse = true;
		}
	}

	public void initLoinPourClasse(RequeteSite requeteSite) throws Exception {
		initLoinEcrireApiClasse(requeteSite);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcrireApiClasse(RequeteSite requeteSite) throws Exception {
			super.requeteSiteEcrireGenClasse(requeteSite);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite) throws Exception {
		requeteSiteEcrireApiClasse(requeteSite);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = org.apache.commons.lang3.StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEcrireApiClasse(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEcrireApiClasse(String var) throws Exception {
		EcrireApiClasse oEcrireApiClasse = (EcrireApiClasse)this;
		switch(var) {
			default:
				return super.obtenirEcrireGenClasse(var);
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
				o = attribuerEcrireApiClasse(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEcrireApiClasse(String var, Object val) throws Exception {
		EcrireApiClasse oEcrireApiClasse = (EcrireApiClasse)this;
		switch(var) {
			default:
				return super.attribuerEcrireGenClasse(var, val);
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
					o = definirEcrireApiClasse(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEcrireApiClasse(String var, String val) throws Exception {
		EcrireApiClasse oEcrireApiClasse = (EcrireApiClasse)this;
		switch(var) {
			default:
				return super.definirEcrireGenClasse(var, val);
		}
	}
}
