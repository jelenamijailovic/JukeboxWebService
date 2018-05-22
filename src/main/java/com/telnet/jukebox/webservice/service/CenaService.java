package com.telnet.jukebox.webservice.service;

import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.database.DAOCena;
import com.telnet.jukebox.webservice.dto.CenaDTO;
import com.telnet.jukebox.webservice.model.Cena;

public class CenaService {

	DAOCena dao = new DAOCena();

	public List<CenaDTO> getCene() throws ClassNotFoundException {
		List<CenaDTO> list = new ArrayList<CenaDTO>();

		for (int i = 0; i < dao.getCene().size(); i++) {
			list.add(entityToDTO(dao.getCene().get(i)));
		}

		return list;
	}

	public CenaDTO getCena(Long id) throws ClassNotFoundException {
		return entityToDTO(dao.getCena(id));
	}

	public CenaDTO addCena(CenaDTO cena) throws ClassNotFoundException {
		return entityToDTO(dao.insertCena(DTOToEntity(cena)));
	}

	public CenaDTO updateCena(CenaDTO cena) throws ClassNotFoundException {
		return entityToDTO(dao.updateCena(DTOToEntity(cena)));
	}

	public void removeCena(Long id) throws ClassNotFoundException {
		dao.removeCena(id);
	}

	public Cena DTOToEntity(CenaDTO cena) {
		Cena entity = new Cena();
		entity.setId(cena.getId());
		entity.setKolicina(cena.getKolicina());
		return entity;
	}

	public CenaDTO entityToDTO(Cena cena) {
		CenaDTO dto = new CenaDTO();
		dto.setId(cena.getId());
		dto.setKolicina(cena.getKolicina());
		return dto;
	}
}
