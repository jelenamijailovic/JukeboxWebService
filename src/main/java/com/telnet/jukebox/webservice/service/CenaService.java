package com.telnet.jukebox.webservice.service;

import java.sql.SQLException;
import java.util.List;

import com.telnet.jukebox.webservice.database.DAOCena;
import com.telnet.jukebox.webservice.model.Cena;

public class CenaService {

	DAOCena dao = new DAOCena();

	public List<Cena> getCene() throws ClassNotFoundException, SQLException {
		return dao.getCene();
	}

	public Cena getCena(Long id) throws ClassNotFoundException, SQLException {
		return dao.getCena(id);
	}

	public Cena addCena(Cena cena) throws ClassNotFoundException, SQLException {
		return dao.insertCena(cena);
	}

	public Cena updateCena(Cena cena) throws ClassNotFoundException, SQLException {
		return dao.updateCena(cena);
	}

	public void removeCena(Long id) throws ClassNotFoundException, SQLException {
		dao.removeCena(id);
	}

}
