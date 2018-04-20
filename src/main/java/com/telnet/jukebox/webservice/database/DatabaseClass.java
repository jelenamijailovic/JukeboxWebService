package com.telnet.jukebox.webservice.database;

import java.util.HashMap;
import java.util.Map;

import com.telnet.jukebox.webservice.model.Cena;
import com.telnet.jukebox.webservice.model.Izvodjac;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Zanr;

public class DatabaseClass {

	private Map<Long, Izvodjac> izvodjaci= new HashMap<>();
	private Map<Long, Zanr> zanrovi= new HashMap<>();
	private Map<Long, Pesma> pesme= new HashMap<>();
	private Map<Long, Cena> cene= new HashMap<>();
	
	public Map<Long, Izvodjac> getIzvodjaci() {
		return izvodjaci;
	}
	
	public Map<Long, Zanr> getZanrovi() {
		return zanrovi;
	}
	
	public Map<Long, Pesma> getPesme() {
		return pesme;
	}
	
	public Map<Long, Cena> getCene() {
		return cene;
	}
	
}
