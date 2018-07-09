package com.telnet.jukebox.webservice.test.mocking.business.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TEST {

	@Test
    public void shouldCheckURIs() throws IOException {

		 
		        URI uri = UriBuilder.fromUri("http://localhost/").port(8282).build();
		 
		        HttpServer server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
		        // Create a handler wrapping the JAX-RS application
		        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new ApplicationConfig(), HttpHandler.class);
		        // Map JAX-RS handler to the server root
		        server.createContext(uri.getPath(), handler);
		        // Start the server
		        server.start();

		        Client client = ClientBuilder.newClient();

		        // Valid URIs
		        Response response = client.target("http://localhost:8282/zanrovi/2").request().get();
		 
		        // Valid URIs
		        assertEquals(200, client.target("http://localhost:8282/zanrovi/2").request().get().getStatus());
		       /* assertEquals(200, client.target("http://localhost:8282/customer/1234").request().get().getStatus());
		        assertEquals(200, client.target("http://localhost:8282/customer?zip=75012").request().get().getStatus());
		        assertEquals(200, client.target("http://localhost:8282/customer/search;firstname=John;surname=Smith").request().get().getStatus());
		 
		        // Invalid URIs
		        assertEquals(404, client.target("http://localhost:8282/customer/AGONCAL").request().get().getStatus());
		        assertEquals(404, client.target("http://localhost:8282/customer/dummy/1234").request().get().getStatus());
		 */
		        // Stop HTTP server
		        server.stop(0);
}
}
