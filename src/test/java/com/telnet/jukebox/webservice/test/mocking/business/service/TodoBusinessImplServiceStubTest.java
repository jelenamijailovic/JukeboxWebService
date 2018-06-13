package com.telnet.jukebox.webservice.test.mocking.business.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.telnet.jukebox.webservice.dto.ZanrDTO;
import com.telnet.jukebox.webservice.test.mocking.data.api.TodoServiceStubZanr;
import com.telnet.jukebox.webservice.test.mocking.data.api.service.TodoServiceZanr;

public class TodoBusinessImplServiceStubTest {

	TodoServiceZanr todoServiceStub= new TodoServiceStubZanr();
	TodoBusinessImplZanr todoBusinessImpl= new TodoBusinessImplZanr(todoServiceStub);
	
	@Test
	public void getZanroviTest() {
		List<ZanrDTO> zanr= todoBusinessImpl.getZanrovi();
		assertEquals(3, zanr.size());
	}
	
	@Test
	public void getZanrTest() {
		ZanrDTO zanr= todoBusinessImpl.getZanr(22);
		assertNotEquals(0, zanr.getId());
	}
	
	@Test
	public void addZanrTest() throws ClassNotFoundException {
		ZanrDTO zanr= todoBusinessImpl.addZanr(new ZanrDTO(4, "RnB"));
		assertNotEquals(0, zanr.getId());
	}
	
	@Test
	public void updateZanrTest() throws ClassNotFoundException {
		ZanrDTO zanr= todoBusinessImpl.updateZanr(new ZanrDTO(3, "Folk"));
		assertNotEquals(0, zanr.getId());
	}
	
	@Test
	public void deleteZanrTest() throws ClassNotFoundException {
		assertEquals(2, todoBusinessImpl.deleteZanr(3));
	}

}
