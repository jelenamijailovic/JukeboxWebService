package com.telnet.jukebox.webservice.resources;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.telnet.jukebox.webservice.model.Korisnik;
import com.telnet.jukebox.webservice.model.Login;
import com.telnet.jukebox.webservice.model.Promet;
import com.telnet.jukebox.webservice.service.KorisnikService;
import com.telnet.jukebox.webservice.service.PrometService;

@Path("/korisnici")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class KorisnikResource {

	final static Logger logger = Logger.getLogger(KorisnikResource.class);

	KorisnikService korisnikService = new KorisnikService();
	PrometService prometService = new PrometService();

	@SuppressWarnings("unused")
	@GET
	public Response getKorisnici() throws ClassNotFoundException {
		logger.info("Prikaz svih izvodjaca");

		List<Korisnik> korisnici = korisnikService.getKorisnici();
		GenericEntity<List<Korisnik>> list = new GenericEntity<List<Korisnik>>(korisnici) {
		};

		Response r;

		if (list == null) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*").entity("Ne postoje uneti korisnici")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje uneti korisnici");
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Korisnici su uspesno prikazani");
		}

		return r;
	}

	@GET
	@Path("/{korisnikId}")
	public Response getKorisnik(@PathParam("korisnikId") Long korisnikId) throws ClassNotFoundException {
		logger.info("Prikaz korisnika sa id-om " + korisnikId);

		Korisnik k = korisnikService.getKorisnik(korisnikId);

		Response r;

		if (k.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoji korisnik sa id-om " + korisnikId).header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.error("Ne postoji korisnik sa id-om " + korisnikId);
		} else {
			r = Response.ok(k).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.info("Uspesno prikazan korisnik sa id-om " + korisnikId);
		}

		return r;
	}

	@POST
	public Response addKorisnika(Korisnik korisnik) throws ClassNotFoundException {
		logger.info("Unosenje korisnika");

		Response r;
		try {
			Korisnik k = korisnikService.insertKorisnik(korisnik);
			r = Response.ok(k).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST")
					.allow("OPTIONS").build();

			logger.info("Korisnik je uspesno unet");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("GRESKA!!");
			r = Response.status(409).header("Access-Control-Allow-Origin", "*").entity("Postoji email u bazi.")
					.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
			logger.error("Postoji email u bazi.\n" + e.getMessage());
		} catch (Exception e) {
			logger.error("Greska pri unosu korisnika:\n" + e.getMessage());
			r = Response.status(403).header("Access-Control-Allow-Origin", "*").entity("Greska pri unosu korisnika")
					.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
		}

		return r;
	}

	@PUT
	@Path("/{korisnikId}")
	public Korisnik updateIzvodjac(@PathParam("korisnikId") Long korisnikId, Korisnik korisnik)
			throws ClassNotFoundException {
		korisnik.setId(korisnikId);

		logger.info("Modifikovanje korisnika sa id-om " + korisnikId);

		Korisnik k = korisnikService.getKorisnik(korisnikId);

		if (k.getId() == 0) {
			logger.error("Korisnik sa id-om " + korisnikId + " ne moze biti modifikovan jer ne postoji");
		} else {
			k = korisnikService.updateKorisnik(korisnik);
			logger.info("Korisnik sa id-om " + korisnikId + " je uspesno modifikovan");
		}

		return k;
	}

	@DELETE
	@Path("/{korisnikId}")
	public void deleteIzvodjac(@PathParam("korisnikId") Long korisnikId) throws ClassNotFoundException {
		logger.info("Brisanje korisnika sa id-om " + korisnikId);

		Korisnik k = korisnikService.getKorisnik(korisnikId);

		if (k.getId() == 0) {
			logger.error("Korisnik sa id-om " + korisnikId + " ne moze biti obrisan jer ne postoji");
		} else {
			korisnikService.deleteKorisnik(korisnikId);
			logger.info("Korisnik sa id-om " + korisnikId + " je uspesno obrisan");
		}

	}

	@GET
	@Path("/{korisnikId}/prometi")
	public Response getSvePesmePoIzvodjacu(@PathParam("korisnikId") Long korisnikId) throws ClassNotFoundException {
		logger.info("Prikaz pesama za izvodjaca sa id-om " + korisnikId);

		List<Promet> prometi = prometService.getSviPrometiPoKorisniku(korisnikId);
		GenericEntity<List<Promet>> list = new GenericEntity<List<Promet>>(prometi) {
		};

		Korisnik k = korisnikService.getKorisnik(korisnikId);

		Response r;

		if (k.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoje prometi za korisnika sa id-om " + korisnikId)
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje prometi za korisnika sa id-om " + korisnikId);
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Uspesan prikaz prometa za korisnika sa id-om " + korisnikId);
		}

		return r;
	}

	@POST
	@Path("/login")
	public Response login(Login login) {
		logger.info("Unosenje korisnika");

		try {
			if (korisnikService.login(login.getEmail(), login.getSifra()) != "") {
				return Response.ok(korisnikService.login(login.getEmail(), login.getSifra()))
						.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST")
						.allow("OPTIONS").build();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Resorurce not found");
			e.printStackTrace();
		}
		return Response.status(Response.Status.CONFLICT).header("Access-Control-Allow-Origin", "*")
				.entity("Pogresna sifra!!!").header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
	}

}
