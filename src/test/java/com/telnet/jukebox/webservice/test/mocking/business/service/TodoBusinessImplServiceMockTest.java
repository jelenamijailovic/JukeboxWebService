package com.telnet.jukebox.webservice.test.mocking.business.service;

//import static org.junit.Assert.assertNotEquals;
//import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
//import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static com.jayway.restassured.RestAssured.given;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.StreamingHttpOutputMessage.Body;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
/*
import com.sun.research.ws.wadl.Method;
import com.telnet.jukebox.webservice.database.ZanrDAO;*/
import com.telnet.jukebox.webservice.dto.CenaDTO;
import com.telnet.jukebox.webservice.dto.IzvodjacDTO;
import com.telnet.jukebox.webservice.dto.KorisnikDTO;
import com.telnet.jukebox.webservice.dto.PesmaDTO;
import com.telnet.jukebox.webservice.dto.PrometDTO;
import com.telnet.jukebox.webservice.dto.ZanrDTO;
import com.telnet.jukebox.webservice.model.Cena;
import com.telnet.jukebox.webservice.model.Izvodjac;
import com.telnet.jukebox.webservice.model.Korisnik;
import com.telnet.jukebox.webservice.model.Pesma;
import com.telnet.jukebox.webservice.model.Promet;
import com.telnet.jukebox.webservice.model.Zanr;
import com.telnet.jukebox.webservice.service.CenaService;
import com.telnet.jukebox.webservice.service.IzvodjacService;
import com.telnet.jukebox.webservice.service.KorisnikService;
import com.telnet.jukebox.webservice.service.PesmaService;
import com.telnet.jukebox.webservice.service.PrometService;
import com.telnet.jukebox.webservice.service.ZanrService;
import com.telnet.jukebox.webservice.test.mocking.data.api.service.TodoServiceZanr;

public class TodoBusinessImplServiceMockTest {

	TodoServiceZanr todoServiceMock = mock(TodoServiceZanr.class);
	TodoBusinessImplZanr todoBusinessImpl = new TodoBusinessImplZanr(todoServiceMock);
	List<ZanrDTO> sviPostojeciZanrovi = Arrays.asList(new ZanrDTO(22, "Punk"), new ZanrDTO(2, "Rock"),
			new ZanrDTO(3, "Narodna"));
	// List<Zanr> sviPostojeciZanrovi= Arrays.asList(new ZanrDTO(22, "Punk"), new
	// ZanrDTO(2, "Rock"), new ZanrDTO(3, "Narodna"));
	// List<ZanrDTO> noviZanrovi= Arrays.asList(new ZanrDTO(1, "RnB"), new
	// ZanrDTO(4, "Folk"), new ZanrDTO(3, "Rap"));

	java.util.Date datum = new java.util.Date();
	java.sql.Date date = new java.sql.Date(datum.getTime());

	ZanrDTO zanrDTO = new ZanrDTO(5, "RnB");
	Zanr zanrEntity = new Zanr(5, "RnB");
	CenaDTO cenaDTO = new CenaDTO(10, 130);
	Cena cenaEntity = new Cena(10, 130);
	IzvodjacDTO izvodjacDTO = new IzvodjacDTO(15, "Ritam nereda");
	Izvodjac izvodjacEntity = new Izvodjac(15, "Ritam nereda");
	PesmaDTO pesmaDTO = new PesmaDTO(30, "Put beznadja", "Ritam nereda", "Punk", 130);
	Pesma pesmaEntity = new Pesma(30, "Put beznadja", "Ritam nereda", "Punk", 130);
	PrometDTO prometDTO = new PrometDTO(156, date, "Put beznadja", 130, 20, "Ritam nereda", "korisnik@gmail.com");
	Promet prometEntity = new Promet(156, date, "Put beznadja", 130, 20, "Ritam nereda", "korisnik@gmail.com");
	KorisnikDTO korisnikDTO = new KorisnikDTO(12, "sifra", "email");
	Korisnik korisnikEntity = new Korisnik(12, "sifra", "email");

	ZanrService serviceZanr = new ZanrService();
	CenaService serviceCena = new CenaService();
	IzvodjacService serviceIzvodjac = new IzvodjacService();
	PesmaService servicePesma = new PesmaService();
	PrometService servicePromet = new PrometService();
	KorisnikService serviceKorisnik = new KorisnikService();

	@BeforeClass
	public static void setup() throws Exception {
		String port = "8081";
		/*if (port == null) {
			RestAssured.port = Integer.valueOf(8080);
		} else {*/
			RestAssured.port = Integer.valueOf(port);
		//}

		String basePath = "/JukeboxWebService/webapi/";
		/*if (basePath == null) {
			basePath = "/JukeboxWebService/webapi/";
		}*/
		RestAssured.basePath = basePath;

		String baseHost = "http://localhost";
		/*if (baseHost == null) {
			baseHost = "http://localhost";
		}*/
		RestAssured.baseURI = baseHost;

	}

	@Test
	public void get() throws Exception {
		
		given().when().get("/zanrovi").then().statusCode(200);
		/*given().when().get("/zanrovi/99").then().statusCode(204);
		given().when().get("/zanrovi/2").then().body("naziv",equalTo("Rock")).body("id", equalTo(2)).statusCode(200);
	}
	
	@Test
	public void post() {
		Map<String,Object> zanr = new HashMap<>();
		zanr.put("id", 4);
		zanr.put("naziv", "RnB");

        given()
        .contentType("application/json")
        .body(zanr)
        .when().post("/zanrovi").then()
        .statusCode(200);*/
	}

	// private static RequestSpecification spec;

	/*
	 * @BeforeClass public static void initSpec() { spec = new
	 * RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(
	 * "http://localhost:8080/") .addFilter(new ResponseLoggingFilter())// log
	 * request and response for better debugging. You can also // only log if a
	 * requests fails. .addFilter(new RequestLoggingFilter()).build(); }
	 * 
	 * @Test public void useSpec() { given().spec(spec).param("limit",
	 * 20).when().get("zanrovi").then().statusCode(200); }
	 */

	/*
	 * @Test public void createBlogAndCheckExistence() { ZanrDTO newZanr =
	 * createDummyZanr(); String blogResourceLocation = addZanr("zanrovi", newZanr);
	 * ZanrDTO retrievedBlog = getZanr(blogResourceLocation, ZanrDTO.class);
	 * assertEqualBlog(newZanr, retrievedBlog); }
	 * 
	 * private ZanrDTO createDummyZanr() { ZanrDTO zanr = new ZanrDTO();
	 * zanr.setId(8); zanr.setNaziv("Reggae"); return zanr; }
	 * 
	 * private String addZanr(String path, Object bodyPayload) { return given()
	 * .spec(spec) .body(bodyPayload) .when() .post(path) .then() .statusCode(201)
	 * .extract().header("location"); }
	 * 
	 * private <T> T getZanr(String locationHeader, Class<T> responseClass) { return
	 * given() .spec(spec) .when() .get(locationHeader) .then() .statusCode(200)
	 * .extract().as(responseClass); }
	 * 
	 * private void assertEqualBlog(ZanrDTO noviZanr, ZanrDTO povucenZanr) {
	 * assertEquals(povucenZanr.getId(), noviZanr.getId());
	 * assertEquals(povucenZanr.getNaziv(), noviZanr.getNaziv()); }
	 */
	/*
	 * @Test public void getZanroviTestMock() {
	 * when(todoServiceMock.sviZanrovi()).thenReturn(sviPostojeciZanrovi);
	 * List<ZanrDTO> zanr= todoBusinessImpl.getZanrovi(); assertEquals(3,
	 * zanr.size()); }
	 * 
	 * @Test public void getZanrTest() throws ClassNotFoundException {
	 * when(todoServiceMock.sviZanrovi()).thenReturn(sviPostojeciZanrovi); int x =
	 * anyInt(); ZanrDTO z= service.getZanr(22); ZanrDTO a=
	 * todoBusinessImpl.getZanr(22); assertEquals(z, a); }
	 * 
	 * @Test public void addZanrTest() throws ClassNotFoundException {
	 * when(todoServiceMock.sviZanrovi()).thenReturn(sviPostojeciZanrovi); ZanrDTO
	 * z= service.addZanr(new ZanrDTO(4, "RnB")); ZanrDTO a=
	 * todoBusinessImpl.addZanr(new ZanrDTO(4, "RnB")); assertEquals(z, a); ZanrDTO
	 * zanr= todoBusinessImpl.addZanr(new ZanrDTO(4, "RnB")); assertNotEquals(0,
	 * zanr.getId()); }
	 * 
	 * @Test public void updateZanrTest() throws ClassNotFoundException {
	 * when(todoServiceMock.sviZanrovi()).thenReturn(sviPostojeciZanrovi); ZanrDTO
	 * zanr= todoBusinessImpl.updateZanr(new ZanrDTO(3, "RnB")); assertNotEquals(0,
	 * zanr.getId()); }
	 * 
	 * @Test public void deleteZanrTest() throws ClassNotFoundException {
	 * when(todoServiceMock.sviZanrovi()).thenReturn(sviPostojeciZanrovi); // int i=
	 * anyInt(); assertEquals(2, todoBusinessImpl.deleteZanr(22)); }
	 */

	@Test
	public void toDTO() {
		assertEquals(zanrDTO, serviceZanr.entityToDTO(zanrEntity));
		assertEquals(cenaDTO, serviceCena.entityToDTO(cenaEntity));
		assertEquals(izvodjacDTO, serviceIzvodjac.entityToDTO(izvodjacEntity));
		assertEquals(pesmaDTO, servicePesma.entityToDTO(pesmaEntity));
		assertEquals(prometDTO, servicePromet.entityToDTO(prometEntity));
		assertEquals(korisnikDTO, serviceKorisnik.entityToDTO(korisnikEntity));
	}

	@Test
	public void toEntity() {
		assertEquals(zanrEntity, serviceZanr.DTOToEntity(zanrDTO));
		assertEquals(cenaEntity, serviceCena.DTOToEntity(cenaDTO));
		assertEquals(izvodjacEntity, serviceIzvodjac.DTOToEntity(izvodjacDTO));
		assertEquals(pesmaEntity, servicePesma.DTOToEntity(pesmaDTO));
		assertEquals(prometEntity, servicePromet.DTOToEntity(prometDTO));
		assertEquals(korisnikEntity, serviceKorisnik.DTOToEntity(korisnikDTO));
	}

	/*
	 * @Test public void GetAll
	 */

}
