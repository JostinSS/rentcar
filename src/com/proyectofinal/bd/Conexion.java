package com.proyectofinal.bd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.proyectofinal.entidades.Accesorio;
import com.proyectofinal.entidades.Cliente;
import com.proyectofinal.entidades.Usuario;
import com.proyectofinal.ui.VentanaAdministrador;

public class Conexion {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement prst = null;
	ArrayList<Usuario> usuario = new ArrayList<Usuario>();
	ArrayList<Cliente> cliente = new ArrayList<Cliente>();
	ArrayList<Accesorio> accesorio = new ArrayList<Accesorio>();
	
	private static Conexion instancia;
	
	public static Conexion getInstacia(){
		if(instancia == null){
			instancia = new Conexion();
		}
		return instancia;
	}
	
	public Conexion(){
	
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","");
			st = con.createStatement();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//Login/////////////////////////////////////////////////////////////////////////////////////////////////
	public void validarUsuario(String usuario, String password){
		try {
			
			rs = st.executeQuery("SELECT usuario, password, cargo FROM usuario WHERE usuario = '"+ usuario 
					+ "' AND password = '" + password	+ "' AND cargo IN ('Administrador','Vendedor')");

			if (rs.next()) {

				if (rs.getString("Cargo").equals("Administrador")) {
					
					VentanaAdministrador ventanaAdminsitrador = new VentanaAdministrador();
					ventanaAdminsitrador.setLocationRelativeTo(null);
					ventanaAdminsitrador.setVisible(true);
					
				} else if (rs.getString("Cargo").equals("Vendedor")) {
					System.out.println("Bienvenido vendedor");
						
				}
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Usuario y/o contraseña es incorrecto.","Error", JOptionPane.ERROR_MESSAGE);	
			}

		} catch (SQLException e) {

			e.printStackTrace();
			
		}
	}
	
	//FINN Login/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/// INICIO USUARIO /////////////////////////////////////////////////////////////////////////////////////////////////
	public ArrayList<Usuario> cargarUsuarios(){
		usuario = new ArrayList<Usuario>();
		try {
			rs = st.executeQuery("SELECT Nombre, Apellido, Usuario, Password, Cargo FROM Usuario");
			
			while(rs.next()){
				usuario.add(new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
		} catch (SQLException e) {
			System.out.println("Error:"+e.getMessage());
			e.printStackTrace();
		}
		return usuario;
	}
	
	public void agregarUsuario(Usuario usuario){
			try {
				prst = con.prepareStatement("INSERT INTO USUARIO (Nombre, Apellido, Usuario, Password, Cargo) VALUES (?,?,?,?,?)");
				prst.setString(1, usuario.getNombre());
				prst.setString(2, usuario.getApellido());
				prst.setString(3, usuario.getUsuario());
				prst.setString(4, usuario.getPassword());
				prst.setString(5, usuario.getCargo());
				prst.execute();
				
				if(st != null){
					JOptionPane.showMessageDialog(null, "El usuario ha sido agregado correctamente.", "Usuario agregado.", JOptionPane.INFORMATION_MESSAGE);
				}
				
			
			}catch (MySQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible, intente con otro", "Nombre de usuario en uso", JOptionPane.ERROR_MESSAGE);
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	
	public void eliminarUsuario(Usuario usuario){
		try {
			prst = con.prepareStatement("DELETE FROM USUARIO WHERE nombre = ?");
			prst.setString(1, usuario.getNombre());
			prst.execute();
			
			if(st != null){
				JOptionPane.showMessageDialog(null, "Se ha usuario ha sido eliminado exitosamente","Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void modificarUsuario(String clave,Usuario usuario){
		try {
			prst = con.prepareStatement("UPDATE USUARIO SET NOMBRE = ?, apellido =? , usuario= ?, password = ?, cargo = ? WHERE usuario = ?");
			prst.setString(1, usuario.getNombre());
			prst.setString(2, usuario.getApellido());
			prst.setString(3, usuario.getUsuario());
			prst.setString(4, usuario.getPassword());
			prst.setString(5, usuario.getCargo());
			prst.setString(6, clave);
			prst.executeUpdate();
			
			if(prst != null){
				JOptionPane.showMessageDialog(null, "El Usuario ha sido modificado exitosamente.", "Modificación del contacto", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/// FIN USUARIO /////////////////////////////////////////////////////////////////////////////////////////////////

	/// INICIO CLIENTE /////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<Cliente> cargarCliente(){
		cliente = new ArrayList<Cliente>();
		try {
			rs = st.executeQuery("SELECT nombre, apellido, telefono, documento, idCliente FROM cliente");
			while(rs.next()){
				cliente.add(new Cliente(rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("documento"), rs.getString("idCliente")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	public void agregarCliente(Cliente cliente){
		
		
		try {
			System.out.println(cliente.getDocumento());
			File imagen = new File(cliente.getDocumento());
			FileInputStream  foto = new FileInputStream(imagen);
			
			prst = con.prepareStatement("INSERT INTO CLIENTE VALUES (?,?,?,?,?)");
			prst.setString(1, cliente.getNombre());
			prst.setString(2, cliente.getApellido());
			prst.setString(3, cliente.getTelefono());
			prst.setBinaryStream(4, foto, (int) imagen.length());
			prst.setString(5, cliente.getIdCliente());
			prst.execute();
			
			if(prst != null){
				JOptionPane.showMessageDialog(null, "El Cliente ha sido agregado correctamente", "Cliente agregado", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	public void eliminarCliente(Cliente cliente){
		try {
			prst = con.prepareStatement("DELETE FROM CLIENTE WHERE idCliente = ?");
			prst.setString(1, cliente.getIdCliente());
			prst.execute();
			if(st != null){
				JOptionPane.showMessageDialog(null, "Se ha Cliente ha sido eliminado exitosamente","Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modificarCliente(String id, Cliente cliente) {
		try {
			File imagen = new File(cliente.getDocumento());
			FileInputStream  foto = new FileInputStream(imagen);
			
			prst = con.prepareStatement("UPDATE CLIENTE SET nombre = ?, apellido = ?, telefono = ?, documento = ? WHERE idCliente = ?");
			prst.setString(1, cliente.getNombre());
			prst.setString(2, cliente.getApellido());
			prst.setString(3, cliente.getTelefono());
			prst.setBinaryStream(4, foto, (int) imagen.length());
			prst.setString(5, id);
			prst.execute();
			if(prst != null){
				JOptionPane.showMessageDialog(null, "El Cliente ha sido modificado exitosamente.", "Modificación del contacto", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//// FIN CLIENTEEEEEEEEEEE///////////////////////////////////
	
	/*public ArrayList<Cliente> buscarCliente(String nombre){
		//SELECT * FROM travel WHERE name like '%%%1s%%'", nombre));
		cliente = new ArrayList<Cliente>();
		try {
			rs = st.executeQuery("SELECT nombre, apellido, telefono, documento, idCliente FROM cliente WHERE nombre like '"+nombre+"'");
			while(rs.next()){
				cliente.add(new Cliente(rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("documento"), rs.getString("idCliente")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cliente;
		
	}*/
	///INICIO ACCESORIO
	
	public ArrayList<Accesorio> cargarAcccesorios(){
		accesorio = new ArrayList<Accesorio>();
		try {
			rs = st.executeQuery("SELECT idAccesorio, Nombre, Serial, Descripcion, Precio FROM accesorio");
			while(rs.next()){
				accesorio.add(new Accesorio(rs.getInt("idAccesorio"), rs.getString("nombre"), rs.getString("serial"), rs.getString("descripcion"), rs.getInt("precio")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accesorio;
	}
	
	
	public void agregarAccesorio(Accesorio accesorio){
		try {
			
			prst = con.prepareStatement("INSERT INTO Accesorio(nombre,Serial,Descripcion,Precio) VALUES (?,?,?,?)");
			prst.setString(1, accesorio.getNombre());
			prst.setString(2, accesorio.getSerial());
			prst.setString(3, accesorio.getDescripcion());
			prst.setInt(4, accesorio.getPrecio());
			prst.execute();
			
			if(prst != null){
				JOptionPane.showMessageDialog(null, "El Accesorio ha sido agregado correctamente", "Accesorio agregado", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarAccesorio(Accesorio accesorio){
		try {
			prst = con.prepareStatement("DELETE FROM ACCESORIO WHERE idAccesorio = ?");
			prst.setInt(1, accesorio.getIdAccesorio());
			prst.execute();
			
			if(prst != null){
				JOptionPane.showMessageDialog(null, "Se ha eliminado el accesorio correctamente", "Accesorio eliminado", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void modificarAccesorio(int id, Accesorio accesorio){
		try {
			prst = con.prepareStatement("UPDATE ACCESORIO SET Nombre = ?, Serial = ?, Descripcion = ? , precio = ? WHERE idAccesorio = ?");
			
			prst.setString(1, accesorio.getNombre());
			prst.setString(2, accesorio.getSerial());
			prst.setString(3, accesorio.getDescripcion());
			prst.setInt(4, accesorio.getPrecio());
			prst.setInt(5, id);
			prst.executeUpdate();
			
			if(prst != null){
				JOptionPane.showMessageDialog(null, "El Accesorio ha sido modificado correctamente", "Accesorio modificado", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
















