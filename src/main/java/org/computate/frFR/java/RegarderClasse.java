package org.computate.frFR.java;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

public class RegarderClasse extends EcrireClasse {
	
	public RegarderClasse() {
	}

	public static void main(String[] args) throws Exception {   
		RegarderClasse regarderClasse = new RegarderClasse();
		try {
			regarderClasse.args = args;
			regarderClasse.initRegarderClasseBase(); 
		}
		catch(Exception e) {
			System.err.println("Erreur pendant traiterEvenements. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
		regarderClasse(regarderClasse);
	}
	
	public static void regarderClasse(RegarderClasse regarderClasse) throws Exception {
		System.out.println("cheminAbsolu : " + regarderClasse.classeCheminAbsolu);

		regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu);
		if("tout".equals(regarderClasse.langueNom)) {
			regarderClasse.ecrireClasseGen(regarderClasse.classeCheminAbsolu, regarderClasse.langueNom);
			for(String langueNom : regarderClasse.toutesLangues) {
				regarderClasse.ecrireClasseGen(regarderClasse.classeCheminAbsolu, langueNom);
			}
			for(String langueNom : regarderClasse.autresLangues) {
				regarderClasse.ecrireClasse(regarderClasse.classeCheminAbsolu, langueNom);
			}
		}
		else {
			regarderClasse.ecrireClasseGen(regarderClasse.classeCheminAbsolu, regarderClasse.langueNom);
		}
	}

	@Test
	public void testStuff() throws Exception {
		String appliNom = "computate";
		String appliChemin = "/usr/local/src/computate";
		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/RegarderClasseBase.java";
		String[] args = ArrayUtils.toArray(appliChemin, classeCheminAbsolu);
		RegarderClasse regarderClasse = new RegarderClasse();
		regarderClasse.args = args;
		regarderClasse.appliNom = appliNom;
		regarderClasse.appliChemin = appliChemin;
		regarderClasse.initRegarderClasseBase(); 
		regarderClasse(regarderClasse);
	}
}
