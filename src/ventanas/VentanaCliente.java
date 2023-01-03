package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import bd.BD;
import clases.Reserva;
import clases.TipoPersona;
import paneles.PanelAlojamiento;
import paneles.PanelAniadirReserva;
import paneles.PanelDestino;
import paneles.PanelEliminarReserva;
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
	private JLabel lblInfor, lblTitulo;
	private JButton btnSalir, btnVolver;
	public VentanaLogin ventanaLogin;
	public PanelReserva pr;
	private JTree tree;
	//private JComboBox<String> comboAn;
	//private JButton btnResguardo;
	//private JButton btnFactura;
	//private PanelAniadirReserva par;
	//private PanelEliminarReserva per;
//	private PanelModificarReserva pmr;
	private JLabel lblHora;

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
		frame.setBounds(40, 50, 1010, 790);
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
					nodo = new DefaultMutableTreeNode("Importe de la reserva");
						nodo.add(new DefaultMutableTreeNode("Imprimir"));
					add(nodo);
					nodo = new DefaultMutableTreeNode("Resguardo");
							nodo.add(new DefaultMutableTreeNode("Imprimir"));
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
						PrintWriter pw = new PrintWriter(new FileOutputStream("Factura.txt", false));
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
				 }
            
			}
		});
		panelIzq.add(tree);
		
		lblTitulo = new JLabel("Cliente");
		lblTitulo.setFont(new Font("Verdana Pro Cond Black", Font.BOLD | Font.ITALIC, 15));
		panelNorte.add(lblTitulo);
		
		pr = new PanelReserva();
		
		/*par = new PanelAniadirReserva();
		per = new PanelEliminarReserva();
		pmr = new PanelModificarReserva();
		
		comboAn = new JComboBox<>();
		comboAn.setBackground(UIManager.getColor("CheckBox.background"));
		comboAn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelInformacion.add(comboAn);
		comboAn.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Opciones:", "AÃ±adir", "Modificar", "Eliminar" }));
		comboAn.setEnabled(true);
		
		btnResguardo = new JButton("Resguardo");
		panelIzq.add(btnResguardo);
		
		btnFactura = new JButton("Factura");
		panelIzq.add(btnFactura);
		
		btnSalir = new JButton("Salir");
		panelSur.add(btnSalir);

		btnVolver = new JButton("Volver a la pagina principal");
		panelSur.add(btnVolver);
		
		comboAn.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == comboAn) {
					String item = comboAn.getSelectedItem().toString();
					
					if("AÃ±adir".equals(item)) {
						
						panelPrincipal.removeAll();
						panelPrincipal.add(par);
						panelPrincipal.updateUI();
						
					}else if("Modificar".equals(item)) {

						panelPrincipal.removeAll();
						panelPrincipal.add(pmr);
						panelPrincipal.updateUI();
						
					}else if("Eliminar".equals(item)) {

						panelPrincipal.removeAll();
						panelPrincipal.add(per);
						panelPrincipal.updateUI();
						
					}else {
						panelPrincipal.removeAll();
						panelPrincipal.updateUI();
					}
				}
			}
		});	
		
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ventanaLogin = new VentanaLogin();
				// ventanalogin.setVisible(true);
				frame.dispose();

			}
		});

		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});*/
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

}
