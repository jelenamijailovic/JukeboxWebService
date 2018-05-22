package com.telnet.jukebox.webservice.dto;

import java.sql.Date;

public class PrometDTO {
	private Long id;
	private Date datum;
	private Long pesmaId;
	private String pesmaNaziv;
	private Long cenaKolicina;
	private Long repetition;
	private String izvodjacIme;
	private Long idKor;
	private String emailKor;

	public PrometDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getCenaKolicina() {
		return cenaKolicina;
	}

	public void setCenaKolicina(Long cenaKolicina) {
		this.cenaKolicina = cenaKolicina;
	}

	public Long getPesmaId() {
		return pesmaId;
	}

	public void setPesmaId(Long pesmaId) {
		this.pesmaId = pesmaId;
	}

	public Long getRepetition() {
		return repetition;
	}

	public void setRepetition(Long repetition) {
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

	public Long getIdKor() {
		return idKor;
	}

	public void setIdKor(Long idKor) {
		this.idKor = idKor;
	}
}
