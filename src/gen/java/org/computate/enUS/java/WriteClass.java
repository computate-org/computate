package org.computate.enUS.java;

public class WriteClassGen extends IndexClass {

	protected void writeClass() { 
		SolrDocumentList searchList = searchResponse.getResults();

		if(searchList.size() > 0) {
			String classeCheminRepertoire = null;
			String classeChemin = null; 
			File classeRepertoire = null;
			File classeFichier = null;
			StringBuilder s = new StringBuilder();
			
			String classeNomSimple = null;
			String classeNomSimpleSuper = null;    
			String classeNomEnsemble = null;
			List<String> classeImportations = null;  
	
			for(int i = 0; i < searchList.size(); i++) { 
				SolrDocument doc = searchList.get(i); 
				Integer partNumber = (Integer)doc.get("partNumber_stored_int");
				if(partNumber.equals(1)) {
					classeCheminRepertoire = (String)doc.get("classeCheminRepertoire_" + nomLangue + "_stored_string");
					classeChemin = (String)doc.get("classeChemin_" + nomLangue + "_stored_string"); 
					classeRepertoire = new File(classeCheminRepertoire);
					classeRepertoire.mkdirs();
					classeFichier = new File(classeChemin);
					classeNomSimple = (String)doc.get("classeNomSimple_" + nomLangue + "_stored_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + nomLangue + "_stored_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + nomLangue + "_stored_string");
					classeImportations = (List<String>)doc.get("classeImportations_" + nomLangue + "_stored_strings");
		
					s.append("package ").append(classeNomEnsemble).append(";\n\n");
					if(classeImportations.size() > 0) {
						for(String classeImportation : classeImportations) {
							s.append("import ").append(classeImportation).append(";\n");
						} 
						s.append("\n");
					}
					s.append("public class ").append(classeNomSimple);
					s.append(" extends ").append(classeNomSimpleSuper);
					s.append(" {\n");
					s.append("\n"); 
				} 
				else {  
					Boolean partEstChamp = (Boolean)doc.get("partEstChamp_stored_boolean");
					Boolean partEstMethode = (Boolean)doc.get("partEstMethode_stored_boolean");
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stored_boolean");
					String champVar = (String)doc.get("champVar_" + nomLangue + "_stored_string");
	
					if(BooleanUtils.isTrue(partEstChamp)) {
						s.append("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstPublic_stored_boolean")))
							s.append("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstProtege_stored_boolean")))
							s.append("protege ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstPrive_stored_boolean")))
							s.append("prive ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstStatique_stored_boolean")))
							s.append("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstFinale_stored_boolean")))
							s.append("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstAbstrait_stored_boolean")))
							s.append("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstNatif_stored_boolean")))
							s.append("native ");
						s.append(";\n");
					}     
	
					if(BooleanUtils.isTrue(partEstMethode)) {
						String methodeVar = (String)doc.get("methodeVar_" + nomLangue + "_stored_string");
						String methodeCodeSource = (String)doc.get("methodeCodeSource_" + nomLangue + "_stored_string");
						s.append("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPublic_stored_boolean")))
							s.append("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstProtege_stored_boolean")))
							s.append("protected ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPrive_stored_boolean")))
							s.append("private ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstStatique_stored_boolean")))
							s.append("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstFinale_stored_boolean")))
							s.append("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstAbstrait_stored_boolean")))
							s.append("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstNatif_stored_boolean")))
							s.append("native ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstVide_stored_boolean")))
							s.append("void ");
						else
							s.append((String)doc.get("methodeNomSimpleComplet_stored_string")).append(" ");
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

	protected void writeClassGen() { 
		SolrDocumentList listeRecherche = searchResponse.getResults();

		if(langueIndexe || !StringUtils.equals(nomLangue, this.nomLangue)) {    
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
				Integer partNumber = (Integer)doc.get("partNumber_stored_int");
				if(partNumber.equals(1)) {
					classeCheminRepertoireGen = (String)doc.get("classeCheminRepertoireGen_" + nomLangue + "_stored_string");
					classeCheminGen = (String)doc.get("classeCheminGen_" + nomLangue + "_stored_string"); 
					classeRepertoire = new File(classeCheminRepertoireGen);
					classeRepertoire.mkdirs();
					classeFichier = new File(classeCheminGen);
					classeNomSimpleGen = (String)doc.get("classeNomSimpleGen_" + nomLangue + "_stored_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + nomLangue + "_stored_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + nomLangue + "_stored_string");
		
					s.append("package ").append(classeNomEnsemble).append(";\n\n");
					s.append("public class ").append(classeNomSimpleGen).append(" extends ").append(classeNomSimpleSuper);
					s.append(" {\n");
					s.append("\n"); 
				} 
				else {
					Boolean partEstChamp = (Boolean)doc.get("partEstChamp_stored_boolean");
					Boolean partEstMethode = (Boolean)doc.get("partEstMethode_stored_boolean");
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stored_boolean");
					String champVar = (String)doc.get("champVar_" + nomLangue + "_stored_string");
	
					if(BooleanUtils.isTrue(partEstChamp)) {
						s.append("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstPublic_stored_boolean")))
							s.append("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstProtege_stored_boolean")))
							s.append("protege ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstPrive_stored_boolean")))
							s.append("prive ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstStatique_stored_boolean")))
							s.append("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstFinale_stored_boolean")))
							s.append("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstAbstrait_stored_boolean")))
							s.append("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("champEstNatif_stored_boolean")))
							s.append("native ");
						s.append(";\n");
					}     
	
					if(BooleanUtils.isTrue(partEstMethode)) {
						String methodeVar = (String)doc.get("methodeVar_" + nomLangue + "_stored_string");
						String methodeCodeSource = (String)doc.get("methodeCodeSource_" + nomLangue + "_stored_string");
						s.append("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPublic_stored_boolean")))
							s.append("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstProtege_stored_boolean")))
							s.append("protected ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPrive_stored_boolean")))
							s.append("private ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstStatique_stored_boolean")))
							s.append("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstFinale_stored_boolean")))
							s.append("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstAbstrait_stored_boolean")))
							s.append("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstNatif_stored_boolean")))
							s.append("native ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstVide_stored_boolean")))
							s.append("void ");
						else
							s.append((String)doc.get("methodeNomSimpleComplet_stored_string")).append(" ");
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

}
