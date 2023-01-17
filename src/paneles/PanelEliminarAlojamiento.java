package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bd.BD;
import clases.Alojamiento;

public class PanelEliminarAlojamiento extends JPanel{

	private static final long serialVersionUID = 1L;
	private static DefaultTableModel modeloAlojamiento;
	private static JTable tableAlojamiento;
	private static JButton btnEliminar;
	private JScrollPane scrollPaneTablaAlojamiento;
	
	private JPanel panelArriba;
	private JPanel panelAbajo;
	private JLabel lblInfo;
	
	
	private Connection con;
	
	public PanelEliminarAlojamiento() {
		setBounds(100,100,900,900);
		setBackground(new Color(176,224,230));
		
		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(0, 2, 0, 0));

		btnEliminar = new JButton("ELIMINAR");
		panelAbajo.add(btnEliminar);
		
		inicializarTabla();
		scrollPaneTablaAlojamiento = new JScrollPane(tableAlojamiento);
		scrollPaneTablaAlojamiento.setBorder(new TitledBorder("Alojamientos"));
		add(scrollPaneTablaAlojamiento, BorderLayout.CENTER);
		
		panelArriba = new JPanel();
		panelArriba.setBackground(new Color(0,153,255));
		add(panelArriba, BorderLayout.NORTH);
		lblInfo = new JLabel("Eliminar alojamiento");
		lblInfo.setBackground(Color.WHITE);
		panelArriba.add(lblInfo);
		
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		BD.closeBD(con);
		cargarModeloTabla();
		
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				con = BD.initBD("confortTravel.db");
				int i = tableAlojamiento.getSelectedRow();
				int id = (int) tableAlojamiento.getValueAt(i, 0);
				BD.eliminarAlojamiento(con,id);
				BD.closeBD(con);
			}
		});
	}
	/**
	 * Método que inicializa la JTable mediante el método cargarModeloTabla
	 */
	private void inicializarTabla() {
		
		Vector<String> cabeceraAlojamientos = new Vector<String>(Arrays.asList("ID","NOMBRE","TIPO","DURACION","DESTINO","PRECIO"));
		modeloAlojamiento = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAlojamientos);
		tableAlojamiento = new JTable(modeloAlojamiento);
		
		cargarModeloTabla();
		tableAlojamiento.setRowHeight(30);
		tableAlojamiento.getColumnModel().getColumn(0).setPreferredWidth(400);
		tableAlojamiento.getColumnModel().getColumn(1).setPreferredWidth(400);
		tableAlojamiento.getColumnModel().getColumn(2).setPreferredWidth(400);
		tableAlojamiento.getColumnModel().getColumn(3).setPreferredWidth(400);
		tableAlojamiento.getColumnModel().getColumn(4).setPreferredWidth(400);
		tableAlojamiento.getColumnModel().getColumn(5).setPreferredWidth(400);
		
	}
	
	/**
	 * Método que elimina una fila de la JTable
	 */
	private static void eliminarFilaDestinoDeLaTabla() {
		int rowCount = modeloAlojamiento.getRowCount();
		for (int i=rowCount-1;i>=0;i--) {
			modeloAlojamiento.removeRow(i);
		}
	}
	
	/**
	 * Método que carga el modelo de la JTable
	 */
	private static void cargarModeloTabla() {
		Connection con = BD.initBD("confortTravel.db");
		try {
			ArrayList<Alojamiento> listaAlojamientos = BD.obtenerAlojamientos();
			eliminarFilaDestinoDeLaTabla();
			for (Alojamiento a: listaAlojamientos) {
				Object fila[] = {a.getId(),a.getNombre_comp(),a.getTalojamiento(), a.getDuracion(), a.getCiudad(), a.getPrecio()};
				modeloAlojamiento.addRow(fila);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		BD.closeBD(con);
	}
 }
