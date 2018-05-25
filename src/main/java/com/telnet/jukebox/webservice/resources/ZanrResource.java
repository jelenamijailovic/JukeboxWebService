package com.telnet.jukebox.webservice.resources;

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

import com.telnet.jukebox.webservice.dto.PesmaDTO;
import com.telnet.jukebox.webservice.dto.ZanrDTO;
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
	public Response getZanrovi() throws ClassNotFoundException {
		logger.info("Prikaz svih zanrova");

		List<ZanrDTO> zanrovi = zanrService.getZanrovi();
		GenericEntity<List<ZanrDTO>> list = new GenericEntity<List<ZanrDTO>>(zanrovi) {
		};

		Response r;

		if (list == null) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*").entity("Ne postoje uneti zanrovi")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje uneti zanrovi");
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Zanrovi su uspesno prikazani");
		}

		return r;
	}

	@GET
	@Path("/{zanrId}")
	public Response getZanr(@PathParam("zanrId") int zanrId) throws ClassNotFoundException {
		logger.info("Prikaz zanra sa id-om " + zanrId);

		ZanrDTO z = zanrService.getZanr(zanrId);

		Response r;

		if (z.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoji zanr sa id-om " + zanrId).header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.error("Ne postoji zanr sa id-om " + zanrId);
		} else {
			r = Response.ok(z).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.info("Uspesno prikazan zanr sa id-om " + zanrId);
		}

		return r;
	}

	@POST
	public ZanrDTO addZanr(ZanrDTO zanr) throws ClassNotFoundException {
		logger.info("Unosenje zanra");

		ZanrDTO z = new ZanrDTO();

		try {
			z = zanrService.addZanr(zanr);
			logger.info("Zanr je uspesno unet");
		} catch (Exception e) {
			logger.error("Greska pri unosu zanra:\n" + e.getMessage());
		}

		return z;
	}

	@PUT
	@Path("/{zanrId}")
	public ZanrDTO updateZanr(@PathParam("zanrId") int zanrId, ZanrDTO zanr) throws ClassNotFoundException {
		zanr.setId(zanrId);

		logger.info("Modifikovanje zanra sa id-om " + zanrId);

		ZanrDTO z = zanrService.getZanr(zanrId);

		if (z.getId() == 0) {
			// || z.getNaziv() == null
			logger.error("Zanr sa id-om " + zanrId + " ne moze biti modifikovan jer ne postoji");
		} else {
			z = zanrService.updateZanr(zanr);
			logger.info("Zanr sa id-om " + zanrId + " je uspesno modifikovan");
		}

		return z;
	}

	@DELETE
	@Path("/{zanrId}")
	public void deleteZanr(@PathParam("zanrId") int zanrId) throws ClassNotFoundException {
		logger.info("Brisanje zanra sa id-om " + zanrId);

		ZanrDTO z = zanrService.getZanr(zanrId);

		if (z.getId() == 0) {
			logger.error("Zanr sa id-om " + zanrId + " ne moze biti obrisan jer ne postoji");
		} else {
			zanrService.removeZanr(zanrId);
			logger.info("Zanr sa id-om " + zanrId + " je uspesno obrisan");
		}

	}

	@GET
	@Path("/{zanrId}/pesme")
	public Response getSvePesmePoZanru(@PathParam("zanrId") int zanrId) throws ClassNotFoundException {
		logger.info("Prikaz pesama za zanr sa id-om " + zanrId);

		List<PesmaDTO> pesme = pesmaService.getSvePesmePoZanru(zanrId);
		GenericEntity<List<PesmaDTO>> list = new GenericEntity<List<PesmaDTO>>(pesme) {
		};

		ZanrDTO z = zanrService.getZanr(zanrId);

		Response r;

		if (z.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoje pesme za zanr sa id-om").header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.error("Ne postoje pesme za zanr sa id-om " + zanrId);
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Uspesan prikaz pesama za zanr sa id-om " + zanrId);

		}

		return r;
	}

}
