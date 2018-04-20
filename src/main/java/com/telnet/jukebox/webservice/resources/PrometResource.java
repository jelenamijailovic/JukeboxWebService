package com.telnet.jukebox.webservice.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.telnet.jukebox.webservice.model.Promet;
import com.telnet.jukebox.webservice.service.PrometService;

public class PrometResource {


	public PrometService prometService= new PrometService();
	
	@GET
	@Path("/{pesmaId}")
	public List<Promet> getSviPrometiPoPesmi(@PathParam("pesmaId") Long pesmaId) {
		return prometService.getSviPrometiPoPesmi(pesmaId);
	}
	
	@GET
	@Path("/{cenaId}")
	public List<Promet> getSviPrometiPoCeni(@PathParam("cenaId") Long cenaId) {
		return prometService.getSviPrometiPoCeni(cenaId);
	}
	
	@POST
	@Path("/{pesmaId}")
	public Promet addPrometZaPesmu(@PathParam("pesmaId") Long pesmaId, Promet promet) {
		return prometService.addPrometZaPesmu(pesmaId, promet);
	}
	
	@POST
	@Path("/{cenaId}")
	public Promet addPrometZaCenu(@PathParam("cenaId") Long cenaId, Promet promet) {
		return prometService.addPrometZaCenu(cenaId, promet);
	}
	
	@PUT
	@Path("/{pesmaId}/{prometId}")
	public Promet updatePrometPoPesmi(@PathParam("pesmaId") Long pesmaId ,@PathParam("prometId") Long prometId, Promet promet) {
		promet.setId(prometId);
		return prometService.updatePrometPoPesmi(pesmaId, promet);
	}
	
	@PUT
	@Path("/{cenaId}/{prometId}")
	public Promet updatePrometPoCeni(@PathParam("prometId") Long prometId ,@PathParam("cenaId") Long cenaId, Promet promet) {
		promet.setId(prometId);
		return prometService.updatePrometPoCeni(cenaId, promet);
	}
	
	@DELETE
	@Path("/{pesmaId}/{prometId}")
	public void deletePrometPoPesmi(@PathParam("pesmaId") Long pesmaId, @PathParam("prometId") Long prometId) {
		prometService.deletePrometPoPesmi(pesmaId, prometId);
	}
	
	@DELETE
	@Path("/{cenaId}/{prometId}")
	public void deletePrometPoCeni(@PathParam("cenaId") Long cenaId, @PathParam("prometId") Long prometId) {
		prometService.deletePrometPoCeni(cenaId, prometId);
	}
	
	@GET
	@Path("/{pesmaId}/{prometId}")
	public Promet getPrometPoPesmi(@PathParam("pesmaId") Long pesmaId ,@PathParam("prometId") Long prometId) {
		return prometService.getPrometPoPesmi(pesmaId, prometId);
	}
	
	@GET
	@Path("/{cenaId}/{prometId}")
	public Promet getPrometPoCeni(@PathParam("cenaId") Long cenaId ,@PathParam("prometId") Long prometId) {
		return prometService.getPrometPoCeni(cenaId, prometId);
	}
	
}
