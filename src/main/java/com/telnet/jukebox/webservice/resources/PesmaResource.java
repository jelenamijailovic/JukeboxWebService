package com.telnet.jukebox.webservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;

import com.telnet.jukebox.webservice.dto.PesmaDTO;
import com.telnet.jukebox.webservice.dto.PrometDTO;
import com.telnet.jukebox.webservice.service.PesmaService;
import com.telnet.jukebox.webservice.service.PrometService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.core.GenericEntity;

@Path("/pesme")
@Api(value = "pesme")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PesmaResource {

	final static Logger logger = Logger.getLogger(PesmaResource.class);

	PesmaService pesmaService = new PesmaService();
	PrometService prometService = new PrometService();

	@GET
	/*
	 * @ApiOperation(value = "Prikazi sve pesme", response = PesmaDTO.class,
	 * responseContainer = "List")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 204, message = "Ne postoje unete pesme"),
	 * 
	 * @ApiResponse(code = 200, message = "Uspesan prikaz") })
	 */
	public Response getSvePesme() throws ClassNotFoundException {
		logger.info("Prikaz svih pesama");

		List<PesmaDTO> pesme = pesmaService.getSvePesme();

		GenericEntity<List<PesmaDTO>> list = new GenericEntity<List<PesmaDTO>>(pesme) {
		};

		Response r;

		if (pesme.isEmpty()) {
			r = Response.status(204).header("Access-Control-Allow-Origin", "*").entity("Ne postoje unete pesme")
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
	@Path("/pagination/{page}")
	/*
	 * @ApiOperation(value = "Prikazi sve pesme na datoj strani", response =
	 * PesmaDTO.class, responseContainer = "List")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 204, message = "Ne postoje unete pesme"),
	 * 
	 * @ApiResponse(code = 200, message = "Uspesan prikaz") })
	 */
	public Response getSvePesmePagination(@PathParam("page") int page) throws ClassNotFoundException {
		logger.info("Prikaz svih pesama");

		List<PesmaDTO> pesme = pesmaService.getSvePesmePagination(page);

		GenericEntity<List<PesmaDTO>> list = new GenericEntity<List<PesmaDTO>>(pesme) {
		};

		Response r;

		if (pesme.isEmpty()) {
			r = Response.status(204).header("Access-Control-Allow-Origin", "*").entity("Ne postoje unete pesme")
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
	/*
	 * @ApiOperation(value = "Prikazi pesmu sa datim id-om", response =
	 * PesmaDTO.class, responseContainer = "PesmaDTO")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 204, message = "Ne postoji pesma sa datim id-om"),
	 * 
	 * @ApiResponse(code = 200, message = "Uspesan prikaz") })
	 */
	public Response getPesma(@PathParam("pesmaId") int pesmaId) throws ClassNotFoundException {
		logger.info("Prikaz pesme sa id-om " + pesmaId);

		PesmaDTO p = pesmaService.getPesma(pesmaId);

		Response r;

		if (p.getId() == 0) {
			r = Response.status(204).header("Access-Control-Allow-Origin", "*")
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
	// @Path("/{zanrId}/{izvodjacId}/{cenaId}")
	/*
	 * @ApiOperation(value = "Unesi novu pesmu", response = PesmaDTO.class,
	 * responseContainer = "PesmaDTO")
	 */
	public PesmaDTO addPesma(@RequestBody PesmaDTO pesma) throws ClassNotFoundException {
		logger.info("Unosenje pesme");

		PesmaDTO p = new PesmaDTO();

		try {
			p = pesmaService.addPesma(pesma.getIzvodjacId(), pesma.getCenaId(), pesma);
			logger.info("Pesma je uspesno uneta");
		} catch (Exception e) {
			logger.error("Greska pri unosu pesme:\n" + e.getMessage());
		}

		return p;
	}

	@PUT
	@Path("/{pesmaId}")
	/*
	 * @ApiOperation(value = "Izmeni pesmu sa datim id-om", response =
	 * PesmaDTO.class, responseContainer = "PesmaDTO")
	 */
	public PesmaDTO updatePesma(@PathParam("pesmaId") int pesmaId, @RequestBody PesmaDTO pesma)
			throws ClassNotFoundException {
		pesma.setId(pesmaId);

		logger.info("Modifikovanje pesme sa id-om " + pesmaId);

		PesmaDTO p = pesmaService.getPesma(pesmaId);

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
	/* @ApiOperation(value = "Obrisi pesmu sa datim id-om") */
	public void deletePesma(@PathParam("pesmaId") int pesmaId) throws ClassNotFoundException {
		logger.info("Brisanje pesme sa id-om " + pesmaId);

		PesmaDTO p = pesmaService.getPesma(pesmaId);

		if (p.getId() == 0) {
			logger.error("Pesma sa id-om " + pesmaId + " ne moze biti obrisana jer ne postoji");
		} else {
			pesmaService.deletePesma(pesmaId);
			logger.info("Pesma sa id-om " + pesmaId + " je uspesno obrisana");
		}

	}

	@GET
	@Path("/recomended")
	/*
	 * @ApiOperation(value = "Prikazi preporucene pesme za korisnika", response =
	 * PesmaDTO.class, responseContainer = "List")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 204, message = "Ne postoje unete pesme"),
	 * 
	 * @ApiResponse(code = 200, message = "Uspesan prikaz"),
	 * 
	 * @ApiResponse(code = 401, message = "Greska pri unosu prometa")})
	 */
	public Response recomended(@HeaderParam("Authorization") String authorization) throws ClassNotFoundException {
		logger.info("Preporucujemo");

		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey("sifra".getBytes()).parseClaimsJws(authorization);

			int id = (int) claims.getBody().get("id");

			List<PesmaDTO> pesme = pesmaService.recomended(id);

			GenericEntity<List<PesmaDTO>> list = new GenericEntity<List<PesmaDTO>>(pesme) {
			};
			logger.info("Korisnik id from token ");
			System.out.println("Korisnik id from token is  :" + id);

			Response r;

			if (pesme.isEmpty()) {
				r = Response.status(204).header("Access-Control-Allow-Origin", "*").entity("Ne postoje unete pesme")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.error("Ne postoje unete pesme");
			} else {
				r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				logger.info("Pesme su uspesno prikazane");

			}
			return r;
		} catch (ExpiredJwtException e) {
			Response r;
			r = Response.status(401).header("Access-Control-Allow-Origin", "*")
					.entity("Greska pri unosu prometa:\n" + e.getMessage())
					.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
			logger.error("Token je istekao. Ulogujte se ponovo.");
			System.out.println("Token je istekao. Ulogujte se ponovo.");
			return r;
		}

	}

	@GET
	@Path("/{pesmaId}/prometi")
	/*
	 * @ApiOperation(value = "Prikazi promete za datu pesmu", response =
	 * PrometDTO.class, responseContainer = "List")
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 204, message =
	 * "Ne postoje prometi za pesmu sa datim id-om"),
	 * 
	 * @ApiResponse(code = 200, message = "Uspesan prikaz") })
	 */
	public Response getSviPrometiPoPesmi(@PathParam("pesmaId") int pesmaId) throws ClassNotFoundException {
		logger.info("Prikaz prometa za pesmu sa id-om " + pesmaId);

		List<PrometDTO> prometi = prometService.getSviPrometiPoPesmi(pesmaId);
		GenericEntity<List<PrometDTO>> list = new GenericEntity<List<PrometDTO>>(prometi) {
		};

		PesmaDTO p = pesmaService.getPesma(pesmaId);

		Response r;

		if (p.getId() == 0) {
			r = Response.status(204).header("Access-Control-Allow-Origin", "*")
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
