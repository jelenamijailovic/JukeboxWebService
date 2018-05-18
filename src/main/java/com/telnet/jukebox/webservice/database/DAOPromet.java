package com.telnet.jukebox.webservice.database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.model.Promet;

public class DAOPromet {

	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Promet> getSvePromete() throws ClassNotFoundException {
		List<Promet> prometi = new ArrayList<Promet>();

		try {
			stmt = DatabaseConnector.conStat().createStatement();
			resultSet = stmt.executeQuery(
					"select pr.prometi_id, pr.prometi_datum, pe.pesme_naziv, c.cene_kolicina from (prometi pr join pesme pe on pr.pesma_id=pe.pesme_id)join cene c on pe.cena_id=c.cene_id");
			while (resultSet.next()) {
				Promet promet = new Promet();
				promet.setId(resultSet.getLong(1));
				promet.setDatum(resultSet.getDate(2));
				promet.setPesmaNaziv(resultSet.getString(3));
				promet.setCenaKolicina(resultSet.getLong(4));
				prometi.add(promet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prometi;
	}

	public List<Promet> getPrometePoKorisniku(Long korisnikId) throws ClassNotFoundException {
		List<Promet> prometi = new ArrayList<Promet>();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement(
					"select pr.prometi_id, pr.prometi_datum, pe.pesme_naziv, c.cene_kolicina, k.korisnicko_ime from (prometi pr join pesme pe on pr.pesma_id=pe.pesme_id)join cene c on pe.cena_id=c.cene_id join korisnici k on pr.korisnik_id= k.korisnici_id where k.korisnici_id= ?");
			prepStmt.setLong(1, korisnikId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				Promet promet = new Promet();
				promet.setId(resultSet.getLong(1));
				promet.setDatum(resultSet.getDate(2));
				promet.setPesmaNaziv(resultSet.getString(3));
				promet.setCenaKolicina(resultSet.getLong(4));
				prometi.add(promet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prometi;
	}

	public List<Promet> getPrometePoPesmi(Long pesmaId) throws ClassNotFoundException {
		List<Promet> prometi = new ArrayList<Promet>();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement(
					"select pr.prometi_id, pr.prometi_datum, pe.pesme_naziv, c.cene_kolicina from (prometi pr join pesme pe on pr.pesma_id=pe.pesme_id)join cene c on pe.cena_id=c.cene_id where pe.pesme_id= ?");
			prepStmt.setLong(1, pesmaId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				Promet promet = new Promet();
				promet.setId(resultSet.getLong(1));
				promet.setDatum(resultSet.getDate(2));
				promet.setPesmaNaziv(resultSet.getString(3));
				promet.setCenaKolicina(resultSet.getLong(4));
				prometi.add(promet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prometi;
	}

	public Promet getPromet(Long prometId) throws ClassNotFoundException {
		Promet promet = new Promet();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement(
					"select pr.prometi_id, pr.prometi_datum, pe.pesme_naziv, c.cene_kolicina from (prometi pr join pesme pe on pr.pesma_id=pe.pesme_id)join cene c on pe.cena_id=c.cene_id where pr.prometi_id= ?");
			prepStmt.setLong(1, prometId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				promet.setId(resultSet.getLong(1));
				promet.setDatum(resultSet.getDate(2));
				promet.setPesmaNaziv(resultSet.getString(3));
				promet.setCenaKolicina(resultSet.getLong(4));
				return promet;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return promet;
	}

	public List<Promet> getTop5Songs() throws ClassNotFoundException {
		List<Promet> prometi = new ArrayList<Promet>();

		try {
			stmt = DatabaseConnector.conStat().createStatement();
			resultSet = stmt.executeQuery(
					"select pe.pesme_naziv, count(pr.pesma_id) as repetition, c.cene_kolicina from prometi pr join pesme pe on pr.pesma_id= pe.pesme_id join cene c on pe.cena_id= c.cene_id group by pe.pesme_naziv, pe.cena_id order by repetition desc limit 5;");
			Long i = (long) 1;
			while (resultSet.next()) {
				Promet promet = new Promet();
				promet.setId(i++);
				promet.setPesmaNaziv(resultSet.getString(1));
				promet.setRepetition(resultSet.getLong(2));
				promet.setCenaKolicina(resultSet.getLong(3));
				prometi.add(promet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prometi;
	}
	
	public List<Promet> getTop5Artists() throws ClassNotFoundException {
		List<Promet> prometi = new ArrayList<Promet>();

		try {
			stmt = DatabaseConnector.conStat().createStatement();
			resultSet = stmt.executeQuery(
					"select i.izvodjaci_ime, count(pr.pesma_id) as repetition from prometi pr join pesme pe on pr.pesma_id= pe.pesme_id join izvodjaci i on pe.izvodjac_id= i.izvodjaci_id group by pe.izvodjac_id order by repetition desc limit 5;");
			Long i = (long) 1;
			while (resultSet.next()) {
				Promet promet = new Promet();
				promet.setId(i++);
				promet.setIzvodjacIme(resultSet.getString(1));
				promet.setRepetition(resultSet.getLong(2));
				prometi.add(promet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prometi;
	}

	public Promet insertPromet(Promet promet) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat()
					.prepareStatement("insert into prometi (prometi_datum, pesma_id) values(?,?)");
			prepStmt.setDate(1, promet.getDatum());
			prepStmt.setLong(2, promet.getPesmaId());
			prepStmt.executeUpdate();
			resultSet = prepStmt.getGeneratedKeys();
			if (resultSet.next()) {
				promet.setId(resultSet.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return promet;
	}

//	public Promet updatePromet(Promet promet) throws ClassNotFoundException {
//		try {
//			prepStmt = DatabaseConnector.conStat()
//					.prepareStatement("update prometi set prometi_datum= ? where prometi_id= ?");
//			prepStmt.setDate(1, promet.getDatum());
//			prepStmt.setLong(2, promet.getId());
//			prepStmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return promet;
//	}

	public void removePromet(Long prometId) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("delete from prometi where prometi_id= ?");
			prepStmt.setLong(1, prometId);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
