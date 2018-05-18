package com.telnet.jukebox.webservice.database;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import com.telnet.jukebox.webservice.model.Korisnik;

public class DAOKorisnik {

	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Korisnik> getKorisnici() throws ClassNotFoundException {
		List<Korisnik> korisnici = new ArrayList<>();

		try {
			stmt = DatabaseConnector.conStat().createStatement();
			resultSet = stmt.executeQuery("select * from korisnici");
			while (resultSet.next()) {
				Korisnik korisnik = new Korisnik();
				korisnik.setId(resultSet.getLong(1));
				korisnik.setEmail(resultSet.getString(2));
				korisnik.setSifra(resultSet.getString(3));
				korisnici.add(korisnik);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return korisnici;
	}

	public Korisnik getKorisnik(Long korisnikId) throws ClassNotFoundException {
		Korisnik korisnik = new Korisnik();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("select * from korisnici where korisnici_id= ?");
			prepStmt.setLong(1, korisnikId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				korisnik.setId(resultSet.getLong(1));
				korisnik.setEmail(resultSet.getString(2));
				korisnik.setSifra(resultSet.getString(3));
				return korisnik;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			korisnik.setSifra(null);
			korisnik.setEmail(null);
		}

		return korisnik;
	}

	public Korisnik insertKorisnik(Korisnik korisnik) throws ClassNotFoundException, SQLException {
		try {
			prepStmt = DatabaseConnector.conStat()
					.prepareStatement("insert into korisnici (korisnici_email, korisnici_sifra) values (?,?)");
			prepStmt.setString(1, korisnik.getEmail());
			prepStmt.setString(2, encryptPassword(korisnik.getSifra()));
			prepStmt.executeUpdate();
			resultSet = prepStmt.getGeneratedKeys();
			if (resultSet.next()) {
				korisnik.setId(resultSet.getLong(1));
			}
		} catch (IOException e) {
			System.out.println("insert");
			e.printStackTrace();
		}

		return korisnik;
	}

	public Korisnik loginKorisnik(String email, String sifra) throws ClassNotFoundException {
		Korisnik korisnik = new Korisnik();
		String sifra1 = encryptPassword(sifra);

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("select * from korisnici where korisnici_email= ?");
			prepStmt.setString(1, email);
			resultSet = prepStmt.executeQuery();

			while (resultSet.next()) {
				korisnik.setId(resultSet.getLong(1));
				korisnik.setEmail(resultSet.getString(2));
				korisnik.setSifra(resultSet.getString(3));

			}
			String korSifra = korisnik.getSifra();

			if (korSifra.equals(sifra1)) {
				korisnik.setSifra(sifra);
			} else {
				System.out.println("Pogresna sifra");
				korisnik.setSifra(null);
				korisnik.setEmail(null);
			}

		} catch (SQLException e) {

		} catch (IOException e) {
			System.out.println("iocatch");
			korisnik.setSifra(null);
			korisnik.setEmail(null);
		}

		return korisnik;
	}

	public Korisnik updateKorisnik(Korisnik korisnik) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement(
					"update korisnici set korisnici_sifra=?, korisnici_email=? where korisnici_id= ?");
			prepStmt.setString(1, korisnik.getSifra());
			prepStmt.setString(2, korisnik.getEmail());
			prepStmt.setLong(3, korisnik.getId());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return korisnik;
	}

	public void deleteKorisnik(Long korisnikId) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("delete from korisnici where korisnici_id= ?");
			prepStmt.setLong(1, korisnikId);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

}
