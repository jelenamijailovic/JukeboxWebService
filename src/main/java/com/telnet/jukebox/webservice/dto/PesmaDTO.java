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

	public PesmaDTO(int id, String naziv, String izvodjacIme, String zanrIme, int cenaKolicina) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.izvodjacIme = izvodjacIme;
		this.zanrIme = zanrIme;
		this.cenaKolicina = cenaKolicina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brojStrana;
		result = prime * result + cenaId;
		result = prime * result + cenaKolicina;
		result = prime * result + id;
		result = prime * result + izvodjacId;
		result = prime * result + ((izvodjacIme == null) ? 0 : izvodjacIme.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((zanrIme == null) ? 0 : zanrIme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PesmaDTO other = (PesmaDTO) obj;
		if (brojStrana != other.brojStrana)
			return false;
		if (cenaId != other.cenaId)
			return false;
		if (cenaKolicina != other.cenaKolicina)
			return false;
		if (id != other.id)
			return false;
		if (izvodjacId != other.izvodjacId)
			return false;
		if (izvodjacIme == null) {
			if (other.izvodjacIme != null)
				return false;
		} else if (!izvodjacIme.equals(other.izvodjacIme))
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (zanrIme == null) {
			if (other.zanrIme != null)
				return false;
		} else if (!zanrIme.equals(other.zanrIme))
			return false;
		return true;
	}
	
	
	
}
