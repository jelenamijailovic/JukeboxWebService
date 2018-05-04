package com.telnet.jukebox.webservice.database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.model.Cena;

public class DAOCena {

	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Cena> getCene() throws ClassNotFoundException {
		List<Cena> cene = new ArrayList<>();

		try {
			stmt = DatabaseConnector.conStat().createStatement();
			resultSet = stmt.executeQuery("select * from cene");
			while (resultSet.next()) {
				Cena cena = new Cena();
				cena.setId(resultSet.getLong(1));
				cena.setKolicina(resultSet.getLong(2));
				cene.add(cena);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cene;
	}

	public Cena getCena(Long cenaId) throws ClassNotFoundException {
		Cena cena = new Cena();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("select * from cene where cene_id= ?");
			prepStmt.setLong(1, cenaId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				cena.setId(resultSet.getLong(1));
				cena.setKolicina(resultSet.getLong(2));
				return cena;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cena;
	}

	public Cena insertCena(Cena cena) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("insert into cene (cene_kolicina) values(?)");
			prepStmt.setLong(1, cena.getKolicina());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cena;
	}

	public Cena updateCena(Cena cena) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat()
					.prepareStatement("update cene set cene_kolicina= ? where cene_id= ?");
			prepStmt.setLong(1, cena.getKolicina());
			prepStmt.setLong(2, cena.getId());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cena;
	}

	public void removeCena(Long cenaId) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("delete from cene where cene_id= ?");
			prepStmt.setLong(1, cenaId);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
