package com.telnet.jukebox.webservice.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.model.Zanr;

public class ZanrDAO {

	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Zanr> getZanrovi() throws ClassNotFoundException {
		List<Zanr> zanrovi = new ArrayList<Zanr>();

		try {
			Connection con = DatabaseConnector.conStat();
			stmt = con.createStatement();
			resultSet = stmt.executeQuery("select * from zanrovi");
			while (resultSet.next()) {
				Zanr zanr = new Zanr();
				zanr.setId(resultSet.getInt(1));
				zanr.setNaziv(resultSet.getString(2));
				zanrovi.add(zanr);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return zanrovi;
	}

	public Zanr getZanr(int zanrId) throws ClassNotFoundException {
		Zanr zanr = new Zanr();

		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement("select * from zanrovi where zanrovi_id= ?");
			prepStmt.setInt(1, zanrId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				zanr.setId(resultSet.getInt(1));
				zanr.setNaziv(resultSet.getString(2));
				return zanr;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return zanr;
	}

	public Zanr insertZanr(Zanr zanr) throws ClassNotFoundException {
		try {
			if (!zanr.getNaziv().equals(null)) {
				Connection con = DatabaseConnector.conStat();
				prepStmt = con.prepareStatement("insert into zanrovi (zanrovi_ime) values(?)");
				prepStmt.setString(1, zanr.getNaziv());
				prepStmt.executeUpdate();
				resultSet = prepStmt.getGeneratedKeys();
				if (resultSet.next()) {
					zanr.setId(resultSet.getInt(1));
				}
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return zanr;
	}

	public Zanr updateZanr(Zanr zanr) throws ClassNotFoundException {
		try {
			if (!zanr.getNaziv().equals(null)) {
				Connection con = DatabaseConnector.conStat();
				prepStmt = con.prepareStatement("update zanrovi set zanrovi_ime= ? where zanrovi_id= ?");
				prepStmt.setString(1, zanr.getNaziv());
				prepStmt.setInt(2, zanr.getId());
				prepStmt.executeUpdate();
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("sql except");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("io except");
			e.printStackTrace();
		}

		return zanr;
	}

	public void removeZanr(int zanrId) throws ClassNotFoundException {
		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement("delete from zanrovi where zanrovi_id= ?");
			prepStmt.setInt(1, zanrId);
			prepStmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
