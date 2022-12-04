package paneles;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bd.BD;
import clases.Reserva;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelEliminarReserva extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextField textId;
	private JPanel panelArriba, panelCentro, panelCentroAbajo;
	private JLabel lblEliminarReserva, lblIDEliminar;
	private JButton btnEliminar;
	
	private JScrollPane scrollPane;
	private JTable tablaReserva;
	private static DefaultTableModel modeloReserva;
	
	private Connection con;
	
	public PanelEliminarReserva() {
		
		setLayout(new BorderLayout(0, 0));
		
		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);
		
		lblEliminarReserva = new JLabel("Eliminar Reserva");
		panelArriba.add(lblEliminarReserva);
		
		panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 0, 0, 0));
		
		inicializarTabla();
		scrollPane = new JScrollPane(tablaReserva);
		scrollPane.setBorder(new TitledBorder("RESERVAS"));
		tablaReserva.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablaReserva);
		panelCentro.add(scrollPane);
		
		panelCentroAbajo = new JPanel();
		panelCentro.add(panelCentroAbajo);
		
		lblIDEliminar = new JLabel("Introduce el ID de la reserva a eliminar:");
		panelCentroAbajo.add(lblIDEliminar);
		
		textId = new JTextField();
		panelCentroAbajo.add(textId);
		textId.setColumns(10);
		
		btnEliminar = new JButton("Eliminar");
		panelCentroAbajo.add(btnEliminar);
		
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		cargarModeloTabla();
		BD.closeBD(con);
		
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = BD.initBD("confortTravel.db");
				int id = Integer.parseInt(textId.getText());
				BD.eliminarReserva(con, id);
				tablaReserva.updateUI();
				BD.closeBD(con);
				
				textId.setText("");
			}
		});
	}
	
	
public void cargarModeloTabla() {
		
		con = BD.initBD("confortTravel.db");
		
		ArrayList<Reserva> listaReservas = BD.obtenerReservas();
		while(modeloReserva.getRowCount() > 0) {
			modeloReserva.removeRow(0);
		}
		
		for(Reserva r: listaReservas) {
			Object fila[] = {r.getId(), r.getOrigen(), r.getDestino(), r.getFechaIni(), r.getFechaFin(), r.getAlquilerTransporte(), r.getTipoAlojamiento(), r.getExcursion(), r.getActividades()};
			modeloReserva.addRow(fila);
		}
		
		BD.closeBD(con);
	
	}
	
	public void inicializarTabla() {
		Vector<String> cabeceraReserva = new Vector<String>(
				Arrays.asList("ID", "ORIGEN","DESTINO","FECHA INICIO", "FECHA FIN", "ALQUILER TRANSPORTE", "TIPO ALOJAMIENTO", "TIPO EXCURSION", "ACTIVIDADES"));
		modeloReserva = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraReserva);
		tablaReserva = new JTable(modeloReserva);
		
		cargarModeloTabla();
		
		modeloReserva = new DefaultTableModel(){
			
			private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
			};

	}
	
	
	

}
