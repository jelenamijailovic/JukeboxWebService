package com.telnet.jukebox.webservice.database;

import java.sql.Connection;
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

	public List<Promet> getSvePromete() throws ClassNotFoundException, SQLException {
		List<Promet> prometi = new ArrayList<>();

		Connection conn = DatabaseConnector.conStat();
		stmt = conn.createStatement();
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

		return prometi;

	}

	public List<Promet> getPrometePoCeni(Long cenaId) throws ClassNotFoundException, SQLException {
		List<Promet> prometi = new ArrayList<>();

		Connection conn = DatabaseConnector.conStat();

		prepStmt = conn.prepareStatement(
				"select pr.prometi_id, pr.prometi_datum, pe.pesme_naziv, c.cene_kolicina from (prometi pr join pesme pe on pr.pesma_id=pe.pesme_id)join cene c on pe.cena_id=c.cene_id where c.cene_id= ?");
		prepStmt.setLong(1, cenaId);
		resultSet = prepStmt.executeQuery();

		while (resultSet.next()) {
			Promet promet = new Promet();

			promet.setId(resultSet.getLong(1));
			promet.setDatum(resultSet.getDate(2));
			promet.setPesmaNaziv(resultSet.getString(3));
			promet.setCenaKolicina(resultSet.getLong(4));

			prometi.add(promet);
		}

		return prometi;

	}

	public List<Promet> getPrometePoPesmi(Long pesmaId) throws ClassNotFoundException, SQLException {
		List<Promet> prometi = new ArrayList<>();

		Connection conn = DatabaseConnector.conStat();

		prepStmt = conn.prepareStatement(
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

		return prometi;

	}

	public Promet getPromet(Long prometId) throws ClassNotFoundException, SQLException {

		Connection conn = DatabaseConnector.conStat();
		Promet promet = new Promet();

		prepStmt = conn.prepareStatement(
				"select pr.prometi_id, pr.prometi_datum, pe.pesme_naziv, c.cene_kolicina from (prometi pr join pesme pe on pr.pesma_id=pe.pesme_id)join cene c on pe.cena_id=c.cene_id where pr.prometi_id= ?");
		prepStmt.setLong(1, prometId);
		resultSet = prepStmt.executeQuery();

		while (resultSet.next()) {
			promet.setId(resultSet.getLong(1));
			promet.setDatum(resultSet.getDate(2));
			promet.setPesmaNaziv(resultSet.getString(3));
			promet.setCenaKolicina(resultSet.getLong(4));
		}

		return promet;
	}

	public Promet insertPromet(Long pesmaId, Promet promet) throws ClassNotFoundException, SQLException {

		Connection conn = DatabaseConnector.conStat();
		prepStmt = conn.prepareStatement("insert into prometi (prometi_datum, pesma_id) values(?,?)");
		prepStmt.setDate(1, promet.getDatum());
		prepStmt.setLong(2, pesmaId);
		prepStmt.executeUpdate();

		return promet;
	}

	public Promet updatePromet(Promet promet) throws ClassNotFoundException, SQLException {

		Connection conn = DatabaseConnector.conStat();

		prepStmt = conn.prepareStatement("update prometi set prometi_datum= ? where prometi_id= ?");
		prepStmt.setDate(1, promet.getDatum());
		prepStmt.setLong(2, promet.getId());
		prepStmt.executeUpdate();

		return promet;
	}

	public void removePromet(Long prometId) throws SQLException, ClassNotFoundException {

		Connection conn = DatabaseConnector.conStat();

		prepStmt = conn.prepareStatement("delete from prometi where prometi_id= ?");
		prepStmt.setLong(1, prometId);
		prepStmt.executeUpdate();
	}
}
