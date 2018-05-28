package com.telnet.jukebox.webservice.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.model.Pesma;

public class PesmaDAO {

	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Pesma> getSvePesme() throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<Pesma>();

		try {
			Connection con = DatabaseConnector.conStat();
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id join zanrovi z on i.zanr_id=z.zanrovi_id join cene c on p.cena_id=c.cene_id order by pesme_id");
			while (resultSet.next()) {
				Pesma pesma = new Pesma();
				pesma.setId(resultSet.getInt(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getInt(5));
				pesme.add(pesma);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesme;

	}
	
	public List<Pesma> getSvePesmePagination(int page) throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<Pesma>();

		try {
			Connection con = DatabaseConnector.conStat();
			int offsetNum= (page-1)*5;
			prepStmt = con.prepareStatement(
					"select ceil((select count(*) from pesme)/5) 'broj strana', p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id join zanrovi z on i.zanr_id=z.zanrovi_id join cene c on p.cena_id=c.cene_id order by pesme_id limit 5 offset ?");
			prepStmt.setInt(1, offsetNum);
			resultSet= prepStmt.executeQuery();
			while (resultSet.next()) {
				Pesma pesma = new Pesma();
				pesma.setBrojStrana(resultSet.getInt(1));
				pesma.setId(resultSet.getInt(2));
				pesma.setNaziv(resultSet.getString(3));
				pesma.setIzvodjacIme(resultSet.getString(4));
				pesma.setZanrIme(resultSet.getString(5));
				pesma.setCenaKolicina(resultSet.getInt(6));
				pesme.add(pesma);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pesme;

	}

	public List<Pesma> getPesmePoIzvodjacu(int izvodjacId) throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<Pesma>();

		
		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on p.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id where i.izvodjaci_id= ?");
			prepStmt.setLong(1, izvodjacId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				Pesma pesma = new Pesma();
				pesma.setId(resultSet.getInt(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getInt(5));
				pesme.add(pesma);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesme;
	}

	public List<Pesma> getPesmePoZanru(int zanrId) throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<Pesma>();

		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on i.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id where z.zanrovi_id= ?");
			prepStmt.setInt(1, zanrId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				Pesma pesma = new Pesma();
				pesma.setId(resultSet.getInt(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getInt(5));
				pesme.add(pesma);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesme;
	}

	public List<Pesma> getPesmePoCeni(int cenaId) throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<Pesma>();

		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on p.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id where c.cene_id= ?");
			prepStmt.setLong(1, cenaId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				Pesma pesma = new Pesma();
				pesma.setId(resultSet.getInt(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getInt(5));
				pesme.add(pesma);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesme;
	}

	public Pesma getPesma(int pesmaId) throws ClassNotFoundException {
		Pesma pesma = new Pesma();

		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on i.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id where p.pesme_id= ?");
			prepStmt.setInt(1, pesmaId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				pesma.setId(resultSet.getInt(1));
				pesma.setNaziv(resultSet.getString(2));
				pesma.setIzvodjacIme(resultSet.getString(3));
				pesma.setZanrIme(resultSet.getString(4));
				pesma.setCenaKolicina(resultSet.getInt(5));
				return pesma;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesma;
	}

	public Pesma insertPesma(int izvodjacId, int cenaId, Pesma pesma) throws ClassNotFoundException {
		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con
					.prepareStatement("insert into pesme (pesme_naziv, izvodjac_id, cena_id) values(?,?,?)");
			prepStmt.setString(1, pesma.getNaziv());
			prepStmt.setInt(2, izvodjacId);
			prepStmt.setInt(3, cenaId);
			prepStmt.executeUpdate();
			resultSet = prepStmt.getGeneratedKeys();
			if (resultSet.next()) {
				pesma.setId(resultSet.getInt(1));
			}
			prepStmt = DatabaseConnector.conStat().prepareStatement("select cene_kolicina from cene where cene_id=?");
			prepStmt.setInt(1, cenaId);
			resultSet = prepStmt.executeQuery();
			if (resultSet.next()) {
				pesma.setCenaKolicina(resultSet.getInt(1));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesma;
	}

	public Pesma updatePesma(Pesma pesma) throws ClassNotFoundException {
		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con
					.prepareStatement("update pesme set pesme_naziv= ? where pesme_id= ?");
			prepStmt.setString(1, pesma.getNaziv());
			prepStmt.setInt(2, pesma.getId());
			prepStmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pesma;
	}

	public void removePesma(int pesmaId) throws ClassNotFoundException {
		try {
			Connection con = DatabaseConnector.conStat();
			prepStmt = con.prepareStatement("delete from pesme where pesme_id= ?");
			prepStmt.setInt(1, pesmaId);
			prepStmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
