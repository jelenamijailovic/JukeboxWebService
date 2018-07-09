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
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor;
import com.telnet.jukebox.webservice.dto.PesmaDTO;
import com.telnet.jukebox.webservice.dto.ZanrDTO;
import com.telnet.jukebox.webservice.service.PesmaService;
import com.telnet.jukebox.webservice.service.ZanrService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ResponseHeader;
//import io.swagger.models.Swagger;

@Path("/zanrovi")
//@Api(value = "zanrovi")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ZanrResource {

	final static Logger logger = Logger.getLogger(ZanrResource.class);

	ZanrService zanrService = new ZanrService();
	PesmaService pesmaService = new PesmaService();

	@GET
	/*
	 * @ApiOperation(value = "Prikazi sve zanrove", response = ZanrDTO.class,
	 * responseContainer = "List")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 204, message = "Ne postoje uneti zanrovi"),
	 * 
	 * @ApiResponse(code = 200, message = "Uspesan prikaz") })
	 */
	public Response getZanrovi() throws ClassNotFoundException {
		logger.info("Prikaz svih zanrova");

		List<ZanrDTO> zanrovi = zanrService.getZanrovi();
		GenericEntity<List<ZanrDTO>> list = new GenericEntity<List<ZanrDTO>>(zanrovi) {
		};

		Response r;

		if (zanrovi.isEmpty()) {
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
	/*
	 * @ApiOperation(value = "Prikazi zanr datog id-a", response = ZanrDTO.class,
	 * responseContainer = "ZanrDTO")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 204, message = "Ne postoji zanr sa datim id-om"),
	 * 
	 * @ApiResponse(code = 200, message = "Uspesan prikaz") })
	 */
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
	/*
	 * @ApiOperation(value = "Unesi novi zanr", response = ZanrDTO.class,
	 * responseContainer = "ZanrDTO")
	 */
	public Response addZanr(ZanrDTO zanr) throws ClassNotFoundException {
		logger.info("Unosenje zanra");

		Response r;

		ZanrDTO z = new ZanrDTO();

		try {
			z = zanrService.addZanr(zanr);
			r = Response.ok(z).build();
			logger.info("Zanr je uspesno unet");
		} catch (NullPointerException e) {
			r = Response.status(400).entity("Morate uneti naziv zanra!!!").build();
			// r = Response.status(406).entity("Zanr je uspesno unet").build();
			logger.error("Greska pri unosu zanra:\n" + e.getMessage());
		}
		
		return r;
	}

	@PUT
	@Path("/{zanrId}")
	/*
	 * @ApiOperation(value = "Izmeni zanr datog id-a", response = ZanrDTO.class,
	 * responseContainer = "ZanrDTO")
	 */
	public Response updateZanr(@PathParam("zanrId") int zanrId, ZanrDTO zanr) throws ClassNotFoundException {
		zanr.setId(zanrId);

		logger.info("Modifikovanje zanra sa id-om " + zanrId);

		ZanrDTO z = zanrService.getZanr(zanrId);

		Response r;
		
		if (z.getId() == 0 || z.getNaziv() == null) {
			// || z.getNaziv() == null
			r= Response.status(404).entity("Zanr sa id-om " + zanrId + " ne moze biti modifikovan jer ne postoji").build();
			logger.error("Zanr sa id-om " + zanrId + " ne moze biti modifikovan jer ne postoji");
		} else {
			try {
			z = zanrService.updateZanr(zanr);
			r= Response.ok(z).build();
			logger.info("Zanr sa id-om " + zanrId + " je uspesno modifikovan");
			}catch (NullPointerException e) {
				r= Response.status(400).entity("Morate uneti naziv!!!").build();
			}
		}

		return r;
	}

	@DELETE
	@Path("/{zanrId}") /* @ApiOperation(value = "Obrisi zanr sa datim id-om") */
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
	/*
	 * @ApiOperation(value = "Prikazi pesme datog zanra", response = PesmaDTO.class,
	 * responseContainer = "List")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 204, message =
	 * "Ne postoje pesme za zanr sa datim id-om"),
	 * 
	 * @ApiResponse(code = 200, message = "Uspesan prikaz") })
	 */ public Response getSvePesmePoZanru(@PathParam("zanrId") int zanrId) throws ClassNotFoundException {
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
	// Swagger swagger = new
	// SwaggerParser().read("http://localhost:8080/JukeboxWebService/webapi/zanrovi");

}
