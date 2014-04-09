package com.proyectofinal.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.proyectofinal.bd.Conexion;
import com.proyectofinal.entidades.Accesorio;

public class ModeloAccesorios extends AbstractTableModel {
	
	private static ModeloAccesorios instancia;
	
	String[] encabezados = {"ID","Nombre", "Serial", "Descripcion", "Precio"};
	ArrayList<Accesorio> arrayAccesorio = new ArrayList<Accesorio>();
	
	public static ModeloAccesorios getInstacia(){
		if(instancia == null){
			instancia = new ModeloAccesorios();
		}
		return instancia;
	}
	
	public ModeloAccesorios(){
		arrayAccesorio = Conexion.getInstacia().cargarAcccesorios();
	}
	
	@Override
	public int getColumnCount() {
		return encabezados.length;
	}

	@Override
	public String getColumnName(int x) {
		// TODO Auto-generated method stub
		return encabezados[x];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return arrayAccesorio.size();
	}

	@Override
	public Object getValueAt(int x, int y) {
		String retorno = null;
		Accesorio accesorio = arrayAccesorio.get(x);
		
		switch (y) {
		case 0:
			retorno = String.valueOf(accesorio.getIdAccesorio());
			break;
		case 1:
			retorno = accesorio.getNombre();
			break;
		case 2:
			retorno = accesorio.getSerial();
			
			break;
		case 3:
			retorno = accesorio.getDescripcion();
			break;
		case 4: 
			retorno = String.valueOf(accesorio.getPrecio());
		}
		
		return retorno;
	}
	
	public void agregarAccesorio(Accesorio accesorio){
		Conexion.getInstacia().agregarAccesorio(accesorio);
		arrayAccesorio = Conexion.getInstacia().cargarAcccesorios();
		fireTableDataChanged();
	}
	
	public void eliminarAccesorio(int fila){
		Conexion.getInstacia().eliminarAccesorio(arrayAccesorio.get(fila));
		arrayAccesorio.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}
	
	public void modificarAccesorio(Accesorio accesorio, int index){
		
		Accesorio clave = arrayAccesorio.get(index);
		Conexion.getInstacia().modificarAccesorio(clave.getIdAccesorio(),accesorio);
		arrayAccesorio = Conexion.getInstacia().cargarAcccesorios(); 
		fireTableDataChanged();
	}

}
