import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


public class Principal extends JFrame implements ActionListener {

	private Sala sala;
	private Juego juego;

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
		this.juego = new Juego(sala.getCantJugadores());
		juego.crearJuego();
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
