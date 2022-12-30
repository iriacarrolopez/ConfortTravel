package paneles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bd.BD;

import clases.Ciudad;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;

public class PanelDestino extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// creaccion de la tabla
	private static DefaultTableModel modeloDestino;
	private static JTable tableDestino;
	private JScrollPane scrollPaneTablaDestino;

	private JPanel panelAbajo;
	private JButton btnInsertarDestino;
	private JButton btnEliminarDestino;
	private JButton btnModificarDestino;

	private JPanel panelArriba;
	private JLabel lblInfo;
//conexion con la base de datso
	private Connection con;

	private int mouseRow = -1;
	

	/**
	 * Create the panel.
	 */
	public PanelDestino() {
		// hacemos la conexion con la BD
				con = BD.initBD("confortTravel.db");
				BD.crearTablas(con);
				//BD.closeBD(con);
				//cargarModeloTabla();
		setBounds(100, 100, 900, 900);
		setBackground(new Color(176, 224, 230));
		setLayout(new BorderLayout(0, 0));

		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(1, 1, 0, 0));
		
		btnInsertarDestino = new JButton("NUEVO DESTINO");
		panelAbajo.add(btnInsertarDestino);
		
		btnEliminarDestino = new JButton("ELIMINAR DESTINO");
		panelAbajo.add(btnEliminarDestino);
		
		btnModificarDestino = new JButton("MODIFICAR DESTINO");
		panelAbajo.add(btnModificarDestino);

		inicializarTabla();
		// La tabla de destino se inserta en un panel con scroll
		scrollPaneTablaDestino = new JScrollPane(tableDestino);
		scrollPaneTablaDestino.setBorder(new TitledBorder("DESTINOS"));
		tableDestino.setFillsViewportHeight(true);
		add(scrollPaneTablaDestino, BorderLayout.CENTER);
		scrollPaneTablaDestino.setViewportView(tableDestino);

		panelArriba = new JPanel();
		panelArriba.setBackground(new Color(0, 153, 255));
		add(panelArriba, BorderLayout.NORTH);

		lblInfo = new JLabel("A\u00F1adir Nuevo Destino");
		lblInfo.setBackground(Color.WHITE);
		panelArriba.add(lblInfo);

		btnInsertarDestino.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String id = JOptionPane.showInputDialog("Introduce el id del destino:");
				String nombre = JOptionPane.showInputDialog("Introduce el nombre del destino:");
				Integer idD = Integer.parseInt(id);

				con = BD.initBD("confortTravel.db");

				cargarModeloTabla();
				BD.insertarDestino(con, idD, nombre);
				// Borramos el contenido del modelo de la tabla
				while (modeloDestino.getRowCount() > 0) {
					modeloDestino.removeRow(0);
				}
				cargarModeloTabla();
				// modeloDestino.addRow(new Object[]{id,nombre});
				BD.closeBD(con);
			}
		});
		btnEliminarDestino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				con = BD.initBD("confortTravel.db");
				int d = tableDestino.getSelectedRow();
				int id = (int) tableDestino.getValueAt(d, 0);
				BD.eliminarDestino(con, id);
				cargarModeloTabla();
				
			}
		});
		btnModificarDestino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				con = BD.initBD("confortTravel.db");
				String id = JOptionPane.showInputDialog("Introduce el id del destino a modificar:  ");
				String nombre = JOptionPane.showInputDialog("Introduce el nombre del destino: ");
				
				BD.obtenerDestinosPorId(Integer.parseInt(id));
				BD.UpdateNombreDestino(Integer.parseInt(id),nombre);
				
				while(modeloDestino.getRowCount()>0) {
					modeloDestino.removeRow(0);
				}
				cargarModeloTabla();
				//BD.closeBD(con);
			}
		});
		
		

	}

	private void inicializarTabla() {
		// Cabecera del modelo de datos
		Vector<String> cabeceraDestinos = new Vector<String>(Arrays.asList("ID", "NOMBRE"));
		modeloDestino = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraDestinos) {
			
			private static final long serialVersionUID = 1L;
};
		// Se crea la tabla de destinos con el modelo de datos
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
		// Se modifica el modelo de selecci�n de la tabla para que se pueda selecciona
		// �nicamente una fila
		tableDestino.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// nuevo
		// PINTAR LA FILA ENTERA

		tableDestino.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = new JLabel(value.toString());

				// Si la celda est� seleccionada se asocia un color de fondo y letra
				if (mouseRow == row) {
					label.setForeground(Color.RED);
					label.setBackground(Color.WHITE);// NO LO COGE
				}
				// Es necesaria esta sentencia para pintar correctamente el color de fondo
				label.setOpaque(true);

				return label;
			}
		});
		// Se define el comportamiento de los eventos de movimiento del rat�n: MOVED
		// DRAGGED
		tableDestino.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// Se obtiene la fila/columna sobre la que est�n el rat�n mientras se mueve
				int row = tableDestino.rowAtPoint(e.getPoint());

				// Cuando el rat�n se mueve sobre tabla, actualiza la fila/columna sobre la que
				// est� el rat�n
				// de esta forma se puede modificar el color de renderizado de la celda.
				mouseRow = row;

				// tengo que volver a repitar la tabla

				// Se fuerza el redibujado de la tabla para modificar el color de la celda sobre
				// la que está el ratón
				tableDestino.repaint();

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public static void cargarModeloTabla() {
		//Connection con = BD.initBD("confortTravel.db");

		try {

			ArrayList<Ciudad> listaCiudades = BD.obtenerTodasCiudades();
			while (modeloDestino.getRowCount() > 0)
				modeloDestino.removeRow(0);
			for (Ciudad c : listaCiudades) {
				Object fila[] = { c.getId(), c.getNombre() };
				// System.out.println(c.getDni());
				modeloDestino.addRow(fila);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//BD.closeBD(con);

	}

}
