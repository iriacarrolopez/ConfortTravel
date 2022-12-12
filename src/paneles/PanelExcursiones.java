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

import clases.Excursion;


public class PanelExcursiones extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaExcursion;
	private JPanel panelArriba, panelAbajo;
	private JLabel lblTitulo;
	private DefaultTableModel modeloExcursion;
	private JScrollPane scrollPaneExcursion;
	private Connection con;
	private JButton btnInsertarExcursion, btnEliminar, btnModificar;

	


	/**
	 * Create the panel.
	 */
	public PanelExcursiones() {
		// hacemos la conexion con la BD
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
				//BD.closeBD(con);
				//cargarModeloTabla();

		setLayout(new BorderLayout(0, 0));

		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);
		panelArriba.setLayout(new GridLayout(0, 4, 0, 0));
		
		lblTitulo = new JLabel("Excursion");
		panelArriba.add(lblTitulo);
		
		

		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(0, 3, 0, 0));

		btnInsertarExcursion = new JButton("NUEVO EXCURSION");
		panelAbajo.add(btnInsertarExcursion);

		btnEliminar = new JButton("ELIMINAR EXCURSION");
		panelAbajo.add(btnEliminar);

		btnModificar = new JButton("MODIFICAR EXCURSION");
		panelAbajo.add(btnModificar);
		

		

		// La tabla de destino se inserta en un panel con scroll
		inicializarTabla();
		tablaExcursion.updateUI();
		
		scrollPaneExcursion = new JScrollPane(tablaExcursion);
		scrollPaneExcursion.setBorder(new TitledBorder("EXCURSIONES"));
		tablaExcursion.setFillsViewportHeight(true);
		add(scrollPaneExcursion, BorderLayout.CENTER);
		scrollPaneExcursion.setViewportView(tablaExcursion);
		
		
		
		
		
		btnInsertarExcursion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				String id = JOptionPane.showInputDialog("Introduce el id :");
				String nombre = JOptionPane.showInputDialog("Introduce el nombre:");
				String tipo = JOptionPane.showInputDialog("Introduce el tipo :");
				String lugar = JOptionPane.showInputDialog("Introduce el lugar :");
				String precio = JOptionPane.showInputDialog("Introduce el precio :");
				String duracion = JOptionPane.showInputDialog("Introduce el duracion :");
				String num = JOptionPane.showInputDialog("Introduce el numPersonas:");
				
				
				BD.insertarExcursion(con, Integer.parseInt(id), nombre, tipo, lugar, Float.parseFloat(precio),
						Integer.parseInt(duracion), Integer.parseInt(num));

				// Borramos el contenido del modelo de la tabla
				while (modeloExcursion.getRowCount() > 0) {
					modeloExcursion.removeRow(0);
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
				String id = JOptionPane.showInputDialog("inserta el id de la excursion");
				Integer idE =Integer.parseInt(id);
				
				BD.EliminarExcursion(con, idE);
				// Borramos el contenido del modelo de la tabla
				
				cargarModeloTabla();
			}
		});
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(null,
						"SOLO ESTA PERMITIDO MODIFICAR EL NUMERO DE PERSONAS QUE PUEDEN PARTICIPAR EN LAS EXCURSIONES",
						"CLOSED_OPTION", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);

				String id = JOptionPane.showInputDialog("Introduzca el id:");
				String num = JOptionPane.showInputDialog("Nuevo numero de personas que pueden participar:");

				//modificar
				BD.obtenerDatosExcursion( Integer.parseInt(id));
				BD.UpdateNumeroPersonasEnExcursion(Integer.parseInt(id), Integer.parseInt(num));
				// Borramos el contenido del modelo de la tabla
				while (modeloExcursion.getRowCount() > 0) {
					modeloExcursion.removeRow(0);
				}
				cargarModeloTabla();
				
			}
		});
		
	}

	public void inicializarTabla() {
		// Cabecera del modelo de datos
		Vector<String> cabeceraExcursion = new Vector<String>(
				Arrays.asList("ID", "NOMBRE", "TIPO EXCUSION", "LUGAR", "PRECIO", "DURACION(HORAS)", "NUMERO DE PERSONAS"));
		// Se crea el modelo de datos para la tabla de comics s�lo con la cabecera
		modeloExcursion = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraExcursion) {
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
		// Se crea la tabla de comics con el modelo de datos
		tablaExcursion= new JTable(modeloExcursion);
		// cargo el modelo
		
		cargarModeloTabla();

		tablaExcursion.setRowHeight(40);

		// Se cambia la anchura de las columnas
				tablaExcursion.getColumnModel().getColumn(0).setPreferredWidth(100);
				tablaExcursion.getColumnModel().getColumn(1).setPreferredWidth(300);
				tablaExcursion.getColumnModel().getColumn(2).setPreferredWidth(300);
				tablaExcursion.getColumnModel().getColumn(3).setPreferredWidth(100);
				tablaExcursion.getColumnModel().getColumn(4).setPreferredWidth(200);
				tablaExcursion.getColumnModel().getColumn(5).setPreferredWidth(200);
				tablaExcursion.getColumnModel().getColumn(6).setPreferredWidth(300);
		// Se modifica el modelo de selecci�n de la tabla para que se pueda selecciona
		// �nicamente una fila
		tablaExcursion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}


	public void cargarModeloTabla() {
		//Connection con = BD.initBD("confortTravel.db");

		try {

			ArrayList<Excursion> lista = BD.obtenerExcursiones();
			System.out.println("Número de excursiones: "+lista.size());
			while (modeloExcursion.getRowCount() > 0) {
				modeloExcursion.removeRow(0);
			}
			/*for (int i = 0; i < listaAlojamientos.size(); i++) {
				   Object[] fila = new Object[6];
				   fila[0]= listaAlojamientos.get(i).getId();
				   fila[1]= listaAlojamientos.get(i).getNombre_comp();
				   fila[2]= listaAlojamientos.get(i).getTalojamiento();
				   fila[3]= listaAlojamientos.get(i).getPrecio();
				   fila[4]= listaAlojamientos.get(i).getDuracion();
				   fila[5]= listaAlojamientos.get(i).getCiudad().getId();
				   modeloAlojamiento.addRow(fila);
				   System.out.println("Cargada fila "+i);
				   System.out.println("CARGANDO MODELO DE LA TABLA...");
			}*/
			for(Excursion ex :lista) {
				Object [] fila = {ex.getId(),ex.getNombre(),ex.getTipo(),ex.getLugar().getId(),ex.getPrecio(),ex.getDuracion(),ex.getNumPersonas()};
				modeloExcursion.addRow(fila);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//BD.closeBD(con);

	}

}