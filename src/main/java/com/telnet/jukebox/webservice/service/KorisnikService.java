package com.telnet.jukebox.webservice.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.json.JsonException;
import javax.ws.rs.core.Application;

import com.telnet.jukebox.webservice.database.DAOKorisnik;
import com.telnet.jukebox.webservice.model.Korisnik;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class KorisnikService extends Application {

	DAOKorisnik dao = new DAOKorisnik();

	public List<Korisnik> getKorisnici() throws ClassNotFoundException {
		List<Korisnik> korisnici = dao.getKorisnici();
		for (int i = 0; i < korisnici.size(); i++) {
			korisnici.get(i).setSifra(encryptPassword(korisnici.get(i).getSifra()));
		}
		return korisnici;
	}

	public Korisnik getKorisnik(Long korisnikId) throws ClassNotFoundException {
		Korisnik korisnik = dao.getKorisnik(korisnikId);
		korisnik.setSifra(encryptPassword(korisnik.getSifra()));
		return korisnik;
	}

	public Korisnik insertKorisnik(Korisnik korisnik) throws ClassNotFoundException, SQLException {
		Korisnik korisnikNovi = dao.insertKorisnik(korisnik);

		return korisnikNovi;
	}

	public Korisnik updateKorisnik(Korisnik korisnik) throws ClassNotFoundException {
		Korisnik korisnikNovi = dao.updateKorisnik(korisnik);
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
		Korisnik korisnik = dao.loginKorisnik(email, sifra);

		String jwt = "";

		if (korisnik.getEmail() != null) {
			Long time = System.currentTimeMillis();
			String id = korisnik.getId().toString();

			jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, sifra.getBytes()).setId(id)
					.setSubject(korisnik.getEmail()).setExpiration(new Date(time + 900000)).compact();
			// JsonObject json = Json.createObjectBuilder().add("JWT", jwt).build();
			return jwt;
		}

		return jwt;
	}

}
