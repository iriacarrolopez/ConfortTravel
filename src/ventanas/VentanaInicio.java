package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

public class VentanaInicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelNorte  ,panelSur,panelCentro,panelIzquierda;
	private JButton btnInicio;
	private JLabel lbldni;
	private JTextField textDni;
	private JPasswordField txtcontrasenia;
	private JLabel lblContrasena,lblImagen;
	private JLabel lblNewLabel;
	
	

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
		setTitle("INICIO SESIÓN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		panelSur = new JPanel();
		panelSur.setBackground(Color.BLUE);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		panelNorte = new JPanel();
		panelNorte.setBackground(Color.BLUE);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("INICIO DE SESI\u00D3N");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.BLACK);
		panelNorte.add(lblNewLabel);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(4, 2, 0, 0));
		panelCentro.setLayout(new GridLayout(5,2));
		
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
		panelCentro.add(textDni);
		
		
		lblContrasena = new JLabel("Introduzca su contrase\u00F1a:");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(lblContrasena);
		
		txtcontrasenia = new JPasswordField(10);
		txtcontrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentro.add(txtcontrasenia);
		
		
		 btnInicio = new JButton("INICIO SESION");
		panelSur.add(btnInicio);
		/**
		 * Boton de inicio de sesión
		 */
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dniExpresR = "\\\\d{8}[A-HJ-NP-TV-Z]";
				String conExpresR = "[A-Z][a-z][0-9][^A-Za-z0-9]";
				String dni= textDni.getText();
				String con = txtcontrasenia.getText();
				if(Pattern.matches(dniExpresR,dni) && Pattern.matches(conExpresR, con)) {
					
					JOptionPane.showMessageDialog(null, "INICIO DE SESIÓN CORRECTO,BIENVENIDO", "CORRECTO ", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "ERROR, compruebe de nuevo los datos introduccidos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
	}

}
