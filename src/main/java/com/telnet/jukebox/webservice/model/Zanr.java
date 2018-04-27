package com.telnet.jukebox.webservice.model;

public class Zanr {

	private long id;
	private String naziv;

	public Zanr() {
	}

	public Zanr(long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	// @XmlTransient
	// public Map<Long, Pesma> getPesme() {
	// return pesme;
	// }
	//
	// public void setPesme(Map<Long, Pesma> pesme) {
	// this.pesme = pesme;
	// }

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
