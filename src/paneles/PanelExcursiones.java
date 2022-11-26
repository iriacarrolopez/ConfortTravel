package paneles;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bd.BD;
import clases.Alojamiento;
import clases.Destino;
import clases.Excursion;
import clases.TipoAlojamiento;
import clases.TipoExcursion;

import javax.swing.JTextField;

public class PanelExcursiones extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaExcursion;
	private JPanel panelArriba, panelAbajo;
	private JLabel lblTitulo;
	private JButton btnInsertarExcursion;
	private static DefaultTableModel modeloExcursion;
	private JScrollPane scrollPaneExcursion;
	private Connection con;
	private JButton btnEliminar;
	private JButton btnModificar;

	/**
	 * Create the panel.
	 */
	public PanelExcursiones() {
		setLayout(new BorderLayout(0, 0));

		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);

		lblTitulo = new JLabel(" EXCURSI\u00D3N");
		panelArriba.add(lblTitulo);

		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(0, 1, 0, 0));

		btnInsertarExcursion = new JButton("NUEVA EXCURSI\u00D3N");
		panelAbajo.add(btnInsertarExcursion);

		btnEliminar = new JButton("ELIMINAR EXCURSI\u00D3N");
		panelAbajo.add(btnEliminar);

		btnModificar = new JButton("MODIFICAR EXCURSI\u00D3N");
		panelAbajo.add(btnModificar);

		btnInsertarExcursion.addActionListener(new ActionListener() {
			// "CREATE TABLE IF NOT EXISTS Excursion (id Integer, nombre String, tipo
			// String,lugar String, precio Float, numPersonas Integer)";
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = JOptionPane.showInputDialog("Introduce el id :");
				String nombre = JOptionPane.showInputDialog("Introduce el nombre:");
				String tipo = JOptionPane.showInputDialog("Introduce el tipo :");
				String lugar = JOptionPane.showInputDialog("Introduce el lugar :");
				String precio = JOptionPane.showInputDialog("Introduce el precio :");
				String duracion = JOptionPane.showInputDialog("Introduce el duracion :");
				String num = JOptionPane.showInputDialog("Introduce el numPersonas:");

				con = BD.initBD("confortTravel.db");

				cargarModeloTabla();
				BD.insertarExcursion(con, Integer.parseInt(id), nombre, tipo, lugar, Float.parseFloat(precio),
						Integer.parseInt(duracion), Integer.parseInt(num));
				// Borramos el contenido del modelo de la tabla
				while (modeloExcursion.getRowCount() > 0) {
					modeloExcursion.removeRow(0);
				}
				cargarModeloTabla();
				// modeloDestino.addRow(new Object[]{id,nombre});
				BD.closeBD(con);
			}

		});
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				con = BD.initBD("confortTravel.db");
				int i = tablaExcursion.getSelectedRow();
				int id = (int) tablaExcursion.getValueAt(i, 0);
				
				BD.EliminarExcursion(con, id);
				// Borramos el contenido del modelo de la tabla
				while (modeloExcursion.getRowCount() > 0) {
					modeloExcursion.removeRow(0);
				}
				cargarModeloTabla();
				BD.closeBD(con);

			}
		});
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				con = BD.initBD("confortTravel.db");
				JOptionPane.showConfirmDialog(null,
						"SOLO ESTA PERMITIDO MODIFICAR EL NUMERO DE PERSONAS QUE PUEDEN PARTICIPAR EN LAS EXCURSIONES",
						"CLOSED_OPTION", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);

				String id = JOptionPane.showInputDialog("Introduzca el id:");
				String num = JOptionPane.showInputDialog("Nuevo numero de personas que pueden participar:");

				
				
				BD.UpdateNumeroPersonasEnExcursion(Integer.parseInt(id), Integer.parseInt(num));
				cargarModeloTabla();
				// Borramos el contenido del modelo de la tabla
				while (modeloExcursion.getRowCount() > 0) {
					modeloExcursion.removeRow(0);
				}
				cargarModeloTabla();
				BD.closeBD(con);

			}
		});

		// La tabla de destino se inserta en un panel con scroll

		inicializarTabla();

		scrollPaneExcursion = new JScrollPane(tablaExcursion);
		scrollPaneExcursion.setBorder(new TitledBorder("Excursion"));
		tablaExcursion.setFillsViewportHeight(true);
		add(scrollPaneExcursion, BorderLayout.CENTER);
		scrollPaneExcursion.setViewportView(tablaExcursion);

		// hacemos la conexion con la BD
		Connection con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		BD.closeBD(con);
		cargarModeloTabla();

	}

	public void inicializarTabla() {
		// Cabecera del modelo de datos
		Vector<String> cabeceraAlojamiento = new Vector<String>(
				Arrays.asList("ID", "NOMBRE", "TIPO EXCUSION", "LUGAR", "PRECIO", "DURACION", "NUMERO DE PERSONAS"));
		// Se crea el modelo de datos para la tabla de comics sólo con la cabecera
		modeloExcursion = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAlojamiento);
		// Se crea la tabla de comics con el modelo de datos
		tablaExcursion = new JTable(modeloExcursion);

		// cargo el modelo
		cargarModeloTabla();

		tablaExcursion.setRowHeight(30);

		// Se cambia la anchura de las columnas
		tablaExcursion.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaExcursion.getColumnModel().getColumn(1).setPreferredWidth(300);
		tablaExcursion.getColumnModel().getColumn(2).setPreferredWidth(300);
		tablaExcursion.getColumnModel().getColumn(3).setPreferredWidth(300);
		tablaExcursion.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaExcursion.getColumnModel().getColumn(5).setPreferredWidth(100);
		tablaExcursion.getColumnModel().getColumn(6).setPreferredWidth(300);
		// para no modificar a mano las celdas
		modeloExcursion = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};

	}

	// Excursion(Integer id,String nombre, TipoExcursion tipo, Destino lugar, Float
	// precio,Integer duracion, Integer numPersonas
	public void cargarModeloTabla() {
		Connection con = BD.initBD("confortTravel.db");

		try {

			ArrayList<Excursion> listaExcursiones = BD.obtenerExcursiones();
			while (modeloExcursion.getRowCount() > 0)
				modeloExcursion.removeRow(0);
			for (Excursion ex : listaExcursiones) {
				Object fila[] = { ex.getId(), ex.getNombre(), ex.getTipo(), ex.getLugarNombre(), ex.getPrecio(),
						ex.getDuracion(), ex.getNumPersonas() };
				// System.out.println(c.getDni());
				modeloExcursion.addRow(fila);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.closeBD(con);

	}
	/*
	 * // Se modifica el modelo de selección de la tabla para que se pueda
	 * selecciona // únicamente una fila
	 * tablaExcursion.setDefaultRenderer(Object.class, new TableCellRenderer() {
	 * 
	 * @Override public Component getTableCellRendererComponent(JTable table, Object
	 * value, boolean isSelected, boolean hasFocus, int row, int column) { // TODO
	 * Auto-generated method stub JLabel L = new JLabel(value.toString()); //codigo
	 * return L; } });
	 */

}
