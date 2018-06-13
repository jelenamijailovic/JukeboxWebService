package ControllerTest;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.telnet.jukebox.webservice.database.ZanrDAO;
import com.telnet.jukebox.webservice.dto.ZanrDTO;
import com.telnet.jukebox.webservice.model.Zanr;
import com.telnet.jukebox.webservice.resources.ZanrResource;
import com.telnet.jukebox.webservice.service.ZanrService;

public class RESTTest {

	/*@Mock
	private ZanrDAO daoMock;
	
	@InjectMocks
	private ZanrService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addZanrTest() throws ClassNotFoundException {
		when(service.insertZanr()).
		when(todoServiceMock.sviZanrovi()).thenReturn(sviPostojeciZanrovi);
		ZanrDTO z= service.addZanr(new ZanrDTO(4, "RnB"));
		ZanrDTO a= todoBusinessImpl.addZanr(new ZanrDTO(4, "RnB"));
		assertEquals(z, a);
		ZanrDTO zanr= todoBusinessImpl.addZanr(new ZanrDTO(4, "RnB"));
		assertNotEquals(0, zanr.getId());
	}
*/
}
