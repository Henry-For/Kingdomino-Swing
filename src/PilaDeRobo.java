import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

public class PilaDeRobo {
	
	private TreeMap<Ficha, Jugador> fichasRonda;
	
	public PilaDeRobo() {
		fichasRonda = new TreeMap<Ficha, Jugador>();
	}
		
	public void almacenarFichas(List<Ficha> f) {
		for (Ficha ficha : f) {
			fichasRonda.put(ficha, null);
		}
	}
	
	public boolean asignarFicha(Ficha f, Jugador j) {
		if(this.fichasRonda.get(f) != null)
		{
			System.out.println("Ficha ocupada");
			return false;
		}
		
		this.fichasRonda.put(this.fichasRonda.ceilingKey(f), j); // preguntar uso de ceilingkey
		return true;
	}
	
	public void mostrarFichas() {
		for (Ficha ficha : fichasRonda.keySet()) {
			System.out.println(ficha);
		}
	}

	public TreeMap<Ficha, Jugador> getFichasRonda() {
		return fichasRonda;
	}	
	
	public void mostrarOrdenJugadores()
	{
		System.out.println("Los jugadores tienen el siguiente orden:");
		for (Entry<Ficha, Jugador> set : this.fichasRonda.entrySet()) {
			if(set.getValue() != null)
				System.out.println("- " + set.getValue().getNickName());
		}
	}
}