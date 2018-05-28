package com.telnet.jukebox.webservice.service;

import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.database.PesmaDAO;
import com.telnet.jukebox.webservice.dto.PesmaDTO;
import com.telnet.jukebox.webservice.model.Pesma;

public class PesmaService {

	PesmaDAO dao = new PesmaDAO();

	public List<PesmaDTO> getSvePesmePoIzvodjacu(int izvodjacId) throws ClassNotFoundException {
		List<PesmaDTO> list = new ArrayList<PesmaDTO>();

		for (int i = 0; i < dao.getPesmePoIzvodjacu(izvodjacId).size(); i++) {
			list.add(entityToDTO(dao.getPesmePoIzvodjacu(izvodjacId).get(i)));
		}

		return list;
	}

	public List<PesmaDTO> getSvePesmePoZanru(int zanrId) throws ClassNotFoundException {
		List<PesmaDTO> list = new ArrayList<PesmaDTO>();

		for (int i = 0; i < dao.getPesmePoZanru(zanrId).size(); i++) {
			list.add(entityToDTO(dao.getPesmePoZanru(zanrId).get(i)));
		}

		return list;
	}

	public List<PesmaDTO> getSvePesmePoCeni(int cenaId) throws ClassNotFoundException {
		List<PesmaDTO> list = new ArrayList<PesmaDTO>();

		for (int i = 0; i < dao.getPesmePoCeni(cenaId).size(); i++) {
			list.add(entityToDTO(dao.getPesmePoCeni(cenaId).get(i)));
		}

		return list;
	}

	public List<PesmaDTO> getSvePesme() throws ClassNotFoundException {
		List<PesmaDTO> list = new ArrayList<PesmaDTO>();

		for (int i = 0; i < dao.getSvePesme().size(); i++) {
			list.add(entityToDTO(dao.getSvePesme().get(i)));
		}

		return list;
	}
	
	public List<PesmaDTO> getSvePesmePagination(int page) throws ClassNotFoundException {
		List<PesmaDTO> list = new ArrayList<PesmaDTO>();

		for (int i = 0; i < dao.getSvePesmePagination(page).size(); i++) {
			list.add(entityToDTO(dao.getSvePesmePagination(page).get(i)));
		}

		return list;
	}

	public PesmaDTO getPesma(int pesmaId) throws ClassNotFoundException {
		return entityToDTO(dao.getPesma(pesmaId));

	}

	public PesmaDTO addPesma(int izvodjacId, int cenaId, PesmaDTO pesma) throws ClassNotFoundException {
		return entityToDTO(dao.insertPesma(izvodjacId, cenaId, DTOToEntity(pesma)));
	}

	public PesmaDTO updatePesma(PesmaDTO pesma) throws ClassNotFoundException {
		return entityToDTO(dao.updatePesma(DTOToEntity(pesma)));
	}

	public void deletePesma(int pesmaId) throws ClassNotFoundException {
		dao.removePesma(pesmaId);
	}

	public Pesma DTOToEntity(PesmaDTO pesma) {
		Pesma entity = new Pesma();
		entity.setBrojStrana(pesma.getBrojStrana());
		entity.setId(pesma.getId());
		entity.setNaziv(pesma.getNaziv());
		entity.setIzvodjacIme(pesma.getIzvodjacIme());
		entity.setZanrIme(pesma.getZanrIme());
		entity.setCenaKolicina(pesma.getCenaKolicina());
		return entity;
	}

	public PesmaDTO entityToDTO(Pesma pesma) {
		PesmaDTO dto = new PesmaDTO();
		dto.setBrojStrana(pesma.getBrojStrana());
		dto.setId(pesma.getId());
		dto.setNaziv(pesma.getNaziv());
		dto.setIzvodjacIme(pesma.getIzvodjacIme());
		dto.setZanrIme(pesma.getZanrIme());
		dto.setCenaKolicina(pesma.getCenaKolicina());
		return dto;
	}

}
