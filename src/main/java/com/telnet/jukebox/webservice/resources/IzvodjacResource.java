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

import com.telnet.jukebox.webservice.dto.IzvodjacDTO;
import com.telnet.jukebox.webservice.dto.PesmaDTO;
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
	public Response getIzvodjaci() throws ClassNotFoundException {
		logger.info("Prikaz svih izvodjaca");

		List<IzvodjacDTO> izvodjaci = izvodjacService.getIzvodjaci();
		GenericEntity<List<IzvodjacDTO>> list = new GenericEntity<List<IzvodjacDTO>>(izvodjaci) {
		};

		Response r;

		if (list == null) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*").entity("Ne postoje uneti izvodjaci")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje uneti izvodjaci");
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Izvodjaci su uspesno prikazani");
		}

		return r;
	}

	@GET
	@Path("/{izvodjacId}")
	public Response getIzvodjac(@PathParam("izvodjacId") int izvodjacId) throws ClassNotFoundException {
		logger.info("Prikaz izvodjaca sa id-om " + izvodjacId);

		IzvodjacDTO i = izvodjacService.getIzvodjac(izvodjacId);

		Response r;

		if (i.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoji izvodjac sa id-om " + izvodjacId).header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.error("Ne postoji izvodjac sa id-om " + izvodjacId);
		} else {
			r = Response.ok(i).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.info("Uspesno prikazan izvodjac sa id-om " + izvodjacId);
		}

		return r;
	}

	@POST
	public IzvodjacDTO addIzvodjaca(IzvodjacDTO izvodjac) throws ClassNotFoundException {
		logger.info("Unosenje izvodjaca");

		IzvodjacDTO i = new IzvodjacDTO();

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
	public IzvodjacDTO updateIzvodjac(@PathParam("izvodjacId") int izvodjacId, IzvodjacDTO izvodjac)
			throws ClassNotFoundException {
		izvodjac.setId(izvodjacId);

		logger.info("Modifikovanje izvodjaca sa id-om " + izvodjacId);

		IzvodjacDTO i = izvodjacService.getIzvodjac(izvodjacId);

		if (i.getId() == 0) {
			logger.error("Izvodjac sa id-om " + izvodjacId + " ne moze biti modifikovan jer ne postoji");
		} else {
			i = izvodjacService.updateIzvodjac(izvodjac);
			logger.info("Izvodjac sa id-om " + izvodjacId + " je uspesno modifikovan");
		}

		return i;
	}

	@DELETE
	@Path("/{izvodjacId}")
	public void deleteIzvodjac(@PathParam("izvodjacId") int izvodjacId) throws ClassNotFoundException {
		logger.info("Brisanje izvodjaca sa id-om " + izvodjacId);

		IzvodjacDTO i = izvodjacService.getIzvodjac(izvodjacId);

		if (i.getId() == 0) {
			logger.error("Izvodjac sa id-om " + izvodjacId + " ne moze biti obrisan jer ne postoji");
		} else {
			izvodjacService.removeIzvodjac((izvodjacId));
			logger.info("Izvodjac sa id-om " + izvodjacId + " je uspesno obrisan");
		}

	}

	@GET
	@Path("/{izvodjacId}/pesme")
	public Response getSvePesmePoIzvodjacu(@PathParam("izvodjacId") int izvodjacId) throws ClassNotFoundException {
		logger.info("Prikaz pesama za izvodjaca sa id-om " + izvodjacId);

		List<PesmaDTO> pesme = pesmaService.getSvePesmePoIzvodjacu(izvodjacId);
		GenericEntity<List<PesmaDTO>> list = new GenericEntity<List<PesmaDTO>>(pesme) {
		};

		IzvodjacDTO i = izvodjacService.getIzvodjac(izvodjacId);

		Response r;

		if (i.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoje pesme za izvodjaca sa id-om " + izvodjacId)
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje pesme za izvodjaca sa id-om " + izvodjacId);
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Uspesan prikaz pesama za izvodjaca sa id-om " + izvodjacId);
		}

		return r;
	}

}
