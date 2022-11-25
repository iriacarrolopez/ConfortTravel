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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bd.BD;
import clases.Alojamiento;
import clases.Destino;

public class PanelAnadirAlojamiento extends JPanel {
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
	private Connection	con;
	
	private int mouseRow =-1;
	private int mouseColumn =-1;
	

	/**
	 * Create the panel.
	 */
	public PanelAnadirAlojamiento() {
		setLayout(new BorderLayout(0, 0));

		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);

		lblTitulo = new JLabel("AÑADIR ALOJAMIENTO");
		panelArriba.add(lblTitulo);

		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		
		 btnInsertarAlojamiento = new JButton("NUEVO ALOJAMIENTO");
		panelAbajo.add(btnInsertarAlojamiento);
		
		btnInsertarAlojamiento.addActionListener(new ActionListener() {
			
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
				BD.insertarAlojamiento(con,Integer.parseInt(id), nombre, tipo,Float.parseFloat(precio), Integer.parseInt(duracion), destino);
				
				//Borramos el contenido del modelo de la tabla
				while(modeloAlojamiento.getRowCount()>0) {
					modeloAlojamiento.removeRow(0);
				}
				cargarModeloTabla();
				//modeloDestino.addRow(new Object[]{id,nombre});
				BD.closeBD(con);
				}
				
				
			
		});
		
		
		// La tabla de destino se inserta en un panel con scroll
		
		
		tablaAlojamiento = new JTable();
		scrollPaneAlojamiento = new JScrollPane(tablaAlojamiento);
		scrollPaneAlojamiento.setBorder(new TitledBorder("ALOJAMIENTOS"));
		tablaAlojamiento.setFillsViewportHeight(true);
		add(scrollPaneAlojamiento, BorderLayout.CENTER);
		scrollPaneAlojamiento.setViewportView(tablaAlojamiento);
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
		modeloAlojamiento = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAlojamiento);
		// Se crea la tabla de comics con el modelo de datos
		tablaAlojamiento= new JTable(modeloAlojamiento);
		//cargo el modelo
		cargarModeloTabla();
		 
		tablaAlojamiento.setRowHeight(30);
		

		// Se cambia la anchura de las columnas
		tablaAlojamiento.getColumnModel().getColumn(0).setPreferredWidth(400);
		tablaAlojamiento.getColumnModel().getColumn(1).setPreferredWidth(400);
		
		
			// Se modifica el modelo de selección de la tabla para que se pueda selecciona
			// únicamente una fila
			tablaAlojamiento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		
			
			

	}
	public void cargarModeloTabla() {
		Connection con = BD.initBD("confortTravel.db");

		try {

			ArrayList<Alojamiento> listaAlojamientos= BD.obtenerAlojamientos();
			while (modeloAlojamiento.getRowCount() > 0)
				modeloAlojamiento.removeRow(0);
			for (Alojamiento a : listaAlojamientos) {
				Object fila[] = { a.getId(), a.getNombre_comp(),a.getTalojamiento(),a.getPrecio(),a.getDuracion(),a.getDestinoNombre() };
				// System.out.println(c.getDni());
				modeloAlojamiento.addRow(fila);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.closeBD(con);

	}
	/*public static void eliminarFilaAlojamientoDeLaTabla() {
		int rowCount = modeloAlojamiento.getRowCount();
		// Recorre y elimina la fila en la que esta
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloAlojamiento.removeRow(i);
		}

	}*/
		

}
