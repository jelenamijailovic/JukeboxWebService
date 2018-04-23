package com.telnet.jukebox.webservice.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Zanr {

	private long id;
	private String naziv;
	private Map<Long, Pesma> pesme = new HashMap<>();

	public Zanr() {
	}

	public Zanr(long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
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

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
