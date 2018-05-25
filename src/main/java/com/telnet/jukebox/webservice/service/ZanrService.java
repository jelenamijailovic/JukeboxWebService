package com.telnet.jukebox.webservice.service;

import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.database.ZanrDAO;
import com.telnet.jukebox.webservice.dto.ZanrDTO;
import com.telnet.jukebox.webservice.model.Zanr;

public class ZanrService {

	ZanrDAO dao = new ZanrDAO();

	public List<ZanrDTO> getZanrovi() throws ClassNotFoundException {
		List<ZanrDTO> list = new ArrayList<ZanrDTO>();

		for (int i = 0; i < dao.getZanrovi().size(); i++) {
			list.add(entityToDTO(dao.getZanrovi().get(i)));
		}

		return list;
	}

	public ZanrDTO getZanr(int id) throws ClassNotFoundException {
		return entityToDTO(dao.getZanr(id));
	}

	public ZanrDTO addZanr(ZanrDTO zanr) throws ClassNotFoundException {
		return entityToDTO(dao.insertZanr(DTOToEntity(zanr)));
	}

	public ZanrDTO updateZanr(ZanrDTO zanr) throws ClassNotFoundException {
		return entityToDTO(dao.updateZanr(DTOToEntity(zanr)));
	}

	public void removeZanr(int id) throws ClassNotFoundException {
		dao.removeZanr(id);
	}

	public Zanr DTOToEntity(ZanrDTO zanr) {
		Zanr entity = new Zanr();
		entity.setId(zanr.getId());
		entity.setNaziv(zanr.getNaziv());
		return entity;
	}

	public ZanrDTO entityToDTO(Zanr zanr) {
		ZanrDTO dto = new ZanrDTO();
		dto.setId(zanr.getId());
		dto.setNaziv(zanr.getNaziv());
		return dto;
	}
}
