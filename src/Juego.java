import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.Graphics2D;

public class Juego extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table1;
	private JTable table2;
	private JTable table3;
	private JTable table4;
	private JTable pilaAct;
	private JTable pilaSig;
	private Integer cantJugadores;
	private Casillero[] seleccionado = null;
	//private int cantClicks = 0;
	//private boolean mutex = false;
	private boolean enTablero = false;
	private ArrayList<Jugador> jugadores;
	private Mazo mazo;

	public Juego(ArrayList<Jugador> jugadores, Mazo mazo) {
		this.jugadores = jugadores;
		cantJugadores = jugadores.size();
		System.out.println(cantJugadores);

		setLayout(null);
		setBounds(100, 100, 1920, 1080);
		this.mazo = mazo;
	}

	public void crearJuego() {

		crearTablas();

		JLabel lblNewLabel = new JLabel("PILA DE ROBO ACTUAL");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		lblNewLabel.setBounds(702, 87, 241, 32);
		add(lblNewLabel);

		JLabel lblPilaDeRobo = new JLabel("PILA DE ROBO SIGUIENTE");
		lblPilaDeRobo.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		lblPilaDeRobo.setBounds(996, 87, 241, 32);
		add(lblPilaDeRobo);

	}

	@SuppressWarnings("deprecation")
	private JTable crearPila(int x, int y) {
		DefaultTableModel model = new DefaultTableModel(4, 2);
		JTable table = new JTable(model);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(90);
		table.getColumnModel().getColumn(0).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(1).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(x, y, 180, 360);/* ATENCION !!! AL CAMBIAR LA INTERFAZ ESTO SE CAMBIA */
		table.enable(false);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {

				int row = table.rowAtPoint(event.getPoint());
				int col = table.columnAtPoint(event.getPoint());

				//int col1 = col == 0 ? 1 : 0;
				if (!enTablero && table.getValueAt(row, col) != null)
					agregarFicha((Casillero) table.getValueAt(row, 0), (Casillero) table.getValueAt(row, 1));
				else
					System.out.println("No se puede");
				System.out.println(row + " " + col);
				System.out.println(table.getValueAt(row, col));
				System.out.println(seleccionado);
			}
		});

		return table;
	}

	@SuppressWarnings("deprecation")
	private JTable crearTabla(int x, int y, Color color, Jugador jugador) {
		DefaultTableModel model = new DefaultTableModel(5, 5);
		JTable table = new JTable(model);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(90);
		table.getColumnModel().getColumn(0).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(1).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(2).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(3).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonCell());
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

				if (table.getValueAt(row, col) == null && jugador.getTurno()) {
					if (enTablero) {
						
						seleccionado[1].setPosicion(new Posicion(row,col));
						
						int x = seleccionado[0].getPosicion().getX();
						int y = seleccionado[0].getPosicion().getY();
						
						if(x == row) {
							if(y > col) {
								Casillero aux = seleccionado[0];
								seleccionado[0] = seleccionado[1];
								seleccionado[1] = aux;
							}							
						} else {
							if(x > row) {
								Casillero aux = seleccionado[0];
								seleccionado[0] = seleccionado[1];
								seleccionado[1] = aux;
							}
						}
						
						x = seleccionado[0].getPosicion().getX();
						y = seleccionado[0].getPosicion().getY();
						row = seleccionado[1].getPosicion().getX();
						col = seleccionado[1].getPosicion().getY();

						Ficha f = new Ficha(1, seleccionado);
						Posicion pos1 = new Posicion(x, y);
						Posicion pos2 = new Posicion(row, col);
						if ((((x + 1 == row || x - 1 == row) && (y == col)) || ((y + 1 == col || y - 1 == col) && (x == row))) && jugador.getTablero().posicionarFicha(f, pos1, pos2)) {

							double angulo = 0;
							if (col == y) {
								if (x > row) {
									angulo = 90;
								} else {
									angulo = 270;
								}
							} else {
								if (y > col) {
									angulo = 0;
								} else {
									angulo = 180;
								}
							}
							System.out.println(angulo);
							seleccionado[0].rotate(angulo);
							seleccionado[1].rotate(angulo);
							table.setValueAt(seleccionado[0], x, y);
							table.setValueAt(seleccionado[1], row, col);
							jugador.setTurno(false);
							jugadores.get(1).setTurno(true);
						} else {
							System.out.println("Error, no son consecutivas");
							table.setValueAt(null, x, y);
							table.setValueAt(null, row, col);
						}
						seleccionado = null;
						enTablero = false;
					} else if (seleccionado != null && table.getValueAt(row, col) == null) {
						seleccionado[0].setPosicion(new Posicion(row, col));
						table.setValueAt(seleccionado[0], row, col);
						enTablero = true;
					}
				} else {
					System.out.println("Casillero ocupada");
				}

				System.out.println(row + " " + col);
				System.out.println(table.getValueAt(row, col));
			}
		});

		return table;
	}

	private void crearTablas() {
		System.out.println(this.cantJugadores);
		table1 = crearTabla(10, 11, Color.RED, jugadores.get(0));
		jugadores.get(0).setTurno(true);
		jugadores.get(0).getTablero().agregarCastillo("Fichas/castillo_rojo.jpg");
		add(table1);
		agregarCasillero(table1, 2, 2, jugadores.get(0).getTablero().getCastillo(), 0);

		JLabel nombreJugador1 = new JLabel(jugadores.get(0).getNickName());
		nombreJugador1.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		nombreJugador1.setBounds(465, 460, 241, 32);
		add(nombreJugador1);

		table2 = crearTabla(1460, 11, Color.BLUE, jugadores.get(1));
		add(table2);
		jugadores.get(1).getTablero().agregarCastillo("Fichas/castillo_azul.jpg");
		agregarCasillero(table2, 2, 2, jugadores.get(1).getTablero().getCastillo(), 0);

		JLabel nombreJugador2 = new JLabel(jugadores.get(1).getNickName());
		nombreJugador2.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		nombreJugador2.setBounds(1380, 460, 241, 32);
		add(nombreJugador2);

		if (this.cantJugadores >= 3) {
			table3 = crearTabla(1460, 560, Color.GREEN, jugadores.get(2));
			add(table3);
			jugadores.get(2).getTablero().agregarCastillo("Fichas/castillo_verde.jpg");
			agregarCasillero(table3, 2, 2, jugadores.get(2).getTablero().getCastillo(), 0);

			JLabel nombreJugador3 = new JLabel(jugadores.get(2).getNickName());
			nombreJugador3.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			nombreJugador3.setBounds(1380, 540, 241, 32);
			add(nombreJugador3);
		}

		if (this.cantJugadores == 4) {
			table4 = crearTabla(10, 560, Color.YELLOW, jugadores.get(3));
			add(table4);
			jugadores.get(3).getTablero().agregarCastillo("Fichas/castillo_amarillo.jpg");
			agregarCasillero(table4, 2, 2, jugadores.get(3).getTablero().getCastillo(), 0);

			JLabel nombreJugador4 = new JLabel(jugadores.get(3).getNickName());
			nombreJugador4.setFont(new Font("Sylfaen", Font.PLAIN, 19));
			nombreJugador4.setBounds(465, 540, 241, 32);
			add(nombreJugador4);
		}

		pilaAct = crearPila(720, 130);
		pilaSig = crearPila(1020, 130);

		add(pilaAct);
		add(pilaSig);

		setearPila(pilaAct, mazo.devolverFichas());
		setearPila(pilaSig, mazo.devolverFichas());
		setearPila(pilaSig, mazo.devolverFichas());
		setearPila(pilaSig, mazo.devolverFichas());
		setearPila(pilaAct, mazo.devolverFichas());
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
		return new ImageIcon(imagen.getImage().getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));
	}

	public ImageIcon escalarImagen(Image imagen, int width, int heigth) {
		return new ImageIcon(imagen.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));
	}

	public void agregarFicha(Casillero c1, Casillero c2) {
		this.seleccionado = new Casillero[] { c1, c2 };
	}

	@SuppressWarnings("serial")
	private class ButtonCell extends AbstractCellEditor implements TableCellRenderer {

		private JLabel btn;

		ButtonCell() {
			btn = new JLabel();
		}

		@Override
		public Object getCellEditorValue() {
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (value instanceof Icon) {
				btn.setIcon((Icon) value);
				btn.setText(null);
			} else if (value instanceof Casillero) {
				ImageIcon icon = escalarImagen(((Casillero) value).getImagen(), 90, 90);
				btn.setIcon(icon);
				btn.setText(null);
			} else {
				btn.setIcon(null);
				btn.setText(null);
			}
			return btn;
		}
	}
}
