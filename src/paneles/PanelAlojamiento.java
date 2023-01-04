package paneles;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;

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
import clases.Ciudad;
import clases.TipoAlojamiento;


public class PanelAlojamiento extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaAlojamiento;
	private JPanel panelArriba, panelAbajo;
	private JLabel lblTitulo;
	private DefaultTableModel modeloAlojamiento;
	private JScrollPane scrollPaneAlojamiento;
	private Connection con;
	private JButton btnInsertarAlojamiento, btnEliminar, btnModificar;

	
	private JLabel lblFiltro;
	//private JTextField textField;

int mouserow =-1;
int mouseColumn =-1;
	/**
	 * Create the panel.
	 */
	public PanelAlojamiento() {
		// hacemos la conexion con la BD
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
				//BD.closeBD(con);
				//cargarModeloTabla();

		setLayout(new BorderLayout(0, 0));

		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);
		panelArriba.setLayout(new GridLayout(0, 4, 0, 0));
		
		/*lblTitulo = new JLabel("ALOJAMIENTO");
		panelArriba.add(lblTitulo);
		
		lblFiltro = new JLabel("Tipo de Alojamiento");
		panelArriba.add(lblFiltro);
		
		textField = new JTextField();
		panelArriba.add(textField);*/

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
		tablaAlojamiento.updateUI();
		
		scrollPaneAlojamiento = new JScrollPane(tablaAlojamiento);
		scrollPaneAlojamiento.setBorder(new TitledBorder("ALOJAMIENTOS"));
		tablaAlojamiento.setFillsViewportHeight(true);
		add(scrollPaneAlojamiento, BorderLayout.CENTER);
		scrollPaneAlojamiento.setViewportView(tablaAlojamiento);
		
		
		
		tablaAlojamiento.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				TipoAlojamiento ta = (TipoAlojamiento) modeloAlojamiento.getValueAt(row, 2);
				if(ta.equals(TipoAlojamiento.HOTEL)) {
					c.setBackground(Color.lightGray);
					
				}else if(ta.equals(TipoAlojamiento.APARTAMENTO)) {
					c.setBackground(Color.cyan);
				}else if (ta.equals(TipoAlojamiento.valueOf("VILLA"))) {
					c.setBackground(Color.white);
					
				}else if(ta.equals(TipoAlojamiento.valueOf("BUNGALO"))) {
					c.setBackground(Color.green);
					
				}else {
					c.setBackground(Color.orange);
				}
				// TODO Auto-generated method stub
				return c;
			}
		});
		
		btnInsertarAlojamiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = JOptionPane.showInputDialog("Introduce el id :");
				String nombre = JOptionPane.showInputDialog("Introduce el nombre:");
				String tipo = JOptionPane.showInputDialog("Introduce el tipo :");
				String precio = JOptionPane.showInputDialog("Introduce el precio :");
				String duracion = JOptionPane.showInputDialog("Introduce el duracion :");
				String destino = JOptionPane.showInputDialog("Introduce el destino(nombre) :");
				
				Ciudad c = new Ciudad();
				 c = BD.getCiudadByNombre(destino);
				Integer des = c.getId();
				
				Integer idA = Integer.parseInt(id);
				Float p = Float.parseFloat(precio);
				Integer d = Integer.parseInt(duracion);
			//	Integer idD = Integer.parseInt(destino);
				//c
				//Ciudad destinoD = BD.obtenerIdDestino(destino);
			
				con = BD.initBD("confortTravel.db");
				BD.insertarAlojamiento(con, idA, nombre, tipo, p,d,des);
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
				int d = tablaAlojamiento.getSelectedRow();
				int id = (int) tablaAlojamiento.getValueAt(d, 0);
				BD.eliminarAlojamiento(con, id);
				// Borramos el contenido del modelo de la tabla
				cargarModeloTabla();
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = JOptionPane.showInputDialog("Introduce el id  del alojamiento que sea modificar:");
				String precio = JOptionPane.showInputDialog("Introduce el precio:");
				String duracion = JOptionPane.showInputDialog("Introduce la duracion (dias):");
				
				//modificar
				BD.obtenerAlojamientosPorid(Integer.parseInt(id));
				BD.UpdatePrecioPorDuracion(Integer.parseInt(id), Float.parseFloat(precio),Integer.parseInt(duracion));
				// Borramos el contenido del modelo de la tabla
				while (modeloAlojamiento.getRowCount() > 0) {
					modeloAlojamiento.removeRow(0);
				}
				cargarModeloTabla();
				
			}
		});
		
	}

	public void inicializarTabla() {
		// Cabecera del modelo de datos
		Vector<String> cabeceraAlojamiento = new Vector<String>(Arrays.asList("ID","NOMBRE", "TIPO ALOJAMIENTO", "PRECIO", "DURACION", "DESTINO"));
		modeloAlojamiento = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAlojamiento) {
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
		// Se crea la tabla de alojamiento con el modelo de datos
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
	
		// Se modifica el modelo de selecci�n de la tabla para que se pueda selecciona
		// �nicamente una fila
		tablaAlojamiento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	
	public void cargarModeloTabla() {
		
		//Connection con = BD.initBD("confortTravel.db");
		try {

			ArrayList<Alojamiento> listaAlojamientos = BD.obtenerAlojamientos();
			System.out.println("Número de alojamientos: "+listaAlojamientos.size());
			while (modeloAlojamiento.getRowCount() > 0) {
				modeloAlojamiento.removeRow(0);
			}
			for(Alojamiento a: listaAlojamientos) {
				Object [] fila = {a.getId(),a.getNombre_comp(),a.getTalojamiento(),a.getPrecio(),a.getDuracion(),a.getCiudad().getNombre()};
				modeloAlojamiento.addRow(fila);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//BD.closeBD(con);

	}

}
