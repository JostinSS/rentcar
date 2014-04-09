package com.proyectofinal.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.proyectofinal.bd.Conexion;
import com.proyectofinal.entidades.Cliente;


public class ModeloClientes extends AbstractTableModel {
	
	private static ModeloClientes instancia;
	ArrayList<Cliente> ArrayClientes = new ArrayList<Cliente>();
	String tituloTabla[] = {"Nombre", "Apellido", "Telefono", "Documento","ID"};
	
	public static ModeloClientes getInstacia(){
		if(instancia == null){
			instancia = new ModeloClientes();
		}
		return instancia;
	}
	
	public ModeloClientes() {
		ArrayClientes = Conexion.getInstacia().cargarCliente();
	}

	public int getColumnCount() {

		return tituloTabla.length;
	}

	public String getColumnName(int x) {
		return tituloTabla[x];
	}

	public int getRowCount() {
		return ArrayClientes.size();
	}

	public Object getValueAt(int x, int y) {
		String respuesta = null;
		Cliente cliente = ArrayClientes.get(x);
		
		switch (y) {
		case 0:
			respuesta = cliente.getNombre();
			break;
		case 1:
			respuesta = cliente.getApellido();
			break;
		case 2:
			respuesta = cliente.getTelefono();
			break;
		case 3:
			respuesta = cliente.getDocumento();
			break;
		case 4:
			respuesta = cliente.getIdCliente();
			break;
		}

		return respuesta;
	}
	
	public void agregarCliente(Cliente cliente){
		Conexion.getInstacia().agregarCliente(cliente);
		ArrayClientes = Conexion.getInstacia().cargarCliente();
		fireTableDataChanged();
		
	}
	
	public void eliminarCliente(int fila){
		Conexion.getInstacia().eliminarCliente(ArrayClientes.get(fila));
		ArrayClientes.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}
	
	public void modificarCliente(Cliente cliente, int index){
		Cliente clave = ArrayClientes.get(index);
		Conexion.getInstacia().modificarCliente(cliente.getIdCliente(),cliente);
		ArrayClientes = Conexion.getInstacia().cargarCliente(); 
		fireTableDataChanged();
	}


}
