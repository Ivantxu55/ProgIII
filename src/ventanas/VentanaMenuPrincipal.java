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
	private JButton botonLogo = new JButton(new ImageIcon("img/logo - copia.png"));
	//JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	private JButton botonCompra1 = new JButton("Comprar1");
	private JButton botonCompra2 = new JButton("Comprar2");
	private JButton botonCompra3 = new JButton("Comprar3");
	
	private JPanel anuncio1 = new JPanel();
	private JPanel anuncio2 = new JPanel();
	private JPanel anuncio3 = new JPanel();
	private JPanel panelMenuBar = new JPanel();
	private JPanel panelAnuncios = new JPanel();
	
	private JMenuBar menuBar1 = new JMenuBar();
	private JMenuBar menuBar2 = new JMenuBar();
	
	protected JLabel promo1 = new JLabel("Esto es una imagen1");
	protected JLabel promo2 = new JLabel("Esto es una imagen2");
	protected JLabel promo3 = new JLabel("Esto es una imagen3");
	private JLabel espacioB0 = new JLabel("                                               ");
	private JLabel espacioB1 = new JLabel("                                               ");
	private JLabel espacioB2 = new JLabel("                                               ");
	
	protected static final Logger logger = Logger.getLogger(Main.class.getName());
	
	public VentanaMenuPrincipal(){
		
		logger.info(String.format("Accediendo a la ventana3."));
		
		//escribirTXTPromos();
		
		JFrame frame = new JFrame("VentanaMenuPrincipal");
		frame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
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
		menuBar2.add(botonLogo);

		//Se distribuyen los anuncios 
		panelAnuncios.setLayout(new GridLayout(1,3));
		frame.add(BorderLayout.CENTER, panelAnuncios);
	
		//Se crea el anuncio1
		panelAnuncios.add(anuncio1);
		anuncio1.setLayout(new BorderLayout());
		anuncio1.add(promo1);
		anuncio1.add(BorderLayout.SOUTH, botonCompra1);
		
		//Se crea el anuncio2
		panelAnuncios.add(anuncio2);
		anuncio2.setLayout(new BorderLayout());
		anuncio2.add(promo2);
		anuncio2.add(BorderLayout.SOUTH, botonCompra2);		
		
		//Se crea el anuncio3
		panelAnuncios.add(anuncio3);
		anuncio3.setLayout(new BorderLayout());
		anuncio3.add(promo3);
		anuncio3.add(BorderLayout.SOUTH, botonCompra3);		
		
		//Para visualizar la ventana
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		//Actionlisteners para los distintos botones
		
//Descomentar cuando las ventanas se implementen {
//		
		//Listener que te lleva a Conciertos
//		ActionListener actionConcierto = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				frame.dispose();
//				// abre la ventana de los conciertos
//				Ventana4 vConciertos = new Ventana4();
//			}
//		};
//		
		//Listener que te lleva a Festivales
//
		ActionListener actionFestival = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaFestival i= new VentanaFestival(festivales);
				frame.dispose();
				//IMPLEMENTAR DATOS DE FESTIVAL EN TABLA
				i.PromocionarBoton.setEnabled(true);
				i.seguirBoton.setEnabled(true);
				i.esConcieto= false;
				}
		};
		
		botonFestival.addActionListener(actionFestival);
		// listener para el botoncompra1
//
		// Te lleva a la ventana en la que se encuentra el cotenido del anuncio con ese contenido marcado
//		
		// listener para el botoncompra2
//
		// Te lleva a la ventana en la que se encuentra el cotenido del anuncio con ese contenido marcado		
//
		// listener para el botoncompra3
//
		// Te lleva a la ventana en la que se encuentra el cotenido del anuncio con ese contenido marcado
//
//		//Se le añaden los listener a sus componentes
		
		ActionListener actionConcierto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaConciertos i= new VentanaConciertos(conciertos);
				frame.dispose();
				//IMPLEMENTAR DATOS DE CONCIERTO EN TABLA
				i.editarBoton.setEnabled(true);
				i.PromocionarBoton.setEnabled(true);
				i.seguirBoton.setEnabled(true);
				i.esConcieto= true;
				}
		};
		
		
		botonConcierto.addActionListener(actionConcierto);

		botonCompra1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		botonCompra2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		botonCompra3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	};
	/*
	public void leerTXTPromos() {
		 String ruta = "config\\promos.txt";
		 Path path = Paths.get(ruta);

	        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
	            String linea;
	            List<String> lineas = new ArrayList<>();
	            
	            while ((linea = br.readLine()) != null) {
	            	lineas.add(linea);
	                System.out.println(linea);
	            }
	           
        	   if (lineas.get(0).equals("Esto es una imagen1")) {
        		   
        	   } else {
        		   lineas.get(0).replace(lineas.get(0), promo1.getText());
        		   // Escribe la lista actualizada de vuelta al archivo
                   Files.write(path, lineas, StandardCharsets.UTF_8);
                   System.out.println("Línea reemplazada con éxito.");
        	   }
        	   
        	   if (lineas.get(1).equals("Esto es una imagen2")) {
        		   
        	   } else {
        		   lineas.get(1).replace(lineas.get(0), promo2.getText());
        	   }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public void escribirTXTPromos() {
        String nombreArchivo = "config\\promos.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribe en el archivo
            escritor.write(promo1.getText() + "\n");
            escritor.write(promo2.getText() + "\n");
            escritor.write(promo3.getText() + "\n");

            System.out.println("Contenido escrito en el archivo.");

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	*/
	public static void main(String[] args) {
		VentanaMenuPrincipal ventana3 = new VentanaMenuPrincipal();
	}
}
