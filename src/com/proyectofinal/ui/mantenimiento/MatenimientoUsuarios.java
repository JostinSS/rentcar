package com.proyectofinal.ui.mantenimiento;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.proyectofinal.bd.Conexion;
import com.proyectofinal.entidades.Usuario;
import com.proyectofinal.modelos.ModeloUsuarios;
import com.proyectofinal.ui.BuscadorTablas;


public class MatenimientoUsuarios extends JFrame implements ActionListener, MouseListener{

	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JComboBox comboCargo;
	

	public JTable tablaUsuarios;
	
	private static MatenimientoUsuarios instancia;
	private JButton btnActualizar;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JLabel lblBuscar;
	public static MatenimientoUsuarios getInstacia(){
		if(instancia == null){
			instancia = new MatenimientoUsuarios();
		}
		return instancia;
	}
	
	public MatenimientoUsuarios() {
		ModeloUsuarios modeloUsuario = new ModeloUsuarios();
		setTitle("Matenimiento de Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 491);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(10, 129, 62, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellido.setBounds(10, 154, 62, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsuario.setBounds(10, 182, 62, 14);
		getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContrasea.setBounds(10, 210, 70, 14);
		getContentPane().add(lblContrasea);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCargo.setBounds(10, 238, 62, 17);
		getContentPane().add(lblCargo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(121, 129, 170, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(121, 154, 170, 19);
		getContentPane().add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(121, 182, 170, 19);
		getContentPane().add(txtUsuario);
		
		comboCargo = new JComboBox();
		comboCargo.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Vendedor"}));
		comboCargo.setBounds(121, 238, 170, 19);
		getContentPane().add(comboCargo);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(121, 208, 170, 19);
		getContentPane().add(txtContrasena);
		
		JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
		scrollPane.setBounds(349, 51, 396, 391);
		getContentPane().add(scrollPane);
		
		tablaUsuarios = new JTable(ModeloUsuarios.getInstacia());
		scrollPane.setViewportView(tablaUsuarios);
		//tablaUsuarios.setModel();
		tablaUsuarios.addMouseListener(this);
				
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 277, 89, 23);
		btnAgregar.addActionListener(this);
		getContentPane().add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(109, 277, 89, 23);
		btnEliminar.addActionListener(this);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Modificar");
		btnActualizar.setBounds(208, 277, 89, 23);
		btnActualizar.addActionListener(this);
		getContentPane().add(btnActualizar);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(450, 18, 208, 19);
		getContentPane().add(txtBuscar);
		txtBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(672, 17, 75, 23);
		btnBuscar.addActionListener(this);
		btnBuscar.setMnemonic('B');
		getContentPane().add(btnBuscar);
		
		lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(354, 21, 46, 14);
		getContentPane().add(lblBuscar);
				
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAgregar){
			if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtContrasena.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos!", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				ModeloUsuarios.getInstacia().AgregarUsuario(new Usuario(txtNombre.getText(), txtApellido.getText(), txtUsuario.getText(), txtContrasena.getText(), comboCargo.getSelectedItem().toString()));
				txtNombre.setText("");
				txtApellido.setText("");
				txtContrasena.setText("");
				txtUsuario.setText("");
				}
			
			}else if(e.getSource() == btnEliminar){
				int fila = tablaUsuarios.getSelectedRow();
				if (fila < 0){
					JOptionPane.showMessageDialog(this, "Debes seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					ModeloUsuarios.getInstacia().eliminarUsuario(tablaUsuarios.getSelectedRow());
					txtNombre.setText("");
					txtApellido.setText("");
					txtContrasena.setText("");
					txtUsuario.setText("");
				}
			}else if(e.getSource() == btnActualizar){
				if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtContrasena.getText().isEmpty()){
					JOptionPane.showMessageDialog(this, "Debes seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					int index = tablaUsuarios.getSelectedRow();
					ModeloUsuarios.getInstacia().modificarUsuario(new Usuario(txtNombre.getText(), txtApellido.getText(), txtUsuario.getText(), txtContrasena.getText(), comboCargo.getSelectedItem().toString()),index);
					txtNombre.setText("");
					txtApellido.setText("");
					txtContrasena.setText("");
					txtUsuario.setText("");
				}
			}else if(e.getSource() == btnBuscar){
				BuscadorTablas.getInstancia().buscar(tablaUsuarios, txtBuscar.getText());
			}
		}

	
	public void mouseClicked(MouseEvent e) {
		int fila = tablaUsuarios.getSelectedRow();
		
		txtNombre.setText(tablaUsuarios.getValueAt(fila, 0).toString());
		txtApellido.setText(tablaUsuarios.getValueAt(fila, 1).toString());
		txtUsuario.setText(tablaUsuarios.getValueAt(fila, 2).toString());
		txtContrasena.setText(tablaUsuarios.getValueAt(fila, 3).toString());
		comboCargo.setSelectedItem(tablaUsuarios.getValueAt(fila, 4).toString());
				
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JTextField getTxtBuscar() {
		return txtBuscar;
	}	
	
	
}
