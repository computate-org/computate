package org.computate.tout.java;

public class EcrireClasseGen extends IndexerClasse {

	protected void ecrireClasse() { 
		SolrDocumentList listeRecherche = reponseRecherche.getResults();

		if(listeRecherche.size() > 0) {
			String classeCheminRepertoire = null;
			String classeChemin = null; 
			File classeRepertoire = null;
			File classeFichier = null;
			StringBuilder s = new StringBuilder();
			
			String classeNomSimple = null;
			String classeNomSimpleSuper = null;    
			String classeNomEnsemble = null;
	
			for(int i = 0; i < listeRecherche.size(); i++) {
				SolrDocument doc = listeRecherche.get(i); 
				Integer partNumero = (Integer)doc.get("partNumero_stocke_int");
				if(partNumero.equals(1)) {
					classeCheminRepertoire = (String)doc.get("classeCheminRepertoire_" + nomLangue + "_stocke_string");
					classeChemin = (String)doc.get("classeChemin_" + nomLangue + "_stocke_string"); 
					classeRepertoire = new File(classeCheminRepertoire);
					classeRepertoire.mkdirs();
					classeFichier = new File(classeChemin);
					classeNomSimple = (String)doc.get("classeNomSimple_" + nomLangue + "_stocke_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + nomLangue + "_stocke_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + nomLangue + "_stocke_string");
		
					s.append("package ").append(classeNomEnsemble).append(";\n\n");
					s.append("public class ").append(classeNomSimple).append(" extends ").append(classeNomSimpleSuper);
					s.append(" {\n");
					s.append("\n"); 
				} 
				else {  
					Boolean partEstChamp = (Boolean)doc.get("partEstChamp_stocke_boolean");
					Boolean partEstMethode = (Boolean)doc.get("partEstMethode_stocke_boolean");
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stocke_boolean");
					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stocke_boolean");
					String champVar = (String)doc.get("champVar_" + nomLangue + "_stocke_string");
	
					if(BooleanUtils.isTrue(partEstChamp)) {
						s.append("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstPublic_stocke_boolean")))
							s.append("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstProtege_stocke_boolean")))
							s.append("protege ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstPrive_stocke_boolean")))
							s.append("prive ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstStatique_stocke_boolean")))
							s.append("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstFinale_stocke_boolean")))
							s.append("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstAbstrait_stocke_boolean")))
							s.append("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstNatif_stocke_boolean")))
							s.append("native ");
						s.append(";\n");
					}     
	
					if(BooleanUtils.isTrue(partEstMethode)) {
						String methodeVar = (String)doc.get("methodeVar_" + nomLangue + "_stocke_string");
						String methodeCodeSource = (String)doc.get("methodeCodeSource_" + nomLangue + "_stocke_string");
						s.append("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPublic_stocke_boolean")))
							s.append("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstProtege_stocke_boolean")))
							s.append("protected ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPrive_stocke_boolean")))
							s.append("private ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstStatique_stocke_boolean")))
							s.append("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstFinale_stocke_boolean")))
							s.append("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstAbstrait_stocke_boolean")))
							s.append("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstNatif_stocke_boolean")))
							s.append("native ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstVide_stocke_boolean")))
							s.append("void ");
						else
							s.append((String)doc.get("methodeNomSimpleComplet_stocke_string")).append(" ");
						s.append(methodeVar);
						s.append("(");
						s.append(")");
						s.append(" {");
						s.append(methodeCodeSource);
						s.append("}\n\n");
					} 
				}
			}
			s.append("}\n");
			FileUtils.write(classeFichier, s, Charset.forName("UTF-8")); 
		}
		else {
			System.err.println("No file was found in the search engine. ");
		}
	}

	protected void ecrireClasseGen() { 
		SolrDocumentList listeRecherche = reponseRecherche.getResults();

		String classeCheminRepertoireGen = null;
		String classeCheminGen = null; 
		File classeRepertoire = null;
		File classeFichier = null;
		StringBuilder s = new StringBuilder();
		
		String classeNomSimpleGen = null;
		String classeNomSimpleSuper = null;    
		String classeNomEnsemble = null;     

		for(int i = 0; i < listeRecherche.size(); i++) {
			SolrDocument doc = listeRecherche.get(i); 
			Integer partNumero = (Integer)doc.get("partNumero_stocke_int");
			if(partNumero.equals(1)) {
				classeCheminRepertoireGen = (String)doc.get("classeCheminRepertoireGen_" + nomLangue + "_stocke_string");
				classeCheminGen = (String)doc.get("classeCheminGen_" + nomLangue + "_stocke_string"); 
				classeRepertoire = new File(classeCheminRepertoireGen);
				classeRepertoire.mkdirs();
				classeFichier = new File(classeCheminGen);
				classeNomSimpleGen = (String)doc.get("classeNomSimpleGen_" + nomLangue + "_stocke_string");
				classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + nomLangue + "_stocke_string");
				classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + nomLangue + "_stocke_string");
	
				s.append("package ").append(classeNomEnsemble).append(";\n\n");
				s.append("public class ").append(classeNomSimpleGen).append(" extends ").append(classeNomSimpleSuper);
				s.append(" {\n");
				s.append("\n"); 
			} 
			else {
				Boolean partEstChamp = (Boolean)doc.get("partEstChamp_stocke_boolean");
				Boolean partEstMethode = (Boolean)doc.get("partEstMethode_stocke_boolean");
				Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stocke_boolean");
				Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stocke_boolean");
				String champVar = (String)doc.get("champVar_" + nomLangue + "_stocke_string");

				if(BooleanUtils.isTrue(partEstChamp)) {
					s.append("\t");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstPublic_stocke_boolean")))
						s.append("public ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstProtege_stocke_boolean")))
						s.append("protege ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstPrive_stocke_boolean")))
						s.append("prive ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstStatique_stocke_boolean")))
						s.append("static ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstFinale_stocke_boolean")))
						s.append("final ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstAbstrait_stocke_boolean")))
						s.append("abstract ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstNatif_stocke_boolean")))
						s.append("native ");
					s.append(";\n");
				}     

				if(BooleanUtils.isTrue(partEstMethode)) {
					String methodeVar = (String)doc.get("methodeVar_" + nomLangue + "_stocke_string");
					String methodeCodeSource = (String)doc.get("methodeCodeSource_" + nomLangue + "_stocke_string");
					s.append("\t");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPublic_stocke_boolean")))
						s.append("public ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstProtege_stocke_boolean")))
						s.append("protected ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPrive_stocke_boolean")))
						s.append("private ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstStatique_stocke_boolean")))
						s.append("static ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstFinale_stocke_boolean")))
						s.append("final ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstAbstrait_stocke_boolean")))
						s.append("abstract ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstNatif_stocke_boolean")))
						s.append("native ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstVide_stocke_boolean")))
						s.append("void ");
					else
						s.append((String)doc.get("methodeNomSimpleComplet_stocke_string")).append(" ");
					s.append(methodeVar);
					s.append("(");
					s.append(")");
					s.append(" {");
					s.append(methodeCodeSource);
					s.append("}\n\n");
				} 
			}
		}
		s.append("}\n");
		FileUtils.write(classeFichier, s, Charset.forName("UTF-8"));  
	}

}
