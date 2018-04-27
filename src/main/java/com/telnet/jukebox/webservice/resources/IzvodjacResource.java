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

import com.telnet.jukebox.webservice.model.Izvodjac;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.service.IzvodjacService;
import com.telnet.jukebox.webservice.service.PesmaService;

@Path("/izvodjaci")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class IzvodjacResource {

	final static Logger logger = Logger.getLogger(ZanrResource.class);

	IzvodjacService izvodjacService = new IzvodjacService();
	PesmaService pesmaService = new PesmaService();

	@SuppressWarnings("unused")
	@GET
	public Response getIzvodjaci() throws SQLException, ClassNotFoundException {

		logger.info("Prikaz svih izvodjaca");

		// List<Izvodjac> izvodjaci = izvodjacService.getIzvodjaci();
		// GenericEntity<List<Izvodjac>> list = new
		// GenericEntity<List<Izvodjac>>(izvodjaci) {
		// };
		GenericEntity<List<Izvodjac>> list = null;
		Response r = null;

		try {
			if (list == null) {

				r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoje uneti izvodjaci");

			} else {
				r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Izvodjaci su uspesno prikazani");

			}
		} catch (Exception e) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu izvodjaca:\n" + e.getMessage());

		}
		return r;
	}

	@GET
	@Path("/{izvodjacId}")
	public Response getIzvodjac(@PathParam("izvodjacId") Long izvodjacId) throws SQLException, ClassNotFoundException {

		logger.info("Prikaz izvodjaca sa id-om " + izvodjacId);

		Response r = null;

		try {
			if (izvodjacService.getIzvodjac(izvodjacId) == null) {
				r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoji izvodjac sa id-om " + izvodjacId);
			} else {
				r = Response.ok(izvodjacService.getIzvodjac(izvodjacId)).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Uspesno prikazan izvodjac sa id-om " + izvodjacId);
			}
		} catch (Exception e) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu izvodjaca:\n" + izvodjacId);
		}
		return r;
	}

	@POST
	public Izvodjac addIzvodjaca(Izvodjac izvodjac) throws SQLException, ClassNotFoundException {

		logger.info("Unosenje izvodjaca");

		Izvodjac i = null;

		try {
			i = izvodjacService.addIzvodjac(izvodjac);
			logger.info("Izvodjac je uspesno unet");
		} catch (Exception e) {
			logger.error("Greska pri unosu izvodjaca:\n" + e.getMessage());
		}
		return i;
	}

	@PUT
	@Path("/{izvodjacId}")
	public Izvodjac updateIzvodjac(@PathParam("izvodjacId") Long izvodjacId, Izvodjac izvodjac)
			throws SQLException, ClassNotFoundException {

		izvodjac.setId(izvodjacId);

		logger.info("Modifikovanje izvodjaca sa id-om " + izvodjacId);

		Izvodjac i = null;

		try {
			if (izvodjacService.getIzvodjac(izvodjacId) == null) {
				logger.warn("Zanr sa id-om " + izvodjacId + " ne moze biti modifikovan jer ne postoji");
			} else {
				i = izvodjacService.updateIzvodjac(izvodjac);
				logger.info("Zanr je uspesno modifikovan");
			}
		} catch (Exception e) {
			logger.error("Greska pri modifikaciji zanra:\n" + e.getMessage());
		}
		return i;
	}

	@DELETE
	@Path("/{izvodjacId}")
	public void deleteIzvodjac(@PathParam("izvodjacId") Long izvodjacId) throws SQLException, ClassNotFoundException {

		logger.info("Brisanje izvodjaca sa id-om " + izvodjacId);

		try {
			if (izvodjacService.getIzvodjac(izvodjacId) == null) {
				logger.warn("Izvodjac sa id-om " + izvodjacId + " ne moze biti obrisan jer ne postoji");
			} else {
				izvodjacService.removeIzvodjac((izvodjacId));
				logger.info("Izvodjac je uspesno obrisan");
			}
		} catch (Exception e) {
			logger.error("Greska pri brisanju izvodjaca:\n" + e.getMessage());
		}
	}

	@GET
	@Path("/{izvodjacId}/pesme")
	public Response getSvePesmePoIzvodjacu(@PathParam("izvodjacId") Long izvodjacId)
			throws ClassNotFoundException, SQLException {

		logger.info("Prikaz pesama za izvodjaca sa id-om " + izvodjacId);

		List<Pesma> pesme = pesmaService.getSvePesmePoIzvodjacu(izvodjacId);
		GenericEntity<List<Pesma>> list = new GenericEntity<List<Pesma>>(pesme) {
		};

		Response r = null;

		try {
			if (izvodjacService.getIzvodjac(izvodjacId) == null) {
				r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoje pesme za izvodjaca sa id-om " + izvodjacId);
			} else {
				r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Uspesan prikaz pesama za izvodjaca sa id-om " + izvodjacId);
			}
		} catch (Exception e) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu pesama za izvodjaca sa id-om " + izvodjacId + ":\n" + e.getMessage());
		}
		return r;
	}

}
