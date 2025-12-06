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

import java.io.IOException;
import java.util.List;


/**   
 * NomCanonique.enUS: org.computate.enUS.java.StringPrintWriters
 **/
public class StringPrintWriters {   

  public static StringPrintWriters create(ToutEcrivain...writers) {
    StringPrintWriters o = new StringPrintWriters();
    o.initDeepForClass();
    o.addStringPrintWriters(writers);
    return o;
  }

  protected void _stringPrintWriters(List<ToutEcrivain> c) {
  }

  /**
   * Param1.var.enUS: numberTabs
   * Param2.var.enUS: objects
   * r: objets
   * r.enUS: objects
   * r: ToutEcrivain
   * r.enUS: AllWriter
   * r: nombreTabulations
   * r.enUS: numberTabs
   */
  public StringPrintWriters t(int nombreTabulations, Object...objets) {
    for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
      stringPrintWriter.t(nombreTabulations, objets);
    }
    return this;
  }

  /**
   * Param1.var.enUS: numberTabs
   * Param2.var.enUS: objects
   * r: objets
   * r.enUS: objects
   * r: ToutEcrivain
   * r.enUS: AllWriter
   * r: nombreTabulations
   * r.enUS: numberTabs
   */
  public StringPrintWriters tl(int nombreTabulations, Object...objets) {
    for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
      stringPrintWriter.tl(nombreTabulations, objets);
    }
    return this;
  }

  /**
   * Param1.var.enUS: objects
   * r: objets
   * r.enUS: objects
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public StringPrintWriters l(Object...objets) {
    for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
      stringPrintWriter.l(objets);
    }
    return this;
  }

  /**
   * Param1.var.enUS: objects
   * r: objets
   * r.enUS: objects
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public StringPrintWriters s(Object...objets) { 
    for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
      stringPrintWriter.s(objets);
    }
    return this;
  }

  /**
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public void flushClose() throws IOException {
    for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
      stringPrintWriter.flushClose();
    }
  }

  @Override public String toString() {
    return stringPrintWriters.get(0).toString();
  }

  ////////////////////////
  // stringPrintWriters //
  ////////////////////////

  /**	The entity " stringPrintWriters "
   *	It is constructed before being initialized with the constructor by default List<StringPrintWriter>(). 
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  protected List<ToutEcrivain> stringPrintWriters = new java.util.ArrayList<ToutEcrivain>();

  public List<ToutEcrivain> getStringPrintWriters() {
    return stringPrintWriters;
  }

  public void setStringPrintWriters(List<ToutEcrivain> l) {
    this.stringPrintWriters = l;
  }

  /**
   * Param1.var.enUS: objects
   * r: objets
   * r.enUS: objects
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */
  public StringPrintWriters addStringPrintWriters(ToutEcrivain...objets) {
    for(ToutEcrivain o : objets) {
      addStringPrintWriters(o);
    }
    return (StringPrintWriters)this;
  }

  public StringPrintWriters addStringPrintWriters(ToutEcrivain o) {
    if(o != null && !stringPrintWriters.contains(o))
      this.stringPrintWriters.add(o);
    return (StringPrintWriters)this;
  }

  protected StringPrintWriters stringPrintWritersInit() {
    _stringPrintWriters(stringPrintWriters);
    return (StringPrintWriters)this;
  }

  //////////////
  // initDeep //
  //////////////

  protected boolean alreadyInitializedStringPrintWriters = false;

  public StringPrintWriters initDeepStringPrintWriters() {
    if(!alreadyInitializedStringPrintWriters) {
      alreadyInitializedStringPrintWriters = true;
      stringPrintWritersInit();
    }
    return (StringPrintWriters)this;
  }

  public void initDeepForClass() {
    initDeepStringPrintWriters();
  }
}
