import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Sala sala;
	private Juego juego;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	
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

	public Principal() {
		
		this.sala = new Sala();
		//this.server = new Servidor(50000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		sala.setBorder(new EmptyBorder(5, 5, 5, 5));
		sala.setLayout(null);
		add(sala,BorderLayout.CENTER);
		sala.botonIniciarJuegoActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crearJuego();
			}
		});
		
		setContentPane(sala);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		setContentPane(juego);
	}
	
	public void crearJuego() {
		jugadores.add(new Jugador(sala.getTxtJugador_1().getText(), Color.RED));
		jugadores.add(new Jugador(sala.getTxtJugador_2().getText(), Color.BLUE));
		if(sala.getTxtJugador_3().isVisible())
			jugadores.add(new Jugador(sala.getTxtJugador_3().getText(), Color.GREEN));
		if(sala.getTxtJugador_4().isVisible())
			jugadores.add(new Jugador(sala.getTxtJugador_4().getText(), Color.YELLOW));
		

		this.juego = new Juego(jugadores);

		juego.iniciar();
		juego.setBorder(new EmptyBorder(5, 5, 5, 5));
		juego.setLayout(new BorderLayout(0, 0));
		add(juego,BorderLayout.CENTER);
		setContentPane(juego);
		
		ImageIcon backgroundImg=new ImageIcon(getClass().getResource("fondoMadera.jpg"));
		Image img=backgroundImg.getImage();
		Image newimg=img.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
		backgroundImg = new ImageIcon(newimg);
		JLabel background = new JLabel(backgroundImg);
		add(background);
	}
}
