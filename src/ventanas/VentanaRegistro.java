package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import bd.BD;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;

public class VentanaRegistro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelNorte, panelSur, panelCentro, panelIzquierda;
	private JButton btnRegistro;
	private JLabel lbldni;
	private JTextField textDni;
	private JPasswordField txtContrasenia, txtContrasenia2;
	private JLabel lblContrasena, lblContrasena2 ,lblImagen, lblTitulo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblTipo;
	private JComboBox<String> comboBox;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		
		Connection con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		
		setTitle("REGISTRARSE COMO USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		
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
		
		lblTitulo = new JLabel("REGISTRARSE COMO USUARIO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitulo.setForeground(Color.BLACK);
		panelNorte.add(lblTitulo);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(4, 2, 0, 0));
		panelCentro.setLayout(new GridLayout(6,2));
		
		panelIzquierda = new JPanel();
		panelIzquierda.setBackground(Color.WHITE);
		contentPane.add(panelIzquierda, BorderLayout.WEST);
		lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblImagen.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/imagenes/iconoIni.jpg")));
		
		panelIzquierda.add(lblImagen);
		
		lblNombre = new JLabel("Introduzca su nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblNombre);
		
		txtNombre = new JTextField();
		panelCentro.add(txtNombre);
		txtNombre.setColumns(10);
		
		
		lbldni = new JLabel("Introduzca su dni:");
		lbldni.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lbldni);
		
		textDni = new JTextField(20);
		panelCentro.add(textDni);
		
		
		lblContrasena = new JLabel("Introduzca una contraseña:");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblContrasena);
		
		txtContrasenia = new JPasswordField(10);
		txtContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(txtContrasenia);
		
		
		lblContrasena2 = new JLabel("Confirme su contraseña:");
		lblContrasena2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblContrasena2);
		
		txtContrasenia2 = new JPasswordField(10);
		txtContrasenia2.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(txtContrasenia2);
		
		lblEmail = new JLabel("Introduzca su email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblEmail);
		
		txtEmail = new JTextField();
		panelCentro.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblTipo = new JLabel("Elige el tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblTipo);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("ARIAL", Font.PLAIN, 12));
		comboBox.addItem("CLIENTE");
		comboBox.addItem("ADMINISTRADOR");
		panelCentro.add(comboBox);
		
		
		 btnRegistro = new JButton("REGISTRO");
		panelSur.add(btnRegistro);
		/**
		 * Boton de registro del usuario
		 */
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = txtNombre.getText();
				String dniExpresR = "[0-9]{8}[A-Z]";
				String conExpresR = "[A-Z][a-z][0-9][^A-Za-z0-9]";
				String dni= textDni.getText();
				String cont = txtContrasenia.getText();
				String cont2 = txtContrasenia2.getText();
				String email = txtEmail.getText();
				if(Pattern.matches(dniExpresR, dni) && Pattern.matches(conExpresR, cont) && Pattern.matches(conExpresR, cont2)) {
					String sel = comboBox.getSelectedItem().toString();
					if(sel.equals("CLIENTE")) {
						BD.insertarPersona(con, nom, dni, cont2, email, sel);
					}else if(sel.equals("ADMINISTRADOR")) {
						BD.insertarPersona(con, nom, dni, cont2, email, sel);
					}
					JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO, BIENVENIDO", "CORRECTO ", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "ERROR, compruebe de nuevo los datos introduccidos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				txtNombre.setText("");
				textDni.setText("");
				txtContrasenia.setText("");
				txtContrasenia2.setText("");
				txtEmail.setText("");
			}
		});
		
		
	}

}

