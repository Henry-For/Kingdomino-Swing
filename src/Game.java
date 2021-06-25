import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTable;

public class Game {
	
	private ArrayList<Jugador> jugadores;
	private final static int CANT_RONDAS = 12;
	private PilaDeRobo pilaDeRoboActual;
	private PilaDeRobo pilaDeRoboSiguiente;
	private Mazo mazo;
	//Iterator<Entry<Ficha,Jugador>> ronda;
	private int turno;
	private int ronda;
	private Iterator<Jugador> jugadoresActuales;
	private boolean enTablero;
	private boolean enPila;
	
	public Game(ArrayList<Jugador> jugadores, Mazo mazo) {
		this.jugadores = jugadores;
		this.mazo = mazo;
		this.pilaDeRoboSiguiente = new PilaDeRobo();
	}

/*	public void ejecutarJuego() {
		mazo.mezclarMazo();
		
		//ejecutarPrimerRonda();
	
		for (int i=0; i<CANT_RONDAS; i++) {
			System.out.println("Ronda " + (i+1) + "\nLos jugadores posicionan fichas");
			this.ejecutarRonda();
			System.out.println("---------------------------------------------\n");
			this.pilaDeRoboActual = this.pilaDeRoboSiguiente;
		}
		this.obtenerGanadores();
		Jugador.mostrarJugadores(jugadores);
	}
*/	
	// Se encarga de asignar los primeros turnos de manera aleatoria. 
	
	public List<Jugador> inicializar(JTable actual,JTable siguiente) {
		this.mazo.mezclarMazo();
		
		pilaDeRoboSiguiente.setTable(siguiente);
		
		pilaDeRoboSiguiente.almacenarFichas(mazo.devolverFichas());
		
//		Jugador.mezclarJugadores(jugadores);
	
		this.jugadoresActuales = this.jugadores.iterator();
		this.jugadoresActuales.next().setTurno(true);
//		int i = 0;
//		for (Jugador jugador : jugadores) {
//			Ficha fichaElegida;
//			do
//			{
//				fichaElegida= jugador.seleccionarFicha(pilaDeRoboActual,i);			
//			}while(pilaDeRoboActual.asignarFicha(fichaElegida, jugador) == false);
//			i++;
//		}
//		this.pilaDeRoboActual = this.pilaDeRoboSiguiente;
		this.turno = 0;
		this.ronda = 0;
		this.enTablero = false;
		this.enPila = true;
		//this.cambiarRonda(actual, siguiente);
		return this.jugadores;
	}
	
	public List<Jugador> devolverJugadores() {
		return this.jugadores;
	}
	
	public void agregarFicha(Jugador j,Ficha f) {
		pilaDeRoboSiguiente.asignarFicha(f, j);
		System.out.println("El jugador " + j.getNickName() + " seleccionó una ficha");
		this.turno++;
		j.setTurno(false);
		
		if(this.jugadoresActuales.hasNext())
			this.jugadoresActuales.next().setTurno(true);
		//else
		//	this.cambiarRonda();
	}
	
	public void cambiarRonda(JTable actual,JTable siguiente) {
		
		System.out.println("\n------Cambio de ronda-------\n");
		this.ronda++;
		
		if(this.ronda == Game.CANT_RONDAS) {
			System.out.println("FIN JUEGO");
			return;
		}
		
		this.pilaDeRoboActual = this.pilaDeRoboSiguiente;
		this.pilaDeRoboActual.setTable(actual);
		//this.pilaDeRoboActual.almacenarFichas(new ArrayList<Ficha>(this.pilaDeRoboActual.getFichasRonda().keySet()));
		
		this.jugadoresActuales = this.jugadores.iterator();//this.pilaDeRoboActual.getJugadoresOrdenados();
		
		this.pilaDeRoboSiguiente = new PilaDeRobo();
		this.pilaDeRoboSiguiente.setTable(siguiente);
		this.pilaDeRoboSiguiente.almacenarFichas(this.mazo.devolverFichas());
	
		this.jugadoresActuales.next().setTurno(true);
		this.turno = 0;
		
	}
	
	public Ficha obtenerFicha(Jugador j) {
		for (Entry<Ficha, Jugador> set : pilaDeRoboActual.getFichasRonda().entrySet()) {
			if(set.getValue() != null && set.getValue().equals(j))
				return set.getKey();
		}
		return null;
	}
	
	public Jugador devolverTurno() {
		for (Jugador jugador : jugadores) {
			if(jugador.getTurno())
				return jugador;
		}
		
		return null;
	}
	
/*	public void ejecutarRonda() {

		this.pilaDeRoboSiguiente = new PilaDeRobo();
		pilaDeRoboSiguiente.almacenarFichas(mazo.devolverFichas());
		
		this.pilaDeRoboActual.mostrarOrdenJugadores();
		
		for (Entry<Ficha, Jugador> set : pilaDeRoboActual.getFichasRonda().entrySet()) {
			Jugador jugador = set.getValue();
			if(jugador != null) {
				System.out.println("\n===================================================");
				if(jugador.elegirPosicionFicha(set.getKey()))
				{
					System.out.println(jugador.getNickName() + " ficha " + set.getKey() + "\ninsertada" );
				}
				else
				{
					System.out.println(jugador.getNickName() + " ficha no insertada");
				}
				Ficha fichaElegida;
				do
				{
					fichaElegida = jugador.seleccionarFicha(pilaDeRoboSiguiente);					
				}while(pilaDeRoboSiguiente.asignarFicha(fichaElegida, jugador) == false);
				
				//System.out.println("Tu ficha asignada es: " + fichaElegida);
			}
		}
	}
*/	
	public void obtenerGanadores() {
		Jugador.ordenarJugadoresPuntaje(jugadores);
	}

	public boolean esfinRonda() {
		return this.turno == this.jugadores.size();
	}
	
	public boolean getEnTablero() {
		return this.enTablero;
	}

	public boolean getEnPila() {
		return enPila;
	}
	
	public void activarPila() {
		this.enPila = true;
	}
	
}
