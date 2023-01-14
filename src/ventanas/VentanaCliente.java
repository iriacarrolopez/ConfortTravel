package ventanas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import bd.BD;
import clases.Ciudad;
import clases.Excursion;
import clases.Reserva;
import clases.TipoPersona;
import paneles.PanelAlojamiento;

import paneles.PanelDestino;
import paneles.PanelExcursiones;
import paneles.PanelModificarReserva;
import paneles.PanelReserva;

public class VentanaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private JPanel contentPane, panelPrincipal, panelSur, panelIzq, panelNorte, panelInformacion;
	private JLabel lblInfor, lblTitulo, lblCiudad, lblPresupuesto;
	private JButton btnSalir, btnVolver, btnBuscar;
	public VentanaLogin ventanaLogin;
	public PanelReserva pr;
	private JTree tree;
	private DefaultTableModel modeloTablaExcursiones;
	private JTable tablaExcursiones;
	private JScrollPane scrollTablaExcursiones;
	private JComboBox<Ciudad> cbCiudades;
	private JTextField txtPresupuesto;
	//private JComboBox<String> comboAn;
	//private JButton btnResguardo;
	//private JButton btnFactura;
	//private PanelAniadirReserva par;
	//private PanelEliminarReserva per;
//	private PanelModificarReserva pmr;
	private JLabel lblHora;
	
	List<List<Excursion>> alCombinaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente window = new VentanaCliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaCliente() {
		frame = new JFrame();
		frame.setTitle("Ventana Cliente");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
		
		
		panelNorte = new JPanel();
		panelNorte.setBackground(new Color(0, 204, 255));
		contentPane.add(panelNorte, BorderLayout.NORTH);

		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(204, 204, 255));
		contentPane.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setBounds(100, 100, 700, 700);
		panelPrincipal.setLayout(new GridLayout(1, 0, 0, 0));

		panelIzq = new JPanel();
		panelIzq.setBackground(Color.WHITE);
		contentPane.add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new GridLayout(3, 0, 0, 0));
		
		lblTitulo = new JLabel("Cliente");
		lblTitulo.setFont(new Font("Verdana Pro Cond Black", Font.BOLD | Font.ITALIC, 15));
		panelNorte.add(lblTitulo);

		
		
		/*
		 * nuevo
		 */
		tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("CLIENTES") {
			
				private static final long serialVersionUID = 1L;

				{
					DefaultMutableTreeNode nodo;
					nodo = new DefaultMutableTreeNode("Coger Reserva");
						nodo.add(new DefaultMutableTreeNode("NuevaReserva"));
					add(nodo);
					nodo = new DefaultMutableTreeNode("Ver excursiones");
						nodo.add(new DefaultMutableTreeNode("Posibles excursiones"));
					add(nodo);
					nodo = new DefaultMutableTreeNode("Resguardo");
							nodo.add(new DefaultMutableTreeNode("Imprimir"));
					add(nodo);
					nodo = new DefaultMutableTreeNode("Fibonacci");
						nodo.add(new DefaultMutableTreeNode("ImprimirFibonacci"));
					add(nodo);
					nodo = new DefaultMutableTreeNode("Otras opciones");
						nodo.add(new DefaultMutableTreeNode("Salir"));
						nodo.add(new DefaultMutableTreeNode("Volver"));
					add(nodo);
						
				
	add(nodo);
				}
			}
		));
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				  DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				 String nodos =selectedNode.getUserObject().toString();
            
				 if("NuevaReserva".equals(nodos)) {
					 panelPrincipal.removeAll();
						panelPrincipal.add(pr);
						panelPrincipal.updateUI();
				 } else if ("Imprimir".equals(nodos)) {
					 
					 String dni = JOptionPane.showInputDialog(null, "Introduzca su dni");
					 ArrayList<Reserva> ar = new ArrayList<>();
					 ar = BD.obtenerReservasDni(dni);
					 for (Reserva r: ar) {
						 System.out.println(r.toString());
					 }
					 
					 try {
						PrintWriter pw = new PrintWriter(new FileOutputStream("Recursividad/Factura.txt", false));
						pw.println("Factura del cliente "+dni);
						pw.println();
						float precioTotal = 0;
						for (Reserva r: ar) {
							pw.println(r.getDestino()+" "+r.getFechaIni()+"   "+r.getPrecio()+" €");
							precioTotal = precioTotal + r.getPrecio();
						}
						pw.println("------------------");
						pw.println("TOTAL "+precioTotal+" €");
						pw.close();
					 } catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 } else if ("ImprimirFibonacci".equals(nodos)) {
					 
					 String dni = JOptionPane.showInputDialog(null, "Introduzca su dni");
					 ArrayList<Reserva> ar = new ArrayList<>();
					 ar = BD.obtenerReservasDni(dni);
					 for (Reserva r: ar) {
						 System.out.println(r.toString());
					 }
					 
					 try {
						PrintWriter pw = new PrintWriter(new FileOutputStream("Recursividad/Fibonacci.txt", false));
						pw.println("Fibonacci del cliente "+dni);
						pw.println();
						float n = 0;
						for (Reserva r: ar) {
							pw.println(r.getDestino()+" "+r.getFechaIni()+"   "+r.getPrecio()+" €");
							n = n + r.getPrecio();
							fibonacci(n);
						}
						pw.println("------------------");
						pw.println("FIBONACCI "+fibonacci(n)+" €");
						pw.close();
					 } catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 } else if ("Posibles excursiones".equals(nodos)) {
					 panelPrincipal.removeAll();
					String [] titulos = {"NOMBRE","TIPO","LUGAR","PRECIO","EDAD","DURACION","PLAZAS DISPONIBLES"};
					modeloTablaExcursiones = new DefaultTableModel();
					lblCiudad = new JLabel("Elige la ciudad: ");
					cbCiudades = new JComboBox<Ciudad>();
					ArrayList<Ciudad> aCiudades = BD.obtenerTodasCiudades();
					for (Ciudad c: aCiudades) {
						cbCiudades.addItem(c);
					}
					modeloTablaExcursiones.setColumnIdentifiers(titulos);
					tablaExcursiones = new JTable(modeloTablaExcursiones);
					scrollTablaExcursiones = new JScrollPane(tablaExcursiones);
					panelPrincipal.add(scrollTablaExcursiones);
					panelSur.add(lblCiudad);
					panelSur.add(cbCiudades);
					lblPresupuesto = new JLabel("Presupuesto");
					txtPresupuesto = new JTextField(10);
					panelSur.add(lblPresupuesto);
					panelSur.add(txtPresupuesto);
					
					btnBuscar = new JButton("BUSCAR");
					panelSur.add(btnBuscar);
					ArrayList<Excursion> alExcursiones = BD.obtenerExcursiones();
					for (Excursion x: alExcursiones) {
						Object [] row = {x.getNombre(),x.getTipo(),x.getLugarNombre(),x.getPrecio(),x.getEdad(),x.getDuracion(),x.getNumPersonas()};
						modeloTablaExcursiones.addRow(row);
					}
					//RENDER
					tablaExcursiones.setRowHeight(40);
					tablaExcursiones.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
						
						private static final long serialVersionUID = 1L;

						@Override
						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
								int row, int column) {
							// TODO Auto-generated method stub
							//Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							this.setText(value.toString());
							if(column == 4) {
								String edad = (String) table.getModel().getValueAt(row, 4);
						
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
					
					//boton buscar
					btnBuscar.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Ciudad ciudadDeseada = (Ciudad) cbCiudades.getSelectedItem();
							ArrayList<Excursion> listaExcursiones = BD.obtenerExcursionesPorDestino(ciudadDeseada);
							int filas = modeloTablaExcursiones.getRowCount();
							for (int i=0;i<filas;i++) {
								modeloTablaExcursiones.removeRow(0);
							}
							for (Excursion ex: listaExcursiones) {
								Object [] fila = {ex.getNombre(),ex.getTipo(),ex.getLugarNombre(),ex.getPrecio(),ex.getEdad(),ex.getDuracion(),ex.getNumPersonas()};
								modeloTablaExcursiones.addRow(fila);
							}
							double presupuesto = Double.parseDouble(txtPresupuesto.getText());
							alCombinaciones = combinaciones(listaExcursiones, presupuesto, 10);
							System.out.println(String.format("Combinaciones de menos de %.2f€", presupuesto));
							alCombinaciones.forEach(r -> System.out.println(r));
							try {
								PrintWriter pw = new PrintWriter(new FileOutputStream("Excursiones.txt", false));
								pw.println("Posibles excursiones en "+ciudadDeseada+" con un presupuesto de "+presupuesto+"€");
								pw.println();
								alCombinaciones.forEach(r -> pw.println(r));
								pw.close();
							 } catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							 }
						}
					});
					setVisible(false);
				
				}
			}

			private float fibonacci(float n) {
				// TODO Auto-generated method stub
				if (n>1)
				    return fibonacci(n-1) + fibonacci(n-2);  //función recursiva
				    else return n;
			}
		});
		panelIzq.add(tree);
		
		lblTitulo = new JLabel("Cliente");
		lblTitulo.setFont(new Font("Verdana Pro Cond Black", Font.BOLD | Font.ITALIC, 15));
		panelNorte.add(lblTitulo);
		
		pr = new PanelReserva();
		
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
		lblHora = new JLabel();
		panelNorte.add(lblHora);
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
						lblHora.setText(formateador.format(LocalDateTime.now()));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		};
		
		Thread hilo = new Thread(runnable);
		hilo.start();
		

	}
	
	public VentanaCliente(TipoPersona tipo) {
		VentanaInicio vi = new VentanaInicio();
		vi.dispose();
		frame.setVisible(true);
	}
	public static void combinaciones(List<List<Excursion>> result, List<Excursion> elementos, double disponible, double sobranteMax, List<Excursion> temp) {
		// Caso base. Si el importe disponible es negativo se detiene la recursividad
    	if (disponible < 0) {
        	return;
        // Caso base. Si el importe disponible es menor que el sobrante máximo
        } else if (disponible < sobranteMax) {
        	//Se reordena la lista temporal de productos para evitar combinaciones equivalentes
        	//Para reordenar se crea un Comparator de productos con una expresión lambda.
        	temp.sort((Excursion e1, Excursion e2) -> Integer.compare(e1.getId(), e2.getId()));
        	//Se añade la lista temporal si no se había añadido previamente.
        	if (!result.contains(temp)) {
            	//Se añade la lista temporal a la lista de resultados
                result.add(new ArrayList<>(temp));        	
        	}
        } else {
            // Caso recursivo. Por cada elemento        	
        	for(Excursion e : elementos) {
        		//Se añade el elemento a la lista temporal
        		temp.add(e);
        		//Se realiza la invocación recursiva en la que se va decrementado el importe disponible
        		combinaciones(result, elementos, disponible-e.getPrecio(), sobranteMax, temp);
        		//Se elimina el último de la lista temporal
        		temp.remove(temp.size()-1);
        	}
        }
	}
	
	public static List<List<Excursion>> combinaciones(List<Excursion> elementos, double disponible, double sobranteMax) {
    	//Se inicializa la lista de combinaciones que se devolverá como resultado.
    	List<List<Excursion>> result = new ArrayList<>();
    	//Se invoca al método recursivo
    	combinaciones(result, elementos, disponible, sobranteMax, new ArrayList<>());
    	//Se devuelve el resultado.
    	return result;
    }
}

