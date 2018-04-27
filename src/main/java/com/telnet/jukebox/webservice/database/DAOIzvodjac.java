package com.telnet.jukebox.webservice.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.model.Izvodjac;

public class DAOIzvodjac {

	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Izvodjac> getIzvodjaci() throws ClassNotFoundException, SQLException {
		List<Izvodjac> izvodjaci = new ArrayList<>();
		// try {
		stmt = DatabaseConnector.conStat().createStatement();
		resultSet = stmt.executeQuery("select * from izvodjaci");

		while (resultSet.next()) {
			Izvodjac izvodjac = new Izvodjac();
			izvodjac.setId(resultSet.getLong(1));
			izvodjac.setIme(resultSet.getString(2));
			izvodjaci.add(izvodjac);
		}

		return izvodjaci;
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// return null;
	}

	public Izvodjac getIzvodjac(Long izvodjacId) throws ClassNotFoundException {
		Izvodjac izvodjac = new Izvodjac();

		try {
			prepStmt = DatabaseConnector.conStat().prepareStatement("select * from izvodjaci where izvodjaci_id= ?");
			prepStmt.setLong(1, izvodjacId);
			resultSet = prepStmt.executeQuery();

			while (resultSet.next()) {
				izvodjac.setId(resultSet.getLong(1));
				izvodjac.setIme(resultSet.getString(2));
				return izvodjac;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Izvodjac insertIzvodjac(Izvodjac izvodjac) throws ClassNotFoundException, SQLException {
		// try {
		prepStmt = DatabaseConnector.conStat().prepareStatement("insert into izvodjaci (izvodjaci_ime) values (?)");
		prepStmt.setString(1, izvodjac.getIme());
		prepStmt.executeUpdate();
		return izvodjac;
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// return null;
	}

	public Izvodjac updateIzvodjac(Izvodjac izvodjac) throws ClassNotFoundException, SQLException {
		// try {
		prepStmt = DatabaseConnector.conStat()
				.prepareStatement("update izvodjaci set izvodjaci_ime= ? where izvodjaci_id= ?");
		prepStmt.setString(1, izvodjac.getIme());
		prepStmt.setLong(2, izvodjac.getId());
		prepStmt.executeUpdate();
		return izvodjac;
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// return null;
	}

	public void removeIzvodjac(long izvodjacId) throws ClassNotFoundException, SQLException {
		// try {
		prepStmt = DatabaseConnector.conStat().prepareStatement("delete from izvodjaci where izvodjaci_id= ?");
		prepStmt.setLong(1, izvodjacId);
		prepStmt.executeUpdate();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

	}

}
