package com.telnet.jukebox.webservice.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.json.JsonException;
import javax.ws.rs.core.Application;

import com.telnet.jukebox.webservice.database.DAOKorisnik;
import com.telnet.jukebox.webservice.dto.KorisnikDTO;
import com.telnet.jukebox.webservice.model.Korisnik;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class KorisnikService extends Application {

	DAOKorisnik dao = new DAOKorisnik();

	public List<KorisnikDTO> getKorisnici() throws ClassNotFoundException {
		List<KorisnikDTO> list = new ArrayList<KorisnikDTO>();

		for (int i = 0; i < dao.getKorisnici().size(); i++) {
			list.add(entityToDTO(dao.getKorisnici().get(i)));
		}

		// for (int i = 0; i < list.size(); i++) {
		// list.get(i).setSifra(encryptPassword(list.get(i).getSifra()));
		// }
		return list;
	}

	public KorisnikDTO getKorisnik(Long korisnikId) throws ClassNotFoundException {
		KorisnikDTO korisnik = entityToDTO(dao.getKorisnik(korisnikId));
		korisnik.setSifra(encryptPassword(korisnik.getSifra()));
		return korisnik;
	}

	public KorisnikDTO insertKorisnik(KorisnikDTO korisnik) throws ClassNotFoundException, SQLException {
		KorisnikDTO korisnikNovi = entityToDTO(dao.insertKorisnik(DTOToEntity(korisnik)));

		return korisnikNovi;
	}

	public KorisnikDTO updateKorisnik(KorisnikDTO korisnik) throws ClassNotFoundException {
		KorisnikDTO korisnikNovi = entityToDTO(dao.updateKorisnik(DTOToEntity(korisnik)));
		korisnikNovi.setSifra(encryptPassword(korisnikNovi.getSifra()));
		return korisnikNovi;
	}

	public void deleteKorisnik(Long korisnikId) throws ClassNotFoundException {
		dao.deleteKorisnik(korisnikId);
	}

	private static String encryptPassword(String password) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public String login(String email, String sifra) throws ClassNotFoundException, JsonException {
		KorisnikDTO korisnik = entityToDTO(dao.loginKorisnik(email, sifra));

		String jwt = "";

		if (korisnik.getEmail() != null) {
			Long time = System.currentTimeMillis();
			String id = korisnik.getId().toString();

			jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, sifra.getBytes()).setId(id)
					.setSubject(korisnik.getEmail()).setExpiration(new Date(time + 15000)).compact();
			// JsonObject json = Json.createObjectBuilder().add("JWT", jwt).build();
			return jwt;
		}

		return jwt;
	}

	public Korisnik DTOToEntity(KorisnikDTO korisnik) {
		Korisnik entity = new Korisnik();
		entity.setId(korisnik.getId());
		entity.setSifra(korisnik.getSifra());
		entity.setEmail(korisnik.getEmail());
		return entity;
	}

	public KorisnikDTO entityToDTO(Korisnik korisnik) {
		KorisnikDTO dto = new KorisnikDTO();
		dto.setId(korisnik.getId());
		dto.setSifra(korisnik.getSifra());
		dto.setEmail(korisnik.getEmail());
		return dto;
	}

}
