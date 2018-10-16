package org.computate.frFR.java;

public abstract class EcrireTouteClassesGen<DEV> {

	protected boolean dejaInitialiseEcrireTouteClasses = false;

	public void initLoinEcrireTouteClasses() throws Exception {
		if(!dejaInitialiseEcrireTouteClasses) {
			dejaInitialiseEcrireTouteClasses = true;
		}
	}
}
