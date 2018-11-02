package org.computate.frFR.java;

/**	Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue.
 */
public abstract class EcrireGenClasseGen<DEV> {
	public static final String VAL_entiteCommentaireLigne1Part1 = "L'entit\u00E9 \u00AB ";
	public static final String VAL_entiteCommentaireLigne1Part2 = " \u00BB";
	public static final String VAL_entiteCouvertureLigne1Part1 = " est d\u00E9fini comme null avant d'\u00EAtre initialis\u00E9. ";
	public static final String VAL_entiteCouvertureLigne2Part1 = " est pour envelopper une valeur \u00E0 assigner \u00E0 ce champ lors de l'initialisation. ";
	public static final String VAL_entiteConstruitLigne1Part1 = "Il est construit avant d'\u00EAtre initialis\u00E9 avec le constructeur par d\u00E9faut ";
	public static final String VAL_entiteConstruitLigne1Part2 = "(). ";
	public static final String VAL_entiteConstruitLigne2Part1 = " est le champ d\u00E9j\u00E0 construit. ";
	public static final String VAL_entiteThrowsLigne2Part1 = " afin que toute exception lors de l'initialisation est g\u00E9r\u00E9e par le servlet. ";
}
