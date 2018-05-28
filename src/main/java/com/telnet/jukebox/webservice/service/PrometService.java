package com.telnet.jukebox.webservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.HeaderParam;
import javax.xml.bind.DatatypeConverter;

import com.telnet.jukebox.webservice.database.PrometDAO;
import com.telnet.jukebox.webservice.dto.PesmaDTO;
import com.telnet.jukebox.webservice.dto.PrometDTO;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Promet;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class PrometService {

	PrometDAO dao = new PrometDAO();
	KorisnikService korisnikService= new KorisnikService();

	public List<PrometDTO> getSviPrometi() throws ClassNotFoundException {
		List<PrometDTO> list = new ArrayList<PrometDTO>();

		for (int i = 0; i < dao.getSvePromete().size(); i++) {
			list.add(entityToDTO(dao.getSvePromete().get(i)));
		}

		return list;
	}

	public List<PrometDTO> getSviPrometiPoPesmi(int pesmaId) throws ClassNotFoundException {
		List<PrometDTO> list = new ArrayList<PrometDTO>();

		for (int i = 0; i < dao.getPrometePoPesmi(pesmaId).size(); i++) {
			list.add(entityToDTO(dao.getPrometePoPesmi(pesmaId).get(i)));
		}

		return list;
	}

	public List<PrometDTO> getSviPrometiPoKorisniku(int korisnikId) throws ClassNotFoundException {
		List<PrometDTO> list = new ArrayList<PrometDTO>();

		for (int i = 0; i < dao.getPrometePoKorisniku(korisnikId).size(); i++) {
			list.add(entityToDTO(dao.getPrometePoKorisniku(korisnikId).get(i)));
		}

		return list;
	}

	public PrometDTO getPromet(int prometId) throws ClassNotFoundException {
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

	public PrometDTO addPromet(int pesmaId, int korisnikId, PrometDTO promet) throws ClassNotFoundException {
//
//		KorisnikDTO kor= korisnikService.
		
		java.util.Date datum = new java.util.Date();
		java.sql.Date date = new java.sql.Date(datum.getTime());

		promet.setPesmaId(pesmaId);
		
//		Jws<Claims> claims = Jwts.parser()
//				  .setSigningKey("sifra".getBytes())
//				  .parseClaimsJws(Authorization);
//		String id= claims.getBody().getId();
//		System.out.println(id);
//		Long idKor= Long.parseLong(id);
//		System.out.println(idKor);
		promet.setIdKor(korisnikId);
		
		promet.setDatum(date);

		PrometDTO promet1 = entityToDTO(dao.insertPromet(pesmaId, korisnikId, DTOToEntity(promet)));

		

		return promet1;
	}

	// public Promet updatePromet(Promet promet) throws ClassNotFoundException {
	// return dao.updatePromet(promet);
	// }

	public void deletePromet(int prometId) throws ClassNotFoundException {
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
