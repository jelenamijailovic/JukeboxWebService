package com.telnet.jukebox.webservice.dto;

public class LoginDTO {

	private String email;
	private String sifra;

	public LoginDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {

		this.sifra = sifra;
	}
}
