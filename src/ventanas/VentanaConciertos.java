package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JCalendar;

import datos.Concierto;
import datos.Festival;
import datos.Genero;
import datos.LoggerConfig;
import datos.Tipo;
import modelos.ModeloTablaConciertoCliente;
import modelos.ModeloTablaFestivalCliente;

public class VentanaConciertos  extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTable tablaConciertos;
	private JMenuBar menuConcierto;
	protected JButton seguirBoton;
	private JLabel labelLogo = new JLabel(new ImageIcon("img/logo - copia.png"));
	private JMenuBar menuBar1 = new JMenuBar();
	private JLabel espacioB0 = new JLabel("                                               ");
	
	protected static final Logger logger = Logger.getLogger(VentanaFestival.class.getName());
	private Concierto elegido;
	
    static {
        // Configurar el logger
        try {
            FileHandler fileHandler = new FileHandler(LoggerConfig.getProperty("log.filepath"));
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Configurar el nivel de registro
            Level logLevel = Level.parse(LoggerConfig.getProperty("log.level"));
            logger.setLevel(logLevel);
            fileHandler.setLevel(logLevel);
        } catch (IOException e) {
        }
    }
	
	public VentanaConciertos(ArrayList<Concierto> conciertos) {

		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);

		menuConcierto = new JMenuBar();
		this.setJMenuBar(menuConcierto);

		JPanel panelVacio = new JPanel();

		JPanel buscador = new JPanel(new GridBagLayout());
		
		menuConcierto.add(panelVacio);
		panelVacio.setBorder(new EmptyBorder(10, 10, 10, 300));
		panelVacio.setBackground(this.getBackground());
		menuConcierto.add(buscador);

		GridBagConstraints gbc = new GridBagConstraints();

		seguirBoton = new JButton("Seguir");
		JPanel panelBotonesFinal = new JPanel();
		
		ModeloTablaConciertoCliente tablamodelo = new ModeloTablaConciertoCliente(conciertos);
		tablaConciertos = new JTable(tablamodelo);
		
		panelBotonesFinal.add(seguirBoton);
		
		tablaConciertos.setRowSelectionAllowed(true);

		ActionListener butonSeguirListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					int filaSeleccionada = tablaConciertos.getSelectedRow();
					elegido = conciertos.get(filaSeleccionada);

					if (elegido!= null) {
						//TODO
						//CAMBIARLO CUANDO CONVENGA LA LLAMADA A SIGUIENTE VENTANA
						VentanaPago v = new VentanaPago(elegido.getNombre(), Tipo.Concierto);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un concierto para poder continuar.");
					}
			}

		};

		seguirBoton.addActionListener(butonSeguirListener);

		JPanel panelInicial = new JPanel();

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.15;
		gbc.weighty = 0.2;
		gbc.insets = new Insets(0, 0, 0, 0);

		p.add(panelInicial);
		p.add(panelInicial, gbc);

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

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.6;
		gbc.weighty = 0.6;
		gbc.insets = new Insets(0, 0, 0, 0);

		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(new BorderLayout());
		panelTabla.add(new JScrollPane(tablaConciertos), BorderLayout.CENTER);
		p.add(panelTabla, gbc);
		
		JPanel panelTablaRight = new JPanel();

		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 0);

		p.add(panelTablaRight);
		p.add(panelTablaRight, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.0;
		gbc.weighty = 0.2;
		gbc.insets = new Insets(0, 0, 0, 0);

		p.add(panelBotonesFinal, gbc);
		
		this.setTitle("Ventana de conciertos");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	
}
