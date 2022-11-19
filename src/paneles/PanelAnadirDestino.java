package paneles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bd.BD;

import clases.Destino;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;

public class PanelAnadirDestino extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// creaccion de la tabla
	private static DefaultTableModel modeloDestino;
	private static JTable tableDestino;
	private JLabel lblMensaje;
	private JScrollPane scrollPaneTablaDestino;

	private JPanel panelAbajo;
	JButton btnInsertarDestino;

	private JPanel panelArriba;
	private JLabel lblInfo;
//conexion con la base de datso
	private Connection con;

	/**
	 * Create the panel.
	 */
	public PanelAnadirDestino() {
		setBounds(100, 100, 900, 900);
		setBackground(new Color(176, 224, 230));
		setLayout(new BorderLayout(0, 0));

		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(0, 2, 0, 0));

		lblMensaje = new JLabel("Introduce el nuevo destino:");
		panelAbajo.add(lblMensaje);

		JButton btnInsertarDestino = new JButton("NUEVO DESTINO");
		panelAbajo.add(btnInsertarDestino);

		inicializarTabla();
		// La tabla de destino se inserta en un panel con scroll
		scrollPaneTablaDestino = new JScrollPane(tableDestino);
		scrollPaneTablaDestino.setBorder(new TitledBorder("DESTINOS"));
		tableDestino.setFillsViewportHeight(true);
		add(scrollPaneTablaDestino, BorderLayout.CENTER);

		panelArriba = new JPanel();
		panelArriba.setBackground(new Color(0, 153, 255));
		add(panelArriba, BorderLayout.NORTH);

		lblInfo = new JLabel("A\u00F1adir Nuevo Destino");
		lblInfo.setBackground(Color.WHITE);
		panelArriba.add(lblInfo);

		// hacemos la conexion con la BD
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		BD.closeBD(con);
		cargarModeloTabla();

	}

	private void inicializarTabla() {
		// Cabecera del modelo de datos
		Vector<String> cabeceraDestinos = new Vector<String>(Arrays.asList("ID", "NOMBRE"));
		// Se crea el modelo de datos para la tabla de comics sólo con la cabecera
		modeloDestino = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraDestinos);
		// Se crea la tabla de comics con el modelo de datos
		tableDestino = new JTable(modeloDestino);

		cargarModeloTabla();
		
		// Se cambia la altura de las filas
		tableDestino.setRowHeight(30);
		// MODIFICAR LAS COLUMNAS

		// Se cambia la anchura de las columnas
		tableDestino.getColumnModel().getColumn(0).setPreferredWidth(400);
		tableDestino.getColumnModel().getColumn(1).setPreferredWidth(400);
		
		/*
		 * 
		 * PARA SELECIONAR SOLO UNA FILA
		 * 
		 * 
		 */
		// Se modifica el modelo de selección de la tabla para que se pueda selecciona
		// únicamente una fila
		tableDestino.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	public static void eliminarFilaDestinoDeLaTabla() {
		int rowCount = modeloDestino.getRowCount();
		// Recorre y elimina la fila en la que esta
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloDestino.removeRow(i);
		}

	}

	public static void cargarModeloTabla() {
		Connection con = BD.initBD("BaseDeDatos.db");

		try {

			ArrayList<Destino> listaDestinos = BD.obtenerDestinos();
			while (modeloDestino.getRowCount() > 0)
				modeloDestino.removeRow(0);
			for (Destino d : listaDestinos) {
				Object fila[] = { d.getId(), d.getNombre() };
				// System.out.println(c.getDni());
				modeloDestino.addRow(fila);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.closeBD(con);

	}

}
