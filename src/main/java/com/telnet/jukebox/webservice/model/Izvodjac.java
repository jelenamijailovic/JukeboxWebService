package com.telnet.jukebox.webservice.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Izvodjac {

	private long id;
	private String ime;
	private Map<Long, Pesma> pesme= new HashMap<>();
	
	public Izvodjac() {}

	public Izvodjac(long id, String ime) {
		super();
		this.id = id;
		this.ime = ime;
	}
	
	@XmlTransient
	public Map<Long, Pesma> getPesme() {
		return pesme;
	}

	public void setPesme(Map<Long, Pesma> pesme) {
		this.pesme = pesme;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	
	
}
