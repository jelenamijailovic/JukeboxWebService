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

import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Zanr;
import com.telnet.jukebox.webservice.service.PesmaService;
import com.telnet.jukebox.webservice.service.ZanrService;

@Path("/zanrovi")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ZanrResource {

	final static Logger logger = Logger.getLogger(ZanrResource.class);

	ZanrService zanrService = new ZanrService();
	PesmaService pesmaService = new PesmaService();

	@SuppressWarnings("unused")
	@GET
	public Response getZanrovi() throws ClassNotFoundException, SQLException {
		// return zanrService.getZanrovi();
		logger.info("Prikaz svih zanrova");

		List<Zanr> zanrovi = zanrService.getZanrovi();
		GenericEntity<List<Zanr>> list = new GenericEntity<List<Zanr>>(zanrovi) {
		};
		// GenericEntity<List<Zanr>> list = null;z

		try {
			if (list == null) {

				Response r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoje uneti zanrovi.");
				return r;
			} else {
				Response r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Zanrovi su uspesno prikazani.");
				return r;
			}
		} catch (Exception e) {
			Response r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu zanra:\n" + e.getMessage());
			return r;
		}

	}

	@GET
	@Path("/{zanrId}")
	public Response getZanr(@PathParam("zanrId") Long zanrId) throws ClassNotFoundException, SQLException {
		// return zanrService.getZanr(zanrId);
		logger.info("Prikaz zanra sa id-om " + zanrId);
		try {
			if (zanrService.getZanr(zanrId) == null) {
				Response r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoji zanr sa id-om " + zanrId);
				return r;
			} else {
				Response r = Response.ok(zanrService.getZanr(zanrId)).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Uspesno prikazan zanr sa id-om " + zanrId);
				return r;
			}
		} catch (Exception e) {
			Response r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu zanra:\n" + e.getMessage());
			return r;
		}
	}

	@POST
	public Zanr addZanr(Zanr zanr) throws ClassNotFoundException, SQLException {
		logger.info("Unosenje zanra.");

		Zanr z = null;

		try {
			z = zanrService.addZanr(zanr);
			logger.info("Zanr je uspesno unet.");
		} catch (Exception e) {
			logger.error("Greska pri unosu zanra:\n" + e.getMessage());
		}
		return z;
	}

	@PUT
	@Path("/{zanrId}")
	public Zanr updateZanr(@PathParam("zanrId") Long zanrId, Zanr zanr) throws ClassNotFoundException, SQLException {
		zanr.setId(zanrId);
		logger.info("Modifikovanje zanra.");

		Zanr z = null;

		try {
			if (zanrService.getZanr(zanrId) == null || zanrService.getZanr(zanrId).getNaziv()==null) {
				logger.warn("Zanr sa id-om " + zanrId + " ne moze biti modifikovan jer ne postoji");
			} else {
				z = zanrService.updateZanr(zanr);
				logger.info("Zanr je uspesno modifikovan.");
			}
		} catch (Exception e) {
			logger.error("Greska pri modifikaciji zanra:\n" + e.getMessage());
		}
		return z;
	}

	@DELETE
	@Path("/{zanrId}")
	public void deleteZanr(@PathParam("zanrId") Long zanrId) throws ClassNotFoundException, SQLException {
		logger.info("Brisanje zanra.");
		
		try {
			if (zanrService.getZanr(zanrId) == null) {
				logger.warn("Zanr sa id-om " + zanrId + " ne moze biti obrisan jer ne postoji.");
			} else {
				zanrService.removeZanr(zanrId);
				logger.info("Zanr je uspesno obrisan.");
			}
		} catch (Exception e) {
			logger.error("Greska pri brisanju zanra:\n" + e.getMessage());
		}
	}

	// @Path("/{zanrId}/pesme")
	// public PesmaResource getPesmeResource() {
	// return new PesmaResource();
	// }

	@GET
	@Path("/{zanrId}/pesme")
	public Response getSvePesmePoZanru(@PathParam("zanrId") Long zanrId) throws ClassNotFoundException, SQLException {
		// return pesmaService.getSvePesmePoZanru(zanrId);

		logger.info("Prikaz pesama za zanr sa id-om " + zanrId);

		List<Pesma> pesme = pesmaService.getSvePesmePoZanru(zanrId);
		GenericEntity<List<Pesma>> list = new GenericEntity<List<Pesma>>(pesme) {
		};

		try {
			if (zanrService.getZanr(zanrId) == null) {
				Response r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoje pesme za zanr sa id-om: " + zanrId);
				return r;
			} else {
				Response r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Uspesan prikaz pesama za zanr sa id-om: " + zanrId);
				return r;
			}
		} catch (Exception e) {
			Response r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu pesama za zanr sa id-om" + zanrId + ":\n" + e.getMessage());
			return r;
		}
	}

}
