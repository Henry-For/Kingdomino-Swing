import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTable;

public class Game {
	
	private ArrayList<Jugador> jugadores;
	private final static int CANT_RONDAS = 2;
	private PilaDeRobo pilaDeRoboActual;
	private PilaDeRobo pilaDeRoboSiguiente;
	private Mazo mazo;
	//Iterator<Entry<Ficha,Jugador>> ronda;
	private int turno;
	private int ronda;
	private Iterator<Jugador> jugadoresActuales;
	private boolean enTablero;
	private boolean enPila;
	
	public Game(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
		this.mazo = Archivo.generarMazo("src/fichas.txt");
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
	
	public List<Jugador> inicializar(JTable siguiente) {
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
		
		if(!pilaDeRoboSiguiente.asignarFicha(f, j))
			return;
		
		System.out.println("El jugador " + j.getNickName() + " seleccionó la ficha " + pilaDeRoboSiguiente.getFichasRonda().ceilingKey(f));
		this.turno++;
		j.setTurno(false);
		j.getPuntaje();
		if(this.jugadoresActuales.hasNext())
			this.jugadoresActuales.next().setTurno(true);
		
		if(this.ronda != 0) {
			this.activarTablero();
			this.desactivarPila();
		}
		//else
		//	this.cambiarRonda();
	}
	
	public void cambiarRonda(JTable actual,JTable siguiente) {
		
		if(this.ronda != 0)
			System.out.println("\n--------Fin ronda " + this.ronda +"----------\n");
		this.ronda++;
		System.out.println("\n------Comienzo ronda " + this.ronda +"-------\n");
		if(this.ronda == Game.CANT_RONDAS+1) {
			this.desactivarPila();
			this.desactivarTablero();
			System.out.println("FIN JUEGO");
			this.obtenerGanadores();
			//Jugador.mostrarJugadores(jugadores);
			return;
		}
		
		this.pilaDeRoboActual = this.pilaDeRoboSiguiente;
		//this.pilaDeRoboActual.redibujar();
		this.pilaDeRoboActual.setTable(actual);
		this.pilaDeRoboActual.redibujar();
		//this.pilaDeRoboActual.almacenarFichas(new ArrayList<Ficha>(this.pilaDeRoboSiguiente.getFichasRonda().keySet()));
		
		this.jugadoresActuales = this.pilaDeRoboActual.getJugadoresOrdenados().iterator();
		
		this.pilaDeRoboSiguiente = new PilaDeRobo();
		this.pilaDeRoboSiguiente.setTable(siguiente);
		this.pilaDeRoboSiguiente.almacenarFichas(this.mazo.devolverFichas());
		this.pilaDeRoboSiguiente.redibujar();
		
		Jugador aux = this.jugadoresActuales.next();
		aux.setTurno(true);
		
		System.out.println("\nEl que comienza la ronda es el jugador " + aux.getNickName());
		
		this.turno = 0;
		this.enTablero = true;
		this.enPila = false;
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

	public Set<Entry<Ficha, Jugador>> getEntryJugadoresPorFicha() {
		return pilaDeRoboActual.getEntryJugadores();
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
	
	public void desactivarPila() {
		this.enPila = false;
	}

	public void activarTablero() {
		this.enTablero = true;
	}
	
	public void desactivarTablero() {
		this.enTablero = false;
	}
	
	public boolean esRondaPreliminar() {
		return this.ronda == 0;
	}
}
