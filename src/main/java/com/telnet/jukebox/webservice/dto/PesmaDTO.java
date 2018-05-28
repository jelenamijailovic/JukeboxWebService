package com.telnet.jukebox.webservice.dto;

public class PesmaDTO {

	private int id;
	private String naziv;
	private String izvodjacIme;
	private String zanrIme;
	private int cenaKolicina;
	private int izvodjacId;
	private int cenaId;
	private int brojStrana;

	public PesmaDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
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

	public int getCenaKolicina() {
		return cenaKolicina;
	}

	public void setCenaKolicina(int cenaKolicina) {
		this.cenaKolicina = cenaKolicina;
	}

	public int getIzvodjacId() {
		return izvodjacId;
	}

	public void setIzvodjacId(int izvodjacId) {
		this.izvodjacId = izvodjacId;
	}

	public int getCenaId() {
		return cenaId;
	}

	public void setCenaId(int cenaId) {
		this.cenaId = cenaId;
	}

	public int getBrojStrana() {
		return brojStrana;
	}

	public void setBrojStrana(int brojStrana) {
		this.brojStrana = brojStrana;
	}
}
