package com.telnet.jukebox.webservice.service;

import java.sql.SQLException;
import java.util.List;

import com.telnet.jukebox.webservice.database.DAOIzvodjac;
import com.telnet.jukebox.webservice.model.Izvodjac;

public class IzvodjacService {

	DAOIzvodjac dao = new DAOIzvodjac();

	public List<Izvodjac> getIzvodjaci() throws SQLException, ClassNotFoundException {
		return dao.getIzvodjaci();
	}

	public Izvodjac getIzvodjac(Long id) throws SQLException, ClassNotFoundException {
		return dao.getIzvodjac(id);
	}

	public Izvodjac addIzvodjac(Izvodjac izvodjac) throws SQLException, ClassNotFoundException {
		return dao.insertIzvodjac(izvodjac);
	}

	public Izvodjac updateIzvodjac(Izvodjac izvodjac) throws SQLException, ClassNotFoundException {
		return dao.updateIzvodjac(izvodjac);
	}

	public void removeIzvodjac(Long id) throws SQLException, ClassNotFoundException {
		dao.removeIzvodjac(id);
	}

}
