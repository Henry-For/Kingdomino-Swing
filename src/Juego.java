import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.border.MatteBorder;
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

public class Juego extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table1;
	private JTable table2;
	private JTable table3;
	private JTable table4;
	private JTable pilaAct;
	private JTable pilaSig = null;
	private Integer cantJugadores;
	private List<Jugador> jugadores;
	private Jugador jugadorActual;
	private Game logicaJuego;
	private JButton botones[] = new JButton[4];

	public Juego(ArrayList<Jugador> jugadores, Mazo mazo) {
		this.jugadores = jugadores;
		cantJugadores = jugadores.size();
		
		this.logicaJuego = new Game(jugadores);

		setLayout(null);
		setBounds(100, 100, 1920, 1080);
	}

	public void crearJuego() {

		crearTablas();

		JLabel lblPilaActual = new JLabel("PILA DE ROBO ACTUAL");
		lblPilaActual.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		lblPilaActual.setBounds(702, 87, 241, 32);
		add(lblPilaActual);

		JLabel lblPilaSig = new JLabel("PILA DE ROBO SIGUIENTE");
		lblPilaSig.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		lblPilaSig.setBounds(996, 87, 241, 32);
		add(lblPilaSig);
		
		this.pilaAct = crearPila(720, 130, true);
		this.pilaSig = crearPila(1020, 130, false);
		
		add(this.pilaAct);
		add(this.pilaSig);
		
		JButton boton1 = new JButton();   
		boton1.setVisible(false);
		boton1.setBounds(630, 150, 50, 50);
		add(boton1);
		
		JButton boton2 = new JButton();   
		boton2.setVisible(false);
		boton2.setBounds(630, 240, 50, 50);
		add(boton2);
		
		JButton boton3 = new JButton();   
		boton3.setVisible(false);
		boton3.setBounds(630, 330, 50, 50);
		add(boton3);
		
		JButton boton4 = new JButton();   
		boton4.setVisible(false);
		boton4.setBounds(630, 420, 50, 50);
		add(boton4);
		
		botones[0] = boton1;
		botones[1] = boton2;
		botones[2] = boton3;
		botones[3] = boton4;
	    
		//this.pilaSig = crearPila(1020, 130);
		
		this.jugadores = this.logicaJuego.inicializar(pilaSig);
		//this.enPila = true;
		//this.jugadoresRonda = this.logicaJuego.devolverJugadores();
	}

	@SuppressWarnings("deprecation")
	private JTable crearTabla(int x, int y, Color color, Jugador jugador) {
		DefaultTableModel model = new DefaultTableModel(5, 5);
		JTable table = new JTable(model);
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
		table.enable(false);

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
						
						//seleccionado[1].setPosicion(new Posicion(row,col));
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

						//Ficha f = new Ficha(1, seleccionado);
						Posicion pos1 = new Posicion(x, y);
						Posicion pos2 = new Posicion(row, col);
						if ((((x + 1 == row || x - 1 == row) && (y == col)) || ((y + 1 == col || y - 1 == col) && (x == row))) && jugador.getTablero().posicionarFicha(f, pos1, pos2)) {

							//System.out.println(angulo);
							c1.rotate(angulo);
							c2.rotate(angulo);
							table.setValueAt(c1, x, y);
							table.setValueAt(c2,row, col);
							//jugadorActual.setTurno(false);
							//jugadores.get(1).setTurno(true);
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

//				if(!jugadoresrondaactual.hasnext()) {
//					//system.out.println("iniciando ubicacion de fichas");
//					while(jugadoresrondaactual.previousindex() > 0) {
//						jugadoresrondaactual.previous();
//					}
//					//logicajuego.cambiarronda();
//				}
				//System.out.println(row + " " + col);
				//System.out.println(table.getValueAt(row, col));
			}
		});

		return table;
	}

	private void crearTablas() {
		System.out.println(this.cantJugadores);
		table1 = crearTabla(10, 11, jugadores.get(0).getColor(), jugadores.get(0));
		jugadores.get(0).setTurno(true);
		jugadores.get(0).getTablero().agregarCastillo("Fichas/castillo_rojo.jpg");
		jugadores.get(0).agregarTable(table1);
		
		add(table1);
		agregarCasillero(table1, 2, 2, jugadores.get(0).getTablero().getCastillo(), 0);

		JLabel nombreJugador1 = new JLabel(jugadores.get(0).getNickName());
		nombreJugador1.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		nombreJugador1.setBounds(465, 460, 241, 32);
		add(nombreJugador1);

		table2 = crearTabla(1460, 11, jugadores.get(1).getColor(), jugadores.get(1));
		jugadores.get(1).getTablero().agregarCastillo("Fichas/castillo_azul.jpg");
		jugadores.get(1).agregarTable(table2);
		add(table2);
		agregarCasillero(table2, 2, 2, jugadores.get(1).getTablero().getCastillo(), 0);

		JLabel nombreJugador2 = new JLabel(jugadores.get(1).getNickName());
		nombreJugador2.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		nombreJugador2.setBounds(1380, 460, 241, 32);
		add(nombreJugador2);

		if (this.cantJugadores >= 3) {
			table3 = crearTabla(1460, 560, jugadores.get(2).getColor(), jugadores.get(2));
			add(table3);
			jugadores.get(2).getTablero().agregarCastillo("Fichas/castillo_verde.jpg");
			agregarCasillero(table3, 2, 2, jugadores.get(2).getTablero().getCastillo(), 0);

			JLabel nombreJugador3 = new JLabel(jugadores.get(2).getNickName());
			nombreJugador3.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			nombreJugador3.setBounds(1380, 540, 241, 32);
			add(nombreJugador3);
		}

		if (this.cantJugadores == 4) {
			table4 = crearTabla(10, 560, jugadores.get(3).getColor(), jugadores.get(3));
			add(table4);
			jugadores.get(3).getTablero().agregarCastillo("Fichas/castillo_amarillo.jpg");
			agregarCasillero(table4, 2, 2, jugadores.get(3).getTablero().getCastillo(), 0);

			JLabel nombreJugador4 = new JLabel(jugadores.get(3).getNickName());
			nombreJugador4.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			nombreJugador4.setBounds(465, 540, 241, 32);
			add(nombreJugador4);
		}
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

	public void setearPila(JTable tabla, List<Ficha> fichasPila) {
		for (int i = 0; i < fichasPila.size(); i++) {
			agregarCasillero(tabla, i, 0, fichasPila.get(i).getCasilleros()[0], 0);
			agregarCasillero(tabla, i, 1, fichasPila.get(i).getCasilleros()[1], 0);
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

	public void agregarFicha(Casillero c1, Casillero c2,int pos) {
		//this.seleccionado = new Casillero[] { c1, c2 };
		//Ficha f = new Ficha(new Casillero[] { c1, c2 });
		//f.setCasilleros(seleccionado);
		//this.logicaJuego.agregarFicha(this.jugadoresRonda.get(0),new Casillero[] { c1, c2 },pos);
	}
	
	public void agregarFicha(Ficha f) {
		this.logicaJuego.agregarFicha(jugadorActual,f);
	}

	@SuppressWarnings("deprecation")
	private JTable crearPila(int x, int y, boolean esActual) {
		//DefaultTableModel model = new DefaultTableModel(4, 2);
		PilaModel model = new PilaModel(this.cantJugadores);
		JTable table = new JTable(model);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(90);
		//table.setDefaultRenderer(getClass(), new Renderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new Renderer());
		table.getColumnModel().getColumn(1).setCellRenderer(new Renderer());
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(x, y, 180, 90*this.cantJugadores);/* ATENCION !!! AL CAMBIAR LA INTERFAZ ESTO SE CAMBIA */
		table.enable(false);

		if (!esActual) {
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {

					int row = table.rowAtPoint(event.getPoint());
					int col = table.columnAtPoint(event.getPoint());

				if(!logicaJuego.getEnPila()) {
					System.out.println("No se puede seleccionar fichas ya!");
					return;
				}
				
				
				/*if(!actual.verificar(table)) {
					System.out.println("Error: es el turno de " + actual.getNickName());
					jugadoresRondaActual.previous();
					return;
				}*/
				jugadorActual = logicaJuego.devolverTurno();
				
				if (!logicaJuego.getEnTablero() && table.getValueAt(row, col) != null) {
					agregarFicha(((PilaModel)table.getModel()).getFichaAt(row,col));
					//logicaJuego.activarTablero();
				}
					//agregarFicha((Casillero) table.getValueAt(row, 0), (Casillero) table.getValueAt(row, 1),row);
				else
					System.out.println("Pila no accesible todavia");
				
				if(logicaJuego.esfinRonda()) {
					logicaJuego.cambiarRonda(pilaAct,pilaSig);
					actualizarMarcaFichaPila();
					//enTablero = true;
				}
				
				//System.out.println(row + " " + col);
				//System.out.println(table.getValueAt(row, col));
				//System.out.println(seleccionado);
			}
		});

		}
		return table;
	}
}
