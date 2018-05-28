package com.telnet.jukebox.webservice.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.json.JsonException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.telnet.jukebox.webservice.database.KorisnikDAO;
import com.telnet.jukebox.webservice.dto.KorisnikDTO;
import com.telnet.jukebox.webservice.model.Korisnik;
import com.telnet.jukebox.webservice.model.Login;
import com.telnet.jukebox.webservice.resources.LoginResource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class KorisnikService extends Application {

	KorisnikDAO dao = new KorisnikDAO();

	public List<KorisnikDTO> getKorisnici() throws ClassNotFoundException {
		List<KorisnikDTO> list = new ArrayList<KorisnikDTO>();

		for (int i = 0; i < dao.getKorisnici().size(); i++) {
			list.add(entityToDTO(dao.getKorisnici().get(i)));
		}

		// for (int i = 0; i < list.size(); i++) {
		// list.get(i).setSifra(encryptPassword(list.get(i).getSifra()));
		// }
		return list;
	}

	public KorisnikDTO getKorisnik(int korisnikId) throws ClassNotFoundException {
		KorisnikDTO korisnik = entityToDTO(dao.getKorisnik(korisnikId));
		korisnik.setSifra(encryptPassword(korisnik.getSifra()));
		return korisnik;
	}

	public KorisnikDTO insertKorisnik(KorisnikDTO korisnik) throws ClassNotFoundException, SQLException {
		KorisnikDTO korisnikNovi = entityToDTO(dao.insertKorisnik(DTOToEntity(korisnik)));

		return korisnikNovi;
	}

	public KorisnikDTO updateKorisnik(KorisnikDTO korisnik) throws ClassNotFoundException {
		KorisnikDTO korisnikNovi = entityToDTO(dao.updateKorisnik(DTOToEntity(korisnik)));
		korisnikNovi.setSifra(encryptPassword(korisnikNovi.getSifra()));
		return korisnikNovi;
	}

	public void deleteKorisnik(int korisnikId) throws ClassNotFoundException {
		dao.deleteKorisnik(korisnikId);
	}

	private static String encryptPassword(String password) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public String login(Login login) throws ClassNotFoundException {
		KorisnikDTO korisnik = entityToDTO(dao.loginKorisnik(login));

		String jwt = "";

		if (korisnik.getEmail() != null) {
			Long time = System.currentTimeMillis();
			int id = korisnik.getId();
			
			/*Claims claims = Jwts.claims().setSubject("info");
			claims.put("id", id);
			claims.put("email", korisnik.getEmail());
			claims.setExpiration(new Date(time + 950000000));
			
			jwt= Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, "sifra".getBytes()).compact();*/
			
			
			jwt= Jwts.builder()
					  .claim("email", korisnik.getEmail())
					  .claim("id", korisnik.getId())
					  .setExpiration(new Date(time + 600000000))
					  .signWith(
					    SignatureAlgorithm.HS256,
					    "sifra".getBytes()
					  )
					  .compact();
			
			/*Jws<Claims> claims = Jwts.parser()
			  .setSigningKey(login.getSifra().getBytes())
			  .parseClaimsJws(jwt);
			Object exp = claims.getBody();
			System.out.println(exp);*/
			/*jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, login.getSifra().getBytes()).setId(id)
					.setSubject(korisnik.getEmail()).setExpiration(new Date(time + 15000)).compact();*/
			// JsonObject json = Json.createObjectBuilder().add("JWT", jwt).build();
		
			
			return jwt;
		}

		return jwt;
	}

	public Korisnik DTOToEntity(KorisnikDTO korisnik) {
		Korisnik entity = new Korisnik();
		entity.setId(korisnik.getId());
		entity.setSifra(korisnik.getSifra());
		entity.setEmail(korisnik.getEmail());
		return entity;
	}

	public KorisnikDTO entityToDTO(Korisnik korisnik) {
		KorisnikDTO dto = new KorisnikDTO();
		dto.setId(korisnik.getId());
		dto.setSifra(korisnik.getSifra());
		dto.setEmail(korisnik.getEmail());
		return dto;
	}

	/*public static KorisnikDTO getDataFromToken(String token) {

		String authToken = token;
		authToken = authToken.substring("Bearer ".length()).trim();
		Claims body = Jwts.parser().setSigningKey(LoginResource.secret).parseClaimsJws(authToken)
				.getBody();
		KorisnikDTO model = new KorisnikDTO();
		model.setId((Long) body.get("id"));
		model.setEmail((String) body.get("email"));
		model.setSifra((String) body.get("password"));
		return model;

	}*/
	
	
	/*public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("loginAuth");
		String authenticationheader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authenticationheader == null ) {
//			ErrorMessages message = new ErrorMessages("Empty header",401);
			Response response = Response.status(401).entity("Empty header").build();
			throw new WebApplicationException(response);
			
		} 
		
		try {
			 // Validate the token
            validateToken(authenticationheader);
			
		} catch (Exception e) {
//			ErrorMessages message = new ErrorMessages("Token expired",401);
			requestContext.abortWith (Response.status(Response.Status.UNAUTHORIZED).entity("Token expired").build());
		}			
		
	}
	private void validateToken(String token) {
		Jwts.parser().setSigningKey(LoginResource.secret).parseClaimsJws(token);
		
	}*/

}
