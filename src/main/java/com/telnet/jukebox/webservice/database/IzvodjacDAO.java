package com.telnet.jukebox.webservice.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.model.Izvodjac;

public class IzvodjacDAO {

	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Izvodjac> getIzvodjaci() throws ClassNotFoundException {
		List<Izvodjac> izvodjaci = new ArrayList<Izvodjac>();

		try {
			Connection con = DatabaseConnector.conStat();
			stmt = con.createStatement();
			resultSet = stmt.executeQuery("select * from izvodjaci");
			while (resultSet.next()) {
				Izvodjac izvodjac = new Izvodjac();
				izvodjac.setId(resultSet.getInt(1));
				izvodjac.setIme(resultSet.getString(2));
				izvodjaci.add(izvodjac);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return izvodjaci;
	}

	public Izvodjac getIzvodjac(int izvodjacId) throws ClassNotFoundException {
		Izvodjac izvodjac = new Izvodjac();

		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement("select * from izvodjaci where izvodjaci_id= ?");
			prepStmt.setInt(1, izvodjacId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				izvodjac.setId(resultSet.getInt(1));
				izvodjac.setIme(resultSet.getString(2));
				return izvodjac;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			izvodjac.setIme(null);
		}

		return izvodjac;
	}

	public Izvodjac insertIzvodjac(Izvodjac izvodjac) throws ClassNotFoundException {
		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement("insert into izvodjaci (izvodjaci_ime) values (?)");
			prepStmt.setString(1, izvodjac.getIme());
			prepStmt.executeUpdate();
			resultSet = prepStmt.getGeneratedKeys();
			if (resultSet.next()) {
				izvodjac.setId(resultSet.getInt(1));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return izvodjac;
	}

	public Izvodjac updateIzvodjac(Izvodjac izvodjac) throws ClassNotFoundException {
		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement("update izvodjaci set izvodjaci_ime= ? where izvodjaci_id= ?");
			prepStmt.setString(1, izvodjac.getIme());
			prepStmt.setInt(2, izvodjac.getId());
			prepStmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return izvodjac;
	}

	public void removeIzvodjac(int izvodjacId) throws ClassNotFoundException {
		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement("delete from izvodjaci where izvodjaci_id= ?");
			prepStmt.setInt(1, izvodjacId);
			prepStmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
