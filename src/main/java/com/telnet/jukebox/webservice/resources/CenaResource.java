package com.telnet.jukebox.webservice.resources;

import java.sql.SQLException;
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

import com.telnet.jukebox.webservice.model.Cena;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.service.CenaService;
import com.telnet.jukebox.webservice.service.PesmaService;

@Path("/cene")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class CenaResource {

	CenaService cenaService = new CenaService();
	PesmaService pesmaService= new PesmaService();

	@GET
	public Response getCene() throws ClassNotFoundException, SQLException {
//		return cenaService.getCene();
		List<Cena> cene = cenaService.getCene();
        GenericEntity<List<Cena>> list = new GenericEntity<List<Cena>>(cene) {
        };
        return Response.ok(list) 
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
	}

	@POST
	public Cena addCena(Cena cena) throws ClassNotFoundException, SQLException {
		return cenaService.addCena(cena);
	}

	@PUT
	@Path("/{cenaId}")
	public Cena updateIzvodjac(@PathParam("cenaId") Long cenaId, Cena cena)
			throws ClassNotFoundException, SQLException {
		cena.setId(cenaId);
		return cenaService.updateCena(cena);
	}

	@DELETE
	@Path("/{cenaId}")
	public void deleteCena(@PathParam("cenaId") Long cenaId) throws ClassNotFoundException, SQLException {
		cenaService.removeCena(cenaId);
	}

	@GET
	@Path("/{cenaId}")
	public Response getCena(@PathParam("cenaId") Long cenaId) throws ClassNotFoundException, SQLException {
//		return cenaService.getCena(cenaId);
		return Response.ok() 
				.entity(cenaService.getCena(cenaId)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET")
				.allow("OPTIONS").build();
	}
	
	@GET
	@Path("/{cenaId}/pesme")
	public Response getSvePesmePoCeni(@PathParam("cenaId") Long cenaId) throws ClassNotFoundException, SQLException {
//		return pesmaService.getSvePesmePoCeni(cenaId);
		List<Pesma> pesme = pesmaService.getSvePesmePoCeni(cenaId);
        GenericEntity<List<Pesma>> list = new GenericEntity<List<Pesma>>(pesme) {
        };
        return Response.ok(list) 
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
	}

}
