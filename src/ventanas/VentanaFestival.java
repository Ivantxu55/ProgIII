package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.*;

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
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
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

public class VentanaFestival extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTable tablaFestivales;
	private JMenuBar menuFestival;

	private JTextField txtFiltro;

	protected JButton seguirBoton;
	
	protected static final Logger logger = Logger.getLogger(Main.class.getName());
	private Festival elegido;
	
	public VentanaFestival(ArrayList<Festival> festivales) {
		
		logger.info(String.format("Accediendo a ventana festivales."));

		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);

		menuFestival = new JMenuBar();
		this.setJMenuBar(menuFestival);

		txtFiltro = new JTextField();

		JPanel buscador = new JPanel(new GridBagLayout());
		
		JLabel buscadorLabel = new JLabel("Filtrar por nombre:    ");
		menuFestival.add(new JLabel(new ImageIcon("img/BannerFestivales.png")));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 20, 0, 0);
		gbc.weightx = 0.3;
		buscador.add(buscadorLabel, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.7;

		gbc.insets = new Insets(4, 0, 0, 0);

		buscador.add(txtFiltro, gbc);



		JCalendar calendar = new JCalendar();
		JPanel data = new JPanel();
		data.add(calendar);


		seguirBoton = new JButton("Seguir");
		JPanel panelBotonesFinal = new JPanel();
		
		ModeloTablaFestivalCliente tablamodelo = new ModeloTablaFestivalCliente(festivales);
		tablaFestivales = new JTable(tablamodelo);
		
		panelBotonesFinal.add(seguirBoton);
		tablaFestivales.setRowSelectionAllowed(true);
		

		ActionListener butonSeguirListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				int filaSeleccionada = tablaFestivales.getSelectedRow();
				elegido = festivales.get(filaSeleccionada);

				if (elegido!= null) {
					//TODO
					//CAMBIARLO CUANDO CONVENGA LA LLAMADA A SIGUIENTE VENTANA
					VentanaPago v = new VentanaPago(elegido.getNombre(), Tipo.Festival);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un festival para poder continuar.");
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
		
		tablaFestivales = new JTable(tablamodelo);
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

		this.tablaFestivales.setDefaultRenderer(Object.class, cellRenderer);

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
		panelTabla.add(new JScrollPane(tablaFestivales), BorderLayout.CENTER);
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
		
		this.setTitle("Ventana de festivales");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	
}
