package com.telnet.jukebox.webservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.telnet.jukebox.webservice.database.DatabaseClass;
import com.telnet.jukebox.webservice.model.Izvodjac;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Zanr;

public class PesmaService {
	
	private Map<Long, Zanr> zanrovi= new DatabaseClass().getZanrovi();
	private Map<Long, Izvodjac> izvodjaci= new DatabaseClass().getIzvodjaci();
	
	
//	public Izvodjac getIzvodjac(String pesmaNaziv, String ime) {
//		Map<String, Izvodjac> izvodjaci= pesme.get(pesmaNaziv).getIzvodjac();
//		return izvodjaci.get(ime);
//	}
//	
//	public Izvodjac addIzvodjac(String pesmaNaziv, Izvodjac izvodjac) {
//		Map<String, Izvodjac> izvodjaci= pesme.get(pesmaNaziv).getIzvodjac();
//		izvodjac.setId(izvodjaci.size()+1);
//		izvodjaci.put(izvodjac.getIme(), izvodjac);
//		return izvodjac;
//	}
//	
//	public Izvodjac updateIzvodjac(String pesmaNaziv, Izvodjac izvodjac) {
//		Map<String, Izvodjac> izvodjaci= pesme.get(pesmaNaziv).getIzvodjac();
//		if(izvodjac.getIme().isEmpty()) {
//			return null;
//		}
//		izvodjaci.put(izvodjac.getIme(), izvodjac);
//		return izvodjac;
//	}
//	
//	public Izvodjac removeIzvodjac(String pesmaNaziv, String ime) {
//		Map<String, Izvodjac> izvodjaci= pesme.get(pesmaNaziv).getIzvodjac();
//		return izvodjaci.remove(ime);
//	}
	
	public List<Pesma> getSvePesmePoIzvodjacu(Long izvodjacId) {
		Map<Long, Pesma> pesme= izvodjaci.get(izvodjacId).getPesme();
		return new ArrayList<Pesma>(pesme.values());
	}
	
	public List<Pesma> getSvePesmePoZanru(Long zanrId) {
		Map<Long, Pesma> pesme= zanrovi.get(zanrId).getPesme();
		return new ArrayList<Pesma>(pesme.values());
	}
	
//	public Map<String, Pesma> getSvePesmePoIzvodjacu(String izvodjacIme) {
//		Izvodjac izvodjac= izvodjaci.get(izvodjacIme);
//		Map<String, Pesma> pesme= izvodjaci.get(izvodjacIme).getPesme();
//		return pesme; 
//	}
	
//	public Map<String, Pesma> getSvePesmePoZanru(String zanrNaziv) {
//		Zanr zanr= zanrovi.get(zanrNaziv);
//		Map<String, Pesma> pesme= zanrovi.get(zanrNaziv).getPesme();
//		return pesme; 
//	}
	
	public Pesma getPesmaPoIzvodjacu(Long izvodjacId, Long pesmaId) {
		Izvodjac izvodjac= izvodjaci.get(izvodjacId);
		Map<Long, Pesma> pesme= izvodjaci.get(izvodjacId).getPesme();
		Pesma pesma= pesme.get(pesmaId);
		System.out.println("Naziv pesme :"+pesma);
		return pesma; 
	}
	
	public Pesma getPesmaPoZanru(Long zanrId, Long pesmaId) {
		Zanr zanr= zanrovi.get(zanrId);
		Map<Long, Pesma> pesme= zanrovi.get(zanrId).getPesme();
		Pesma pesma= pesme.get(pesmaId);
		System.out.println("Naziv pesme :"+pesma);
		return pesma; 
	}
	
	public Pesma addPesmaPoIzvodjacu(Long izvodjacId, Pesma pesma) {
		Map<Long, Pesma> pesme= izvodjaci.get(izvodjacId).getPesme();
		pesma.setId(pesme.size()+1);
		pesme.put(pesma.getId(), pesma);
		return pesma;
	}
	
	public Pesma addPesmaPoZanru(Long zanrId, Pesma pesma) {
		Map<Long, Pesma> pesme= zanrovi.get(zanrId).getPesme();
		pesma.setId(pesme.size()+1);
		pesme.put(pesma.getId(), pesma);
		return pesma;
	}
	
	public Pesma updatePesmaPoIzvodjacu(Long izvodjacId, Pesma pesma) {
		Map<Long, Pesma> pesme= izvodjaci.get(izvodjacId).getPesme();
		if(pesma.getId()<=0) {
			return null;
		}
		pesme.put(pesma.getId(), pesma);
		return pesma;
	}
	
	public Pesma updatePesmaPoZanru(Long zanrId, Pesma pesma) {
		Map<Long, Pesma> pesme= izvodjaci.get(zanrId).getPesme();
		if(pesma.getId()<=0) {
			return null;
		}
		pesme.put(pesma.getId(), pesma);
		return pesma;
	}
	
	public Pesma deletePesmaPoIzvodjacu(Long izvodjacId, Long pesmaId) {
		Map<Long, Pesma> pesme= izvodjaci.get(izvodjacId).getPesme();
		return pesme.remove(pesmaId);
	}
	
	public Pesma deletePesmaPoZanru(Long zanrId, Long pesmaId) {
		Map<Long, Pesma> pesme= zanrovi.get(zanrId).getPesme();
		return pesme.remove(pesmaId);
	}
	
}
