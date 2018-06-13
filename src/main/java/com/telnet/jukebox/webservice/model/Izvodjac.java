package com.telnet.jukebox.webservice.model;

public class Izvodjac {

	private int id;
	private String ime;

	public Izvodjac() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Izvodjac(int id, String ime) {
		super();
		this.id = id;
		this.ime = ime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
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
		Izvodjac other = (Izvodjac) obj;
		if (id != other.id)
			return false;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		return true;
	}
	


}
