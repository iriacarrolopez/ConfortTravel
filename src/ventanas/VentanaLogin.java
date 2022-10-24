package ventanas;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLogin {

	private JFrame frame;
	private JPanel contentPane, panelIzq, panelCentro, panelDerecha;
	private JButton btnInicioSesion;
	private JButton btnRegistrar;
	private JLabel lblTitulo,lblImagen1,lblImagen2;
	private VentanaRegistro VReg;
	private VentanaInicio VIni;

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
		frame = new JFrame();
		frame.setBackground(new Color(248, 248, 255));
		frame.setBounds(200, 200, 950, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout());
		
		//paneles
		panelIzq = new JPanel();
		contentPane.add(panelIzq);
		//panelIzq.setLayout(new GridLayout(0, 1, 0, 0));
		panelIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		lblImagen1 = new JLabel();
		lblImagen1.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblImagen1.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/logo.jpg")));
		
		panelIzq.add(lblImagen1);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro);
		panelCentro.setLayout(new GridLayout(3, 0, 0, 0));
		
		panelDerecha = new JPanel();
		contentPane.add(panelDerecha);
		panelDerecha.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		lblImagen2 = new JLabel();
		lblImagen2.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/travel.jpg")));
		panelDerecha.add(lblImagen2);
		
		
		
		//titulo
		
		lblTitulo = new JLabel("BIENVENIDO A COMFORTRAVEL");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBackground(SystemColor.controlLtHighlight);
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panelCentro.add(lblTitulo);
		
		//BOTONES
		btnRegistrar = new JButton("REGISTRAR");
		panelCentro.add(btnRegistrar);	
		btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ir a la ventana de registro
				VReg = new VentanaRegistro();
				VReg.setVisible(true);
			}
		});
		btnInicioSesion = new JButton("INICIO SESION");		
		panelCentro.add(btnInicioSesion);
		
		/**
		 * Boton inicio de sesion
		 * Este boton nos lleva a la venta de inicio sesión
		 */
		btnInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ir a la ventana de inicio
				VIni = new VentanaInicio();
				VIni.setVisible(true);
				
			}
		});
		
		
	
	}
	

}
