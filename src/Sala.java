import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import javafx.scene.image.Image;

import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class Sala extends JPanel {
	private JTextField txtJugador_1;
	private JTextField txtJugador_2;
	private JTextField txtJugador_3;
	private JTextField txtJugador_4;

	/**
	 * Create the panel.
	 */
 
    
	public Sala() {
		setBackground(new Color(238, 232, 170));
		setForeground(Color.YELLOW);
		setLayout(null);
		
		JButton btnNewButton = new JButton("COMENZAR JUEGO");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(273, 250, 146, 39);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Selecione la cantidad de jugadores : ( 2 - 4) ");
		lblNewLabel.setBounds(10, 33, 242, 24);
		add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				
				if((Integer)spinner.getValue() == 2) {
					txtJugador_3.setVisible(false);
					txtJugador_4.setVisible(false);
				}
				
				if((Integer)spinner.getValue() == 3) {
					txtJugador_3.setVisible(true);
					txtJugador_4.setVisible(false);
				}
				if((Integer)spinner.getValue() == 4) {
					txtJugador_3.setVisible(true);
					txtJugador_4.setVisible(true);
				}
			}
		});
		spinner.setModel(new SpinnerNumberModel(2, 2, 4, 1));
		spinner.setBounds(374, 35, 45, 20);
		add(spinner);
		

		txtJugador_1 = new JTextField();
		txtJugador_1.setText("Jugador 1");
		txtJugador_1.setBounds(10, 68, 170, 20);
		add(txtJugador_1);
		txtJugador_1.setColumns(10);
		txtJugador_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println(txtJugador_1.getText());
			}
		});
		
		txtJugador_2 = new JTextField();
		txtJugador_2.setText("Jugador 2");
		txtJugador_2.setColumns(10);
		txtJugador_2.setBounds(10, 111, 170, 20);
		add(txtJugador_2);
		txtJugador_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println(txtJugador_2.getText());
			}
		});
		
		
		txtJugador_3 = new JTextField();
		txtJugador_3.setText("Jugador 3");
		txtJugador_3.setVisible(false);
		txtJugador_3.setColumns(10);
		txtJugador_3.setBounds(10, 155, 170, 20);
		add(txtJugador_3);
		txtJugador_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println(txtJugador_3.getText());
			}
		});
		
		
		txtJugador_4 = new JTextField();
		txtJugador_4.setText("Jugador 4");
		txtJugador_4.setVisible(false);
		txtJugador_4.setColumns(10);
		txtJugador_4.setBounds(10, 203, 170, 20);
		add(txtJugador_4);
		txtJugador_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println(txtJugador_4.getText());
			}
		});
		
		
	    }

	}

