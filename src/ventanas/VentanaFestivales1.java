package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import datos.Concierto;
import datos.Festival;
import datos.ParseCSV;
import datos.Tipo;
import modelos.ModeloTablaConciertoCliente;
import modelos.ModeloTablaFestivalCliente;

public class VentanaFestivales1 extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private JTable tablaFestival;
	private JScrollPane scrollTablaFestival;
	protected JButton seguirBoton;
	private JLabel labelLogo = new JLabel(new ImageIcon("img/logo - copia.png"));
	private JLabel labelLogoConciertos = new JLabel(new ImageIcon("img/BannerFestivales.png"));
	private JMenuBar menuBar;
	
	protected static final Logger logger = Logger.getLogger(Main.class.getName());
	private Festival elegido;
	
	public VentanaFestivales1(ArrayList<Festival> festivales) {
		logger.info(String.format("Has entrado a festivales"));
		
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
		
		ModeloTablaFestivalCliente tablamodelo = new ModeloTablaFestivalCliente(festivales);
		tablaFestival = new JTable(tablamodelo);
		tablaFestival.setRowSelectionAllowed(true);
		
		tablaFestival = new JTable(tablamodelo);
		
		scrollTablaFestival = new JScrollPane(tablaFestival);
		
		panelCentro.add(scrollTablaFestival);
		
		seguirBoton = new JButton("Seguir");
		panelSur.add(seguirBoton);
		
		add(panelCentro, BorderLayout.CENTER);
		add(panelSur, BorderLayout.SOUTH);

		// Configuración de los eventos.
		
		seguirBoton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					int filaSeleccionada = tablaFestival.getSelectedRow();
					elegido = festivales.get(filaSeleccionada);

					if (elegido!= null) {
						//TODO
						//CAMBIARLO CUANDO CONVENGA LA LLAMADA A SIGUIENTE VENTANA
						VentanaPago v = new VentanaPago(elegido);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un festival para poder continuar.");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Seleccione un festival para poder continuar.");
				}
				
			}
		});
		
        TableCellRenderer cellFestival = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			result.setBackground(table.getBackground());
			result.setForeground(table.getForeground());
			result.setHorizontalAlignment(JLabel.CENTER);
			
        	if(table.equals(tablaFestival)) {
    				result.setBackground(randColor());
    			
    			if (value instanceof Float) {
            		result.setHorizontalAlignment(JLabel.LEFT);
            		result.setText(value.toString() + "€");
            	
    			}
    			
    			if(isSelected) {
    				result.setBackground(randColor().darker());
    			}
        	}
        	result.setOpaque(true);
        	return result;
        	
        };
        tablaFestival.setDefaultRenderer(Object.class, cellFestival);
		
		// Configuración de la ventana.
		setVisible(true);
	}
	
	public static Color randColor() {
		final float hue = new Random().nextFloat();
		// Saturation between 0.1 and 0.3
		final float saturation = (new Random().nextInt(2000) + 1000) / 10000f;
		final float luminance = 0.9f;
		final Color color = Color.getHSBColor(hue, saturation, luminance);
		return color;
	}
}
