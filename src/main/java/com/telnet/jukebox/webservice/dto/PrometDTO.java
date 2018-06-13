package com.telnet.jukebox.webservice.dto;

import java.sql.Date;

public class PrometDTO {
	private int id;
	private Date datum;
	private int pesmaId;
	private String pesmaNaziv;
	private int cenaKolicina;
	private int repetition;
	private String izvodjacIme;
	private int idKor;
	private String emailKor;

	public PrometDTO() {
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

	public PrometDTO(int id, Date datum, String pesmaNaziv, int cenaKolicina, int repetition,
			String izvodjacIme, String emailKor) {
		super();
		this.id = id;
		this.datum = datum;
		this.pesmaNaziv = pesmaNaziv;
		this.cenaKolicina = cenaKolicina;
		this.repetition = repetition;
		this.izvodjacIme = izvodjacIme;
		this.emailKor = emailKor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cenaKolicina;
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + ((emailKor == null) ? 0 : emailKor.hashCode());
		result = prime * result + id;
		result = prime * result + idKor;
		result = prime * result + ((izvodjacIme == null) ? 0 : izvodjacIme.hashCode());
		result = prime * result + pesmaId;
		result = prime * result + ((pesmaNaziv == null) ? 0 : pesmaNaziv.hashCode());
		result = prime * result + repetition;
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
		PrometDTO other = (PrometDTO) obj;
		if (cenaKolicina != other.cenaKolicina)
			return false;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (emailKor == null) {
			if (other.emailKor != null)
				return false;
		} else if (!emailKor.equals(other.emailKor))
			return false;
		if (id != other.id)
			return false;
		if (idKor != other.idKor)
			return false;
		if (izvodjacIme == null) {
			if (other.izvodjacIme != null)
				return false;
		} else if (!izvodjacIme.equals(other.izvodjacIme))
			return false;
		if (pesmaId != other.pesmaId)
			return false;
		if (pesmaNaziv == null) {
			if (other.pesmaNaziv != null)
				return false;
		} else if (!pesmaNaziv.equals(other.pesmaNaziv))
			return false;
		if (repetition != other.repetition)
			return false;
		return true;
	}
	
	
}
