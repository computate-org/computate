package org.computate.enUS.java;

import java.io.IOException;
import java.util.List;

public class AllWriters {

	protected List<AllWriter> stringPrintWriters = new java.util.ArrayList<AllWriter>();

	protected boolean alreadyInitializedStringPrintWriters = false;

	public static AllWriters create(AllWriter...writers) {
		AllWriters o = new AllWriters();
		o.initDeepForClass();
		o.addStringPrintWriters(writers);
		return o;
	}

	protected void  _stringPrintWriters(List<AllWriter> c) {
	}

	public AllWriters t(int nombreTabulations, Object...objets) {
		for(AllWriter stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.t(nombreTabulations, objets);
		}
		return this;
	}

	public AllWriters tl(int nombreTabulations, Object...objets) {
		for(AllWriter stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.tl(nombreTabulations, objets);
		}
		return this;
	}

	public AllWriters l(Object...objets) {
		for(AllWriter stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.l(objets);
		}
		return this;
	}

	public AllWriters s(Object...objets) { 
		for(AllWriter stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.s(objets);
		}
		return this;
	}

	public void  flushClose() throws IOException, IOException {
		for(AllWriter stringPrintWriter : stringPrintWriters) {
			stringPrintWriter.flushClose();
		}
	}

	@Override()
	public String toString() {
		return stringPrintWriters.get(0).toString();
	}

	public List<AllWriter> getStringPrintWriters() {
		return stringPrintWriters;
	}

	public void  setStringPrintWriters(List<AllWriter> l) {
		this.stringPrintWriters = l;
	}

	public AllWriters addStringPrintWriters(AllWriter...objets) {
		for(AllWriter o : objets) {
			addStringPrintWriters(o);
		}
		return (AllWriters)this;
	}

	public AllWriters addStringPrintWriters(AllWriter o) {
		if(o != null && !stringPrintWriters.contains(o))
			this.stringPrintWriters.add(o);
		return (AllWriters)this;
	}

	protected AllWriters stringPrintWritersInit() {
		_stringPrintWriters(stringPrintWriters);
		return (AllWriters)this;
	}

	public AllWriters initDeepStringPrintWriters() {
		if(!alreadyInitializedStringPrintWriters) {
			alreadyInitializedStringPrintWriters = true;
			stringPrintWritersInit();
		}
		return (AllWriters)this;
	}

	public void  initDeepForClass() {
		initDeepStringPrintWriters();
	}
}
