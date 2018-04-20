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
import javax.ws.rs.core.MediaType;

import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.service.PesmaService;



@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})

public class PesmaResource {

	public PesmaService pesmaService= new PesmaService();
	
	@GET
//	@Path("/{izvodjacId}")
//	@PathParam("izvodjacId") 
	public List<Pesma> getSvePesmePoIzvodjacu(Long izvodjacId) {
		return pesmaService.getSvePesmePoIzvodjacu(izvodjacId);
	}
	
//	@GET
//	@Path("/{zanrId}")
//	@PathParam("zanrId") 
//	public List<Pesma> getSvePesmePoZanru(Long zanrId) {
//		return pesmaService.getSvePesmePoZanru(zanrId);
//	}
	
	@POST
	@Path("/{izvodjacId}/{zanrId}")
	public Pesma addPesmaPoIzvodjacu(@PathParam("izvodjacId") Long izvodjacId, @PathParam("zanrId") Long zanrId, Pesma pesma) {
		return pesmaService.addPesmaPoIzvodjacu(izvodjacId, pesma);
	}
	
	@POST
//	@Path("/{zanrId}")
//	@PathParam("zanrId") 
	public Pesma addPesmaPoZanru(Long zanrId, Pesma pesma) {
		return pesmaService.addPesmaPoZanru(zanrId, pesma);
	}
	
	@PUT
//	{izvodjacId}/
//	@PathParam("izvodjacId") 
	@Path("/{pesmaId}")
	public Pesma updatePesmaPoIzvodjacu(@PathParam("pesmaId") Long pesmaId ,Long izvodjacId, Pesma pesma) {
		pesma.setId(pesmaId);
		return pesmaService.updatePesmaPoIzvodjacu(izvodjacId, pesma);
	}
	
	@PUT
//	/{zanrId}
//	@PathParam("zanrId") 
	@Path("/{pesmaId}")
	public Pesma updatePesmaPoZanru(@PathParam("pesmaId") Long pesmaId ,Long zanrId, Pesma pesma) {
		pesma.setId(pesmaId);
		return pesmaService.updatePesmaPoIzvodjacu(zanrId, pesma);
	}
	
	@DELETE
//	{izvodjacId}/
//	@PathParam("izvodjacId") 
	@Path("/{pesmaId}")
	public void deletePesmaPoIzvodjacu(Long izvodjacId, @PathParam("pesmaId") Long pesmaId) {
		pesmaService.deletePesmaPoIzvodjacu(izvodjacId, pesmaId);
	}
	
	@DELETE
//	{zanrId}/
//	@PathParam("zanrId") 
	@Path("/{pesmaId}")
	public void deletePesmaPoZanru(Long zanrId, @PathParam("pesmaId") Long pesmaId) {
		pesmaService.deletePesmaPoZanru(zanrId, pesmaId);
	}
	
	@GET
//	{izvodjacId}/
//	@PathParam("izvodjacId") 
	@Path("/{pesmaId}")
	public Pesma getPesmaPoIzvodjacu(Long izvodjacId ,@PathParam("pesmaId") Long pesmaId) {
		return pesmaService.getPesmaPoIzvodjacu(izvodjacId, pesmaId);
	}
	
	@GET
//	{zanrId}/
//	@PathParam("zanrId") 
	@Path("/{pesmaId}")
	public Pesma getPesmaPoZanru(Long zanrId ,@PathParam("pesmaId") Long pesmaId) {
		return pesmaService.getPesmaPoZanru(zanrId, pesmaId);
	}
	
}
