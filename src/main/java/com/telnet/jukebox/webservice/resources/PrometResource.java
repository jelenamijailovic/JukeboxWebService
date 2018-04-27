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

import com.telnet.jukebox.webservice.model.Promet;
import com.telnet.jukebox.webservice.service.PrometService;

@Path("/prometi")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PrometResource {

	final static Logger logger = Logger.getLogger(ZanrResource.class);

	public PrometService prometService = new PrometService();

	@SuppressWarnings("unused")
	@GET
	public Response getSviPrometiPo() throws ClassNotFoundException, SQLException {

		logger.info("Prikaz svih prometa");

		List<Promet> prometi = prometService.getSviPrometi();
		GenericEntity<List<Promet>> list = new GenericEntity<List<Promet>>(prometi) {
		};

		Response r = null;

		try {
			if (list == null) {

				r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoje uneti prometi");

			} else {
				r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Prometi su uspesno prikazani");

			}
		} catch (Exception e) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu prometa:\n" + e.getMessage());

		}
		return r;
	}

	@GET
	@Path("/{prometId}")
	public Response getPromet(@PathParam("prometId") Long prometId) throws ClassNotFoundException, SQLException {

		logger.info("Prikaz prometa sa id-om " + prometId);

		Response r = null;

		try {
			if (prometService.getPromet(prometId) == null) {
				r = Response.noContent().header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.warn("Ne postoji promet sa id-om " + prometId);
			} else {
				r = Response.ok(prometService.getPromet(prometId)).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Uspesno prikazan promet sa id-om " + prometId);
			}
		} catch (Exception e) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Greska pri prikazu prometa:\n" + prometId);
		}
		return r;
	}

	@POST
	@Path("/{pesmaId}")
	public Promet addPromet(@PathParam("pesmaId") Long pesmaId, Promet promet)
			throws ClassNotFoundException, SQLException {

		logger.info("Unosenje prometa");

		Promet p = null;

		try {
			p = prometService.addPromet(pesmaId, promet);
			logger.info("Promet je uspesno unet");
		} catch (Exception e) {
			logger.error("Greska pri unosu prometa:\n" + e.getMessage());
		}
		return p;
	}

	@PUT
	@Path("/{prometId}")
	public Promet updatePrometPoPesmi(@PathParam("prometId") Long prometId, Promet promet)
			throws ClassNotFoundException, SQLException {

		promet.setId(prometId);

		logger.info("Modifikovanje prometa sa id-om " + prometId);

		Promet p = null;

		try {
			if (prometService.getPromet(prometId) == null) {
				logger.warn("Promet sa id-om " + prometId + " ne moze biti modifikovan jer ne postoji");
			} else {
				p = prometService.updatePromet(promet);
				logger.info("Promet je uspesno modifikovan");
			}
		} catch (Exception e) {
			logger.error("Greska pri modifikaciji prometa:\n" + e.getMessage());
		}
		return p;
	}

	@DELETE
	@Path("/{prometId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deletePromet(@PathParam("prometId") Long prometId) throws ClassNotFoundException, SQLException {

		logger.info("Brisanje prometa sa id-om " + prometId);

		try {
			if (prometService.getPromet(prometId) == null) {
				logger.warn("Promet sa id-om " + prometId + " ne moze biti obrisan jer ne postoji");
			} else {
				prometService.deletePromet(prometId);
				logger.info("Promet je uspesno obrisan");
			}
		} catch (Exception e) {
			logger.error("Greska pri brisanju prometa:\n" + e.getMessage());
		}

		// Podcast podcastById = podcastService.getPodcastById(id);
		// return Response.ok() //200
		// .entity(null)
		// .header("Access-Control-Allow-Origin", "*")
		// .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
		// .allow("OPTIONS").build();
	}

}
