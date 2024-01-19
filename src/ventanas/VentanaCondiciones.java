package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


public class VentanaCondiciones extends JFrame{

	private static final long serialVersionUID = 1L;
	private JCheckBox check;
	private JButton seguir;
	private JEditorPane contrato;
	
	public VentanaCondiciones(){
		
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;		
		contrato = new JEditorPane();
        contrato.setContentType("text/html");
		contrato.setEditable(false);
		contrato.setText("    <header>" + "        <h1>Términos y Condiciones de Deusticket</h1>" + "    </header>" + "    <section>" + "        <h2>1. Aceptación de Términos</h2>" + "        <p>Al utilizar la aplicación Deusticket, usted acepta los siguientes términos y condiciones.</p>" + "    </section>" + "    <section>" + "        <h2>2. Uso de la Aplicación</h2>" + "        <p>Usted se compromete a utilizar Deusticket de acuerdo con las leyes y regulaciones aplicables y no para fines ilegales o no autorizados.</p>" + "    </section>" + "    <section>" + "        <h2>3. Privacidad</h2>" + "        <p>Nuestra política de privacidad detalla cómo recopilamos, utilizamos y compartimos su información personal.</p>" + "    </section>" + "    <section>" + "        <h2>4. Contenido del Usuario</h2>" + "        <p>Usted es responsable del contenido que publique en la aplicación y acepta no publicar contenido ofensivo o ilegal.</p>" + "    </section>" + "    <section>" + "        <h2>5. Modificaciones</h2>" + "        <p>Deusticket se reserva el derecho de modificar estos términos en cualquier momento. Las modificaciones serán efectivas una vez publicadas.</p>" + "    </section>" + "    <section>" + "        <h2>6. Terminación</h2>" + "        <p>Podemos dar por terminado su acceso a Deusticket en cualquier momento si viola estos términos o por cualquier otro motivo sin previo aviso.</p>" + "    </section>" + "    <section>" + "        <h2>7. Limitación de Responsabilidad</h2>" + "        <p>No nos hacemos responsables de pérdidas o daños resultantes del uso de Deusticket.</p>" + "    </section>" + "    <section>" + "        <h2>8. Ley Aplicable</h2>" + "        <p>Estos términos se rigen por las leyes del Estado ficticio de Deustonia.</p>" + "    </section>" + "    <section>" + "        <h2>9. Contacto</h2>" + "        <p>Si tiene alguna pregunta o inquietud, póngase en contacto con nosotros eninfo@deusticket.com.</p>" + "    </section>" + "    <footer>" + "        <p>Última actualización: 19 de octubre de 2023</p>" + "    </footer>" 
		);
		JScrollPane scroll= new JScrollPane(contrato);
		scroll.setSize(WIDTH, HEIGHT-50);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 0;
		p.add(scroll, c);
		
		check = new JCheckBox("Acepto las condiciones");
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		p.add(check, c);
		
		seguir = new JButton("Seguir");
		c.fill = GridBagConstraints.SOUTH;
		c.weightx = 0.2;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		p.add(seguir, c);
		seguir.setEnabled(false);
		check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(check.isSelected()) {
					seguir.setEnabled(true);
				}
				
			}
		});
		
		add(p);
		seguir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ventana8 v = new Ventana8();
				dispose();
				
			}
		});

		
		this.setTitle("Condiciones de la compra");		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setSize(500, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VentanaCondiciones());
	}


}
