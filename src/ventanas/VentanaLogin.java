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
import javax.swing.UIManager;
import java.awt.BorderLayout;

public class VentanaLogin extends JFrame {

	private static JFrame frame;
	private JPanel contentPane;
	private JButton btnInicioSesion;
	private JButton btnRegistrar;
	private VentanaRegistro VReg;
	private VentanaInicio VIni;
	private JLabel lblinfo;
	private JPanel panelDerecha;
	private JPanel panel;
	private JLabel lblNewLabel;
	private Thread t;
	private JFrame ventanaActual;

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
		ventanaActual = this;
		frame = new JFrame();
		frame.setBackground(new Color(248, 248, 255));
		// frame.setBounds(200, 200, 950, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		frame.setContentPane(contentPane);

		frame.setTitle(" Booking");
		frame.setIconImage(new ImageIcon("imagenes/logo.jpg").getImage());
		frame.setSize(1050, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		contentPane.setLayout(new BorderLayout(0, 0));
						
						panelDerecha = new JPanel();
						panelDerecha.setBackground(Color.WHITE);
						contentPane.add(panelDerecha, BorderLayout.WEST);
								panelDerecha.setLayout(new GridLayout(0, 1, 0, 0));
								
								lblinfo = new JLabel("WELCOME TO COMFORT TRAVEL");
								lblinfo.setBackground(Color.WHITE);
								Runnable r3 = new Runnable() {
									
									@Override
									public void run() {
										boolean color = false;
										int x = lblinfo.getX();
										int xInicialVentana = ventanaActual.getX();
										int xFinalVentana =ventanaActual.getWidth() + xInicialVentana;
										while(true) {
											x = x + 10;
											if(x>=xFinalVentana) {
												x = 0;
												if(!color) {
													lblinfo.setForeground(Color.WHITE);
													lblinfo.setBackground(Color.BLACK);
													color = true;
												}else {
													lblinfo.setForeground(Color.BLACK);
													lblinfo.setBackground(Color.WHITE);
													color = false;
												}
											}
											lblinfo.setBounds(x, lblinfo.getY(), lblinfo.getWidth(), lblinfo.getHeight());
											try {
												Thread.sleep(1000);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}
											
										
									
								};
								
								/*Thread*/ t = new Thread(r3);
								t.start();
							
								panelDerecha.add(lblinfo);
								lblinfo.setFont(new Font("Tahoma", Font.BOLD, 15));
								lblinfo.setHorizontalAlignment(SwingConstants.CENTER);
								btnInicioSesion = new JButton("");
								panelDerecha.add(btnInicioSesion);
								btnInicioSesion.setForeground(Color.WHITE);
								btnInicioSesion.setBorder(null);
								btnInicioSesion.setBackground(Color.WHITE);
								btnInicioSesion.setToolTipText("INICIO SESION");
								btnInicioSesion.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/boton_inicio.png")));
								btnInicioSesion.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										// ir a la ventana de inicio
										VIni = new VentanaInicio();
										VIni.setVisible(true);
										frame.dispose();
									}
								});
						
								// BOTONES
								btnRegistrar = new JButton("");
								panelDerecha.add(btnRegistrar);
								btnRegistrar.setForeground(Color.WHITE);
								btnRegistrar.setBorder(null);
								btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 22));
								btnRegistrar.setBackground(Color.WHITE);
								btnRegistrar.setToolTipText("REGISTRAR");
								btnRegistrar.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/botonR.png")));
								
								panel = new JPanel();
								panel.setBackground(Color.WHITE);
								contentPane.add(panel, BorderLayout.CENTER);
								
								lblNewLabel = new JLabel("");
								lblNewLabel.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/confotT.jpg")));
								panel.add(lblNewLabel);
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
		 * Boton inicio de sesion Este boton nos lleva a la venta de inicio sesi�n
		 */

	}

}
