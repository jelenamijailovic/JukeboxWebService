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

import com.telnet.jukebox.webservice.model.Izvodjac;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.service.IzvodjacService;
import com.telnet.jukebox.webservice.service.PesmaService;

@Path("/izvodjaci")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class IzvodjacResource {

	IzvodjacService izvodjacService = new IzvodjacService();
	PesmaService pesmaService = new PesmaService();

	@GET
	public Response getIzvodjaci() throws SQLException, ClassNotFoundException {
//		System.out.println(izvodjacService.getIzvodjaci().get(1).getIme());
//		return izvodjacService.getIzvodjaci();
		List<Izvodjac> izvodjaci = izvodjacService.getIzvodjaci();
        GenericEntity<List<Izvodjac>> list = new GenericEntity<List<Izvodjac>>(izvodjaci) {
        };
        return Response.ok(list) 
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
	}

	@GET
	@Path("/{izvodjacId}")
	public Response getIzvodjac(@PathParam("izvodjacId") Long izvodjacId) throws SQLException, ClassNotFoundException {
//		return izvodjacService.getIzvodjac(izvodjacId);
		return Response.ok() 
				.entity(izvodjacService.getIzvodjac(izvodjacId)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET")
				.allow("OPTIONS").build();
	}

	@POST
	public Izvodjac addIzvodjaca(Izvodjac izvodjac) throws SQLException, ClassNotFoundException {
		return izvodjacService.addIzvodjac(izvodjac);
	}

	@PUT
	@Path("/{izvodjacId}")
	public Izvodjac updateIzvodjac(@PathParam("izvodjacId") Long izvodjacId, Izvodjac izvodjac)
			throws SQLException, ClassNotFoundException {
		izvodjac.setId(izvodjacId);
		return izvodjacService.updateIzvodjac(izvodjac);
	}

	@DELETE
	@Path("/{izvodjacId}")
	public void deleteIzvodjac(@PathParam("izvodjacId") Long izvodjacId) throws SQLException, ClassNotFoundException {
		izvodjacService.removeIzvodjac(izvodjacId);
	}

	@GET
	@Path("/{izvodjacId}/pesme")
	public Response getSvePesmePoIzvodjacu(@PathParam("izvodjacId") Long izvodjacId)
			throws ClassNotFoundException, SQLException {
//		return pesmaService.getSvePesmePoIzvodjacu(izvodjacId);
		List<Pesma> pesme = pesmaService.getSvePesmePoIzvodjacu(izvodjacId);
        GenericEntity<List<Pesma>> list = new GenericEntity<List<Pesma>>(pesme) {
        };
        return Response.ok(list) 
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
	}

}
