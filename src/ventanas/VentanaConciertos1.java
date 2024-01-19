package ventanas;

import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import datos.Concierto;
import datos.ParseCSV;
import datos.Tipo;
import modelos.ModeloTablaConciertoCliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConciertos1 extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private JTable tablaConciertos;
	private JScrollPane scrollTablaConciertos;
	protected JButton seguirBoton;
	private JLabel labelLogo = new JLabel(new ImageIcon("img/logo - copia.png"));
	private JLabel labelLogoConciertos = new JLabel(new ImageIcon("img/BannerConciertos.png"));
	private JMenuBar menuBar;
	
	protected static final Logger logger = Logger.getLogger(Main.class.getName());
	private Concierto elegido;
	
	public VentanaConciertos1(ArrayList<Concierto> conciertos) {
		
		logger.info(String.format("Has entrado a concieros."));
		
		// Configuración de la ventana.
		setTitle("Ventana conciertos 1");
		setSize(1000, 600);
		setLocation(300, 125);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Creación de contenedores de la ventana y su configuración.
		
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(1,1));
		
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new FlowLayout());
		
		// Asignación de los componentes a los contenedores.
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menuBar.add(labelLogoConciertos);
		
		ModeloTablaConciertoCliente tablamodelo = new ModeloTablaConciertoCliente(conciertos);
		tablaConciertos = new JTable(tablamodelo);
		tablaConciertos.setRowSelectionAllowed(true);
		
		tablaConciertos = new JTable(tablamodelo);
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());

			if (row % 2 == 0) {
				result.setBackground(new Color(250, 249, 249));
			}

			if (isSelected) {
				result.setBackground(table.getSelectionBackground());
				result.setForeground(table.getSelectionForeground());
			}

			result.setOpaque(true);

			return result;
		};

		this.tablaConciertos.setDefaultRenderer(Object.class, cellRenderer);
		
		scrollTablaConciertos = new JScrollPane(tablaConciertos);
		
		panelCentro.add(scrollTablaConciertos);
		
		seguirBoton = new JButton("Seguir");
		panelSur.add(seguirBoton);
		
		add(panelCentro, BorderLayout.CENTER);
		add(panelSur, BorderLayout.SOUTH);

		// Configuración de los eventos.
		
		seguirBoton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int filaSeleccionada = tablaConciertos.getSelectedRow();
				elegido = conciertos.get(filaSeleccionada);

				if (elegido!= null) {
					//TODO
					//CAMBIARLO CUANDO CONVENGA LA LLAMADA A SIGUIENTE VENTANA
					VentanaPago v = new VentanaPago(elegido);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un concierto para poder continuar.");
				}
			}
		});
		
		// Configuración de la ventana.
		setVisible(true);
	}
	
	public static void main(String[] args) {
		ArrayList<Concierto> c = ParseCSV.leerConciertos("resources/CSV/Conciertos.csv");
		VentanaConciertos1 v = new VentanaConciertos1(c);
	}
}
