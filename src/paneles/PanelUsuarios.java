package paneles;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;

import com.toedter.calendar.JCalendarBeanInfo;

import bd.BD;
import clases.Cliente;
import clases.Persona;
import clases.Reserva;

import javax.swing.JScrollPane;

/*Nuevo*/
public class PanelUsuarios extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panelNorte, panelCentro;
	JLabel lblINFO;
	DefaultListModel<Persona> modeloCliente;
	private JScrollPane scrollPaneList;
	private JList<Persona> listCliente;
	private JTextArea textAreaReservas;
	//N 
	/*
	 * mapa
	 * string -> dni 
	 * arraylist -> todas las reservas por ese dni
	 */
	private TreeMap<String, ArrayList<Reserva>> mapaClienteR;
	private Connection con;
	private JButton btnGuardar;

	/**
	 * Create the panel.
	 */
	public PanelUsuarios() {
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
		
		setLayout(new BorderLayout(0, 0));
		panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);

		lblINFO = new JLabel("USUARIOS");
		panelNorte.add(lblINFO);

		panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnGuardar = new JButton("GUARDAR");
		panelNorte.add(btnGuardar);
		/* crear jlist */
		modeloCliente = new DefaultListModel<>();

		listCliente = new JList<Persona>(modeloCliente);
		scrollPaneList = new JScrollPane();
		scrollPaneList.setViewportView(listCliente);
		panelCentro.add(scrollPaneList);

		textAreaReservas = new JTextArea();
		panelCentro.add(textAreaReservas);
		cargarListaClientes();
		
		mapaClienteR = new TreeMap<String, ArrayList<Reserva>>();
		
	

		listCliente.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Persona c = listCliente.getSelectedValue();
			
				TreeMap<String ,ArrayList<Reserva>> mapaClienteR = BD.obtenerTreeMapReservasPorDni(c.getDni());
				System.out.println(mapaClienteR);
				textAreaReservas.setText(" ");
				String texto ="";
				for(String dni : mapaClienteR.keySet()) {
					ArrayList<Reserva> a = mapaClienteR.get(dni);
					for(Reserva r :a) {
						texto = texto + r + "\n";
					}
					textAreaReservas.setText(texto);
				}
			}
		});
		
	}

	/*private void cargarTextArea(Persona c) {
		textAreaReservas.setText("");
		String texto = "";
		// Vamos a cargar en el textArea los valores del mapa asociados al empleado
		// seleccionado en la JList
		for (Reserva t : mapaClienteR.get(c)) {
			
			texto = texto + t + "\n";
		}
		textAreaReservas.setText(texto);
	}*/

	private void cargarListaClientes() {

		try {

			ArrayList<Persona> lista =BD.ObtenerClientes("CLIENTE");
			modeloCliente.removeAllElements();
			for (Persona p : lista) {
				mapaClienteR = BD.obtenerTreeMapReservasPorDni(p.getDni());
				System.out.println(lista);
				System.out.println(mapaClienteR);
				modeloCliente.addElement(p);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	btnGuardar.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//guardarMapaEnFichero("nuevoficheroReservas.txt");
		}
	});
	setVisible(true);
	}
}
