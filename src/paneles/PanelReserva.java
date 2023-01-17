package paneles;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.sqlite.core.DB;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;

import bd.BD;
import clases.Ciudad;
import clases.Cliente;
import clases.Reserva;
import clases.TipoActividad;
import clases.TipoAlojamiento;
import clases.TipoAlquiler;
import clases.TipoExcursion;
import ventanas.VentanaInicio;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class PanelReserva extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private JPanel panelArriba, panelCentro, panelCentroCentro, panelCentroAbajo, panelCC1, panelCC2, panelCC3,
			panelCC4, panelCA1, panelCA2, panelCA3, panelCA4;
	private JLabel lblAniadirReserva, lblEliminarReserva, lblOrigen, lblDestino, lblFechaIni, lblFechaFin, lblTipoAlojamiento,
			lblAlquilerTransporte, lblExcursiones, lblActividades,lblDNI,labelC;
	private JTextField txtIDVuelo,txtDni;
	private JComboBox<Ciudad> comboBoxOrigen, comboBoxDestino;
	private JComboBox<TipoAlojamiento> comboBoxTipoAlojamiento;
	private JComboBox<TipoAlquiler> cbAlquilerTransporte;
	private JComboBox<TipoExcursion> cbExcursion;
	private JComboBox<TipoActividad> cbActividades;
	private JButton btnAceptar, btnGuardar, btnInsertar;
	private JDateChooser dcFechaInicio, dcFechaFin;
	private Connection con;
	private JProgressBar progressBar;
	private static Timer timer ;
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

		lblEliminarReserva = new JLabel("Alt + Click para eliminar una reserva");
		panelCC1.add(lblEliminarReserva);
		
		btnInsertar = new JButton("INSERTAR");
		panelCC1.add(btnInsertar);

		panelCC2 = new JPanel();
		panelCentroCentro.add(panelCC2);
		panelCC2.setLayout(new GridLayout(5,1,0,0));
		
		lblDNI = new JLabel("DNI_CLIENTE");
		panelCC2.add(lblDNI);
		txtDni =   new JTextField();
		panelCC2.add(txtDni);
		txtDni.setColumns(10);
		
		lblOrigen = new JLabel("Origen");
		panelCC2.add(lblOrigen);
		progressBar = new JProgressBar(0,100);
		progressBar.setVisible(true);
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.GREEN);
		progressBar.setBackground(Color.LIGHT_GRAY);
		progressBar.setBorder(new LineBorder(Color.DARK_GRAY));
		
			
		labelC = new JLabel();
		 timer = new Timer(100, new ActionListener() {
			int cont =0;
			Thread hilo = new Thread();
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				progressBar.setValue(cont++);
			 
				labelC.setText("CARGANDO :");
				
					if(cont==100) {
						hilo.start();
						timer.stop();
						
						JOptionPane.showMessageDialog(null, "FUNCION REALIZADA CON  EXITO");
						cont=0;
					
					
				}
				
			}
		});
		 
		panelArriba.add(progressBar);
		
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
		
		dcFechaInicio = new JDateChooser(new JSpinnerDateEditor());
		panelCC3.add(dcFechaInicio);

		panelCC4 = new JPanel();
		panelCentroCentro.add(panelCC4);

		lblFechaFin = new JLabel("Fecha Fin");
		panelCC4.add(lblFechaFin);
		
		dcFechaFin = new JDateChooser(new JSpinnerDateEditor());
		panelCC4.add(dcFechaFin);

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
		BD.closeBD(con);
		cargarModeloTabla();
		
		ocultarCampos();
		
		btnInsertar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.start();
				panelCC2.setVisible(true);
				panelCC3.setVisible(true);
				panelCC4.setVisible(true);
				panelCentroAbajo.setVisible(true);
				btnInsertar.setVisible(false);
				
			}
		});

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null,"PARA MÁS SEGURIDAD INTRODUZCA EL SU DNI ");
				
					
				Integer id = BD.obtenerMayorCodigoReserva() + 1;
				Ciudad origen = (Ciudad) comboBoxOrigen.getSelectedItem();
				Ciudad destino = (Ciudad) comboBoxDestino.getSelectedItem();
				Integer idOrigen = origen.getId();
				Integer idDestino = destino.getId();

				Date fechaInicio = dcFechaInicio.getDate();
				Date fechaFin = dcFechaFin.getDate();

				String fInicio = SDF_FECHAS.format(fechaInicio);
				String fFin = SDF_FECHAS.format(fechaFin);

				String tipoAlquiler = String.valueOf(cbAlquilerTransporte.getSelectedItem());
				TipoAlquiler ta = TipoAlquiler.valueOf(tipoAlquiler);

				String tipoAlojamiento = String.valueOf(comboBoxTipoAlojamiento.getSelectedItem());
				TipoAlojamiento tal = TipoAlojamiento.valueOf(tipoAlojamiento);

				String tipoExcursion = String.valueOf(cbExcursion.getSelectedItem());
				TipoExcursion te = TipoExcursion.valueOf(tipoExcursion);

				String tipoActividad = String.valueOf(cbActividades.getSelectedItem());
				TipoActividad tact = TipoActividad.valueOf(tipoActividad);
				String dni = txtDni.getText();
				
				
				
				//Float precio = Float.parseFloat(txtPrecio.getText());
				
				//Calculamos el precio
				float precioTotal = 1000;
				
				if(ta != TipoAlquiler.NINGUNO) {
					precioTotal = precioTotal + 300;
				}
				if(tal != TipoAlojamiento.NINGUNO) {
					precioTotal = precioTotal + 500;
				}
				if(te != TipoExcursion.NINGUNA_EXCURSION) {
					precioTotal = precioTotal + 100;
				}
				if(tact != TipoActividad.NINGUNA) {
					precioTotal = precioTotal + 250;
				}
				
				if(!dni.equals(VentanaInicio.dni)) {
					JOptionPane.showMessageDialog(null,"INTRODUCE BIEN TU DNI ");
				}else if(origen.equals(destino)){
					JOptionPane.showMessageDialog(null,"EL ORIGEN Y DESTINO NO PUEDEN SER IGUALES");
				}else {
					JOptionPane.showMessageDialog(null,"RESERVA INSERTADA CORRECTAMENTE ");
					
					BD.insertarReserva(id, idOrigen, idDestino, fInicio, fFin, tipoAlquiler, tipoAlojamiento,
							tipoExcursion, tipoActividad,dni, precioTotal);
					
					timer.stop();
					
					cargarModeloTabla();
					
					ocultarCampos();
					labelC.setText(" ");
					txtDni.setText("");
				}
				


			}
		});
		
		tablaReserva.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.isAltDown()) {
					int row = tablaReserva.getSelectedRow();
					int idSeleccionado = (int) tablaReserva.getValueAt(row, 0);
					BD.eliminarReserva(idSeleccionado);
					cargarModeloTabla();
				}
				
				
			}
		});
		
		tablaReserva.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				TipoAlquiler ta = (TipoAlquiler) modeloReserva.getValueAt(row, 5);
				
				if(ta.equals(TipoAlquiler.BICICLETA)) {
					c.setBackground(Color.YELLOW);
				}else if(ta.equals(TipoAlquiler.COCHE)) {
					c.setBackground(Color.LIGHT_GRAY);
				}else if(ta.equals(TipoAlquiler.MOTO)) {
					c.setBackground(Color.ORANGE);
				}else if(ta.equals(TipoAlquiler.PATIN_ELECTRICO)) {
					c.setBackground(Color.GREEN);
				}else {
					c.setBackground(Color.CYAN);
				}
				
				return c;
				
				/*
				 * Ciudad c1 = new Ciudad(1, "Sevilla");
		Ciudad c2 = new Ciudad(2, "Barcelona");
		Ciudad c3 = new Ciudad(3, "Madrid");
		Ciudad c4 = new Ciudad(4, "Bilbao");
		Ciudad c5 = new Ciudad(5, "Mallorca");
		Ciudad c6 = new Ciudad(6, "Paris");
		Ciudad c7 = new Ciudad(7, "Malta");
				 */
			}
		});
	}

	/**
	 * Método que carga el método de la JTable a partir de la BD
	 */
	private void cargarModeloTabla() {

		System.out.println("CARGANDO EL MODELO");

		ArrayList<Reserva> listaReservas = (ArrayList<Reserva>) BD.obtenerReservasPorCliente(VentanaInicio.dni);
		while (modeloReserva.getRowCount() > 0) {
			modeloReserva.removeRow(0);
		}

		for (Reserva r : listaReservas) {
			Object fila[] = { r.getId(), r.getOrigen(), r.getDestino(), r.getFechaIni(), r.getFechaFin(),
					r.getAlquilerTransporte(), r.getTipoAlojamiento(), r.getExcursion(), r.getActividades(),r.getDni(),r.getPrecioTotal() };
			modeloReserva.addRow(fila);
		}

		tablaReserva.repaint();

	}

	/**
	 * Método que inicializa la JTable mediante el método de cargarModeloTabla
	 */
	private void inicializarTabla() {
		Vector<String> cabeceraReserva = new Vector<String>(Arrays.asList("ID", "ORIGEN", "DESTINO", "FECHA INICIO",
				"FECHA FIN", "ALQUILER TRANSPORTE", "TIPO ALOJAMIENTO", "TIPO EXCURSION", "ACTIVIDADES","DNI_CLIENTE","PRECIO"));
		modeloReserva = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraReserva) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if(columnIndex == 0 || columnIndex == 9 || columnIndex == 10) {
					return false;
				}else {
					return true;
				}
			}

		};
		tablaReserva = new JTable(modeloReserva);

		cargarModeloTabla();

	}
	
	/**
	 * Método que oculta los campos cuando no se utilicen
	 */
	private void ocultarCampos() {
		panelCC2.setVisible(false);
		panelCC3.setVisible(false);
		panelCC4.setVisible(false);
		panelCentroAbajo.setVisible(false);
		btnInsertar.setVisible(true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
