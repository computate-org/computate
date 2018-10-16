package org.computate.enUS.java;

import org.computate.enUS.java.WriteGenClass;
import org.computate.enUS.java.WriteGenClass;

/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public abstract class WriteAllClassGen<DEV> extends WriteGenClass {

	protected boolean dejaInitialiseWriteAllClass = false;

	public void initLoinWriteAllClass() throws Exception {
		if(!dejaInitialiseWriteAllClass) {
			super.initLoinWriteGenClass(requeteSite);
			dejaInitialiseWriteAllClass = true;
		}
	}
}
