package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paneles.PanelAnadirDestino;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;

public class VentanaAdministrador {
	private static JFrame frame;
	private JPanel contentPane, panelPrincipal;
	private JComboBox<String> comboAn, comboEl, comboMo;
	private JButton btnAnadir, btnEliminar, btnModificar;
	public VentanaLogin ventanalogin;
	//paneles
	PanelAnadirDestino pad = new PanelAnadirDestino() ;
	/*PanelAnadirAlojamiento paa = new PanelAnaidirAlojamiento();
	PanelAnadirExcursiones pae= new PanelAnadirExcursiones();*/

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
		frame.setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
//		paneles

		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Administrador");
		lblTitulo.setFont(new Font("Verdana Pro Cond Black", Font.BOLD | Font.ITALIC, 15));
		panelNorte.add(lblTitulo);

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		JButton btnSalir = new JButton("Salir");
		panelSur.add(btnSalir);

		JButton btnVolver = new JButton("Volver a la pagina principal");
		panelSur.add(btnVolver);

		JPanel panelIzq = new JPanel();
		contentPane.add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new GridLayout(7, 0, 0, 0));

		JLabel lblInfor = new JLabel("Introduce que operaci\u00F3n desea realizar");
		panelIzq.add(lblInfor);
//		paneles

// panel que va a cambiar dependiedo que opcion elija el usuario		 
		panelPrincipal = new JPanel();
		contentPane.add(panelPrincipal, BorderLayout.CENTER);

		btnAnadir = new JButton("AÑADIR");
		btnAnadir.setPreferredSize(new Dimension(5, 5));
		panelIzq.add(btnAnadir);

		comboAn = new JComboBox<>();
		comboAn.addItem("Añadir Destino ");
		comboAn.addItem("Añadir Alojamiento");
		comboAn.addItem("Añadir Excursiones");
		panelIzq.add(comboAn);
		comboAn.setVisible(false);

		btnAnadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				comboAn.setVisible(true);
				String itemSeleccionado = (String) comboAn.getSelectedItem();
				if ("Añadir Destino".equals(itemSeleccionado)) {
					// visualizar el panel añadir x
					panelPrincipal.removeAll();//BORRO
					panelPrincipal.add(pad); //no funciona
					panelPrincipal.updateUI();

				} else if ("Añadir Alojamiento ".equals(itemSeleccionado)) {
					// visualizar el panelañadir y
					panelPrincipal.removeAll();//BORRO
				//	panelPrincipal.add(paa);
					panelPrincipal.updateUI();
				} else if("Añadir Excursiones".equals(itemSeleccionado)){
					// visualizar el panel añadir z
					panelPrincipal.removeAll();//BORRO
				//	panelPrincipal.add(pae);
					panelPrincipal.updateUI();
				}

			}
		});
		btnEliminar = new JButton("ELIMINAR");
		panelIzq.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboEl.setVisible(true);

				String itemSeleccionado = (String) comboEl.getSelectedItem();
				if ("Eliminar x".equals(itemSeleccionado)) {
					// visualizar el panel añadir x

				} else if ("Eliminar y".equals(itemSeleccionado)) {
					// visualizar el panelañadir y
				} else {
					// visualizar el panel añadir z
				}

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
