package paneles;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bd.BD;
import clases.Reserva;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelModificarReserva extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JPanel panelArriba, panelCentro, panelCentroCentro, panelCentroAbajo;
	private JLabel lblModificarReserva, lblIntroducirID;
	private JButton btnModificarID;
	

	private JScrollPane scrollPane;
	private JTable tablaReserva;
	private static DefaultTableModel modeloReserva;
	
	private Connection con;
	
	public PanelModificarReserva() {
		setLayout(new BorderLayout(0, 0));
		
		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);
		
		lblModificarReserva = new JLabel("Modificar Reserva");
		panelArriba.add(lblModificarReserva);
		
		panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(3, 0, 0, 0));
		
		inicializarTabla();
		scrollPane = new JScrollPane(tablaReserva);
		scrollPane.setBorder(new TitledBorder("RESERVAS"));
		tablaReserva.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablaReserva);
		panelCentro.add(scrollPane);
		
		panelCentroCentro = new JPanel();
		panelCentro.add(panelCentroCentro);
		
		lblIntroducirID = new JLabel("Introduce el ID de la reserva a modificar:");
		panelCentroCentro.add(lblIntroducirID);
		
		txtID = new JTextField();
		panelCentroCentro.add(txtID);
		txtID.setColumns(10);
		
		btnModificarID = new JButton("MODIFICAR");
		panelCentroCentro.add(btnModificarID);
		
		panelCentroAbajo = new JPanel();
		panelCentro.add(panelCentroAbajo);		
		
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		cargarModeloTabla();
		
		
		btnModificarID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = BD.initBD("confortTravel.db");
				//LLAMAR A LOS METODOS DE MODIFICAR!!!!!
			}
		});
		
		
		BD.closeBD(con);
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
