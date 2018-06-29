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

		ecrireClasse.indexerClasse(ecrireClasse.classeCheminAbsolu);
		if("tout".equals(ecrireClasse.nomLangue)) {
			ecrireClasse.ecrireClasseGen(ecrireClasse.classeCheminAbsolu, ecrireClasse.nomLangue);
			for(String nomLangue : ecrireClasse.toutesLangues) {
				ecrireClasse.ecrireClasseGen(ecrireClasse.classeCheminAbsolu, nomLangue);
			}
		}
		else {
			ecrireClasse.ecrireClasseGen(ecrireClasse.classeCheminAbsolu, ecrireClasse.nomLangue);
		}
	}
}
