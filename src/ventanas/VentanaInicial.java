/*
package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import files.InglesProperties;
public class VentanaInicial {
	
	public VentanaInicial(){
	//creation of the window
	JFrame frame = new JFrame("Ventana 1");
	frame.setSize((int)Toolkit. getDefaultToolkit(). getScreenSize().getWidth()/2, (int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight()/2);
	
	//creation of the panel
	JPanel panelGeneral = new JPanel();
	frame.add(panelGeneral);
	panelGeneral.setLayout(new BorderLayout());
	JPanel panelBotones = new JPanel();
	
	panelBotones.setLayout(new GridBagLayout());
	
	//Making of the components inside of the window 
			JMenuBar menuBar= new JMenuBar();
			JMenu menuIdiomas= new JMenu("Menu de idiomas");
			
			JPanel panelPane= new JPanel(new BorderLayout(0,3));
			
			JLabel avisoError = new JLabel("Contraseña incorrecta", SwingConstants.CENTER);
			avisoError.setForeground(frame.getBackground());
			JButton buttonseguirPane = new JButton("Seguir");
			//JPasswordField pwf= new JPasswordField(10);
			JTextField pwf= new JTextField(10);
			
			panelPane.add(buttonseguirPane, BorderLayout.SOUTH);
			panelPane.add(avisoError, BorderLayout.CENTER);
			panelPane.add(pwf, BorderLayout.NORTH);
	
			Object[] componentsoptionPaneVentana2 = {panelPane};
			
	//Creating the Action Listeners
	
	
	ActionListener actionButtonAdministrador = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showOptionDialog(frame, "Insertar contraseña:", "Ventana 2", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, componentsoptionPaneVentana2, null);	
		}};	
		
	ActionListener actionButtonCliente = new ActionListener(){
	
		@Override
		public void actionPerformed(ActionEvent e) {
			Ventana_3 v = new Ventana_3();
			frame.dispose();
			//Añadir ventana de anuncios	
		}};
		ActionListener botonSeguirPaneAction = new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(pwf.getText().toString().equals("11")) {
				Ventana_3 v = new Ventana_3();
				v.esAdmin= true;
				frame.dispose();
				//Pasar a la siguente ventana(La ventana de concierto administrador)
				}else {
					avisoError.setForeground(Color.DARK_GRAY);
				}}};
			buttonseguirPane.addActionListener(botonSeguirPaneAction);
	
	//Creation of buttons and assigning listeners to them 
	JButton buttonAdministrador = new JButton("Administrador");
	buttonAdministrador.addActionListener(actionButtonAdministrador);
	JButton buttonCliente = new JButton("     Cliente     ");
	
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridx=1;
	gbc.gridy=3;
	gbc.gridwidth=  1;
	gbc.gridheight= 1;
	gbc.anchor = GridBagConstraints.CENTER;
	gbc.weightx= 0.5;
	gbc.weighty= 0;
	
	panelBotones.add(buttonCliente, gbc);
	
	gbc.gridx=1;
	gbc.gridy= 4;
	gbc.gridwidth= 1;
	gbc.gridheight=1;
	//gbc.fill=GridBagConstraints.NORTH;
	
	gbc.insets= new Insets(20,0,0,0);
	gbc.anchor = GridBagConstraints.CENTER;
	gbc.weightx= 0.5;
	gbc.weighty= 0;
	panelBotones.add(buttonAdministrador, gbc);
	
	buttonAdministrador.addActionListener(actionButtonAdministrador);
	buttonCliente.addActionListener(actionButtonCliente);
	
	//Creation of the items inside of the menu
	JMenuItem menuItemIngles= new JMenuItem("English");
	
	JMenuItem menuItemEuskera= new JMenuItem("Euskera");
	JMenuItem menuItemCastellano= new JMenuItem("Castellano");
	
	//Adding the items inside the menu
	menuIdiomas.add(menuItemIngles);
	menuIdiomas.add(menuItemEuskera);
	menuIdiomas.add(menuItemCastellano);
	
	//adding the menu to menubar
	menuBar.add(menuIdiomas);
	
	//adding menubar to panel
	panelGeneral.add(menuBar, BorderLayout.PAGE_START);
	panelGeneral.add(panelBotones, BorderLayout.CENTER );
	
	//Setting the basics in the window
	//JFrame.setDefaultLookAndFeelDecorated(true);
	
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setResizable(false);
	
	}	
	
}
*/