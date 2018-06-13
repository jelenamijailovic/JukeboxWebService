package com.telnet.jukebox.webservice.dto;

public class ZanrDTO {

	private int id;
	private String naziv;

	public ZanrDTO() {
	}

	public int getId() {
		return id;
	}

	public ZanrDTO(int id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
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
		ZanrDTO other = (ZanrDTO) obj;
		if (id != other.id)
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		return true;
	}
	

}
