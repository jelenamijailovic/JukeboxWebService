package com.telnet.jukebox.webservice.service;

import java.util.List;

import com.telnet.jukebox.webservice.database.DAOPromet;
import com.telnet.jukebox.webservice.model.Promet;

public class PrometService {

	DAOPromet dao = new DAOPromet();

	public List<Promet> getSviPrometi() throws ClassNotFoundException {
		return dao.getSvePromete();
	}

	public List<Promet> getSviPrometiPoPesmi(Long pesmaId) throws ClassNotFoundException {
		return dao.getPrometePoPesmi(pesmaId);
	}

	public Promet getPromet(Long prometId) throws ClassNotFoundException {
		return dao.getPromet(prometId);
	}

	public Promet addPromet(Long pesmaId) throws ClassNotFoundException {
		Promet promet= new Promet();
		
		promet.setPesmaId(pesmaId);
		
		java.util.Date datum = new java.util.Date();

		java.sql.Date date = new java.sql.Date(datum.getTime());
		
		promet.setDatum(date);
		
		return dao.insertPromet(promet);
	}

	public Promet updatePromet(Promet promet) throws ClassNotFoundException {
		return dao.updatePromet(promet);
	}

	public void deletePromet(Long prometId) throws ClassNotFoundException {
		dao.removePromet(prometId);
	}

}
