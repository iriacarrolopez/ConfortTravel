package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import bd.BD;
/*NO LAS UTILIZAMOS DE MOMENTO*/
//import clases.Administrador;
//import clases.Cliente;
import clases.Persona;
import clases.TipoPersona;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

public class VentanaInicio extends JFrame {

	/**
	 * 
	 */
	//private static JFrame frame;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelNorte, panelSur, panelCentro, panelIzquierda;
	private JButton btnInicio;
	private JLabel lbldni;
	private JTextField textDni;
	private JPasswordField txtcontrasenia;
	private JLabel lblContrasena, lblImagen;
	private JLabel lblNewLabel;
	private JLabel lblHora;
	protected VentanaAdministrador va;
	protected VentanaCliente vcl;
	protected JFrame ventanaActual;
	
	public static String dni;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInicio() {

		//frame = new JFrame();
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("INICIO DE SESI�N");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		Connection con = BD.initBD("confortTravel.db");
		System.out.println("--Abriendo la conexion con la base de datos en la ventana inicio sesion");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelSur = new JPanel();
		panelSur.setBackground(Color.CYAN);
		contentPane.add(panelSur, BorderLayout.SOUTH);

		panelNorte = new JPanel();
		panelNorte.setBackground(Color.CYAN);
		contentPane.add(panelNorte, BorderLayout.NORTH);

		lblNewLabel = new JLabel("INICIO DE SESI\u00D3N");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.BLACK);
		panelNorte.add(lblNewLabel);

		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(4, 2, 0, 0));
		panelCentro.setLayout(new GridLayout(5, 2));

		panelIzquierda = new JPanel();
		panelIzquierda.setBackground(Color.WHITE);
		contentPane.add(panelIzquierda, BorderLayout.WEST);
		lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		


		lblImagen.setIcon(new ImageIcon(VentanaInicio.class.getResource("/imagenes/iconoIni.jpg")));

		panelIzquierda.add(lblImagen);

		lbldni = new JLabel("Introduzca su dni:");
		lbldni.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lbldni);

		textDni = new JTextField(20);
		textDni.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(textDni);

		lblContrasena = new JLabel("Introduzca su contraseña:");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblContrasena);

		txtcontrasenia = new JPasswordField(10);
		txtcontrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(txtcontrasenia);

		btnInicio = new JButton("INICIO SESION");
		panelSur.add(btnInicio);
		
		
		
		/**
		 * BOTON INICIO SESION
		 */
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dniExpresR = "[0-9]{8}[A-Z]{1}";
				
				//String conExpresR = "[A-Z][a-z]";
				dni = textDni.getText();
				String cont = String.valueOf(txtcontrasenia.getPassword());
				if (Pattern.matches(dniExpresR, dni)/* && Pattern.matches(conExpresR, cont)*/){
					Persona p = BD.obtenerDatosPersona(con, dni);
					BD.closeBD(con);
					if (p != null) {
						if (p.getContrasenia().equals(cont)) {
							if(p.getTipo().equals(TipoPersona.ADMINISTRADOR)) {
								
								va = new VentanaAdministrador();
								JOptionPane.showMessageDialog(null, "Bienvenido", "SESION INICIADA EN ADMINISTRADOR",JOptionPane.INFORMATION_MESSAGE);
								System.out.println("entra con el usuario administrador");
								//frame.dispose();
								ventanaActual.setVisible(false);
									
							}else {
								vcl = new VentanaCliente();
								JOptionPane.showMessageDialog(null, "Bienvenido", "SESION INICIADA EN CLIENTE",
										JOptionPane.INFORMATION_MESSAGE);
								System.out.println("entra con el usuario cliente");
								//frame.dispose();
								ventanaActual.setVisible(false);
							}
							
							System.out.println("--Inicio de sesion correcto");
							//frame.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "La contrase�a no es correcta", "ERROR",
									JOptionPane.ERROR_MESSAGE);
							System.out.println("--La contrase�a es incorrecta");
						}
					} else {
						JOptionPane.showMessageDialog(null, "No existe un registro asociado a ese DNI", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						System.out.println("--No existe un registro asociado a ese DNI");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					System.out.println("Los datos no cumplen los requisitos");
				}
				textDni.setText("");
				txtcontrasenia.setText("");
			}
		});
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
		lblHora = new JLabel();
		panelNorte.add(lblHora);
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
						lblHora.setText(formateador.format(LocalDateTime.now()));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		};
		
		Thread hilo = new Thread(runnable);
		hilo.start();
		


	}
	

	
	

}
