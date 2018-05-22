package com.telnet.jukebox.webservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;

import com.telnet.jukebox.webservice.dto.PrometDTO;
import com.telnet.jukebox.webservice.service.PrometService;

@Path("/prometi")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PrometResource {

	final static Logger logger = Logger.getLogger(ZanrResource.class);

	public PrometService prometService = new PrometService();

	@SuppressWarnings("unused")
	@GET
	public Response getSviPrometi() throws ClassNotFoundException {
		logger.info("Prikaz svih prometa");

		List<PrometDTO> prometi = prometService.getSviPrometi();
		GenericEntity<List<PrometDTO>> list = new GenericEntity<List<PrometDTO>>(prometi) {
		};

		Response r;

		if (list == null) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*").entity("Ne postoje uneti prometi")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje uneti prometi");
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Prometi su uspesno prikazani");
		}

		return r;
	}

	@GET
	@Path("/{prometId}")
	public Response getPromet(@PathParam("prometId") Long prometId) throws ClassNotFoundException {
		logger.info("Prikaz prometa sa id-om " + prometId);

		PrometDTO p = prometService.getPromet(prometId);

		Response r;

		if (p.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoji promet sa id-om " + prometId).header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.error("Ne postoji promet sa id-om " + prometId);
		} else {
			r = Response.ok(prometService.getPromet(prometId)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Uspesno prikazan promet sa id-om " + prometId);
		}

		return r;
	}

	@GET
	@Path("/top5songs")
	public Response getTop5Songs() throws ClassNotFoundException {
		logger.info("Prikaz top 5 pesama");

		List<PrometDTO> prometi = prometService.getTop5Songs();
		GenericEntity<List<PrometDTO>> list = new GenericEntity<List<PrometDTO>>(prometi) {
		};

		Response r;

		if (prometi == null) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*").entity("Ne postoje pesme")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje pesme");
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Top 5 pesama je uspesno prikazano");
		}

		return r;
	}

	@SuppressWarnings("unused")
	@GET
	@Path("/top5artists")
	public Response getTop5Artists() throws ClassNotFoundException {
		logger.info("Prikaz top 5 izvodjaca");

		List<PrometDTO> prometi = prometService.getTop5Artists();
		GenericEntity<List<PrometDTO>> list = new GenericEntity<List<PrometDTO>>(prometi) {
		};

		Response r;

		if (list == null) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*").entity("Ne postoje izvodjaci")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje izvodjaci");
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Top 5 izvodjaca je uspesno prikazano");
		}

		return r;
	}

	@POST
	// @Path("/{pesmaId}")
	public Response addPromet(@RequestBody PrometDTO promet) throws ClassNotFoundException {
		logger.info("Unosenje prometa");

		PrometDTO promet1 = prometService.addPromet(promet.getPesmaId(), promet.getIdKor(), promet);

		Response r;

		try {
			r = Response.ok(promet1).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
			logger.info("Promet je uspesno unet");
		} catch (Exception e) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Greska pri unosu prometa:\n" + e.getMessage())
					.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
			logger.error("Greska pri unosu prometa:\n" + e.getMessage());
		}

		return r;
	}

	// @PUT
	// @Path("/{prometId}")
	// public Promet updatePrometPoPesmi(@PathParam("prometId") Long prometId,
	// Promet promet)
	// throws ClassNotFoundException {
	// promet.setId(prometId);
	//
	// logger.info("Modifikovanje prometa sa id-om " + prometId);
	//
	// Promet p = prometService.getPromet(prometId);
	//
	// if (p.getId() == 0) {
	// logger.error("Promet sa id-om " + prometId + " ne moze biti modifikovan jer
	// ne postoji");
	// } else {
	// p = prometService.updatePromet(promet);
	// logger.info("Promet sa id-om " + prometId + " je uspesno modifikovan");
	// }
	//
	// return promet;
	// }

	@DELETE
	@Path("/{prometId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deletePromet(@PathParam("prometId") Long prometId) throws ClassNotFoundException {
		logger.info("Brisanje prometa sa id-om " + prometId);

		PrometDTO p = prometService.getPromet(prometId);

		if (p.getId() == 0) {
			logger.error("Promet sa id-om " + prometId + " ne moze biti obrisan jer ne postoji");
		} else {
			prometService.deletePromet(prometId);
			logger.info("Promet sa id-om " + prometId + " je uspesno obrisan");
		}

		// Podcast podcastById = podcastService.getPodcastById(id);
		// return Response.ok() //200
		// .entity(null)
		// .header("Access-Control-Allow-Origin", "*")
		// .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
		// .allow("OPTIONS").build();
	}

}
