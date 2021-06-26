import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.awt.Font;

public class Juego extends JPanel {

	private JButton botonFinalizarJuego = new JButton("FinalizarJuego");
	
	private static final long serialVersionUID = 1L;
	private JTable[] tableros;
	
	private JTable pilaAct;
	private JTable pilaSig;
	
	private Integer cantJugadores;
	private List<Jugador> jugadores;
	private Jugador jugadorActual;
	
	private JButton botones[] = new JButton[4];
	private Game logicaJuego;

	public Juego(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
		this.cantJugadores = jugadores.size();
		botonFinalizarJuego.setBounds(124, 699, 146, 39);
		add(botonFinalizarJuego);
		this.crearTablas();
		this.crearBotones();
		this.crearPilas();
		
		this.logicaJuego = new Game(jugadores);

		setLayout(null);
		setBounds(100, 100, 1920, 1080);
	}
	
	public void iniciar() {
		
		this.jugadores = this.logicaJuego.inicializar(pilaSig);
		
		// Yo supongo que aca deberia funcionar no tuve manera de probarlo, pero deberian poner el doClick del boton en algun punto donde se termine el juego para que muestr el panel con los jugadores y poner en invisible el boton.
		botonFinalizarJuego.doClick();
	}
	
	public void botonIniciarJuegoActionListener(ActionListener listener) {
		botonFinalizarJuego.addActionListener(listener);
		
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
		
		this.pilaAct = new PilaTable(1020,130,this.cantJugadores);
		this.pilaAct.setVisible(false);

		this.pilaSig = new PilaTable(720,130,this.cantJugadores);
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
		
		int i = 0;
		for (Jugador jugador : jugadores) {
			
			this.tableros[i] = crearTabla(posicionesTablero[i][0],posicionesTablero[i][1], jugador.getColor(), jugador);
			
			jugador.getTablero().agregarCastillo("Fichas/castillo_rojo.jpg");
			jugador.agregarTable(this.tableros[i]);
			
			agregarCasillero(this.tableros[i], 2, 2, jugador.getTablero().getCastillo(), 0);

			
			JLabel nombreJugador = new JLabel(jugador.getNickName());
			nombreJugador.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			nombreJugador.setBounds(posicionesNombres[i][0],posicionesNombres[i][1], 241, 32);
			
			add(this.tableros[i]);
			add(nombreJugador);

			i++;
		}
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
					System.out.println("No seleccionaste ficha todavia");
					return;
				}

				if(!jugadorActual.equals(jugador)) {
					System.out.println("Este tablero no le pertenece al jugador " + jugadorActual.getNickName());
					return;
				}
				
				Ficha f = logicaJuego.obtenerFicha(jugadorActual);
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
								Casillero aux = c1;
								c1 = c2;
								c2 = aux;
								angulo = 180;
							}
							else {
								angulo = 0;
							}
						} else {
							if(x > row) {
								Casillero aux = c1;
								c1 = c2;
								c2 = aux;
								
								angulo = 270;
							}
							else {
								angulo = 90;
							}
						}
						
						x = c1.getPosicion().getX();
						y = c1.getPosicion().getY();
						row = c2.getPosicion().getX();
						col = c2.getPosicion().getY();

						Posicion pos1 = new Posicion(x, y);
						Posicion pos2 = new Posicion(row, col);
						if (sonConsecutivas(pos1,pos2) && jugador.getTablero().posicionarFicha(f, pos1, pos2)) {

							c1.rotate(angulo);
							c2.rotate(angulo);
							table.setValueAt(c1, x, y);
							table.setValueAt(c2,row, col);

							logicaJuego.desactivarTablero();
							logicaJuego.activarPila();
							
							System.out.println("El jugador " + jugadorActual.getNickName() + " inserto su ficha");
							System.out.println("Ahora te toca seleccionar una ficha de la pila de robo!");
						} else {
							System.out.println("Error, no son consecutivas");
							
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
					System.out.println("Casillero ocupado");
				}

			}
		});

		return table;
	}
	
	public boolean sonConsecutivas(Posicion p1, Posicion p2) {
		
		int x = p1.getX();
		int y = p1.getY();
		int row = p2.getX();
		int col = p2.getY();
		
		return (((x + 1 == row || x - 1 == row) && (y == col)) || ((y + 1 == col || y - 1 == col) && (x == row)));
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

	public ImageIcon escalarImagen(ImageIcon imagen, int width, int heigth) {
		return new ImageIcon(imagen.getImage().getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH));
	}

	public ImageIcon escalarImagen(Image imagen, int width, int heigth) {
		return new ImageIcon(imagen.getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH));
	}
	
	public void agregarFicha(Ficha f) {
		this.logicaJuego.agregarFicha(jugadorActual,f);
	}
	
	private void agregarListener() {
		this.pilaSig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {

				int row = pilaSig.rowAtPoint(event.getPoint());
				int col = pilaSig.columnAtPoint(event.getPoint());

				if(!logicaJuego.getEnPila()) {
					System.out.println("No se puede seleccionar fichas ya!");
					return;
				}
				
				
				jugadorActual = logicaJuego.devolverTurno();
				
				if (!logicaJuego.getEnTablero() && pilaSig.getValueAt(row, col) != null) {
					agregarFicha(((PilaModel)pilaSig.getModel()).getFichaAt(row,col));
				}
				else
					System.out.println("Pila no accesible todavia");
				
				if(logicaJuego.esfinRonda()) {
					
					if(logicaJuego.esRondaPreliminar()) {
						dibujarPilaSig();
					}
					
					logicaJuego.cambiarRonda(pilaAct,pilaSig);
					actualizarMarcaFichaPila();
				}
			}
		});
	}

	public void dibujarPilaSig() {
		
		this.pilaSig.setBounds(1020, 130, 180, 90*this.cantJugadores);
		this.pilaAct.setBounds(720, 130, 180, 90*this.cantJugadores);
		this.pilaAct.setVisible(true);
	}
}
