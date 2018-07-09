package com.telnet.jukebox.webservice.test.mocking.business.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import com.telnet.jukebox.webservice.dto.ZanrDTO;
import com.telnet.jukebox.webservice.model.Zanr;
import com.telnet.jukebox.webservice.service.ZanrService;

import com.telnet.jukebox.webservice.dto.CenaDTO;
import com.telnet.jukebox.webservice.dto.IzvodjacDTO;
import com.telnet.jukebox.webservice.dto.KorisnikDTO;
import com.telnet.jukebox.webservice.dto.PesmaDTO;
import com.telnet.jukebox.webservice.dto.PrometDTO;

import com.telnet.jukebox.webservice.model.Cena;
import com.telnet.jukebox.webservice.model.Izvodjac;
import com.telnet.jukebox.webservice.model.Korisnik;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Promet;

import com.telnet.jukebox.webservice.service.CenaService;
import com.telnet.jukebox.webservice.service.IzvodjacService;
import com.telnet.jukebox.webservice.service.KorisnikService;
import com.telnet.jukebox.webservice.service.PesmaService;
import com.telnet.jukebox.webservice.service.PrometService;
import java.util.List;

public class DTOTest {

	DAOTest daoTest = new DAOTest();

	List<ZanrDTO> getZanroviDTO = Arrays.asList(new ZanrDTO(1, "Punk"), new ZanrDTO(2, "Rock"), new ZanrDTO(3, "Folk"),
			new ZanrDTO(4, "RnB"), new ZanrDTO(5, "RnB"));
	List<Zanr> getZanroviEntity = daoTest.getZanrovi();

	java.util.Date datum = new java.util.Date();
	java.sql.Date date = new java.sql.Date(datum.getTime());

	ZanrDTO getZanrDTO = new ZanrDTO(2, "Rock");
	Zanr getZanrEntity = daoTest.getZanr(2);
	ZanrDTO insertZanrDTO = new ZanrDTO(4, "RnB");
	//Zanr insertZanrEntity = daoTest.insertZanrCheck(new Zanr(4, "RnB"));

	ZanrDTO updateZanrDTO = new ZanrDTO(3, "Folk");
	Zanr updateZanrEntity = daoTest.updateZanrCheck(new Zanr(3, "Folk"));
	
	CenaDTO cenaDTO = new CenaDTO(10, 130);
	Cena cenaEntity = new Cena(10, 130);
	IzvodjacDTO izvodjacDTO = new IzvodjacDTO(15, "Ritam nereda");
	Izvodjac izvodjacEntity = new Izvodjac(15, "Ritam nereda");
	PesmaDTO pesmaDTO = new PesmaDTO(30, "Put beznadja", "Ritam nereda", "Punk", 130);
	Pesma pesmaEntity = new Pesma(30, "Put beznadja", "Ritam nereda", "Punk", 130);
	PrometDTO prometDTO = new PrometDTO(156, date, "Put beznadja", 130, 20, "Ritam nereda", "korisnik@gmail.com");
	Promet prometEntity = new Promet(156, date, "Put beznadja", 130, 20, "Ritam nereda", "korisnik@gmail.com");
	KorisnikDTO korisnikDTO = new KorisnikDTO(12, "sifra", "email");
	Korisnik korisnikEntity = new Korisnik(12, "sifra", "email");

	CenaService serviceCena = new CenaService();
	IzvodjacService serviceIzvodjac = new IzvodjacService();
	PesmaService servicePesma = new PesmaService();
	PrometService servicePromet = new PrometService();
	KorisnikService serviceKorisnik = new KorisnikService();
	ZanrService serviceZanr = new ZanrService();

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

	public List<ZanrDTO> getZanrovi() {
		List<Zanr> getZanroviEntity = daoTest.getZanrovi();
		List<ZanrDTO> zanrovi = new ArrayList<>();
		for (int i = 0; i < getZanroviEntity.size(); i++) {
			zanrovi.add(serviceZanr.entityToDTO(getZanroviEntity.get(i)));
		}

		return zanrovi;
	}

	public ZanrDTO getZanr(int zanrId) {
		return serviceZanr.entityToDTO(daoTest.getZanr(zanrId));
	}

	public ZanrDTO addZanr(ZanrDTO zanr) {
		return serviceZanr.entityToDTO(daoTest.insertZanr(serviceZanr.DTOToEntity(zanr)));
	}

	public ZanrDTO updateZanr(ZanrDTO zanr) {
		return serviceZanr.entityToDTO(daoTest.updateZanr(serviceZanr.DTOToEntity(zanr)));
	}

	public void removeZanr(int zanrId) {
		daoTest.removeZanr(zanrId);
	}
	
	@Test
	public void toEntity() {
		assertEquals(daoTest.insertZanrCheck(new Zanr(4, "RnB")), serviceZanr.DTOToEntity(insertZanrDTO));
		assertEquals(updateZanrEntity, serviceZanr.DTOToEntity(updateZanrDTO));
		
		assertEquals(cenaEntity, serviceCena.DTOToEntity(cenaDTO));
		assertEquals(izvodjacEntity, serviceIzvodjac.DTOToEntity(izvodjacDTO));
		assertEquals(pesmaEntity, servicePesma.DTOToEntity(pesmaDTO));
		assertEquals(prometEntity, servicePromet.DTOToEntity(prometDTO));
		assertEquals(korisnikEntity, serviceKorisnik.DTOToEntity(korisnikDTO));
		}

	@Test
	public void toDTO() {
		assertEquals(getZanrDTO, serviceZanr.entityToDTO(getZanrEntity));
		// assertEquals(insertZanrDTO, serviceZanr.entityToDTO(insertZanrEntity));
		//assertEquals(updateZanrDTO, serviceZanr.entityToDTO(updateZanrEntity));
		List<Zanr> getZanroviEntity = daoTest.getZanrovi();
		for (int i = 0; i < getZanroviEntity.size(); i++) {
			assertEquals(getZanroviDTO.get(i), serviceZanr.entityToDTO(getZanroviEntity.get(i)));
		}

		assertEquals(cenaDTO, serviceCena.entityToDTO(cenaEntity));
		assertEquals(izvodjacDTO, serviceIzvodjac.entityToDTO(izvodjacEntity));
		assertEquals(pesmaDTO, servicePesma.entityToDTO(pesmaEntity));
		assertEquals(prometDTO, servicePromet.entityToDTO(prometEntity));
		assertEquals(korisnikDTO, serviceKorisnik.entityToDTO(korisnikEntity));
		 
	}
}

