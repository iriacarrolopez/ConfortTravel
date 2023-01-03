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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.sqlite.core.DB;

import com.toedter.calendar.JCalendar;

import bd.BD;
import clases.Ciudad;
import clases.Cliente;
import clases.Reserva;
import clases.TipoActividad;
import clases.TipoAlojamiento;
import clases.TipoAlquiler;
import clases.TipoExcursion;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class PanelReserva extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelArriba, panelCentro, panelCentroCentro, panelCentroAbajo, panelCC1, panelCC2, panelCC3,
			panelCC4, panelCA1, panelCA2, panelCA3, panelCA4;
	private JLabel lblAniadirReserva, lblIDVuelo, lblOrigen, lblDestino, lblFechaIni, lblFechaFin, lblTipoAlojamiento,
			lblAlquilerTransporte, lblExcursiones, lblActividades,lblDNI, lblPrecio;
	private JTextField txtIDVuelo,txtDni, txtPrecio;
	private JComboBox<Ciudad> comboBoxOrigen, comboBoxDestino;
	private JComboBox<TipoAlojamiento> comboBoxTipoAlojamiento;
	private JComboBox<TipoAlquiler> cbAlquilerTransporte;
	private JComboBox<TipoExcursion> cbExcursion;
	private JComboBox<TipoActividad> cbActividades;
	private JButton btnAceptar, btnGuardar, btnCargar;
	private JCalendar cFechaInicio, cFechaFin;
	private Connection con;

	private final static SimpleDateFormat SDF_FECHAS = new SimpleDateFormat("dd/MM/yyyy");

	private JScrollPane scrollPane;
	private JTable tablaReserva;
	private static DefaultTableModel modeloReserva;

	public PanelReserva() {
		setLayout(new BorderLayout(0, 0));

		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);

		lblAniadirReserva = new JLabel("AÃ±adir Reserva");
		panelArriba.add(lblAniadirReserva);

		panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(3, 1, 0, 0));

		inicializarTabla();
		tablaReserva.updateUI();
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
		panelCC2.setLayout(new GridLayout(5,1,0,0));
		
		lblDNI = new JLabel("DNI_CLIENTE");
		panelCC2.add(lblDNI);
		txtDni =   new JTextField();
		panelCC2.add(txtDni);
		txtDni.setColumns(10);
		
		lblPrecio = new JLabel("PRECIO");
		panelCC2.add(lblPrecio);
		txtPrecio = new JTextField();
		panelCC2.add(txtPrecio);
		txtPrecio.setColumns(10);
		
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
		btnGuardar = new JButton("Guardar");
		btnCargar = new JButton("Cargar");
		panelCA4.add(btnCargar);
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		cargarModeloTabla();

		btnCargar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedReader br = new BufferedReader(new FileReader("Conf/miR.csv"));
					String linea = br.readLine();
					while (linea != null) {
						String[] datos = linea.split(";");
						// Reserva r = new
						// Reserva(Integer.parseInt(datos[0]),datos[1],datos[2],datos[3],
						// datos[4],TipoAlquiler.valueOf(datos[5]),TipoAlojamiento.valueOf(datos[6]),TipoExcursion.valueOf(datos[7]),TipoActividad.valueOf(datos[8]));
						System.out.println(Arrays.toString(datos));

						linea = br.readLine();
					}
					br.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		/*
		 * btnGuardar.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { JFileChooser jfc = new
		 * JFileChooser("../ConfortTravel"); int sel = jfc.showSaveDialog(null); if(sel
		 * == JFileChooser.APPROVE_OPTION) { File f = jfc.getSelectedFile(); PrintWriter
		 * pw; try { pw = new PrintWriter(f); //for(int i=0;i<modeloReserva.size;i++){
		 * // Reserva r = modeloReserva.getElementAt(i); //
		 * pw.println(r.getId()+";"+r.getOrigen()+";"+r.getDestino()+";"+r.getFechaIni()
		 * +";"+r.getFechaFin()+";"+r.getAlquilerTransporte()+ //
		 * ";"+r.getTipoAlojamiento()+";"+r.getExcursion()+";"+r.getActividades()); }
		 * for(int i=0;i<modeloReserva.getRowCount();i++) { //Integer id=(Integer)
		 * modeloReserva.getValueAt(i, 0); String id=(String)
		 * modeloReserva.getValueAt(i, 0); String origen=(String)
		 * modeloReserva.getValueAt(i, 1); String destino =
		 * (String)modeloReserva.getValueAt(i, 2); String fi=(String)
		 * modeloReserva.getValueAt(i, 3); String fn = (String)
		 * modeloReserva.getValueAt(i, 4); String alq=(String)
		 * modeloReserva.getValueAt(i, 5); String t1 = (String)
		 * modeloReserva.getValueAt(i, 6); String t2=(String)
		 * modeloReserva.getValueAt(i, 7); String t3 = (String)
		 * modeloReserva.getValueAt(i, 8);
		 * pw.println(id+";"+origen+";"+destino+";"+fi+";"+fn+";"+alq+";"+t1+";"+t2+";"+
		 * t3); } pw.flush(); pw.close(); } catch (FileNotFoundException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 * 
		 * } } }); panelCA4.add(btnGuardar);
		 */

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null,"PARA MÁS SEGURIDAD INTRODUZCA EL SU DNI ");
				
				Integer id = Integer.parseInt(txtIDVuelo.getText());
				Ciudad origen = (Ciudad) comboBoxOrigen.getSelectedItem();
				Ciudad destino = (Ciudad) comboBoxDestino.getSelectedItem();
				Integer idOrigen = origen.getId();
				Integer idDestino = destino.getId();

				Date fechaInicio = cFechaInicio.getDate();
				Date fechaFin = cFechaFin.getDate();

				String fInicio = SDF_FECHAS.format(fechaInicio);
				String fFin = SDF_FECHAS.format(fechaFin);

				String tipoAlquiler = String.valueOf(cbAlquilerTransporte.getSelectedItem());
				// TipoAlquiler ta = TipoAlquiler.valueOf(tipoAlquiler);

				String tipoAlojamiento = String.valueOf(comboBoxTipoAlojamiento.getSelectedItem());
				// TipoAlojamiento tal = TipoAlojamiento.valueOf(tipoAlojamiento);

				String tipoExcursion = String.valueOf(cbExcursion.getSelectedItem());
				// TipoExcursion te = TipoExcursion.valueOf(tipoExcursion);

				String tipoActividad = String.valueOf(cbActividades.getSelectedItem());
				// TipoActividad tact = TipoActividad.valueOf(tipoActividad);
				String dni = txtDni.getText();
				
				Float precio = Float.parseFloat(txtPrecio.getText());
				
				BD.insertarReserva(con, id, idOrigen, idDestino, fInicio, fFin, tipoAlquiler, tipoAlojamiento,
						tipoExcursion, tipoActividad,dni, precio);

				BD.closeBD(con);

			}
		});

	}

	private void cargarModeloTabla() {

		con = BD.initBD("confortTravel.db");

		ArrayList<Reserva> listaReservas = BD.obtenerReservas();
		while (modeloReserva.getRowCount() > 0) {
			modeloReserva.removeRow(0);
		}

		for (Reserva r : listaReservas) {
			Object fila[] = { r.getId(), r.getOrigen(), r.getDestino(), r.getFechaIni(), r.getFechaFin(),
					r.getAlquilerTransporte(), r.getTipoAlojamiento(), r.getExcursion(), r.getActividades(),r.getDni() };
			modeloReserva.addRow(fila);
		}

		BD.closeBD(con);

	}

	private void inicializarTabla() {
		Vector<String> cabeceraReserva = new Vector<String>(Arrays.asList("ID", "ORIGEN", "DESTINO", "FECHA INICIO",
				"FECHA FIN", "ALQUILER TRANSPORTE", "TIPO ALOJAMIENTO", "TIPO EXCURSION", "ACTIVIDADES","DNI_CLIENTE"));
		modeloReserva = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraReserva);
		tablaReserva = new JTable(modeloReserva);

		cargarModeloTabla();

		modeloReserva = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};

	}
}
