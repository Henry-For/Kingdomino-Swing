import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JTable;

public class Jugador implements Comparable<Jugador>{
	
	private int id;
	private static int cantidadJugadores= 0;
	private String nickName;
	private int puntaje;
	private Tablero tablero;
	private static Scanner tec;
	private boolean turno = false;
	private Color color;
	
	public Jugador(String nickName, Color color) {
		this.nickName = nickName;
		cantidadJugadores++;
		this.id = cantidadJugadores;
		this.tablero = new Tablero();
		tec = new Scanner(System.in);
		this.color = color;
	}
	
	public Ficha seleccionarFicha(PilaDeRobo pr,int numFicha) {
		//pr.mostrarFichas();
		
		System.out.println(this.nickName + "seleccionó ficha: "+ numFicha);
		//int opcion = tec.nextInt();
		
		List<Ficha> fichas = new ArrayList<Ficha>(pr.getFichasRonda().keySet());
		
		return fichas.get(numFicha);
	}

	public boolean elegirPosicionFicha(Ficha f) {
		Posicion 	posCasillero1 = new Posicion(),
					posCasillero2 = new Posicion();
		do{
			System.out.println(this.nickName + " inserte la posicion de la ficha:");
			int x = tec.nextInt();
			int y = tec.nextInt();
			int x1 = tec.nextInt();
			int y1 = tec.nextInt();			
			posCasillero1.setX(x);
			posCasillero1.setY(y);
			posCasillero2.setX(x1);
			posCasillero2.setY(y1);
			
		}while(tablero.posicionarFicha(f, posCasillero1, posCasillero2) == false);
		
		return true; // Si devuelve falso la interfaz deberia marcar en rojo.
	}
	
	private void obtenerPuntaje() {
		
		this.puntaje = this.tablero.calcularPuntaje();
	}
	
	public int getPuntaje() {
		this.obtenerPuntaje();
		return this.puntaje;
	}

	public static void mezclarJugadores(ArrayList<Jugador> jugadores) {
		Collections.shuffle(jugadores, new Random());
	}
	
	public static void ordenarJugadoresPuntaje(ArrayList<Jugador> jugadores) {
		Collections.sort(jugadores,Collections.reverseOrder());
	}

	public String getNickName() {
		return this.nickName;
	}

	@Override
	public int compareTo(Jugador j2) {
		return this.puntaje - j2.puntaje;
	}
	
	public static void mostrarJugadores(ArrayList<Jugador> jugadores)
	{
		for (Jugador jugador : jugadores) {
			System.out.println(jugador);
		}
	}

	@Override
	public String toString() {
		return "Jugador [ "+"nickName=" + nickName + ", puntaje=" + puntaje + "]";
	}
	
	public String toStringPuntajes() {
		return nickName + "                      " + puntaje ;
	}
	
	public boolean getTurno() {
		return turno;
	}
	
	public void setTurno(boolean bool) {
		this.turno = bool;
	}
	
	public Tablero getTablero() {
		return tablero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + puntaje;
		result = prime * result + (turno ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Jugador other = (Jugador) obj;
		if (id != other.id)
			return false;
		
		return true;
	}
	
	public void agregarTable(JTable table) {
		this.tablero.setTable(table);
	}
	
	public boolean verificar(JTable table) {
		return this.tablero.getTable() == table;
	}

	public Color getColor() {
		return color;
	}
}