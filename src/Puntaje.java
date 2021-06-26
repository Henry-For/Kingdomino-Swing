import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.print.DocFlavor.URL;
import javax.swing.DefaultListModel;
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
import javax.swing.JList;
import java.awt.Font;

public class Puntaje extends JPanel {

	private ArrayList<Jugador> jugadores;
	private DefaultListModel modelo = new DefaultListModel();
	public Puntaje() {
		setBackground(new Color(238, 232, 170));
		setForeground(Color.YELLOW);
		setLayout(null);

		JList list = new JList();
		list.setBounds(59, 47, 161, 141);
		add(list);
		list.setModel(modelo);
		
		JLabel lblNewLabel = new JLabel("PUNTAJES");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 27));
		lblNewLabel.setBounds(77, 11, 169, 25);
		add(lblNewLabel);

	}

	public void cargarJugadoresEnPuntajes(ArrayList<Jugador> jugadores) {
		modelo.removeAllElements();
		modelo.addElement("NICKNAME             PUNTAJE");
		for (Jugador jugador : jugadores) {
			modelo.addElement(jugador.toStringPuntajes());
		}
		
	}

}