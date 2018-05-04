package com.telnet.jukebox.webservice.service;

import java.util.List;

import com.telnet.jukebox.webservice.database.DAOPesma;
import com.telnet.jukebox.webservice.model.Pesma;

public class PesmaService {

	DAOPesma dao = new DAOPesma();

	public List<Pesma> getSvePesmePoIzvodjacu(Long izvodjacId) throws ClassNotFoundException {
		return dao.getPesmePoIzvodjacu(izvodjacId);
	}

	public List<Pesma> getSvePesmePoZanru(Long zanrId) throws ClassNotFoundException {
		return dao.getPesmePoZanru(zanrId);
	}

	public List<Pesma> getSvePesmePoCeni(Long cenaId) throws ClassNotFoundException {
		return dao.getPesmePoCeni(cenaId);
	}

	public List<Pesma> getSvePesme() throws ClassNotFoundException {
		return dao.getSvePesme();
	}

	public Pesma getPesma(Long pesmaId) throws ClassNotFoundException {
		return dao.getPesma(pesmaId);
	}

	public Pesma addPesma(Long izvodjacId, Long zanrId, Long cenaId, Pesma pesma) throws ClassNotFoundException {
		return dao.insertPesma(izvodjacId, zanrId, cenaId, pesma);
	}

	public Pesma updatePesma(Pesma pesma) throws ClassNotFoundException {
		return dao.updatePesma(pesma);
	}

	public void deletePesma(Long pesmaId) throws ClassNotFoundException {
		dao.removePesma(pesmaId);
	}

}
