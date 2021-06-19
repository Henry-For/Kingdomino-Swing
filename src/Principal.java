import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame implements ActionListener {

	private Sala sala = new Sala();
	private Juego juego = new Juego();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		//sala = new Sala();
		sala.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(sala);
		sala.setLayout(null);
		add(sala,BorderLayout.CENTER);
		sala.botonIniciarJuegoActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setContentPane(juego);
				//pack();
			}
		});
		
		//juego = new Juego();
		juego.setBorder(new EmptyBorder(5, 5, 5, 5));
		juego.setLayout(new BorderLayout(0, 0));
		add(juego,BorderLayout.CENTER);
		setContentPane(sala);
		//pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		setContentPane(juego);
		
	}
}
