package org.computate.frFR.java;

import java.io.PrintWriter;

/**   
 * nomCanonique.enUS: org.computate.enUS.java.WriteApiClass
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */  
public class EcrireApiClasse extends EcrireApiClasseGen<EcrireGenClasse> {  

	protected PrintWriter auteurApiGenClasse;
	protected String classeNomSimpleApiGen;

	public void apiCodeClasseDebut(String langueNom) throws Exception {
		o = auteurApiGenClasse;
		l("package ", classeNomEnsemble, ";");
		l();
		tl(0, "import javax.servlet.http.HttpServlet;");
		if(classeImportationsGen.size() > 0) { 
			for(String classeImportation : classeImportationsGen) {
				l("import ", classeImportation, ";");
			}
			l();
		}
		ecrireCommentaire(classeCommentaire, 0); 
		s("public abstract class ", classeNomSimpleApiGen);
		l(" extends HttpServlet {");
	}

	public void apiCodeClasseFin(String langueNom) throws Exception {
		o = auteurApiGenClasse;
		tl(0, "}");

		System.out.println("Ecrire: " + classeCheminApiGen); 
		auteurApiGenClasse.flush();
		auteurApiGenClasse.close();
	}
}
