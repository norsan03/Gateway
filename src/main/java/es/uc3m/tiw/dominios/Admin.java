package es.uc3m.tiw.dominios;

import java.io.Serializable;

public class Admin implements Serializable{
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	
	private static final long serialVersionUID = 1L;
	
	
	private long id;
	private String email;
	private String password;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
