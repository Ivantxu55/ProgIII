package ventanas;


import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import datos.Concierto;
import datos.Festival;
import datos.Genero;
import modelos.ModeloTablaConciertoCliente;
import modelos.ModeloTablaFestivalCliente;
import datos.ParseCSV;




public class VentanaInicial extends JFrame{
	
	private static final long serialVersionUID = 1L;


	
	public VentanaInicial(ArrayList<Concierto> conciertos, ArrayList<Festival> festivales) {
		
        JFrame frame = new JFrame("Conciertos y Festivales");

        // Configuración del frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Conciertos y Festivales");
        frame.setSize(900, 300);

        frame.setLayout(new GridLayout(2,1));

        // Añadir filas de datos

		
		
		ModeloTablaFestivalCliente modeloFest = new ModeloTablaFestivalCliente(festivales);
//		
        modeloFest.setFestivales(festivales);
		ModeloTablaConciertoCliente modeloConcierto = new ModeloTablaConciertoCliente(conciertos);
		modeloConcierto.setConciertos(conciertos);
		
        
        // Crear la tabla con el modelo de datos
        JTable tabla = new JTable(modeloConcierto);
        JTable tablaF = new JTable(modeloFest);
       

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabla);
        JScrollPane scrollPaneFest = new JScrollPane(tablaF);

        // Agregar el JScrollPane al frame
        frame.add(scrollPane);
        frame.add(scrollPaneFest);

        // Hacer visible el frame
        frame.setVisible(true);
        frame.setResizable(true);
        
        TableCellRenderer cellFestival = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			result.setBackground(table.getBackground());
			result.setForeground(table.getForeground());
			result.setHorizontalAlignment(JLabel.CENTER);
			

			
        	if(table.equals(tablaF)) {
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
        tablaF.setDefaultRenderer(Object.class, cellFestival);
		
        tabla.setDefaultRenderer(Object.class, cellConcierto);
        
	}
	public static Color randColor() {
		final float hue = new Random().nextFloat();
		// Saturation between 0.1 and 0.3
		final float saturation = (new Random().nextInt(2000) + 1000) / 10000f;
		final float luminance = 0.9f;
		final Color color = Color.getHSBColor(hue, saturation, luminance);
		return color;
	}

	public static void main(String[] args) {
		ArrayList<Concierto> c = ParseCSV.leerConciertos("resources/CSV/Conciertos.csv");
		ArrayList<Festival> f = ParseCSV.leerFestivales("resources/CSV/Festivales.csv", c);
		
		SwingUtilities.invokeLater(() -> new VentanaInicial(c, f));
	}

}