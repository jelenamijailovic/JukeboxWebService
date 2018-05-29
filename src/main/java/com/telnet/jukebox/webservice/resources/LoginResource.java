package com.telnet.jukebox.webservice.resources;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.telnet.jukebox.webservice.database.KorisnikDAO;
import com.telnet.jukebox.webservice.dto.LoginDTO;
import com.telnet.jukebox.webservice.service.KorisnikService;
import com.telnet.jukebox.webservice.service.LoginService;

//@Path("/korisnici/login")
public class LoginResource {
	
	final static Logger logger = Logger.getLogger(LoginResource.class);

	// LoginSecurity loginsecurity = new LoginSecurity();
	KorisnikService korisnikService = new KorisnikService();
	LoginService service = new LoginService();
	// LoginDAO loginDao = new LoginDAO();
	KorisnikDAO korisnikDao = new KorisnikDAO();
	// ListService listservice = new ListService();

	public static String secret = "secret";

//	@POST
	/*@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response checkEmailPassword(LoginDTO login) {
		try {
			String email = korisnikDao.loginKorisnik(login.getEmail(), login.getSifra()).getEmail();
			String sifra = korisnikDao.loginKorisnik(login.getEmail(), login.getSifra()).getSifra();
			Long id = korisnikDao.loginKorisnik(login.getEmail(), login.getSifra()).getId();

			System.out.println(email);
			logger.info(email);
			Claims claims = Jwts.claims().setSubject("info");
			claims.put("id", id);
			claims.put("email", email);
			claims.put("sifra", sifra);
			Long time = System.currentTimeMillis();
			claims.setExpiration(new Date(time + 15000));
			
			Token token = new Token(
					Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact());
			return Response.status(Status.ACCEPTED).entity(token).build();
		} catch (Exception ex) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}*/

//	@GET
//	@Path("/getuser")
	/*@Produces(MediaType.APPLICATION_JSON)
	public Response getInfoFromtoken(ContainerRequestContext requestContex) {
		// ErrorMessages messages = new ErrorMessages("Token is not valid",401);
		Response responses = Response.status(Status.UNAUTHORIZED).entity("Token is not valid").build();
		try {
			return Response.status(Status.ACCEPTED)
					.entity(KorisnikService.getDataFromToken(requestContex.getHeaderString("Authorization"))).build();

		} catch (Exception ex) {

			throw new WebApplicationException(responses);
		}
	}*/
}
