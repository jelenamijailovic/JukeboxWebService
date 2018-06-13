package com.telnet.jukebox.webservice.model;

public class Cena {

	private int id;
	private int kolicina;

	public Cena() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public Cena(int id, int kolicina) {
		super();
		this.id = id;
		this.kolicina = kolicina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + kolicina;
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
		Cena other = (Cena) obj;
		if (id != other.id)
			return false;
		if (kolicina != other.kolicina)
			return false;
		return true;
	}

}
