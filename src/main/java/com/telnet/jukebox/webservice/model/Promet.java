package com.telnet.jukebox.webservice.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

public class Promet {

	private long id;
	private Date datum;
	
	public Promet() {}

	public Promet(long id) throws ParseException {
		super();
		DateFormat df = new SimpleDateFormat("MMM dd hh:mm:ss yyyy", Locale.ENGLISH);
		this.id = id;
		this.datum = df.parse(df.format(datum));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	
	
	
	
}
