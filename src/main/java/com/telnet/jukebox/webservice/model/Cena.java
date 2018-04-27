package com.telnet.jukebox.webservice.model;

public class Cena {

	private long id;
	private long kolicina;

	public Cena() {
	}

	public Cena(long id, long kolicina) {
		super();
		this.id = id;
		this.kolicina = kolicina;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKolicina() {
		return kolicina;
	}

	public void setKolicina(long kolicina) {
		this.kolicina = kolicina;
	}

}
