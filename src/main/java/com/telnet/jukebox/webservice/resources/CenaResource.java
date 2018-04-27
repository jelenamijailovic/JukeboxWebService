package com.telnet.jukebox.webservice.resources;

import java.sql.SQLException;
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

import com.telnet.jukebox.webservice.model.Cena;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.service.CenaService;
import com.telnet.jukebox.webservice.service.PesmaService;

@Path("/cene")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class CenaResource {

	final static Logger logger = Logger.getLogger(ZanrResource.class);

	CenaService cenaService = new CenaService();
	PesmaService pesmaService = new PesmaService();

	@SuppressWarnings("unused")
	@GET
	public Response getCene() throws ClassNotFoundException, SQLException {

		logger.info("Prikaz svih cena");

		List<Cena> cene = cenaService.getCene();
		GenericEntity<List<Cena>> list = new GenericEntity<List<Cena>>(cene) {
		};
		Response r = null;

		try {
			if (list == null) {
				r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoje unete cene");

			} else {
				r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Cene su uspesno prikazane");

			}
		} catch (Exception e) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu cena:\n" + e.getMessage());

		}
		return r;
	}

	@GET
	@Path("/{cenaId}")
	public Response getCena(@PathParam("cenaId") Long cenaId) throws ClassNotFoundException, SQLException {

		Response r = null;

		try {
			if (cenaService.getCena(cenaId) == null) {
				r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoji cena sa id-om " + cenaId);
			} else {
				r = Response.ok(cenaService.getCena(cenaId)).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Uspesno prikazan cena sa id-om " + cenaId);
			}
		} catch (Exception e) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu cene:\n" + cenaId);
		}
		return r;
	}

	@POST
	public Cena addCena(Cena cena) throws ClassNotFoundException, SQLException {

		logger.info("Unosenje cene");

		Cena c = null;

		try {
			c = cenaService.addCena(cena);
			logger.info("Cena je uspesno unet");
		} catch (Exception e) {
			logger.error("Greska pri unosu cene:\n" + e.getMessage());
		}
		return c;
	}

	@PUT
	@Path("/{cenaId}")
	public Cena updateIzvodjac(@PathParam("cenaId") Long cenaId, Cena cena)
			throws ClassNotFoundException, SQLException {

		cena.setId(cenaId);

		logger.info("Modifikovanje cene sa id-om " + cenaId);

		Cena c = null;

		try {
			if (cenaService.getCena(cenaId) == null) {
				logger.warn("Cena sa id-om " + cenaId + " ne moze biti modifikovana jer ne postoji");
			} else {
				c = cenaService.updateCena(cena);
				logger.info("Cena je uspesno modifikovana");
			}
		} catch (Exception e) {
			logger.error("Greska pri modifikaciji cene:\n" + e.getMessage());
		}
		return c;
	}

	@DELETE
	@Path("/{cenaId}")
	public void deleteCena(@PathParam("cenaId") Long cenaId) throws ClassNotFoundException, SQLException {

		logger.info("Brisanje cene sa id-om " + cenaId);

		try {
			if (cenaService.getCena(cenaId) == null) {
				logger.warn("Cena sa id-om " + cenaId + " ne moze biti obrisana jer ne postoji");
			} else {
				cenaService.removeCena(cenaId);
				logger.info("Cena je uspesno obrisana");
			}
		} catch (Exception e) {
			logger.error("Greska pri brisanju cene:\n" + e.getMessage());
		}
	}

	@GET
	@Path("/{cenaId}/pesme")
	public Response getSvePesmePoCeni(@PathParam("cenaId") Long cenaId) throws ClassNotFoundException, SQLException {

		logger.info("Prikaz pesama za cenu sa id-om " + cenaId);

		List<Pesma> pesme = pesmaService.getSvePesmePoCeni(cenaId);
		GenericEntity<List<Pesma>> list = new GenericEntity<List<Pesma>>(pesme) {
		};

		Response r = null;

		try {
			if (cenaService.getCena(cenaId) == null) {
				r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoje pesme za cenu sa id-om " + cenaId);
			} else {
				r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Uspesan prikaz pesama za cenu sa id-om " + cenaId);
			}
		} catch (Exception e) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu pesama za cenu sa id-om " + cenaId + ":\n" + e.getMessage());
		}
		return r;
	}

}
