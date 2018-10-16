package org.computate.frFR.java;

public abstract class EcrireTouteClasseGen<DEV> {

	protected boolean dejaInitialiseEcrireTouteClasse = false;

	public void initLoinEcrireTouteClasse() throws Exception {
		if(!dejaInitialiseEcrireTouteClasse) {
			dejaInitialiseEcrireTouteClasse = true;
		}
	}
}
