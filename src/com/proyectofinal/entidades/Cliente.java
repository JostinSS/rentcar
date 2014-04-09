package com.proyectofinal.entidades;


public class Cliente {

	private String nombre;
	private String apellido;
	private String telefono;
	private String documento;
	private String idCliente;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public Cliente(String nombre, String apellido, String telefono,
			String documento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.documento = documento;
		
	}
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	public Cliente(String nombre, String apellido, String telefono,
			String documento, String idCliente) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.documento = documento;
		this.idCliente = idCliente;
		
	}
	
	
	

}
