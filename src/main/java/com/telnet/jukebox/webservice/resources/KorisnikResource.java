package com.telnet.jukebox.webservice.resources;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.telnet.jukebox.webservice.dto.KorisnikDTO;
import com.telnet.jukebox.webservice.dto.PrometDTO;
import com.telnet.jukebox.webservice.model.Login;
import com.telnet.jukebox.webservice.service.KorisnikService;
import com.telnet.jukebox.webservice.service.PrometService;

@Path("/korisnici")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class KorisnikResource {

	final static Logger logger = Logger.getLogger(KorisnikResource.class);

	KorisnikService korisnikService = new KorisnikService();
	PrometService prometService = new PrometService();

	@SuppressWarnings("unused")
	@GET
	public Response getKorisnici() throws ClassNotFoundException {
		logger.info("Prikaz svih izvodjaca");

		List<KorisnikDTO> korisnici = korisnikService.getKorisnici();
		GenericEntity<List<KorisnikDTO>> list = new GenericEntity<List<KorisnikDTO>>(korisnici) {
		};

		Response r;

		if (list == null) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*").entity("Ne postoje uneti korisnici")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje uneti korisnici");
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Korisnici su uspesno prikazani");
		}

		return r;
	}

	@GET
	@Path("/{korisnikId}")
	public Response getKorisnik(@PathParam("korisnikId") int korisnikId) throws ClassNotFoundException {
		logger.info("Prikaz korisnika sa id-om " + korisnikId);

		KorisnikDTO k = korisnikService.getKorisnik(korisnikId);

		Response r;

		if (k.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoji korisnik sa id-om " + korisnikId).header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.error("Ne postoji korisnik sa id-om " + korisnikId);
		} else {
			r = Response.ok(k).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET")
					.allow("OPTIONS").build();
			logger.info("Uspesno prikazan korisnik sa id-om " + korisnikId);
		}

		return r;
	}

	@POST
	public Response addKorisnika(KorisnikDTO korisnik) throws ClassNotFoundException {
		logger.info("Unosenje korisnika");
		

		Response r;
		try {
			KorisnikDTO k = korisnikService.insertKorisnik(korisnik);
			r = Response.ok(k).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST")
					.allow("OPTIONS").build();

			logger.info("Korisnik je uspesno unet");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("GRESKA!!");
			r = Response.status(409).header("Access-Control-Allow-Origin", "*").entity("Postoji email u bazi.")
					.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
			logger.error("Postoji email u bazi.\n" + e.getMessage());
		} catch (Exception e) {
			logger.error("Greska pri unosu korisnika:\n" + e.getMessage());
			r = Response.status(403).header("Access-Control-Allow-Origin", "*").entity("Greska pri unosu korisnika")
					.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
		}

		return r;
	}

	@PUT
	@Path("/{korisnikId}")
	public KorisnikDTO updateKorisnik(@PathParam("korisnikId") int korisnikId, KorisnikDTO korisnik)
			throws ClassNotFoundException {
		korisnik.setId(korisnikId);

		logger.info("Modifikovanje korisnika sa id-om " + korisnikId);

		KorisnikDTO k = korisnikService.getKorisnik(korisnikId);

		if (k.getId() == 0) {
			logger.error("Korisnik sa id-om " + korisnikId + " ne moze biti modifikovan jer ne postoji");
		} else {
			k = korisnikService.updateKorisnik(korisnik);
			logger.info("Korisnik sa id-om " + korisnikId + " je uspesno modifikovan");
		}

		return k;
	}

	@DELETE
	@Path("/{korisnikId}")
	public void deleteIzvodjac(@PathParam("korisnikId") int korisnikId) throws ClassNotFoundException {
		logger.info("Brisanje korisnika sa id-om " + korisnikId);

		KorisnikDTO k = korisnikService.getKorisnik(korisnikId);

		if (k.getId() == 0) {
			logger.error("Korisnik sa id-om " + korisnikId + " ne moze biti obrisan jer ne postoji");
		} else {
			korisnikService.deleteKorisnik(korisnikId);
			logger.info("Korisnik sa id-om " + korisnikId + " je uspesno obrisan");
		}

	}

	@GET
	@Path("/{korisnikId}/prometi")
	public Response getSviPrometiPoKorisniku(@PathParam("korisnikId") int korisnikId) throws ClassNotFoundException {
		logger.info("Prikaz pesama za izvodjaca sa id-om " + korisnikId);
		/*String authenticationheader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		System.out.println(authenticationheader);
		logger.info(authenticationheader);*/
		
		/*HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet();
		HttpResponse response;
		try {
			response = client.execute(request);
			Header[] headers = response.getAllHeaders();
			for (Header header : headers) {
				System.out.println("Key : " + header.getName() 
				      + " ,Value : " + header.getValue());
			}

			//get header by 'key'
			String server = response.getFirstHeader("Server").getValue();
			System.out.println(server);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
			
		

		List<PrometDTO> prometi = prometService.getSviPrometiPoKorisniku(korisnikId);
		GenericEntity<List<PrometDTO>> list = new GenericEntity<List<PrometDTO>>(prometi) {
		};

		KorisnikDTO k = korisnikService.getKorisnik(korisnikId);

		Response r;

		if (k.getId() == 0) {
			r = Response.status(404).header("Access-Control-Allow-Origin", "*")
					.entity("Ne postoje prometi za korisnika sa id-om " + korisnikId)
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.error("Ne postoje prometi za korisnika sa id-om " + korisnikId);
		} else {
			r = Response.ok(list).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
			logger.info("Uspesan prikaz prometa za korisnika sa id-om " + korisnikId);
		}

		return r;
	}

	@POST
	@Path("/login")
	public Response login(Login login) {
		logger.info("Login korisnika");

		try {
			if (korisnikService.login(login) != "") {
				logger.info(korisnikService.login(login));
				return Response.ok(korisnikService.login(login)).header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
			}
		} catch (ClassNotFoundException e) {
			logger.error("Resorurce not found");
			System.out.println("Resorurce not found");
			e.printStackTrace();
		}
		logger.error("Pogresna sifra!!!");
		return Response.status(Response.Status.CONFLICT).header("Access-Control-Allow-Origin", "*")
				.entity("Pogresna sifra!!!").header("Access-Control-Allow-Methods", "POST").allow("OPTIONS").build();
	}

	

	/*
	 * @GET
	 * 
	 * @Path("/getuser") public Response getInfoFromtoken(ContainerRequestContext
	 * requestContex) { // ErrorMessages messages = new
	 * ErrorMessages("Token is not valid",401); Response responses =
	 * Response.status(Status.UNAUTHORIZED).entity("Token is not valid").build();
	 * try { return Response.status(Status.ACCEPTED)
	 * .entity(KorisnikService.getDataFromToken(requestContex.getHeaderString(
	 * "Authorization"))).build();
	 * 
	 * } catch (Exception ex) {
	 * 
	 * throw new WebApplicationException(responses); } }
	 */

}
