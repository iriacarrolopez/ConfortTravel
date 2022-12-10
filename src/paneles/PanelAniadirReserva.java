package paneles;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import bd.BD;
import clases.Ciudad;
import clases.Reserva;
import clases.TipoActividad;
import clases.TipoAlojamiento;
import clases.TipoAlquiler;
import clases.TipoExcursion;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAniadirReserva extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JPanel panelArriba, panelCentro, panelCentroCentro, panelCentroAbajo, panelCC1, panelCC2, panelCC3, panelCC4, panelCA1, panelCA2, panelCA3, panelCA4;
	private JLabel lblAniadirReserva, lblIDVuelo, lblOrigen, lblDestino, lblFechaIni, lblFechaFin, lblTipoAlojamiento, lblAlquilerTransporte, lblExcursiones, lblActividades;
	private JTextField txtIDVuelo;
	private JComboBox<Ciudad> comboBoxOrigen, comboBoxDestino;
	private JComboBox<TipoAlojamiento> comboBoxTipoAlojamiento;
	private JComboBox<TipoAlquiler> cbAlquilerTransporte;
	private JComboBox<TipoExcursion> cbExcursion;
	private JComboBox<TipoActividad> cbActividades;
	private JButton btnAceptar;
	private JCalendar cFechaInicio, cFechaFin;
	private Connection con;
	
	private final static SimpleDateFormat SDF_FECHAS = new SimpleDateFormat("dd/MM/yyyy");
	
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
		
		Ciudad c1 = new Ciudad(1, "Sevilla");
		Ciudad c2 = new Ciudad(2, "Barcelona");
		Ciudad c3 = new Ciudad(3, "Madrid");
		Ciudad c4 = new Ciudad(4, "Bilbao");
		Ciudad c5 = new Ciudad(5, "Mallorca");
		Ciudad c6 = new Ciudad(6, "Paris");
		Ciudad c7 = new Ciudad(7, "Malta");
		comboBoxOrigen = new JComboBox<Ciudad>();
		comboBoxOrigen.addItem(c1);
		comboBoxOrigen.addItem(c2);
		comboBoxOrigen.addItem(c3);
		comboBoxOrigen.addItem(c4);
		comboBoxOrigen.addItem(c5);
		comboBoxOrigen.addItem(c6);
		comboBoxOrigen.addItem(c7);
		panelCC2.add(comboBoxOrigen);
		
		lblDestino = new JLabel("Destino");
		panelCC2.add(lblDestino);
		
		comboBoxDestino = new JComboBox<Ciudad>();
		comboBoxDestino.addItem(c1);
		comboBoxDestino.addItem(c2);
		comboBoxDestino.addItem(c3);
		comboBoxDestino.addItem(c4);
		comboBoxDestino.addItem(c5);
		comboBoxDestino.addItem(c6);
		comboBoxDestino.addItem(c7);
		panelCC2.add(comboBoxDestino);
		
		panelCC3 = new JPanel();
		panelCentroCentro.add(panelCC3);
		
		lblFechaIni = new JLabel("Fecha Inicio");
		panelCC3.add(lblFechaIni);
		
		cFechaInicio = new JCalendar();
		panelCC3.add(cFechaInicio);
		
		panelCC4 = new JPanel();
		panelCentroCentro.add(panelCC4);
		
		lblFechaFin = new JLabel("Fecha Fin");
		panelCC4.add(lblFechaFin);
		
		cFechaFin = new JCalendar();
		panelCC4.add(cFechaFin);
		
		panelCentroAbajo = new JPanel();
		panelCentro.add(panelCentroAbajo);
		panelCentroAbajo.setLayout(new GridLayout(0, 4, 0, 0));
		
		panelCA1 = new JPanel();
		panelCentroAbajo.add(panelCA1);
		
		lblTipoAlojamiento = new JLabel("Tipo Alojamiento:");
		panelCA1.add(lblTipoAlojamiento);
		
		comboBoxTipoAlojamiento = new JComboBox<>(TipoAlojamiento.values());
		panelCA1.add(comboBoxTipoAlojamiento);
		
		lblAlquilerTransporte = new JLabel("Alquiler Transporte");
		panelCA1.add(lblAlquilerTransporte);
		
		cbAlquilerTransporte = new JComboBox<>(TipoAlquiler.values());
		panelCA1.add(cbAlquilerTransporte);
		
		panelCA2 = new JPanel();
		panelCentroAbajo.add(panelCA2);
		
		lblExcursiones = new JLabel("Excursiones");
		panelCA2.add(lblExcursiones);
		
		cbExcursion = new JComboBox<>(TipoExcursion.values());
		panelCA2.add(cbExcursion);
		
		panelCA3 = new JPanel();
		panelCentroAbajo.add(panelCA3);
		
		lblActividades = new JLabel("Actividades");
		panelCA3.add(lblActividades);
		
		cbActividades = new JComboBox<>(TipoActividad.values());
		panelCA3.add(cbActividades);
				
		panelCA4 = new JPanel();
		panelCentroAbajo.add(panelCA4);
		
		btnAceptar = new JButton("Aceptar");
		panelCA4.add(btnAceptar);
		
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		cargarModeloTabla();
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(txtIDVuelo.getText());
				Ciudad origen = (Ciudad) comboBoxOrigen.getSelectedItem();
				Ciudad destino = (Ciudad) comboBoxDestino.getSelectedItem();
				int idOrigen = origen.getId();
				int idDestino = destino.getId();
				
				Date fechaInicio = cFechaInicio.getDate();
				Date fechaFin = cFechaFin.getDate();
				
				String fInicio = SDF_FECHAS.format(fechaInicio);
				String fFin = SDF_FECHAS.format(fechaFin);
				
				
				String tipoAlquiler = String.valueOf(cbAlquilerTransporte.getSelectedItem());
				//TipoAlquiler ta = TipoAlquiler.valueOf(tipoAlquiler);
				
				String tipoAlojamiento = String.valueOf(comboBoxTipoAlojamiento.getSelectedItem());
				//TipoAlojamiento tal = TipoAlojamiento.valueOf(tipoAlojamiento);
				
				String tipoExcursion = String.valueOf(cbExcursion.getSelectedItem());
				//TipoExcursion te = TipoExcursion.valueOf(tipoExcursion);
				
				String tipoActividad = String.valueOf(cbActividades.getSelectedItem());
				//TipoActividad tact = TipoActividad.valueOf(tipoActividad);
				
				BD.insertarReserva(con, id, idOrigen, idDestino, fInicio, fFin, tipoAlquiler, tipoAlojamiento, tipoExcursion, tipoActividad);
				
				BD.closeBD(con);
						
			}
		});
		
		
		
		
	}
	
	private void cargarModeloTabla() {
		
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
	
	private void inicializarTabla() {
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
