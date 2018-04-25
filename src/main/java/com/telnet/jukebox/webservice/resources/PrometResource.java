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

import com.telnet.jukebox.webservice.model.Promet;
import com.telnet.jukebox.webservice.service.PrometService;

@Path("/prometi")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PrometResource {

	public PrometService prometService = new PrometService();

	@GET
	public Response getSviPrometiPoCeni() throws ClassNotFoundException, SQLException {
//		return prometService.getSviPrometi();
		List<Promet> prometi = prometService.getSviPrometi();
        GenericEntity<List<Promet>> list = new GenericEntity<List<Promet>>(prometi) {
        };
        return Response.ok(list) 
        		.header("Access-Control-Allow-Origin", "*")
        		.header("Access-Control-Allow-Methods", "GET").allow("OPTIONS").build();
	}

	@GET
	@Path("/{prometId}")
	public Response getPromet(@PathParam("prometId") Long prometId) throws ClassNotFoundException, SQLException {
//		return prometService.getPromet(prometId);
		return Response.ok() 
				.entity(prometService.getPromet(prometId)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET")
				.allow("OPTIONS").build();
	}

	@POST
	@Path("/{pesmaId}")
	public Promet addPromet(@PathParam("pesmaId") Long pesmaId, Promet promet)
			throws ClassNotFoundException, SQLException {
		return prometService.addPromet(pesmaId, promet);
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
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deletePromet(@PathParam("prometId") Long prometId) throws ClassNotFoundException, SQLException {
		prometService.deletePromet(prometId);
	
	
//	Podcast podcastById = podcastService.getPodcastById(id);
//	return Response.ok() //200
//			.entity(null)
//			.header("Access-Control-Allow-Origin", "*")
//			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
//			.allow("OPTIONS").build();
}

}
