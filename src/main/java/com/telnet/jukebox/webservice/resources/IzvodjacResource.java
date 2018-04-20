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

import com.telnet.jukebox.webservice.model.Izvodjac;
import com.telnet.jukebox.webservice.service.IzvodjacService;

@Path("/izvodjaci")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})

public class IzvodjacResource {
	
	IzvodjacService izvodjacService= new IzvodjacService();
	
	@GET
	public List<Izvodjac> getIzvodjaci() throws SQLException, ClassNotFoundException {
		System.out.println(izvodjacService.getIzvodjaci().get(1).getIme());
		return izvodjacService.getIzvodjaci();
	}
	
	@GET
	@Path("/{izvodjacId}")
	public Izvodjac getIzvodjac(@PathParam("izvodjacId") Long izvodjacId) throws SQLException, ClassNotFoundException {
		
		return izvodjacService.getIzvodjac(izvodjacId);
	}
	
	@POST
	public Izvodjac addIzvodjaca(Izvodjac izvodjac) throws SQLException, ClassNotFoundException {
		return izvodjacService.addIzvodjac(izvodjac);
	}
	
	@PUT
	@Path("/{izvodjacId}")
	public Izvodjac updateIzvodjac(@PathParam("izvodjacId") Long izvodjacId, Izvodjac izvodjac) throws SQLException, ClassNotFoundException {
		izvodjac.setId(izvodjacId);
		return izvodjacService.updateIzvodjac(izvodjac);
	}
	
	@DELETE
	@Path("/{izvodjacId}")
	public void deleteIzvodjac(@PathParam("izvodjacId") Long izvodjacId) throws SQLException, ClassNotFoundException {
		izvodjacService.removeIzvodjac(izvodjacId);
	}
	
	@Path("/{izvodjacId}/pesme")
	public PesmaResource getPesmeResource() {
		return new PesmaResource();
	}

}
