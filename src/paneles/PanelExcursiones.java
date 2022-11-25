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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bd.BD;
import clases.Alojamiento;
import clases.Destino;

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
	private Connection	con;

	/**
	 * Create the panel.
	 */
	public PanelExcursiones() {
		setLayout(new BorderLayout(0, 0));

		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);

		lblTitulo = new JLabel("AÑADIR EXCUSION");
		panelArriba.add(lblTitulo);

		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		
		 btnInsertarExcursion = new JButton("Nueva Excursion");
		panelAbajo.add(btnInsertarExcursion);
		
		btnInsertarExcursion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = JOptionPane.showInputDialog("Introduce el id :");
				String nombre =JOptionPane.showInputDialog("Introduce el nombre:");
				String tipo = JOptionPane.showInputDialog("Introduce el tipo :");
				String precio =JOptionPane.showInputDialog("Introduce el precio :");
				String duracion =JOptionPane.showInputDialog("Introduce el duracion :");
				String destino = JOptionPane.showInputDialog("Introduce el destino :");
				
				con = BD.initBD("confortTravel.db");
				
				cargarModeloTabla();
				BD.insertarAlojamiento(con,Integer.parseInt(id), nombre, tipo,Float.parseFloat(precio), Integer.parseInt(duracion), Integer.parseInt(destino));
				
				//Borramos el contenido del modelo de la tabla
				while(modeloExcursion.getRowCount()>0) {
					modeloExcursion.removeRow(0);
				}
				cargarModeloTabla();
				//modeloDestino.addRow(new Object[]{id,nombre});
				BD.closeBD(con);
				}
				
				
			
		});
		
		
		// La tabla de destino se inserta en un panel con scroll
		
		
		tablaExcursion = new JTable();
		scrollPaneExcursion = new JScrollPane(tablaExcursion);
		scrollPaneExcursion.setBorder(new TitledBorder("Excursion"));
		tablaExcursion.setFillsViewportHeight(true);
		add(scrollPaneExcursion, BorderLayout.CENTER);
		scrollPaneExcursion.setViewportView(tablaExcursion);
		inicializarTabla();
		// hacemos la conexion con la BD
				Connection con = BD.initBD("confortTravel.db");
				BD.crearTablas(con);
				BD.closeBD(con);
				cargarModeloTabla();

	}
	
	
	
	
	
	
	
	
	public void inicializarTabla() {
		// Cabecera del modelo de datos
		Vector<String> cabeceraAlojamiento = new Vector<String>(Arrays.asList("NOMBRE","TIPO ALOJAMIENTO","PRECIO","DURACION","DESTINO"));
		// Se crea el modelo de datos para la tabla de comics sólo con la cabecera
		modeloExcursion = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAlojamiento);
		// Se crea la tabla de comics con el modelo de datos
		tablaExcursion= new JTable(modeloExcursion);
		//cargo el modelo
		cargarModeloTabla();
		 
		tablaExcursion.setRowHeight(30);
		

		// Se cambia la anchura de las columnas
		tablaExcursion.getColumnModel().getColumn(0).setPreferredWidth(400);
		tablaExcursion.getColumnModel().getColumn(1).setPreferredWidth(400);
		
		
			// Se modifica el modelo de selección de la tabla para que se pueda selecciona
			// únicamente una fila
		tablaExcursion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
	public void cargarModeloTabla() {
		/*Connection con = BD.initBD("confortTravel.db");

		try {

			//ArrayList<Alojamientos> listaAlojamientos= BD.obtenerAlojamientos();
			while (modeloAlojamiento.getRowCount() > 0)
				modeloAlojamiento.removeRow(0);
			for (Alojamiento a : listaAlojaminetos) {
				Object fila[] = { a.getId(), a.getNombre_comp(),a.getTalojamiento(),a.getPrecio(),a.getDuracion(),a.getDestinoNombre() };
				// System.out.println(c.getDni());
				modeloAlojamiento.addRow(fila);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.closeBD(con);*/

	}
	/*public static void eliminarFilaAlojamientoDeLaTabla() {
		int rowCount = modeloAlojamiento.getRowCount();
		// Recorre y elimina la fila en la que esta
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloAlojamiento.removeRow(i);
		}

	}*/
		

}

