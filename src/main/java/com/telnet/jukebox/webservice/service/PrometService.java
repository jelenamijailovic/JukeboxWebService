package com.telnet.jukebox.webservice.service;

import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.database.DAOPromet;
import com.telnet.jukebox.webservice.dto.PrometDTO;
import com.telnet.jukebox.webservice.model.Promet;

public class PrometService {

	DAOPromet dao = new DAOPromet();

	public List<PrometDTO> getSviPrometi() throws ClassNotFoundException {
		List<PrometDTO> list = new ArrayList<PrometDTO>();

		for (int i = 0; i < dao.getSvePromete().size(); i++) {
			list.add(entityToDTO(dao.getSvePromete().get(i)));
		}

		return list;
	}

	public List<PrometDTO> getSviPrometiPoPesmi(Long pesmaId) throws ClassNotFoundException {
		List<PrometDTO> list = new ArrayList<PrometDTO>();

		for (int i = 0; i < dao.getPrometePoPesmi(pesmaId).size(); i++) {
			list.add(entityToDTO(dao.getPrometePoPesmi(pesmaId).get(i)));
		}

		return list;
	}

	public List<PrometDTO> getSviPrometiPoKorisniku(Long korisnikId) throws ClassNotFoundException {
		List<PrometDTO> list = new ArrayList<PrometDTO>();

		for (int i = 0; i < dao.getPrometePoKorisniku(korisnikId).size(); i++) {
			list.add(entityToDTO(dao.getPrometePoKorisniku(korisnikId).get(i)));
		}

		return list;
	}

	public PrometDTO getPromet(Long prometId) throws ClassNotFoundException {
		return entityToDTO(dao.getPromet(prometId));
	}

	public List<PrometDTO> getTop5Songs() throws ClassNotFoundException {
		List<PrometDTO> list = new ArrayList<PrometDTO>();

		for (int i = 0; i < dao.getTop5Songs().size(); i++) {
			list.add(entityToDTO(dao.getTop5Songs().get(i)));
		}

		return list;
	}

	public List<PrometDTO> getTop5Artists() throws ClassNotFoundException {
		List<PrometDTO> list = new ArrayList<PrometDTO>();

		for (int i = 0; i < dao.getTop5Artists().size(); i++) {
			list.add(entityToDTO(dao.getTop5Artists().get(i)));
		}

		return list;
	}

	public PrometDTO addPromet(Long pid, Long kid, PrometDTO promet) throws ClassNotFoundException {

		java.util.Date datum = new java.util.Date();
		java.sql.Date date = new java.sql.Date(datum.getTime());

		promet.setDatum(date);

		PrometDTO promet1 = entityToDTO(dao.insertPromet(pid, kid, DTOToEntity(promet)));

		promet1.setPesmaId(pid);
		promet1.setIdKor(kid);

		return promet1;
	}

	// public Promet updatePromet(Promet promet) throws ClassNotFoundException {
	// return dao.updatePromet(promet);
	// }

	public void deletePromet(Long prometId) throws ClassNotFoundException {
		dao.removePromet(prometId);
	}

	public Promet DTOToEntity(PrometDTO promet) {
		Promet entity = new Promet();
		entity.setId(promet.getId());
		entity.setDatum(promet.getDatum());
		entity.setPesmaNaziv(promet.getPesmaNaziv());
		entity.setCenaKolicina(promet.getCenaKolicina());
		entity.setRepetition(promet.getRepetition());
		entity.setIzvodjacIme(promet.getIzvodjacIme());
		entity.setEmailKor(promet.getEmailKor());
		return entity;
	}

	public PrometDTO entityToDTO(Promet promet) {
		PrometDTO dto = new PrometDTO();
		dto.setId(promet.getId());
		dto.setDatum(promet.getDatum());
		dto.setPesmaNaziv(promet.getPesmaNaziv());
		dto.setCenaKolicina(promet.getCenaKolicina());
		dto.setRepetition(promet.getRepetition());
		dto.setIzvodjacIme(promet.getIzvodjacIme());
		dto.setEmailKor(promet.getEmailKor());
		return dto;
	}

}
