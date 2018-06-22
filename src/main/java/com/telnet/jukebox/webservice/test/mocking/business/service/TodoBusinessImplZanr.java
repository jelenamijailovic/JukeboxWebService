/*package com.telnet.jukebox.webservice.test.mocking.business.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.telnet.jukebox.webservice.dto.ZanrDTO;
import com.telnet.jukebox.webservice.model.Zanr;
import com.telnet.jukebox.webservice.test.mocking.data.api.service.Database;

public class TodoBusinessImplZanr {

	private Database ds;
	private Statement stmt;
	private ResultSet resultSet;
	
	
	public TodoBusinessImplZanr(Database todoService) {
		super();
		this.ds = todoService;
	}
	
	public List<Zanr> getZanrovi() throws ClassNotFoundException {
		List<Zanr> zanrovi = new ArrayList<Zanr>();

		try {
			Connection con = ds.conStat();
			stmt = con.createStatement();
			resultSet = stmt.executeQuery("select * from zanrovi");
			while (resultSet.next()) {
				Zanr zanr = new Zanr();
				zanr.setId(resultSet.getInt(1));
				zanr.setNaziv(resultSet.getString(2));
				zanrovi.add(zanr);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return zanrovi;
	}
	
	

	
	
	public ZanrDTO getZanr(int id){
		List<ZanrDTO> sviZanrovi= todoService.sviZanrovi();
		ZanrDTO izabraniZanr= new ZanrDTO();
		
		for(ZanrDTO zanr : sviZanrovi) {
			if(zanr.getId()==id) {
				izabraniZanr= zanr;
			}
		}
		return izabraniZanr;
	}
	
	public List<ZanrDTO> getZanrovi(){
		List<ZanrDTO> sviZanrovi= todoService.sviZanrovi();
		return sviZanrovi;
	}
	
	public ZanrDTO addZanr(ZanrDTO zanr) throws ClassNotFoundException {
		List<ZanrDTO> sviZanrovi= todoService.sviZanrovi();
		ZanrDTO unetZanr= new ZanrDTO(0, null);

		for (int i = 0; i < sviZanrovi.size(); i++) {

			if (zanr.getId() != sviZanrovi.get(i).getId()) {
				unetZanr= zanr;
			}

		}
		return unetZanr;
	}
	
	public ZanrDTO updateZanr(ZanrDTO zanr) throws ClassNotFoundException {
		List<ZanrDTO> sviZanrovi= todoService.sviZanrovi();
		ZanrDTO izmenjenZanr= new ZanrDTO(0, null);

		for (int i = 0; i < sviZanrovi.size(); i++) {

			if (zanr.getId() == sviZanrovi.get(i).getId()) {
				sviZanrovi.set(i, zanr);
				izmenjenZanr= sviZanrovi.get(i);
				return izmenjenZanr;
			}

		}
		return izmenjenZanr;
	}
	
	public int deleteZanr(int id) throws ClassNotFoundException {
		List<ZanrDTO> sviZanrovi= todoService.sviZanrovi();
		int br=0;
		for(int i = 0; i < sviZanrovi.size(); i++) {
			if(id==sviZanrovi.get(i).getId()) {
				br=sviZanrovi.size()-1;
				return br;
			}else {
				br=sviZanrovi.size();
			}
		}
		
		return br;
	}
	
	public ZanrDTO entityToDTO(Zanr zanr) {
		ZanrDTO dto = new ZanrDTO();
		dto.setId(zanr.getId());
		dto.setNaziv(zanr.getNaziv());
		return dto;
	}
}
*/