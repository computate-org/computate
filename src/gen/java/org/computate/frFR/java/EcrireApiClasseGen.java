package org.computate.frFR.java;

import org.computate.frFR.java.EcrireGenClasse;

/**	Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue.
 */
public abstract class EcrireApiClasseGen<DEV> extends EcrireGenClasse {

	protected boolean dejaInitialiseEcrireApiClasse = false;

	public void initLoinEcrireApiClasse() throws Exception {
		if(!dejaInitialiseEcrireApiClasse) {
			dejaInitialiseEcrireApiClasse = true;
		}
	}
}
