package com.telnet.jukebox.webservice.database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.model.Pesma;

public class DAOPesma {

	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Pesma> getSvePesme() throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<>();

		try {
			stmt = DatabaseConnector.conStat().createStatement();
			resultSet = stmt.executeQuery(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on p.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id");
			while (resultSet.next()) {
				Pesma pesma = new Pesma();
				pesma.setId(resultSet.getLong(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getLong(5));
				pesme.add(pesma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesme;
	}

	public List<Pesma> getPesmePoIzvodjacu(Long izvodjacId) throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<>();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on p.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id where i.izvodjaci_id= ?");
			prepStmt.setLong(1, izvodjacId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				Pesma pesma = new Pesma();
				pesma.setId(resultSet.getLong(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getLong(5));
				pesme.add(pesma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesme;
	}

	public List<Pesma> getPesmePoZanru(Long zanrId) throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<>();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on p.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id where z.zanrovi_id= ?");
			prepStmt.setLong(1, zanrId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				Pesma pesma = new Pesma();
				pesma.setId(resultSet.getLong(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getLong(5));
				pesme.add(pesma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesme;
	}

	public List<Pesma> getPesmePoCeni(Long cenaId) throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<>();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on p.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id where c.cene_id= ?");
			prepStmt.setLong(1, cenaId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				Pesma pesma = new Pesma();
				pesma.setId(resultSet.getLong(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getLong(5));
				pesme.add(pesma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesme;
	}

	public Pesma getPesma(Long pesmaId) throws ClassNotFoundException {
		Pesma pesma = new Pesma();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on p.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id where p.pesme_id= ?");
			prepStmt.setLong(1, pesmaId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				pesma.setId(resultSet.getLong(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getLong(5));
				return pesma;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesma;
	}

	public Pesma insertPesma(Long izvodjacId, Long zanrId, Long cenaId, Pesma pesma) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat()
					.prepareStatement("insert into pesme (pesme_naziv, izvodjac_id, zanr_id, cena_id) values(?,?,?,?)");
			prepStmt.setString(1, pesma.getNaziv());
			prepStmt.setLong(2, izvodjacId);
			prepStmt.setLong(3, zanrId);
			prepStmt.setLong(4, cenaId);
			prepStmt.executeUpdate();
			resultSet = prepStmt.getGeneratedKeys();
			if (resultSet.next()) {
				pesma.setId(resultSet.getLong(1));
			}
			prepStmt = DatabaseConnector.conStat()
					.prepareStatement("select cene_kolicina from cene where cene_id=?");
			prepStmt.setLong(1, cenaId);
			resultSet= prepStmt.executeQuery();
			if (resultSet.next()) {
				pesma.setCenaKolicina(resultSet.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesma;
	}

	public Pesma updatePesma(Pesma pesma) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat()
					.prepareStatement("update pesme set pesme_naziv= ? where pesme_id= ?");
			prepStmt.setString(1, pesma.getNaziv());
			prepStmt.setLong(2, pesma.getId());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesma;
	}

	public void removePesma(Long pesmaId) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("delete from pesme where pesme_id= ?");
			prepStmt.setLong(1, pesmaId);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
