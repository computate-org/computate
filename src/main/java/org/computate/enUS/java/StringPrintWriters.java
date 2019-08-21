package org.computate.enUS.java;

import java.io.IOException;
import java.util.List;

public class StringPrintWriters {

	public static StringPrintWriters create(AllWriter...writers) {
		StringPrintWriters o = new StringPrintWriters();
		o.initDeepForClass();
		o.addStringPrintWriters(writers);
		return o;
	}

	protected void  _stringPrintWriters(List<AllWriter> c) {
	}

	public StringPrintWriters t(int nombreTabulations, Object...objets) {
		for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.t(nombreTabulations, objets);
		}
		return this;
	}

	public StringPrintWriters tl(int nombreTabulations, Object...objets) {
		for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.tl(nombreTabulations, objets);
		}
		return this;
	}

	public StringPrintWriters l(Object...objets) {
		for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.l(objets);
		}
		return this;
	}

	public StringPrintWriters s(Object...objets) { 
		for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.s(objets);
		}
		return this;
	}

	public void  flushClose() throws IOException, IOException {
		for(ToutEcrivain stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.flushClose();
		}
	}

	@Override()
	public String toString() {
		return stringPrintWriters.get(0).toString();
	}

	protected List<AllWriter> stringPrintWriters = new java.util.ArrayList<ToutEcrivain>();

	public List<AllWriter> getStringPrintWriters() {
		return stringPrintWriters;
	}

	public void  setStringPrintWriters(List<AllWriter> l) {
		this.stringPrintWriters = l;
	}

	public StringPrintWriters addStringPrintWriters(AllWriter...objets) {
		for(ToutEcrivain o : objets) {
			addStringPrintWriters(o);
		}
		return (StringPrintWriters)this;
	}

	public StringPrintWriters addStringPrintWriters(AllWriter o) {
		if(o != null && !stringPrintWriters.contains(o))
			this.stringPrintWriters.add(o);
		return (StringPrintWriters)this;
	}

	protected StringPrintWriters stringPrintWritersInit() {
		_stringPrintWriters(stringPrintWriters);
		return (StringPrintWriters)this;
	}

	protected boolean alreadyInitializedStringPrintWriters = false;

	public StringPrintWriters initDeepStringPrintWriters() {
		if(!alreadyInitializedStringPrintWriters) {
			alreadyInitializedStringPrintWriters = true;
			stringPrintWritersInit();
		}
		return (StringPrintWriters)this;
	}

	public void  initDeepForClass() {
		initDeepStringPrintWriters();
	}
}
