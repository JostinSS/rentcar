package com.proyectofinal.entidades;

public class Accesorio {
	private int idAccesorio;
	private String nombre;
	private String serial;
	private String descripcion;
	private int precio;

	public Accesorio(int idAccesorio, String nombre, String serial,
			String descripcion, int precio) {
		super();
		this.idAccesorio = idAccesorio;
		this.nombre = nombre;
		this.serial = serial;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public Accesorio(String nombre, String serial,
			String descripcion, int precio) {
		super();
		
		this.nombre = nombre;
		this.serial = serial;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public int getIdAccesorio() {
		return idAccesorio;
	}

	public void setIdAccesorio(int idAccesorio) {
		this.idAccesorio = idAccesorio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

}
