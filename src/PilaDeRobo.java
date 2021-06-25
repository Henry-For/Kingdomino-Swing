import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTable;

public class PilaDeRobo {
	
	private TreeMap<Ficha, Jugador> fichasRonda;
	private JTable tabla;
	
	public PilaDeRobo() {
		fichasRonda = new TreeMap<Ficha, Jugador>();
	}
		
	public void almacenarFichas(List<Ficha> f) {
		
		for (Ficha ficha : f) {
			fichasRonda.put(ficha, null);
			((PilaModel)this.tabla.getModel()).insertarFicha(ficha);
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

	public void setTable(JTable pilaRobo) {
		this.tabla = pilaRobo;
		//if(this.tabla != null)
		//	((PilaModel)this.tabla.getModel()).limpiar();
	}
	
	public List<Jugador> getJugadoresOrdenados() {
		List<Jugador> jugadores = new LinkedList<Jugador>();
		
		for (Jugador jugador : this.fichasRonda.values()) {
			if(jugador != null) 
				jugadores.add(jugador);
		}
		
		return jugadores;
	}
	
	public Set<Entry<Ficha,Jugador>> getEntryJugadores() {
		return fichasRonda.entrySet();
	}
	
	public void redibujar() {
		PilaModel p = ((PilaModel)this.tabla.getModel());
		
		p.limpiarLista();
		p.fireTableDataChanged();
		
		
		for (Ficha ficha : this.fichasRonda.keySet()) {
			p.insertarFicha(ficha);
		}
	}
}