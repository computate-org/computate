package org.computate.enUS.java;

/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public abstract class WriteGenClassGen<DEV> {
	public static final String VAL_entityCommentLine1Part1 = "The \"";
	public static final String VAL_entityCommentLine1Part2 = "\" entity";
	public static final String VAL_entityWrapLine1Part1 = " is set to null before it is initialized. ";
	public static final String VAL_entityWrapLine2Part1 = " is for wrapping a value to be assigned to this field during initialization. ";
	public static final String VAL_entityConstructedLine1Part1 = "It is constructed before it is initialized with the default constructor ";
	public static final String VAL_entityConstructedLine1Part2 = "(). ";
	public static final String VAL_entityConstructedLine2Part1 = " is the field already constructed. ";
	public static final String VAL_entityThrowsLine2Part1 = " so that any exception during initialization is handled by the servlet. ";

	/////////////////////
	// initialiserLoin //
	/////////////////////

	protected boolean dejaInitialiseWriteGenClass = false;

	public void initLoinWriteGenClass(RequeteSite requeteSite) throws Exception {
		setRequeteSite_(requeteSite);
		initLoinWriteGenClass();
	}

	public void initLoinWriteGenClass() throws Exception {
		if(!dejaInitialiseWriteGenClass) {
			dejaInitialiseWriteGenClass = true;
		}
	}

	public void initLoinPourClasse(RequeteSite requeteSite) throws Exception {
		initLoinWriteGenClass(requeteSite);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteWriteGenClass(RequeteSite requeteSite) throws Exception {
	}

	public void requeteSitePourClasse(RequeteSite requeteSite) throws Exception {
		requeteSiteWriteGenClass(requeteSite);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = org.apache.commons.lang3.StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirWriteGenClass(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirWriteGenClass(String var) throws Exception {
		WriteGenClass oWriteGenClass = (WriteGenClass)this;
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
				o = attribuerWriteGenClass(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerWriteGenClass(String var, Object val) throws Exception {
		WriteGenClass oWriteGenClass = (WriteGenClass)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean definirPourClasse(String var, String...vals) throws Exception {
		String[] vars = org.apache.commons.lang3.StringUtils.split(var, ".");
		Object o = null;
		String val = vals == null ? null : vals[vals.length - 1];
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirWriteGenClass(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirWriteGenClass(String var, String val) throws Exception {
		WriteGenClass oWriteGenClass = (WriteGenClass)this;
		switch(var) {
			default:
				return null;
		}
	}
}
