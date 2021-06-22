import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jugador implements Comparable<Jugador>{
	
	private int id;
	private static int cantidadJugadores= 0;
	private String nickName;
	private int puntaje;
	private Tablero tablero;
	private static Scanner tec;
	private boolean turno = false;
	
	public Jugador(String nickName) {
		this.nickName = nickName;
		cantidadJugadores++;
		this.id = cantidadJugadores;
		this.tablero = new Tablero();
		tec = new Scanner(System.in);
	}
	
	public Ficha seleccionarFicha(PilaDeRobo pr) {
		pr.mostrarFichas();
		
		System.out.print(this.nickName + " seleccione ficha: ");
		int opcion = tec.nextInt();
		
		List<Ficha> fichas = new ArrayList<Ficha>(pr.getFichasRonda().keySet());
		
		return fichas.get(opcion-1);
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
		Collections.sort(jugadores);
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
	
	public boolean getTurno() {
		return turno;
	}
	
	public void setTurno(boolean bool) {
		this.turno = bool;
	}
	
	public Tablero getTablero() {
		return tablero;
	}
}