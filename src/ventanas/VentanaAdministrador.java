package ventanas;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.BD;
import clases.Ciudad;
import clases.Reserva;
import clases.TipoPersona;
import paneles.PanelAlojamiento;
import paneles.PanelDestino;
import paneles.PanelExcursiones;
import paneles.PanelReserva;
import paneles.PanelUsuarios;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import javax.swing.JButton;


import java.awt.Font;
import java.awt.Color;

public class VentanaAdministrador extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private JPanel contentPane, panelPrincipal, panelSur, panelIzq, panelNorte;
	private JButton btnVolver, btnSalir,btnReservas;
	public VentanaLogin ventanalogin;
	private JLabel lblTitulo;
	// paneles
	public PanelDestino pad;
	public PanelAlojamiento paa;
	public PanelExcursiones pex;
	public PanelUsuarios pusuario;
	public PanelReserva pr;
	
	private JLabel lblHora;
	private HashMap<Ciudad, ArrayList<Reserva>> mapaReservas;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem m1,m2,m3,m4,m5;
	
	

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {
		frame = new JFrame();
		frame.setTitle("Ventana Administrador");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setBounds(40, 50, 1010, 790);
		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
//		paneles

		panelNorte = new JPanel();
		panelNorte.setBackground(new Color(0, 204, 255));
		contentPane.add(panelNorte, BorderLayout.NORTH);

		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		// panel que va a cambiar dependiedo que opcion elija el usuario
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(204, 204, 255));
		contentPane.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setBounds(100, 100, 700, 700);
		panelPrincipal.setLayout(new GridLayout(1, 0, 0, 0));

		panelIzq = new JPanel();
		panelIzq.setBackground(Color.WHITE);
		contentPane.add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new GridLayout(2, 0, 0, 0));
		// inicializacion de paneles
		pad = new PanelDestino();
		paa = new PanelAlojamiento();
		pex = new PanelExcursiones();
		pr= new PanelReserva();
		pusuario = new PanelUsuarios();
//Etiquetas
		lblTitulo = new JLabel("Administrador");
		lblTitulo.setFont(new Font("Verdana Pro Cond Black", Font.BOLD | Font.ITALIC, 15));
		panelNorte.add(lblTitulo);
//botones 
		btnVolver = new JButton("VOLVER AL LA PRINCIPAL");
		panelSur.add(btnVolver);

		btnSalir = new JButton("SALIR");
		panelSur.add(btnSalir);
		
		//menu
		menuBar = new JMenuBar();
		menu=new JMenu("Opciones");
		
		frame.setJMenuBar(menuBar);
		m1 = new JMenuItem("Destino");
		
		menu.add(m1);
		m2 = new JMenuItem("Alojamientos");
		menu.add(m2);
		m3 = new JMenuItem("Excursiones");
		menu.add(m3);
		m4 = new JMenuItem("Usuarios");
		menu.add(m4);
		m5 = new JMenuItem("Rerservas");
		menu.add(m5);
		menuBar.add(menu);
		panelNorte.add(menuBar);
		
	/**
	 * Menu con todas las interacciones que puede realizar el administrador	
	 */
	m1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			panelPrincipal.removeAll();
			panelPrincipal.add(pad);
			panelPrincipal.updateUI();
			
		}
	});
	m2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			panelPrincipal.removeAll();
			panelPrincipal.add(paa);
			panelPrincipal.updateUI();
			
		}
	});
	m3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
			panelPrincipal.removeAll();
			panelPrincipal.add(pex);
			panelPrincipal.updateUI();

			
		}
	});
	m4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			panelPrincipal.removeAll();
			panelPrincipal.add(pusuario);
			panelPrincipal.updateUI();
			
		}
	});
	m5.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			panelPrincipal.removeAll();
			panelPrincipal.add(pr);
			panelPrincipal.updateUI();
			
		}
	});


		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ventanalogin = new VentanaLogin();
				frame.dispose();
				ventanalogin.setVisible(true);

			}
		});

		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});
		
		mapaReservas = new HashMap<>();
		ArrayList<Ciudad> listaCiudades = BD.obtenerTodasCiudades();
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		for (Ciudad c: listaCiudades) {
			if (!mapaReservas.containsKey(c)) {
				listaReservas = BD.obtenerReservasPorDestino(c.getId());
				mapaReservas.put(c, listaReservas);
			}
		}
		
		btnReservas = new JButton("RESERVAS POR DESTINO");
		panelSur.add(btnReservas);
		
		btnReservas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Ciudad c: mapaReservas.keySet()) {
					System.out.println("Reservas en "+c.getNombre());
					for(Reserva r: mapaReservas.get(c)) {
						System.out.println(r.toString());
					}
				}
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
	public VentanaAdministrador(TipoPersona tipo) {
		
		VentanaInicio vi = new VentanaInicio();
		vi.dispose();
		
		this.setVisible(true);
	}
}