package com.telnet.jukebox.webservice.service;

import java.util.List;

import com.telnet.jukebox.webservice.database.DAOIzvodjac;
import com.telnet.jukebox.webservice.model.Izvodjac;

public class IzvodjacService {

	DAOIzvodjac dao = new DAOIzvodjac();

	public List<Izvodjac> getIzvodjaci() throws ClassNotFoundException {
		return dao.getIzvodjaci();
	}

	public Izvodjac getIzvodjac(Long id) throws ClassNotFoundException {
		return dao.getIzvodjac(id);
	}

	public Izvodjac addIzvodjac(Izvodjac izvodjac) throws ClassNotFoundException {
		return dao.insertIzvodjac(izvodjac);
	}

	public Izvodjac updateIzvodjac(Izvodjac izvodjac) throws ClassNotFoundException {
		return dao.updateIzvodjac(izvodjac);
	}

	public void removeIzvodjac(Long id) throws ClassNotFoundException {
		dao.removeIzvodjac(id);
	}

}
