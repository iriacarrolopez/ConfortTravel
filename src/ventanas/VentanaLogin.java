package ventanas;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/*No lo usamos ahora*/
//import bd.BD;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
/*No lo usamos ahora*/
//import java.sql.Connection;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JFrame{

	private static JFrame frame;
	private JPanel contentPane, panelCentro, panelDerecha;
	private JButton btnInicioSesion;
	private JButton btnRegistrar;
	private JLabel lblTitulo, lblImagen2;
	private VentanaRegistro VReg;
	private JPanel panelBotones;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/* No la vamos a necesitar ahora mismo */
		// Connection con = BD.initBD("confortTravel.db");

		frame = new JFrame();
		frame.setBackground(new Color(248, 248, 255));
		//frame.setBounds(200, 200, 950, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout());

		
		
		frame.setTitle(" Booking");
		frame.setIconImage(new ImageIcon("imagenes/logo.jpg").getImage());		
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro);
		panelCentro.setLayout(new GridLayout(2, 0, 0, 0));

		panelDerecha = new JPanel();
		contentPane.add(panelDerecha);
		panelDerecha.setLayout(new FlowLayout(FlowLayout.CENTER));

		lblImagen2 = new JLabel();
		lblImagen2.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/travel.jpg")));
		panelDerecha.add(lblImagen2);

		// titulo

		lblTitulo = new JLabel("BIENVENIDO A COMFORTRAVEL");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBackground(SystemColor.controlLtHighlight);
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panelCentro.add(lblTitulo);
		/**
		 * Boton registrar - Nos lleva a la ventana inicio sesion
		 */
		
		panelBotones = new JPanel();
		panelCentro.add(panelBotones);
		btnInicioSesion = new JButton("INICIO SESION");
		panelBotones.add(btnInicioSesion);
		
				// BOTONES
				btnRegistrar = new JButton("REGISTRAR");
				panelBotones.add(btnRegistrar);
				btnRegistrar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						// ir a la ventana de registro
						VReg = new VentanaRegistro();
						VReg.setVisible(true);
						frame.dispose();
					}
				});

		/**
		 * Boton inicio de sesion Este boton nos lleva a la venta de inicio sesión
		 */
		btnInicioSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ir a la ventana de inicio
				VentanaInicio VIni = new VentanaInicio();
				VIni.setVisible(true);
				frame.dispose();
			}
		});

	}

}
