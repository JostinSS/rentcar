package com.proyectofinal.ui.mantenimiento;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.proyectofinal.entidades.Accesorio;
import com.proyectofinal.modelos.ModeloAccesorios;
import com.proyectofinal.ui.BuscadorTablas;


public class MatenimientoAccesorio extends JFrame implements ActionListener, MouseListener, KeyListener{
	private JTextField txtNombre;
	private JTextField txtSerial;
	private JTextField txtPrecio;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JTable tablaAccesorios;
	private JTextArea txtDescripcion;
	private JButton btnModificar;
	private JButton btnBuscar;
	
	private static MatenimientoAccesorio instancia;
	private JTextField txtBuscar;
	public static MatenimientoAccesorio getInstacia(){
		if(instancia == null){
			instancia = new MatenimientoAccesorio();
		}
		return instancia;
	}
	
	public MatenimientoAccesorio() {
		setTitle("Mantenimiento de Accesorios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 458);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 26, 71, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblSerial = new JLabel("Serial:");
		lblSerial.setBounds(10, 54, 71, 14);
		getContentPane().add(lblSerial);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 109, 71, 14);
		getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 84, 71, 14);
		getContentPane().add(lblPrecio);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(91, 23, 141, 17);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtSerial = new JTextField();
		txtSerial.setColumns(10);
		txtSerial.setBounds(91, 52, 141, 17);
		getContentPane().add(txtSerial);
		
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(this);
		txtPrecio.setBounds(91, 81, 141, 17);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 271, 71, 23);
		btnAgregar.addActionListener(this);
		getContentPane().add(btnAgregar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(85, 271, 63, 23);
		btnBorrar.addActionListener(this);
		getContentPane().add(btnBorrar);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaAccesorios);
		scrollPaneTabla.setBounds(242, 48, 383, 361);
		getContentPane().add(scrollPaneTabla);
		
		tablaAccesorios = new JTable(ModeloAccesorios.getInstacia());
		scrollPaneTabla.setViewportView(tablaAccesorios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 220, 130);
		getContentPane().add(scrollPane);
		
		txtDescripcion = new JTextArea();
		scrollPane.setViewportView(txtDescripcion);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(154, 271, 75, 23);
		btnModificar.addActionListener(this);
		getContentPane().add(btnModificar);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(322, 23, 180, 19);
		getContentPane().add(txtBuscar);
		txtBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(510, 22, 115, 23);
		btnBuscar.addActionListener(this);
		btnBuscar.setMnemonic('B');
		getContentPane().add(btnBuscar);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(242, 26, 70, 14);
		getContentPane().add(lblBuscar);
		tablaAccesorios.addMouseListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAgregar){
			if(txtNombre.getText().isEmpty() || txtSerial.getText().isEmpty() || txtPrecio.getText().isEmpty() || txtDescripcion.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos!", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
			ModeloAccesorios.getInstacia().agregarAccesorio(new Accesorio(txtNombre.getText(),txtSerial.getText(),txtDescripcion.getText(),(Integer.parseInt(txtPrecio.getText()))));
			
			txtNombre.setText("");
			txtSerial.setText("");
			txtDescripcion.setText("");
			txtPrecio.setText("");
			}
		}else if (e.getSource() == btnBorrar){
			int fila = tablaAccesorios.getSelectedRow();
			if (fila < 0){
				JOptionPane.showMessageDialog(this, "Debes seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				ModeloAccesorios.getInstacia().eliminarAccesorio(fila);
				
				txtNombre.setText("");
				txtSerial.setText("");
				txtDescripcion.setText("");
				txtPrecio.setText("");
		}
		
		}else if(e.getSource() == btnModificar){
			int index = tablaAccesorios.getSelectedRow();
			if(txtNombre.getText().isEmpty() || txtSerial.getText().isEmpty() || txtPrecio.getText().isEmpty() || txtDescripcion.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Debe rellenar todos los campos!", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
			
			ModeloAccesorios.getInstacia().modificarAccesorio(new Accesorio(txtNombre.getText(),txtSerial.getText(),txtDescripcion.getText(),(Integer.parseInt(txtPrecio.getText()))),index);
		
			txtNombre.setText("");
			txtSerial.setText("");
			txtDescripcion.setText("");
			txtPrecio.setText("");
			}
		}else if(e.getSource() == btnBuscar){
			BuscadorTablas.getInstancia().buscar(tablaAccesorios, txtBuscar.getText());
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int fila = tablaAccesorios.getSelectedRow();
		
		 txtNombre.setText(tablaAccesorios.getValueAt(fila, 1).toString());
		 txtSerial.setText(tablaAccesorios.getValueAt(fila, 2).toString());
		 txtDescripcion.setText(tablaAccesorios.getValueAt(fila, 3).toString());
		 txtPrecio.setText(tablaAccesorios.getValueAt(fila, 4).toString());
		 
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(!Character.isDigit(e.getKeyChar()) && !Character.isISOControl(e.getKeyChar()))
		{
		     Toolkit.getDefaultToolkit().beep();
		     e.consume();
		 }
		
	}
}
