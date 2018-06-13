/*
 * package com.telnet.jukebox.webservice.swagger;
 * 
 * import java.util.HashSet; import java.util.Set;
 * 
 * import javax.ws.rs.core.Application;
 * 
 * import org.glassfish.jersey.server.ResourceConfig; import
 * org.springframework.context.annotation.ComponentScan; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import com.telnet.jukebox.webservice.MyResource; import
 * com.telnet.jukebox.webservice.resources.CenaResource; import
 * com.telnet.jukebox.webservice.resources.IzvodjacResource; import
 * com.telnet.jukebox.webservice.resources.KorisnikResource; import
 * com.telnet.jukebox.webservice.resources.PesmaResource; import
 * com.telnet.jukebox.webservice.resources.PrometResource; import
 * com.telnet.jukebox.webservice.resources.ZanrResource;
 * 
 * import io.swagger.jaxrs.config.BeanConfig; import
 * io.swagger.jaxrs.listing.ApiListingResource; import
 * io.swagger.jaxrs.listing.SwaggerSerializers;
 * 
 * @Configuration
 * 
 * @ComponentScan
 * 
 * public class SampleApplication extends Application{
 * 
 * @Override public Set<Class<?>> getClasses() { Set<Class<?>> resources = new
 * HashSet<Class<?>>();
 * 
 * resources.add(ZanrResource.class); resources.add(IzvodjacResource.class);
 * resources.add(KorisnikResource.class); resources.add(PesmaResource.class);
 * resources.add(PrometResource.class); resources.add(CenaResource.class);
 * 
 * resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
 * resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
 * 
 * return resources; }
 * 
 * 
 * @Configuration static class JerseyConfig extends ResourceConfig { public
 * JerseyConfig() { register(MyResource.class); register(CenaResource.class);
 * register(IzvodjacResource.class); register(KorisnikResource.class);
 * register(PesmaResource.class); register(PrometResource.class);
 * register(ZanrResource.class); configureSwagger(); }
 * 
 * private void configureSwagger() { register(ApiListingResource.class);
 * register(SwaggerSerializers.class); BeanConfig beanConfig = new BeanConfig();
 * // beanConfig.setVersion(1.0); beanConfig.setSchemes(new String[]{"http"});
 * beanConfig.setHost("localhost:8080");
 * beanConfig.setBasePath("/JukeboxWebService/webapi");
 * beanConfig.setResourcePackage("com.telnet.jukebox.webservice.resources");
 * beanConfig.setPrettyPrint(true); beanConfig.setScan(true); } }
 * 
 * public SampleApplication() { BeanConfig beanConfig = new BeanConfig();
 * beanConfig.setVersion("1.0.2"); beanConfig.setSchemes(new String[]{"http"});
 * beanConfig.setHost("localhost:8002"); beanConfig.setBasePath("/api");
 * beanConfig.setResourcePackage("io.swagger.resources");
 * beanConfig.setScan(true); }
 * 
 * @Override public Set<Class<?>> getClasses() { Set<Class<?>> resources = new
 * HashSet<Class<?>>();
 * 
 * resources.add(ZanrResource.class); resources.add(IzvodjacResource.class);
 * resources.add(KorisnikResource.class); resources.add(PesmaResource.class);
 * resources.add(PrometResource.class); resources.add(CenaResource.class);
 * 
 * 
 * return resources; } }
 */