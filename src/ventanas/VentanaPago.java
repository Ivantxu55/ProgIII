package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import bd.DBManager;
import datos.*;

public class VentanaPago extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public VentanaPago(Object elegido) {
		
		// Configuración de la ventana.
		setTitle("Pagar");
		setSize(750, 300);
		setLocation(450, 250);;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Creación de contenedores de la ventana y su configuración.
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JTextArea area = new JTextArea();
		area.setEditable(false);

		area.setLineWrap(true);
		JLabel lTotal = new JLabel("");

		if(elegido instanceof Concierto) {
			area.setText(((Concierto) elegido).toStringPers());
			lTotal.setText("Total:" + ((Concierto) elegido).getPrecio() + "€");
		}else if(elegido instanceof Festival) {
			area.setText(((Festival) elegido).toStringPers());
			lTotal.setText("Total:" + ((Festival) elegido).getPrecio() + "€");
		}
		
		JLabel lMetodoPago = new JLabel("Métodos de pago: ");
		JComboBox<String> cbMetodoPago = new JComboBox<String>();
		for (TipoTarjeta tipoT: TipoTarjeta.values()) {
			cbMetodoPago.addItem(tipoT.toString());
		
		}
		
		JLabel lNombre = new JLabel("Nombre:");
		JTextField txtNombre = new JTextField();
		JLabel lTarjeta = new JLabel("Número de tarjeta:");
		JPasswordField txtTarjeta = new JPasswordField();
		txtTarjeta.setSize(10, 10);
		JButton btnPagar = new JButton("Pagar");
		
		// Asignación de los componentes a los contenedores.
				
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 6;
		gbc.weighty = 1;
		
		panel.add(area, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.05;
		gbc.weighty = 0.2;
		
		panel.add(lTotal, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.EAST;
		gbc.weightx = 0.01;
		gbc.weighty = 0;
		
		panel.add(lNombre, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.weighty = 0;
		
		panel.add(txtNombre, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.EAST;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		panel.add(lMetodoPago, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		panel.add(cbMetodoPago, gbc);

		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.EAST;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		panel.add(lTarjeta, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0.2;
		
		panel.add(txtTarjeta, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx = 0;
		gbc.weighty = 0.2;
		
		panel.add(btnPagar, gbc);
		
		add(panel);
		
		// Configuración de la ventana.
		setVisible(true);
		
		// Configuración de los eventos.
		btnPagar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if (true) {
					Long num = Long.getLong(String.valueOf(txtTarjeta.getPassword()));
					Cliente c = new Cliente(TipoTarjeta.valueOf((String)cbMetodoPago.getSelectedItem()) , txtNombre.getText(), num);
					c.store();
					VentanaCondiciones v = new VentanaCondiciones();
					dispose();
					
				}
			}
		});
		
	}
	
	
}