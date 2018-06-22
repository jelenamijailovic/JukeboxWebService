/*package com.telnet.jukebox.webservice.test.mocking.data.api.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.telnet.jukebox.webservice.dto.ZanrDTO;

*//**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 12.03.15 08:33
 *//*
@RunWith(MockitoJUnitRunner.class)
public class InMemoryRestWithMockitoTest {

    public static interface BackendService {

        ZanrDTO getMyModel(int id, String naziv);
    }

    @Path("zanrovi")
    public static class MyResource {

        private BackendService backendService;

        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Produces(MediaType.APPLICATION_XML)
        public ZanrDTO createMyModel(int id, String naziv) {

            return backendService.getMyModel(id, naziv);
        }

    }

    @InjectMocks
    public static MyResource sut = new MyResource();
    public static InMemoryRestServer server;

    @Mock
    private BackendService backendServiceMock;

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = InMemoryRestServer.create(sut);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.close();
    }

    @Test
    public void postWithoutMocking() throws Exception {

        Response response = server.newRequest("/zanrovi").request().buildPost(Entity.text("42")).invoke();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void postWithMocking() throws Exception {

        when(backendServiceMock.getMyModel(5, "noviZanr")).thenReturn(new ZanrDTO(5, "noviZanr"));

        Response response = server.newRequest("/zanrovi").request().buildPost(Entity.text("5, noviZanr")).invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        ZanrDTO myModel = response.readEntity(ZanrDTO.class);
        assertEquals(5, myModel.getId());
        assertEquals("noviZanr", myModel.getNaziv());
    }
}*/