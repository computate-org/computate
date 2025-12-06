/*
 * Copyright Computate Limited Liability Company in Utah, USA. 
 * SPDX-License-Identifier: AGPL-3.0
 * This program and the accompanying materials are made available under the
 * terms of the GNU AFFERO GENERAL PUBLIC LICENSE which is available at
 * 
 * https://www.gnu.org/licenses/agpl-3.0.html
 * 
 * You may not propagate or modify a covered work except as expressly provided 
 * under this License. Any attempt otherwise to propagate or modify it is void, 
 * and will automatically terminate your rights under this License (including 
 * any patent licenses granted under the third paragraph of section 11).
 */
package org.computate.frFR.java;    

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.text.StringEscapeUtils;


/**   
 * NomCanonique.enUS: org.computate.enUS.java.AllWriter
 **/
public class ToutEcrivain {     

  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public static ToutEcrivain create() {
    ToutEcrivain o = new ToutEcrivain();
    try {
      o.initDeepForClass();
    } catch (FileNotFoundException e) {
      ExceptionUtils.rethrow(e);
    }
    return o;
  }

  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public static ToutEcrivain create(String tab) {
    ToutEcrivain o = new ToutEcrivain();
    try {
      o.setTab(tab);
      o.initDeepForClass();
    } catch (FileNotFoundException e) {
      ExceptionUtils.rethrow(e);
    }
    return o;
  }

  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public static ToutEcrivain create(File file) {
    ToutEcrivain o = new ToutEcrivain();
    o.setFile(file);
    try {
      o.initDeepForClass();
    } catch (FileNotFoundException e) {
      ExceptionUtils.rethrow(e);
    }
    return o;
  }

  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public static ToutEcrivain create(File file, String tab) {
    ToutEcrivain o = new ToutEcrivain();
    o.setFile(file);
    o.setTab(tab);
    try {
      o.initDeepForClass();
    } catch (FileNotFoundException e) {
      ExceptionUtils.rethrow(e);
    }
    return o;
  }

  /**
   * {@inheritDoc}
   * 
   **/
  protected void _file(File o) {
  }

  protected void _stringWriter(StringWriter o) {
    if(file == null)
      this.stringWriter = new StringWriter();
  }

  protected void _printWriter(PrintWriter o) throws FileNotFoundException {
    if(file == null)
      this.printWriter = new PrintWriter(stringWriter);
    else
      this.printWriter = new PrintWriter(file);
  }

  /**
   * {@inheritDoc}
   * 
   **/
  protected void _empty(Boolean c) {
    this.empty = true;
  }

  public ToutEcrivain t(int nombreTabulations, Object...objets) {
    for(int i = 0; i < nombreTabulations; i++)
      s(tab);
    s(objets);
    return this;
  }
  public ToutEcrivain tl(int nombreTabulations, Object...objets) {
    for(int i = 0; i < nombreTabulations; i++)
      s(tab);
    s(objets);
    s("\n");
    return this;
  }

  public ToutEcrivain l(Object...objets) {
    s(objets);
    s("\n");
    return this;
  }

  public ToutEcrivain s(Object...objets) { 
    for(Object objet : objets) {
      if(objet != null) {
        if(objet instanceof List) {
          List<?> chaine = (List<?>)objet;
          for(Object objet2 : chaine) {
            if(objet2 != null && !StringUtils.isEmpty(objet2.toString()))
              printWriter.append(objet2.toString());
          }
        }
        else {
          if(!StringUtils.isEmpty(objet.toString()))
            printWriter.append(objet.toString());
        }
      }
    }
    empty = false;
    return this;
  }

  public ToutEcrivain sx(Object...objets) {
    try {
      for(Object objet : objets)
        if(objet != null)
          printWriter.append(StringEscapeUtils.escapeHtml4(objet.toString()));
    } catch(Exception ex) {
      ExceptionUtils.rethrow(ex);
    }
    return this;
  }

  /**
   * Param1.var.enUS: objects
   * r: objet
   * r.enUS: object
   */
  public String q(Object...objets) {
    StringBuilder o = new StringBuilder();
    o.append("\"");
    for(Object objet : objets)
      if(objet != null)
        o.append(StringUtils.replace(StringUtils.replace(objet.toString(), "\\", "\\\\"), "\"", "\\\""));
    o.append("\"");
    return o.toString();
  }

  public void flushClose() throws IOException {
    printWriter.flush();
    if(stringWriter != null)
      stringWriter.flush();
    printWriter.close();
    if(stringWriter != null)
      stringWriter.close();
  }

  @Override public String toString() {
    return stringWriter.toString();
  }

  //////////////////
  // stringWriter //
  //////////////////

  /**	The entity " stringWriter "
   *	It is constructed before being initialized with the constructor by default StringWriter(). 
   */
  protected StringWriter stringWriter; 

  public StringWriter getStringWriter() {
    return stringWriter;
  }

  public void setStringWriter(StringWriter o) {
    this.stringWriter = o;
  }
  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  protected ToutEcrivain stringWriterInit() {
    _stringWriter(stringWriter);
    return (ToutEcrivain)this;
  }

  //////////
  // file //
  //////////
  protected File file;
  
  public File getFile() {
    return file;
  }

  public void setFile(File o) {
    this.file = o;
  }

  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  protected ToutEcrivain fileInit() {
    _file(file);
    return (ToutEcrivain)this;
  }

  /////////////////
  // printWriter //
  /////////////////

  /**	The entity " printWriter "
   *	 is defined as null before being initialized. 
   */
  protected PrintWriter printWriter;

  public PrintWriter getPrintWriter() {
    return printWriter;
  }

  public void setPrintWriter(PrintWriter o) {
    this.printWriter = o;
  }
  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  protected ToutEcrivain printWriterInit() throws FileNotFoundException {
    _printWriter(printWriter);
    return (ToutEcrivain)this;
  }

  ///////////
  // tab //
  ///////////

  /**
   * The tab characters to use
   */
  protected String tab = "  ";

  public String getTab() {
    return tab;
  }

  public void setTab(String o) {
    this.tab = o;
  }

  ///////////
  // empty //
  ///////////

  /**	The entity " empty "
   *	 is defined as null before being initialized. 
   */
  protected Boolean empty = false;

  public Boolean getEmpty() {
    return empty;
  }

  public void setEmpty(Boolean o) {
    this.empty = o;
  }
  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public ToutEcrivain setEmpty(String o) {
    if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
      this.empty = Boolean.parseBoolean(o);
    return (ToutEcrivain)this;
  }
  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  protected ToutEcrivain emptyInit() {
    _empty(empty);
    return (ToutEcrivain)this;
  }

  public Boolean solrEmpty() {
    return empty;
  }

  public String strEmpty() {
    return empty == null ? "" : empty.toString();
  }

  public String displayNameEmpty() {
    return null;
  }

  public String HtmInfobulleEmpty() {
    return null;
  }

  public String htmlEmpty() {
    return empty == null ? "" : StringEscapeUtils.escapeHtml4(strEmpty());
  }

  //////////////
  // initDeep //
  //////////////

  protected boolean alreadyInitializedStringPrintWriter = false;

  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public ToutEcrivain initDeepStringPrintWriter() throws FileNotFoundException {
    if(!alreadyInitializedStringPrintWriter) {
      alreadyInitializedStringPrintWriter = true;
      stringWriterInit();
      printWriterInit();
      emptyInit();
    }
    return (ToutEcrivain)this;
  }

  public void initDeepForClass() throws FileNotFoundException {
    initDeepStringPrintWriter();
  }
}
