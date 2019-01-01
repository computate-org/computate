package org.computate.enUS.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

public class StringPrintWriter {

	protected StringWriter stringWriter;

	protected File file;

	protected PrintWriter printWriter;

	protected Boolean empty = false;

	protected boolean alreadyInitializedStringPrintWriter = false;

	public static StringPrintWriter create() throws FileNotFoundException, FileNotFoundException {
		StringPrintWriter o = new StringPrintWriter();
		o.initDeepForClass();
		return o;
	}

	public static StringPrintWriter create(File file) throws FileNotFoundException, FileNotFoundException {
		StringPrintWriter o = new StringPrintWriter();
		o.setFile(file);
		o.initDeepForClass();
		return o;
	}

	protected void  _file(File o) {
	}

	protected void  _stringWriter(StringWriter o) {
		if(file == null)
			this.stringWriter = new StringWriter();
	}

	protected void  _printWriter(PrintWriter o) throws FileNotFoundException, FileNotFoundException {
		if(file == null)
			this.printWriter = new PrintWriter(stringWriter);
		else
			this.printWriter = new PrintWriter(file);
	}

	protected void  _empty(Boolean c) {
		this.empty = true;
	}

	public StringPrintWriter t(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		return this;
	}

	public StringPrintWriter tl(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		s("\n");
		return this;
	}

	public StringPrintWriter l(Object...objets) {
		s(objets);
		s("\n");
		return this;
	}

	public StringPrintWriter s(Object...objets) { 
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

	public void  flushClose() throws IOException, IOException {
		printWriter.flush();
		if(stringWriter != null)
			stringWriter.flush();
		printWriter.close();
		if(stringWriter != null)
			stringWriter.close();
	}

	@Override()
	public String toString() {
		return stringWriter.toString();
	}

	public StringWriter getStringWriter() {
		return stringWriter;
	}

	public void  setStringWriter(StringWriter o) {
		this.stringWriter = o;
	}

	protected StringPrintWriter stringWriterInit() {
		_stringWriter(stringWriter);
		return (StringPrintWriter)this;
	}

	public File getFile() {
		return file;
	}

	public void  setFile(File o) {
		this.file = o;
	}

	protected StringPrintWriter fileInit() {
		_file(file);
		return (StringPrintWriter)this;
	}

	public PrintWriter getPrintWriter() {
		return printWriter;
	}

	public void  setPrintWriter(PrintWriter o) {
		this.printWriter = o;
	}

	protected StringPrintWriter printWriterInit() throws FileNotFoundException, FileNotFoundException {
		_printWriter(printWriter);
		return (StringPrintWriter)this;
	}

	public Boolean getEmpty() {
		return empty;
	}

	public void  setEmpty(Boolean o) {
		this.empty = o;
	}

	public StringPrintWriter setEmpty(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.empty = Boolean.parseBoolean(o);
		return (StringPrintWriter)this;
	}

	protected StringPrintWriter emptyInit() {
		_empty(empty);
		return (StringPrintWriter)this;
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

	public String htmlTooltipEmpty() {
		return null;
	}

	public String htmlEmpty() {
		return empty == null ? "" : StringEscapeUtils.escapeHtml4(strEmpty());
	}

	public StringPrintWriter initDeepStringPrintWriter() throws FileNotFoundException, FileNotFoundException {
		if(!alreadyInitializedStringPrintWriter) {
			alreadyInitializedStringPrintWriter = true;
			stringWriterInit();
			printWriterInit();
			emptyInit();
		}
		return (StringPrintWriter)this;
	}

	public void  initDeepForClass() throws FileNotFoundException, FileNotFoundException {
		initDeepStringPrintWriter();
	}
}
