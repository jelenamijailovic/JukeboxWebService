package com.telnet.jukebox.webservice.service;

import java.sql.SQLException;
import java.util.List;

import com.telnet.jukebox.webservice.database.DAOPesma;
import com.telnet.jukebox.webservice.model.Pesma;

public class PesmaService {

	DAOPesma dao = new DAOPesma();

	public List<Pesma> getSvePesmePoIzvodjacu(Long izvodjacId) throws ClassNotFoundException, SQLException {
		return dao.getPesmePoIzvodjacu(izvodjacId);
	}

	public List<Pesma> getSvePesmePoZanru(Long zanrId) throws ClassNotFoundException, SQLException {
		return dao.getPesmePoZanru(zanrId);
	}
	
	public List<Pesma> getSvePesmePoCeni(Long cenaId) throws ClassNotFoundException, SQLException {
		return dao.getPesmePoCeni(cenaId);
	}

	public List<Pesma> getSvePesme() throws ClassNotFoundException, SQLException {
		return dao.getSvePesme();
	}

	public Pesma getPesma(Long pesmaId) throws ClassNotFoundException, SQLException {
		return dao.getPesma(pesmaId);
	}

	public Pesma addPesma(Long izvodjacId, Long zanrId, Long cenaId, Pesma pesma) throws ClassNotFoundException, SQLException {
		return dao.insertPesma(izvodjacId, zanrId, cenaId, pesma);
	}

	public Pesma updatePesma(Pesma pesma) throws ClassNotFoundException, SQLException {
		return dao.updatePesma(pesma);
	}

	public void deletePesma(Long pesmaId) throws ClassNotFoundException, SQLException {
		dao.removePesma(pesmaId);
	}

}
