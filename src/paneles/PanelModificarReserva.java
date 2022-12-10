package paneles;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import bd.BD;
import clases.Reserva;
import clases.TipoActividad;
import clases.TipoAlojamiento;
import clases.TipoAlquiler;
import clases.TipoExcursion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelModificarReserva extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JPanel panelArriba, panelCentro, panelCentroCentro, panelCentroAbajo;
	private JLabel lblModificarReserva, lblIntroducirID;
	private JButton btnModificarID;
	private JComboBox<TipoAlojamiento> cbTipoAlojamiento;
	private JComboBox<TipoAlquiler> cbAlquilerTransporte;
	private JComboBox<TipoExcursion> cbExcursion;
	private JComboBox<TipoActividad> cbActividad;
	

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
				
				int id = Integer.parseInt(txtID.getText());
				
				/*Object [] colores ={"rojo","negro","amarillo","azul","majenta"}; 
				Object opcion = JOptionPane.showInputDialog(null,"Selecciona un color", "Elegir",JOptionPane.QUESTION_MESSAGE,null,colores, colores[0]);
				System.out.println(opcion);*/
				
				Object [] tipoAlojamiento = {TipoAlojamiento.RURAL, TipoAlojamiento.APARTAMENTO, TipoAlojamiento.HOTEL, TipoAlojamiento.VILLA, TipoAlojamiento.BUNGALO, TipoAlojamiento.CAMPING};
				String alojamiento = String.valueOf(JOptionPane.showInputDialog(null,"Selecciona un color", "Elegir",JOptionPane.QUESTION_MESSAGE,null,tipoAlojamiento, tipoAlojamiento[0]));
				
				Object [] tipoAlquiler = {TipoAlquiler.COCHE, TipoAlquiler.BICICLETA, TipoAlquiler.PATIN_ELECTRICO, TipoAlquiler.MOTO};
				String alquiler = String.valueOf(JOptionPane.showInputDialog(null,"Selecciona un color", "Elegir",JOptionPane.QUESTION_MESSAGE,null,tipoAlquiler, tipoAlquiler[0]));
				
				Object [] tipoExcursion = {TipoExcursion.ACUATICA, TipoExcursion.SENDERISMO, TipoExcursion.TOUR};
				String excursion = String.valueOf(JOptionPane.showInputDialog(null,"Selecciona un color", "Elegir",JOptionPane.QUESTION_MESSAGE,null,tipoExcursion, tipoExcursion[0]));
				
				Object [] tipoActividad = {TipoActividad.SNORKEL, TipoActividad.SKI, TipoActividad.PATINAJE, TipoActividad.CINE, TipoActividad.BOLOS, TipoActividad.RUNNING};
				String actividad = String.valueOf(JOptionPane.showInputDialog(null,"Selecciona un color", "Elegir",JOptionPane.QUESTION_MESSAGE,null,tipoActividad, tipoActividad[0]));
				
				
				BD.uptadeReservas(id, alquiler, alojamiento, excursion, actividad);
				
				JOptionPane.showMessageDialog(null, "Reserva modificada correctamente", "CORRECTO ",JOptionPane.INFORMATION_MESSAGE);
				
				txtID.setText("");
				
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
