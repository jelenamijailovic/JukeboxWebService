package com.telnet.jukebox.webservice.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

public class Pesma {

	private long id;
	private String naziv;
	private String izvodjacIme;
	private String zanrIme;

	private Map<Long, Promet> prometi = new HashMap<>();

	public Pesma() {

	}

	public Pesma(long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
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

	@XmlTransient
	public Map<Long, Promet> getPrometi() {
		return prometi;
	}

	public void setPrometi(Map<Long, Promet> prometi) {
		this.prometi = prometi;
	}

	public String getIzvodjacIme() {
		return izvodjacIme;
	}

	public void setIzvodjacIme(String izvodjacIme) {
		this.izvodjacIme = izvodjacIme;
	}

	public String getZanrIme() {
		return zanrIme;
	}

	public void setZanrIme(String zanrIme) {
		this.zanrIme = zanrIme;
	}

}
