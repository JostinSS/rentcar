package com.proyectofinal.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.proyectofinal.bd.Conexion;
import com.proyectofinal.modelos.ModeloAccesorios;


public class Login extends JFrame implements ActionListener{
	private JPanel panel;

	private JTextField txtUsuario = new JTextField();
	private JPasswordField txtContraseña = new JPasswordField();
	
	private JButton btnIniciar = new JButton("Iniciar sesion");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private static Login instancia;
	public static Login getInstacia(){
		if(instancia == null){
			instancia = new Login();
		}
		return instancia;
	}
	
	public Login() {
		setTitle("Inicio de sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 140);
		setResizable(false);
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,2,4,7));
		panel.setBorder(new TitledBorder("Introduzca sus datos"));
		panel.add(new JLabel("Usuario: "));
		panel.add(txtUsuario);
		panel.add(new JLabel("Contraseña:"));
		panel.add(txtContraseña);
		panel.add(btnIniciar);
		panel.add(btnCancelar);
		btnIniciar.addActionListener(this);
		btnCancelar.addActionListener(this);
		this.add(panel);
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnIniciar){
			if(txtUsuario.getText().isEmpty() || txtContraseña.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Ningún de los campos puede quedarse vacio.", "Advertencia!", JOptionPane.WARNING_MESSAGE);
			}else{
				Conexion.getInstacia().validarUsuario(txtUsuario.getText(), txtContraseña.getText());
				
			}
		}else if(e.getSource() == btnCancelar){
			int respuesta = 0;
			respuesta = JOptionPane.showConfirmDialog(null, "Seguro que desea salir?", "Salir.", JOptionPane.YES_NO_OPTION);
			
			if(respuesta == 0){
				this.dispose();
			}
		}
		
	}

}
