package paneles;

import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bd.BD;

import clases.Destino;
import java.awt.GridLayout;
import java.sql.Connection;


import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

public class PanelAnadirDestino extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//creaccion de la tabla
			private static DefaultTableModel modeloDestino;
			private JTable tablaDestino;
			private static Connection con;
			
			private JPanel panelAbajo;
			private JLabel lblTitulo;
	/**
	 * Create the panel.
	 */
	public PanelAnadirDestino() {
		setBackground(new Color(176, 224, 230));
		setLayout(new BorderLayout(0, 0));
		
		tablaDestino = new JTable();
		add(tablaDestino);
		tablaDestino = new JTable( modeloDestino);
		
		 lblTitulo = new JLabel("A\u00F1adir Destino");
		add(lblTitulo, BorderLayout.NORTH);
		
		 panelAbajo = new JPanel();
		panelAbajo.setBackground(new Color(176, 224, 230));
		add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(0, 2, 0, 0));
		Object columnas[] = {"Dni","Nombre"};
		 modeloDestino= new DefaultTableModel();
		 modeloDestino.setColumnIdentifiers(columnas);
		 
		 
		//hacemos la conexion con la BD
			con = BD.initBD("confortTravel.db");
			BD.crearTablas(con);
			BD.closeBD(con);
		cargarModeloTabla();
		

	}
	public static  void cargarModeloTabla() {
		con = BD.initBD("BaseDeDatos.db");
	
		ArrayList<Destino> listaDestinos = new ArrayList<>();
		try {
			//System.out.println(a.get(0).getDni());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.closeBD(con);
		while(modeloDestino.getRowCount()>0)
			modeloDestino.removeRow(0);
		for(Destino d: listaDestinos) {
			
			String fila[] = {d.getId(),d.getNombre()};
			//System.out.println(c.getDni());
			modeloDestino.addRow(fila);
		}
	}
}
