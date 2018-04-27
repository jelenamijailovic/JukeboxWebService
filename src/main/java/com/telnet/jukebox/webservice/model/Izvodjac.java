package com.telnet.jukebox.webservice.model;

public class Izvodjac {

	private long id;
	private String ime;

	public Izvodjac() {
	}

	public Izvodjac(long id, String ime) {
		super();
		this.id = id;
		this.ime = ime;
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
