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

import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Zanr;
import com.telnet.jukebox.webservice.service.PesmaService;
import com.telnet.jukebox.webservice.service.ZanrService;

@Path("/zanrovi")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ZanrResource {

	ZanrService zanrService = new ZanrService();
	PesmaService pesmaService = new PesmaService();

	@GET
	public List<Zanr> getZanrovi() throws ClassNotFoundException, SQLException {
		return zanrService.getZanrovi();

	}

	@POST
	public Zanr addZanr(Zanr zanr) throws ClassNotFoundException, SQLException {
		return zanrService.addZanr(zanr);
	}

	@PUT
	@Path("/{zanrId}")
	public Zanr updateZanr(@PathParam("zanrId") Long zanrId, Zanr zanr) throws ClassNotFoundException, SQLException {
		zanr.setId(zanrId);
		return zanrService.updateZanr(zanr);
	}

	@DELETE
	@Path("/{zanrId}")
	public void deleteZanr(@PathParam("zanrId") Long zanrId) throws ClassNotFoundException, SQLException {
		zanrService.removeZanr(zanrId);
	}

	@GET
	@Path("/{zanrId}")
	public Zanr getZanr(@PathParam("zanrId") Long zanrId) throws ClassNotFoundException, SQLException {
		return zanrService.getZanr(zanrId);
	}

	// @Path("/{zanrId}/pesme")
	// public PesmaResource getPesmeResource() {
	// return new PesmaResource();
	// }

	@GET
	@Path("/{zanrId}/pesme")
	public List<Pesma> getSvePesmePoZanru(@PathParam("zanrId") Long zanrId)
			throws ClassNotFoundException, SQLException {
		return pesmaService.getSvePesmePoZanru(zanrId);
	}

}
