package com.telnet.jukebox.webservice.service;

import java.util.List;

import com.telnet.jukebox.webservice.database.DAOCena;
import com.telnet.jukebox.webservice.model.Cena;

public class CenaService {

	DAOCena dao = new DAOCena();

	public List<Cena> getCene() throws ClassNotFoundException {
		return dao.getCene();
	}

	public Cena getCena(Long id) throws ClassNotFoundException {
		return dao.getCena(id);
	}

	public Cena addCena(Cena cena) throws ClassNotFoundException {
		return dao.insertCena(cena);
	}

	public Cena updateCena(Cena cena) throws ClassNotFoundException {
		return dao.updateCena(cena);
	}

	public void removeCena(Long id) throws ClassNotFoundException {
		dao.removeCena(id);
	}

}
