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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Promet;
import com.telnet.jukebox.webservice.service.PesmaService;
import com.telnet.jukebox.webservice.service.PrometService;

import javax.ws.rs.core.GenericEntity;

@Path("/pesme")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PesmaResource {

	final static Logger logger = Logger.getLogger(ZanrResource.class);

	PesmaService pesmaService = new PesmaService();
	PrometService prometService = new PrometService();

	@SuppressWarnings("unused")
	@GET
	public Response getSvePesme() throws ClassNotFoundException {
		logger.info("Prikaz svih pesama");

		List<Pesma> pesme = pesmaService.getSvePesme();
		GenericEntity<List<Pesma>> list = new GenericEntity<List<Pesma>>(pesme) {
		};

		Response r;

		if (list == null) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*").entity("Ne postoje unete pesme")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje unete pesme");
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Pesme su uspesno prikazane");
		}

		return r;
	}

	@GET
	@Path("/{pesmaId}")
	public Response getPesma(@PathParam("pesmaId") Long pesmaId) throws ClassNotFoundException {
		logger.info("Prikaz pesme sa id-om " + pesmaId);

		Pesma p = pesmaService.getPesma(pesmaId);

		Response r;

		if (p.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoji pesma sa id-om " + pesmaId).header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.error("Ne postoji pesma sa id-om " + pesmaId);
		} else {
			r = Response.ok(p).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.info("Uspesno prikazana pesma sa id-om " + pesmaId);
		}

		return r;
	}

	@POST
	@Path("/{zanrId}/{izvodjacId}/{cenaId}")
	public Pesma addPesma(@PathParam("izvodjacId") Long izvodjacId, @PathParam("zanrId") Long zanrId,
			@PathParam("cenaId") Long cenaId, Pesma pesma) throws ClassNotFoundException {
		logger.info("Unosenje pesme");

		Pesma p = new Pesma();

		try {
			p = pesmaService.addPesma(izvodjacId, zanrId, cenaId, pesma);
			logger.info("Pesma je uspesno uneta");
		} catch (Exception e) {
			logger.error("Greska pri unosu pesme:\n" + e.getMessage());
		}

		return p;
	}

	@PUT
	@Path("/{pesmaId}")
	public Pesma updatePesma(@PathParam("pesmaId") Long pesmaId, Pesma pesma) throws ClassNotFoundException {
		pesma.setId(pesmaId);

		logger.info("Modifikovanje pesme sa id-om " + pesmaId);

		Pesma p = pesmaService.getPesma(pesmaId);

		if (p.getId() == 0) {
			logger.error("Pesma sa id-om " + pesmaId + " ne moze biti modifikovana jer ne postoji");
		} else {
			p = pesmaService.updatePesma(pesma);
			logger.info("Pesma sa id-om " + pesmaId + " je uspesno modifikovana");
		}

		return p;
	}

	@DELETE
	@Path("/{pesmaId}")
	public void deletePesma(@PathParam("pesmaId") Long pesmaId) throws ClassNotFoundException {
		logger.info("Brisanje pesme sa id-om " + pesmaId);

		Pesma p = pesmaService.getPesma(pesmaId);

		if (p.getId() == 0) {
			logger.error("Pesma sa id-om " + pesmaId + " ne moze biti obrisana jer ne postoji");
		} else {
			pesmaService.deletePesma(pesmaId);
			logger.info("Pesma sa id-om " + pesmaId + " je uspesno obrisana");
		}

	}

	@GET
	@Path("/{pesmaId}/prometi")
	public Response getSviPrometiPoPesmi(@PathParam("pesmaId") Long pesmaId) throws ClassNotFoundException {
		logger.info("Prikaz prometa za pesmu sa id-om " + pesmaId);

		List<Promet> prometi = prometService.getSviPrometiPoPesmi(pesmaId);
		GenericEntity<List<Promet>> list = new GenericEntity<List<Promet>>(prometi) {
		};

		Pesma p = pesmaService.getPesma(pesmaId);

		Response r;

		if (p.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoje prometi za pesmu sa id-om " + pesmaId)
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje prometi za pesmu sa id-om " + pesmaId);
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Uspesan prikaz prometa za pesmu sa id-om " + pesmaId);
		}

		return r;
	}

}
