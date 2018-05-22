package com.telnet.jukebox.webservice.dto;

public class PesmaDTO {

	private Long id;
	private String naziv;
	private String izvodjacIme;
	private String zanrIme;
	private Long cenaKolicina;
	private Long izvodjacId;
	private Long cenaId;

	public PesmaDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getCenaKolicina() {
		return cenaKolicina;
	}

	public void setCenaKolicina(Long cenaKolicina) {
		this.cenaKolicina = cenaKolicina;
	}

	public Long getIzvodjacId() {
		return izvodjacId;
	}

	public void setIzvodjacId(Long izvodjacId) {
		this.izvodjacId = izvodjacId;
	}

	public Long getCenaId() {
		return cenaId;
	}

	public void setCenaId(Long cenaId) {
		this.cenaId = cenaId;
	}
}
