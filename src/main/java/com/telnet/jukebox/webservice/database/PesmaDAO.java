package com.telnet.jukebox.webservice.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.telnet.jukebox.webservice.model.Pesma;

public class PesmaDAO {

	DatabaseConnector ds;
	Statement stmt = null;
	PreparedStatement prepStmt = null;
	ResultSet resultSet = null;

	public List<Pesma> getSvePesme() throws ClassNotFoundException {
		List<Pesma> pesme = new ArrayList<Pesma>();

		try {
			Connection con = ds.conStat();
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
			Connection con = ds.conStat();
			int offsetNum = (page - 1) * 5;
			prepStmt = con.prepareStatement(
					"select ceil((select count(*) from pesme)/5) 'broj strana', p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id join zanrovi z on i.zanr_id=z.zanrovi_id join cene c on p.cena_id=c.cene_id order by pesme_id limit 5 offset ?");
			prepStmt.setInt(1, offsetNum);
			resultSet = prepStmt.executeQuery();
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
			Connection con = ds.conStat();
			prepStmt = con.prepareStatement(
					"select p.pesme_id, p.pesme_naziv, i.izvodjaci_ime, z.zanrovi_ime, c.cene_kolicina from ((pesme p join izvodjaci i on p.izvodjac_id=i.izvodjaci_id)join zanrovi z on i.zanr_id=z.zanrovi_id)join cene c on p.cena_id=c.cene_id where i.izvodjaci_id= ?");
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
			Connection con = ds.conStat();
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
			Connection con = ds.conStat();
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
			Connection con = ds.conStat();
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
			Connection con = ds.conStat();
			prepStmt = con.prepareStatement("insert into pesme (pesme_naziv, izvodjac_id, cena_id) values(?,?,?)");
			prepStmt.setString(1, pesma.getNaziv());
			prepStmt.setInt(2, izvodjacId);
			prepStmt.setInt(3, cenaId);
			prepStmt.executeUpdate();
			resultSet = prepStmt.getGeneratedKeys();
			if (resultSet.next()) {
				pesma.setId(resultSet.getInt(1));
			}
			prepStmt = ds.conStat().prepareStatement("select cene_kolicina from cene where cene_id=?");
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
			Connection con = ds.conStat();
			prepStmt = con.prepareStatement("update pesme set pesme_naziv= ? where pesme_id= ?");
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
			Connection con = ds.conStat();
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

	public List<Pesma> recomended(int korisnikId) {
		Random randomGenerator = new Random();
		// List<Pesma> catalogue= daoPesma.getSvePesme();
		List<Pesma> recomended = new ArrayList<Pesma>();
		List<Pesma> poZanru = new ArrayList<Pesma>();

		int zanr = 0;

		try {
			Connection con = ds.conStat();
			prepStmt = con.prepareStatement(
					"select z.zanrovi_id, pe.pesme_naziv, count(pr.pesma_id) as repetition, k.korisnici_id from prometi pr join pesme pe on pr.pesma_id= pe.pesme_id join izvodjaci i on i.izvodjaci_id=pe.izvodjac_id join zanrovi z on z.zanrovi_id=i.zanr_id join korisnici k on pr.korisnik_id= k.korisnici_id group by pe.pesme_naziv, k.korisnici_email, z.zanrovi_id having k.korisnici_id=? order by repetition desc limit 1;");
			prepStmt.setInt(1, korisnikId);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				zanr = resultSet.getInt(1);
			}
			con.close();

			poZanru = getPesmePoZanru(zanr);

			for (int br = 0; br < 3; br++) {
				int index = randomGenerator.nextInt(poZanru.size());
				Pesma randomPesma = new Pesma();
				randomPesma = poZanru.get(index);
				for (int i = 0; i < recomended.size(); i++) {
					if (randomPesma == recomended.get(i)) {
						System.out.println("Pesma je ponovljena");
						randomPesma = poZanru.get(index + 1);
						i = recomended.size();
					} else {
						randomPesma = poZanru.get(index);
					}
				}
				recomended.add(randomPesma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return recomended;

	}

}
