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

	protected boolean esConcieto = true;
	private JTable tablaConciertos;
	private JMenuBar menuConcierto;
	private JMenu filtros;
	private JMenuItem nomCon, fechCon, artCon, genCon;
	private JTextField txtFiltro;

	protected JButton seguirBoton, editarBoton, PromocionarBoton;
	
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
		filtros = new JMenu("Filtros");

		JPanel panelVacio = new JPanel();
		txtFiltro = new JTextField();

		JPanel buscador = new JPanel(new GridBagLayout());
		
		JLabel buscadorLabel = new JLabel("Filtrar por nombre:    ");
		menuConcierto.add(filtros);
		menuConcierto.add(panelVacio);
		// Insets u = new Insets(0, 0, 0, 40);
		panelVacio.setBounds(0, 0, 220, buscadorLabel.getHeight());
		panelVacio.setBorder(new EmptyBorder(10, 10, 10, 300));
		panelVacio.setBackground(this.getBackground());
		menuConcierto.add(buscador);

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

		nomCon = new JMenuItem("Nombre");
		fechCon = new JMenu("Fecha");
		artCon = new JMenuItem("Artista");
		genCon = new JMenu("Género");
		filtros.add(nomCon);
		filtros.add(fechCon);

		JCalendar calendar = new JCalendar();
		JPanel data = new JPanel();
		data.add(calendar);
		fechCon.add(data);

		filtros.add(artCon);
		filtros.add(genCon);
		JCheckBoxMenuItem i;
		for (Genero genero : Genero.values()) {
			i = new JCheckBoxMenuItem(genero.name());
			genCon.add(i);
		}

		seguirBoton = new JButton("Seguir");
		editarBoton = new JButton("Editar");
		PromocionarBoton = new JButton("Promocionar");
		JPanel panelBotonesFinal = new JPanel();
		
		ModeloTablaConciertoCliente tablamodelo = new ModeloTablaConciertoCliente(conciertos);
		tablaConciertos = new JTable(tablamodelo);

		//panelBotonesFinal.add(editarBoton);
		editarBoton.setEnabled(false);
		panelBotonesFinal.add(PromocionarBoton);

		PromocionarBoton.setEnabled(false);
		// PromocionarBoton.setVisible(false);
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
						Ventana6 v = new Ventana6(elegido.getNombre(), Tipo.Concierto);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un concierto para poder continuar.");
					}
			}

		};

		seguirBoton.addActionListener(butonSeguirListener);

		ActionListener butonPromocionarListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (esConcieto == true) {
					// Prommocionar concierto
					tablaConciertos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							
							int filaSeleccionada = tablaConciertos.getSelectedRow();
							
							elegido = conciertos.get(filaSeleccionada);
							System.out.println(elegido);
							
						}
					});
					
					if(elegido != null) { 
						
						VentanaMenuPrincipal v = new VentanaMenuPrincipal();
						
						if (v.promo1.getText().equals("Esto es una imagen1") == true) {
							v.promo1.setText("img/FESTIVAL-SONICA-2022.png");
							v.promo1.setIcon(new ImageIcon("img/FESTIVAL-SONICA-2022.png"));
							
						} else if (v.promo2.getText().equals("Esto es una imagen2") == true) {
							v.promo2.setIcon(new ImageIcon("img/RUFWFZVEM3CX4PDVHIUCNM7ZE4.png"));
							
						} else if (v.promo3.getText().equals("Esto es una imagen3") == true) {
							v.promo3.setIcon(new ImageIcon("img/A3_Cartel_FESTIVAL_DE_JAZZ-scaled.png"));
							
						} else {
							
							ImageIcon img = (ImageIcon) v.promo1.getIcon();
							v.promo1.setIcon(new ImageIcon("img/Cartel-Festival-Flamenco-Benicassim-2023-707x1024.png"));
							ImageIcon img2 = (ImageIcon) v.promo2.getIcon();
							v.promo2.setIcon(img);
							v.promo3.setIcon(img2);
							
						}
						dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un concierto para promocionarlo.");
					}
					
				} else if (esConcieto == false) {
					JOptionPane.showMessageDialog(null, "Esa eleccion no es correcta. Seleccione un concierto");
			       
				}
			}
		};

		// Agregar ListSelectionListener solo si la tablaFestivales no es nula
		
		PromocionarBoton.addActionListener(butonPromocionarListener);

		ActionListener butonEditarListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
		        int filaSeleccionada = tablaConciertos.getSelectedRow();
		        if (filaSeleccionada != -1) {
		            // Obtener el modelo de la tabla
		            ModeloTablaFestivalCliente modelo = (ModeloTablaFestivalCliente) tablaConciertos.getModel();

		            // Obtener los datos de la fila seleccionada
		            Festival festivalSeleccionado = modelo.getFestival(filaSeleccionada);

		            // Realizar modificaciones en los datos
		            // Por ejemplo, cambiar el nombre del festival
		            festivalSeleccionado.setNombre("Nuevo Nombre");

		            // Actualizar el modelo de la tabla con los datos modificados
		            modelo.fireTableDataChanged();
		            
		        }
			}
		};

		editarBoton.addActionListener(butonEditarListener);
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


//		ModeloTablaFestivalCliente tablamodelo = new ModeloTablaFestivalCliente(festivales);
		tablaConciertos = new JTable(tablamodelo);
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());

			// Si el valor es de tipo Editorial: se renderiza con la imagen centrada
			/*
			 * if (value instanceof Genero) { Genero e = (Genero) value;
			 * 
			 * result.setText(""); result.setToolTipText(e.toString());
			 * result.setHorizontalAlignment(JLabel.CENTER);
			 * 
			 * switch (e) { case HIP_HOP: result.setIcon(new ImageIcon("")); break; case
			 * BLUES: result.setIcon(new ImageIcon("")); break; default: }
			 * 
			 * }
			 */

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
		// gbc.anchor= GridBagConstraints.CENTER;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 0);

		p.add(panelTablaRight);
		// panelTablaRight.setBackground(Color.RED);
		p.add(panelTablaRight, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		// gbc.anchor= GridBagConstraints.LINE_START;
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
