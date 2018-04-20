package com.telnet.jukebox.webservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.telnet.jukebox.webservice.database.DatabaseClass;
import com.telnet.jukebox.webservice.model.Cena;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Promet;

public class PrometService {
	
	private Map<Long, Pesma> pesme= new DatabaseClass().getPesme();
	private Map<Long, Cena> cene= new DatabaseClass().getCene();
	
//	public Map<Date, Promet> getSviPrometiPoPesmi(String pesmaNaziv) {
//		Pesma pesma= pesme.get(pesmaNaziv);
//		Map<Date, Promet> prometi= pesme.get(pesmaNaziv).getPrometi();
//		return prometi; 
//	}
//	
//	public Map<Date, Promet> getSviPrometiPoCeni(Long cenaKolicina) {
//		Cena cena= cene.get(cenaKolicina);
//		Map<Date, Promet> prometi= cene.get(cenaKolicina).getPrometi();
//		return prometi; 
//	}
	
	public List<Promet> getSviPrometiPoPesmi(Long pesmaId) {
		Map<Long, Promet> prometi= pesme.get(pesmaId).getPrometi();
		return new ArrayList<Promet>(prometi.values());
	}
	
	public List<Promet> getSviPrometiPoCeni(Long cenaId) {
		Map<Long, Promet> prometi= cene.get(cenaId).getPrometi();
		return new ArrayList<Promet>(prometi.values());
	}
	
	public Promet getPrometPoPesmi(Long pesmaId, Long prometId) {
		Pesma pesma= pesme.get(pesmaId);
		Map<Long, Promet> prometi= pesme.get(pesmaId).getPrometi();
		Promet promet= prometi.get(prometId);
		System.out.println("Datum prometa :"+promet);
		return promet; 
	}
	
	public Promet getPrometPoCeni(Long cenaId,  Long prometId) {
		Cena cena= cene.get(cenaId);
		Map<Long, Promet> prometi= cene.get(cenaId).getPrometi();
		Promet promet= prometi.get(prometId);
		System.out.println("Datum prometa :"+promet);
		return promet; 
	}
	
	public Promet addPrometZaPesmu(Long pesmaId, Promet promet) {
		Map<Long, Promet> prometi= pesme.get(pesmaId).getPrometi();
		promet.setId(prometi.size()+1);
		prometi.put(promet.getId(), promet);
		return promet;
	}
	
	public Promet addPrometZaCenu(Long cenaId, Promet promet) {
		Map<Long, Promet> prometi= cene.get(cenaId).getPrometi();
		promet.setId(prometi.size()+1);
		prometi.put(promet.getId(), promet);
		return promet;
	}
	
	public Promet updatePrometPoPesmi(Long pesmaId, Promet promet) {
		Map<Long, Promet> prometi= pesme.get(pesmaId).getPrometi();
		if(promet.getId()<=0) {
			return null;
		}
		prometi.put(promet.getId(), promet);
		return promet;
	}
	
	public Promet updatePrometPoCeni(Long cenaId, Promet promet) {
		Map<Long, Promet> prometi= pesme.get(cenaId).getPrometi();
		if(promet.getId()<=0) {
			return null;
		}
		prometi.put(promet.getId(), promet);
		return promet;
	}
	
	public Promet deletePrometPoPesmi(Long pesmaId, Long prometId) {
		Map<Long, Promet> prometi= pesme.get(pesmaId).getPrometi();
		return prometi.remove(prometId);
	}
	
	public Promet deletePrometPoCeni(Long cenaId, Long prometId) {
		Map<Long, Promet> prometi= pesme.get(cenaId).getPrometi();
		return prometi.remove(prometId);
	}

}
