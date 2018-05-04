package com.telnet.jukebox.webservice.database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.model.Zanr;

public class DAOZanr {

	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Zanr> getZanrovi() throws ClassNotFoundException {
		List<Zanr> zanrovi = new ArrayList<>();

		try {
			stmt = DatabaseConnector.conStat().createStatement();
			resultSet = stmt.executeQuery("select * from zanrovi");
			while (resultSet.next()) {
				Zanr zanr = new Zanr();
				zanr.setId(resultSet.getLong(1));
				zanr.setNaziv(resultSet.getString(2));
				zanrovi.add(zanr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return zanrovi;
	}

	public Zanr getZanr(Long zanrId) throws ClassNotFoundException {
		Zanr zanr = new Zanr();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("select * from zanrovi where zanrovi_id= ?");
			prepStmt.setLong(1, zanrId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				zanr.setId(resultSet.getLong(1));
				zanr.setNaziv(resultSet.getString(2));
				return zanr;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return zanr;
	}

	public Zanr insertZanr(Zanr zanr) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("insert into zanrovi (zanrovi_ime) values(?)");
			prepStmt.setString(1, zanr.getNaziv());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return zanr;
	}

	public Zanr updateZanr(Zanr zanr) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat()
					.prepareStatement("update zanrovi set zanrovi_ime= ? where zanrovi_id= ?");
			prepStmt.setString(1, zanr.getNaziv());
			prepStmt.setLong(2, zanr.getId());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql except");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("io except");
			e.printStackTrace();
		}

		return zanr;
	}

	public void removeZanr(Long zanrId) throws ClassNotFoundException {
		try {
			prepStmt = DatabaseConnector.conStat()
					.prepareStatement("delete from zanrovi where zanrovi_id= ?");
			prepStmt.setLong(1, zanrId);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
