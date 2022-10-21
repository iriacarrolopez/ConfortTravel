package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaLogin extends JFrame {

	private JPanel contentPane, panelIzq, panelCentro;
	private JButton btnInicioSesion;
	private JButton btnRegistrar;
	private JLabel lblTitulo;
	private JPanel panelDerecha;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 550);;
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		/*
		 * Paneles
		 */
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		panelIzq = new JPanel();
		panelIzq.setBackground(SystemColor.textHighlight);
		contentPane.add(panelIzq);
		//panelIzq.setLayout(new GridLayout(0, 1, 0, 0));
		panelIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panelCentro = new JPanel();
		panelCentro.setBackground(SystemColor.window);
		contentPane.add(panelCentro);
		panelCentro.setLayout(new GridLayout(3, 0, 0, 0));
		
		
		
		//titulo
		
		lblTitulo = new JLabel("BIENVENIDO A COMFORTRAVEL");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBackground(SystemColor.text);
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panelCentro.add(lblTitulo);
		
		//BOTONES
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelCentro.add(btnRegistrar);	
		btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ir a la ventana de registro
			}
		});
		btnInicioSesion = new JButton("INICIO SESION");		
		btnInicioSesion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelCentro.add(btnInicioSesion);
		
		panelDerecha = new JPanel();
		panelDerecha.setBackground(SystemColor.textHighlight);
		contentPane.add(panelDerecha);
		
		btnInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ir a la ventana de inicio
			}
		});
		
		
	
	
	}

}
