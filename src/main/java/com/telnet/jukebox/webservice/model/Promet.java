package com.telnet.jukebox.webservice.model;

import java.sql.Date;

public class Promet {

	private int id;
	private Date datum;
	private int pesmaId;
	private String pesmaNaziv;
	private int cenaKolicina;
	private int repetition;
	private String izvodjacIme;
	private int idKor;
	private String emailKor;

	public Promet() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getPesmaNaziv() {
		return pesmaNaziv;
	}

	public void setPesmaNaziv(String pesmaNaziv) {
		this.pesmaNaziv = pesmaNaziv;
	}

	public int getCenaKolicina() {
		return cenaKolicina;
	}

	public void setCenaKolicina(int cenaKolicina) {
		this.cenaKolicina = cenaKolicina;
	}

	public int getPesmaId() {
		return pesmaId;
	}

	public void setPesmaId(int pesmaId) {
		this.pesmaId = pesmaId;
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public String getIzvodjacIme() {
		return izvodjacIme;
	}

	public void setIzvodjacIme(String izvodjacIme) {
		this.izvodjacIme = izvodjacIme;
	}

	public String getEmailKor() {
		return emailKor;
	}

	public void setEmailKor(String emailKor) {
		this.emailKor = emailKor;
	}

	public int getIdKor() {
		return idKor;
	}

	public void setIdKor(int idKor) {
		this.idKor = idKor;
	}

}
