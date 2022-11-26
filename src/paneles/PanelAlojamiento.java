package paneles;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bd.BD;
import clases.Alojamiento;
import clases.Destino;
import clases.TipoAlojamiento;

public class PanelAlojamiento extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaAlojamiento;
	private JPanel panelArriba, panelAbajo;
	private JLabel lblTitulo;
	private JButton btnInsertarAlojamiento;
	private static DefaultTableModel modeloAlojamiento;
	private JScrollPane scrollPaneAlojamiento;
	private Connection con;
	private JButton btnModificar;

	private int mouseRow = -1;
	private int mouseColumn = -1;
	private JButton btnEliminar;

	/**
	 * Create the panel.
	 */
	public PanelAlojamiento() {
		setLayout(new BorderLayout(0, 0));

		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);

		lblTitulo = new JLabel("ALOJAMIENTO");
		panelArriba.add(lblTitulo);

		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(0, 3, 0, 0));

		btnInsertarAlojamiento = new JButton("NUEVO ALOJAMIENTO");
		panelAbajo.add(btnInsertarAlojamiento);

		btnEliminar = new JButton("ELIMINAR ALOJAMIENTO");
		panelAbajo.add(btnEliminar);

		btnModificar = new JButton("MODIFICAR ALOJAMIENTO");
		panelAbajo.add(btnModificar);
		

		

		// La tabla de destino se inserta en un panel con scroll
inicializarTabla();
		
		scrollPaneAlojamiento = new JScrollPane(tablaAlojamiento);
		scrollPaneAlojamiento.setBorder(new TitledBorder("ALOJAMIENTOS"));
		tablaAlojamiento.setFillsViewportHeight(true);
		add(scrollPaneAlojamiento, BorderLayout.CENTER);
		scrollPaneAlojamiento.setViewportView(tablaAlojamiento);
		//inicializo la tabla
		
		
		btnInsertarAlojamiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = JOptionPane.showInputDialog("Introduce el id :");
				String nombre = JOptionPane.showInputDialog("Introduce el nombre:");

				String tipo = JOptionPane.showInputDialog("Introduce el tipo :");
				String precio = JOptionPane.showInputDialog("Introduce el precio :");
				String duracion = JOptionPane.showInputDialog("Introduce el duracion :");
				String destino = JOptionPane.showInputDialog("Introduce el destino :");
				Integer idA = Integer.parseInt(id);
				Float p =Float.parseFloat(precio);
				Integer d = Integer.parseInt(duracion);
				Integer des = Integer.parseInt(destino);
				con = BD.initBD("confortTravel.db");

				cargarModeloTabla();
				BD.insertarAlojamiento(con, idA, nombre, tipo, p,d, des);

				// Borramos el contenido del modelo de la tabla
				while (modeloAlojamiento.getRowCount() > 0) {
					modeloAlojamiento.removeRow(0);
				}
				cargarModeloTabla();
				
				BD.closeBD(con);
			}

		});
		/*
		 * Al hacer click en una fila se te borra
		 */
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				con = BD.initBD("confortTravel.db");
				int i = tablaAlojamiento.getSelectedRow();
				int id = (int) tablaAlojamiento.getValueAt(i, 0);
				BD.eliminarAlojamiento(con, id);
				// Borramos el contenido del modelo de la tabla
				while (modeloAlojamiento.getRowCount() > 0) {
					modeloAlojamiento.removeRow(0);
				}
				cargarModeloTabla();
				BD.closeBD(con);
			}
		});
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				con = BD.initBD("confortTravel.db");
				String id = JOptionPane.showInputDialog("Introduce el id  del alojamiento que sea modificar:");
				String precio = JOptionPane.showInputDialog("Introduce el precio:");
				String duracion = JOptionPane.showInputDialog("Introduce la duracion (dias):");
				
				//modificar
				BD.obtenerAlojamientosPorid(Integer.parseInt(id));
				BD.UpdatePrecioPorDuracion(Integer.parseInt(id), Float.parseFloat(precio),Integer.parseInt(duracion));
				// Borramos el contenido del modelo de la tabla
				cargarModeloTabla();
				while (modeloAlojamiento.getRowCount() > 0) {
					modeloAlojamiento.removeRow(0);
				}
				cargarModeloTabla();
				BD.closeBD(con);
				
				
				

			}
		});
		// hacemos la conexion con la BD
		Connection con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		BD.closeBD(con);
		cargarModeloTabla();

	}

	public void inicializarTabla() {
		// Cabecera del modelo de datos
		Vector<String> cabeceraAlojamiento = new Vector<String>(
				Arrays.asList("ID","NOMBRE", "TIPO ALOJAMIENTO", "PRECIO", "DURACION", "DESTINO"));
		// Se crea el modelo de datos para la tabla de comics sólo con la cabecera
		modeloAlojamiento = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAlojamiento);
		// Se crea la tabla de comics con el modelo de datos
		tablaAlojamiento = new JTable(modeloAlojamiento);
		// cargo el modelo
		
		cargarModeloTabla();

		tablaAlojamiento.setRowHeight(30);

		// Se cambia la anchura de las columnas
		tablaAlojamiento.getColumnModel().getColumn(0).setPreferredWidth(200);
		tablaAlojamiento.getColumnModel().getColumn(1).setPreferredWidth(300);
		tablaAlojamiento.getColumnModel().getColumn(2).setPreferredWidth(400);
		tablaAlojamiento.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablaAlojamiento.getColumnModel().getColumn(4).setPreferredWidth(200);
		tablaAlojamiento.getColumnModel().getColumn(5).setPreferredWidth(400);
		//no modificar las celdas de la tabla
		 modeloAlojamiento= new DefaultTableModel(){
				
				private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
				};
		// Se modifica el modelo de selección de la tabla para que se pueda selecciona
		// únicamente una fila
		tablaAlojamiento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	/*public void cargarModeloTabla() {
		Connection con = BD.initBD("confortTravel.db");

		try {

			ArrayList<Alojamiento> listaAlojamientos = BD.obtenerAlojamientos();
			while (modeloAlojamiento.getRowCount() > 0)
				modeloAlojamiento.removeRow(0);
			for (Alojamiento a : listaAlojamientos) {
				
				Object fila[] = { a.getId(), a.getNombre_comp(), a.getTalojamiento(), a.getPrecio(), a.getDuracion(),a.getDestinoNombre() };
				// System.out.println(c.getDni());
				modeloAlojamiento.addRow(fila);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.closeBD(con);

	}*/
	public void cargarModeloTabla() {
		Connection con = BD.initBD("confortTravel.db");

		try {

			ArrayList<Alojamiento> listaAlojamientos = BD.obtenerAlojamientos();
			while (modeloAlojamiento.getRowCount() > 0)
				modeloAlojamiento.removeRow(0);
			for (int i = 0; i < listaAlojamientos.size(); i++) {
				   Object[] fila = new Object[6];
				   fila[0]= listaAlojamientos.get(i).getId();
				   fila[1]= listaAlojamientos.get(i).getNombre_comp();
				   fila[2]= listaAlojamientos.get(i).getTalojamiento();
				   fila[3]= listaAlojamientos.get(i).getPrecio();
				   fila[4]= listaAlojamientos.get(i).getDuracion();
				   fila[5]= listaAlojamientos.get(i).getDestinoNombre();
				  
				   modeloAlojamiento.addRow(fila);
				   System.out.println("CARGANDO MODELO DE LA TABLA...");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.closeBD(con);

	}

}
