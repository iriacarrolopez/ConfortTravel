package paneles;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import bd.BD;
import clases.Ciudad;
import clases.Excursion;


public class PanelExcursiones extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaExcursion;
	private JPanel panelArriba, panelAbajo;
	private JLabel lblTitulo ,labelC;
	private DefaultTableModel modeloExcursion;
	private JScrollPane scrollPaneExcursion;
	private Connection con;
	 static Timer timer;
	private JButton btnInsertarExcursion, btnEliminar, btnModificar;
	private JProgressBar progressBar;

	


	/**
	 * Create the panel.
	 */
	public PanelExcursiones() {
		// hacemos la conexion con la BD
		con = BD.initBD("confortTravel.db");
		BD.crearTablas(con);
				//BD.closeBD(con);
				//cargarModeloTabla();

		setLayout(new BorderLayout(0, 0));

		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);
		panelArriba.setLayout(new GridLayout(0, 4, 0, 0));
		
		lblTitulo = new JLabel("Excursion");
		labelC = new JLabel();
		panelArriba.add(lblTitulo);
		panelArriba.add(labelC);
		
		progressBar = new JProgressBar(0,100);
		progressBar.setVisible(true);
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.GREEN);
		progressBar.setBackground(Color.LIGHT_GRAY);
		progressBar.setBorder(new LineBorder(Color.DARK_GRAY));
		
			
		
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
		
		

		panelAbajo = new JPanel();
		add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setLayout(new GridLayout(0, 3, 0, 0));

		btnInsertarExcursion = new JButton("NUEVO EXCURSION");
		panelAbajo.add(btnInsertarExcursion);

		btnEliminar = new JButton("ELIMINAR EXCURSION");
		panelAbajo.add(btnEliminar);

		btnModificar = new JButton("MODIFICAR EXCURSION");
		panelAbajo.add(btnModificar);
		

		

		// La tabla de destino se inserta en un panel con scroll
		inicializarTabla();
		tablaExcursion.updateUI();
		
		scrollPaneExcursion = new JScrollPane(tablaExcursion);
		scrollPaneExcursion.setBorder(new TitledBorder("EXCURSIONES"));
		tablaExcursion.setFillsViewportHeight(true);
		add(scrollPaneExcursion, BorderLayout.CENTER);
		scrollPaneExcursion.setViewportView(tablaExcursion);
		
		
		
		
		
		

		btnInsertarExcursion.addActionListener(new ActionListener() {

			

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				//List<Integer> excursiones = BD.getExcursiones();
				
				timer.start();
				String id = JOptionPane.showInputDialog("Introduce el id :");
				
				String nombre = JOptionPane.showInputDialog("Introduce el nombre:");
				String tipo = JOptionPane.showInputDialog("Introduce el tipo :");
				String lugar = JOptionPane.showInputDialog("Introduce el lugar :");
				String precio = JOptionPane.showInputDialog("Introduce el precio :");
				String edad = JOptionPane.showInputDialog("Introduce la edad :");
				String duracion = JOptionPane.showInputDialog("Introduce el duracion :");
				String num = JOptionPane.showInputDialog("Introduce el numPersonas:");
				
				Ciudad c = BD.getCiudadByNombre(lugar);
				
				String nlugar = c.getNombre();
				
				
				BD.insertarExcursion(con, Integer.parseInt(id), nombre, tipo, nlugar, Float.parseFloat(precio),edad,
						Integer.parseInt(duracion), Integer.parseInt(num));
				
					
					
				
				
				// Borramos el contenido del modelo de la tabla
				while (modeloExcursion.getRowCount() > 0) {
					modeloExcursion.removeRow(0);
				}
				cargarModeloTabla();
				timer.stop();
				labelC.setText(" ");
				BD.closeBD(con);
			}

		});
		
		/*
		 * Al hacer click en una fila se te borra
		 */
		btnEliminar.addActionListener(new ActionListener() {

			
				@Override
				public void actionPerformed(ActionEvent e) {
					con = BD.initBD("confortTravel.db");
					int d = tablaExcursion.getSelectedRow();
					int id = (int) tablaExcursion.getValueAt(d, 0);
					BD.EliminarExcursion(con, id);
					cargarModeloTabla();
				}
		});
	
			
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(null,
						"SOLO ESTA PERMITIDO MODIFICAR EL NUMERO DE PERSONAS QUE PUEDEN PARTICIPAR EN LAS EXCURSIONES",
						"CLOSED_OPTION", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);

				String id = JOptionPane.showInputDialog("Introduzca el id:");
				String num = JOptionPane.showInputDialog("Nuevo numero de personas que pueden participar:");

				//modificar
				BD.obtenerDatosExcursion( Integer.parseInt(id));
				BD.UpdateNumeroPersonasEnExcursion(Integer.parseInt(id), Integer.parseInt(num));
				// Borramos el contenido del modelo de la tabla
				while (modeloExcursion.getRowCount() > 0) {
					modeloExcursion.removeRow(0);
				}
				cargarModeloTabla();
				
			}
		});
		
	}

	/**
	 * Método que inicializa la JTable mediante el método cargarModeloTabla
	 */
	public void inicializarTabla() {
		// Cabecera del modelo de datos
		Vector<String> cabeceraExcursion = new Vector<String>(
				Arrays.asList("ID", "NOMBRE", "TIPO EXCUSION", "LUGAR", "PRECIO","EDAD", "DURACION(HORAS)", "NUMERO DE PERSONAS"));
		// Se crea el modelo de datos para la tabla de comics s�lo con la cabecera
		modeloExcursion = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraExcursion) {
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
		// Se crea la tabla de comics con el modelo de datos
		tablaExcursion= new JTable(modeloExcursion);
		// cargo el modelo
		
		cargarModeloTabla();

		tablaExcursion.setRowHeight(40);

		// Se cambia la anchura de las columnas
		tablaExcursion.getColumnModel().getColumn(0).setPreferredWidth(100);//id
		tablaExcursion.getColumnModel().getColumn(1).setPreferredWidth(300);//nombre
		tablaExcursion.getColumnModel().getColumn(2).setPreferredWidth(300);//tipo
		tablaExcursion.getColumnModel().getColumn(3).setPreferredWidth(100);//lugar
		tablaExcursion.getColumnModel().getColumn(4).setPreferredWidth(200);//precio
		tablaExcursion.getColumnModel().getColumn(5).setPreferredWidth(200);//edad
		tablaExcursion.getColumnModel().getColumn(6).setPreferredWidth(200);//duracion
		tablaExcursion.getColumnModel().getColumn(7).setPreferredWidth(300);//numero personas
		// Se modifica el modelo de selecci�n de la tabla para que se pueda selecciona
		// �nicamente una fila
		tablaExcursion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaExcursion.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// TODO Auto-generated method stub
				//Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				this.setText(value.toString());
				if(column == 5) {
					String edad = (String) table.getModel().getValueAt(row, 5);
			
					if(edad.contains("Todas las edades")) {
						this.setForeground(Color.GREEN);
					}else {
						this.setForeground(Color.RED);
					}
				}else {
					this.setForeground(Color.BLACK);
				}
				
				return this;
			}
		});
			
		}
	
	/**
	 * Método que carga el modelo de la JTable a partir de la BD
	 */
	public void cargarModeloTabla() {
		//Connection con = BD.initBD("confortTravel.db");

		try {

			ArrayList<Excursion> lista = BD.obtenerExcursiones();
			System.out.println("Número de excursiones: "+lista.size());
			while (modeloExcursion.getRowCount() > 0) {
				modeloExcursion.removeRow(0);
			}
			/*for (int i = 0; i < listaAlojamientos.size(); i++) {
				   Object[] fila = new Object[6];
				   fila[0]= listaAlojamientos.get(i).getId();
				   fila[1]= listaAlojamientos.get(i).getNombre_comp();
				   fila[2]= listaAlojamientos.get(i).getTalojamiento();
				   fila[3]= listaAlojamientos.get(i).getPrecio();
				   fila[4]= listaAlojamientos.get(i).getDuracion();
				   fila[5]= listaAlojamientos.get(i).getCiudad().getId();
				   modeloAlojamiento.addRow(fila);
				   System.out.println("Cargada fila "+i);
				   System.out.println("CARGANDO MODELO DE LA TABLA...");
			}*/
			for(Excursion ex :lista) {
				Object [] fila = {ex.getId(),ex.getNombre(),ex.getTipo(),ex.getLugar().getNombre(),ex.getPrecio(),ex.getEdad(),ex.getDuracion(),ex.getNumPersonas()};
				modeloExcursion.addRow(fila);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//BD.closeBD(con);

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