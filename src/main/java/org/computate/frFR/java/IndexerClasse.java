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
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.Duration;
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
import org.computate.i18n.I18n;
import org.computate.vertx.api.ApiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

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

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

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
	public static final String VAL_nomCanoniquePath = "io.vertx.pgclient.data.Path";
	public static final String VAL_nomCanoniquePolygon = "io.vertx.pgclient.data.Polygon";
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

	ClasseParts classePartsJsonObjectSerializer;
	ClasseParts classePartsJsonObjectDeserializer;

	ClasseParts classePartsJsonArraySerializer;
	ClasseParts classePartsJsonArrayDeserializer;

	ClasseParts classePartsPointSerializer;
	ClasseParts classePartsPointDeserializer;

	ClasseParts classePartsPathSerializer;
	ClasseParts classePartsPathDeserializer;

	ClasseParts classePartsPolygonSerializer;
	ClasseParts classePartsPolygonDeserializer;

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
	ClasseParts classePartsModeleBase;
	ClasseParts classePartsResultatBase;
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
	private String classeVideoId;

	/**
	 * Var.enUS: contextUri
	 */
	private String classeUri;

	/**
	 * Var.enUS: contextDescription
	 */
	private String classeDescription;

	/**
	 * Var.enUS: contextAName
	 */
	private String classeUnNom;

	/**
	 * Var.enUS: contextThis
	 */
	private String classeCe;

	/**
	 * Var.enUS: contextThisName
	 */
	private String classeCeNom;

	/**
	 * Var.enUS: contextA
	 */
	private String classeUn;

	/**
	 * Var.enUS: contextCreated
	 */
	private String classeCree;

	/**
	 * Var.enUS: contextModified
	 */
	private String classeModifie;

	/**
	 * Var.enUS: contextTheName
	 */
	private String classeLeNom;

	/**
	 * Var.enUS: contextNameSingular
	 */
	private String classeNomSingulier;

	/**
	 * Var.enUS: contextNamePlural
	 */
	private String classeNomPluriel;

	/**
	 * Var.enUS: contextActualName
	 */
	private String classeNomActuel;

	/**
	 * Var.enUS: contextAll
	 */
	private String classeTous;

	/**
	 * Var.enUS: contextAllName
	 */
	private String classeTousNom;

	/**
	 * Var.enUS: contextSearchAllNameBy
	 */
	private String classeRechercherTousNomPar;

	/**
	 * Var.enUS: contextSearchAllName
	 */
	private String classeRechercherTousNom;

	/**
	 * Var.enUS: contextTheNames
	 */
	private String classeLesNoms;

	/**
	 * Var.enUS: contextNoNameFound
	 */
	private String classeAucunNomTrouve;

	/**
	 * Var.enUS: contextOfName
	 */
	private String classeDeNom;

	/**
	 * Var.enUS: contextAdjective
	 */
	private String classeAdjectif;

	/**
	 * Var.enUS: contextAdjectivePlural
	 */
	private String classeAdjectifPluriel;

	/**
	 * Var.enUS: contextAdjectiveVar
	 */
	private String classeAdjectifVar;

	/**
	 * Var.enUS: contextANameAdjective
	 */
	private String classeUnNomAdjectif;

	/**
	 * Var.enUS: contextTheNameAdjective
	 */
	private String classeLeNomAdjectif;

	/**
	 * Var.enUS: contextNameAdjectiveSingular
	 */
	private String classeNomAdjectifSingulier;

	/**
	 * Var.enUS: contextNameAdjectivePlural
	 */
	private String classeNomAdjectifPluriel;

	/**
	 * Var.enUS: contextTitle
	 */
	private String classeTitre;

	/**
	 * Var.enUS: contextH1
	 */
	private String classeH1;

	/**
	 * Var.enUS: contextH2
	 */
	private String classeH2;

	/**
	 * Var.enUS: contextH3
	 */
	private String classeH3;

	/**
	 * Var.enUS: contextColor
	 */
	private String classeCouleur;

	private String classeIcone;

	private Integer classeLignes;
	private Integer classeOrdre;
	private Integer classeOrdreSql;

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
	 * @param classeSuperDoc 
	 */       
	public void peuplerClassesSuperQdoxInterfacesEtMoi(
			JavaClass c
			, ArrayList<JavaClass> classesSuperQdox
			, ArrayList<JavaClass> classesSuperQdoxEtMoi
			, ArrayList<JavaClass> classesSuperQdoxEtMoiSansGen
			, ArrayList<JavaClass> classesSuperQdoxEtInterfaces
			, ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi
			, SolrDocument classeSuperDoc
			) throws Exception { 
		if(c != null) {
			JavaClass classeSuper = c.getSuperJavaClass();
			List<JavaClass> interfacesImplementees = c.getInterfaces();

			for(JavaClass interfaceQdox : interfacesImplementees) {
				if(interfaceQdox != null && !interfaceQdox.getCanonicalName().equals("java.lang.Object") && !c.equals(interfaceQdox)) {
					classesSuperQdoxInterfacesEtMoi.add(interfaceQdox);
					classesSuperQdoxEtInterfaces.add(classeSuper);
					peuplerClassesSuperQdoxInterfacesEtMoi(interfaceQdox, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtMoiSansGen, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi, classeSuperDoc); // Doesn't seem to work for interfaces that extend other interfaces.
				}
			}
			classesSuperQdoxInterfacesEtMoi.add(c);
			classesSuperQdoxEtMoi.add(c);
			if(!StringUtils.endsWith(c.getCanonicalName(), "Gen"))
				classesSuperQdoxEtMoiSansGen.add(c);
			try {
				if(classeSuper != null && !classeSuper.getCanonicalName().equals("java.lang.Object") && !c.equals(classeSuper)) {
					if(classeSuperDoc == null) {
						classesSuperQdoxEtInterfaces.add(classeSuper);
						classesSuperQdox.add(classeSuper);
						peuplerClassesSuperQdoxInterfacesEtMoi(classeSuper, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtMoiSansGen, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi, classeSuperDoc);
					} else {
						List<String> classesSuperEtMoiSansGen = Optional.ofNullable(classeSuperDoc.getFieldValues("classesSuperEtMoiSansGen_stored_strings")).orElse(Arrays.asList()).stream().map(v -> (String)v).collect(Collectors.toList());
						for(String classeSuperNomCanonique: classesSuperEtMoiSansGen) {
							JavaClass classeSuperActuel = bricoleur.getClassByName(classeSuperNomCanonique);
							classesSuperQdoxEtMoiSansGen.add(classeSuperActuel);
							classesSuperQdoxEtInterfaces.add(classeSuperActuel);
							classesSuperQdox.add(classeSuperActuel);
						}
					}
				}
			} catch (Throwable ex) {
				LOG.error(String.format(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), classeSuper.getCanonicalName()), ex);
				ExceptionUtils.rethrow(ex);
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
			Matcher m = Pattern.compile("^" + nomChampRegex + "(." + langueNom + ")?: (.*)", Pattern.MULTILINE).matcher(commentaire);
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
			Matcher m = Pattern.compile("^" + nomChampRegex + ": (.*)", Pattern.MULTILINE).matcher(commentaire);
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
		rechercheSolr.addFilterQuery("classeMotsCles_indexed_strings:" + ClientUtils.escapeQueryChars(langueConfigGlobale.getString(I18n.var_classeNomSimple) + nomSimple));
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
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_Couverture), langueNom);
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
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_RequeteSite), langueNom);
	}

	protected ClasseParts classePartsUtilisateurSite(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_UtilisateurSite), langueNom);
	}

	protected ClasseParts classePartsOutilRecherche(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_OutilRecherche), langueNom);
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
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_RequeteApi), langueNom);
		if(parts == null) {
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.api.ApiRequest", langueNom);
			parts.setEtendGen(true);
		}
		return parts;
	}

	protected ClasseParts classePartsModeleBase(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_ModeleBase), langueNom);
	}

	protected ClasseParts classePartsResultatBase(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_ResultatBase), langueNom);
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
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_ResultatRecherche), langueNom);
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
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_ToutEcrivain), langueNom);
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
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_ListeRecherche), langueNom);
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
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_MiseEnPage), langueNom);
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
		return classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_PagePart), langueNom);
	}

	protected ClasseParts classePartsVerticle(String nomEnsembleDomaine, String langueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "Verticle", langueNom);
	}

	protected ClasseParts classePartsBaseApiServiceImpl(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_BaseApiServiceImpl), langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.api.BaseApiServiceImpl", langueNom);
		return parts;
	}

	protected ClasseParts classePartsMailVerticle(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_MailVerticle), langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.verticle.EmailVerticle", langueNom);
		return parts;
	}

	protected ClasseParts classePartsConfigCles(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, langueConfigGlobale.getString(I18n.var_ConfigCles), langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.config.ComputateConfigKeys", langueNom);
		return parts;
	}

	protected ClasseParts classePartsJsonObjectDeserializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "JsonObjectDeserializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.serialize.vertx.JsonObjectDeserializer", langueNom);
		return parts;
	}

	protected ClasseParts classePartsJsonArrayDeserializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "JsonArrayDeserializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.serialize.vertx.JsonArrayDeserializer", langueNom);
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

	protected ClasseParts classePartsPathDeserializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "PathDeserializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.serialize.pgclient.PgClientPathDeserializer", langueNom);
		return parts;
	}
	protected ClasseParts classePartsPathSerializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "PathSerializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.serialize.pgclient.PgClientPathSerializer", langueNom);
		return parts;
	}

	protected ClasseParts classePartsPolygonDeserializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "PolygonDeserializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.serialize.pgclient.PgClientPolygonDeserializer", langueNom);
		return parts;
	}
	protected ClasseParts classePartsPolygonSerializer(String nomEnsembleDomaine, String langueNom) throws Exception {
		ClasseParts parts = classePartsPourNomSimple(nomEnsembleDomaine, "PolygonSerializer", langueNom);
		if(parts == null)
			parts = ClasseParts.initClasseParts(this, "org.computate.vertx.serialize.pgclient.PgClientPolygonSerializer", langueNom);
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
		indexerStockerSolr(classeDoc, "siteNom", siteNom);
		JavaClass classeQdox = null;
		JavaClass classeQdoxSuper = null;
		try {
			classeQdox = bricoleur.getClassByName(classeNomCanonique.toString());
			classeQdoxSuper = classeQdox.getSuperJavaClass();
		} catch(Throwable ex) {
			LOG.error(String.format(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), classeNomCanonique), ex);
			ExceptionUtils.rethrow(ex);
		}
		JavaClass classeQdoxString = bricoleur.getClassByName(String.class.getCanonicalName());

		Boolean classeMotsClesTrouves = false;
		List<String> classeMotsCles = new ArrayList<String>();

		Boolean classeTrisTrouves = false;
		List<String> classeTrisOrdre = new ArrayList<String>();
		List<String> classeTrisVar = new ArrayList<String>();

		List<String> classeInitLoinExceptions = new ArrayList<String>(); 
		String classeVarSuggere = null;
		String classeVarTexte = null;
		String classeVarUrlPageAffichage = null;
		String classeVarUrlPageEdition = null;
		String classeVarUrlPageUtilisateur = null;
		String classeVarUrlTelechargement = null;
		String classeVarUrlApi = null;
		String classeVarId = null;
		String classeVarIdSuffixeSolr = null;
		String classeVarNomSuffixeSolr = null;
		String classeVarNom = null;
		String classeVarTitre = null;
		String classeHtmInfobulle = null;
		String classeJsInfobulle = null;
		String classeJsPATCH = null;
		String classeJsWebsocket = null;
		String classeVarH1 = null;
		String classeVarH2 = null;
		String classeVarH3 = null;
		String classeVarDescription = null;
		String classeVarImageUrl = null;
		String classeVarModifie = null;
		String classeVarCree = null;
		String classeVarClePrimaire = null;
		String classeVarClePrimaireSuffixeSolr = null;
		String classeVarInheritClePrimaire = null;
		String classeVarInheritClePrimaireSolr = null;
		String classeVarSauvegardes = null;
		String classeVarCleUnique = null;
		String classeVarZone = null;
		String classeVarEmplacement = null;
		String classeVarEmplacementCouleur = null;
		String classeVarEmplacementTitre = null;
		String classeVarEmplacementUrl = null;
		String classeEmplacementSvg = null;
		String classeEmplacementRayon = null;
		String classeVarEmplacementSvg = null;
		String classeVarEmplacementRayon = null;
		String classeVarAire = null;
		String classeVarAireCouleur = null;
		String classeVarAireTitre = null;
		String classeVarAireUrl = null;

		String classeNomCanoniqueSuper = Object.class.getCanonicalName();
		Boolean classeSuperErreur = false;
		try {
			classeNomCanoniqueSuper = classeQdoxSuper.getCanonicalName();
		} catch (Throwable ex) {
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
		} catch (Throwable ex) {
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
		
		String classeCommentaire = stockerSolr(classeDoc, "classeCommentaire", classeQdox.getComment());
		stockerRegexCommentaires(classeLangueNom, classeDoc, "classeCommentaire", classeCommentaire);
		String classeNomEnsemble = StringUtils.substringBeforeLast(classeNomCanonique, ".");
		String classeChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), ".java");
		String classeCheminRepertoire = StringUtils.substringBeforeLast(classeChemin, "/");
		String classeCheminGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Gen.java");

		Boolean classePromesse = regexTrouve("^(classe)?" + i18nGlobale.getString(I18n.var_Promesse) + ": (true)$", classeCommentaire);
		indexerStockerSolr(classeDoc, "classeBaseEtendGen", classeBaseEtendGen);
		Boolean classeContientRequeteSite = false;
		try {
			classeContientRequeteSite = classeQdox.getMethodBySignature("get" + i18nGlobale.getString(I18n.var_RequeteSite) +"_", new ArrayList<JavaType>(), true) != null
					|| classePartsBase != null && BooleanUtils.isTrue((Boolean)classePartsBase.getDocumentSolr().get("classeContientRequeteSite_stored_boolean"));
		} catch (Throwable ex) {
			// TODO ctate fix this to pull from solr. 
		}
		indexerStockerSolr(classeDoc, "classeContientRequeteSite", classeContientRequeteSite);

		String classeCheminRepertoireGen = StringUtils.substringBeforeLast(classeCheminGen, "/");
		String classeCle = String.format("%s %s", solrCollectionComputate, classeCheminAbsolu);
		Instant modifiee = Instant.now();
		Date modifieeDate = Date.from(modifiee);
		Boolean classeContientCouverture = false;

		Boolean classeTraduire = indexerStockerSolr(classeDoc, "classeTraduire", !regexTrouve("^" + i18nGlobale.getString(I18n.var_Traduire) + ": \\s*(false)$", classeCommentaire));
		Boolean classeEtendGen = StringUtils.endsWith(classeNomSimpleSuper, "Gen");
		if(classeSuperErreur || !classeEtendGen && regexTrouve("^(classe)?Gen: (true)$", classeCommentaire)) {
			classeEtendGen = true;
		}
		if(regexTrouve("^(classe)?Gen: (false)$", classeCommentaire) || classeQdox.isInterface())
			classeEtendGen = false;

//		if(classeTraduire) {
			for(String langueNom : classeAutresLangues) {
				String siteCheminLangue = siteChemins.get(langueNom);
				stockerRegexCommentaires(langueNom, classeDoc, "classeCommentaire", classeCommentaire);
				String cheminSrcMainJavaLangue = siteCheminLangue + "/src/main/java";
				String cheminSrcGenJavaLangue = siteCheminLangue + "/src/gen/java";
				String classeNomCanoniqueLangue = regex("^" + i18nGlobale.getString(I18n.var_NomCanonique) + "\\." + langueNom + ": (.*)", classeCommentaire);
	
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
		classePartsResultatBase = classePartsResultatBase(nomEnsembleDomaine, classeLangueNom);
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
		classePartsJsonObjectDeserializer = classePartsJsonObjectDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsJsonArrayDeserializer = classePartsJsonArrayDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsPointDeserializer = classePartsPointDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsPointSerializer = classePartsPointSerializer(nomEnsembleDomaine, classeLangueNom);
		classePartsPathDeserializer = classePartsPathDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsPathSerializer = classePartsPathSerializer(nomEnsembleDomaine, classeLangueNom);
		classePartsPolygonDeserializer = classePartsPolygonDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsPolygonSerializer = classePartsPolygonSerializer(nomEnsembleDomaine, classeLangueNom);
		classePartsLocalTimeDeserializer = classePartsLocalTimeDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsLocalTimeSerializer = classePartsLocalTimeSerializer(nomEnsembleDomaine, classeLangueNom);
		classePartsLocalDateDeserializer = classePartsLocalDateDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsLocalDateSerializer = classePartsLocalDateSerializer(nomEnsembleDomaine, classeLangueNom);
		classePartsZonedDateTimeDeserializer = classePartsZonedDateTimeDeserializer(nomEnsembleDomaine, classeLangueNom);
		classePartsZonedDateTimeSerializer = classePartsZonedDateTimeSerializer(nomEnsembleDomaine, classeLangueNom);

		Boolean classeInitLoin = !regexTrouve("^" + i18nGlobale.getString(I18n.var_InitLoin) + ": (false)$", classeCommentaire);
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

		SolrDocument classeSuperDoc = null;   
		if(classeEtendGen && StringUtils.isNotBlank(classeNomCompletSuperGenerique)) {
			ClasseParts classePartsSuperGenerique = ClasseParts.initClasseParts(this, classeNomCompletSuperGenerique, classeLangueNom);
			classePartsGenAjouter(classePartsSuperGenerique, classeLangueNom);

			if(StringUtils.startsWith(classeNomCanoniqueSuper, nomEnsembleDomaine) || StringUtils.startsWith(classeNomCanoniqueSuper, ClasseParts.SITE_JAVA_ENSEMBLE_COMPUTATE)) {
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

		ArrayList<JavaClass> classesSuperQdox = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtMoi = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtMoiSansGen = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi = new ArrayList<JavaClass>();
		peuplerClassesSuperQdoxInterfacesEtMoi(classeQdox, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtMoiSansGen, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi, classeSuperDoc);

		for(JavaClass c : classesSuperQdoxEtMoiSansGen) {
			indexerStockerListeSolr(classeDoc, "classesSuperEtMoiSansGen", c.getCanonicalName()); 

		}
		String fqClassesSuperEtMoi = "(" + classesSuperQdoxEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c.getCanonicalName())).collect(Collectors.joining(" OR ")) + ")";

		SolrInputDocument classeDocClone = classeDoc.deepCopy();
		Integer partNumero = 1;

		indexerStockerSolr(classeDoc, "classeEstAbstrait", classeQdox.isAbstract()); 
		Boolean classeModele = indexerStockerSolr(classeDoc, "classeModele", 
				regexTrouve("^" + i18nGlobale.getString(I18n.var_Modele) + ": \\s*(true)$", classeCommentaire) 
				|| classeSuperDoc != null && (BooleanUtils.isTrue((Boolean)classeSuperDoc.get("classeModele_stored_boolean"))));
		Boolean classeFiware = indexerStockerSolr(classeDoc, "classeFiware", regexTrouve("^Fiware: \\s*(true)$", classeCommentaire));
		String classeModeleAlternatif = indexerStockerSolr(classeDoc, "classeModeleAlternatif", regexLangue(langueNomGlobale, "^" + i18nGlobale.getString(I18n.var_ModeleAlternatif), classeCommentaire, classeNomSimple));
		String classeUriPageRecherche = indexerStockerSolr(langueNomGlobale, classeDoc, "classeUriPageRecherche", regex("^" + i18nGlobale.getString(I18n.var_UriPageRecherche) + ": (.*)", classeCommentaire));
		String classeUriPageAffichage = indexerStockerSolr(langueNomGlobale, classeDoc, "classeUriPageAffichage", regex("^" + i18nGlobale.getString(I18n.var_UriPageAffichage) + ": (.*)", classeCommentaire));
		String classeUriPageUtilisateur = indexerStockerSolr(langueNomGlobale, classeDoc, "classeUriPageUtilisateur", regex("^" + i18nGlobale.getString(I18n.var_UriPageUtilisateur) + ": (.*)", classeCommentaire));
		String classeUriTelechargement = indexerStockerSolr(langueNomGlobale, classeDoc, "classeUriTelechargement", regex("^" + i18nGlobale.getString(I18n.var_UriTelechargement) + ": (.*)", classeCommentaire));
		String classeUriPageEdition = indexerStockerSolr(langueNomGlobale, classeDoc, "classeUriPageEdition", regex("^" + i18nGlobale.getString(I18n.var_UriPageEdition) + ": (.*)", classeCommentaire));
		Boolean classeApi = indexerStockerSolr(classeDoc, "classeApi", regexTrouve("^(classe)?Api: \\s*(true)$", classeCommentaire)
				|| classeUriPageRecherche != null
				|| classeUriPageEdition != null
				|| classeUriPageAffichage != null
				|| classeUriPageUtilisateur != null
				|| classeUriTelechargement != null
				);
		Boolean classePage = regexTrouve("^" + i18nGlobale.getString(I18n.var_Page) + ": \\s*(true)$", classeCommentaire)
				|| classeUriPageRecherche != null
				|| classeUriPageEdition != null
				|| classeUriPageAffichage != null
				|| classeUriPageUtilisateur != null
				;
		Boolean classePageAvecTemplate = false;
		Boolean classePageSimple = indexerStockerSolr(classeDoc, "classePageSimple", regexTrouve("^" + i18nGlobale.getString(I18n.var_PageSimple) + ": \\s*(true)$", classeCommentaire));
		Boolean classeSauvegarde = indexerStockerSolr(classeDoc, "classeSauvegarde", regexTrouve("^" + i18nGlobale.getString(I18n.var_Sauvegarde) + ": (true)$", classeCommentaire) || classeModele || classeFiware);
		indexerStockerSolr(langueNomGlobale, classeDoc, "classePageTemplate", regexLangue(langueNomGlobale, "^" + i18nGlobale.getString(I18n.var_PageTemplate), classeCommentaire));
		String classeApiClasseNomSimple = regexLangue(langueNomGlobale, "^" + i18nGlobale.getString(I18n.var_ApiClasse), classeCommentaire, classeNomSimple);

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

		String classeTypeContenu = regex("^" + i18nGlobale.getString(I18n.var_TypeMedia) + ": (.*)", classeCommentaire);
		if(StringUtils.isNotBlank(classeTypeContenu))
			indexerStockerSolr(classeDoc, "classeTypeContenu", classeTypeContenu);
		else 
			classeTypeContenu = null;

		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomAffichage", regexLangue(classeLangueNom, "^" + i18nGlobale.getString(I18n.var_NomAffichage), classeCommentaire));

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

			classeNomSimpleApiEnsembleInfo = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleApiEnsembleInfo", "package-info");
			classeNomSimpleGenApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleGenApiServiceImpl", classeNomSimple + StringUtils.capitalize(classeLangueNom) + "GenApiServiceImpl");
			classeNomSimpleApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleApiServiceImpl", classeNomSimple + StringUtils.capitalize(classeLangueNom) + "ApiServiceImpl");
			classeNomSimpleGenApiService = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleGenApiService", classeNomSimple + StringUtils.capitalize(classeLangueNom) + "GenApiService");

			classeNomCanoniqueApiEnsembleInfo = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueApiEnsembleInfo", classeNomEnsemble + "." + classeNomSimpleApiEnsembleInfo);
			classeNomCanoniqueGenApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueGenApiServiceImpl", classeNomEnsemble + "." + classeNomSimpleGenApiServiceImpl);
			classeNomCanoniqueApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueApiServiceImpl", classeNomEnsemble + "." + classeNomSimpleApiServiceImpl);
			classeNomCanoniqueGenApiService = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueGenApiService", classeNomEnsemble + "." + classeNomSimpleGenApiService);

			classeCheminApiEnsembleInfo = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueApiEnsembleInfo, ".", "/"), ".java");
			classeCheminGenApiServiceImpl = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceImpl, ".", "/"), ".java");
			classeCheminApiServiceImpl = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueApiServiceImpl, ".", "/"), ".java");
			classeCheminGenApiService = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueGenApiService, ".", "/"), ".java");

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

				indexerStockerSolr(langueNom, classeDoc, "classeNomAffichage", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAffichage), classeCommentaire));
	
				if(classeApi) {

					String classeNomSimpleApiEnsembleInfoLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleApiEnsembleInfo", "package-info");
					String classeNomSimpleGenApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGenApiServiceImpl", classeNomSimpleLangue + StringUtils.capitalize(langueNom) + "GenApiServiceImpl");
					String classeNomSimpleApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleApiServiceImpl", classeNomSimpleLangue + StringUtils.capitalize(langueNom) + "ApiServiceImpl");
					String classeNomSimpleGenApiServiceLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGenApiService", classeNomSimpleLangue + StringUtils.capitalize(langueNom) + "GenApiService");
		
					String classeNomCanoniqueApiEnsembleInfoLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueApiEnsembleInfo", classeNomEnsembleLangue + "." + classeNomSimpleApiEnsembleInfoLangue);
					String classeNomCanoniqueGenApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueGenApiServiceImpl", classeNomEnsembleLangue + "." + classeNomSimpleGenApiServiceImplLangue);
					String classeNomCanoniqueApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueApiServiceImpl", classeNomEnsembleLangue + "." + classeNomSimpleApiServiceImplLangue);
					String classeNomCanoniqueGenApiServiceLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueGenApiService", classeNomEnsembleLangue + "." + classeNomSimpleGenApiServiceLangue);
		
					// String classeCheminApiEnsembleInfoLangue = concat(cheminSrcGenJavaVertxLangue, "/", StringUtils.replace(classeNomCanoniqueApiEnsembleInfoLangue, ".", "/"), ".java");
					// String classeCheminGenApiServiceImplLangue = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceImplLangue, ".", "/"), ".java");
					// String classeCheminApiServiceImplLangue = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomCanoniqueApiServiceImplLangue, ".", "/"), ".java");
					// String classeCheminGenApiServiceLangue = concat(cheminSrcMainJavaVertxLangue, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceLangue, ".", "/"), ".java");
					String classeCheminApiEnsembleInfoLangue = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueApiEnsembleInfoLangue, ".", "/"), ".java");
					String classeCheminGenApiServiceImplLangue = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceImplLangue, ".", "/"), ".java");
					String classeCheminApiServiceImplLangue = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueApiServiceImplLangue, ".", "/"), ".java");
					String classeCheminGenApiServiceLangue = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceLangue, ".", "/"), ".java");
		
					indexerStockerSolr(langueNom, classeDoc, "classeCheminApiEnsembleInfo", classeCheminApiEnsembleInfoLangue); 
					indexerStockerSolr(langueNom, classeDoc, "classeCheminGenApiServiceImpl", classeCheminGenApiServiceImplLangue); 
					indexerStockerSolr(langueNom, classeDoc, "classeCheminApiServiceImpl", classeCheminApiServiceImplLangue); 
					indexerStockerSolr(langueNom, classeDoc, "classeCheminGenApiService", classeCheminGenApiServiceLangue); 
				}
			}
		}

		Boolean classeEstModeleBase = indexerStockerSolr(classeDoc, "classeEstModeleBase"
				, classePartsModeleBase != null 
				&& classeNomSimple.equals(classeNomSimple));
		Boolean classeEstResultatBase = indexerStockerSolr(classeDoc, "classeEstResultatBase"
				, classePartsResultatBase != null 
				&& classeNomSimple.equals(classeNomSimple));

		Boolean classeEtendModeleBase = indexerStockerSolr(classeDoc, "classeEtendModeleBase"
				, classePartsModeleBase != null 
				&& classeNomSimpleSuperGenerique != null 
				&& classeNomSimpleSuperGenerique.equals(classePartsModeleBase.nomSimple(langueNomGlobale)));
		Boolean classeEtendResultatBase = indexerStockerSolr(classeDoc, "classeEtendResultatBase"
				, classePartsResultatBase != null 
				&& classeNomSimpleSuperGenerique != null 
				&& classeNomSimpleSuperGenerique.equals(classePartsResultatBase.nomSimple(langueNomGlobale)));
		indexerStockerSolr(classeDoc, "classeInitLoinAvant" , regexTrouve("^" + i18nGlobale.getString(I18n.var_InitLoin) + i18nGlobale.getString(I18n.var_Avant) + ": (true)$", classeCommentaire));
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
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.regex.Matcher", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.regex.Pattern", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.annotation.JsonProperty", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.annotation.JsonIgnore", classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, JsonFormat.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, JsonSerialize.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		classePartsGenAjouter(ClasseParts.initClasseParts(this, JsonDeserialize.class.getCanonicalName(), classeLangueNom), classeLangueNom);

		if(classePartsLocalDateSerializer != null)
			classePartsGenAjouter(classePartsLocalDateSerializer, classeLangueNom);

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
		Boolean classePageRecherchePublicLire = false;
		Boolean classeRoleSession = false;
		Boolean classeRoleUtilisateur = false;
		Boolean classeRoleChacun = false;
		Boolean classeRolesTrouves = false;
		
		if(classeCommentaire != null) {
			String motif = "^Val(:([^:\\n]+):)?\\.(\\w+)(\\.([^:\\n]+))?: ([>|-]{0,2}(\\d*)\\n)?([\\s\\S]*?)(?=\\n^\\w|\\Z)";
			Matcher classeValsRecherche = Pattern.compile(motif, Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeValsTrouves = classeValsRecherche.find();
			while(classeValsTrouves) {
				String classeValVar = classeValsRecherche.group(3);
				String classeValLangue = classeValsRecherche.group(5);
				String classeValCode = classeValsRecherche.group(2);
				String classeValValeur = null;
				String groupe1 = classeValsRecherche.group(6);
				String groupe2 = classeValsRecherche.group(7);
				String groupe3 = classeValsRecherche.group(8);
				Integer spaces = 2;
				if(groupe2 != null && groupe2.length() > 0)
					spaces = Integer.parseInt(groupe2);
				classeValValeur = groupe3.replaceAll("^" + String.join("", Collections.nCopies(spaces, " ")), "").replaceAll("\n" + String.join("", Collections.nCopies(spaces, " ")), "\n");

				if(groupe1 != null && groupe1.contains(">"))
					classeValValeur = classeValValeur.replaceAll("\\n([^\\n])", " $1");

				if(groupe1 != null && groupe1.contains("-"))
					classeValValeur = classeValValeur.replaceAll("\\n+\\Z", "");
				else if(groupe1 != null && !groupe1.contains("+"))
					classeValValeur = classeValValeur.replaceAll("\\n\\Z", "");

				if(classeValCode == null)
					classeValCode = "";
				if(classeValLangue == null) {
					stockerListeSolr(classeDoc, "classeValsVar", classeValVar);
					stockerListeSolr(classeDoc, "classeValsLangue", classeLangueNom);
					stockerListeSolr(classeDoc, "classeValsCode", classeValCode);
					stockerListeSolr(classeDoc, "classeValsValeur", classeValValeur);
				}
				else {
					stockerListeSolr(classeDoc, "classeValsVar", classeValVar);
					stockerListeSolr(classeDoc, "classeValsLangue", classeValLangue);
					stockerListeSolr(classeDoc, "classeValsCode", classeValCode);
					stockerListeSolr(classeDoc, "classeValsValeur", classeValValeur);
				}
				classeValsTrouves = classeValsRecherche.find();
			}

			classePublicLire = indexerStockerSolr(classeDoc, "classePublicLire", regexTrouve("^" + i18nGlobale.getString(I18n.var_PublicLire) + ": (true)$", classeCommentaire));
			classePageRecherchePublicLire = indexerStockerSolr(classeDoc, "classePageRecherchePublicLire", regexTrouve("^" + i18nGlobale.getString(I18n.var_PageRecherchePublicLire) + ": (true)$", classeCommentaire));
			classeRoleSession = indexerStockerSolr(classeDoc, "classeRoleSession", regexTrouve("^" + i18nGlobale.getString(I18n.var_AuthSession) + ": (true)$", classeCommentaire));
			classeRoleUtilisateur = indexerStockerSolr(classeDoc, "classeRoleUtilisateur", regexTrouve("^" + i18nGlobale.getString(I18n.var_AuthUtilisateur) + ": (true)$", classeCommentaire));
			classeRoleChacun = indexerStockerSolr(classeDoc, "classeRoleChacun", regexTrouve("^" + i18nGlobale.getString(I18n.var_AuthChacun) + ": (true)$", classeCommentaire));

			Matcher classeRolesRecherche = Pattern.compile("^" + i18nGlobale.getString(I18n.var_Role) + "(\\.([^:\n]+))?: (.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			classeRolesTrouves = classeRolesRecherche.find();
			boolean classeRolesTrouvesActuel = classeRolesTrouves;
			while(classeRolesTrouvesActuel) {
				String classeRoleValeur = classeRolesRecherche.group(3);
				String classeRoleLangue = classeRolesRecherche.group(2);
				classeRoleLangue = classeRoleLangue == null ? classeLangueNom : classeRoleLangue;
				stockerListeSolr(classeDoc, "classeRoles", classeRoleValeur);
				stockerListeSolr(classeDoc, "classeRolesLangue", classeRoleLangue);
				classeRolesTrouves = true;
				classeRolesTrouvesActuel = classeRolesRecherche.find();
			}
			indexerStockerSolr(classeDoc, "classeRolesTrouves", classeRolesTrouves); 

			Matcher classeRoleLiresRecherche = Pattern.compile("^" + i18nGlobale.getString(I18n.var_AuthLire) + "\\.([^:\n]+): (.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
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

			Matcher classeFiltresRecherche = Pattern.compile("^" + i18nGlobale.getString(I18n.var_Filtre) + "\\.([^:\n]+): (.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
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

			Matcher classeMotsClesRecherche = Pattern.compile("^" + i18nGlobale.getString(I18n.var_MotCle) + ": (.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeMotsClesTrouvesActuel = classeMotsClesRecherche.find();
			while(classeMotsClesTrouvesActuel) {
				String classeMotCleValeur = classeMotsClesRecherche.group(1);
				classeMotsClesTrouvesActuel = classeMotsClesRecherche.find();
				if(!classeMotsCles.contains(classeMotCleValeur))
					classeMotsCles.add(classeMotCleValeur);
				classeMotsClesTrouves = true;
			}

			Matcher classeTrisRecherche = Pattern.compile("^" + i18nGlobale.getString(I18n.var_Tri) + "\\.([^:]+): (.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeTrisTrouvesActuel = classeTrisRecherche.find();
			while(classeTrisTrouvesActuel) {
				String classeTriOrdre = classeTrisRecherche.group(1);
				String classeTriVar = classeTrisRecherche.group(2);
				classeTrisTrouvesActuel = classeTrisRecherche.find();
				classeTrisOrdre.add(classeTriOrdre);
				classeTrisVar.add(classeTriVar);
				classeTrisTrouves = true;
			}

			String sqlString = regex("^Sql: (.*)$", classeCommentaire, 1);
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

			Matcher classeMapRecherche = Pattern.compile("^Map\\.([^:]+): (.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
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
						} catch (Throwable ex) {
							LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
						}
					}
					else if("Double".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Double.parseDouble(classeMapValeur));
						} catch (Throwable ex) {
							LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
						}
					}
					else if("Long".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Long.parseLong(classeMapValeur));
						} catch (Throwable ex) {
							LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
						}
					}
					else if("Boolean".equals(classeMapCleType)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Boolean.parseBoolean(classeMapValeur));
						} catch (Throwable ex) {
							LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
						}
					}
					else if("ZonedDateTime".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Date.from(ZonedDateTime.parse(classeMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
						} catch (Throwable ex) {
							LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
						}
					}
					else if("LocalDateTime".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Date.from(LocalDateTime.parse(classeMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()));
						} catch (Throwable ex) {
							LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
						}
					}
					else if("LocalDate".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Date.from(LocalDate.parse(classeMapValeur, DateTimeFormatter.ISO_OFFSET_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()));
						} catch (Throwable ex) {
							LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
						}
					}
					else if("LocalTime".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], classeMapValeur);
						} catch (Throwable ex) {
							LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
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

		List<String> classeSuperEcrireMethodes;
		List<String> classeEcrireMethodes = new ArrayList<>();
		String classeNomSimpleBaseGenLangue = null;
		if(classeSuperDoc != null) {
			classeSuperEcrireMethodes = (List<String>)classeSuperDoc.get("classeEcrireMethodes_stored_strings");
			classeNomSimpleBaseGenLangue = (String)classeSuperDoc.get("classeNomSimpleBaseGenLangue_" + classeLangueNom + "_stored_string");
		} else {
			classeSuperEcrireMethodes = new ArrayList<>();
		}
		if(classeNomSimpleBaseGenLangue == null)
			classeNomSimpleBaseGenLangue = classeNomSimpleSuper;
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleBaseGenLangue",  classeNomSimpleBaseGenLangue);

		String classeOrdreStr = regex("^" + i18nGlobale.getString(I18n.var_Ordre) + ": (.*)", classeCommentaire);
		if(NumberUtils.isParsable(classeOrdreStr))
			classeOrdre = indexerStockerSolr(classeDoc, "classeOrdre", Integer.parseInt(classeOrdreStr)); 

		String classeOrdreSqlStr = regex("^" + i18nGlobale.getString(I18n.var_OrdreSql) + ": (.*)", classeCommentaire, classeOrdreStr);
		if(NumberUtils.isParsable(classeOrdreSqlStr))
			classeOrdreSql = indexerStockerSolr(classeDoc, "classeOrdreSql", Integer.parseInt(classeOrdreSqlStr)); 

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

		if(classeDoc.getField(solrId) == null)
			classeDoc.addField(solrId, classeCle);  

		indexerStockerSolr(classeDoc, "partEstClasse", true);
		indexerStockerSolr(classeDoc, "partNumero", partNumero);

		String classeCodeSourceComplet = null;
		try {
			classeCodeSourceComplet = classeQdox.getCodeBlock();
		} catch(Throwable ex) {
			LOG.error(String.format(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), classeQdox.getCanonicalName()), ex);
			ExceptionUtils.rethrow(ex);
		}
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
				indexerStockerSolr(champDoc, "champTraduire", !regexTrouve("^" + i18nGlobale.getString(I18n.var_Traduire) + ": \\s*(false)$", champCommentaire));
	
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
				champDoc.addField(solrId, String.format("%s %s %s", solrCollectionComputate, champNomCanoniqueComplet, champCle));

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
				String constructeurCle = solrCollectionComputate + " " + classeCheminAbsolu + "." + classeNomSimple + "(";

				for(int i = 0; i < constructeurParamsQdox.size(); i++) {
					JavaParameter paramQdox = constructeurParamsQdox.get(i);
					if(i > 0)
						constructeurCle += ", ";
					constructeurCle += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
				}
				constructeurCle += ")"; 

				constructeurDoc.addField(solrId, constructeurCle);
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
					JavaMethod methodeQdoxActuelle = null;
					String methodeCommentaireActuelle = null;
	
					if(classeEtendGen && !methodeQdox.isStatic() && !methodeQdox.isFinal() && methodeQdox.getDeclaringClass().equals(classeQdox) 
							&& methodeQdox.isProtected() && methodeParamsQdox.size() == 1 && classeQdoxRetour.isVoid()
							&& StringUtils.startsWith(methodeQdox.getName(), "_")) {

						JavaClass entiteClasseQdox = methodeParamsQdox.get(0).getJavaClass();
						String entiteVar = StringUtils.substringAfter(methodeQdox.getName(), "_");

						if(methodeEstSubstitue) {
							// est entite substitué
							JavaMethod methodeQdoxTmp = classeQdox.getSuperJavaClass().getSuperJavaClass().getMethodBySignature(methodeQdox.getName(), Arrays.asList(entiteClasseQdox));
							if(methodeQdoxTmp != null) {
								methodeQdoxActuelle = methodeQdox;
								methodeQdox = methodeQdoxTmp;
								methodeCommentaireActuelle = methodeCommentaire;
								methodeCommentaire = methodeQdox.getComment();
							}
						}

						// est Entite. 
						SolrInputDocument entiteDoc = classeDocClone.deepCopy();

						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVar", entiteVar);
						indexerStockerSolr(entiteDoc, "entiteEstSubstitue", methodeEstSubstitue);
						indexerStockerListeSolr(classeLangueNom, classeDoc, "classeEntiteVars", entiteVar);
						String entiteVarCapitalise = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVarCapitalise", StringUtils.capitalize(entiteVar));
						ClasseParts entiteClasseParts = ClasseParts.initClasseParts(this, entiteClasseQdox, classeLangueNom);
						Boolean entiteCouverture = false;
						Boolean entitePromesse = false;

						if(classePartsCouverture == null)
							throw new RuntimeException(String.format("%s %s %s %s %s. ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_Couverture), i18nGlobale.getString(I18n.var_manquante), i18nGlobale.getString(I18n.var_dans), cheminSrcMainJava));
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
								List<String> classesSuperEtMoiSansGen = (List<String>)classePartsGenerique.getDocumentSolr().get("classesSuperEtMoiSansGen_stored_strings");
								if(classesSuperEtMoiSansGen != null) {
									for(String nomCanonique : classesSuperEtMoiSansGen) {
										entiteNomsCanoniquesSuperEtMoiSansGen.add(nomCanonique);
										indexerStockerListeSolr(entiteDoc, "classesSuperEtMoiSansGen", nomCanonique); 
									}
								}
							}
						}
						else if(entiteClasseParts != null && entiteClasseParts.getDocumentSolr() != null) {

							List<String> classesSuperEtMoiSansGen = (List<String>)entiteClasseParts.getDocumentSolr().get("classesSuperEtMoiSansGen_stored_strings");
							if(classesSuperEtMoiSansGen != null) {
								for(String nomCanonique : classesSuperEtMoiSansGen) {
									entiteNomsCanoniquesSuperEtMoiSansGen.add(nomCanonique);
									indexerStockerListeSolr(entiteDoc, "classesSuperEtMoiSansGen", nomCanonique); 
								}
							}
						}

						indexerStockerSolr(entiteDoc, "entitePromesse", entitePromesse);
						indexerStockerSolr(entiteDoc, "entiteCouverture", entiteCouverture);
						Boolean entiteInitialise = indexerStockerSolr(entiteDoc, "entiteInitialise", !regexTrouve("^" + i18nGlobale.getString(I18n.var_Initialise) + ": (false)$", methodeCommentaire) && !entiteVar.endsWith("_") && BooleanUtils.isTrue(entiteClasseParts.getEtendGen()));

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

						if("JsonObject".equals(entiteNomSimple)) {
							classePartsGenAjouter(classePartsJsonObjectDeserializer, classeLangueNom);
						}
						if("JsonArray".equals(entiteNomSimple)) {
							classePartsGenAjouter(classePartsJsonArrayDeserializer, classeLangueNom);
						}

						if("Point".equals(entiteNomSimple)) {
							classePartsGenAjouter(classePartsPointSerializer, classeLangueNom);
							classePartsGenAjouter(classePartsPointDeserializer, classeLangueNom);
						}

						if(
								"Point".equals(entiteNomSimple) || "Path".equals(entiteNomSimple) || "Polygon".equals(entiteNomSimple)
								|| "Point".equals(entiteNomSimpleGenerique) || "Path".equals(entiteNomSimpleGenerique) || "Polygon".equals(entiteNomSimpleGenerique)
								) {
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.databind.ObjectMapper", classeLangueNom), classeLangueNom);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.databind.module.SimpleModule", classeLangueNom), classeLangueNom);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.databind.deser.BeanDeserializerModifier", classeLangueNom), classeLangueNom);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.databind.JsonDeserializer", classeLangueNom), classeLangueNom);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.databind.DeserializationConfig", classeLangueNom), classeLangueNom);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.databind.BeanDescription", classeLangueNom), classeLangueNom);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.stream.Collectors", classeLangueNom), classeLangueNom);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.Json", classeLangueNom), classeLangueNom);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "com.fasterxml.jackson.databind.ObjectMapper", classeLangueNom), classeLangueNom);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.pgclient.data.Point", classeLangueNom), classeLangueNom);
						}
						if("Path".equals(entiteNomSimple) || "Path".equals(entiteNomSimpleGenerique)) {
							classePartsGenAjouter(classePartsPathSerializer, classeLangueNom);
							classePartsGenAjouter(classePartsPathDeserializer, classeLangueNom);
						}
						if("Polygon".equals(entiteNomSimple) || "Polygon".equals(entiteNomSimpleGenerique)) {
							classePartsGenAjouter(classePartsPolygonSerializer, classeLangueNom);
							classePartsGenAjouter(classePartsPolygonDeserializer, classeLangueNom);
						}

						JavaMethod entiteSetter = null;
						try {
							entiteSetter = classeQdox.getMethodBySignature("set" + entiteVarCapitalise, new ArrayList<JavaType>() {{ add(classeQdoxString); }}, true);
						} catch (Throwable ex) {
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
						if(classeOrdre != null)
							indexerStockerSolr(entiteDoc, "classeOrdre", classeOrdre);
						if(classeOrdreSql != null)
							indexerStockerSolr(entiteDoc, "classeOrdreSql", classeOrdreSql);

						if(entiteNomsCanoniquesSuperEtMoiSansGen.size() > 0) {

							SolrQuery rechercheSolrMethodeAvant = new SolrQuery();   
							rechercheSolrMethodeAvant.setQuery("*:*");
							rechercheSolrMethodeAvant.setRows(10);
							String fqMethodeAvant = "(" + entiteNomsCanoniquesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(i18nGlobale.getString(I18n.var_avant) + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolrMethodeAvant.addFilterQuery("classesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
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
							String fqMethodeApres = "(" + entiteNomsCanoniquesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(i18nGlobale.getString(I18n.var_apres) + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolrMethodeApres.addFilterQuery("classesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
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
							String motif = "^Val(:([^:\\n]+):)?\\.(\\w+)(\\.([^:\\n]+))?: ([>|-]{0,2}(\\d*)\\n)?([\\s\\S]*?)(?=\\n^\\w|\\Z)";
							Matcher entiteValsRecherche = Pattern.compile(motif, Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteValsTrouves = entiteValsRecherche.find();
							while(entiteValsTrouves) {
								String entiteValVar = entiteValsRecherche.group(3);
								String entiteValLangue = entiteValsRecherche.group(5);
								String entiteValCode = entiteValsRecherche.group(2);
								String entiteValValeur = null;
								String groupe1 = entiteValsRecherche.group(6);
								String groupe2 = entiteValsRecherche.group(7);
								String groupe3 = entiteValsRecherche.group(8);
								Integer spaces = 2;
								if(groupe2 != null && groupe2.length() > 0)
									spaces = Integer.parseInt(groupe2);
								entiteValValeur = groupe3.replaceAll("^" + String.join("", Collections.nCopies(spaces, " ")), "").replaceAll("\n" + String.join("", Collections.nCopies(spaces, " ")), "\n");

								if(groupe1 != null && groupe1.contains(">"))
									entiteValValeur = entiteValValeur.replaceAll("\\n([^\\n])", " $1");

								if(groupe1 != null && groupe1.contains("-"))
									entiteValValeur = entiteValValeur.replaceAll("\\n+\\Z", "");
								else if(groupe1 != null && !groupe1.contains("+"))
									entiteValValeur = entiteValValeur.replaceAll("\\n\\Z", "");

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

							Matcher entiteMotsClesRecherche = Pattern.compile("^" + i18nGlobale.getString(I18n.var_MotCle) + ": (.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
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

							Matcher entiteFacetsRecherche = Pattern.compile("^Facet: (.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteFacetsTrouves = entiteFacetsRecherche.find();
							boolean entiteFacetsTrouvesActuel = entiteFacetsTrouves;
							while(entiteFacetsTrouvesActuel) {
								String entiteFacetValeur = entiteFacetsRecherche.group(1);
								indexerStockerListeSolr(entiteDoc, "entiteFacets", entiteFacetValeur);
								entiteFacetsTrouves = true;
								entiteFacetsTrouvesActuel = entiteFacetsRecherche.find();
							}
							indexerStockerSolr(entiteDoc, "entiteFacetsTrouves", entiteFacetsTrouves); 

							String sqlString = regex("^Sql: (.*)$", methodeCommentaire, 1);
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

							Matcher entiteMapRecherche = Pattern.compile("^Map.([^:]+): (.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
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
										} catch (Throwable ex) {
											LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
										}
									}
									else if("Double".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Double.parseDouble(entiteMapValeur));
										} catch (Throwable ex) {
											LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
										}
									}
									else if("Long".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Long.parseLong(entiteMapValeur));
										} catch (Throwable ex) {
											LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
										}
									}
									else if("Boolean".equals(entiteMapCleType)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Boolean.parseBoolean(entiteMapValeur));
										} catch (Throwable ex) {
											LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
										}
									}
									else if("ZonedDateTime".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Date.from(ZonedDateTime.parse(entiteMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
										} catch (Throwable ex) {
											LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
										}
									}
									else if("LocalDateTime".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Date.from(LocalDateTime.parse(entiteMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()));
										} catch (Throwable ex) {
											LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
										}
									}
									else if("LocalDate".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Date.from(LocalDate.parse(entiteMapValeur, DateTimeFormatter.ISO_OFFSET_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()));
										} catch (Throwable ex) {
											LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
										}
									}
									else if("LocalTime".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], entiteMapValeur);
										} catch (Throwable ex) {
											LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
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
						indexerStockerSolr(entiteDoc, "entiteExact", regexTrouve("^" + i18nGlobale.getString(I18n.var_Exact) + ": (true)$", methodeCommentaire));
						Boolean entiteClePrimaire = indexerStockerSolr(entiteDoc, "entiteClePrimaire", regexTrouve("^" + i18nGlobale.getString(I18n.var_ClePrimaire) + ": (true)$", methodeCommentaire));
						Boolean entiteInheritClePrimaire = indexerStockerSolr(entiteDoc, "entiteInheritClePrimaire", regexTrouve("^" + i18nGlobale.getString(I18n.var_InheritClePrimaire) + ": (true)$", methodeCommentaire));
						Boolean entiteSauvegardes = indexerStockerSolr(entiteDoc, "entiteSauvegardes", regexTrouve("^" + i18nGlobale.getString(I18n.var_Sauvegardes) + ": (true)$", methodeCommentaire));
						Boolean entiteCleUnique = indexerStockerSolr(entiteDoc, "entiteCleUnique", regexTrouve("^" + i18nGlobale.getString(I18n.var_CleUnique) + ": (true)$", methodeCommentaire));
						Boolean entiteZone = indexerStockerSolr(entiteDoc, "entiteZone", regexTrouve("^" + i18nGlobale.getString(I18n.var_Zone) + ": (true)$", methodeCommentaire));
						Boolean entiteEmplacement = indexerStockerSolr(entiteDoc, "entiteEmplacement", regexTrouve("^" + i18nGlobale.getString(I18n.var_Emplacement) + ": (true)$", methodeCommentaire));
						Boolean entiteEmplacementCouleur = indexerStockerSolr(entiteDoc, "entiteEmplacementCouler", regexTrouve("^" + i18nGlobale.getString(I18n.var_Emplacement) + i18nGlobale.getString(I18n.var_Couleur) + ": (true)$", methodeCommentaire));
						Boolean entiteEmplacementTitre = indexerStockerSolr(entiteDoc, "entiteEmplacementTitre", regexTrouve("^" + i18nGlobale.getString(I18n.var_Emplacement) + i18nGlobale.getString(I18n.var_Titre) + ": (true)$", methodeCommentaire));
						Boolean entiteEmplacementUrl = indexerStockerSolr(entiteDoc, "entiteEmplacementUrl", regexTrouve("^" + i18nGlobale.getString(I18n.var_Emplacement) + i18nGlobale.getString(I18n.var_Url) + ": (true)$", methodeCommentaire));
						Boolean entiteEmplacementSvg = indexerStockerSolr(entiteDoc, "entiteEmplacementSvg", regexTrouve("^" + i18nGlobale.getString(I18n.var_Emplacement) + "Svg: (true)$", methodeCommentaire));
						Boolean entiteEmplacementRayon = indexerStockerSolr(entiteDoc, "entiteEmplacementRayon", regexTrouve("^" + i18nGlobale.getString(I18n.var_Emplacement) + i18nGlobale.getString(I18n.var_Rayon) + ": (true)$", methodeCommentaire));
						Boolean entiteAire = indexerStockerSolr(entiteDoc, "entiteAire", regexTrouve("^" + i18nGlobale.getString(I18n.var_Aire) + ": (true)$", methodeCommentaire));
						Boolean entiteAireCouleur = indexerStockerSolr(entiteDoc, "entiteAireCouler", regexTrouve("^" + i18nGlobale.getString(I18n.var_Aire) + i18nGlobale.getString(I18n.var_Couleur) + ": (true)$", methodeCommentaire));
						Boolean entiteAireTitre = indexerStockerSolr(entiteDoc, "entiteAireTitre", regexTrouve("^" + i18nGlobale.getString(I18n.var_Aire) + i18nGlobale.getString(I18n.var_Titre) + ": (true)$", methodeCommentaire));
						Boolean entiteAireUrl = indexerStockerSolr(entiteDoc, "entiteAireUrl", regexTrouve("^" + i18nGlobale.getString(I18n.var_Aire) + i18nGlobale.getString(I18n.var_Url) + ": (true)$", methodeCommentaire));
						Boolean entiteCrypte = indexerStockerSolr(entiteDoc, "entiteCrypte", regexTrouve("^" + i18nGlobale.getString(I18n.var_Crypte) + ": (true)$", methodeCommentaire));
						Boolean entiteSuggere = indexerStockerSolr(entiteDoc, "entiteSuggere", regexTrouve("^" + i18nGlobale.getString(I18n.var_Suggere) + ": (true)$", methodeCommentaire));
						Boolean entiteVarUrlPageAffichage = indexerStockerSolr(entiteDoc, "entiteVarUrlPageAffichage", regexTrouve("^VarUrl" + i18nGlobale.getString(I18n.var_PageAffichage) + ": (true)$", methodeCommentaire));
						Boolean entiteVarUrlPageEdition = indexerStockerSolr(entiteDoc, "entiteVarUrlPageEdition", regexTrouve("^VarUrl" + i18nGlobale.getString(I18n.var_PageEdition) + ": (true)$", methodeCommentaire));
						Boolean entiteVarUrlPageUtilisateur = indexerStockerSolr(entiteDoc, "entiteVarUrlPageUtilisateur", regexTrouve("^VarUrl" + i18nGlobale.getString(I18n.var_PageUtilisateur) + ": (true)$", methodeCommentaire));
						Boolean entiteVarUrlTelechargement = indexerStockerSolr(entiteDoc, "entiteVarUrlTelechargement", regexTrouve("^VarUrl" + i18nGlobale.getString(I18n.var_Telechargement) + ": (true)$", methodeCommentaire));
						Boolean entiteVarUrlApi = indexerStockerSolr(entiteDoc, "entiteVarUrlApi", regexTrouve("^VarUrlApi: (true)$", methodeCommentaire));
						String entiteVarUrl = regex("^" + i18nGlobale.getString(I18n.var_VarUrl) + ": (.*)$", methodeCommentaire);
						if(entiteVarUrl != null)
							indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVarUrl", entiteVarUrl);
						Boolean entiteVarId = regexTrouve("^" + i18nGlobale.getString(I18n.var_VarId) + ": (true)$", methodeCommentaire);
						{
							if(methodeCommentaireActuelle != null)
								entiteVarId = Optional.ofNullable(regexTrouve("^" + i18nGlobale.getString(I18n.var_VarId) + ": (true)$", methodeCommentaireActuelle)).orElse(entiteVarId);
							if(entiteVarId != null) {
								indexerStockerSolr(entiteDoc, "entiteVarId", entiteVarId);
							}
						}

						Boolean entiteUnique = indexerStockerSolr(entiteDoc, "entiteUnique", regexTrouve("^" + i18nGlobale.getString(I18n.var_Unique) + ": (true)$", methodeCommentaire) || entiteVarId);
						Boolean entiteVarNom = regexTrouve("^" + i18nGlobale.getString(I18n.var_VarNom) + ": (true)$", methodeCommentaire);
						{
							if(methodeCommentaireActuelle != null)
								entiteVarNom = Optional.ofNullable(regexTrouve("^" + i18nGlobale.getString(I18n.var_VarNom) + ": (true)$", methodeCommentaireActuelle)).orElse(entiteVarNom);
							if(entiteVarNom != null) {
								indexerStockerSolr(entiteDoc, "entiteVarNom", entiteVarNom);
							}
						}
						Boolean entiteVarTitre = indexerStockerSolr(entiteDoc, "entiteVarTitre", regexTrouve("^" + i18nGlobale.getString(I18n.var_VarTitre) + ": (true)$", methodeCommentaire));
						Boolean entiteVarH1 = indexerStockerSolr(entiteDoc, "entiteVarH1", regexTrouve("^VarH1: (true)$", methodeCommentaire));
						Boolean entiteVarH2 = indexerStockerSolr(entiteDoc, "entiteVarH2", regexTrouve("^VarH2: (true)$", methodeCommentaire));
						Boolean entiteVarH3 = indexerStockerSolr(entiteDoc, "entiteVarH3", regexTrouve("^VarH3: (true)$", methodeCommentaire));
						Boolean entiteVarDescription = indexerStockerSolr(entiteDoc, "entiteVarDescription", regexTrouve("^" + i18nGlobale.getString(I18n.var_VarDescription) + ": (true)$", methodeCommentaire));
						Boolean entiteVarImageUrl = indexerStockerSolr(entiteDoc, "entiteVarImageUrl", regexTrouve("^VarImageUrl: (true)$", methodeCommentaire));
						Boolean entiteVarModifie = indexerStockerSolr(entiteDoc, "entiteVarModifie", regexTrouve("^" + i18nGlobale.getString(I18n.var_VarModifie) + ": (true)$", methodeCommentaire));
						Boolean entiteVarCree = indexerStockerSolr(entiteDoc, "entiteVarCree", regexTrouve("^" + i18nGlobale.getString(I18n.var_VarCree) + ": (true)$", methodeCommentaire));
						Boolean entiteSauvegarde = indexerStockerSolr(entiteDoc, "entiteSauvegarde", regexTrouve("^" + i18nGlobale.getString(I18n.var_Sauvegarde) + ": (true)$", methodeCommentaire));
						Boolean entiteIncremente = indexerStockerSolr(entiteDoc, "entiteIncremente", regexTrouve("^" + i18nGlobale.getString(I18n.var_Incremente) + ": (true)$", methodeCommentaire));
						Boolean entiteTexte = indexerStockerSolr(entiteDoc, "entiteTexte", regexTrouve("^" + i18nGlobale.getString(I18n.var_Texte) + ": (true)$", methodeCommentaire));
						Boolean entiteDocValues = indexerStockerSolr(entiteDoc, "entiteDocValues", regexTrouve("^" + i18nGlobale.getString(I18n.var_DocValues) + ": (true)$", methodeCommentaire));
						Boolean entiteIndexe = indexerStockerSolr(entiteDoc, "entiteIndexe", regexTrouve("^" + i18nGlobale.getString(I18n.var_Indexe) + ": (true)$", methodeCommentaire) || entiteCleUnique || entiteCrypte || entiteSuggere || entiteTexte || entiteClePrimaire || entiteIncremente || entiteDocValues);
						Boolean entiteStocke = indexerStockerSolr(entiteDoc, "entiteStocke", regexTrouve("^" + i18nGlobale.getString(I18n.var_Stocke) + ": (true)$", methodeCommentaire));
						Boolean entiteRequis = indexerStockerSolr(entiteDoc, "entiteRequis", regexTrouve("^" + i18nGlobale.getString(I18n.var_Requis) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteIndexeOuStocke", entiteCleUnique || entiteCrypte || entiteSuggere || entiteTexte || entiteIndexe || entiteStocke || entiteDocValues || entiteIncremente || entiteTexte);
						indexerStockerSolr(entiteDoc, "entiteCacherPOST", regexTrouve("^" + i18nGlobale.getString(I18n.var_Cacher) + "POST: (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteCacherPATCH", regexTrouve("^" + i18nGlobale.getString(I18n.var_Cacher) + "PATCH: (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "cacherRecherche", regexTrouve("^" + i18nGlobale.getString(I18n.var_Cacher) + i18nGlobale.getString(I18n.var_Recherche) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteIgnorer", regexTrouve("^" + i18nGlobale.getString(I18n.var_Ignorer) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteDeclarer", regexTrouve("^" + i18nGlobale.getString(I18n.var_Declarer) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRechercher", regexTrouve("^" + i18nGlobale.getString(I18n.var_Rechercher) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteAjouter", regexTrouve("^" + i18nGlobale.getString(I18n.var_Ajouter) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSupprimer", regexTrouve("^" + i18nGlobale.getString(I18n.var_Supprimer) + ": (true)$", methodeCommentaire));
						Boolean entiteDefinir = indexerStockerSolr(entiteDoc, "entiteDefinir", regexTrouve("^" + i18nGlobale.getString(I18n.var_Definir) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteModifier", !regexTrouve("^" + i18nGlobale.getString(I18n.var_Modifier) + ": (false)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRecharger", regexTrouve("^" + i18nGlobale.getString(I18n.var_Recharger) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteMultiligne", regexTrouve("^" + i18nGlobale.getString(I18n.var_Multiligne) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSignature", regexTrouve("^" + i18nGlobale.getString(I18n.var_Signature) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteCles", regexTrouve("^" + i18nGlobale.getString(I18n.var_Cles) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSetTrim", regexTrouve("^SetTrim: (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSetLower", regexTrouve("^SetLower: (true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSetUpper", regexTrouve("^SetUpper: (true)$", methodeCommentaire));

						String entiteLangue = regex("^" + i18nGlobale.getString(I18n.var_Langue) + ": (.*)$", methodeCommentaire);
						if(entiteLangue != null)
							indexerStockerSolr(entiteDoc, "entiteLangue", entiteLangue);

						{
							String str = regexLangue(classeLangueNom, "^" + i18nGlobale.getString(I18n.var_NomAffichage), methodeCommentaire);
							if(methodeCommentaireActuelle != null)
								str = Optional.ofNullable(regexLangue(classeLangueNom, "^" + i18nGlobale.getString(I18n.var_NomAffichage), methodeCommentaireActuelle)).orElse(str);
							if(str != null) {
								indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomAffichage", str);
							}
						}
						{
							String str = regexLangue(classeLangueNom, "^" + i18nGlobale.getString(I18n.var_Description), methodeCommentaire);
							if(methodeCommentaireActuelle != null)
								str = Optional.ofNullable(regexLangue(classeLangueNom, "^" + i18nGlobale.getString(I18n.var_Description), methodeCommentaireActuelle)).orElse(str);
							if(str != null) {
								indexerStockerSolr(classeLangueNom, entiteDoc, "entiteDescription", str);
							}
						}
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteUniteLabel", regexLangue(classeLangueNom, "^" + i18nGlobale.getString(I18n.var_UniteLabel), methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteOptionnel", regexTrouve("^" + i18nGlobale.getString(I18n.var_Optionnel) + ": (true)$", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteHtmInfobulle", regex("^" + i18nGlobale.getString(I18n.var_HtmInfobulle) + ": (.*[>|-]{0,2}[\\s\\S]*)(^\\w|\\Z)", methodeCommentaire, 1));
						indexerStockerSolrRegex(classeLangueNom, entiteDoc, "entiteVarApi", "VarApi", methodeCommentaire);
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteEnumNomSimple", regexLangue(classeLangueNom, "^" + i18nGlobale.getString(I18n.var_EnumNomSimple), methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteEnumVar", regexLangue(classeLangueNom, "^EnumVar", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteEnumVarDescription", regexLangue(classeLangueNom, "^" + i18nGlobale.getString(I18n.var_EnumVarDescription), methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteImageBase64Url", regexLangue(classeLangueNom, "^ImageBase64Url", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteIcone", regex("^" + i18nGlobale.getString(I18n.var_Icone) + ": (.*)", methodeCommentaire, 1));
						indexerStockerSolr(entiteDoc, "entiteLien", regexTrouve("^" + i18nGlobale.getString(I18n.var_Lien) + ": (true)$", methodeCommentaire));
						Boolean entiteCouleur = indexerStockerSolr(entiteDoc, "entiteCouleur", regexTrouve("^" + i18nGlobale.getString(I18n.var_Couleur) + ": (true)$", methodeCommentaire));
						if(entiteCouleur)
							indexerStockerSolr(classeDoc, "classeEntiteCouleur", entiteVar);

						JsonObject entiteRechercherMaxObjet = regexYamlObject(i18nGlobale.getString(I18n.var_RechercherMax), methodeCommentaire);
						if(entiteRechercherMaxObjet != null) {
							indexerStockerSolr(entiteDoc, "entiteRechercherMaxVarJsonArray", entiteRechercherMaxObjet.getString(i18nGlobale.getString(I18n.var_Var) + "JsonArray")); 
							indexerStockerSolr(entiteDoc, "entiteRechercherMaxVarValeur", entiteRechercherMaxObjet.getString(i18nGlobale.getString(I18n.var_VarValeur))); 
							indexerStockerSolr(entiteDoc, "entiteRechercherMaxVar", entiteRechercherMaxObjet.getString(i18nGlobale.getString(I18n.var_Var))); 
							indexerStockerSolr(entiteDoc, "entiteRechercherMaxValeur", entiteRechercherMaxObjet.getString(i18nGlobale.getString(I18n.var_Valeur))); 
						}
						JsonObject entiteRadioObjet = regexYamlObject(i18nGlobale.getString(I18n.var_Radio), methodeCommentaire);
						if(entiteRadioObjet != null) {
							for(String valeur : entiteRadioObjet.fieldNames()) {
								indexerStockerListeSolr(entiteDoc, "entiteRadioValeurs", valeur); 
								indexerStockerListeSolr(entiteDoc, "entiteRadioTextes", entiteRadioObjet.getString(valeur)); 
							}
						}
						JsonObject entiteOptionObjet = regexYamlObject(i18nGlobale.getString(I18n.var_Option), methodeCommentaire);
						if(entiteOptionObjet != null) {
							for(String valeur : entiteOptionObjet.fieldNames()) {
								indexerStockerListeSolr(entiteDoc, "entiteOptionValeurs", valeur); 
								indexerStockerListeSolr(entiteDoc, "entiteOptionTextes", entiteOptionObjet.getString(valeur)); 
							}
						}

						Boolean entiteHighlighting = indexerStockerSolr(entiteDoc, "entiteHighlighting", regexTrouve("^Highlighting: (true)$", methodeCommentaire));
						Boolean entiteHtml = entiteDefinir || regexTrouve("^Html: (true)$", methodeCommentaire);

						{ 
							String str = regex("^" + i18nGlobale.getString(I18n.var_HtmColonne) + ": (.*)$", methodeCommentaire);
							if(str == null && methodeCommentaireActuelle != null)
								str = regex("^" + i18nGlobale.getString(I18n.var_HtmColonne) + ": (.*)$", methodeCommentaireActuelle);
							if(NumberUtils.isCreatable(str)) {
								indexerStockerSolr(entiteDoc, "entiteHtmColonne", Integer.parseInt(str));
								entiteHtml = true;
							}
						}
						{ 
							String str = regex("^" + i18nGlobale.getString(I18n.var_HtmLigne) + ": (.*)$", methodeCommentaire);
							if(str == null && methodeCommentaireActuelle != null)
								str = regex("^" + i18nGlobale.getString(I18n.var_HtmLigne) + ": (.*)$", methodeCommentaireActuelle);
							if(NumberUtils.isCreatable(str)) {
								Integer i = Integer.parseInt(str);
								indexerStockerSolr(entiteDoc, "entiteHtmLigne", i);
								entiteHtml = true;
								String entiteHtmLigneTitreOuvert = indexerStockerSolr(entiteDoc, "entiteHtmLigneTitreOuvert", regex("^" + i18nGlobale.getString(I18n.var_HtmLigneTitreOuvert) + ": (.*)$", methodeCommentaire));
								indexerStockerSolr(entiteDoc, "entiteHtmLigneTitre", regex("^" + i18nGlobale.getString(I18n.var_HtmLigneTitre) + ": (.*)$", methodeCommentaire, entiteHtmLigneTitreOuvert));
								indexerStockerSolr(entiteDoc, "entiteHtmLigneEnTeteExpression", regex("^" + i18nGlobale.getString(I18n.var_HtmLigne) + i18nGlobale.getString(I18n.var_EnTeteExpression) + ": (.*)$", methodeCommentaire));
								if(regexTrouve("^" + i18nGlobale.getString(I18n.var_HtmLigne) + i18nGlobale.getString(I18n.var_Verticale) + ": (true)$", methodeCommentaire))
									indexerStockerSolr(entiteDoc, "entiteHtmLigneVerticale", true);
								else if(regexTrouve("^" + i18nGlobale.getString(I18n.var_HtmLigne) + i18nGlobale.getString(I18n.var_Verticale) + ": (false)$", methodeCommentaire))
									indexerStockerSolr(entiteDoc, "entiteHtmLigneVerticale", false);
							}
						}
						{ 
							String str = regex("^" + i18nGlobale.getString(I18n.var_HtmCellule) + ": (.*)$", methodeCommentaire);
							if(str == null && methodeCommentaireActuelle != null)
								str = regex("^" + i18nGlobale.getString(I18n.var_HtmCellule) + ": (.*)$", methodeCommentaireActuelle);
							if(NumberUtils.isCreatable(str)) {
								indexerStockerSolr(entiteDoc, "entiteHtmCellule", Integer.parseInt(str));
								entiteHtml = true;
							}
						}

						indexerStockerSolr(entiteDoc, "entiteHtml", entiteHtml);

						{ 
							String str = regex("^" + i18nGlobale.getString(I18n.var_LongeurMin) + ": (.*)$", methodeCommentaire, 1);
							Integer num = NumberUtils.isCreatable(str) ? Integer.parseInt(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteLongeurMin", num);
						}

						{
							String str = regex("^" + i18nGlobale.getString(I18n.var_LongeurMax) + ": (.*)$", methodeCommentaire, 1);
							Integer num = NumberUtils.isCreatable(str) ? Integer.parseInt(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteLongeurMax", num);
						}

						{
							String str = regex("^Min: (.*)$", methodeCommentaire, 1);
							if(str != null)
								indexerStockerSolr(entiteDoc, "entiteMin", str);
						}

						{
							String str = regex("^Max: (.*)$", methodeCommentaire, 1);
							if(str != null)
								indexerStockerSolr(entiteDoc, "entiteMax", str);
						}

						{
							String str = regex("^" + i18nGlobale.getString(I18n.var_Defaut) + ": (.*)$", methodeCommentaire, 1);
							indexerStockerSolr(entiteDoc, "entiteDefaut", str);
						}

						if(classeTraduire) {
							for(String langueNom : classeAutresLangues) {  
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomAffichage", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAffichage), methodeCommentaire));
								indexerStockerSolr(langueNom, entiteDoc, "entiteEnumVarDescription", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_EnumVarDescription), methodeCommentaire));
								indexerStockerSolr(langueNom, entiteDoc, "entiteHtmInfobulle", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_HtmInfobulle), methodeCommentaire));
								indexerStockerSolrRegex(langueNom, entiteDoc, "entiteVarApi", "VarApi", methodeCommentaire);
								indexerStockerSolr(langueNom, entiteDoc, "entiteEnumVar", regexLangue(langueNom, "^EnumVar", methodeCommentaire));
								indexerStockerSolr(langueNom, entiteDoc, "entiteImageBase64Url", regexLangue(langueNom, "^ImageBase64Url", methodeCommentaire));

								if(entiteVarUrl != null) {
									SolrQuery rechercheSolrVar = new SolrQuery();   
									rechercheSolrVar.setQuery("*:*");
									rechercheSolrVar.setRows(1);
									rechercheSolrVar.addFilterQuery("classesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
									rechercheSolrVar.addFilterQuery("entiteVar_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteVarUrl));
									rechercheSolrVar.addFilterQuery("nomEnsembleDomaine_indexed_string:(" + computateEnsembleRecherchePrefixe + ClientUtils.escapeQueryChars(nomEnsembleDomaine) + ")");
									rechercheSolrVar.addFilterQuery("partEstEntite_indexed_boolean:true");
									rechercheSolrVar.addFilterQuery("entiteEstSubstitue_indexed_boolean:false");
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

						Matcher classeRessourcesAutorisationRecherche = methodeCommentaire == null ? null : Pattern.compile("^" + i18nGlobale.getString(I18n.var_RessourceAutorisation) + ": (.*)", Pattern.MULTILINE).matcher(methodeCommentaire);
						boolean classeRessourcesAutorisationTrouves = classeRessourcesAutorisationRecherche == null ? false : classeRessourcesAutorisationRecherche.find();
						boolean classeRessourcesAutorisationTrouvesActuel = classeRessourcesAutorisationTrouves;
						while(classeRessourcesAutorisationTrouvesActuel) {
							String classeRessourceAutorisation = classeRessourcesAutorisationRecherche.group(1);
							stockerListeSolr(classeDoc, "classeRessourcesAutorisation", String.format("%s-%s", classeRessourceAutorisation, entiteVar));
							classeRessourcesAutorisationTrouves = true;
							classeRessourcesAutorisationTrouvesActuel = classeRessourcesAutorisationRecherche.find();
						}

						Matcher entiteAttribuerRecherche = methodeCommentaire == null ? null : Pattern.compile("^" + i18nGlobale.getString(I18n.var_Attribuer) + ": ([^\\.]+)\\.(.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
						boolean entiteAttribuerTrouve = entiteAttribuerRecherche == null ? false : entiteAttribuerRecherche.find();
						if(entiteAttribuerTrouve) {
							String entiteAttribuerNomSimple = entiteAttribuerRecherche.group(1);
							String entiteAttribuerVar = entiteAttribuerRecherche.group(2);
							String entiteAttribuerPageUriJs = null;

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
								Boolean entiteAttribuerEtendModeleBase = BooleanUtils.isTrue((Boolean)docClasse.get("classeEtendModeleBase_stored_boolean"));
								Boolean entiteAttribuerEtendResultatBase = BooleanUtils.isTrue((Boolean)docClasse.get("classeEtendResultatBase_stored_boolean"));
								String entiteAttribuerNomCanoniqueGenApiServiceImpl = (String)docClasse.get("classeNomCanoniqueGenApiServiceImpl_" + classeLangueNom + "_stored_string");
								String entiteAttribuerNomCanoniqueApiServiceImpl = (String)docClasse.get("classeNomCanoniqueApiServiceImpl_" + classeLangueNom + "_stored_string");
								String entiteAttribuerNomSimpleGenApiServiceImpl = (String)docClasse.get("classeNomSimpleGenApiServiceImpl_" + classeLangueNom + "_stored_string");
								String entiteAttribuerNomSimpleApiServiceImpl = (String)docClasse.get("classeNomSimpleApiServiceImpl_" + classeLangueNom + "_stored_string");
								entiteAttribuerPageUriJs = (String)docClasse.get("classePageUriJs_" + classeLangueNom + "_stored_string");

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
								rechercheSolrVar.addFilterQuery("entiteEstSubstitue_indexed_boolean:false");
								QueryResponse reponseRechercheVar = clientSolrComputate.query(rechercheSolrVar);
								SolrDocumentList listeRechercheVar = reponseRechercheVar.getResults();

								if(listeRechercheVar.size() > 0) {
									SolrDocument docEntite = listeRechercheVar.get(0);

									indexerStockerSolr(entiteDoc, "entiteAttribuer", true);
									indexerStockerSolr(entiteDoc, "entiteAttribuerAttribuer", BooleanUtils.isTrue((Boolean)docEntite.get("entiteAttribuer_stored_boolean")));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerNomSimple", entiteAttribuerNomSimple);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerNomCanonique", entiteAttribuerNomCanonique);
									indexerStockerSolr(entiteDoc, "entiteAttribuerEtendModeleBase", entiteAttribuerEtendModeleBase);
									indexerStockerSolr(entiteDoc, "entiteAttribuerEtendResultatBase", entiteAttribuerEtendResultatBase);
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
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarUrlPageAffichage", (String)docClasse.get("classeVarUrlPageAffichage_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarUrlPageEdition", (String)docClasse.get("classeVarUrlPageEdition_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarUrlPageUtilisateur", (String)docClasse.get("classeVarUrlPageUtilisateur_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarUrlTelechargement", (String)docClasse.get("classeVarUrlTelechargement_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarUrlApi", (String)docClasse.get("classeVarUrlApi_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarId", (String)docClasse.get("classeVarId_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarTitre", (String)docClasse.get("classeVarTitre_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarH1", (String)docClasse.get("classeVarH1_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarH2", (String)docClasse.get("classeVarH2_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarH3", (String)docClasse.get("classeVarH3_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerApiUri", (String)docClasse.get("classeApiUri_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerPageUri", (String)docClasse.get("classeApiUri" + i18nGlobale.getString(I18n.var_PageRecherche) + "_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarDescription", (String)docClasse.get("classeVarDescription_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVarImageUrl", (String)docClasse.get("classeVarImageUrl_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerContexteUnNom", (String)docClasse.get("classeUnNom_" + classeLangueNom + "_stored_string"));
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerContextePluriel", (String)docClasse.get("classePluriel_" + classeLangueNom + "_stored_string"));
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
									indexerStockerSolr(entiteDoc, "entiteAttribuerContexteCouleur", (String)docClasse.get("classeCouleur_stored_string"));
									indexerStockerSolr(entiteDoc, "entiteAttribuerContexteIcone", (String)docClasse.get("classeIcone_stored_string"));
									indexerStockerSolr(entiteDoc, "entiteAttribuerContexteRows", (Integer)docClasse.get("classeLignes_stored_int"));

									String entiteOperationIdPATCH = regexLangue(classeLangueNom, "(classe)?ApiOperationIdPATCH", classeCommentaire, "patch" + classeNomSimple);
									if(entiteOperationIdPATCH != null)
										indexerStockerSolr(classeLangueNom, entiteDoc, "entiteOperationIdPATCH", entiteOperationIdPATCH);

									String entiteAttribuerTypeJson = (String)docEntite.get("entiteTypeJson_stored_string");
									if(entiteAttribuerTypeJson != null)
										indexerStockerSolr(entiteDoc, "entiteAttribuerTypeJson", entiteAttribuerTypeJson);

									String entiteAttribuerOperationIdPATCH = (String)docClasse.get("classeApiOperationIdPATCH_" + classeLangueNom + "_stored_string");
									if(entiteAttribuerOperationIdPATCH != null)
										indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerOperationIdPATCH", entiteAttribuerOperationIdPATCH);
									String entiteAttribuerOperationIdRecherche = (String)docClasse.get("classeApiOperationId" + i18nGlobale.getString(I18n.var_Recherche) + "_" + classeLangueNom + "_stored_string");
									if(entiteAttribuerOperationIdRecherche != null)
										indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerOperationId" + i18nGlobale.getString(I18n.var_Recherche), entiteAttribuerOperationIdRecherche);

									String classeAttribuerNomSimplePage = (String)docClasse.get("classePageNomSimple" + i18nGlobale.getString(I18n.var_PageRecherche) + "_" + classeLangueNom + "_stored_string");
									if(classeAttribuerNomSimplePage != null) {
										if(!Optional.ofNullable(classeDoc.getFieldValues("classeAttribuerNomSimplePages_" + classeLangueNom + "_indexed_strings")).orElse(Arrays.asList()).contains(classeAttribuerNomSimplePage))
											indexerStockerListeSolr(classeLangueNom, classeDoc, "classeAttribuerNomSimplePages", classeAttribuerNomSimplePage);
										if(!Optional.ofNullable(classeDoc.getFieldValues("classeAttribuerNomSimple_" + classeLangueNom + "_indexed_strings")).orElse(Arrays.asList()).contains(entiteAttribuerNomSimple))
											indexerStockerListeSolr(classeLangueNom, classeDoc, "classeAttribuerNomSimple", entiteAttribuerNomSimple);
										if(!Optional.ofNullable(classeDoc.getFieldValues("classeAttribuerPageUriJs_" + classeLangueNom + "_indexed_strings")).orElse(Arrays.asList()).contains(entiteAttribuerPageUriJs))
											indexerStockerListeSolr(classeLangueNom, classeDoc, "classeAttribuerPageUriJs", entiteAttribuerPageUriJs);
									}

									if(classeTraduire) {
										for(String langueNom : classeAutresLangues) {  
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
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarUrlPageAffichage", (String)docClasse.get("classeVarUrlPageAffichage_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarUrlPageEdition", (String)docClasse.get("classeVarUrlPageEdition_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarUrlPageUtilisateur", (String)docClasse.get("classeVarUrlPageUtilisateur_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarUrlTelechargement", (String)docClasse.get("classeVarUrlTelechargement_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarUrlApi", (String)docClasse.get("classeVarUrlApi_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarId", (String)docClasse.get("classeVarId_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarTitre", (String)docClasse.get("classeVarTitre_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarH1", (String)docClasse.get("classeVarH1_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarH2", (String)docClasse.get("classeVarH2_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarH3", (String)docClasse.get("classeVarH3_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerApiUri", (String)docClasse.get("classeApiUri_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerPageUri", (String)docClasse.get("classeApiUri" + i18nGlobale.getString(I18n.var_PageRecherche) + "_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarDescription", (String)docClasse.get("classeVarDescription_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVarImageUrl", (String)docClasse.get("classeVarImageUrl_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerContexteUnNom", (String)docClasse.get("classeUnNom_" + langueNom + "_stored_string"));
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerContexteNomPluriel", (String)docClasse.get("classeNomPluriel_" + langueNom + "_stored_string"));
											
											entiteOperationIdPATCH = regexLangue(langueNom, "(classe)?ApiOperationIdPATCH", classeCommentaire, "patch" + classeNomSimpleLangue);
											if(entiteOperationIdPATCH != null)
												indexerStockerSolr(langueNom, entiteDoc, "entiteOperationIdPATCH", entiteOperationIdPATCH);

											entiteAttribuerOperationIdPATCH = (String)docClasse.get("classeApiOperationIdPATCH_" + langueNom + "_stored_string");

											classeAttribuerNomSimplePage = (String)docClasse.get("classePageNomSimple" + i18nGlobale.getString(I18n.var_PageRecherche) + "_" + langueNom + "_stored_string");
											if(classeAttribuerNomSimplePage != null) {
												if(!Optional.ofNullable(classeDoc.getFieldValues("classeAttribuerNomSimplePages_" + classeLangueNom + "_indexed_strings")).orElse(Arrays.asList()).contains(classeAttribuerNomSimplePage))
													indexerStockerListeSolr(langueNom, classeDoc, "classeAttribuerNomSimplePages", classeAttribuerNomSimplePage);
												if(!Optional.ofNullable(classeDoc.getFieldValues("classeAttribuerNomSimple_" + classeLangueNom + "_indexed_strings")).orElse(Arrays.asList()).contains(classeAttribuerNomSimplePage))
													indexerStockerListeSolr(langueNom, classeDoc, "classeAttribuerNomSimple", classeNomSimpleLangue);
											}

											if(entiteAttribuerOperationIdPATCH != null)
												indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerOperationIdPATCH", entiteAttribuerOperationIdPATCH);
											entiteAttribuerOperationIdRecherche = (String)docClasse.get("classeApiOperationId" + i18nGlobale.getString(I18n.var_Recherche) + "_" + langueNom + "_stored_string");
											if(entiteAttribuerOperationIdRecherche != null)
												indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerOperationId" + i18nGlobale.getString(I18n.var_Recherche), entiteAttribuerOperationIdRecherche);
										}
									}
								}
							}
						}
						
						for(JavaAnnotation annotation : annotations) {
							String entiteAnnotationLangue = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAnnotations", annotation.getType().getCanonicalName());
						}
	
						String entiteCle = solrCollectionComputate + " " + classeCheminAbsolu + "." + entiteVar;
		
						// Entites Solr du entite. 
		
						entiteDoc.addField(solrId, entiteCle);
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
								entiteNomSimpleVertxJson = "JsonObject";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonObject;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePath)) {
								entiteNomSimpleVertxJson = "JsonObject";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonObject;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePolygon)) {
								entiteNomSimpleVertxJson = "JsonObject";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonObject;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
								entiteNomSimpleVertxJson = "JsonObject";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonObject;
							}
							else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonArray)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
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
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePoint)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "JsonObject";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonObject;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePath)) {
									entiteNomSimpleVertxJson = "JsonArray";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
									entiteListeNomSimpleVertxJson = "JsonObject";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonObject;
								}
								else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon)) {
									entiteNomSimpleVertxJson = "JsonObject";
									entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonObject;
									entiteListeNomSimpleVertxJson = "JsonObject";
									entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonObject;
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
						String entiteSuffixeSolr = null;
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
							entiteSolrNomCanonique = VAL_nomCanoniqueString;
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
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePath)) {
							entiteSolrNomCanonique = VAL_nomCanoniquePath;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_location";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePolygon)) {
							entiteSolrNomCanonique = VAL_nomCanoniquePolygon;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_location";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueString;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_string";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonArray)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueString;
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
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueString + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueString, ".") + ">";
								entiteSuffixeType = "_strings";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueString + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueString, ".") + ">";
								entiteSuffixeType = "_dates";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueLong + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueLong, ".") + ">";
								entiteSuffixeType = "_longs";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePoint)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniquePoint + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniquePoint, ".") + ">";
								entiteSuffixeType = "_strings";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePath)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniquePath + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniquePath, ".") + ">";
								entiteSuffixeType = "_strings";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniquePolygon + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniquePolygon, ".") + ">";
								entiteSuffixeType = "_location";
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

						// entiteSuffixeSolr
						if(entiteCrypte) {
							entiteSuffixeSolr = String.format("_encrypted%s", entiteSuffixeType);
						} else if(entiteIncremente) {
							entiteSuffixeSolr = "_incremented";
						} else if(entiteSuggere) {
							entiteSuffixeSolr = "_suggested";
						} else if(entiteTexte) {
							entiteSuffixeSolr = String.format("_text%s", classeLangueNom);
						} else {
							entiteSuffixeSolr = String.format("%s%s",  (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType);
						}

						stockerSolr(entiteDoc, "entiteSolrNomCanonique", entiteSolrNomCanonique);
						stockerSolr(entiteDoc, "entiteSolrNomSimple", entiteSolrNomSimple);
						stockerSolr(entiteDoc, "entiteSuffixeType", entiteSuffixeType);
						stockerSolr(entiteDoc, "entiteSuffixeSolr", entiteSuffixeSolr);

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
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePath)) {
							entiteTypeSql = "path";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePolygon)) {
							entiteTypeSql = "polygon";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
							entiteTypeSql = "jsonb";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonArray)) {
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
								entiteTypeSql = "boolean[]";
								entiteListeTypeSql = "boolean";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLocalTime)) {
								entiteTypeSql = "timestamp[]";
								entiteListeTypeSql = "timestamp";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteTypeSql = "timestamp[] with time zone";
								entiteListeTypeSql = "timestamp with time zone";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteTypeSql = "bigint[]";
								entiteListeTypeSql = "bigint";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
								entiteTypeSql = "decimal[]";
								entiteListeTypeSql = "decimal";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
								entiteTypeSql = "double precision[]";
								entiteListeTypeSql = "double precision";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
								entiteTypeSql = "real[]";
								entiteListeTypeSql = "real";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
								entiteTypeSql = "integer[]";
								entiteListeTypeSql = "integer";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueString)) {
								entiteTypeSql = "text[]";
								entiteListeTypeSql = "text";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePoint)) {
								entiteTypeSql = "point[]";
								entiteListeTypeSql = "point";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePath)) {
								entiteTypeSql = "path[]";
								entiteListeTypeSql = "path";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon)) {
								entiteTypeSql = "Geometry(Polygon, 4326)";
								entiteListeTypeSql = "polygon";
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
						String entiteNgsiType = null;
						String entiteFiwareType = regex("^FiwareType: (.*)", methodeCommentaire);
						String entiteListeFiwareType = regex("^" + i18nGlobale.getString(I18n.var_Liste) + "FiwareType: (.*)", methodeCommentaire);
						String entiteFiwareContexte = indexerStockerSolr(entiteDoc, "entiteFiwareContexte", regex("^" + i18nGlobale.getString(I18n.var_FiwareContexte) + ": (.*)", methodeCommentaire));
						String entiteDocs = indexerStockerSolr(entiteDoc, "entiteDocs", regex("^Docs: (.*)", methodeCommentaire));
						String entiteNomAlternatif = indexerStockerSolr(entiteDoc, "entiteNomAlternatif", regex("^" + i18nGlobale.getString(I18n.var_NomAlternatif) + ": (.*)", methodeCommentaire));
						String entiteDescriptionAlternatif = indexerStockerSolr(entiteDoc, "entiteDescriptionAlternatif", regex("^" + i18nGlobale.getString(I18n.var_DescriptionAlternatif) + ": (.*)", methodeCommentaire));
						String entiteDocsAlternatif = indexerStockerSolr(entiteDoc, "entiteDocsAlternatif", regex("^" + i18nGlobale.getString(I18n.var_DocsAlternatif) + ": (.*)", methodeCommentaire));
						String entiteFormatJson = null;
						String entiteFormatHtm = regex("^FormatHtm: (.*)", methodeCommentaire);
						String entiteListeTypeJson = null;
						if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBoolean)) {
							entiteTypeJson = "boolean";
							if(entiteFiwareType == null)
								entiteFiwareType = "boolean";
							entiteNgsiType = "Property";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalTime)) {
							entiteTypeJson = "string";
							if(entiteFiwareType == null)
								entiteFiwareType = "string";
							entiteNgsiType = "Property";
							entiteFormatJson = "time";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "short";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
							entiteTypeJson = "string";
							if(entiteFiwareType == null)
								entiteFiwareType = "string";
							entiteNgsiType = "Property";
							if(entiteFormatHtm == null) {
								if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueTimestamp)) {
									entiteFormatHtm = "yyyy-MM-dd'T'HH:mm:ss.SSS";
//									entiteFormatJson = "date-time";
								}
								else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueLocalDateTime)) {
									entiteFormatHtm = "yyyy-MM-dd'T'HH:mm:ss.SSS";
//									entiteFormatJson = "date-time";
								}
								else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueDate)) {
									entiteFormatHtm = "yyyy-MM-dd'T'HH:mm:ss.SSS";
//									entiteFormatJson = "date";
								}
								else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueZonedDateTime)) {
									entiteFormatHtm = "yyyy-MM-dd'T'HH:mm:ss.SSS'['VV']'";
//									entiteFormatJson = "date-time";
								}
							}
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalDate)) {
							entiteTypeJson = "string";
							if(entiteFiwareType == null)
								entiteFiwareType = "string";
							entiteNgsiType = "Property";
							entiteFormatJson = "date";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "short";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
							entiteTypeJson = "string";
							if(entiteFiwareType == null)
								entiteFiwareType = "number";
							entiteNgsiType = "Property";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "integer";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePoint)) {
							entiteTypeJson = "object";
							if(entiteFiwareType == null)
								entiteFiwareType = "object";
							entiteNgsiType = "GeoProperty";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePath)) {
							entiteTypeJson = "object";
							if(entiteFiwareType == null)
								entiteFiwareType = "object";
							entiteNgsiType = "GeoProperty";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniquePolygon)) {
							entiteTypeJson = "object";
							if(entiteFiwareType == null)
								entiteFiwareType = "object";
							entiteNgsiType = "GeoProperty";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
							entiteTypeJson = "object";
							if(entiteFiwareType == null)
								entiteFiwareType = "object";
							entiteNgsiType = "Property";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueVertxJsonArray)) {
							entiteTypeJson = "array";
							if(entiteFiwareType == null)
								entiteFiwareType = "array";
							entiteNgsiType = "Property";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
							entiteTypeJson = "string";
							if(entiteFiwareType == null)
								entiteFiwareType = "number";
							entiteNgsiType = "Property";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueDouble)) {
							entiteTypeJson = "string";
							if(entiteFiwareType == null)
								entiteFiwareType = "number";
							entiteNgsiType = "Property";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueFloat)) {
							entiteTypeJson = "string";
							if(entiteFiwareType == null)
								entiteFiwareType = "number";
							entiteNgsiType = "Property";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "default";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueInteger)) {
							entiteTypeJson = "string";
							if(entiteFiwareType == null)
								entiteFiwareType = "number";
							entiteNgsiType = "Property";
							if(entiteFormatHtm == null)
								entiteFormatHtm = "integer";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
							if(entiteNomCanoniqueGenerique.equals(VAL_nomCanoniqueBoolean)) {
								entiteTypeJson = "array";
								if(entiteFiwareType == null)
									entiteFiwareType = "array";
								entiteListeFiwareType = "boolean";
								entiteNgsiType = "Property";
								entiteListeTypeJson = "boolean";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLocalTime)) {
								entiteTypeJson = "array";
								if(entiteFiwareType == null)
									entiteFiwareType = "array";
								entiteListeFiwareType = "string";
								entiteNgsiType = "Property";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteTypeJson = "array";
								if(entiteFiwareType == null)
									entiteFiwareType = "array";
								entiteListeFiwareType = "string";
								entiteNgsiType = "Property";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteTypeJson = "array";
								if(entiteFiwareType == null)
									entiteFiwareType = "array";
								entiteListeFiwareType = "string";
								entiteNgsiType = "Property";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
								entiteTypeJson = "array";
								if(entiteFiwareType == null)
									entiteFiwareType = "array";
								entiteListeFiwareType = "string";
								entiteNgsiType = "Property";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
								entiteTypeJson = "array";
								if(entiteFiwareType == null)
									entiteFiwareType = "array";
								entiteListeFiwareType = "string";
								entiteNgsiType = "Property";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
								entiteTypeJson = "array";
								if(entiteFiwareType == null)
									entiteFiwareType = "array";
								entiteListeFiwareType = "string";
								entiteNgsiType = "Property";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
								entiteTypeJson = "array";
								if(entiteFiwareType == null)
									entiteFiwareType = "array";
								entiteListeFiwareType = "string";
								entiteNgsiType = "Property";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon)) {
								entiteTypeJson = "object";
								if(entiteFiwareType == null)
									entiteFiwareType = "object";
								entiteNgsiType = "GeoProperty";
								if(entiteFormatHtm == null)
									entiteFormatHtm = "default";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueString)) {
								entiteTypeJson = "array";
								if(entiteFiwareType == null)
									entiteFiwareType = "array";
								entiteListeFiwareType = "string";
								entiteNgsiType = "Property";
								entiteListeTypeJson = "string";
							} else {
								entiteTypeJson = "array";
							}
							stockerSolr(entiteDoc, "entiteListeTypeJson", entiteListeTypeJson);
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueString)) {
							if(entiteFiwareType == null)
								entiteFiwareType = "string";
							entiteNgsiType = "Property";
							entiteTypeJson = "string";
						}
						indexerStockerSolr(entiteDoc, "entiteTypeJson", entiteTypeJson);

						indexerStockerSolr(entiteDoc, "entiteNgsiType", entiteNgsiType);
						indexerStockerSolr(entiteDoc, "entiteFiwareType", entiteFiwareType);
						indexerStockerSolr(entiteDoc, "entiteListeFiwareType", entiteListeFiwareType);

						String entiteNgsiType2 = regex("^NgsiType: (.*)", methodeCommentaire);
						if(entiteNgsiType2 != null)
							entiteNgsiType = entiteNgsiType2;
						String entiteFiwareType2 = regex("^NgsiType: (.*)", methodeCommentaire);
						if(entiteFiwareType2 != null)
							entiteFiwareType = entiteFiwareType2;
						String entiteListeFiwareType2 = regex("^ListFiwareType: (.*)", methodeCommentaire);
						if(entiteListeFiwareType2 != null)
							entiteListeFiwareType = entiteListeFiwareType2;

						if(entiteFormatJson != null)
							stockerSolr(entiteDoc, "entiteFormatJson", entiteFormatJson);
						indexerStockerSolr(entiteDoc, "entiteFormatHtm", entiteFormatHtm);

						if(entiteClePrimaire) {
							classeVarClePrimaire = stockerSolr(classeLangueNom, classeDoc, "classeVarClePrimaire", entiteVar);
							classeVarClePrimaireSuffixeSolr = stockerSolr(classeDoc, "classeVarClePrimaireSuffixeSolr", entiteSuffixeSolr);
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
						if(entiteZone) {
							classeVarZone = stockerSolr(classeLangueNom, classeDoc, "classeVarZone", entiteVar);
						}
						if(entiteEmplacement) {
							classeVarEmplacement = stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacement", entiteVar);
						}
						if(entiteEmplacementCouleur) {
							classeVarEmplacementCouleur = stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacementCouleur", entiteVar);
						}
						if(entiteEmplacementTitre) {
							classeVarEmplacementTitre = stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacementTitre", entiteVar);
						}
						if(entiteEmplacementUrl) {
							classeVarEmplacementUrl = stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacementUrl", entiteVar);
						}
						if(entiteEmplacementSvg) {
							classeVarEmplacementSvg = stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacementSvg", entiteVar);
						}
						if(entiteEmplacementRayon) {
							classeVarEmplacementRayon = stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacementRayon", entiteVar);
						}
						if(entiteAire) {
							classeVarAire = stockerSolr(classeLangueNom, classeDoc, "classeVarAire", entiteVar);
						}
						if(entiteAireCouleur) {
							classeVarAireCouleur = stockerSolr(classeLangueNom, classeDoc, "classeVarAireCouleur", entiteVar);
						}
						if(entiteAireTitre) {
							classeVarAireTitre = stockerSolr(classeLangueNom, classeDoc, "classeVarAireTitre", entiteVar);
						}
						if(entiteAireUrl) {
							classeVarAireUrl = stockerSolr(classeLangueNom, classeDoc, "classeVarAireUrl", entiteVar);
						}
						if(entiteSuggere && entiteVar.equals(i18nGlobale.getString(I18n.var_objetSuggere))) {
							classeVarSuggere = stockerSolr(classeLangueNom, classeDoc, "classeVarSuggere", entiteVar);
						}
						if(entiteTexte && entiteVar.equals(i18nGlobale.getString(I18n.var_objetTexte))) {
							classeVarTexte = stockerSolr(classeLangueNom, classeDoc, "classeVarTexte", entiteVar);
						}
						if(entiteVarUrlPageAffichage) {
							classeVarUrlPageAffichage = stockerSolr(classeLangueNom, classeDoc, "classeVarUrlPageAffichage", entiteVar);
						}
						if(entiteVarUrlPageEdition) {
							classeVarUrlPageEdition = stockerSolr(classeLangueNom, classeDoc, "classeVarUrlPageEdition", entiteVar);
						}
						if(entiteVarUrlPageUtilisateur) {
							classeVarUrlPageUtilisateur = stockerSolr(classeLangueNom, classeDoc, "classeVarUrlPageUtilisateur", entiteVar);
						}
						if(entiteVarUrlTelechargement) {
							classeVarUrlTelechargement = stockerSolr(classeLangueNom, classeDoc, "classeVarUrlTelechargement", entiteVar);
						}
						if(entiteVarUrlApi) {
							classeVarUrlApi = stockerSolr(classeLangueNom, classeDoc, "classeVarUrlApi", entiteVar);
						}
						if(entiteVarId) {
							classeVarId = stockerSolr(classeLangueNom, classeDoc, "classeVarId", entiteVar);
							classeVarIdSuffixeSolr = stockerSolr(classeDoc, "classeVarIdSuffixeSolr", entiteSuffixeSolr);
						}
						if(entiteVarNom) {
							classeVarNom = stockerSolr(classeLangueNom, classeDoc, "classeVarNom", entiteVar);
							classeVarNomSuffixeSolr = stockerSolr(classeDoc, "classeVarNomSuffixeSolr", entiteSuffixeSolr);
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
								if(entiteSuggere && entiteVarLangue.equals(i18nGlobale.getString(I18n.var_objetSuggere))) {
									stockerSolr(langueNom, classeDoc, "classeVarSuggere", entiteVarLangue);
								}
								if(entiteTexte && entiteVarLangue.equals(i18nGlobale.getString(I18n.var_objetTexte))) {
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
								if(entiteVarUrlPageAffichage) {
									classeVarUrlPageAffichage = stockerSolr(langueNom, classeDoc, "classeVarUrlPageAffichage", entiteVarLangue);
								}
								if(entiteVarUrlPageEdition) {
									classeVarUrlPageEdition = stockerSolr(langueNom, classeDoc, "classeVarUrlPageEdition", entiteVarLangue);
								}
								if(entiteVarUrlPageUtilisateur) {
									classeVarUrlPageUtilisateur = stockerSolr(langueNom, classeDoc, "classeVarUrlPageUtilisateur", entiteVarLangue);
								}
								if(entiteVarUrlTelechargement) {
									classeVarUrlTelechargement = stockerSolr(langueNom, classeDoc, "classeVarUrlTelechargement", entiteVarLangue);
								}
								if(entiteVarUrlApi) {
									classeVarUrlApi = stockerSolr(langueNom, classeDoc, "classeVarUrlApi", entiteVarLangue);
								}
								if(entiteVarId) {
									classeVarId = stockerSolr(langueNom, classeDoc, "classeVarId", entiteVarLangue);
								}
								if(entiteVarNom) {
									classeVarNom = stockerSolr(langueNom, classeDoc, "classeVarNom", entiteVarLangue);
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
	
						String methodeCle = solrCollectionComputate + " " + classeCheminAbsolu + "." + methodeVar + "(";
						for(int i = 0; i < methodeParamsQdox.size(); i++) {
							JavaParameter paramQdox = methodeParamsQdox.get(i);
							if(i > 0)
								methodeCle += ", ";
							methodeCle += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
						}
						methodeCle += ")";  
		
						// Methodes Solr du methode. 
		
						methodeDoc.addField(solrId, methodeCle);
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
	
						regexListe("^" + classeLangueNom + ": ([^\n]+)", methodeCommentaire);

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
						indexerStockerSolr(classeLangueNom, methodeDoc, "methodeNomAffichage", regexLangue(classeLangueNom, "^" + i18nGlobale.getString(I18n.var_NomAffichage), methodeCommentaire));

						indexerStockerSolr(methodeDoc, "methodeLigneDebut", methodeQdox.getLineNumber() - (methodeCommentaire == null ? 0 : (methodeCommentaire.split(REGEX_LIGNE).length + 1)));
						indexerStockerSolr(methodeDoc, "methodeLigneFin", methodeQdox.getLineNumber() - (methodeCodeSource == null ? 0 : (methodeCodeSource.split(REGEX_LIGNE).length - 1)));

						if(classeTraduire) {
							for(String langueNom : classeAutresLangues) {  
								methodeVarLangue = regex("^Var\\." + langueNom + ": ([^\n]+)", methodeCommentaire);
								methodeVarLangue = indexerStockerSolr(langueNom, methodeDoc, "methodeVar", methodeVarLangue == null ? methodeVar : methodeVarLangue);
								regexListe("^" + langueNom + ": ([^\n]+)", methodeCommentaire);
								methodeCodeSourceLangue = regexRemplacerTout(methodeCommentaire, methodeCodeSource, langueNom);
								indexerStockerListeSolr(langueNom, classeDoc, "classeMethodeVars", methodeVarLangue);
								String methodeStringLangue = regex("^String\\." + langueNom + ":(.*)", methodeCommentaire);
								if(methodeStringLangue != null) {
									methodeCodeSourceLangue = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodeStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
									indexerStockerSolr(langueNom, methodeDoc, "methodeString", methodeStringLangue); 
								}
								stockerSolr(langueNom, methodeDoc, "methodeCodeSource", methodeCodeSourceLangue);
								stockerRegexCommentaires(langueNom, methodeDoc, "methodeCommentaire", methodeCommentaire);
								indexerStockerSolr(langueNom, methodeDoc, "methodeNomAffichage", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAffichage), methodeCommentaire));
							} 
						}

						if(methodeCommentaire != null) {
							String motif = "^Val(:([^:\\n]+):)?\\.(\\w+)(\\.([^:\\n]+))?: ([>|-]{0,2}(\\d*)\\n)?([\\s\\S]*?)(?=\\n^\\w|\\Z)";
							Matcher methodeValsRecherche = Pattern.compile(motif, Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean methodeValsTrouves = methodeValsRecherche.find();
							while(methodeValsTrouves) {
								String methodeValVar = methodeValsRecherche.group(3);
								String methodeValLangue = methodeValsRecherche.group(5);
								String methodeValCode = methodeValsRecherche.group(2);
								String methodeValValeur = null;
								String groupe1 = methodeValsRecherche.group(6);
								String groupe2 = methodeValsRecherche.group(7);
								String groupe3 = methodeValsRecherche.group(8);
								Integer spaces = 2;
								if(groupe2 != null && groupe2.length() > 0)
									spaces = Integer.parseInt(groupe2);
								methodeValValeur = groupe3.replaceAll("^" + String.join("", Collections.nCopies(spaces, " ")), "").replaceAll("\n" + String.join("", Collections.nCopies(spaces, " ")), "\n");

								if(groupe1 != null && groupe1.contains(">"))
									methodeValValeur = methodeValValeur.replaceAll("\\n([^\\n])", " $1");

								if(groupe1 != null && groupe1.contains("-"))
									methodeValValeur = methodeValValeur.replaceAll("\\n+\\Z", "");
								else if(groupe1 != null && !groupe1.contains("+"))
									methodeValValeur = methodeValValeur.replaceAll("\\n\\Z", "");

								if(methodeValCode == null)
									methodeValCode = "";
								if(methodeValLangue == null) {
									stockerListeSolr(methodeDoc, "methodeValsVar", methodeValVar);
									stockerListeSolr(methodeDoc, "methodeValsLangue", classeLangueNom);
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
			classeVarClePrimaireSuffixeSolr = (String)classeSuperDoc.get("classeVarClePrimaireSuffixeSolr_stored_string");
			if(classeVarClePrimaire != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarClePrimaire", classeVarClePrimaire);
				stockerSolr(classeDoc, "classeVarClePrimaireSuffixeSolr", classeVarClePrimaireSuffixeSolr);
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
			if(classeVarInheritClePrimaire == null) {
				if(classeVarId != null) {
					classeVarInheritClePrimaire = classeVarId;
				} else if(classeEtendResultatBase) {
					classeVarInheritClePrimaire = classeVarCleUnique;
				} else if(classeEtendModeleBase) {
					classeVarInheritClePrimaire = classeVarClePrimaire;
				}
			}
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

		if(classeVarZone == null && classeSuperDoc != null) {
			classeVarZone = (String)classeSuperDoc.get("classeVarZone_" + classeLangueNom + "_stored_string");
			if(classeVarZone != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarZone", classeVarZone);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarZoneLangue = (String)classeSuperDoc.get("classeVarZone_" + langueNom + "_stored_string");
						if(classeVarZoneLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarZone", classeVarZoneLangue);
						}
					}
				}
			}
		}

		if(classeVarEmplacement == null && classeSuperDoc != null) {
			classeVarEmplacement = (String)classeSuperDoc.get("classeVarEmplacement_" + classeLangueNom + "_stored_string");
			if(classeVarEmplacement != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacement", classeVarEmplacement);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarEmplacementLangue = (String)classeSuperDoc.get("classeVarEmplacement_" + langueNom + "_stored_string");
						if(classeVarEmplacementLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarEmplacement", classeVarEmplacementLangue);
						}
					}
				}
			}
		}
		if(classeVarEmplacementCouleur == null && classeSuperDoc != null) {
			classeVarEmplacementCouleur = (String)classeSuperDoc.get("classeVarEmplacementCouleur_" + classeLangueNom + "_stored_string");
			if(classeVarEmplacementCouleur != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacementCouleur", classeVarEmplacementCouleur);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarEmplacementCouleurLangue = (String)classeSuperDoc.get("classeVarEmplacementCouleur_" + langueNom + "_stored_string");
						if(classeVarEmplacementCouleurLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarEmplacementCouleur", classeVarEmplacementCouleurLangue);
						}
					}
				}
			}
		}
		if(classeVarEmplacementTitre == null && classeSuperDoc != null) {
			classeVarEmplacementTitre = (String)classeSuperDoc.get("classeVarEmplacementTitre_" + classeLangueNom + "_stored_string");
			if(classeVarEmplacementTitre != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacementTitre", classeVarEmplacementTitre);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarEmplacementTitreLangue = (String)classeSuperDoc.get("classeVarEmplacementTitre_" + langueNom + "_stored_string");
						if(classeVarEmplacementTitreLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarEmplacementTitre", classeVarEmplacementTitreLangue);
						}
					}
				}
			}
		}
		if(classeVarEmplacementUrl == null && classeSuperDoc != null) {
			classeVarEmplacementUrl = (String)classeSuperDoc.get("classeVarEmplacementUrl_" + classeLangueNom + "_stored_string");
			if(classeVarEmplacementUrl != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarEmplacementUrl", classeVarEmplacementUrl);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarEmplacementUrlLangue = (String)classeSuperDoc.get("classeVarEmplacementUrl_" + langueNom + "_stored_string");
						if(classeVarEmplacementUrlLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarEmplacementUrl", classeVarEmplacementUrlLangue);
						}
					}
				}
			}
		}

		if(classeVarAire == null && classeSuperDoc != null) {
			classeVarAire = (String)classeSuperDoc.get("classeVarAire_" + classeLangueNom + "_stored_string");
			if(classeVarAire != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarAire", classeVarAire);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarAireLangue = (String)classeSuperDoc.get("classeVarAire_" + langueNom + "_stored_string");
						if(classeVarAireLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarAire", classeVarAireLangue);
						}
					}
				}
			}
		}
		if(classeVarAireCouleur == null && classeSuperDoc != null) {
			classeVarAireCouleur = (String)classeSuperDoc.get("classeVarAireCouleur_" + classeLangueNom + "_stored_string");
			if(classeVarAireCouleur != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarAireCouleur", classeVarAireCouleur);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarAireCouleurLangue = (String)classeSuperDoc.get("classeVarAireCouleur_" + langueNom + "_stored_string");
						if(classeVarAireCouleurLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarAireCouleur", classeVarAireCouleurLangue);
						}
					}
				}
			}
		}
		if(classeVarAireTitre == null && classeSuperDoc != null) {
			classeVarAireTitre = (String)classeSuperDoc.get("classeVarAireTitre_" + classeLangueNom + "_stored_string");
			if(classeVarAireTitre != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarAireTitre", classeVarAireTitre);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarAireTitreLangue = (String)classeSuperDoc.get("classeVarAireTitre_" + langueNom + "_stored_string");
						if(classeVarAireTitreLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarAireTitre", classeVarAireTitreLangue);
						}
					}
				}
			}
		}
		if(classeVarAireUrl == null && classeSuperDoc != null) {
			classeVarAireUrl = (String)classeSuperDoc.get("classeVarAireUrl_" + classeLangueNom + "_stored_string");
			if(classeVarAireUrl != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarAireUrl", classeVarAireUrl);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarAireUrlLangue = (String)classeSuperDoc.get("classeVarAireUrl_" + langueNom + "_stored_string");
						if(classeVarAireUrlLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarAireUrl", classeVarAireUrlLangue);
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

		if(classeVarUrlPageAffichage == null && classeSuperDoc != null) {
			classeVarUrlPageAffichage = (String)classeSuperDoc.get("classeVarUrlPageAffichage_" + classeLangueNom + "_stored_string");
			if(classeVarUrlPageAffichage != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarUrlPageAffichage", classeVarUrlPageAffichage);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarUrlPageAffichageLangue = (String)classeSuperDoc.get("classeVarUrlPageAffichage_" + langueNom + "_stored_string");
						if(classeVarUrlPageAffichageLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarUrlPageAffichage", classeVarUrlPageAffichageLangue);
						}
					}
				}
			}
		}
		if(classeVarUrlPageEdition == null && classeSuperDoc != null) {
			classeVarUrlPageEdition = (String)classeSuperDoc.get("classeVarUrlPageEdition_" + classeLangueNom + "_stored_string");
			if(classeVarUrlPageEdition != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarUrlPageEdition", classeVarUrlPageEdition);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarUrlPageEditionLangue = (String)classeSuperDoc.get("classeVarUrlPageEdition_" + langueNom + "_stored_string");
						if(classeVarUrlPageEditionLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarUrlPageEdition", classeVarUrlPageEditionLangue);
						}
					}
				}
			}
		}
		if(classeVarUrlPageUtilisateur == null && classeSuperDoc != null) {
			classeVarUrlPageUtilisateur = (String)classeSuperDoc.get("classeVarUrlPageUtilisateur_" + classeLangueNom + "_stored_string");
			if(classeVarUrlPageUtilisateur != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarUrlPageUtilisateur", classeVarUrlPageUtilisateur);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarUrlPageUtilisateurLangue = (String)classeSuperDoc.get("classeVarUrlPageUtilisateur_" + langueNom + "_stored_string");
						if(classeVarUrlPageUtilisateurLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarUrlPageUtilisateur", classeVarUrlPageUtilisateurLangue);
						}
					}
				}
			}
		}
		if(classeVarUrlTelechargement == null && classeSuperDoc != null) {
			classeVarUrlTelechargement = (String)classeSuperDoc.get("classeVarUrlTelechargement_" + classeLangueNom + "_stored_string");
			if(classeVarUrlTelechargement != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarUrlTelechargement", classeVarUrlTelechargement);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarUrlTelechargementLangue = (String)classeSuperDoc.get("classeVarUrlTelechargement_" + langueNom + "_stored_string");
						if(classeVarUrlTelechargementLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarUrlTelechargement", classeVarUrlTelechargementLangue);
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
			classeVarIdSuffixeSolr = (String)classeSuperDoc.get("classeVarIdSuffixeSolr_stored_string");
			if(classeVarId != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarId", classeVarId);
				stockerSolr(classeDoc, "classeVarIdSuffixeSolr", classeVarIdSuffixeSolr);
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
		if(classeVarNom == null && classeSuperDoc != null) {
			classeVarNom = (String)classeSuperDoc.get("classeVarNom_" + classeLangueNom + "_stored_string");
			classeVarNomSuffixeSolr = (String)classeSuperDoc.get("classeVarNomSuffixeSolr_stored_string");
			if(classeVarNom != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarNom", classeVarNom);
				stockerSolr(classeDoc, "classeVarNomSuffixeSolr", classeVarNomSuffixeSolr);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarNomLangue = (String)classeSuperDoc.get("classeVarNom_" + langueNom + "_stored_string");
						if(classeVarNomLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarNom", classeVarNomLangue);
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

		String classeNomSimpleLangue = (String)classeDoc.get("classeNomSimple_" + langueNomGlobale + "_stored_string").getValue();
		Boolean classeAuth = false;
		String classePageNomSimple = null;

		if(classePage) {
			classePageNomSimple = classeNomSimpleLangue + i18nGlobale.getString(I18n.var_Page);
			String classePageSuperNomSimple = regexLangue(langueNomGlobale, "^" + i18nGlobale.getString(I18n.var_PageSuper), classeCommentaire, Optional.ofNullable(classePartsMiseEnPage).map(c -> c.nomSimple(classeLangueNom)).orElse(null));
			String classeNomEnsembleLangue = (String)classeDoc.get("classeNomEnsemble_" + langueNomGlobale + "_indexed_string").getValue();
			String classePageNomCanonique = (String)classeDoc.get("classeNomCanonique_" + langueNomGlobale + "_stored_string").getValue() + i18nGlobale.getString(I18n.var_Page);
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageNomCanonique", classePageNomCanonique);
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageNomSimple", classePageNomSimple);

			String classeGenPageNomSimple;
			String classeGenPageChemin;
			if(StringUtils.contains(classePageNomSimple, "Page")) {
				classeGenPageNomSimple = StringUtils.substringBeforeLast(classePageNomSimple, "Page") + "GenPage" + StringUtils.substringAfterLast(classePageNomSimple, "Page");
				classeGenPageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
			}
			else {
				classeGenPageNomSimple = "Gen" + classePageNomSimple;
				classeGenPageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
			}
			indexerStockerSolr(langueNomGlobale, classeDoc, "classeGenPageNomSimple", classeGenPageNomSimple);
			String classeGenPageNomCanonique = classeNomEnsembleLangue + "." + classeGenPageNomSimple;
			indexerStockerSolr(langueNomGlobale, classeDoc, "classeGenPageNomCanonique", classeGenPageNomCanonique);
			String classePageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimple, ".java");
			indexerStockerSolr(langueNomGlobale, classeDoc, "classeGenPageChemin", classeGenPageChemin); 
			indexerStockerSolr(langueNomGlobale, classeDoc, "classePageChemin", classePageChemin); 
			// String classePageTemplate = indexerStockerSolr(langueNomGlobale, classeDoc, "classePageTemplate", concat(langueNomGlobale.substring(0, 2), "-", langueNomGlobale.substring(2, 4).toLowerCase(), "/", classePageNomSimple, ".htm"));
			// String classePageCheminJinja = indexerStockerSolr(langueNomGlobale, classeDoc, "classePageCheminJinja", concat(siteChemin, "/src/main/resources/templates/", classePageTemplate));
			// String classeGenPageTemplate = indexerStockerSolr(langueNomGlobale, classeDoc, "classeGenPageTemplate", concat(langueNomGlobale.substring(0, 2), "-", langueNomGlobale.substring(2, 4).toLowerCase(), "/", classeGenPageNomSimple, ".htm"));
			// String classeGenPageCheminJinja = indexerStockerSolr(langueNomGlobale, classeDoc, "classeGenPageCheminJinja", concat(siteChemin, "/src/main/resources/templates/", classeGenPageTemplate));
			// String classePageSuperTemplate = indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperTemplate", concat(langueNomGlobale.substring(0, 2), "-", langueNomGlobale.substring(2, 4).toLowerCase(), "/", classePageSuperNomSimple, ".htm"));
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
					String classePageSuperPageTemplate = (String)docPageSuper.get("classePageTemplate_" + classeLangueNom + "_stored_string");
					indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperNomCanonique", classePageSuperNomCanoniqueMethode);
					indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperNomSimple", classePageSuperNomSimple);
					indexerStockerSolr(langueNomGlobale, classeDoc, "classePageSuperPageTemplate", classePageSuperPageTemplate);
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
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.time.temporal.ChronoUnit", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Duration.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Instant.class.getCanonicalName(), classeLangueNom), classeLangueNom);
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
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Objects.class.getCanonicalName(), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Promise", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, classePartsConfigCles.nomCanonique(classeLangueNom), classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "org.computate.search.response.solr.SolrResponse", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.util.HashMap", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "org.computate.search.tool.TimeTool", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "org.computate.search.tool.SearchTool", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", classeLangueNom), classeLangueNom);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.pgclient.data.Point", classeLangueNom), classeLangueNom);
	
			for(ClasseParts classePartGenPage : classePartsGenPage.values()) {
				indexerStockerListeSolr(langueNomGlobale, classeDoc, "classeImportationsGenPage", classePartGenPage.nomCanonique(classeLangueNom));
			}
		}

		String classeSmartDataModelStr = regex("^SmartDataModel:\\s(.*)", classeCommentaire);

		indexerStockerSolr(classeDoc, "classeGenere", regexTrouve("^" + i18nGlobale.getString(I18n.str_Genere) + ": \\s*(true)$", classeCommentaire));
		Boolean classeContexte = indexerStockerSolr(classeDoc, "classeContexte", regexTrouve("^" + i18nGlobale.getString(I18n.var_Contexte) + ": \\s*(true)$", classeCommentaire) || classePage || classeSmartDataModelStr != null);

		if(classeContexte) {
			classeCouleur = regex("^" + i18nGlobale.getString(I18n.var_Couleur) + ": (.*)", classeCommentaire);
			if(classeCouleur != null)
				indexerStockerSolr(classeDoc, "classeCouleur", classeCouleur); 

			classeIcone = regex("^" + i18nGlobale.getString(I18n.var_Icone) + ": (.*)", classeCommentaire);
			if(classeIcone != null)
				indexerStockerSolr(classeDoc, "classeIcone", classeIcone); 

			classeEmplacementSvg = regex("^" + i18nGlobale.getString(I18n.var_Emplacement) + "Svg: (.*)", classeCommentaire);
			if(classeEmplacementSvg != null)
				indexerStockerSolr(classeDoc, "classeEmplacementSvg", classeEmplacementSvg); 

			String classeEmplacementRayonStr = regex("^" + i18nGlobale.getString(I18n.var_Emplacement) + i18nGlobale.getString(I18n.var_Rayon) + ": (.*)", classeCommentaire);
			if(NumberUtils.isParsable(classeEmplacementRayonStr))
				classeLignes = indexerStockerSolr(classeDoc, "classeEmplacementRayon", Integer.parseInt(classeEmplacementRayonStr)); 

			String classeLignesStr = regex("^" + i18nGlobale.getString(I18n.var_Lignes) + ": (.*)", classeCommentaire);
			if(NumberUtils.isParsable(classeLignesStr))
				classeLignes = indexerStockerSolr(classeDoc, "classeLignes", Integer.parseInt(classeLignesStr)); 

			for(String langueNom : toutesLangues) {

				classeVideoId = regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_VideoId), classeCommentaire);
				if(classeVideoId != null)
					indexerStockerSolr(langueNom, classeDoc, "classeVideoId", classeVideoId); 

				classeUri = regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Uri), classeCommentaire);
				if(classeUri != null)
					indexerStockerSolr(langueNom, classeDoc, "classeUri", classeUri); 

				classeDescription = regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Description), classeCommentaire);
				if(classeDescription != null)
					indexerStockerSolr(langueNom, classeDoc, "classeDescription", classeDescription); 

				String classeImageLargeurStr = regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_ImageLargeur), classeCommentaire);
				if(NumberUtils.isCreatable(classeImageLargeurStr))
					indexerStockerSolr(langueNom, classeDoc, "classeImageLargeur", Integer.parseInt(classeImageLargeurStr));

				String classeImageHauteurStr = regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_ImageHauteur), classeCommentaire);
				if(NumberUtils.isCreatable(classeImageHauteurStr))
					indexerStockerSolr(langueNom, classeDoc, "classeImageHauteur", Integer.parseInt(classeImageHauteurStr));
					
				classeUnNom = regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_UnNom), classeCommentaire);
				if(classeUnNom != null) {
					if("frFR".equals(langueNom)) {
						indexerStockerSolr(langueNom, classeDoc, "classeUnNom", classeUnNom); 
						classeNomSingulier = indexerStockerSolr(langueNom, classeDoc, "classeNomSingulier", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomSingulier), classeCommentaire, StringUtils.substringAfter(classeUnNom, " ")));
						classeNomPluriel = indexerStockerSolr(langueNom, classeDoc, "classeNomPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomPluriel), classeCommentaire, classeNomSingulier + "s"));
						String classeApiUri = regexLangue(langueNom, "^ApiUri", classeCommentaire);
						classeLesNoms = indexerStockerSolr(langueNom, classeDoc, "classeLesNoms", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LesNoms), classeCommentaire, CONTEXTE_frFR_LesPluriel + classeNomPluriel));
		
						classeAdjectif = regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Adjectif), classeCommentaire);
						if(classeAdjectif != null) {
							classeAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "classeAdjectifPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_AdjectifPluriel), classeCommentaire, classeAdjectif + CONTEXTE_frFR_AdjectifPluriel));
							classeAdjectifVar = indexerStockerSolr(langueNom, classeDoc, "classeAdjectifVar", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_AdjectifVar), classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(classeAdjectif, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
							if(CONTEXTE_frFR_AdjectifAvant) {
								classeNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifSingulier", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifSingulier), classeCommentaire, classeAdjectif + " " + classeNomSingulier));
								classeNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifPluriel), classeCommentaire, classeAdjectifPluriel + " " + classeNomPluriel));
							}
							else {
								classeNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifSingulier", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifSingulier), classeCommentaire, classeNomSingulier + " " + classeAdjectif));
								classeNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "classeNomSingulierAdjectifPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomSingulierAdjectifPluriel), classeCommentaire, classeNomPluriel + " " + classeAdjectifPluriel));
							}
						}
						else {
							classeNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifSingulier", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifSingulier), classeCommentaire, classeNomSingulier));
							classeNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifPluriel), classeCommentaire, classeNomPluriel));
						}
		
						if(classeUnNom.startsWith(CONTEXTE_frFR_UneFeminin)) {
							classeCe = indexerStockerSolr(langueNom, classeDoc, "classeCe", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Ce), classeCommentaire, CONTEXTE_frFR_CetteFemininConsonne));
							classeUn = indexerStockerSolr(langueNom, classeDoc, "classeUn", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Un), classeCommentaire, CONTEXTE_frFR_UneFeminin));
							classeCree = indexerStockerSolr(langueNom, classeDoc, "classeCree", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Cree), classeCommentaire, CONTEXTE_frFR_CreeeFeminin));
							classeModifie = indexerStockerSolr(langueNom, classeDoc, "classeModifie", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Modifie), classeCommentaire, CONTEXTE_frFR_ModifieeFeminin));
							classeNomActuel = indexerStockerSolr(langueNom, classeDoc, "classeNomActuel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomActuel), classeCommentaire, CONTEXTE_frFR_ActuelleFemininAvant + classeNomSingulier + CONTEXTE_frFR_ActuelleFemininApres));
							classeTous = indexerStockerSolr(langueNom, classeDoc, "classeTous", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Tous), classeCommentaire, CONTEXTE_frFR_ToutesFemininPluriel));
							classeTousNom = indexerStockerSolr(langueNom, classeDoc, "classeTousNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_TousNom), classeCommentaire, CONTEXTE_frFR_ToutesFemininPluriel + classeNomPluriel));
							classeRechercherTousNomPar = indexerStockerSolr(langueNom, classeDoc, "classeRechercherTousNomPar", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_RechercherTousNomPar), classeCommentaire, CONTEXTE_frFR_Rechercher + classeNomPluriel + CONTEXTE_frFR_Par));
							classeRechercherTousNom = indexerStockerSolr(langueNom, classeDoc, "classeRechercherTousNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_RechercherTousNom), classeCommentaire, CONTEXTE_frFR_Rechercher + classeNomPluriel));
							classeAucunNomTrouve = indexerStockerSolr(langueNom, classeDoc, "classeAucunNomTrouve", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_AucunNomTrouve), classeCommentaire, CONTEXTE_frFR_AucuneTrouveFemininAvant + classeNomSingulier + CONTEXTE_frFR_AucuneTrouveFemininApres));
							if(classeAdjectif != null) {
								if(CONTEXTE_frFR_AdjectifAvant)
									classeUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeUnNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_UnNomAdjectif), classeCommentaire, CONTEXTE_frFR_UneFeminin + classeAdjectif + " " + classeNomSingulier));
								else
									classeUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeUnNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_UnNomAdjectif), classeCommentaire, CONTEXTE_frFR_UneFeminin + classeNomSingulier + " " + classeAdjectif));
							}
		
							String suffixe = StringUtils.substringAfter(classeUnNom, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								classeCeNom = indexerStockerSolr(langueNom, classeDoc, "classeCeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_CeNom), classeCommentaire, CONTEXTE_frFR_CetteFemininVoyelle + suffixe));
								classeLeNom = indexerStockerSolr(langueNom, classeDoc, "classeLeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNom), classeCommentaire, CONTEXTE_frFR_LFemininVoyelle + suffixe));
								classeDeNom = indexerStockerSolr(langueNom, classeDoc, "classeDeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_DeNom), classeCommentaire, CONTEXTE_frFR_DVoyelle + suffixe));
								if(classeAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant) {
										classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_frFR_LFemininVoyelle + classeAdjectif + " " + classeNomSingulier));
									}
									else {
										classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_frFR_LFemininVoyelle + classeNomSingulier + " " + classeAdjectif));
									}
								}
							}
							else {
								classeCeNom = indexerStockerSolr(langueNom, classeDoc, "classeCeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_CeNom), classeCommentaire, CONTEXTE_frFR_CetteFemininConsonne + suffixe));
								classeLeNom = indexerStockerSolr(langueNom, classeDoc, "classeLeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNom), classeCommentaire, CONTEXTE_frFR_LaFemininConsonne + suffixe));
								classeDeNom = indexerStockerSolr(langueNom, classeDoc, "classeDeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_DeNom), classeCommentaire, CONTEXTE_frFR_DeConsonne + suffixe));
								if(classeAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant) {
										classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_frFR_LaFemininConsonne + classeAdjectif + " " + classeNomSingulier));
									}
									else {
										classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_frFR_LaFemininConsonne + classeNomSingulier + " " + classeAdjectif));
									}
								}
							}
						}
						else if(classeUnNom.startsWith(CONTEXTE_frFR_UnMasculin)) {
							classeCe = indexerStockerSolr(langueNom, classeDoc, "classeCe", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Ce), classeCommentaire, CONTEXTE_frFR_CeMasculinConsonne));
							classeUn = indexerStockerSolr(langueNom, classeDoc, "classeUn", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Un), classeCommentaire, CONTEXTE_frFR_UnMasculin));
							classeCree = indexerStockerSolr(langueNom, classeDoc, "classeCree", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Cree), classeCommentaire, CONTEXTE_frFR_CreeMasculin));
							classeModifie = indexerStockerSolr(langueNom, classeDoc, "classeModifie", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Modifie), classeCommentaire, CONTEXTE_frFR_ModifieMasculin));
							classeNomActuel = indexerStockerSolr(langueNom, classeDoc, "classeNomActuel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomActuel), classeCommentaire, CONTEXTE_frFR_ActuelMasculinAvant + classeNomSingulier + CONTEXTE_frFR_ActuelMasculinApres));
							classeTous = indexerStockerSolr(langueNom, classeDoc, "classeTous", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_TousNom), classeCommentaire, CONTEXTE_frFR_TousMasculinPluriel));
							classeTousNom = indexerStockerSolr(langueNom, classeDoc, "classeTousNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_TousNom), classeCommentaire, CONTEXTE_frFR_TousMasculinPluriel + classeNomPluriel));
							classeRechercherTousNomPar = indexerStockerSolr(langueNom, classeDoc, "classeRechercherTousNomPar", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_RechercherTousNomPar), classeCommentaire, CONTEXTE_frFR_Rechercher + classeNomPluriel + CONTEXTE_frFR_Par));
							classeRechercherTousNom = indexerStockerSolr(langueNom, classeDoc, "classeRechercherTousNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_RechercherTousNom), classeCommentaire, CONTEXTE_frFR_Rechercher + classeNomPluriel));
							classeAucunNomTrouve = indexerStockerSolr(langueNom, classeDoc, "classeAucunNomTrouve", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_AucunNomTrouve), classeCommentaire, CONTEXTE_frFR_AucunTrouveMasculinAvant + classeNomSingulier + CONTEXTE_frFR_AucunTrouveMasculinApres));
							if(classeAdjectif != null) {
								if(CONTEXTE_frFR_AdjectifAvant)
									classeUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeUnNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_UnNomAdjectif), classeCommentaire, CONTEXTE_frFR_UnMasculin + classeAdjectif + " " + classeNomSingulier));
								else
									classeUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeUnNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_UnNomAdjectif), classeCommentaire, CONTEXTE_frFR_UnMasculin + classeNomSingulier + " " + classeAdjectif));
							}
		
							String suffixe = StringUtils.substringAfter(classeUnNom, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								classeCeNom = indexerStockerSolr(langueNom, classeDoc, "classeCeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_CeNom), classeCommentaire, CONTEXTE_frFR_CetMasculinVoyelle + suffixe));
								classeLeNom = indexerStockerSolr(langueNom, classeDoc, "classeLeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNom), classeCommentaire, CONTEXTE_frFR_LMasculinVoyelle + suffixe));
								classeDeNom = indexerStockerSolr(langueNom, classeDoc, "classeDeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_DeNom), classeCommentaire, CONTEXTE_frFR_DVoyelle + suffixe));
								if(classeAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant) {
										classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_frFR_LMasculinVoyelle + classeAdjectif + " " + classeNomSingulier));
									}
									else {
										classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_frFR_LMasculinVoyelle + classeNomSingulier + " " + classeAdjectif));
									}
								}
							}
							else {
								classeCeNom = indexerStockerSolr(langueNom, classeDoc, "classeCeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_CeNom), classeCommentaire, CONTEXTE_frFR_CeMasculinConsonne + suffixe));
								classeLeNom = indexerStockerSolr(langueNom, classeDoc, "classeLeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNom), classeCommentaire, CONTEXTE_frFR_LeMasculinConsonne + suffixe));
								classeDeNom = indexerStockerSolr(langueNom, classeDoc, "classeDeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_DeNom), classeCommentaire, CONTEXTE_frFR_DeConsonne + suffixe));
								if(classeAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant) {
										classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_frFR_LeMasculinConsonne + classeAdjectif + " " + classeNomSingulier));
									}
									else {
										classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_frFR_LeMasculinConsonne + classeNomSingulier + " " + classeAdjectif));
									}
								}
							}
						}
						indexerStockerSolr(langueNom, classeDoc, "classeCeMinuscule", classeCe); 
					}
					else if("enUS".equals(langueNom)) {
						indexerStockerSolr(langueNom, classeDoc, "classeUnNom", classeUnNom); 
						classeNomSingulier = indexerStockerSolr(langueNom, classeDoc, "classeNomSingulier", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomSingulier), classeCommentaire, StringUtils.substringAfter(classeUnNom, " ")));
						classeNomPluriel = indexerStockerSolr(langueNom, classeDoc, "classeNomPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomPluriel), classeCommentaire, classeNomSingulier + "s"));
						String classeApiUri = regexLangue(langueNom, "^ApiUri", classeCommentaire);
						classeLesNoms = indexerStockerSolr(langueNom, classeDoc, "classeLesNoms", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LesNoms), classeCommentaire, CONTEXTE_enUS_LesPluriel + classeNomPluriel));
		
						classeAdjectif = regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Adjectif), classeCommentaire);
						if(classeAdjectif != null) {
							classeAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "classeAdjectifPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_AdjectifPluriel), classeCommentaire, classeAdjectif + CONTEXTE_enUS_AdjectifPluriel));
							classeAdjectifVar = indexerStockerSolr(langueNom, classeDoc, "classeAdjectifVar", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_AdjectifVar), classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(classeAdjectif, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
							if(CONTEXTE_enUS_AdjectifAvant) {
								classeNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifSingulier", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifSingulier), classeCommentaire, classeAdjectif + " " + classeNomSingulier));
								classeNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifPluriel), classeCommentaire, classeAdjectifPluriel + " " + classeNomPluriel));
							}
							else {
								classeNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifSingulier", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifSingulier), classeCommentaire, classeNomSingulier + " " + classeAdjectif));
								classeNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "classeNomSingulierAdjectifPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomSingulierAdjectifPluriel), classeCommentaire, classeNomPluriel + " " + classeAdjectifPluriel));
							}
						}
						else {
							classeNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifSingulier", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifSingulier), classeCommentaire, classeNomSingulier));
							classeNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "classeNomAdjectifPluriel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomAdjectifPluriel), classeCommentaire, classeNomPluriel));
						}
		
						classeCe = indexerStockerSolr(langueNom, classeDoc, "classeCe", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Ce), classeCommentaire, CONTEXTE_enUS_CetteConsonne));
						classeUn = indexerStockerSolr(langueNom, classeDoc, "classeUn", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Un), classeCommentaire, CONTEXTE_enUS_UnConsonne));
						classeCree = indexerStockerSolr(langueNom, classeDoc, "classeCree", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Cree), classeCommentaire, CONTEXTE_enUS_Creee));
						classeModifie = indexerStockerSolr(langueNom, classeDoc, "classeModifie", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Modifie), classeCommentaire, CONTEXTE_enUS_Modifiee));
						classeNomActuel = indexerStockerSolr(langueNom, classeDoc, "classeNomActuel", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_NomActuel), classeCommentaire, CONTEXTE_enUS_ActuelleAvant + classeNomSingulier + CONTEXTE_enUS_ActuelleApres));
						classeTous = indexerStockerSolr(langueNom, classeDoc, "classeTous", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_TousNom), classeCommentaire, CONTEXTE_enUS_ToutesPluriel));
						classeTousNom = indexerStockerSolr(langueNom, classeDoc, "classeTousNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_TousNom), classeCommentaire, CONTEXTE_enUS_ToutesPluriel + classeNomPluriel));
						classeRechercherTousNomPar = indexerStockerSolr(langueNom, classeDoc, "classeRechercherTousNomPar", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_RechercherTousNomPar), classeCommentaire, CONTEXTE_enUS_Rechercher + classeNomPluriel + CONTEXTE_enUS_Par));
						classeRechercherTousNom = indexerStockerSolr(langueNom, classeDoc, "classeRechercherTousNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_RechercherTousNom), classeCommentaire, CONTEXTE_enUS_Rechercher + classeNomPluriel));
						classeAucunNomTrouve = indexerStockerSolr(langueNom, classeDoc, "classeAucunNomTrouve", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_AucunNomTrouve), classeCommentaire, CONTEXTE_enUS_AucuneTrouveAvant + classeNomSingulier + CONTEXTE_enUS_AucuneTrouveApres));
	
						String suffixe = StringUtils.substringAfter(classeUnNom, " ");
						String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);

						if(classeAdjectif != null) {
							if(StringUtils.containsAny(classeAdjectif.substring(0, 1), 'a', 'e', 'i', 'o', 'u', 'h'))
								classeUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeUnNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_UnNomAdjectif), classeCommentaire, CONTEXTE_enUS_UnVoyelle + classeAdjectif + " " + classeNomSingulier));
							else
								classeUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeUnNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_UnNomAdjectif), classeCommentaire, CONTEXTE_enUS_UnConsonne + classeAdjectif + " " + classeNomSingulier));
						}
						else {
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h'))
								classeUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeUnNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_UnNomAdjectif), classeCommentaire, CONTEXTE_enUS_UnVoyelle + classeNomSingulier));
							else
								classeUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeUnNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_UnNomAdjectif), classeCommentaire, CONTEXTE_enUS_UnConsonne + classeNomSingulier));
						}

						if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h')) {
							classeCeNom = indexerStockerSolr(langueNom, classeDoc, "classeCeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_CeNom), classeCommentaire, CONTEXTE_enUS_CetteVoyelle + suffixe));
							classeLeNom = indexerStockerSolr(langueNom, classeDoc, "classeLeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNom), classeCommentaire, CONTEXTE_enUS_LVoyelle + suffixe));
							classeDeNom = indexerStockerSolr(langueNom, classeDoc, "classeDeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_DeNom), classeCommentaire, CONTEXTE_enUS_DVoyelle + suffixe));
							if(classeAdjectif != null) {
								if(CONTEXTE_enUS_AdjectifAvant) {
									classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_enUS_LVoyelle + classeAdjectif + " " + classeNomSingulier));
								}
								else {
									classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_enUS_LVoyelle + classeNomSingulier + " " + classeAdjectif));
								}
							}
						}
						else {
							classeCeNom = indexerStockerSolr(langueNom, classeDoc, "classeCeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_CeNom), classeCommentaire, CONTEXTE_enUS_CetteConsonne + suffixe));
							classeLeNom = indexerStockerSolr(langueNom, classeDoc, "classeLeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNom), classeCommentaire, CONTEXTE_enUS_LaConsonne + suffixe));
							classeDeNom = indexerStockerSolr(langueNom, classeDoc, "classeDeNom", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_DeNom), classeCommentaire, CONTEXTE_enUS_DeConsonne + suffixe));
							if(classeAdjectif != null) {
								if(CONTEXTE_enUS_AdjectifAvant) {
									classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_enUS_LaConsonne + classeAdjectif + " " + classeNomSingulier));
								}
								else {
									classeLeNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "classeLeNomAdjectif", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_LeNomAdjectif), classeCommentaire, CONTEXTE_enUS_LaConsonne + classeNomSingulier + " " + classeAdjectif));
								}
							}
						}
						indexerStockerSolr(langueNom, classeDoc, "classeCeMinuscule", classeCe); 
					}
				}
	
				classeTitre = regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_Titre), classeCommentaire, classeNomPluriel);
				if(classeTitre != null)
					indexerStockerSolr(langueNom, classeDoc, "classeTitre", classeTitre); 
	
				indexerStockerSolr(classeLangueNom, classeDoc, "classeHtmInfobulle", regexYamlString(i18nGlobale.getString(I18n.var_HtmInfobulle), classeCommentaire));
				indexerStockerSolr(classeLangueNom, classeDoc, "classeJsInfobulle", regexYamlString(i18nGlobale.getString(I18n.var_JsInfobulle), classeCommentaire));
				indexerStockerSolr(classeLangueNom, classeDoc, "classeJsPATCH", regexYamlString("JsPATCH", classeCommentaire));
				indexerStockerSolr(classeLangueNom, classeDoc, "classeJsWebsocket", regexYamlString("JsWebsocket", classeCommentaire));
	
				classeH1 = regexLangue(langueNom, "^H1", classeCommentaire);
				if(classeH1 != null)
					indexerStockerSolr(langueNom, classeDoc, "classeH1", classeH1); 
	
				classeH2 = regexLangue(langueNom, "^H2", classeCommentaire);
				if(classeH2 != null)
					indexerStockerSolr(langueNom, classeDoc, "classeH2", classeH2); 
	
				classeH3 = regexLangue(langueNom, "^H3", classeCommentaire);
				if(classeH3 != null)
					indexerStockerSolr(langueNom, classeDoc, "classeH3", classeH3); 
			}
		}

		if(classeApi) {

			String classePageRechercheTemplate = null;
			for(String langueNom : toutesLangues) {
				String classeApiUri = indexerStockerSolrRegex(langueNom, classeDoc, "classeApiUri", "ApiUri", classeCommentaire);
				String classeApiTag = indexerStockerSolrRegex(langueNom, classeDoc, "classeApiTag", "ApiTag", classeCommentaire, classeNomAdjectifPluriel);

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
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Vertx", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.eventbus.EventBus", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.pgclient.PgPool", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.kafka.client.producer.KafkaProducer", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.mqtt.MqttClient", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.amqp.AmqpSender", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.rabbitmq.RabbitMQClient", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.impl.JsonUtil", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.authorization.AuthorizationProvider", classeLangueNom), classeLangueNom);
				if(classePage)
					classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "com.hubspot.jinjava.Jinjava", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.eventbus.DeliveryOptions", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.IOException", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Collections", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Map", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.concurrent.TimeUnit", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.Instant", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.Duration", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.temporal.ChronoUnit", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.computate.search.response.solr.SolrResponse.StatsField", classeLangueNom), classeLangueNom);
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
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "com.hubspot.jinjava.Jinjava", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.nio.file.Path", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.nio.file.Files", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "com.google.common.io.Resources", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.nio.charset.StandardCharsets", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.computate.vertx.request.ComputateSiteRequest", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.computate.vertx.config.ComputateConfigKeys", classeLangueNom), classeLangueNom);
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
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpResponseExpectation", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.nio.charset.Charset", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.authorization.RoleBasedAuthorization", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.service.ServiceRequest", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.service.ServiceResponse", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.client.HttpResponse", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.client.predicate.ResponsePredicate", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.HashMap", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.User", classeLangueNom), classeLangueNom);
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.authentication.UsernamePasswordCredentials", classeLangueNom), classeLangueNom);
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
				classePartsGenApiAjouter(ClasseParts.initClasseParts(this, BooleanUtils.class.getCanonicalName(), classeLangueNom), classeLangueNom);
				if(classePartsUtilisateurSite == null)
					System.err.println(String.format("%s %s %s %s %s. ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_UtilisateurSite), i18nGlobale.getString(I18n.var_manquante), i18nGlobale.getString(I18n.var_dans), cheminSrcMainJava));
//				else
//					classePartsGenApiAjouter(ClasseParts.initClasseParts(this, classePartsUtilisateurSite.nomCanonique(classeLangueNom) + StringUtils.capitalize(classeLangueNom) + "ApiServiceImpl", classeLangueNom), classeLangueNom);

				classePartsGenApiAjouter(classePartsListeRecherche, classeLangueNom);

				if(classeRoleUtilisateur) {
					classeAuth = true;
				}
				JsonObject classeAuthClientDefautObjet = regexYamlObject(i18nGlobale.getString(I18n.var_AuthClientDefaut), classeCommentaire);
				if(classeAuthClientDefautObjet != null) {
					classeAuth = true;
					for(String portee : classeAuthClientDefautObjet.fieldNames()) {
						indexerStockerListeSolr(langueNom, classeDoc, "classeAuthClientPortees", portee); 
					}
				}

				JsonObject classeAuthGroupeObjet = regexYamlObject(i18nGlobale.getString(I18n.var_AuthGroupe), classeCommentaire);
				if(classeAuthGroupeObjet != null) {
					classeAuth = true;
					for(String classeAuthGroupe : classeAuthGroupeObjet.fieldNames()) {
						JsonObject groupePortees = Optional.ofNullable(classeAuthGroupeObjet.getJsonObject(classeAuthGroupe)).orElse(new JsonObject());
						indexerStockerListeSolr(langueNom, classeDoc, "classeAuthGroupes", classeAuthGroupe); 
						for(String portee : groupePortees.fieldNames()) {
							indexerStockerListeSolr(langueNom, classeDoc, String.format("classeAuthPortees_%s", classeAuthGroupe), portee); 
						}
					}
				}

				if(classeAuth) {
					indexerStockerSolr(langueNom, classeDoc, "classeAuthRessource", regexLangue(langueNom, "^" + i18nGlobale.getString(I18n.var_AuthRessource), classeCommentaire, StringUtils.upperCase(classeNomSimple)));
				}

				JsonObject apiMethodeObjet = regexYamlObject(i18nGlobale.getString(I18n.var_ApiMethode), classeCommentaire);

				if(classeUriPageRecherche != null && !apiMethodeObjet.containsKey(i18nGlobale.getString(I18n.var_PageRecherche))) {
					apiMethodeObjet.put(i18nGlobale.getString(I18n.var_PageRecherche), new JsonObject()
							.put(i18nGlobale.getString(I18n.var_ApiUri), classeUriPageRecherche)
							.put(i18nGlobale.getString(I18n.var_Page), classePageNomSimple)
					);
				}
				if(classeUriPageEdition != null && !apiMethodeObjet.containsKey(i18nGlobale.getString(I18n.var_PageEdition))) {
					apiMethodeObjet.put(i18nGlobale.getString(I18n.var_PageEdition), new JsonObject()
							.put(i18nGlobale.getString(I18n.var_ApiUri), classeUriPageEdition)
							.put(i18nGlobale.getString(I18n.var_Page), classePageNomSimple)
					);
				}
				if(classeUriPageAffichage != null && !apiMethodeObjet.containsKey(i18nGlobale.getString(I18n.var_PageAffichage))) {
					apiMethodeObjet.put(i18nGlobale.getString(I18n.var_PageAffichage), new JsonObject()
							.put(i18nGlobale.getString(I18n.var_ApiUri), classeUriPageAffichage)
							.put(i18nGlobale.getString(I18n.var_Page), classePageNomSimple)
					);
				}
				if(classeUriPageUtilisateur != null && !apiMethodeObjet.containsKey(i18nGlobale.getString(I18n.var_PageUtilisateur))) {
					apiMethodeObjet.put(i18nGlobale.getString(I18n.var_PageUtilisateur), new JsonObject()
							.put(i18nGlobale.getString(I18n.var_ApiUri), classeUriPageUtilisateur)
							.put(i18nGlobale.getString(I18n.var_Page), classePageNomSimple)
					);
				}
				if(classeUriTelechargement != null && !apiMethodeObjet.containsKey(i18nGlobale.getString(I18n.var_Telechargement))) {
					apiMethodeObjet.put(i18nGlobale.getString(I18n.var_Telechargement), new JsonObject()
							.put(i18nGlobale.getString(I18n.var_ApiUri), classeUriTelechargement)
					);
				}

				if(apiMethodeObjet.fieldNames().contains("DELETE")) {
					apiMethodeObjet.put("DELETE" + i18nGlobale.getString(I18n.var_Filtre), new JsonObject()
						.put(i18nGlobale.getString(I18n.var_ApiUri), classeApiUri)
					);
				}
				for(String classeApiMethode : apiMethodeObjet.fieldNames()) {
					JsonObject apiMethode = Optional.ofNullable(apiMethodeObjet.getJsonObject(classeApiMethode)).orElse(new JsonObject());
					String classeApiMethodeLangue = apiMethode.getString(i18nGlobale.getString(I18n.var_Langue));
					Boolean classePageAvecTemplateMethode = false;
	
					if(classeApiMethodeLangue == null || StringUtils.equals(classeApiMethodeLangue, langueNom)) {

						indexerStockerListeSolr(langueNom, classeDoc, "classeApiMethodes", classeApiMethode); 
		
						String classeApiMethodeMethode;
						Boolean classeApiMethodeSecurite = true;
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

						if(classeRoleSession) {
							if(StringUtils.equalsAny(classeApiMethodeMethode, "GET", "POST", "PATCH"))
								classeApiMethodeSecurite = false;
						}
						if("GET".equals(classeApiMethodeMethode) && classePublicLire)
							classeApiMethodeSecurite = false;
						
						if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageAffichage)))
							classeApiMethodeSecurite = false;
						else if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageEdition)))
							classeApiMethodeSecurite = true;
						else if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageUtilisateur)))
							classeApiMethodeSecurite = true;
						else if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_Telechargement)))
							classeApiMethodeSecurite = true;
		
						classeApiMethodeMethode = indexerStockerSolr(langueNom, classeDoc, "classeApiMethode" + classeApiMethode, apiMethode.getString(i18nGlobale.getString(I18n.var_ApiMethode), classeApiMethodeMethode));
						indexerStockerSolr(classeDoc, "classeApiSecurite" + classeApiMethode, classeApiMethodeSecurite);
		
						String classeApiUriMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_ApiUri));
						String classePageMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_Page));
						Boolean classeRoleUtilisateurMethode = indexerStockerSolr(langueNom, classeDoc, "classeRoleUtilisateur" + classeApiMethode, apiMethode.getBoolean(i18nGlobale.getString(I18n.var_AuthUtilisateur), true));
		
						indexerStockerSolr(langueNom, classeDoc, "classeApiOperationId" + classeApiMethode, apiMethode.getString(i18nGlobale.getString(I18n.var_ApiOperationId), StringUtils.lowerCase(classeApiMethode) + classeNomSimpleLangue));
						indexerStockerSolr(langueNom, classeDoc, "classeApiOperationId" + classeApiMethode + "Requete", apiMethode.getString(i18nGlobale.getString(I18n.var_ApiOperationIdRequete), classeApiMethode + classeNomSimpleLangue + i18nGlobale.getString(I18n.var_Requete)));
						indexerStockerSolr(langueNom, classeDoc, "classeApiOperationId" + classeApiMethode + "Reponse", apiMethode.getString(i18nGlobale.getString(I18n.var_ApiOperationIdReponse), classeApiMethode + classeNomSimpleLangue + i18nGlobale.getString(I18n.var_Reponse)));
						indexerStockerSolr(langueNom, classeDoc, "classeApiDescription" + classeApiMethode, apiMethode.getString(i18nGlobale.getString(I18n.var_ApiDescription), classeDescription));
						indexerStockerSolr(langueNom, classeDoc, "classeApiInterne" + classeApiMethode, apiMethode.getBoolean(i18nGlobale.getString(I18n.var_ApiInterne), true));
		
						if(classeEtendBase && classeSuperDoc != null) {
							indexerStockerSolr(langueNom, classeDoc, "classeSuperApiOperationId" + classeApiMethode, (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string"));
							indexerStockerSolr(langueNom, classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Requete", (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Requete" + "_" + langueNom + "_stored_string"));
							indexerStockerSolr(langueNom, classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Reponse", (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Reponse" + "_" + langueNom + "_stored_string"));
						}
		
						String classePageNomSimpleMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_Page));
						String classePageSuperNomSimpleMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_PageSuper), "Object");
						String classeApiTypeMediaRequeteMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_ApiTypeMediaRequete), "application/json");
						String classeApiTypeMedia200Methode = apiMethode.getString(i18nGlobale.getString(I18n.var_ApiTypeMedia) + "200", classePageNomSimpleMethode == null ? "application/json" : "text/html");
						String classeApiMotCleMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_ApiMotCle));
						if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageRecherche))
								|| StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageEdition))
								|| StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageAffichage))
								|| StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageUtilisateur))
								) {
							classePageAvecTemplate = true;
							classePageAvecTemplateMethode = true;
							if(StringUtils.isBlank(classeApiMotCleMethode))
								classeApiMotCleMethode = StringUtils.substringAfterLast(classeApiUriMethode, "/");

							if(classePageMethode != null) {
								if(classeApiUriMethode == null) {
									if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageRecherche)))
										classeApiUriMethode = classeApiUri;
									else if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageEdition)))
										classeApiUriMethode = classeApiUri;
									else if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageAffichage)))
										classeApiUriMethode = classeApiUri;
									else if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_PageUtilisateur)))
										classeApiUriMethode = classeApiUri;
								}
								String classePageTemplateMethode = null;
								if(
										classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition))
										|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageRecherche))
										) {
									String classePageNomFichier = String.format("%s%s.htm", classeNomSimple, classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition)) ? i18nGlobale.getString(I18n.var_PageEdition) : i18nGlobale.getString(I18n.var_PageRecherche));
									String classeGenPageNomFichier = String.format("%sGen%s.htm", classeNomSimple, classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition)) ? i18nGlobale.getString(I18n.var_PageEdition) : i18nGlobale.getString(I18n.var_PageRecherche));

									if(classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition)))
										classePageTemplateMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_PageTemplate), String.format("%s/%s", StringUtils.substringBeforeLast(classeApiUriMethode.substring(1), "/"), classePageNomFichier));
									else
										classePageTemplateMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_PageTemplate), String.format("%s/%s", classeApiUriMethode.substring(1), classePageNomFichier));

									if(classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageRecherche))) {
										classePageRechercheTemplate = classePageTemplateMethode;
									}
									if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PageRecherche))) {
										String classePageUriCss = indexerStockerSolr(langueNomGlobale, classeDoc, "classePageUriCss", concat("/css/", StringUtils.substringBeforeLast(classePageTemplateMethode, "/"), "/", classeNomSimple, ".css"));
										String classePageUriJs = indexerStockerSolr(langueNomGlobale, classeDoc, "classePageUriJs", concat("/js/", StringUtils.substringBeforeLast(classePageTemplateMethode, "/"), "/", classeNomSimple, ".js"));
										String classePageUriJsRecherche = indexerStockerSolr(langueNomGlobale, classeDoc, "classePageUriJsRecherche", concat("/js/", StringUtils.substringBeforeLast(classePageTemplateMethode, "/"), "/", classeNomSimple, i18nGlobale.getString(I18n.var_PageRecherche), ".js"));
										String classePageUriJsEdition = indexerStockerSolr(langueNomGlobale, classeDoc, "classePageUriJsEdition", concat("/js/", StringUtils.substringBeforeLast(classePageTemplateMethode, "/"), "/", classeNomSimple,  i18nGlobale.getString(I18n.var_PageEdition), ".js"));

										indexerStockerSolr(langueNomGlobale, classeDoc, "classePageCheminCss", concat(cheminStatique, classePageUriCss));
										indexerStockerSolr(langueNomGlobale, classeDoc, "classePageCheminJs", concat(cheminStatique, classePageUriJs));
										indexerStockerSolr(langueNomGlobale, classeDoc, "classePageCheminJsRecherche", concat(cheminStatique, classePageUriJsRecherche));
										indexerStockerSolr(langueNomGlobale, classeDoc, "classePageCheminJsEdition", concat(cheminStatique, classePageUriJsEdition));
									}

									String classeGenPageTemplateMethode;
									if(classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition)))
										classeGenPageTemplateMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_PageTemplate), String.format("%s/%s", StringUtils.substringBeforeLast(classeApiUriMethode.substring(1), "/"), classeGenPageNomFichier));
									else
										classeGenPageTemplateMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_PageTemplate), String.format("%s/%s", classeApiUriMethode.substring(1), classeGenPageNomFichier));

									String classeGenPageTemplate = indexerStockerSolr(langueNomGlobale, classeDoc, String.format("classeGen%sTemplate", classeApiMethode), classeGenPageTemplateMethode);
									indexerStockerSolr(langueNomGlobale, classeDoc, String.format("classeGen%sCheminJinja", classeApiMethode), String.format("%s/%s", templateChemin, classeGenPageTemplate));
								} else {
									String classePageNomFichier = String.format("%s%s.htm", classeNomSimple, classeApiMethode);
									// classePageTemplateMethode = apiMethode.getString(i18nGlobale.getString(I18n.var_PageTemplate), String.format("%s/%s", StringUtils.substringBeforeLast(classeApiUriMethode.substring(1), "/"), classePageNomFichier));
									classePageTemplateMethode = "%s.htm";
								}

								indexerStockerSolr(langueNomGlobale, classeDoc, String.format("classe%sTemplate", classeApiMethode), classePageTemplateMethode);
								indexerStockerSolr(langueNomGlobale, classeDoc, String.format("classe%sCheminJinja", classeApiMethode), String.format("%s/%s", templateChemin, classePageTemplateMethode));
							}
						}
						else {
							if(StringUtils.isBlank(classeApiMotCleMethode))
								classeApiMotCleMethode = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(classeApiUriMethode, "/"), "/");
							if(StringUtils.isBlank(classeApiUriMethode)) {
								if("PUTImport".equals(classeApiMethode))
									classeApiUriMethode = classeApiUri + "-import";
								else if(i18nGlobale.getString(I18n.var_PUTCopie).equals(classeApiMethode))
									classeApiUriMethode = classeApiUri + "/" + i18nGlobale.getString(I18n.var_copie);
								else if(i18nGlobale.getString(I18n.var_PUTFusion).equals(classeApiMethode))
									classeApiUriMethode = classeApiUri + "/" + i18nGlobale.getString(I18n.var_fusion);
								else if(StringUtils.contains(classeApiMethode, "POST"))
									classeApiUriMethode = classeApiUri;
								else if(StringUtils.contains(classeApiMethode, "PATCH"))
									classeApiUriMethode = classeApiUri;
								else if(StringUtils.contains(classeApiMethode, "GET"))
									classeApiUriMethode = classeApiUri + "/{" + classeVarId + "}";
								else if(StringUtils.contains(classeApiMethode, "DELETE"))
									classeApiUriMethode = classeApiUri + "/{" + classeVarId + "}";
								else if(StringUtils.contains(classeApiMethode, i18nGlobale.getString(I18n.var_Recherche)))
									classeApiUriMethode = classeApiUri;
							}
						}

						if(this.langueNomGlobale.equals(classeLangueNom)) {
							indexerStockerSolr(langueNom, classeDoc, "classeApiTypeMedia200" + classeApiMethode, classeApiTypeMedia200Methode);
							if(StringUtils.contains(classeApiMethode, "POST")
									|| StringUtils.contains(classeApiMethode, "PATCH")
									|| StringUtils.contains(classeApiMethode, "PUT")
									|| StringUtils.contains(classeApiMethode, "DELETE")
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
	
								String classeGenPageNomSimple;
								String classeGenPageChemin;
								if(StringUtils.contains(classePageNomSimpleMethode, "Page")) {
									classeGenPageNomSimple = StringUtils.substringBeforeLast(classePageNomSimpleMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classePageNomSimpleMethode, "Page");
									classeGenPageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
								}
								else {
									classeGenPageNomSimple = "Gen" + classePageNomSimpleMethode;
									classeGenPageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
								}
								indexerStockerSolr(langueNom, classeDoc, "classeGenPageNomSimple" + classeApiMethode, classeGenPageNomSimple);
								String classeGenPageNomCanonique = classeNomEnsembleLangue + "." + classeGenPageNomSimple;
								indexerStockerSolr(langueNom, classeDoc, "classeGenPageNomCanonique" + classeApiMethode, classeGenPageNomCanonique);
								String classePageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimpleMethode, ".java");
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

								if(StringUtils.contains(classePageNomSimpleMethode, "Page")) {
									classeGenPageNomSimple = StringUtils.substringBeforeLast(classePageNomSimpleMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classePageNomSimpleMethode, "Page");
									classeGenPageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
									classePageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimpleMethode, ".java");
								}
								else {
									classeGenPageNomSimple = "Gen" + classePageNomSimpleMethode;
									classeGenPageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
									classePageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimpleMethode, ".java");
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
									String classePageSuperTemplate = (String)docPageSuper.get("classePageTemplate_" + classePageLangueNom + "_stored_string");

									indexerStockerSolr(langueNom, classeDoc, "classePageSuperNomCanonique" + classeApiMethode, classePageSuperNomCanoniqueMethode);
									indexerStockerSolr(langueNom, classeDoc, "classePageSuperNomSimple" + classeApiMethode, classePageSuperNomSimpleMethode);
									indexerStockerSolr(langueNom, classeDoc, "classePageSuperTemplate" + classeApiMethode, classePageSuperTemplate);
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
							// String classePageCheminJinja = concat(siteChemin, "-static/template/", langueNom.substring(0, 2), "-", langueNom.substring(2, 4).toLowerCase(), "/", classePageNomSimpleMethode, ".htm");
				
							indexerStockerSolr(langueNom, classeDoc, "classePageCheminCss" + classeApiMethode, classePageCheminCss); 
							indexerStockerSolr(langueNom, classeDoc, "classePageCheminJs" + classeApiMethode, classePageCheminJs); 
							// indexerStockerSolr(langueNom, classeDoc, "classePageCheminJinja" + classeApiMethode, classePageCheminJinja); 
							indexerStockerSolr(langueNom, classeDoc, "classePageLangueNom" + classeApiMethode, classePageLangueNom); 
							classePage = true;
						}
					}
					indexerStockerSolr(classeDoc, String.format("classePageAvecTemplate%s", classeApiMethode), classePageAvecTemplateMethode);
				}
		
				for(ClasseParts classePartGenApi : classePartsGenApi.values()) {
//					ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGenApi, langueNom);
//					if(classeImportationClassePartsLangue.getLangueNom() == null || classeImportationClassePartsLangue.getLangueNom().equals(langueNom))
//						indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGenApi", classeImportationClassePartsLangue.nomCanonique(langueNom));
					indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGenApi", classePartGenApi.nomCanonique(langueNom));
				}
			}

			if(classePage && classePageRechercheTemplate != null) {
				String classePageRechercheTemplateRepertoire = StringUtils.substringBeforeLast(classePageRechercheTemplate, "/");

				String classePageEmplacementTemplate = String.format("%s/%s%s.htm", classePageRechercheTemplateRepertoire, classeNomSimple, i18nGlobale.getString(I18n.var_Emplacement));
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageEmplacementTemplate", classePageEmplacementTemplate);
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageEmplacementCheminJinja", String.format("%s/%s", templateChemin, classePageEmplacementTemplate));

				String classePageBarreLateraleTemplate = String.format("%s/%s%s.htm", classePageRechercheTemplateRepertoire, classeNomSimple, i18nGlobale.getString(I18n.var_BarreLaterale));
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageBarreLateraleTemplate", classePageBarreLateraleTemplate);
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageBarreLateraleCheminJinja", String.format("%s/%s", templateChemin, classePageBarreLateraleTemplate));

				String classePageBoutonsRechercheTemplate = String.format("%s/%s%s.htm", classePageRechercheTemplateRepertoire, classeNomSimple, i18nGlobale.getString(I18n.var_BoutonsRecherche));
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageBoutonsRechercheTemplate", classePageBoutonsRechercheTemplate);
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageBoutonsRechercheCheminJinja", String.format("%s/%s", templateChemin, classePageBoutonsRechercheTemplate));

				String classePageBoutonsPaginationTemplate = String.format("%s/%s%s.htm", classePageRechercheTemplateRepertoire, classeNomSimple, i18nGlobale.getString(I18n.var_BoutonsPagination));
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageBoutonsPaginationTemplate", classePageBoutonsPaginationTemplate);
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageBoutonsPaginationCheminJinja", String.format("%s/%s", templateChemin, classePageBoutonsPaginationTemplate));

				String classePageFormulaireRechercheTemplate = String.format("%s/%s%s.htm", classePageRechercheTemplateRepertoire, classeNomSimple, i18nGlobale.getString(I18n.var_FormulaireRecherche));
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageFormulaireRechercheTemplate", classePageFormulaireRechercheTemplate);
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageFormulaireRechercheCheminJinja", String.format("%s/%s", templateChemin, classePageFormulaireRechercheTemplate));

				String classePageRechercheSuggereTemplate = String.format("%s/%s%s.htm", classePageRechercheTemplateRepertoire, classeNomSimple, i18nGlobale.getString(I18n.var_RechercheSuggere));
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageRechercheSuggereTemplate", classePageRechercheSuggereTemplate);
				indexerStockerSolr(langueNomGlobale, classeDoc, "classePageRechercheSuggereCheminJinja", String.format("%s/%s", templateChemin, classePageRechercheSuggereTemplate));
			}
		}
		indexerStockerSolr(classeDoc, "classePageAvecTemplate", classePageAvecTemplate);

		indexerStockerSolr(classeDoc, "classeAuth", classeAuth);

		if(classeSmartDataModelStr != null) {
			String encodedStr = "\"" + Arrays.asList(classeSmartDataModelStr.replaceAll("([a-z])([A-Z])", "$1 $2").split(" +")).stream().map(s -> encodeUrl(s)).collect(Collectors.joining("\" OR \"")) + "\"";
			System.out.println(encodedStr);
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("domainName_suggested:" + encodedStr + "^2 OR domainName_text_enUS:" + encodedStr + "^5 OR submoduleShortName_suggested:" + encodedStr + "^3 OR submoduleShortName_text_enUS:" + encodedStr + "^10 OR modelName_suggested:" + encodedStr + "^3 OR modelName_text_enUS:" + encodedStr + "^20&fl=*,score&sort=score desc");
			rechercheSolr.setRows(10);
			rechercheSolr.addFilterQuery("computateFiwareSmartDataModel_docvalues_boolean:true");
			rechercheSolr.setIncludeScore(true);
			QueryResponse reponseRecherche = clientSolrFiware.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) { 
				SolrDocument doc = listeRecherche.get(0);
				String classeSmartDataDomain = indexerStockerSolr(classeDoc, "classeSmartDataDomain", doc.getFieldValue("domainName_docvalues_string").toString().replace(" ", "").trim()); 
				String classeSmartDataSubModule = indexerStockerSolr(classeDoc, "classeSmartDataSubModule", doc.getFieldValue("submoduleShortName_docvalues_string").toString().trim()); 
				String classeSmartDataModel = indexerStockerSolr(classeDoc, "classeSmartDataModel", doc.getFieldValue("modelName_docvalues_string").toString().trim()); 
				String modelName = ((String)doc.getFieldValue("modelName_docvalues_string"));
				String aName = StringUtils.startsWithAny(modelName.toLowerCase(), "a", "e", "i", "o", "u", "h") ? "an " + modelName : "a " + modelName;
				String idName = toId(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.uncapitalize(modelName)), " ").toLowerCase());
				String modelDescription = (Optional.ofNullable((List<String>)doc.getFieldValue("modelDescription_text_enUS"))).orElse(Arrays.asList()).stream().findFirst().orElse("").replace("\n", " ");
				String modeleIcone = Optional.ofNullable(classeIcone).orElse(String.format("<i class=\"%s fa-conveyor-belt\"></i>", fontawesomeStyle));
				List<String> propertiesNames = (List<String>)doc.getFieldValue("propertiesNames_stored_strings");
				List<String> propertiesTypes = (List<String>)doc.getFieldValue("propertiesTypes_stored_strings");
				List<String> propertiesDescriptions = (List<String>)doc.getFieldValue("propertiesDescriptions_stored_strings");
				List<JsonObject> properties = new ArrayList<JsonObject>();
				addProperty("name", propertiesNames, propertiesTypes, propertiesDescriptions, properties, true);
				addProperty("description", propertiesNames, propertiesTypes, propertiesDescriptions, properties, true);
				addProperty("location", propertiesNames, propertiesTypes, propertiesDescriptions, properties, true);
				addProperty("areaServed", propertiesNames, propertiesTypes, propertiesDescriptions, properties, true);
				addProperty("id", propertiesNames, propertiesTypes, propertiesDescriptions, properties, true);
				propertiesNames = new ArrayList<String>(propertiesNames);
				for(String propertyName : propertiesNames) {
					addProperty(propertyName, propertiesNames, propertiesTypes, propertiesDescriptions, properties, false);
				}

				System.out.println(String.format("Top %s Smart Data Model results were: ", listeRecherche.size()));
				System.out.println(String.format("Model - Submodule - Domain - score", listeRecherche.size()));
				System.out.println(String.format("--------------------------", listeRecherche.size()));
				for(Integer i = 0; i < listeRecherche.size(); i++) {
					SolrDocument model = listeRecherche.get(i);
					System.out.println(String.format("%s. %s %s %s %s", String.format("%02d", (i + 1)), model.getFieldValue("modelName_docvalues_string"), model.getFieldValue("submoduleShortName_docvalues_string"), model.getFieldValue("domainName_docvalues_string"), model.getFieldValue("score")));
				}
				System.out.println(" ");
				if(classeSmartDataModel != null && !classeFiware) {
					ToutEcrivain wSmartDataModel = ToutEcrivain.create();
					Yaml yaml = new Yaml();
					wSmartDataModel.l("package ", classeNomEnsemble, ";");
					wSmartDataModel.l();
					wSmartDataModel.l("import java.math.BigDecimal;");
					wSmartDataModel.l("import java.util.List;");
					wSmartDataModel.l("import java.util.Optional;");
					wSmartDataModel.l("import org.apache.commons.lang3.StringUtils;");
					wSmartDataModel.l("import org.computate.search.tool.SearchTool;");
					wSmartDataModel.l("import org.computate.search.wrap.Wrap;");
					wSmartDataModel.l("import ", classePartsModeleBase.nomCanonique(classeLangueNom), ";");
					wSmartDataModel.l("import org.computate.vertx.search.list.SearchList;");
					wSmartDataModel.l("import org.computate.vertx.config.ComputateConfigKeys;");
					wSmartDataModel.l("import io.vertx.core.Promise;");
					wSmartDataModel.l("import io.vertx.core.json.JsonArray;");
					wSmartDataModel.l("import io.vertx.core.json.JsonObject;");
					wSmartDataModel.l("import io.vertx.pgclient.data.Path;");
					wSmartDataModel.l("import io.vertx.pgclient.data.Point;");
					wSmartDataModel.l("import io.vertx.pgclient.data.Polygon;");
					wSmartDataModel.l();
					wSmartDataModel.l("/**");
					wSmartDataModel.l(" * SmartDataModel: ", classeSmartDataModel, " - ", classeSmartDataSubModule, " - ", classeSmartDataDomain);
					wSmartDataModel.l(" * Fiware: true");
					wSmartDataModel.l(" *");
					wSmartDataModel.l(" * Order: 1");
					wSmartDataModel.l(" * Description: ", modelDescription);
					wSmartDataModel.l(" * AName: ", aName);
					wSmartDataModel.l(" * Icon: ", modeleIcone);
					wSmartDataModel.l(" * Rows: 100");
					wSmartDataModel.l(" * ");
					wSmartDataModel.l(" * SearchPageUri: /en-us/search/", idName);
					wSmartDataModel.l(" * EditPageUri: /en-us/edit/", idName, "/{entityShortId}");
					wSmartDataModel.l(" * ApiUri: /en-us/api/", idName);
					wSmartDataModel.l(" * ApiMethod:");
					wSmartDataModel.l(" *   Search:");
					wSmartDataModel.l(" *   GET:");
					wSmartDataModel.l(" *   PATCH:");
					wSmartDataModel.l(" *   POST:");
					wSmartDataModel.l(" *   DELETE:");
					wSmartDataModel.l(" *   PUTImport:");
					wSmartDataModel.l(" * AuthGroup:");
					wSmartDataModel.l(" *   ", classeNomSimple, "Viewer:");
					wSmartDataModel.l(" *     GET:");
					wSmartDataModel.l(" *   ", classeNomSimple, "Editor:");
					wSmartDataModel.l(" *     GET:");
					wSmartDataModel.l(" *     POST:");
					wSmartDataModel.l(" *     PATCH:");
					wSmartDataModel.l(" *   SuperAdmin:");
					wSmartDataModel.l(" *     POST:");
					wSmartDataModel.l(" *     PATCH:");
					wSmartDataModel.l(" *     GET:");
					wSmartDataModel.l(" *     PUT:");
					wSmartDataModel.l(" *     DELETE:");
					wSmartDataModel.l(" *     SuperAdmin:");
					wSmartDataModel.l(" *     Admin:");
					wSmartDataModel.l(" *   Admin:");
					wSmartDataModel.l(" *     POST:");
					wSmartDataModel.l(" *     PATCH:");
					wSmartDataModel.l(" *     GET:");
					wSmartDataModel.l(" *     PUT:");
					wSmartDataModel.l(" *     DELETE:");
					wSmartDataModel.l(" *     Admin:");
					wSmartDataModel.l(" **/");
					wSmartDataModel.l("public class ", classeNomSimple, " extends ", classeNomSimple, "Gen<BaseModel> {");
	
					Integer row = 3;
					Integer cell = 0;
					for(int i = 0; i < properties.size(); i++) {
						JsonObject property = properties.get(i);
						String fieldName = property.getString("name");
						if(!fieldName.equals(solrId) && !fieldName.equals("type")) {
							String jsonType = property.getString("type");
							String description = property.getString("description");
							String javaType = "JsonObject";
							if("areaServed".equals(fieldName))
								javaType = "Polygon";
							else if("location".equals(fieldName))
								javaType = "Point";
							else if("string".equals(jsonType))
								javaType = "String";
							else if("boolean".equals(jsonType))
								javaType = "Boolean";
							else if("integer".equals(jsonType))
								javaType = "Integer";
							else if("number".equals(jsonType))
								javaType = "BigDecimal";
							else if("location".equals(fieldName))
								javaType = "Path";
							wSmartDataModel.l();
							wSmartDataModel.l("	/**");
							wSmartDataModel.l("	 * {@inheritDoc}");
							if("areaServed".equals(fieldName)) {
								wSmartDataModel.l("	");
								wSmartDataModel.l("	 * LocationColor: true");
								wSmartDataModel.l("	 * Indexed: true");
								wSmartDataModel.l("	 * Stored: true");
								wSmartDataModel.l("	 * DisplayName: area served colors");
								wSmartDataModel.l("	 * Description: The colors of each areaServed Paths. ");
								wSmartDataModel.l("	 */");
								wSmartDataModel.l("	protected void _areaServedColors(List<String> l) {");
								wSmartDataModel.l("	}");
								wSmartDataModel.l();
								wSmartDataModel.l("	/**");
								wSmartDataModel.l("	 * {@inheritDoc}");
								wSmartDataModel.l("	 * LocationTitle: true");
								wSmartDataModel.l("	 * Indexed: true");
								wSmartDataModel.l("	 * Stored: true");
								wSmartDataModel.l("	 * DisplayName: area served titles");
								wSmartDataModel.l("	 * Description: The titles of each areaServed Paths. ");
								wSmartDataModel.l("	 */");
								wSmartDataModel.l("	protected void _areaServedTitles(List<String> l) {");
								wSmartDataModel.l("	}");
								wSmartDataModel.l();
								wSmartDataModel.l("	/**");
								wSmartDataModel.l("	 * {@inheritDoc}");
								wSmartDataModel.l("	 * LocationUrl: true");
								wSmartDataModel.l("	 * Indexed: true");
								wSmartDataModel.l("	 * Stored: true");
								wSmartDataModel.l("	 * DisplayName: area served links");
								wSmartDataModel.l("	 * Description: The links of each areaServed Paths. ");
								wSmartDataModel.l("	 */");
								wSmartDataModel.l("	protected void _areaServedLinks(List<String> l) {");
								wSmartDataModel.l("	}");
								wSmartDataModel.l();
								wSmartDataModel.l("	/**");
								wSmartDataModel.l("	 * {@inheritDoc}");
								wSmartDataModel.l("	 * FiwareType: geo:linestring");
								wSmartDataModel.l("	 * Area: true");
							}
							if("location".equals(fieldName)) {
								row = 4;
								cell = 0;
								wSmartDataModel.l(String.format("	 * HtmRowTitleOpen: GeoJSON details", classeSmartDataModel));
								wSmartDataModel.l("	 * FiwareType: geo:point");
								wSmartDataModel.l("	 * Location: true");
							}
							wSmartDataModel.l("	 * DocValues: true");
							wSmartDataModel.l("	 * Persist: true");
							wSmartDataModel.l("	 * DisplayName: ", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(fieldName), " ").toLowerCase(), "");
							if(description != null)
								wSmartDataModel.l("	 * Description: ", description.replace("\r\n", " ").replace("\n", " "), "");
							if("name".equals(fieldName)) {
								wSmartDataModel.l(String.format("	 * HtmRowTitleOpen: name and description", classeSmartDataModel));
								wSmartDataModel.l("	 * HtmColumn: 1");
								wSmartDataModel.l("	 * VarName: true");
							}
							if("description".equals(fieldName)) {
								wSmartDataModel.l("	 * HtmColumn: 2");
								wSmartDataModel.l("	 * VarDescription: true");
							}
							if("id".equals(fieldName)) {
								row = 5;
								cell = 0;
								wSmartDataModel.l("	 * HtmRowTitleOpen: NGSI-LD details");
								wSmartDataModel.l("	 * HtmRow: ", row, "");
								wSmartDataModel.l("	 * HtmCell: ", cell, "");
								wSmartDataModel.l("	 * Facet: true");
								wSmartDataModel.l("	 **/");
								wSmartDataModel.l("	protected void _", fieldName, "(Wrap<", javaType, "> w) {");
								wSmartDataModel.l("		w.o(String.format(\"urn:ngsi-ld:%s:%s\", CLASS_SIMPLE_NAME, toId(name)));");
								wSmartDataModel.l("	}");
								wSmartDataModel.l("");
								wSmartDataModel.l("	/**");
								wSmartDataModel.l("	 * {@inheritDoc}");
								wSmartDataModel.l("	 * DisplayName: short entity ID");
								wSmartDataModel.l("	 * Description: A short ID for this Smart Data Model");
								wSmartDataModel.l("	 * DocValues: true");
								wSmartDataModel.l("	 * Facet: true");
								wSmartDataModel.l("	 * VarId: true");
								wSmartDataModel.l("	 */");
								wSmartDataModel.l("	protected void _entityShortId(Wrap<String> w) {");
								wSmartDataModel.l("		if(id != null) {");
								wSmartDataModel.l("			w.o(StringUtils.substringAfter(id, String.format(\"urn:ngsi-ld:%s:\", CLASS_SIMPLE_NAME)));");
								wSmartDataModel.l("		}");
								wSmartDataModel.l("	}");
								cell++;
								wSmartDataModel.l();
								wSmartDataModel.l("	/**");
								wSmartDataModel.l("	 * {@inheritDoc}");
								wSmartDataModel.l("	 * DocValues: true");
								wSmartDataModel.l("	 * Persist: true");
								wSmartDataModel.l("	 * DisplayName: NGSILD-Tenant");
								wSmartDataModel.l("	 * Description: The NGSILD-Tenant or Fiware-Service");
								wSmartDataModel.l("	 * HtmRow: ", row, "");
								wSmartDataModel.l("	 * HtmCell: ", cell, "");
								wSmartDataModel.l("	 * Facet: true");
								wSmartDataModel.l("	 */");
								wSmartDataModel.l("	protected void _ngsildTenant(Wrap<String> w) {");
								wSmartDataModel.l("		w.o(", i18nGlobale.getString(I18n.var_requeteSite), "_.getConfig().getString(ComputateConfigKeys.NGSILD_TENANT));");
								wSmartDataModel.l("	}");
								cell++;
								wSmartDataModel.l();
								wSmartDataModel.l("	/**");
								wSmartDataModel.l("	 * {@inheritDoc}");
								wSmartDataModel.l("	 * DocValues: true");
								wSmartDataModel.l("	 * Persist: true");
								wSmartDataModel.l("	 * DisplayName: NGSILD-Path");
								wSmartDataModel.l("	 * Description: The NGSILD-Path or Fiware-ServicePath");
								wSmartDataModel.l("	 * HtmRow: ", row, "");
								wSmartDataModel.l("	 * HtmCell: ", cell, "");
								wSmartDataModel.l("	 * Facet: true");
								wSmartDataModel.l("	 */");
								wSmartDataModel.l("	protected void _ngsildPath(Wrap<String> w) {");
								wSmartDataModel.l("		w.o(", i18nGlobale.getString(I18n.var_requeteSite), "_.getConfig().getString(ComputateConfigKeys.NGSILD_PATH));");
								wSmartDataModel.l("	}");
								cell++;
								wSmartDataModel.l();
								wSmartDataModel.l("	/**");
								wSmartDataModel.l("	 * {@inheritDoc}");
								wSmartDataModel.l("	 * DocValues: true");
								wSmartDataModel.l("	 * Persist: true");
								wSmartDataModel.l("	 * DisplayName: NGSILD context");
								wSmartDataModel.l("	 * Description: The NGSILD context URL for @context data");
								wSmartDataModel.l("	 * HtmRow: ", row, "");
								wSmartDataModel.l("	 * HtmCell: ", cell, "");
								wSmartDataModel.l("	 * Facet: true");
								wSmartDataModel.l("	 */");
								wSmartDataModel.l("	protected void _ngsildContext(Wrap<String> w) {");
								wSmartDataModel.l("		w.o(", i18nGlobale.getString(I18n.var_requeteSite), "_.getConfig().getString(ComputateConfigKeys.CONTEXT_BROKER_CONTEXT));");
								wSmartDataModel.l("	}");
								cell++;
								wSmartDataModel.l();
								wSmartDataModel.l("	/**");
								wSmartDataModel.l("	 * {@inheritDoc}");
								wSmartDataModel.l("	 * DocValues: true");
								wSmartDataModel.l("	 * Persist: true");
								wSmartDataModel.l("	 * DisplayName: NGSILD data");
								wSmartDataModel.l("	 * Description: The NGSILD data with @context from the context broker");
								wSmartDataModel.l("	 * HtmRow: ", row, "");
								wSmartDataModel.l("	 * HtmCell: ", cell, "");
								wSmartDataModel.l("	 * Facet: true");
								wSmartDataModel.l("	 * Multiline: true");
								wSmartDataModel.l("	 */");
								wSmartDataModel.l("	protected void _ngsildData(Wrap<JsonObject> w) {");
								wSmartDataModel.l("	}");
								row++;
								cell = -1;
							} else {
								if(row == 6 && cell == 0)
									wSmartDataModel.l(String.format("	 * HtmRowTitleOpen: %s details", classeSmartDataModel));
								wSmartDataModel.l("	 * HtmRow: ", row, "");
								wSmartDataModel.l("	 * HtmCell: ", cell, "");
								wSmartDataModel.l("	 * Facet: true");
								wSmartDataModel.l("	 **/");
								wSmartDataModel.l("	protected void _", fieldName, "(Wrap<", javaType, "> w) {");
								if("location".equals(fieldName)) {
									wSmartDataModel.l("		w.o(staticSetLocation(siteRequest_, siteRequest_.getConfig().getString(ComputateConfigKeys.DEFAULT_MAP_LOCATION)));");
								}
								wSmartDataModel.l("	}");
							}
							cell++;
							// if(cell > 3) {
							// 	row++;
							// 	cell = 1;
							// }
							wSmartDataModel.l();
						}
					}
					wSmartDataModel.l("}");

					wSmartDataModel.l();
					FileUtils.writeStringToFile(new File(classeCheminAbsolu), wSmartDataModel.toString(), "UTF-8");
				}
			}
		}

		Boolean classeIndexe = indexerStockerSolr(classeDoc, "classeIndexe", regexTrouve("^" + i18nGlobale.getString(I18n.var_Indexe) + ": (true)$", classeCommentaire) || classeSauvegarde || classeModele || classeFiware || classePage);
		Boolean classeImage = indexerStockerSolr(classeDoc, "classeImage", regexTrouve("^" + i18nGlobale.getString(I18n.var_Image) + ": (true)$", classeCommentaire));

		stockerSolr(classeDoc, "classePromesse", classePromesse);
		if(activerVertx || activerQuarkus) {
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Promise", classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Future", classeLangueNom), classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonArray", classeLangueNom), classeLangueNom);
		}

		if(classeApi) {
			classePartsGenAjouter(ClasseParts.initClasseParts(this, ApiRequest.class.getCanonicalName(), classeLangueNom), classeLangueNom);
		}
		if(classeIndexe) {
//			classePartsGenAjouter(classePartsSolrInputDocument, classeLangueNom);
//			classePartsGenAjouter(classePartsSolrClient, classeLangueNom);
//			classePartsGenAjouter(classePartsSolrDocument, classeLangueNom);
			classePartsGenAjouter(classePartsList, classeLangueNom);
			classePartsGenAjouter(classePartsArrayList, classeLangueNom);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, JsonArray.class.getCanonicalName(), classeLangueNom), classeLangueNom);
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
			String classeTriSuffixeType = Optional.ofNullable(clientSolrComputate.getById(solrCollectionComputate + " " + classeCheminAbsolu + "." + classeTriVar)).map(d -> (String)d.get("entiteSuffixeType_stored_string")).orElse(null);
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
		clientSolrComputate.commit(true, false, false);
		String qSupprimer = concat("classeCheminAbsolu_indexed_string", ":\"", classeChemin, "\" AND (modifiee_indexed_date:[* TO ", modifiee.minusMillis(1).toString(), "] OR (*:* AND -modifiee_indexed_date:[* TO *]))");
		UpdateResponse d = clientSolrComputate.deleteByQuery(qSupprimer);
		clientSolrComputate.commit(true, false, false); 
		return classeDoc;
	}

	public void addProperty(String propertyName
			, List<String> propertiesNames
			, List<String> propertiesTypes
			, List<String> propertiesDescriptions
			, List<JsonObject> properties
			, Boolean remove
			) {
		int i = propertiesNames.indexOf(propertyName);
		properties.add(new JsonObject().put("name", propertyName)
				.put("description", propertiesDescriptions.get(i))
				.put("type", propertiesTypes.get(i))
				);
		if(remove) {
			propertiesNames.remove(i);
			propertiesTypes.remove(i);
			propertiesDescriptions.remove(i);
		}
	}

	public String encodeUrl(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			LOG.error(i18nGlobale.getString(I18n.str_Erreur_lors_de_lanalyse_de_la_classe), ex);
			ExceptionUtils.rethrow(ex);
			return "";
		}
	}

	public String toId(String s) {
		if(s != null) {
			s = Normalizer.normalize(s, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
		}

		return s;
	}
}
