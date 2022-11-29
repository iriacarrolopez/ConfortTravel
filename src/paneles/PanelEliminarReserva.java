package paneles;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PanelEliminarReserva extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextField textId;
	private JPanel panelArriba, panelCentro, panelCentroAbajo;
	private JScrollPane scrollPane;
	private JLabel lblEliminarReserva, lblIDEliminar;
	private JButton btnEliminar;
	
	public PanelEliminarReserva() {
		
		setLayout(new BorderLayout(0, 0));
		
		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);
		
		lblEliminarReserva = new JLabel("Eliminar Reserva");
		panelArriba.add(lblEliminarReserva);
		
		panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		panelCentro.add(scrollPane);
		
		panelCentroAbajo = new JPanel();
		panelCentro.add(panelCentroAbajo);
		
		lblIDEliminar = new JLabel("Introduce el ID de la reserva a eliminar:");
		panelCentroAbajo.add(lblIDEliminar);
		
		textId = new JTextField();
		panelCentroAbajo.add(textId);
		textId.setColumns(10);
		
		btnEliminar = new JButton("Eliminar");
		panelCentroAbajo.add(btnEliminar);
	}
	
	

}
