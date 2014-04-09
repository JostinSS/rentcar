package com.proyectofinal.ui.mantenimiento;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.dsig.spec.XPathType.Filter;

import com.proyectofinal.bd.Conexion;
import com.proyectofinal.entidades.Cliente;
import com.proyectofinal.modelos.ModeloClientes;
import com.proyectofinal.modelos.ModeloUsuarios;
import com.proyectofinal.ui.BuscadorTablas;
import com.toedter.calendar.JDateChooser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MantenimientoClientes extends JFrame implements ActionListener, MouseListener{
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	
	private JTable tablaCliente;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnBuscar;
	private String rutaArchivo = null;
	
	private static MantenimientoClientes instancia;
	private JButton btnExaminar;
	private JTextField textField;
	private JTextField txtNombreDocumento;
	private JTextField txtBuscar;
	
	
	public static MantenimientoClientes getInstacia(){
		if(instancia == null){
			instancia = new MantenimientoClientes();
		}
		return instancia;
	}
	

	public MantenimientoClientes() {
		setTitle("Matenimiento de clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 494);
		getContentPane().setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(88, 81, 216, 19);
		getContentPane().add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(88, 106, 216, 19);
		getContentPane().add(txtApellido);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(88, 134, 216, 19);
		getContentPane().add(txtTelefono);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDocumento.setBounds(16, 164, 78, 17);
		getContentPane().add(lblDocumento);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefono.setBounds(16, 134, 62, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellido.setBounds(16, 106, 62, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(16, 81, 62, 14);
		getContentPane().add(lblNombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 223, 89, 23);
		btnAgregar.addActionListener(this);
		getContentPane().add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(109, 223, 89, 23);
		btnEliminar.addActionListener(this);
		getContentPane().add(btnEliminar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(208, 223, 89, 23);
		btnModificar.addActionListener(this);
		getContentPane().add(btnModificar);
		
		
		JScrollPane scrollPane = new JScrollPane(tablaCliente);
		scrollPane.setBounds(314, 45, 431, 400);
		getContentPane().add(scrollPane);
		
		tablaCliente = new JTable(ModeloClientes.getInstacia());
		scrollPane.setViewportView(tablaCliente);
		tablaCliente.addMouseListener(this);
		
		btnExaminar = new JButton("Examinar");
		btnExaminar.addActionListener(this);
		btnExaminar.setBounds(226, 162, 78, 23);
		getContentPane().add(btnExaminar);
		
		txtNombreDocumento = new JTextField();
		txtNombreDocumento.setEditable(false);
		txtNombreDocumento.setBounds(88, 163, 133, 18);
		getContentPane().add(txtNombreDocumento);
		txtNombreDocumento.setColumns(10);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(456, 12, 190, 19);
		getContentPane().add(txtBuscar);
		txtBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(656, 11, 89, 23);
		btnBuscar.setMnemonic('B');
		getContentPane().add(btnBuscar);
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int fila = tablaCliente.getSelectedRow();
		JFileChooser jc = new JFileChooser();
		if(e.getSource() == btnAgregar){
			if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtTelefono.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
			if(rutaArchivo != null){
				ModeloClientes.getInstacia().agregarCliente(new Cliente(txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(), rutaArchivo));
				txtNombre.setText("");
				txtApellido.setText("");
				txtNombreDocumento.setText("");
				txtTelefono.setText("");
				//txtIdCliente.setText("");
				}else{
					JOptionPane.showMessageDialog(this, "Debe seleccionar un documento", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if(e.getSource() == btnExaminar){

			FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG", "jpg");
			jc.setFileFilter(filtro);
			
			int seleccion = jc.showOpenDialog(null);
			File archivo = jc.getSelectedFile();
			
			if(seleccion == jc.APPROVE_OPTION){
				
			jc.setFileFilter(filtro);
			rutaArchivo = archivo.getAbsolutePath();
			String nombreArchivo = archivo.getName();
			txtNombreDocumento.setText(nombreArchivo);
			}else if (seleccion == jc.CANCEL_OPTION){
				JOptionPane.showMessageDialog(this, "Debe seleccionar un documento.", "Error", JOptionPane.ERROR_MESSAGE);
				
			}else{
				
			}
			
			//Copiado de las imagenes 
			File lugarImagen = new File(jc.getSelectedFile().toString());
			if(txtNombre.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Debes rellenar el campo nombre primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
			}else{
			File destinoImagen = new File("D:\\Workspace (New)\\Proyecto Final de programacion II\\img\\"+txtNombre.getText()+".jpg");
			
				FileInputStream fis;
				try {
					fis = new FileInputStream(lugarImagen);
					FileOutputStream fos = new FileOutputStream(destinoImagen); //outFile -> Copia del archivo
					FileChannel lugarOrigen = fis.getChannel(); 
					FileChannel lugarDestino = fos.getChannel(); 
					lugarOrigen.transferTo(0, lugarOrigen.size(), lugarDestino); 
					fis.close(); 
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //inFile -> Archivo a copiar
			}
				
				
			
			
		}else if(e.getSource() == btnEliminar){
			
			
			if (fila < 0){
				JOptionPane.showMessageDialog(this, "Debes seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				ModeloClientes.getInstacia().eliminarCliente(fila);
				txtNombre.setText("");
				txtApellido.setText("");
				txtNombreDocumento.setText("");
				txtTelefono.setText("");
				//txtIdCliente.setText("");
			}
		}else if(e.getSource() == btnModificar){
			
			if(fila < 0){
				JOptionPane.showMessageDialog(this, "Debes seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);
				
			}else if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtNombreDocumento.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Debes rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				
			
				
			}else{
				
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG", "jpg");
				jc.setFileFilter(filtro);
				
				jc.showOpenDialog(null);
				File archivo = jc.getSelectedFile();
				
				jc.setFileFilter(filtro);
				
				rutaArchivo = archivo.getAbsolutePath();
				String nombreArchivo = archivo.getName();
				txtNombreDocumento.setText(nombreArchivo);
				
				ModeloClientes.getInstacia().modificarCliente(new Cliente(txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(), rutaArchivo),fila);
				txtNombre.setText("");
				txtApellido.setText("");
				txtNombreDocumento.setText("");
				txtTelefono.setText("");
				//txtIdCliente.setText("");
			}	
		}else if(e.getSource() == btnBuscar){
			BuscadorTablas.getInstancia().buscar(tablaCliente, txtBuscar.getText());
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	int fila = tablaCliente.getSelectedRow();
	txtNombre.setText(tablaCliente.getValueAt(fila, 0).toString());
	txtApellido.setText(tablaCliente.getValueAt(fila, 1).toString());
	txtTelefono.setText(tablaCliente.getValueAt(fila, 2).toString());
	txtNombreDocumento.setText("Documento del cliente");
	
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
