package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paneles.PanelAnadirAlojamiento;
import paneles.PanelAnadirDestino;
import paneles.PanelAnadirExcursiones;
import paneles.PanelEliminarDestino;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
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
	private JLabel lblInfor, lblTitulo;
	// paneles
	public PanelAnadirDestino pad;
	public PanelEliminarDestino ped;
	public PanelAnadirAlojamiento paa;
	public PanelAnadirExcursiones pex;
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
		panelPrincipal.setLayout(new GridLayout(1, 0, 0, 0));

		panelIzq = new JPanel();
		panelIzq.setBackground(new Color(0, 255, 204));
		contentPane.add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new GridLayout(7, 0, 0, 0));
		// inicializacion de paneles
		pad = new PanelAnadirDestino();
		ped = new PanelEliminarDestino();
		paa = new PanelAnadirAlojamiento();
		pex = new PanelAnadirExcursiones();
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
		comboAn.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Opciones:", "Destino", "Alojamiento", "Excursiones" }));

		panelIzq.add(comboAn);
		// comboAn.setVisible(false);
		comboAn.setEnabled(false);

		/*
		 * nuevo
		 */

		btnAnadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboAn.setEnabled(true);
				comboAn.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if (e.getSource() == comboAn) {
							String item1 = comboAn.getSelectedItem().toString(); // seleciona el primero
							System.out.println(item1);
							if ("Destino".equals(item1)) {

								System.out.println("entra al boton");
								panelPrincipal.removeAll();
								panelPrincipal.add(pad);
								panelPrincipal.updateUI();
							} else if ("Alojamiento".equals(item1)) {
								panelPrincipal.removeAll();
								panelPrincipal.add(paa);
								panelPrincipal.updateUI();

							} else {
								panelPrincipal.removeAll();
								panelPrincipal.add(pex);
								panelPrincipal.updateUI();

							}

						}

					}
				});

			}

		});

		btnEliminar = new JButton("ELIMINAR");
		panelIzq.add(btnEliminar);

		comboEl = new JComboBox<>();
		comboEl.setModel(new DefaultComboBoxModel(new String[] { "Opciones:", "Destino", "Alojamiento", "Excursion" }));
		panelIzq.add(comboEl);

		comboEl.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				comboEl.setEnabled(true);
				comboEl.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if (e.getSource() == comboEl) {
							String item1 = comboEl.getSelectedItem().toString(); // seleciona el primero
							System.out.println(item1);
							if ("Destino".equals(item1)) {

								System.out.println("entra al boton");
								panelPrincipal.removeAll();
								panelPrincipal.add(ped);
								panelPrincipal.updateUI();
							} else if ("Alojamiento".equals(item1)) {
								panelPrincipal.removeAll();
								panelPrincipal.add(paa);
								panelPrincipal.updateUI();

							} else {
								panelPrincipal.removeAll();
								panelPrincipal.add(pex);
								panelPrincipal.updateUI();

							}

						}

					}
				});

			}
		});

		btnModificar = new JButton("MODIFICAR");
		panelIzq.add(btnModificar);

		comboMo = new JComboBox<>();
		comboMo.setModel(
				new DefaultComboBoxModel(new String[] { "Opciones:", "Destino", "Alojamiento", "Excursiones" }));
		panelIzq.add(comboMo);
		comboEl.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboMo.setEnabled(true);
				comboMo.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {

						if (e.getSource() == comboMo) {
							String item1 = comboMo.getSelectedItem().toString(); // seleciona el primero
							System.out.println(item1);
							if ("Destino".equals(item1)) {

								System.out.println("entra al boton");
								panelPrincipal.removeAll();
								// panelPrincipal.add(ped);
								panelPrincipal.updateUI();
							} else if ("Alojamiento".equals(item1)) {
								panelPrincipal.removeAll();
								// panelPrincipal.add(paa);
								panelPrincipal.updateUI();

							} else {
								panelPrincipal.removeAll();
								// panelPrincipal.add(pex);
								panelPrincipal.updateUI();

							}

						}

					}
				});

			}
		});

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
