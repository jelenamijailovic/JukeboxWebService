package com.telnet.jukebox.webservice.service;

import java.util.List;

import com.telnet.jukebox.webservice.database.DAOZanr;
import com.telnet.jukebox.webservice.model.Zanr;

public class ZanrService {

	DAOZanr dao = new DAOZanr();

	public List<Zanr> getZanrovi() throws ClassNotFoundException {
		return dao.getZanrovi();
	}

	public Zanr getZanr(Long id) throws ClassNotFoundException {
		return dao.getZanr(id);
	}

	public Zanr addZanr(Zanr zanr) throws ClassNotFoundException {
		return dao.insertZanr(zanr);
	}

	public Zanr updateZanr(Zanr zanr) throws ClassNotFoundException {
		return dao.updateZanr(zanr);
	}

	public void removeZanr(Long id) throws ClassNotFoundException {
			dao.removeZanr(id);
	}

}
