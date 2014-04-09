package com.proyectofinal.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.proyectofinal.bd.Conexion;
import com.proyectofinal.entidades.Usuario;
import com.proyectofinal.ui.mantenimiento.MatenimientoUsuarios;


public class ModeloUsuarios extends AbstractTableModel {


	String[] tituloTabla = { "Nombre", "Apellido", "Usuario", "Contraseña", "Cargo" };
	ArrayList<Usuario> ArrayUsuarios;
	
	private static ModeloUsuarios instancia;

	public static ModeloUsuarios getInstacia() {
		if (instancia == null) {
			instancia = new ModeloUsuarios();
		}
		return instancia;
	}

	public ModeloUsuarios() {
		ArrayUsuarios = Conexion.getInstacia().cargarUsuarios();
			
	}
	

	public int getColumnCount() {
		return tituloTabla.length;
	}

	public int getRowCount() {
		return ArrayUsuarios.size();
	}

	public String getColumnName(int x) {

		return tituloTabla[x];
	}
	
	public Object getValueAt(int x, int y) {
		String seleccion = null;
		Usuario usuario = ArrayUsuarios.get(x);

		switch (y) {
		case 0:
			seleccion = usuario.getNombre();
			break;
		case 1:
			seleccion = usuario.getApellido();
			break;
		case 2:
			seleccion = usuario.getUsuario();
			break;
		case 3:
			seleccion = usuario.getPassword();
			break;
		case 4:
			seleccion = usuario.getCargo();
			break;
		}

		return seleccion;
	}
	
	public void AgregarUsuario(Usuario usuario){
		Conexion.getInstacia().agregarUsuario(usuario);
		ArrayUsuarios = Conexion.getInstacia().cargarUsuarios();
		fireTableDataChanged();
		
			}

	public void eliminarUsuario(int fila) {
		Conexion.getInstacia().eliminarUsuario(ArrayUsuarios.get(fila));
		ArrayUsuarios.remove(fila);
		fireTableRowsDeleted(fila, fila);
	
	}
	
	public void modificarUsuario(Usuario usuario,int index){
	Usuario clave = ArrayUsuarios.get(index);
	Conexion.getInstacia().modificarUsuario(clave.getUsuario(),usuario);
	ArrayUsuarios = Conexion.getInstacia().cargarUsuarios(); 
	fireTableDataChanged();
	
	
	}
	
}
