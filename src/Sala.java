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

import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.awt.Color;

public class Sala extends JPanel {

	private JTextField txtJugador_1;
	private JTextField txtJugador_2;
	private JTextField txtJugador_3;
	private JTextField txtJugador_4;
	private JButton botonIniciarJuego = new JButton("COMENZAR JUEGO");
	private Integer cantJugadores;

	public Sala() {
		setBackground(new Color(238, 232, 170));
		setForeground(Color.YELLOW);
		setLayout(null);

		botonIniciarJuego.setBounds(273, 250, 146, 39);
		add(botonIniciarJuego);

		JLabel lblNewLabel = new JLabel("Selecione la cantidad de jugadores : ( 2 - 4) ");
		lblNewLabel.setBounds(10, 33, 242, 24);
		add(lblNewLabel);

		this.cantJugadores = 2;
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				txtJugador_3.setVisible(false);
				txtJugador_4.setVisible(false);

				if ((Integer) spinner.getValue() >= 3)
					txtJugador_3.setVisible(true);

				if ((Integer) spinner.getValue() == 4)
					txtJugador_4.setVisible(true);
				
				cargarVariableCantJugadores((Integer) spinner.getValue());

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

	public void botonIniciarJuegoActionListener(ActionListener listener) {
		botonIniciarJuego.addActionListener(listener);
	}

	public Integer getCantJugadores() {
		return cantJugadores;
	}
	
	public void cargarVariableCantJugadores(Integer cant) {
		this.cantJugadores= cant;
	}
	
	public JTextField getTxtJugador_1() {
		return txtJugador_1;
	}

	public JTextField getTxtJugador_2() {
		return txtJugador_2;
	}

	public JTextField getTxtJugador_3() {
		return txtJugador_3;
	}

	public JTextField getTxtJugador_4() {
		return txtJugador_4;
	}

}
