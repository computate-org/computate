package org.computate.tout.java;

public class RegarderClasseGen extends EcrireClasse {

	public static void main() {   
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
		System.out.println("nomLangue : " + ecrireClasse.nomLangue);

		ecrireClasse.indexerClasse(ecrireClasse.classeCheminAbsolu);
		if("tout".equals(ecrireClasse.nomLangue)) {
			ecrireClasse.ecrireClasseGen(ecrireClasse.classeCheminAbsolu, ecrireClasse.nomLangue);
			for(String nomLangue : ecrireClasse.toutesLangues) {
				ecrireClasse.ecrireClasseGen(ecrireClasse.classeCheminAbsolu, nomLangue);
			}
			System.out.println("autresLangues: " + ecrireClasse.autresLangues); 
			for(String nomLangue : ecrireClasse.autresLangues) {
				ecrireClasse.ecrireClasse(ecrireClasse.classeCheminAbsolu, nomLangue);
			}
		}
		else {
			ecrireClasse.ecrireClasseGen(ecrireClasse.classeCheminAbsolu, ecrireClasse.nomLangue);
		}
	}

}
