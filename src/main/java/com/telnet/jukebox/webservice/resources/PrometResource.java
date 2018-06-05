package com.telnet.jukebox.webservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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

import com.telnet.jukebox.webservice.dto.IzvodjacDTO;
import com.telnet.jukebox.webservice.dto.PesmaDTO;
import com.telnet.jukebox.webservice.dto.PrometDTO;
import com.telnet.jukebox.webservice.service.PrometService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/prometi")
@Api(value = "prometi")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PrometResource {

	final static Logger logger = Logger.getLogger(PrometResource.class);

	public PrometService prometService = new PrometService();

	@GET
	/*@ApiOperation(value = "Prikazi sve promete",
    response = PrometDTO.class,
    responseContainer = "List")
	@ApiResponses(value = { 
		      @ApiResponse(code = 204, message = "Ne postoje uneti prometi"),
		      @ApiResponse(code = 200, message = "Uspesan prikaz") })*/
	public Response getSviPrometi() throws ClassNotFoundException {
		logger.info("Prikaz svih prometa");

		List<PrometDTO> prometi = prometService.getSviPrometi();
		GenericEntity<List<PrometDTO>> list = new GenericEntity<List<PrometDTO>>(prometi) {
		};

		Response r;

		if (prometi.isEmpty()) {
			r = Response.status(204).header("Access-Control-Allow-Origin", "*").entity("Ne postoje uneti prometi")
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
	/*@ApiOperation(value = "Prikazi promet sa datim id-om",
    response = PrometDTO.class,
    responseContainer = "PrometDTO")
	@ApiResponses(value = { 
		      @ApiResponse(code = 204, message = "Ne postoji promet sa datim id-om"),
		      @ApiResponse(code = 200, message = "Uspesan prikaz") })*/
	public Response getPromet(@PathParam("prometId") int prometId) throws ClassNotFoundException {
		logger.info("Prikaz prometa sa id-om " + prometId);

		PrometDTO p = prometService.getPromet(prometId);

		Response r;

		if (p.getId() == 0) {
			r = Response.status(204).header("Access-Control-Allow-Origin", "*")
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
	/*@ApiOperation(value = "Prikazi top 5 pesmama",
    response = PesmaDTO.class,
    responseContainer = "List")
	@ApiResponses(value = { 
		      @ApiResponse(code = 204, message = "Ne postoje pesme"),
		      @ApiResponse(code = 200, message = "Uspesan prikaz") })*/
	public Response getTop5Songs() throws ClassNotFoundException {
		logger.info("Prikaz top 5 pesama");

		List<PrometDTO> prometi = prometService.getTop5Songs();
		GenericEntity<List<PrometDTO>> list = new GenericEntity<List<PrometDTO>>(prometi) {
		};

		Response r;

		if (prometi.isEmpty()) {
			r = Response.status(204).header("Access-Control-Allow-Origin", "*").entity("Ne postoje pesme")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje pesme");
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Top 5 pesama je uspesno prikazano");
		}

		return r;
	}

	@GET
	@Path("/top5artists")
	/*@ApiOperation(value = "Prikazi top 5 izvodjaca",
    response = IzvodjacDTO.class,
    responseContainer = "List")
	@ApiResponses(value = { 
		      @ApiResponse(code = 204, message = "Ne postoje izvodjaci"),
		      @ApiResponse(code = 200, message = "Uspesan prikaz") })*/
	public Response getTop5Artists() throws ClassNotFoundException {
		logger.info("Prikaz top 5 izvodjaca");

		List<PrometDTO> prometi = prometService.getTop5Artists();
		GenericEntity<List<PrometDTO>> list = new GenericEntity<List<PrometDTO>>(prometi) {
		};

		Response r;

		if (prometi.isEmpty()) {
			r = Response.status(204).header("Access-Control-Allow-Origin", "*").entity("Ne postoje izvodjaci")
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
	/*@ApiOperation(value = "Unesi novi promet",
    response = PrometDTO.class,
    responseContainer = "PrometDTO")
	@ApiResponses(value = { 
		      @ApiResponse(code = 200, message = "Uspesan unos"),
		      @ApiResponse(code = 400, message = "Greska pri unosu prometa"),
		      @ApiResponse(code = 401, message = "Token je istekao")})*/
	public Response addPromet(@HeaderParam("Authorization") String authorization, @RequestBody PrometDTO promet)
			throws ClassNotFoundException {
		logger.info("Unosenje prometa");

		// String authenticationheader =
		// requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		System.out.println(authorization);
		logger.info(authorization);

		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey("sifra".getBytes()).parseClaimsJws(authorization);

			int id = (int) claims.getBody().get("id");
			promet.setIdKor(id);

			System.out.println("Korisnik id from token si  :" + id);
			/*
			 * System.out.println("LONG ID IS :"+id);
			 * System.out.println("CLIMES SIZE "+claims.getBody().size());
			 * System.out.println(id);
			 */
			// Long idKor= Long.parseLong(id);
			/*
			 * System.out.println(idKor); promet.setIdKor(idKor);
			 * 
			 * /*Jws<Claims> claims = Jwts.parser()
			 * .setSigningKey(login.getSifra().getBytes()) .parseClaimsJws(Authorization);
			 * Object exp = claims.getBody(); System.out.println(exp);
			 */

			// logger.info("podaci:"+ exp);

			PrometDTO promet1 = prometService.addPromet(promet.getPesmaId(), promet.getIdKor(), promet);
			Response r;
			try {
				r = Response.ok(promet1).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
				logger.info("Promet je uspesno unet");
			} catch (Exception e) {
				r = Response.status(400).header("Access-Control-Allow-Origin", "*")
						.entity("Greska pri unosu prometa:\n" + e.getMessage())
						.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
				logger.error("Greska pri unosu prometa:\n" + e.getMessage());
			}
			return r;
		} catch (ExpiredJwtException e) {
			// TODO: handle exception
			Response r;
			r = Response.status(401).header("Access-Control-Allow-Origin", "*")
					.entity("Token je istekao:\n" + e.getMessage())
					.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
			logger.error("Token je istekao. Ulogujte se ponovo.");
			System.out.println("Token je istekao. Ulogujte se ponovo.");
			return r;
		}

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
	/*@ApiOperation(value = "Obrisi promet sa datim id-om")*/
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deletePromet(@PathParam("prometId") int prometId) throws ClassNotFoundException {
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
