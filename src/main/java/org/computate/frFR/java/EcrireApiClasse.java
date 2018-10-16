package org.computate.frFR.java;

import java.io.PrintWriter;

/**   
 * nomCanonique.enUS: org.computate.enUS.java.WriteApiClass
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */  
public class EcrireApiClasse extends EcrireApiClasseGen<EcrireGenClasse> {   

	protected PrintWriter auteurApiClasse;
	protected String classeNomSimpleApi;

	public void apiCodeClasseDebut() throws Exception {
		l("package ", classeNomEnsemble, ";");
		l();
		tl(1, "import javax.servlet.http.HttpServlet;");
		if(classeImportationsGen.size() > 0) { 
			for(String classeImportation : classeImportationsGen) {
				l("import ", classeImportation, ";");
			}
			l();
		}
		ecrireCommentaire(classeCommentaire, 0); 
		s("public abstract class ", classeNomSimpleApi);
		l(" extends HttpServlet {");
	}

	public void apiCodeClasseFin() throws Exception {
		t(1, "}");
	}
}
