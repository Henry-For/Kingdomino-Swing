import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Juego extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable[] tableros;
	
	private JTable pilaAct;
	private JTable pilaSig;
	
	private Integer cantJugadores;
	private List<Jugador> jugadores;
	private Jugador jugadorActual;
	
	private JButton botones[] = new JButton[4];
	private Game logicaJuego;
	
	private AudioInputStream ais;
	Clip clip;
	private JTable puntajes;
	private Consola consola;
	
	public Juego(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
		this.cantJugadores = jugadores.size();
		
		this.crearTablas();
		this.crearBotones();
		this.crearPilas();

		this.logicaJuego = new Game(jugadores);
		
		setLayout(null);
		setBounds(100, 100, 1920, 1080);
		
		this.consola = new Consola(760,754,new JTextArea());
		add(this.consola);
		
		try {
			ais = AudioSystem.getAudioInputStream(getClass().getResource("musicaFondo.wav"));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.setFramePosition(0);
			clip.start();
			clip.loop(100);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void musica() {
		if(clip.isActive())
			clip.stop();
		else
			clip.start();
	}
	
	public void iniciar() {
		
		consola.escribir("Bienvenidos a Kingdomino!");
		this.jugadores = this.logicaJuego.inicializar(this.pilaSig);
		consola.escribir("El jugador " + this.jugadores.get(0).getNickName() + " tuvo suerte y le toca primero!");
	}

	private void crearBotones() {
		
		int offset = 0;
		for (int i = 0; i < botones.length; i++) {
			
			this.botones[i] = new JButton();   
			this.botones[i].setVisible(false);
			this.botones[i].setBounds(630, 150+offset, 50, 50);
			add(this.botones[i]);
			
			offset += 90;
		}
		
		JButton btnNewButton = new JButton("Musica");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				musica();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnNewButton.setBounds(906, 11, 89, 23);
		add(btnNewButton);
		
		JButton descarte = new JButton("Descartar ficha");
		descarte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(logicaJuego.getEnTablero())
					avanzar();
				else
					consola.escribir("Solo utilizable cuando necesites descartar una ficha");
			}
		});
		
		descarte.setBounds(885,680,150,40);
		add(descarte);
	}
	
	private void crearPilas() {
		
		JLabel lblPilaActual = new JLabel("PILA DE ROBO ACTUAL");
		lblPilaActual.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		lblPilaActual.setBounds(702, 87, 241, 32);
		add(lblPilaActual);
		
		JLabel lblPilaSig = new JLabel("PILA DE ROBO SIGUIENTE");
		lblPilaSig.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		lblPilaSig.setBounds(996, 87, 241, 32);
		add(lblPilaSig);
		
		this.pilaAct = new PilaTable(1020,130);
		this.pilaAct.setVisible(false);

		this.pilaSig = new PilaTable(720,130);
		this.agregarListener();
		
		add(this.pilaAct);
		add(this.pilaSig);
		
	}
	
	private void crearTablas() {
		jugadores.get(0).setTurno(true);
		
		this.tableros = new JTable[this.cantJugadores];
		
		int [][] posicionesTablero = {
								{10,11},
								{1460,11},
								{1460,560},
								{10,560}
		};
		
		int [][] posicionesNombres = {
								{465,460},
								{1380,460},
								{1380,540},
								{465,540}
		};
		
		String[] colores = {"rojo","azul","verde","amarillo"};
		
		int i = 0;
		for (Jugador jugador : jugadores) {
			
			this.tableros[i] = crearTabla(posicionesTablero[i][0],posicionesTablero[i][1], jugador.getColor(), jugador);
			
			jugador.getTablero().agregarCastillo("Fichas/castillo_"+ colores[i] +".jpg");
			jugador.agregarTable(this.tableros[i]);
			
			agregarCasillero(this.tableros[i], 2, 2, jugador.getTablero().getCastillo(), 0);

			
			JLabel nombreJugador = new JLabel(jugador.getNickName());
			nombreJugador.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			nombreJugador.setBounds(posicionesNombres[i][0],posicionesNombres[i][1], 241, 32);
			
			add(this.tableros[i]);
			add(nombreJugador);

			i++;
		}
		
		this.puntajes = this.crearPuntajes(860,510,Color.WHITE,this.cantJugadores);
		
		//table_1.setBounds(400,800,200,200);

	    add(this.puntajes);
		((PuntajesModel)this.puntajes.getModel()).actualizarPuntajes();
	}
	
	@SuppressWarnings("deprecation")
	private JTable crearPuntajes(int x,int y, Color color,int cantJugadores) {
		
		//DefaultTableModel model = new DefaultTableModel(cantJugadores,2);
		JTable table = new JTable(new PuntajesModel(this.jugadores)) {
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		
		table.setRowSelectionAllowed(false);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		table.enable(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(x, y, 200,30*cantJugadores);
		table.setBorder(new MatteBorder(2, 2, 2, 2, color));
		table.setBackground(new ColorUIResource(164, 95, 51));
		table.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		return table;
	}

	private JTable crearTabla(int x, int y, Color color, Jugador jugador) {
		DefaultTableModel model = new DefaultTableModel(5, 5);
		JTable table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		table.setRowSelectionAllowed(false);
		table.setRowHeight(90);
		table.getColumnModel().getColumn(0).setCellRenderer(new Renderer());
		table.getColumnModel().getColumn(1).setCellRenderer(new Renderer());
		table.getColumnModel().getColumn(2).setCellRenderer(new Renderer());
		table.getColumnModel().getColumn(3).setCellRenderer(new Renderer());
		table.getColumnModel().getColumn(4).setCellRenderer(new Renderer());
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(x, y, 450, 450);
		table.setBorder(new MatteBorder(2, 2, 2, 2, color));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				
				int row = table.rowAtPoint(event.getPoint());
				int col = table.columnAtPoint(event.getPoint());
				
				jugadorActual = logicaJuego.devolverTurno();
				
				if(!logicaJuego.getEnTablero()) {
					consola.escribir("No seleccionaste ficha todavia");
					//System.out.println("No seleccionaste ficha todavia");
					return;
				}

				if(!jugadorActual.equals(jugador)) {
					consola.escribir("Este tablero no le pertenece al jugador " + jugadorActual.getNickName());
					return;
				}
				
				Ficha f = logicaJuego.obtenerFicha(jugadorActual);
				
				if(!logicaJuego.intentarInsertar(jugadorActual)) {
					consola.escribir("No es posible insertar la ficha.");
					logicaJuego.activarPila();
					logicaJuego.desactivarTablero();
					return;
				}
				
				Casillero c1 = f.getCasilleros()[0];
				Casillero c2 = f.getCasilleros()[1];
				if (table.getValueAt(row, col) == null) {
					if (c1.getPosicion() != null) {
						
						c2.setPosicion(new Posicion(row,col));
						
						int x = c1.getPosicion().getX();
						int y = c1.getPosicion().getY();
						
						double angulo = 0;
						
						if(x == row) {
							if(y > col) {

								angulo = 180;
							}
							else {
								angulo = 0;
							}
						} else {
							if(x > row) {
								
								angulo = 270;
							}
							else {
								angulo = 90;
							}
						}
						
						System.out.println(angulo);

						System.out.println(x + " " + y);
						System.out.println(row + " " + col);
						
						if (sonConsecutivas(c1.getPosicion(),c2.getPosicion()) && logicaJuego.posicionarFicha(jugadorActual,f)) {

							c1.rotate(angulo);
							c2.rotate(angulo);
							table.setValueAt(c1, x, y);
							table.setValueAt(c2,row, col);

							consola.escribir("El jugador " + jugadorActual.getNickName() + " inserto su ficha");
							
							((PuntajesModel)puntajes.getModel()).actualizarPuntajes();
							
							if(!avanzar())
								return;
							
						} else {
							consola.escribir("Error, no son consecutivas");
							
							table.setValueAt(null, x, y);
							table.setValueAt(null, row, col);
							
							c1.setPosicion(null);
							c2.setPosicion(null);
							
						}
					}else 
						if (c1.getPosicion() == null && table.getValueAt(row, col) == null) {
							
							c1.setPosicion(new Posicion(row, col));
							table.setValueAt(c1, row, col);
						}
				} else {
					consola.escribir("Casillero ocupado");
				}

			}
		});

		return table;
	}
	
	public boolean avanzar() {
		
		if(!logicaJuego.esUltimaRonda()) {
			logicaJuego.desactivarTablero();
			logicaJuego.activarPila();								
			consola.escribir("Ahora te toca seleccionar una ficha de la pila de robo!");
		}
		else {

			logicaJuego.cambioDeTurno(jugadorActual,consola);
			
			if(logicaJuego.esfinRonda()) {
				
				consola.escribir("FIN JUEGO");
				return false;
			}
		}
		return true;
	}

	public boolean sonConsecutivas(Posicion p1, Posicion p2) {
		
		int x = p1.getX();
		int y = p1.getY();
		int row = p2.getX();
		int col = p2.getY();
		
		return ( ( (Math.abs(x-row) == 1) && (y == col) ) || ( (Math.abs(y-col) == 1) && (x == row) ) );
	}
	
	public void actualizarMarcaFichaPila() {
		Set<Entry<Ficha, Jugador>> jugadoresAux = logicaJuego.getEntryJugadoresPorFicha();
		int pos = 0;
		for (Entry<Ficha, Jugador> entry : jugadoresAux) {
			Jugador jugador = entry.getValue();
			JButton boton = botones[pos];
			if(jugador != null) {
				boton.setVisible(true);
				boton.setIcon(escalarImagen(jugador.getTablero().getCastillo().getImagen(), 50, 50));
				boton.setBorder(new MatteBorder(2, 2, 2, 2, jugador.getColor()));			
			} else
				boton.setVisible(false);
			pos++;
		}
	}
	

	public void agregarCasillero(JTable tabla, int x, int y, Casillero casillero, double rotacion) {
		if (casillero == null) {
			tabla.getModel().setValueAt(null, x, y);
			return;
		}

		casillero.rotate(rotacion);
		tabla.getModel().setValueAt(casillero, x, y);
	}

	public ImageIcon escalarImagen(Image imagen, int width, int heigth) {
		return new ImageIcon(imagen.getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH));
	}
	
	public void agregarFicha(Ficha f) {
		if(!this.logicaJuego.agregarFicha(jugadorActual,f,this.consola))
			this.consola.escribir("Ficha ocupada");
		
	}
	
	private void agregarListener() {
		this.pilaSig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {

				int row = pilaSig.rowAtPoint(event.getPoint());
				int col = pilaSig.columnAtPoint(event.getPoint());

				if(!logicaJuego.getEnPila()) {
					consola.escribir("No se puede seleccionar fichas ya!");
					return;
				}
				
				
				jugadorActual = logicaJuego.devolverTurno();
				
				if (!logicaJuego.getEnTablero() && pilaSig.getValueAt(row, col) != null) {
					agregarFicha(((PilaModel)pilaSig.getModel()).getFichaAt(row,col));
					//((PuntajesModel)puntajes.getModel()).actualizarPuntajes();
				}
				else
					consola.escribir("Pila no accesible todavia");
				
				if(logicaJuego.esfinRonda()) {
					
					if(logicaJuego.esRondaPreliminar()) {
						dibujarPilaSig();
					}
					else {
						consola.escribir("-------------Fin de ronda " + logicaJuego.getRonda() +  "-------------");						
					}
					
					logicaJuego.cambiarRonda(pilaAct,pilaSig);

					consola.escribir("-----------Comienzo de ronda " + logicaJuego.getRonda() + "-----------");
					consola.escribir("Es el momento de insertar las fichas seleccionadas!");
					actualizarMarcaFichaPila();
					
					if(logicaJuego.esUltimaRonda()) {
						pilaSig.setVisible(false);
					}
				}
			}
		});
	}

	public void dibujarPilaSig() {
		
		this.pilaSig.setBounds(1020, 130, 180, 360);
		this.pilaAct.setBounds(720, 130, 180, 360);
		this.pilaAct.setVisible(true);
	}
}
