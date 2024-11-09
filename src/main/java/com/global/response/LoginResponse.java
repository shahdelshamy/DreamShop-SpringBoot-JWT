package com.global.response;

public class LoginResponse {

	private int id;
	private String token;
	
	
	
	public LoginResponse(int id, String token) {
		super();
		this.id = id;
		this.token = token;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
