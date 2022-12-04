package paneles;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bd.BD;
import clases.Reserva;

import javax.swing.JComboBox;

public class PanelAniadirReserva extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JPanel panelArriba, panelCentro, panelCentroCentro, panelCentroAbajo, panelCC1, panelCC2, panelCC3, panelCC4, panelCA1, panelCA2, panelCA3, panelCA4;
	private JLabel lblAniadirReserva, lblIDVuelo, lblOrigen, lblDestino, lblFechaIni, lblFechaFin, lblTipoAlojamiento, lblAlquilerTransporte, lblExcursiones, lblActividades;
	private JTextField txtIDVuelo, txtFechaIni, txtFechaFin;
	private JComboBox comboBoxOrigen, comboBoxDestino, comboBoxTipoAlojamiento, cbAlquilerTransporte, cbExcursion;
	private JButton btnAceptar;
	private Connection con;
	
	private JScrollPane scrollPane;
	private JTable tablaReserva;
	private static DefaultTableModel modeloReserva;
	
	public PanelAniadirReserva() {
		setLayout(new BorderLayout(0, 0));
		
		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);
		
		lblAniadirReserva = new JLabel("Añadir Reserva");
		panelArriba.add(lblAniadirReserva);
		
		panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(3, 1, 0, 0));
		
		
		inicializarTabla();
		scrollPane = new JScrollPane(tablaReserva);
		scrollPane.setBorder(new TitledBorder("RESERVAS"));
		tablaReserva.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablaReserva);
		panelCentro.add(scrollPane);
		
		panelCentroCentro = new JPanel();
		panelCentro.add(panelCentroCentro);
		panelCentroCentro.setLayout(new GridLayout(0, 4, 0, 0));
		
		panelCC1 = new JPanel();
		panelCentroCentro.add(panelCC1);
		
		lblIDVuelo = new JLabel("ID Reserva:");
		panelCC1.add(lblIDVuelo);
		
		txtIDVuelo = new JTextField();
		panelCC1.add(txtIDVuelo);
		txtIDVuelo.setColumns(10);
		
		panelCC2 = new JPanel();
		panelCentroCentro.add(panelCC2);
		
		lblOrigen = new JLabel("Origen");
		panelCC2.add(lblOrigen);
		
		comboBoxOrigen = new JComboBox();
		comboBoxOrigen.addItem("Sevilla");
		comboBoxOrigen.addItem("Barcelona");
		comboBoxOrigen.addItem("Madrid");
		comboBoxOrigen.addItem("Bilbao");
		comboBoxOrigen.addItem("Mallorca");
		panelCC2.add(comboBoxOrigen);
		
		lblDestino = new JLabel("Destino");
		panelCC2.add(lblDestino);
		
		comboBoxDestino = new JComboBox();
		comboBoxDestino.addItem("Sevilla");
		comboBoxDestino.addItem("Barcelona");
		comboBoxDestino.addItem("Madrid");
		comboBoxDestino.addItem("Bilbao");
		comboBoxDestino.addItem("Mallorca");
		panelCC2.add(comboBoxDestino);
		
		panelCC3 = new JPanel();
		panelCentroCentro.add(panelCC3);
		
		lblFechaIni = new JLabel("Fecha Inicio");
		panelCC3.add(lblFechaIni);
		
		txtFechaIni = new JTextField();
		panelCC3.add(txtFechaIni);
		txtFechaIni.setColumns(10);
		
		lblFechaFin = new JLabel("Fecha Fin");
		panelCC3.add(lblFechaFin);
		
		txtFechaFin = new JTextField();
		panelCC3.add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
		panelCC4 = new JPanel();
		panelCentroCentro.add(panelCC4);
		
		lblTipoAlojamiento = new JLabel("Tipo Alojamiento:");
		panelCC4.add(lblTipoAlojamiento);
		
		comboBoxTipoAlojamiento = new JComboBox();
		comboBoxTipoAlojamiento.addItem("Rural");
		comboBoxTipoAlojamiento.addItem("Apartamento");
		comboBoxTipoAlojamiento.addItem("Hotel");
		comboBoxTipoAlojamiento.addItem("Villa");
		comboBoxTipoAlojamiento.addItem("Bungalo");
		comboBoxTipoAlojamiento.addItem("Camping");
		panelCC4.add(comboBoxTipoAlojamiento);
		
		panelCentroAbajo = new JPanel();
		panelCentro.add(panelCentroAbajo);
		panelCentroAbajo.setLayout(new GridLayout(0, 4, 0, 0));
		
		panelCA1 = new JPanel();
		panelCentroAbajo.add(panelCA1);
		
		lblAlquilerTransporte = new JLabel("Alquiler Transporte");
		panelCA1.add(lblAlquilerTransporte);
		
		cbAlquilerTransporte = new JComboBox();
		cbAlquilerTransporte.addItem("Coche");
		cbAlquilerTransporte.addItem("Bicicleta");
		cbAlquilerTransporte.addItem("Patin Eléctrico");
		cbAlquilerTransporte.addItem("Moto");
		panelCA1.add(cbAlquilerTransporte);
		
		panelCA2 = new JPanel();
		panelCentroAbajo.add(panelCA2);
		
		lblExcursiones = new JLabel("Excursiones");
		panelCA2.add(lblExcursiones);
		
		cbExcursion = new JComboBox();
		cbExcursion.addItem("Acuatica");
		cbExcursion.addItem("Senderismo");
		cbExcursion.addItem("Tour");
		panelCA2.add(cbExcursion);
		
		panelCA3 = new JPanel();
		panelCentroAbajo.add(panelCA3);
		
		lblActividades = new JLabel("Actividades");
		panelCA3.add(lblActividades);
		
		panelCA4 = new JPanel();
		panelCentroAbajo.add(panelCA4);
		
		btnAceptar = new JButton("Aceptar");
		panelCA4.add(btnAceptar);
		

		
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		cargarModeloTabla();
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
