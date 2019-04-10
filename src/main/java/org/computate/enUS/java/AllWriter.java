package org.computate.enUS.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

public class AllWriter {

	public static AllWriter create() throws FileNotFoundException, FileNotFoundException {
		AllWriter o = new AllWriter();
		o.initDeepForClass();
		return o;
	}

	public static AllWriter create(File file) throws FileNotFoundException, FileNotFoundException {
		AllWriter o = new AllWriter();
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

	public AllWriter t(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		return this;
	}

	public AllWriter tl(int nombreTabulations, Object...objets) {
		for(int i = 0; i < nombreTabulations; i++)
			s("\t");
		s(objets);
		s("\n");
		return this;
	}

	public AllWriter l(Object...objets) {
		s(objets);
		s("\n");
		return this;
	}

	public AllWriter s(Object...objets) { 
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

	public String q(Object...objects) {
		StringBuilder o = new StringBuilder();
		o.append("\"");
		for(Object object : objects)
			if(object != null)
				o.append(StringUtils.replace(StringUtils.replace(object.toString(), "\\", "\\\\"), "\"", "\\\""));
		o.append("\"");
		return o.toString();
	}

	public AllWriter be(Object...objects) {
		s("{ e(", q(objects), ")");
		return this;
	}

	public AllWriter e(Object...objects) {
		s("e(", q(objects), ")");
		return this;
	}

	public AllWriter da(String var, Object...objects) {
		s(".a(", q(var), ", ", q(objects), ")");
		return this;
	}

	public AllWriter a(String var, Object...objects) {
		s("a(", q(var), ", ", q(objects), ")");
		return this;
	}

	public AllWriter dal(String var, Object...objects) {
		l(".a(", q(var), ", ", q(objects), ")");
		return this;
	}

	public AllWriter al(String var, Object...objects) {
		l("a(", q(var), ", ", q(objects), ")");
		return this;
	}

	public AllWriter dsxq(Object...objects) {
		s(".sx(", q(objects), ")");
		return this;
	}

	public AllWriter dsq(Object...objects) {
		s(".s(", q(objects), ")");
		return this;
	}

	public AllWriter dsx(Object...objects) {
		s(".sx(");
		s(objects);
		l(");");
		return this;
	}

	public AllWriter ds(Object...objects) {
		s(".s(");
		s(objects);
		l(");");
		return this;
	}

	public AllWriter dsxqscl(Object...objects) {
		l(".sx(", q(objects), ");");
		return this;
	}

	public AllWriter dsqscl(Object...objects) {
		l(".s(", q(objects), ");");
		return this;
	}

	public AllWriter dsxscl(Object...objects) {
		s(".sx(");
		s(objects);
		l(");");
		return this;
	}

	public AllWriter dsscl(Object...objects) {
		s(".s(");
		s(objects);
		l(");");
		return this;
	}

	public AllWriter dsxql(Object...objects) {
		l(".sx(", q(objects), ")");
		return this;
	}

	public AllWriter dsql(Object...objects) {
		l(".s(", q(objects), ")");
		return this;
	}

	public AllWriter dsxl(Object...objects) {
		s(".sx(");
		s(objects);
		l(");");
		return this;
	}

	public AllWriter sxqscl(Object...objects) {
		l("sx(", q(objects), ");");
		return this;
	}

	public AllWriter sqscl(Object...objects) {
		l("s(", q(objects), ");");
		return this;
	}

	public AllWriter sxscl(Object...objects) {
		s("sx(");
		s(objects);
		l(");");
		return this;
	}

	public AllWriter sscl(Object...objects) {
		s("s(");
		s(objects);
		l(");");
		return this;
	}

	public AllWriter sxql(Object...objects) {
		l("sx(", q(objects), ")");
		return this;
	}

	public AllWriter sql(Object...objects) {
		l("s(", q(objects), ")");
		return this;
	}

	public AllWriter sxl(Object...objects) {
		s("sx(");
		s(objects);
		l(");");
		return this;
	}

	public AllWriter dsl(Object...objects) {
		s(".s(");
		s(objects);
		l(");");
		return this;
	}

	public AllWriter df() {
		s(".f()");
		return this;
	}

	public AllWriter dfl() {
		l(".f();");
		return this;
	}

	public AllWriter dfg() {
		s(".fg()");
		return this;
	}

	public AllWriter dfgl() {
		l(".fg();");
		return this;
	}

	public AllWriter fgl() {
		l("fg();");
		return this;
	}

	public AllWriter dfgbl() {
		l(".fg(); }");
		return this;
	}

	public AllWriter dg(Object...objects) {
		s(".g(", q(objects), ")");
		return this;
	}

	public AllWriter gl(Object...objects) {
		l("g(", q(objects), ");");
		return this;
	}

	public AllWriter bgl(Object...objects) {
		l("} g(", q(objects), ");");
		return this;
	}

	public AllWriter dgl(Object...objects) {
		l(".g(", q(objects), ");");
		return this;
	}

	public AllWriter dgbl(Object...objects) {
		l(".g(", q(objects), "); }");
		return this;
	}

	public AllWriter gbl(Object...objects) {
		l("g(", q(objects), "); }");
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

	protected StringWriter stringWriter;

	public StringWriter getStringWriter() {
		return stringWriter;
	}

	public void  setStringWriter(StringWriter o) {
		this.stringWriter = o;
	}

	protected AllWriter stringWriterInit() {
		_stringWriter(stringWriter);
		return (AllWriter)this;
	}

	protected File file;

	public File getFile() {
		return file;
	}

	public void  setFile(File o) {
		this.file = o;
	}

	protected AllWriter fileInit() {
		_file(file);
		return (AllWriter)this;
	}

	protected PrintWriter printWriter;

	public PrintWriter getPrintWriter() {
		return printWriter;
	}

	public void  setPrintWriter(PrintWriter o) {
		this.printWriter = o;
	}

	protected AllWriter printWriterInit() throws FileNotFoundException, FileNotFoundException {
		_printWriter(printWriter);
		return (AllWriter)this;
	}

	protected Boolean empty = false;

	public Boolean getEmpty() {
		return empty;
	}

	public void  setEmpty(Boolean o) {
		this.empty = o;
	}

	public AllWriter setEmpty(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.empty = Boolean.parseBoolean(o);
		return (AllWriter)this;
	}

	protected AllWriter emptyInit() {
		_empty(empty);
		return (AllWriter)this;
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

	protected boolean alreadyInitializedStringPrintWriter = false;

	public AllWriter initDeepStringPrintWriter() throws FileNotFoundException, FileNotFoundException {
		if(!alreadyInitializedStringPrintWriter) {
			alreadyInitializedStringPrintWriter = true;
			stringWriterInit();
			printWriterInit();
			emptyInit();
		}
		return (AllWriter)this;
	}

	public void  initDeepForClass() throws FileNotFoundException, FileNotFoundException {
		initDeepStringPrintWriter();
	}
}
