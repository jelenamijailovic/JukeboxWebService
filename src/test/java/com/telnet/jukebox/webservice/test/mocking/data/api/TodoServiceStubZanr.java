package com.telnet.jukebox.webservice.test.mocking.data.api;

import java.util.Arrays;
import java.util.List;

import com.telnet.jukebox.webservice.dto.ZanrDTO;
import com.telnet.jukebox.webservice.test.mocking.data.api.service.TodoServiceZanr;

public class TodoServiceStubZanr implements TodoServiceZanr{

	@Override
	public List<ZanrDTO> sviZanrovi() {
		List<ZanrDTO> svi= Arrays.asList(new ZanrDTO(22, "Punk"), new ZanrDTO(2, "Rock"), new ZanrDTO(3, "Narodna"));
		return svi;
	}

}
