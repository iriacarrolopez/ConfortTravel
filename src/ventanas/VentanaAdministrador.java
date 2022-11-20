package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paneles.PanelAnadirDestino;
import paneles.PanelEliminarDestino;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class VentanaAdministrador {
	private static JFrame frame;
	private JPanel contentPane, panelPrincipal, panelSur, panelIzq, panelNorte;
	private JComboBox<String> comboAn, comboEl, comboMo;
	private JButton btnAnadir, btnEliminar, btnModificar, btnVolver, btnSalir;
	public VentanaLogin ventanalogin;
	private JLabel lblInfor,lblTitulo ;
	// paneles
	public PanelAnadirDestino pad;
	public PanelEliminarDestino ped;
	/*
	 * PanelAnadirAlojamiento paa = new PanelAnaidirAlojamiento();
	 * PanelAnadirExcursiones pae= new PanelAnadirExcursiones();
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdministrador window = new VentanaAdministrador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {
		frame = new JFrame();
		frame.setTitle("Ventana Administrador");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(40, 50, 1010, 790);
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
		pad = new PanelAnadirDestino();
		ped = new PanelEliminarDestino();

		panelIzq = new JPanel();
		panelIzq.setBackground(new Color(0, 255, 204));
		contentPane.add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new GridLayout(7, 0, 0, 0));
		pad = new PanelAnadirDestino();
		ped = new PanelEliminarDestino();
//Etiquetas
		lblTitulo = new JLabel("Administrador");
		lblTitulo.setFont(new Font("Verdana Pro Cond Black", Font.BOLD | Font.ITALIC, 15));
		panelNorte.add(lblTitulo);

		lblInfor = new JLabel("Introduce que operaci\u00F3n desea realizar");
		lblInfor.setBackground(new Color(102, 153, 255));
		panelIzq.add(lblInfor);


//botones
		btnSalir = new JButton("Salir");
		panelSur.add(btnSalir);

		btnVolver = new JButton("Volver a la pagina principal");
		panelSur.add(btnVolver);


		btnAnadir = new JButton("AÑADIR");
		btnAnadir.setPreferredSize(new Dimension(5, 5));
		panelIzq.add(btnAnadir);

		comboAn = new JComboBox<>();
	
		comboAn.addItem("Añadir Destino ");
		comboAn.addItem("Añadir Alojamiento");
		comboAn.addItem("Añadir Excursiones");
		panelIzq.add(comboAn);
		//comboAn.setVisible(false);
		comboAn.setVisible(false);
		//listener del combo
		/*
		comboAn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboAn.setVisible(true);
				String itemSeleccionado = (String) comboAn.getSelectedItem();
				if ("Añadir Destino".equals(itemSeleccionado)) {
					System.out.println("ha seleccionado añadir destino");
					// visualizar el panel añadir x
					panelPrincipal.removeAll();// BORRO
					panelPrincipal.add(pad);
				
					panelPrincipal.updateUI();
				} else if ("Añadir Alojamiento ".equals(itemSeleccionado)) {
					// visualizar el panelañadir y
					System.out.println("entra al combo de alojamiento");
					panelPrincipal.removeAll();// BORRO
					// panelPrincipal.add(paa);
					panelPrincipal.updateUI();
				} else if ("Añadir Excursiones".equals(itemSeleccionado)) {
					// visualizar el panel añadir z
					panelPrincipal.removeAll();// BORRO
					// panelPrincipal.add(pae);
					panelPrincipal.updateUI();	
				
			}
		}
	});
		
		*/

		btnAnadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			System.out.println("entra al boton");
			panelPrincipal.removeAll();
			panelPrincipal.add(pad);
			panelPrincipal.updateUI();
			
				
			}

				
		});
		btnEliminar = new JButton("ELIMINAR");
		panelIzq.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelPrincipal.removeAll();
				panelPrincipal.add(ped);
				panelPrincipal.updateUI();

			}
		});

		comboEl = new JComboBox<>();
		panelIzq.add(comboEl);
		comboEl.addItem("Eliminar x ");
		comboEl.addItem("Eliminar y");
		comboEl.addItem("Eliminar y");
		comboEl.setVisible(false);

		btnModificar = new JButton("MODIFICAR");
		panelIzq.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboMo.setVisible(true);
				String itemSeleccionado = (String) comboMo.getSelectedItem();
				if ("Modificar x".equals(itemSeleccionado)) {
					// visualizar el panel añadir x

				} else if ("Modificar y".equals(itemSeleccionado)) {
					// visualizar el panelañadir y
				} else {
					// visualizar el panel añadir z
				}

			}
		});
		comboMo = new JComboBox<>();
		comboMo.addItem("Modificar x ");
		comboMo.addItem("Modificar y");
		comboMo.addItem("Modificar y");
		panelIzq.add(comboMo);
		comboMo.setVisible(false);

		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ventanalogin = new VentanaLogin();
				// ventanalogin.setVisible(true);
				frame.dispose();

			}
		});

		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});

	}
}
