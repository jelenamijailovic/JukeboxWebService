package com.telnet.jukebox.webservice.service;

import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.database.IzvodjacDAO;
import com.telnet.jukebox.webservice.dto.IzvodjacDTO;
import com.telnet.jukebox.webservice.model.Izvodjac;

public class IzvodjacService {

	IzvodjacDAO dao = new IzvodjacDAO();

	public List<IzvodjacDTO> getIzvodjaci() throws ClassNotFoundException {
		List<IzvodjacDTO> list = new ArrayList<IzvodjacDTO>();

		for (int i = 0; i < dao.getIzvodjaci().size(); i++) {
			list.add(entityToDTO(dao.getIzvodjaci().get(i)));
		}

		return list;
	}

	public IzvodjacDTO getIzvodjac(int id) throws ClassNotFoundException {
		return entityToDTO(dao.getIzvodjac(id));
	}

	public IzvodjacDTO addIzvodjac(IzvodjacDTO izvodjac) throws ClassNotFoundException {
		return entityToDTO(dao.insertIzvodjac(DTOToEntity(izvodjac)));
	}

	public IzvodjacDTO updateIzvodjac(IzvodjacDTO izvodjac) throws ClassNotFoundException {
		return entityToDTO(dao.updateIzvodjac(DTOToEntity(izvodjac)));
	}

	public void removeIzvodjac(int id) throws ClassNotFoundException {
		dao.removeIzvodjac(id);
	}

	public Izvodjac DTOToEntity(IzvodjacDTO izvodjac) {
		Izvodjac entity = new Izvodjac();
		entity.setId(izvodjac.getId());
		entity.setIme(izvodjac.getIme());
		return entity;
	}

	public IzvodjacDTO entityToDTO(Izvodjac izvodjac) {
		IzvodjacDTO dto = new IzvodjacDTO();
		dto.setId(izvodjac.getId());
		dto.setIme(izvodjac.getIme());
		return dto;
	}
}
