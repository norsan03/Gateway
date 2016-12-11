package es.uc3m.tiw.dominios;

import java.io.Serializable;

public class Cliente implements Serializable{
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(long id, String nombre, String apellidos, String ciudad, String password, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ciudad = ciudad;
		this.password = password;
		this.email = email;
	}
	
	private static final long serialVersionUID = 1L;
	
	
	private long id;
	private String nombre;
	private String apellidos;
	private String ciudad;
	private String email;
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
