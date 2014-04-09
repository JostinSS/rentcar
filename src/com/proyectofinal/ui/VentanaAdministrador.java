package com.proyectofinal.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import com.proyectofinal.ui.mantenimiento.MantenimientoClientes;
import com.proyectofinal.ui.mantenimiento.MatenimientoAccesorio;
import com.proyectofinal.ui.mantenimiento.MatenimientoUsuarios;

public class VentanaAdministrador extends JFrame implements ActionListener{
	private JMenuItem menuUsuarios;
	private JMenuItem menuAccesorios;
	private JMenuItem menuClientes;
	private JMenuItem mntmSalir;
	private JMenuItem menuCerrarSesion;
	
	public VentanaAdministrador() {
		setTitle("Administraci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		JMenu mnArchivo = new JMenu("Archivo");
		barraMenu.add(mnArchivo);
		
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		
		menuCerrarSesion = new JMenuItem("Cerrar sesi\u00F3n");
		menuCerrarSesion.addActionListener(this);
		mnArchivo.add(menuCerrarSesion);
	
		mnArchivo.add(mntmSalir);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		barraMenu.add(mnMantenimiento);
		
		menuUsuarios = new JMenuItem("Usuarios");
		menuUsuarios.addActionListener(this);
		
		menuAccesorios = new JMenuItem("Accesorios");
		menuAccesorios.addActionListener(this);
		
		
		menuClientes = new JMenuItem("Clientes");
		menuClientes.addActionListener(this);
		
		mnMantenimiento.add(menuClientes);
		mnMantenimiento.add(menuUsuarios);
		mnMantenimiento.add(menuAccesorios);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		int respuesta = 0;
		
		if(e.getSource() == menuAccesorios){
			MatenimientoAccesorio.getInstacia().setLocationRelativeTo(null);
			MatenimientoAccesorio.getInstacia().setVisible(true);
			VentanaAdministrador.this.dispose();
		}else if(e.getSource() == menuClientes){
			MantenimientoClientes.getInstacia().setLocationRelativeTo(null);
			MantenimientoClientes.getInstacia().setVisible(true);
			VentanaAdministrador.this.dispose();
		}else if(e.getSource() == menuUsuarios){
			MatenimientoUsuarios.getInstacia().setLocationRelativeTo(null);
			MatenimientoUsuarios.getInstacia().setVisible(true);
			VentanaAdministrador.this.dispose();
		}else if(e.getSource() == mntmSalir){
			respuesta = JOptionPane.showConfirmDialog(null, "Seguro que desea salir?", "Salir?", JOptionPane.YES_NO_OPTION);
			if(respuesta == 1){
				
			}else{
				System.exit(0);
			}
		}else if(e.getSource() == menuCerrarSesion){
			respuesta = JOptionPane.showConfirmDialog(null, "Seguro que desea Cerrar Sesion?", "Terminar Sesion", JOptionPane.YES_NO_OPTION);
			
			if(respuesta == 1){
				
			}else{
				Login.getInstacia().setLocationRelativeTo(null);
				Login.getInstacia().setVisible(true);
				VentanaAdministrador.this.dispose();
			}
				
		}
		
	}
}
