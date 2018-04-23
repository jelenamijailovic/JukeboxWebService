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

import com.telnet.jukebox.webservice.model.Promet;
import com.telnet.jukebox.webservice.service.PrometService;

@Path("/prometi")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PrometResource {

	public PrometService prometService = new PrometService();

	@GET
	public List<Promet> getSviPrometiPoCeni() throws ClassNotFoundException, SQLException {
		return prometService.getSviPrometi();
	}

	@GET
	@Path("/{prometId}")
	public Promet addPromet(@PathParam("prometId") Long prometId) throws ClassNotFoundException, SQLException {
		return prometService.getPromet(prometId);
	}

	@POST
	@Path("/{pesmaId}/{cenaId}")
	public Promet addPromet(@PathParam("pesmaId") Long pesmaId, @PathParam("cenaId") Long cenaId, Promet promet)
			throws ClassNotFoundException, SQLException {
		return prometService.addPromet(pesmaId, cenaId, promet);
	}

	@PUT
	@Path("/{prometId}")
	public Promet updatePrometPoPesmi(@PathParam("prometId") Long prometId, Promet promet)
			throws ClassNotFoundException, SQLException {
		promet.setId(prometId);
		return prometService.updatePromet(promet);
	}

	@DELETE
	@Path("/{prometId}")
	public void deletePromet(@PathParam("prometId") Long prometId) throws ClassNotFoundException, SQLException {
		prometService.deletePromet(prometId);
	}

}
