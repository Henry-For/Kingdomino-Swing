import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTable;

public class Game {
	
	private ArrayList<Jugador> jugadores;
	private final static int CANT_RONDAS = 12;
	private PilaDeRobo pilaDeRoboActual;
	private PilaDeRobo pilaDeRoboSiguiente;
	private Mazo mazo;
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
		
		Jugador.mezclarJugadores(jugadores);
	
		this.jugadoresActuales = this.jugadores.iterator();
		this.jugadoresActuales.next().setTurno(true);
		
		this.turno = 0;
		this.ronda = 0;
		this.enTablero = false;
		this.enPila = true;
		
		return this.jugadores;
	}
	
	public List<Jugador> devolverJugadores() {
		return this.jugadores;
	}
	
	public boolean agregarFicha(Jugador j,Ficha f,Consola consola) {
		
		if(!pilaDeRoboSiguiente.asignarFicha(f, j))
			return false;
		
		consola.escribir("El jugador " + j.getNickName() + " seleccionó una ficha");
		
		//j.obtenerPuntaje();
		
		this.cambioDeTurno(j,consola);
		
		if(this.ronda != 0) {
			this.activarTablero();
			this.desactivarPila();
		}
		
		return true;
	}
	
	public void cambioDeTurno(Jugador j,Consola consola) {
		
		this.turno++;
		
		j.getPuntaje();
		j.setTurno(false);

		if(this.jugadoresActuales.hasNext()) {
			j = this.jugadoresActuales.next();
			j.setTurno(true);
			consola.escribir("Turno del jugador " + j.getNickName());
		}
		
	}
	
	public void cambiarRonda(JTable actual,JTable siguiente) {
		
		if(this.ronda != 0)
			System.out.println("\n--------Fin ronda " + this.ronda +"----------\n");
		this.ronda++;
		System.out.println("\n------Comienzo ronda " + this.ronda +"-------\n");
		
		if(this.esFinJuego()) {
			this.desactivarPila();
			this.desactivarTablero();
			this.obtenerGanadores();
			Jugador.mostrarJugadores(jugadores);
			return;
		}
		
		this.pilaDeRoboActual = this.pilaDeRoboSiguiente;

		this.pilaDeRoboActual.setTable(actual);
		this.pilaDeRoboActual.redibujar();
		
		this.jugadoresActuales = this.pilaDeRoboActual.getJugadoresOrdenados().iterator();
		
		if(this.ronda != Game.CANT_RONDAS) {
			this.pilaDeRoboSiguiente = new PilaDeRobo();
			this.pilaDeRoboSiguiente.setTable(siguiente);
			this.pilaDeRoboSiguiente.almacenarFichas(this.mazo.devolverFichas());
			this.pilaDeRoboSiguiente.redibujar();			
		}
		Jugador primero = this.jugadoresActuales.next();
		primero.setTurno(true);
		
		//System.out.println("\nEl que comienza la ronda es el jugador " + aux.getNickName());
		
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
	
	public boolean esUltimaRonda() {
		return this.ronda == Game.CANT_RONDAS;
	}

	public boolean intentarInsertar(Jugador jugadorActual) {
		return jugadorActual.getTablero().hayEspacioDisponible();
	}

	public int getRonda() {
		return this.ronda;
	}

	public boolean esFinJuego() {
		return this.ronda == Game.CANT_RONDAS+1;
	}
	
	public boolean posicionarFicha(Jugador j,Ficha f) {
		
		if(!j.getTablero().posicionar(f))
			return false;
		
		j.obtenerPuntaje();
		
		return true;
	}
}
