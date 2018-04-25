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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Promet;
import com.telnet.jukebox.webservice.service.PesmaService;
import com.telnet.jukebox.webservice.service.PrometService;

import javax.ws.rs.core.GenericEntity;

@Path("/pesme")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PesmaResource {

	PesmaService pesmaService = new PesmaService();
	PrometService prometService= new PrometService();
	
	@GET
	public Response getSvePesme() throws ClassNotFoundException, SQLException {
//		return pesmaService.getSvePesme();
		  List<Pesma> pesme = pesmaService.getSvePesme();
			        GenericEntity<List<Pesma>> list = new GenericEntity<List<Pesma>>(pesme) {
			        };
		return Response.ok(list) 
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
				
	}

	@POST
	@Path("/{zanrId}/{izvodjacId}/{cenaId}")
	public Pesma addPesma(@PathParam("izvodjacId") Long izvodjacId, @PathParam("zanrId") Long zanrId, @PathParam("cenaId") Long cenaId, Pesma pesma)
			throws ClassNotFoundException, SQLException {
		return pesmaService.addPesma(izvodjacId, zanrId, cenaId, pesma);
	}

	@PUT
	@Path("/{pesmaId}")
	public Pesma updatePesma(@PathParam("pesmaId") Long pesmaId, Pesma pesma)
			throws ClassNotFoundException, SQLException {
		pesma.setId(pesmaId);
		return pesmaService.updatePesma(pesma);
	}

	@DELETE
	@Path("/{pesmaId}")
	public void deletePesma(@PathParam("pesmaId") Long pesmaId) throws ClassNotFoundException, SQLException {
		pesmaService.deletePesma(pesmaId);
	}

	@GET
	@Path("/{pesmaId}")
	public Response getPesma(@PathParam("pesmaId") Long pesmaId) throws ClassNotFoundException, SQLException {
//		return pesmaService.getPesma(pesmaId);
		return Response.ok() 
				.entity(pesmaService.getPesma(pesmaId)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET")
				.allow("OPTIONS").build();
	}
	
	@GET
	@Path("/{pesmaId}/prometi")
	public Response getSviPrometiPoCeni(@PathParam("pesmaId") Long pesmaId) throws ClassNotFoundException, SQLException {
//		return prometService.getSviPrometiPoPesmi(pesmaId);
		List<Promet> prometi = prometService.getSviPrometiPoPesmi(pesmaId);
        GenericEntity<List<Promet>> list = new GenericEntity<List<Promet>>(prometi) {
        };
        return Response.ok(list) 
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
	}

}
