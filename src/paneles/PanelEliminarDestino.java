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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bd.BD;
import clases.Destino;

public class PanelEliminarDestino extends JPanel{

	private static final long serialVersionUID = 1L;
	private static DefaultTableModel modeloDestino;
	private static JTable tableDestino;
	private static JButton btnEliminar;
	private JScrollPane scrollPaneTablaDestino;

	private JPanel panelAbajo;

	private JPanel panelArriba;
	private JLabel lblInfo;
	
	private Connection con;
	
	public PanelEliminarDestino() {
		setBounds(100, 100, 900, 900);
		setBackground(new Color(176, 224, 230));
		setLayout(new BorderLayout(0, 0));

		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(0, 2, 0, 0));

		btnEliminar = new JButton("ELIMINAR");
		panelAbajo.add(btnEliminar);
		
		inicializarTabla();
		scrollPaneTablaDestino = new JScrollPane(tableDestino);
		scrollPaneTablaDestino.setBorder(new TitledBorder("DESTINOS"));
		tableDestino.setFillsViewportHeight(true);
		add(scrollPaneTablaDestino, BorderLayout.CENTER);

		panelArriba = new JPanel();
		panelArriba.setBackground(new Color(0, 153, 255));
		add(panelArriba, BorderLayout.NORTH);
		lblInfo = new JLabel("Eliminar destino");
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
				int d = tableDestino.getSelectedRow();
				int id = (int) tableDestino.getValueAt(d, 0);
				BD.eliminarDestino(con, id);	
				BD.closeBD(con);
			}
		});
		
	}
	
	private void inicializarTabla() {
		
		Vector<String> cabeceraDestinos = new Vector<String>(Arrays.asList("ID","NOMBRE"));
		modeloDestino = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraDestinos);
		tableDestino = new JTable(modeloDestino);
		
		cargarModeloTabla();
		tableDestino.setRowHeight(30);
		tableDestino.getColumnModel().getColumn(0).setPreferredWidth(400);
		tableDestino.getColumnModel().getColumn(1).setPreferredWidth(400);
		tableDestino.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	private static void eliminarFilaDestinoDeLaTabla() {
		int rowCount = modeloDestino.getRowCount();
		for (int i=rowCount-1; i>=0;i--) {
			modeloDestino.removeRow(i);
		}
	}
	
	private static void cargarModeloTabla() {
		Connection con = BD.initBD("confortTravel.db");
		
		try {
			ArrayList<Destino> listaDestinos = BD.obtenerDestinos();
			eliminarFilaDestinoDeLaTabla();
			for (Destino d: listaDestinos) {
				Object fila[] = {d.getId(), d.getNombre()};
				modeloDestino.addRow(fila);
 			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BD.closeBD(con);
		
	}
}
