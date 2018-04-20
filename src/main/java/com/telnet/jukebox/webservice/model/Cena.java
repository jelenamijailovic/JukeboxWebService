package com.telnet.jukebox.webservice.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

public class Cena {

	private long id;
	private long kolicina;
	private Map<Long, Promet> prometi= new HashMap<>();

	
	public Cena() {}

	public Cena(long id, long kolicina) {
		super();
		this.id = id;
		this.kolicina = kolicina;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKolicina() {
		return kolicina;
	}

	public void setKolicina(long kolicina) {
		this.kolicina = kolicina;
	}
	
	@XmlTransient
	public Map<Long, Promet> getPrometi() {
		return prometi;
	}

	public void setPrometi(Map<Long, Promet> prometi) {
		this.prometi = prometi;
	}

	
}
