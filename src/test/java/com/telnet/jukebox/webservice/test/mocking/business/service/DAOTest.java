package com.telnet.jukebox.webservice.test.mocking.business.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.telnet.jukebox.webservice.model.Zanr;

public class DAOTest {

	@BeforeClass
	public static void init() throws SQLException, ClassNotFoundException, IOException {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");

		initDatabase();
	}

	@AfterClass
	public static void destroy() throws SQLException, ClassNotFoundException, IOException {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			statement.executeUpdate("DROP TABLE zanrovi");
			connection.commit();
		}
	}

	private static void initDatabase() throws SQLException {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			statement.execute("CREATE TABLE zanrovi (zanrovi_id INT NOT NULL, zanrovi_ime VARCHAR(45) NOT NULL,"
					+ "PRIMARY KEY (zanrovi_id))");
			connection.commit();
			statement.executeUpdate("INSERT INTO zanrovi VALUES (1,'Punk')");
			statement.executeUpdate("INSERT INTO zanrovi VALUES (2,'Rock')");
			statement.executeUpdate("INSERT INTO zanrovi VALUES (3,'Narodna')");
			connection.commit();
		}
	}

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:hsqldb:mem:jukebox", "vinod", "vinod");
	}

	public Zanr getZanr(int zanrId) {
		Zanr zanr = new Zanr();
		try (Connection connection = getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("select * from zanrovi where zanrovi_id= ?");) {
			prepStatement.setInt(1, zanrId);
			ResultSet result = prepStatement.executeQuery();
			while (result.next()) {
				zanr.setId(result.getInt(1));
				zanr.setNaziv(result.getString(2));
				return zanr;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return zanr;
	}

	public Zanr insertZanr(Zanr unetZanr) {
		Zanr zanr = new Zanr(0, null);
		try (Connection connection = getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("insert into zanrovi (zanrovi_id, zanrovi_ime) values(?,?)");) {
			prepStatement.setInt(1, unetZanr.getId());
			prepStatement.setString(2, unetZanr.getNaziv());
			prepStatement.executeUpdate();
		} catch (SQLException e1) {
			// e1.printStackTrace();
			return zanr;
		}

		return unetZanr;
	}

	public Zanr updateZanr(Zanr izmenjenZanr) {
		Zanr zanr = new Zanr(0, null);
		try (Connection connection = getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("update zanrovi set zanrovi_ime= ? where zanrovi_id= ?");) {
			prepStatement.setString(1, izmenjenZanr.getNaziv());
			prepStatement.setInt(2, izmenjenZanr.getId());
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			return zanr;
		}

		return izmenjenZanr;
	}

	public void removeZanr(int zanrId) {
		try (Connection connection = getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("delete from zanrovi where zanrovi_id= ?");) {
			prepStatement.setInt(1, zanrId);
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Zanr> getZanrovi() {
		List<Zanr> zanrovi = new ArrayList<Zanr>();

		try (Connection connection = getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
			ResultSet result = statement.executeQuery("select * from zanrovi");
			while (result.next()) {
				Zanr zanr = new Zanr();
				zanr.setId(result.getInt(1));
				zanr.setNaziv(result.getString(2));
				zanrovi.add(zanr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return zanrovi;
	}

	public Zanr insertZanrCheck(Zanr zanr) {
		if (insertZanr(zanr).equals(new Zanr(0, null))) {
			return new Zanr(0, null);
		} else {
			return getZanr(zanr.getId());
		}
	}

	public Zanr updateZanrCheck(Zanr zanr) {
		if (updateZanr(zanr).equals(new Zanr(0, null))) {
			return new Zanr(0, null);
		} else {
			return getZanr(zanr.getId());
		}
	}

	public Zanr removeZanrCheck(int zanrId) {
		removeZanr(zanrId);
		return getZanr(zanrId);
	}

	@Test
	public void getZanrTest() {
		assertEquals(new Zanr(2, "Rock"), getZanr(2));
		assertEquals(new Zanr(0, null), getZanr(100));
	}

	@Test
	public void insertZanrTest() {
		Zanr zanr = new Zanr(4, "RnB");
		Zanr postojeciZanr = new Zanr(1, "Punk");
		assertEquals(new Zanr(4, "RnB"), insertZanrCheck(zanr));
		assertEquals(new Zanr(0, null), insertZanrCheck(postojeciZanr));
	}

	@Test
	public void updateZanrTest() {
		Zanr zanr = new Zanr(3, "Folk");
		Zanr nepostojeciZanr = new Zanr(100, "Folk");
		assertEquals(new Zanr(3, "Folk"), updateZanrCheck(zanr));
		assertEquals(new Zanr(0, null), updateZanrCheck(nepostojeciZanr));
	}

	@Test
	public void removeZanrTest() {
		assertEquals(new Zanr(0, null), removeZanrCheck(3));
	}

	@Test
	public void getZanroviTest() {
		List<Zanr> z = Arrays.asList(new Zanr(1, "Punk"), new Zanr(2, "Rock"), new Zanr(4, "RnB"));
		assertThat(z, is(getZanrovi()));
	}

}
