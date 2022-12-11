package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.TipoPersona;
import paneles.PanelAlojamiento;
import paneles.PanelDestino;
import paneles.PanelExcursiones;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component.BaselineResizeBehavior;
import javax.swing.UIManager;

public class VentanaAdministrador {
	private static JFrame frame;
	private JPanel contentPane, panelPrincipal, panelSur, panelIzq, panelNorte;
	private JComboBox<String> comboAn, comboEl, comboMo;
	private JButton btnAnadir, btnEliminar, btnModificar, btnVolver, btnSalir;
	public VentanaLogin ventanalogin;
	private JLabel lblInfor, lblTitulo;
	// paneles
	public PanelDestino pad;
	public PanelAlojamiento paa;
	public PanelExcursiones pex;
	private JPanel panelInformacion;
	private JLabel icono;
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
		panelIzq.setBackground(Color.WHITE);
		contentPane.add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new GridLayout(2, 0, 0, 0));
		// inicializacion de paneles
		pad = new PanelDestino();
		paa = new PanelAlojamiento();
		pex = new PanelExcursiones();
//Etiquetas
		lblTitulo = new JLabel("Administrador");
		lblTitulo.setFont(new Font("Verdana Pro Cond Black", Font.BOLD | Font.ITALIC, 15));
		panelNorte.add(lblTitulo);

		panelInformacion = new JPanel();
		panelInformacion.setBackground(Color.BLACK);
		panelIzq.add(panelInformacion);
		panelInformacion.setLayout(new GridLayout(2, 0, 0, 0));
		icono = new JLabel();
		icono.setHorizontalAlignment(SwingConstants.CENTER); 
		icono.setSize(50, 50);
		icono.setIcon(new ImageIcon(VentanaAdministrador.class.getResource("/imagenes/administrador.png")));
		

		panelIzq.add(icono);
		lblInfor = new JLabel("Introduce que operaci\u00F3n desea realizar");
		lblInfor.setForeground(Color.WHITE);
		lblInfor.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelInformacion.add(lblInfor);
		lblInfor.setBackground(UIManager.getColor("Button.foreground"));

		comboAn = new JComboBox<>();
		comboAn.setBackground(UIManager.getColor("CheckBox.background"));
		comboAn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelInformacion.add(comboAn);
		comboAn.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Opciones:", "Destino", "Alojamiento", "Excursiones" }));
		// comboAn.setVisible(false);
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

//botones
		btnSalir = new JButton("Salir");
		panelSur.add(btnSalir);

		btnVolver = new JButton("Volver a la pagina principal");
		panelSur.add(btnVolver);

		/*
		 * nuevo
		 */

		/*
		 * btnAnadir.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * comboAn.setEnabled(true); comboAn.addItemListener(new ItemListener() {
		 * 
		 * @Override public void itemStateChanged(ItemEvent e) { // TODO Auto-generated
		 * method stub if (e.getSource() == comboAn) { String item1 =
		 * comboAn.getSelectedItem().toString(); // seleciona el primero
		 * System.out.println(item1); if ("Destino".equals(item1)) {
		 * 
		 * System.out.println("entra al boton"); panelPrincipal.removeAll();
		 * panelPrincipal.add(pad); panelPrincipal.updateUI(); } else if
		 * ("Alojamiento".equals(item1)) { panelPrincipal.removeAll();
		 * panelPrincipal.add(paa); panelPrincipal.updateUI();
		 * 
		 * } else { panelPrincipal.removeAll(); panelPrincipal.add(pex);
		 * panelPrincipal.updateUI();
		 * 
		 * }
		 * 
		 * }
		 * 
		 * } });
		 * 
		 * }
		 * 
		 * });
		 */

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
	public VentanaAdministrador(TipoPersona tipo) {
		VentanaInicio vi = new VentanaInicio();
		vi.dispose();
		frame.setVisible(true);
	}
}
