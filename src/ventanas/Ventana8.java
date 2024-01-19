package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Ventana8 extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public Ventana8() {
		
		// Configuración de la ventana.
		setTitle("Agradecimiento");
		setSize(400, 350);
		setLocation(650, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Creación de componentes de la ventana y su configuración.
		JLabel imagenAgradecimiento = new JLabel(new ImageIcon("img/logo.png"));
		
		JTextArea txtAgradecimiento1 = new JTextArea("Entrada comprada con éxito.");
		txtAgradecimiento1.setEditable(false);
		txtAgradecimiento1.setOpaque(false);
		
		JTextArea txtAgradecimiento2 = new JTextArea("Muchas gracias por confiar en nosotros.");
		txtAgradecimiento2.setEditable(false);
		txtAgradecimiento2.setOpaque(false);
		
		JButton btnVolverPrincipal = new JButton("Volver a la página principal");
		
		// Creación de contenedores de la ventana y su configuración.
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0.2;
		
		panel.add(imagenAgradecimiento, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.SOUTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		
		panel.add(txtAgradecimiento1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NORTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		
		panel.add(txtAgradecimiento2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx = 0.5;
		gbc.weighty = 0.1;
		
		panel.add(btnVolverPrincipal, gbc);
		
		// Asignación de los componentes a los contenedores.
		
		add(panel);
		
		// Configuración de la ventana.
		setVisible(true);
		
		// Configuración de los eventos.
		
		btnVolverPrincipal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VentanaMenuPrincipal v3 = new VentanaMenuPrincipal();
				dispose();
				
			}
		});
		
		
	}
	
	public static void main(String[] args) {
		Ventana8 v = new Ventana8();
	}
	
}

