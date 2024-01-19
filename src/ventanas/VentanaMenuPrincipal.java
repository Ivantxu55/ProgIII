package ventanas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import datos.Concierto;
import datos.Festival;
import datos.ParseCSV;


public class VentanaMenuPrincipal {
	
	private static final long serialVersionUID = 1L;
	
	private JButton botonConcierto = new JButton("CONCIERTOS");
	private JButton botonFestival = new JButton("FESTIVALES");
	private JLabel labelLogo = new JLabel(new ImageIcon("img/logo - copia.png"));
	
	private JPanel panelMenuBar = new JPanel();
	private JPanel panelAnuncios = new JPanel();
	
	private JMenuBar menuBar1 = new JMenuBar();
	private JMenuBar menuBar2 = new JMenuBar();
	
	protected JLabel promo1;
	protected JLabel promo2;
	protected JLabel promo3;
	private JLabel espacioB0 = new JLabel("                                               ");
	private JLabel espacioB1 = new JLabel("                                               ");
	private JLabel espacioB2 = new JLabel("                                               ");
	
	protected static final Logger logger = Logger.getLogger(Main.class.getName());
	
	public VentanaMenuPrincipal(){
		
		logger.info(String.format("Accediendo a la ventana3."));
		
		//Lista de imagenes posibles
		List<String> imgs = new ArrayList<>(Arrays.asList(	"img/A3_Cartel_FESTIVAL_DE_JAZZ-scaled.png", 
															"img/Cartel-Festival-Flamenco-Benicassim-2023-707x1024.png", 
															"img/FESTIVAL-SONICA-2022.png",
															"img/cartel-coachella-2024.png",
															"img/lollapalooza-lineup-.png"));
		Collections.shuffle(imgs);
		promo1 = new JLabel(new ImageIcon(imgs.get(0)));
		promo2 = new JLabel(new ImageIcon(imgs.get(1)));
		promo3 = new JLabel(new ImageIcon(imgs.get(2)));
		
		JFrame frame = new JFrame("VentanaMenuPrincipal");
		frame.setSize(900, 600);
		ArrayList<Concierto> conciertos = ParseCSV.leerConciertos("resources/CSV/Conciertos.csv");
		ArrayList<Festival> festivales = ParseCSV.leerFestivales("resources/CSV/Festivales.csv", conciertos);
	
		//Se colocan los componentes en los JMenuBar
		frame.add(panelMenuBar);
		panelMenuBar.setLayout(new BorderLayout());
		panelMenuBar.setBounds(0, 0, 1250, 60);
		panelMenuBar.add(BorderLayout.NORTH, menuBar1);

		panelMenuBar.add(menuBar2);
		menuBar2.add(espacioB0);
		menuBar2.add(botonConcierto);
		menuBar2.add(espacioB1);
		menuBar2.add(botonFestival);
		menuBar2.add(espacioB2);
		menuBar2.add(labelLogo);

		//Se distribuyen los anuncios 
		panelAnuncios.setLayout(new GridLayout(1,3));
		frame.add(BorderLayout.CENTER, panelAnuncios);	
		
		panelAnuncios.add(promo1);
		panelAnuncios.add(promo2);
		panelAnuncios.add(promo3);
		
		//Para visualizar la ventana
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		//Actionlisteners para los distintos botones
		
		//Listener que te lleva a Festivales
		
		ActionListener actionFestival = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaFestival i= new VentanaFestival(festivales);
				frame.dispose();
				//IMPLEMENTAR DATOS DE FESTIVAL EN TABLA
				i.seguirBoton.setEnabled(true);
				}
		};
		
		botonFestival.addActionListener(actionFestival);

		//Listener que te lleva a Conciertos
		
		ActionListener actionConcierto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaConciertos i= new VentanaConciertos(conciertos);
				frame.dispose();
				//IMPLEMENTAR DATOS DE CONCIERTO EN TABLA
				i.seguirBoton.setEnabled(true);
				}
		};
		
		botonConcierto.addActionListener(actionConcierto);
		
	};
	
	public static void main(String[] args) {
		VentanaMenuPrincipal ventana3 = new VentanaMenuPrincipal();
	}
}
