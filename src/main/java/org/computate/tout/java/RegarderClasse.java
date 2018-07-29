package org.computate.tout.java;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class RegarderClasse extends EcrireClasse {

	public static void main(String[] args) throws Exception {   
		RegarderClasse ecrireClasse = new RegarderClasse();
		try {
			ecrireClasse.args = args;
			ecrireClasse.initRegarderClasseBase(); 
		}
		catch(Exception e) {
			System.err.println("Erreur pendant traiterEvenements. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
		System.out.println("cheminAbsolu : " + ecrireClasse.classeCheminAbsolu);
		System.out.println("langueNom : " + ecrireClasse.langueNom);

		ecrireClasse.indexerClasse(ecrireClasse.classeCheminAbsolu);
		if("tout".equals(ecrireClasse.langueNom)) {
			ecrireClasse.ecrireClasseGen(ecrireClasse.classeCheminAbsolu, ecrireClasse.langueNom);
			for(String langueNom : ecrireClasse.toutesLangues) {
				ecrireClasse.ecrireClasseGen(ecrireClasse.classeCheminAbsolu, langueNom);
			}
			for(String langueNom : ecrireClasse.autresLangues) {
				ecrireClasse.ecrireClasse(ecrireClasse.classeCheminAbsolu, langueNom);
			}
		}
		else {
			ecrireClasse.ecrireClasseGen(ecrireClasse.classeCheminAbsolu, ecrireClasse.langueNom);
		}
	}
}
