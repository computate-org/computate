/*
 * Copyright (c) 2018-2022 Computate Limited Liability Company in Utah, USA. 
 *
 * This program and the accompanying materials are made available under the
 * terms of the GNU GENERAL PUBLIC LICENSE Version 3 which is available at
 * 
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 * 
 * You may not propagate or modify a covered work except as expressly provided 
 * under this License. Any attempt otherwise to propagate or modify it is void, 
 * and will automatically terminate your rights under this License (including 
 * any patent licenses granted under the third paragraph of section 11).
 */
package org.computate.frFR.java;   

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration2.YAMLConfiguration;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.WordUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaConstructor;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaGenericDeclaration;
import com.thoughtworks.qdox.model.JavaMember;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.JavaType;
import com.thoughtworks.qdox.model.JavaTypeVariable;
import com.thoughtworks.qdox.model.impl.DefaultJavaParameterizedType;

/**
 * NomCanonique.enUS: org.computate.enUS.java.IndexClass
 */ 
public class IndexerClasse extends RegarderClasseBase { 

	public static final String REGEX_LIGNE = "\r\n|\r|\n";

	/**
	 * Var.enUS: VAL_canonicalNameString
	 */
	public static final String VAL_nomCanoniqueString = String.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameBoolean
	 */
	public static final String VAL_nomCanoniqueBoolean = Boolean.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameDate
	 */
	public static final String VAL_nomCanoniqueDate = Date.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameLong
	 */
	public static final String VAL_nomCanoniqueLong = Long.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameDouble
	 */
	public static final String VAL_nomCanoniqueDouble = Double.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameFloat
	 */
	public static final String VAL_nomCanoniqueFloat = Float.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameBigDecimal
	 */
	public static final String VAL_nomCanoniqueBigDecimal = BigDecimal.class.getCanonicalName();
	public static final String VAL_nomCanoniquePoint = "io.vertx.pgclient.data.Point";
	/**
	 * Var.enUS: VAL_canonicalNameInteger
	 */
	public static final String VAL_nomCanoniqueInteger = Integer.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameTimestamp
	 */
	public static final String VAL_nomCanoniqueTimestamp = Timestamp.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameLocalDateTime
	 */
	public static final String VAL_nomCanoniqueLocalDateTime = LocalDateTime.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameZonedDateTime
	 */
	public static final String VAL_nomCanoniqueZonedDateTime = ZonedDateTime.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameLocalDate
	 */
	public static final String VAL_nomCanoniqueLocalDate = LocalDate.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameLocalTime
	 */
	public static final String VAL_nomCanoniqueLocalTime = LocalTime.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameList
	 */
	public static final String VAL_nomCanoniqueList = List.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameArrayList
	 */
	public static final String VAL_nomCanoniqueArrayList = ArrayList.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameSet
	 */
	public static final String VAL_nomCanoniqueSet = Set.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameHashSet
	 */
	public static final String VAL_nomCanoniqueHashSet = HashSet.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameInstant
	 */
	public static final String VAL_nomCanoniqueInstant = Instant.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameVertxJsonArray
	 */
	public static final String VAL_nomCanoniqueVertxJsonArray = "io.vertx.core.json.JsonArray";
	/**
	 * Var.enUS: VAL_canonicalNameVertxJsonObject
	 */
	public static final String VAL_nomCanoniqueVertxJsonObject = "io.vertx.core.json.JsonObject";

	ClasseParts classePartsPointSerializer;
	ClasseParts classePartsPointDeserializer;

	ClasseParts classePartsLocalTimeSerializer;
	ClasseParts classePartsLocalTimeDeserializer;

	ClasseParts classePartsLocalDateSerializer;
	ClasseParts classePartsLocalDateDeserializer;

	ClasseParts classePartsZonedDateTimeSerializer;
	ClasseParts classePartsZonedDateTimeDeserializer;

//	ClasseParts classePartsSolrInputDocument;
//	/**
//	 * Var.enUS: classPartsSolrDocument
//	 */
//	ClasseParts classePartsSolrDocument;
//	/**
//	 * Var.enUS: classPartsSolrClient
//	 */
//	ClasseParts classePartsSolrClient;
	/**
	 * Var.enUS: classPartsTest
	 */
	ClasseParts classePartsTest;
	/**
	 * Var.enUS: classPartsList
	 */
	ClasseParts classePartsList;
	/**
	 * Var.enUS: classPartsArrayList
	 */
	ClasseParts classePartsArrayList;
	/**
	 * Var.enUS: classPartsHashSet
	 */
	ClasseParts classePartsHashSet;
	/**
	 * Var.enUS: classPartsSiteRequest
	 */ 
	ClasseParts classePartsRequeteSite;
	ClasseParts classePartsUtilisateurSite;
	/**
	 * Var.enUS: classPartsApiRequest
	 */
	ClasseParts classePartsRequeteApi;
	/**
	 * Var.enUS: classPartsModeleBase
	 */
	ClasseParts classePartsModeleBase;
	/**
	 * Var.enUS: classPartsSearchResult
	 */
	ClasseParts classePartsResultatRecherche;
	/**
	 * Var.enUS: classPartsAllWriter
	 */
	ClasseParts classePartsToutEcrivain;
	/**
	 * Var.enUS: classPartsWrap
	 */
	ClasseParts classePartsCouverture;
	/**
	 * Var.enUS: classPartsSearchList
	 */
	ClasseParts classePartsListeRecherche;
	/**
	 * Var.enUS: classPartsPageLayout
	 */
	ClasseParts classePartsMiseEnPage;
	/**
	 * Var.enUS: classPartsPagePart
	 */
	ClasseParts classePartsPagePart;

	ClasseParts classePartsVerticle;

	ClasseParts classePartsBaseApiServiceImpl;

	ClasseParts classePartsMailVerticle;

	ClasseParts classePartsConfigCles;

	/**
	 * Var.enUS: CONTEXT_frFR_AMale
	 */
	String CONTEXTE_frFR_UnMasculin = "un ";
	/**
	 * Var.enUS: CONTEXT_frFR_AFemale
	 */
	String CONTEXTE_frFR_UneFeminin = "une ";

	/**
	 * Var.enUS: CONTEXT_enUS_AConsonant
	 */
	String CONTEXTE_enUS_UnConsonne = "a ";
	/**
	 * Var.enUS: CONTEXT_enUS_AVowel
	 */
	String CONTEXTE_enUS_UnVoyelle = "an ";

	/**
	 * Var.enUS: CONTEXT_frFR_ThisMaleVowel
	 */
	String CONTEXTE_frFR_CetMasculinVoyelle = "cet ";
	/**
	 * Var.enUS: CONTEXT_frFR_ThisFemaleVowel
	 */
	String CONTEXTE_frFR_CetteFemininVoyelle = "cette ";
	/**
	 * Var.enUS: CONTEXT_enUS_ThisVowel
	 */
	String CONTEXTE_enUS_CetteVoyelle = "this ";

	/**
	 * Var.enUS: CONTEXT_frFR_ThisMaleConsonant
	 */
	String CONTEXTE_frFR_CeMasculinConsonne = "ce ";
	/**
	 * Var.enUS: CONTEXT_frFR_ThisFemaleConsonant
	 */
	String CONTEXTE_frFR_CetteFemininConsonne = "cette ";
	/**
	 * Var.enUS: CONTEXT_enUS_ThisConsonant
	 */
	String CONTEXTE_enUS_CetteConsonne = "this ";

	/**
	 * Var.enUS: CONTEXT_frFR_ThesePlural
	 */
	String CONTEXTE_frFR_CesPluriel = "ces ";
	/**
	 * Var.enUS: CONTEXT_enUS_ThesePlural
	 */
	String CONTEXTE_enUS_CesPluriel = "these ";

	/**
	 * Var.enUS: CONTEXT_frFR_CreatedMale
	 */
	String CONTEXTE_frFR_CreeMasculin = "créé ";
	/**
	 * Var.enUS: CONTEXT_frFR_CreatedFemale
	 */
	String CONTEXTE_frFR_CreeeFeminin = "créée ";
	/**
	 * Var.enUS: CONTEXT_enUS_Created
	 */
	String CONTEXTE_enUS_Creee = "created ";

	/**
	 * Var.enUS: CONTEXT_frFR_ModifiedMale
	 */
	String CONTEXTE_frFR_ModifieMasculin = "modifié ";
	/**
	 * Var.enUS: CONTEXT_frFR_ModifiedFemale
	 */
	String CONTEXTE_frFR_ModifieeFeminin = "modifiée ";
	/**
	 * Var.enUS: CONTEXT_enUS_Modified
	 */
	String CONTEXTE_enUS_Modifiee = "modified ";

	/**
	 * Var.enUS: CONTEXT_frFR_TheMaleVowel
	 */
	String CONTEXTE_frFR_LMasculinVoyelle = "l'";
	/**
	 * Var.enUS: CONTEXT_frFR_TheFemaleVowel
	 */
	String CONTEXTE_frFR_LFemininVoyelle = "l'";
	/**
	 * Var.enUS: CONTEXT_enUS_TheVowel
	 */
	String CONTEXTE_enUS_LVoyelle = "the";

	/**
	 * Var.enUS: CONTEXT_frFR_TheMaleConsonant
	 */
	String CONTEXTE_frFR_LeMasculinConsonne = "le ";
	/**
	 * Var.enUS: CONTEXT_frFR_TheFemaleConsonant
	 */
	String CONTEXTE_frFR_LaFemininConsonne = "la ";
	/**
	 * Var.enUS: CONTEXT_enUS_TheConsonant
	 */
	String CONTEXTE_enUS_LaConsonne = "the ";

	/**
	 * Var.enUS: CONTEXT_frFR_ThePlural
	 */
	String CONTEXTE_frFR_LesPluriel = "les ";
	/**
	 * Var.enUS: CONTEXT_enUS_ThePlural
	 */
	String CONTEXTE_enUS_LesPluriel = "the ";

	/**
	 * Var.enUS: CONTEXT_frFR_Search
	 */
	String CONTEXTE_frFR_Rechercher = "rechercher ";
	/**
	 * Var.enUS: CONTEXT_enUS_Search
	 */
	String CONTEXTE_enUS_Rechercher = "search ";

	/**
	 * Var.enUS: CONTEXT_frFR_By
	 */
	String CONTEXTE_frFR_Par = " par ";
	/**
	 * Var.enUS: CONTEXT_enUS_By
	 */
	String CONTEXTE_enUS_Par = " by ";

	/**
	 * Var.enUS: CONTEXT_frFR_CurrentMaleBefore
	 */
	String CONTEXTE_frFR_ActuelMasculinAvant = "";
	/**
	 * Var.enUS: CONTEXT_frFR_CurrentFemaleBefore
	 */
	String CONTEXTE_frFR_ActuelleFemininAvant = "";
	/**
	 * Var.enUS: CONTEXT_enUS_CurrentBefore
	 */
	String CONTEXTE_enUS_ActuelleAvant = "current ";

	/**
	 * Var.enUS: CONTEXT_frFR_CurrentMaleAfter
	 */
	String CONTEXTE_frFR_ActuelMasculinApres = " actuel";
	/**
	 * Var.enUS: CONTEXT_frFR_CurrentFemaleAfter
	 */
	String CONTEXTE_frFR_ActuelleFemininApres = " actuelle";
	/**
	 * Var.enUS: CONTEXT_enUS_CurrentAfter
	 */
	String CONTEXTE_enUS_ActuelleApres = "";

	/**
	 * Var.enUS: CONTEXT_frFR_AllMalePlural
	 */
	String CONTEXTE_frFR_TousMasculinPluriel = "tous ";
	/**
	 * Var.enUS: CONTEXT_frFR_AllFemalePlural
	 */
	String CONTEXTE_frFR_ToutesFemininPluriel = "toutes ";
	/**
	 * Var.enUS: CONTEXT_enUS_AllPlural
	 */
	String CONTEXTE_enUS_ToutesPluriel = "all ";

	/**
	 * Var.enUS: CONTEXT_frFR_NoneFoundMaleBefore
	 */
	String CONTEXTE_frFR_AucunTrouveMasculinAvant = "aucun ";
	/**
	 * Var.enUS: CONTEXT_frFR_NoneFoundFemaleBefore
	 */
	String CONTEXTE_frFR_AucuneTrouveFemininAvant = "aucune ";
	/**
	 * Var.enUS: CONTEXT_enUS_NoneFoundBefore
	 */
	String CONTEXTE_enUS_AucuneTrouveAvant = "no ";

	/**
	 * Var.enUS: CONTEXT_frFR_NoneFoundMaleAfter
	 */
	String CONTEXTE_frFR_AucunTrouveMasculinApres = " trouvé";
	/**
	 * Var.enUS: CONTEXT_frFR_NoneFoundFemaleAfter
	 */
	String CONTEXTE_frFR_AucuneTrouveFemininApres = " trouvée";
	/**
	 * Var.enUS: CONTEXT_enUS_NoneFoundAfter
	 */
	String CONTEXTE_enUS_AucuneTrouveApres = " found";

	/**
	 * Var.enUS: CONTEXT_frFR_OfVowel
	 */
	String CONTEXTE_frFR_DVoyelle = "d'";
	/**
	 * Var.enUS: CONTEXT_enUS_OfVowel
	 */
	String CONTEXTE_enUS_DVoyelle = "of ";

	/**
	 * Var.enUS: CONTEXT_frFR_OfConsonant
	 */
	String CONTEXTE_frFR_DeConsonne = "de ";
	/**
	 * Var.enUS: CONTEXT_enUS_OfConsonant
	 */
	String CONTEXTE_enUS_DeConsonne = "of ";

	/**
	 * Var.enUS: CONTEXT_frFR_AdjectivePlural
	 */
	String CONTEXTE_frFR_AdjectifPluriel = "s";
	/**
	 * Var.enUS: CONTEXT_enUS_AdjectivePlural
	 */
	String CONTEXTE_enUS_AdjectifPluriel = "";

	/**
	 * Var.enUS: CONTEXT_frFR_AdjectiveBefore
	 */
	Boolean CONTEXTE_frFR_AdjectifAvant = false;
	/**
	 * Var.enUS: CONTEXT_enUS_AdjectiveBefore
	 */
	Boolean CONTEXTE_enUS_AdjectifAvant = true;

	/**
	 * Var.enUS: classPartsGenApi
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 */
	protected LinkedHashMap<String, ClasseParts> classePartsGenApi = new LinkedHashMap<String, ClasseParts>();
	/**
	 * Var.enUS: classPartsGenPage
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 */ 
	protected LinkedHashMap<String, ClasseParts> classePartsGenPage = new LinkedHashMap<String, ClasseParts>();

	/**
	 * Var.enUS: contextVideoId
	 */
	private String contexteVideoId;

	/**
	 * Var.enUS: contextUri
	 */
	private String contexteUri;

	/**
	 * Var.enUS: contextDescription
	 */
	private String contexteDescription;

	/**
	 * Var.enUS: contextAName
	 */
	private String contexteUnNom;

	/**
	 * Var.enUS: contextThis
	 */
	private String contexteCe;

	/**
	 * Var.enUS: contextThisName
	 */
	private String contexteCeNom;

	/**
	 * Var.enUS: contextA
	 */
	private String contexteUn;

	/**
	 * Var.enUS: contextCreated
	 */
	private String contexteCree;

	/**
	 * Var.enUS: contextModified
	 */
	private String contexteModifie;

	/**
	 * Var.enUS: contextTheName
	 */
	private String contexteLeNom;

	/**
	 * Var.enUS: contextNameSingular
	 */
	private String contexteNomSingulier;

	/**
	 * Var.enUS: contextNamePlural
	 */
	private String contexteNomPluriel;

	/**
	 * Var.enUS: contextActualName
	 */
	private String contexteNomActuel;

	/**
	 * Var.enUS: contextAll
	 */
	private String contexteTous;

	/**
	 * Var.enUS: contextAllName
	 */
	private String contexteTousNom;

	/**
	 * Var.enUS: contextSearchAllNameBy
	 */
	private String contexteRechercherTousNomPar;

	/**
	 * Var.enUS: contextSearchAllName
	 */
	private String contexteRechercherTousNom;

	/**
	 * Var.enUS: contextTheNames
	 */
	private String contexteLesNoms;

	/**
	 * Var.enUS: contextNoNameFound
	 */
	private String contexteAucunNomTrouve;

	/**
	 * Var.enUS: contextNameVar
	 */
	private String contexteNomVar;

	/**
	 * Var.enUS: contextOfName
	 */
	private String contexteDeNom;

	/**
	 * Var.enUS: contextAdjective
	 */
	private String contexteAdjectif;

	/**
	 * Var.enUS: contextAdjectivePlural
	 */
	private String contexteAdjectifPluriel;

	/**
	 * Var.enUS: contextAdjectiveVar
	 */
	private String contexteAdjectifVar;

	/**
	 * Var.enUS: contextANameAdjective
	 */
	private String contexteUnNomAdjectif;

	/**
	 * Var.enUS: contextTheNameAdjective
	 */
	private String contexteLeNomAdjectif;

	/**
	 * Var.enUS: contextNameAdjectiveSingular
	 */
	private String contexteNomAdjectifSingulier;

	/**
	 * Var.enUS: contextNameAdjectivePlural
	 */
	private String contexteNomAdjectifPluriel;

	/**
	 * Var.enUS: contextTitle
	 */
	private String contexteTitre;

	/**
	 * Var.enUS: contextH1
	 */
	private String contexteH1;

	/**
	 * Var.enUS: contextH2
	 */
	private String contexteH2;

	/**
	 * Var.enUS: contextH3
	 */
	private String contexteH3;

	/**
	 * Var.enUS: contextColor
	 */
	private String contexteCouleur;

	/**
	 * Var.enUS: contextIconGroup
	 */
	private String contexteIconeGroupe;

	/**
	 * Var.enUS: contextIconName
	 */
	private String contexteIconeNom;

	/**
	 * Var.enUS: contextRows
	 */
	private Integer contexteRows;

	/**
	 * Var.enUS: populateQdoxSuperClassesInterfacesAndMe
	 * Param2.var.enUS: qdoxSuperClasses
	 * Param3.var.enUS: qdoxSuperClassesAndMe
	 * Param4.var.enUS: qdoxSuperClassesAndMeWithoutGen
	 * Param5.var.enUS: qdoxSuperClassesAndInterfaces
	 * Param6.var.enUS: qdoxSuperClassesInterfacesAndMe
	 * r: classeSuper
	 * r.enUS: superClass
	 * r: interfacesImplementees
	 * r.enUS: interfacesImplemented
	 * r: classesSuperQdoxInterfacesEtMoi
	 * r.enUS: qdoxSuperClassesInterfacesAndMe
	 * r: classesSuperQdoxEtInterfaces
	 * r.enUS: qdoxSuperClassesAndInterfaces
	 * r: classesSuperQdoxEtMoiSansGen
	 * r.enUS: qdoxSuperClassesAndMeWithoutGen
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: classesSuperQdox
	 * r.enUS: qdoxSuperClasses
	 * r: peuplerClassesSuperQdoxInterfacesEtMoi
	 * r.enUS: populateQdoxSuperClassesInterfacesAndMe
	 */       
	public void peuplerClassesSuperQdoxInterfacesEtMoi (
			JavaClass c
			, ArrayList<JavaClass> classesSuperQdox
			, ArrayList<JavaClass> classesSuperQdoxEtMoi
			, ArrayList<JavaClass> classesSuperQdoxEtMoiSansGen
			, ArrayList<JavaClass> classesSuperQdoxEtInterfaces
			, ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi
			) throws Exception { 
		if(c != null) {
			JavaClass classeSuper = c.getSuperJavaClass();
			List<JavaClass> interfacesImplementees = c.getInterfaces();

			for(JavaClass interfaceQdox : interfacesImplementees) {
				if(interfaceQdox != null && !interfaceQdox.getCanonicalName().equals("java.lang.Object") && !c.equals(interfaceQdox)) {
					classesSuperQdoxInterfacesEtMoi.add(interfaceQdox);
					classesSuperQdoxEtInterfaces.add(classeSuper);
					peuplerClassesSuperQdoxInterfacesEtMoi(interfaceQdox, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtMoiSansGen, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi); // Doesn't seem to work for interfaces that extend other interfaces.
				}
			}
			classesSuperQdoxInterfacesEtMoi.add(c);
			classesSuperQdoxEtMoi.add(c);
			if(!StringUtils.endsWith(c.getCanonicalName(), "Gen"))
				classesSuperQdoxEtMoiSansGen.add(c);
			try {
				if(classeSuper != null && !classeSuper.getCanonicalName().equals("java.lang.Object") && !c.equals(classeSuper)) {
					classesSuperQdoxEtInterfaces.add(classeSuper);
					classesSuperQdox.add(classeSuper);
					peuplerClassesSuperQdoxInterfacesEtMoi(classeSuper, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtMoiSansGen, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi);
				}
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean stockerSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_boolean"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean stockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_boolean"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_string"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerListeSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_strings"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean stockerListeSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_booleans"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeListSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerListeSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValues
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeursChamp
	 * r.enUS: fieldValues
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected List<String> stockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	}
	
	/**
	 * Var.enUS: indexSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * Var.enUS: indexListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerListeSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_booleans"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * Var.enUS: indexListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */ 
	protected String indexerListeSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_strings"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * Var.enUS: indexSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexListSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerListeSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValues
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeursChamp
	 * r.enUS: fieldValues
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected List<String> indexerSolr(String langueNom, SolrInputDocument doc, String nomChamp, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	} 
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Long indexerStockerSolr(SolrInputDocument doc, String nomChamp, Long valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_long"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_long"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Double indexerStockerSolr(SolrInputDocument doc, String nomChamp, Double valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_double"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_double"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Integer indexerStockerSolr(SolrInputDocument doc, String nomChamp, Integer valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_int"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_int"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerStockerSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_boolean"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_boolean"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Long indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Long valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_long"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_long"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Double indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Double valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_double"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_double"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Integer indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Integer valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_int"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_int"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_boolean"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_boolean"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Date indexerStockerSolr(SolrInputDocument doc, String nomChamp, Date valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_date"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_date"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_string"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * Var.enUS: indexStoreSolrRegex
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldNameRegex
	 * Param5.var.enUS: comment
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 * r: valeurDefaut
	 * r.enUS: defaultValue
	 * r: commentaire
	 * r.enUS: comment
	 * r: langueNom
	 * r.enUS: languageName
	 * r: indexerStocker
	 * r.enUS: indexStore
	 */
	protected String indexerStockerSolrRegex(String langueNom, SolrInputDocument doc, String nomChamp, String nomChampRegex, String commentaire) throws Exception {
		return indexerStockerSolrRegex(langueNom, doc, nomChamp, nomChampRegex, commentaire, null);
	}
	
	/**
	 * Var.enUS: indexStoreSolrRegex
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldNameRegex
	 * Param5.var.enUS: comment
	 * Param6.var.enUS: defaultValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 * r: valeurDefaut
	 * r.enUS: defaultValue
	 * r: commentaire
	 * r.enUS: comment
	 * r: langueNom
	 * r.enUS: languageName
	 */
	protected String indexerStockerSolrRegex(String langueNom, SolrInputDocument doc, String nomChamp, String nomChampRegex, String commentaire, String valeurDefaut) throws Exception {
		String valeurChamp = null;
		if(nomChampRegex != null && commentaire != null) {
			Matcher m = Pattern.compile("^" + nomChampRegex + "(." + langueNom + ")?:\\s*(.*)", Pattern.MULTILINE).matcher(commentaire);
			if(m.find()) {
				valeurChamp = m.group(2);
			}
		}
		if(StringUtils.isBlank(valeurChamp)) {
			valeurChamp = valeurDefaut;
		}
		if(StringUtils.isNotBlank(valeurChamp)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolrRegex
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldNameRegex
	 * Param4.var.enUS: comment
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 * r: valeurDefaut
	 * r.enUS: defaultValue
	 * r: commentaire
	 * r.enUS: comment
	 * r: langueNom
	 * r.enUS: languageName
	 * r: indexerStocker
	 * r.enUS: indexStore
	 */
	protected String indexerStockerSolrRegex(SolrInputDocument doc, String nomChamp, String nomChampRegex, String commentaire) throws Exception {
		return indexerStockerSolrRegex(doc, nomChamp, nomChampRegex, commentaire, null);
	}
	
	/**
	 * Var.enUS: indexStoreSolrRegex
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldNameRegex
	 * Param4.var.enUS: comment
	 * Param5.var.enUS: defaultValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 * r: valeurDefaut
	 * r.enUS: defaultValue
	 * r: commentaire
	 * r.enUS: comment
	 * r: langueNom
	 * r.enUS: languageName
	 */
	protected String indexerStockerSolrRegex(SolrInputDocument doc, String nomChamp, String nomChampRegex, String commentaire, String valeurDefaut) throws Exception {
		String valeurChamp = null;
		if(nomChampRegex != null && commentaire != null) {
			Matcher m = Pattern.compile("^" + nomChampRegex + ":\\s*(.*)", Pattern.MULTILINE).matcher(commentaire);
			if(m.find()) {
				valeurChamp = m.group(1);
			}
		}
		if(StringUtils.isBlank(valeurChamp)) {
			valeurChamp = valeurDefaut;
		}
		if(StringUtils.isNotBlank(valeurChamp)) {
			doc.addField(concat(nomChamp, "_stored_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}

	
	/**
	 * Var.enUS: indexStoreListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerListeSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_strings"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_strings"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreListSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerListeSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValues
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeursChamp
	 * r.enUS: fieldValues
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected List<String> indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNomGlobale)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
				doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	}

	/**
	 * Var.enUS: classDocsAdd
	 * Param1.var.enUS: canonicalName
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeDocs
	 * r.enUS: classDocs
	 */
	protected SolrDocument classeDocsAjouter(String nomCanonique) throws Exception {
		SolrDocument doc = null;
		if(StringUtils.startsWith(nomEnsembleDomaine, nomCanonique)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomGlobale + "_indexed_string:" + ClientUtils.escapeQueryChars(nomCanonique));
			rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
			rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
			QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) {
				doc = listeRecherche.get(0);
				classeDocs.put(nomCanonique, doc);
			}
		}
		return doc;
	}
	
	/**
	 * Var.enUS: searchCanonicalName
	 * Param1.var.enUS: languageName
	 * Param2.var.enUS: canonicalName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeDocsAjouter
	 * r.enUS: classDocsAdd
	 */
	protected String rechercherNomCanonique(String langueNom, String nomCanonique) throws Exception {
		SolrDocument doc = classeDocsAjouter(nomCanonique);
		String val = null;
		if(doc != null) {
			val = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
		}
		if(StringUtils.isEmpty(val)) {
			val = nomCanonique;  
		}
		return val;
	} 

	////////////
	// autres //
	////////////

	/**
	 * Var.enUS: classPartsForSimpleName
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: simpleName
	 * Param3.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classeMotsCles
	 * r.enUS: classKeywords
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: nomSimple
	 * r.enUS: simpleName
	 */
	protected ClasseParts classePartsPourNomSimple(String nomEnsembleDomaine, String nomSimple, String langueNom) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeMotsCles_indexed_strings:" + ClientUtils.escapeQueryChars(langueConfigGlobale.getString(ConfigCles.var_classeNomSimple) + nomSimple));
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, langueNom);
		}
		return classeParts;
	}

	/**
	 * Var.enUS: classPartsWrap
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: Couverture
	 * r.enUS: Wrap
	 */
	protected ClasseParts classePartsCouverture(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_Couverture), langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.search.wrap.Wrap", langueNom);
		return parts;
	}

	/**
	 * Var.enUS: classPartsSiteRequest
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 */
	protected ClasseParts classePartsRequeteSite(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_RequeteSite), langueNom);
	}

	protected ClasseParts classePartsUtilisateurSite(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_UtilisateurSite), langueNom);
	}

	protected ClasseParts classePartsOutilRecherche(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_OutilRecherche), langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.search.tool.SearchTool", langueNom);
		return parts;
	}

	/**
	 * Var.enUS: classPartsApiRequest
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: RequeteApi
	 * r.enUS: ApiRequest
	 */
	protected ClasseParts classePartsRequeteApi(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_RequeteApi), langueNom);
		if(parts == null) {
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.api.ApiRequest", langueNom);
			parts.setEtendGen(true);
		}
		return parts;
	}

	/**
	 * Var.enUS: classPartsModeleBase
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ModeleBase
	 * r.enUS: ModeleBase
	 */
	protected ClasseParts classePartsModeleBase(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_ModeleBase), langueNom);
	}

	/**
	 * Var.enUS: classPartsSearchResult
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ResultatRecherche
	 * r.enUS: SearchResult
	 */
	protected ClasseParts classePartsResultatRecherche(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_ResultatRecherche), langueNom);
		if(parts == null) {
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.search.list.SearchResult", langueNom);
			parts.setEtendGen(true);
		}
		return parts;
	}

	/**
	 * Var.enUS: classPartsAllWriter
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 */
	protected ClasseParts classePartsToutEcrivain(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_ToutEcrivain), langueNom);
		if(parts == null) {
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.writer.AllWriter", langueNom);
			parts.setEtendGen(true);
		}
		return parts;
	}

	/**
	 * Var.enUS: classPartsSearchList
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 */
	protected ClasseParts classePartsListeRecherche(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_ListeRecherche), langueNom);
		if(parts == null) {
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.search.list.SearchList", langueNom);
			parts.setEtendGen(true);
		}
		return parts;
	}

	/**
	 * Var.enUS: classPartsPageLayout
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 */
	protected ClasseParts classePartsMiseEnPage(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_MiseEnPage), langueNom);
	}

	/**
	 * Var.enUS: classPartsPagePart
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 */
	protected ClasseParts classePartsPagePart(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_PagePart), langueNom);
	}

	protected ClasseParts classePartsVerticle(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "Verticle", langueNom);
	}

	protected ClasseParts classePartsBaseApiServiceImpl(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_BaseApiServiceImpl), langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.api.BaseApiServiceImpl", langueNom);
		return parts;
	}

	protected ClasseParts classePartsMailVerticle(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_MailVerticle), langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.verticle.EmailVerticle", langueNom);
		return parts;
	}

	protected ClasseParts classePartsConfigCles(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(ConfigCles.var_ConfigCles), langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.config.ComputateConfigKeys", langueNom);
		return parts;
	}

	protected ClasseParts classePartsPointDeserializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "PointDeserializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.serialize.pgclient.PgClientPointDeserializer", langueNom);
		return parts;
	}

	protected ClasseParts classePartsPointSerializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "PointSerializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.serialize.pgclient.PgClientPointSerializer", langueNom);
		return parts;
	}

	protected ClasseParts classePartsZonedDateTimeDeserializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "ZonedDateTimeDeserializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.search.serialize.ComputateZonedDateTimeDeserializer", langueNom);
		return parts;
	}

	protected ClasseParts classePartsZonedDateTimeSerializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "ZonedDateTimeSerializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.search.serialize.ComputateZonedDateTimeSerializer", langueNom);
		return parts;
	}

	protected ClasseParts classePartsLocalDateDeserializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "LocalDateDeserializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.search.serialize.ComputateLocalDateDeserializer", langueNom);
		return parts;
	}

	protected ClasseParts classePartsLocalDateSerializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "LocalDateSerializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.search.serialize.ComputateLocalDateSerializer", langueNom);
		return parts;
	}

	protected ClasseParts classePartsLocalTimeDeserializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "LocalTimeDeserializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.search.serialize.ComputateLocalTimeDeserializer", langueNom);
		return parts;
	}

	protected ClasseParts classePartsLocalTimeSerializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "LocalTimeSerializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.search.serialize.ComputateLocalTimeSerializer", langueNom);
		return parts;
	}

	/**
	 * Var.enUS: storeRegexComments
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: entityVar
	 * Param4.var.enUS: comment
	 * r: langueNom
	 * r.enUS: languageName
	 * r: varEntite
	 * r.enUS: entityVar
	 * r: commentaire
	 * r.enUS: comment
	 * r: trouve
	 * r.enUS: found
	 * r: langue
	 * r.enUS: language
	 * r: texte
	 * r.enUS: text
	 * r: stockerSolr
	 * r.enUS: storeSolr
	 */
	public String stockerRegexCommentaires(String langueNom, SolrInputDocument doc, String varEntite, String commentaire) throws Exception {
		if(!StringUtils.isEmpty(commentaire)) {
			Matcher m = Pattern.compile("^(enUS|frFR): (.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			StringBuilder b = new StringBuilder();
			
			while(trouve) {
				String langue = m.group(1);
				String texte = m.group(2);
				if(langueNom.equals(langue))
					b.append(texte).append("\n");

				trouve = m.find();
			}
			if(StringUtils.isNotEmpty(b))
				stockerSolr(langueNom, doc, varEntite, b.toString());
		}
		return commentaire;
	}  

	/**
	 */
	public void classePartsGenAjouter(ClasseParts classeParts, String langueNom) {
		if(classePartsGen != null && classeParts != null && !classePartsGen.containsKey(classeParts.nomCanonique(langueNom)) && StringUtils.contains(classeParts.nomCanonique(langueNom), ".") && !StringUtils.contains(classeParts.nomCanonique(langueNom), ","))
			classePartsGen.put(classeParts.nomCanonique(langueNom), classeParts);
	}

	/**
	 */
	public void classePartsGenApiAjouter(ClasseParts classeParts, String langueNom) {
		if(classePartsGenApi != null && classeParts != null && !classePartsGenApi.containsKey(classeParts.nomCanonique(langueNom)) && StringUtils.contains(classeParts.nomCanonique(langueNom), "."))
			classePartsGenApi.put(classeParts.nomCanonique(langueNom), classeParts);
	}

	/**
	 */
	public void classePartsGenPageAjouter(ClasseParts classeParts, String langueNom) {
		if(classePartsGenPage != null && classeParts != null && !classePartsGenPage.containsKey(classeParts.nomCanonique(langueNom)) && StringUtils.contains(classeParts.nomCanonique(langueNom), "."))
			classePartsGenPage.put(classeParts.nomCanonique(langueNom), classeParts);
	}

	/**
	 * Var.enUS: indexClass
	 * Param1.var.enUS: classAbsolutePath
	 * Param2.var.enUS: classDoc
	 * Param3.var.enUS: classLanguageName
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: classeEstAbstrait
	 * r.enUS: classIsAbstract
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: classeNomCanoniqueSuperGeneriqueLangue
	 * r: classeSuperEcrireMethodes
	 * r.enUS: classSuperWriteMethods
	 * r.enUS: classSuperCanonicalNameGenericLanguage
	 * r: classeNomCanoniqueSuperGenerique
	 * r.enUS: classSuperCanonicalNameGeneric
	 * r: classeNomCanoniqueSuper
	 * r.enUS: classSuperCanonicalName
	 * r: classeNomCanoniqueGenLangue
	 * r.enUS: classCanonicalNameGenLanguage
	 * r: classeNomCanoniqueGen
	 * r.enUS: classCanonicalNameGen
	 * r: classeNomCanoniqueSuperDoc
	 * r.enUS: classSuperCanonicalNameDoc
	 * r: classeNomCanoniqueLangue
	 * r.enUS: classCanonicalNameLanguage
	 * r: classeNomCanoniquePageLangue
	 * r.enUS: classCanonicalNamePageLanguage
	 * r: classeNomCanoniqueApiLangue
	 * r.enUS: classCanonicalNameApiLanguage
	 * r: classeNomCanoniquePageGenLangue
	 * r.enUS: classCanonicalNamePageGenLanguage
	 * r: classeNomCanoniqueApiGenLangue
	 * r.enUS: classCanonicalNameApiGenLanguage
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeNomSimpleSuperGeneriqueLangue
	 * r.enUS: classSuperSimpleNameGenericLanguage
	 * r: classeNomSimpleSuperGenerique
	 * r.enUS: classSuperSimpleNameGeneric
	 * r: classeNomSimpleSuper
	 * r.enUS: classSuperSimpleName
	 * r: classeNomSimpleGenLangue
	 * r.enUS: classSimpleNameGenLanguage
	 * r: classeNomSimpleGen
	 * r.enUS: classSimpleNameGen
	 * r: classeNomSimpleSuperDoc
	 * r.enUS: classSuperSimpleNameDoc
	 * r: classeNomSimpleGenLangue
	 * r.enUS: classSimpleNameGenLanguage
	 * r: classeNomSimpleLangue
	 * r.enUS: classSimpleNameLanguage
	 * r: classeNomSimplePageLangue
	 * r.enUS: classSimpleNamePageLanguage
	 * r: classeNomSimpleGenPageLangue
	 * r.enUS: classSimpleNameGenPageLanguage
	 * r: classeNomSimpleGenApiServiceImplLangue
	 * r.enUS: classSimpleNameGenApiServiceImplLanguage
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: bricoleur
	 * r.enUS: builder
	 * r: classeQdoxSuper
	 * r.enUS: classSuperQdox
	 * r: classeQdoxRetour
	 * r.enUS: classReturnQdox
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * r:  indexerStockerSolr
	 * r.enUS: indexStoreSolr
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeNomCompletSuperGeneriqueLangue
	 * r.enUS: classSuperCompleteNameGenericLanguage
	 * r: classeNomCompletSuperGenerique
	 * r.enUS: classSuperCompleteNameGeneric
	 * r: classeNomCompletSuperLangue
	 * r.enUS: classSuperCompleteNameLanguage
	 * r: classeNomCompletSuper
	 * r.enUS: classSuperCompleteName
	 * r: stockerRegexCommentaires
	 * r.enUS: storeRegexComments
	 * r: cheminSrcMainJavaLangue
	 * r.enUS: srcMainJavaPathLanguage
	 * r: cheminSrcGenJavaLangue
	 * r.enUS: srcGenJavaPathLanguage
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcMainResources
	 * r.enUS: srcMainResourcesPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: commentaire
	 * r.enUS: comment
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: classeNomEnsembleLangue
	 * r.enUS: classPackageNameLanguage
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeCheminRepertoireGenLangue
	 * r.enUS: classDirPathGenLanguage
	 * r: classeCheminRepertoireGen
	 * r.enUS: classDirPathGen
	 * r: classeCheminRepertoireLangue
	 * r.enUS: classDirPathLanguage
	 * r: classeCheminRepertoire
	 * r.enUS: classDirPath
	 * r: classeCheminGenLangue
	 * r.enUS: classPathGenLanguage
	 * r: classeCheminGen
	 * r.enUS: classPathGen
	 * r: classeCheminAbsolu
	 * r.enUS: classPathAbsolute
	 * r: classeCheminLangue
	 * r.enUS: classPathLanguage
	 * r: classeChemin
	 * r.enUS: classPath
	 * r: peuplerClassesSuperQdoxInterfacesEtMoi
	 * r.enUS: populateQdoxSuperClassesInterfacesAndMe
	 * r: classeCle
	 * r.enUS: classKey
	 * r: modifieeDate
	 * r.enUS: modifiedDate
	 * r: classesSuperQdoxInterfacesEtMoi
	 * r.enUS: qdoxSuperClassesInterfacesAndMe
	 * r: classesSuperQdoxEtInterfaces
	 * r.enUS: qdoxSuperClassesAndInterfaces
	 * r: classesSuperQdoxEtMoiSansGen
	 * r.enUS: qdoxSuperClassesAndMeWithoutGen
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: classesSuperQdox
	 * r.enUS: qdoxSuperClasses
	 * r: modifiee
	 * r.enUS: modified
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: classeAutresLangues
	 * r.enUS: classOtherLanguages
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: siteCheminVertxLangue
	 * r.enUS: sitePathVertxLanguage
	 * r: siteCheminLangue
	 * r.enUS: sitePathLanguage
	 * r: siteCheminsVertx
	 * r.enUS: sitePathsVertx
	 * r: siteChemins
	 * r.enUS: sitePaths
	 * r: stockerSolr
	 * r.enUS: storeSolr
	 * r: rechercherNomCanonique
	 * r.enUS: searchCanonicalName
	 * r: regexRemplacerTout
	 * r.enUS: regexReplaceAll
	 * r: classeDocClone
	 * r.enUS: classDocClone
	 * r: partNumero
	 * r.enUS: partNumber
	 * r: classeImportationClassePartsLangue
	 * r.enUS: classImportClassPartsLanguage
	 * r: classeImportationClasseParts
	 * r.enUS: classeImportClassParts
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * r: classeImportations
	 * r.enUS: classImports
	 * r: indexerStockerListeSolr
	 * r.enUS: indexStoreListSolr
	 * r: membresQdox
	 * r.enUS: membersQdox
	 * r: membreQdox
	 * r.enUS: memberQdox
	 * r: champDoc
	 * r.enUS: fieldDoc
	 * r: champQdox
	 * r.enUS: fieldQdox
	 * r: champCommentaire
	 * r.enUS: fieldComment
	 * r: champCle
	 * r.enUS: fieldKey
	 * r: champCodeSourceLangue
	 * r.enUS: fieldSourceCodeLanguage
	 * r: champCodeSource
	 * r.enUS: fieldSourceCode
	 * r: partEstChamp
	 * r.enUS: partIsField
	 * r: champEstPublic
	 * r.enUS: fieldIsPublic
	 * r: champEstProtege
	 * r.enUS: fieldIsProtected
	 * r: champEstPrive
	 * r.enUS: fieldIsPrivate
	 * r: champEstStatique
	 * r.enUS: fieldIsStatic
	 * r: champEstFinale
	 * r.enUS: fieldIsFinal
	 * r: champEstAbstrait
	 * r.enUS: fieldIsAbstract
	 * r: champEstNatif
	 * r.enUS: fieldIsNative
	 * r: annotationsLangue
	 * r.enUS: annotationsLanguage
	 * r: annotations
	 * r.enUS: annotations
	 * r: champEstTest
	 * r.enUS: fieldIsTest
	 * r: champEstSubstitue
	 * r.enUS: fieldIsOverride
	 * r: champAnnotationLangue
	 * r.enUS: fieldAnnotationLanguage
	 * r: champClassePartsLangue
	 * r.enUS: fieldClassPartsLanguage
	 * r: methodeString
	 * r.enUS: methodString
	 * r: methodeStringLangue
	 * r.enUS: methodStringLanguage
	 * r: entiteString
	 * r.enUS: entityString
	 * r: entiteStringLangue
	 * r.enUS: entityStringLanguage
	 * r: champStringLangue
	 * r.enUS: fieldStringLanguage
	 * r: champString
	 * r.enUS: fieldString
	 * r: champClasseParts
	 * r.enUS: fieldClassParts
	 * r: champVarLangue
	 * r.enUS: fieldVarLanguage
	 * r: champVar
	 * r.enUS: fieldVar
	 * r: champNomCanoniqueComplet
	 * r.enUS: fieldCanonicalNameComplete
	 * r: champNomSimpleComplet
	 * r.enUS: fieldSimpleNameComplete
	 * r: champTraduire
	 * r.enUS: fieldTranslate
	 * r: indexerListeSolr
	 * r.enUS: indexListSolr
	 * r: stockerListeSolr
	 * r.enUS: storeListSolr
	 * r: constructeurDoc
	 * r.enUS: constructorDoc
	 * r: constructeurQdox
	 * r.enUS: constructorQdox
	 * r: constructeurCommentaire
	 * r.enUS: constructorComment
	 * r: constructeurCle
	 * r.enUS: constructorKey
	 * r: constructeurCodeSourceLangue
	 * r.enUS: constructorSourceCodeLanguage
	 * r: constructeurCodeSource
	 * r.enUS: constructorSourceCode
	 * r: constructeurEstPublic
	 * r.enUS: constructorIsPublic
	 * r: constructeurEstProtege
	 * r.enUS: constructorIsProtected
	 * r: constructeurEstPrive
	 * r.enUS: constructorIsPrivate
	 * r: constructeurEstStatique
	 * r.enUS: constructorIsStatic
	 * r: constructeurEstFinale
	 * r.enUS: constructorIsFinal
	 * r: constructeurEstAbstrait
	 * r.enUS: constructorIsAbstract
	 * r: constructeurEstNatif
	 * r.enUS: constructorIsNative
	 * r: constructeurAnnotations
	 * r.enUS: constructorAnnotations
	 * r: constructeurAnnotationLangue
	 * r.enUS: constructorAnnotationLanguage
	 * r: constructeurParamsQdox
	 * r.enUS: constructorParamsQdox
	 * r: constructeurExceptionsQdox
	 * r.enUS: constructorExceptionsQdox
	 * r: constructeurParamNum
	 * r.enUS: constructorParamNum
	 * r: constructeurParamQdox
	 * r.enUS: constructorParamQdox
	 * r: constructeurParamsVarLangue
	 * r.enUS: constructorParamsVarLanguage
	 * r: constructeurParamVarLangue
	 * r.enUS: constructorParamVarLanguage
	 * r: constructeurParamsVarLangue
	 * r.enUS: constructorParamsVar
	 * r: constructeurParamsNomSimpleComplet
	 * r.enUS: constructorParamsSimpleNameComplete
	 * r: constructeurParamsVar
	 * r.enUS: constructorParamsVar
	 * r: constructeurParamsArgsVariables
	 * r.enUS: constructorParamsVariableArgs
	 * r: constructeurParamVarLangue
	 * r.enUS: constructorParamVar
	 * r: constructeurParamClassePartsLangue
	 * r.enUS: constructorParamClassPartsLanguage
	 * r: constructeurParamClasseParts
	 * r.enUS: constructorParamClassParts
	 * r: constructeurParamNomSimpleComplet
	 * r.enUS: constructorParamSimpleNameComplete
	 * r: constructeurParamVar
	 * r.enUS: constructeurParamVar
	 * r: constructeurParamArgsVariables
	 * r.enUS: constructorParamVariableArgs
	 * r: constructeurAnnotationBlocCode
	 * r.enUS: constructorAnnotationCodeBlock
	 * r: constructeurExceptionQdox
	 * r.enUS: constructorExceptionQdox
	 * r: constructeurExceptionNomSimpleComplet
	 * r.enUS: constructorExceptionSimpleNameComplete
	 * r: constructeurAnnotation
	 * r.enUS: constructorAnnotation
	 * r: partEstConstructeur
	 * r.enUS: partIsConstructor
	 * r: methodeVarLangue
	 * r.enUS: methodVarLanguage
	 * r: methodeVar
	 * r.enUS: methodVar
	 * r: methodeDoc
	 * r.enUS: methodDoc
	 * r: methodeQdox
	 * r.enUS: methodQdox
	 * r: methodeCommentaire
	 * r.enUS: methodComment
	 * r: methodeCle
	 * r.enUS: methodKey
	 * r: methodeCodeSourceLangue
	 * r.enUS: methodSourceCodeLanguage
	 * r: methodeCodeSource
	 * r.enUS: methodSourceCode
	 * r: methodeEstPublic
	 * r.enUS: methodIsPublic
	 * r: methodeEstProtege
	 * r.enUS: methodIsProtected
	 * r: methodeEstPrive
	 * r.enUS: methodIsPrivate
	 * r: methodeEstStatique
	 * r.enUS: methodIsStatic
	 * r: methodeEstFinale
	 * r.enUS: methodIsFinal
	 * r: methodeEstAbstrait
	 * r.enUS: methodIsAbstract
	 * r: methodeEstNatif
	 * r.enUS: methodIsNative
	 * r: methodeAnnotations
	 * r.enUS: methodAnnotations
	 * r: methodeAnnotationLangue
	 * r.enUS: methodAnnotationLanguage
	 * r: methodeParamsClassePart
	 * r.enUS: methodParamsClassPart
	 * r: methodeParamsQdox
	 * r.enUS: methodParamsQdox
	 * r: methodeExceptionsQdox
	 * r.enUS: methodExceptionsQdox
	 * r: methodeParamNum
	 * r.enUS: methodParamNum
	 * r: methodeParamQdox
	 * r.enUS: methodParamQdox
	 * r: methodeParamVarLangue
	 * r.enUS: methodParamVarLanguage
	 * r: methodeParamsVar
	 * r.enUS: methodParamsVar
	 * r: methodeParamClassePartsLangue
	 * r.enUS: methodParamClassPartsLanguage
	 * r: methodeParamsNomSimpleComplet
	 * r.enUS: methodParamsSimpleNameComplete
	 * r: methodeParamNomSimpleComplet
	 * r.enUS: methodParamSimpleNameComplete
	 * r: methodeParamsArgsVariables
	 * r.enUS: methodParamsVariableArgs
	 * r: methodeParamVar
	 * r.enUS: methodeParamVar
	 * r: methodeParamArgsVariables
	 * r.enUS: methodParamVariableArgs
	 * r: methodeAnnotationBlocCode
	 * r.enUS: methodAnnotationCodeBlock
	 * r: methodeExceptionQdox
	 * r.enUS: methodExceptionQdox
	 * r: methodeExceptionNomSimpleComplet
	 * r.enUS: methodExceptionSimpleNameComplete
	 * r: methodeAnnotation
	 * r.enUS: methodAnnotation
	 * r: partEstMethode
	 * r.enUS: partIsMethod
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: methodeRetourNomSimpleComplet
	 * r.enUS: methodReturnSimpleNameComplete
	 * r: methodeRetourClassePartsLangue
	 * r.enUS: methodReturnClassPartsLanguage
	 * r: methodeRetourClasseParts
	 * r.enUS: methodReturnClassParts
	 * r: methodeEstVide
	 * r.enUS: methodIsVoid
	 * r: methodeNomSimpleRetourComplet
	 * r.enUS: methodReturnSimpleNameComplete
	 * r: methodeEstTest
	 * r.enUS: methodIsTest
	 * r: methodeEstSubstitue
	 * r.enUS: methodIsOverride
	 * r: methodeExceptionsNomSimpleComplet
	 * r.enUS: methodExceptionsSimpleNameComplete
	 * r: methodeExceptionNomSimpleComplet
	 * r.enUS: methodExceptionSimpleNameComplete
	 * r: regexCle
	 * r.enUS: regexKey
	 * r: regexValeur
	 * r.enUS: regexValue
	 * r: qSupprimer
	 * r.enUS: qDelete
	 * r: nomCanoniqueGenerique
	 * r.enUS: canonicalNameGeneric
	 * r: nomCanoniqueComplet
	 * r.enUS: canonicalNameComplete
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: nomSimpleGenerique
	 * r.enUS: simpleNameGeneric
	 * r: nomSimpleComplet
	 * r.enUS: simpleNameComplete
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: regexListe
	 * r.enUS: regexList
	 * r: remplacerClesLangue
	 * r.enUS: replaceKeysLanguage
	 * r: remplacerValeursLangue
	 * r.enUS: replaceValuesLanguage
	 * r: annotationsLangue
	 * r.enUS: annotationsLanguage
	 * r: methodeParamsTypeNom
	 * r.enUS: methodParamsTypeName
	 * r: classeSuperParamsType
	 * r.enUS: classSuperTypeParameters
	 * r: classeSuperParamTypeNom
	 * r.enUS: classSuperTypeParameterName
	 * r: classeSuperParamType
	 * r.enUS: classSuperTypeParameter
	 * r: classeSuperParamsTypeNom
	 * r.enUS: classSuperTypeParameterNames
	 * r: classeParamsTypeNom
	 * r.enUS: classTypeParameterNames
	 * r: classeParamTypeNom
	 * r.enUS: classTypeParameterName
	 * r: classeParamsType
	 * r.enUS: classTypeParameters
	 * r: classeParamType
	 * r.enUS: classTypeParameter
	 * r: methodeParamsTypeNom
	 * r.enUS: methodParamsTypeName
	 * r: methodeParamsType
	 * r.enUS: methodParamsType
	 * r: methodeParamType
	 * r.enUS: methodParamType
	 * r: classePartsSuperLangue
	 * r.enUS: classSuperPartsLanguage
	 * r: regexTrouve
	 * r.enUS: regexFound
	 * r: siteCheminVertx
	 * r.enUS: sitePathVertx
	 * r: siteChemin
	 * r.enUS: sitePath
	 * r: siteNom
	 * r.enUS: siteName
	 * r: classeMethodesEcrites
	 * r.enUS: classMethodsWritten
	 * r: classeBaseEtendGen
	 * r.enUS: baseClassExtendsGen
	 * r: etendGen
	 * r.enUS: extendsGen
	 * r: classeEstBase
	 * r.enUS: classIsBase
	 * r: classeEtendBase
	 * r.enUS: classExtendsBase
	 * r: classeContientRequeteSite
	 * r.enUS: classContainsSiteRequest
	 * r: classeContientCouverture
	 * r.enUS: classContainsWrap
	 * r: classePartsRequeteSite
	 * r.enUS: classPartsSiteRequest
	 * r: classeModele
	 * r.enUS: classModel
	 * r: classeInitLoin
	 * r.enUS: classInitDeep
	 * r: classePartsGenAjouter
	 * r.enUS: classPartsGenAdd
	 * r: classePartsGenPageAjouter
	 * r.enUS: classPartsGenPageAdd
	 * r: classePartsGenApiAjouter
	 * r.enUS: classPartsGenApiAdd
	 * r: classePartsUtilisateurSite
	 * r.enUS: classPartsSiteUser
	 * r: classePartsRequeteApi
	 * r.enUS: classPartsApiRequest
	 * r: classePartsModeleBase
	 * r.enUS: classPartsModeleBase
	 * r: classePartsResultatRecherche
	 * r.enUS: classPartsSearchResult
	 * r: classePartsSolrInputDocument
	 * r.enUS: classPartsSolrInputDocument
	 * r: classePartsSolrClient
	 * r.enUS: classPartsSolrClient
	 * r: classePartsToutEcrivain
	 * r.enUS: classPartsAllWriter
	 * r: classePartsListeRecherche
	 * r.enUS: classPartsSearchList
	 * r: classePartsMiseEnPage
	 * r.enUS: classPartsPageLayout
	 * r: classeImplementsNomSimpleCompletLangue
	 * r.enUS: classImplementsSimpleNameCompleteLanguage
	 * r: classeImplementsNomSimpleComplet
	 * r.enUS: classImplementsSimpleNameComplete
	 * r: classePageLangueNom
	 * r.enUS: classPageLanguageName
	 * 
	 * r: classeValsRecherche
	 * r.enUS: classValsSearch
	 * r: classeValsTrouves
	 * r.enUS: classValsFound
	 * r: classeValsVar
	 * r.enUS: classValsVar
	 * r: classeValsLangue
	 * r.enUS: classValsLanguage
	 * r: classeValsValeur
	 * r.enUS: classValsValue
	 * r: classeValVar
	 * r.enUS: classValVar
	 * r: classeValLangue
	 * r.enUS: classValLanguage
	 * r: classeValValeur
	 * r.enUS: classValValue
	 * 
	 * r: entiteValsRecherche
	 * r.enUS: entityValsSearch
	 * r: entiteValsTrouves
	 * r.enUS: entityValsFound
	 * r: entiteValsVar
	 * r.enUS: entityValsVar
	 * r: entiteValsLangue
	 * r.enUS: entityValsLanguage
	 * r: entiteValsValeur
	 * r.enUS: entityValsValue
	 * r: entiteValVar
	 * r.enUS: entityValVar
	 * r: entiteValLangue
	 * r.enUS: entityValLanguage
	 * r: entiteValValeur
	 * r.enUS: entityValValue
	 * 
	 * r: methodeValsRecherche
	 * r.enUS: methodValsSearch
	 * r: methodeValsTrouves
	 * r.enUS: methodValsFound
	 * r: methodeValsVar
	 * r.enUS: methodValsVar
	 * r: methodeValsLangue
	 * r.enUS: methodValsLanguage
	 * r: methodeValsValeur
	 * r.enUS: methodValsValue
	 * r: methodeValVar
	 * r.enUS: methodValVar
	 * r: methodeValLangue
	 * r.enUS: methodValLanguage
	 * r: methodeValValeur
	 * r.enUS: methodValValue
	 * 
	 * r: classeRolesRecherche
	 * r.enUS: classRolesSearch
	 * r: classeRolesTrouvesActuel
	 * r.enUS: classRolesFoundCurrent
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRolesVar
	 * r.enUS: classRolesVar
	 * r: classeRolesLangue
	 * r.enUS: classRolesLanguage
	 * r: classeRolesRoleValeur
	 * r.enUS: classRolesValue
	 * r: classeRoleLangue
	 * r.enUS: classRoleLanguage
	 * r: classeRoleValeur
	 * r.enUS: classRoleValue
	 * r: classeRoles
	 * r.enUS: classRoles
	 * 
	 * r: classeFiltresRecherche
	 * r.enUS: classFiltersSearch
	 * r: classeFiltresTrouvesActuel
	 * r.enUS: classFiltersFoundCurrent
	 * r: classeFiltresTrouves
	 * r.enUS: classFiltersFound
	 * r: classeFiltresVar
	 * r.enUS: classFiltersVar
	 * r: classeFiltresLangue
	 * r.enUS: classFiltersLanguage
	 * r: classeFiltresFiltreValeur
	 * r.enUS: classFiltersValue
	 * r: classeFiltreValeur
	 * r.enUS: classFilterValue
	 * r: classeFiltres
	 * r.enUS: classFilters
	 * 
	 * r: classeMotsClesRecherche
	 * r.enUS: classKeywordsSearch
	 * r: classeMotsClesTrouvesActuel
	 * r.enUS: classKeywordsFoundActual
	 * r: classeMotsClesTrouves
	 * r.enUS: classKeywordsFound
	 * r: classeMotsCles
	 * r.enUS: classKeywords
	 * r: classeMotCleValeur
	 * r.enUS: classKeywordValue
	 * r: entiteMotsClesRecherche
	 * r.enUS: entityKeywordsSearch
	 * r: entiteMotsClesTrouvesActuel
	 * r.enUS: entityKeywordsFoundCurrent
	 * r: entiteMotsClesTrouves
	 * r.enUS: entityKeywordsFound
	 * r: entiteMotsClesVar
	 * r.enUS: entityKeywordsVar
	 * r: entiteMotsClesLangue
	 * r.enUS: entityKeywordsLanguage
	 * r: entiteMotsClesMotCleValeur
	 * r.enUS: entityKeywordsValue
	 * r: entiteMotCleLangue
	 * r.enUS: entityKeywordLanguage
	 * r: entiteMotCleValeur
	 * r.enUS: entityKeywordValue
	 * r: entiteMotsCles
	 * r.enUS: entityKeywords
	 * 
	 * r: classeMapRecherche
	 * r.enUS: classMapSearch
	 * r: classeMapTrouveActuel
	 * r.enUS: classMapFoundActual
	 * r: classeMapCle
	 * r.enUS: classMapKey
	 * r: classeMapValeur
	 * r.enUS: classMapValue
	 * r: classeMap
	 * r.enUS: classMap
	 * r: entiteMapRecherche
	 * r.enUS: entityMapSearch
	 * r: entiteMapTrouveActuel
	 * r.enUS: entityMapFoundCurrent
	 * r: entiteMapTrouve
	 * r.enUS: entityMapFound
	 * r: entiteMapVar
	 * r.enUS: entityMapVar
	 * r: entiteMapLangue
	 * r.enUS: entityMapLanguage
	 * r: entiteMapCle
	 * r.enUS: entityMapKey
	 * r: entiteMapMapValeur
	 * r.enUS: entityMapValue
	 * r: entiteMapLangue
	 * r.enUS: entityMapLanguage
	 * r: entiteMapValeur
	 * r.enUS: entityMapValue
	 * r: entiteMap
	 * r.enUS: entityMap
	 * 
	 * r: classePartsSuperGeneriqueLangue
	 * r.enUS: classPartsSuperGenericLanguage
	 * r: classePartsSuperGenerique
	 * r.enUS: classPartsSuperGeneric
	 * 
	 * r: apiMethode
	 * r.enUS: apiMethod
	 * r: classeApiMotCleMethode
	 * r.enUS: classApiKeywordMethod
	 * r: classeApiMotCle
	 * r.enUS: classApiKeyword
	 * r: ApiMotCle
	 * r.enUS: ApiKeyword
	 * r: classeApiTypeMedia
	 * r.enUS: classApiMediaType
	 * r: apiMotCle
	 * r.enUS: apiKeyword
	 * r: apiTypeMedia
	 * r.enUS: apiMediaType
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: apiUriRecherche
	 * r.enUS: apiUriSearch
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: classeApiUriLangue
	 * r.enUS: classApiUriLanguage
	 * 
	 * r: classeImportClassParts
	 * r.enUS: classImportClassParts
	 * r: classeImportation
	 * r.enUS: classImport
	 * r: constructeurParamVar
	 * r.enUS: constructorParamVar
	 * r: ignorer
	 * r.enUS: ignore
	 * r: methodeClasseQdoxRetour
	 * r.enUS: methodClassQdoxReturn
	 * r: methodeNomCanoniqueRetourComplet
	 * r.enUS: methodCanonicalNameReturnComplete
	 * r: methodeNomCanoniqueRetour
	 * r.enUS: methodCanonicalNameReturn
	 * r: est Entite. 
	 * r.enUS: is Entity. 
	 * r: entiteDoc
	 * r.enUS: entityDoc
	 * r: entiteVarCapitalise
	 * r.enUS: entityVarCapitalized
	 * r: entiteVarCouverture
	 * r.enUS: entityVarWrap
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: entiteCommentaire
	 * r.enUS: entityComment
	 * r: entiteClasseQdox
	 * r.enUS: entityClassQdox
	 * r: entiteClasseParts
	 * r.enUS: entityClassParts
	 * r: entiteCouverture
	 * r.enUS: entityWrap
	 * r: entiteNomsCanoniquesSuperEtMoiSansGen
	 * r.enUS: entityCanonicalNamesSuperAndMeWithoutGen
	 * r: classePartsGenerique
	 * r.enUS: classPartsGeneric
	 * r: documentSolr
	 * r.enUS: solrDocument
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: entiteInitialise
	 * r.enUS: entityInitialized
	 * r: entiteNomCanoniqueCompletGenerique
	 * r.enUS: entityCanonicalNameCompleteGeneric
	 * r: entiteNomCanoniqueComplet
	 * r.enUS: entityCanonicalNameComplete
	 * r: entiteNomCanoniqueGenerique
	 * r.enUS: entityCanonicalNameGeneric
	 * r: entiteNomCanonique
	 * r.enUS: entityCanonicalName
	 * r: entiteNomSimpleCompletGenerique
	 * r.enUS: entitySimpleNameCompleteGeneric
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: entiteNomSimpleGenerique
	 * r.enUS: entitySimpleNameGeneric
	 * r: entiteNomSimple
	 * r.enUS: entitySimpleName
	 * r: entiteNomCompletGenerique
	 * r.enUS: entityCompleteNameGeneric
	 * r: entiteSetter
	 * r.enUS: entitySetter
	 * r: entiteDefinir
	 * r.enUS: entityDefined
	 * r: entiteClasseSuperQdox
	 * r.enUS: entitySuperClassQdox
	 * r: entiteInitLoin
	 * r.enUS: entityInitDeep
	 * r: entiteParamVar
	 * r.enUS: entityParamVar
	 * r: fqClassesSuperEtMoi
	 * r.enUS: fqSuperClassesAndMe
	 * r: rechercheSolrMethodeAvant
	 * r.enUS: solrSearchMethodBefore
	 * r: reponseRechercheMethodeAvant
	 * r.enUS: searchResponseMethodBefore
	 * r: listeRechercheMethodeAvant
	 * r.enUS: searchListMethodBefore
	 * r: rechercheSolrMethodeApres
	 * r.enUS: solrSearchMethodAfter
	 * r: reponseRechercheMethodeApres
	 * r.enUS: searchResponseMethodAfter
	 * r: listeRechercheMethodeApres
	 * r.enUS: searchListMethodAfter
	 * r: fqMethodeAvant
	 * r.enUS: fqMethodBefore
	 * r: fqMethodeApres
	 * r.enUS: fqMethodAfter
	 * r: methodVarActuel
	 * r.enUS: methodVarCurrent
	 * r: methodeClasseNomCanonique
	 * r.enUS: methodClassCanonicalName
	 * r: entiteMethodesAvantVisibilite
	 * r.enUS: entityMethodsBeforeVisibility
	 * r: entiteMethodesAvantVar
	 * r.enUS: entityMethodsBeforeVar
	 * r: entiteMethodesAvantParamVar
	 * r.enUS: entityMethodsBeforeParamVar
	 * r: entiteMethodesAvantParamNomSimple
	 * r.enUS: entityMethodsBeforeSimpleName
	 * r: entiteMethodesAvantNomParam
	 * r.enUS: entityMethodsBeforeParamName
	 * r: entiteMethodesAvantEcrire
	 * r.enUS: entityMethodsBeforeWrite
	 * r: entiteMethodesApresVisibilite
	 * r.enUS: entityMethodsAfterVisibility
	 * r: entiteMethodesApresVar
	 * r.enUS: entityMethodsAfterVar
	 * r: entiteMethodesApresParamVar
	 * r.enUS: entityMethodsAfterParamVar
	 * r: entiteMethodesApresParamNomSimple
	 * r.enUS: entityMethodsAfterSimpleName
	 * r: entiteMethodesApresNomParam
	 * r.enUS: entityMethodsAfterParamName
	 * r: entiteMethodesApresEcrire
	 * r.enUS: entityMethodsAfterWrite
	 * r: methodeParamsNomCanonique
	 * r.enUS: methodParamCanonicalNames
	 * r: methodeParamNomCanonique
	 * r.enUS: methodParamCanonicalName
	 * r: entiteMethodesAvant
	 * r.enUS: entityMethodsBefore
	 * r: entiteMethodesApres
	 * r.enUS: entityMethodsAfter
	 * r: entiteOptionsRecherche
	 * r.enUS: entityOptionsSearch
	 * r: entiteOptionsTrouves
	 * r.enUS: entityOptionsFound
	 * r: entiteOptionsVar
	 * r.enUS: entityOptionsVar
	 * r: entiteOptionsLangue
	 * r.enUS: entityOptionsLanguage
	 * r: entiteOptionsDescription
	 * r.enUS: entityOptionsDescription
	 * r: entiteOptionsValeurs
	 * r.enUS: entityOptionsValues
	 * r: entiteOptionVar
	 * r.enUS: entityOptionVar
	 * r: entiteOptionLangue
	 * r.enUS: entityOptionLanguage
	 * r: entiteOptionDescription
	 * r.enUS: entityOptionDescription
	 * r: entiteOptionValeurs
	 * r.enUS: entityOptionValues
	 * r: entiteOptions
	 * r.enUS: entityOptions
	 * r: entiteIndexeOuStocke
	 * r.enUS: entityIndexedOrStored
	 * r: entiteExact
	 * r.enUS: entityExact
	 * r: entiteClePrimaire
	 * r.enUS: entityPrimaryKey
	 * r: entiteCleUnique
	 * r.enUS: entityUniqueKey
	 * r: entiteCrypte
	 * r.enUS: entityEncrypted
	 * r: entiteSuggere
	 * r.enUS: entitySuggested
	 * r: entiteSauvegarde
	 * r.enUS: entitySaved
	 * r: entiteIndexe
	 * r.enUS: entityIndexed
	 * r: entiteIncremente
	 * r.enUS: entityIncremented
	 * r: entiteStocke
	 * r.enUS: entityStored
	 * r: entiteTexte
	 * r.enUS: entityText
	 * r: entiteLangue
	 * r.enUS: entityLanguage
	 * r: entiteNomAffichage
	 * r.enUS: entityDisplayName
	 * r: entiteDescription
	 * r.enUS: entityDescription
	 * r: entiteLongeurMin
	 * r.enUS: entityMinLength
	 * r: entiteLongeurMax
	 * r.enUS: entityMaxLength
	 * r: entiteVarApi
	 * r.enUS: entityVarApi
	 * r: EnumNomSimple
	 * r.enUS: EnumSimpleName
	 * r: entiteMin
	 * r.enUS: entityMin
	 * r: entiteMax
	 * r.enUS: entityMax
	 * r: entiteOptionnel
	 * r.enUS: entityOptional
	 * r: entiteIgnorer
	 * r.enUS: entityIgnored
	 * r: entiteDeclarer
	 * r.enUS: entityDeclared
	 * r: entiteRechercher
	 * r.enUS: entitySearch
	 * r: entiteAjouter
	 * r.enUS: entityAdd
	 * r: entiteSupprimer
	 * r.enUS: entityDelete
	 * r: entiteRecharger
	 * r.enUS: entityRefresh
	 * r: entiteMultiligne
	 * r.enUS: entityMultiline
	 * r: entiteSignature
	 * r.enUS: entitySignature
	 * r: entiteCles
	 * r.enUS: entityKeys
	 * r: entiteAttribuerNomCanonique
	 * r.enUS: entityAttributeCanonicalName
	 * r: entiteAttribuerNomSimple
	 * r.enUS: entityAttributeSimpleName
	 * r: entiteAttribuerTrisVar
	 * r.enUS: entityAttributeSortsVar
	 * r: entiteAttribuerVarSuggere
	 * r.enUS: entityAttributeVarSuggest
	 * r: entiteAttribuerVarUrlId
	 * r.enUS: entityAttributeVarUrlId
	 * r: entiteAttribuerVarUrlPk
	 * r.enUS: entityAttributeVarUrlPk
	 * r: entiteAttribuerVarId
	 * r.enUS: entityAttributeVarId
	 * r: entiteAttribuerVarTitre
	 * r.enUS: entityAttributeVarTitle
	 * r: entiteAttribuerVarDescription
	 * r.enUS: entityAttributeVarDescription
	 * r: entiteAttribuerVarImageUrl
	 * r.enUS: entityAttributeVarImageUrl
	 * r: entiteAttribuerVar
	 * r.enUS: entityAttributeVar
	 * r: entiteAttribuer
	 * r.enUS: entityAttribute
	 * r: entiteHtmlTooltip
	 * r.enUS: entityHtmlTooltip
	 * r: HtmlLigne
	 * r.enUS: HtmlRow
	 * r: HtmlColonne
	 * r.enUS: HtmlColumn
	 * r: HtmlCellule
	 * r.enUS: HtmlCell
	 * r: entiteHtml
	 * r.enUS: entityHtml
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: entiteAnnotations
	 * r.enuS: entityAnnotations
	 * r: entiteCle
	 * r.enUS: entityKey
	 * r: entiteCodeSource
	 * r.enUS: entitySourceCode
	 * r: entiteListeNomSimpleVertxJson
	 * r.enUS: entityListSimpleNameVertxJson
	 * r: entiteListeNomCanoniqueVertxJson
	 * r.enUS: entityListCanonicalNameVertxJson
	 * r: entiteSolrNomCanonique
	 * r.enUS: entitySolrCanonicalName
	 * r: entiteSolrNomSimple
	 * r.enUS: entitySolrSimpleName
	 * r: entiteSuffixeType
	 * r.enUS: entityTypeSuffix
	 * r: entiteTypeJson
	 * r.enUS: entityJsonType
	 * r: entiteAttribuerTypeJson
	 * r.enUS: entityAttributeJsonType
	 * r: entiteFormatJson
	 * r.enUS: entityJsonFormat
	 * r: entiteListeTypeJson
	 * r.enUS: entityListJsonType
	 * r: entiteAnnotationLangue
	 * r.enUS: entityAnnotationLanguage
	 * r: entiteAnnotations
	 * r.enUS: entityAnnotations
	 * r: classePartsBase
	 * r.enUS: classPartsBase
	 * r: classeApi
	 * r.enUS: classApi
	 * r: classePage
	 * r.enUS: classPage
	 * r: classePartsTest
	 * r.enUS: classPartsTest
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: classeIndexe
	 * r.enUS: classIndexed
	 * r: classeImage
	 * r.enUS: classImage
	 * r: classeVarUrlId
	 * r.enUS: classVarUrlId
	 * r: classeVarUrlPk
	 * r.enUS: classVarUrlPk
	 * r: classeVarId
	 * r.enUS: classVarId
	 * r: classeVarTitre
	 * r.enUS: classVarTitle
	 * r: classeVarDescription
	 * r.enUS: classVarDescription
	 * r: classeVarCree
	 * r.enUS: classVarCree
	 * r: classeVarModifie
	 * r.enUS: classVarModified
	 * r: classeVarImageUrl
	 * r.enUS: classVarImageUrl
	 * r: classeVarSuggere
	 * r.enUS: classVarSuggest
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: classeVarCleUnique
	 * r.enUS: classVarUniqueKey
	 * r: entiteEtendPagePart
	 * r.enUS: entityExtendsPagePart
	 * r: docClasse
	 * r.enUS: docClass
	 * r: docEntite
	 * r.enUS: docEntity
	 * r: entiteAttribuerOperationIdPATCH
	 * r.enUS: entityAttributeOperationIdPATCH
	 * r: entiteAttribuerOperationIdRecherche
	 * r.enUS: entityAttributeOperationIdSearch
	 * r: entiteOperationIdPATCH
	 * r.enUS: entityOperationIdPATCH
	 * 
	 * r: classeSuperErreur
	 * r.enUS: superClassError
	 * r: classePartsCouverture
	 * r.enUS: classPartsWrap
	 * r: classePartsGen
	 * r.enUS: classPartsGen
	 * r: classePartGen
	 * r.enUS: classPartGen
	 * r: classeInitLoinExceptions
	 * r.enUS: classInitDeepExceptions
	 * r: classeInitLoinException
	 * r.enUS: classInitDeepException
	 * r: siteEcrireMethodes
	 * r.enUS: siteWriteMethods
	 * r: classeEcrireMethode
	 * r.enUS: classWriteMethod
	 * r: entiteEcrireMethode
	 * r.enUS: entityWriteMethod
	 * r: classeEntiteVars
	 * r.enUS: classEntityVars
	 * r: classeSuperEntiteVars
	 * r.enUS: classSuperEntityVars
	 * r: classeMethodeVars
	 * r.enUS: classMethodVars
	 * r: classeSuperMethodeVars
	 * r.enUS: classSuperMethodVars
	 * r: classeSuperEntiteVar
	 * r.enUS: classSuperEntityVar
	 * r: classeAttribuerNomSimplePage
	 * r.enUS: classAttributeSimpleNamePage
	 * 
	 * r: classeApiOperationIdRechercheRequete
	 * r.enUS: classApiOperationIdSearchRequest
	 * r: classeApiOperationIdRequete
	 * r.enUS: classApiOperationIdRequest
	 * r: classeApiOperationIdRechercheReponse
	 * r.enUS: classApiOperationIdSearchResponse
	 * r: classeApiOperationIdReponse
	 * r.enUS: classApiOperationIdResponse
	 * r: PageRecherche
	 * r.enUS: SearchPage
	 * 
	 * r: regexLangue
	 * r.enUS: regexLanguage
	 * 
	 * r: ?Ce"
	 * r.enUS: ?This"
	 * r: ?Un"
	 * r.enUS: ?A"
	 * r: _Un)
	 * r.enUS: _A)
	 * r: ?Cree"
	 * r.enUS: ?Created"
	 * r: _Creee)
	 * r.enUS: _Created)
	 * r: ?Modifie"
	 * r.enUS: ?Modified"
	 * r: _Modifiee)
	 * r.enUS: _Modified)
	 * r: ?NomActuel"
	 * r.enUS: ?ActualName"
	 * r: ?TousNom"
	 * r.enUS: ?AllName"
	 * r: ?RechercherTousNomPar"
	 * r.enUS: ?SearchAllNameBy"
	 * r: ?RechercherTousNom"
	 * r.enUS: ?SearchAllName"
	 * r: ?AucunNomTrouve"
	 * r.enUS: ?NoNameFound"
	 * r: ?CeNom"
	 * r.enUS: ?ThisName"
	 * r: ?LeNom"
	 * r.enUS: ?TheName"
	 * r: ?DeNom"
	 * r.enUS: ?OfName"
	 * r: ?Par"
	 * r.enUS: ?By"
	 * r: _Par
	 * r.enUS: _By
	 * r: ?Une"
	 * r.enUS: ?A"
	 * r: _UneFeminin
	 * r.enUS: _AFemale
	 * r: _UnMasculin
	 * r.enUS: _AMale
	 * r: _Une
	 * r.enUS: _A
	 * r: _ActuelleAvant
	 * r.enUS: _CurrentBefore
	 * r: _ActuelleApres
	 * r.enUS: _CurrentAfter
	 * r: _AucuneTrouveAvant
	 * r.enUS: _NoneFoundBefore
	 * r: _AucuneTrouveApres
	 * r.enUS: _NoneFoundAfter
	 * r: _CetteVoyelle
	 * r.enUS: _ThisVowel
	 * r: _LVoyelle
	 * r.enUS: _TheVowel
	 * r: _LaConsonne
	 * r.enUS: _TheConsonant
	 * r: UnNomMinuscule
	 * r.enUS: ANameLowercase
	 * r: CeMinuscule
	 * r.enUS: ThisLowercase
	 * r: CeNomMinuscule
	 * r.enUS: ThisNameLowercase
	 * r: UnMinuscule
	 * r.enUS: ALowercase
	 * r: LeNomMinuscule
	 * r.enUS: TheNameLowercase
	 * r: SingulierNomMinuscule
	 * r.enUS: SingularNameLowercase
	 * r: ActuelNomMinuscule
	 * r.enUS: ActualNameLowercase
	 * r: ToutMinuscule
	 * r.enUS: AllLowercase
	 * r: ToutNomMinuscule
	 * r.enUS: AllNameLowercase
	 * r: AucunTrouveNomMinuscule
	 * r.enUS: NoneFoundNameLowercase
	 * r: DeNomMinuscule
	 * r.enUS: OfNameLowercase
	 * r: NomVarMinuscule
	 * r.enUS: NameVarLowercase
	 * r: AdjectifMinuscule
	 * r.enUS: AdjectiveLowercase
	 * r: AdjectifVarMinuscule
	 * r.enUS: AdjectiveVarLowercase
	 * r: UnNomAdjectifMinuscule
	 * r.enUS: ANameAdjectiveLowercase
	 * r: LeNomAdjectifMinuscule
	 * r.enUS: TheNameAdjectiveLowercase
	 * r: SingulierNomAdjectifMinuscule
	 * r.enUS: SingularNameAdjectiveLowercase
	 * r: PlurielNomAdjectifMinuscule
	 * r.enUS: PluralNameAdjectiveLowercase
	 * r: Couleur
	 * r.enUS: Color
	 * r: IconeGroupe
	 * r.enUS: IconGroup
	 * r: IconeNom
	 * r.enUS: IconName
	 * 
	 * r: UnMasculin
	 * r.enUS: AMale
	 * r: UneFeminin
	 * r.enUS: AFemale
	 * r: CetMasculinVoyelle
	 * r.enUS: ThisMaleVowel
	 * r: CetteFemininVoyelle
	 * r.enUS: ThisFemaleVowel
	 * r: CeMasculinConsonne
	 * r.enUS: ThisMaleConsonant
	 * r: CetteFemininConsonne
	 * r.enUS: ThisFemaleConsonant
	 * r: CesPluriel
	 * r.enUS: ThesePlural
	 * r: LMasculinVoyelle
	 * r.enUS: TheMaleVowel
	 * r: LFemininVoyelle
	 * r.enUS: TheFemaleVowel
	 * r: LeMasculinConsonne
	 * r.enUS: TheMaleConsonant
	 * r: LaFemininConsonne
	 * r.enUS: TheFemaleConsonant
	 * r: LesPluriel
	 * r.enUS: ThePlural
	 * r: ActuelMasculinAvant
	 * r.enUS: CurrentMaleBefore
	 * r: ActuelleFemininAvant
	 * r.enUS: CurrentFemaleBefore
	 * r: ActuelMasculinApres
	 * r.enUS: CurrentMaleAfter
	 * r: ActuelleFemininApres
	 * r.enUS: CurrentFemaleAfter
	 * r: TousMasculinPluriel
	 * r.enUS: AllMalePlural
	 * r: ToutesFemininPluriel
	 * r.enUS: AllFemalePlural
	 * r: AucunTrouveMasculinAvant
	 * r.enUS: NoneFoundMaleBefore
	 * r: AucuneTrouveFemininAvant
	 * r.enUS: NoneFoundFemaleBefore
	 * r: AucunTrouveMasculinApres
	 * r.enUS: NoneFoundMaleAfter
	 * r: AucuneTrouveFemininApres
	 * r.enUS: NoneFoundFemaleAfter
	 * r: DVoyelle
	 * r.enUS: OfVowel
	 * r: DeConsonne
	 * r.enUS: OfConsonant
	 * r: AdjectifPluriel
	 * r.enUS: AdjectivePlural
	 * r: UnNomAdjectif
	 * r.enUS: ANameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheNameAdjective
	 * r: AdjectifAvant
	 * r.enUS: AdjectiveBefore
	 * r: NomSingulierAdjectifPluriel
	 * r.enUS: NameSingularAdjectivePlural
	 * r: NomAdjectifSingulier
	 * r.enUS: NameAdjectiveSingular
	 * r: NomAdjectifPluriel
	 * r.enUS: NameAdjectivePlural
	 * r: PlurielNomAdjectif
	 * r.enUS: PluralNameAdjective
	 * r: SingulierNomAdjectif
	 * r.enUS: SingularNameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheAdjectiveName
	 * r: UnNomAdjectif
	 * r.enUS: AnAdjectiveName
	 * r: AdjectifVar
	 * r.enUS: AdjectiveVar
	 * r: Adjectif
	 * r.enUS: Adjective
	 * r: CreeMasculin
	 * r.enUS: CreatedMale
	 * r: CreeeFeminin
	 * r.enUS: CreatedFemale
	 * r: ModifieMasculin
	 * r.enUS: ModifiedMale
	 * r: ModifieeFeminin
	 * r.enUS: ModifiedFemale
	 * r: RechercherTousNomPar
	 * r.enUS: SearchAllNameBy
	 * r: RechercherTousNom
	 * r.enUS: SearchAllName
	 * r: CetteConsonne
	 * r.enUS: ThisConsonant
	 * 
	 * r: Rechercher
	 * r.enUS: Search
	 * r: contexteNomAdjectifSingulier
	 * r.enUS: contextNameAdjectiveSingular
	 * r: contexteCeNom
	 * r.enUS: contextThisName
	 * r: contexteCe
	 * r.enUS: contextThis
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: contexteUn
	 * r.enUS: contextA
	 * r: contexteLeNom
	 * r.enUS: contextTheName
	 * r: contexteLesNoms
	 * r.enUS: contextTheNames
	 * r: contexteCree
	 * r.enUS: contextCreated
	 * r: contexteModifie
	 * r.enUS: contextModified
	 * r: contexteNomSingulier
	 * r.enUS: contextNameSingular
	 * r: contexteNomPluriel
	 * r.enUS: contextNamePlural
	 * r: contexteNomActuel
	 * r.enUS: contextActualName
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: contexteImageUri
	 * r.enUS: contextImageUri
	 * r: contexteDescription
	 * r.enUS: contextDescription
	 * r: contexteImageLargeur
	 * r.enUS: contextImageWidth
	 * r: contexteImageHauteur
	 * r.enUS: contextImageHeight
	 * r: ImageLargeur
	 * r.enUS: ImageWidth
	 * r: ImageHauteur
	 * r.enUS: ImageHeight
	 * r: NomSingulier
	 * r.enUS: NameSingular
	 * r: NomPluriel
	 * r.enUS: NamePlural
	 * r: NomVar
	 * r.enUS: NameVar
	 * r: LesNom
	 * r.enUS: TheName
	 * r: contexteTous
	 * r.enUS: contextAll
	 * r: contexteAucunNomTrouve
	 * r.enUS: contextNoNameFound
	 * r: contexteNomVar
	 * r.enUS: contextNameVar
	 * r: contexteDeNom
	 * r.enUS: contextOfName
	 * r: contexteNom
	 * r.enUS: contextName
	 * r: classeContexte
	 * r.enUS: classContext
	 * 
	 * r: Chaine
	 * r.enUS: Chain
	 * r: NomSimple
	 * r.enUS: SimpleName
	 * r: ?Modele
	 * r.enUS: ?Model
	 * r: ?ClePrimaire
	 * r.enUS: ?PrimaryKey
	 * r: ?CleUnique
	 * r.enUS: ?UniqueKey
	 * r: ?Crypte
	 * r.enUS: ?Encrypted
	 * r: ?Suggere
	 * r.enUS: ?Suggested
	 * r: ?Sauvegarde
	 * r.enUS: ?Saved
	 * r: ?Indexe
	 * r.enUS: ?Indexed
	 * r: ?Incremente
	 * r.enUS: ?Incremented
	 * r: ?Stocke
	 * r.enUS: ?Stored
	 * r: ?Texte
	 * r.enUS: ?Text
	 * r: ?Langue
	 * r.enUS: ?Language
	 * r: ?LongeurMin
	 * r.enUS: ?MinLength
	 * r: ?LongeurMax
	 * r.enUS: ?MaxLength
	 * r: ?Optionnel
	 * r.enUS: ?Optional
	 * r: ?NomAffichage
	 * r.enUS: ?DisplayName
	 * r: ?Titre
	 * r.enUS: ?Title
	 * r: ?Ignorer
	 * r.enUS: ?Ignored
	 * r: ?Declarer
	 * r.enUS: ?Declared
	 * r: ?Rechercher
	 * r.enUS: ?Search
	 * r: ?Ajouter
	 * r.enUS: ?Add
	 * r: ?Supprimer
	 * r.enUS: ?Delete
	 * r: ?Modifier
	 * r.enUS: ?Modify
	 * r: ?Recharger
	 * r.enUS: ?Refresh
	 * r: ?Multiligne
	 * r.enUS: ?Multiline
	 * r: ?Cles
	 * r.enUS: ?Keys
	 * r: ?MotCle
	 * r.enUS: ?Keyword
	 * r: siteEcrireMethode
	 * r.enUS: siteWriteMethod
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: toutesLangues
	 * r.enUS: allLanguages
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: NomCanonique
	 * r.enUS: CanonicalName
	 * r: ApiMethode
	 * r.enUS: ApiMethod
	 * r: InitLoin
	 * r.enUS: InitDeep
	 * r: motCle
	 * r.enUS: keyword
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: rechercheSolrClasse
	 * r.enUS: solrSearchClass
	 * r.enUS: solrSearch
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: reponseRechercheClasse
	 * r.enUS: searchResponseClass
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: listeRechercheClasse
	 * r.enUS: searchListClass
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: champ
	 * r.enUS: field
	 * r: recherche
	 * r.enUS: search
	 * r: Recherche
	 * r.enUS: Search
	 * r: Requete
	 * r.enUS: Request
	 * r: Reponse
	 * r.enUS: Response
	 * r: Couverture
	 * r.enUS: Wrap
	 * r: Avant
	 * r.enUS: Before
	 * r: avant
	 * r.enUS: before
	 * r: Apres
	 * r.enUS: After
	 * r: apres
	 * r.enUS: after
	 * r: classe
	 * r.enUS: class
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 * r: requete
	 * r.enUS: request
	 * r: reponse
	 * r.enUS: response
	 * r: entite
	 * r.enUS: entity
	 * r: contexte
	 * r.enUS: context
	 * r: CONTEXTE
	 * r.enUS: CONTEXT
	 * r: Titre
	 * r.enUS: Title
	 * r: Traduire
	 * r.enUS: Translate
	 * r: methode
	 * r.enUS: method
	 * r: Contexte
	 * r.enUS: Context
	 * r: suffixe
	 * r.enUS: suffix
	 */        
	public SolrInputDocument indexerClasse(String classeCheminAbsolu, SolrInputDocument classeDoc, String classeLangueNom) throws Exception { 

		String classeStr = FileUtils.readFileToString(new File(classeCheminAbsolu), "UTF-8");
		Matcher classeDroitAuteurMatcher = Pattern.compile("\\/\\*[\\s\\S]+?[Cc]opyright[\\s\\S]+?(?=\\*\\/)\\*\\/", Pattern.MULTILINE).matcher(classeStr);
		if(classeDroitAuteurMatcher.find()) {
			String classeDroitAuteur = classeDroitAuteurMatcher.group(0);
			indexerStockerSolr(classeDoc, "classeDroitAuteur", classeDroitAuteur);
		}


		String[] classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);
		String classeNomCanonique = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classeCheminAbsolu, "."), "/java/"), "/", ".");
		String classeNomSimple = StringUtils.substringAfterLast(classeNomCanonique, ".");
		String classeNomCanoniqueGen = classeNomCanonique + "Gen";
		String classeNomSimpleGen = classeNomSimple + "Gen";

		indexerStockerSolr(classeDoc, "siteChemin", siteChemin);
		indexerStockerSolr(classeDoc, "siteCheminVertx", siteCheminVertx);
		indexerStockerSolr(classeDoc, "siteNom", siteNom);
		JavaClass classeQdox = bricoleur.getClassByName(classeNomCanonique.toString());
		JavaClass classeQdoxSuper = classeQdox.getSuperJavaClass();
		JavaClass classeQdoxString = bricoleur.getClassByName(String.class.getCanonicalName());

		Boolean classeMotsClesTrouves = false;
		List<String> classeMotsCles = new ArrayList<String>();

		Boolean classeTrisTrouves = false;
		List<String> classeTrisOrdre = new ArrayList<String>();
		List<String> classeTrisVar = new ArrayList<String>();

		List<String> classeInitLoinExceptions = new ArrayList<String>(); 
		String classeVarSuggere = null;
		String classeVarTexte = null;
		String classeVarUrlId = null;
		String classeVarUrlPk = null;
		String classeVarUrlApi = null;
		String classeVarId = null;
		String classeVarTitre = null;
		String classeVarH1 = null;
		String classeVarH2 = null;
		String classeVarH3 = null;
		String classeVarDescription = null;
		String classeVarImageUrl = null;
		String classeVarModifie = null;
		String classeVarCree = null;
		String classeVarClePrimaire = null;
		String classeVarInheritClePrimaire = null;
		String classeVarSauvegardes = null;
		String classeVarCleUnique = null;

		String classeNomCanoniqueSuper = Object.class.getCanonicalName();
		Boolean classeSuperErreur = false;
		try {
			classeNomCanoniqueSuper = classeQdoxSuper.getCanonicalName();
		} catch (Exception e) {
			classeSuperErreur = true;
		}

		String classeNomSimpleSuper = StringUtils.substringAfterLast(classeNomCanoniqueSuper, ".");
		if(StringUtils.isEmpty(classeNomSimpleSuper))
			classeNomSimpleSuper = classeNomCanoniqueSuper;

		List<String> classeMethodesEcrites = new ArrayList<String>();

		List<JavaTypeVariable<JavaGenericDeclaration>> classeParamsType = classeQdox.getTypeParameters();
		for(JavaTypeVariable<JavaGenericDeclaration> classeParamType : classeParamsType) {
			String classeParamTypeNom = classeParamType.getName();
			stockerListeSolr(classeDoc, "classeParamsTypeNom", classeParamTypeNom);
		}

		if(classeQdoxSuper instanceof DefaultJavaParameterizedType) {
			DefaultJavaParameterizedType typeSuper = (DefaultJavaParameterizedType)classeQdoxSuper;
			List<JavaType> classeSuperParamsType = typeSuper.getActualTypeArguments();
			for(JavaType classeSuperParamType : classeSuperParamsType) {
				String classeSuperParamTypeNom = classeSuperParamType.getValue();
				stockerListeSolr(classeDoc, "classeSuperParamsTypeNom", classeSuperParamTypeNom);
			}
		}
		
		String classeNomCompletSuper = Object.class.getCanonicalName();
		try {
			classeNomCompletSuper = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCompletSuper", classeQdoxSuper.getGenericCanonicalName());
		} catch (Exception e) {
			if(classeQdoxSuper != null && classeQdoxSuper.getGenericFullyQualifiedName().contains("<"))
				classeNomCompletSuper = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCompletSuper", classeQdoxSuper.getGenericFullyQualifiedName());
		}
		for(JavaClass cImplements : classeQdox.getImplementedInterfaces()) {
			ClasseParts classePartsImplements = ClasseParts.initClasseParts(this, cImplements, classeLangueNom);
			indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImplementsNomSimpleComplet", classePartsImplements.nomSimple(classeLangueNom));
		}

		String classeNomCompletSuperGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(classeNomCompletSuper, "<"), ">");
		String classeNomCanoniqueSuperGenerique = null;
		String classeNomSimpleSuperGenerique = null;
		Boolean classeBaseEtendGen = false;
		ClasseParts classePartsBase = null;
		if(StringUtils.isNotEmpty(classeNomCompletSuper)) {
			indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCompletSuperGenerique", classeNomCompletSuperGenerique);
			if(classeNomCompletSuper.contains("<")) {
				classeNomCanoniqueSuperGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(classeNomCompletSuper, ">"), "<");
				classeNomCanoniqueSuperGenerique = classeNomCanoniqueSuperGenerique.contains(",") ? StringUtils.substringBefore(classeNomCanoniqueSuperGenerique, ",") : classeNomCanoniqueSuperGenerique;
				indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueSuperGenerique", classeNomCanoniqueSuperGenerique);
				classeNomCompletSuperGenerique = classeNomCanoniqueSuperGenerique;

				if(classeNomCanoniqueSuperGenerique.contains("."))
					classeNomSimpleSuperGenerique = StringUtils.substringAfterLast(classeNomCanoniqueSuperGenerique, ".");
				else
					classeNomSimpleSuperGenerique = classeNomCanoniqueSuperGenerique;
				indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleSuperGenerique", classeNomSimpleSuperGenerique);

//				classePartsBase = ClasseParts.initClasseParts(this, classeNomCanoniqueSuperGenerique, classeLangueNom, classeLangueNom);
				classePartsBase = ClasseParts.initClasseParts(this, classeNomCanoniqueSuperGenerique, classeLangueNom);
				classeBaseEtendGen = classePartsBase.getEtendGen();
				if(classeBaseEtendGen == null)
					classeBaseEtendGen = false;
			}
		}
		Boolean classeEstBase = indexerStockerSolr(classeDoc, "classeEstBase", !classeBaseEtendGen || StringUtils.isEmpty(classeNomCompletSuperGenerique) || StringUtils.equals(classeNomCompletSuperGenerique, "java.lang.Object"));
		Boolean classeEtendBase = indexerStockerSolr(classeDoc, "classeEtendBase", !classeEstBase && classeBaseEtendGen && !StringUtils.equals(classeNomCompletSuperGenerique, "java.lang.Object"));
		
		String classeCommentaire = stockerRegexCommentaires(classeLangueNom, classeDoc, "classeCommentaire", classeQdox.getComment());
		String classeNomEnsemble = StringUtils.substringBeforeLast(classeNomCanonique, ".");
		String classeChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), ".java");
		String classeCheminRepertoire = StringUtils.substringBeforeLast(classeChemin, "/");
		String classeCheminGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Gen.java");

		Boolean classePromesse = regexTrouve("^(classe)?" + classeLangueConfig.getString(ConfigCles.var_Promesse) + ":\\s*(true)$", classeCommentaire);
		indexerStockerSolr(classeDoc, "classeBaseEtendGen", classeBaseEtendGen);
		Boolean classeContientRequeteSite = false;
		try {
			classeContientRequeteSite = classeQdox.getMethodBySignature("get" + classeLangueConfig.getString(ConfigCles.var_RequeteSite) +"_", new ArrayList<JavaType>(), true) != null
					|| classePartsBase != null && BooleanUtils.isTrue((Boolean)classePartsBase.getDocumentSolr().get("classeContientRequeteSite_stored_boolean"));
		} catch (Exception e) {
			// TODO ctate fix this to pull from solr. 
		}
		indexerStockerSolr(classeDoc, "classeContientRequeteSite", classeContientRequeteSite);

		String classeCheminRepertoireGen = StringUtils.substringBeforeLast(classeCheminGen, "/");
		String classeCle = classeCheminAbsolu;
		Instant modifiee = Instant.now();
		Date modifieeDate = Date.from(modifiee);
		Boolean classeContientCouverture = false;

		Boolean classeTraduire = indexerStockerSolr(classeDoc, "classeTraduire", !regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Traduire) + ": \\s*(false)$", classeCommentaire));
		Boolean classeEtendGen = StringUtils.endsWith(classeNomSimpleSuper, "Gen");
		if(classeSuperErreur || !classeEtendGen && regexTrouve("^(classe)?Gen:\\s*(true)$", classeCommentaire)) {
			classeEtendGen = true;
		}
		if(regexTrouve("^(classe)?Gen:\\s*(false)$", classeCommentaire) || classeQdox.isInterface())
			classeEtendGen = false;

//		if(classeTraduire) {
			for(String langueNom : classeAutresLangues) {
				String siteCheminLangue = siteChemins.get(langueNom);
				String siteCheminVertxLangue = siteCheminsVertx.get(langueNom);
				stockerRegexCommentaires(langueNom, classeDoc, "classeCommentaire", classeCommentaire);
				String cheminSrcMainJavaLangue = siteCheminLangue + "/src/main/java";
				String cheminSrcGenJavaLangue = siteCheminLangue + "/src/gen/java";
				String classeNomCanoniqueLangue = regex("^" + classeLangueConfig.getString(ConfigCles.var_NomCanonique) + "\\." + langueNom + ":\\s*(.*)", classeCommentaire);
	
				if(classeNomCanoniqueLangue == null)
					classeNomCanoniqueLangue = classeNomCanonique.replace(this.langueNomGlobale, langueNom);
				String classeNomSimpleLangue = StringUtils.substringAfterLast(classeNomCanoniqueLangue, ".");
				String classeNomEnsembleLangue = StringUtils.substringBeforeLast(classeNomCanoniqueLangue, ".");
				String classeNomCanoniqueGenLangue = classeNomCanoniqueLangue + "Gen";
				String classeNomSimpleGenLangue = classeNomSimpleLangue + "Gen";
				String classeCheminLangue = indexerStockerSolr(langueNom, classeDoc, "classeChemin", concat(cheminSrcMainJavaLangue, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java"));
				String classeCheminRepertoireLangue = stockerSolr(langueNom, classeDoc, "classeCheminRepertoire", StringUtils.substringBeforeLast(classeCheminLangue, "/"));
				String classeCheminGenLangue = indexerStockerSolr(langueNom, classeDoc, "classeCheminGen", concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniqueGenLangue, ".", "/"), ".java"));
				String classeCheminRepertoireGenLangue = stockerSolr(langueNom, classeDoc, "classeCheminRepertoireGen", StringUtils.substringBeforeLast(classeCheminGenLangue, "/"));
	
				indexerStockerSolr(langueNom, classeDoc, "classeNomCanonique", classeNomCanoniqueLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimple", classeNomSimpleLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueGen", classeNomCanoniqueGenLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGen", classeNomSimpleGenLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomEnsemble", classeNomEnsembleLangue); 
	
				String classeNomCompletSuperLangue;
				ClasseParts classePartsSuperLangue = null;
	
				if(classeEtendGen) {
					classePartsSuperLangue = ClasseParts.initClasseParts(this, classeNomCanoniqueLangue + "Gen", classeLangueNom);
				}
				else if(classeQdoxSuper != null) {
					classePartsSuperLangue = ClasseParts.initClasseParts(this, classeQdoxSuper, classeLangueNom);
				}
	
				if(classePartsSuperLangue != null) {
					indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueSuper", classePartsSuperLangue.nomCanonique(langueNom)); 
					indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleSuper", classePartsSuperLangue.nomSimple(langueNom)); 
					indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueCompletSuper", classePartsSuperLangue.nomCanoniqueComplet(langueNom));
					indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleCompletSuper", classePartsSuperLangue.nomSimpleComplet(langueNom));
					if(StringUtils.isNotEmpty(classeNomCompletSuperGenerique)) {
						ClasseParts classePartsSuperGeneriqueLangue = ClasseParts.initClasseParts(this, classeNomCompletSuperGenerique, classeLangueNom);
						indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueSuperGenerique", classePartsSuperGeneriqueLangue.nomCanoniqueComplet(langueNom));
						indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleSuperGenerique", classePartsSuperGeneriqueLangue.nomSimpleComplet(langueNom));
					}
				}
				for(JavaClass cImplements : classeQdox.getImplementedInterfaces()) {
					ClasseParts classePartsImplements = ClasseParts.initClasseParts(this, cImplements, classeLangueNom);
					indexerStockerListeSolr(langueNom, classeDoc, "classeImplementsNomSimpleComplet", classePartsImplements.nomSimpleComplet(langueNom));
				}
//			}
		}

		classePartsTest = ClasseParts.initClasseParts(this, "org.junit.Test", classeLangueNom);
		classePartsList = ClasseParts.initClasseParts(this, List.class.getCanonicalName(), classeLangueNom);
		classePartsArrayList = ClasseParts.initClasseParts(this, ArrayList.class.getCanonicalName(), classeLangueNom);
		classePartsUtilisateurSite = classePartsUtilisateurSite(nomEnsembleDomaine, classeLangueNom);
		classePartsRequeteApi = classePartsRequeteApi(nomEnsembleDomaine, classeLangueNom);
		classePartsModeleBase = classePartsModeleBase(nomEnsembleDomaine, classeLangueNom);
		classePartsResultatRecherche = classePartsResultatRecherche(nomEnsembleDomaine, classeLangueNom);
		classePartsToutEcrivain = classePartsToutEcrivain(nomEnsembleDomaine, classeLangueNom);
		classePartsListeRecherche = classePartsListeRecherche(nomEnsembleDomaine, classeLangueNom);
		classePartsCouverture = classePartsCouverture(nomEnsembleDomaine, classeLangueNom);
		classePartsMiseEnPage = classePartsMiseEnPage(nomEnsembleDomaine, classeLangueNom);
		classePartsPagePart = classePartsPagePart(nomEnsembleDomaine, classeLangueNom);
		classePartsBaseApiServiceImpl = classePartsBaseApiServiceImpl(nomEnsembleDomaine, classeLangueNom);
		classePartsVerticle = classePartsVerticle(nomEnsembleDomaine, classeLangueNom);
		classePartsRequeteSite = classePartsRequeteSite(nomEnsembleDomaine, classeLangueNom);
		classePartsMailVerticle = classePartsMailVerticle(nomEnsembleDomaine, classeLangueNom);
		classePartsConfigCles = classePartsConfigCles(nomEnsembleDomaine, classeLangueNom);
		classePartsPointDeserializer = classePartsPointDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsPointSerializer = classePartsPointSerializer(nomEnsembleDomaine, classeLangueNom);
		classePartsLocalTimeDeserializer = classePartsLocalTimeDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsLocalTimeSerializer = classePartsLocalTimeSerializer(nomEnsembleDomaine, classeLangueNom);
		classePartsLocalDateDeserializer = classePartsLocalDateDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsLocalDateSerializer = classePartsLocalDateSerializer(nomEnsembleDomaine, classeLangueNom);
		classePartsZonedDateTimeDeserializer = classePartsZonedDateTimeDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsZonedDateTimeSerializer = classePartsZonedDateTimeSerializer(nomEnsembleDomaine, classeLangueNom);

		Boolean classeInitLoin = !regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_InitLoin) + ":\\s*(false)$", classeCommentaire);
		if(classeInitLoin)
			classeInitLoin = classeEtendBase || classeEstBase;
//			classeInitLoin = classeContientRequeteSite;
		classeInitLoin = stockerSolr(classeDoc, "classeInitLoin", classeInitLoin);
		if(classeInitLoin && classePartsRequeteSite != null) {
			if(classePartsRequeteSite.getEtendBase())
				classePartsGenAjouter(ClasseParts.initClasseParts(this, classePartsRequeteSite.getNomCanoniqueSuperGenerique(), classeLangueNom), classeLangueNom);
			else
				classePartsGenAjouter(classePartsRequeteSite, classeLangueNom);
		}
		indexerStockerSolr(classeDoc, "classeEtendGen", classeEtendGen);

		indexerStockerSolr(classeDoc, "langueNom", classeLangueNom); 
		indexerStockerSolr(classeDoc, "modifiee", modifieeDate); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanonique", classeNomCanonique); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimple", classeNomSimple); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomEnsemble", classeNomEnsemble); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueGen", classeNomCanoniqueGen); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleGen", classeNomSimpleGen); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueSuper", classeNomCanoniqueSuper); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleSuper", classeNomSimpleSuper); 
		indexerStockerSolr(classeDoc, "classeCheminAbsolu", classeCheminAbsolu);
		indexerStockerSolr(classeLangueNom, classeDoc, "classeChemin", classeChemin); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminRepertoire", classeCheminRepertoire);  
		indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminGen", classeCheminGen); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminRepertoireGen", classeCheminRepertoireGen); 
		indexerStockerSolr(classeDoc, "nomEnsembleDomaine", nomEnsembleDomaine); 

		ArrayList<JavaClass> classesSuperQdox = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtMoi = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtMoiSansGen = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi = new ArrayList<JavaClass>();
		peuplerClassesSuperQdoxInterfacesEtMoi(classeQdox, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtMoiSansGen, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi);

		for(JavaClass c : classesSuperQdoxEtMoiSansGen) {
			indexerStockerListeSolr(classeDoc, "entiteClassesSuperEtMoiSansGen", c.getCanonicalName()); 

		}
		String fqClassesSuperEtMoi = "(" + classesSuperQdoxEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c.getCanonicalName())).collect(Collectors.joining(" OR ")) + ")";

		SolrInputDocument classeDocClone = classeDoc.deepCopy();
		Integer partNumero = 1;

		indexerStockerSolr(classeDoc, "classeEstAbstrait", classeQdox.isAbstract()); 
		Boolean classeModele = indexerStockerSolr(classeDoc, "classeModele", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Modele) + ": \\s*(true)$", classeCommentaire));
		Boolean classeApi = indexerStockerSolr(classeDoc, "classeApi", regexTrouve("^(classe)?Api: \\s*(true)$", classeCommentaire));
		Boolean classePage = regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Page) + ": \\s*(true)$", classeCommentaire);
		Boolean classePageSimple = indexerStockerSolr(classeDoc, "classePageSimple", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_PageSimple) + ": \\s*(true)$", classeCommentaire));
		Boolean classeSauvegarde = indexerStockerSolr(classeDoc, "classeSauvegarde", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Sauvegarde) + ":\\s*(true)$", classeCommentaire) || classeModele);
		String classeApiClasseNomSimple = regexLangue(langueNomGlobale, "^" + classeLangueConfig.getString(ConfigCles.var_ApiClasse), classeCommentaire, classeNomSimple);

				if(classeApiClasseNomSimple != null) {
					SolrQuery recherchePageClasse = new SolrQuery();   
					recherchePageClasse.setQuery("*:*");
					recherchePageClasse.setRows(1);
					recherchePageClasse.addFilterQuery("classeNomSimple_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classeApiClasseNomSimple));
					recherchePageClasse.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
					recherchePageClasse.addFilterQuery("partEstClasse_indexed_boolean:true");
					recherchePageClasse.addFilterQuery("langueNom_indexed_string:" + ClientUtils.escapeQueryChars(classeLangueNom));
					QueryResponse reponseRecherchePageClasse = clientSolrComputate.query(recherchePageClasse);
					SolrDocumentList listeRecherchePageClasse = reponseRecherchePageClasse.getResults();
	
					if(listeRecherchePageClasse.size() > 0) {
						SolrDocument docPageClasse = listeRecherchePageClasse.get(0);
						String classePageClasseNomCanoniqueMethode = (String)docPageClasse.get("classeNomCanonique_" + classeLangueNom + "_stored_string");
						indexerStockerSolr(langueNomGlobale, classeDoc, "classePageClasseNomCanonique", classePageClasseNomCanoniqueMethode);
						indexerStockerSolr(langueNomGlobale, classeDoc, "classeApiClasseNomSimple", classeApiClasseNomSimple);
						classePartsGenPageAjouter(ClasseParts.initClasseParts(this, classePageClasseNomCanoniqueMethode, classeLangueNom), classeLangueNom);
						classePartsGenApiAjouter(ClasseParts.initClasseParts(this, classePageClasseNomCanoniqueMethode, classeLangueNom), classeLangueNom);
					}
				}

		String classeTypeContenu = regex("^" + classeLangueConfig.getString(ConfigCles.var_TypeMedia) + ":\\s*(.*)", classeCommentaire);
		if(StringUtils.isNotBlank(classeTypeContenu))
			indexerStockerSolr(classeDoc, "classeTypeContenu", classeTypeContenu);
		else 
			classeTypeContenu = null;

		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomAffichage", regexLangue(classeLangueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAffichage) + "", classeCommentaire));

//		indexerStockerSolr(classeDoc, "siteChemin", siteChemin);

		String classeNomSimpleApiEnsembleInfo;
		String classeNomSimpleGenApiServiceImpl;
		String classeNomSimpleApiServiceImpl;
		String classeNomSimpleGenApiService;

		String classeNomCanoniqueApiEnsembleInfo;
		String classeNomCanoniqueGenApiServiceImpl;
		String classeNomCanoniqueApiServiceImpl;
		String classeNomCanoniqueGenApiService;

		String classeCheminApiEnsembleInfo;
		String classeCheminGenApiServiceImpl;
		String classeCheminApiServiceImpl;
		String classeCheminGenApiService;

		if(classeApi) {

			String siteCheminVertx = siteCheminsVertx.get(classeLangueNom);
			String cheminSrcGenJavaVertx = (siteCheminVertx == null ? siteChemin : siteCheminVertx) + "/src/gen/java";
			String cheminSrcMainJavaVertx = (siteCheminVertx == null ? siteChemin : siteCheminVertx) + "/src/main/java";

			classeNomSimpleApiEnsembleInfo = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleApiEnsembleInfo", "package-info");
			classeNomSimpleGenApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleGenApiServiceImpl", classeNomSimple + StringUtils.capitalize(classeLangueNom) + "GenApiServiceImpl");
			classeNomSimpleApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleApiServiceImpl", classeNomSimple + StringUtils.capitalize(classeLangueNom) + "ApiServiceImpl");
			classeNomSimpleGenApiService = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleGenApiService", classeNomSimple + StringUtils.capitalize(classeLangueNom) + "GenApiService");

			classeNomCanoniqueApiEnsembleInfo = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueApiEnsembleInfo", classeNomEnsemble + "." + classeNomSimpleApiEnsembleInfo);
			classeNomCanoniqueGenApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueGenApiServiceImpl", classeNomEnsemble + "." + classeNomSimpleGenApiServiceImpl);
			classeNomCanoniqueApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueApiServiceImpl", classeNomEnsemble + "." + classeNomSimpleApiServiceImpl);
			classeNomCanoniqueGenApiService = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueGenApiService", classeNomEnsemble + "." + classeNomSimpleGenApiService);

			classeCheminApiEnsembleInfo = concat(cheminSrcGenJavaVertx, "/", StringUtils.replace(classeNomCanoniqueApiEnsembleInfo, ".", "/"), ".java");
			classeCheminGenApiServiceImpl = concat(cheminSrcMainJavaVertx, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceImpl, ".", "/"), ".java");
			classeCheminApiServiceImpl = concat(cheminSrcMainJavaVertx, "/", StringUtils.replace(classeNomCanoniqueApiServiceImpl, ".", "/"), ".java");
			classeCheminGenApiService = concat(cheminSrcMainJavaVertx, "/", StringUtils.replace(classeNomCanoniqueGenApiService, ".", "/"), ".java");

			indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminApiEnsembleInfo", classeCheminApiEnsembleInfo); 
			indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminGenApiServiceImpl", classeCheminGenApiServiceImpl); 
			indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminApiServiceImpl", classeCheminApiServiceImpl); 
			indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminGenApiService", classeCheminGenApiService); 
		}

		if(classeTraduire) {
			for(String langueNom : classeAutresLangues) {

				String siteCheminLangue = siteChemins.get(langueNom);
				String cheminSrcMainJavaLangue = siteCheminLangue + "/src/main/java";
				String cheminSrcGenJavaLangue = siteCheminLangue + "/src/gen/java";
				String classeNomCanoniqueLangue = (String)classeDoc.get("classeNomCanonique_" + langueNom + "_indexed_string").getValue();
				String classeNomSimpleLangue = (String)classeDoc.get("classeNomSimple_" + langueNom + "_indexed_string").getValue();
				String classeNomEnsembleLangue = (String)classeDoc.get("classeNomEnsemble_" + langueNom + "_indexed_string").getValue();
	
				String classeNomSimpleApiLangue = classeNomSimpleLangue + "Api";
				String classeNomSimplePageLangue = classeNomSimpleLangue + "Page";
				String classeNomSimpleGenPageLangue = classeNomSimpleLangue + "PageGen";
				String classeNomCanoniqueApiLangue = classeNomCanoniqueLangue + "Api";
				String classeNomCanoniqueApiGenLangue = classeNomCanoniqueLangue + "ApiGen";
				String classeNomCanoniquePageLangue = classeNomCanoniqueLangue + "Page";
				String classeNomCanoniquePageGenLangue = classeNomCanoniqueLangue + "PageGen";
				String classeCheminApiGenLangue = indexerStockerSolr(langueNom, classeDoc, "classeCheminApiGen", concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniqueApiGenLangue, ".", "/"), ".java"));
				String classeCheminGenPageLangue = indexerStockerSolr(langueNom, classeDoc, "classeCheminGenPage", concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniquePageGenLangue, ".", "/"), ".java"));
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleApi", classeNomSimpleApiLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimplePage", classeNomSimplePageLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGenPage", classeNomSimpleGenPageLangue); 

				indexerStockerSolr(langueNom, classeDoc, "classeNomAffichage", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAffichage) + "", classeCommentaire));
	
				if(classeApi) {

					String siteCheminVertxLangue = siteCheminsVertx.get(langueNom);
					String cheminSrcGenJavaVertxLangue = (siteCheminVertxLangue == null ? siteCheminLangue : siteCheminVertxLangue) + "/src/gen/java";
					String cheminSrcMainJavaVertxLangue = (siteCheminVertxLangue == null ? siteCheminLangue : siteCheminVertxLangue) + "/src/main/java";

					String classeNomSimpleApiEnsembleInfoLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleApiEnsembleInfo", "package-info");
					String classeNomSimpleGenApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGenApiServiceImpl", classeNomSimpleLangue + StringUtils.capitalize(langueNom) + "GenApiServiceImpl");
					String classeNomSimpleApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleApiServiceImpl", classeNomSimpleLangue + StringUtils.capitalize(langueNom) + "ApiServiceImpl");
					String classeNomSimpleGenApiServiceLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGenApiService", classeNomSimpleLangue + StringUtils.capitalize(langueNom) + "GenApiService");
		
					String classeNomCanoniqueApiEnsembleInfoLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueApiEnsembleInfo", classeNomEnsembleLangue + "." + classeNomSimpleApiEnsembleInfoLangue);
					String classeNomCanoniqueGenApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueGenApiServiceImpl", classeNomEnsembleLangue + "." + classeNomSimpleGenApiServiceImplLangue);
					String classeNomCanoniqueApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueApiServiceImpl", classeNomEnsembleLangue + "." + classeNomSimpleApiServiceImplLangue);
					String classeNomCanoniqueGenApiServiceLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueGenApiService", classeNomEnsembleLangue + "." + classeNomSimpleGenApiServiceLangue);
		
					String classeCheminApiEnsembleInfoLangue = concat(cheminSrcGenJavaVertxLangue, "/", StringUtils.replace(classeNomCanoniqueApiEnsembleInfoLangue, ".", "/"), ".java");
					String classeCheminGenApiServiceImplLangue = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceImplLangue, ".", "/"), ".java");
					String classeCheminApiServiceImplLangue = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomCanoniqueApiServiceImplLangue, ".", "/"), ".java");
					String classeCheminGenApiServiceLangue = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceLangue, ".", "/"), ".java");
		
					indexerStockerSolr(langueNom, classeDoc, "classeCheminApiEnsembleInfo", classeCheminApiEnsembleInfoLangue); 
					indexerStockerSolr(langueNom, classeDoc, "classeCheminGenApiServiceImpl", classeCheminGenApiServiceImplLangue); 
					indexerStockerSolr(langueNom, classeDoc, "classeCheminApiServiceImpl", classeCheminApiServiceImplLangue); 
					indexerStockerSolr(langueNom, classeDoc, "classeCheminGenApiService", classeCheminGenApiServiceLangue); 
				}
			}
		}

		if(activerVertx) {
			if(classeEtendBase) {
				if(classePartsModeleBase != null && classeNomSimpleSuperGenerique.equals(classePartsModeleBase.nomSimple(langueNomGlobale)))
					classePromesse = true;
				classePartsGenAjouter(classePartsModeleBase, classeLangueNom);
			}
			else if(classeEstBase) {
				if(classePartsModeleBase != null && classePartsModeleBase != null && classeNomSimple.equals(classePartsModeleBase.nomSimple(langueNomGlobale)))
					classePromesse = true;
				classePartsGenAjouter(classePartsModeleBase, classeLangueNom);
			}
			if(classeSauvegarde) {
				classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonObject", classeLangueNom), classeLangueNom);
				classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueDate, classeLangueNom), classeLangueNom);
				classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.Set", classeLangueNom), classeLangueNom);
			}
			classePartsGenAjouter(classePartsRequeteApi, classeLangueNom);
			classePartsGenAjouter(classePartsConfigCles, classeLangueNom);
		}
		classePartsGenAjouter(ClasseParts.initClasseParts(this, Optional.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, List.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.StringUtils", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.Objects", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.annotation.JsonProperty", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.annotation.JsonIgnore", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, JsonFormat.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, JsonSerialize.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, JsonDeserialize.class.getCanonicalName(), classeLangueNom), classeLangueNom);

		if(classePartsLocalDateSerializer != null)
			classePartsGenAjouter(classePartsLocalDateSerializer, classeLangueNom);

		if(classePartsLocalDateDeserializer != null)
			classePartsGenAjouter(classePartsLocalDateDeserializer, classeLangueNom);

		if(classePartsZonedDateTimeSerializer != null)
			classePartsGenAjouter(classePartsZonedDateTimeSerializer, classeLangueNom);

		if(classePartsZonedDateTimeDeserializer != null)
			classePartsGenAjouter(classePartsZonedDateTimeDeserializer, classeLangueNom);

		classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.annotation.JsonInclude", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.annotation.JsonInclude.Include", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, ToStringSerializer.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, MathContext.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, NumberUtils.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, NumberFormat.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, Arrays.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, ArrayList.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, HashMap.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		if(activerLog) {
			classePartsGenAjouter(ClasseParts.initClasseParts(this, Logger.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, LoggerFactory.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		}
		classePartsGenAjouter(ClasseParts.initClasseParts(this, RoundingMode.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, Map.class.getCanonicalName(), classeLangueNom), classeLangueNom);

		Boolean classePublicLire = false;
		Boolean classeRoleSession = false;
		Boolean classeRoleUtilisateur = false;
		Boolean classeRoleChacun = false;
		Boolean classeRolesTrouves = false;
		
		if(classeCommentaire != null) {

			Matcher classeValsRecherche = Pattern.compile("^Val(:([^:\n]+):)?\\.(\\w+)\\.([^:\n]+):(.*)", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeValsTrouves = classeValsRecherche.find();
			while(classeValsTrouves) {
				String classeValVar = classeValsRecherche.group(3);
				String classeValLangue = classeValsRecherche.group(4);
				String classeValCode = classeValsRecherche.group(2);
				String classeValValeur = classeValsRecherche.group(5);
				if(classeValCode == null)
					classeValCode = "";
				stockerListeSolr(classeDoc, "classeValsVar", classeValVar);
				stockerListeSolr(classeDoc, "classeValsLangue", classeValLangue);
				stockerListeSolr(classeDoc, "classeValsCode", classeValCode);
				stockerListeSolr(classeDoc, "classeValsValeur", classeValValeur);
				classeValsTrouves = classeValsRecherche.find();
			}

			classePublicLire = indexerStockerSolr(classeDoc, "classePublicLire", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_PublicLire) + ":\\s*(true)$", classeCommentaire));
			classeRoleSession = indexerStockerSolr(classeDoc, "classeRoleSession", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_RoleSession) + ":\\s*(true)$", classeCommentaire));
			classeRoleUtilisateur = indexerStockerSolr(classeDoc, "classeRoleUtilisateur", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_RoleUtilisateur) + ":\\s*(true)$", classeCommentaire));
			classeRoleChacun = indexerStockerSolr(classeDoc, "classeRoleChacun", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_RoleChacun) + ":\\s*(true)$", classeCommentaire));

			Matcher classeRolesRecherche = Pattern.compile("^" + classeLangueConfig.getString(ConfigCles.var_Role) + "\\.([^:\n]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			classeRolesTrouves = classeRolesRecherche.find();
			boolean classeRolesTrouvesActuel = classeRolesTrouves;
			while(classeRolesTrouvesActuel) {
				String classeRoleValeur = classeRolesRecherche.group(2);
				String classeRoleLangue = classeRolesRecherche.group(1);
				stockerListeSolr(classeDoc, "classeRoles", classeRoleValeur);
				stockerListeSolr(classeDoc, "classeRolesLangue", classeRoleLangue);
				classeRolesTrouves = true;
				classeRolesTrouvesActuel = classeRolesRecherche.find();
			}
			indexerStockerSolr(classeDoc, "classeRolesTrouves", classeRolesTrouves); 

			Matcher classeRoleLiresRecherche = Pattern.compile("^" + classeLangueConfig.getString(ConfigCles.var_RoleLire) + "\\.([^:\n]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeRoleLiresTrouves = classeRoleLiresRecherche.find();
			boolean classeRoleLiresTrouvesActuel = classeRoleLiresTrouves;
			while(classeRoleLiresTrouvesActuel) {
				String classeRoleLireValeur = classeRoleLiresRecherche.group(2);
				String classeRoleLireLangue = classeRoleLiresRecherche.group(1);
				stockerListeSolr(classeDoc, "classeRoleLires", classeRoleLireValeur);
				stockerListeSolr(classeDoc, "classeRoleLiresLangue", classeRoleLireLangue);
				classeRoleLiresTrouves = true;
				classeRoleLiresTrouvesActuel = classeRoleLiresRecherche.find();
			}
			indexerStockerSolr(classeDoc, "classeRoleLiresTrouves", classeRoleLiresTrouves); 

			indexerStockerSolr(classeDoc, "classeSessionEcrire", BooleanUtils.isTrue(classeRoleSession));
			indexerStockerSolr(classeDoc, "classeUtilisateurEcrire", BooleanUtils.isTrue(classeRoleUtilisateur));
			indexerStockerSolr(classeDoc, "classePublicEcrire", !classeRolesTrouves);
			indexerStockerSolr(classeDoc, "classeSessionLire", !classeRolesTrouves || BooleanUtils.isTrue(classeRoleSession));
			indexerStockerSolr(classeDoc, "classeUtilisateurLire", !classeRolesTrouves || BooleanUtils.isTrue(classeRoleUtilisateur) || BooleanUtils.isTrue(classePublicLire));

			Matcher classeFiltresRecherche = Pattern.compile("^" + classeLangueConfig.getString(ConfigCles.var_Filtre) + "\\.([^:\n]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeFiltresTrouves = classeFiltresRecherche.find();
			boolean classeFiltresTrouvesActuel = classeFiltresTrouves;
			while(classeFiltresTrouvesActuel) {
				String classeFiltreValeur = classeFiltresRecherche.group(2);
				String classeFiltreLangue = classeFiltresRecherche.group(1);
				stockerListeSolr(classeDoc, "classeFiltres", classeFiltreValeur);
				stockerListeSolr(classeDoc, "classeFiltresLangue", classeFiltreLangue);
				classeFiltresTrouves = true;
				classeFiltresTrouvesActuel = classeFiltresRecherche.find();
			}
			indexerStockerSolr(classeDoc, "classeFiltresTrouves", classeFiltresTrouves); 

			Matcher classeMotsClesRecherche = Pattern.compile("^" + classeLangueConfig.getString(ConfigCles.var_MotCle) + ":\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeMotsClesTrouvesActuel = classeMotsClesRecherche.find();
			while(classeMotsClesTrouvesActuel) {
				String classeMotCleValeur = classeMotsClesRecherche.group(1);
				classeMotsClesTrouvesActuel = classeMotsClesRecherche.find();
				if(!classeMotsCles.contains(classeMotCleValeur))
					classeMotsCles.add(classeMotCleValeur);
				classeMotsClesTrouves = true;
			}

			Matcher classeTrisRecherche = Pattern.compile("^" + classeLangueConfig.getString(ConfigCles.var_Tri) + "\\.([^:]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeTrisTrouvesActuel = classeTrisRecherche.find();
			while(classeTrisTrouvesActuel) {
				String classeTriOrdre = classeTrisRecherche.group(1);
				String classeTriVar = classeTrisRecherche.group(2);
				classeTrisTrouvesActuel = classeTrisRecherche.find();
				classeTrisOrdre.add(classeTriOrdre);
				classeTrisVar.add(classeTriVar);
				classeTrisTrouves = true;
			}

			String sqlString = regex("^Sql:\\s*(.*)$", classeCommentaire, 1);
			if(NumberUtils.isCreatable(sqlString)) {
				Integer sqlInteger = Integer.parseInt(sqlString);
				Integer sqlMigration = Math.abs(sqlInteger);
				Boolean sqlCreate = sqlInteger > 0;
				Boolean sqlDrop = sqlInteger < 0;
				indexerStockerSolr(classeDoc, "sqlMigration", sqlMigration);
				if(sqlCreate)
					indexerStockerSolr(classeDoc, "sqlCreate", sqlCreate);
				if(sqlDrop)
					indexerStockerSolr(classeDoc, "sqlDrop", sqlDrop);
			}

			Matcher classeMapRecherche = Pattern.compile("^Map\\.([^:]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeMapTrouveActuel = classeMapRecherche.find();
			while(classeMapTrouveActuel) {
				String classeMapCle = classeMapRecherche.group(1);
				String classeMapValeur = classeMapRecherche.group(2);
				String[] classeMapCleParts = StringUtils.split(classeMapCle, ".");
				if(classeMapCleParts.length == 2) {
					String classeMapCleType = classeMapCleParts[0];
					if("Integer".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Integer.parseInt(classeMapValeur));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("Double".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Double.parseDouble(classeMapValeur));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("Long".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Long.parseLong(classeMapValeur));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("Boolean".equals(classeMapCleType)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Boolean.parseBoolean(classeMapValeur));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("ZonedDateTime".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Date.from(ZonedDateTime.parse(classeMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("LocalDateTime".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Date.from(LocalDateTime.parse(classeMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("LocalDate".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Date.from(LocalDate.parse(classeMapValeur, DateTimeFormatter.ISO_OFFSET_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("LocalTime".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], classeMapValeur);
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else {
						indexerStockerSolr(classeDoc, classeMapCle, classeMapValeur);
					}
				}
				else {
					indexerStockerSolr(classeDoc, classeMapCle, classeMapValeur);
				}
				classeMapTrouveActuel = classeMapRecherche.find();
			}
		}

		SolrDocument classeSuperDoc = null;   
		if(classeEtendGen && StringUtils.isNotBlank(classeNomCompletSuperGenerique)) {
			ClasseParts classePartsSuperGenerique = ClasseParts.initClasseParts(this, classeNomCompletSuperGenerique, classeLangueNom);
			classePartsGenAjouter(classePartsSuperGenerique, classeLangueNom);

			if(StringUtils.startsWith(classeNomCanoniqueSuper, nomEnsembleDomaine) || StringUtils.startsWith(classeNomCanoniqueSuper, ClasseParts.NOM_ENSEMBLE_DOMAINE_COMPUTATE)) {
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1);
				rechercheSolr.addFilterQuery("classeNomCanonique_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classeNomCanoniqueSuperGenerique));
				rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
				rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
				QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList listeRecherche = reponseRecherche.getResults();
				if(listeRecherche.size() > 0) { 
					classeSuperDoc = listeRecherche.get(0);
				}
			} 
			else if(!StringUtils.contains(classeNomCanoniqueSuper, ".") && StringUtils.isNotBlank(classeNomCanoniqueSuper)) {
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1);
				rechercheSolr.addFilterQuery("classeNomCanonique_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classeNomCanoniqueSuperGenerique));
				rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
				rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
				QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList listeRecherche = reponseRecherche.getResults();
				if(listeRecherche.size() > 0) { 
					classeSuperDoc = listeRecherche.get(0);
				}
			}
		}

		List<String> classeSuperEcrireMethodes;
		List<String> classeEcrireMethodes = new ArrayList<>();
		if(classeSuperDoc != null) 
			classeSuperEcrireMethodes = (List<String>)classeSuperDoc.get("classeEcrireMethodes_stored_strings");
		else
			classeSuperEcrireMethodes = new ArrayList<>();

		for(String siteEcrireMethode : siteEcrireMethodes) {
			if(classeQdox.getMethodBySignature(siteEcrireMethode, new ArrayList<JavaType>()) != null
					|| classeQdox.getMethodBySignature(siteEcrireMethode + classeNomSimple, new ArrayList<JavaType>()) != null) {
				if(!classeEcrireMethodes.contains(siteEcrireMethode)) {
					indexerStockerListeSolr(classeDoc, "classeEcrireMethodes",  siteEcrireMethode);
					classeEcrireMethodes.add(siteEcrireMethode);
				}
			}
		}

		if(classeSuperDoc != null) {
			if(classeSuperEcrireMethodes != null) {
				for(String classeSuperEcrireMethode : classeSuperEcrireMethodes) {
					indexerStockerListeSolr(classeDoc, "classeSuperEcrireMethodes",  classeSuperEcrireMethode);
					if(!classeEcrireMethodes.contains(classeSuperEcrireMethode)) {
						indexerStockerListeSolr(classeDoc, "classeEcrireMethodes",  classeSuperEcrireMethode);
						classeEcrireMethodes.add(classeSuperEcrireMethode);
					}
				}
			}
			Boolean classeSuperPromesse = (Boolean)classeSuperDoc.get("classePromesse_stored_boolean");
			if(classeSuperPromesse)
				classePromesse = true;
		}

		if(classeDoc.getField("id") == null)
			classeDoc.addField("id", classeCle);  

		indexerStockerSolr(classeDoc, "partEstClasse", true);
		indexerStockerSolr(classeDoc, "partNumero", partNumero);

		String classeCodeSourceComplet = classeQdox.getCodeBlock();
		stockerSolr(classeLangueNom, classeDoc, "classeCodeSourceDebut", regex("([\\s\\S]*?class " + classeNomSimple + "[^\\{]*[;\\{])", classeCodeSourceComplet, 1));
		stockerSolr(classeLangueNom, classeDoc, "classeCodeSourceFin", "}\n");

		indexerStockerSolr(classeDoc, "classeLigneDebut", classeQdox.getLineNumber() - (classeCommentaire == null ? 0 : (classeCommentaire.split(REGEX_LIGNE).length + 1)));
		indexerStockerSolr(classeDoc, "classeLigneFin", classeQdox.getLineNumber());
		
		for(String classeImportation : classeQdox.getSource().getImports()) {
			ClasseParts classeImportationClasseParts = ClasseParts.initClasseParts(this, classeImportation, classeLangueNom);
			indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImportations", classeImportationClasseParts.nomCanonique(classeLangueNom));

			for(String langueNom : classeAutresLangues) {  
//				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classeImportationClasseParts, langueNom);
				indexerStockerListeSolr(langueNom, classeDoc, "classeImportations", classeImportationClasseParts.nomCanonique(langueNom));
			}
		}

		List<JavaMember> membresQdox = classeQdox.getMembers();
		for(JavaMember membreQdox : membresQdox) {  
			partNumero++;

			if(membreQdox instanceof JavaField) {    
				SolrInputDocument champDoc = classeDocClone.deepCopy();
				JavaField champQdox = (JavaField)membreQdox;
				String champCommentaire = champQdox.getComment();
				String champVar = champQdox.getName();
				String champCle = classeCheminAbsolu + "." + champVar;
				String champCodeSourceComplet = champQdox.getCodeBlock();
				String champCodeSource = StringUtils.substringBeforeLast(StringUtils.trim(regex("[\\s\\S]+?" + champVar + "\\s*=([\\s\\S]*)", champCodeSourceComplet, 1)), ";");
				champCodeSourceComplet = regex("^([\\s\\S]+?" + champVar + ")\\s*[=;]", champCodeSourceComplet, 1);
				if(!StringUtils.isBlank(champCodeSource))
					champCodeSource = " = " + champCodeSource;
				stockerSolr(classeLangueNom, champDoc, "champCodeSourceDebut", champCodeSourceComplet);
				stockerSolr(classeLangueNom, champDoc, "champCodeSourceFin", ";\n");
				String champString = regex("^String\\." + classeLangueNom + ":(.*)", champCommentaire);
				if(champString != null) {
					champCodeSource = "\"" + StringUtils.replace(StringUtils.replace(champString, "\\", "\\\\"), "\"", "\\\"") + "\"";
					indexerStockerSolr(classeLangueNom, champDoc, "champString", champString); 
				}
				indexerStockerSolr(champDoc, "champLigneDebut", champQdox.getLineNumber() - (champCommentaire == null ? 0 : (champCommentaire.split(REGEX_LIGNE).length + 1)));
				indexerStockerSolr(champDoc, "champLigneFin", champQdox.getLineNumber() + (champCodeSource == null ? 0 : champCodeSource.split(REGEX_LIGNE).length));

				// Champs Solr du champ. 

				indexerStockerSolr(classeLangueNom, champDoc, "champVar", champVar); 
				indexerStockerSolr(champDoc, "partEstChamp", true);
				indexerStockerSolr(champDoc, "partNumero", partNumero);
				indexerStockerSolr(champDoc, "champEstPublic", champQdox.isPublic()); 
				indexerStockerSolr(champDoc, "champEstProtege", champQdox.isProtected()); 
				indexerStockerSolr(champDoc, "champEstPrive", champQdox.isPrivate()); 
				indexerStockerSolr(champDoc, "champEstStatique", champQdox.isStatic()); 
				indexerStockerSolr(champDoc, "champEstFinale", champQdox.isFinal()); 
				indexerStockerSolr(champDoc, "champEstAbstrait", champQdox.isAbstract()); 
				indexerStockerSolr(champDoc, "champEstNatif", champQdox.isNative()); 
				indexerStockerSolr(champDoc, "champTraduire", !regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Traduire) + ": \\s*(false)$", champCommentaire));
	
				/////////////////
				// Annotations //
				/////////////////
				List<JavaAnnotation> annotations = champQdox.getAnnotations(); 
				ArrayList<String> annotationsLangue = new ArrayList<String>();
				Boolean champEstTest = false;
				Boolean champEstSubstitue = false;
				for(JavaAnnotation annotation : annotations) {
					String champAnnotationLangue = annotation.getType().getCanonicalName();
					indexerStockerListeSolr(classeLangueNom, champDoc, "champAnnotation", champAnnotationLangue); 

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						champEstTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						champEstSubstitue = true;
					}
				}
				indexerStockerSolr(champDoc, "champEstTest", champEstTest); 
				indexerStockerSolr(champDoc, "champEstSubstitue", champEstSubstitue); 

				ClasseParts champClasseParts = ClasseParts.initClasseParts(this, champQdox.getType(), classeLangueNom);
	
				stockerRegexCommentaires(classeLangueNom, champDoc, "champCommentaire", champCommentaire);
				stockerSolr(classeLangueNom, champDoc, "champNomSimpleComplet", champClasseParts.nomSimpleComplet(classeLangueNom));
				String champNomCanoniqueComplet = stockerSolr(classeLangueNom, champDoc, "champNomCanoniqueComplet", champClasseParts.nomCanoniqueComplet(classeLangueNom));
				stockerSolr(classeLangueNom, champDoc, "champCodeSource", champCodeSource);
				champDoc.addField("id", champNomCanoniqueComplet + " " + champCle);

				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) { 
//						ClasseParts champClassePartsLangue = ClasseParts.initClasseParts(this, champClasseParts, langueNom);
						String champVarLangue = regex("^Var\\." + langueNom + ": (.*)", champCommentaire);
						champVarLangue = champVarLangue == null ? champVar : champVarLangue;
						String champCodeSourceLangue = regexRemplacerTout(champCommentaire, champCodeSource, langueNom);
						String champStringLangue = regex("^String\\." + langueNom + ":(.*)", champCommentaire);
						if(champStringLangue != null) {
							champCodeSourceLangue = "\"" + StringUtils.replace(StringUtils.replace(champStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\"";
							indexerStockerSolr(langueNom, champDoc, "champString", champString); 
						}
	
						indexerStockerSolr(langueNom, champDoc, "champVar", champVarLangue); 
						stockerSolr(langueNom, champDoc, "champNomSimpleComplet", champClasseParts.nomSimpleComplet(langueNom));
						stockerSolr(langueNom, champDoc, "champNomCanoniqueComplet", champClasseParts.nomCanoniqueComplet(langueNom));
						stockerRegexCommentaires(langueNom, champDoc, "champCommentaire", champCommentaire);
						stockerSolr(langueNom, champDoc, "champCodeSource", champCodeSourceLangue);
					}  
				}

				clientSolrComputate.add(champDoc); 
			}
			else if(membreQdox instanceof JavaConstructor) { 
				SolrInputDocument constructeurDoc = classeDocClone.deepCopy();
				JavaConstructor constructeurQdox = (JavaConstructor)membreQdox;
				String constructeurCommentaire = constructeurQdox.getComment();
				List<JavaParameter> constructeurParamsQdox = constructeurQdox.getParameters();
				List<JavaAnnotation> constructeurAnnotations = constructeurQdox.getAnnotations(); 
				List<JavaClass> constructeurExceptionsQdox = constructeurQdox.getExceptions();
				for(Integer constructeurParamNum = 1; constructeurParamNum <= constructeurParamsQdox.size(); constructeurParamNum++) {
					JavaParameter constructeurParamQdox = constructeurParamsQdox.get(constructeurParamNum - 1);
					String constructeurParamVar = constructeurParamQdox.getName();
					stockerListeSolr(classeLangueNom, constructeurDoc, "constructeurParamsVar", constructeurParamVar);
					ClasseParts constructeurParamClasseParts = ClasseParts.initClasseParts(this, constructeurParamQdox.getJavaClass(), classeLangueNom);
					classePartsGenAjouter(constructeurParamClasseParts, classeLangueNom);
					stockerListeSolr(classeLangueNom, constructeurDoc, "constructeurParamsNomSimpleComplet", constructeurParamClasseParts.nomSimpleComplet(classeLangueNom));
					stockerListeSolr(constructeurDoc, "constructeurParamsArgsVariables", constructeurParamQdox.isVarArgs());
					if(classeTraduire) {
						for(String langueNom : classeAutresLangues) { 
							String constructeurParamVarLangue = regex("^(constructeur)?Param" + constructeurParamNum + "\\.var\\." + langueNom + ": (.*)", constructeurCommentaire);
							if(constructeurParamVarLangue == null)
								constructeurParamVarLangue = constructeurParamVar;
//							ClasseParts constructeurParamClassePartsLangue = ClasseParts.initClasseParts(this, constructeurParamClasseParts, langueNom);
	
//							classePartsGenAjouter(constructeurParamClasseParts, classeLangueNom);
							stockerListeSolr(langueNom, constructeurDoc, "constructeurParamsNomSimpleComplet", constructeurParamClasseParts.nomSimpleComplet(langueNom));
							stockerListeSolr(langueNom, constructeurDoc, "constructeurParamsVar", constructeurParamVarLangue);
						}  
					}
				}
				for(JavaAnnotation constructeurAnnotation : constructeurAnnotations) {
//					String constructeurAnnotationBlocCode = stockerListeSolr(classeLangueNom, constructeurDoc, "constructeurAnnotationBlocCode", annotation.toString());
				}
				for(JavaClass constructeurExceptionQdox : constructeurExceptionsQdox) {
					String constructeurExceptionNomSimpleComplet = StringUtils.substringAfterLast(constructeurExceptionQdox.getCanonicalName(), ".");
					stockerListeSolr(constructeurDoc, "constructeurExceptionNomSimpleComplet", constructeurExceptionNomSimpleComplet);
				}
				String constructeurCle = classeCheminAbsolu + "." + classeNomSimple + "(";

				for(int i = 0; i < constructeurParamsQdox.size(); i++) {
					JavaParameter paramQdox = constructeurParamsQdox.get(i);
					if(i > 0)
						constructeurCle += ", ";
					constructeurCle += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
				}
				constructeurCle += ")"; 

				constructeurDoc.addField("id", constructeurCle);
				indexerStockerSolr(constructeurDoc, "partEstConstructeur", true);
				indexerStockerSolr(constructeurDoc, "partNumero", partNumero);

				indexerStockerSolr(constructeurDoc, "constructeurEstPublic", constructeurQdox.isPublic());
				indexerStockerSolr(constructeurDoc, "constructeurEstProtege", constructeurQdox.isProtected());
				indexerStockerSolr(constructeurDoc, "constructeurEstPrive", constructeurQdox.isPrivate());
				indexerStockerSolr(constructeurDoc, "constructeurEstStatique", constructeurQdox.isStatic());
				indexerStockerSolr(constructeurDoc, "constructeurEstFinale", constructeurQdox.isFinal());
				indexerStockerSolr(constructeurDoc, "constructeurEstAbstrait", constructeurQdox.isAbstract());
				indexerStockerSolr(constructeurDoc, "constructeurEstNatif", constructeurQdox.isNative());
				stockerRegexCommentaires(classeLangueNom, constructeurDoc, "constructeurCommentaire", constructeurCommentaire);

				String constructeurCodeSource = constructeurQdox.getSourceCode();
				String constructeurCodeSourceComplet = constructeurQdox.getCodeBlock();
				String src = regex("([\\s\\S]*?\\w+\\s*\\([^\\)]*\\)[^;\\{]*[;\\{])", constructeurCodeSourceComplet, 1);
				if(src.endsWith(";") && !constructeurQdox.isAbstract())
					src = StringUtils.substringBeforeLast(src, ";") + " {";
				stockerSolr(classeLangueNom, constructeurDoc, "constructeurCodeSourceDebut", src);
				stockerSolr(classeLangueNom, constructeurDoc, "constructeurCodeSourceFin", "}\n");
				String constructeurCodeSourceLangue = constructeurCodeSource;
				ArrayList<String> remplacerClesLangue = regexListe("^r." + classeLangueNom + "\\s*=\\s*(.*)\\n.*", constructeurCommentaire);
				ArrayList<String> remplacerValeursLangue = regexListe("^r." + classeLangueNom + "\\s*=\\s*.*\\n(.*)", constructeurCommentaire);
				for(int i = 0; i < remplacerClesLangue.size(); i++) {
					String cle = remplacerClesLangue.get(i);
					String valeur = remplacerValeursLangue.get(i);
					StringUtils.replace(constructeurCodeSourceLangue, cle, valeur);
				}
				stockerSolr(classeLangueNom, constructeurDoc, "constructeurCodeSource", constructeurCodeSourceLangue);

				indexerStockerSolr(constructeurDoc, "constructeurLigneDebut", constructeurQdox.getLineNumber() - (constructeurCommentaire == null ? 0 : (constructeurCommentaire.split(REGEX_LIGNE).length + 1)));
				indexerStockerSolr(constructeurDoc, "constructeurLigneFin", constructeurQdox.getLineNumber() + (constructeurCodeSource == null ? 0 : (constructeurCodeSource.split(REGEX_LIGNE).length - 1)));

				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						constructeurCodeSourceLangue = regexRemplacerTout(constructeurCommentaire, constructeurCodeSource, langueNom);
						stockerSolr(langueNom, constructeurDoc, "constructeurCodeSource", constructeurCodeSourceLangue);
						stockerRegexCommentaires(langueNom, constructeurDoc, "constructeurCommentaire", constructeurCommentaire);
					} 
				}

				clientSolrComputate.add(constructeurDoc); 
			}
			else if(membreQdox instanceof JavaMethod) {   
				JavaMethod methodeQdox = (JavaMethod)membreQdox;
				String methodeCommentaire = methodeQdox.getComment();
				Boolean ignorer = "true".equals(regex("ignorer: (.*)", methodeCommentaire));
				if(!ignorer) {
					JavaClass methodeClasseQdoxRetour = methodeQdox.getReturns();
					String methodeNomCanoniqueRetourComplet = null;
					String methodeNomCanoniqueRetour = null;
					JavaClass classeQdoxRetour = methodeQdox.getReturns();
					List<JavaParameter> methodeParamsQdox = methodeQdox.getParameters();
		
					List<JavaAnnotation> annotations = methodeQdox.getAnnotations(); 
					ArrayList<String> annotationsLangue = new ArrayList<String>();
					Boolean methodeEstTest = false;
					Boolean methodeEstSubstitue = false;
					String methodeVar = methodeQdox.getName();
					for(JavaAnnotation annotation : annotations) {
						String methodeAnnotationLangue = annotation.getType().getCanonicalName();
	
						if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
							methodeEstTest = true;
						}
						if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
							methodeEstSubstitue = true;
						}
					}

					List<JavaClass> methodeExceptionsQdox = methodeQdox.getExceptions();
	
					if(classeEtendGen && !methodeEstSubstitue && !methodeQdox.isStatic() && !methodeQdox.isFinal() && methodeQdox.getDeclaringClass().equals(classeQdox) 
							&& methodeQdox.isProtected() && methodeParamsQdox.size() == 1 && classeQdoxRetour.isVoid()
							&& StringUtils.startsWith(methodeQdox.getName(), "_")) {

						// est Entite. 
						SolrInputDocument entiteDoc = classeDocClone.deepCopy();
						String entiteVar = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVar", StringUtils.substringAfter(methodeQdox.getName(), "_"));
						indexerStockerListeSolr(classeLangueNom, classeDoc, "classeEntiteVars", entiteVar);
						String entiteVarCapitalise = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVarCapitalise", StringUtils.capitalize(entiteVar));
						JavaClass entiteClasseQdox = methodeParamsQdox.get(0).getJavaClass();
						ClasseParts entiteClasseParts = ClasseParts.initClasseParts(this, entiteClasseQdox, classeLangueNom);
						Boolean entiteCouverture = false;
						Boolean entitePromesse = false;

						if(classePartsCouverture == null)
							throw new RuntimeException(String.format("%s %s %s %s %s. ", classeLangueConfig.getString(ConfigCles.var_classe), classeLangueConfig.getString(ConfigCles.var_Couverture), classeLangueConfig.getString(ConfigCles.var_manquante), classeLangueConfig.getString(ConfigCles.var_dans), cheminSrcMainJava));
						if(StringUtils.equalsAny(entiteClasseParts.nomSimple(classeLangueNom), "Promise", classePartsCouverture.nomSimple(classeLangueNom))) {
							if(StringUtils.equals(entiteClasseParts.nomSimple(classeLangueNom), "Promise")) {
								entitePromesse = true;
								classePromesse = true;
							}
							entiteClasseParts = ClasseParts.initClasseParts(this, entiteClasseParts.nomCanoniqueGenerique(classeLangueNom), classeLangueNom);
							entiteCouverture = true;
							classeContientCouverture = true;
						}

						classePartsGenAjouter(entiteClasseParts, classeLangueNom);
						classePartsGenPageAjouter(entiteClasseParts, classeLangueNom);
						List<String> entiteNomsCanoniquesSuperEtMoiSansGen = new ArrayList<String>();
						if(entiteClasseParts.getValeurGenerique() != null) {
							ClasseParts classePartsGenerique = ClasseParts.initClasseParts(this, entiteClasseParts.nomCanoniqueGenerique(classeLangueNom), classeLangueNom);
							classePartsGenAjouter(classePartsGenerique, classeLangueNom);
							classePartsGenPageAjouter(classePartsGenerique, classeLangueNom);
							classePartsGenAjouter(entiteClasseParts, classeLangueNom);

							if(classePartsGenerique.getDocumentSolr() != null) {
								List<String> entiteClassesSuperEtMoiSansGen = (List<String>)classePartsGenerique.getDocumentSolr().get("entiteClassesSuperEtMoiSansGen_stored_strings");
								if(entiteClassesSuperEtMoiSansGen != null) {
									for(String nomCanonique : entiteClassesSuperEtMoiSansGen) {
										entiteNomsCanoniquesSuperEtMoiSansGen.add(nomCanonique);
										indexerStockerListeSolr(entiteDoc, "entiteClassesSuperEtMoiSansGen", nomCanonique); 
									}
								}
							}
						}
						else if(entiteClasseParts != null && entiteClasseParts.getDocumentSolr() != null) {

							List<String> entiteClassesSuperEtMoiSansGen = (List<String>)entiteClasseParts.getDocumentSolr().get("entiteClassesSuperEtMoiSansGen_stored_strings");
							if(entiteClassesSuperEtMoiSansGen != null) {
								for(String nomCanonique : entiteClassesSuperEtMoiSansGen) {
									entiteNomsCanoniquesSuperEtMoiSansGen.add(nomCanonique);
									indexerStockerListeSolr(entiteDoc, "entiteClassesSuperEtMoiSansGen", nomCanonique); 
								}
							}
						}

						indexerStockerSolr(entiteDoc, "entitePromesse", entitePromesse);
						indexerStockerSolr(entiteDoc, "entiteCouverture", entiteCouverture);
						Boolean entiteInitialise = indexerStockerSolr(entiteDoc, "entiteInitialise", !entiteVar.endsWith("_") && BooleanUtils.isTrue(entiteClasseParts.getEtendGen()));

						String entiteNomCanonique = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCanonique", entiteClasseParts.nomCanonique(classeLangueNom));
						String entiteNomSimple = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimple", entiteClasseParts.nomSimple(classeLangueNom));
						String entiteNomCompletGenerique = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCompletGenerique", entiteClasseParts.nomCanoniqueGenerique(classeLangueNom));
						String entiteNomCanoniqueGenerique = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCanoniqueGenerique", entiteClasseParts.nomCanoniqueGenerique(classeLangueNom));
						String entiteNomSimpleGenerique = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimpleGenerique", entiteClasseParts.nomSimpleGenerique(classeLangueNom));
						String entiteNomCanoniqueActuel = entiteNomCanoniqueGenerique == null ? entiteNomCanonique : entiteNomCanoniqueGenerique;
						String entiteNomSimpleActuel = entiteNomSimpleGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique;
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCanoniqueComplet", entiteClasseParts.nomCanoniqueComplet(classeLangueNom));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimpleComplet", entiteClasseParts.nomSimpleComplet(classeLangueNom));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimpleCompletGenerique", entiteClasseParts.nomSimpleGenerique(classeLangueNom));

						if("Point".equals(entiteNomSimple)) {
							classePartsGenAjouter(classePartsPointSerializer, classeLangueNom);
							classePartsGenAjouter(classePartsPointDeserializer, classeLangueNom);
						}

						JavaMethod entiteSetter = null;
						try {
							entiteSetter = classeQdox.getMethodBySignature("set" + entiteVarCapitalise, new ArrayList<JavaType>() {{ add(classeQdoxString); }}, true);
						} catch (Exception e) {
						}
						
						JavaClass entiteClasseQdoxBase = null;
						JavaClass entiteClasseSuperQdox = entiteClasseQdox.getSuperJavaClass();
						if(entiteClasseSuperQdox != null) {
							String entiteNomCanoniqueSuperQdox = entiteClasseSuperQdox.getCanonicalName();
							if(entiteNomCanoniqueSuperQdox.endsWith("Gen")) {
								entiteClasseQdoxBase = entiteClasseSuperQdox.getSuperJavaClass();
							}
						}
						
						String entiteNomCanoniqueBase = null;
						if(entiteClasseQdoxBase != null) {
							String s = entiteClasseQdoxBase.getCanonicalName();
							if(s.contains("."))
								entiteNomCanoniqueBase = s;
						}
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCanoniqueBase", entiteNomCanoniqueBase);
						if(entiteCouverture || entitePromesse)
							entiteClasseQdox = bricoleur.getClassByName(entiteNomCanonique);

						boolean entiteEtendPagePart = classePartsPagePart != null && entiteNomsCanoniquesSuperEtMoiSansGen.contains(classePartsPagePart.nomCanonique(classeLangueNom));
								
						for(String siteEcrireMethode : siteEcrireMethodes) {
							if(entiteClasseQdox.getMethodBySignature(siteEcrireMethode, new ArrayList<JavaType>()) != null
									|| entiteClasseQdox.getMethodBySignature(siteEcrireMethode + classeNomSimple, new ArrayList<JavaType>()) != null
									|| entiteEtendPagePart && siteEcrireMethode.equals("htmlBody"))
								indexerStockerListeSolr(entiteDoc, "entiteEcrireMethodes",  siteEcrireMethode);
							if(entiteEtendPagePart && siteEcrireMethode.equals("htmlBody")) {
								for(String langueNom : toutesLangues) {
									indexerStockerListeSolr(langueNom, classeDoc, "classeMethodeVars", "htmlBody" + entiteVarCapitalise);
								}
							}
						}
						
						String entiteNomSimpleBase = null;
						if(StringUtils.isNotEmpty(entiteNomCanoniqueBase)) {
							entiteNomSimpleBase = StringUtils.substringAfterLast(entiteNomCanoniqueBase, ".");
						}
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimpleBase", entiteNomSimpleBase);
						
						String entiteVarParam = methodeParamsQdox.get(0).getName();
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVarParam", entiteVarParam);
						
						String entiteVarCouverture = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVarCouverture", entiteVar + "Couverture");

						Boolean entiteInitLoin = indexerStockerSolr(entiteDoc, "entiteInitLoin", !entiteVar.endsWith("_"));

						if(entiteNomsCanoniquesSuperEtMoiSansGen.size() > 0) {

							SolrQuery rechercheSolrMethodeAvant = new SolrQuery();   
							rechercheSolrMethodeAvant.setQuery("*:*");
							rechercheSolrMethodeAvant.setRows(10);
							String fqMethodeAvant = "(" + entiteNomsCanoniquesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(classeLangueConfig.getString(ConfigCles.var_avant) + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolrMethodeAvant.addFilterQuery("entiteClassesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
							rechercheSolrMethodeAvant.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
							rechercheSolrMethodeAvant.addFilterQuery("partEstMethode_indexed_boolean:true");
							rechercheSolrMethodeAvant.addFilterQuery("methodeVar_" + classeLangueNom + "_indexed_string:" + fqMethodeAvant);
							QueryResponse reponseRechercheMethodeAvant = clientSolrComputate.query(rechercheSolrMethodeAvant);
							SolrDocumentList listeRechercheMethodeAvant = reponseRechercheMethodeAvant.getResults();
	
							for(SolrDocument documentSolr : listeRechercheMethodeAvant) {
								String methodeVarActuel = (String)documentSolr.get("methodeVar_" + classeLangueNom + "_stored_string");
								String methodeClasseNomCanonique = (String)documentSolr.get("classeNomCanonique_" + classeLangueNom + "_stored_string");
								List<String> methodeParamsNomSimpleComplet = (List<String>)documentSolr.get("methodeParamsNomSimpleComplet_" + classeLangueNom + "_stored_strings");
								String methodeParamNomSimpleComplet = methodeParamsNomSimpleComplet.get(0);
								List<String> methodeParamsVar = (List<String>)documentSolr.get("methodeParamsVar_" + classeLangueNom + "_stored_strings");
								String methodeParamVar = methodeParamsVar.get(0);
								Boolean entiteMethodesAvantEcrire = (StringUtils.equals(methodeClasseNomCanonique, classeNomCanonique)) && !classeMethodesEcrites.contains(methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantVisibilite", BooleanUtils.isTrue((Boolean)documentSolr.get("methodeEstPublic_stored_boolean")) ? "public" : "protected");
								stockerListeSolr(entiteDoc, "entiteMethodesAvantNomParam", methodeParamsVar.size() > 1);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantEcrire", entiteMethodesAvantEcrire);
								classeMethodesEcrites.add(methodeVarActuel);
								List<String> methodeParamsNomCanonique = (List<String>)documentSolr.get("methodeParamsNomCanonique_" + classeLangueNom + "_stored_strings");
								if(methodeParamsNomCanonique != null) {
									String methodeParamNomCanonique = methodeParamsNomCanonique.get(0);
									classePartsGenAjouter(ClasseParts.initClasseParts(this, methodeParamNomCanonique, classeLangueNom), classeLangueNom);
								}
								for(String langueNom : toutesLangues) {  
									methodeVarActuel = (String)documentSolr.get("methodeVar_" + langueNom + "_stored_string");
									methodeClasseNomCanonique = (String)documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string");
									methodeParamsNomSimpleComplet = (List<String>)documentSolr.get("methodeParamsNomSimpleComplet_" + langueNom + "_stored_strings");
									methodeParamNomSimpleComplet = methodeParamsNomSimpleComplet.get(0);
									methodeParamsVar = (List<String>)documentSolr.get("methodeParamsVar_" + langueNom + "_stored_strings");
									methodeParamVar = methodeParamsVar.get(0);
									stockerListeSolr(langueNom, entiteDoc, "entiteMethodesAvantVar", methodeVarActuel);
									stockerListeSolr(langueNom, entiteDoc, "entiteMethodesAvantParamVar", methodeParamVar);
									stockerListeSolr(langueNom, entiteDoc, "entiteMethodesAvantParamNomSimple", methodeParamNomSimpleComplet);
								}
							}
	
							SolrQuery rechercheSolrMethodeApres = new SolrQuery();   
							rechercheSolrMethodeApres.setQuery("*:*");
							rechercheSolrMethodeApres.setRows(10);
							String fqMethodeApres = "(" + entiteNomsCanoniquesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(classeLangueConfig.getString(ConfigCles.var_apres) + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolrMethodeApres.addFilterQuery("entiteClassesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
							rechercheSolrMethodeApres.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
							rechercheSolrMethodeApres.addFilterQuery("partEstMethode_indexed_boolean:true");
							rechercheSolrMethodeApres.addFilterQuery("methodeVar_" + classeLangueNom + "_indexed_string:" + fqMethodeApres);
							QueryResponse reponseRechercheMethodeApres = clientSolrComputate.query(rechercheSolrMethodeApres);
							SolrDocumentList listeRechercheMethodeApres = reponseRechercheMethodeApres.getResults();
	
							for(SolrDocument documentSolr : listeRechercheMethodeApres) {
								String methodeVarActuel = (String)documentSolr.get("methodeVar_" + classeLangueNom + "_stored_string");
								String methodeClasseNomCanonique = (String)documentSolr.get("classeNomCanonique_" + classeLangueNom + "_stored_string");
								List<String> methodeParamsNomSimpleComplet = (List<String>)documentSolr.get("methodeParamsNomSimpleComplet_" + classeLangueNom + "_stored_strings");
								String methodeParamNomSimpleComplet = methodeParamsNomSimpleComplet.get(0);
								List<String> methodeParamsVar = (List<String>)documentSolr.get("methodeParamsVar_" + classeLangueNom + "_stored_strings");
								String methodeParamVar = methodeParamsVar.get(0);
								stockerListeSolr(entiteDoc, "entiteMethodesApresVisibilite", BooleanUtils.isTrue((Boolean)documentSolr.get("methodeEstPublic_stored_boolean")) ? "public" : "protected");
								stockerListeSolr(entiteDoc, "entiteMethodesApresVar", methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesApresParamVar", methodeParamVar);
								stockerListeSolr(entiteDoc, "entiteMethodesApresParamNomSimple", methodeParamNomSimpleComplet);
								stockerListeSolr(entiteDoc, "entiteMethodesApresNomParam", methodeParamsVar.size() > 1);
								Boolean entiteMethodesAvantEcrire = (StringUtils.equals(methodeClasseNomCanonique, classeNomCanonique)) && !classeMethodesEcrites.contains(methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantEcrire", entiteMethodesAvantEcrire);
								classeMethodesEcrites.add(methodeVarActuel);
								List<String> methodeParamsNomCanonique = (List<String>)documentSolr.get("methodeParamsNomCanonique_" + classeLangueNom + "_stored_strings");
								if(methodeParamsNomCanonique != null) {
									String methodeParamNomCanonique = methodeParamsNomCanonique.get(0);
									classePartsGenAjouter(ClasseParts.initClasseParts(this, methodeParamNomCanonique, classeLangueNom), classeLangueNom);
								}
								for(String langueNom : toutesLangues) {  
									methodeVarActuel = (String)documentSolr.get("methodeVar_" + langueNom + "_stored_string");
									methodeClasseNomCanonique = (String)documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string");
									methodeParamsNomSimpleComplet = (List<String>)documentSolr.get("methodeParamsNomSimpleComplet_" + langueNom + "_stored_strings");
									methodeParamNomSimpleComplet = methodeParamsNomSimpleComplet.get(0);
									methodeParamsVar = (List<String>)documentSolr.get("methodeParamsVar_" + langueNom + "_stored_strings");
									methodeParamVar = methodeParamsVar.get(0);
									stockerListeSolr(langueNom, entiteDoc, "entiteMethodesApresVar", methodeVarActuel);
									stockerListeSolr(langueNom, entiteDoc, "entiteMethodesApresParamVar", methodeParamVar);
									stockerListeSolr(langueNom, entiteDoc, "entiteMethodesApresParamNomSimple", methodeParamNomSimpleComplet);
								}
							}
						}

						if(methodeCommentaire != null) {

							Matcher entiteValsRecherche = Pattern.compile("^Val(:([^:\n]+):)?\\.(\\w+)(\\.([^:\n]+))?:(.*)", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteValsTrouves = entiteValsRecherche.find();
							while(entiteValsTrouves) {
								String entiteValVar = entiteValsRecherche.group(3);
								String entiteValLangue = entiteValsRecherche.group(5);
								String entiteValCode = entiteValsRecherche.group(2);
								String entiteValValeur = entiteValsRecherche.group(6);
								if(entiteValCode == null)
									entiteValCode = "";
								if(entiteValLangue == null) {
									stockerListeSolr(entiteDoc, "entiteValsVar", entiteValVar);
									stockerListeSolr(entiteDoc, "entiteValsLangue", classeLangueNom);
									stockerListeSolr(entiteDoc, "entiteValsCode", entiteValCode);
									stockerListeSolr(entiteDoc, "entiteValsValeur", entiteValValeur);
								}
								else {
									stockerListeSolr(entiteDoc, "entiteValsVar", entiteValVar);
									stockerListeSolr(entiteDoc, "entiteValsLangue", entiteValLangue);
									stockerListeSolr(entiteDoc, "entiteValsCode", entiteValCode);
									stockerListeSolr(entiteDoc, "entiteValsValeur", entiteValValeur);
								}
								entiteValsTrouves = entiteValsRecherche.find();
							}

							Matcher entiteOptionsRecherche = Pattern.compile("^Option\\.(\\w+)\\.([^:\\n]+)(:([^:\\n]+))?(:([^\\n]+))?", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteOptionsTrouves = entiteOptionsRecherche.find();
							while(entiteOptionsTrouves) {
								String entiteOptionLangue = entiteOptionsRecherche.group(1);
								String entiteOptionVar = entiteOptionsRecherche.group(2);
								String entiteOptionDescription = entiteOptionsRecherche.group(4);
								String entiteOptionValeurs = entiteOptionsRecherche.group(6);
								if(StringUtils.isBlank(entiteOptionDescription))
									entiteOptionDescription = entiteOptionVar;
								stockerListeSolr(entiteDoc, "entiteOptionsVar", entiteOptionVar);
								stockerListeSolr(entiteDoc, "entiteOptionsLangue", entiteOptionLangue);
								stockerListeSolr(entiteDoc, "entiteOptionsDescription", entiteOptionDescription);
								stockerListeSolr(entiteDoc, "entiteOptionsValeurs", StringUtils.isNotBlank(entiteOptionValeurs) ? entiteOptionValeurs : "");
								entiteOptionsTrouves = entiteOptionsRecherche.find();
							}
							if(entiteOptionsTrouves)
								stockerSolr(entiteDoc, "entiteOptions", true);

							Matcher entiteMotsClesRecherche = Pattern.compile("^" + classeLangueConfig.getString(ConfigCles.var_MotCle) + ":\\s*(.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteMotsClesTrouves = entiteMotsClesRecherche.find();
							boolean entiteMotsClesTrouvesActuel = entiteMotsClesTrouves;
							while(entiteMotsClesTrouvesActuel) {
								String entiteMotCleValeur = entiteMotsClesRecherche.group(1);
								indexerStockerListeSolr(entiteDoc, "entiteMotsCles", entiteMotCleValeur);
								entiteMotsClesTrouves = true;
								entiteMotsClesTrouvesActuel = entiteMotsClesRecherche.find();
								if(!classeMotsCles.contains(entiteMotCleValeur))
									classeMotsCles.add(entiteMotCleValeur);
								classeMotsClesTrouves = true;
							}
							indexerStockerSolr(entiteDoc, "entiteMotsClesTrouves", entiteMotsClesTrouves); 

							Matcher entiteFacetsRecherche = Pattern.compile("^Facet:\\s*(.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteFacetsTrouves = entiteFacetsRecherche.find();
							boolean entiteFacetsTrouvesActuel = entiteFacetsTrouves;
							while(entiteFacetsTrouvesActuel) {
								String entiteFacetValeur = entiteFacetsRecherche.group(1);
								indexerStockerListeSolr(entiteDoc, "entiteFacets", entiteFacetValeur);
								entiteFacetsTrouves = true;
								entiteFacetsTrouvesActuel = entiteFacetsRecherche.find();
							}
							indexerStockerSolr(entiteDoc, "entiteFacetsTrouves", entiteFacetsTrouves); 

							String sqlString = regex("^Sql:\\s*(.*)$", methodeCommentaire, 1);
							if(NumberUtils.isCreatable(sqlString)) {
								Integer sqlInteger = Integer.parseInt(sqlString);
								Integer sqlMigration = Math.abs(sqlInteger);
								Boolean sqlCreate = sqlInteger > 0;
								Boolean sqlDrop = sqlInteger < 0;
								indexerStockerSolr(entiteDoc, "sqlMigration", sqlMigration);
								if(sqlCreate)
									indexerStockerSolr(entiteDoc, "sqlCreate", sqlCreate);
								if(sqlDrop)
									indexerStockerSolr(entiteDoc, "sqlDrop", sqlDrop);
							}

							Matcher entiteMapRecherche = Pattern.compile("^Map.([^:]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteMapTrouve = entiteMapRecherche.find();
							boolean entiteMapTrouveActuel = entiteMapTrouve;
							while(entiteMapTrouveActuel) {
								String entiteMapCle = entiteMapRecherche.group(1);
								String entiteMapValeur = entiteMapRecherche.group(2);
								String[] entiteMapCleParts = StringUtils.split(entiteMapCle, ".");
								if(entiteMapCleParts.length == 2) {
									String entiteMapCleType = entiteMapCleParts[0];
									if("Integer".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Integer.parseInt(entiteMapValeur));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("Double".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Double.parseDouble(entiteMapValeur));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("Long".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Long.parseLong(entiteMapValeur));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("Boolean".equals(entiteMapCleType)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Boolean.parseBoolean(entiteMapValeur));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("ZonedDateTime".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Date.from(ZonedDateTime.parse(entiteMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("LocalDateTime".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Date.from(LocalDateTime.parse(entiteMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("LocalDate".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Date.from(LocalDate.parse(entiteMapValeur, DateTimeFormatter.ISO_OFFSET_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("LocalTime".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], entiteMapValeur);
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else {
										indexerStockerSolr(entiteDoc, entiteMapCle, entiteMapValeur);
									}
								}
								else {
									indexerStockerSolr(entiteDoc, entiteMapCle, entiteMapValeur);
								}
								entiteMapTrouveActuel = entiteMapRecherche.find();
							}
						}

						indexerStockerSolr(entiteDoc, "entiteContientRequeteSite", entiteClasseParts != null && entiteClasseParts.getDocumentSolr() != null && (Boolean)entiteClasseParts.getDocumentSolr().get("classeContientRequeteSite_stored_boolean"));
						indexerStockerSolr(entiteDoc, "entiteExact", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Exact) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteClePrimaire = indexerStockerSolr(entiteDoc, "entiteClePrimaire", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_ClePrimaire) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteInheritClePrimaire = indexerStockerSolr(entiteDoc, "entiteInheritClePrimaire", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_InheritClePrimaire) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteSauvegardes = indexerStockerSolr(entiteDoc, "entiteSauvegardes", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Sauvegardes) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteCleUnique = indexerStockerSolr(entiteDoc, "entiteCleUnique", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_CleUnique) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteCrypte = indexerStockerSolr(entiteDoc, "entiteCrypte", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Crypte) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteSuggere = indexerStockerSolr(entiteDoc, "entiteSuggere", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Suggere) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteVarUrlId = indexerStockerSolr(entiteDoc, "entiteVarUrlId", regexTrouve("^VarUrlId:\\s*(true)$", methodeCommentaire));
						Boolean entiteVarUrlPk = indexerStockerSolr(entiteDoc, "entiteVarUrlPk", regexTrouve("^VarUrlPk:\\s*(true)$", methodeCommentaire));
						Boolean entiteVarUrlApi = indexerStockerSolr(entiteDoc, "entiteVarUrlApi", regexTrouve("^VarUrlApi:\\s*(true)$", methodeCommentaire));
						String entiteVarUrl = regex("^" + classeLangueConfig.getString(ConfigCles.var_VarUrl) + ":\\s*(.*)$", methodeCommentaire);
						if(entiteVarUrl != null)
							indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVarUrl", entiteVarUrl);
						Boolean entiteVarId = indexerStockerSolr(entiteDoc, "entiteVarId", regexTrouve("^VarId:\\s*(true)$", methodeCommentaire));
						Boolean entiteVarTitre = indexerStockerSolr(entiteDoc, "entiteVarTitre", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_VarTitre) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteVarH1 = indexerStockerSolr(entiteDoc, "entiteVarH1", regexTrouve("^VarH1:\\s*(true)$", methodeCommentaire));
						Boolean entiteVarH2 = indexerStockerSolr(entiteDoc, "entiteVarH2", regexTrouve("^VarH2:\\s*(true)$", methodeCommentaire));
						Boolean entiteVarH3 = indexerStockerSolr(entiteDoc, "entiteVarH3", regexTrouve("^VarH3:\\s*(true)$", methodeCommentaire));
						Boolean entiteVarDescription = indexerStockerSolr(entiteDoc, "entiteVarDescription", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_VarDescription) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteVarImageUrl = indexerStockerSolr(entiteDoc, "entiteVarImageUrl", regexTrouve("^VarImageUrl:\\s*(true)$", methodeCommentaire));
						Boolean entiteVarModifie = indexerStockerSolr(entiteDoc, "entiteVarModifie", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_VarModifie) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteVarCree = indexerStockerSolr(entiteDoc, "entiteVarCree", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_VarCree) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteSauvegarde = indexerStockerSolr(entiteDoc, "entiteSauvegarde", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Sauvegarde) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteIncremente = indexerStockerSolr(entiteDoc, "entiteIncremente", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Incremente) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteTexte = indexerStockerSolr(entiteDoc, "entiteTexte", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Texte) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteDocValues = indexerStockerSolr(entiteDoc, "entiteDocValues", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_DocValues) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteIndexe = indexerStockerSolr(entiteDoc, "entiteIndexe", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Indexe) + ":\\s*(true)$", methodeCommentaire) || entiteCleUnique || entiteCrypte || entiteSuggere || entiteTexte || entiteClePrimaire || entiteIncremente || entiteDocValues);
						Boolean entiteStocke = indexerStockerSolr(entiteDoc, "entiteStocke", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Stocke) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteIndexeOuStocke", entiteCleUnique || entiteCrypte || entiteSuggere || entiteTexte || entiteIndexe || entiteStocke || entiteDocValues || entiteIncremente || entiteTexte);
						indexerStockerSolr(entiteDoc, "entiteIgnorer", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Ignorer) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteDeclarer", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Declarer) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRechercher", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Rechercher) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteAjouter", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Ajouter) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSupprimer", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Supprimer) + ":\\s*(true)$", methodeCommentaire));
						Boolean entiteDefinir = indexerStockerSolr(entiteDoc, "entiteDefinir", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Definir) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteModifier", !regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Modifier) + ":\\s*(false)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRecharger", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Recharger) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteMultiligne", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Multiligne) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSignature", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Signature) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteCles", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Cles) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSetTrim", regexTrouve("^SetTrim:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSetLower", regexTrouve("^SetLower:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSetUpper", regexTrouve("^SetUpper:\\s*(true)$", methodeCommentaire));

						String entiteLangue = regex("^" + classeLangueConfig.getString(ConfigCles.var_Langue) + ":\\s*(.*)$", methodeCommentaire);
						if(entiteLangue != null)
							indexerStockerSolr(entiteDoc, "entiteLangue", entiteLangue);

						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomAffichage", regexLangue(classeLangueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAffichage) + "", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteDescription", regexLangue(classeLangueNom, "^Description", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteOptionnel", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Optionnel) + ":\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteHtmlTooltip", regexLangue(classeLangueNom, "^HtmlTooltip", methodeCommentaire));
						indexerStockerSolrRegex(classeLangueNom, entiteDoc, "entiteVarApi", "VarApi", methodeCommentaire);
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteEnumNomSimple", regexLangue(classeLangueNom, "^" + classeLangueConfig.getString(ConfigCles.var_EnumNomSimple) + "", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteEnumVar", regexLangue(classeLangueNom, "^EnumVar", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteEnumVarDescription", regexLangue(classeLangueNom, "^" + classeLangueConfig.getString(ConfigCles.var_EnumVarDescription) + "", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteImageBase64Url", regexLangue(classeLangueNom, "^ImageBase64Url", methodeCommentaire));

						Boolean entiteHighlighting = indexerStockerSolr(entiteDoc, "entiteHighlighting", regexTrouve("^Highlighting:\\s*(true)$", methodeCommentaire));
						Boolean entiteHtml = entiteDefinir || regexTrouve("^Html:\\s*(true)$", methodeCommentaire);

						{ 
							String str = regex("^" + classeLangueConfig.getString(ConfigCles.var_HtmlColonne) + ":\\s*(.*)$", methodeCommentaire);
							if(NumberUtils.isCreatable(str)) {
								indexerStockerSolr(entiteDoc, "entiteHtmlColonne", Integer.parseInt(str));
								entiteHtml = true;
							}
						}
						{ 
							String str = regex("^" + classeLangueConfig.getString(ConfigCles.var_HtmlLigne) + ":\\s*(.*)$", methodeCommentaire);
							if(NumberUtils.isCreatable(str)) {
								indexerStockerSolr(entiteDoc, "entiteHtmlLigne", Integer.parseInt(str));
								entiteHtml = true;
							}
						}
						{ 
							String str = regex("^" + classeLangueConfig.getString(ConfigCles.var_HtmlCellule) + ":\\s*(.*)$", methodeCommentaire);
							if(NumberUtils.isCreatable(str)) {
								indexerStockerSolr(entiteDoc, "entiteHtmlCellule", Integer.parseInt(str));
								entiteHtml = true;
							}
						}

						indexerStockerSolr(entiteDoc, "entiteHtml", entiteHtml);

						{ 
							String str = regex("^" + classeLangueConfig.getString(ConfigCles.var_LongeurMin) + ":\\s*(.*)$", methodeCommentaire, 1);
							Integer num = NumberUtils.isCreatable(str) ? Integer.parseInt(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteLongeurMin", num);
						}

						{
							String str = regex("^" + classeLangueConfig.getString(ConfigCles.var_LongeurMax) + ":\\s*(.*)$", methodeCommentaire, 1);
							Integer num = NumberUtils.isCreatable(str) ? Integer.parseInt(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteLongeurMax", num);
						}

						{
							String str = regex("^Min:\\s*(.*)$", methodeCommentaire, 1);
							Double num = NumberUtils.isCreatable(str) ? Double.parseDouble(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteMin", num);
						}

						{
							String str = regex("^Max:\\s*(.*)$", methodeCommentaire, 1);
							Double num = NumberUtils.isCreatable(str) ? Double.parseDouble(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteMax", num);
						}

						if(classeTraduire) {
							for(String langueNom : classeAutresLangues) {  
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomAffichage", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAffichage) + "", methodeCommentaire));
								indexerStockerSolr(langueNom, entiteDoc, "entiteEnumVarDescription", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_EnumVarDescription) + "", methodeCommentaire));
								indexerStockerSolr(langueNom, entiteDoc, "entiteHtmlTooltip", regexLangue(langueNom, "^HtmlTooltip", methodeCommentaire));
								indexerStockerSolrRegex(langueNom, entiteDoc, "entiteVarApi", "VarApi", methodeCommentaire);
								indexerStockerSolr(langueNom, entiteDoc, "entiteEnumVar", regexLangue(langueNom, "^EnumVar", methodeCommentaire));
								indexerStockerSolr(langueNom, entiteDoc, "entiteImageBase64Url", regexLangue(langueNom, "^ImageBase64Url", methodeCommentaire));

								if(entiteVarUrl != null) {
									SolrQuery rechercheSolrVar = new SolrQuery();   
									rechercheSolrVar.setQuery("*:*");
									rechercheSolrVar.setRows(1);
									rechercheSolrVar.addFilterQuery("entiteClassesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
									rechercheSolrVar.addFilterQuery("entiteVar_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteVarUrl));
									rechercheSolrVar.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
									rechercheSolrVar.addFilterQuery("partEstEntite_indexed_boolean:true");
									QueryResponse reponseRechercheVar = clientSolrComputate.query(rechercheSolrVar);
									SolrDocumentList listeRechercheVar = reponseRechercheVar.getResults();
	
									if(listeRechercheVar.size() > 0) {
										SolrDocument docEntite = listeRechercheVar.get(0);
										String var2 = (String)docEntite.get("entiteVar_" + langueNom + "_stored_string");
										if(var2 != null)
											indexerStockerSolr(langueNom, entiteDoc, "entiteVarUrl", var2);
									}
								}
							}
						}

						Matcher entiteAttribuerRecherche = methodeCommentaire == null ? null : Pattern.compile("^" + classeLangueConfig.getString(ConfigCles.var_Attribuer) + ":\\s*([^\\.]+)\\.(.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
						boolean entiteAttribuerTrouve = entiteAttribuerRecherche == null ? false : entiteAttribuerRecherche.find();
						if(entiteAttribuerTrouve) {
							String entiteAttribuerNomSimple = entiteAttribuerRecherche.group(1);
							String entiteAttribuerVar = entiteAttribuerRecherche.group(2);

							SolrQuery rechercheSolrClasse = new SolrQuery();   
							rechercheSolrClasse.setQuery("*:*");
							rechercheSolrClasse.setRows(1);
							rechercheSolrClasse.addFilterQuery("classeNomSimple_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerNomSimple));
							rechercheSolrClasse.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
							rechercheSolrClasse.addFilterQuery("partEstClasse_indexed_boolean:true");
							QueryResponse reponseRechercheClasse = clientSolrComputate.query(rechercheSolrClasse);
							SolrDocumentList listeRechercheClasse = reponseRechercheClasse.getResults();

							if(listeRechercheClasse.size() > 0) {
								SolrDocument docClasse = listeRechercheClasse.get(0);
								String entiteAttribuerNomCanonique = (String)docClasse.get("classeNomCanonique_" + classeLangueNom + "_stored_string");
								String entiteAttribuerNomCanoniqueGenApiServiceImpl = (String)docClasse.get("classeNomCanoniqueGenApiServiceImpl_" + classeLangueNom + "_stored_string");
								String entiteAttribuerNomCanoniqueApiServiceImpl = (String)docClasse.get("classeNomCanoniqueApiServiceImpl_" + classeLangueNom + "_stored_string");
								String entiteAttribuerNomSimpleGenApiServiceImpl = (String)docClasse.get("classeNomSimpleGenApiServiceImpl_" + classeLangueNom + "_stored_string");
								String entiteAttribuerNomSimpleApiServiceImpl = (String)docClasse.get("classeNomSimpleApiServiceImpl_" + classeLangueNom + "_stored_string");

								classePartsGenAjouter(ClasseParts.initClasseParts(this, entiteAttribuerNomCanonique, classeLangueNom), classeLangueNom);
								classePartsGenApiAjouter(ClasseParts.initClasseParts(this, entiteAttribuerNomCanonique, classeLangueNom), classeLangueNom);

								indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImportationsGenApi", entiteAttribuerNomCanoniqueApiServiceImpl);
								indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImportationsGenApi", entiteAttribuerNomCanonique);

								SolrQuery rechercheSolrVar = new SolrQuery();   
								rechercheSolrVar.setQuery("*:*");
								rechercheSolrVar.setRows(1);
								rechercheSolrVar.addFilterQuery("classeNomCanonique_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerNomCanonique));
								rechercheSolrVar.addFilterQuery("entiteVar_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerVar));
								rechercheSolrVar.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
								rechercheSolrVar.addFilterQuery("partEstEntite_indexed_boolean:true");
								QueryResponse reponseRechercheVar = clientSolrComputate.query(rechercheSolrVar);
								SolrDocumentList listeRechercheVar = reponseRechercheVar.getResults();

								if(listeRechercheVar.size() > 0) {
									SolrDocument docEntite = listeRechercheVar.get(0);

									indexerStockerSolr(entiteDoc, "entiteAttribuer", true);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerNomSimple", entiteAttribuerNomSimple);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerNomCanonique", entiteAttribuerNomCanonique);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerNomCanoniqueGenApiServiceImpl", entiteAttribuerNomCanoniqueGenApiServiceImpl);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerNomSimpleGenApiServiceImpl", entiteAttribuerNomSimpleGenApiServiceImpl);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerNomSimpleApiServiceImpl", entiteAttribuerNomSimpleApiServiceImpl);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVar", entiteAttribuerVar);
									{
										List<String> listeVal = Optional.ofNullable((List<String>)docClasse.get("classeTrisVar_" + classeLangueNom + "_stored_strings")).orElse(Collections.emptyList());
										List<String> listeSuffixeType = Optional.ofNullable((List<String>)docClasse.get("classeTrisSuffixeType_stored_strings")).orElse(Collections.emptyList());
										if(listeVal.size() == listeSuffixeType.size()) {
											for(int i = 0; i < listeVal.size(); i++) {
												String var = listeVal.get(i);
												String suffixeType = listeSuffixeType.get(i);
												indexerStockerListeSolr(classeLangueNom, entiteDoc, "entiteAttribuerTrisVar", var);
												indexerStockerListeSolr(entiteDoc, "entiteAttribuerTrisSuffixeType", suffixeType);
												
											}
										}
									}
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarSuggere", (String)docClasse.get("classeVarSuggere_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarTexte", (String)docClasse.get("classeVarTexte_" + classeLangueNom + "_stored_strings"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarUrlId", (String)docClasse.get("classeVarUrlId_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarUrlPk", (String)docClasse.get("classeVarUrlPk_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarUrlApi", (String)docClasse.get("classeVarUrlApi_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarId", (String)docClasse.get("classeVarId_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarTitre", (String)docClasse.get("classeVarTitre_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarH1", (String)docClasse.get("classeVarH1_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarH2", (String)docClasse.get("classeVarH2_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarH3", (String)docClasse.get("classeVarH3_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerApiUri", (String)docClasse.get("classeApiUri_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerPageUri", (String)docClasse.get("classeApiUri" + classeLangueConfig.getString(ConfigCles.var_PageRecherche) + "_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarDescription", (String)docClasse.get("classeVarDescription_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarImageUrl", (String)docClasse.get("classeVarImageUrl_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerContexteUnNom", (String)docClasse.get("contexteUnNom_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerContextePluriel", (String)docClasse.get("contextePluriel_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(entiteDoc, "entiteAttribuerUtilisateurEcrire", (Boolean)docClasse.get("classeUtilisateurEcrire_stored_boolean"));
									indexerStockerSolr(entiteDoc, "entiteAttribuerSessionEcrire", (Boolean)docClasse.get("classeSessionEcrire_stored_boolean"));
									indexerStockerSolr(entiteDoc, "entiteAttribuerPublicLire", (Boolean)docClasse.get("classePublicLire_stored_boolean"));
									List<String> entiteClasseRoles = (List<String>)docClasse.get("classeRoles_stored_strings");
									List<String> entiteClasseRolesLangue = (List<String>)docClasse.get("classeRolesLangue_stored_strings");
									if(entiteClasseRoles != null && entiteClasseRoles.size() > 0) {
										for(int i = 0; i < entiteClasseRoles.size(); i++) {
											String entiteClasseRole = entiteClasseRoles.get(i);
											String entiteClasseRoleLangue = entiteClasseRolesLangue.get(i);
											indexerStockerListeSolr(entiteDoc, "entiteAttribuerClasseRoles", entiteClasseRole);
											indexerStockerListeSolr(entiteDoc, "entiteAttribuerClasseRolesLangue", entiteClasseRoleLangue);
										}
									}
									indexerStockerSolr(entiteDoc, "entiteAttribuerContexteCouleur", (String)docClasse.get("contexteCouleur_stored_string"));
									indexerStockerSolr(entiteDoc, "entiteAttribuerContexteIconeGroupe", (String)docClasse.get("contexteIconeGroupe_stored_string"));
									indexerStockerSolr(entiteDoc, "entiteAttribuerContexteIconeNom", (String)docClasse.get("contexteIconeNom_stored_string"));
									indexerStockerSolr(entiteDoc, "entiteAttribuerContexteRows", (Integer)docClasse.get("contexteRows_stored_int"));

									String entiteOperationIdPATCH = regexLangue(classeLangueNom, "(classe)?ApiOperationIdPATCH", classeCommentaire, "patch" + classeNomSimple);
									if(entiteOperationIdPATCH != null)
										indexerStockerSolr(classeLangueNom, entiteDoc, "entiteOperationIdPATCH", entiteOperationIdPATCH);

									String entiteAttribuerTypeJson = (String)docEntite.get("entiteTypeJson_stored_string");
									if(entiteAttribuerTypeJson != null)
										indexerStockerSolr(entiteDoc, "entiteAttribuerTypeJson", entiteAttribuerTypeJson);

									String entiteAttribuerOperationIdPATCH = (String)docClasse.get("classeApiOperationIdPATCH_" + classeLangueNom + "_stored_string");
									if(entiteAttribuerOperationIdPATCH != null)
										indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerOperationIdPATCH", entiteAttribuerOperationIdPATCH);
									String entiteAttribuerOperationIdRecherche = (String)docClasse.get("classeApiOperationId" + classeLangueConfig.getString(ConfigCles.var_Recherche) + "_" + classeLangueNom + "_stored_string");
									if(entiteAttribuerOperationIdRecherche != null)
										indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerOperationId" + classeLangueConfig.getString(ConfigCles.var_Recherche), entiteAttribuerOperationIdRecherche);

									String classeAttribuerNomSimplePage = (String)docClasse.get("classePageNomSimple" + classeLangueConfig.getString(ConfigCles.var_PageRecherche) + "_" + classeLangueNom + "_stored_string");
									if(classeAttribuerNomSimplePage != null) {
										if(!Optional.ofNullable(classeDoc.getFieldValues("classeAttribuerNomSimplePages_" + classeLangueNom + "_indexed_strings")).orElse(Arrays.asList()).contains(classeAttribuerNomSimplePage))
											indexerStockerListeSolr(classeLangueNom, classeDoc, "classeAttribuerNomSimplePages", classeAttribuerNomSimplePage);
										if(!Optional.ofNullable(classeDoc.getFieldValues("classeAttribuerNomSimple_" + classeLangueNom + "_indexed_strings")).orElse(Arrays.asList()).contains(entiteAttribuerNomSimple))
											indexerStockerListeSolr(classeLangueNom, classeDoc, "classeAttribuerNomSimple", entiteAttribuerNomSimple);
									}

									if(classeTraduire) {
										for(String langueNom : classeAutresLangues) {  
											YAMLConfiguration langueConfig = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/i18n/i18n_%s.yml", appComputate, langueNom));
											String entiteAttribuerNomCanoniqueLangue = (String)docEntite.get("classeNomCanonique_" + langueNom + "_stored_string");
											String entiteAttribuerNomSimpleLangue = (String)docEntite.get("classeNomSimple_" + langueNom + "_stored_string");
											String entiteAttribuerNomCanoniqueGenApiServiceImplLangue = (String)docClasse.get("classeNomCanoniqueGenApiServiceImpl_" + langueNom + "_stored_string");
											String entiteAttribuerNomCanoniqueApiServiceImplLangue = (String)docClasse.get("classeNomCanoniqueApiServiceImpl_" + langueNom + "_stored_string");
											String entiteAttribuerNomSimpleGenApiServiceImplLangue = (String)docClasse.get("classeNomSimpleGenApiServiceImpl_" + langueNom + "_stored_string");
											String entiteAttribuerNomSimpleApiServiceImplLangue = (String)docClasse.get("classeNomSimpleApiServiceImpl_" + langueNom + "_stored_string");
											String entiteAttribuerVarLangue = (String)docEntite.get("entiteVar_" + langueNom + "_stored_string");
											String classeNomSimpleLangue = (String)Optional.ofNullable(classeDoc.get("classeNomSimple_" + langueNom + "_stored_string")).map(SolrInputField::getValue).orElse(null);

											indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGenApi", entiteAttribuerNomCanoniqueApiServiceImplLangue);
											indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGenApi", entiteAttribuerNomCanoniqueLangue);
	
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerNomSimple", entiteAttribuerNomSimpleLangue);
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerNomCanonique", entiteAttribuerNomCanoniqueLangue);
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerNomSimpleGenApiServiceImpl", entiteAttribuerNomSimpleGenApiServiceImplLangue);
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerNomSimpleApiServiceImpl", entiteAttribuerNomSimpleApiServiceImplLangue);
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerNomCanoniqueGenApiServiceImpl", entiteAttribuerNomCanoniqueGenApiServiceImplLangue);
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVar", entiteAttribuerVarLangue);
											{
												List<String> listeVal = Optional.ofNullable((List<String>)docClasse.get("classeTrisVar_" + langueNom + "_stored_strings")).orElse(Collections.emptyList());
												List<String> listeSuffixeType = Optional.ofNullable((List<String>)docClasse.get("classeTrisSuffixeType_stored_strings")).orElse(Collections.emptyList());
												if(listeVal.size() == listeSuffixeType.size()) {
													for(int i = 0; i < listeVal.size(); i++) {
														String var = listeVal.get(i);
														indexerStockerListeSolr(langueNom, entiteDoc, "entiteAttribuerTrisVar", var);
													}
												}
											}
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarSuggere", (String)docClasse.get("classeVarSuggere_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarTexte", (String)docClasse.get("classeVarTexte_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarUrlId", (String)docClasse.get("classeVarUrlId_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarUrlPk", (String)docClasse.get("classeVarUrlPk_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarUrlApi", (String)docClasse.get("classeVarUrlApi_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarId", (String)docClasse.get("classeVarId_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarTitre", (String)docClasse.get("classeVarTitre_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarH1", (String)docClasse.get("classeVarH1_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarH2", (String)docClasse.get("classeVarH2_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarH3", (String)docClasse.get("classeVarH3_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerApiUri", (String)docClasse.get("classeApiUri_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerPageUri", (String)docClasse.get("classeApiUri" + langueConfig.getString(ConfigCles.var_PageRecherche) + "_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarDescription", (String)docClasse.get("classeVarDescription_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarImageUrl", (String)docClasse.get("classeVarImageUrl_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerContexteUnNom", (String)docClasse.get("contexteUnNom_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerContexteNomPluriel", (String)docClasse.get("contexteNomPluriel_" + langueNom + "_stored_string"));
											
											entiteOperationIdPATCH = regexLangue(langueNom, "(classe)?ApiOperationIdPATCH", classeCommentaire, "patch" + classeNomSimpleLangue);
											if(entiteOperationIdPATCH != null)
												indexerStockerSolr(langueNom, entiteDoc, "entiteOperationIdPATCH", entiteOperationIdPATCH);

											entiteAttribuerOperationIdPATCH = (String)docClasse.get("classeApiOperationIdPATCH_" + langueNom + "_stored_string");

											classeAttribuerNomSimplePage = (String)docClasse.get("classePageNomSimple" + langueConfig.getString(ConfigCles.var_PageRecherche) + "_" + langueNom + "_stored_string");
											if(classeAttribuerNomSimplePage != null) {
												if(!Optional.ofNullable(classeDoc.getFieldValues("classeAttribuerNomSimplePages_" + classeLangueNom + "_indexed_strings")).orElse(Arrays.asList()).contains(classeAttribuerNomSimplePage))
													indexerStockerListeSolr(langueNom, classeDoc, "classeAttribuerNomSimplePages", classeAttribuerNomSimplePage);
												if(!Optional.ofNullable(classeDoc.getFieldValues("classeAttribuerNomSimple_" + classeLangueNom + "_indexed_strings")).orElse(Arrays.asList()).contains(classeAttribuerNomSimplePage))
													indexerStockerListeSolr(langueNom, classeDoc, "classeAttribuerNomSimple", classeNomSimpleLangue);
											}

											if(entiteAttribuerOperationIdPATCH != null)
												indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerOperationIdPATCH", entiteAttribuerOperationIdPATCH);
											entiteAttribuerOperationIdRecherche = (String)docClasse.get("classeApiOperationId" + langueConfig.getString(ConfigCles.var_Recherche) + "_" + langueNom + "_stored_string");
											if(entiteAttribuerOperationIdRecherche != null)
												indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerOperationId" + langueConfig.getString(ConfigCles.var_Recherche), entiteAttribuerOperationIdRecherche);
										}
									}
								}
							}
						}
						
						for(JavaAnnotation annotation : annotations) {
							String entiteAnnotationLangue = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAnnotations", annotation.getType().getCanonicalName());
						}
	
						String entiteCle = classeCheminAbsolu + "." + entiteVar;
		
						// Entites Solr du entite. 
		
						entiteDoc.addField("id", entiteCle);
						indexerStockerSolr(entiteDoc, "partEstEntite", true);
						indexerStockerSolr(entiteDoc, "partNumero", partNumero);

						String entiteCodeSource = methodeQdox.getSourceCode();
						String entiteCodeSourceComplet = methodeQdox.getCodeBlock();
						String src = regex("([\\s\\S]*?\\w+\\s*\\([^\\)]*\\)[^;\\{]*[;\\{])", entiteCodeSourceComplet, 1);
						if(src.endsWith(";") && !methodeQdox.isAbstract())
							src = StringUtils.substringBeforeLast(src, ";") + " {";
						stockerSolr(classeLangueNom, entiteDoc, "entiteCodeSourceDebut", src);
						stockerSolr(classeLangueNom, entiteDoc, "entiteCodeSourceFin", "}\n");
						String entiteString = regex("^String\\." + classeLangueNom + ":(.*)", methodeCommentaire);
						if(entiteString != null) {
							entiteCodeSource = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entiteString, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
							indexerStockerSolr(classeLangueNom, entiteDoc, "entiteString", entiteString); 
						}
						stockerSolr(classeLangueNom, entiteDoc, "entiteCodeSource", entiteCodeSource); 

						indexerStockerSolr(entiteDoc, "entiteLigneDebut", methodeQdox.getLineNumber() - (methodeCommentaire == null ? 0 : (methodeCommentaire.split(REGEX_LIGNE).length + 1)));
						indexerStockerSolr(entiteDoc, "entiteLigneFin", methodeQdox.getLineNumber() + (entiteCodeSource == null ? 0 : (entiteCodeSource.split(REGEX_LIGNE).length - 1)));

						if(activerVertx || activerQuarkus) {
							/////////////////////////
							// entiteTypeVertxJson //
							/////////////////////////
							String entiteNomSimpleVertxJson = null;
							String entiteNomCanoniqueVertxJson = null;
							String entiteListeNomSimpleVertxJson = null;
							String entiteListeNomCanoniqueVertxJson = null;
							if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBoolean)) {
								entiteNomSimpleVertxJson = "Boolean";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueBoolean;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalTime)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.LocalTime", classeLangueNom), classeLangueNom);
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneOffset", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDateTime", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDate", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZonedDateTime", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, ChronoUnit.class.getCanonicalName(), classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueDate, classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.Instant", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.Locale", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, OffsetDateTime.class.getCanonicalName(), classeLangueNom), classeLangueNom);
								classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom), classeLangueNom);
								classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.util.Locale", classeLangueNom), classeLangueNom);
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalDate)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneOffset", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDate", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, ChronoUnit.class.getCanonicalName(), classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueDate, classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.Instant", classeLangueNom), classeLangueNom);
								classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.Locale", classeLangueNom), classeLangueNom);
								classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom), classeLangueNom);
								classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.util.Locale", classeLangueNom), classeLangueNom);
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePoint)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
								entiteNomSimpleVertxJson = "JsonObject";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonObject;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								classePartsGenAjouter(ClasseParts.initClasseParts(this, NumberUtils.class.getCanonicalName(), classeLangueNom), classeLangueNom);
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueDouble)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueFloat)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueInteger)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
								if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBoolean)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "Boolean";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueBoolean;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLocalTime)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "String";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "String";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueInstant;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLocalDate)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "String";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueInstant;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "String";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "String";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueString;
									classePartsGenAjouter(ClasseParts.initClasseParts(this, NumberUtils.class.getCanonicalName(), classeLangueNom), classeLangueNom);
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "String";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "String";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "String";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueString)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "String";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueString;
								}
								if(entiteListeNomCanoniqueVertxJson != null) {
									stockerSolr(entiteDoc, "entiteListeNomSimpleVertxJson", entiteListeNomSimpleVertxJson);
									stockerSolr(entiteDoc, "entiteListeNomCanoniqueVertxJson", entiteListeNomCanoniqueVertxJson);
									classePartsGenAjouter(ClasseParts.initClasseParts(this, entiteListeNomCanoniqueVertxJson, classeLangueNom), classeLangueNom);
								}
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueString)) {
								entiteNomSimpleVertxJson = "String";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
							}
							if(entiteNomCanoniqueVertxJson != null) {
								classePartsGenAjouter(ClasseParts.initClasseParts(this, entiteNomCanoniqueVertxJson, classeLangueNom), classeLangueNom);
								stockerSolr(entiteDoc, "entiteNomSimpleVertxJson", entiteNomSimpleVertxJson);
								stockerSolr(entiteDoc, "entiteNomCanoniqueVertxJson", entiteNomCanoniqueVertxJson);
							}
						}

						////////////////////
						// entiteSolrNomCanonique //
						////////////////////
						String entiteSolrNomCanonique = null;
						String entiteSolrNomSimple = null;
						String entiteSuffixeType = null;
						if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBoolean)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueBoolean;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_boolean";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalTime)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueString;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_string";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueDate;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_date";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueLong;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_long";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePoint)) {
							entiteSolrNomCanonique = VAL_nomCanoniquePoint;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_location";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueVertxJsonObject;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_string";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueDouble;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueDouble)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueDouble;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueFloat)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueFloat;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_float";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueInteger)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueInteger;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_int";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
							if(entiteNomCanoniqueGenerique.equals(VAL_nomCanoniqueBoolean)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueBoolean + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueBoolean, ".") + ">";
								entiteSuffixeType = "_booleans";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLocalTime)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueDate + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueString, ".") + ">";
								entiteSuffixeType = "_strings";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueDate + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueDate, ".") + ">";
								entiteSuffixeType = "_dates";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueLong + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueLong, ".") + ">";
								entiteSuffixeType = "_longs";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueBigDecimal + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueBigDecimal, ".") + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueDouble + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueDouble, ".") + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueFloat + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueFloat, ".") + ">";
								entiteSuffixeType = "_floats";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueInteger + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueInteger, ".") + ">";
								entiteSuffixeType = "_ints";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueString)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueString + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueString, ".") + ">";
								entiteSuffixeType = "_strings";
							}
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueString)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueString;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_string";
						}
						stockerSolr(entiteDoc, "entiteSolrNomCanonique", entiteSolrNomCanonique);
						stockerSolr(entiteDoc, "entiteSolrNomSimple", entiteSolrNomSimple);
						stockerSolr(entiteDoc, "entiteSuffixeType", entiteSuffixeType);

						///////////////////
						// entiteTypeSql //
						///////////////////
						String entiteTypeSql = null;
						String entiteListeTypeSql = null;
						if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBoolean)) {
							entiteTypeSql = "boolean";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalTime)) {
							entiteTypeSql = "timestamp";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
							entiteTypeSql = "timestamp with time zone";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalDate)) {
							entiteTypeSql = "date";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
							if(entiteClePrimaire)
								entiteTypeSql = "bigserial primary key";
							else
								entiteTypeSql = "bigint";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePoint)) {
							entiteTypeSql = "point";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
							entiteTypeSql = "jsonb";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
							entiteTypeSql = "decimal";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueDouble)) {
							entiteTypeSql = "double precision";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueFloat)) {
							entiteTypeSql = "real";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueInteger)) {
							entiteTypeSql = "integer";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
							if(entiteNomCanoniqueGenerique.equals(VAL_nomCanoniqueBoolean)) {
								entiteTypeSql = "array";
								entiteListeTypeSql = "bigint";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLocalTime)) {
								entiteTypeSql = "array";
								entiteListeTypeSql = "bigint";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteTypeSql = "array";
								entiteListeTypeSql = "bigint";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteTypeSql = "array";
								entiteListeTypeSql = "bigint";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
								entiteTypeSql = "array";
								entiteListeTypeSql = "bigint";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
								entiteTypeSql = "array";
								entiteListeTypeSql = "bigint";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
								entiteTypeSql = "array";
								entiteListeTypeSql = "bigint";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
								entiteTypeSql = "array";
								entiteListeTypeSql = "bigint";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueString)) {
								entiteTypeSql = "array";
								entiteListeTypeSql = "bigint";
							}
							stockerSolr(entiteDoc, "entiteListeTypeSql", entiteListeTypeSql);
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueString)) {
							entiteTypeSql = "text";
						}
						stockerSolr(entiteDoc, "entiteTypeSql", entiteTypeSql);

						////////////////////
						// entiteTypeJson //
						////////////////////
						String entiteTypeJson = null;
						String entiteFormatJson = null;
						String entiteFormatHtm = regex("^FormatHtm: (.*)", methodeCommentaire);
						String entiteListeTypeJson = null;
						if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBoolean)) {
							entiteTypeJson = "boolean";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalTime)) {
							entiteTypeJson = "string";
							entiteFormatJson = "date-time";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "short";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
							entiteTypeJson = "string";
							entiteFormatJson = "date-time";
							if(entiteFormatHtm == null) {
								if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueTimestamp))
									entiteFormatHtm = "yyyy-MM-dd'T'HH:mm:ss.SSS";
								else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueLocalDateTime))
									entiteFormatHtm = "yyyy-MM-dd'T'HH:mm:ss.SSS";
								else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueDate))
									entiteFormatHtm = "yyyy-MM-dd'T'HH:mm:ss.SSS";
								else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueZonedDateTime))
									entiteFormatHtm = "yyyy-MM-dd'T'HH:mm:ss.SSS'['VV']'";
							}
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalDate)) {
							entiteTypeJson = "string";
							entiteFormatJson = "date";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "short";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
							entiteTypeJson = "string";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "integer";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePoint)) {
							entiteTypeJson = "string";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
							entiteTypeJson = "object";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
							entiteTypeJson = "string";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueDouble)) {
							entiteTypeJson = "string";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueFloat)) {
							entiteTypeJson = "string";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueInteger)) {
							entiteTypeJson = "string";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "integer";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
							if(entiteNomCanoniqueGenerique.equals(VAL_nomCanoniqueBoolean)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "boolean";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLocalTime)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueString)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							} else {
								entiteTypeJson = "array";
							}
							stockerSolr(entiteDoc, "entiteListeTypeJson", entiteListeTypeJson);
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueString)) {
							entiteTypeJson = "string";
						}
						indexerStockerSolr(entiteDoc, "entiteTypeJson", entiteTypeJson);
						if(entiteFormatJson != null)
							stockerSolr(entiteDoc, "entiteFormatJson", entiteFormatJson);
						indexerStockerSolr(entiteDoc, "entiteFormatHtm", entiteFormatHtm);

						if(entiteClePrimaire) {
							classeVarClePrimaire = stockerSolr(classeLangueNom, classeDoc, "classeVarClePrimaire", entiteVar);
						}
						if(entiteInheritClePrimaire) {
							classeVarInheritClePrimaire = stockerSolr(classeLangueNom, classeDoc, "classeVarInheritClePrimaire", entiteVar);
						}
						if(entiteSauvegardes) {
							classeVarSauvegardes = stockerSolr(classeLangueNom, classeDoc, "classeVarSauvegardes", entiteVar);
						}
						if(entiteCleUnique) {
							classeVarCleUnique = stockerSolr(classeLangueNom, classeDoc, "classeVarCleUnique", entiteVar);
						}
						if(entiteSuggere && entiteVar.equals(classeLangueConfig.getString(ConfigCles.var_objetSuggere))) {
							classeVarSuggere = stockerSolr(classeLangueNom, classeDoc, "classeVarSuggere", entiteVar);
						}
						if(entiteTexte && entiteVar.equals(classeLangueConfig.getString(ConfigCles.var_objetTexte))) {
							classeVarTexte = stockerSolr(classeLangueNom, classeDoc, "classeVarTexte", entiteVar);
						}
						if(entiteVarUrlId) {
							classeVarUrlId = stockerSolr(classeLangueNom, classeDoc, "classeVarUrlId", entiteVar);
						}
						if(entiteVarUrlPk) {
							classeVarUrlPk = stockerSolr(classeLangueNom, classeDoc, "classeVarUrlPk", entiteVar);
						}
						if(entiteVarUrlApi) {
							classeVarUrlApi = stockerSolr(classeLangueNom, classeDoc, "classeVarUrlApi", entiteVar);
						}
						if(entiteVarId) {
							classeVarId = stockerSolr(classeLangueNom, classeDoc, "classeVarId", entiteVar);
						}
						if(entiteVarTitre) {
							classeVarTitre = stockerSolr(classeLangueNom, classeDoc, "classeVarTitre", entiteVar);
						}
						if(entiteVarH1) {
							classeVarH1 = stockerSolr(classeLangueNom, classeDoc, "classeVarH1", entiteVar);
						}
						if(entiteVarH2) {
							classeVarH2 = stockerSolr(classeLangueNom, classeDoc, "classeVarH2", entiteVar);
						}
						if(entiteVarH3) {
							classeVarH3 = stockerSolr(classeLangueNom, classeDoc, "classeVarH3", entiteVar);
						}
						if(entiteVarDescription) {
							classeVarDescription = stockerSolr(classeLangueNom, classeDoc, "classeVarDescription", entiteVar);
						}
						if(entiteVarImageUrl) {
							classeVarImageUrl = stockerSolr(classeLangueNom, classeDoc, "classeVarImageUrl", entiteVar);
						}
						if(entiteVarModifie) {
							classeVarModifie = stockerSolr(classeLangueNom, classeDoc, "classeVarModifie", entiteVar);
						}
						if(entiteVarCree) {
							classeVarCree = stockerSolr(classeLangueNom, classeDoc, "classeVarCree", entiteVar);
						}
				
						if(classeTraduire) {
							for(String langueNom : classeAutresLangues) {  
								YAMLConfiguration langueConfig = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/i18n/i18n_%s.yml", appComputate, langueNom));
//								ClasseParts entiteClassePartsLangue = ClasseParts.initClasseParts(this, entiteClasseParts, langueNom);
					
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomCanonique", entiteClasseParts.nomCanonique(langueNom)); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomSimple", entiteClasseParts.nomSimple(langueNom)); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomCanoniqueComplet", entiteClasseParts.nomCanoniqueComplet(langueNom)); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomSimpleComplet", entiteClasseParts.nomSimpleComplet(langueNom)); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomCanoniqueGenerique", entiteClasseParts.nomCanoniqueGenerique(langueNom)); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomSimpleGenerique", entiteClasseParts.nomSimpleGenerique(langueNom)); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomSimpleCompletGenerique", entiteClasseParts.nomSimpleGenerique(langueNom)); 
	
								indexerStockerSolr(langueNom, entiteDoc, "entiteVarParam", entiteVarParam); 
	
								String entiteVarLangue = regex("^Var\\." + langueNom + ": (.*)", methodeCommentaire);
								entiteVarLangue = indexerStockerSolr(langueNom, entiteDoc, "entiteVar", entiteVarLangue == null ? entiteVar : entiteVarLangue);
								indexerStockerSolr(langueNom, entiteDoc, "entiteVarCapitalise", StringUtils.capitalize(entiteVarLangue));
								indexerStockerListeSolr(langueNom, classeDoc, "classeEntiteVars", entiteVarLangue);
								if(entiteSuggere && entiteVarLangue.equals(langueConfig.getString(ConfigCles.var_objetSuggere))) {
									stockerSolr(langueNom, classeDoc, "classeVarSuggere", entiteVarLangue);
								}
								if(entiteTexte && entiteVarLangue.equals(langueConfig.getString(ConfigCles.var_objetTexte))) {
									stockerSolr(langueNom, classeDoc, "classeVarTexte", entiteVarLangue);
								}
								if(entiteClePrimaire) {
									stockerSolr(langueNom, classeDoc, "classeVarClePrimaire", entiteVarLangue);
								}
								if(entiteInheritClePrimaire) {
									stockerSolr(langueNom, classeDoc, "classeVarInheritClePrimaire", entiteVarLangue);
								}
								if(entiteSauvegardes) {
									stockerSolr(langueNom, classeDoc, "classeVarSauvegardes", entiteVarLangue);
								}
								if(entiteCleUnique) {
									stockerSolr(langueNom, classeDoc, "classeVarCleUnique", entiteVarLangue);
								}
								if(entiteVarUrlId) {
									classeVarUrlId = stockerSolr(langueNom, classeDoc, "classeVarUrlId", entiteVarLangue);
								}
								if(entiteVarUrlPk) {
									classeVarUrlPk = stockerSolr(langueNom, classeDoc, "classeVarUrlPk", entiteVarLangue);
								}
								if(entiteVarUrlApi) {
									classeVarUrlApi = stockerSolr(langueNom, classeDoc, "classeVarUrlApi", entiteVarLangue);
								}
								if(entiteVarId) {
									classeVarId = stockerSolr(langueNom, classeDoc, "classeVarId", entiteVarLangue);
								}
								if(entiteVarTitre) {
									classeVarTitre = stockerSolr(langueNom, classeDoc, "classeVarTitre", entiteVarLangue);
								}
								if(entiteVarH1) {
									classeVarH1 = stockerSolr(langueNom, classeDoc, "classeVarH1", entiteVarLangue);
								}
								if(entiteVarH2) {
									classeVarH2 = stockerSolr(langueNom, classeDoc, "classeVarH2", entiteVarLangue);
								}
								if(entiteVarH3) {
									classeVarH3 = stockerSolr(langueNom, classeDoc, "classeVarH3", entiteVarLangue);
								}
								if(entiteVarDescription) {
									classeVarDescription = stockerSolr(langueNom, classeDoc, "classeVarDescription", entiteVarLangue);
								}
								if(entiteVarImageUrl) {
									classeVarImageUrl = stockerSolr(langueNom, classeDoc, "classeVarImageUrl", entiteVarLangue);
								}
								if(entiteVarModifie) {
									classeVarModifie = stockerSolr(langueNom, classeDoc, "classeVarModifie", entiteVarLangue);
								}
								if(entiteVarCree) {
									classeVarCree = stockerSolr(langueNom, classeDoc, "classeVarCree", entiteVarLangue);
								}
		
								String entiteCodeSourceLangue = entiteCodeSource;
								entiteCodeSourceLangue = regexRemplacerTout(methodeCommentaire, entiteCodeSource, langueNom);
								String entiteStringLangue = regex("^String\\." + langueNom + ":(.*)", methodeCommentaire);
								if(entiteStringLangue != null) {
									entiteCodeSourceLangue = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entiteStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
									indexerStockerSolr(langueNom, entiteDoc, "entiteString", entiteStringLangue); 
								}
								stockerSolr(langueNom, entiteDoc, "entiteCodeSource", entiteCodeSourceLangue); 
		
								stockerRegexCommentaires(langueNom, entiteDoc, "entiteCommentaire", methodeCommentaire);
							}
						}

						for(JavaClass methodeExceptionQdox : methodeExceptionsQdox) { 
							String methodeExceptionNomSimpleComplet = StringUtils.substringAfterLast(methodeExceptionQdox.getCanonicalName(), ".");
							ClasseParts methodeExceptionClasseParts = ClasseParts.initClasseParts(this, methodeExceptionQdox.getCanonicalName(), classeLangueNom);
							if(!classeInitLoinExceptions.contains(methodeExceptionClasseParts.nomCanoniqueComplet(classeLangueNom)))
								classeInitLoinExceptions.add(methodeExceptionClasseParts.nomCanoniqueComplet(classeLangueNom));
							stockerListeSolr(entiteDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionNomSimpleComplet);
							classePartsGenAjouter(methodeExceptionClasseParts, classeLangueNom);
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) {  
//									ClasseParts methodeExceptionClassePartsLangue = ClasseParts.initClasseParts(this, methodeExceptionQdox.getCanonicalName(), langueNom);
									stockerListeSolr(entiteDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionClasseParts.nomSimpleComplet(langueNom));
								}
							}
						}

						clientSolrComputate.add(entiteDoc); 
					}
					else {  
						// est Methode. 
						
						SolrInputDocument methodeDoc = classeDocClone.deepCopy();
						indexerStockerSolr(classeLangueNom, methodeDoc, "methodeVar", methodeVar);
						indexerStockerSolr(classeLangueNom, methodeDoc, "methodeDescription", regexLangue(classeLangueNom, "^Description", methodeCommentaire));
						indexerStockerListeSolr(classeLangueNom, classeDoc, "classeMethodeVars", methodeVar);
						for(Integer methodeParamNum = 1; methodeParamNum <= methodeParamsQdox.size(); methodeParamNum++) {
							JavaParameter methodeParamQdox = methodeParamsQdox.get(methodeParamNum - 1);
							String methodeParamVar = methodeParamQdox.getName();
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeParamsVar", methodeParamVar);
							ClasseParts methodeParamsClassePart = ClasseParts.initClasseParts(this, methodeParamQdox.getJavaClass(), classeLangueNom);
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeParamsNomCanonique", methodeParamsClassePart.nomCanonique(classeLangueNom));
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeParamsNomSimpleComplet", methodeParamsClassePart.nomSimpleComplet(classeLangueNom));
							stockerListeSolr(methodeDoc, "methodeParamsArgsVariables", methodeParamQdox.isVarArgs());
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) { 
									String methodeParamVarLangue = regex("^Param" + methodeParamNum + "\\.var\\." + langueNom + ": (.*)", methodeCommentaire);
									if(methodeParamVarLangue == null)
										methodeParamVarLangue = methodeParamVar;
//									ClasseParts methodeParamClassePartsLangue = ClasseParts.initClasseParts(this, methodeParamsClassePart, langueNom);
	
									stockerListeSolr(langueNom, methodeDoc, "methodeParamsNomCanonique", methodeParamsClassePart.nomCanonique(langueNom));
									stockerListeSolr(langueNom, methodeDoc, "methodeParamsNomSimpleComplet", methodeParamsClassePart.nomSimpleComplet(langueNom));
									stockerListeSolr(langueNom, methodeDoc, "methodeParamsVar", methodeParamVarLangue);
								}  
							}
						}

						List<JavaTypeVariable<JavaGenericDeclaration>> methodeParamsType = methodeQdox.getTypeParameters();
						for(JavaTypeVariable<JavaGenericDeclaration> methodeParamType : methodeParamsType) {
							String methodeParamTypeNom = methodeParamType.getName();
							stockerListeSolr(methodeDoc, "methodeParamsTypeNom", methodeParamTypeNom);
						}

						for(JavaAnnotation annotation : annotations) {
							ClasseParts methodeAnnotationClasseParts = ClasseParts.initClasseParts(this, annotation.getType(), classeLangueNom);
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeAnnotationsNomSimpleComplet", methodeAnnotationClasseParts.nomSimpleComplet(classeLangueNom));
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeAnnotationsBlocCode", StringUtils.substringAfter(annotation.toString(), methodeAnnotationClasseParts.nomSimple(classeLangueNom)));
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) {  
//									ClasseParts methodeAnnotationClassePartsLangue = ClasseParts.initClasseParts(this, methodeAnnotationClasseParts, langueNom);
									stockerListeSolr(langueNom, methodeDoc, "methodeAnnotationsNomSimpleComplet", methodeAnnotationClasseParts.nomSimpleComplet(langueNom));
									stockerListeSolr(langueNom, methodeDoc, "methodeAnnotationsBlocCode", StringUtils.substringAfter(annotation.toString(), methodeAnnotationClasseParts.nomSimple(langueNom)));
								}
							}
						}

						for(JavaClass methodeExceptionQdox : methodeExceptionsQdox) { 
							ClasseParts methodeExceptionClasseParts = ClasseParts.initClasseParts(this, methodeExceptionQdox.getCanonicalName(), classeLangueNom);
							stockerListeSolr(methodeDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionClasseParts.nomSimpleComplet(classeLangueNom));
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) {  
									stockerListeSolr(methodeDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionClasseParts.nomSimpleComplet(langueNom));
								}
							}
						}

						Boolean methodeEstVide = false;
						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void")) {
							ClasseParts methodeRetourClasseParts = ClasseParts.initClasseParts(this, methodeQdox.getReturns(), classeLangueNom);
							stockerSolr(classeLangueNom, methodeDoc, "methodeRetourNomSimpleComplet", methodeRetourClasseParts.nomSimpleComplet(classeLangueNom));
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) { 
//									ClasseParts methodeRetourClassePartsLangue = ClasseParts.initClasseParts(this, methodeRetourClasseParts, langueNom);
									stockerSolr(langueNom, methodeDoc, "methodeRetourNomSimpleComplet", methodeRetourClasseParts.nomSimpleComplet(langueNom));
								}
							}
						}
						else {
							methodeEstVide = true;
						}
						methodeEstVide = indexerStockerSolr(methodeDoc, "methodeEstVide", methodeEstVide);
	
						String methodeCle = classeCheminAbsolu + "." + methodeVar + "(";
						for(int i = 0; i < methodeParamsQdox.size(); i++) {
							JavaParameter paramQdox = methodeParamsQdox.get(i);
							if(i > 0)
								methodeCle += ", ";
							methodeCle += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
						}
						methodeCle += ")";  
		
						// Methodes Solr du methode. 
		
						methodeDoc.addField("id", methodeCle);
						indexerStockerSolr(methodeDoc, "partEstMethode", true);
						indexerStockerSolr(methodeDoc, "partNumero", partNumero);

						indexerStockerSolr(methodeDoc, "methodeEstPublic", methodeQdox.isPublic());
						indexerStockerSolr(methodeDoc, "methodeEstProtege", methodeQdox.isProtected());
						indexerStockerSolr(methodeDoc, "methodeEstPrive", methodeQdox.isPrivate());
						indexerStockerSolr(methodeDoc, "methodeEstStatique", methodeQdox.isStatic());
						indexerStockerSolr(methodeDoc, "methodeEstFinale", methodeQdox.isFinal());
						indexerStockerSolr(methodeDoc, "methodeEstAbstrait", methodeQdox.isAbstract());
						indexerStockerSolr(methodeDoc, "methodeEstNatif", methodeQdox.isNative());
						indexerStockerSolr(methodeDoc, "methodeEstTest", methodeEstTest);
						indexerStockerSolr(methodeDoc, "methodeEstSubstitue", methodeEstSubstitue);
						stockerRegexCommentaires(classeLangueNom, methodeDoc, "methodeCommentaire", methodeCommentaire);
	
						String methodeVarLangue = regex("^Var\\." + classeLangueNom + ": (.*)", methodeCommentaire);
						methodeVarLangue =  methodeVarLangue == null ? methodeVar : methodeVarLangue;
	
						regexListe("^" + classeLangueNom + ":\\s*([^\n]+)", methodeCommentaire);

						String methodeCodeSource = methodeQdox.getSourceCode();
						String methodeCodeSourceComplet = methodeQdox.getCodeBlock();
						String src = regex("([\\s\\S]*?\\w+\\s*\\([^\\)]*\\)[^;\\{]*[;\\{])", methodeCodeSourceComplet, 1);
						if(src.endsWith(";") && !methodeQdox.isAbstract())
							src = StringUtils.substringBeforeLast(src, ";") + " {";
						stockerSolr(classeLangueNom, methodeDoc, "methodeCodeSourceDebut", src);
						stockerSolr(classeLangueNom, methodeDoc, "methodeCodeSourceFin", "}\n");
						String methodeCodeSourceLangue = methodeCodeSource;
						String methodeString = regex("^String\\." + classeLangueNom + ":(.*)", methodeCommentaire);
						if(methodeString != null) {
							methodeCodeSource = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodeString, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
							indexerStockerSolr(classeLangueNom, methodeDoc, "methodeString", methodeString); 
						}
						if(classeTypeContenu != null) {
							methodeCodeSource = RegExUtils.replaceAll(StringUtils.trim(methodeCodeSource), "(?m)^//", "");
							if(StringUtils.isNotBlank(methodeCodeSource))
								methodeCodeSource += "\n";
						}

						stockerSolr(classeLangueNom, methodeDoc, "methodeCodeSource", methodeCodeSource);
						indexerStockerSolr(classeLangueNom, methodeDoc, "methodeNomAffichage", regexLangue(classeLangueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAffichage) + "", methodeCommentaire));

						indexerStockerSolr(methodeDoc, "methodeLigneDebut", methodeQdox.getLineNumber() - (methodeCommentaire == null ? 0 : (methodeCommentaire.split(REGEX_LIGNE).length + 1)));
						indexerStockerSolr(methodeDoc, "methodeLigneFin", methodeQdox.getLineNumber() - (methodeCodeSource == null ? 0 : (methodeCodeSource.split(REGEX_LIGNE).length - 1)));

						if(classeTraduire) {
							for(String langueNom : classeAutresLangues) {  
								methodeVarLangue = regex("^Var\\." + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
								methodeVarLangue = indexerStockerSolr(langueNom, methodeDoc, "methodeVar", methodeVarLangue == null ? methodeVar : methodeVarLangue);
								regexListe("^" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
								methodeCodeSourceLangue = regexRemplacerTout(methodeCommentaire, methodeCodeSource, langueNom);
								indexerStockerListeSolr(langueNom, classeDoc, "classeMethodeVars", methodeVarLangue);
								String methodeStringLangue = regex("^String\\." + langueNom + ":(.*)", methodeCommentaire);
								if(methodeStringLangue != null) {
									methodeCodeSourceLangue = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodeStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
									indexerStockerSolr(langueNom, methodeDoc, "methodeString", methodeStringLangue); 
								}
								stockerSolr(langueNom, methodeDoc, "methodeCodeSource", methodeCodeSourceLangue);
								stockerRegexCommentaires(langueNom, methodeDoc, "methodeCommentaire", methodeCommentaire);
								indexerStockerSolr(langueNom, methodeDoc, "methodeNomAffichage", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAffichage) + "", methodeCommentaire));
							} 
						}

						if(methodeCommentaire != null) {

							Matcher methodeValsRecherche = Pattern.compile("^Val(:([^:\n]+):)?\\.(\\w+)(\\.([^:\n]+))?:(.*)", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean methodeValsTrouves = methodeValsRecherche.find();
							while(methodeValsTrouves) {
								String methodeValVar = methodeValsRecherche.group(3);
								String methodeValLangue = methodeValsRecherche.group(5);
								String methodeValCode = methodeValsRecherche.group(2);
								String methodeValValeur = methodeValsRecherche.group(6);
								if(methodeValCode == null)
									methodeValCode = "";
								if(methodeValLangue == null) {
									stockerListeSolr(methodeDoc, "methodeValsVar", methodeValVar);
									stockerListeSolr(methodeDoc, "methodeValsLangue", "");
									stockerListeSolr(methodeDoc, "methodeValsCode", methodeValCode);
									stockerListeSolr(methodeDoc, "methodeValsValeur", methodeValValeur);
								}
								else {
									stockerListeSolr(methodeDoc, "methodeValsVar", methodeValVar);
									stockerListeSolr(methodeDoc, "methodeValsLangue", methodeValLangue);
									stockerListeSolr(methodeDoc, "methodeValsCode", methodeValCode);
									stockerListeSolr(methodeDoc, "methodeValsValeur", methodeValValeur);
								}
								methodeValsTrouves = methodeValsRecherche.find();
							}
						}
	
						clientSolrComputate.add(methodeDoc); 
					}
				}
			}
		}

		if(classeVarSuggere == null && classeSuperDoc != null) {
			classeVarSuggere = (String)classeSuperDoc.get("classeVarSuggere_" + classeLangueNom + "_stored_string");
			if(classeVarSuggere != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarSuggere", classeVarSuggere);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarSuggereLangue = (String)classeSuperDoc.get("classeVarSuggere_" + langueNom + "_stored_string");
						if(classeVarSuggereLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarSuggere", classeVarSuggereLangue);
						}
					}
				}
			}
		}
		if(classeVarTexte == null && classeSuperDoc != null) {
			classeVarTexte = (String)classeSuperDoc.get("classeVarTexte_" + classeLangueNom + "_stored_string");
			if(classeVarTexte != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarTexte", classeVarTexte);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarTexteLangue = (String)classeSuperDoc.get("classeVarTexte_" + langueNom + "_stored_string");
						if(classeVarTexteLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarTexte", classeVarTexteLangue);
						}
					}
				}
			}
		}
		if(classeVarClePrimaire == null && classeSuperDoc != null) {
			classeVarClePrimaire = (String)classeSuperDoc.get("classeVarClePrimaire_" + classeLangueNom + "_stored_string");
			if(classeVarClePrimaire != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarClePrimaire", classeVarClePrimaire);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarClePrimaireLangue = (String)classeSuperDoc.get("classeVarClePrimaire_" + langueNom + "_stored_string");
						if(classeVarClePrimaireLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarClePrimaire", classeVarClePrimaireLangue);
						}
					}
				}
			}
		}
		if(classeVarInheritClePrimaire == null && classeSuperDoc != null) {
			classeVarInheritClePrimaire = (String)classeSuperDoc.get("classeVarInheritClePrimaire_" + classeLangueNom + "_stored_string");
			if(classeVarInheritClePrimaire != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarInheritClePrimaire", classeVarInheritClePrimaire);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarInheritClePrimaireLangue = (String)classeSuperDoc.get("classeVarInheritClePrimaire_" + langueNom + "_stored_string");
						if(classeVarInheritClePrimaireLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarInheritClePrimaire", classeVarInheritClePrimaireLangue);
						}
					}
				}
			}
		}
		if(classeVarSauvegardes == null && classeSuperDoc != null) {
			classeVarSauvegardes = (String)classeSuperDoc.get("classeVarSauvegardes_" + classeLangueNom + "_stored_string");
			if(classeVarSauvegardes != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarSauvegardes", classeVarSauvegardes);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarSauvegardesLangue = (String)classeSuperDoc.get("classeVarSauvegardes_" + langueNom + "_stored_string");
						if(classeVarSauvegardesLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarSauvegardes", classeVarSauvegardesLangue);
						}
					}
				}
			}
		}
		if(classeVarCleUnique == null && classeSuperDoc != null) {
			classeVarCleUnique = (String)classeSuperDoc.get("classeVarCleUnique_" + classeLangueNom + "_stored_string");
			if(classeVarCleUnique != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarCleUnique", classeVarCleUnique);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarCleUniqueLangue = (String)classeSuperDoc.get("classeVarCleUnique_" + langueNom + "_stored_string");
						if(classeVarCleUniqueLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarCleUnique", classeVarCleUniqueLangue);
						}
					}
				}
			}
		}
		if(classeVarModifie == null && classeSuperDoc != null) {
			classeVarModifie = (String)classeSuperDoc.get("classeVarModifie_" + classeLangueNom + "_stored_string");
			if(classeVarModifie != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarModifie", classeVarModifie);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarModifieLangue = (String)classeSuperDoc.get("classeVarModifie_" + langueNom + "_stored_string");
						if(classeVarModifieLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarModifie", classeVarModifieLangue);
						}
					}
				}
			}
		}
		if(classeVarCree == null && classeSuperDoc != null) {
			classeVarCree = (String)classeSuperDoc.get("classeVarCree_" + classeLangueNom + "_stored_string");
			if(classeVarCree != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarCree", classeVarCree);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarCreeLangue = (String)classeSuperDoc.get("classeVarCree_" + langueNom + "_stored_string");
						if(classeVarCreeLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarCree", classeVarCreeLangue);
						}
					}
				}
			}
		}
		if(classeVarUrlId == null && classeSuperDoc != null) {
			classeVarUrlId = (String)classeSuperDoc.get("classeVarUrlId_" + classeLangueNom + "_stored_string");
			if(classeVarUrlId != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarUrlId", classeVarUrlId);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarUrlIdLangue = (String)classeSuperDoc.get("classeVarUrlId_" + langueNom + "_stored_string");
						if(classeVarUrlIdLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarUrlId", classeVarUrlIdLangue);
						}
					}
				}
			}
		}
		if(classeVarUrlPk == null && classeSuperDoc != null) {
			classeVarUrlPk = (String)classeSuperDoc.get("classeVarUrlPk_" + classeLangueNom + "_stored_string");
			if(classeVarUrlPk != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarUrlPk", classeVarUrlPk);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarUrlPkLangue = (String)classeSuperDoc.get("classeVarUrlPk_" + langueNom + "_stored_string");
						if(classeVarUrlPkLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarUrlPk", classeVarUrlPkLangue);
						}
					}
				}
			}
		}
		if(classeVarUrlApi == null && classeSuperDoc != null) {
			classeVarUrlApi = (String)classeSuperDoc.get("classeVarUrlApi_" + classeLangueNom + "_stored_string");
			if(classeVarUrlApi != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarUrlApi", classeVarUrlApi);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarUrlApiLangue = (String)classeSuperDoc.get("classeVarUrlApi_" + langueNom + "_stored_string");
						if(classeVarUrlApiLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarUrlApi", classeVarUrlApiLangue);
						}
					}
				}
			}
		}
		if(classeVarId == null && classeSuperDoc != null) {
			classeVarId = (String)classeSuperDoc.get("classeVarId_" + classeLangueNom + "_stored_string");
			if(classeVarId != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarId", classeVarId);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarIdLangue = (String)classeSuperDoc.get("classeVarId_" + langueNom + "_stored_string");
						if(classeVarIdLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarId", classeVarIdLangue);
						}
					}
				}
			}
		}
		if(classeVarTitre == null && classeSuperDoc != null) {
			classeVarTitre = (String)classeSuperDoc.get("classeVarTitre_" + classeLangueNom + "_stored_string");
			if(classeVarTitre != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarTitre", classeVarTitre);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarTitreLangue = (String)classeSuperDoc.get("classeVarTitre_" + langueNom + "_stored_string");
						if(classeVarTitreLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarTitre", classeVarTitreLangue);
						}
					}
				}
			}
		}

		for(String classeInitLoinException : classeInitLoinExceptions) {
			indexerListeSolr(classeDoc, "classeInitLoinExceptions", classeInitLoinException); 
			stockerListeSolr(classeDoc, "classeInitLoinExceptions", classeInitLoinException); 
		}

		indexerStockerSolr(classeDoc, "classeMotsClesTrouves", classeMotsClesTrouves); 
		for(String classeMotCleValeur : classeMotsCles) {
			indexerListeSolr(classeDoc, "classeMotsCles", classeMotCleValeur); 
			stockerListeSolr(classeDoc, "classeMotsCles", classeMotCleValeur); 
		}
		
		classePartsGenAjouter(classePartsCouverture, classeLangueNom);

		ArrayList<String> classeApiMethodes = regexListe("^" + classeLangueConfig.getString(ConfigCles.var_ApiMethode) + ":\\s*(.*)", classeCommentaire);
		if(!classeApiMethodes.contains("Recherche") && classeMotsClesTrouves && (classeMotsCles.contains("Recherche.requete") || classeMotsCles.contains("Recherche.reponse")))
			classeApiMethodes.add("Recherche");
		if(!classeApiMethodes.contains("POST") && classeMotsClesTrouves && (classeMotsCles.contains("POST.requete") || classeMotsCles.contains("POST.reponse")))
			classeApiMethodes.add("POST");
		if(!classeApiMethodes.contains("PATCH") && classeMotsClesTrouves && (classeMotsCles.contains("PATCH.requete") || classeMotsCles.contains("PATCH.reponse")))
			classeApiMethodes.add("PATCH");
		if(!classeApiMethodes.contains("GET") && classeMotsClesTrouves && (classeMotsCles.contains("GET.requete") || classeMotsCles.contains("GET.reponse")))
			classeApiMethodes.add("GET");
		if(!classeApiMethodes.contains("PUT") && classeMotsClesTrouves && (classeMotsCles.contains("PUT.requete") || classeMotsCles.contains("PUT.reponse")))
			classeApiMethodes.add("PUT");
		if(!classeApiMethodes.contains("DELETE") && classeMotsClesTrouves && (classeMotsCles.contains("DELETE.requete") || classeMotsCles.contains("DELETE.reponse")))
			classeApiMethodes.add("DELETE");

		String classeNomSimpleLangue = (String)classeDoc.get("classeNomSimple_" + langueNomGlobale + "_stored_string").getValue();

		if(classeApi) {

			for(String langueNom : toutesLangues) {
				YAMLConfiguration langueConfig = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/i18n/i18n_%s.yml", appComputate, langueNom));
				String classeApiUri = indexerStockerSolrRegex(langueNom, classeDoc, "classeApiUri", "ApiUri", classeCommentaire);
				String classeApiTag = indexerStockerSolrRegex(langueNom, classeDoc, "classeApiTag", "ApiTag", classeCommentaire);

				classePartsGenApi.clear();
		
				if(classePartsRequeteSite.getEtendBase())
					classePartsGenApiAjouter(ClasseParts.initClasseParts(this, classePartsRequeteSite.getNomCanoniqueSuperGenerique(), classeLangueNom), classeLangueNom);
				else
					classePartsGenApiAjouter(classePartsRequeteSite, classeLangueNom);
				classePartsGenApiAjouter(classePartsUtilisateurSite, classeLangueNom);
				classePartsGenApiAjouter(classePartsRequeteApi, classeLangueNom);
				classePartsGenApiAjouter(classePartsResultatRecherche, classeLangueNom);
				classePartsGenApiAjouter(classePartsMailVerticle, classeLangueNom);
				classePartsGenApiAjouter(classePartsConfigCles, classeLangueNom);
				classePartsGenApiAjouter(classePartsBaseApiServiceImpl, classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.client.WebClient", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Objects", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.WorkerExecutor", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.eventbus.EventBus", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.pgclient.PgPool", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.impl.JsonUtil", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.authorization.AuthorizationProvider", classeLangueNom), classeLangueNom);
				if(classePage)
					classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.templ.handlebars.HandlebarsTemplateEngine", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.eventbus.DeliveryOptions", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.IOException", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Collections", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Map", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.concurrent.TimeUnit", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.Instant", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.stream.Collectors", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.Json", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.StringUtils", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.security.Principal", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.PrintWriter", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Collection", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.math.BigDecimal", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, RoundingMode.class.getCanonicalName(), classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Date", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.computate.search.serialize.ComputateZonedDateTimeSerializer", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.List", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.ArrayList", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Arrays", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Set", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.HashSet", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Handler", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.RoutingContext", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, NumberUtils.class.getCanonicalName(), classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.Router", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Vertx", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.reactivestreams.ReactiveReadStream", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.reactivestreams.ReactiveWriteStream", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.MultiMap", classeLangueNom), classeLangueNom);
				if(activerOpenIdConnect)
					classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.oauth2.OAuth2Auth", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, Logger.class.getCanonicalName(), classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, LoggerFactory.class.getCanonicalName(), classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.sqlclient.Transaction", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.sqlclient.SqlConnection", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.sqlclient.Tuple", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.sqlclient.Row", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonArray", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonObject", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDateTime", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.LocalTime", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.sql.Timestamp", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Future", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Promise", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.MultiMap", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.AsyncResult", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.net.URLEncoder", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Handler", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.buffer.Buffer", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.CompositeFuture", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpHeaders", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.nio.charset.Charset", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.service.ServiceRequest", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.service.ServiceResponse", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.client.predicate.ResponsePredicate", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.HashMap", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.User", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Optional", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.stream.Stream", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.net.URLDecoder", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.regex.Pattern", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.regex.Matcher", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Map.Entry", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Iterator", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.computate.search.tool.SearchTool", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.computate.search.response.solr.SolrResponse", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, Base64.class.getCanonicalName(), classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, ZonedDateTime.class.getCanonicalName(), classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, CollectionUtils.class.getCanonicalName(), classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, BooleanUtils.class.getCanonicalName(), classeLangueNom), classeLangueNom);
				if(classePartsUtilisateurSite == null)
					System.err.println(String.format("%s %s %s %s %s. ", classeLangueConfig.getString(ConfigCles.var_classe), classeLangueConfig.getString(ConfigCles.var_UtilisateurSite), classeLangueConfig.getString(ConfigCles.var_manquante), classeLangueConfig.getString(ConfigCles.var_dans), cheminSrcMainJava));
				else
					classePartsGenApiAjouter(ClasseParts.initClasseParts(this, classePartsUtilisateurSite.nomCanonique(classeLangueNom) + StringUtils.capitalize(classeLangueNom) + "ApiServiceImpl", classeLangueNom), classeLangueNom);

				classePartsGenApiAjouter(classePartsListeRecherche, classeLangueNom);

				Matcher classeApiMethodesRegex = Pattern.compile("^" + classeLangueConfig.getString(ConfigCles.var_ApiMethode) + "(\\.([^:\n]+))?:\\s*(.*)", Pattern.MULTILINE).matcher(classeCommentaire);
				boolean classeApiMethodesTrouves = classeApiMethodesRegex.find();
				while(classeApiMethodesTrouves) {
					String classeApiMethode = classeApiMethodesRegex.group(3);
					String classeApiMethodeLangue = classeApiMethodesRegex.group(2);
	
					if(classeApiMethodeLangue == null || StringUtils.equals(classeApiMethodeLangue, langueNom)) {

						indexerStockerListeSolr(langueNom, classeDoc, "classeApiMethodes", classeApiMethode); 
		
						String classeApiMethodeMethode;
						if(StringUtils.contains(classeApiMethode, "POST"))
							classeApiMethodeMethode = "POST";
						else if(StringUtils.contains(classeApiMethode, "PATCH"))
							classeApiMethodeMethode = "PATCH";
						else if(StringUtils.contains(classeApiMethode, "DELETE"))
							classeApiMethodeMethode = "DELETE";
						else if(StringUtils.contains(classeApiMethode, "PUT"))
							classeApiMethodeMethode = "PUT";
						else
							classeApiMethodeMethode = "GET";
		
						indexerStockerSolr(langueNom, classeDoc, "classeApiMethode" + classeApiMethode, regex("^" + classeLangueConfig.getString(ConfigCles.var_ApiMethode) + "\\." + classeApiMethode + "." + langueNom + ":\\s*(.*)", classeCommentaire, classeApiMethodeMethode));
		
						String classeApiUriMethode = regexLangue(langueNom, "(classe)?ApiUri" + "\\." + classeApiMethode + "." + langueNom, classeCommentaire);
						Boolean classeRoleUtilisateurMethode = indexerStockerSolr(langueNom, classeDoc, "classeRoleUtilisateur" + classeApiMethode, regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_RoleUtilisateur) + "\\." + classeApiMethode + "\\." + langueNom + ":\\s*(true)$", classeCommentaire));
		
						indexerStockerSolrRegex(langueNom, classeDoc, "classeApiOperationId" + classeApiMethode, "ApiOperationId" + classeApiMethode + "." + langueNom, classeCommentaire, StringUtils.lowerCase(classeApiMethode) + classeNomSimpleLangue);
						indexerStockerSolrRegex(langueNom, classeDoc, "classeApiOperationId" + classeApiMethode + "Requete", "ApiOperationId" + classeApiMethode + "Requete" + "." + langueNom, classeCommentaire, classeApiMethode + classeNomSimpleLangue + langueConfig.getString(ConfigCles.var_Requete));
						indexerStockerSolrRegex(langueNom, classeDoc, "classeApiOperationId" + classeApiMethode + "Reponse", "ApiOperationId" + classeApiMethode + "Reponse" + "." + langueNom, classeCommentaire, classeApiMethode + classeNomSimpleLangue + langueConfig.getString(ConfigCles.var_Reponse));
						indexerStockerSolrRegex(langueNom, classeDoc, "classeApiDescription" + classeApiMethode, "ApiDescription" + classeApiMethode + "." + langueNom, classeCommentaire, regexLangue(langueNom, "(classe)?Description" + "\\." + classeApiMethode, classeCommentaire));
						indexerStockerSolr(langueNom, classeDoc, "classeApiInterne" + classeApiMethode, regexTrouve("^Api" + classeLangueConfig.getString(ConfigCles.var_Interne) + "\\." + classeApiMethode + ": \\s*(true)$", classeCommentaire));
		
						if(classeEtendBase && classeSuperDoc != null) {
							indexerStockerSolr(langueNom, classeDoc, "classeSuperApiOperationId" + classeApiMethode, (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string"));
							indexerStockerSolr(langueNom, classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Requete", (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Requete" + "_" + langueNom + "_stored_string"));
							indexerStockerSolr(langueNom, classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Reponse", (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Reponse" + "_" + langueNom + "_stored_string"));
						}
		
						String classePageNomSimpleMethode = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Page) + "\\." + classeApiMethode, classeCommentaire);
						String classePageSuperNomSimpleMethode = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_PageSuper) + "\\." + classeApiMethode, classeCommentaire, "Object");
						String classeApiTypeMediaRequeteMethode = regexLangue(langueNom, "^Api" + langueConfig.getString(ConfigCles.var_TypeMedia) + langueConfig.getString(ConfigCles.var_Requete) + "\\." + classeApiMethode, classeCommentaire, "application/json");
						String classeApiTypeMedia200Methode = regexLangue(langueNom, "^ApiTypeMedia200" + "\\." + classeApiMethode, classeCommentaire, classePageNomSimpleMethode == null ? "application/json" : "text/html");
						String classeApiMotCleMethode = regexLangue(langueNom, "^ApiMotCle" + classeApiMethode, classeCommentaire);
						if(StringUtils.contains(classeApiMethode, "POST")
								|| StringUtils.contains(classeApiMethode, langueConfig.getString(ConfigCles.var_Recherche))
								|| StringUtils.contains(classeApiMethode, "PATCH")
								|| StringUtils.contains(classeApiMethode, "PUT")
								) {
							if(StringUtils.isBlank(classeApiMotCleMethode))
								classeApiMotCleMethode = StringUtils.substringAfterLast(classeApiUriMethode, "/");
							if(StringUtils.isBlank(classeApiUriMethode)) {
								if("PUTImport".equals(classeApiMethode))
									classeApiUriMethode = classeApiUri + "-import";
								else if(langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode))
									classeApiUriMethode = classeApiUri + "/" + langueConfig.getString(ConfigCles.var_copie);
								else if(langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode))
									classeApiUriMethode = classeApiUri + "/" + langueConfig.getString(ConfigCles.var_fusion);
								else
									classeApiUriMethode = classeApiUri;
							}
						}
						else {
							if(StringUtils.isBlank(classeApiMotCleMethode))
								classeApiMotCleMethode = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(classeApiUriMethode, "/"), "/");
							if(StringUtils.isBlank(classeApiUriMethode))
								classeApiUriMethode = classeApiUri + "/{id}";
						}

						if(this.langueNomGlobale.equals(classeLangueNom)) {
							indexerStockerSolr(langueNom, classeDoc, "classeApiTypeMedia200" + classeApiMethode, classeApiTypeMedia200Methode);
							if(StringUtils.contains(classeApiMethode, "POST")
									|| StringUtils.contains(classeApiMethode, "PATCH")
									|| StringUtils.contains(classeApiMethode, "PUT")
									) {
								indexerStockerSolr(langueNom, classeDoc, "classeApiTypeMediaRequete" + classeApiMethode, classeApiTypeMediaRequeteMethode);
							}
						}
						indexerStockerSolr(langueNom, classeDoc, "classeApiMotCle" + classeApiMethode, classeApiMotCleMethode);
						indexerStockerSolr(langueNom, classeDoc, "classeApiUri" + classeApiMethode, classeApiUriMethode);

						if(classePageNomSimpleMethode != null) {
							String classePageLangueNom = null;

							classePartsGenPage.clear();
		
							SolrQuery recherchePage = new SolrQuery();   
							recherchePage.setQuery("*:*");
							recherchePage.setRows(1);
							recherchePage.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classePageNomSimpleMethode));
							recherchePage.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
							recherchePage.addFilterQuery("partEstClasse_indexed_boolean:true");
							QueryResponse reponseRecherchePage = clientSolrComputate.query(recherchePage);
							SolrDocumentList listeRecherchePage = reponseRecherchePage.getResults();
		
							if(listeRecherchePage.size() > 0) {
								SolrDocument docEntite = listeRecherchePage.get(0);
								String classeNomEnsembleLangue = (String)classeDoc.get("classeNomEnsemble_" + langueNom + "_indexed_string").getValue();
								String classePageNomCanoniqueMethode = (String)docEntite.get("classeNomCanonique_" + langueNom + "_stored_string");
								indexerStockerSolr(langueNom, classeDoc, "classePageNomCanonique" + classeApiMethode, classePageNomCanoniqueMethode);
								indexerStockerSolr(langueNom, classeDoc, "classePageNomSimple" + classeApiMethode, classePageNomSimpleMethode);

								String siteCheminVertxLangue = siteCheminsVertx.get(langueNom);
								String cheminSrcMainJavaVertxLangue = (siteCheminVertxLangue == null ? siteChemin : siteCheminVertxLangue) + "/src/main/java";
	
								String classeGenPageNomSimple;
								String classeGenPageChemin;
								if(StringUtils.contains(classePageNomSimpleMethode, "Page")) {
									classeGenPageNomSimple = StringUtils.substringBeforeLast(classePageNomSimpleMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classePageNomSimpleMethode, "Page");
									classeGenPageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
								}
								else {
									classeGenPageNomSimple = "Gen" + classePageNomSimpleMethode;
									classeGenPageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
								}
								indexerStockerSolr(langueNom, classeDoc, "classeGenPageNomSimple" + classeApiMethode, classeGenPageNomSimple);
								String classeGenPageNomCanonique = classeNomEnsembleLangue + "." + classeGenPageNomSimple;
								indexerStockerSolr(langueNom, classeDoc, "classeGenPageNomCanonique" + classeApiMethode, classeGenPageNomCanonique);
								String classePageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimpleMethode, ".java");
								indexerStockerSolr(langueNom, classeDoc, "classeGenPageChemin" + classeApiMethode, classeGenPageChemin); 
								indexerStockerSolr(langueNom, classeDoc, "classePageChemin" + classeApiMethode, classePageChemin); 
								classePageLangueNom = langueNom;

								classePartsGenApiAjouter(ClasseParts.initClasseParts(this, classePageNomCanoniqueMethode, classePageLangueNom), classePageLangueNom);
							}

							if(classePageLangueNom == null) {
								String classeNomEnsembleLangue = (String)classeDoc.get("classeNomEnsemble_" + langueNom + "_indexed_string").getValue();
								String classeGenPageNomSimple;
								String classeGenPageChemin;
								String classePageChemin;

								String siteCheminVertxLangue = siteCheminsVertx.get(langueNom);
								String cheminSrcMainJavaVertxLangue = (siteCheminVertxLangue == null ? siteChemin : siteCheminVertxLangue) + "/src/main/java";

								if(StringUtils.contains(classePageNomSimpleMethode, "Page")) {
									classeGenPageNomSimple = StringUtils.substringBeforeLast(classePageNomSimpleMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classePageNomSimpleMethode, "Page");
									classeGenPageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
									classePageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimpleMethode, ".java");
								}
								else {
									classeGenPageNomSimple = "Gen" + classePageNomSimpleMethode;
									classeGenPageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
									classePageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimpleMethode, ".java");
								}
								indexerStockerSolr(langueNom, classeDoc, "classeGenPageNomSimple" + classeApiMethode, classeGenPageNomSimple);
								indexerStockerSolr(langueNom, classeDoc, "classeGenPageChemin" + classeApiMethode, classeGenPageChemin); 
								indexerStockerSolr(langueNom, classeDoc, "classePageChemin" + classeApiMethode, classePageChemin); 
								classePageLangueNom = langueNom;

								String classePageNomCanoniqueMethode = classeNomEnsemble + "." + classePageNomSimpleMethode;
								String classeGenPageNomCanonique = classeNomEnsembleLangue + "." + classeGenPageNomSimple;
								indexerStockerSolr(langueNom, classeDoc, "classeGenPageNomCanonique" + classeApiMethode, classeGenPageNomCanonique);
								
								indexerStockerSolr(langueNom, classeDoc, "classePageNomCanonique" + classeApiMethode, classePageNomCanoniqueMethode);
								indexerStockerSolr(langueNom, classeDoc, "classePageNomSimple" + classeApiMethode, classePageNomSimpleMethode);
							}

							if(classePageSuperNomSimpleMethode != null) {
								SolrQuery recherchePageSuper = new SolrQuery();   
								recherchePageSuper.setQuery("*:*");
								recherchePageSuper.setRows(1);
								recherchePageSuper.addFilterQuery("classeNomSimple_" + classePageLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classePageSuperNomSimpleMethode));
								recherchePageSuper.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
								recherchePageSuper.addFilterQuery("partEstClasse_indexed_boolean:true");
								recherchePageSuper.addFilterQuery("langueNom_indexed_string:" + ClientUtils.escapeQueryChars(classePageLangueNom));
								QueryResponse reponseRecherchePageSuper = clientSolrComputate.query(recherchePageSuper);
								SolrDocumentList listeRecherchePageSuper = reponseRecherchePageSuper.getResults();
			
								if(listeRecherchePageSuper.size() > 0) {
									SolrDocument docPageSuper = listeRecherchePageSuper.get(0);
									String classePageSuperNomCanoniqueMethode = (String)docPageSuper.get("classeNomCanonique_" + classePageLangueNom + "_stored_string");
									indexerStockerSolr(langueNom, classeDoc, "classePageSuperNomCanonique" + classeApiMethode, classePageSuperNomCanoniqueMethode);
									indexerStockerSolr(langueNom, classeDoc, "classePageSuperNomSimple" + classeApiMethode, classePageSuperNomSimpleMethode);
									classePartsGenPageAjouter(ClasseParts.initClasseParts(this, classePageSuperNomCanoniqueMethode, classePageLangueNom), classeLangueNom);
								}
								else {
									if(classePartsMiseEnPage != null) {
										indexerStockerSolr(langueNom, classeDoc, "classePageSuperNomCanonique" + classeApiMethode, (String)classePartsMiseEnPage.nomCanonique(classePageLangueNom));
										indexerStockerSolr(langueNom, classeDoc, "classePageSuperNomSimple" + classeApiMethode, (String)classePartsMiseEnPage.nomSimple(classePageLangueNom));
										classePartsGenPageAjouter(classePartsMiseEnPage, classeLangueNom);
									}
								}
							}
							else {
								if(classePartsMiseEnPage != null) {
									indexerStockerSolr(langueNom, classeDoc, "classePageSuperNomCanonique" + classeApiMethode, (String)classePartsMiseEnPage.nomCanonique(classePageLangueNom));
									indexerStockerSolr(langueNom, classeDoc, "classePageSuperNomSimple" + classeApiMethode, (String)classePartsMiseEnPage.nomSimple(classePageLangueNom));
									classePartsGenPageAjouter(classePartsMiseEnPage, classeLangueNom);
								}
							}
							if(classePartsMiseEnPage != null) {
								classePartsGenPageAjouter(ClasseParts.initClasseParts(this, classePartsMiseEnPage.nomCanonique(classeLangueNom), classeLangueNom), classeLangueNom);
							}
	
							String classePageCheminCss = concat(siteChemin, "-static/css/", langueNom, "/", classePageNomSimpleMethode, ".css");
							String classePageCheminJs = concat(siteChemin, "-static/js/", langueNom, "/", classePageNomSimpleMethode, ".js");
							String classePageCheminHbs = concat(siteChemin, "-static/template/", langueNom, "/", classePageNomSimpleMethode, ".hbs");
				
							indexerStockerSolr(langueNom, classeDoc, "classePageCheminCss" + classeApiMethode, classePageCheminCss); 
							indexerStockerSolr(langueNom, classeDoc, "classePageCheminJs" + classeApiMethode, classePageCheminJs); 
							indexerStockerSolr(langueNom, classeDoc, "classePageCheminHbs" + classeApiMethode, classePageCheminHbs); 
							indexerStockerSolr(langueNom, classeDoc, "classePageLangueNom" + classeApiMethode, classePageLangueNom); 
							classePage = true;
						}
					}
					classeApiMethodesTrouves = classeApiMethodesRegex.find();
				}
		
				for(ClasseParts classePartGenApi : classePartsGenApi.values()) {
//					ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGenApi, langueNom);
//					if(classeImportationClassePartsLangue.getLangueNom() == null || classeImportationClassePartsLangue.getLangueNom().equals(langueNom))
//						indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGenApi", classeImportationClassePartsLangue.nomCanonique(langueNom));
					indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGenApi", classePartGenApi.nomCanonique(langueNom));
				}
			}
		}

		if(classePage) {
			String classePageNomSimple = classeNomSimpleLangue + classeLangueConfig.getString(ConfigCles.var_Page);
			String classePageSuperNomSimple = regexLangue(langueNomGlobale, "^" + classeLangueConfig.getString(ConfigCles.var_PageSuper), classeCommentaire, "Object");
			String classeNomEnsembleLangue = (String)classeDoc.get("classeNomEnsemble_" + langueNomGlobale + "_indexed_string").getValue();
			String classePageNomCanonique = (String)classeDoc.get("classeNomCanonique_" + langueNomGlobale + "_stored_string").getValue() + classeLangueConfig.getString(ConfigCles.var_Page);
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageNomCanonique", classePageNomCanonique);
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageNomSimple", classePageNomSimple);

			String siteCheminVertxLangue = siteCheminsVertx.get(langueNomGlobale);
			String cheminSrcMainJavaVertxLangue = (siteCheminVertxLangue == null ? siteChemin : siteCheminVertxLangue) + "/src/main/java";

			String classeGenPageNomSimple;
			String classeGenPageChemin;
			if(StringUtils.contains(classePageNomSimple, "Page")) {
				classeGenPageNomSimple = StringUtils.substringBeforeLast(classePageNomSimple, "Page") + "GenPage" + StringUtils.substringAfterLast(classePageNomSimple, "Page");
				classeGenPageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
			}
			else {
				classeGenPageNomSimple = "Gen" + classePageNomSimple;
				classeGenPageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
			}
			indexerStockerSolr(langueNomGlobale, classeDoc, "classeGenPageNomSimple", classeGenPageNomSimple);
			String classeGenPageNomCanonique = classeNomEnsembleLangue + "." + classeGenPageNomSimple;
			indexerStockerSolr(langueNomGlobale, classeDoc, "classeGenPageNomCanonique", classeGenPageNomCanonique);
			String classePageChemin = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimple, ".java");
			indexerStockerSolr(langueNomGlobale, classeDoc, "classeGenPageChemin", classeGenPageChemin); 
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageChemin", classePageChemin); 
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageCheminCss", concat(siteChemin, "-static/css/", langueNomGlobale, "/", classePageNomSimple, ".css"));
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageCheminJs", concat(siteChemin, "-static/js/", langueNomGlobale, "/", classePageNomSimple, ".js"));
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageCheminHbs", concat(siteChemin, "/src/main/resources/templates/", langueNomGlobale, "/", classePageNomSimple, ".hbs"));
			indexerStockerSolr(langueNomGlobale, classeDoc, "classeGenPageCheminHbs", concat(siteChemin, "/src/main/resources/templates/", langueNomGlobale, "/", classeGenPageNomSimple, ".hbs"));
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageLangueNom", langueNomGlobale); 

			if(classePageSuperNomSimple != null) {
				SolrQuery recherchePageSuper = new SolrQuery();   
				recherchePageSuper.setQuery("*:*");
				recherchePageSuper.setRows(1);
				recherchePageSuper.addFilterQuery("classeNomSimple_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classePageSuperNomSimple));
				recherchePageSuper.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
				recherchePageSuper.addFilterQuery("partEstClasse_indexed_boolean:true");
				recherchePageSuper.addFilterQuery("langueNom_indexed_string:" + ClientUtils.escapeQueryChars(classeLangueNom));
				QueryResponse reponseRecherchePageSuper = clientSolrComputate.query(recherchePageSuper);
				SolrDocumentList listeRecherchePageSuper = reponseRecherchePageSuper.getResults();

				if(listeRecherchePageSuper.size() > 0) {
					SolrDocument docPageSuper = listeRecherchePageSuper.get(0);
					String classePageSuperNomCanoniqueMethode = (String)docPageSuper.get("classeNomCanonique_" + classeLangueNom + "_stored_string");
					indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperNomCanonique", classePageSuperNomCanoniqueMethode);
					indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperNomSimple", classePageSuperNomSimple);
					classePartsGenPageAjouter(ClasseParts.initClasseParts(this, classePageSuperNomCanoniqueMethode, classeLangueNom), classeLangueNom);
				}
				else {
					if(classePartsMiseEnPage != null) {
						indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperNomCanonique", (String)classePartsMiseEnPage.nomCanonique(classeLangueNom));
						indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperNomSimple", (String)classePartsMiseEnPage.nomSimple(classeLangueNom));
						classePartsGenPageAjouter(classePartsMiseEnPage, classeLangueNom);
					}
				}
			}
			else {
				if(classePartsMiseEnPage != null) {
					indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperNomCanonique", (String)classePartsMiseEnPage.nomCanonique(classeLangueNom));
					indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperNomSimple", (String)classePartsMiseEnPage.nomSimple(classeLangueNom));
					classePartsGenPageAjouter(classePartsMiseEnPage, classeLangueNom);
				}
			}

			classePartsGenPageAjouter(classePartsRequeteSite, classeLangueNom);
			classePartsGenPageAjouter(classePartsUtilisateurSite, classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.io.IOException", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpServerRequest", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpServerResponse", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(classePartsListeRecherche, classeLangueNom);
			classePartsGenPageAjouter(classePartsCouverture, classeLangueNom);
			classePartsGenPageAjouter(classePartsMiseEnPage, classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, LocalDateTime.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, LocalTime.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, LocalDate.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, ZonedDateTime.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, DateTimeFormatter.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Locale.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonObject", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.service.ServiceRequest", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonArray", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.net.URLDecoder", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, StringUtils.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Map.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, List.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Optional.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Stream.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Collectors.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Arrays.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, BigDecimal.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, RoundingMode.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, MathContext.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, CollectionUtils.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Objects.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Promise", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, classePartsConfigCles.nomCanonique(classeLangueNom), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "org.computate.search.response.solr.SolrResponse", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.util.HashMap", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "org.computate.search.tool.TimeTool", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", classeLangueNom), classeLangueNom);
	
			for(ClasseParts classePartGenPage : classePartsGenPage.values()) {
				indexerStockerListeSolr(langueNomGlobale, classeDoc, "classeImportationsGenPage", classePartGenPage.nomCanonique(classeLangueNom));
			}
		}

		Boolean classeContexte = indexerStockerSolr(classeDoc, "classeContexte", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Contexte) + ": \\s*(true)$", classeCommentaire) || classePage);

		if(classeContexte) {
			contexteCouleur = regex("^" + classeLangueConfig.getString(ConfigCles.var_Couleur) + ":\\s*(.*)", classeCommentaire);
			if(contexteCouleur != null)
				indexerStockerSolr(classeDoc, "contexteCouleur", contexteCouleur); 

			contexteIconeGroupe = regex("^" + classeLangueConfig.getString(ConfigCles.var_IconeGroupe) + ":\\s*(.*)", classeCommentaire);
			if(contexteIconeGroupe != null)
				indexerStockerSolr(classeDoc, "contexteIconeGroupe", contexteIconeGroupe); 

			contexteIconeNom = regex("^" + classeLangueConfig.getString(ConfigCles.var_IconeNom) + ":\\s*(.*)", classeCommentaire);
			if(contexteIconeNom != null)
				indexerStockerSolr(classeDoc, "contexteIconeNom", contexteIconeNom); 

			String contexteRowsStr = regex("^" + classeLangueConfig.getString(ConfigCles.var_Lignes) + ":\\s*(.*)", classeCommentaire);
			if(NumberUtils.isParsable(contexteRowsStr))
				contexteRows = indexerStockerSolr(classeDoc, "contexteRows", Integer.parseInt(contexteRowsStr)); 

			for(String langueNom : toutesLangues) {

				contexteVideoId = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_VideoId) + "", classeCommentaire);
				if(contexteVideoId != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteVideoId", contexteVideoId); 

				contexteUri = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Uri) + "", classeCommentaire);
				if(contexteUri != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteUri", contexteUri); 

				contexteDescription = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Description) + "", classeCommentaire);
				if(contexteDescription != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteDescription", contexteDescription); 

				String contexteImageLargeurStr = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_ImageLargeur) + "", classeCommentaire);
				if(NumberUtils.isCreatable(contexteImageLargeurStr))
					indexerStockerSolr(langueNom, classeDoc, "contexteImageLargeur", Integer.parseInt(contexteImageLargeurStr));

				String contexteImageHauteurStr = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_ImageHauteur) + "", classeCommentaire);
				if(NumberUtils.isCreatable(contexteImageHauteurStr))
					indexerStockerSolr(langueNom, classeDoc, "contexteImageHauteur", Integer.parseInt(contexteImageHauteurStr));
					
				contexteUnNom = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_UnNom) + "", classeCommentaire);
				if(contexteUnNom != null) {
					if("frFR".equals(langueNom)) {
						indexerStockerSolr(langueNom, classeDoc, "contexteUnNom", contexteUnNom); 
						contexteNomSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomSingulier", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomSingulier) + "", classeCommentaire, StringUtils.substringAfter(contexteUnNom, " ")));
						contexteNomPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomPluriel) + "", classeCommentaire, contexteNomSingulier + "s"));
						contexteNomVar = indexerStockerSolr(langueNom, classeDoc, "contexteNomVar", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomVar) + "", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteNomSingulier, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
						contexteLesNoms = indexerStockerSolr(langueNom, classeDoc, "contexteLesNoms", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LesNoms) + "", classeCommentaire, CONTEXTE_frFR_LesPluriel + contexteNomPluriel));
		
						contexteAdjectif = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Adjectif) + "", classeCommentaire);
						if(contexteAdjectif != null) {
							contexteAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteAdjectifPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_AdjectifPluriel) + "", classeCommentaire, contexteAdjectif + CONTEXTE_frFR_AdjectifPluriel));
							contexteAdjectifVar = indexerStockerSolr(langueNom, classeDoc, "contexteAdjectifVar", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_AdjectifVar) + "", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteAdjectif, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
							if(CONTEXTE_frFR_AdjectifAvant) {
								contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifSingulier) + "", classeCommentaire, contexteAdjectif + " " + contexteNomSingulier));
								contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifPluriel) + "", classeCommentaire, contexteAdjectifPluriel + " " + contexteNomPluriel));
							}
							else {
								contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifSingulier) + "", classeCommentaire, contexteNomSingulier + " " + contexteAdjectif));
								contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomSingulierAdjectifPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomSingulierAdjectifPluriel) + "", classeCommentaire, contexteNomPluriel + " " + contexteAdjectifPluriel));
							}
						}
						else {
							contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifSingulier) + "", classeCommentaire, contexteNomSingulier));
							contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifPluriel) + "", classeCommentaire, contexteNomPluriel));
						}
		
						if(contexteUnNom.startsWith(CONTEXTE_frFR_UneFeminin)) {
							contexteCe = indexerStockerSolr(langueNom, classeDoc, "contexteCe", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Ce) + "", classeCommentaire, CONTEXTE_frFR_CetteFemininConsonne));
							contexteUn = indexerStockerSolr(langueNom, classeDoc, "contexteUn", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Un) + "", classeCommentaire, CONTEXTE_frFR_UneFeminin));
							contexteCree = indexerStockerSolr(langueNom, classeDoc, "contexteCree", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Cree) + "", classeCommentaire, CONTEXTE_frFR_CreeeFeminin));
							contexteModifie = indexerStockerSolr(langueNom, classeDoc, "contexteModifie", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Modifie) + "", classeCommentaire, CONTEXTE_frFR_ModifieeFeminin));
							contexteNomActuel = indexerStockerSolr(langueNom, classeDoc, "contexteNomActuel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomActuel) + "", classeCommentaire, CONTEXTE_frFR_ActuelleFemininAvant + contexteNomSingulier + CONTEXTE_frFR_ActuelleFemininApres));
							contexteTous = indexerStockerSolr(langueNom, classeDoc, "contexteTous", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Tous) + "", classeCommentaire, CONTEXTE_frFR_ToutesFemininPluriel));
							contexteTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteTousNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_TousNom) + "", classeCommentaire, CONTEXTE_frFR_ToutesFemininPluriel + CONTEXTE_frFR_LesPluriel + contexteNomPluriel));
							contexteRechercherTousNomPar = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNomPar", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_RechercherTousNomPar) + "", classeCommentaire, CONTEXTE_frFR_Rechercher + contexteNomPluriel + CONTEXTE_frFR_Par));
							contexteRechercherTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_RechercherTousNom) + "", classeCommentaire, CONTEXTE_frFR_Rechercher + contexteNomPluriel));
							contexteAucunNomTrouve = indexerStockerSolr(langueNom, classeDoc, "contexteAucunNomTrouve", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_AucunNomTrouve) + "", classeCommentaire, CONTEXTE_frFR_AucuneTrouveFemininAvant + contexteNomSingulier + CONTEXTE_frFR_AucuneTrouveFemininApres));
							if(contexteAdjectif != null) {
								if(CONTEXTE_frFR_AdjectifAvant)
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_UnNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_UneFeminin + contexteAdjectif + " " + contexteNomSingulier));
								else
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_UnNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_UneFeminin + contexteNomSingulier + " " + contexteAdjectif));
							}
		
							String suffixe = StringUtils.substringAfter(contexteUnNom, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_CeNom) + "", classeCommentaire, CONTEXTE_frFR_CetteFemininVoyelle + suffixe));
								contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNom) + "", classeCommentaire, CONTEXTE_frFR_LFemininVoyelle + suffixe));
								contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_DeNom) + "", classeCommentaire, CONTEXTE_frFR_DVoyelle + suffixe));
								if(contexteAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant) {
										contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_LFemininVoyelle + contexteAdjectif + " " + contexteNomSingulier));
									}
									else {
										contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_LFemininVoyelle + contexteNomSingulier + " " + contexteAdjectif));
									}
								}
							}
							else {
								contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_CeNom) + "", classeCommentaire, CONTEXTE_frFR_CetteFemininConsonne + suffixe));
								contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNom) + "", classeCommentaire, CONTEXTE_frFR_LaFemininConsonne + suffixe));
								contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_DeNom) + "", classeCommentaire, CONTEXTE_frFR_DeConsonne + suffixe));
								if(contexteAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant) {
										contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_LaFemininConsonne + contexteAdjectif + " " + contexteNomSingulier));
									}
									else {
										contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_LaFemininConsonne + contexteNomSingulier + " " + contexteAdjectif));
									}
								}
							}
						}
						else if(contexteUnNom.startsWith(CONTEXTE_frFR_UnMasculin)) {
							contexteCe = indexerStockerSolr(langueNom, classeDoc, "contexteCe", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Ce) + "", classeCommentaire, CONTEXTE_frFR_CeMasculinConsonne));
							contexteUn = indexerStockerSolr(langueNom, classeDoc, "contexteUn", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Un) + "", classeCommentaire, CONTEXTE_frFR_UnMasculin));
							contexteCree = indexerStockerSolr(langueNom, classeDoc, "contexteCree", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Cree) + "", classeCommentaire, CONTEXTE_frFR_CreeMasculin));
							contexteModifie = indexerStockerSolr(langueNom, classeDoc, "contexteModifie", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Modifie) + "", classeCommentaire, CONTEXTE_frFR_ModifieMasculin));
							contexteNomActuel = indexerStockerSolr(langueNom, classeDoc, "contexteNomActuel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomActuel) + "", classeCommentaire, CONTEXTE_frFR_ActuelMasculinAvant + contexteNomSingulier + CONTEXTE_frFR_ActuelMasculinApres));
							contexteTous = indexerStockerSolr(langueNom, classeDoc, "contexteTous", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_TousNom) + "", classeCommentaire, CONTEXTE_frFR_TousMasculinPluriel));
							contexteTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteTousNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_TousNom) + "", classeCommentaire, CONTEXTE_frFR_TousMasculinPluriel + CONTEXTE_frFR_LesPluriel + contexteNomPluriel));
							contexteRechercherTousNomPar = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNomPar", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_RechercherTousNomPar) + "", classeCommentaire, CONTEXTE_frFR_Rechercher + contexteNomPluriel + CONTEXTE_frFR_Par));
							contexteRechercherTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_RechercherTousNom) + "", classeCommentaire, CONTEXTE_frFR_Rechercher + contexteNomPluriel));
							contexteAucunNomTrouve = indexerStockerSolr(langueNom, classeDoc, "contexteAucunNomTrouve", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_AucunNomTrouve) + "", classeCommentaire, CONTEXTE_frFR_AucunTrouveMasculinAvant + contexteNomSingulier + CONTEXTE_frFR_AucunTrouveMasculinApres));
							if(contexteAdjectif != null) {
								if(CONTEXTE_frFR_AdjectifAvant)
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_UnNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_UnMasculin + contexteAdjectif + " " + contexteNomSingulier));
								else
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_UnNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_UnMasculin + contexteNomSingulier + " " + contexteAdjectif));
							}
		
							String suffixe = StringUtils.substringAfter(contexteUnNom, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_CeNom) + "", classeCommentaire, CONTEXTE_frFR_CetMasculinVoyelle + suffixe));
								contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNom) + "", classeCommentaire, CONTEXTE_frFR_LMasculinVoyelle + suffixe));
								contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_DeNom) + "", classeCommentaire, CONTEXTE_frFR_DVoyelle + suffixe));
								if(contexteAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant) {
										contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_LMasculinVoyelle + contexteAdjectif + " " + contexteNomSingulier));
									}
									else {
										contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_LMasculinVoyelle + contexteNomSingulier + " " + contexteAdjectif));
									}
								}
							}
							else {
								contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_CeNom) + "", classeCommentaire, CONTEXTE_frFR_CeMasculinConsonne + suffixe));
								contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNom) + "", classeCommentaire, CONTEXTE_frFR_LeMasculinConsonne + suffixe));
								contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_DeNom) + "", classeCommentaire, CONTEXTE_frFR_DeConsonne + suffixe));
								if(contexteAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant) {
										contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_LeMasculinConsonne + contexteAdjectif + " " + contexteNomSingulier));
									}
									else {
										contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_frFR_LeMasculinConsonne + contexteNomSingulier + " " + contexteAdjectif));
									}
								}
							}
						}
						indexerStockerSolr(langueNom, classeDoc, "contexteCeMinuscule", contexteCe); 
					}
					else if("enUS".equals(langueNom)) {
						indexerStockerSolr(langueNom, classeDoc, "contexteUnNom", contexteUnNom); 
						contexteNomSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomSingulier", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomSingulier) + "", classeCommentaire, StringUtils.substringAfter(contexteUnNom, " ")));
						contexteNomPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomPluriel) + "", classeCommentaire, contexteNomSingulier + "s"));
						contexteNomVar = indexerStockerSolr(langueNom, classeDoc, "contexteNomVar", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomVar) + "", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteNomSingulier, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
						contexteLesNoms = indexerStockerSolr(langueNom, classeDoc, "contexteLesNoms", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LesNoms) + "", classeCommentaire, CONTEXTE_enUS_LesPluriel + contexteNomPluriel));
		
						contexteAdjectif = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Adjectif) + "", classeCommentaire);
						if(contexteAdjectif != null) {
							contexteAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteAdjectifPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_AdjectifPluriel) + "", classeCommentaire, contexteAdjectif + CONTEXTE_enUS_AdjectifPluriel));
							contexteAdjectifVar = indexerStockerSolr(langueNom, classeDoc, "contexteAdjectifVar", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_AdjectifVar) + "", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteAdjectif, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
							if(CONTEXTE_enUS_AdjectifAvant) {
								contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifSingulier) + "", classeCommentaire, contexteAdjectif + " " + contexteNomSingulier));
								contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifPluriel) + "", classeCommentaire, contexteAdjectifPluriel + " " + contexteNomPluriel));
							}
							else {
								contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifSingulier) + "", classeCommentaire, contexteNomSingulier + " " + contexteAdjectif));
								contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomSingulierAdjectifPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomSingulierAdjectifPluriel) + "", classeCommentaire, contexteNomPluriel + " " + contexteAdjectifPluriel));
							}
						}
						else {
							contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifSingulier) + "", classeCommentaire, contexteNomSingulier));
							contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifPluriel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomAdjectifPluriel) + "", classeCommentaire, contexteNomPluriel));
						}
		
						contexteCe = indexerStockerSolr(langueNom, classeDoc, "contexteCe", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Ce) + "", classeCommentaire, CONTEXTE_enUS_CetteConsonne));
						contexteUn = indexerStockerSolr(langueNom, classeDoc, "contexteUn", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Un) + "", classeCommentaire, CONTEXTE_enUS_UnConsonne));
						contexteCree = indexerStockerSolr(langueNom, classeDoc, "contexteCree", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Cree) + "", classeCommentaire, CONTEXTE_enUS_Creee));
						contexteModifie = indexerStockerSolr(langueNom, classeDoc, "contexteModifie", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Modifie) + "", classeCommentaire, CONTEXTE_enUS_Modifiee));
						contexteNomActuel = indexerStockerSolr(langueNom, classeDoc, "contexteNomActuel", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_NomActuel) + "", classeCommentaire, CONTEXTE_enUS_ActuelleAvant + contexteNomSingulier + CONTEXTE_enUS_ActuelleApres));
						contexteTous = indexerStockerSolr(langueNom, classeDoc, "contexteTous", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_TousNom) + "", classeCommentaire, CONTEXTE_enUS_ToutesPluriel));
						contexteTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteTousNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_TousNom) + "", classeCommentaire, CONTEXTE_enUS_ToutesPluriel + CONTEXTE_enUS_LesPluriel + contexteNomPluriel));
						contexteRechercherTousNomPar = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNomPar", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_RechercherTousNomPar) + "", classeCommentaire, CONTEXTE_enUS_Rechercher + contexteNomPluriel + CONTEXTE_enUS_Par));
						contexteRechercherTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNomr", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_RechercherTousNom) + "", classeCommentaire, CONTEXTE_enUS_Rechercher + contexteNomPluriel));
						contexteAucunNomTrouve = indexerStockerSolr(langueNom, classeDoc, "contexteAucunNomTrouve", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_AucunNomTrouve) + "", classeCommentaire, CONTEXTE_enUS_AucuneTrouveAvant + contexteNomSingulier + CONTEXTE_enUS_AucuneTrouveApres));
	
						String suffixe = StringUtils.substringAfter(contexteUnNom, " ");
						String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);

						if(contexteAdjectif != null) {
							if(StringUtils.containsAny(contexteAdjectif.substring(0, 1), 'a', 'e', 'i', 'o', 'u', 'h'))
								contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_UnNomAdjectif) + "", classeCommentaire, CONTEXTE_enUS_UnVoyelle + contexteAdjectif + " " + contexteNomSingulier));
							else
								contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_UnNomAdjectif) + "", classeCommentaire, CONTEXTE_enUS_UnConsonne + contexteAdjectif + " " + contexteNomSingulier));
						}
						else {
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h'))
								contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_UnNomAdjectif) + "", classeCommentaire, CONTEXTE_enUS_UnVoyelle + contexteNomSingulier));
							else
								contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_UnNomAdjectif) + "", classeCommentaire, CONTEXTE_enUS_UnConsonne + contexteNomSingulier));
						}

						if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h')) {
							contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_CeNom) + "", classeCommentaire, CONTEXTE_enUS_CetteVoyelle + suffixe));
							contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNom) + "", classeCommentaire, CONTEXTE_enUS_LVoyelle + suffixe));
							contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_DeNom) + "", classeCommentaire, CONTEXTE_enUS_DVoyelle + suffixe));
							if(contexteAdjectif != null) {
								if(CONTEXTE_enUS_AdjectifAvant) {
									contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_enUS_LVoyelle + contexteAdjectif + " " + contexteNomSingulier));
								}
								else {
									contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_enUS_LVoyelle + contexteNomSingulier + " " + contexteAdjectif));
								}
							}
						}
						else {
							contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_CeNom) + "", classeCommentaire, CONTEXTE_enUS_CetteConsonne + suffixe));
							contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNom) + "", classeCommentaire, CONTEXTE_enUS_LaConsonne + suffixe));
							contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_DeNom) + "", classeCommentaire, CONTEXTE_enUS_DeConsonne + suffixe));
							if(contexteAdjectif != null) {
								if(CONTEXTE_enUS_AdjectifAvant) {
									contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_enUS_LaConsonne + contexteAdjectif + " " + contexteNomSingulier));
								}
								else {
									contexteLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_LeNomAdjectif) + "", classeCommentaire, CONTEXTE_enUS_LaConsonne + contexteNomSingulier + " " + contexteAdjectif));
								}
							}
						}
						indexerStockerSolr(langueNom, classeDoc, "contexteCeMinuscule", contexteCe); 
					}
				}
	
				contexteTitre = regexLangue(langueNom, "^" + classeLangueConfig.getString(ConfigCles.var_Titre) + "", classeCommentaire, contexteNomPluriel);
				if(contexteTitre != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteTitre", contexteTitre); 
	
				contexteH1 = regexLangue(langueNom, "^H1", classeCommentaire);
				if(contexteH1 != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteH1", contexteH1); 
	
				contexteH2 = regexLangue(langueNom, "^H2", classeCommentaire);
				if(contexteH2 != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteH2", contexteH2); 
	
				contexteH3 = regexLangue(langueNom, "^H3", classeCommentaire);
				if(contexteH3 != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteH3", contexteH3); 
			}
		}

		Boolean classeIndexe = indexerStockerSolr(classeDoc, "classeIndexe", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Indexe) + ":\\s*(true)$", classeCommentaire) || classeSauvegarde || classeModele || classePage);
		Boolean classeImage = indexerStockerSolr(classeDoc, "classeImage", regexTrouve("^" + classeLangueConfig.getString(ConfigCles.var_Image) + ":\\s*(true)$", classeCommentaire));

		stockerSolr(classeDoc, "classePromesse", classePromesse);
		if(activerVertx || activerQuarkus) {
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Promise", classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Future", classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonArray", classeLangueNom), classeLangueNom);
		}

		if(classeIndexe) {
//			classePartsGenAjouter(classePartsSolrInputDocument, classeLangueNom);
//			classePartsGenAjouter(classePartsSolrClient, classeLangueNom);
//			classePartsGenAjouter(classePartsSolrDocument, classeLangueNom);
			classePartsGenAjouter(classePartsList, classeLangueNom);
			classePartsGenAjouter(classePartsArrayList, classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.computate.search.response.solr.SolrResponse", classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueVertxJsonObject, classeLangueNom), classeLangueNom);
		}

		if(classeImage) {
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, DefaultExecutor.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, CommandLine.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, File.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, BufferedImage.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, ImageIO.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		}

		for(ClasseParts classePartGen : classePartsGen.values()) {
			indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImportationsGen", classePartGen.nomCanonique(classeLangueNom));
			for(String langueNom : classeAutresLangues) {  
//				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGen, langueNom);
//				indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGen", classeImportationClassePartsLangue.nomCanonique);
				indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGen", classePartGen.nomCanonique(langueNom));
			}
		}

		if(classeSuperDoc != null) {
			for(String langueNom : toutesLangues) {
				List<String> classeSuperEntiteVars = (List<String>)classeSuperDoc.get("classeEntiteVars_" + langueNom + "_stored_strings");
				if(classeSuperEntiteVars != null) {
					for(String classeSuperEntiteVar : classeSuperEntiteVars)
						indexerStockerListeSolr(langueNom, classeDoc, "classeEntiteVars", classeSuperEntiteVar);
				}
			}
		}

		if(classeSuperDoc != null) {
			for(String langueNom : toutesLangues) {
				List<String> classeSuperMethodeVars = (List<String>)classeSuperDoc.get("classeMethodeVars_" + langueNom + "_stored_strings");
				if(classeSuperMethodeVars != null) {
					for(String classeSuperMethodeVar : classeSuperMethodeVars)
						indexerStockerListeSolr(langueNom, classeDoc, "classeMethodeVars", classeSuperMethodeVar);
				}
			}
		}

		indexerStockerSolr(classeDoc, "classeTrisTrouves", classeTrisTrouves); 
		for(Integer i = 0; i < classeTrisOrdre.size(); i++) {
			String classeTriOrdre = classeTrisOrdre.get(i);
			String classeTriVar = classeTrisVar.get(i);
			String classeTriSuffixeType = Optional.ofNullable(clientSolrComputate.getById(classeCheminAbsolu + "." + classeTriVar)).map(d -> (String)d.get("entiteSuffixeType_stored_string")).orElse(null);
			if(classeTriSuffixeType == null)
				classeTriSuffixeType = Optional.ofNullable(clientSolrComputate.getById(classeSuperDoc.get("classeCheminAbsolu_stored_string") + "." + classeTriVar)).map(d -> (String)d.get("entiteSuffixeType_stored_string")).orElse(null);
			indexerStockerListeSolr(classeDoc, "classeTrisSuffixeType", classeTriSuffixeType); 
			indexerStockerListeSolr(classeDoc, "classeTrisOrdre", classeTriOrdre); 
			indexerStockerListeSolr(classeLangueNom, classeDoc, "classeTrisVar", classeTriVar); 
			List<String> classeEntiteVars = (List<String>)classeDoc.get("classeEntiteVars_" + classeLangueNom + "_stored_strings").getValue();
			for(String langueNom : classeAutresLangues) {  
				List<String> classeEntiteVarsLangue = (List<String>)classeDoc.get("classeEntiteVars_" + langueNom + "_stored_strings").getValue();
				int j = classeEntiteVars.indexOf(classeTriVar);
				if(j >= 0) {
					String classeTriVarLangue = classeEntiteVarsLangue.get(j);
					indexerStockerListeSolr(langueNom, classeDoc, "classeTrisVar", classeTriVarLangue); 
				}
			}
		} 

		indexerStockerSolr(classeDoc, "classePage", classePage);

		clientSolrComputate.add(classeDoc);
		clientSolrComputate.commit(false, false, true);
		String qSupprimer = concat("classeCheminAbsolu_indexed_string", ":\"", classeChemin, "\" AND (modifiee_indexed_date:[* TO ", modifiee.minusMillis(1).toString(), "] OR (*:* AND -modifiee_indexed_date:[* TO *]))");
		UpdateResponse d = clientSolrComputate.deleteByQuery(qSupprimer);
		clientSolrComputate.commit(false, false, true); 
		return classeDoc;
	}
}
