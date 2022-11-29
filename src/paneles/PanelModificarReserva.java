package paneles;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelModificarReserva extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JPanel panelArriba, panelCentro, panelCentroCentro, panelCentroAbajo;
	private JLabel lblModificarReserva, lblIntroducirID;
	private JScrollPane scrollPane;
	private JButton btnModificarID;
	
	public PanelModificarReserva() {
		setLayout(new BorderLayout(0, 0));
		
		panelArriba = new JPanel();
		add(panelArriba, BorderLayout.NORTH);
		
		lblModificarReserva = new JLabel("Modificar Reserva");
		panelArriba.add(lblModificarReserva);
		
		panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(3, 0, 0, 0));
		
		scrollPane = new JScrollPane();
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
	}
	
	

}
