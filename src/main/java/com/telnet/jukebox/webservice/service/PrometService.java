package com.telnet.jukebox.webservice.service;

import java.sql.SQLException;
import java.util.List;

import com.telnet.jukebox.webservice.database.DAOPromet;
import com.telnet.jukebox.webservice.model.Promet;

public class PrometService {

	DAOPromet dao = new DAOPromet();

	public List<Promet> getSviPrometi() throws ClassNotFoundException, SQLException {
		return dao.getSvePromete();
	}

	public List<Promet> getSviPrometiPoCeni(Long cenaId) throws ClassNotFoundException, SQLException {
		return dao.getPrometePoCeni(cenaId);
	}

	public List<Promet> getSviPrometiPoPesmi(Long pesmaId) throws ClassNotFoundException, SQLException {
		return dao.getPrometePoPesmi(pesmaId);
	}

	public Promet getPromet(Long prometId) throws ClassNotFoundException, SQLException {
		return dao.getPromet(prometId);
	}

	public Promet addPromet(Long pesmaId, Long cenaId, Promet promet) throws ClassNotFoundException, SQLException {
		return dao.insertPromet(pesmaId, cenaId, promet);
	}

	public Promet updatePromet(Promet promet) throws ClassNotFoundException, SQLException {
		return dao.updatePromet(promet);
	}

	public void deletePromet(Long prometId) throws ClassNotFoundException, SQLException {
		dao.removePromet(prometId);
	}

}
